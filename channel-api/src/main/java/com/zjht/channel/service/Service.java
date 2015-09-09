/** 
 * Project Name:channel-server-api 
 * File Name:Service.java 
 * Package Name:com.zjht.channel.service 
 * Date:2015年9月1日下午3:34:30 
 * 
 */

package com.zjht.channel.service;

import java.util.Map;

/**
 * ClassName: Service <br/>
 * Function: 服务接口. <br/>
 * date: 2015年9月1日 下午3:34:30 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public interface Service {

    /**
     * 具体业务处理逻辑的方法. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param params
     * @return String json字符串 按照接口约定返回，这里只需返回具体内容
     * @since JDK 1.8
     */
    Map<String,Object> handle(Map<String, String> params);
}
