/** 
 * Project Name : channel-manager 
 * File Name : Result.java 
 * Package Name : com.zjht.channel.manager.common.bean 
 * Date : Sep 17, 20159:06:36 AM 
 * 
 */

package com.zjht.channel.manager.controller.bean;

/** 
 * ClassName: Result <br/> 
 * Function: 结果对象. <br/>
 * 前端页面ajax调用后台服务时，返回该对象的json字符串 
 * date: Sep 17, 2015 9:06:36 AM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Result<T> {
	private String code;//提示代码
	private String message;//提示信息
	private T data;//具体内容
	
	public Result(){
	    
	}
	
	public Result(String code,String message){
		this.code    = code;
		this.message = message;
	}
	
	/** 
	 * code. 
	 * 
	 * @return  the code 
	 * @since   JDK 1.8
	 */
	public String getCode() {
		return code;
	}

	/** 
	 * message. 
	 * 
	 * @return  the message 
	 * @since   JDK 1.8
	 */
	public String getMessage() {
		return message;
	}

	/** 
	 * data. 
	 * 
	 * @return  the data 
	 * @since   JDK 1.8
	 */
	public T getData() {
		return data;
	}

	/** 
	 * code. 
	 * 
	 * @param   code    the code to set 
	 * @since   JDK 1.8
	 */
	public Result<T> setCode(String code) {
		this.code = code;
		return this;
	}

	/** 
	 * message. 
	 * 
	 * @param   message    the message to set 
	 * @since   JDK 1.8
	 */
	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	/** 
	 * data. 
	 * 
	 * @param   data    the data to set 
	 * @since   JDK 1.8
	 */
	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	

}
