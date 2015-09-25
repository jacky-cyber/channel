package com.zjht.channel.configuration.impl.zookeeper;

import static com.zjht.channel.common.constant.RespCode._00304;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.constant.ZKConfig;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.configuration.impl.zookeeper.helper.ZKHelper;
import com.zjht.channel.exception.ExceptionHelper;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.service.bean.Application;
import com.zjht.channel.service.bean.Consumer;
import com.zjht.channel.service.bean.DubboConfig;
import com.zjht.channel.service.bean.Reference;
import com.zjht.channel.service.bean.Registry;

/**
 * 
 * @ClassName: ZKConfigurationBuilder
 * @Description: 从zookeeper生成配置对象工厂类实现
 * @author jun
 * @date 2015年8月25日
 */
@Service("ConfigurationBuilder")
public class ZKConfigurationBuilder implements ConfigurationBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKConfigurationBuilder.class);

    private Configuration config;

    @Resource(name = "ZKClient")
    private ZKClient zkClient;

    /**
     * 
     * 基于zookeeper创建Configuration对象. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    @PostConstruct
    @Override
    public void build() {
        try {
            config = new Configuration().setWhitelists(buildWhitelistsConfig())// 白名单配置信息
                    .setSecurity(buildSecurityConfig())// 终端-密钥配置信息
                    .setPermission(buildPermissionConfig())// 终端-权限配置信息
                    .setDubboConfig(buildDubboConfig());// dubbo服务配置信息
            LOGGER.debug("*************{}",config.getDubboConfig());
            LOGGER.debug("配置信息：{}", config);
        } catch (Exception e) {
            LOGGER.error("生成配置失败", e);
            throw new RuntimeException("生成配置失败,原因:" + e.getMessage());
        }
    }

    /**
     * 创建dubbo服务配置信息
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private DubboConfig buildDubboConfig() {
        DubboConfig dubboConfig =
                new DubboConfig().setApplication(ZKHelper.nodeOf(Application.class, zkClient))
                        .setConsumer(ZKHelper.nodeOf(Consumer.class, zkClient))
                        .setRegistry(ZKHelper.nodeOf(Registry.class, zkClient))
                        .setReferences(ZKHelper.nodesOf(Reference.class, zkClient));
        return dubboConfig;
    }

    /* *//**
          * 获取dubbo reference配置<br/>
          * 在zookeeper上的结构为:<br/>
          * <p>
          * ZKConfig.DUBBO.REFERENCE_ROOT/{服务名称}/{服务版本}/{服务信息}
          * </p>
          * 其中{服务名称}可能多个{服务版本}
          * 
          * @author jun
          * @return
          * @since JDK 1.8
          *//*
            * private List<Reference> buildReferences() { String basePath =
            * ZKConfig.DUBBO.REFERENCE_ROOT; List<String> names = zkClient.getChildren(basePath);
            * List<Reference> references = ListHelper.newArrayList();
            * 
            * if(ListHelper.isEmpty(names)){ LOGGER.warn("在节点[{}]未发现任何服务信息",basePath); }
            * 
            * //服务名称列表 names.forEach(name->{ String path =
            * basePath.concat(Symbol.SLASH.code()).concat(name); //服务版本列表 List<String> versions =
            * zkClient.getChildren(path); if(ListHelper.isEmpty(versions)){
            * ExceptionHelper.newApplicationException(_00304, path); } versions.forEach(version->{
            * Reference reference = ZKHelper.nodeOf(Reference.class,zkClient) .setName(name)
            * .setVersion(version); references.add(reference); }); }); return references; }
            */

    /**
     * 生成终端的访问服务的权限配置. <br/>
     * zookeeper上的节点结构:<br/>
     * 
     * @author jun
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    private Map<String, List<Reference>> buildPermissionConfig() throws Exception {
        Map<String, List<Reference>> permission = Maps.newHashMap();
        List<String> appnos = ListHelper.newArrayList();
        String path = ZKConfig.INSPECTION.PERMISSION_ROOT;

        appnos = zkClient.getChildren(path);
        if (ListHelper.isEmpty(appnos)) {
            ExceptionHelper.newZookeeperException(_00304, path).throwout();
        }

        appnos.forEach(appno -> {
            LOGGER.debug("创建终端编号[{}]的服务访问权限信息", appno);
            buildInsPerChild(permission, path, appno);
        });
        return permission;
    }

    /**
     * 创建/inspection/permission/{appno}下的子节点，即服务名称列表
     * 
     * @author jun
     * @param path
     * @param permission
     * @since JDK 1.8
     */
    private void buildInsPerChild(Map<String, List<Reference>> permission, String basePath,
            String appno) {
        String path = "";
        path = basePath.concat(Symbol.SLASH.code()).concat(appno);
        List<String> svrNames = zkClient.getChildren(path);
        if (ListHelper.isEmpty(svrNames)) {
            ExceptionHelper.newZookeeperException(_00304, path).throwout();
        }

        List<Reference> services = Lists.newArrayList();
        svrNames.forEach(name -> {
            buildInsPerAppnoChild(services, basePath, appno, name);
        });
        permission.put(appno, services);
    }

    /**
     * 创建/inspection/permission/{appno}/{name}下的子节点，即服务版本列表
     * 
     * @author jun
     * @param services
     * @param path
     * @param name
     * @since JDK 1.8
     */
    private void buildInsPerAppnoChild(List<Reference> services, String basePath, String appno,
            String name) {
        String path = "";
        path = basePath.concat(Symbol.SLASH.code()).concat(appno).concat(Symbol.SLASH.code())
                .concat(name);
        List<String> svrVersions = zkClient.getChildren(path);
        if (ListHelper.isEmpty(svrVersions)) {
            ExceptionHelper.newZookeeperException(_00304, path).throwout();
        }
        svrVersions.forEach(version -> {
            LOGGER.debug("\t 服务名称[{}],服务版本[{}]", name, version);
            services.add(new Reference().setName(name).setVersion(version));
        });
    }

    /**
     * 生成终端密钥配置<br/>
     * zookeeper上的节点：<br/>
     * {key}为{appno}节点上的数据<br/>
     * /channel/inspection/security/{appno}-{key}
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Map<String, String> buildSecurityConfig() {
        LOGGER.info("开始生成security配置...");
        Map<String, String> security = Maps.newHashMap();
        List<String> appnos = null;// 用户名
        String path = ZKConfig.INSPECTION.SECURITY_ROOT;

        appnos = zkClient.getChildren(path);

        if (ListHelper.isEmpty(appnos)) {
            ExceptionHelper.newZookeeperException(_00304, path).throwout();
        }

        appnos.forEach(appno -> {
            String key = zkClient.getData(path.concat(Symbol.SLASH.code()).concat(appno));
            LOGGER.debug("appno=[{}],key=[{}]", appno, key);
            security.put(appno, key);
        });
        return security;
    }


    /**
     * 生成白名单配置信息. <br/>
     * 
     * @author jun
     * @return
     * @throws Exception
     * @since JDK 1.8
     */
    private List<String> buildWhitelistsConfig() throws Exception {
        return zkClient.getChildren(ZKConfig.INSPECTION.WHITELISTS_ROOT);
    }

    /**
     * @see com.zjht.channel.configuration.ConfigurationBuilder#configuration()
     */
    @Override
    public Configuration configuration() {
        return config;
    }

}
