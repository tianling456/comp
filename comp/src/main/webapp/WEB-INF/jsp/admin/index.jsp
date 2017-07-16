<!doctype html>
<html lang="en"><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<!-- IE兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 字符编码 -->
<meta charset="UTF-8">
<!-- css start -->
<base href="<%=basePath%>">
<link rel="icon" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="SHORTCUT ICON" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="icon" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<link rel="SHORTCUT ICON" href="<%=basePath%>images/favicon.ico"
	mce_href="<%=basePath%>images/favicon.ico" type="image/x-icon">
<script src="<%=basePath%>jquery/jquery-3.2.1.js"></script>
<link href="<%=basePath%>bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
<link href="<%=basePath%>styles/css/user/index.css" rel="stylesheet">
<!-- script  end -->
<title>百度一下，你就知道</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-1 col-sm-3">
				<div class="cell">
					<ul class="v-menu subdown block-shadow-impact min-size-required">
						<li><a href="#"><img
								src="<%=basePath%>images/metro/images/home.png">游戏</a></li>
						<li class="divider"></li>
						<li class="active"><a href="#"><img
								src="<%=basePath%>images/metro/images/user.png">游戏</a></li>
						<li><a href="#">Люди</a></li>
						<li><a href="#"><img
								src="<%=basePath%>images/metro/images/photo_album.png">
								Фото</a></li>
						<li class="divider"></li>
						<li><a href="#" class="dropdown-toggle"><img
								src="<%=basePath%>images/metro/images/location.png">
								Локации</a>
							<ul class="d-menu" data-role="dropdown">
								<li><a href="#">游戏</a></li>
								<li><a href="#">游戏</a></li>
								<li>
									<div class="item-block text-center">
										<button class="square-button big-button">
											<img class="icon"
												src="<%=basePath%>images/metro/images/round.png">
										</button>
										<button class="square-button big-button">
											<img class="icon"
												src="<%=basePath%>images/metro/images/location.png">
										</button>
										<button class="square-button big-button">
											<img class="icon"
												src="<%=basePath%>images/metro/images/group.png">
										</button>
									</div>
								</li>
								<li><a href="#" class="dropdown-toggle">游戏</a>
									<ul class="d-menu" data-role="dropdown">
										<li><a href="#">游戏</a></li>
										<li><a href="#">游戏</a></li>
										<li>
											<div class="item-block text-center bg-grayLighter">
												<button class="square-button">
													<img class="icon"
														src="<%=basePath%>images/metro/images/round.png">
												</button>
												<button class="square-button">
													<img class="icon"
														src="<%=basePath%>images/metro/images/location.png">
												</button>
												<button class="square-button">
													<img class="icon"
														src="<%=basePath%>images/metro/images/group.png">
												</button>
												<button class="square-button">
													<img class="icon"
														src="<%=basePath%>images/metro/images/power.png">
												</button>
											</div>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li><a href="#"><img
								src="<%=basePath%>images/metro/images/group.png">
								游戏</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
