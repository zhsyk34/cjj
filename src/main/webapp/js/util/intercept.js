$.ajaxSetup({
	cache : false,
	traditional : true,
	type : "POST",
	complete : function(xhr, status) {
		console.log(xhr.responseText.substr(0, 100));
		var data = xhr.getResponseHeader("sessionstatus");
		console.log("ajax");
		(data == "timeout") && relogon();
	}
});

$(function() {
	(!sessionstatus || sessionstatus == "null") && relogon();
});

function relogon() {
	console.log("reload");
	var top = getTopWinow();
	// confirm("您尚未登录或者登录超时,请重新登录...") ;
	top.location.href = basePath + "jsp/logon.jsp";

	function getTopWinow() {
		var w = window;
		while (w != w.parent) {
			w = w.parent;
		}
		return w;
	}
}
