package com.zjht.channel.common.bean;


/**
 * 
 * ClassName: Request <br/>
 * Function: 请求信息封装对象. <br/>
 * date: 2015年8月28日 下午2:36:23 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class Request {
    private String identifyId;//请求唯一标识
    private String ip;// 请求ip地址
    private String appno;// 终端号
    private String svrName;// 服务名称
    private String svrVersion;// 服务版本
    private String msg;//消息
    public String getIdentifyId() {
        return identifyId;
    }
    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getAppno() {
        return appno;
    }
    public void setAppno(String appno) {
        this.appno = appno;
    }
    public String getSvrName() {
        return svrName;
    }
    public void setSvrName(String svrName) {
        this.svrName = svrName;
    }
    public String getSvrVersion() {
        return svrVersion;
    }
    public void setSvrVersion(String svrVersion) {
        this.svrVersion = svrVersion;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Request [identifyId=" + identifyId + ", ip=" + ip + ", appno="
                + appno + ", svrName=" + svrName + ", svrVersion=" + svrVersion
                + ", msg=" + msg + "]";
    }
}
