# CXF webserice

## 1.[下载jar包](http://cxf.apache.org/download.html)
	
	下载下来后,复制到Lib目录下。除去和已有重复的包。

## 2.配置web.xml
```xml
	<servlet>
        <servlet-name>CXFService</servlet-name>
        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>CXFService</servlet-name>
        <url-pattern>/webservice/*</url-pattern>
    </servlet-mapping>  
```

##  3.新建webserver.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jaxws="http://cxf.apache.org/jaxws"
	xsi:schemaLocation="
       http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd
       http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd">
	<!-- 引入CXF -->
	<import resource="classpath:META-INF/cxf/cxf.xml" />
	<bean id="helloWorldImpl" class="com.xbw.spring.webservice.impl.HelloWorldImpl" />
	<!-- 2:通过jaxws:server方式来配置webservice -->
	<jaxws:server serviceClass="com.xbw.spring.webservice.HelloWorld"
		address="/helloworld">
		<jaxws:serviceBean>
			<ref bean="helloWorldImpl" />
		</jaxws:serviceBean>
	</jaxws:server>
</beans>

```

## 4.applicationContext.xml引入上述配置
```xml
 <!-- 引入CXF -->
 <import resource="applications/webserver.xml" />
```

## 测试代码
	
```java
package com.xbw.spring.webservice; 

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWorld {
	public void sayHi(@WebParam(name="text") String text);
}

```
实现类

```java
package com.xbw.spring.webservice.impl; 

import javax.jws.WebService;

import com.xbw.spring.webservice.HelloWorld;
@WebService
public class HelloWorldImpl  implements HelloWorld{

	public void sayHi(String text) {
		System.out.println("hellow,"+text);
	}
}
```
## 修改shiro的认证配置
```xml
<!-- anon 不校验 authc 映射自定义的校验规则 -->
<property name="filterChainDefinitions">
	<value>
		/logon/*=anon
		/webservice/*=anon
		/*/*=authc
	</value>
</property>
```

## 浏览器访问
项目地址/webservice访问路径/服务路径
如：localhost:8080/ssm/webservice/helloworld
就是之前配置的这个服务地址
```xml
<jaxws:server serviceClass="com.xbw.spring.webservice.HelloWorld"
		address="/helloworld">
```