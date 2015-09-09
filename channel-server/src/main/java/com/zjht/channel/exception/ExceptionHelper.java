/** 
 * Project Name:channel-server 
 * File Name:ExceptionHelper.java 
 * Package Name:com.zjht.channel.exception 
 * Date:2015年8月28日下午3:43:44 
 * 
 */
  
  
package com.zjht.channel.exception;  

import com.zjht.channel.exception.bean.ErrorMessage;
 
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
	
	/**
	 * 
	 * wrapper:将异常包装为自定义对象. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param t
	 * @return 
	 * @since JDK 1.8
	 */
	
	public ErrorMessage wrapper(Throwable t){
		ErrorMessage errMsg = new ErrorMessage();
//		
//		String msg = t.getMessage();
//		
//		if(RespCode.isRespCode(msg)){
//			errMsg.setRespCode(msg);
//			errMsg.setRespMsg(RespCode.message(msg));
//		}else{
//			errMsg.setRespCode(RespCode._99999);
//			errMsg.setRespMsg(msg);
//		}
//		
		return errMsg;
	}

    /** 
     * newException:抛出一个InspectionException异常. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param code
     * @param message 
     * @since JDK 1.8
     */  
    public static void newException(String code, String message) {
       throw new InspectionException(code,message);
    }
}
  