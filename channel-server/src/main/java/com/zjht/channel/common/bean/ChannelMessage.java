/** 
 * Project Name:channel-server 
 * File Name:ChannelMessage.java 
 * Package Name:com.zjht.channel.common.bean 
 * Date:2015年9月6日下午2:47:05 
 * 
 */
  
  
package com.zjht.channel.common.bean;  

import java.util.Map;
 
/** 
 * ClassName: ChannelMessage <br/> 
 * Function: 渠道消息封装对象. <br/> 
 * date: 2015年9月6日 下午2:47:05 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ChannelMessage {
    private String service;
    private String version;
    private String type;
    private Map<String,String> content;
    private String state;
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
    public Map<String, String> getContent() {
        return content;
    }
    public void setContent(Map<String, String> content) {
        this.content = content;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
