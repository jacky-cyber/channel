package com.zjht.channel.manager.service.impl;

import static com.zjht.channel.manager.common.constant.NodeStatus.*;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zjht.channel.helper.common.AssertionHelper;
import com.zjht.channel.helper.common.JsonHelper;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.manager.common.constant.NodeStatus;
import com.zjht.channel.manager.exception.ApplicationException;
import com.zjht.channel.manager.exception.ZookeeperException;
import com.zjht.channel.manager.service.ConfigurationService;
import com.zjht.channel.manager.zookeeper.bean.Configuration;

/**
 * Function: 渠道系统配置信息处理类，负责整合本地数据库和发布zookeeper的数据配置. <br/>
 * date: Sep 18, 2015 3:04:00 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Service("ConfigurationService")
public class ConfigurationServiceImpl implements ConfigurationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    @Resource(name = "ZKConfigurationService")
    private ConfigurationService zkConfigService;

    @Resource(name = "DBConfigurationService")
    private ConfigurationService dbConfigService;

    /**
     * @see com.zjht.channel.manager.service.ConfigurationService#configure()
     */
    @Override
    public Configuration configure() {
        Configuration dbConfig = null;// 从数据库获取的配置信息
        Configuration zkConfig = null;// 从zookeeper获取的配置信息

        LOGGER.info("从zookeeper获取配置信息...");
        try {
            zkConfig = zkConfigService.configure();
        } catch (ZookeeperException e) {
            // 该异常为自定义异常，zookeepe上目录不存在等情况会抛出
            // 当系统没有任何配置时，zookeeper上没有目录是正常情况
            // 所以这里无需处理，简单打印日志信息即可
            // 其他异常都会抛出由上层统一处理
            LOGGER.info("从zookeeper获取配置失败，[{}]:[{}]", e.code(), e.message());
        }
        LOGGER.info("{}",JsonHelper.toJson(zkConfig));

        LOGGER.info("从数据库获取配置信息");
        try {
            dbConfig = dbConfigService.configure();
        } catch (ApplicationException e) {
            // 该异常为自定义异常，数据库中不存在记录等情况会抛出
            // 当系统没有任何配置时，数据库钟没有记录是正常情况
            // 所以这里无需处理，简单打印日志信息即可
            // 其他异常都会抛出由上层统一处理
            LOGGER.info("从数据库获取配置失败，[{}]:[{}]", e.code(), e.message());
        }
        LOGGER.info("{}",JsonHelper.toJson(dbConfig));

        LOGGER.info("合并配置信息...");
        return merge(dbConfig, zkConfig);
    }

    /**
     *
     * 合并db和zk中的配置信息，以第一个参数（即本地数据中的配置信息）为基础。<br/>
     * <b合并规则：</b><br/>
     * <pre>
     * 1. db & zk are null , return null;
     * 2. db is null,zk is not null, 设置zk节点的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.DELETED，return zk
     * 3. db is not null, zk is null, 设置db节点的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.ADDED
     * 4. db & zk are not null， 对比两者节点中的data是否一致：
     *  (1) 一致： 设置data为db的data，状态为@see com.zjht.channel.manager.common.constant.NodeStatus.UNCHANAGED
     *  (2)不一致：设置data为db的data，状态为@see com.zjht.channel.manager.common.constant.NodeStatus.UNCHANAGED
     * </pre>
     * 
     * 合并两个配置信息，以第一个参数（即本地数据中的配置信息）为基础。<br/>
     * <b>合并规则:</b><br/>
     * <pre>
     * 1. zk存在，db不存在，zk cofing node的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.DELETED
     * 2. zk不存在，db存在，db config node的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.ADDED
     * 3. zk存在，db存在，配置信息不一致，db config node的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.UPDATED
     * 4. zk存在，db存在，配置信息一致，db/zk config node的状态为@see com.zjht.channel.manager.common.constant.NodeStatus.UNCHANAGED
     * </pre>
     * 
     * @author jun
     * @param dbConfig 数据库生成的配置信息对象
     * @param zkConfig zookeeper生成的配置信息对象
     * @return
     * @since JDK 1.8
     */
    private Configuration merge(Configuration dbConfig, Configuration zkConfig) {
        LOGGER.debug("数据库中配置：{}",JsonHelper.toJson(dbConfig));
        LOGGER.debug("zookeeper中配置{}",JsonHelper.toJson(zkConfig));
        
        //1. db & zk are null
        if (Objects.isNull(dbConfig) && Objects.isNull(zkConfig)) {
            LOGGER.info("数据库和zookeeper中配置均为空");
            return null;
        }

        //2. db is null,zk is not null
        if (Objects.isNull(dbConfig)&&!Objects.isNull(zkConfig)) {
            LOGGER.info("数据库中配置为空，zookeeper不为空");
            return loopSetStatus(zkConfig, DELETED);
        }

        //3. db is not null, zk is null
        if (!Objects.isNull(dbConfig)&&Objects.isNull(zkConfig)) {
            LOGGER.info("zookeeper中配置为空，数据库不为空");
            return loopSetStatus(dbConfig, ADDED);
        }
        
        //4. db & zk are not null
        return mergeNode(dbConfig,zkConfig);
    }


    /**
     * 对比并合并配置节点，比对规则说明见
     * {@link com.zjht.channel.manager.service.impl.ConfigurationServiceImpl#merge()}
     * 
     * @author jun
     * @param dbConfig
     * @param zkConfig
     * @since JDK 1.8
     */
    private Configuration mergeNode(Configuration dbConfig, Configuration zkConfig) {
        //比较两边的配置节点的值，用于设置节点状态
        boolean isEuqalData = Objects.equals(dbConfig.getData(), zkConfig.getData());
        
        //合并后的结果对象
        Configuration config = new Configuration()
                .setNode(dbConfig.getNode())
                .setOriginalData(isEuqalData?"":zkConfig.getData())
                .setData(dbConfig.getData())
                .setStatus(isEuqalData?UNCHANAGED.code():UPDATED.code())
                .setChildren(mergeChildren(dbConfig, zkConfig));
        return config;
    }

    /**
     * 合并子节点,合并规则见{@link com.zjht.channel.manager.service.impl.ConfigurationServiceImpl#merge()}
     * 
     * @author jun
     * @param dbConfig
     * @param zkConfig
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> mergeChildren(Configuration dbConfig, Configuration zkConfig) {
        List<Configuration> dbChildrens = dbConfig.getChildren();
        List<Configuration> zkChildrens = zkConfig.getChildren();
        
        List<Configuration> mergeChildrens = ListHelper.newArrayList();

        if (ListHelper.isEmpty(dbChildrens)&&ListHelper.isEmpty(zkChildrens)) {
            //zk和db中config的config都为空
            return null;
        }
        
        // 如果db config没有子节点，则将zk config的所有节点置为NodeStatus.DELETED
        if (ListHelper.isEmpty(dbChildrens)&&!ListHelper.isEmpty(zkChildrens)) {
            zkChildrens.forEach(n -> {
                loopSetStatus(n, DELETED);
            });
            return zkChildrens;
        }
        
        // 如果zk config没有子节点，则将db config的所有子节点置为NodeStatus.ADDED
        if (!ListHelper.isEmpty(dbChildrens)&&ListHelper.isEmpty(zkChildrens)) {
            dbChildrens.forEach(n -> {
                loopSetStatus(n, ADDED);
            });
            return dbChildrens;
        }

        // 遍历处理db config
        //1. db在zk不存在的节点状态置为ADDED，并添加到合并集合中
        //2. 过滤已经在合并集合中存在的节点，即1步中添加的。
        //3. 遍历处理：只处理两边都存在的节点
        dbChildrens.stream()
            .filter(dbC->{
                return !Objects.isNull(dbC);
            })
            .map(dbC -> {
                //db在zk不存在的节点状态置为ADDED
                if (!zkChildrens.contains(dbC)) {
                    mergeChildrens.add(loopSetStatus(dbC,ADDED));
                }
                return dbC;
            })
            .filter(dbC -> !mergeChildrens.contains(dbC))
            .forEach(dbC -> {
                zkChildrens.stream()
                    .filter(zkC -> Objects.equals(dbC.getNode(), zkC.getNode()))
                    .forEach(zkC -> {
                        mergeChildrens.add(merge(dbC,zkC));
                    });
            });
        
        // 遍历处理zk config
        //1. zk在db不存在的节点状态置为DELETED，并添加到合并集合中
        //2. 过滤已经在合并集合中存在的节点，即1步中添加的。
        //3. 遍历处理：只处理两边都存在的节点
        zkChildrens.stream()
            .filter(zkC->{
                return !Objects.isNull(zkC);
            })
            .map(zkC -> {
                if (!dbChildrens.contains(zkC)) {
                    mergeChildrens.add(loopSetStatus(zkC,DELETED));
                }
                return zkC;
            })
            .filter(zkC -> !mergeChildrens.contains(zkC))
            .forEach(zkC -> {
                dbChildrens.stream()
                    .filter(dbC -> Objects.equals(zkC.getNode(), dbC.getNode()))
                    .forEach(dbC -> {
                        mergeChildrens.add(merge(dbC,zkC));
                    });
            });
        return mergeChildrens;
    }

    /**
     * 循环设置配置信息对象的状态
     * 
     * @author jun
     * @param config
     * @param nodeStatus
     * @return
     * @since JDK 1.8
     */
    private Configuration loopSetStatus(Configuration config, NodeStatus nodeStatus) {
        AssertionHelper.check(!Objects.isNull(config), "配置信息对象不能为空");
        
        List<Configuration> children = config.setStatus(nodeStatus.code()).getChildren(); 
        
        if(ListHelper.isEmpty(children)){
            return config;
        }
        
        children.forEach(n -> {
            n.setStatus(nodeStatus.code());
            if (!ListHelper.isEmpty(n.getChildren())) {
                loopSetStatus(n, nodeStatus);
            }
        });
        return config;
    }


    public static void main(String[] args) {
        ConfigurationServiceImpl csi = new ConfigurationServiceImpl();
        // System.out.println(">>Merge1=>" + JsonHelper.toJson(merge1(csi)));
        // System.out.println(">>Merge2=>" + JsonHelper.toJson(merge2(csi)));
        // System.out.println(">>Merge3=>" + JsonHelper.toJson(merge3(csi)));
        System.out.println(">>Merge4=>" + JsonHelper.toJson(merge4(csi)));
    }

    /**
     * db&zk config is not null
     * 
     * @author jun
     * @param csi
     * @return
     * @since JDK 1.8
     */
    private static Configuration merge4(ConfigurationServiceImpl csi) {
        Configuration dbConfig = new Configuration();;
        dbConfig.setNode("channel");
        dbConfig.setData("chanel data2");
        dbConfig.setChildren(
                ListHelper.listOf(new Configuration("inspection2", null, "inspection data"),
                        new Configuration("service", null, "service data")));

        Configuration zkConfig = new Configuration();
        zkConfig.setNode("channel");
        zkConfig.setData("chanel data");
        zkConfig.setChildren(
                ListHelper.listOf(new Configuration("inspection", null, "inspection data"),
                        new Configuration("service", null, "service data")));
        return csi.merge(dbConfig, zkConfig);
    }

    /**
     * db config is not null,zk config is null.
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private static Configuration merge3(ConfigurationServiceImpl csi) {
        Configuration dbConfig = new Configuration();;
        dbConfig.setNode("channel");
        dbConfig.setData("chanel data");
        dbConfig.setChildren(
                ListHelper.listOf(new Configuration("inspection", null, "inspection data"),
                        new Configuration("service", null, "service data")));

        Configuration zkConfig = null;

        return csi.merge(dbConfig, zkConfig);
    }

    /**
     * db config is null， zk config is not null.
     * 
     * @author jun
     * @param csi
     * @return
     * @since JDK 1.8
     */
    private static Configuration merge2(ConfigurationServiceImpl csi) {
        Configuration dbConfig = null;

        Configuration zkConfig = new Configuration();
        zkConfig.setNode("channel");
        zkConfig.setData("chanel data");
        zkConfig.setChildren(
                ListHelper.listOf(new Configuration("inspection", null, "inspection data"),
                        new Configuration("service", null, "service data")));

        return csi.merge(dbConfig, zkConfig);
    }

    /**
     * db config is null, zk config is null.
     * 
     * @author jun
     * @param csi
     * @return
     * @since JDK 1.8
     */
    private static Configuration merge1(ConfigurationServiceImpl csi) {
        Configuration dbConfig = null;
        Configuration zkConfig = null;
        return csi.merge(dbConfig, zkConfig);
    }

    /** 
     * @see com.zjht.channel.manager.service.ConfigurationService#publish(com.zjht.channel.manager.zookeeper.bean.Configuration) 
     */
    @Override
    public void publish(Configuration config) {
        AssertionHelper.check(!ObjectHelper.isNull(config), "发布的配置不能为空");
        LOGGER.info("发布配置:"+JsonHelper.toJson(config));
        zkConfigService.publish(config);
    }
}
