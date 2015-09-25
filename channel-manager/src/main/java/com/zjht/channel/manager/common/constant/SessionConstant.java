/** 
 * Project Name : channel-manager 
 * File Name : SessionConstant.java 
 * Package Name : com.zjht.channel.manager.common.constant 
 * Date : Sep 14, 20155:20:03 PM 
 * 
 */

package com.zjht.channel.manager.common.constant;

/** 
 * ClassName: SessionConstant <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 14, 2015 5:20:03 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public enum SessionConstant {
	USER("session_user"),
	THEME("session_theme")	;
	private String name;
	
	private SessionConstant(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
