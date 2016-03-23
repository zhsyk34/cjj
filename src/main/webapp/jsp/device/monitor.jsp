<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title>监控画面</title>
</head>
<body>
	<header>
		<h3>监控画面</h3>
		<nav>
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
		<div id="data"></div>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/monitor"></script>
</body>
</html>
