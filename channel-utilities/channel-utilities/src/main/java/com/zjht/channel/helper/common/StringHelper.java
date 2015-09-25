package com.zjht.channel.helper.common;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.sun.media.jfxmedia.logging.Logger;

/**
 * 
 * ClassName: StringHelper <br/>
 * Function: 字符串处理辅助类. <br/>
 * date: 2015年8月27日 下午2:13:28 <br/>
 * 
 * @author jun
 * @version 0.1
 * @since JDK 1.8
 */
public final class StringHelper {

    /**
     * 根据byte数组按照指定字符编码生成字符串. <br/>
     * 
     * @author jun
     * @param bytes byte数组
     * @param charset 字符编码
     * @return
     * @since JDK 1.8
     */
    public static String newString(byte[] bytes, Charset charset) {
        return null == bytes ? "" : new String(bytes, charset);
    }


    /**
     * 
     * 根据byte数组按照指定字符编码生成字符串. <br/>
     * 
     * @author jun
     * @param bytes
     * @param charsetName
     * @return
     * @since JDK 1.8
     */
    public static String newString(byte[] bytes, String charsetName) {
        return null == bytes ? "" : new String(bytes, Charset.forName(charsetName));
    }


    /**
     * 将字符串按照指定分隔符分割成字符串数组. <br/>
     * <b>example:</b><br/>
     * split("adad","a")->{"","d","d"}<br/>
     * split("a||123|","|")->{"a","","123",""}<br/>
     * 
     * @author jun
     * @param original
     * @param separator
     * @return
     * @since JDK 1.8
     */
    public static String[] split(String original, String separator) {
        AssertionHelper.check(!isEmpty(original), "original String can not be empty!!!");
        AssertionHelper.check(!isEmpty(separator), "separator can not be empty!!!");

        int originalLen = original.length();
        int separatorLen = separator.length();
        AssertionHelper.check(separatorLen <= originalLen, "separator's length must less original String!!!");
        List<String> strArry = ListHelper.newArrayList();
        StringBuilder strBuilder = new StringBuilder();
        String tempStr = "";
        for (int i = 0; i < originalLen;) {
            if (i == 0) {
                tempStr = original.substring(i, i + separatorLen);
                if (Objects.equals(separator, tempStr)) {
                    strArry.add("");
                    i += separatorLen;
                } else {
                    strBuilder.append(original.charAt(i));
                    i++;
                }
            } else if (0 < i && i < (originalLen - separatorLen)) {
                tempStr = original.substring(i, i + separatorLen);
                if (Objects.equals(separator, tempStr)) {
                    strArry.add(strBuilder.toString());
                    strBuilder.setLength(0);
                    i += separatorLen;
                } else {
                    strBuilder.append(original.charAt(i));
                    i++;
                }
            } else {
                
                tempStr = original.substring(i);
                if (Objects.equals(separator, tempStr)) {
                    strArry.add(strBuilder.toString());
                    strArry.add("");
                } else {
                    strBuilder.append(tempStr);
                    strArry.add(strBuilder.toString());
                }
                break;
            }
        }
        return strArry.toArray(new String[strArry.size()]);
    }

    /**
     * 
     * 去除传入字符串两旁的空白符. <br/>
     * 
     * @author jun
     * @param str
     * @return
     * @since JDK 1.7
     */
    public static String trim(String str) {
        return null == str ? "" : str.trim();
    }

