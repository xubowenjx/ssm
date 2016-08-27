package com.xbw.spring.applications.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.xbw.spring.model.shiro.SUser;

public class RoleFilter  extends AuthorizationFilter {  
		protected final Logger log = Logger.getLogger(this.getClass());
	    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
	            throws IOException {  
	    	HttpServletRequest req= (HttpServletRequest) request;
	    	String path =req.getRequestURI();
	    	String 	 re =req.getContextPath();
	    	path = 	path.substring(re.length());
	        Subject subject=null;
			try {
				subject = getSubject(request, response);
			} catch (Exception e) {
				return false;
			}  
			boolean pass=false;
			if(subject==null){
				pass= false;
			}
			if(subject.hasRole("admin")){
				pass= true;
			}
			pass = pass?pass:subject.isPermitted(path);
			SUser u = (SUser)subject.getSession().getAttribute("currentUser");
			log.info(path+":当前用户"+u.getUserName()+" has power "+pass);
	       return pass;
	    }  
}
