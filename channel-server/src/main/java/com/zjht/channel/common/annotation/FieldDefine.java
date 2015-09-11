/** 
 * Project Name:channel-server 
 * File Name:FieldDefine.java 
 * Package Name:com.zjht.channel.common.annotation 
 * Date:Sep 11, 201512:50:29 PM 
 * 
 */

package com.zjht.channel.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zjht.channel.common.constant.Type;

/** 
 * ClassName: FieldDefine <br/> 
 * Function: 自定义字段规则定义注解. <br/> 
 * date: Sep 11, 2015 12:50:29 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDefine {
	public boolean required() default false;//是否必须
	public int length() default 0;//长度
	public Type type() default Type.String;//类型
	public String regexp() default "";//符合正则表达式，仅限Type.String
}
