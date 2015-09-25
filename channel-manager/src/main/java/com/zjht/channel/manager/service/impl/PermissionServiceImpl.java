/** 
 * Project Name : channel-manager 
 * File Name : PermissionServiceImpl.java 
 * Package Name : com.zjht.channel.manager.service.impl 
 * Date : Sep 17, 20154:34:08 PM 
 * 
 */

package com.zjht.channel.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjht.channel.manager.common.helper.FieldChecker;
import com.zjht.channel.manager.dao.PermissionMapper;
import com.zjht.channel.manager.model.Permission;
import com.zjht.channel.manager.model.PermissionExample;
import com.zjht.channel.manager.service.PermissionService;

/** 
 * ClassName: PermissionServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 17, 2015 4:34:08 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

	
	@Autowired
	private PermissionMapper dao;
	
	/** 
	 * @see com.zjht.channel.manager.service.PermissionService#findAll() 
	 */
	@Override
	public List<Permission> findAll() {
		return dao.selectByExample(new PermissionExample());
	}

	/** 
	 * @see com.zjht.channel.manager.service.PermissionService#add(com.zjht.channel.manager.model.Permission) 
	 */
	@Override
	public void add(Permission record) {
		FieldChecker.check(record);
		dao.insert(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.PermissionService#update(com.zjht.channel.manager.model.Permission) 
	 */
	@Override
	public void update(Permission record) {
		FieldChecker.check(record);
		dao.updateByPrimaryKey(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.PermissionService#delete(long) 
	 */
	@Override
	public void delete(long id) {
		dao.deleteByPrimaryKey(id);
	}
}
