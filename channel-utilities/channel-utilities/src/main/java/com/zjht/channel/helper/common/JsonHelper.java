package com.zjht.channel.helper.common;

import java.text.DateFormat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 基于gson的json字符串相关处理工具类. <br/>
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 9:43:38 PM
 */
public final class JsonHelper {
    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat(DateFormat.LONG)
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//            .setPrettyPrinting()
            .setVersion(1.0)
            .create();

    /**
     * 将对象转为json字符串
     * 
     * @author jun
     * @param obj
     * @return 
     * @since JDK 1.8
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
   
    /**
     * 将json字符串转换为对象 
     * 
     * @author jun
     * @param json
     * @param clazz
     * @return 
     * @since JDK 1.8
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
