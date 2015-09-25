package com.zjht.channel.manager.service;

import com.zjht.channel.manager.zookeeper.bean.Configuration;

/** 
 * Function: 渠道系统配置业务处理接口. <br/> 
 * date: Sep 18, 2015 3:02:11 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface ConfigurationService {

	/**
	 * 获取渠道系统配置信息对象. <br/> 
	 * 
	 * @author jun 
	 * @return 
	 * @since JDK 1.8
	 */
	Configuration configure();

	/**
	 * 发布渠道系统配置
	 * 
	 * @author jun 
	 * @since JDK 1.8
	 */
	void publish(Configuration config);
}
