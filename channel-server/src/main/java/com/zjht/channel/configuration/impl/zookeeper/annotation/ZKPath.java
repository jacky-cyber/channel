package com.zjht.channel.configuration.impl.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * zookeeper路径注解类<br/>
 * 表示从zookeeper获取数据的根节点<br/>
 * 使用方式：<br/>
 * 1. 常规<br/>
 * <pre>
 * @ZKPath("/channel/config")
 * public class Config{}
 * </pre>
 * 2. 路径变量:</br/>
 * <pre>
 * @ZKPath("/cahnnel/{name}/{value}")
 * public class Config{
 * 
 *  @ZKPathVariable("name")
 *  public String name;
 *  
 *  @ZKPathVariable("value")
 *  public String value;
 *  
 * }
 * </pre>
 * <p>使用路径变量方式时，需要配合{@link com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPathVariable}使用，否则无法获取到路径值</p>
 * 
 * @author jun
 * @since JDK 1.8
 * @date Sep 23, 2015 9:52:36 PM 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZKPath {
    public String value();
}
