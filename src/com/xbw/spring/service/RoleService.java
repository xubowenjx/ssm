package com.xbw.spring.service;

import java.util.List;

import com.xbw.spring.model.shiro.SRole;

public interface RoleService {
	List<SRole> getSRolesByUserId(Integer userId);
}
