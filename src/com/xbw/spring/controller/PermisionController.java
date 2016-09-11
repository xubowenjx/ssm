package com.xbw.spring.controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perm")
public class PermisionController {
	public String initPerm(){
		
		return "perm";
	}
}

    