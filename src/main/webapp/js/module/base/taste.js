require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	dialog();
	search();
	find();
	remove();

	function dialog() {
		$("#editor").modal({
			width : 300,
			top : 100,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});
		$("#add").on("click", function() {
			$("#editor").modal("title", "增加口味");
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});
		$("table").on("click", ".update", function() {
			$("#editor").modal("title", "修改口味");
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#name").val(row.name);
				$("#style").val(row.styleId);
			} else {
				$("#editor").modal("clear");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var name = $.trim($("#name").val());
		var styleId = $("#style").val();
		var url = id ? "json/Taste_update" : "json/Taste_save";

		if (validate.isEmpty(name)) {
			$.alert("口味名称不能为空");
			return false;
		}

		var params = {
			id : id,
			name : name,
			styleId : styleId
		};

		var exist = true;
		$.ajax({
			url : "json/Taste_exist",
			async : false,
			data : params,
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert("该名称已存在");
			return false;
		}

		crud.merge(url, params, find);
	}

	function loadStyle() {
		var select = $("#style").empty();
		$.ajax({
			url : "json/Taste_findStyle",
			success : function(data) {
				$.each(data.list || [], function(index, row) {
					var option = $("<option></option>");
					option.val(row.id).text(row.name);
					select.append(option);
				});
			}
		});
	}

	function remove() {
		var url = "json/Taste_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$("#data :checkbox:checked").each(function() {
				var id = parseInt($(this).val());
				ids.push(id);
			});
			crud.del(url, ids, find);
		});
	}

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		loadStyle();
		var name = $.trim($("#search-name").val());
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Taste_find",
			data : {
				name : name,
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
			str += "<td class='name'></td>";
			str += "<td class='style'></td>";
			str += "<td><button class='btn btn-warning btn-small update'>修改</button><button class='btn btn-danger btn-small del'>删除</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".name").text(row.name);
				tr.find(".style").text(row.styleName);
				$("#data").append(tr);
			});
		}
	}
});
