/** 
 * Project Name:example-service 
 * File Name:Response.java 
 * Package Name:com.zjht.channel.example.service.bean 
 * Date:2015年9月8日上午11:57:43 
 * 
 */
  
  
package com.zjht.channel.example.service.bean;  
 
/** 
 * ClassName: Response <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年9月8日 上午11:57:43 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Response implements java.io.Serializable{
    /** 
     * serialVersionUID:TODO 
     * @since JDK 1.8
     */  
    private static final long serialVersionUID = 1163710513117145366L;
    private String code;
    private String message;
    
    public Response(){
        super();
    }
    
    /** 
     * Creates a new instance of Response. 
     * 
     * @param string
     * @param string2 
     */  
    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
  