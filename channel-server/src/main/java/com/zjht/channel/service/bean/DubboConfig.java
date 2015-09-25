/** 
 * Project Name:channel-server 
 * File Name:DubboConfig.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 20152:18:32 PM 
 * 
 */

package com.zjht.channel.service.bean;

import java.util.List;

/** 
 * ClassName: DubboConfig <br/> 
 * Function: Dubbo配置信息. <br/> 
 * date: Sep 11, 2015 2:18:32 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class DubboConfig {
	private Application application;
	private Registry registry;
	private Consumer consumer;
	private List<Reference> references;
	/** 
	 * application. 
	 * 
	 * @return  the application 
	 * @since   JDK 1.8
	 */
	public Application getApplication() {
		return application;
	}
	/** 
	 * application. 
	 * 
	 * @param   application    the application to set 
	 * @since   JDK 1.8
	 */
	public DubboConfig setApplication(Application application) {
		this.application = application;
		return this;
	}
	/** 
	 * registry. 
	 * 
	 * @return  the registry 
	 * @since   JDK 1.8
	 */
	public Registry getRegistry() {
		return registry;
	}
	/** 
	 * registry. 
	 * 
	 * @param   registry    the registry to set 
	 * @since   JDK 1.8
	 */
	public DubboConfig setRegistry(Registry registry) {
		this.registry = registry;
		return this;
	}
	/** 
	 * consumer. 
	 * 
	 * @return  the consumer 
	 * @since   JDK 1.8
	 */
	public Consumer getConsumer() {
		return consumer;
	}
	/** 
	 * consumer. 
	 * 
	 * @param   consumer    the consumer to set 
	 * @since   JDK 1.8
	 */
	public DubboConfig setConsumer(Consumer consumer) {
		this.consumer = consumer;
		return this;
	}
	/** 
	 * references. 
	 * 
	 * @return  the references 
	 * @since   JDK 1.8
	 */
	public List<Reference> getReferences() {
		return references;
	}
	/** 
	 * references. 
	 * 
	 * @param   references    the references to set 
	 * @since   JDK 1.8
	 */
	public DubboConfig setReferences(List<Reference> references) {
		this.references = references;
		return this;
	}
	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "DubboConfig [application=" + application + ", registry=" + registry + ", consumer=" + consumer
				+ ", references=" + references + "]";
	}
}
