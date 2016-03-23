require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	dialog();
	search();
	find();
	remove();

	function dialog() {
		$("#editor").modal({
			width : 360,
			top : 100,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});
		$("#add").on("click", function() {
			$("#editor").modal("title", "增加用户");
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});
		$("table").on("click", ".update", function() {
			$("#editor").modal("title", "修改用户");
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			var row = $("#editor").data("row");
			$("#editor").modal("clear");
			if (row) {
				$("#id").val(row.id);
				$("#name").val(row.name);
			}
		}
	}

	function merge() {
		var id = $("#id").val();
		var name = $.trim($("#name").val());
		var password = $.trim($("#password").val());
		var confirm = $.trim($("#confirm").val());
		var url = id ? "json/User_update" : "json/User_save";

		if (validate.isEmpty(name)) {
			$.alert("用户名不能为空");
			return false;
		}
		if (!id && validate.isEmpty(password)) {
			$.alert("密码不能为空");
			return false;
		}
		if (password != confirm) {
			$.alert("两次输入的密码不一致,请重新确认");
			return false;
		}

		var exist = true;
		var params = {
			id : id,
			name : name,
			password : password
		};
		$.ajax({
			url : "json/User_exist",
			async : false,
			data : params,
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert("该用户名已存在");
			return false;
		}

		crud.merge(url, params, find);
	}

	function remove() {
		var url = "json/User_delete";
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
		var name = $.trim($("#search-name").val());
		var options = $("#page").page("options");
		$.ajax({
			url : "json/User_find",
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
			if (!data.list) {
				return;
			}

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td class='name'></td>";
			str += "<td><button class='btn btn-warning btn-small update'>修改</button><button class='btn btn-danger btn-small del'>删除</button></td>";
			str += "</tr>";

			$.each(data.list, function(index, row) {
				var tr = $(str);
				tr.data("row", row);
				tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".name").text(row.name);

				$("#data").append(tr);
			});
		}
	}
});
