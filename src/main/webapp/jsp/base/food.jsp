<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/food.css">
<title>餐点</title>
</head>
<body>
	<header>
		<h3>餐点</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-name">名称</label><input id="search-name" class="text">
			</div>
			<div class="inline group">
				<label class="addon" for="search-type">类型</label> <select class="text" id="search-type">
				</select>
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">查询</button>
				<button class="btn btn-success btn-small" id="add">增加</button>
			</div>

			<ul class="inline" id="check-ctrl">
				<li class="inline"><button class="btn btn-info btn-small" id="check-all">全选</button></li>
				<li class="inline"><button class="btn btn-info btn-small" id="check-inverse">反选</button></li>
				<li class="inline"><button class="btn btn-info btn-small" id="check-cancel">取消选择</button></li>
				<li class="inline"><button class="btn btn-danger btn-small" id="del-all">删除所选</button></li>
			</ul>
		</nav>
	</header>
	<div class="main">
		<div id="data"></div>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<!-- material dialog-->
	<div id="material">
		<div class="inline dialog-nav">
			<div class="inline group">
				<label class="addon" for="material-name">名称</label><input id="material-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-material">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="20%">选择</th>
						<th width="45%">名称</th>
						<th width="35%">图片</th>
					</tr>
				</thead>
				<tbody id="material-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="material-page"></div>
		</div>
	</div>

	<!-- editor dialog -->
	<div id="editor">
		<table class="table table-edit">
			<tbody>
				<tr>
					<td id="show"><div class="info">
							<div class="wrap">
								<img src="" alt="">
								<div class="desc">
									<label class="name"></label> <label class="price"></label>
								</div>
							</div>
						</div></td>
					<td><table class="table table-edit table-hover">
							<tbody>
								<tr>
									<th>名称</th>
									<td><input id="id" type="hidden"><input class="text" id="name"></td>
								</tr>
								<tr>
									<th>简称</th>
									<td><input class="text" id="abbreviation"></td>
								</tr>
								<tr>
									<th>别名</th>
									<td><input class="text" id="nickname"></td>
								</tr>
								<tr>
									<th>类型</th>
									<td><select class="text" id="typeId">
									</select></td>
								</tr>
								<tr>
									<th>单价</th>
									<td><input class="text" id="price"></td>
								</tr>
								<tr>
									<th>图片</th>
									<td><input type="hidden" id="materialId">
										<button class="btn btn-primary" id="load-material">选择</button></td>
								</tr>
								<tr>
									<th>介绍</th>
									<td><textarea class="text" id="introduction" rows="5" cols="50"></textarea></td>
								</tr>
								<tr>
									<th>调味</th>
									<td><div id="taste"></div></td>
								</tr>
							</tbody>
						</table></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/base/food"></script>
</body>
</html>
