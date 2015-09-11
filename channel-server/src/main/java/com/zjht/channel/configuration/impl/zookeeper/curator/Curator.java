/** 
 * Project Name:channel-server 
 * File Name:Curator.java 
 * Package Name:com.zjht.channel.configuration.impl.zookeeper.curator 
 * Date:2015年9月6日下午4:37:53 
 * 
 */
  
  
package com.zjht.channel.configuration.impl.zookeeper.curator;  

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.configuration.impl.zookeeper.ZKClient;
 
/** 
 * ClassName: Curator <br/> 
 * Function: zookeeper客户端curator的实现封装. <br/> 
 * date: 2015年9月6日 下午4:37:53 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
@Service("ZKClient")
public class Curator implements ZKClient {
    
    private final static Logger logger = LoggerFactory.getLogger(Curator.class);
    
    @Value("${zk.address}")
    private String zkAddress;// zookeeper集群地址

    @Value("${zk.baseSleepTimeMs}")
    private int zkBaseSleepTimeMs;// 连接zookeeper失败时重试间隔(单位ms)

    @Value("${zk.maxRetries}")
    private int zkMaxRetries;// 连接zookeeper失败时重试次数
    
    @Value("${zk.charset}")
    private String zkCharset;// zookeeper存储数据的字符编码
    
    public CuratorFramework client;
    
    private Curator(){
        // zookeeper集群地址
        String connectString = "172.16.104.147:2181,172.16.111.106:2181,172.16.111.107:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();
    }
    
    @PostConstruct
    public void init(){
        if (isConnected()) {
            return ;
        }
        
        logger.info("开始连接zookeeper集群,集群地址[{}],连接失败重试间隔[{}],连接失败重试次数[{}]",zkAddress, zkBaseSleepTimeMs, zkMaxRetries);
        RetryPolicy retryPolicy = null;
        retryPolicy = new ExponentialBackoffRetry(zkBaseSleepTimeMs,zkMaxRetries);
        client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
        logger.info("成功连接到zookepper集群");
    }
    
    /**
     * @Title: isConnection
     * @Description: 是否连接到zookeeper集群
     * @return boolean
     * @throws
     */
    private boolean isConnected() {
        return client != null
                && CuratorFrameworkState.STARTED.equals(client.getState());
    }
    
    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#create(java.lang.String, byte[])
     */
    public void create(String path,byte[] bytes){
        Preconditions.checkArgument(!StringHelper.isEmpty(path), "path不能为空");
        String[] paths = null;
        String tempPath = "";
        try{
            if(!Objects.isNull(client.checkExists().forPath(path))){
                throw new Exception("zookeeper节点["+path+"]已存在！");
            }
            
            //将路径分解，逐个判断并创建
            paths = StringHelper.split(path, Symbol.SLASH.code());
            for (int i = 1; i < paths.length; i++) {
                tempPath = tempPath.concat(Symbol.SLASH.code().concat(paths[i]));
                if(Objects.isNull(client.checkExists().forPath(tempPath))){
                    logger.debug("创建zookeeper节点[{}]",tempPath);
                    client.create().forPath(tempPath);
                }
            }
            
            //设置最后一个节点的值
            if(!Objects.isNull(bytes)){
                client.setData().forPath(path, bytes);
            }
        }catch(Exception e){
            logger.error("创建zookeeper节点[{}]失败",path,e);
        }
    }
    
    /**
     * 创建zookeeper节点且不设置值. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path 
     * @since JDK 1.8
     */
    public void create(String path){
        create(path,null);
    }

    /** 
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#delete(java.lang.String) 
     */
    @Override
    public void delete(String path) {
        // TODO Auto-generated method stub
        
    }

    /** 
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#update(java.lang.String, byte[]) 
     */
    @Override
    public void update(String path, byte[] bytes) {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String[] args) throws Exception {
        Curator curator = new Curator();
        curator.init2();
        
    }

    /** 
     *  初始化. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @throws UnsupportedEncodingException 
     * @since JDK 1.8
     */  
    private void init2() throws UnsupportedEncodingException {
        create("/channel/inspection/whitelists/127.0.0.1");
        create("/channel/inspection/whitelists/172.16.104.147");
        create("/channel/inspection/whitelists/192.168.23.3");

        create("/channel/inspection/security/ZJ208160/key","eUaPRnpD0+qzPulr9LrNqNX99+M+iSDq".getBytes("UTF-8"));
        create("/channel/inspection/security/ZJ150706/key","lA5ej/5Ykhz32p2UoSo9x8HHhgTEV6ek".getBytes("UTF-8"));
                
        create("/channel/inspection/permission/ZJ208160/test1/1.0");
        create("/channel/inspection/permission/ZJ208160/test1/2.0");
        create("/channel/inspection/permission/ZJ208160/test2/1.0");
        create("/channel/inspection/permission/ZJ150706/test1/1.0");
        create("/channel/inspection/permission/ZJ150706/test2/1.0");
                
        create("/channel/service/test1/1.0/packageName","com.zjht.channel.example.service".getBytes("UTF-8"));
        create("/channel/service/test1/1.0/className","ExampleService1".getBytes("UTF-8"));
        create("/channel/service/test1/1.0/status","enabled".getBytes("UTF-8"));//disabled
        
        create("/channel/service/test2/1.0/packageName","com.zjht.channel.example.service".getBytes("UTF-8"));
        create("/channel/service/test2/1.0/className","ExampleService2".getBytes("UTF-8"));
        create("/channel/service/test2/1.0/status","disabled".getBytes("UTF-8"));
    }

    /** 
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#getChildren(java.lang.String) 
     */
    @Override
    public List<String> getChildren(String path)  {
        List<String> childrenNodes = null;
        try {
            logger.debug("获取路径[{}]下的子节点",path);
            childrenNodes = client.getChildren().forPath(path);
        } catch (Exception e) {
            logger.error("获取路径[{}]子节点失败",path,e);
            throw new RuntimeException("获取路径["+path+"]子节点失败");
        }
        return childrenNodes;
    }

    /** 
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#getData(java.lang.String) 
     */
    @Override
    public byte[] getData(String path) {
        byte[] bytes = null;
        try{
            logger.debug("获取路径[{}]的数据",path);
            bytes = client.getData().forPath(path);
        }catch(Exception e){
            logger.error("获取路径[{}]的数据失败",path,e);
            throw new RuntimeException("获取路径["+path+"]的数据失败");
        }
        return bytes;
    }
    
}
  