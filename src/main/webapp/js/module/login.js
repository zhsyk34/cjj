require.config({
	baseUrl : "js",
	shim : {
		"modal" : [ "jquery" ]
	},
	paths : {
		"jquery" : "lib/jquery",
		"modal" : "lib/zhsy/js/modal"
	}
});

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
			url : "json/User_login",// TODO
			traditional : true,
			async : false,
			data : {
				name : name,
				password : password
			},
			success : function(data) {
				if (data.result == "success") {
					window.location.href = "jsp/index.jsp";
				}
				if (data.result == "fail") {
					alert("用户名密码不正确,请重新登录");
				}
			}
		});
	});
});