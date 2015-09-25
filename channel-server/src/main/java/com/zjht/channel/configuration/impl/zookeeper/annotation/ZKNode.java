package com.zjht.channel.configuration.impl.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *  zookeeper节点数据规则注解
 * 
 * @author jun
 * @since JDK 1.8
 * @date Sep 23, 2015 9:55:36 PM
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZKNode {
    
    /**
     * 字段是否必须. <br/>
     */
    public boolean required() default false;

    /**
     * 字段长度<br/>
     * 仅适用于<b>字符串(java.lang.String)</b>，不适用于其他对象. <br/>
     */
    public int length() default 0;

    /**
     * 字段最大长度<br/>
     * 仅适用于<b>字符串(java.lang.String)</b>，不适用于其他对象. <br/>
     */
    public int maxLength() default 0;

    /**
     * 字段最小长度<br/>
     * 仅适用于<b>字符串(java.lang.String)</b>，不适用于其他对象. <br/>
     */
    public int minLength() default 0;

    /**
     * 正则 仅适用于<b>字符串(java.lang.String)</b>，不适用于其他对象. <br/>
     */
    public String regex() default "";

    /**
     * 字段的名称<br/>
     */
    public String name();

    /**
     * 字段取值范围 仅适用于<b>字符串(java.lang.String)</b>，不适用于其他对象. <br/>
     */
    public String[] range() default {};
    
    /**
     * 当设置值为空时的默认值 
     */
    public String value() default "";
}
