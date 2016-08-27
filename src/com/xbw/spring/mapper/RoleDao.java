package com.xbw.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xbw.spring.model.shiro.SRole;

public interface RoleDao {
	SRole addRole(SRole role);
	List<SRole> getSRolesByUserId(@Param("userId") Integer userId);
}
