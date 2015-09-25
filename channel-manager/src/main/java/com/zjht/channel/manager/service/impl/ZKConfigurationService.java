package com.zjht.channel.manager.service.impl;

import static com.zjht.channel.manager.common.constant.ErrorMessage._00403;
import static com.zjht.channel.manager.common.constant.NodeStatus.ADDED;
import static com.zjht.channel.manager.common.constant.NodeStatus.DELETED;
import static com.zjht.channel.manager.common.constant.NodeStatus.UPDATED;
import static com.zjht.channel.manager.common.constant.Symbol.SLASH;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zjht.channel.helper.common.AssertionHelper;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;
import com.zjht.channel.manager.service.ConfigurationService;
import com.zjht.channel.manager.zookeeper.bean.Configuration;
import com.zjht.channel.manager.zookeeper.helper.ZKHelper;
import com.zjht.channel.manager.zookeeper.service.ZKClient;

/**
 * 渠道系统该配置在zookeeper具体操作类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 9:47:03 AM
 */
@Service("ZKConfigurationService")
public class ZKConfigurationService implements ConfigurationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKConfigurationService.class);
    
    @Resource(name = "ZKClient")
    private ZKClient zkClient;

    @Value("${zk.channel.namespace}")
    private String zkNamespace;


    /**
     * @see com.zjht.channel.manager.service.ConfigurationService#configure()
     */
    @Override
    public Configuration configure() {
        Configuration channel = null;
        try{
         channel = new Configuration()
                .setNode(zkNamespace)
                .setChildren(getChannelChildren())
                .setData(zkClient.getData("/"));
        }catch(Exception e){
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return channel;
    }

    /**
     * 
     * 创建下的子节点，即: <br/>
     * /insepction和/service
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getChannelChildren() {
        List<Configuration> channel = null;
        try {
            channel = ListHelper.listOf(
                        getInspectionNode(), 
                        getServiceNode());
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return channel;
    }

    /**
     * 获取/service节点数据. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private Configuration getServiceNode() {
        String path = "/service";
        Configuration service = null;
        try {
            service = new Configuration()
                    .setNode(ZKHelper.getNodeName(path))
                    .setChildren(getServiceChildren())
                    .setData(zkClient.getData(path));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return service;
    }

    /**
     * 获取/service的子节点数据. <br/>
     * zookeeper中数据存储结构:<br/>
     * /service/{service_name}/{service_version} <br/>
     * 
     * 服务名称和服务版本的关系:<br/>
     * service_name : service_version = 1 : n
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getServiceChildren() {
        String path = "/service";
        List<Configuration> service = Lists.newArrayList();
        service.add(buildServiceDubboNode());
        return service;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboNode() {
        Configuration dubbo = new Configuration()
                .setNode("dubbo")
                .setChildren(buildServiceDubboChildren())
                .setData("");
        return dubbo;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboChildren() {
        String path = "/service";
        List<Configuration> service = ListHelper.listOf(
                buildServiceDubboApplicationNode(),
                buildServiceDubboRegistryNode(),
                buildServiceDubboConsumerNode(),
                buildServiceDubboReferenceNode()
                );
        return service;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboReferenceNode() {
        Configuration reference = new Configuration()
                .setNode("reference")
                .setChildren(buildServiceDubboReferenceChildren())
                .setData("");
        return reference;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboReferenceChildren() {
        String path = "/service/dubbo/reference";
        List<Configuration> children = Lists.newArrayList();
        try {
            //服务名称
            zkClient.getChildren(path).forEach(n -> {
                children.add(new Configuration(ZKHelper.getNodeName(n),buildServiceDubboReferenceNameChildren(ZKHelper.createZPath(path,n)),zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboReferenceNameChildren(String path) {
        List<Configuration> children = Lists.newArrayList();
        try {
            //服务版本
            zkClient.getChildren(path).forEach(n -> {
                children.add(new Configuration(ZKHelper.getNodeName(n),buildServiceDubbpReferenceNameVersionChildren(ZKHelper.createZPath(path,n)),zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @param createZPath
     * @return 
     */  
    private List<Configuration> buildServiceDubbpReferenceNameVersionChildren(String path) {
        List<Configuration> children = Lists.newArrayList();
        try {
            //服务信息
            zkClient.getChildren(path).forEach(n -> {
                children.add(new Configuration(ZKHelper.getNodeName(n),null,zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboConsumerNode() {
        Configuration consumer = new Configuration()
                .setNode("consumer")
                .setChildren(buildServiceDubboConsumerChildre())
                .setData("");
        
        return consumer;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboConsumerChildre() {
        String path = "/service/dubbo/consumer";
        List<Configuration> children = Lists.newArrayList();
        try {
            zkClient.getChildren(path).forEach(n -> {
                children.add(new Configuration(ZKHelper.getNodeName(n),null,zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboRegistryNode() {
        Configuration registry = new Configuration()
                .setNode("registry")
                .setChildren(buildServiceDubboRegistryChildren())
                .setData("");
        return registry;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboRegistryChildren() {
        String path = "/service/dubbo/registry";
        List<Configuration> children = Lists.newArrayList();
        try {
            // 服务名称列表
            zkClient.getChildren(path).forEach(n -> {
                children.add(new Configuration(ZKHelper.getNodeName(n),null,zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboApplicationNode() {
        Configuration application  = new Configuration()
                .setNode("application")
                .setChildren(buildServiceDubboApplicationChildren())
                .setData("");
        return application;
    }

    /** 
     * TODO
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboApplicationChildren() {
        String path = "/service/dubbo/application";
        List<Configuration> application = Lists.newArrayList();
        try {
            // 服务名称列表
            zkClient.getChildren(path).forEach(n -> {
                application.add(new Configuration(ZKHelper.getNodeName(n),null,zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return application;
    }

    /** 
     * 获取/service/{serviceName}下的子节点，即服务版本
     * 
     * @author jun
     * @param path
     * @param name
     * @return 
     * @since JDK 1.8
     */  
    private List<Configuration> getSerNameChildren(String path, String name) {
        List<Configuration> children = new ArrayList<Configuration>() ;

        try {
            // 服务版本列表
            zkClient.getChildren(path.concat(SLASH.code()).concat(name)).forEach(version -> {
                children.add(new Configuration(ZKHelper.getNodeName(version),getSerNameVerionChildren(path,name,version), zkClient.getData(path.concat(SLASH.code()).concat(name).concat(SLASH.code())
                        .concat(version))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /** 
     * 获取/service/{name}/{version}下的子节点，即服务属性
     * 
     * @author jun
     * @param path
     * @param name
     * @param version
     * @return 
     * @since JDK 1.8
     */  
    private List<Configuration> getSerNameVerionChildren(String path, String name, String version) {
        
        List<Configuration> children = null;
        try {
            children =  ListHelper.listOf(
                    new Configuration("owner", null,
                            zkClient.getData(path.concat(SLASH.code()).concat(name)
                                    .concat(SLASH.code()).concat(version).concat(SLASH.code())
                                    .concat("owner"))),
                    new Configuration("organization", null,
                            zkClient.getData(path.concat(SLASH.code()).concat(name)
                                    .concat(SLASH.code()).concat(version).concat(SLASH.code())
                                    .concat("organization"))),
                    new Configuration("beanId", null,
                            zkClient.getData(path.concat(SLASH.code()).concat(name)
                                    .concat(SLASH.code()).concat(version).concat(SLASH.code())
                                    .concat("beanId"))),
                    new Configuration("fullyQualifiedName", null,
                            zkClient.getData(path.concat(SLASH.code()).concat(name)
                                    .concat(SLASH.code()).concat(version).concat(SLASH.code())
                                    .concat("fullyQualifiedName"))),
                    new Configuration("status", null,
                            zkClient.getData(path.concat(SLASH.code()).concat(name)
                                    .concat(SLASH.code()).concat(version).concat(SLASH.code())
                                    .concat("status"))));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return children;
    }

    /**
     * 创建/inspection节点. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private Configuration getInspectionNode() {
        String path = "/inspection";
        Configuration inspection = null;
        try {
            inspection = new Configuration()
                    .setNode(ZKHelper.getNodeName(path))
                    .setChildren(getInspectionChildren())
                    .setData(zkClient.getData(path));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return inspection;
    }

    /**
     * 创建/inspection的子节点. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInspectionChildren() {
        List<Configuration> inspection = null;
        try {
            inspection = ListHelper.listOf(
                    getInsWhiltelistsNode(),
                    getInsSecurityNode(), 
                    getInsPermissionNode());
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return inspection;
    }

    /**
     * 
     * 获取/inspection/permission节点数据. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private Configuration getInsPermissionNode() {
        String path = "/inspection/permission";
        Configuration permission = null;
        try {
            permission = new Configuration()
                    .setNode(ZKHelper.getNodeName(path))
                    .setChildren(getInsPermissionChildren())
                    .setData(zkClient.getData(path));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }  
        return permission;
    }

    /**
     * 获取/inspection/permission子节点数据. <br/>
     * zookeeper存储结构：<br/>
     * /inspection/permission/{appno}/{serviceName}/{version}
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInsPermissionChildren() {
        String path = "/inspection/permission";
        List<Configuration> permission = Lists.newArrayList();

        // 终端号
        zkClient.getChildren(path).forEach(appno -> {
            permission.add(new Configuration(ZKHelper.getNodeName(appno),
                    getInsPerAppnoChildren(path, appno),
                    zkClient.getData(path.concat(SLASH.code()).concat(appno))));
        });
        return permission;
    }

    /**
     * 
     * 获取/inspection/permission/{appno}的子节点，即服务名称列表
     * 
     * @author jun
     * @param path
     * @param appno {appno}
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInsPerAppnoChildren(String path, String appno) {
        List<Configuration> children = ListHelper.newArrayList();

        // 服务名称
        zkClient.getChildren(path.concat(SLASH.code()).concat(appno)).forEach(serName -> {
            children.add(new Configuration(ZKHelper.getNodeName(serName),
                    getInsPerAppnoSerChildren(path, appno, serName),
                    zkClient.getData(path.concat(SLASH.code()).concat(appno).concat(SLASH.code())
                            .concat(serName))));
        });
        return children;
    }

    /**
     * 
     * 获取/inspection/permission/{appno}/{serviceName}的子节点,即服务版本列表
     * 
     * @author jun
     * @param path
     * @param appno {appno}
     * @param serName {serviceName}
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInsPerAppnoSerChildren(String path, String appno,
            String serName) {
        List<Configuration> children = ListHelper.newArrayList();

        // 服务版本
        zkClient.getChildren(
                path.concat(SLASH.code()).concat(appno).concat(SLASH.code()).concat(serName))
                .forEach(version -> {
                    children.add(new Configuration(version, null,
                            zkClient.getData(path.concat(SLASH.code()).concat(appno)
                                    .concat(SLASH.code()).concat(serName).concat(SLASH.code())
                                    .concat(version))));
                });

        return children;
    }

    /**
     * 
     * 创建/inspection/security节点数据. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private Configuration getInsSecurityNode() {
        String path = "/inspection/security";
        Configuration security = null;
        try {
            security = new Configuration()
                    .setNode(ZKHelper.getNodeName(path))
                    .setChildren(getInsSecurityChildren())
                    .setData(zkClient.getData(path));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return security;
    }

    /**
     * 
     * 创建/inspection/security子节点数据，即: <br/>
     * /inspection/security/{appno}（key）
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInsSecurityChildren() {
        String path = "/inspection/security";
        List<Configuration> security = Lists.newArrayList();
        try {
            zkClient.getChildren(path).forEach(n -> {
                security.add(new Configuration(ZKHelper.getNodeName(n), null,
                        zkClient.getData(path.concat(SLASH.code()).concat(n))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }        
        return security;
    }

    /**
     * 
     * 创建/inspection/whitelists节点. <br/>
     * 存储数据规则:<br/>
     * /inspection/whitelists/ip1<br/>
     * /inspection/whitelists/ip2<br/>
     * /inspection/whitelists/ip3<br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private Configuration getInsWhiltelistsNode() {
        String path = "/inspection/whitelists";
        Configuration whitelists = null;
        try {
            whitelists = new Configuration()
                    .setNode(ZKHelper.getNodeName(path))
                    .setChildren(getInsWhitelistsChildren())
                    .setData(zkClient.getData(path));
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return whitelists;
    }

    /**
     * 
     * 创建/inspection/whitelists子节点，即ip列表. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> getInsWhitelistsChildren() {
        String path = "/inspection/whitelists";
        List<Configuration> whitelists = Lists.newArrayList();
        try {
            zkClient.getChildren(path).forEach(r -> {
                whitelists.add(new Configuration(ZKHelper.getNodeName(r), null,
                        zkClient.getData(path.concat(SLASH.code()).concat(r))));
            });
        } catch (Exception e) {
            LOGGER.warn("从zookeeper生成配置失败，原因:{}",e.getMessage());
        }
        return whitelists;
    }

    /**
     * @see com.zjht.channel.manager.service.ConfigurationService#publish(com.zjht.channel.manager.zookeeper.bean.Configuration)
     */
    @Override
    public void publish(Configuration config) {
        AssertionHelper.check(!ObjectHelper.isNull(config), "发布的配置不能为空");
        List<Configuration> children = config.getChildren();
        if (!ListHelper.isEmpty(children)) {
            children.forEach(n -> {
                publish(n, "");
            });
        }
    }

    /**
     * 发布配置到zookeeper<br/>
     * 根据节点状态来创建/修改/删除zk目录
     * 
     * @author jun
     * @param n
     * @param string
     * @since JDK 1.8
     */
    private void publish(Configuration config, String ppath) {
        AssertionHelper.check(!ObjectHelper.isNull(config), "要发布的配置信息不能为空");
        String zpath = ZKHelper.createZPath(ppath, config.getNode());
        String data = config.getData();
        String status = config.getStatus();

        // 新增
        if (ObjectHelper.equal(status, ADDED.code())) {
            zkClient.create(zpath, data);
        } else if (ObjectHelper.equal(status, UPDATED.code())) {
            zkClient.update(zpath, data);
        } else if (ObjectHelper.equal(status, DELETED.code())) {
            zkClient.delete(zpath);
            //!!!!!!!!!!!!zk删除节点时，会将节点下的所有数据都删除
            //删除成功之后讲config的children设置为null，防止循环处理时出错
            config.setChildren(null);
        } else {
            ExceptionHelper.newZookeeperException(_00403, status);
        }

        List<Configuration> children = config.getChildren();
        if (!ListHelper.isEmpty(children)) {
            children.forEach(n -> {
                publish(n, zpath);
            });
        }
    }

    public static void main(String[] args) {
        System.out.println(ZKHelper.createZPath(
                ZKHelper.createZPath(ZKHelper.createZPath("", "123213"), "asd"), "AAA"));
    }
}
