var current_record;//记录当前选择的记录
$(document).ready(function() {
	// *********************start dataTable********************* 
	$('#dataTable').dataTable({
		"oLanguage" : {
			"sUrl" : "resources/datatables.zh_CN.json"
		},
		"ajax" : {
			"url" : "permission/all",
			"type" : "GET",
			"dataSrc":"data"
		},
		"columns": [
		            { "data": "id","defaultContent": "","visible": false},
		            { "data": "appno","defaultContent": "","visible": true },
		            { "data": "serviceName","defaultContent": "","visible": true },
		            { "data": "serviceVersion","defaultContent": "","visible": true },
		            { "data": "updateTime","defaultContent": "","visible": true },
		            { "data": "remark","defaultContent": "","visible": true },
		          ],
		"createdRow": function ( row, data, index ) {
			// 0表示第一列,以此类推
			//第4列日期显示格式
			// Date().pattern()函数来自于#resources/js/basic.js
			$('td', row).eq(3).text(new Date(data.updateTime).format("yyyy-MM-dd HH:mm:ss"));
		},
		"initComplete" : function(settings, json) {
			// attention：
			// 更改datatable中的select需要在表格初始化完后后执行，否则select不会显示
			// 同时在jquery.dataTable.js的line3470 增加如下一行代码：
			// select.addClass('selectpicker');
			$('.selectpicker').selectpicker();
		} 
	});// end DataTable
	
	$.ajax({// stat initializing appno select
	    type: "GET",
	    dataType: "json",
	    url: "security/all",
	    success: function(data){
	    	if("00000"===data.code){
	    		$(data.data).each(function(){
		    		$("#appno").append("<option value='"+this.appno+"'>"+this.appno+"</option>");
				});
	    	}else{
	    		swal("Oh no!",data.code+":"+data.message, "error");
	    	}
	    	$('#appno').selectpicker('refresh');
	    },
	    error: function(e){
	    	swal("Oops！","系统初始化出错了！","error");
	    	
	    }
	 });// end initializing appno select
	
	$.ajax({// stat initializing service name&version select
	    type: "GET",
	    dataType: "json",
	    url: "service/all",
	    success: function(data){
	    	if("00000"===data.code){
	    		$(data.data).each(function(){
		    		$("#service_name").append("<option value='"+this.name+"'>"+this.name+"</option>");
		    		$("#service_version").append("<option value='"+this.version+"'>"+this.version+"</option>");
				});
	    	}else{
	    		swal("Oh no!",data.code+":"+data.message, "error");
	    	}
	    	$('#service_name').selectpicker('refresh');
	    	$('#service_version').selectpicker('refresh');
	    },
	    error: function(e){
	    	swal("Oops！","系统初始化出错了！","error");
	    	
	    }
	 });// end initializing service name&version select
	
	// *********************start select record***************
    var record_select_css = "info";
    //给table添加选中/取消事件
    //标记选择的样式，并记录选择的记录
	$('#dataTable tbody').on('click', 'tr', function() {
		//如果current_record为空,则：
		//1. 给当前选择记录添加样式；
		//2. 移除current_record的样式；
		//3. 将current_record置为当前选择记录。
		if(current_record!=null){
			//如果current_record为当前选择记录，说明是取消选择,则:
			//1. 取消当前记录的样式;
			//2. 将current_record置为null（未选择）
			if(current_record===this){
				$(this).removeClass(record_select_css);
				current_record = null;
			}else{
				$(this).addClass(record_select_css);
				$(current_record).removeClass(record_select_css);
				current_record = this;
			}
		}else{
			$(this).addClass(record_select_css);
			current_record = this;
		}
	});
	// *********************end select record***************
	
    //新增按钮
    $('#add_btn').on('click', function () {
    clearModal();//新增窗口打开时清空模式窗口
 	   $("#_modeal_title").html("新增权限信息");
 	   $("#operateType").val("add");
 	   $('#_modal').modal("show");
    });
    
    //修改按钮
    $('#update_btn').on('click', function () {
    	if(!current_record){
    		swal("","请先点击要修改的记录");
    		return;
    	}
    	fillModal();
 	    $("#_modeal_title").html("修改权限信息");
 	    $("#operateType").val("update");
 	    $('#_modal').modal("show");
    });
    
    //修改按钮
    $('#delete_btn').on('click', function () {
    	if(!current_record){
    		swal("","请先点击要删除的记录");
    		return;
    	}
    	
    	swal({
    	  title: "",
    	  text: "确定要删除吗?",
    	  type: "warning",
    	  showCancelButton: true,
    	  confirmButtonClass: "btn-danger",
    	  confirmButtonText: "确 定",
    	  cancelButtonText: "取 消",
    	  closeOnConfirm: false,
    	  closeOnCancel: true
    	},
    	function(isConfirm) {
    	  if (isConfirm) {
    		  toDelete();
    	  } 
    	});
    });
    
    //保存按钮
    $('#save_btn').on('click',function (){
    	//再验证数据
    	if(!validate()){
    		return ;
    	}
    	//最后调用方法
    	var operateType = $("#operateType").val();
    	if("add"===operateType){//新增
    		toAdd();
    	}else if("update"===operateType){//修改
    		toUpdate();
    	}
    });
})

