/** 
 * Project Name:channel-manager 
 * File Name:UserController.java 
 * Package Name:com.zjht.channel.controller 
 * Date:Sep 9, 20154:09:59 PM 
 * 
 */

package com.zjht.channel.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * ClassName: UserController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 9, 2015 4:09:59 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	//
	@RequestMapping(value="",method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(){
		return "home";
	}
	
}
