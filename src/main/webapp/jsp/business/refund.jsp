<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>退币处理</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/refund.css">
</head>
<body>
	<header>
		<h3>退币异常处理</h3>
		<nav>
			<table>
				<tr>
					<td width="33%">
						<div class="group">
							<label class="addon" for="authenticode">验证码</label><input class="text" id="authenticode">
						</div>
					</td>
					<td width="33%">
						<div class="group">
							<label class="addon" for="orderNo">订单编号</label><input class="text" id="orderNo">
						</div>
					</td>
					<td width="33%">
						<div class="group">
							<label class="addon" for="terminalNo">终端编号</label> <input class="text" id="terminalNo">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="group">
							<label class="addon" for="reason">异常原因</label> <select class="text" id="reason">
								<option value="">全部</option>
								<option value="machine">机器异常</option>
								<option value="balance">余额不足</option>
							</select>
						</div>
					</td>
					<td>
						<div class="group">
							<label class="addon" for="type">异常类型</label> <select class="text" id="type">
								<option value="">全部</option>
								<option value="lack">找零失败</option>
								<option value="error">退币异常</option>
							</select>
						</div>
					</td>
					<td>
						<div class="group">
							<label class="addon" for="over">处理进度</label> <select class="text" id="over">
								<option value="">全部</option>
								<option value="0">未处理</option>
								<option value="1">已处理</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="inline group">
							<label class="addon" for="begin">开始日期</label> <input class="text" id="begin" readonly>
						</div>
					</td>
					<td>
						<div class="inline group">
							<label class="addon" for="end">结束日期</label> <input class="text" id="end" readonly>
						</div>
					</td>
					<td>
						<button class="btn btn-primary btn-small" id="find">查询</button>
					</td>
				</tr>
			</table>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th width="10%">验证码</th>
					<th width="10%">终端编号</th>
					<th width="10%">订单编号</th>
					<th width="10%">异常原因</th>
					<th width="10%">异常类型</th>
					<th width="10%">应退金额</th>
					<th width="12%">发生时间</th>
					<th width="12%">处理时间</th>
					<th width="11%">操作</th>
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

	<script src="js/lib/require.js" data-main="module/business/refund"></script>
</body>
</html>