    /**
     * 
     * 判断字符串是否为空（null或者空串)<br/>
     * <b>example:</b><br/>
     * <p>
     * isEmpty(null)->true<br/>
     * isEmpty("")->true<br/>
     * isEmpty(" ")->true<br/>
     * isEmpty("abc")->false
     * </p>
     * <br/>
     * 
     * @author jun
     * @param str
     * @return
     * @since JDK 1.7
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(trim(str));
    }

    /**
     * 将{}占位符替换为指定字符串. <br/>
     * <b>example:</b>
     * <p>
     * replace("您的号码{}没访问权限","111")==>您的号码111没有访问权限
     * <p>
     * replace("aaa{}bbb{}ccc","111"，"222")==>aaa111bbb222ccc
     * 
        
        
        return
     * @author jun
     * @param original
     * @param args
     * @return
     * @since JDK 1.8
     */
    public static String replace(String original, Object... args) {
        if (isEmpty(original)) {
            return "";
        }

        if (null == args || args.length == 0) {
            return original;
        }

        String[] strs = split(original, "{}");
        AssertionHelper.check((strs.length-1) == args.length,
                "占位符个数[" + (strs.length-1) + "]与参数个数[" + args.length + "]不一致!");

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                buffer.append(strs[i]);
            }
            buffer.append(args[i]).append(strs[i + 1]);
        }
        return buffer.toString();
    }


    /**
     * 根据指定长度和字符串在左边填充字符串. <br/>
     * <b>example:</b><br/>
     * <p>
     * leftPad("123",10,"0")==>"0000000123"
     * </p>
     * 
     * @author jun
     * @param str 需要填充的字符串
     * @param maxLen 填充长度
     * @param symbol 填充字符串
     * @return
     * @since JDK 1.8
     */
    public static String leftPad(String str, int maxLen, String symbol) {
        AssertionHelper.check(!isEmpty(str), "传入字符串不能为空");
        AssertionHelper.check(!isEmpty(symbol), "传入填充字符串不能为空");
        for (int i = 0; i < maxLen; i++) {
            str = symbol + str;
        }
        return str;
    }


    /**
     * 将字符串转换成byte数组
     * 
     * @author jun
     * @param data
     * @param charset
     * @return
     * @since JDK 1.8
     */
    public static byte[] bytes(String data, String charset) {
        AssertionHelper.check(!isEmpty(charset), "字符编码不能为空");

        return trim(data).getBytes(Charset.forName(charset));
    }

    public static String stest(String s, Object... sss) {
        return replace(s, sss);
    }

    public static String[] split2(String s, String spector) {
        String str = "";
        int index = 0;
        int len = spector.length();
        for (; s.indexOf(spector) != -1;) {
            index = s.indexOf(spector);
            str = s.substring(0, index);
            s = s.substring(index + len);
            System.out.println(str);
        }
        return null;
    }
    
    
    public static int times(String original,String matchers){
        AssertionHelper.check(!isEmpty(original), "original String can not be empty!!!");
        AssertionHelper.check(!isEmpty(matchers), "matchers can not be empty!!!");
        
        int originalLen = original.length();
        int matchersLen = matchers.length();
        
        AssertionHelper.check(matchersLen<=originalLen, "matchers's length must less & equal origina'sl length!!!");
        int times = 0;
        String tempStr = "";
        
        for (int i = 0; i <= (originalLen-matchersLen); i++) {
            tempStr = original.substring(i,i+matchersLen);
            if(Objects.equals(tempStr, matchers)){
                times++;
            }
        }
        return times;
    }
    
    
    /**
     * 
     * beta!!!!
     * 
     * @author jun
     * @param original
     * @param beginHolder
     * @param endHolder
     * @return
     */
    public static String[] hold(String original,String beginHolder,String endHolder){
        AssertionHelper.check(!isEmpty(original), "original String can not be empty!!!");
        AssertionHelper.check(!isEmpty(beginHolder), "beginHolder can not be empty!!!");
        AssertionHelper.check(!isEmpty(endHolder), "endHolder can not be empty!!!");
        
        int originalLen  = original.length();
        int beginHolderLen =  beginHolder.length();
        int endHolderLen = endHolder.length();
        
        AssertionHelper.check((beginHolderLen+endHolderLen)<=originalLen, "this sum of beginHolder&endHolder must less or equal original !!!");
        
        int beginHolderTimes = times(original,beginHolder);
        int endHolderTimes = times(original,endHolder);
        AssertionHelper.check(beginHolderTimes==endHolderTimes, "beginHolder's times must equal endHolder's times !!!");
        
        String tempStr = original;
        int beginIndex = 0;
        int endIndex   = 0;
        String[] holderStrs = new String[beginHolderTimes];
        for (int i = 0; i < beginHolderTimes; i++) {
             tempStr =  tempStr.substring(endIndex);
             beginIndex = tempStr.indexOf(beginHolder);
             endIndex   = tempStr.indexOf(endHolder);
             holderStrs[i] = tempStr.substring(beginIndex+1, endIndex);
             endIndex++;
        }
        return holderStrs;
    }

    public static void main(String[] args) {
        System.out.println(replace("{}!!{}!!","BBB","CCC"));
        System.out.println(Arrays.toString(split("|asdasd|asdasd|","|")));
    }
}
