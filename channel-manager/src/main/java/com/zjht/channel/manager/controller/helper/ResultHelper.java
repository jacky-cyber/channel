package com.zjht.channel.manager.controller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.controller.WhitelistsController;
import com.zjht.channel.manager.controller.bean.Result;
import com.zjht.channel.manager.exception.ApplicationException;

/** 
 * ClassName: ResultlHelper <br/> 
 * Function: Resutl对象辅助处理类. <br/> 
 * date: Sep 19, 2015 1:55:47 PM <br/> 
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 */
public final class ResultHelper {
    private final static Logger logger = LoggerFactory.getLogger(ResultHelper.class);
    
    /**
     * 生成Result对象. <br/> 
     * 
     * @author jun
     * @param error
     * @param t
     * @return 
     * @since JDK 1.8
     */
    public static <T> Result<T> newResult(ErrorMessage error,Object ... args){
        return new Result<T>(error.code(),StringHelper.replace(error.message(), args));
    }
    
    /**
     * 将异常信息包装后返回result对象. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param e
     * @return 
     * @since JDK 1.8
     */
    public static <T> Result<T> newResult(Exception e){
        Result<T> result = null;
        if(e instanceof ApplicationException){
            logger.error("ApplicationException...",e);
            ApplicationException ae = ObjectHelper.trans(e);
            result = new Result<>(ae.code(),ae.message());
        }else{
            logger.error("未处理的异常信息：",e);
            result = new Result<>(ErrorMessage._99999.code(),ErrorMessage._99999.message());
        }
        return result;
    }
}
