;
!function() {
	var layer = layui.layer;
	var status;//信息完整标识
	function check_form(){
		var code_name = $("#code_name").val();
		var code_description = $("#code_description").val();
		if(code_name == ""||code_description == ""){
			status = 0;
			layer.msg("信息填写不完整", {
				icon : 5
			});
		}else{
			status = 1;
		}
	}
	//TODO 项目成员获取功能
	
	
	$("#creat_button").on("click",function(){
		check_form();
		if(status == 0){
			return;
		}
		$.ajax({
			url : getRootPath_web() + "/code/creat",
			type : "post",
			async : false,
			data : {
				"codeName" : $("#code_name").val(),
				"description" : $("#code_description").val(),
				"isPublic" : $('public_check').prop('checked')?false:true
				//TODO 项目成员获取
				//"proMembers" : ""
			},
			success : function(result) {
				if (result.status == 0) {
					layer.msg("创建成功");
				}else{
					layer.alert(result.msg, {
						title : '提交结果'
					});
				}
			}
		});
	});
	
	//获取网站根目录
	function getRootPath_web() {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083
		var localhostPaht = curWwwPath.substring(0, pos);
		//获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName);
	}

}();