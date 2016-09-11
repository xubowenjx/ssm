package com.xbw.spring.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Column extends TagSupport {
		
	/**   
	 */
	private static final long serialVersionUID = -6874635287264808043L;
	private String name;
	private String lable;
	private String value;
	private int width;
	
	@Override
	public int doStartTag() throws JspException {
		Tag tag =  this.getParent();
	 if (tag instanceof Table) {
		Table table = (Table) tag;
		table.addNode(new MyNode(lable, name, value));
	}else{
		try {
			throw new Exception(" 必须在table 标签中使用!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		return EVAL_BODY_INCLUDE;
	}
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
