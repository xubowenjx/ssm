package com.xbw.spring.tag;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xbw.spring.util.CommUtils;

public class FormTag extends TagSupport {
	/**   
	 */
	private static final long serialVersionUID = -6751576341398486477L;
	private String action;
	private String method = "post";
	private String name;
	private String id;
	private String onSubmit;
	private String direct = "normal";
	//记录表单中的元素 input select 等
	private List<MyNode> list = new LinkedList();
	/**
     *  doStartTag()方法返回一个整数值，用来决定程序的后续流程。  
     *　A.Tag.SKIP_BODY：表示标签之间的内容被忽略  
     *　B.Tag.EVAL_BODY_INCLUDE：表示标签之间的内容被正常执行  
	 */
	@Override
	public int doStartTag() throws JspException {
		 if(!list.isEmpty()){
			 list = new LinkedList();
		 }
	       		return EVAL_BODY_INCLUDE;  
	}
	/**  
     *  doEndTag：当JSP容器遇到自定义标签的结束标志，就会调用doEndTag()方法。doEndTag()方法也返回一个整数值，用来决定程序后续流程。  
　  *  A.Tag.SKIP_PAGE：表示立刻停止执行网页，网页上未处理的静态内容和JSP程序均被忽略任何已有的输出内容立刻返回到客户的浏览器上。  
　  *  B.Tag.EVAL_PAGE：表示按照正常的流程继续执行JSP网页  
     */  
	public int doEndTag() throws JspException {  
		 JspWriter out = pageContext.getOut();  
		 onSubmit = onSubmit==null?"":onSubmit;
		 
	        try {  
	            out.write("<form class='form-horizontal' action="+action+" method="+method+" onsubmit='"+onSubmit+"' name='"+name+"' id='"+id+"'>");
	            int l = list.size();
	            boolean b = "normal".equals(direct);
	            for(int i  = 0;i<l;i++){ 
	             
            		MyNode node = list.get(i);
	                out.write("<div class='pull-left coldiv'><div class='form-group'>");
	              if(b){
	            	  out.write("	 <label for='"+node.getName()+"' class='col-sm-4 control-label collab'>"
	        				+ node.getName()
	        				+ "</label>");
	            	  out.write("<div class='col-sm-8'> <input type='text'  class='form-control' name="+node.getName()+" value='"+node.getValue()+"' id='"+node.getName()+"' /></div>");  
	            	
	              }else{
	            	  out.write("<div class='col-sm-8'> <input type='text'  class='form-control' name="+node.getName()+" value='"+node.getValue()+"' id='"+node.getName()+"' /></div>");  
	            	  out.write("	 <label for='"+node.getName()+"' class='col-sm-4 control-label collab' style='text-align:left;'>"
		        				+ node.getName()
		        				+ "</label>");
	              }
	                
	              out.write("</div></div>");
	            }  
	            out.write("</form>");
	        } catch (IOException e) {  
	        	CommUtils.log(e, "FormTag标签");
	        }  
        return EVAL_PAGE;  
    }  
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOnSubmit() {
		return onSubmit;
	}
	public void setOnSubmit(String onSubmit) {
		this.onSubmit = onSubmit;
	}
	public List<MyNode> getList() {
		return list;
	}
	public void setList(List<MyNode> list) {
		this.list = list;
	}
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}

	
}
