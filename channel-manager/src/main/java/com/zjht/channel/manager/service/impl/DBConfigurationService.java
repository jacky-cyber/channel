package com.zjht.channel.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.manager.dao.PermissionMapper;
import com.zjht.channel.manager.dao.SecurityMapper;
import com.zjht.channel.manager.dao.ServiceMapper;
import com.zjht.channel.manager.dao.WhitelistsMapper;
import com.zjht.channel.manager.model.Permission;
import com.zjht.channel.manager.model.PermissionExample;
import com.zjht.channel.manager.model.SecurityExample;
import com.zjht.channel.manager.model.Service;
import com.zjht.channel.manager.model.ServiceExample;
import com.zjht.channel.manager.model.WhitelistsExample;
import com.zjht.channel.manager.service.ConfigurationService;
import com.zjht.channel.manager.zookeeper.bean.Configuration;
import com.zjht.channel.manager.zookeeper.helper.ZKHelper;

/**
 * 从数据库生成渠道系统的配置信息
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 9:49:50 AM
 */
@org.springframework.stereotype.Service("DBConfigurationService")
public class DBConfigurationService implements ConfigurationService {

    @Autowired
    private WhitelistsMapper whitelistsDao;

    @Autowired
    private PermissionMapper permissionDao;

    @Autowired
    private SecurityMapper securityDao;

    @Autowired
    private ServiceMapper serviceDao;

    @Value("${zk.channel.namespace}")
    private String zkNamespace;
    
    @Value("${dubbo.application.name}")
    private String dubbo_application_name;
    @Value("${dubbo.application.version}")
    private String dubbo_application_version;
    @Value("${dubbo.application.owner}")
    private String dubbo_application_owner;
    @Value("${dubbo.application.organization}")
    private String dubbo_application_organization;
    @Value("${dubbo.application.environment}")
    private String dubbo_application_environment;
    @Value("${dubbo.application.compiler}")
    private String dubbo_application_compiler;
    @Value("${dubbo.application.logger}")
    private String dubbo_application_logger;

    @Value("${dubbo.registry.group}")
    private String dubbo_registry_group;
    @Value("${dubbo.registry.protocol}")
    private String dubbo_registry_protocol;
    @Value("${dubbo.registry.id}")
    private String dubbo_registry_id;
    @Value("${dubbo.registry.address}")
    private String dubbo_registry_address;
    @Value("${dubbo.registry.timeout}")
    private String dubbo_registry_timeout;
    @Value("${dubbo.registry.session}")
    private String dubbo_registry_session;
    @Value("${dubbo.registry.file}")
    private String dubbo_registry_file;
    @Value("${dubbo.registry.wait}")
    private String dubbo_registry_wait;
    @Value("${dubbo.registry.check}")
    private String dubbo_registry_check;
    @Value("${dubbo.registry.subscribe}")
    private String dubbo_registry_subscribe;

    @Value("${dubbo.consumer.owner}")
    private String dubbo_consumer_owner;
    @Value("${dubbo.consumer.group}")
    private String dubbo_consumer_group;
    @Value("${dubbo.consumer.timeout}")
    private String dubbo_consumer_timeout;
    @Value("${dubbo.consumer.retries}")
    private String dubbo_consumer_retries;
    @Value("${dubbo.consumer.loadbalance}")
    private String dubbo_consumer_loadbalance;
    @Value("${dubbo.consumer.check}")
    private String dubbo_consumer_check;
    @Value("${dubbo.consumer.proxy}")
    private String dubbo_consumer_proxy;
    @Value("${dubbo.consumer.actives}")
    private String dubbo_consumer_actives;
    @Value("${dubbo.consumer.cluster}")
    private String dubbo_consumer_cluster;

    /**
     * 根节点
     * 
     * @see com.zjht.channel.manager.service.ConfigurationService#configure()
     */
    @Override
    public Configuration configure() {
        Configuration channel = new Configuration().setNode(zkNamespace)
                .setChildren(buildChannelChildren()).setData("");
        return channel;
    }

