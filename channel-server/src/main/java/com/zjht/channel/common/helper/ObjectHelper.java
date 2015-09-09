package com.zjht.channel.common.helper;

/**
 * 
 * @ClassName: ObjectHelper
 * @Description: 对象处理辅助类
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月25日
 */
public final class ObjectHelper {

	/**
	 * 
	 * trans:将对象转型. <br/> 
	 * <b>example:</b>
	 * 
	 * <p>A implements B</p>
	 * <p>B b  = new A();</p>
	 * <p>A a = ObjectHelper.trans(b);</p>
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param obj
	 * @return 
	 * @since JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	public static <T> T trans(Object obj){
		return (T)obj;
	}
	
	
	/**
	 * 
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
}
