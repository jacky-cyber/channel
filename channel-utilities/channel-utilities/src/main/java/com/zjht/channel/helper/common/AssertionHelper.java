package com.zjht.channel.helper.common;

/** 
 * 用于断言表达式的工具类.<br/> 
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:29:16 PM 
 */
public class AssertionHelper {
    
    /**
     * 如果表达式不为true，则抛出一个RuntimeException<br/> 
     * 
     * @author jun
     * @param expression
     * @param message 
     * @since JDK 1.8
     */
    public static void check(boolean expression,String message){
        if(!expression){
            throw new RuntimeException(message);
        }
    }
}
