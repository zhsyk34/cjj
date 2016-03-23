<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title>连线记录</title>
</head>
<body>
	<header>
		<h3>连线记录</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="begin">开始日期</label> <input class="text" id="begin" readonly>
				</div>
				<div class="inline group">
					<label class="addon" for="end">结束日期</label> <input class="text" id="end" readonly>
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon" for="terminalNo">终端编号</label><input class="text" id="terminalNo">
				</div>
				<div class="inline group">
					<label class="addon">连线状态</label>
					<div class="text" id="online">
						<label><input type="radio" name="online" value="" checked>全部</label> <label><input type="radio" name="online" value="true">在线</label> <label><input type="radio" name="online" value="false">离线</label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%">终端编号</th>
					<th width="20%">终端位置</th>
					<th width="20%">IP地址</th>
					<th width="20%">检测时间</th>
					<th width="20%">连线状态</th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/record"></script>
</body>
</html>
