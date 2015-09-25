/** 
 * Project Name:channel-server 
 * File Name:ZKClient.java 
 * Package Name:com.zjht.channel.configuration.impl.zookeeper 
 * Date:2015年9月6日下午4:37:39 
 * 
 */

package com.zjht.channel.configuration.impl.zookeeper;

import java.util.List;

/**
 * ClassName: ZKClient <br/>
 * Function: zookeeper接口客户端接口定义. <br/>
 * date: 2015年9月6日 下午4:37:39 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public interface ZKClient {

    /**
     * 创建zookeeper节点，并设置值. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @param data
     * @since JDK 1.8
     */
    void create(String path, String data) ;

    /**
     * 
     * 删除zookeeper节点. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @since JDK 1.8
     */
    void delete(String path);

    /**
     * 
     * 修改zookeeper节点的值. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @param bytes
     * @since JDK 1.8
     */
    void update(String path, String data);

    /**
     * 获取zookeeper指定目录下的子节点. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param string
     * @return
     * @since JDK 1.8
     */
    List<String> getChildren(String path) ;
    
    /**
     * 
     * 获取指定节点下的数据. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @return 
     * @since JDK 1.8
     */
    String getData(String path);
    
    /**
     * 判断路径是否存在
     * 
     * @author jun
     * @param path
     * @return 
     * @since JDK 1.8
     */
    boolean exists(String path);

    
}
