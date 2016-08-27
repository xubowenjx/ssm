package com.xbw.spring.service;

import com.xbw.spring.model.shiro.SUser;

/**
 * author: xubowen
 * date: 2016/7/3 11:43
 */
public interface UserService {
	SUser checkUser(  String name);
}
