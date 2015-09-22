/**
 * Project Name:channel-manager File Name:LoginController.java Package
 * Name:com.zjht.channel.controller Date:Sep 9, 20154:09:59 PM
 * 
 */

package com.zjht.channel.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.common.constant.Parameters;
import com.zjht.channel.manager.common.constant.SessionConstant;
import com.zjht.channel.manager.exception.LoginException;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;
import com.zjht.channel.manager.service.UserService;

/**
 * ClassName: LoginController <br/>
 * Function: 用户登录模块controller. <br/>
 * date: Sep 9, 2015 4:09:59 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Value("${theme_name}")
    private String theme;

    @Resource(name = "UserService")
    private UserService service;

    /**
     * 登录页面
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpSession session) {
        LOGGER.debug("show login page");
        String _theme = "";
        _theme = ObjectHelper.trans(session.getAttribute(SessionConstant.THEME.getName()));
        _theme = StringHelper.isEmpty(_theme)
                ? (StringHelper.isEmpty(this.theme) ? "default" : this.theme) : _theme;
        session.setAttribute(SessionConstant.THEME.getName(), _theme);
        LOGGER.debug("using theme [{}]", _theme);
        return "login";
    }

    /**
     * 登出页面
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        LOGGER.debug("user logout!");
        session.removeAttribute(SessionConstant.USER.getName());
        session.invalidate();
        return "login";
    }

    /**
     * 验证登陆信息
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/home");
        try {
            if (service.validate(username, password)) {
                session.setAttribute(SessionConstant.USER.getName(), username);
                LOGGER.info("user [{}] login!", username);
            }
        } catch (LoginException e) {
            LOGGER.info("LoginException！code=[{}],message=[{}]", e.code(), e.getMessage());
            mav.setViewName("login");
            mav.addObject(Parameters.ERROR.getName(), e);
        } catch (Exception e) {
            mav.setViewName("login");
            mav.addObject(Parameters.ERROR.getName(),
                    ExceptionHelper.newApplicationException(ErrorMessage._99999));
        }
        return mav;
    }

    @RequestMapping(value = "/theme/{themeName}", method = RequestMethod.GET)
    public String theme(@PathVariable("themeName") String themeName, HttpSession session) {
        session.setAttribute(SessionConstant.THEME.getName(), themeName);
        return "redirect:/home";
    }
}
