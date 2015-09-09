
package com.zjht.channel.inspector.impl;  

import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.inspector.InspectionEngine;
import com.zjht.channel.inspector.Inspector;

 
/** 
 * ClassName: StandardInspectionEngine <br/> 
 * Function: 标准规则检查器引擎. <br/> 
 * date: 2015年8月28日 上午11:50:55 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */

@Service("InspectionEngine")
public class StandardInspectionEngine implements InspectionEngine {
    private final static Logger logger = LoggerFactory.getLogger(StandardInspectionEngine.class);
    
    /**     规则检查器      
            请按顺序排列:
                校验时按照初始化的顺序进行校验，一旦校验失败则退出校验。
     */
    @Resource(name="WhitelistsInspector")
    private Inspector whitelistsInspector;
    
    @Resource(name="RequestPathInspector")
    private Inspector requestPathInspector;
    
    @Resource(name="SecurityInspector")
    private Inspector securityInspector;
    
    @Resource(name="PermissionInspector")
    private Inspector permissionInspector;
    /**      规则检查器         **/
    
    
	/**  使用queue存储所有规则  **/
	private  Queue<Inspector> inspectorQueue = Lists.newLinkedList();
	
	/**
	 * 初始化规则检查器. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com 
	 * @since JDK 1.8
	 */
	@PostConstruct
	public  void init(){
	    logger.info("开始初始化规则检查器...");
        logger.info("开始添加规则检查器[{}]",whitelistsInspector.getClass().getName());
        inspectorQueue.offer(whitelistsInspector);
        logger.info("开始添加规则检查器[{}]",requestPathInspector.getClass().getName());
        inspectorQueue.offer(requestPathInspector);
        logger.info("开始添加规则检查器[{}]",securityInspector.getClass().getName());
        inspectorQueue.offer(securityInspector);
        logger.info("开始添加规则检查器[{}]",permissionInspector.getClass().getName());
        inspectorQueue.offer(permissionInspector);
	    logger.info("成功初始化规则检查器，总个数[{}]",inspectorQueue.size());
	}

	/**
	 * @see com.zjht.channel.inspector.InspectionEngine#validate(java.lang.Object)
	 */
	@Override
	public  <T> void validate(T t){
	    try{
	    inspectorQueue.forEach(i->{
	        if(!i.validate(t)){
	            throw i.getException();
            };
         });
	    }catch(InspectionException ie){
	        throw ie;
	    }catch(Exception e){
	        logger.error("校验器出现错误",e);
	        throw new RuntimeException("校验器出现错误,原因"+e.getMessage());
	    }
	}
}