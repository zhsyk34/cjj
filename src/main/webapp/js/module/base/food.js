require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.list($("#data"));

	dialog();

	loadType();
	loadTaste();
	listenMaterial();

	search();
	find();
	remove();

	// show();TODO
	function show() {
		$("#data").on("click", ".info", function() {
			var img = $(this).find("img");

			$("#thumbnail img").attr("src", img.attr("src"));

			var width = img.width();
			var height = img.height();

			$("#thumbnail .modal-dialog").css({
				left : "50%",
				top : "50%"
			})
			// $("#thumbnail").fadeIn();
		});
	}

	function dialog() {
		$("#material").modal({
			top : 100,
			title : "图片选择",
			zIndex : 2020,
			width : 500,
			buttons : null
		});
		$("#load-material").on("click", function() {
			loadMaterial();
			$("#material").modal("open");
		});

		//
		$("#editor").modal({
			width : 800,
			top : 80,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});

		$("#name").on("change", function() {
			$("#show").find(".name").text($.trim($("#name").val()));
		});
		$("#price").on("change", function() {
			$("#show").find(".price").text($.trim($("#price").val()));
		});

		$("#add").on("click", function() {
			$("#editor").modal("title", "增加餐点");
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});

		$("#data").on("click", ".update", function() {
			$("#editor").modal("title", "修改餐点");
			var row = $(this).parents(".info").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			$("#editor").modal("clear");

			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#name").val(row.name);
				$("#typeId").val(row.typeId);
				$("#price").val(row.price);
				$("#materialId").val(row.materialId);
				$("#introduction").val(row.introduction);

				// taste array
				$.each(row.tasteList || [], function(index, taste) {
					$("#taste").find(":checkbox[value='" + taste.id + "']").prop("checked", true);
				});
				// show
				$("#show").find("img").attr({
					"src" : row.path,
					"alt" : row.name
				});
				$("#show").find(".name").text(row.name);
				$("#show").find(".price").text(row.price);
			} else {
				$("#show").find("img").attr({
					"src" : "",
					"alt" : ""
				});
				$("#show").find(".name").text("");
				$("#show").find(".price").text("");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var name = $.trim($("#name").val());
		var typeId = parseInt($("#typeId").val());
		var price = parseFloat($("#price").val());
		var materialId = parseInt($("#materialId").val());
		var introduction = $.trim($("#introduction").val());

		var tasteIds = [];
		$("#taste").find(":checkbox:checked").each(function() {
			var tasteId = $(this).val();
			tasteIds.push(tasteId);
		});

		if (validate.isEmpty(name)) {
			$.alert("餐点名称不能为空");
			return false;
		}
		if (!validate.isPositive(price, true)) {
			$.alert("请填写正确的价格");
			return false;
		}
		if (!validate.isNatural(materialId)) {
			$.alert("请选择素材");
			return false;
		}

		var params = {
			id : id,
			name : name,
			typeId : typeId,
			price : price,
			materialId : materialId,
			introduction : introduction,
			tasteIds : tasteIds
		};

		var url = id ? "json/Food_update" : "json/Food_save";

		var exist = true;
		$.ajax({
			url : "json/Food_exist",
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

	function remove() {
		var url = "json/Food_delete";
		$("#data").on("click", ".del", function() {
			var row = $(this).parents(".info").data("row");
			var id = parseInt(row.id);
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$('#data :checkbox:checked').each(function() {
				var row = $(this).parents(".info").data("row");
				var id = parseInt(row.id);
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
		$("header :radio").on("change", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var name = $.trim($("#search-name").val());
		var typeId = parseInt($("#search-type").val()) || 0;

		var options = $("#page").page("options");

		var params = {
			name : name,
			typeId : typeId,
			pageNo : options.pageNo,
			pageSize : options.pageSize
		};

		$.ajax({
			url : "json/Food_find",
			data : params,
			success : function(data) {
				crud.page(data, find);
				load(data);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<div class='info'>";

			str += "<div class='wrap'>";
			str += "<img>";
			str += "<div class='desc'>";
			str += "<label class='name'></label>";
			str += "<label class='price'></label>";
			str += "</div>";
			str += "</div>";

			str += "<div class='operation'>";
			str += "<label class='inline'><input type='checkbox'>选择</label>";
			str += "<button class='btn btn-small btn-warning update'>修改</button>";
			str += "<button class='btn btn-small btn-danger del'>删除</button>";
			str += "</div>";

			str += "</div>";

			$.each(data.list || [], function(index, row) {
				var div = $(str).data("row", row);
				div.find("img").attr("src", row.path);
				div.find(".name").text(row.name);
				div.find(".price").text(row.price);
				$("#data").append(div);
			});
		}
	}

	function listenMaterial() {
		crud.page(null, loadMaterial, $("#material-page"));

		$("#find-material").on("click", function() {
			$("#material-page").page({
				pageNo : 1
			});
			loadMaterial();
		});

		$("#material").on("click", ":radio", function() {
			var material = $(this).parents("tr").data("row");
			$("#materialId").val(material.id);
			$("#show").find("img").attr({
				"src" : material.path
			});
		});

	}

	function loadMaterial() {
		var name = $.trim($("#material-name").val());
		var options = $("#material-page").page("options");

		var params = {
			name : name,
			type : "image",
			pageNo : options.pageNo,
			pageSize : options.pageSize
		}

		var materialId = parseInt($("#materialId").val()) || 0;

		var tbody = $("#material-data").empty();

		var str = "<tr>";
		str += "<td><input type='radio' name='materialId'></td>";
		str += "<td class='materialName'></td>";
		str += "<td><img class='inline'></td>";
		str += "</tr>";

		$.ajax({
			url : "json/Material_find",
			async : false,
			data : params,
			success : function(data) {
				var list = data.list || [];
				for (var i = 0, len = list.length; i < len; i++) {
					var material = list[i];
					var tr = $(str).data("row", material);
					tr.find(":radio").val(material.id).prop("checked", material.id == materialId);
					tr.find(".materialName").text(material.name);
					tr.find("img").attr("src", material.path);
					tbody.append(tr);
				}
				// tbody.find(":radio[value='" + materialId +
				// "']").prop("checked", true);
				crud.page(data, loadMaterial, $("#material-page"));
			}
		});
	}

	function loadType() {
		var select = $("#search-type,#typeId").empty();
		$("#search-type").append("<option value='0' selected>全部</option>");

		$.ajax({
			url : "json/Food_findType",
			success : function(data) {
				$.each(data.list || [], function(index, row) {
					var option = $("<option></option>");
					option.val(row.id);
					option.text(row.name);
					select.append(option);
				});
			}
		});
	}

	function loadTaste() {
		var map = {};
		var div = $("#taste").empty();

		$.ajax({
			url : "json/Food_findTaste",
			success : function(data) {
				$.each(data.list || [], function(index, taste) {
					var key = taste.styleId;
					map[key] = map[key] || [];
					map[key].push(taste);
				});
				$.each(map, function(index, list) {
					var dl = $("<dl></dl>");
					$("<dt></dt>").text(index).appendTo(dl);
					$.each(list, function(id, taste) {
						var dd = "<dd class='inline'>";
						dd += "<label class='inline'>";
						dd += "<input type='checkbox' value='" + taste.id + "'>";
						dd += taste.name;
						dd += "</label></dd>";
						dl.append(dd);
					});
					div.append(dl);
				});
			}
		});
	}
});
