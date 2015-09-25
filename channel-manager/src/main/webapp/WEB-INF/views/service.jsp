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
<title>渠道管理系统-服务管理</title>
<script src="resources/js/service.js"></script>
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
				<th nowrap>服务名称</th>
				<th nowrap>服务版本</th>
				<th nowrap>服务接口全限定名</th>
				<th nowrap>Bean Id</th>
				<th nowrap>负责人</th>
				<th nowrap>组织名称</th>
				<th nowrap>状态</th>
				<th nowrap>更新时间</th>
				<th nowrap>备注</th>
			</tr>
		</thead>
		<!-- <tfoot>
			<tr>
				<th width="30px">序号</th>
				<th nowrap>编号</th>
				<th nowrap>服务名称</th>
				<th nowrap>服务版本</th>
				<th nowrap>服务接口全限定名</th>
				<th nowrap>Bean Id</th>
				<th nowrap>负责人</th>
				<th nowrap>组织名称</th>
				<th nowrap>状态</th>
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
	            	<h3 class="modal-title" id="_modeal_title2"></h3>
	            </div>
	            <div class="modal-body">
	            	<form class="form-horizontal">
		                <fieldset>
		                	<legend id="_modeal_title"></legend>
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="name">服务名称</label>
						    <div class="col-lg-4">
						    	<input type="text" class="form-control" id="name"  autocomplete="off" placeholder="请输入服务名称" >
						    </div>
						    
						     <label class="col-lg-2 control-label" for="version">服务版本</label>
						    <div class="col-lg-4">
						    	<input type="text" class="form-control" id="version"  autocomplete="off" placeholder="请输入服务版本" >
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="fully_qualified_name">全限定名</label>
						    <div class="col-lg-10">
						    	<input type="text" class="form-control" id="fully_qualified_name"  autocomplete="off" placeholder="请输入服务接口全限定名" >
						    </div>
						  </div>					  
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="bean_id">Bean Id</label>
						    <div class="col-lg-10">
						    	<input type="text" class="form-control" id="bean_id"  autocomplete="off" placeholder="请输入Bean Id" >
						    </div>
						  </div>					  
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="owner">负责人</label>
						    <div class="col-lg-4">
						    	<input type="text" class="form-control" id="owner"  autocomplete="off" placeholder="请输入负责人" >
						    </div>
						    
					      	<label class="col-lg-2 control-label" for="organization">组织名称</label>
						    <div class="col-lg-4">
						    	<input type="text" class="form-control" id="organization"  autocomplete="off" placeholder="请输入组织名称" >
						    </div>
						  </div>					  
						  <div class="form-group">
						    <label class="col-lg-2 control-label" for="status">服务状态</label>
			                    <div class="col-lg-10">
			                      <div class="radio">
			                        <label>
			                          <input name="status" value="01" type="radio">
			                          启用
			                        </label>
			                      </div>
			                      <div class="radio">
			                        <label>
			                          <input name="status" value="00" type="radio">
			                          停用
			                        </label>
			                      </div>
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
