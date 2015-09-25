package com.zjht.channel.helper.common;

/**
 * 
 * 对象相关处理工具类 
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:49:57 PM
 */
public final class ObjectHelper {

	/**
	 * 将对象强制转型
	 * <b>示例：</b><br/>
	 * <pre>
	 * Object a = new String();
	 * a = "test string";
	 * if(a instanceof String){ 
	 *     String b = trans(a);
	 * } 
	 * </pre>
	 * 
	 * <b>注意：</b></br/>
	 * <p>在使用该方法前需要注意判断实例类型</p>
	 * 
	 * @author jun
	 * @param obj
	 * @return 
	 * @since JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	public static <T> T trans(Object obj){
		return (T)obj;
	}
	
	/**
	 * equals:两个对象比较，地址相同或equal为ture则返回ture，否则返回false. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param o1
	 * @param o2
	 * @return 
	 * @since JDK 1.8
	 */
	public static boolean equal(Object o1,Object o2){
	    return o1 == o2 || (o1 != null && o1.equals(o2));
	}
	
	/**
	 * 判断对象是否为null，是返回ture，否则返回false
	 * 
	 * @author jun
	 * @param obj
	 * @return 
	 * @since JDK 1.8
	 */
	public static boolean isNull(Object obj){
	    return null==obj;
	}
}
