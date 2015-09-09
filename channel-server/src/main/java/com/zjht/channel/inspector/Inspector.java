package com.zjht.channel.inspector;


/**
 * 
 * ClassName: Inspector <br/> 
 * Function: 规则检查器接口. <br/> 
 * date: 2015年9月8日 下午2:05:09 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface Inspector{
	
	
    /**
     * 检查的具体方法 <br/> 
     * <b>注意：</b>
     * <p>当返回值为false时，务必保证getException()的值不为空<br/>
     * 否则会出现NullPointException。<br/>
     * </P>
     *
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param t
     * @return 
     * @since JDK 1.8
     */
	<T> boolean validate(T t);
	
	
	/**
	 * 
	 * 返回校验失败的Exception. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */
	RuntimeException getException();
	
} 


