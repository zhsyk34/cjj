$.ajaxSetup({
	cache : false,
	traditional : true,
	type : "POST",
	complete : function(XMLHttpRequest, textStatus) {
		var data = XMLHttpRequest.responseText;
		if (/^{/.test(data)) {
			data = eval("(" + data + ")");
			// console.log("json", data);
		} else {
			// console.log("normal", data);
		}

		if (data.result == "offline") {// TODO
			// window.location.href = "login.jsp";
		}
	}
});