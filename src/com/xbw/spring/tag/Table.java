package com.xbw.spring.tag;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.xbw.spring.util.FieldsCollector;

/**
 * @ClassName: Table   
 * @Description: 封装表格
 * @author: xubowen
 * @date: 2016年1月13日 上午10:00:59   
 *
 */
public class Table extends TagSupport{
	/**   
	 */
	private static final long serialVersionUID = -8588528450180152105L;
	//表格数据源
	private String data;
	//表格样式
	private String className;
	//内联样式
	private String style;
	
	//是否多选
	private Boolean mutiSelect = false;
	//存放子节点
   private List<MyNode> list =Lists.newLinkedList();

	@Override
	public int doStartTag() throws JspException {
		 if(list.size()>0){
			 list = Lists.newLinkedList();;
		 }
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doEndTag() throws JspException {
		 JspWriter out = pageContext.getOut();  
		String id =  data+"_table";
		String table = "<table id='"+id+"' name='"+id+"' class='"+className+"'>";
		//1构造表头
		StringBuilder thead =new StringBuilder("<thead>");
		int l = list.size();
		int j = 0;
		MyNode node = null;
		String th = "<th>";
		String _th = "</th>";
		if(mutiSelect){
			thead .append(th+"<input type='checkbox' id='"+data+"_checkbox' name='"+data+"_checkbox'/>"+_th);
		}
		for(int i = 0;i<l;i++){
			node = list.get(i);
			thead .append(th+node.getLable()+_th);
		}
		 
		thead.append("</thead>");
		//构造表体
		StringBuilder tbody = new StringBuilder("<tbody>");
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Object rs= request.getAttribute(data);
		ResultSet _rs=null;
		List<Object> _list=null;
		if(rs instanceof ResultSet){
			_rs=(ResultSet)rs;
		}else if(rs instanceof List){
			_list = (List<Object>) rs;
		}
		  Gson gson = new Gson();
		String v ;
		String _tr = "</tr>";
		String td = "<td>";
		String _td = "</td>";
		 try {
			while(_rs!=null&&_rs.next()){//构造每一行
				StringBuilder tr = new StringBuilder("<tr>");
				j = 0;
				if(mutiSelect){
					tr .append(td+"<input type='checkbox' id='"+data+"_checkbox' name='"+data+"_checkbox'/>"+_td);
				}
				for(int i = 0;i<l;i++){
					v =  _rs.getString(list.get(i).getName());
					tr.append(td+v+_td);
				}
				tr.append(_tr); 
				tbody.append(tr);
			 }
			if(_rs!=null){
				_rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 if(_list!=null&&_list.size()>0){//构造每一行
			 List<Map<String, Object>> maps = null;
			try {
				maps = FieldsCollector.getFileds(_list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				for(int i = 0;i<maps.size();i++){
					StringBuilder tr = new StringBuilder("<tr>");
					if(mutiSelect){
						tr .append(td+"<input type='checkbox' id='"+data+"_checkbox' name='"+data+"_checkbox'/>"+_td);
					}
					Map<String, Object>  _cur = maps.get(i);
					for(int k=0;k<l;k++){
							v =  maps.get(i).get(list.get(k).getName())+"";
							tr.append(td+v+_td);
					}
					tr.append(_tr); 
					tbody.append(tr);
				}
				
			 }
		 thead. append(tbody);
		 table =  table+thead.toString()+"</table>";
		 try {
			out.write(table);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String clazz) {
		this.className = clazz;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void addNode(MyNode node ){
		this.list.add(node);
	}
	public Boolean getMutiSelect() {
		return mutiSelect;
	}
	public void setMutiSelect(Boolean mutiSelect) {
		this.mutiSelect = mutiSelect;
	}
	
}
