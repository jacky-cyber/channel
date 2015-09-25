/**
 * Project Name:channel-server File Name:ExceptionHelper.java Package
 * Name:com.zjht.channel.exception Date:2015年8月28日下午3:43:44
 * 
 */


package com.zjht.channel.exception;

import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.helper.common.StringHelper;

/**
 * ClassName: ExceptionHelper <br/>
 * Function: 异常辅助处理类. <br/>
 * date: 2015年8月28日 下午3:43:44 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ExceptionHelper {

    public static ApplicationException newApplicationException(RespCode respCode,Object... args) {
        return new ApplicationException(respCode.code(),StringHelper.replace(respCode.message(), args));
    }
    
    public static InspectionException newInspectionException(RespCode respCode,Object... args) {
        return new InspectionException(respCode.code(),StringHelper.replace(respCode.message(), args));
    }

    public static ZookeeperException newZookeeperException(RespCode respCode,Object... args) {
        return new ZookeeperException(respCode.code(),StringHelper.replace(respCode.message(), args));
    }
    
    public static void main(String[] args) {
    }
    
}
