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
<script src="<%=basePath%>javascript/jquery/jquery.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap/bootstrap.min.js"></script>
<title>用户注册</title>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<!-- 字符编码 -->
<meta charset="UTF-8">
</head>
<body>
	<form class="form-horizontal" role="form">
		<fieldset>
			<legend>配置数据源</legend>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="ds_host">主机名</label>
				<div class="col-sm-4">
					<input class="form-control" id="ds_host" type="text"
						placeholder="192.168.1.161" />
				</div>
				<label class="col-sm-2 control-label" for="ds_name">数据库名</label>
				<div class="col-sm-4">
					<input class="form-control" id="ds_name" type="text"
						placeholder="msh" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="ds_username">用户名</label>
				<div class="col-sm-4">
					<input class="form-control" id="ds_username" type="text"
						placeholder="root" />
				</div>
				<label class="col-sm-2 control-label" for="ds_password">密码</label>
				<div class="col-sm-4">
					<input class="form-control" id="ds_password" type="password"
						placeholder="123456" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>选择相关表</legend>
			<div class="form-group">
				<label for="disabledSelect" class="col-sm-2 control-label">表名</label>
				<div class="col-sm-10">
					<select id="disabledSelect" class="form-control">
						<option>禁止选择</option>
						<option>禁止选择</option>
					</select>
				</div>
			</div>
		</fieldset>

		<fieldset>
			<legend>字段名</legend>
			<div class="form-group">
				<label for="disabledSelect" class="col-sm-2 control-label">表名</label>
				<div class="col-sm-10">
					<select id="disabledSelect" class="form-control">
						<option>禁止选择</option>
						<option>禁止选择</option>
					</select>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>