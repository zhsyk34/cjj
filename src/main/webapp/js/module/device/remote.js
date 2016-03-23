require([ "jquery", "modal", "page", "progress", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, progress, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	crud.page(null, find);
	dialog();
	find();
	remote();

	function remote() {
		var url = "json/Terminal_remote";
		var params = {};
		$("#remote").on("click", "button", function() {
			var ids = [];
			$("#data :checkbox:checked").each(function() {
				var id = $(this).parents("tr").data("row").id;
				ids.push(id);
			});
			if (ids.length == 0) {
				$.alert("请选择终端");
				return false;
			}
			params.ids = ids;
			params.status = $(this).attr("id");

			$.ajax({
				url : url,
				async : false,
				traditional : true,
				data : params,
				success : function(data) {
					if (data.result) {
						$.alert("已发送");
					}
				}
			});
		});
	}

	function dialog() {
		$("#template").modal({
			width : 650,
			top : 100,
			title : "终端模板管理",
			buttons : [ "cancel" ]
		});

		$("#data").on("click", ".update", function() {
			var id = $(this).parents("tr").data("row").id;
			$("#template").data("id", id);
			loadTemplate();
			$("#template").modal("open");
		});

		crud.page(null, loadTemplate, $("#template-page"));
		$("#find-template").on("click", function() {
			$("#template-page").page({
				pageNo : 1
			});
			loadTemplate();
		});

		// TODO
		$("#template").on("click", "button", function() {
			var url = "json/Terminal_saveTemplate";
			var row = $(this).parents("tr").data("row");

			var params = {
				terminalId : row.terminalId,
				templateId : row.templateId
			};

			var clazz = $(this).attr("class");

			if (/del/.test(clazz)) {
				params.status = "waitdelete";
			} else if (/down/.test(clazz)) {
				params.status = "waitdown";
			} else if (/use/.test(clazz)) {
				url = "json/Terminal_useTemplate";
			} else if (/cancel/.test(clazz)) {
				params.status = "canceldown";
			}
			crud.merge(url, params, loadTemplate);
		});

		$("#template").on("click", "button", function() {
		});

		function loadTemplate() {
			var id = $("#template").data("id");
			var templateName = $.trim($("#template-name").val());
			var options = $("#template-page").page("options");

			var tbody = $("#template-data");
			$.ajax({
				url : "json/Terminal_findTemplate",
				data : {
					id : id,
					templateName : templateName,
					pageNo : options.pageNo,
					pageSize : options.pageSize
				},
				async : false,
				success : function(data) {
					tbody.empty();

					var str = "<tr>";
					str += "<td class='name'></td>";
					str += "<td class='status'></td>";
					str += "<td class='used'></td>";
					str += "<td class='operation'></td>";
					str += "</tr>";

					var down = "<button class='btn btn-primary btn-small down'>下载</button>";
					var use = "<button class='btn btn-success btn-small use'>启用</button>";
					var del = "<button class='btn btn-danger btn-small del'>删除</button>";
					var cancel = "<button class='btn btn-warning btn-small cancel'>取消</button>";

					$.each(data.list || [], function(index, row) {
						var tr = $(str).data("row", row);
						// tr.find(".name").text(row.templateName);
						tr.find(".name").text(row.templateName + ", " + row.status);

						switch (row.status) {
						case null:
							tr.find(".status").text("未下载");
							tr.find(".operation").html(down);
							break;
						case "hasdown":
							tr.find(".status").text("已下载");
							tr.find(".used").html(row.used ? "已启用" : use);
							row.used || tr.find(".operation").html(del);
							break;
						case "canceldown":
							tr.find(".status").text("正在撤销...");
							// tr.find(".operation").html(down);
							break;
						case "waitdown":
							var div = $("<div class='show'></div>");
							tr.find(".status").html(div);
							tr.find(".operation").html(cancel);

							updateProgress(tr, row);
							div.progress({
								width : 120,
								height : 14,
								tip : false
							});
							break;
						case "hasdelete":
							tr.find(".status").text("未下载");
							tr.find(".operation").html(down);
							break;
						case "waitdelete":
							tr.find(".status").text("正在删除...");
							break;
						}
						tbody.append(tr);
					});

					crud.page(data, loadTemplate, $("#template-page"));
				}
			});
		}

		function updateProgress(target, params) {
			readProgress();
			var timer = null;
			timer = setInterval(readProgress, 1000);

			function readProgress() {
				$.ajax({
					url : "json/Terminal_progress",
					data : {
						terminalId : params.terminalId,
						templateId : params.templateId
					},
					async : false,
					success : function(data) {
						console.log(data);
						var percent = data.progress;
						var div = $(target).find(".show");
						div.progress({
							value : percent
						});
						if (data.status != "WAITDOWN") {
							loadTemplate(params.terminalId);
							clearInterval(timer);
							timer = null;
						}
					}
				});
			}
		}
	}

	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Terminal_search",
			data : {
				pageNo : options.pageNo,
				pageSize : options.pageSize
			},
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='terminalNo'></td>";
			str += "<td class='location'></td>";
			str += "<td class='type'></td>";
			str += "<td class='editor'></td>";
			str += "</tr>";

			var button = "<button class='btn btn-primary btn-small update'>设置</button>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".location").text(row.location);
				if (row.type == "SHOP") {
					tr.find(".type").text("客户端");
				} else {
					tr.find(".type").text("厨房端");
					tr.find(".editor").html(button);
				}

				$("#data").append(tr);
			});
		}
	}
});
