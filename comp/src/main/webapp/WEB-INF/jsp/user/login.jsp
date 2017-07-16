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
<link rel="icon" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="SHORTCUT ICON" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<script src="<%=basePath%>jquery/jquery.js"></script>
<link href="<%=basePath%>bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath%>styles/css/user/login.css" rel="stylesheet">
<link href="<%=basePath%>alert/css/alert.css" rel="stylesheet">
<script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
<script src="<%=basePath%>bootstrap/js/modal.js"></script>
<script src="<%=basePath%>javascript/user/login.js"></script>
<script src="<%=basePath%>javascript/alert.js"></script>

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
</head>
<body>
	<div class="login-form">
		<div class="login-code">二维码登录</div>
		<div class="login-user">账号登录</div>
		<div class="login-input">
			生成的验证码：<img id="changeCaptcha" src="<%=basePath%>captcha/captchaimage.com"> <a href="javascript:changeCaptcha()">看不清，换一张</a>
			<br>
			<br>
			请输入验证码：<input id="captchaCode" type="text"> 
			<input type="button" value="提交验证" onclick="checkCaptcha()">
		</div>
	</div>
</body>

<script type="text/javascript">
//获取验证码图片 
function changeCaptcha(){
    $("#changeCaptcha").attr("src","<%=basePath%>captcha/captchaimage.com?"+Math.floor(Math.random()*100));
}
//验证输入的验证码 
function checkCaptcha(){
    var captchaCode = $("#captchaCode").val();
    $.ajax({
        type:'post',
        dataType:'json',
        async : true,
        url:'<%=basePath%>captcha/checkCaptchaCode.com',
        data:{"captchaCode" : captchaCode},
        success:function(result){
            alert(result.message);
        }
    });
}
</script>
</html>
