package com.zjht.channel.manager.exception;

/**
 * 
 * 与zookeeper操作异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 10:51:12 PM
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
     * serialVersionUID:TODO 
     * @since JDK 1.8
     */  
    private static final long serialVersionUID = -5865907556313218435L;

}
