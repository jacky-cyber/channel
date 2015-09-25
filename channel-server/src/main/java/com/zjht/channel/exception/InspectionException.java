package com.zjht.channel.exception;

import com.zjht.channel.common.constant.RespCode;

/**
 *  规则检查异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 23, 2015 9:36:21 AM
 */
public class InspectionException extends ApplicationException {

    /** 
     * Creates a new instance of InspectionException. 
     * 
     * @param code
     * @param message 
     */  
    public InspectionException(String code, String message) {
        super(code, message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @since JDK 1.8
     */
    private static final long serialVersionUID = 2505678993540507870L;
  
}
