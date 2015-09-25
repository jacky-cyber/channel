package com.zjht.channel.manager.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.controller.bean.Result;
import com.zjht.channel.manager.controller.helper.ResultHelper;
import com.zjht.channel.manager.service.ConfigurationService;
import com.zjht.channel.manager.zookeeper.bean.Configuration;

/**
 * ClassName: HomeController <br/>
 * Function: 主页controller. <br/>
 * date: Sep 14, 2015 3:08:59 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    @Resource(name = "ConfigurationService")
    private ConfigurationService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage() {
        LOGGER.debug("show home page");
        return "home";
    }

    @RequestMapping(value = "/home/config", method = RequestMethod.GET,
            headers = {"content-type=application/json"})
    public @ResponseBody Result<Configuration> config() {
        Result<Configuration> result = null;
        try {
            LOGGER.debug("get config");
            result = ResultHelper.newResult(ErrorMessage._00000);
            result.setData( service.configure());
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }
    
    @RequestMapping(value = "/home/publish", method = RequestMethod.GET,
            headers = {"content-type=application/json"})
    public @ResponseBody Result<Configuration> publish() {
        Result<Configuration> result = null;
        try {
            LOGGER.debug("get publish");
            service.publish(service.configure());
            result = ResultHelper.newResult(ErrorMessage._00000);
        } catch (Exception e) {
            result = ResultHelper.newResult(e);
        }
        return result;
    }
}