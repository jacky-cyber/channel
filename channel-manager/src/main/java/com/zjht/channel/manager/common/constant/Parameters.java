/** 
 * Project Name : channel-manager 
 * File Name : Parameters.java 
 * Package Name : com.zjht.channel.manager.common.constant 
 * Date : Sep 14, 20154:38:22 PM 
 * 
 */

package com.zjht.channel.manager.common.constant;

/** 
 * ClassName: Parameters <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 14, 2015 4:38:22 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public enum Parameters {
	ERROR("error"),
	ERROR_FLAG("error_flag"),
	ERROR_CODE("error_code"),
	ERROR_MESSAGE("error_message");
	
	private String name;
	
	private Parameters(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
