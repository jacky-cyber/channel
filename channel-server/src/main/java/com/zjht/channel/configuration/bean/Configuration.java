package com.zjht.channel.configuration.bean;

import java.util.List;
import java.util.Map;

import com.zjht.channel.service.bean.DubboConfig;
import com.zjht.channel.service.bean.Reference;

public class Configuration {
	
	private List<String> whitelists ;// 白名单
	private Map<String, String> security ;// 用户名/密钥
	private Map<String, List<Reference>> permission ;// 权限
	private DubboConfig dubboConfig;//dubbo配置信息
	
	public Configuration(){
		super();
	}

	/** 
	 * whitelists. 
	 * 
	 * @return  the whitelists 
	 * @since   JDK 1.8
	 */
	public List<String> getWhitelists() {
		return whitelists;
	}

	/** 
	 * whitelists. 
	 * 
	 * @param   whitelists    the whitelists to set 
	 * @since   JDK 1.8
	 */
	public void setWhitelists(List<String> whitelists) {
		this.whitelists = whitelists;
	}

	/** 
	 * security. 
	 * 
	 * @return  the security 
	 * @since   JDK 1.8
	 */
	public Map<String, String> getSecurity() {
		return security;
	}

	/** 
	 * security. 
	 * 
	 * @param   security    the security to set 
	 * @since   JDK 1.8
	 */
	public void setSecurity(Map<String, String> security) {
		this.security = security;
	}

	/** 
	 * permission. 
	 * 
	 * @return  the permission 
	 * @since   JDK 1.8
	 */
	public Map<String, List<Reference>> getPermission() {
		return permission;
	}

	/** 
	 * permission. 
	 * 
	 * @param   permission    the permission to set 
	 * @since   JDK 1.8
	 */
	public void setPermission(Map<String, List<Reference>> permission) {
		this.permission = permission;
	}

	/** 
	 * dubboConfig. 
	 * 
	 * @return  the dubboConfig 
	 * @since   JDK 1.8
	 */
	public DubboConfig getDubboConfig() {
		return dubboConfig;
	}

	/** 
	 * dubboConfig. 
	 * 
	 * @param   dubboConfig    the dubboConfig to set 
	 * @since   JDK 1.8
	 */
	public void setDubboConfig(DubboConfig dubboConfig) {
		this.dubboConfig = dubboConfig;
	}
}
