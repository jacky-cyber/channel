package com.zjht.channel.configuration;

import com.zjht.channel.configuration.bean.Configuration;

/**
 * 
 * @ClassName: ConfigurationBuilder
 * @Description: 配置对象工厂接口
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月25日
 */
public interface ConfigurationBuilder {

    /**
     * 创建配置对象.<br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com 
     * @since JDK 1.8
     */
    void build();
    
    
    /**
     * 
     * 获取配置对象. <br/> 
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return 
     * @since JDK 1.8
     */
    Configuration configuration();
    
	
	
}