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
	public Configuration setWhitelists(List<String> whitelists) {
		this.whitelists = whitelists;
		return this;
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
	public Configuration setSecurity(Map<String, String> security) {
		this.security = security;
		return this;
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
	public Configuration setPermission(Map<String, List<Reference>> permission) {
		this.permission = permission;
		return this;
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
	public Configuration setDubboConfig(DubboConfig dubboConfig) {
		this.dubboConfig = dubboConfig;
		return this;
	}

    /** 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "Configuration [whitelists=" + whitelists + ", security=" + security
                + ", permission=" + permission + ", dubboConfig=" + dubboConfig + "]";
    }
	
	
}
