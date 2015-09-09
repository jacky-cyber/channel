/** 
 * Project Name:channel-server 
 * File Name:RegulationEngine.java 
 * Package Name:com.zjht.channel.regulation 
 * Date:2015年8月31日下午2:05:48 
 * 
 */
  
  
package com.zjht.channel.inspector;  
 
/** 
 * ClassName: InspectionEngine <br/> 
 * Function: 规则检查引擎接口定义. <br/> 
 * date: 2015年8月31日 下午2:05:48 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public interface InspectionEngine {
	
	/**
	 * validate:校验所有的规则. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param t 
	 * @since JDK 1.8
	 */
	<T> void validate(T t);
}
  