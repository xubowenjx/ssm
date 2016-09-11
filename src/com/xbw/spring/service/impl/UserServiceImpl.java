package com.xbw.spring.service.impl;

import java.util.List;

import com.xbw.spring.mapper.UserDao;
import com.xbw.spring.model.shiro.SUser;
import com.xbw.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: xubowen
 * date: 2016/7/3 11:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public SUser checkUser(String name) {
        return userDao.getUser(name);
    }
    
    public List<SUser> getUserList(){
    		return userDao.getUserList();
    }
}
