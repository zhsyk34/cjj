require([ "jquery", "modal" ], function($, modal) {
	$("#login").on("click", function() {
		var name = $("#name").val();
		var password = $("#password").val();

		if (!/^[A-Za-z0-9]+$/.test(name)) {
			alert("请填写合法的用户名");

			return false;
		}

		if (!/^[A-Za-z0-9]+$/.test(password)) {
			alert("请填写合法的密码");
			return false;
		}

		$.ajax({
			url : "json/User_login",
			traditional : true,
			async : false,
			data : {
				name : name,
				password : password
			},
			success : function(data) {
				if (data.result == "success") {
					var basePath = location.protocol + "//" + location.host + "/" + location.pathname.split(/\//)[1];
					window.location.href = basePath + "/jsp/index.jsp";
				}
				if (data.result == "fail") {
					alert("用户名密码不正确,请重新登录");
				}
			}
		});
	});

});