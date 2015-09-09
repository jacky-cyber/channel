/** 
 * Project Name:channel-server 
 * File Name:ServiceInvokerListener.java 
 * Package Name:com.zjht.channel.service.impl.dubbo.listener 
 * Date:2015年9月8日上午10:24:43 
 * 
 */
  
  
package com.zjht.channel.service.impl.dubbo.listener;  

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.InvokerListener;
import com.alibaba.dubbo.rpc.RpcException;
import com.zjht.channel.service.ServiceManager;
 
/** 
 * ClassName: ServiceInvokerListener <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年9月8日 上午10:24:43 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ServiceInvokerListener implements  InvokerListener{
    private final static Logger logger = LoggerFactory.getLogger(ServiceInvokerListener.class);

    
    public ServiceInvokerListener(){
        logger.info("initialized！！！！！！！！！！！！！！！！！！！！！");
    }
    
    /** 
     * @see com.alibaba.dubbo.rpc.InvokerListener#referred(com.alibaba.dubbo.rpc.Invoker) 
     */
    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        // TODO Auto-generated method stub
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
        logger.info("!!!!!!!!!!!referred！{}",invoker);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.alibaba.dubbo.rpc.InvokerListener#destroyed(com.alibaba.dubbo.rpc.Invoker) 
     */
    @Override
    public void destroyed(Invoker<?> invoker) {
        // TODO Auto-generated method stub
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        logger.info("!!!!!!!!!!!destroyed！{}",invoker);
        
    }

}
  