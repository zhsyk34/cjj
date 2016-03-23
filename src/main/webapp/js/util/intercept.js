$.ajaxSetup({
	cache : false,
	traditional : true,
	type : "POST",
	complete : function(XMLHttpRequest, textStatus) {
		// console.log(XMLHttpRequest.responseText);
		var data = eval("(" + XMLHttpRequest.responseText + ")");
		if (data.result == "offline") {// TODO
			// window.location.href = "login.jsp";
		}
	}
});