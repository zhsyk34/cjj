<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>登录</title>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="css/module/login.css">
</head>
<body>
	<div id="container">
		<form>
			<input id="name" placeholder="用户名"> <input id="password" type="password" placeholder="密码">
			<button id="login" class="btn btn-primary" type="button">登录</button>
		</form>
	</div>
	<script src="js/lib/require.js" data-main="module/login"></script>
</body>
</html>

