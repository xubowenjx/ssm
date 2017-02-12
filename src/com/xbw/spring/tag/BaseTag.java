package com.xbw.spring.tag; 

import javax.servlet.jsp.tagext.TagSupport;

public class BaseTag extends TagSupport {
	    
	private static final long serialVersionUID = 6937705079891556823L;
	protected String name;
	protected String lable;
	protected String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

    