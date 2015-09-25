/** 
 * Project Name : channel-manager 
 * File Name : UserService.java 
 * Package Name : com.zjht.channel.manager.service 
 * Date : Sep 15, 20155:29:14 PM 
 * 
 */

package com.zjht.channel.manager.service;

import com.zjht.channel.manager.model.User;

/** 
 * ClassName: UserService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: Sep 15, 2015 5:29:14 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public interface UserService {
	/** 
	 *  检查用户名和密码是否正确. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param username
	 * @param password
	 * @return 
	 * @since JDK 1.8
	 */  
	boolean validate(String username, String password);
}
