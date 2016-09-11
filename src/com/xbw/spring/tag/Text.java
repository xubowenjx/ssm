package com.xbw.spring.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Text extends TagSupport {
	/**   
	 */
	private static final long serialVersionUID = 5415650779204107889L;
	private String name;
	private String lable;
	private String value;
	@Override
	public int doStartTag() throws JspException {
		Tag pTag =  this.getParent(); 
		if( pTag instanceof FormTag){
			FormTag form = 	(FormTag)pTag;
			List<MyNode> nodes = form.getList();
			MyNode n = new MyNode(lable, name, value);
			nodes.add(n);
			return EVAL_BODY_INCLUDE;  
		}else{
			try {
				throw new Exception("必须在form标签中使用!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.doStartTag();
	}
	public int doEndTag() throws JspException {  
        return EVAL_PAGE;  
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
	

}
