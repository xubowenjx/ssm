package com.xbw.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xbw.spring.model.shiro.SUser;

/**
 * author: xubowen date: 2016/7/3 11:35
 */
public interface UserDao {
	SUser getUser(@Param("name") String name);

	List<SUser> getUserList();
}
