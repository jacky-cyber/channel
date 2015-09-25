/** 
 * Project Name : channel-manager 
 * File Name : IllegalParameterException.java 
 * Package Name : com.zjht.channel.manager.exception 
 * Date : Sep 17, 20151:38:17 PM 
 * 
 */

package com.zjht.channel.manager.exception;

/** 
 * ClassName: IllegalParameterException <br/> 
 * Function: 参数检查不通过时抛出该异常. <br/> 
 * date: Sep 17, 2015 1:38:17 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class IllegalParameterException extends ApplicationException {

	/** 
     * Creates a new instance of IllegalParameterException. 
     * 
     * @param code
     * @param message 
     */  
    public IllegalParameterException(String code, String message) {
        super(code, message);
    }

    /** 
	 * @since JDK 1.8
	 */  
	private static final long serialVersionUID = -6993925611096855415L;

}
