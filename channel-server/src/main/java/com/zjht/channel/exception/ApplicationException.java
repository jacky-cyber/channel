package com.zjht.channel.exception;

import com.zjht.channel.common.constant.RespCode;

/**
 * 自定义非受检异常类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 23, 2015 9:31:43 AM
 */
public class ApplicationException extends RuntimeException {

    /**
     * @since JDK 1.8
     */
    private static final long serialVersionUID = 5426594297458264932L;
    private String code;
    private String message;
    
    
    public ApplicationException(String code,String message) {
        super(message);
        this.code    = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
    
    public void throwout(){
        throw this;
    }
}
