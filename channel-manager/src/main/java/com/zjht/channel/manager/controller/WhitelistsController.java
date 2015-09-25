/**
 * Project Name:channel-manager File Name:WhitelistsController.java Package
 * Name:com.zjht.channel.manager.controller Date:Sep 10, 201510:03:25 AM
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
import com.zjht.channel.manager.model.Whitelists;
import com.zjht.channel.manager.service.WhitelistsService;

/**
 * ClassName: WhitelistsController <br/>
 * Function: 白名单模块控制器. <br/>
 * date: Sep 10, 2015 10:03:25 AM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Controller
public class WhitelistsController {

    private final static Logger logger = LoggerFactory.getLogger(WhitelistsController.class);

    @Resource(name = "WhitelistsService")
    private WhitelistsService service;

    @RequestMapping(value = "/whitelists", method = RequestMethod.GET)
    public String whitelistsPage() {
        logger.debug("show whitelists page");
        return "whitelists";
    }

    @RequestMapping(value = "/whitelists/all", method = RequestMethod.GET)
    public @ResponseBody Result<List<Whitelists>> getAll() {
        logger.debug("get all whitelists ");
        Result<List<Whitelists>> result = null;
        try {
            result = ResultHelper.newResult(ErrorMessage._00000);
            result.setData(service.findAll());
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/whitelists", method = RequestMethod.POST)
    public @ResponseBody Result<?> post(@RequestBody Whitelists record) {
        logger.debug("post whitelists ");
        Result<String> result = null;
        try {
            service.add(record);
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/whitelists/{id}", method = RequestMethod.PUT,
            headers = {"content-type=application/json"})
    public @ResponseBody Result<?> put(@PathVariable(value = "id") long id,
            @RequestBody Whitelists record) {
        logger.debug("put whitelists ");
        Result<?> result = null;
        try {
            logger.debug("Request Body:{}", record);
            service.update(record.setId(id).setUpdateTime(new Date()));
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }

    @RequestMapping(value = "/whitelists/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Result<?> delete(@PathVariable(value = "id") long id) {
        logger.debug("delete whitelists ");
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
