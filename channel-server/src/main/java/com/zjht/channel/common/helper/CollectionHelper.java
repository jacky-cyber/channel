package com.zjht.channel.common.helper;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * ClassName: CollectionHelper <br/> 
 * Function: 集合相关处理辅助类. <br/> 
 * date: 2015年8月27日 下午2:30:18 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.7
 */
public final class CollectionHelper {
	
	/**
	 * 
	 * parseList:将字符串按照指定格式分割后转换为List集合. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param arryStr 
	 * @param separator
	 * @return 
	 * @since JDK 1.7
	 */
	public static List<String> parse(String arryStr,String separator){
		return Arrays.asList(StringHelper.split(arryStr, separator));
	}
	
}
