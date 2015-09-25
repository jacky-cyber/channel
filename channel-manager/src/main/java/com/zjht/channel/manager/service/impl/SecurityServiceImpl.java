/** 
 * Project Name : channel-manager 
 * File Name : SecurityServiceImpl.java 
 * Package Name : com.zjht.channel.manager.service.impl 
 * Date : Sep 17, 20153:06:42 PM 
 * 
 */

package com.zjht.channel.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjht.channel.manager.common.helper.FieldChecker;
import com.zjht.channel.manager.dao.SecurityMapper;
import com.zjht.channel.manager.model.Security;
import com.zjht.channel.manager.model.SecurityExample;
import com.zjht.channel.manager.service.SecurityService;

/** 
 * ClassName: SecurityServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 17, 2015 3:06:42 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@Service("SecurityService")
public class SecurityServiceImpl implements SecurityService {
	
	@Autowired
	private SecurityMapper dao;

	/** 
	 * @see com.zjht.channel.manager.service.SecurityService#findAll() 
	 */
	@Override
	public List<Security> findAll() {
		return dao.selectByExample(new SecurityExample());
	}

	/** 
	 * @see com.zjht.channel.manager.service.SecurityService#add(com.zjht.channel.manager.model.Security) 
	 */
	@Override
	public void add(Security record) {
		FieldChecker.check(record);
		dao.insert(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.SecurityService#update(com.zjht.channel.manager.model.Security) 
	 */
	@Override
	public void update(Security record) {
		FieldChecker.check(record);
		dao.updateByPrimaryKey(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.SecurityService#delete(long) 
	 */
	@Override
	public void delete(long id) {
		dao.deleteByPrimaryKey(id);
	}

}
