package com.zjht.channel.common.bean;

/**
 * 
 * ClassName: Response <br/> 
 * Function: 响应对象 <br/> 
 * date: 2015年8月28日 下午2:13:38 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class Response<T> {
    private String identifyId;//请求的唯一标识
	private String service;//服务名称
	private String version;//服务版本
	private String type;//类别
	private T content;//内容
	private String state;//状态
	public String getIdentifyId() {
        return identifyId;
    }
    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }
    public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    @Override
    public String toString() {
        return "Response [identifyId=" + identifyId + ", service=" + service
                + ", version=" + version + ", type=" + type + ", content="
                + content + ", state=" + state + "]";
    }
}
