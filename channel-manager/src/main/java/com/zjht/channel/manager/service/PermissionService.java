/** 
 * Project Name : channel-manager 
 * File Name : PermissionService.java 
 * Package Name : com.zjht.channel.manager.service 
 * Date : Sep 17, 20154:31:14 PM 
 * 
 */

package com.zjht.channel.manager.service;

import java.util.List;

import com.zjht.channel.manager.model.Permission;

/** 
 * ClassName: PermissionService <br/> 
 * Function: 服务权限业务处理接口. <br/> 
 * date: Sep 17, 2015 4:31:14 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface PermissionService {

	/** 
	 * 获取权限列表. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */  
	List<Permission> findAll();

	/** 
	 * 添加权限. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void add(Permission record);

	/** 
	 * 修改权限. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void update(Permission record);

	/** 
	 * 删除权限. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param id 
	 * @since JDK 1.8
	 */  
	void delete(long id);

}
