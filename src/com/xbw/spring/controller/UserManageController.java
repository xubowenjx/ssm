package com.xbw.spring.controller; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xbw.spring.model.shiro.SUser;
import com.xbw.spring.service.UserService;

@Controller
@RequestMapping("/usermanag")
public class UserManageController {
	private static final String BASEDIR="usermanage/";
	@Autowired
	private UserService userService;
	@RequestMapping("/user")
	public String initUser(Model m){
		List<SUser> listuser = userService.getUserList();
		m.addAttribute("user", listuser);
		return BASEDIR+"users";
	}
}

    