/** 
 * Project Name:channel-server 
 * File Name:ServiceHelper.java 
 * Package Name:com.zjht.channel.service.helper 
 * Date:2015年9月1日上午10:19:35 
 * 
 */
  
  
package com.zjht.channel.service.helper;  

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.helper.common.StringHelper;
 
/** 
 * ClassName: ServiceHelper <br/> 
 * Function: 服务处理相关辅助类. <br/> 
 * date: 2015年9月1日 上午10:19:35 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public final class ServiceHelper {
    
    /**
     * 生成服务的唯一标识，用作调用com.zjht.channel.service.ServiceManager#invoke(java.lang.String,
     *      java.lang.String, java.util.Map).<br/>
     *  时，获取指定的服务。 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param svrName   服务名称
     * @param svrVersion 服务版本
     * @return 
     * @since JDK 1.8
     */
    public static String getIdentify(String svrName,String svrVersion){
        Preconditions.checkArgument(!StringHelper.isEmpty(svrName), "服务名称不能为空！");
        Preconditions.checkArgument(!StringHelper.isEmpty(svrVersion), "服务版本不能为空！");
        
        return svrName.concat(Symbol.HYPHEN.code()).concat(svrVersion);
    }
    
    
    public static void test(){
        
    }
    
}
  