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
<title>渠道管理系统-权限管理</title>
<script src="resources/js/permission.js"></script>
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
				<th nowrap>服务名称</th>
				<th nowrap>服务版本</th>
				<th nowrap>更新时间</th>
				<th nowrap>备注</th>
			</tr>
		</thead>
		<!-- <tfoot>
			<tr>
				<th width="30px">序号</th>
				<th nowrap>编号</th>
				<th nowrap>终端号</th>
				<th nowrap>服务名称</th>
				<th nowrap>服务版本</th>
				<th nowrap>更新时间</th>
				<th nowrap>备注</th>
			</tr>
		</tfoot> -->
	</table>
	<!-- -----end data table------ -->
	
	 <!-- begin modal window -->
	<div class="modal fade" id="_modal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	            	<h4 class="modal-title"></h4>
	            </div>
	            <div class="modal-body">
	                <form class="form-horizontal">
	                <fieldset>
	                	<legend id="_modeal_title"></legend>
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="appno">终端号</label>
						    <div class="col-lg-10">
						    	<select class="form-control selectpicker" id="appno" name = "appno"  data-live-search="true">
						    		<option value=''>请选择终端号</option>
						    	</select>
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="service_name">服务名称</label>
						    <div class="col-lg-10">
						    	<select class="form-control selectpicker" id="service_name" name = "service_name"  data-live-search="true">
						    		<option value=''>请选择服务名称</option>
						    	</select>
						    </div>
						  </div>					  
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="service_version">服务版本</label>
						    <div class="col-lg-10">
 							    <select class="form-control selectpicker" id="service_version" name = "service_version"  data-live-search="true">
						    		<option value=''>请选择服务版本</option>
						    	</select>
						    </div>
						  </div>					  
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="remark">备注</label>
						    <div class="col-lg-10">
						    	<input type="text" class="form-control" id="remark" placeholder="请输入备注">
						    </div>
						  </div>
						</fieldset>
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
