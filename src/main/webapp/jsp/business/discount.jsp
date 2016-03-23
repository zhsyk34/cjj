<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>促销管理</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/activity.css">
</head>
<body>
	<header>
		<h3>促销管理</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-kitchen">厨房编号</label><input id="search-kitchen" class="text">
				</div>
				<div class="inline group">
					<label class="addon" for="search-food">餐点名称</label><input id="search-food" class="text">
				</div>
				<div class="inline group">
					<label class="addon">活动状态</label>
					<div class="text" id="search-used">
						<label><input type="radio" name="search-used" value="" checked>全部</label> <label><input type="radio" name="search-used" value="0">未活动</label> <label><input type="radio" name="search-used" value="1">进行中</label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
			</div>
			<div>
				<ul class="inline">
					<li class="inline"><button class="btn btn-success btn-small" id="used-all">批量启用</button></li>
					<li class="inline"><button class="btn btn-warning btn-small" id="unused-all">批量禁用</button></li>
					<li class="inline"><button class="btn btn-primary btn-small" id="update-all">批量设置</button></li>
					<li class="inline"><button class="btn btn-danger btn-small" id="revoke-all">批量撤销</button></li>
				</ul>
				<ul class="inline" id="check-ctrl">
					<li class="inline"><button class="btn btn-info btn-small" id="check-all">全选</button></li>
					<li class="inline"><button class="btn btn-info btn-small" id="check-inverse">反选</button></li>
					<li class="inline"><button class="btn btn-info btn-small" id="check-cancel">取消选择</button></li>
				</ul>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="5%"><input id="check-parent" type="checkbox"></th>
					<th width="5%">序号</th>
					<th width="10%">厨房编号</th>
					<th width="10%">厨房位置</th>
					<th width="10%">餐点</th>
					<th width="20%">停售时间</th>
					<th width="10%">数量</th>
					<th width="10%">原价</th>
					<th width="10%">促销价</th>
					<th width="10%">启用</th>
					<th width="10%">设置</th>
					<th width="10%">撤销</th>
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
				<th>开始日期</th>
				<td><input class="text" id="begin"></td>
			</tr>
			<tr>
				<th>结束日期</th>
				<td><input class="text" id="end"></td>
			</tr>
			<tr>
				<th>促销数量</th>
				<td><input class="text" id="count"></td>
			</tr>
			<tr>
				<th>促销方式</th>
				<td id="pattern"><label><input type="radio" name="pattern" value="discount" checked>降价</label> <label><input type="radio" name="pattern" value="percent">打折</label></td>
			</tr>
			<tr class="pattern-discount">
				<th>促销价格</th>
				<td><input class="text" id="discount"></td>
			</tr>
			<tr class="pattern-percent">
				<th>促销折扣</th>
				<td><input class="text" id="percent"><span>%</span></td>
			</tr>
			<tr>
				<th>是否启用</th>
				<td id="used"><label><input type="radio" name="used" value="1" checked>是</label> <label><input type="radio" name="used" value="0">否</label></td>
			</tr>
		</table>
	</div>
	<script src="js/lib/require.js" data-main="module/business/activity"></script>
</body>
</html>

