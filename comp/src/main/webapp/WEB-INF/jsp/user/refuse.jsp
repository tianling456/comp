<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath%>styles/bootstrap/bootstrap.css" rel="stylesheet">
<script src="<%=basePath%>javascript/jquery/jquery.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap/bootstrap.min.js"></script>
<title>refuse</title>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<!-- 字符编码 -->
<meta charset="UTF-8">
</head>
<body>
	<p>登录失败的页面</p>
</body>
</html>
