var isPublish = true;
$(document).ready(function() {
// $.getJSON("channel.json", function(data) {});
	$(".home-alert").hide();
	sync();//默认进行一次同步
	
	$("#publish_btn").on('click',function(){
		if(isPublish){
			swal("","当前配置状态为已发布，无需发布");
			return;
		}
		toPublish();
	});
	
	$("#sync_btn").on('click',function(){
		//先将发布标志置为已发布，防止没有信息需要提示时，提示框一直显示
		//重新生成tree时，存在新增/修改/删除的节点时，会将其更改为false
		isPublish = true; 
		sync();
	});
	
});

/**
 * 将本地数据库配置发布到zookeeper
 */
function toPublish(){
	$.ajax({
	    type: "GET",
	    dataType: "json",
	    contentType:"application/json",
	    timeout:10000,
        url: "home/publish",
	    success: function(json) {
	    	if("00000"===json.code){
	    		isPublish = true;
	    		$(".home-alert").prop("class","alert alert-dismissible alert-info  home-alert");
		    	$(".home-alert > p").html("");
		    	$(".home-alert").hide();
	    		swal("Good!",json.message,"success");
	    		sync();
	    	}else{
	    		swal("Oh no!", json.message, "error");
	    	}
	    },
	    error: function() {
	    	$(".home-alert").prop("class","alert alert-dismissible alert-danger  home-alert");
	    	$(".home-alert > p").html("有些不好的事情发生了");
	    	$(".home-alert").show();
	    }
	});
}

/**
 * 本地数据库与zookeeper的配置进行同步对比，生成tree
 */
function sync(){
	$.ajax({
	    type: "GET",
	    dataType: "json",
	    contentType:"application/json",
	    timeout:10000,
        url: "home/config",
	    success: function(json) {
	    	hanlde(json);
	    },
	    error: function() {
	    	$(".home-alert").prop("class","alert alert-dismissible alert-danger  home-alert");
	    	$(".home-alert > p").html("有些不好的事情发生了");
	    	$(".home-alert").show();
	    }
	});		
}
	
/**
 * 处理服务端返回的json字符串，将其组装为tree展示
 */
function hanlde(json){
	if("00000"!=json.code){
		$(".home-alert").prop("class","alert alert-dismissible alert-danger  home-alert");
    	$(".home-alert > p").html("Oops!"+json.code+":"+json.message);
    	$(".home-alert").show();
		return;
	}
	
	var data = json.data;
	if(!data.node){
		$(".home-alert").prop("class","alert alert-dismissible alert-warning home-alert");
    	$(".home-alert > p").html("看起来，系统中好像没有任何配置信息...");
    	$(".home-alert").show();
		return;
	}
	
	$("#channel_config_tree").html("<ul>" + tree(data)+ "</ul>");
	if(!isPublish){
		$(".home-alert").prop("class","alert alert-dismissible alert-warning home-alert");
    	$(".home-alert > p").html("当前有配置信息未发布");
    	$(".home-alert").show();
	}
	
	// $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title',
	// '');
	
	// start toggle node css
	$('.tree ul li > span > a').mouseover(function(){
		$(this).prop("class","btn btn-info btn-sm");
		$(this).parent().parent().find('ul > li > span > a').prop("class","btn btn-info btn-sm");
	}).mouseout(function(){
		$(this).prop("class","btn btn-default btn-sm");
		$(this).parent().parent().find('ul > li > span > a').prop("class","btn btn-default btn-sm");
	});
	// end toggle node css
	
	$('.tree ul li > span').on(
			'click',
			function(e) {
				var children = $(this).parent().find(' > ul > li');
				if (children.is(":visible")) {
					children.hide('fast');
					$(this).find(' > i').prop("class",'icon-folder-close-alt');
				} else {
					children.show('fast');
					$(this).attr('title', '').find(' > i').prop('class','icon-folder-open-alt');
				}
				e.stopPropagation();
			});
}

/**
 * 根据json对象生成树形结构的html内容
 * 
 * @param data
 */
function tree(data) {
	var html = "";
	$(data).each(
			function(index, record) {
                var _node     = record.node;// 字符串，节点名。
                var _children = record.children;// 数组，字节点。
                var _orgData  = record.originalData;// 字符串，原数据。
                var _data     = record.data;// 字符串，数据。
                var _status   = record.status;// 字符串，状态，00-无变化；01-新增；02-修改；03-删除。
                
				html += '<li><span>';
				if (_children && _children.length > 0) {
					html+='<i class="icon-folder-open-alt"></i>';
				}
				
				html +='<a class="btn btn-default btn-sm">';
				
				if("01"===_status){//新增
					html+='/' + _node +' (+) </a></span>&nbsp;&nbsp;<span class="text-primary">'+ _data + '</span>'
					isPublish = false;
				}else if('02'===_status){//修改
					html+='/' + _node +'</a></span>&nbsp;&nbsp;<span class="text-muted"><s>'+_orgData+'</s></span>&nbsp;<span class="text-primary">'+ _data + '</span>'
					isPublish = false;
				}else if ('03'===_status){//删除
					html+='<s>/'+ _node +'</s></a></span>&nbsp;&nbsp;<span class="text-primary"><span class="text-muted"><s>'+ _data + '</s></span></span>'
					isPublish = false;
				}else{
					html+='/' + _node +'</a></span>&nbsp;&nbsp;<span class="text-primary">'+ _data + '</span>'
				}
				
				if (_children && _children.length > 0) {
					html = html + "<ul>";
					html = html + tree(record.children);
					html = html + "</ul>";
				}
				html = html + "</li>";
			});
	return html;
}