package com.xbw.spring.model.shiro;

/**
 * SRole entity. @author MyEclipse Persistence Tools
 */

public class SRole implements java.io.Serializable {

	private static final long serialVersionUID = 3042698957465739034L;
	private Integer id;
	private String roleName;
	private Integer valid;

	// Constructors

	
	public SRole() {
		//default constructor 
	}

	/** full constructor */
	public SRole(String roleName, Integer valid) {
		this.roleName = roleName;
		this.valid = valid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

}