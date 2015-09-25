/** 
 * Project Name : channel-manager 
 * File Name : ProviderService.java 
 * Package Name : com.zjht.channel.manager.service 
 * Date : Sep 17, 20155:22:05 PM 
 * 
 */

package com.zjht.channel.manager.service;

import java.util.List;

import com.zjht.channel.manager.model.Service;

/** 
 * ClassName: ProviderService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 17, 2015 5:22:05 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface ServiceBusiness {

	/** 
	 * TODO(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */  
	List<Service> findAll();

	/** 
	 * TODO(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void add(Service record);

	/** 
	 * TODO(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void update(Service record);

	/** 
	 * TODO(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param id 
	 * @since JDK 1.8
	 */  
	void delete(long id);

}
