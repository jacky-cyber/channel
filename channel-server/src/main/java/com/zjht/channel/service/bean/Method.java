/** 
 * Project Name:channel-server 
 * File Name:MethodInfo.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 201512:45:06 PM 
 * 
 */

package com.zjht.channel.service.bean;

import com.zjht.channel.common.annotation.FieldDefine;

/** 
 * ClassName: MethodInfo <br/> 
 * Function: dubbo服务的method配置信息. <br/> 
 * date: Sep 11, 2015 12:45:06 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Method {
	@FieldDefine(required=true)
	private String name;//方法名
	private int timeout;//方法调用超时时间(毫秒) 
	private int retries;//远程服务调用重试次数，不包括第一次调用，不需要重试请设为0，默认为reference的retries
	private String loadbalance;//负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用。默认loadbalance 
	private boolean async;//是否异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程，默认为reference的async
	private boolean sent;//异步调用时，标记sent=true时，表示网络已发出数据，默认true
	private int actives;//每服务消费者最大并发调用限制,默认值0 
	private int executes;//每服务每方法最大使用线程数限制，默认值0
	private boolean sticky;//设置true 该接口上的所有方法使用同一个provider.如果需要更复杂的规则，请使用用路由,默认false
	private boolean needReturn;//方法调用是否需要返回值,async设置为true时才生效，如果设置为true，则返回future，或回调onreturn等方法，如果设置为false，则请求发送成功后直接返回Null ,默认true
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
	 * sent. 
	 * 
	 * @return  the sent 
	 * @since   JDK 1.8
	 */
	public boolean isSent() {
		return sent;
	}
	/** 
	 * sent. 
	 * 
	 * @param   sent    the sent to set 
	 * @since   JDK 1.8
	 */
	public void setSent(boolean sent) {
		this.sent = sent;
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
	 * executes. 
	 * 
	 * @return  the executes 
	 * @since   JDK 1.8
	 */
	public int getExecutes() {
		return executes;
	}
	/** 
	 * executes. 
	 * 
	 * @param   executes    the executes to set 
	 * @since   JDK 1.8
	 */
	public void setExecutes(int executes) {
		this.executes = executes;
	}
	/** 
	 * sticky. 
	 * 
	 * @return  the sticky 
	 * @since   JDK 1.8
	 */
	public boolean isSticky() {
		return sticky;
	}
	/** 
	 * sticky. 
	 * 
	 * @param   sticky    the sticky to set 
	 * @since   JDK 1.8
	 */
	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}
	/** 
	 * needReturn. 
	 * 
	 * @return  the needReturn 
	 * @since   JDK 1.8
	 */
	public boolean isNeedReturn() {
		return needReturn;
	}
	/** 
	 * needReturn. 
	 * 
	 * @param   needReturn    the needReturn to set 
	 * @since   JDK 1.8
	 */
	public void setNeedReturn(boolean needReturn) {
		this.needReturn = needReturn;
	}
	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "MethodInfo [name=" + name + ", timeout=" + timeout + ", retries=" + retries + ", loadbalance="
				+ loadbalance + ", async=" + async + ", sent=" + sent + ", actives=" + actives + ", executes="
				+ executes + ", sticky=" + sticky + ", needReturn=" + needReturn + "]";
	}
}
