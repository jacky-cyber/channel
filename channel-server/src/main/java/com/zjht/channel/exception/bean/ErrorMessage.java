/** 
 * Project Name:channel-server 
 * File Name:ErrorMessage.java 
 * Package Name:com.zjht.channel.common.vo 
 * Date:2015年8月28日下午3:40:56 
 * 
 */
  
  
package com.zjht.channel.exception.bean;  
 
/** 
 * ClassName: ErrorMessage <br/> 
 * Function: 处理出现异常时的响应信息对象. <br/> 
 * date: 2015年8月28日 下午3:40:56 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ErrorMessage {
	private String respCode;
	private String respMsg;
	
	public ErrorMessage(){
	    super();
	}
	
	public ErrorMessage(String respCode,String respMsg){
	    this.respCode = respCode;
	    this.respMsg  = respMsg;
	}
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

    @Override
    public String toString() {
        return "ErrorMessage [respCode=" + respCode + ", respMsg=" + respMsg
                + "]";
    }
}
  