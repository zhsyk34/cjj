require([ "jquery", "modal", "page", "datePicker", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, datePicker, checkctrl, crud, intercept, validate) {
	var href = window.location.href;
	var type = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf(".jsp"));
	checkctrl.table($(".main table"));
	dialog();
	search();
	find();
	used();
	revoke();

	function update() {
		var url = "json/Activity_update";
		var ids = $("#editor").data("ids");
		var begin = $.trim($("#begin").val());
		var end = $.trim($("#end").val());
		var count = parseInt($.trim($("#count").val()));
		var used = $("#used :radio:checked").val();

		var unit = parseInt($.trim($("#unit").val()));
		var percent = parseInt($.trim($("#percent").val()));
		var discount = parseFloat($.trim($("#discount").val()));

		var params = {
			ids : ids,
			type : type,
			begin : begin,
			end : end,
			count : count,
			used : used
		};

		switch (type) {
		case "stop":
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {// TODO
			case "count":
				if (!validate.isNatural(count)) {
					$.alert("请填写正确的停售数量");
					return false;
				}
				params.begin = null;
				params.end = null;
				break;
			case "date":
				if (validate.isEmpty(begin)) {
					$.alert("开始日期不能为空");
					return false;
				}
				if (validate.isEmpty(end)) {
					$.alert("结束日期不能为空");
					return false;
				}
				params.count = 0;
				break;
			}
			break;
		case "gift":
			if (validate.isEmpty(begin)) {
				$.alert("开始日期不能为空");
				return false;
			}
			if (validate.isEmpty(end)) {
				$.alert("结束日期不能为空");
				return false;
			}
			if (!validate.isNatural(count)) {
				$.alert("请填写正确的赠送数量");
				return false;
			}
			if (!validate.isNatural(unit)) {
				$.alert("请填写正确的每份数量");
				return false;
			}
			break;
		case "discount":
			if (validate.isEmpty(begin)) {
				$.alert("开始日期不能为空");
				return false;
			}
			if (validate.isEmpty(end)) {
				$.alert("结束日期不能为空");
				return false;
			}
			if (!validate.isNatural(count)) {
				$.alert("请填写正确的促销数量");
				return false;
			}
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {
			case "discount":
				if (!discount) {
					$.alert("请填写正确的促销价格");
					return false;
				}
				params.discount = discount;
				break;
			case "percent":
				if (!percent || percent < 1 || percent > 99) {
					$.alert("请填写正确的促销折扣(1-99)");
					return false;
				}
				params.percent = percent;
				break;
			}
			break;
		}

		crud.merge(url, params, find);
	}

	function dialog() {
		$("#editor").modal({
			width : 400,
			top : 100,
			title : "停售设置",
			before : function() {
				return update();
			},
			reset : function() {
				loadDialog();
			}
		});
		// pattern-listen
		$("#pattern").on("change", ":radio", function() {
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {
			case "count":
				$(".pattern-count").show();
				$(".pattern-date").hide();
				break;
			case "date":
				$(".pattern-count").hide();
				$(".pattern-date").show();
				break;
			case "discount":
				$(".pattern-percent").hide();
				$(".pattern-discount").show();
				break;
			case "percent":
				$(".pattern-percent").show();
				$(".pattern-discount").hide();
				break;
			}
		});

		// data-selector
		$("#begin").on("click", function() {
			WdatePicker({
				maxDate : "#F{$dp.$D('end')}",
				dateFmt : "yyyy-MM-dd HH:mm:ss"
			});
		});
		$("#end").on("click", function() {
			WdatePicker({
				minDate : "#F{$dp.$D('begin')}",
				dateFmt : "yyyy-MM-dd HH:mm:ss"
			});
		});

		// update-event
		$("#data").on("click", ".update", function() {
			$("#data").find(":checkbox").prop("checked", false);
			$(this).parents("tr").find(":checkbox").prop("checked", true);
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			$("#editor").data("ids", row.id);
			loadDialog();
			$("#editor").modal("open");
		});
		$("#update-all").on("click", function() {
			var ids = select();
			if (ids.length == 0) {
				$.alert("请选择数据");
				return;
			}
			$("#editor").removeData("row");
			$("#editor").data("ids", ids);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			var row = $("#editor").data("row");
			if (row) {
				$("#begin").val(row.begin ? row.begin.replace("T", " ") : "");
				$("#end").val(row.end ? row.end.replace("T", " ") : "");
				$("#count").val(row.count || "");

				var value = row.used ? 1 : 0;
				$("#used :radio[value='" + value + "']").prop("checked", true);

				switch (type) {
				case "stop":
					if (row.count) {
						$("#pattern :radio[value='count']").prop("checked", true);
						$(".pattern-count").show();
						$(".pattern-date").hide();
					} else {
						$("#pattern :radio[value='date']").prop("checked", true);
						$(".pattern-count").hide();
						$(".pattern-date").show();
					}
					break;
				case "gift":
					$("#unit").val(row.unit);
					break;
				case "discount":
					if (row.percent) {
						$("#pattern :radio[value='percent']").prop("checked", true);
						$(".pattern-percent").show();
						$(".pattern-discount").hide();
						$("#percent").val(row.percent);
					} else {
						$("#pattern :radio[value='discount']").prop("checked", true);
						$(".pattern-percent").hide();
						$(".pattern-discount").show();
					}
					$("#discount").val(row.discount);
					break;
				}

			} else {
				$("#editor").modal("clear");
			}
		}
	}

	function revoke() {
		var url = "json/Activity_delete";
		var params = {
			ids : []
		};

		$("#data").on("click", ".revoke", function() {
			var id = parseInt($(this).parents("tr").data("row").id);
			
			params.ids = [ id ];
			console.log(params);return false;
			crud.merge(url, params, find);
		});
		$("#revoke-all").on("click", function() {
			params.ids = select().ids;
			console.log(params);return false;
			if (params.ids.length == 0) {
				$.alert("请选择数据");
				return;
			}
			crud.merge(url, params, find);
		});
	}

	function used() {
		var url = "json/Activity_used";
		var params = {
			ids : [],
			used : null,
			type : type
		};
		$("#data").on("click", ".disable", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			params.ids = [ id ];
			params.used = 0;
			crud.merge(url, params, find);
		});
		$("#data").on("click", ".enable", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			params.ids = [ id ];
			params.used = 1;
			crud.merge(url, params, find);
		});

		$("#unused-all").on("click", function() {
			params.ids = select();
			if (params.ids.length == 0) {
				$.alert("请选择数据");
				return;
			}
			params.used = 0;
			crud.merge(url, params, find);
		});
		$("#used-all").on("click", function() {
			params.ids = select();
			if (params.ids.length == 0) {
				$.alert("请选择数据");
				return;
			}
			params.used = 1;
			crud.merge(url, params, find);
		});

	}

	// util
	function select() {
		var result = {
			ids : [],
			kitchenIds : [],
			foodIds : []
		};
		$("#data :checkbox:checked").each(function() {
			var row = $(this).parents("tr").data("row");
			var id = parseInt(row.id);
			var kitchenId = parseInt(row.kitchenId);
			var foodId = parseInt(row.foodId);
			id && ids.push(id);
			kitchenId && kitchenIds.push(id);
			foodId && foodIds.push(id);
		});
		return result;
	}

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
		$("#search-used").on("change", ":radio", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var kitchen = $.trim($("#search-kitchen").val());
		var food = $.trim($("#search-food").val());
		var used = $("#search-used :radio:checked").val();
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Activity_find",
			data : {
				kitchen : kitchen,
				food : food,
				used : used,
				type : type,
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
			$("#check-parent").prop("checked", false);

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td class='kitchenNo'></td>";
			str += "<td class='location'></td>";
			str += "<td class='foodName'></td>";
			str += "<td><div class='begin'></div><div class='end'></div></td>";
			str += "<td class='count'></td>";
			switch (type) {
			case "stop":
				break;
			case "gift":
				str += "<td class='unit'></td>";
				break;
			case "discount":
				str += "<td class='price'></td>";
				str += "<td class='discount'></td>";
				break;
			}
			str += "<td class='used'></td>";
			str += "<td><button class='btn btn-primary btn-small update'>设置</button></td>";
			str += "<td class='delete'></td>";
			str += "</tr>";

			var enable = "<button class='btn btn-success btn-small enable'>启用</button>";
			var disable = "<button class='btn btn-warning btn-small disable'>禁用</button>";
			var revoke = "<button class='btn btn-danger btn-small revoke'>撤销</button>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				// tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".kitchenNo").text(row.kitchenNo);
				tr.find(".location").text(row.location);
				tr.find(".foodName").text(row.foodName);
				tr.find(".begin").text(row.begin ? "从 " + row.begin.replace("T", " ") : "");
				tr.find(".end").text(row.end ? "到 " + row.end.replace("T", " ") : "");
				tr.find(".count").text(row.count || "");

				tr.find(".delete").html(row.type == type.toUpperCase() ? revoke : "");

				switch (type) {
				case "stop":
					tr.find(".used").html(row.used ? disable : enable);
					break;
				case "gift":
					tr.find(".unit").text(row.unit || "");
					tr.find(".used").html(row.used ? disable : enable);
					break;
				case "discount":
					tr.find(".price").text(row.price);
					tr.find(".discount").text(row.discount || "");
					tr.find(".used").html(row.used ? disable : "");// 促销不能立即启用
					break;
				}

				$("#data").append(tr);
			});
		}
	}
});
