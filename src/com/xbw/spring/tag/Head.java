package com.xbw.spring.tag; 

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Head extends TagSupport {
	@Override
	public int doStartTag() throws JspException {
		 return SKIP_BODY;
	}
	
}

    