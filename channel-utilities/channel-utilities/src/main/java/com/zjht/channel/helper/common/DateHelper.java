package com.zjht.channel.helper.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 日期相关处理辅助类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 1:49:27 PM 
 */
public final class DateHelper {
    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    private final static SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DEFAULT_FORMAT);  
    
    /**
     * 根据传入的格式和日期，格式日期字符串
     * 
     * @author jun
     * @param date
     * @param format
     * @return 
     * @since JDK 1.8
     */
    public static String format(Date date,String format){
        if(ObjectHelper.equal(format, DEFAULT_FORMAT)){
            return DEFAULT_DATE_FORMATTER.format(date);
        }
        return new SimpleDateFormat(format).format(date);
    }
    
    
    /**
     * 根据传入的格式和日期，格式日期字符串
     * 
     * @author jun
     * @param date
     * @param format
     * @return 
     * @since JDK 1.8
     */
    public static String format(Date date){
        return format(date,DEFAULT_FORMAT);
    }
    
    public static void main(String[] args) {
        System.out.println(format(new Date()));
    }
}
