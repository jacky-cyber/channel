/** 
 * Project Name:channel-server 
 * File Name:JsonHelper.java 
 * Package Name:com.zjht.channel.common.helper 
 * Date:2015年8月28日下午3:13:31 
 * 
 */
  
  
package com.zjht.channel.common.helper;  

import java.text.DateFormat;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zjht.channel.common.bean.Response;
 
/** 
 * ClassName: JsonHelper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年8月28日 下午3:13:31 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public final class JsonHelper {
	private static Gson gson = new GsonBuilder()
//		.registerTypeAdapter(Id.class, new IdTypeAdapter())
		.enableComplexMapKeySerialization()
		.serializeNulls()
		.setDateFormat(DateFormat.LONG)
		.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//		.setPrettyPrinting()
		.setVersion(1.0)
		.create();
	
	/**
	 * 
	 * toJson:将对象转为json字符串. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param obj
	 * @return 
	 * @since JDK 1.8
	 */
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		System.out.println(toJson(list));;
	}


	/**
	 * 
	 * 将json字符串转换为对象. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param json
	 * @param clazz
	 * @return 
	 * @since JDK 1.8
	 */
    public static <T>T fromJson(String json,Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
  