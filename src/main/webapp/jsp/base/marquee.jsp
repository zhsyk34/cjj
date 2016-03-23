<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>跑马灯</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="js/lib/zhsy/css/slider.css">
<link rel="stylesheet" href="js/lib/spectrum/spectrum.css">
<link rel="stylesheet" href="css/module/marquee.css">
</head>
<body>
	<header>
		<h3>跑马灯</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-title">标题</label><input id="search-title" class="text">
			</div>
			<div class="inline group">
				<label class="addon" for="search-content">内容</label><input id="search-content" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">查询</button>
			</div>
			<div class="inline">
				<button class="btn btn-success btn-small" id="add">增加</button>
			</div>
			<ul class="inline" id="check-ctrl">
				<li class="inline"><button class="btn btn-info btn-small" id="check-all">全选</button></li>
				<li class="inline"><button class="btn btn-info btn-small" id="check-inverse">反选</button></li>
				<li class="inline"><button class="btn btn-info btn-small" id="check-cancel">取消选择</button></li>
				<li class="inline"><button class="btn btn-danger btn-small" id="del-all">删除所选</button></li>
			</ul>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width=""><input id="check-parent" type="checkbox"></th>
					<th width="">序号</th>
					<th width="">标题</th>
					<th width="">内容</th>
					<th width="">方向</th>
					<th width="">速度</th>
					<th width="">字体</th>
					<th width="">字号</th>
					<th width="">颜色</th>
					<th width="">背景色</th>
					<th width="">编辑</th>
				</tr>
			</thead>
			<tbody id="data">
			</tbody>
		</table>
	</div>

	<div id="editor">
		<table class="table table-hover table-edit">
			<tr>
				<th>预览</th>
				<td>
					<div id="wrap">
						<div id="preview"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th width="20%">标题<input id="id" type="hidden"></th>
				<td width="80%"><input class="text" id="title"></td>
			</tr>
			<tr>
				<th>内容</th>
				<td><textarea id="content" rows="3" cols="60" class="text"></textarea></td>
			</tr>
			<tr>
				<th>方向</th>
				<td id="direction"><label><input type="radio" name="dir" value="left" checked>左</label> <label><input type="radio" name="dir" value="right">右</label></td>
			</tr>
			<tr>
				<th>速度</th>
				<td><div id="speed"></div></td>
			</tr>
			<tr>
				<th>字体</th>
				<td><select id="font" class="text">
						<option value="标楷体" selected>标楷体</option>
						<option value="华康俪特圆">华康俪特圆</option>
						<option value="华康细圆体">华康细圆体</option>
						<option value="华康细圆体">华康粗圆体</option>
						<option value="华康中黑体">华康中黑体</option>
						<option value="华康粗黑体">华康粗黑体</option>
						<option value="DFHaiBaoW12-B5">华康海报粗体</option>
						<option value="DFHaiBaoW9-B5">华康海报细体</option>
						<option value="DFFangYuan Std W7">华康方圆体</option>
						<option value="华康正颜楷体W7">华康正颜楷体</option>
						<option value="华康勘亭流">华康勘亭流</option>
						<option value="文鼎粗圆">文鼎粗圆</option>
				</select></td>
			</tr>
			<tr>
				<th>字号</th>
				<td><div id="size"></div></td>
			</tr>
			<tr>
				<th>颜色</th>
				<td><input id="color"></td>
			</tr>
			<tr>
				<th>背景</th>
				<td><input id="background"></td>
			</tr>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/base/marquee"></script>
</body>
</html>

