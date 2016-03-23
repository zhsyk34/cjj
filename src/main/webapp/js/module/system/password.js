require([ "jquery", "modal", "intercept", "validate" ], function($, modal, intercept, validate) {

	$("#sure").on("click", function() {
		update();
	});
	$("#reset").on("click", function() {
		$("#original,#password,#confirm").val("");
	});

	function update() {
		var id = parseInt($("#id").val()) || null;
		var original = $("#original").val();
		var password = $("#password").val();
		var confirm = $("#confirm").val();

		if (!id) {
			$.alert("请重新登录");
			return false;
		}

		if (validate.isEmpty(original)) {
			$.alert("请填写原密码");
			return false;
		}
		if (validate.isEmpty(password)) {
			$.alert("密码不能为空");
			return false;
		}
		if (password != confirm) {
			$.alert("两次输入的密码不一致,请重新确认");
			return false;
		}

		$.ajax({
			url : "json/User_modify",
			async : false,
			data : {
				id : id,
				original : original,
				password : password
			},
			success : function(data) {
				if (data.result == "notexist") {
					$.alert("原密码不正确");
				} else if (data.result == "update") {
					$.alert("修改成功");
				} else {
					$.alert("出错了");
				}
			}
		});
	}
});
