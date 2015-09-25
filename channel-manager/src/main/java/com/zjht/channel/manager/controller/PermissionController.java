/** 
 * Project Name : channel-manager 
 * File Name : PermissionController.java 
 * Package Name : com.zjht.channel.manager.controller 
 * Date : Sep 16, 20151:53:45 PM 
 * 
 */

package com.zjht.channel.manager.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.controller.bean.Result;
import com.zjht.channel.manager.controller.helper.ResultHelper;
import com.zjht.channel.manager.model.Permission;
import com.zjht.channel.manager.service.PermissionService;

/**
 * ClassName: PermissionController <br/>
 * Function: 服务权限管理控制器. <br/>
 * date: Sep 16, 2015 1:53:45 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Controller
public class PermissionController {

	private final static Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Resource(name="PermissionService")
	private PermissionService service;
	
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public String permissionPage() {
		logger.debug("show permission page");
		return "permission";
	}
	
	@RequestMapping(value="/permission/all",method=RequestMethod.GET)
	public @ResponseBody Result<List<Permission>> getAll(){
		logger.debug("get all permission ");
		Result<List<Permission>> result = null;
		try{
		    result = ResultHelper.newResult(ErrorMessage._00000);
		    result.setData( service.findAll());
		}catch(Exception e){
			result = ResultHelper.newResult(e);
		}
		return result;
	}
	
	@RequestMapping(value="/permission",method=RequestMethod.POST)
	public @ResponseBody Result<?> post(@RequestBody Permission record){
		logger.debug("post permission ");
		Result<?> result = null;
		try{
			service.add(record);
			result = ResultHelper.newResult(ErrorMessage._00000);
		}catch(Exception e){
		    result = ResultHelper.newResult(e);
		}
		return result;
	}
	
	@RequestMapping(value="/permission/{id}",method=RequestMethod.PUT,headers={"content-type=application/json"})
	public @ResponseBody Result<?> put(@PathVariable(value="id")long id,@RequestBody Permission record ){
		logger.debug("put permission ");
		Result<?> result = null;
		try{
			logger.debug("Request Body:{}",record);
			record.setId(id).setUpdateTime(new Date());
			service.update(record);
			result = ResultHelper.newResult(ErrorMessage._00000);
		}catch(Exception e){
		    result = ResultHelper.newResult(e);
		}
		return result;
	}
	
	@RequestMapping(value="/permission/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Result<?> delete(@PathVariable(value="id")long id){
		logger.debug("delete permission ");
		Result<String> result = null;
		try{
			service.delete(id);
			result = ResultHelper.newResult(ErrorMessage._00000);
		}catch(Exception e){
		    result = ResultHelper.newResult(e);
		}
		return result;
	}
}
