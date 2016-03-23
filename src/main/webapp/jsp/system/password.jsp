<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>密码修改</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>密码修改</h3>

	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover table-edit">
			<thead>
				<tr>
					<th width="20%">登录用户<input type="hidden" id="id" value="<s:property value='%{#session.user.id}' />"></th>
					<td width="80%"><s:property value="%{#session.user.name}" /></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>原 密 码</th>
					<td><input class="text" type="password" id="original"></td>
				</tr>
				<tr>
					<th>新 密 码</th>
					<td><input class="text" type="password" id="password"></td>
				</tr>
				<tr>
					<th>确认密码</th>
					<td><input class="text" type="password" id=confirm></td>
				</tr>
			</tbody>
		</table>
	</div>

	<footer>
		<button class="btn btn-success" id="sure">确定</button>
		<button class="btn btn-info" id="reset">重置</button>
	</footer>

	<script src="js/lib/require.js" data-main="module/system/password"></script>
</body>
</html>

