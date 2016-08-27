## 1.搭建环境
*	新建一个动态web项目
*	导入spring4的jar包
* web-inf下新建dispacher-servlet.xml
* web-inf下新建applicationContext.xml
* 修改web.xml


## 2.配置dispacher-servlet.xml
*	dispacher-servlet.xml是用来配置Spring本身的一些运行设置。
 	```xml
 	
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
  xmlns:mvc="http://www.springframework.org/schema/mvc"
  xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">
  <!-- 设置根路径访问 -->
  <mvc:view-controller path="/" view-name="forward:/jsp/login.jsp" />
  <!-- 开启注解 -->
  <mvc:annotation-driven />
  <!-- 配置静态资源访问 -->
  <mvc:resources mapping="/dist/**" location="/dist/" />
  <!--配置视图 -->
  <bean
    class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/jsp/" />
    <property name="suffix" value=".jsp" />
  </bean>
  <!--文件上传配置 -->
  <bean id="multipartResolver"
    class="org.springframework.web.multipart.commons.CommonsMultipartResolver" />
  <!--注解扫描的基包 -->
  <context:component-scan base-package="com.xbw.spring" />
</beans>

 	```

## 3.修改web.xml
  
  ```xml
  <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
     <!--
     加载dispatcher-servlet.会自动在WEB-INF下寻找{servlet-name}-servlet.xml
        -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
  ```

##	第一个controller

	```java
@Controller
@RequestMapping("/logon")
public class LoginControll {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name","xubowen");
        return "index";
     }
}
```


