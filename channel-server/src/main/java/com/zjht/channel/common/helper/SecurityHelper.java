/** 
 * Project Name:channel-server 
 * File Name:SecurityHelper.java 
 * Package Name:com.zjht.channel.common.helper 
 * Date:2015年9月8日下午1:36:11 
 * 
 */
  
  
package com.zjht.channel.common.helper;  

import java.security.MessageDigest;
 
/** 
 * ClassName: SecurityHelper <br/> 
 * Function: 安全性相关的辅助类. <br/> 
 * date: 2015年9月8日 下午1:36:11 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public final class SecurityHelper {

    /**
     * 生成摘要
     * 
     * @param s
     * @return
     * @throws Exception
     */
    public  static byte[] digest(String plaintext,String algorithm,String charset) 
    {
        byte[] bytes = null;
        try{
            byte[] btInput = plaintext.getBytes(charset);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance(algorithm);
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            bytes = mdInst.digest();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return bytes;
    }
    
}
  