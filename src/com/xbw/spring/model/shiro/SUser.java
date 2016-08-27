package com.xbw.spring.model.shiro;


/**
 * SUser entity. @author MyEclipse Persistence Tools
 */

public class SUser implements java.io.Serializable {
	

	private Integer userId;
	private String userName;
	private String password;
	private String userType;

	// Constructors

	/** default constructor */
	public SUser() {
	}

	/** full constructor */
	public SUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SUser [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + "]";
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}