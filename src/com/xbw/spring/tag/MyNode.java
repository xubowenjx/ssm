package com.xbw.spring.tag;

public class MyNode {
	private String lable ;
	private String name;
	private String value;
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getName() {
		return name;
	}
	public MyNode() {
		super();
	}
	public MyNode(String lable, String name, String value) {
		super();
		this.lable = lable;
		this.name = name;
		this.value = value;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
