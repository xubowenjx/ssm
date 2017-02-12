package com.xbw.spring.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import com.xbw.spring.util.BusinessException;
import com.xbw.spring.util.CommUtils;

public class Text extends BaseTag {
	/**   
	 */
	private static final long serialVersionUID = 5415650779204107889L;
	 
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
			CommUtils.log(new BusinessException("必须在form标签中使用"), "table标签");
		}
		return super.doStartTag();
	}
	public int doEndTag() throws JspException {  
        return EVAL_PAGE;  
    }  

}
