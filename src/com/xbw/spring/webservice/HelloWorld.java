package com.xbw.spring.webservice; 

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWorld {
	public void sayHi(@WebParam(name="text") String text);
}

    