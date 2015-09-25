/** 
 * Project Name : channel-manager 
 * File Name : ExceptionHelper.java 
 * Package Name : com.zjht.channel.manager.exception.helper 
 * Date : Sep 17, 20159:34:37 AM 
 * 
 */

package com.zjht.channel.manager.exception.helper;

import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.exception.ApplicationException;
import com.zjht.channel.manager.exception.IllegalParameterException;
import com.zjht.channel.manager.exception.LoginException;
import com.zjht.channel.manager.exception.ServiceException;
import com.zjht.channel.manager.exception.ZookeeperException;

/** 
 * ClassName: ExceptionHelper <br/> 
 * Function: 异常处理辅助类. <br/> 
 * date: Sep 17, 2015 9:34:37 AM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class ExceptionHelper {
    
    /** 
     * 生成一个新的ApplicationException对象
     * 
     * @author jun
     * @param errorMessage
     * @return 
     * @since JDK 1.8
     */  
    public static ApplicationException newApplicationException(ErrorMessage error,Object ... args){
        return new ApplicationException(error.code(),StringHelper.replace(error.message(), args));
    }
    
    /**
     * 生成一个新的LoginException对象
     * 
     * @author jun
     * @param error
     * @param args
     * @return 
     * @since JDK 1.8
     */
    public static  LoginException newLoginException(ErrorMessage error,Object ... args){
        return new LoginException(error.code(),StringHelper.replace(error.message(), args));
    }
    
	/**
	 * 生成一个新的IllegalParameterException对象
	 * 
	 * @author jun
	 * @param error
	 * @param args
	 * @return 
	 * @since JDK 1.8
	 */
    public static  IllegalParameterException newIllegalParameterException(ErrorMessage error,Object ... args){
	    return new IllegalParameterException(error.code(),StringHelper.replace(error.message(), args));
	}

    /**
     * 生成一个新的ZookeeperException对象
     * 
     * @author jun
     * @param error
     * @param args
     * @return 
     * @since JDK 1.8
     */
    public static  ZookeeperException newZookeeperException(ErrorMessage error,Object ... args){
        return new ZookeeperException(error.code(),StringHelper.replace(error.message(), args));
    }
    
    
    /** 
     * 生成一个新的ServiceException对象
     * 
     * @author jun
     * @param errorMessage
     * @return 
     * @since JDK 1.8
     */  
    public static ServiceException newServiceException(ErrorMessage error,Object ... args){
        return new ServiceException(error.code(),StringHelper.replace(error.message(), args));
    }
    
}