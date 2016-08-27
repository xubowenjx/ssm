<%--
  Created by IntelliJ IDEA.
  User: xubowen
  Date: 2016/6/25
  Time: 20:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="check">
            <label>
                name:<input type="text" name="name"/>
            </label>
            <label>
                password:<input type="password" name="password"/>${msg}
            </label>
            <div><input type="submit" value="login"></div>
        </form>
    </div>
</div>
</body>
</html>
