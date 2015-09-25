/** 
 * Project Name : channel-manager 
 * File Name : WhitelistsServiceImpl.java 
 * Package Name : com.zjht.channel.manager.service.impl 
 * Date : Sep 16, 20154:33:36 PM 
 * 
 */

package com.zjht.channel.manager.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjht.channel.manager.common.helper.FieldChecker;
import com.zjht.channel.manager.dao.WhitelistsMapper;
import com.zjht.channel.manager.model.Whitelists;
import com.zjht.channel.manager.model.WhitelistsExample;
import com.zjht.channel.manager.service.WhitelistsService;

/** 
 * ClassName: WhitelistsServiceImpl <br/> 
 * Function: 白名单业务逻辑处理具体实现类. <br/> 
 * date: Sep 16, 2015 4:33:36 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@Service(value="WhitelistsService")
public class WhitelistsServiceImpl implements WhitelistsService {
	private final static Logger logger = LoggerFactory.getLogger(WhitelistsServiceImpl.class);
	
	@Autowired
	private WhitelistsMapper dao;
	
	/** 
	 * @see com.zjht.channel.manager.service.WhitelistsService#findAll() 
	 */
	@Override
	public List<Whitelists> findAll() {
		return dao.selectByExample(new WhitelistsExample());
	}

	/** 
	 * @see com.zjht.channel.manager.service.WhitelistsService#delete(long) 
	 */
	@Override
	public void delete(long id) {
		dao.deleteByPrimaryKey(id);
	}

	/** 
	 * @see com.zjht.channel.manager.service.WhitelistsService#update(com.zjht.channel.manager.model.Whitelists) 
	 */
	@Override
	public void update(Whitelists record) {
		FieldChecker.check(record);
		dao.updateByPrimaryKeySelective(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.WhitelistsService#add(com.zjht.channel.manager.model.Whitelists) 
	 */
	@Override
	public void add(Whitelists record) {
		FieldChecker.check(record);
		dao.insert(record);
	}
}