<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	/*
	com.zjht.model.User user = (com.zjht.model.User)session.getAttribute(com.zjht.constant.SessionConstant.SESSION_USER);
	String username = user==null?"":user.getName();
	*/
	String username = "admin";
%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<title><decorator:title default="渠道管理系统" /></title>

<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<LINK href="favicon.ico" type="image/x-icon" rel="icon">
<LINK href="favicon.ico" type="image/x-icon" rel="shortcut icon">

<!--Loading BOOTSTRAP MAIN STYLES -->
<link href="resources/css/bootstrap.css" rel="stylesheet" />
<!--Loading FONTAWESOME MAIN STYLE -->
<link href="resources/css/font-awesome.min.css" rel="stylesheet" />
<!--Loading CUSTOM STYLE -->
<link href="resources/css/style.css" rel="stylesheet" />

<!-- Loading custom css -->
<link href="resources/css/common.css" rel="stylesheet">
<decorator:head />
</head>
<body>
	<div class="container">
		<!-- NAV SECTION START-->
		<div class="navbar navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">渠道管理系统</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">白名单</a></li>
						<li><a href="#">终端秘钥</a></li>
						<li><a href="#">服务权限</a></li>
						<li><a href="#">服务管理</a></li>
					</ul>
				</div>

			</div>
		</div>
		<!--NAV SECTION END -->

		<decorator:body />
		<footer>
			Copyright©2015 中经汇通信息技术中心 渠道管理系统 version 0.1
		</footer>
	</div>
	<!-- Load JS here for greater good -->
	<script src="resources/js/jquery.js"></script>
	<!-- CORE BOOTSTRAP LIBRARY -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>