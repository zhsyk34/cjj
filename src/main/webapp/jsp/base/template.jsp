<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title>模板</title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/template.css">
</head>
<body>
	<header>
		<h3>模板</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-name">名称</label><input id="search-name" class="text">
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
					<th width="10%"><input id="check-parent" type="checkbox"></th>
					<th width="15%">序号</th>
					<th width="30%">名称</th>
					<th width="15%">类型</th>
					<th width="30%">编辑</th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- editor -->
	<div id="editor">
		<table class="table table-hover table-edit">
			<tbody>
				<tr>
					<th width="20%">类型</th>
					<td id="type"><label><input type="radio" name="type" value="s01" checked>S01</label> <label><input type="radio" name="type" value="s02">S02</label> <label><input type="radio" name="type" value="e01">E01</label></td>
				</tr>
				<tr>
					<th>内容</th>
					<td id="content"><label><input type="radio" name="content" value="number" checked>取号 </label> <label><input type="radio" name="content" value="video">视频</label> <label><input type="radio" name="content" value="picture">图片</label></td>
				</tr>
				<tr>
					<th>名称</th>
					<td><input type="hidden" id="id"><input class="text" id="name"></td>
				</tr>
				<tr class="layout">
					<th>布局</th>
					<td>
						<div class="inline">
							<label for="rowcount">行数</label><input class="text" value="25" id="rowcount">
						</div>
						<div class="inline">
							<label for="colcount">列数</label><input class="text" value="3" id="colcount">
						</div>
					</td>
				</tr>
				<!-- logo -->
				<tr id="logo">
					<th>Logo
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- food -->
				<tr id="foodList">
					<th>餐点
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- number -->
				<tr id="number">
					<th>取号
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- video -->
				<tr id="videoList">
					<th>视频
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- 图片相关 -->
				<tr id="pictureList" class="picture">
					<th>图片
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<tr class="picture">
					<th>轮播</th>
					<td><input class="text" id="interlude"></td>
				</tr>
				<tr class="picture">
					<th>效果</th>
					<td><select class="text" id="effect">
							<option value="RANDOM" selected="selected">随机</option>
							<option value="ALPHA">渐变</option>
							<option value="CIRCLE">圆形</option>
							<option value="MOVE">移动</option>
							<option value="BLINDS">百叶窗</option>
					</select></td>
				</tr>
				<!-- 跑马灯 -->
				<tr id="marqueeList">
					<th>跑马灯
						<button class="btn btn-primary">选择</button>
					</th>
					<td>
						<div class="comment">选择多则跑马灯时,相关设定(背景、字体等)以第一则为主</div>
						<div class="thumbnail"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- material -->
	<div id="material-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="material-name">名称</label><input id="material-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-material">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="" id="material-parent"></th>
						<th width="">序号</th>
						<th width="">名称</th>
						<th width="">图片</th>
						<th width="">预览</th>
					</tr>
				</thead>
				<tbody id="material-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="material-page"></div>
		</div>
	</div>

	<!-- food -->
	<div id="food-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="food-name">名称</label><input id="food-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-food">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="food-parent" type="checkbox"></th>
						<th width="">序号</th>
						<th width="">名称</th>
						<th width="">价格</th>
						<th width="">图片</th>
						<th width="">调味</th>
					</tr>
				</thead>
				<tbody id="food-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="food-page"></div>
		</div>
	</div>

	<!-- marquee -->
	<div id="marquee-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="marquee-name">名称</label> <input id="marquee-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-marquee">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="marquee-parent" type="checkbox"></th>
						<th width="">序号</th>
						<th width="">标题</th>
						<th width="">内容</th>
					</tr>
				</thead>
				<tbody id="marquee-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="marquee-page"></div>
		</div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/base/template"></script>
</body>
</html>

