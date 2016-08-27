package com.xbw.spring.model.shiro;

/**
 * SRolePermRela entity. @author MyEclipse Persistence Tools
 */

public class SRolePermRela implements java.io.Serializable {

	// Fields

	private Integer prRelaId;
	private Integer roleId;
	private Integer permissionId;

	// Constructors

	/** default constructor */
	public SRolePermRela() {
	}

	/** full constructor */
	public SRolePermRela(Integer roleId, Integer permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	// Property accessors

	public Integer getPrRelaId() {
		return this.prRelaId;
	}

	public void setPrRelaId(Integer prRelaId) {
		this.prRelaId = prRelaId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}