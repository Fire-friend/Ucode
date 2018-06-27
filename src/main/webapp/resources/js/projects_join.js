;
!function(){
	var proStatus,description,creatTime,progress = "10%",member,codeName,imagePath,isPublic;
	
	//创建一条工程信息，包括，状态，描述，创建时间，进度，项目成员
	function creatCode(){
		var code_tr = document.createElement("tr");
		var code_td_sign = document.createElement("td");
		var code_td_sign_span = document.createElement("span");
		var code_td_des = document.createElement("td");
		var code_td_des_a = document.createElement("a");
		var br = document.createElement("br");
		var code_td_des_small = document.createElement("small");
		var code_progress_td = document.createElement("td");
		var code_progress_td_small = document.createElement("small");
		var code_progress_td_div = document.createElement("div");
		var code_progress_td_div_div = document.createElement("div");
		var code_member_td = document.createElement("td");
		var code_member_td_a = document.createElement("a");
		var code_member_td_a_image = document.createElement("img");
		
		code_tr.append(code_td_sign);
		code_td_sign.append(code_td_sign_span);
		code_tr.append(code_td_des);
		code_td_des.append(code_td_des_a);
		code_td_des.append(br);
		code_td_des.append(code_td_des_small);
		code_tr.append(code_progress_td);
		code_progress_td.append(code_progress_td_small);
		code_progress_td.append(code_progress_td_div);
		code_progress_td_div.append(code_progress_td_div_div);
		code_tr.append(code_member_td);
		code_member_td.append(code_member_td_a);
		code_member_td_a.append(code_member_td_a_image);
		
		code_td_sign.className = "project-status";
		code_td_sign_span.className = "label label-primary";
		//取消样式
		//code_td_sign_span.className = "label label-default";
		code_td_des.className = "project-title";
		code_progress_td.className = "project-completion";
		code_progress_td_div.className = "progress progress-mini";
		code_progress_td_div_div.className = "progress-bar";
		code_member_td.className = "project-people";
		code_member_td_a_image.className = "img-circle";
		
		//项目状态
		code_td_sign_span.innerHTML = proStatus;
		
		//项目详情连接
		code_td_des_a.href="#";
		
		//项目描述文字
		code_td_des_a.innerHTML = codeName;
		//项目创建时间
		code_td_des_small.innerHTML = creatTime;
		//项目进度
		code_progress_td_small.innerHTML = progress;
		code_progress_td_div_div.style.width = progress;
		
		//项目成员信息详情连接
		code_member_td_a.href="#";
		//项目成员头像
		code_member_td_a_image.alt = "image";
		//项目成员头像路径
		code_member_td_a_image.src = "../resources/frame/img/a3.jpg";
		
		return code_tr;
	}
	
	$.ajax({
		url :"/codingSoft/code/codeQuery.do",
		type : "post",
		data : {
			"action" : "join"
		},
		success : function(result) {
			if(result[0].status == 0){
				return;
			}
			for(var i = 0,l = result.length;i<l;i++){
				proStatus = "进行中";
				description = result[i].description;
				creatTime = "创建时间:"+result[i].creat_time;
				codeName = result[i].codeName;
				imagePath = result[i].codeImgPath;
				isPublic = result[i].isPublic;
				//TODO imagePath
				
				document.getElementById("code_info").append(creatCode());
			}
		}
	});
	
	
}();
