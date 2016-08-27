package com.xbw.spring.model.shiro;

/**
 * SPerm entity. @author MyEclipse Persistence Tools
 */

public class SPerm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String permisionName;
	private String permisionUrl;
	private Integer valid;
	private Integer needCheck;

	// Constructors

	/** default constructor */
	public SPerm() {
	}

	/** full constructor */
	public SPerm(String permisionName, String permisionUrl, Integer valid) {
		this.permisionName = permisionName;
		this.permisionUrl = permisionUrl;
		this.valid = valid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermisionName() {
		return this.permisionName;
	}

	public void setPermisionName(String permisionName) {
		this.permisionName = permisionName;
	}

	public String getPermisionUrl() {
		return this.permisionUrl;
	}

	public void setPermisionUrl(String permisionUrl) {
		this.permisionUrl = permisionUrl;
	}

	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(Integer needCheck) {
		this.needCheck = needCheck;
	}

}