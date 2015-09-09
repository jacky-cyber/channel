package com.zjht.channel.inspector.impl;

import io.vertx.core.http.HttpServerRequest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.Parameters;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.helper.ObjectHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.inspector.Inspector;
import com.zjht.channel.service.bean.ServiceInfo;

/**
 * 
 * ClassName: PermissionInspector <br/> 
 * Function: 请求权限规则检查器. <br/> 
 * date: 2015年8月31日 下午5:02:20 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
@Service("PermissionInspector")
public class PermissionInspector implements Inspector{

   private final static Logger logger = LoggerFactory.getLogger(PermissionInspector.class);
    
    /**
     * 用来存储校验失败时的自定义异常类，保证线程安全
     */
    private ThreadLocal<InspectionException> threadLocal = new ThreadLocal<InspectionException>();
    
    @Resource(name="ConfigurationBuilder")
    private ConfigurationBuilder configBuilder;
    
	@Override
	public <T> boolean validate(T t) {
	    logger.info("【请求权限检查器】");
	    Preconditions.checkArgument((t instanceof HttpServerRequest), "["+t+"]必须为["+HttpServerRequest.class.getName()+"]的实例");
        
        HttpServerRequest httpRequest = null;
        boolean isValid   = false;
        String[] svrInfo  = null;
        String appno      = "";
        String svrName    = "";
        String svrVersion = "";
        
        httpRequest    = ObjectHelper.trans(t);
        appno      = StringHelper.trim(httpRequest.getParam(Parameters.APPNO.getName()));
        svrInfo    = StringHelper.split(httpRequest.path(),Symbol.SLASH.code());
        svrName    = svrInfo[2];
        svrVersion = svrInfo[3];
        
        isValid = checkPermission(appno,svrName,svrVersion);
        
        logger.info("检查appno[{}]是否有访问服务[{}]及服务版本[{}]权限，结果：[{}]",appno,svrName,svrVersion,isValid);
        
		return isValid;
	}

	
    /**
     * 
     * checkPermission:检查appno是否权访问服务. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param appno
     * @param svrName
     * @param svrVersion
     * @return 
     * @since JDK 1.8
     */
    private boolean checkPermission(String appno, String svrName,String svrVersion) {
        Configuration config   = null;
        Map<String, List<ServiceInfo>> permissions = null;
        
        config = configBuilder.configuration();
        permissions = config.getPermission();
        
        if(!(permissions.containsKey(appno)&&permissions.get(appno).contains(new ServiceInfo(svrName,svrVersion)))){
            threadLocal.set(new InspectionException(RespCode._00104.code(),
                    StringHelper.replace(RespCode._00104.message(),appno,svrName,svrVersion)));
            return false;
        }
        return true;
    }

    /** 
     * @see com.zjht.channel.inspector.Inspector#getException() 
     */
    @Override
    public RuntimeException getException() {
        return threadLocal.get();
    }
	
    
    public static void main(String[] args) {
        System.out.println(StringHelper.replace("终端号[{}]没有访问服务名称[{}]及其版本[{}]的权限", "1","2","3"));
    }
}
