package com.zjht.channel.service.bean;

import java.util.List;

public class Reference{
	
	private String id;//服务引用BeanId 
	private String fullyQualifiedName;//服务接口类的全限定名称
	private String name;//服务名称
	private String version;//服务版本；
	private String owner;//负责人
	private String group;//组织名称
	
	/** 
	 * 服务方法调用超时时间(毫秒) ,缺省使用consumer的timeout
	 */
	private int timeout;
	
	/** 
	 * 远程服务调用重试次数，不包括第一次调用，不需要重试请设为0 ，缺省使用<dubbo:consumer>的retries
	 */
	private int retries; 
	
	/**
	 * 对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数 ,缺省使用<dubbo:consumer>的connections
	 */
	private int connections; 
	
	/**
	 *  负载均衡算法（有多个Provider时，如何挑选Provider调用），缺省是随机（random）。 
	 *  还可以有轮训(roundrobin)、最不活跃优先（leastactive，指从Consumer端并发调用最好的Provider， 
	 *  可以减少的反应慢的Provider的调用，因为反应更容易累积并发的调用）
	 */
	private String loadbalance;
	
	/**
	 * 是否异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程 
	 *  缺省使用<dubbo:consumer>的async 
	 */
	private boolean async;
	
	/**
	 * 是否缺省泛化接口，如果为泛化接口，将返回GenericServivce
	 * 缺省使用<dubbo:consumer>的generic 
	 */
	private boolean generic;
	
	/**
	 * 是启动时检查提供者是否存在，true报错，false忽略 
	 * 缺省使用<dubbo:consumer>的check 
	 */
	private boolean check;
	

	/**
	 * 选择动态代理实现策略，可选：javassist, jdk 
	 * 默认javassist
	 */
	private String proxy;
	
	
	/**
	 * 客户端传输类型设置，如Dubbo协议的netty或mina。 
	 */
	private String client;
	
	/**
	 *  消费者端，最大并发调用限制，即当Consumer对一个服务的并发调用到上限后，新调用会Wait直到超时。 
	 *  在方法上配置（dubbo:method ）则并发限制针对方法，在接口上配置（dubbo:service），则并发限制针对服务。   
	 */
	private int actives;
	
	/**
	 *  集群方式，可选：failover/failfast/failsafe/failback/forking 
	 */
	private String cluster;
	
	/**
	 * 是否在afterPropertiesSet()时饥饿初始化引用，否则等到有人注入或引用该实例时再初始化。 
	 * 默认false
	 */
	private boolean init;
	
	
	/**
	 * 服务消费方远程调用过程拦截器名称，多个名称用逗号分隔 
	 */
	private String filter;
	
	/**
	 * 服务消费方引用服务监听器名称，多个名称用逗号分隔 
	 */
	private String listener;
	
	/**
	 * 治理 	服务调用者所在的分层。如：biz、dao、intl:web、china:acton。 
	 */
	private String layer;
	
	private List<Method> methods;
	
	public Reference(){
	    super();
	}
	
	public Reference(String name,String version){
	    this.name    = name;
	    this.version = version;
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
	 * fullyQualifiedName. 
	 * 
	 * @return  the fullyQualifiedName 
	 * @since   JDK 1.8
	 */
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	/** 
	 * fullyQualifiedName. 
	 * 
	 * @param   fullyQualifiedName    the fullyQualifiedName to set 
	 * @since   JDK 1.8
	 */
	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}

	/** 
	 * name. 
	 * 
	 * @return  the name 
	 * @since   JDK 1.8
	 */
	public String getName() {
		return name;
	}

	/** 
	 * name. 
	 * 
	 * @param   name    the name to set 
	 * @since   JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * version. 
	 * 
	 * @return  the version 
	 * @since   JDK 1.8
	 */
	public String getVersion() {
		return version;
	}

	/** 
	 * version. 
	 * 
	 * @param   version    the version to set 
	 * @since   JDK 1.8
	 */
	public void setVersion(String version) {
		this.version = version;
	}

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
	 * group. 
	 * 
	 * @return  the group 
	 * @since   JDK 1.8
	 */
	public String getGroup() {
		return group;
	}

	/** 
	 * group. 
	 * 
	 * @param   group    the group to set 
	 * @since   JDK 1.8
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * connections. 
	 * 
	 * @return  the connections 
	 * @since   JDK 1.8
	 */
	public int getConnections() {
		return connections;
	}

	/** 
	 * connections. 
	 * 
	 * @param   connections    the connections to set 
	 * @since   JDK 1.8
	 */
	public void setConnections(int connections) {
		this.connections = connections;
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
	 * @return  the async 治理 	服务调用者所在的分层。如：biz、dao、intl:web、china:acton。 
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
	public boolean isGeneric() {
		return generic;
	}

	/** 
	 * generic. 
	 * 
	 * @param   generic    the generic to set 
	 * @since   JDK 1.8
	 */
	public void setGeneric(boolean generic) {
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
	 * client. 
	 * 
	 * @return  the client 
	 * @since   JDK 1.8
	 */
	public String getClient() {
		return client;
	}

	/** 
	 * client. 
	 * 
	 * @param   client    the client to set 
	 * @since   JDK 1.8
	 */
	public void setClient(String client) {
		this.client = client;
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
	 * cluster. 
	 * 
	 * @return  the cluster 
	 * @since   JDK 1.8
	 */
	public String getCluster() {
		return cluster;
	}

	/** 
	 * cluster. 
	 * 
	 * @param   cluster    the cluster to set 
	 * @since   JDK 1.8
	 */
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	/** 
	 * init. 
	 * 
	 * @return  the init 
	 * @since   JDK 1.8
	 */
	public boolean isInit() {
		return init;
	}

	/** 
	 * init. 
	 * 
	 * @param   init    the init to set 
	 * @since   JDK 1.8
	 */
	public void setInit(boolean init) {
		this.init = init;
	}

	/** 
	 * methods. 
	 * 
	 * @return  the methods 
	 * @since   JDK 1.8
	 */
	public List<Method> getMethods() {
		return methods;
	}

	/** 
	 * methods. 
	 * 
	 * @param   methods    the methods to set 
	 * @since   JDK 1.8
	 */
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	/** 
	 * @see java.lang.Object#hashCode() 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/** 
	 * @see java.lang.Object#equals(java.lang.Object) 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reference other = (Reference) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Reference [id=" + id + ", fullyQualifiedName=" + fullyQualifiedName + ", name=" + name + ", version="
				+ version + ", owner=" + owner + ", group=" + group + ", timeout=" + timeout + ", retries=" + retries
				+ ", connections=" + connections + ", loadbalance=" + loadbalance + ", async=" + async + ", generic="
				+ generic + ", check=" + check + ", proxy=" + proxy + ", client=" + client + ", actives=" + actives
				+ ", cluster=" + cluster + ", init=" + init + ", filter=" + filter + ", listener=" + listener
				+ ", layer=" + layer + ", methods=" + methods + "]";
	}

}
