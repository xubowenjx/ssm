package com.xbw.spring.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import com.xbw.spring.util.BusinessException;
import com.xbw.spring.util.CommUtils;

public class Column extends BaseTag {
		
	/**   
	 */
	private static final long serialVersionUID = -6874635287264808043L;
	@Override
	public int doStartTag() throws JspException {
		Tag tag =  this.getParent();
	 if (tag instanceof Table) {
		Table table = (Table) tag;
		table.addNode(new MyNode(lable, name, value));
	}else{
			CommUtils.log(new BusinessException("必须在table标签中使用"), "Column标签");
	}
		return EVAL_BODY_INCLUDE;
	}
	
}
