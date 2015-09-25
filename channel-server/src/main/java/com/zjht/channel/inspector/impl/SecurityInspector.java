package com.zjht.channel.inspector.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.Parameters;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.configuration.ConfigurationBuilder;
import com.zjht.channel.configuration.bean.Configuration;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.helper.common.SecurityHelper;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.inspector.Inspector;

import io.vertx.core.http.HttpServerRequest;
import jodd.util.Base64;

/**
 * 
 * @ClassName: SecurityInspector
 * @Description: 请求安全性规则检查器
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月25日
 */
@Service("SecurityInspector")
public class SecurityInspector implements Inspector {

    private final static Logger logger = LoggerFactory.getLogger(SecurityInspector.class);
    
    /**
     * 用来存储校验失败时的自定义异常类，保证线程安全
     */
    private ThreadLocal<InspectionException> threadLocal = new ThreadLocal<InspectionException>();
    
    @Resource(name="ConfigurationBuilder")
    private ConfigurationBuilder configBuilder;
    
	@Override
	public <T> boolean validate(T t) {
	    logger.info("【安全信息检查器】");
	    Preconditions.checkArgument((t instanceof HttpServerRequest), "["+t+"]必须为["+HttpServerRequest.class.getName()+"]的实例");
        
        HttpServerRequest httpRequest = null;
        
        boolean isValid = false;
        String appno    = "";
        String msg      = "";
        String sign     = "";
        
        httpRequest = ObjectHelper.trans(t);
        appno   = httpRequest.getParam(Parameters.APPNO.getName());
        msg     = httpRequest.getParam(Parameters.MSG.getName());
        sign    = httpRequest.getParam(Parameters.SIGN.getName());
        
        isValid = checkSecurity(appno,msg,sign);
        logger.info("检查appno[{}],msg[{}],sign[{}]是否合法，结果:[{}]",appno,msg,sign,isValid);
        
		return isValid;
	}
	

    /**
     * 
     * checkSecurity:检查是否请求合法. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param appno
     * @param msg
     * @param sign
     * @return 
     * @since JDK 1.8
     */
    private boolean checkSecurity(String appno, String msg, String sign) {
        Map<String, String> securityMap  = null;
        Configuration config   = null;
        String key = "";
        
        logger.debug("检查appno[{}]是否为空",appno);
        if(StringHelper.isEmpty(appno)){
            threadLocal.set(new InspectionException(RespCode._00105.code(),
                    StringHelper.replace(RespCode._00105.message(), Parameters.APPNO.getName())));
            return false;
        }
        
        logger.debug("检查sign[{}]是否为空",sign);
        if(StringHelper.isEmpty(sign)){
            threadLocal.set(new InspectionException(RespCode._00105.code(),
                    StringHelper.replace(RespCode._00105.message(), Parameters.SIGN.getName())));
            return false;
        }
        
        logger.debug("检查msg[{}]是否为空",msg);
        if(StringHelper.isEmpty(msg)){
            threadLocal.set(new InspectionException(RespCode._00105.code(),
                    StringHelper.replace(RespCode._00105.message(), Parameters.MSG.getName())));
            return false;
        }
        
        config = configBuilder.configuration();
        securityMap = config.getSecurity();
        logger.debug("检查是否存在终端号[{}]",appno);
        if(!securityMap.containsKey(appno)){
            threadLocal.set(new InspectionException(RespCode._00103.code(),
                    StringHelper.replace(RespCode._00103.message(), appno)));
            return false;
        }
        
        //获取终端对应的密钥
        key = securityMap.get(appno);
        logger.debug("进行验签");
        if(!verify(msg,key,sign)){
            threadLocal.set(new InspectionException(RespCode._00107.code(),
                    RespCode._00107.message()));
            return false;
        }
        return true;
    }


    /** 
     * <b>旧版接口验签方式.</b> <br/> 
     * <p>1. 通过将key+msg+key使用SHA-1算法生成摘要<br/>
     * 2. 将上面生成的摘要使用Base64进行编码<br/>
     * 3. 将2中的结果对比请求传入的sign，检查是否一致
     * </p>
     * @author jun yangwenjun@chinaexpresscard.com
     * @param msg  明文
     * @param key  密钥
     * @param sign 签名
     * @since JDK 1.8
     */  
    private boolean verify(String msg, String key, String sign) {
        String charset    = "UTF-8";
        String algorithm  = "SHA-1";
        byte[] digest = null;
        
        logger.debug("verify：msg=[{}],key=[{}],sign=[{}]",msg,key,sign);
        digest = SecurityHelper.digest(key.concat(msg).concat(key), algorithm, charset);
        
        return Objects.equal(sign, Base64.encodeToString(digest));
    }


    /** 
     * @see com.zjht.channel.inspector.Inspector#getException() 
     */
    @Override
    public RuntimeException getException() {
        return threadLocal.get();
    }
}
