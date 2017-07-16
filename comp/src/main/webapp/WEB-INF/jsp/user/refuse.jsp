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
<link rel="icon" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="SHORTCUT ICON" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link href="<%=basePath%>bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="<%=basePath%>jquery/jquery-3.2.1.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
<title>登录</title>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Bootstrap是Twitter推出的一个用于前端开发的开源工具包。它由Twitter的设计师Mark Otto和Jacob Thornton合作开发，是一个CSS/HTML框架。目前，Bootstrap最新版本为3.0 。Bootstrap中文网致力于为广大国内开发者提供详尽的中文文档、代码实例等，助力开发者掌握并使用这一框架。">
<meta name="keywords" content="Bootstrap,CSS,CSS框架,CSS framework,javascript,bootcss,bootstrap开发,bootstrap代码,bootstrap入门">
<meta name="author" content="Bootstrap中文网">
<meta name="robots" content="index,follow">
<!-- 字符编码 -->
<meta charset="UTF-8">
<!-- 字符编码 -->
<meta charset="UTF-8">
</head>
<body>
	<p>登录失败的页面</p>
</body>
</html>
