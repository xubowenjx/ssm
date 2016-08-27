package com.xbw.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xbw.spring.model.shiro.SPerm;

public interface PermDao {
	/**
	 * 
	 * @Title: addPerm
	 * @Description: 新增权限项
	 * @Author: xubowen              
	 * @Create Date: 2016年7月9日 下午3:58:03
	 * @History: 2016年7月9日 下午3:58:03 xubowen Created.
	 *
	 * @param perm
	 * @return
	 *
	 */
	SPerm addPerm(SPerm perm);
	/**
	 * 
	 * @Title: getPermsByRole
	 * @Description:  获取角色下所有权限项
	 * @Author: xubowen              
	 * @Create Date: 2016年7月9日 下午3:57:43
	 * @History: 2016年7月9日 下午3:57:43 xubowen Created.
	 *
	 * @param roleId
	 * @return
	 *
	 */
	List<SPerm> getPermsByRole(@Param("roleId")Integer roleId);
	/**
	 * 
	 * @Title: getPermsByUser
	 * @Description:  获取用户下所有权限项
	 * @Author: xubowen              
	 * @Create Date: 2016年7月9日 下午3:59:19
	 * @History: 2016年7月9日 下午3:59:19 xubowen Created.
	 *
	 * @param userId
	 * @return
	 *
	 */
	List<SPerm> getPermsByUser(@Param("userId") Integer userId);
}
