<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/order.css">
<title>销售统计</title>
</head>
<body>
	<header>
		<h3>销售统计</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="shop">客户端</label> <input class="text" id="shop">
				</div>
				<div class="inline group">
					<label class="addon" for="kitchen">厨房端</label> <input class="text" id="kitchen">
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
				<div class="inline">
					<!-- <button class="btn btn-success btn-small" id="export">导出</button> -->
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon" for="begin">开始日期</label> <input class="text" id="begin" readonly>
				</div>
				<div class="inline group">
					<label class="addon" for="end">结束日期</label> <input class="text" id="end" readonly>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="yesterday">昨日</button>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="today">今日</button>
				</div>
				<div class="inline">
					<button class="btn btn-info btn-small" id="preweek">上周</button>
				</div>
				<div class="inline">
					<button class="btn btn-info btn-small" id="week">本周</button>
				</div>
				<div class="inline">
					<button class="btn btn-warning btn-small" id="premonth">上月</button>
				</div>
				<div class="inline">
					<button class="btn btn-warning btn-small" id="month">本月</button>
				</div>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%" rowspan="2">客户端</th>
					<th width="10%" rowspan="2">厨房端</th>
					<th width="50%" colspan="4">订单内容</th>
					<th width="15%" rowspan="2">合计</th>
					<th width="15%" rowspan="2">查看</th>
				</tr>
				<tr>
					<th width="10%">序号</th>
					<th width="20%">餐点</th>
					<th width="10%">数量</th>
					<th width="10%">总价</th>
				</tr>
			</thead>
			<tbody id="data">
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/business/sell"></script>
</body>
</html>
