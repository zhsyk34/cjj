<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>首页</title>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="css/module/index.css">
</head>
<body>
	<div id="container">
		<nav id="nav">
			<div id="logo"></div>
			<div id="welcome">
				欢迎<span><s:property value="#session.user.name" /> </span>登录
			</div>
			<div class="accordion">
				<p>数据管理</p>
				<ul>
					<li><a href="jsp/base/food.jsp">餐点管理</a></li>
					<li><a href="jsp/base/taste.jsp">口味管理</a></li>
					<li><a href="jsp/base/style.jsp">口味类型</a></li>
					<li><a href="jsp/base/type.jsp">类型管理</a></li>
					<li><a href="jsp/base/material.jsp">素材管理</a></li>
					<li><a href="jsp/base/marquee.jsp">跑 马 灯</a></li>
					<li><a href="jsp/base/template.jsp">模板管理</a></li>
					<li><a href="jsp/base/seat.jsp">座位管理</a></li>
				</ul>
				<p>营业管理</p>
				<ul>
					<li><a href="jsp/business/rule.jsp">订单规则</a></li>
					<li><a href="jsp/business/order.jsp">订单明细</a></li>
					<li><a href="jsp/business/sell.jsp">销售统计</a></li>
					<li><a href="jsp/business/refund.jsp">退币管理</a></li>
					<li><a href="jsp/business/stop.jsp">停售管理</a></li>
					<li><a href="jsp/business/gift.jsp">赠品管理</a></li>
					<li><a href="jsp/business/discount.jsp">促销管理</a></li>
				</ul>
				<p>终端管理</p>
				<ul>
					<li><a href="jsp/device/terminal.jsp">终端信息</a></li>
					<li><a href="jsp/device/setting.jsp">终端设置</a></li>
					<li><a href="jsp/device/list.jsp">终端一览</a></li>
					<li><a href="jsp/device/monitor.jsp">监控画面</a></li>
					<li><a href="jsp/device/record.jsp">连线记录</a></li>
					<li><a href="jsp/device/remote.jsp">远程管理</a></li>
				</ul>
				<p>系统管理</p>
				<ul>
					<li><a href="jsp/system/config.jsp">系统设置</a></li>
					<li><a href="jsp/system/cashbox.jsp">钱箱设置</a></li>
					<li><a href="jsp/system/user.jsp">用户管理</a></li>
					<li><a href="jsp/system/password.jsp">密码修改</a></li>
					<li id="logout">安全退出</li>
				</ul>
			</div>
		</nav>
		<div id="content">
			<iframe src="jsp/device/list.jsp"></iframe>
		</div>
	</div>

	<div id="footer">
		<div id="version">
			<span>版本:</span>
			<s:property value="#application.version" />
		</div>
		<div id="serverId">
			<span>编号:</span>
			<s:property value="#application.serverid" />
		</div>
		<span id="date"></span>
	</div>

	<script src="js/lib/require.js" data-main="module/index"></script>
</body>
</html>

