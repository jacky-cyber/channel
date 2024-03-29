/**
 * Project Name:channel-server File Name:ServiceException.java Package
 * Name:com.zjht.channel.exception Date:2015年9月2日下午3:46:13
 * 
 */


package com.zjht.channel.exception;

import com.zjht.channel.common.constant.RespCode;

/**
 * ClassName: ServiceException <br/>
 * Function: 服务调用异常类. <br/>
 * date: 2015年9月2日 下午3:46:13 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ServiceException extends ApplicationException {

    /**
     * Creates a new instance of ServiceException.
     * 
     * @param code
     * @param message
     */
    public ServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * @since JDK 1.8
     */
    private static final long serialVersionUID = -7025798595288080079L;



}
