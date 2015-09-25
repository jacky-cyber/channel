package com.zjht.channel.helper.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * List集合相关处理工具类 <br/>
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:24:03 PM
 */
public final class ListHelper {

    /**
     * 生成一个新的ArrayList<T>对象<br/>
     * 
     * @author jun
     * @return
     * @since JDK 1.8
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    /**
     * 根据传入的参数(类型T)构建ArrayList<T>对象. <br/>
     * <b>使用示例:</b><br/>
     * <p>
     * List<String> list = listOf("a","b","c");
     * </p>
     * <b>注意:</b><br/>
     * <p>
     * 使用该方法时，args个数至少为1
     * 
     * @author jun
     * @param args 参数，支持1～n
     * @return
     * @since JDK 1.8
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> listOf(T... args) {
        AssertionHelper.check(!Objects.isNull(args) && args.length > 0, "args不能为空");
        return new ArrayList<T>(Arrays.asList(args));
    }


    /**
     * 
     * 判断list集合是否为空<br/>
     * 传入值为null或者元素为0返回true，其他情况返回false
     * 
     * @author jun
     * @param list
     * @return 
     * @since JDK 1.8
     */
    public static <T> boolean isEmpty(List<T> list) {
        return ObjectHelper.isNull(list) || 0 == list.size();
    }

}
