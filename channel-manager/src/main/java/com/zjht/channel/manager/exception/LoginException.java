package com.zjht.channel.manager.exception;

/**
 * 
 * 登陆异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 10:49:37 PM
 */
public class LoginException extends ApplicationException {

    /** 
     * Creates a new instance of LoginException. 
     * 
     * @param code
     * @param message 
     */  
    public LoginException(String code, String message) {
        super(code, message);
    }

    /** 
	 * serialVersionUID:TODO 
	 * @since JDK 1.8
	 */  
	private static final long serialVersionUID = -8748588294229710496L;

}
