var current_record;//记录当前选择的记录
$(document).ready(function() {
	// *********************start dataTable********************* 
	//DataTable 异常处理
	//alert ：(default) - Alert the error
	//throw ：Throw a Javascript error
	//none  ：Do nothing 
	$.fn.dataTable.ext.errMode = 'none';
	$('#dataTable').on( 'error.dt', function ( e, settings, techNote, message ) {
		console.log("[error] DataTable:"+message);
    }).dataTable({
		"oLanguage" : {
			"sUrl" : "resources/datatables.zh_CN.json"
		},
		"ajax" : {
			"url" : "whitelists/all",
			"type" : "GET",
			"dataSrc":"data"
		},
		"autoWidth": true,
		"order": [3,'desc'],//此处如果不设定排序，默认就是第一个排序，但是oraderable=false时，显示时会有排序的图标存在。
		"columns": [
		            { "data": "id","defaultContent": "","visible": false},
		            { "data": "ip","defaultContent": "","visible": true,},
		            { "data": "hostname","defaultContent": "","visible": true },
		            { "data": "updateTime","defaultContent": "","visible": true },
		            { "data": "remark","defaultContent": "","visible": true },
		          ],
		          "columnDefs": [
		                         { className: "sorting_disabled", "targets": [ 0 ] }
		                       ],
		"createdRow": function ( row, data, index ) {
			//0表示第一列,以此类推
			//如果前面的列有"visible": false，则跳过
			//第4列日期显示格式
			// Date().pattern()函数来自于#resources/js/basic.js
			$('td', row).eq(2).text(new Date(data.updateTime).format("yyyy-MM-dd HH:mm:ss"));
		},
		"initComplete" : function(settings, json) {
			 $('#dataTable').DataTable().column(0).sort();
			// attention：
			// 更改datatable中的select需要在表格初始化完后后执行，否则select不会显示
			// 同时在jquery.dataTable.js的line3470 增加如下一行代码：
			// select.addClass('selectpicker');
			$('.selectpicker').selectpicker();
		}
	});
	// *********************end dataTable*********************
	
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
 	   $("#_modeal_title").html("新增白名单信息");
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
 	    $("#_modeal_title").html("修改白名单信息");
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
 * 删除白名单信息
 */
function toDelete(){
	var _id    = $('#dataTable').DataTable().row(current_record).data().id;
	var url    = "whitelists/"+_id;
	$.ajax({
	    type: "DELETE",
	    dataType: "json",
        url: url,
	    complete :function(){
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
	    	swal("Oops!", "有些不好的事情发生了", "error");
	    }
	});
}
/**
 * 验证数据是否符合规则
 * 符合返回true，否则返回false
 * @returns
 */
function validate(){
	var _ip       = $.trim($("#ipAddress").val());
	return !isEmpty(_ip,"请输入ip地址");
}

/**
 * 添加白名单
 */
function toAdd(){
	var _ip       = $.trim($("#ipAddress").val());
	var _hostname = $.trim($("#hostname").val());
	var _remark   = $.trim($("#remark").val());
	var params    = '{"ip":"'+_ip+'","hostname":"'+_hostname+'","remark":"'+_remark+'"}';
	var url       = "whitelists";
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    contentType:"application/json",
        url: url,
	    data: params,
	    complete :function(){
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
 * 更新白名单
 */
function toUpdate(){
	var _id       = $('#dataTable').DataTable().row(current_record).data().id;
	var _ip       = $.trim($("#ipAddress").val());
	var _hostname = $.trim($("#hostname").val());
	var _remark   = $.trim($("#remark").val());
	var params    = '{"ip":"'+_ip+'","hostname":"'+_hostname+'","remark":"'+_remark+'"}';
	var url       = "whitelists/"+_id;
	$.ajax({
	    type: "PUT",
	    dataType: "json",
	    contentType:"application/json",
        url: url,
	    data: params,
	    complete :function(){
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
	$("#ipAddress").val("");
	$("#hostname").val("");
	$("#remark").val("");
}

/**
 * 填充模式窗口（修改操作适用）
 */
function fillModal(){
	var data = $('#dataTable').DataTable().row(current_record).data();
	$("#ipAddress").val(data.ip);
	$("#hostname").val(data.hostname);
	$("#remark").val(data.remark);
}