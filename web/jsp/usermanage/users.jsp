<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.xubowen.com/taglib/xbw" prefix="x"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/dist/css/bootstrap.css"
	rel="stylesheet">
</head>
<body class="contailer-fluid">
	<div class="row">
	<div class="col-md-3"></div>
		<div class="col-md-6">
			<x:table data="user" className="table table-striped ">
				<x:column lable="用户ID" name="userId"></x:column>
				<x:column lable="用户名字" name="userName"></x:column>
				<x:column lable="用户类型" name="userType"></x:column>
			</x:table>
		</div>
		<div class="col-md-3"></div>
	</div>
	
</body>
</html>