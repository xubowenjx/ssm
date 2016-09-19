package com.xbw.spring.webservice.impl; 

import javax.jws.WebService;

import com.xbw.spring.webservice.HelloWorld;
@WebService
public class HelloWorldImpl  implements HelloWorld{

	public void sayHi(String text) {
		System.out.println("hellow,"+text);
	}
}

    