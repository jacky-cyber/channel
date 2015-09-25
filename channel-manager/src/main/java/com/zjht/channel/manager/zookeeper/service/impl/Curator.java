/**
 * Project Name:channel-server File Name:Curator.java Package
 * Name:com.zjht.channel.configuration.impl.zookeeper.curator Date:2015年9月6日下午4:37:53
 * 
 */


package com.zjht.channel.manager.zookeeper.service.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.common.constant.Symbol;
import com.zjht.channel.manager.exception.ZookeeperException;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;
import com.zjht.channel.manager.zookeeper.service.ZKClient;

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

    @Value("${zk.channel.namespace}")
    private String zkNamespace;

    @Value("${zk.charset}")
    private String zkCharset;// zookeeper存储数据的字符编码

    @Value("${zk.sessionTimeoutMs}")
    private int sessionTimeoutMs;

    @Value("${zk.connectionTimeoutMs}")
    private int connectionTimeoutMs;

    public CuratorFramework zkClient;

    @PostConstruct
    public void init() {
        if (isConnected()) {
            return;
        }
        logger.info("开始连接zookeeper集群...");
        logger.info("集群地址[{}],session超时时间[{}],连接超时时间[{}]", zkAddress,sessionTimeoutMs, connectionTimeoutMs);
        zkClient = CuratorFrameworkFactory
                .builder()
                .connectString(zkAddress)
                .namespace(zkNamespace)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                .sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs)
                .build();
        zkClient.start();

        try {
            //异步监听节点变化
            zkClient.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    logger.debug("node is changed!{}", event);
                }
            }).inBackground().forPath("/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // flunt事物操作，最后一起提交
        // client.inTransaction().create().forPath("").and().commit();
        logger.info("成功连接到zookepper集群");
    }

    /**
     * @Title: isConnection @Description: 是否连接到zookeeper集群 @return boolean @throws
     */
    private boolean isConnected() {
        return zkClient != null && CuratorFrameworkState.STARTED.equals(zkClient.getState());
    }

    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#create(java.lang.String, byte[])
     */
    public void create(String path, String data) {
        Preconditions.checkArgument(!StringHelper.isEmpty(path), "path不能为空");
        if (exists(path)) {
            ExceptionHelper.newZookeeperException(ErrorMessage._00402, zkNamespace, path).throwout();
        }
        
        try {
            zkClient.create()
            .creatingParentsIfNeeded()//当父节点不存在时，自动创建
            .withMode(CreateMode.PERSISTENT)//存储类型（临时的还是持久的）
//            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)//访问权限
            .forPath(path,data.getBytes(zkCharset));
        }catch (Exception e) {
            throw new RuntimeException("创建zookeeper节点[{"+path+"}]失败，原因："+e.getMessage());
        }
    }

    /**
     * 创建zookeeper节点且不设置值. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param path
     * @since JDK 1.8
     */
    public void create(String path) {
        create(path, null);
    }

    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#delete(java.lang.String)
     */
    @Override
    public void delete(String path) {
        if (!exists(path)) {
            ExceptionHelper.newZookeeperException(ErrorMessage._00401, zkNamespace, path).throwout();
        }
        try{
            zkClient.delete().deletingChildrenIfNeeded().forPath(path);
        }catch(Exception e){
            throw new RuntimeException("删除zookeeper节点[" + path + "]失败，原因："+e.getMessage());
        }

    }

    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#update(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void update(String path, String data) {
        if (!exists(path)) {
            ExceptionHelper.newZookeeperException(ErrorMessage._00401, zkNamespace, path).throwout();
        }
        try{
            zkClient.setData().forPath(path,StringHelper.bytes(data, zkCharset));
        }catch(Exception e){
            throw new RuntimeException("修改zookeeper节点[" + path + "]的数据为["+data+"]失败，原因："+e.getMessage());
        }
    }

    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#getChildren(java.lang.String)
     */
    @Override
    public List<String> getChildren(String path) {
        List<String> childrenNodes = null;
        try {
            logger.debug("获取zookeeper节点[{}]下的子节点", path);
            if (!exists(path)) {
                ExceptionHelper.newZookeeperException(ErrorMessage._00401, zkNamespace, path).throwout();
            }
            childrenNodes = zkClient.getChildren().forPath(path);
        } catch (ZookeeperException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("获取zookeeper节点[" + path + "]的子目录失败，原因："+e.getMessage());
        }
        return childrenNodes;
    }

    /**
     * @see com.zjht.channel.configuration.impl.zookeeper.ZKClient#getData(java.lang.String)
     */
    @Override
    public String getData(String path) {
        String data = "";
        try {
            logger.debug("获取zookeeper节点[{}]的数据", path);
            if (!exists(path)) {
                ExceptionHelper.newZookeeperException(ErrorMessage._00401, zkNamespace, path).throwout();
            }
            data = StringHelper.newString(zkClient.getData().forPath(path), zkCharset);
        } catch (ZookeeperException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("获取zookeeper节点[" + path + "]的数据失败，原因："+e.getMessage());
        }
        return data;
    }

    /** 
     * @see com.zjht.channel.manager.zookeeper.service.ZKClient#exists(java.lang.String) 
     */
    @Override
    public boolean exists(String path) {
        boolean isExists = false;
        
        try{
            if(StringHelper.isEmpty(path)){
                return false;
            }
            
            isExists = !Objects.isNull(zkClient.checkExists().forPath(path));
        }catch(Exception e){
            throw new RuntimeException("判断zookeeper节点[" + path + "]失败，原因："+e.getMessage());
        }
        return isExists;
    }
}
