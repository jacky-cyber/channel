<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>渠道管理系统-终端秘钥</title>
<link href="resources/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="resources/css/security.css" rel="stylesheet">

<script src="resources/js/jquery.dataTables.js"></script>
<script src="resources/js/dataTables.bootstrap.js"></script>
<script src="resources/js/security.js"></script>
</head>
<body>
	<div class="page-header">
		<a href="javascript:void(0)" class="btn btn-default" id="add_btn">添 加</a> 
		&nbsp;
		<a href="javascript:void(0)" class="btn btn-primary" id="update_btn">修 改</a>
		&nbsp; 
		<a href="javascript:void(0)" class="btn btn-danger" id="delete_btn">删 除</a>
	</div>

	<!-- -----begin data table------ -->
	<table id="dataTable" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th nowrap>编号</th>
				<th nowrap>终端号</th>
				<th nowrap>秘钥</th>
				<th nowrap>使用者</th>
				<th nowrap>更新时间</th>
				<th nowrap>备注</th>
			</tr>
		</thead>
		<!-- <tfoot>
			<tr>
				<th>编号</th>
				<th>终端号</th>
				<th>秘钥</th>
				<th>更新时间</th>
				<th>备注</th>
			</tr>
		</tfoot> -->
	</table>
	<!-- -----end data table------ -->
	
	 <!-- begin modal window -->
	<div class="modal fade" id="_modal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	            	<h3 class="modal-title" id="_modeal_title"></h3>
	            </div>
	            <div class="modal-body">
	                <form id="whitelists_form">
					  <div class="form-group">
					    <label for="appno">终端号</label>
					    <input type="text" class="form-control" id="appno"  autocomplete="off" placeholder="请输入终端号" >
					  </div>
					  <div class="form-group">
					    <label for="key">秘钥</label>
					    <input type="text" class="form-control" id="key"  autocomplete="off" placeholder="请输入秘钥" >
					  </div>					  
					  <div class="form-group">
					    <label for="name">使用者</label>
					    <input type="text" class="form-control" id="name"  autocomplete="off" placeholder="请输入使用者" >
					  </div>					  
					  <div class="form-group">
					    <label for="remark">备注</label>
					    <input type="text" class="form-control" id="remark" placeholder="请输入备注">
					  </div>
					</form>
	            </div>
	            <div class="modal-footer">
	            	<input type="hidden" id="operateType" name="operateType" value=""/>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
	                <button type="button" class="btn btn-primary" id="save_btn">保 存</button>
	        </div>
	    </div>
	  </div>
	</div>
	<!-- end modal window -->
</body>
</html>
