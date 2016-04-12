<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="logon-title" /></title>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="css/module/login.css">
</head>
<body>
	<div id="container">
		<form>
			<input id="name" placeholder="<s:text name='logon-name' />"> <input id="password" type="password" placeholder="<s:text name='logon-password' />">
			<button id="login" class="btn btn-primary" type="submit">
				<s:text name="logon-submit" />
			</button>
		</form>
	</div>
	<script src="js/lib/require.js" data-main="module/login"></script>
</body>
</html>