/**
 * 删除权限信息
 */
function toDelete(){
	var _id    = $('#dataTable').DataTable().row(current_record).data().id;
	var url    = "permission/"+_id;
	$.ajax({
	    type: "DELETE",
	    dataType: "json",
        url: url,
	    complete :function(){
	         //AJAX请求完成时调用
	    },
	    success: function(data) { 
	        var code    = data.code;
	        var message = data.message;
	        if("00000"===data.code){
	        	swal("Good!", data.message, "success");
	        	//重新加载dataTable中的数据
	        	$('#dataTable').DataTable().ajax.reload();
	        	//清除当前选择记录对象
	        	current_record = null;
	        }else{
	        	swal("Oh no!", data.message, "error");
	        }
	    },
	    error: function() {
	    	swal("Oops!", "有些不好的事情发生了", "errremarkor");
	    }
	});
}
/**
 * 验证数据是否符合规则
 * 符合返回true，否则返回false
 * @returns
 */
function validate(){
	var _appno       = $.trim($("#appno").val());
	var _serviceName = $.trim($("#service_name").val());
	var _serviceVersion = $.trim($("#service_version").val());
	return !isEmpty(_appno,"请选择终端号")
			&&!isEmpty(_serviceName,"请选择服务名称")
			&&!isEmpty(_serviceVersion,"请选择服务版本");
}

/**
 * 添加权限
 */
function toAdd(){
	var _appno       = $.trim($("#appno").val());
	var _serviceName = $.trim($("#service_name").val());
	var _serviceVersion = $.trim($("#service_version").val());
	var _remark   = $.trim($("#remark").val());
	var params    = '{"appno":"'+_appno+'","serviceName":"'+_serviceName+'","serviceVersion":"'+_serviceVersion+'","remark":"'+_remark+'"}';
	var url       = "permission";
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    contentType:"application/json",
        url: url,
	    data: params,
	    complete :function(){
	         //AJAX请求完成时调用
	    },
	    success: function(data) { 
	        var code    = data.code;
	        var message = data.message;
	        if("00000"===data.code){
	        	swal("Good!", data.message, "success");
	        	//重新加载dataTable中的数据
	        	$('#dataTable').DataTable().ajax.reload();
	        	//清除当前选择记录对象
	        	current_record = null;
	        	//关闭模式窗口
	        	$('#_modal').modal("hide");
	        }else{
	        	swal("Oh no!", data.message, "danger");
	        }
	    },
	    error: function() {
	    	swal("Oops!", "有些不好的事情发生了", "Danger");
	    }
	});
}

/**
 * 更新权限
 */
function toUpdate(){
	var _id       = $('#dataTable').DataTable().row(current_record).data().id;
	var _appno       = $.trim($("#appno").val());
	var _serviceName = $.trim($("#service_name").val());
	var _serviceVersion = $.trim($("#service_version").val());
	var _remark   = $.trim($("#remark").val());
	var params    = '{"appno":"'+_appno+'","serviceName":"'+_serviceName+'","serviceVersion":"'+_serviceVersion+'","remark":"'+_remark+'"}';
	var url       = "permission/"+_id;
	$.ajax({
	    type: "PUT",
	    dataType: "json",
	    contentType:"application/json",
        url: url,
	    data: params,
	    complete :function(){
	         //AJAX请求完成时调用
	    },
	    success: function(data) { 
	        var code    = data.code;
	        var message = data.message;
	        if("00000"===data.code){
	        	swal("Good!", data.message, "success");
	        	//重新加载dataTable中的数据
	        	$('#dataTable').DataTable().ajax.reload();
	        	//清除当前选择记录对象
	        	current_record = null;
	        	//关闭模式窗口
	        	$('#_modal').modal("hide");
	        }else{
	        	swal("Oh no!", data.message, "error");
	        }
	    },
	    error: function() {
	    	swal("Oops!", "有些不好的事情发生了", "error");
	    }
	});	
}


/**
 * 清空模式窗口（新增操作适用）
 */
function clearModal(){
	$("#appno").selectpicker('val', '');
	$("#service_name").selectpicker('val', '');
	$("#service_version").selectpicker('val', '');
	$("#remark").val('');
}

/**
 * 填充模式窗口（修改操作适用）
 */
function fillModal(){
	var record = $('#dataTable').DataTable().row(current_record).data();
	$("#appno").selectpicker('val', record.appno);
	$("#service_name").selectpicker('val', record.serviceName);
	$("#service_version").selectpicker('val', record.serviceVersion);
	$("#remark").val(record.remark);
}