    /**
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildChannelChildren() {
        List<Configuration> channel = ListHelper.listOf(buildInspectionNode(), buildServiceNode());
        return channel;
    }

    /**
     * 获取/service节点数据. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Configuration buildServiceNode() {
        String path = "/service";
        Configuration service = new Configuration().setNode(ZKHelper.getNodeName(path))
                .setChildren(buildServiceChildren()).setData("");
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
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildServiceChildren() {
        List<Configuration> children = Lists.newArrayList();
        children.add(buildSerDubboNode());
        return children;
    }

    /**
     * 创建爱你dubbo节点
     * 
     * @author jun
     * @return
     */
    private Configuration buildSerDubboNode() {
        Configuration dubbo = new Configuration().setNode(ZKHelper.getNodeName("/service/dubbo"))
                .setChildren(buildDubboChildren()).setData("");
        return dubbo;
    }

    /**
     * 创建dubbo子节点：<br/>
     * 
     * <pre>
     * /channel/service/dubbo/reference
     * /channel/service/dubbo/application
     * /channel/service/dubbo/registry
     * /channel/service/dubbo/consumer
     * </pre>
     * 
     * @author jun
     * @return
     */
    private List<Configuration> buildDubboChildren() {
        List<Configuration> children = Lists.newArrayList();
        children.add(buildServiceDubboApplicationNode());
        children.add(buildServiceDubboRegistryNode());
        children.add(buildServiceDubboConsumerNode());
        children.add(buildServiceDubboReferenceNode());
        return children;
    }

    /** 
     * 创建/service/dubbo/consumer节点
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboConsumerNode() {
        Configuration consumer =
                new Configuration()
                .setNode(ZKHelper.getNodeName("/service/dubbo/consumer"))
                .setChildren(buildServiceDubboConsumerChildren())
                .setData("");
        return consumer;
    }

    /** 
     * 创建/service/dubbo/consumer子节点
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboConsumerChildren() {
        List<Configuration> children = ListHelper.listOf(
                new Configuration("owner",null,dubbo_consumer_owner),
                new Configuration("group",null,dubbo_consumer_group),
                new Configuration("timeout",null,dubbo_consumer_timeout),
                new Configuration("retries",null,dubbo_consumer_retries),
                new Configuration("loadbalance",null,dubbo_consumer_loadbalance),
                new Configuration("check",null,dubbo_consumer_check),
                new Configuration("proxy",null,dubbo_consumer_proxy),
                new Configuration("actives",null,dubbo_consumer_actives),
                new Configuration("cluster",null,dubbo_consumer_cluster)
                );
        return children;
    }

    /** 
     * 创建/service/dubbo/application节点
     * 
     * @author jun
     * @return 
     */  
    private Configuration buildServiceDubboRegistryNode() {
        Configuration registry =
                new Configuration()
                .setNode(ZKHelper.getNodeName("/service/dubbo/registry"))
                .setChildren(buildServiceDubboRegistryChildren())
                .setData("");
        return registry;
    }

    /** 
     * 创建/service/dubbo/application节点
     * 
     * @author jun
     * @return 
     */  
    private List<Configuration> buildServiceDubboRegistryChildren() {
        List<Configuration> children = ListHelper.listOf(
                new Configuration("id",null,dubbo_registry_id),
                new Configuration("group",null,dubbo_registry_group),
                new Configuration("protocol",null,dubbo_registry_protocol),
                new Configuration("address",null,dubbo_registry_address),
                new Configuration("timeout",null,dubbo_registry_timeout),
                new Configuration("session",null,dubbo_registry_session),
                new Configuration("file",null,dubbo_registry_file),
                new Configuration("wait",null,dubbo_registry_wait),
                new Configuration("check",null,dubbo_registry_check),
                new Configuration("subscribe",null,dubbo_registry_subscribe)
                );
        return children;
    }

