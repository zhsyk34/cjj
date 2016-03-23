<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<title>终端信息</title>
</head>
<body>
	<header>
		<h3>终端信息</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-terminalNo">终端编号</label><input class="text" id="search-terminalNo">
				</div>
				<div class="inline group">
					<label class="addon">终端类型</label>
					<div class="text" id="search-type">
						<label><input type="radio" name="search-type" value="" checked>全部</label> <label><input type="radio" name="search-type" value="shop">客户端</label> <label><input type="radio" name="search-type" value="kitchen">厨房端</label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
				<div class="inline">
					<button class="btn btn-success btn-small" id="add">增加</button>
				</div>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%">编号</th>
					<th width="15%">类型</th>
					<th width="20%">位置</th>
					<th width="15%">版本</th>
					<th width="30%">编辑</th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<div id="editor">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="25%">编号</th>
					<td width="75%"><input id="id" type="hidden"><input class="text" id="terminalNo"></td>
				</tr>
				<tr>
					<th>类型</th>
					<td>
						<div id="type">
							<label><input type="radio" name="type" value="shop">客户端</label> <label><input type="radio" name="type" value="kitchen">厨房端</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>位置</th>
					<td><input class="text" id="location"></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/device/terminal"></script>
</body>
</html>
