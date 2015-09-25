package com.zjht.channel.manager.controller;

import static com.zjht.channel.manager.common.constant.ErrorMessage.*;

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

import com.zjht.channel.manager.controller.bean.Result;
import com.zjht.channel.manager.controller.helper.ResultHelper;
import com.zjht.channel.manager.model.Service;
import com.zjht.channel.manager.service.ServiceBusiness;

/**
 * 
 * 服务管理模块控制器
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 11:21:56 PM
 */
@Controller
public class ServiceController {

    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Resource(name = "ServiceBusiness")
    private ServiceBusiness service;

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String servicePage() {
        logger.debug("show service page");
        return "service";
    }

    @RequestMapping(value = "/service/all", method = RequestMethod.GET)
    public @ResponseBody Result<List<Service>> getAll() {
        logger.debug("get all service ");
        Result<List<Service>> result = null;
        try {
            result = ResultHelper.newResult(_00000);
            result.setData( service.findAll());
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public @ResponseBody Result<?> post(@RequestBody Service record) {
        logger.debug("post service ");
        Result<?> result = null;
        try {
            service.add(record);
            result = ResultHelper.newResult(_00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.PUT,
            headers = {"content-type=application/json"})
    public @ResponseBody Result<?> put(@PathVariable(value = "id") long id,
            @RequestBody Service record) {
        logger.debug("put service ");
        Result<?> result = null;
        try {
            logger.debug("Request Body:{}", record);
            record.setId(id).setUpdateTime(new Date());
            service.update(record);
            result = ResultHelper.newResult(_00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Result<?> delete(@PathVariable(value = "id") long id) {
        logger.debug("delete service ");
        Result<?> result = null;
        try {
            service.delete(id);
            result = ResultHelper.newResult(_00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }


}
