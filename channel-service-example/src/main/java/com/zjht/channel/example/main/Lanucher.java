/** 
 * Project Name:example-service 
 * File Name:ServerLanucher.java 
 * Package Name:com.zjht.channel.example.main 
 * Date:2015年9月1日下午4:27:26 
 * 
 */
  
  
package com.zjht.channel.example.main;  

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
 
/** 
 * ClassName: ServerLanucher <br/> 
 * Function: 示例启动类. <br/> 
 * date: 2015年9月1日 下午4:27:26 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class Lanucher {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext appContext  = null;
        String filepath = "spring-dubbo.xml";
        try{
            appContext = new ClassPathXmlApplicationContext(new String[] {filepath});
            appContext.registerShutdownHook();
            appContext.start();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }finally{
//            appContext.close();
        }
        System.out.println(">>example-service服务已启动");
        System.in.read();
    }
}
  