<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title>终端一览</title>
</head>
<body>
	<header>
		<h3>终端一览</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%">终端编号</th>
					<th width="20%">终端位置</th>
					<th width="25%">检测时间</th>
					<th width="20%">连线状态</th>
					<!-- <th width="15%">终端画面</th> -->
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/list"></script>
</body>
</html>
