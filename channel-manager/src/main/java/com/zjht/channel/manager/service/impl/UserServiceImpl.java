package com.zjht.channel.manager.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.dao.UserMapper;
import com.zjht.channel.manager.exception.LoginException;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;
import com.zjht.channel.manager.model.User;
import com.zjht.channel.manager.model.UserExample;
import com.zjht.channel.manager.service.UserService;

/**
 * Function: 用户业务处理具体实现类. <br/>
 * date: Sep 16, 2015 10:17:42 AM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
@Service(value = "UserService")
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper dao;

    /**
     * @see com.zjht.channel.manager.service.UserService#validate(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean validate(String username, String password) {
        username = StringHelper.trim(username);
        password = StringHelper.trim(password);
        
        logger.debug("检查用户名是否为空");
        if (StringHelper.isEmpty(username)) {
            ExceptionHelper.newLoginException(ErrorMessage._00101).throwout();
        }

        logger.debug("检查密码是否为空");
        if (StringHelper.isEmpty(password)) {
            ExceptionHelper.newLoginException(ErrorMessage._00102).throwout();
        }

        logger.debug("检查用户名和密码是否正确");
        UserExample example = new UserExample();
        example.or().andNameEqualTo(username);
        List<User> users = dao.selectByExample(example);
        if (users == null || users.size() == 0) {
            ExceptionHelper.newLoginException(ErrorMessage._00103).throwout();
        }
        
        // !!!!同一个用户名在用户表中不能存在多个
        User user = users.get(0);
        if (!Objects.equals(username, user.getName())
                || !Objects.equals(password, user.getPassword())) {
            ExceptionHelper.newLoginException(ErrorMessage._00104).throwout();
        }
        return true;
    }

}
