package com.zjht.channel.helper.common;

import java.util.Arrays;
import java.util.Collection;

/**
 * 集合类相关处理工具类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:48:22 PM
 */
public class CollectionHelper {

    /**
     * 给传入的Collection对象添加值. <br/>
     * <b>Example:</b><br/>
     * List<String> list = new ArrayList<String>();<br/>
     * setOf(list,"a","b","c");
     * 
     * @author jun
     * @param c 集合实例
     * @param args 需要添加的值，支持0~n个
     * @since JDK 1.8
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> of(Collection<T> c, T... args) {
        c.addAll(Arrays.asList(args));
        return c;
    }
}