    /**
     * 创建/service/dubbo/application节点
     * 
     * @author jun
     * @return
     */
    private Configuration buildServiceDubboApplicationNode() {
        Configuration application =
                new Configuration()
                .setNode(ZKHelper.getNodeName("/service/dubbo/application"))
                .setChildren(buildServiceDubboApplicationChildren())
                .setData("");
        return application;
    }

    /**
     * 创建/service/dubbo/application子节点
     * 
     * @author jun
     * @return
     */
    private List<Configuration> buildServiceDubboApplicationChildren() {
        List<Configuration> children = ListHelper.listOf(
                new Configuration("name",null,dubbo_application_name),
                new Configuration("version",null,dubbo_application_version),
                new Configuration("owner",null,dubbo_application_owner),
                new Configuration("organization",null,dubbo_application_organization),
                new Configuration("environment",null,dubbo_application_environment),
                new Configuration("compiler",null,dubbo_application_compiler),
                new Configuration("logger",null,dubbo_application_logger)
                );
        return children;
    }

    /**
     * 创建/service/dubbo/reference节点
     * 
     * @author jun
     * @return
     */
    private Configuration buildServiceDubboReferenceNode() {
        Configuration reference =
                new Configuration().setNode(ZKHelper.getNodeName("/service/dubbo/reference"))
                        .setChildren(buildServiceDubboReferenceChildren()).setData("");
        return reference;
    }

    /**
     * 创建/service/dubbo/reference子节点
     * 
     * @author jun
     * @return
     */
    private List<Configuration> buildServiceDubboReferenceChildren() {
        List<Configuration> children = Lists.newArrayList();
        List<Service> records = serviceDao.selectByExample(new ServiceExample());
        // 服务名称
        records.forEach(name -> {
            children.add(new Configuration(name.getName(),
                    buildServiceDubboReferenceNameChildren(records, name), ""));
        });
        return children;
    }

