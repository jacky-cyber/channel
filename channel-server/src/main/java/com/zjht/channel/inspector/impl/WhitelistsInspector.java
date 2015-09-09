package com.zjht.channel.inspector.impl;

import io.vertx.core.http.HttpServerRequest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.helper.ObjectHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.inspector.Inspector;

/**
 * 
 * @ClassName: WhitelistsInspector
 * @Description: 白名单规则检查器
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月25日
 */
@Service("WhitelistsInspector")
public class WhitelistsInspector implements Inspector {
	private final static Logger logger = LoggerFactory.getLogger(WhitelistsInspector.class);
	
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
	    logger.info("【白名单检查器】");
		Preconditions.checkArgument((t instanceof HttpServerRequest), "["+t+"]必须为["+HttpServerRequest.class.getName()+"]的实例");
		
		HttpServerRequest httpRequest = null;
        boolean isValid = false;
        String ipAddr   = "";
		
		httpRequest = ObjectHelper.trans(t);
		ipAddr  = httpRequest.remoteAddress().host();
		isValid = isWhitelists(ipAddr);
		logger.info("检查IP地址[{}]是否为白名单,结果[{}]",ipAddr,isValid);
		return isValid;
	}

	
	/**
	 * 
	 * isWhitelists:判断传入IP地址是否白名单. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param ipAddr IP地址
	 * @return 
	 * @since JDK 1.8
	 */
	private boolean isWhitelists(String ipAddr) {
        boolean isWhitelists = false;
        Configuration config = null;
		
		config       = configBuilder.configuration();
		isWhitelists = config.getWhitelists().contains(ipAddr);
		
		if(!isWhitelists){
            threadLocal.set(new InspectionException(RespCode._00100.code(),
                    StringHelper.replace(RespCode._00100.message(), ipAddr)));
        }
		return isWhitelists;
	}

    /** 
     * @see com.zjht.channel.inspector.Inspector#getException() 
     */
    @Override
    public RuntimeException getException() {
        return threadLocal.get();
    }
	
}
