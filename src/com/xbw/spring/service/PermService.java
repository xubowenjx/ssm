package com.xbw.spring.service;

import java.util.List;

import com.xbw.spring.model.shiro.SPerm;

public interface PermService {
	List<SPerm> getPermsByRole(Integer roleId);
}
