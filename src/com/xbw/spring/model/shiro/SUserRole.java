package com.xbw.spring.model.shiro;

/**
 * SUserRole entity. @author MyEclipse Persistence Tools
 */

public class SUserRole implements java.io.Serializable {

	// Fields

	private Integer relaId;
	private Integer userId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public SUserRole() {
	}

	/** full constructor */
	public SUserRole(Integer userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getRelaId() {
		return this.relaId;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}