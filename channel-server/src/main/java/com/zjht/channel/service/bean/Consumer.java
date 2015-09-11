/** 
 * Project Name:channel-server 
 * File Name:Consumer.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 20152:09:07 PM 
 * 
 */

package com.zjht.channel.service.bean;

/** 
 * ClassName: Consumer <br/> 
 * Function: Dubbo Consumer配置信息. <br/> 
 * date: Sep 11, 2015 2:09:07 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Consumer {
	
	private String owner;//负责人
	
	/**
	 * 远程服务调用超时时间(毫秒) 
	 *  默认值1000
	 */
	private int timeout;
	
	/**
	 * 远程服务调用重试次数，不包括第一次调用，不需要重试请设为0 
	 * 默认值2
	 */
	private int retries;
	
	/**
	 * 负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用 
	 * 默认值random
	 */
	private String loadbalance;
	
	/**
	 * 是否缺省异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程 
	 * 默认值false
	 */
	private boolean async;
	
	
	/**
	 * 是否缺省泛化接口，如果为泛化接口，将返回GenericService 
	 * 默认false
	 */
	private String generic;
	
	/**
	 * 启动时检查提供者是否存在，true报错，false忽略 
	 * 默认true
	 */
	private boolean check;
	
	/**
	 * 生成动态代理方式，可选：jdk/javassist 
	 * 默认javassist
	 */
	private String proxy;
	
	/**
	 * 每服务消费者每服务每方法最大并发调用数 
	 * 默认0
	 */
	private int actives;
	
	/**
	 * 集群方式，可选：failover/failfast/failsafe/failback/forking 
	 * 默认failover
	 */
	private String cluseter;
	
	/**
	 * 服务消费方远程调用过程拦截器名称，多个名称用逗号分隔 
	 */
	private String filter;
	
	/**
	 * 服务消费方引用服务监听器名称，多个名称用逗号分隔 
	 */
	private String listener;
	
	/**
	 * 服务调用者所在的分层。如：biz、dao、intl:web、china:acton。 
	 */
	private String layer;

	/** 
	 * owner. 
	 * 
	 * @return  the owner 
	 * @since   JDK 1.8
	 */
	public String getOwner() {
		return owner;
	}

	/** 
	 * owner. 
	 * 
	 * @param   owner    the owner to set 
	 * @since   JDK 1.8
	 */
	public void setOwner(String owner) {
		this.owner = owner;
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
	 * retries. 
	 * 
	 * @return  the retries 
	 * @since   JDK 1.8
	 */
	public int getRetries() {
		return retries;
	}

	/** 
	 * retries. 
	 * 
	 * @param   retries    the retries to set 
	 * @since   JDK 1.8
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}

	/** 
	 * loadbalance. 
	 * 
	 * @return  the loadbalance 
	 * @since   JDK 1.8
	 */
	public String getLoadbalance() {
		return loadbalance;
	}

	/** 
	 * loadbalance. 
	 * 
	 * @param   loadbalance    the loadbalance to set 
	 * @since   JDK 1.8
	 */
	public void setLoadbalance(String loadbalance) {
		this.loadbalance = loadbalance;
	}

	/** 
	 * async. 
	 * 
	 * @return  the async 
	 * @since   JDK 1.8
	 */
	public boolean isAsync() {
		return async;
	}

	/** 
	 * async. 
	 * 
	 * @param   async    the async to set 
	 * @since   JDK 1.8
	 */
	public void setAsync(boolean async) {
		this.async = async;
	}


	/** 
	 * generic. 
	 * 
	 * @return  the generic 
	 * @since   JDK 1.8
	 */
	public String getGeneric() {
		return generic;
	}

	/** 
	 * generic. 
	 * 
	 * @param   generic    the generic to set 
	 * @since   JDK 1.8
	 */
	public void setGeneric(String generic) {
		this.generic = generic;
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
	 * proxy. 
	 * 
	 * @return  the proxy 
	 * @since   JDK 1.8
	 */
	public String getProxy() {
		return proxy;
	}

	/** 
	 * proxy. 
	 * 
	 * @param   proxy    the proxy to set 
	 * @since   JDK 1.8
	 */
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	/** 
	 * actives. 
	 * 
	 * @return  the actives 
	 * @since   JDK 1.8
	 */
	public int getActives() {
		return actives;
	}

	/** 
	 * actives. 
	 * 
	 * @param   actives    the actives to set 
	 * @since   JDK 1.8
	 */
	public void setActives(int actives) {
		this.actives = actives;
	}

	/** 
	 * cluseter. 
	 * 
	 * @return  the cluseter 
	 * @since   JDK 1.8
	 */
	public String getCluseter() {
		return cluseter;
	}

	/** 
	 * cluseter. 
	 * 
	 * @param   cluseter    the cluseter to set 
	 * @since   JDK 1.8
	 */
	public void setCluseter(String cluseter) {
		this.cluseter = cluseter;
	}

	/** 
	 * filter. 
	 * 
	 * @return  the filter 
	 * @since   JDK 1.8
	 */
	public String getFilter() {
		return filter;
	}

	/** 
	 * filter. 
	 * 
	 * @param   filter    the filter to set 
	 * @since   JDK 1.8
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/** 
	 * listener. 
	 * 
	 * @return  the listener 
	 * @since   JDK 1.8
	 */
	public String getListener() {
		return listener;
	}

	/** 
	 * listener. 
	 * 
	 * @param   listener    the listener to set 
	 * @since   JDK 1.8
	 */
	public void setListener(String listener) {
		this.listener = listener;
	}

	/** 
	 * layer. 
	 * 
	 * @return  the layer 
	 * @since   JDK 1.8
	 */
	public String getLayer() {
		return layer;
	}

	/** 
	 * layer. 
	 * 
	 * @param   layer    the layer to set 
	 * @since   JDK 1.8
	 */
	public void setLayer(String layer) {
		this.layer = layer;
	}

	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Consumer [owner=" + owner + ", timeout=" + timeout + ", retries=" + retries + ", loadbalance="
				+ loadbalance + ", async=" + async + ", generic=" + generic
				+ ", check=" + check + ", proxy=" + proxy + ", actives=" + actives + ", cluseter=" + cluseter
				+ ", filter=" + filter + ", listener=" + listener + ", layer=" + layer + "]";
	}
	
}
