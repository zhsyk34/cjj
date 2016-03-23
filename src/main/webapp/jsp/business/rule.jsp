<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>订单规则</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/rule.css">
</head>
<body>
	<header>
		<h3>订单规则</h3>
		<nav>
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
					<th width="10%">序号</th>
					<th width="40%">订单编号规则</th>
					<th width="10%">启/禁用</th>
					<th width="30%">编辑</th>
				</tr>
			</thead>
			<tbody id="data">
			</tbody>
		</table>
	</div>

	<!-- edit dialog -->
	<div id="editor">
		<div>
			<input type="hidden" id="id"> <span>订单编号以</span> <input class="text" id="prefix"><span>开头,共</span><input class="text" id="length"><span>位,起始值为</span><input class="text" id="start">
		</div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/business/rule"></script>
</body>
</html>

