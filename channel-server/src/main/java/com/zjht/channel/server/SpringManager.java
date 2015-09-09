/** 
 * Project Name:channel-server 
 * File Name:SpringManager.java 
 * Package Name:com.zjht.channel.server 
 * Date:2015年8月31日上午9:52:48 
 * 
 */

package com.zjht.channel.server;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;

/**
 * ClassName: SpringManager <br/>
 * Function: spring容器管理类. <br/>
 * date: 2015年8月31日 上午9:52:48 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public final class SpringManager {

	private static SpringManager springMgr;

	private SpringManager() {

	}

	public static SpringManager newInstance() {
		if (springMgr == null) {
			springMgr = new SpringManager();
		}
		return springMgr;
	}

	private ConfigurableApplicationContext appContext;

	/**
	 * 
	 * initApplicationContext:通过配置文件初始化spring容器. <br/>
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param filenames
	 * @return
	 * @since JDK 1.8
	 */
	public SpringManager initApplicationContext(String... filenames) {
		Preconditions.checkArgument(filenames != null && filenames.length > 0,"配置文件参数不能为空");
		appContext = new ClassPathXmlApplicationContext(filenames);
		return springMgr;
	}

	/**
	 * 
	 * initApplicationContext:通过注解类初始化spring容器. <br/>
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param clazz
	 * @return
	 * @since JDK 1.8
	 */
	public SpringManager initApplicationContext(Class<?> clazz) {
		appContext = new AnnotationConfigApplicationContext(clazz);
		return springMgr;
	}

	public ConfigurableApplicationContext getAppContext() {
		return appContext;
	}

}