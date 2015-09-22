<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<title><decorator:title default="渠道管理系统" /></title>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="channel manager">
<meta name="author" content="com.zjht.channel">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<LINK href="favicon.ico" type="image/x-icon" rel="icon">
<LINK href="favicon.ico" type="image/x-icon" rel="shortcut icon">

<link href="resources/css/themes/${session_theme}/bootstrap.css" rel="stylesheet" />
<link href="resources/css/bootstrap-select.css" rel="stylesheet" />
<link href="resources/css/sweet-alert.css" rel="stylesheet">
<link href="resources/css/dataTables.bootstrap.css" rel="stylesheet">

<!-- loading custom css -->
<link href="resources/css/custom.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/lock.css" rel="stylesheet">
<link href="resources/css/basic.css" rel="stylesheet">

<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/bootstrap-select.js"></script>
<script src="resources/js/sweet-alert.js"></script>
<script src="resources/js/jquery.dataTables.js"></script>
<script src="resources/js/dataTables.bootstrap.js"></script>

<!-- custom js -->
<script src="resources/js/lock.js"></script>
<script src="resources/js/basic.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var uri = '<%=request.getRequestURI()%>';
	
	$("#menu li").each(function (){
		$(this).removeClass("active");
	});
	
	$("#menu a").each(function (){
		if(uri==("<%=request.getContextPath()%>/"+$(this).attr("href")))
		{
			$(this).parent("li").eq(0).addClass("active");
		}
	});
})
</script>
<decorator:head />
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="home" class="navbar-brand">渠道管理系统</a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav" id="menu">
					<li><a href="whitelists">白名单</a></li>
					<li><a href="security">终端秘钥</a></li>
					<li><a href="permission">权限管理</a></li>
					<li><a href="service">服务管理</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" id="themes">欢迎您，${session_user}<span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<!-- <li class="divider"></li> -->
							<li><a href="logout">登出</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" id="themes">主题（${session_theme}）<span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="theme/cerulean">cerulean</a></li>
							<li><a href="theme/cosmo">cosmo</a></li>
					<!-- 		<li><a href="theme/cyborg">cyborg</a></li>
							<li><a href="theme/darkly">darkly</a></li> -->
							<li><a href="theme/default">default</a></li>
							<!-- <li><a href="theme/flatly">flatly</a></li> -->
							<li><a href="theme/journal">journal</a></li>
							<li><a href="theme/lumen">lumen</a></li>
							<li><a href="theme/paper">paper</a></li>
							<li><a href="theme/readable">readable</a></li>
							<!-- <li><a href="theme/sandstone">sandstone</a></li> -->
							<li><a href="theme/simplex">simplex</a></li>
							<li><a href="theme/slate">slate</a></li>
							<li><a href="theme/spacelab">spacelab</a></li>
							<li><a href="theme/superhero">superhero</a></li>
							<li><a href="theme/united">united</a></li>
							<li><a href="theme/yeti">yeti</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container content">
		<decorator:body />
	</div>
	<footer>Copyright©2015 中经汇通信息技术中心</footer>
</body>
</html>