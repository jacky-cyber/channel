/** 
 * Project Name:channel-server 
 * File Name : DubboServiceManager.java 
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
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.helper.ObjectHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.exception.ServiceException;
import com.zjht.channel.service.ServiceManager;
import com.zjht.channel.service.bean.Application;
import com.zjht.channel.service.bean.Consumer;
import com.zjht.channel.service.bean.Method;
import com.zjht.channel.service.bean.Reference;
import com.zjht.channel.service.bean.Registry;
import com.zjht.channel.service.helper.DynamicClassLoader;
import com.zjht.channel.service.helper.ServiceHelper;

/**
 * ClassName: DubboServiceManager <br/>
 * Function: 基于dubbo的服务管理. <br/>
 * date: 2015年9月1日 上午9:50:21 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Service("ServiceManager")
public class DubboServiceManager implements ServiceManager {

    private final static Logger logger = LoggerFactory.getLogger(DubboServiceManager.class);

    private ApplicationConfig applicationConfig;
    private RegistryConfig registryConfig;
    private ConsumerConfig consumerConfig;
    
    private Map<String, ReferenceConfig<com.zjht.channel.service.Service>> referenceMap;
    
    @Resource(name = "ConfigurationBuilder")
    private ConfigurationBuilder configFactory;
    
    @PostConstruct
    public void init(){
        initApplication();
        initRegistry();
        initConsumer();
        initReference();
    }
    
    /** 
	 * api方式初始化<dubbo:consumer/>. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com 
	 * @since JDK 1.8
	 */  
	private void initConsumer() {
		Consumer consumer  = configFactory.configuration().getDubboConfig().getConsumer();
		Preconditions.checkArgument(!Objects.isNull(consumer), "未初始化Dubbo Consumer！");
		
		consumerConfig = new ConsumerConfig();
		consumerConfig.setOwner(consumer.getOwner());
		consumerConfig.setApplication(applicationConfig);
		consumerConfig.setRegistry(registryConfig);
		consumerConfig.setActives(consumer.getActives());
		consumerConfig.setAsync(consumer.isAsync());
		consumerConfig.setCheck(consumer.isCheck());
		consumerConfig.setCluster(consumer.getCluseter());
		consumerConfig.setFilter(consumer.getFilter());
		consumerConfig.setGeneric(consumer.getGeneric());
		consumerConfig.setLayer(consumer.getLayer());
		consumerConfig.setListener(consumer.getListener());
		consumerConfig.setLoadbalance(consumer.getLoadbalance());
		consumerConfig.setProxy(consumer.getProxy());
		consumerConfig.setRetries(consumer.getRetries());
		consumerConfig.setTimeout(consumer.getTimeout());
	}

	/** 
     * initReference:初始化<dubbo:reference/>. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initReference() {
        List<Reference> referenceList = configFactory.configuration().getDubboConfig().getReferences();
        Preconditions.checkArgument(!Objects.isNull(referenceMap), "未初始化Dubbo Reference！");

        logger.debug("配置中总服务数：{}",referenceMap.size());
        logger.debug("开始加载服务");
        referenceMap = Maps.newHashMap();
        for (Reference reference : referenceList) {
        	logger.debug("加载服务{}",reference);
            load(reference);
        }
        logger.debug("完成加载服务，加载服务总数[{}]",referenceMap.size());
    }

    /** 
     * api方式初始化<dubbo:registry/>. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initRegistry() {
    	Registry registry = configFactory.configuration().getDubboConfig().getRegistry();
    	Preconditions.checkArgument(!Objects.isNull(registry), "未初始化Dubbo Registry！");
    	
        registryConfig = new RegistryConfig();
        registryConfig.setId(registry.getId());
        registryConfig.setAddress(registry.getAddress());
        registryConfig.setProtocol(registry.getProtocol());
        registryConfig.setCheck(registry.isCheck());
        registryConfig.setUsername(registry.getUsername());
        registryConfig.setPassword(registry.getPassword());
        registryConfig.setDynamic(registry.isDynamic());
        registryConfig.setFile(registry.getFile());
        registryConfig.setSession(registry.getSession());
        registryConfig.setTimeout(registry.getTimeout());
        registryConfig.setSubscribe(registry.isSubscribe());
        registryConfig.setRegister(registry.isRegister());
    }

    /** 
     * api方式初始化<dubbo:application/>.
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */  
    private void initApplication() {
    	Application application = configFactory.configuration().getDubboConfig().getApplication();
    	Preconditions.checkArgument(!Objects.isNull(application),"未初始化Dubbo Application！");
    	
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName(application.getName());//当前应用的名称
        applicationConfig.setVersion(application.getVersion());//当前应用的版本
        applicationConfig.setOwner(application.getOwner());//应用负责人
        applicationConfig.setOrganization(application.getOrganization());//组织名称(部门)
        applicationConfig.setArchitecture(application.getArchitecture());//服务分层对应的架构
        applicationConfig.setEnvironment(application.getEnvironment());//应用环境，如：develop/test/product，不同环境使用不同的缺省值，以及作为只用于开发测试功能的限制条件
        applicationConfig.setCompiler(application.getCompiler());//Java字节码编译器，用于动态类的生成，可选：jdk或javassist
        applicationConfig.setLogger(application.getLogger());//日志输出方式，可选：slf4j,jcl,log4j,jdk
    }

    /**
     * @see com.zjht.channel.service.ServiceManager#load(com.zjht.channel.service.bean.Reference)
     */
    @Override
    public void load(Reference reference) {
        ReferenceConfig<com.zjht.channel.service.Service> refConfig = new ReferenceConfig<com.zjht.channel.service.Service>();
        refConfig.setApplication(applicationConfig);
        refConfig.setRegistry(registryConfig);
        refConfig.setConsumer(consumerConfig);
        refConfig.setGroup(reference.getGroup());
        refConfig.setOwner(reference.getOwner());
        refConfig.setId(reference.getId());
        refConfig.setActives(reference.getActives());
        refConfig.setClient(reference.getClient());
        refConfig.setCluster(reference.getCluster());
        refConfig.setConnections(reference.getConnections());
        refConfig.setInterface(DynamicClassLoader.loadClass(reference.getFullyQualifiedName()));
        refConfig.setLoadbalance(reference.getLoadbalance());
        refConfig.setProxy(reference.getProxy());
        refConfig.setRetries(reference.getRetries());
        refConfig.setTimeout(reference.getTimeout());
        refConfig.setVersion(reference.getVersion());
        refConfig.setInit(reference.isInit());
        refConfig.setAsync(reference.isAsync());
        refConfig.setCheck(reference.isCheck());
        refConfig.setGeneric(reference.isGeneric());
        refConfig.setFilter(reference.getFilter());
        refConfig.setListener(reference.getListener());
        refConfig.setLayer(reference.getLayer());
        refConfig.setMethods(initMethodConfig(reference.getMethods()));
        referenceMap.put(ServiceHelper.getIdentify(reference.getName(), reference.getVersion()), refConfig);
    }

    /** 
	 * api方式初始化<dubbo:method/>. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
     * @param list 
	 * @param  
	 * @since JDK 1.8
	 */  
	private List<MethodConfig> initMethodConfig(List<Method> methods) {
		List<MethodConfig> methodConfigs =  Lists.newArrayList();
		
		if(Objects.isNull(methods)||methods.size()<=0){
			logger.info("未发现需要注册的Dubbo Method");
			return methodConfigs;
		}
		
		logger.info("开始注册DUbbo Method,总数：{}",methods.size());
		methods.forEach(m->{
			MethodConfig methodConfig = new MethodConfig();
			methodConfig.setActives(m.getActives());
			methodConfig.setAsync(m.isAsync());
			methodConfig.setExecutes(m.getExecutes());
			methodConfig.setLoadbalance(m.getLoadbalance());
			methodConfig.setName(m.getName());
			methodConfig.setRetries(m.getRetries());
			methodConfig.setTimeout(m.getTimeout());
			methodConfig.setReturn(m.isNeedReturn());
			methodConfig.setSent(m.isSent());
			methodConfig.setSticky(m.isSticky());
			methodConfigs.add(methodConfig);
		});
		return methodConfigs;
	}

	/**
     * @see com.zjht.channel.service.ServiceManager#unload(com.zjht.channel.service.bean.Reference)
     */
    @Override
    public void unload(com.zjht.channel.service.bean.Reference service) {
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
        if(Objects.isNull(referenceMap)){
            throw new ServiceException(RespCode._00200.code(),RespCode._00200.message());
        }
        
        ReferenceConfig<com.zjht.channel.service.Service> refService = referenceMap.get(ServiceHelper.getIdentify(svrName, svrVersion));
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
