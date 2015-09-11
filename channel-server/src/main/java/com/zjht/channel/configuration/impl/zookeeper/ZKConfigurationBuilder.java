package com.zjht.channel.configuration.impl.zookeeper;

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
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.service.bean.Reference;

/**
 * 
 * @ClassName: ZKConfigurationBuilder
 * @Description: 从zookeeper生成配置对象工厂类实现
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月25日
 */
@Service("ConfigurationBuilder")
public class ZKConfigurationBuilder implements ConfigurationBuilder{
	private final static Logger logger = LoggerFactory.getLogger(ZKConfigurationBuilder.class);
	
	private Configuration config ;

	@Resource(name="ZKClient")
	private ZKClient curator;
	/**
	 * 
	 * 基于zookeeper创建Configuration对象. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */
	@PostConstruct
	@Override
	public void build() {
		try{
			config = new Configuration();
			config.setWhitelists(buildWhitelistsConfig());//白名单配置信息
			config.setSecurity(buildSecurityConfig());//终端-密钥配置信息
			config.setPermission(buildPermissionConfig());//终端-权限配置信息
//			config.setServices(buildServiceConfig());//服务配置信息
			logger.debug("配置信息：{}",config);
		}catch(Exception e){
			logger.error("生成配置失败",e);
			throw new RuntimeException("生成配置失败,原因:"+e.getMessage());
		}
	}

	/** 
	 * buildServiceConfig:创建服务配置. <br/> 
	 * <p>配置路径：</p>
	 * <b>serviceName:</b>/chanel/service/{serviceName}<br/> 
	 * <b>version    :</b>/chanel/service/{serviceName}/{version}<br/> 一个service可能多个version
	 * <b>packageName:</b>/chanel/service/{serviceName}/{version}/{packageName}<br/> 一个version一个
	 * <b>className  :</b>/chanel/service/{serviceName}/{version}/{className}<br/> 一个version一个
	 * <b>status     :</b>/chanel/service/{serviceName}/{version}/{status}<br/>  一个version一个
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @throws Exception 
	 * @since JDK 1.8
	 */  
	private List<Reference> buildServiceConfig() throws Exception {
		List<String> svcNames      = null;
		List<String> svcVersions   = null;
		List<Reference> services = Lists.newArrayList();
		
		String basePath = "/channel/service";
		
		svcNames = curator.getChildren(basePath);
		for (String svcName : svcNames) {
		    svcVersions = curator.getChildren(basePath.concat(Symbol.SLASH.code()).concat(svcName));
		    logger.debug("服务名称:[]",svcName);
		    for (String svcVersion : svcVersions) {
		        logger.debug("服务版本:[]",svcVersion);
		        String packageNamePath = basePath.concat(Symbol.SLASH.code()).concat(svcName).concat(Symbol.SLASH.code()).concat(svcVersion).concat(Symbol.SLASH.code()).concat("packageName");
		        String classNamePath   = basePath.concat(Symbol.SLASH.code()).concat(svcName).concat(Symbol.SLASH.code()).concat(svcVersion).concat(Symbol.SLASH.code()).concat("className");
		        String statusPath      = basePath.concat(Symbol.SLASH.code()).concat(svcName).concat(Symbol.SLASH.code()).concat(svcVersion).concat(Symbol.SLASH.code()).concat("status");
		        logger.debug("packageNamePath=[]",packageNamePath);
		        logger.debug("classNamePath=[]",classNamePath);
		        logger.debug("statusPath=[]",statusPath);
		        
		        Reference service = new Reference();
//		        service.setServiceName(svcName);
//		        service.setServiceVersion(svcVersion);
//	            service.setPackageName(StringHelper.newString(curator.getData(packageNamePath), "utf-8"));
//	            service.setClassName(StringHelper.newString(curator.getData(classNamePath), "utf-8"));
//	            service.setStatus(StringHelper.newString(curator.getData(statusPath), "utf-8"));
	            services.add(service);
	            logger.debug("构建服务信息{}",service);
            }
		}
		return services;
	}


	/**
	 * 
	 * 生成终端的访问服务的权限配置. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	private Map<String, List<Reference>> buildPermissionConfig() throws Exception {
		Map<String,List<Reference>> permission = Maps.newHashMap();
		List<Reference> services = Lists.newArrayList();
		List<String> appnos   = null;
		List<String> svrNames = null;
		List<String> svrVersions = null;
		String basePath = "/channel/inspection/permission";
		
		appnos = curator.getChildren(basePath);
		
		for (String appno : appnos) {
		    logger.debug("创建终端编号[{}]的服务访问权限信息",appno);
		    svrNames = curator.getChildren(basePath.concat(Symbol.SLASH.code()).concat(appno));
		    for(String svrName:svrNames){
		        
		        svrVersions = curator.getChildren(basePath.concat(Symbol.SLASH.code()).concat(appno).concat(Symbol.SLASH.code()).concat(svrName));
		        
		        for (String svrVersion : svrVersions) {
		            logger.debug("\t 服务名称[{}],服务版本[{}]",svrName,svrVersion);
                    services.add(new Reference(svrName,svrVersion));
                }
		    }
			permission.put(appno, services);
		}
		return permission;
	}

	/**
	 * @Title: buildSecurityConfig
	 * @Description: 生成终端密钥配置
	 * @return
	 * @throws Exception Map<String,String>
	 * @throws
	 */
	private Map<String, String> buildSecurityConfig() throws Exception {
		Map<String,String> security = Maps.newHashMap();
		List<String> appnos = null;//用户名
		String key = "";
		String basePath = "/channel/inspection/security";
		
		appnos = curator.getChildren(basePath);
		for (String appno:appnos) {
		    key = StringHelper.newString(
		                curator.getData(basePath
		                                .concat(Symbol.SLASH.code())
		                                .concat(appno)
		                                .concat(Symbol.SLASH.code())
		                                .concat("key")), "utf-8");
		    logger.debug("appno=[{}],key=[{}]",appno,key);
			security.put(appno, key);
		}
		return security;
	}


	/**
	 * 
	 * 生成白名单配置信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	private List<String> buildWhitelistsConfig() throws Exception {
	    String basePath = "/channel/inspection/whitelists";
		return curator.getChildren(basePath);
	}

    /** 
     * @see com.zjht.channel.configuration.ConfigurationBuilder#configuration() 
     */
    @Override
    public Configuration configuration() {
        return config;
    }
}
