<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>系统设置</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>系统设置</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="16%">取餐方式</th>
					<td><label><input type="radio" name="takeShow" value="0">不显示</label> <label><input type="radio" name="takeShow" value="1">显示</label> <select class="text" id="takeAway">
							<option value="0">内用</option>
							<option value="1">外带</option>
					</select></td>
				</tr>
				<tr>
					<th>桌号显示</th>
					<td><label><input type="radio" name="seat" value="0">不显示</label> <label><input type="radio" name="seat" value="1">显示</label></td>
				</tr>
				<tr>
					<th>分类显示</th>
					<td><label><input type="radio" name="foodType" value="0">不显示</label> <label><input type="radio" name="foodType" value="1">显示</label></td>
				</tr>
				<tr>
					<th>列印单据</th>
					<td><label><input type="checkbox" id="shopOrder">客户端</label> <label><input type="checkbox" id="kitchenOrder">厨房端</label></td>
				</tr>
				<tr class="pay">
					<th>支付方式</th>
					<td><label><input type="checkbox" id="cash">现金</label> <label><input type="checkbox" id="creditcard">信用卡</label> <label><input type="checkbox" id="wechat">微信</label> <label><input type="checkbox" id="alipay">支付宝</label> <label><input type="checkbox" id="member">会员</label> <label><input type="checkbox" id="metrocard">一卡通</label> <label><input type="checkbox" id="easycard">悠游卡</label></td>
				</tr>
				<tr>
					<th>附加费</th>
					<td>
						<div>
							<label><input type="radio" name="accessory" value="0">禁用</label> <label><input type="radio" name="accessory" value="1">启用</label>
						</div>
						<div>
							<div class="group accessory">
								<label class="addon" for="accessoryName">名称</label><input class="text" id="accessoryName">
							</div>
						</div>
						<div>
							<div class="group accessory">
								<label class="addon" for="accessoryPercent">比例</label><input class="text" id="accessoryPercent">
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<footer>
			<button class="btn btn-success" id="sure">确定</button>
			<button class="btn btn-info" id="reset">重置</button>
		</footer>
	</div>

	<script src="js/lib/require.js" data-main="module/system/config"></script>
</body>
</html>

