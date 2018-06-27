;
!function(){
	//获取当前项目名字
	var thisURL = document.URL;  
	var  getval =thisURL.split('?')[1];  
	
	$.ajax({
		url :"/codingSoft/code/getDetail.do",
		type : "post",
		data : {
			"codeName" : getval
		},
		success : function(result) {
			if(result.status == 0){
				return;
			}
			$("#code_name").text(result.codeName);
			$("#code_creat_user").text(result.creatUser);
			$("#code_creat_time").text(result.creatTime);
			$("#code_creat_time").text(result.creatTime);
			$("#code_des").text(result.codeDescription);
			$("#code_path").val(result.path);
			copyUrl();
			//TODO 团队成员，最后更新时间
		}
	});
	
	function copyUrl(){
		var clipboard = new Clipboard('.copy_span');
	    clipboard.on('success', function(e) {
	    	alert("已经复制到剪贴板")
	    });
	    clipboard.on('error', function(e) {
	    	alert("复制到剪贴板失败");
	    });
	}
	
}();