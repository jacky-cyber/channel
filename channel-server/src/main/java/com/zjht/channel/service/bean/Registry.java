/** 
 * Project Name:channel-server 
 * File Name:Registry.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 20152:19:50 PM 
 * 
 */

package com.zjht.channel.service.bean;

import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKNode;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPath;

/** 
 * Function: Dubbo Registry配置类. <br/> 
 * date: Sep 11, 2015 2:19:50 PM <br/> 
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 */
@ZKPath("/service/dubbo/registry")
public class Registry {
    
    @ZKNode(name="注册中心的bean id")
	private String id;
	
    @ZKNode(required=true,name="组名(zookeeper上的根节点)")
    private String group;
    
	/**
	 * 注册中心服务器地址，如果地址没有端口缺省为9090，
	 * 同一集群内的多个地址用逗号分隔，如：ip:port,ip:port
	 */
	@ZKNode(required=true,name="注册中心服务器地址")
	private String address;
	
	@ZKNode(required=true,name="注册中心协议")
	private String protocol;
	
	@ZKNode(name="注册中心用户名")
	private String username;
	
	@ZKNode(name="注册中心密码")
	private String password;
	
	/**
	 * 默认5000
	 */
	@ZKNode(name="注册中心请求超时时间(毫秒)",value="5000")
	private int timeout;
	
	/**
	 * 注册中心会话超时时间(毫秒)，用于检测提供者非正常断线后的脏数据，比如用心跳检测的实现，
	 * 此时间就是心跳间隔，不同注册中心实现不一样。 
	 * 默认60000
	 */
	@ZKNode(name="注册中心会话超时时间(毫秒)",value="60000")
	private int session;
	
	/**
	 * 使用文件缓存注册中心地址列表及服务提供者列表，<br/>
	 * 应用重启时将基于此文件恢复，<br/>
	 * <b>注意：两个注册中心不能使用同一文件存储</b>
	 * 
	 */
	@ZKNode(name="缓存注册中心地址列表及服务提供者列表文件路径")
	private String file;
	
	/**
	 * 停止时等待通知完成时间(毫秒) 
	 * 默认0
	 */
	@ZKNode(name="停止时等待通知完成时间(毫秒) ",value="0")
	private int wait;
	
	/**
	 * 注册中心不存在时，是否报错 
	 * 默认true
	 */
	@ZKNode(name="是否注册中心不存在报错",value="false",range={"true","false"})
	private boolean check;
	
	/**
	 * 是否向此注册中心订阅服务，如果设为false，将只注册，不订阅
	 * 默认true
	 */
	@ZKNode(name="是否向此注册中心订阅服务",value="true",range={"true"})
	private boolean subscribe;

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress() {
        return address;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getSession() {
        return session;
    }

    public String getFile() {
        return file;
    }

    public int getWait() {
        return wait;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    /** 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "Registry [id=" + id + ", group=" + group + ", address=" + address + ", protocol="
                + protocol + ", username=" + username + ", password=" + password + ", timeout="
                + timeout + ", session=" + session + ", file=" + file + ", wait=" + wait
                + ", check=" + check + ", subscribe=" + subscribe + "]";
    } 
    
    
}
