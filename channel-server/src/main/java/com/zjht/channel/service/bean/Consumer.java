package com.zjht.channel.service.bean;

import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKNode;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPath;

/** 
 * Function: Dubbo Consumer配置信息. <br/> 
 * date: Sep 11, 2015 2:09:07 PM <br/> 
 * 
 * @author jun 
 * @version v0.1
 * @since JDK 1.8
 */
@ZKPath("/service/dubbo/comsumer")
public class Consumer {
	
    @ZKNode(name="负责人")
	private String owner;
    
    @ZKNode(name="组织名称(部门)")
	private String group;
	
    //默认值1000
    @ZKNode(name="远程服务调用超时时间(毫秒)")
	private int timeout;
	
	//远程服务调用重试次数，不包括第一次调用，不需要重试请设为0 ,默认值2
    @ZKNode(name="远程服务调用重试次数")
	private int retries;
	
	/**
	 * 负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用 
	 * 默认值random
	 */
    @ZKNode(name="负载均衡策略",range={"random","roundrobin","leastactive"})
	private String loadbalance;
	
	/**
	 * 是否缺省异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程 
	 * 默认值false
	 */
    @ZKNode(name="是否缺省异步执行")
	private boolean async;
	
	/**
	 * 是否缺省泛化接口，如果为泛化接口，将返回GenericService 
	 * 默认false
	 */
    @ZKNode(name="是否缺省泛化接口")
	private String generic;
	
	/**
	 * 启动时检查提供者是否存在，true报错，false忽略 
	 * 默认true
	 */
    @ZKNode(name="启动时检查提供者是否存在")
	private boolean check;
	
	/**
	 * 生成动态代理方式，可选：jdk/javassist 
	 * 默认javassist
	 */
    @ZKNode(name="动态代理方式",range={"javassist","jdk"})
	private String proxy;
	
	/**
	 * 每服务消费者每服务每方法最大并发调用数 
	 * 默认0
	 */
    @ZKNode(name="每服务消费者每服务每方法最大并发调用数")
	private int actives;
	
	/**
	 * 集群方式，可选：failover/failfast/failsafe/failback/forking 
	 * 默认failover
	 */
    @ZKNode(name="集群方式",range={"failover","failfast","failsafe","failback","forking "})
	private String cluseter;

    public String getOwner() {
        return owner;
    }

    public String getGroup() {
        return group;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getRetries() {
        return retries;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public boolean isAsync() {
        return async;
    }

    public String getGeneric() {
        return generic;
    }

    public boolean isCheck() {
        return check;
    }

    public String getProxy() {
        return proxy;
    }

    public int getActives() {
        return actives;
    }

    public String getCluseter() {
        return cluseter;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public void setActives(int actives) {
        this.actives = actives;
    }

    public void setCluseter(String cluseter) {
        this.cluseter = cluseter;
    }

    /** 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "Consumer [owner=" + owner + ", group=" + group + ", timeout=" + timeout
                + ", retries=" + retries + ", loadbalance=" + loadbalance + ", async=" + async
                + ", generic=" + generic + ", check=" + check + ", proxy=" + proxy + ", actives="
                + actives + ", cluseter=" + cluseter + "]";
    }
}
