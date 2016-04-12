<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!-- <%@ page import="com.baiyi.order.model.User"%> -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	String dialect = request.getLocale().toString();
	/*
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect(basePath + "jsp/logon.jsp");
	}
	*/
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="<%=basePath%>" />
<script src="js/lib/require.config.js"></script>
<link rel="stylesheet" href="css/lib/init.css">
</head>