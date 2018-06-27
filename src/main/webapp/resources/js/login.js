;
!function() {
	var layer = layui.layer,
		form = layui.form,
		carousel = layui.carousel;

	// 背景图片轮播
	carousel.render({
		elem : '#login_carousel',
		width : '100%',
		height : '100%',
		interval : 3000,
		arrow : 'none',
		anim : 'fade',
		indicator : 'none'
	});

	// 验证码值存储变量
	var vailCode;
	// 执行获取验证码
	refCode();

	// 点击刷新验证码
	$("#refCode_login_img").on("click", function() {
		refCode();
	});

	getUserSession();
	//获取session中的用户
	function getUserSession() {
		$.ajax({
			url : getRootPath_web() + "/user/getSessionUser",
			type : "post",
			success : function(result) {
				if (result.status != 0) {
					location = "index.html";
				}
			}
		});
	}

	//点击图片改变验证码
	$("#user_veryCode_img").on("click", function() {
		this.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
		refCode();
	});
	
	// 获取验证码
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

	// 自定义验证规则
	form.verify({
		email : function(value) {
			var regPhone = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
			if (!regPhone.test(value.trim())) {
				return "请输入正确的邮箱";
			}
		},
		code : function(value) {
			if (value.toUpperCase() != vailCode) {
				refCode();
				return "验证码不正确";
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
	form.on("submit(login)", function() {
		$.ajax({
			url : getRootPath_web()+"/user/login.do",
			type : "post",
			data : {
				"email" : $("#email").val(),
				"password" : $("#password").val()
			},
			success : function(result) {
				if (result.status == 0) {
					location = getRootPath_web() + "/page/pageindex.html";
				} else {
					refCode();
					$("#password").val("");
					layer.alert(result.msg, {
						title : '提交结果'
					});
				}
			}
		});

		return false;
	});
}();