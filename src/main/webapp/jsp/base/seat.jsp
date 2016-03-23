<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>座位</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
</head>
<body>
	<header>
		<h3>座位</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="seat-name">名称</label><input id="seat-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">查询</button>
			</div>
			<div class="inline">
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

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%"><input id="check-parent" type="checkbox"></th>
					<th width="15%">序号</th>
					<th width="35%">名称</th>
					<th width="40%">编辑</th>
				</tr>
			</thead>
			<tbody id="data">
			</tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<!-- edit dialog -->
	<div id="editor">
		<table class="table table-hover table-edit">
			<tr>
				<th>名称<input type="hidden" id="id">
				</th>
				<td><input class="text" id="name"> <label class="btn btn-small btn-primary" id="batch">批量</label></td>
			</tr>
			<tr class="begin">
				<th>开始</th>
				<td><input class="text" id="begin"></td>
			</tr>
			<tr class="end">
				<th>结束</th>
				<td><input class="text" id="end"></td>
			</tr>
		</table>
	</div>
	<script src="js/lib/require.js" data-main="module/base/seat"></script>
</body>
</html>

