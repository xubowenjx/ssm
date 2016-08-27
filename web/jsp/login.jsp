<%--
  Created by IntelliJ IDEA.
  User: xubowen
  Date: 2016/6/25
  Time: 20:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
<link href="<%=request.getContextPath()%>/dist/css/bootstrap.css"
	rel="stylesheet">
<style type="text/css">
input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px white inset;
	border: 1px solid #CCC !important;
}
input:FOCUS {
	background-color: #fff;
}
 form>div.row{
 text-align: center;
 }
.form-group label{
	display: inline-block;
	padding:0;
	margin:0;
	width: 24%;
}
.form-group .form-control{
	display: inline-block;
	width: 74%;
}
</style>
</head>
<body class="container">
	<div class="row">
		<form action="<%=request.getContextPath()%>/logon/login"  method="post" autocomplete="off">
			<div class="row">
			<div class="form-group col-md-4" >
				<label> name: </label>
				<input type="text" name="username"
					class="form-control"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"/>
			</div>
			</div>
			<div class="row">
			<div class="form-group col-md-4">
				<label> password: </label>
				<input type="password" name="password"
					class="form-control" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"/> 
			</div>
			</div>
			<div class="row">
			<div class="form-group col-md-4">
				<input type="submit" value="login" class="btn btn-success pull-right">
			</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
window.onload=function(){
	var msg = '${message_login}';
	if(msg){
		alert(msg);
	}
}
</script>
</html>
