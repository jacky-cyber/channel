package com.zjht.channel.manager.common.constant;

/** 
 * TODO
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 9:08:20 AM 
 */
public enum NodeStatus {
    UNCHANAGED("00"),
    ADDED("01"),
    UPDATED("02"),
    DELETED("03");
    
    private String code;
    
    private  NodeStatus(String code){
        this.code = code; 
    }
    
    public String code(){
        return code;
    }
    
}
