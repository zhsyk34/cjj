define([ "jquery", "modal", "page" ], function() {
	return {
		page : function(data, callback, target) {// 翻页控件
			var options = {
				onChangePage : function(pageNo, pageSize) {
					callback();
				}
			};
			if (data) {
				options.pageNo = data.pageNo;
				options.pageSize = data.pageSize;
				options.dataCount = data.count;
			}
			if (target) {
				target.page(options);
			} else {
				$("#page").page(options);
			}
		},
		merge : function(url, params, callback) {
			$.ajax({
				url : url,
				async : false,
				traditional : true,
				data : params,
				success : function(data) {
					switch (data.result) {
					case "create":
						$.alert("增加成功");
						break;
					case "update":
						$.alert("修改成功");
						break;
					case "enable":
						$.alert("启用成功");
						break;
					case "disable":
						$.alert("禁用成功");
						break;
					case "revoke":
						$.alert("撤销成功");
						break;
					case "error":
						$.alert("出错了");
						return;
					}
					callback && callback();
				}
			});
		},
		del : function(url, ids, callback) {
			if (typeof ids == "number") {
				ids = [ ids ];
			}
			if (ids.length == 0) {
				$.alert("请选择要删除的数据！");
				return;
			}
			$.confirm({
				after : function() {
					$.ajax({
						url : url,
						traditional : true,
						async : false,
						data : {
							ids : ids
						},
						success : function(data) {
							if (data.result == "relate") {
								$.alert("数据正被使用中，删除失败！");
								return;
							}
							if (data.result == "delete") {
								$.alert("删除成功！");
							}
							$("#page").page({
								pageNo : 1
							});
							if (typeof callback == "function") {
								callback();
							}
						}
					});
				}
			});
			$.confirm("确认删除？");
		}
	};
});