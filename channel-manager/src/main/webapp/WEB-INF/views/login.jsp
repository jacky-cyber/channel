<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>渠道管理系统-登录</title>

<LINK href="favicon.ico" type="image/x-icon" rel="icon">
<LINK href="favicon.ico" type="image/x-icon" rel="shortcut icon">

<link href="resources/css/themes/${session_theme}/bootstrap.css" rel="stylesheet" />
<link href="resources/css/custom.min.css" rel="stylesheet">
<!-- Loading custom css -->
<link href="resources/css/login.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<c:if test="${not empty error}">
			<div class="error_style">
				<div class="alert alert-dismissible alert-danger">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>${error.code()}:</strong> ${error.message()}
				</div>
			</div>
		</c:if>
		<div class="modal-dialog login-container">
			<div class="modal-content">
				<div class="panel-heading">
					<!-- <h3 class="panel-title"></h3> -->
				</div>
				<div class="panel-body">
					<form class="form-horizontal login" method="post">
						<fieldset>
							<legend>欢迎您，请登录</legend>
							<div class="form-group">
								<label for="username" class="col-lg-2 control-label">用户</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="username"
										name="username" placeholder="Username" value="channel">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-lg-2 control-label">密码</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="Password"  value="channel2">
									<div class="checkbox">
										<label> <input type="checkbox"> 记住我
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="reset" class="btn btn-default">取消</button>
									&nbsp;&nbsp;&nbsp;
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Load JS here for greater good -->
	<script src="resources/js/jquery-1.10.2.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/login.js"></script>
</body>
</html>
