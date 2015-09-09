package com.zjht.channel.configuration.bean;

import java.util.List;
import java.util.Map;

import com.zjht.channel.service.bean.ServiceInfo;

public class Configuration {
	
	private List<String> whitelists ;// 白名单
	private Map<String, String> security ;// 用户名/密钥
	private Map<String, List<ServiceInfo>> permission ;// 权限
	private List<ServiceInfo> services ;// 服务
	
	public Configuration(){
		super();
	}

    public List<String> getWhitelists() {
        return whitelists;
    }

    public void setWhitelists(List<String> whitelists) {
        this.whitelists = whitelists;
    }

    public Map<String, String> getSecurity() {
        return security;
    }

    public void setSecurity(Map<String, String> security) {
        this.security = security;
    }

    public Map<String, List<ServiceInfo>> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, List<ServiceInfo>> permission) {
        this.permission = permission;
    }

    public List<ServiceInfo> getServices() {
        return services;
    }

    public void setServices(List<ServiceInfo> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Configuration [whitelists=" + whitelists + ", security="
                + security + ", permission=" + permission + ", services="
                + services + "]";
    }
}
