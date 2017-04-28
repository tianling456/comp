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
<base href="<%=basePath%>">
<link href="<%=basePath%>styles/bootstrap/bootstrap.css"
	rel="stylesheet">
<link href="<%=basePath%>styles/css/login.css" rel="stylesheet">
<script src="<%=basePath%>javascript/jquery/jquery.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap/bootstrap.min.js"></script>
<script src="<%=basePath%>javascript/user/login.js"></script>
<title>登录</title>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<!-- 字符编码 -->
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<form action="<%=basePath%>user/login.com" method="post">
					用户：<input type="text" name="username"></input>
					</br>
					密码：<input type="password" name="password"></input>
					</br>
					验证码：<input type="text" name="kaptchaReceived"></input>
					</br>
					<input type="submit" value="登录"></input>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
