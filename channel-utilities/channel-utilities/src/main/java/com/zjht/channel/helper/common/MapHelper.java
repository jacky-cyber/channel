package com.zjht.channel.helper.common;

import java.util.HashMap;
import java.util.Map;

/** 
 * Map集合相关处理辅助类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 2:09:36 PM 
 */
public final class MapHelper {
    
    /**
     * @author jun
     * @return 
     * @since JDK 1.8
     */
    public static <K,V> Map<K,V> newHashMap(){
        return new HashMap<K,V>();
    }
}
