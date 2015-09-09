/** 
 * Project Name:channel-server 
 * File Name:ServiceManager.java 
 * Package Name:com.zjht.channel.service 
 * Date:2015年9月1日上午9:46:47 
 * 
 */
  
  
package com.zjht.channel.service;  

import java.util.Map;

import com.zjht.channel.service.bean.ServiceInfo;
 
/** 
 * ClassName: ServiceManager <br/> 
 * Function: 服务管理接口定义. <br/> 
 * date: 2015年9月1日 上午9:46:47 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public interface ServiceManager {
    
    /**
     * 
     * load:加载服务. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param service 
     * @since JDK 1.8
     */
    void load(ServiceInfo service);
    
    
    
    /**
     * 
     * unload:卸载服务. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param service 
     * @since JDK 1.8
     */
    void unload(ServiceInfo service);
    
    
    /**
     * invoke:调用指定的名称和版本的服务. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param svrName
     * @param svrVersion
     * @param params
     * @return Map<String,Object>
     * @since JDK 1.8
     */
    Map<String,Object> invoke(String svrName,String svrVersion,Map<String,String> params);
}
  