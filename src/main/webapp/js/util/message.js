$.i18n.properties({
	name : "message",
	path : "recources/",
	mode : "map",
	// language : navigator.language || navigator.userLanguage,
	cache : true,
	callback : function() {
		$.prop = function(key) {
			return $.i18n.prop(key);
			// console.log($.i18n.map[key]);
		}
	}
});
