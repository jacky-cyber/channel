/** 
 * Project Name:channel-server 
 * File Name:Parameters.java 
 * Package Name:com.zjht.channel.common.constant
 * Date:2015年8月28日下午2:46:11 
 * 
 */
  
  
package com.zjht.channel.common.constant;  
 
/** 
 * ClassName: Parameters <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年8月28日 下午2:46:11 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public enum Parameters {
	APPNO("appNo"),
	MSG("msg"),
	SIGN("sign");
	
	private String name;
	
	private Parameters(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
  