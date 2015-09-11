/** 
 * Project Name:channel-server 
 * File Name:RequestPathRegulation.java 
 * Package Name:com.zjht.channel.regulation 
 * Date:2015年8月28日上午11:48:43 
 * 
 */
  
  
package com.zjht.channel.inspector.impl;  

import io.vertx.core.http.HttpServerRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.helper.ObjectHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.inspector.Inspector;
import com.zjht.channel.service.bean.Reference;
 
/** 
 * ClassName: RequestPathInspector <br/> 
 * Function: 请求路径规则检查器. <br/> 
 * date: 2015年8月28日 上午11:48:43 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.7
 */
@Service("RequestPathInspector")
public class RequestPathInspector implements Inspector {
	private final static Logger logger = LoggerFactory.getLogger(RequestPathInspector.class);
	
	/**
     * 用来存储校验失败时的自定义异常类，保证线程安全
     */
    private ThreadLocal<InspectionException> threadLocal = new ThreadLocal<InspectionException>();
	
	@Resource(name="ConfigurationBuilder")
	private ConfigurationBuilder configBuilder;
	
	/** 
	 * @see com.zjht.channel.inspector.Inspector#validate(java.lang.Object) 
	 */
	@Override
	public <T> boolean validate(T t) {
	    logger.info("【请求路径检查器】");
	    Preconditions.checkArgument((t instanceof HttpServerRequest), "["+t+"]必须为["+HttpServerRequest.class.getName()+"]的实例");
        
        HttpServerRequest httpRequest = null;
        boolean isValid = false;
        String path     = "";
        
        httpRequest = ObjectHelper.trans(t);
		path = httpRequest.path();
		isValid = checkPath(path);
		
		logger.info("检查请求路径[{}]是否为合法,结果{}",path,isValid);
		
		return isValid;
	}
	
	/** 
     * checkPath:校验请求路径是否合法. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @return 
     * @since JDK 1.8
     */  
    private boolean checkPath(String path) {
        String[] svrInfo     = null;
        String serviceName       = "";
        String serviceVersion    = "";
        boolean existService = false;
        
        logger.info("请求路径：{}",path);
        svrInfo = StringHelper.split(path, Symbol.SLASH.code());
        logger.debug("请求路径信息：{}",Arrays.toString(svrInfo));
        
        if(svrInfo.length!=4){
            threadLocal.set(new InspectionException(RespCode._00101.code(),
                    StringHelper.replace(RespCode._00101.message(), path,"")));
            return false;
        }
        
        serviceName    = svrInfo[2];
        serviceVersion = svrInfo[3];
        
        existService = existService(serviceName,serviceVersion);
        
        logger.debug("检查是否存在请求的服务[{}]及服务版本[{}],结果[{}]",serviceName,serviceVersion,existService);
        if(!existService){
            threadLocal.set(new InspectionException(RespCode._00102.code(),
                    StringHelper.replace(RespCode._00102.message(), serviceName,serviceVersion)));
            return false;
        }
        
        return true;
    }

    /** 
     * existsService:检查请求路径是否存在指定的服务和版本. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param svrName
     * @param svrVersion
     * @return 
     * @since JDK 1.8
     */  
    private boolean existService(String svrName, String svrVersion) {
        List<Reference> services =  configBuilder.configuration().getDubboConfig().getReferences();
		logger.debug("services:{}",services);
		
        for (Reference service : services) {
            if(ObjectHelper.equal(new Reference(svrName,svrVersion), service)){
                return true;
            }
        }
        
        return false;
    }

    /** 
     * @see com.zjht.channel.inspector.Inspector#getException() 
     */
    @Override
    public RuntimeException getException() {
        return threadLocal.get();
    }

}
  