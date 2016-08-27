## 1.搭建环境
*	`new Module` .
*	选择spring，勾选上`spring MVC`,这样会自动下载需要的包
*	点击`next`,给你的项目取个名字
*	然后自动生成了一个项目结构，WEB-INF下面也生成了ApplicationContext.xml和dispacher-servlet.xml。建好自己的包。
*	进入到web.xml 修改`DispatcherServlet`的&lt;url-pattern&gt;为`/`，此时一个基本的环境搭建好了。但是还不能看到效果
![工程目录](./images/work-dir.png)

## 2.基本配置
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
 	

*	第一个controller

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

*   tomcat部署后访问 [tomcat配置]()

![界面](./images/result-1.png)
