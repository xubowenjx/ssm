package com.xbw.spring.applications.shiro;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xbw.spring.model.shiro.SPerm;
import com.xbw.spring.model.shiro.SRole;
import com.xbw.spring.model.shiro.SUser;
import com.xbw.spring.service.PermService;
import com.xbw.spring.service.RoleService;
import com.xbw.spring.service.UserService;

public class SelfRealm extends AuthorizingRealm {
 
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermService permService;
	protected final Logger log = Logger.getLogger(this.getClass());
	
	  /*
	   * 不在配置文件中写的话就在这写
	   *  @PostConstruct  
	    public void initCredentialsMatcher() {  
	        setCredentialsMatcher(new PaswordChek());  
	    }  */
	   
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("==================SelfRealm 验证用户 授予角色权限======================");
		 String currentUsername = (String)super.getAvailablePrincipal(principals);  
		 SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
	        if(null!=currentUsername){
	        	SUser u = userService.checkUser(currentUsername);
	        	boolean b ="A".equals(u.getUserType());
	        	log.info("=========当前登录用户类型============"+(b?"管理员":"普通用户"));
	        	if(b){//管理员用户不用继续了
	        		simpleAuthorInfo.addRole("admin");
	        		 return simpleAuthorInfo;  
	        	}
	        	
	        	if(u!=null&&u.getUserId()!=null){
	        		List<SRole> roles =  roleService.getSRolesByUserId(u.getUserId());
	        		for(SRole r:roles){
	        			Integer id = r.getId();
	        			//添加角色
	        			simpleAuthorInfo.addRole(id+"");
	        			 List<SPerm> perms = permService.getPermsByRole(id);
	        			 //添加权限项
	        			 for(SPerm p: perms){
	        				 simpleAuthorInfo.addStringPermission(p.getPermisionUrl());
	        			 }
	        		}
	 	            return simpleAuthorInfo;  
	        	}
	        }
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
        SUser u = userService.checkUser(token.getUsername());
        if(u!=null&&u.getUserId()!=null){
        	 AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(u.getUserName(), u.getPassword(), this.getName());  
             this.setSession("currentUser", u);  
             return authcInfo;  
        }
        return null;  
    }  
      
      
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  

}
