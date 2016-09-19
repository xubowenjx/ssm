Spring Shiro
-
1. Shiro介绍
    
    Shiro是apache的一套web身份验证和授权系统。

2. Spring 配置Shiro

    web.xml
    ```xml

    <filter>  
        <filter-name>shiroFilter</filter-name>  
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>  
        <init-param>  
            <param-name>targetFilterLifecycle</param-name>  
            <param-value>true</param-value>  
        </init-param>  
    </filter>  
    <filter-mapping>  
        <filter-name>shiroFilter</filter-name>  
        <!-- 这里必须是带* -->
        <url-pattern>/*</url-pattern>  
    </filter-mapping> 

    ```
3. 配置Shiro.xml
    
    ```xml

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd ">
    <!-- 自定义密码校验 -->
    <bean id="mycredentialsMatcher" class="com.xbw.spring.applications.shiro.PaswordChek" />
    <!-- 缓存管理 -->
    <bean id="shiroCacheManager" class="org.apache.shiro.cache.MemoryConstrainedCacheManager"></bean>

    <bean id="myRealm" class="com.xbw.spring.applications.shiro.SelfRealm">
        <!-- 自定义realm加入自定义密码校验 -->
        <property name="credentialsMatcher" ref="mycredentialsMatcher" />
    </bean>
    <!-- 这里主要是设置自定义的单Realm应用,若有多个Realm,可使用'realms'属性代替 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myRealm" />
        <property name="cacheManager" ref="shiroCacheManager" />
    </bean>
    <!-- 自定义访问权限校验 -->
    <bean id="myCaptchaFilter" class="com.xbw.spring.applications.shiro.RoleFilter" />
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <!-- Shiro的核心安全接口,这个属性是必须的 -->
        <property name="securityManager" ref="securityManager" />
        <!-- 要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面 -->
        <property name="loginUrl" value="/" />
        <!-- 对于未授权的请求跳转403界面 -->
        <property name="unauthorizedUrl" value="/jsp/errors/403.jsp" />
        <property name="filters">
            <map>
                <entry key="authc" value-ref="myCaptchaFilter" />
            </map>
        </property>
        <!-- anon 不校验 authc 映射自定义的校验规则 -->
        <property name="filterChainDefinitions">
            <value>
                <!--login开头的都不校验-->
                /logon/*=anon
                <!--统一使用自定义校验-->
                /*/*=authc
            </value>
        </property>

    </bean>
    </beans>
    ```
4.  自定义密码校验
    ```java

    public class PaswordChek extends SimpleCredentialsMatcher {
    
        @Override
        public boolean doCredentialsMatch(AuthenticationToken token,
            AuthenticationInfo info) {
        UsernamePasswordToken _token = (UsernamePasswordToken) token;  
         String pwd = String.valueOf(_token.getPassword());
        Object tokenCredentials = SecurUtils.md5(pwd);  
        Object accountCredentials = getCredentials(info);  
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
        return equals(tokenCredentials, accountCredentials);  
        }
    }

    ```
5. 自定义认证授权

    ```java

        public class SelfRealm extends AuthorizingRealm {
 
        @Autowired
        private UserService userService;
        @Autowired
        private RoleService roleService;
        @Autowired
        private PermService permService;
        protected final Logger log = Logger.getLogger(this.getClass());
    
        /*
         * 不在配置文件中写的话就在这写
         *  @PostConstruct  
            public void initCredentialsMatcher() {  
               setCredentialsMatcher(new PaswordChek());  
            }  */

        @Override
         /**
          * 用户认证成功后进行基本的授权
          */
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

             String currentUsername = (String)super.getAvailablePrincipal(principals);  
             SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
                if(null!=currentUsername){
                    SUser u = userService.checkUser(currentUsername);
                    boolean b ="A".equals(u.getUserType());
                    if(b){//管理员用户不用继续了
                        simpleAuthorInfo.addRole("admin");
                         return simpleAuthorInfo;  
                    }
                    
                    if(u!=null&&u.getUserId()!=null){
                        List<SRole> roles =  roleService.getSRolesByUserId(u.getUserId());
                        for(SRole r:roles){
                            Integer id = r.getId();
                            //添加角色
                            simpleAuthorInfo.addRole(id+"");
                             List<SPerm> perms = permService.getPermsByRole(id);
                             //添加权限项
                             for(SPerm p: perms){
                                 simpleAuthorInfo.addStringPermission(p.getPermisionUrl());
                             }
                        }
                        return simpleAuthorInfo;  
                    }
                }
            return null;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(
                AuthenticationToken authcToken) throws AuthenticationException {
            UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
            SUser u = userService.checkUser(token.getUsername());
            if(u!=null&&u.getUserId()!=null){
                 AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(u.getUserName(), u.getPassword(), this.getName());  
                 this.setSession("currentUser", u);  
                 return authcInfo;  
            }
            return null;  
        }  
          
          
        /** 
         * 将一些数据放到ShiroSession中,以便于其它地方使用 
         * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
         */  
        private void setSession(Object key, Object value){  
            Subject currentUser = SecurityUtils.getSubject();  
            if(null != currentUser){  
                Session session = currentUser.getSession();  
                System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
                if(null != session){  
                    session.setAttribute(key, value);  
                }  
            }  
        }  

        }

    ```

6.自定义权限认证


    ```java
        public class RoleFilter  extends AuthorizationFilter {  
        protected final Logger log = Logger.getLogger(this.getClass());
        public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
                throws IOException {  
            HttpServletRequest req= (HttpServletRequest) request;
            String path =req.getRequestURI();
            String   re =req.getContextPath();
            path =  path.substring(re.length());
            Subject subject=null;
            try {
                subject = getSubject(request, response);
            } catch (Exception e) {
                return false;
            }  
            boolean pass=false;
            if(subject==null){
                pass= false;
            }
            if(subject.hasRole("admin")){
                pass= true;
            }
            pass = pass?pass:subject.isPermitted(path);
            SUser u = (SUser)subject.getSession().getAttribute("currentUser");
            if(u==null){
                return false;
            } 
             log.info(path+":当前用户"+u.getUserName()+" has power "+pass);
           return pass;
        }  
    }

    ```
