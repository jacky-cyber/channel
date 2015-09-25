/** 
 * Project Name : channel-manager 
 * File Name : BaseException.java 
 * Package Name : com.zjht.channel.manager.exception 
 * Date : Sep 16, 201510:23:41 AM 
 * 
 */

package com.zjht.channel.manager.exception;

import com.zjht.channel.manager.common.constant.ErrorMessage;

/** 
 * ClassName: BaseException <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 16, 2015 10:23:41 AM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class ApplicationException extends RuntimeException {

	/** 
	 * @since JDK 1.8
	 */  
	private static final long serialVersionUID = 509148855268319049L;
	private String code;
	private String message;
	
	public ApplicationException(String  code,String message){
		super(message);
		this.code    = code;
		this.message = message;
	}

	/** 
	 * code. 
	 * 
	 * @return  the code 
	 * @since   JDK 1.8
	 */
	public String code() {
		return code;
	}

	/** 
	 * message. 
	 * 
	 * @return  the message 
	 * @since   JDK 1.8
	 */
	public String message() {
		return message;
	}
	
	/**
	 * 抛出该异常
	 * 
	 * @author jun 
	 * @since JDK 1.8
	 */
	public void throwout(){
	    throw this;
	}
}