    /**
     * 获取/service/dubbo/reference/{name}的子节点数据. <br/>
     * 即服务版本
     * 
     * @author jun
     * @param records
     * @param name
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildServiceDubboReferenceNameChildren(
            List<com.zjht.channel.manager.model.Service> records,
            com.zjht.channel.manager.model.Service name) {
        List<Configuration> children = ListHelper.newArrayList();
        // 服务版本
        records.stream().filter(ver -> ObjectHelper.equal(name.getName(), ver.getName()))
                .forEach(ver -> {
                    children.add(new Configuration(ver.getVersion(),
                            buildServiceNameVerionChildren(records, name, ver), ""));
                });
        return children;
    }

    /**
     * 获取/service/{name}/{version}的子节点数据. <br/>
     * 即服务属性
     * 
     * @author jun
     * @param records
     * @param name
     * @param ver
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildServiceNameVerionChildren(
            List<com.zjht.channel.manager.model.Service> records,
            com.zjht.channel.manager.model.Service name,
            com.zjht.channel.manager.model.Service ver) {
        List<Configuration> children =
                ListHelper.listOf(new Configuration("owner", null, ver.getOwner()),
                        new Configuration("group", null, ver.getOrganization()),
                        new Configuration("fullyQualifiedName", null, ver.getFullyQualifiedName()),
                        new Configuration("id", null, ver.getBeanId()),
                        new Configuration("status", null, ver.getStatus()),
                        new Configuration("name", null, ver.getName()),
                        new Configuration("version", null, ver.getVersion())
                        );
        return children;
    }

    /**
     * 创建/inspection节点. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Configuration buildInspectionNode() {
        String path = "/inspection";
        Configuration inspection = new Configuration().setNode(ZKHelper.getNodeName(path))
                .setChildren(buildInspectionChildren()).setData("");
        return inspection;
    }

    /**
     * 创建/inspection的子节点. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionChildren() {
        List<Configuration> inspection = ListHelper.listOf(buildInsWhiltelistsNode(),
                buildInspectionSecurityNode(), buildInspectionPermissionNode());
        return inspection;
    }

    /**
     * 
     * 获取/inspection/permission节点数据. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Configuration buildInspectionPermissionNode() {
        Configuration permission = new Configuration().setNode("permission")
                .setChildren(buildInspectionPermissionChildren()).setData("");
        return permission;
    }

    /**
     * 获取/inspection/permission子节点数据. <br/>
     * zookeeper存储结构：<br/>
     * /inspection/permission/{appno}/{serviceName}/{version}
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionPermissionChildren() {
        List<Configuration> children = Lists.newArrayList();

        List<Permission> records = permissionDao.selectByExample(new PermissionExample());
        records.forEach(r1 -> {
            // 终端号列表
            children.add(new Configuration(r1.getAppno(), buildInspectionPermissionAppnoChildren(records, r1), ""));
        });
        return children;

    }


    /**
     * 获取/inspection/permission/{appno}子节点数据. <br/>
     * 即服务名称.
     * 
     * @author jun
     * @param records
     * @param per
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionPermissionAppnoChildren(List<Permission> records, Permission per) {
        List<Configuration> children = ListHelper.newArrayList();
        records.stream().filter(ser -> ObjectHelper.equal(per.getAppno(), ser.getAppno()))
                .forEach(ser -> {
                    children.add(new Configuration(ser.getServiceName(),
                            buildInspectionPermissionAppnoNameChildren(records, per, ser), ""));
                });
        return children;
    }

    /**
     * 获取/inspection/permission/{appno}/{serviceName}子节点数据. <br/>
     * 即服务版本.
     * 
     * @author jun
     * @param records
     * @param per
     * @param ser
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionPermissionAppnoNameChildren(List<Permission> records,
            Permission per, Permission ser) {
        List<Configuration> children = ListHelper.newArrayList();
        records.stream()
                .filter(ver -> ObjectHelper.equal(per.getAppno(), ver.getAppno())
                        && ObjectHelper.equal(ser.getServiceName(), ver.getServiceName()))
                .forEach(ver -> {
                    children.add(new Configuration(ver.getServiceVersion(), null, ""));
                });
        return children;
    }

    /**
     * 
     * 创建/inspection/security节点数据. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Configuration buildInspectionSecurityNode() {
        String path = "/inspection/security";
        Configuration security = new Configuration().setNode(ZKHelper.getNodeName(path))
                .setChildren(buildInspectionSecurityChildren()).setData("");
        return security;
    }

    /**
     * 
     * 创建/inspection/security子节点数据，即: <br/>
     * /inspection/security/appno（key）
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionSecurityChildren() {
        List<Configuration> security = Lists.newArrayList();
        securityDao.selectByExample(new SecurityExample()).forEach(s -> {
            security.add(new Configuration(s.getAppno(), null, s.getKey()));
        });
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
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private Configuration buildInsWhiltelistsNode() {
        String path = "/inspection/whitelists";
        Configuration whitelists = new Configuration().setNode(ZKHelper.getNodeName(path))
                .setChildren(buildInspectionWhitelistsChildren()).setData("");
        return whitelists;
    }

    /**
     * 
     * 创建/inspection/whitelists子节点，即ip列表. <br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    private List<Configuration> buildInspectionWhitelistsChildren() {
        List<Configuration> whitelists = Lists.newArrayList();
        whitelistsDao.selectByExample(new WhitelistsExample()).forEach(w -> {
            whitelists.add(new Configuration(w.getIp(), null, ""));
        });
        return whitelists;
    }


    /**
     * @see com.zjht.channel.manager.service.ConfigurationService#publish(com.zjht.channel.manager.zookeeper.bean.Configuration)
     */
    @Override
    public void publish(Configuration config) {
        // TODO Auto-generated method stub
    }

}
