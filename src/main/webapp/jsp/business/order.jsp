<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/order.css">
<title>订单明细</title>
</head>
<body>
	<header>
		<h3>订单明细</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-orderNo">订单编号</label><input class="text" id="search-orderNo">
				</div>
				<div class="inline group">
					<label class="addon" for="search-shop">客户端</label> <input class="text" id="search-shop">
				</div>
				<div class="inline group">
					<label class="addon" for="search-kitchen">厨房端</label> <input class="text" id="search-kitchen">
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon">订单来源</label>
					<div class="text">
						<label><input type="radio" name="from" value="" checked>全部</label> <label><input type="radio" name="from" value="0">终端</label> <label><input type="radio" name="from" value="-1">后台</label>
					</div>
				</div>
				<div class="inline group">
					<label class="addon">订单状态</label>
					<div class="text">
						<label><input type="radio" name="status" value="all" checked>全部</label> <label><input type="radio" name="status" value="normal">正常</label> <label><input type="radio" name="status" value="nullify">取消</label>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="6%">序号</th>
					<th width="10%">订单编号</th>
					<th width="10%">客户端</th>
					<th width="10%">厨房端</th>
					<th width="10%">订单明细</th>
					<th width="8%">总价</th>
					<th width="8%">收取</th>
					<th width="8%">找零</th>
					<th width="15%">时间</th>
					<th width="15%">编辑</th>
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

	<div id="editor">
		<div class="inline order-header">
			<div>
				<div class="inline group">
					<label class="addon">订单编号</label>
					<div class="text" id="orderNo"></div>
				</div>
				<div class="inline group">
					<label class="addon">客户端</label>
					<div class="text" id="shop"></div>
				</div>
				<div class="inline group">
					<label class="addon">厨房端</label>
					<div class="text" id="kitchen"></div>
				</div>
			</div>

			<div class="money">
				<div class="inline group">
					<label class="addon" for="total">总价</label> <input class="text" id="total">
				</div>
				<div class="inline group">
					<label class="addon" for="income">收取</label> <input class="text" id="income">
				</div>
				<div class="inline group">
					<label class="addon" for="expense">找零</label> <input class="text" id="expense">
				</div>
				<button class="btn btn-success btn-small" id="add">添加</button>
			</div>
		</div>

		<div class="main">
			<table class="table table-edit table-hover">
				<thead>
					<tr class="detail">
						<th>订单明细</th>
						<td>
							<div class="inline group">
								<label class="addon">餐点</label> <input class="text food">
							</div> <!-- <button class="btn btn-small btn-primary">查询</button>
							-->

							<div class="inline group">
								<label class="addon">调味</label> <input class="text taste">
							</div> <!-- 
							<button class="btn btn-small btn-primary">选择</button>
							 -->

							<div class="inline group">
								<label class="addon">价格</label><input class="text price">
							</div>
							<div class="inline group">
								<label class="addon">数量</label><input class="text count">
							</div>
							<button class="btn btn-small btn-danger cancel">删除</button>
						</td>
					</tr>
				</thead>
				<tbody id="details">
				</tbody>
			</table>
		</div>
	</div>

	<!-- food -->
	<!-- 
	<div id="food-dialog">
		<div class="inline dialog-nav">
			<div class="inline group">
				<label class="addon" for="food-name">名称</label><input id="food-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-food">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="food-parent" type="checkbox"></th>
						<th width="">序号</th>
						<th width="">名称</th>
						<th width="">价格</th>
						<th width="">图片</th>
						<th width="">调味</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="food-page"></div>
		</div>
	</div>
 -->

	<script src="js/lib/require.js" data-main="module/business/order"></script>
</body>
</html>
