package com.xbw.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbw.spring.mapper.PermDao;
import com.xbw.spring.model.shiro.SPerm;
import com.xbw.spring.service.PermService;

@Service
public class PermServiceImpl implements PermService {
	@Autowired
	private PermDao permDao;
	public List<SPerm> getPermsByRole(Integer roleId) {
		return roleId==null?null:permDao.getPermsByRole(roleId);
	}

}
