package com.zjht.channel.helper.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Set集合相关处理工具类<br/>
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:47:34 PM
 */
public final class SetHelper {
    /**
     * 根据传入的参数构建HashSet对象. <br/>
     * <b>使用示例:</b><br/>
     * <p>
     * Set<String> set = setOf("a","b","c");
     * </p>
     * 
     * @author jun
     * @param args 需要设置的值，支持0~n个
     * @return
     * @since JDK 1.8
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> setOf(T... args) {
        return new HashSet<T>(Arrays.asList(args));
    }
}
