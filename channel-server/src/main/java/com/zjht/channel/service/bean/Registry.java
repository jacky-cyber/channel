/** 
 * Project Name:channel-server 
 * File Name:Registry.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 20152:19:50 PM 
 * 
 */

package com.zjht.channel.service.bean;

/** 
 * ClassName: Registry <br/> 
 * Function: Dubbo Registry配置类. <br/> 
 * date: Sep 11, 2015 2:19:50 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Registry {
	private String id;//注册中心的bean id;
	
	/**
	 * 注册中心服务器地址，如果地址没有端口缺省为9090，
	 * 同一集群内的多个地址用逗号分隔，如：ip:port,ip:port
	 */
	private String address;
	
	private String protocol;//注册中心协议
	
	private String username;//登陆注册中心用户名
	private String password;//登陆注册中心密码
	
	/**
	 * 注册中心请求超时时间(毫秒) 
	 * 默认5000
	 */
	private int timeout;
	
	/**
	 * 注册中心会话超时时间(毫秒)，用于检测提供者非正常断线后的脏数据，比如用心跳检测的实现，
	 * 此时间就是心跳间隔，不同注册中心实现不一样。 
	 * 默认60000
	 */
	private int session;
	
	/**
	 * 使用文件缓存注册中心地址列表及服务提供者列表，<br/>
	 * 应用重启时将基于此文件恢复，<br/>
	 * <b>注意：两个注册中心不能使用同一文件存储</b>
	 * 
	 */
	private String file;
	
	/**
	 * 停止时等待通知完成时间(毫秒) 
	 * 默认0
	 */
	private int wait;
	
	/**
	 * 注册中心不存在时，是否报错 
	 * 默认true
	 */
	private boolean check;
	
	/**
	 * 是否向此注册中心注册服务，如果设为false，将只订阅，不注册
	 * 默认true
	 */
	private boolean register; 
	
	/**
	 * 是否向此注册中心订阅服务，如果设为false，将只注册，不订阅
	 * 默认true
	 */
	private boolean subscribe; 
	
	/**
	 * 服务是否动态注册，如果设为false，注册后将显示后disable状态，需人工启用，并且服务提供者停止时，也不会自动取消册，需人工禁用。
	 * 默认ture
	 */
	private boolean dynamic;

	/** 
	 * id. 
	 * 
	 * @return  the id 
	 * @since   JDK 1.8
	 */
	public String getId() {
		return id;
	}

	/** 
	 * id. 
	 * 
	 * @param   id    the id to set 
	 * @since   JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * address. 
	 * 
	 * @return  the address 
	 * @since   JDK 1.8
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * address. 
	 * 
	 * @param   address    the address to set 
	 * @since   JDK 1.8
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** 
	 * protocol. 
	 * 
	 * @return  the protocol 
	 * @since   JDK 1.8
	 */
	public String getProtocol() {
		return protocol;
	}

	/** 
	 * protocol. 
	 * 
	 * @param   protocol    the protocol to set 
	 * @since   JDK 1.8
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/** 
	 * username. 
	 * 
	 * @return  the username 
	 * @since   JDK 1.8
	 */
	public String getUsername() {
		return username;
	}

	/** 
	 * username. 
	 * 
	 * @param   username    the username to set 
	 * @since   JDK 1.8
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 
	 * password. 
	 * 
	 * @return  the password 
	 * @since   JDK 1.8
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * password. 
	 * 
	 * @param   password    the password to set 
	 * @since   JDK 1.8
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * timeout. 
	 * 
	 * @return  the timeout 
	 * @since   JDK 1.8
	 */
	public int getTimeout() {
		return timeout;
	}

	/** 
	 * timeout. 
	 * 
	 * @param   timeout    the timeout to set 
	 * @since   JDK 1.8
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/** 
	 * session. 
	 * 
	 * @return  the session 
	 * @since   JDK 1.8
	 */
	public int getSession() {
		return session;
	}

	/** 
	 * session. 
	 * 
	 * @param   session    the session to set 
	 * @since   JDK 1.8
	 */
	public void setSession(int session) {
		this.session = session;
	}

	/** 
	 * file. 
	 * 
	 * @return  the file 
	 * @since   JDK 1.8
	 */
	public String getFile() {
		return file;
	}

	/** 
	 * file. 
	 * 
	 * @param   file    the file to set 
	 * @since   JDK 1.8
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/** 
	 * wait. 
	 * 
	 * @return  the wait 
	 * @since   JDK 1.8
	 */
	public int getWait() {
		return wait;
	}

	/** 
	 * wait. 
	 * 
	 * @param   wait    the wait to set 
	 * @since   JDK 1.8
	 */
	public void setWait(int wait) {
		this.wait = wait;
	}

	/** 
	 * check. 
	 * 
	 * @return  the check 
	 * @since   JDK 1.8
	 */
	public boolean isCheck() {
		return check;
	}

	/** 
	 * check. 
	 * 
	 * @param   check    the check to set 
	 * @since   JDK 1.8
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/** 
	 * register. 
	 * 
	 * @return  the register 
	 * @since   JDK 1.8
	 */
	public boolean isRegister() {
		return register;
	}

	/** 
	 * register. 
	 * 
	 * @param   register    the register to set 
	 * @since   JDK 1.8
	 */
	public void setRegister(boolean register) {
		this.register = register;
	}

	/** 
	 * subscribe. 
	 * 
	 * @return  the subscribe 
	 * @since   JDK 1.8
	 */
	public boolean isSubscribe() {
		return subscribe;
	}

	/** 
	 * subscribe. 
	 * 
	 * @param   subscribe    the subscribe to set 
	 * @since   JDK 1.8
	 */
	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	/** 
	 * dynamic. 
	 * 
	 * @return  the dynamic 
	 * @since   JDK 1.8
	 */
	public boolean isDynamic() {
		return dynamic;
	}

	/** 
	 * dynamic. 
	 * 
	 * @param   dynamic    the dynamic to set 
	 * @since   JDK 1.8
	 */
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Registry [id=" + id + ", address=" + address + ", protocol=" + protocol + ", username=" + username
				+ ", password=" + password + ", timeout=" + timeout + ", session=" + session + ", file=" + file
				+ ", wait=" + wait + ", check=" + check + ", register=" + register + ", subscribe=" + subscribe
				+ ", dynamic=" + dynamic + "]";
	}
}
