<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="js/lib/zhsy/css/progress.css">
<link rel="stylesheet" href="css/module/remote.css">
<title>远程管理</title>
</head>
<body>
	<header>
		<h3>远程管理</h3>
		<nav>
			<div class="inline" id="remote">
				<button class="btn btn-success btn-small" id="correct">校正时间</button>

				<button class="btn btn-primary btn-small" id="boot">重启终端</button>
				<button class="btn btn-danger btn-small" id="shut">关闭终端</button>

				<button class="btn btn-primary btn-small" id="open">启动远端</button>
				<button class="btn btn-danger btn-small" id="close">关闭远端</button>
			</div>
			<ul class="inline" id="check-ctrl">
				<li class="inline"><button class="btn btn-info btn-small" id="check-all">全选</button></li>
				<li class="inline"><button class="btn btn-info btn-small" id="check-inverse">反选</button></li>
			</ul>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="%"><input id="check-parent" type="checkbox"></th>
					<th width="%">终端编号</th>
					<th width="%">终端位置</th>
					<th width="%">终端类型</th>
					<th width="%">模板管理</th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<div id="template">
		<div class="inline dialog-nav">
			<div class="inline group">
				<label class="addon" for="template-name">名称</label><input id="template-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-template">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="30%">模板</th>
						<th width="30%">状态</th>
						<th width="20%">启用</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody id="template-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="template-page"></div>
		</div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/remote"></script>
</body>
</html>
