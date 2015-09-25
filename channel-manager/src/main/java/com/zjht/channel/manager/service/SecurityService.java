/** 
 * Project Name : channel-manager 
 * File Name : SecurityService.java 
 * Package Name : com.zjht.channel.manager.service 
 * Date : Sep 17, 20153:04:34 PM 
 * 
 */

package com.zjht.channel.manager.service;

import java.util.List;

import com.zjht.channel.manager.model.Security;

/** 
 * ClassName: SecurityService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 17, 2015 3:04:34 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface SecurityService {

	/** 
	 * 获取所有的安全信息列表. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */  
	List<Security> findAll();

	/** 
	 * 添加安全信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void add(Security record);

	/** 
	 * 更新安全信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void update(Security record);

	/** 
	 * 删除安全信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param id 
	 * @since JDK 1.8
	 */  
	void delete(long id);

}
