/** 
 * Project Name:channel-server 
 * File Name:DynamicClassHelper.java 
 * Package Name:com.zjht.channel.service.helper 
 * Date:2015年9月2日下午2:53:42 
 * 
 */
  
  
package com.zjht.channel.service.helper;  

import javassist.ClassPool;
import javassist.CtClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjht.channel.common.helper.FileHelper;
import com.zjht.channel.service.Service;
 
/** 
 * ClassName: DynamicClassLoader <br/> 
 * Function: 动态class加载辅助类，基于javassist. <br/> 
 * date: 2015年9月2日 下午2:53:42 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class DynamicClassLoader {
    
    private final static Logger logger = LoggerFactory.getLogger(DynamicClassLoader.class);
    
    /**
     * 
     * loadClass：动态生成Class实例，并生成Class文件放入项目classpath. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param packagename
     * @param classname
     * @return 
     * @since JDK 1.8
     */
    public static Class<?> loadClass(String fullyQualifiedName){
        ClassPool cPool = null;
        Class<?> clazz  = null;
        try{
            cPool = new ClassPool(true);//使用系统的classpath
            CtClass cc = cPool.makeInterface(fullyQualifiedName);
            
            //设置实现接口com.zjht.channel.service.Service
            CtClass iClass = cPool.get(Service.class.getName());
            cc.setInterfaces(new CtClass[]{iClass});
            
//            CtMethod method = CtNewMethod.abstractMethod(cPool.get(String.class.getName()), "handle", new CtClass[]{cPool.get(Map.class.getName())}, null, cc);
//            cc.addMethod(method);
            
            //设置类名
            cc.setName(fullyQualifiedName);
            
            //将class文件写入到项目的classpath路径
            cc.writeFile(FileHelper.getClasspath());
            
            //通过系统类装载器生成class实例
            clazz = cc.toClass(ClassLoader.getSystemClassLoader(), null);
        }catch(Exception e){
            logger.error("动态生成class[package={},class=,{}]失败",e);
            throw new RuntimeException(e.getMessage());
        }
        return clazz;
    }
}
  