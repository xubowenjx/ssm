   Spring配置mybatis
-
1.  添加mybaitis相关jar包
    >mybats-3.*.jar  mybatis-spring.*.jar,mysql驱动包

2.  添加一个dataSource.properties
    ```
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc\:mysql\://localhost\:3306/xbw1?useUnicode\=true&characterEncoding\=UTF8
    jdbc.username=root
    jdbc.password=123
    jdbc.maxActive=10
    jdbc.maxIdle=0
    ```
3.  添加一个dataSource.xml
    >在WEB-INF下面建一个applications文件夹,专门来放应用的配置

    ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:tx="http://www.springframework.org/schema/tx"
               xmlns:context="http://www.springframework.org/schema/context"
               xsi:schemaLocation="
               http://www.springframework.org/schema/beans 
               http://www.springframework.org/schema/beans/spring-beans.xsd
               http://www.springframework.org/schema/tx 
               http://www.springframework.org/schema/tx/spring-tx.xsd
               http://www.springframework.org/schema/context 
               http://www.springframework.org/schema/context/spring-context.xsd">
            <!--加载数据源配置文件-->
            <context:property-placeholder location="classpath:dataSource.properties"
            ignore-unresolvable="true"/>
            <!--mybatis 数据源-->
            <bean id="dataSource" class="org.apache.ibatis.datasource.pooled.PooledDataSource"
               destroy-method="forceCloseAll">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
                <property name="poolMaximumIdleConnections" value="${jdbc.maxIdle}"/>
                <property name="poolMaximumActiveConnections" value="${jdbc.maxActive}"/>
            </bean>
            <!-- 事务管理, 全局使用JtaTransactionManager -->
            <bean id="transactionManager"
                  class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                <property name="dataSource" ref="dataSource" />
            </bean>
            <!-- 配置数据源实体映射关系 -->
            <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
                <property name="dataSource" ref="dataSource" />
                <property name="typeAliasesPackage" value="com.xbw.spring.model" />
            </bean>
            <!-- 配置dao层mapper映射 -->
            <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
                <property name="basePackage" value="com.xbw.spring.mapper" />
            </bean>
            <!--允许注解开启事务-->
            <tx:annotation-driven/>
        </beans>
    ```
    在applicationContext.xml中加入这个配置
    ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context 
            http://www.springframework.org/schema/context/spring-context.xsd">
         <!-- 启用 autowire -->
         <context:annotation-config/>
         <!--应用扫描的基包位置-->
         <context:component-scan base-package="com.xbw.spring"/>
         <!--加载数据源配置xml-->
         <import resource="WEB-INF/applications/dataSource.xml"/>
     </beans>
    ```
4.   写一个简单的登录测试

    UserDao.java
    ```java
    public interface UserDao {
          String checkUser(@Param("name") String name);
     }
    ```
    UserDao.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
    <mapper namespace="com.xbw.spring.mapper.UserDao">
        <resultMap type="com.xbw.spring.model.UserModel" id="User">
            <id column="user_id" property="userId" jdbcType="INTEGER"/>
            <result column="user_name" property="name" jdbcType="VARCHAR"/>
            <result column="password" property="password" jdbcType="VARCHAR"/>
        </resultMap>
        <select id="checkUser" resultType="java.lang.String" parameterType="java.lang.String">
            SELECT password FROM pf_account where login_name=#{name}
        </select>
    </mapper>
    ```
    UserService和Dao一样
    UserServiceImpl.java
    ```java
    @Service
    public class UserServiceImpl implements UserService {
        @Autowired
        private UserDao userDao;
        @Override
        public String checkUser(String name) {
            return userDao.checkUser(name);
        }
    }
    ```
    修改loginController.java
    ```java
    @Controller
    @RequestMapping("/login")
    public class LoginController {

        @Autowired
        private UserService userService;

        @RequestMapping("/check")
        public String index(UserModel userModel,Model model) {
            String name = userModel.getName();
            if(name!=null){
                String password =   userService.checkUser(userModel.getName());
                String message = "success";
                if(!userModel.getPassword().equals(password)){
                    message = "wrong password";
                }
                model.addAttribute("msg",message);
            }
            return "index";
        }
    }
    ```
    web/jsp/index.jsp

    ```html
    <form action="check">
        <label>
           name:<input type="text" name="name"/>
        </label>
        <label>
            password:<input type="password" name="password"/>${msg}
        </label>
        <div><input type="submit" value="login"></div>
    </form>
    ```


   之所以建一个applications文件夹和把数据源单独用一个xml来配置。
   就是为了以后再加入更多的application的时候保持配置的清晰.
   当然，其实可以划分的更细，只是主applicationContext.xml加载路径正确就ok.
   目前文档结构：
   ![dir-1](images/work-dir1.png)




