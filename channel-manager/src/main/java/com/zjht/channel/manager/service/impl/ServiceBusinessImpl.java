/** 
 * Project Name : channel-manager 
 * File Name : ProviderServiceImpl.java 
 * Package Name : com.zjht.channel.manager.service.impl 
 * Date : Sep 17, 20155:22:51 PM 
 * 
 */

package com.zjht.channel.manager.service.impl;

import static com.zjht.channel.manager.common.constant.ErrorMessage.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.manager.common.helper.FieldChecker;
import com.zjht.channel.manager.dao.ServiceMapper;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;
import com.zjht.channel.manager.model.Service;
import com.zjht.channel.manager.model.ServiceExample;
import com.zjht.channel.manager.service.ServiceBusiness;

/** 
 * ClassName: ProviderServiceImpl <br/> 
 * Function: 服务管理业务处理类. <br/> 
 * date: Sep 17, 2015 5:22:51 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@org.springframework.stereotype.Service("ServiceBusiness")
public class ServiceBusinessImpl implements ServiceBusiness {

	@Autowired
	private ServiceMapper dao;
	
	/** 
	 * @see com.zjht.channel.manager.service.ServiceBusiness#findAll() 
	 */
	@Override
	public List<Service> findAll() {
		return dao.selectByExample(new ServiceExample());
	}

	/** 
	 * @see com.zjht.channel.manager.service.ServiceBusiness#add(com.zjht.channel.manager.model.Service) 
	 */
	@Override
	public void add(Service record) {
		FieldChecker.check(record);//校验字段
		
        String name    = record.getName();
        String version = record.getVersion();
		ServiceExample example = new ServiceExample();
		
		example.or()
    		.andNameEqualTo(name)
    		.andVersionEqualTo(version);
		
		//判断数据库是否已存在name&version的数据
		if(!ListHelper.isEmpty(dao.selectByExample(example))){
		    ExceptionHelper.newServiceException(_00501, name,version).throwout();
		}
		
		example = null;
		example = new ServiceExample();
		String fullyQualifiedName = record.getFullyQualifiedName();
		example.or()
          .andFullyQualifiedNameEqualTo(fullyQualifiedName)
          .andVersionEqualTo(version);
		//判断数据库是否已存在fullQualifiedName&version的数据
        if(!ListHelper.isEmpty(dao.selectByExample(example))){
            ExceptionHelper.newServiceException(_00502, fullyQualifiedName,version).throwout();
        }
        
		dao.insert(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.ServiceBusiness#update(com.zjht.channel.manager.model.Service) 
	 */
	@Override
	public void update(Service record) {
		FieldChecker.check(record);
		dao.updateByPrimaryKey(record);
	}

	/** 
	 * @see com.zjht.channel.manager.service.ServiceBusiness#delete(long) 
	 */
	@Override
	public void delete(long id) {
		dao.deleteByPrimaryKey(id);
	}
}
