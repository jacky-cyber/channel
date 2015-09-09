/** 
 * Project Name:channel-server 
 * File Name:RegulationException.java 
 * Package Name:com.zjht.channel.exception 
 * Date:2015年8月28日下午3:36:33 
 * 
 */

package com.zjht.channel.exception;

/**
 * ClassName: InspectionException <br/>
 * Function: 规则检查异常类 <br/>
 * date: 2015年8月28日 下午3:36:33 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class InspectionException extends RuntimeException {

    /**
     * @since JDK 1.8
     */
    private static final long serialVersionUID = 2505678993540507870L;

    private String code;
    private String message;

    public InspectionException() {
        super();
    }

    public InspectionException(String code, String message) {
        super(message);
        this.code    = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
