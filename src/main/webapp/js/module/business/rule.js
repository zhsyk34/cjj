require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	crud.page(null, find);
	dialog();
	find();
	remove();
	used();

	function dialog() {
		$("#editor").modal({
			width : 500,
			top : 120,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});
		$("#add").on("click", function() {
			$("#editor").modal("title", "增加规则");
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});
		$("table").on("click", ".update", function() {
			$("#editor").modal("title", "修改规则");
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#prefix").val(row.prefix);
				$("#length").val(row.length);
				$("#start").val(row.start);
			} else {
				$("#editor").modal("clear");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var prefix = $.trim($("#prefix").val());
		var length = parseInt($("#length").val());
		var start = parseInt($("#start").val());
		var url = id ? "json/OrderRule_update" : "json/OrderRule_save";

		if (validate.isEmpty(prefix)) {
			$.alert("订单编号开头不能为空");
			return false;
		}

		if (!validate.isNatural(length)) {
			$.alert("订单编号必须大于0位");
			return false;
		}
		if (!validate.isNatural(start)) {
			$.alert("订单编号起始值必须为正整数");
			return false;
		}

		var params = {
			id : id,
			prefix : prefix,
			length : length,
			start : start
		};

		crud.merge(url, params, find);
	}

	function used() {
		$("#data").on("click", ".switch", function() {
			var url = "json/OrderRule_used";
			var row = $(this).parents("tr").data("row");
			var id = row.id;
			var used = !row.used;

			var params = {
				id : id,
				used : used
			};
			crud.merge(url, params, find);
		});
	}

	function remove() {
		var url = "json/OrderRule_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$('#data :checkbox:checked').each(function() {
				var id = parseInt($(this).val());
				ids.push(id);
			});
			crud.del(url, ids, find);
		});
	}

	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/OrderRule_find",
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
			$("#check-parent").prop("checked", false);

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td><span>订单编号以<span><span class='prefix'></span><span>开头,共</span><span class='length'></span><span>位,起始值为</span><span class='start'></span></td>";
			str += "<td class='used'></td>";
			str += "<td><button class='switch btn btn-small'></button><button class='btn btn-warning btn-small update'>修改</button><button class='btn btn-danger btn-small del'>删除</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".prefix").text(row.prefix);
				tr.find(".length").text(row.length);
				tr.find(".start").text(row.start);
				tr.find(".used").text(row.used ? "启用" : "禁用");
				tr.find(".switch").addClass(row.used ? "btn-info" : "btn-success").text(row.used ? "禁用" : "启用");

				$("#data").append(tr);
			});
		}
	}
});
