;
!function() {
	var layer = layui.layer,
		form = layui.form;

	// 当勾选不同意协议按钮后禁止注册
	form.on("checkbox(agreen-checkbox)", function(data) {
		if (!data.elem.checked) {
			$("#reg").addClass("layui-btn-disabled");
			$("#reg").prop("disabled", "disabled");
		} else {
			$("#reg").removeClass("layui-btn-disabled");
			$("#reg").removeAttr("disabled");
		}
	});

	// 验证码值存储变量
	var vailCode;
	// 执行获取验证码
	refCode();

	// 点击刷新验证码
	$("#refCode_reg_img").on("click", function() {
		refCode();
	});

	// 获取图片验证码
	function refCode() {
		$.ajax({
			url : getRootPath_web() + "/user/imageVailCode",
			type : "post",
			success : function(result) {
				vailCode = result.veryfyCode;
				$("#code").val("");
			}
		});
	}

	//点击图片改变验证码
	$("#user_veryCode_img").on("click", function() {
		this.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
		refCode();
	});
	
	// 发送邮箱验证码
	var InterValObj; // 定时器变量
	var count = 30; // 间隔函数，1秒执行
	var curCount; // 当前剩余秒数
	var msg_send_count = 0;
	// 发送验证码
	$("#msg-btn").on("click", function() {
		if ($(this).prop("disabled") != "disabled") {
			vailEmail();
			if (!/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g.test($("#email").val())) {
				layer.msg("请先输入正确的邮箱", {
					icon : 5
				});
				return false;
			} else if (email_status == 0) {
				layer.msg("该邮箱已被注册", {
					icon : 5
				});
				return false;
			}
			msg_send_count++;
			curCount = count;
			// 设置button效果，开始计时
			$("#msg-btn").addClass("layui-btn-disabled");
			$("#msg-btn").prop("disabled", "disabled"); // 设置按钮为禁用状态
			$("#msg-btn").text("正在发送..."); // 更改按钮文字
			// 向后台发送处理数据
			$.ajax({
				url : getRootPath_web() + "/user/sendEmail",
				type : "post",
				data : {
					"emailAdress" : $("#email").val(),
					"type" : "info"
				},
				success : function(result) {
					if (result.status == 0) {
						$("#msg-btn").text(curCount + "秒后再次获取"); // 更改按钮文字
						InterValObj = setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
					} else {
						layer.msg(result.msg);
						$("#msg-btn").text("重新发送验证码");
						$("#msg-btn").removeClass("layui-btn-disabled");
						$("#msg-btn").removeProp("disabled"); // 移除禁用状态改为可用
					}
				}
			});
		}
	});

	// timer处理函数
	function SetRemainTime() {
		if (curCount == 0) {
			clearInterval(InterValObj); // 停止计时器
			$("#msg-btn").removeClass("layui-btn-disabled");
			$("#msg-btn").removeProp("disabled"); // 移除禁用状态改为可用
			$("#msg-btn").text("重新发送验证码");
		} else {
			curCount--;
			$("#msg-btn").text(curCount + "秒后再次获取");
		}
	}

	var email_status;

	// 验证邮箱唯一
	function vailEmail() {
		$.ajax({
			url : getRootPath_web() + "/user/checkEmail",
			type : "post",
			async : false,
			data : {
				"emailAdress" : $("#email").val(),
				"type" : "email"
			},
			success : function(result) {
				email_status = result.status;
			}
		});
	}

	// 邮箱输入框改变时验证
	$("#email").on("change", function() {
		vailEmail();
		if (email_status == 0) {
			layer.msg("该邮箱已被注册", {
				icon : 5
			});
		}
	});


	// 自定义验证规则
	form.verify({
		email : function() {
			vailEmail();
			if (email_status == 0) {
				return "该邮箱已被注册";
			}
		},
		regexit : function(value) {
			if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(value)) {
				return "请输入正确的手机号码";
			}
		},
		pwd : function(value) {
			if (value.length < 8) {
				return "密码长度不能少于8位";
			} else if (!/^(\w){8,20}$/.test(value)) {
				return "密码只能包含字母、数字或下划线";
			}
		},
		rePwd : function(value) {
			if (value != $("#password").val()) {
				return "两次输入的密码不一致";
			}
		},
		code : function(value) {
			if (value.toUpperCase() != vailCode) {
				refCode();
				return "图像验证码错误";
			}
		},
		msgcode : function(value) {
			if (msg_send_count == 0) {
				layer.msg("请点击获取验证码");
			} else {
				$.ajax({
					url : getRootPath_web() + "/user/sendEmail",
					type : "post",
					async : false,
					data : {
						"code" : value,
						"type" : "veryfy"
					},
					success : function(result) {
						if (result.status == 0) {
							return result.msg;
						}
					}
				});
			}
		}
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

	//监听提交  
	form.on("submit(register)", function() {
		if (email_status == 0) {
			layer.msg("该邮箱已被注册", {
				icon : 5
			});
		} else if (msg_send_count == 0) {
			layer.msg("请点击获取验证码");
		} else {

			// 发送注册请求到后台匹配
			$.ajax({
				url : getRootPath_web() + "/user/register",
				type : "post",
				data : {
					"username" : $("#username").val(),
					"email" : $("#email").val(),
					"phone" : $("#phone").val(),
					"password" : $("#password").val()
				},
				success : function(result) {
					if (result.status == 0) {
						layer.msg("注册成功");
						setTimeout("location='login.html'", 2000);
					} else {
						$("form")[0].reset();
						
						layer.msg(result.msg, {
							icon : 5
						});
					}
				}
			});


		}

		return false;
	});
}();