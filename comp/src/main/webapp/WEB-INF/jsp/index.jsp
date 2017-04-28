<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=1">
<!-- 字符编码 -->
<meta charset="UTF-8">
<base href="<%=basePath%>">
<link rel="icon" href="<%=basePath%>images/favicon.ico" mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="SHORTCUT ICON" href="<%=basePath%>images/favicon.ico" mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<script src="<%=basePath%>javascript/jquery/jquery.min.js"></script>
<link href="<%=basePath%>styles/bootstrap/bootstrap.css" rel="stylesheet">
<script src="<%=basePath%>javascript/bootstrap/bootstrap.min.js"></script>
<link href="<%=basePath%>styles/css/index.css" rel="stylesheet">
<title>首页</title>
</head>
<body>
<div class="index-nav">
	<div class="container-fluid">
	 	<nav class="navbar navbar-default navbar-fixed-top">
	 		 <div class="container">
    	     	<div class="navbar-header">
    	     		 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				      <a class="navbar-brand" href="#">Brand</a>
				    </div>
				
				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				        <!-- <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li> -->
				        <li><a href="#">Link</a></li>
				        <li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
				          <ul class="dropdown-menu">
				            <li><a href="<%=basePath%>/user/info.com">Action</a></li>
				            <li><a href="#">Another action</a></li>
				            <li><a href="#">Something else here</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Separated link</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">One more separated link</a></li>
				          </ul>
				        </li>
				      </ul>
				      <ul class="nav navbar-nav navbar-right">
				        <li><a href="#">登录</a></li>
				        <li><a href="#">注册</a></li>
				        <li><a href="#">退出</a></li>
				      </ul>
    	     	</div>
    	     </div>
	 	</nav>
	</div>
</div>
<!-- <div class="content"> -->
	<div class="container">
		<div class="row"></div>
	</div>
</body>
</html>
