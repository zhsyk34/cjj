$.i18n.properties({
	name : "message",
	path : "recources/",
	mode : "map",
	cache : true,
	callback : function() {

	}
});
$.message = function(key) {
	return $.i18n.prop(key);
}
