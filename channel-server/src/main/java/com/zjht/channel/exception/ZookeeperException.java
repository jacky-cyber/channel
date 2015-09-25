package com.zjht.channel.exception;

import com.zjht.channel.common.constant.RespCode;

/**
 *  操作zookeeper时的异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 23, 2015 9:36:21 AM
 */
public class ZookeeperException extends ApplicationException {

    /** 
     * Creates a new instance of ZookeeperException. 
     * 
     * @param code
     * @param message 
     */  
    public ZookeeperException(String code, String message) {
        super(code, message);
    }

    /** 
     * TODO 
     *
     * @since JDK 1.8
     */  
    private static final long serialVersionUID = 1686010072488807395L;

  
}
