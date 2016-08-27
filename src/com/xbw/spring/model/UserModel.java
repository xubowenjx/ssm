package com.xbw.spring.model;

/**
 * author: xubowen
 * date: 2016/7/3 11:37
 */
public class UserModel {
        private String name;
        private  String password;
        private String userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
