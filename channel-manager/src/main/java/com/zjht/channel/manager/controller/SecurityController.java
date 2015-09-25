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
import com.zjht.channel.manager.model.Security;
import com.zjht.channel.manager.service.SecurityService;

/**
 * 终端秘钥模块控制器
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 11:21:36 PM
 */
@Controller
public class SecurityController {

    private final static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Resource(name = "SecurityService")
    private SecurityService service;

    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public String securityPage() {
        logger.debug("show security page");
        return "security";
    }

    @RequestMapping(value = "/security/all", method = RequestMethod.GET)
    public @ResponseBody Result<List<Security>> getAll() {
        logger.debug("get all security ");
        Result<List<Security>> result = null;
        try {
            result = ResultHelper.newResult(ErrorMessage._00000);
            result.setData( service.findAll());
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/security", method = RequestMethod.POST)
    public @ResponseBody Result<?> post(@RequestBody Security record) {
        logger.debug("post security ");
        Result<?> result = null;
        try {
            service.add(record);
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/security/{id}", method = RequestMethod.PUT,
            headers = {"content-type=application/json"})
    public @ResponseBody Result<?> put(@PathVariable(value = "id") long id,
            @RequestBody Security record) {
        logger.debug("put security ");
        Result<?> result = null;
        try {
            logger.debug("Request Body:{}", record);
            record.setId(id).setUpdateTime(new Date());
            service.update(record);
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/security/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Result<?> delete(@PathVariable(value = "id") long id) {
        logger.debug("delete security ");
        Result<?> result = null;
        try {
            service.delete(id);
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }


}
