<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/material.css">
<title>素材管理</title>
</head>
<body>
	<!-- upload and search -->
	<header>
		<h3>素材</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-name">名称</label><input id="search-name" class="text">
				</div>
				<div class="inline group">
					<label class="addon">类型</label>
					<div class="text" id="search-type">
						<label><input type="radio" name="search-type" value="" checked>全部</label> <label><input type="radio" name="search-type" value="image">图片</label> <label><input type="radio" name="search-type" value="video">视频</label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">查询</button>
				</div>
				<div class="inline">
					<button class="btn btn-success btn-small" id="add">上传</button>
				</div>
			</div>
			<div>
				<ul class="inline" id="check-ctrl">
					<li class="inline"><button class="btn btn-info btn-small" id="check-all">全选</button></li>
					<li class="inline"><button class="btn btn-info btn-small" id="check-inverse">反选</button></li>
					<li class="inline"><button class="btn btn-info btn-small" id="check-cancel">取消选择</button></li>
					<li class="inline"><button class="btn btn-danger btn-small" id="del-all">删除所选</button></li>
				</ul>
			</div>
		</nav>
	</header>

	<div class="main">
		<div id="data"></div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<!-- upload dialog -->
	<div id="upload">
		<form method="post" enctype="multipart/form-data" action="json/Material_save">
			<table class="table table-hover table-edit">
				<thead>
					<tr>
						<th width="40%">请选择上传素材</th>
						<th width="35%">素材名称</th>
						<th width="25%"><button class="btn btn-primary btn-small" id="append" type="button">增加</button></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label class="btn btn-default btn-small file">浏览...<input type="file" name="upload"></label><span class="path-info inline"></span></td>
						<td><input name="nameList" class="text"></td>
						<th><button class="btn btn-info btn-small clean" type="button">重置</button></th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<!-- preview -->
	<div id="preview">
		<img>
		<object type="application/x-shockwave-flash" data="tool/vcastr/vcastr3.swf">
			<param name="movie" value="tool/vcastr/vcastr3.swf">
			<param name="wmode" value="opaque">
			<param name="allowFullScreen" value="true">
			<param name="FlashVars" id="flash">
		</object>
	</div>

	<!-- eidt dialog -->
	<div id="editor">
		<table class="table table-hover table-edit">
			<thead>
				<tr>
					<th>素材名称</th>
					<td><input type="hidden" id="id"><input class="text" id="name"></td>
				</tr>
			</thead>
		</table>
	</div>
	<script src="js/lib/require.js" data-main="module/base/material"></script>
</body>
</html>
