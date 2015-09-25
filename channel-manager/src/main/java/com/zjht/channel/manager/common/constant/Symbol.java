/** 
 * Project Name:channel-server 
 * File Name:Symbol.java 
 * Package Name:com.zjht.channel.common.constant 
 * Date:2015年8月27日下午2:44:26 
 * 
 */
  
  
package com.zjht.channel.manager.common.constant;  
 
/** 
 * ClassName: Symbol <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年8月27日 下午2:44:26 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.7
 */
public enum Symbol {
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
	COMMA(","),
	HYPHEN("-"),
	SLASH("/"), 
	DOT(".");
	
	private String code;
	
	private Symbol(String code){
		this.code = code;
	}

	public String code(){
		return this.code;
	}
}
  