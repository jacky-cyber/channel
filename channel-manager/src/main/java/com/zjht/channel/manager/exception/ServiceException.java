package com.zjht.channel.manager.exception;

/**
 * 
 * 业务逻辑异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 10:50:37 PM
 */
public class ServiceException extends ApplicationException {
    /** 
     * Creates a new instance of ServiceException. 
     * 
     * @param code
     * @param message 
     */  
    public ServiceException(String code, String message) {
        super(code, message);
    }

    /** 
	 * @since JDK 1.8
	 */  
	private static final long serialVersionUID = 5139445136074647524L;

}
