/** 
 * Project Name:channel-server 
 * File Name:StandardServiceManager.java 
 * Package Name:com.zjht.channel.service 
 * Date:2015年9月1日上午9:50:21 
 * 
 */

package com.zjht.channel.service.impl.dubbo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.helper.ObjectHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.exception.ServiceException;
import com.zjht.channel.service.ServiceManager;
import com.zjht.channel.service.bean.ServiceInfo;
import com.zjht.channel.service.helper.DynamicClassLoader;
import com.zjht.channel.service.helper.ServiceHelper;

/**
 * ClassName: DubboServiceManager <br/>
 * Function: 基于dubbo的服务管理. <br/>
 * date: 2015年9月1日 上午9:50:21 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
@Service("ServiceManager")
public class DubboServiceManager implements ServiceManager {

    private final static Logger logger = LoggerFactory.getLogger(DubboServiceManager.class);

    private ApplicationConfig application;
    private RegistryConfig registry;
    private Map<String, ReferenceConfig<com.zjht.channel.service.Service>> services;
    
    @Resource(name = "ConfigurationBuilder")
    private ConfigurationBuilder configFactory;
    
    @PostConstruct
    public void init(){
        initApplication();
        initRegistry();
        initReference();
    }
    
    /** 
     * initReference:初始化<dubbo:reference/>. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initReference() {
        Configuration config = configFactory.configuration();
        Preconditions.checkArgument(!Objects.isNull(config), "配置信息未初始化！");

        List<ServiceInfo> svrInfos = config.getServices();
        logger.debug("配置中总服务数：{}",svrInfos.size());
        logger.debug("开始加载服务");
        services = Maps.newHashMap();
        for (ServiceInfo svrInfo : svrInfos) {
            load(svrInfo);
            logger.debug("加载服务{}成功",svrInfo);
        }
        logger.debug("完成加载服务，加载服务总数[{}]",services.size());
    }

    /** 
     * 初始化<dubbo:registry/>. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initRegistry() {
        registry = new RegistryConfig();
        registry.setProtocol("zookeeper");//注册中心使用zookeeper
        registry.setAddress("172.16.104.147:2181,172.16.111.106:2181,172.16.111.107:2181");
        registry.setSession(60000);//注册中心会话超时时间(毫秒)，用于检测提供者非正常断线后的脏数据，比如用心跳检测的实现，此时间就是心跳间隔
        registry.setCheck(true);//注册中心找不到时是否报错
        registry.setSubscribe(true);
    }

    /** 
     * initApplication:初始化<dubbo:applicatio/>. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initApplication() {
        application = new ApplicationConfig();
        application.setName("hi_consumer");//当前应用的名称
        application.setVersion("0.1");//当前应用的版本
        application.setOwner("jun");//应用负责人
        application.setOrganization("zjht_channel");//组织名称/部门
        application.setCompiler("javassist");//Java字节码编译器，用于动态类的生成，可选：jdk或javassist
        application.setLogger("slf4j");//日志输出方式，可选：slf4j,jcl,log4j,jdk
    }

    /**
     * @see com.zjht.channel.service.ServiceManager#load(com.zjht.channel.service.bean.ServiceInfo)
     */
    @Override
    public void load(com.zjht.channel.service.bean.ServiceInfo svrInfo) {
        ReferenceConfig<com.zjht.channel.service.Service> refService = new ReferenceConfig<com.zjht.channel.service.Service>();
        refService.setOwner("jun");
        refService.setApplication(application);
        refService.setRegistry(registry);
        refService.setId(svrInfo.getClassName());
        refService.setInterface(DynamicClassLoader.loadClass(svrInfo.getPackageName(),svrInfo.getClassName()));
        refService.setVersion(svrInfo.getServiceVersion());
        refService.setCheck(false);//启动时检查依赖的服务是否可用，不可用时会抛出异常，这里关闭检查
        refService.setListener("service");
        refService.setInit(true);//饥饿加载
        refService.setProxy("javassist");//选择动态代理实现策略，可选：javassist, jdk 
        services.put(ServiceHelper.getIdentify(svrInfo.getServiceName(), svrInfo.getServiceVersion()), refService);

    }

    /**
     * @see com.zjht.channel.service.ServiceManager#unload(com.zjht.channel.service.bean.ServiceInfo)
     */
    @Override
    public void unload(com.zjht.channel.service.bean.ServiceInfo service) {
        //TODO
    }

    /**
     * @see com.zjht.channel.service.ServiceManager#invoke(java.lang.String,
     *      java.lang.String, java.util.Map)
     */
    @Override
    public Map<String,Object> invoke(String svrName, String svrVersion,
            Map<String, String> params) {
        Preconditions.checkArgument(!StringHelper.isEmpty(svrName), "服务名称不能为空！");
        Preconditions.checkArgument(!StringHelper.isEmpty(svrVersion), "服务版本不能为空！");
        
        logger.debug("检查服务列表是否为空");
        if(Objects.isNull(services)){
            throw new ServiceException(RespCode._00200.code(),RespCode._00200.message());
        }
        
        ReferenceConfig<com.zjht.channel.service.Service> refService = services.get(ServiceHelper.getIdentify(svrName, svrVersion));
        logger.debug("检查服务是否存在");
        if(Objects.isNull(refService)){
            throw new ServiceException(RespCode._00201.code(),StringHelper.replace(RespCode._00201.message(), svrName,svrVersion));
        }
        
        Map<String,Object> respContent = null;//调用服务的返回内容
        try{
            com.zjht.channel.service.Service service = refService.get();
            
            if(Objects.isNull(service)){
                throw new ServiceException(RespCode._00203.code(),StringHelper.replace(RespCode._00203.message(), svrName,svrVersion));
            }
            
            logger.debug("开始调用名称[{}]及版本[{}]的服务", svrName,svrVersion);
            respContent =  service.handle(params);//调用服务
            logger.debug("成功调用名称[{}]及版本[{}]的服务,返回结果[{}]", svrName,svrVersion,respContent);
        }catch(Exception e){
            logger.error("调用名称["+svrName+"]及版本["+svrVersion+"]的服务出现异常,异常信息：",e);
            //此try{}catch(){}是为了保证进行回声测试时出现异常时，能够正常抛出自定义异常，正常返回结果给调用方
            try{
                logger.debug("开始进行回声测试，检测服务是否可用");
                com.zjht.channel.service.Service service = refService.get();
                EchoService echoService = (EchoService) service; // 强制转型为EchoService
                String status = ObjectHelper.trans(echoService.$echo("OK")); // 回声测试可用性
                logger.debug("回声测试结果[{}]",status);
                if(Objects.equals("OK", status)){
                    throw new ServiceException(RespCode._00202.code(),StringHelper.replace(RespCode._00202.message(), svrName,svrVersion));
                }else{
                    throw new ServiceException(RespCode._00203.code(),StringHelper.replace(RespCode._00203.message(), svrName,svrVersion));
                }
            }catch(ServiceException se){
                //自定义异常直接抛出由上层处理
                throw se;
            }catch(Exception ex){
                logger.error("回声测试出现异常");
                //非自定义异常，包装成自定义异常抛出
                throw new ServiceException(RespCode._00203.code(),StringHelper.replace(RespCode._00203.message(), svrName,svrVersion));
            }
        }
        return respContent;
    }
}
