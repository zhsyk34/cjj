$.message = function(key) {
	var result;
	$.i18n.properties({
		name : "message",
		path : "recources/",
		mode : "map",
		cache : true,
		callback : function() {
			result = $.i18n.prop(key);
		}
	});
	console.log(result);
	return result;
}
