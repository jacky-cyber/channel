/** 
 * Project Name : channel-manager 
 * File Name : WhitelistsService.java 
 * Package Name : com.zjht.channel.manager.service 
 * Date : Sep 16, 20154:32:40 PM 
 * 
 */

package com.zjht.channel.manager.service;

import java.util.List;

import com.zjht.channel.manager.model.Whitelists;

/** 
 * ClassName: WhitelistsService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 16, 2015 4:32:40 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface WhitelistsService {
	
	/**
	 * 
	 * 获取所有的白名单列表. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @return 
	 * @since JDK 1.8
	 */
	List<Whitelists> findAll();

	/** 
	 * 删除白名单信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param id 
	 * @since JDK 1.8
	 */  
	void delete(long id);

	/** 
	 * 修改白名单信息. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param whitelists 
	 * @since JDK 1.8
	 */  
	void update(Whitelists record);

	/** 
	 * 新增白名单. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param record 
	 * @since JDK 1.8
	 */  
	void add(Whitelists record);
}
