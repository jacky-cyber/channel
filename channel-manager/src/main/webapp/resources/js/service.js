var current_record;//记录当前选择的记录
$(document).ready(function() {
	// *********************start dataTable********************* 
	$('#dataTable').dataTable({
		"oLanguage" : {
			"sUrl" : "resources/datatables.zh_CN.json"
		},
		"ajax" : {
			"url" : "service/all",
			"type" : "GET",
			"dataSrc":"data"
		},
		"columns": [
		            { "data": "id","defaultContent": "","visible": false},
		            { "data": "name","defaultContent": "","visible": true },
		            { "data": "version","defaultContent": "","visible": true },
		            { "data": "fullyQualifiedName","defaultContent": "","visible": true },
		            { "data": "beanId","defaultContent": "","visible": true },
		            { "data": "owner","defaultContent": "","visible": true },
		            { "data": "organization","defaultContent": "","visible": true },
		            { "data": "status","defaultContent": "","visible": true },
		            { "data": "updateTime","defaultContent": "","visible": true },
		            { "data": "remark","defaultContent": "","visible": true },
		          ],
		"createdRow": function ( row, data, index ) {
			// 0表示第一列,以此类推
			
			//第7列服务状态
			$('td', row).eq(6).text(data.status==="01"?"启用":"停用");
			//第8列日期显示格式
			// Date().pattern()函数来自于#resources/js/basic.js
			$('td', row).eq(7).text(new Date(data.updateTime).format("yyyy-MM-dd HH:mm:ss"));
		},
		"initComplete" : function(settings, json) {
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
 	   $("#_modeal_title").html("新增服务信息");
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
 	    $("#_modeal_title").html("修改服务信息");
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
 * 删除服务信息
 */
function toDelete(){
	var _id    = $('#dataTable').DataTable().row(current_record).data().id;
	var url    = "service/"+_id;
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
	var _owner                = $.trim($('#owner').val());
	var _organization         = $.trim($('#organization').val());
	var _bean_id              = $.trim($('#bean_id').val());
	var _fully_qualified_name = $.trim($('#fully_qualified_name').val());
	var _name                 = $.trim($('#name').val());
	var _version              = $.trim($('#version').val());
	var _status               = $('input[type="radio"][name="status"]:checked').val();	
	
	return !isEmpty(_name,"请输入服务名称")
			&&!isEmpty(_version,"请输入服务版本")
			&&!isEmpty(_fully_qualified_name,"请输入服务接口全限定名")
			&&!isEmpty(_bean_id,"请输入Bean Id")
			&&!isEmpty(_owner,"请输入负责人")
			&&!isEmpty(_organization,"请输入组织名称")
			&&!isEmpty(_status,"请选择服务状态");
}

/**
 * 添加服务
 */
function toAdd(){
	var _owner                = $.trim($('#owner').val());
	var _organization         = $.trim($('#organization').val());
	var _bean_id              = $.trim($('#bean_id').val());
	var _fully_qualified_name = $.trim($('#fully_qualified_name').val());
	var _name                 = $.trim($('#name').val());
	var _version              = $.trim($('#version').val());
	var _status               = $('input[type="radio"][name="status"]:checked').val()
	var _remark               = $.trim($('#remark').val());
	var params = '{"owner":"'+_owner+'",'+
	        '"organization":"'+_organization+'",'+
	        '"beanId":"'+_bean_id+'",'+
	        '"fullyQualifiedName":"'+_fully_qualified_name+'",'+
	        '"name":"'+_name+'",'+
	        '"version":"'+_version+'",'+
	        '"status":"'+_status+'",'+
	        '"remark":"'+_remark+'"}';
	var url       = "service";
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
	        	swal("Oh no!", data.message, "error");
	        }
	    },
	    error: function() {
	    	swal("Oops!", "有些不好的事情发生了", "error");
	    }
	});
}

/**
 * 更新服务
 */
function toUpdate(){
	var _id       = $('#dataTable').DataTable().row(current_record).data().id;
	var _owner                = $.trim($('#owner').val());
	var _organization         = $.trim($('#organization').val());
	var _bean_id              = $.trim($('#bean_id').val());
	var _fully_qualified_name = $.trim($('#fully_qualified_name').val());
	var _name                 = $.trim($('#name').val());
	var _version              = $.trim($('#version').val());
	var _status               = $('input[type="radio"][name="status"]:checked').val();
	var _remark               = $.trim($('#remark').val());
	var params = '{"owner":"'+_owner+'",'+
	        '"organization":"'+_organization+'",'+
	        '"beanId":"'+_bean_id+'",'+
	        '"fullyQualifiedName":"'+_fully_qualified_name+'",'+
	        '"name":"'+_name+'",'+
	        '"version":"'+_version+'",'+
	        '"status":"'+_status+'",'+
	        '"remark":"'+_remark+'"}';
	var url       = "service/"+_id;_status
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
	$('#owner').val("");
	$('#organization').val("");
	$('#bean_id').val("");
	$('#fully_qualified_name').val("");
	$('#name').val("");
	$('#version').val("");
//	$("input[type='radio'][name='status'][value='01']").attr("checked","checked");//默认启用
	$("input[type='radio'][name='status']").prop("checked",false);//默认启用
	$('#remark').val("");
}

/**
 * 填充模式窗口（修改操作适用）
 */
function fillModal(){
	var record = $('#dataTable').DataTable().row(current_record).data();
	$('#owner').val(record.owner);
	$('#organization').val(record.organization);
	$('#bean_id').val(record.beanId);
	$('#fully_qualified_name').val(record.fullyQualifiedName);
	$('#name').val(record.name);
	$('#version').val(record.version);
	$("input[type='radio'][name='status'][value='"+record.status+"']").prop("checked",true);
	$('#remark').val(record.remark);
}