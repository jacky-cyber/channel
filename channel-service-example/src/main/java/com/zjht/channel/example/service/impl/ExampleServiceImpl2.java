/** 
 * Project Name:example-service 
 * File Name:ExampleServiceImpl.java 
 * Package Name:com.zjht.channel.example.service.impl 
 * Date:2015年9月1日下午4:09:02 
 * 
 */

package com.zjht.channel.example.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zjht.channel.example.service.ExampleService2;

/**
 * ClassName: ExampleServiceImpl <br/>
 * Function: 服务实现示例. <br/>
 * date: 2015年9月1日 下午4:09:02 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ExampleServiceImpl2 implements ExampleService2 {

    /**
     * @see com.zjht.channel.service.Service#handle(java.util.Map)
     */
    public Map<String,Object> handle(Map<String, String> params) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("Status", "The call is successful.");
        retMap.put("Class Name",this.getClass().getName());
        retMap.put("Parameters",params);
        return retMap;
    }

}
