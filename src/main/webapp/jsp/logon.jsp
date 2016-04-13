<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/logon.css">
<title></title>
</head>
<body>
	<div id="container">
		<form>
			<input id="name" placeholder=""> <input id="password" type="password" placeholder="">
			<button id="logon" class="btn btn-primary" type="button">
				logon
			</button>
		</form>
	</div>
	<script src="js/lib/require.js" data-main="module/logon"></script>
</body>
</html>

