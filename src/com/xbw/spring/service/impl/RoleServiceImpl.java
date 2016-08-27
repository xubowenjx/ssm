package com.xbw.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbw.spring.mapper.RoleDao;
import com.xbw.spring.model.shiro.SRole;
import com.xbw.spring.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	public List<SRole> getSRolesByUserId(Integer userId) {
		return userId==null?null:roleDao.getSRolesByUserId(userId);
	}

}
