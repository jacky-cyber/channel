<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>渠道管理系统-主页</title>
<link href="resources/css/home.css" rel="stylesheet"/>
<script src="resources/js/home.js"></script>
</head>
<body>
<div class="page-header">
	<a href="javascript:void(0)" class="btn btn-primary btn-lg" id="publish_btn">发 布</a>
	&nbsp;&nbsp;
	<a href="javascript:void(0)" class="btn btn-danger btn-lg" id="sync_btn">同 步</a>
 </div>

<div class="alert alert-dismissible alert-info home-alert" >
    <button type="button" class="close" data-dismiss="alert">×</button>
    <p></p>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">渠道系统配置信息</h3>
  </div>
  <div class="panel-body">
    <div class="tree" id="channel_config_tree"></div>
  </div>
</div>
</body>
</html>