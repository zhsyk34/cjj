<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/remote.css">
<title>终端设置</title>
</head>
<body>
	<header>
		<h3>终端设置</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%">终端编号</th>
					<th width="10%">列印发票</th>
					<th width="10%">远端</th>
					<th width="20%">开机时间</th>
					<th width="20%">关机时间</th>
					<th width="10%">启用关机</th>
					<th width="10%">编辑</th>
					<th width="10%">座位管理</th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<!-- seats -->
	<div id="seat">
		<table class="table table-edit table-hover">
			<tr>
				<th width="15%">终端编号</th>
				<td width="85%"><span id="seat-terminal"></span> <label><input type="checkbox" id="seat-parent">全选</label></td>
			</tr>
			<tr>
				<th>座位选择</th>
				<td><ul></ul></td>
			</tr>
		</table>
	</div>

	<div id="editor">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="20%">终端编号<input id="id" type="hidden"></th>
					<td width="80%"><span id="terminalNo"></span></td>
				</tr>
				<tr>
					<th>列印发票</th>
					<td>
						<div id="invoice">
							<label><input type="radio" name="invoice" value="0">否</label> <label><input type="radio" name="invoice" value="1">是</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>远端号码</th>
					<td><input class="text" id="teamViewer"></td>
				</tr>
				<tr>
					<th>时间设置</th>
					<td>
						<table class="table times">
							<thead>
								<tr>
									<th width="30%">序号</th>
									<th width="35%">开机时间</th>
									<th width="35%">关机时间</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>时间一</td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
								<tr>
									<td>时间二</td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
								<tr>
									<td>时间三</td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<th>启用关机</th>
					<td>
						<div id="shut">
							<label><input type="radio" name="shut" value="0">否</label> <label><input type="radio" name="shut" value="1">是</label>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/device/setting"></script>
</body>
</html>
