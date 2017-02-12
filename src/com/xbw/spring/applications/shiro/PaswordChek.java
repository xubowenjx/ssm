package com.xbw.spring.applications.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.xbw.spring.util.SecurUtils;
/**
 * @ClassName: PaswordChek   
 * @Description: 自定义shiro密码校验 加载realm里面
 * @author: xubowen
 * @date: 2016年7月9日 16:23:55   
 *
 */
public class PaswordChek extends SimpleCredentialsMatcher {
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		UsernamePasswordToken tokenn = (UsernamePasswordToken) token;  
		 String pwd = String.valueOf(tokenn.getPassword());
        Object tokenCredentials = SecurUtils.md5(pwd);  
        Object accountCredentials = getCredentials(info);  
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
        return equals(tokenCredentials, accountCredentials);  
	}
}
