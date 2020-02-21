<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/style.css" />
<title>用户查看</title>
</head>
<body>
	<div class="cl pd-20" style="background-color: #5bacb6">
		<img id="avatar" class="avatar size-XL l"
			src="static/h-ui/images/ucnter/avatar-default.jpg">
		<dl style="margin-left: 80px; color: #fff">
			<dt>
				<span id="username" class="f-18"></span>
			</dt>
			<dd id="description" class="pt-10 f-12" style="margin-left: 0">这家伙很懒，什么也没有留下</dd>
		</dl>
	</div>
	<div class="pd-20">
		<table class="table">
			<tbody>
				<tr>
					<th class="text-r" width="80">性别：</th>
					<td id="sex"></td>
				</tr>
				<tr>
					<th class="text-r">手机：</th>
					<td id="phone"></td>
				</tr>
				<tr>
					<th class="text-r">邮箱：</th>
					<td id="email"></td>
				</tr>
				<tr>
					<th class="text-r">地址：</th>
					<td id="address"></td>
				</tr>
				<tr>
					<th class="text-r">创建时间：</th>
					<td id="created"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="lib/common.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		var USERINFO = {
				
			loginInfo : function() {
				var _ticket = $.cookie("HEALTH_TOKEN");
				if (!_ticket) {
					return;
				}

				$.ajax({
					url : "http://localhost:8086/admin/token/" + _ticket,
					dataType : "jsonp",
					type : "GET",
					success : function(data) {

						if (data.code == 200) {
							$("#username").html(data.result.name);
							$("#phone").html(data.result.phone);
							$("#email").html(data.result.email);
							$("#sex").html(data.result.sex);
							$("#address").html(data.result.address);
							$("#created").html(data.message);
							if (data.result.description != null) {
								$("#description").html(data.result.description);
							}
							if (data.result.file != null && data.result.file != "") {
								$("#avatar").attr("src", data.result.file);
							}
						} else {
							location.href = "http://localhost:8086/login";
						}
					},
					error : function(XMLHttpRequest) {
						layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
					}
				});
			}
		}

		$(function() {
			//用户信息
			USERINFO.loginInfo();
		});
	</script>
</body>
</html>
