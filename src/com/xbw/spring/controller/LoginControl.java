package com.xbw.spring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.xbw.spring.applications.i18n.MessageSourceHelper;
import com.xbw.spring.util.CommUtils;

@Controller
@RequestMapping("/logon")
public class LoginControl {
		
	@Autowired
    private  MessageSourceHelper messageSourceHelper ;
	/**
	 * 用户登录
	 */

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX
				+ "/";
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		String msgLogin = "message_login";
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			currentUser.login(token);
			resultPageURL = "workdesk";
		} catch (UnknownAccountException e) {
			request.setAttribute("", "未知账户");
			CommUtils.log(e, null);
		} catch (IncorrectCredentialsException e) {
			request.setAttribute(msgLogin, "密码不正确");
			CommUtils.log(e, null);
		} catch (LockedAccountException e) {
			request.setAttribute(msgLogin, "账户已锁定");
			CommUtils.log(e, null);
		} catch (ExcessiveAttemptsException e) {
			request.setAttribute(msgLogin, "用户名或密码错误次数过多");
			CommUtils.log(e, null);
		} catch (AuthenticationException e) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			CommUtils.log(e, null);
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			request.setAttribute("userName", username);
			CommUtils.log(null,"用户[" + username + "]登录认证通过");
		} else {
			token.clear();
		}
		return resultPageURL;
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.FORWARD_URL_PREFIX
				+ "/";
	}

	@RequestMapping(value = "/getUserInfo")
	public String getUserInfo(HttpServletRequest request) {
		String currentUser = (String) request.getSession().getAttribute(
				"currentUser");
		CommUtils.log(null,"当前登录的用户为[" + currentUser + "]");
		request.setAttribute("currUser", currentUser);
		
		return "/shiro/common";
	}
	@RequestMapping(value = "/setLanguage" )
	@ResponseBody
	public String setLanguage(HttpServletRequest request) {
		String language = request.getParameter("language");
		String[] supportLangus = {"en_US","zh_CN"};
		List<String> ls = Arrays.asList(supportLangus);
		Locale lo = request.getLocale();
		if(ls.contains(language)){
			 lo = new Locale(language);
		}
		return messageSourceHelper.getMessage("xbw.msg.hellow",new Object[]{"ddd"}, lo);
	}
	 
}
