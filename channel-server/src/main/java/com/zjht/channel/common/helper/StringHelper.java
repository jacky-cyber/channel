package com.zjht.channel.common.helper;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

/**
 * 
 * ClassName: StringHelper <br/> 
 * Function: 字符串处理辅助类. <br/> 
 * date: 2015年8月27日 下午2:13:28 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version  v0.0.1
 * @since JDK 1.7
 */
public final class StringHelper {
	

	/**
	 * 
	 * newString:根据byte数组按照指定字符编码生成字符串. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param bytes byte数组
	 * @param charset 字符编码
	 * @return 
	 * @since JDK 1.7
	 */
	public static String newString(byte[] bytes,Charset charset){
		return null==bytes?"":new String(bytes,charset);
	}
	

	/**
	 * 
	 * newString:根据byte数组按照指定字符编码生成字符串. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param bytes
	 * @param charsetName
	 * @return 
	 * @since JDK 1.7
	 */
	public static String newString(byte[] bytes,String charsetName){
		return null==bytes?"":new String(bytes,Charset.forName(charsetName));
	}
	

	/**
	 * 
	 * split:将字符串按照指定分隔符分割成字符串数组. <br/> 
	 * <b>example:</b><br/> 
	 * split("adad","a")->{"","d","d"}<br/> 
	 * split("a||123|","|")->{"a","","123",""}<br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param str
	 * @param separator
	 * @return 
	 * @since JDK 1.7
	 */
	public static String[] split(String str,String separator){
		return null==str?new String[]{}:str.split(separator);
	}

	/**
	 * 
	 * trim:去除传入字符串两旁的空白符. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param str
	 * @return 
	 * @since JDK 1.7
	 */
	public static String trim(String str){
		return null==str?"":str.trim();
	}

	/**
	 * 
	 * isEmpty:判断字符串是否为空（null或者空串)<br/> 
	 * <b>example:</b><br/>
	 * <p>isEmpty(null)->true<br/> 
	 * isEmpty("")->true<br/> 
	 * isEmpty(" ")->true<br/> 
	 * isEmpty("abc")->false</p><br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param str
	 * @return 
	 * @since JDK 1.7
	 */
	public static boolean isEmpty(String str){
		return null==str||"".equals(trim(str));
	}
	
	/**
	 * 
	 * replace:将{}占位符替换为指定字符串. <br/> 
	 * <b>example:</b>
	 * <p>replace("您的号码{}没访问权限","111")==>您的号码111没有访问权限
	 * <p>replace("aaa{}bbb{}ccc","111"，"222")==>aaa111bbb222ccc
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param original
	 * @param params
	 * @return 
	 * @since JDK 1.8
	 */
	public static String replace(String original,Object ... params){
		Preconditions.checkArgument(!isEmpty(original), "替换字符串不能为空");
		Preconditions.checkArgument(params!=null&&params.length>0, "替换参数不能为空！");
		
		String[] strs = split(original, "[{][}]");
		Preconditions.checkArgument((strs.length-1)==params.length, "占位符个数["+(strs.length-1)+"]与参数个数["+params.length+"]不一致!");
		StringBuilder buffer = new StringBuilder();
		
		for (int i = 0; i < params.length; i++) {
		    if(i==0){
		        buffer.append(strs[i]);
		    }
			buffer.append(params[i]).append(strs[i+1]);
		}
		return buffer.toString();
	}


    /** 
     * 根据指定长度和字符串在左边填充字符串. <br/> 
     * <b>example:</b><br/>
     * <p>leftPad("123",10,"0")==>"0000000123"</p>
     * @author jun yangwenjun@chinaexpresscard.com
     * @param str    需要填充的字符串
     * @param maxLen 填充长度
     * @param symbol 填充字符串
     * @return 
     * @since JDK 1.8
     */  
    public static String leftPad(String str, int maxLen,String symbol) {
        Preconditions.checkArgument(!isEmpty(str), "传入字符串不能为空");
        Preconditions.checkArgument(!isEmpty(symbol), "传入填充字符串不能为空");
        
        for (int i = 0; i < maxLen; i++) {
            str = symbol + str;
        }
        return str;
    } 
    
    public static void main(String[] args) {
        System.out.println(leftPad("123",-1,"0"));
        System.out.println(leftPad("123",7,"0"));
    }
    
    
	
}
