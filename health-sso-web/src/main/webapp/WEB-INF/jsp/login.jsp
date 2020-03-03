<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>校园健康后台管理系统</title>
<link rel="Shortcut Icon" href="icon/all.png" />
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="校园健康后台管理系统 v1.0,Health,校园健康后台管理系统">
<meta name="description"
	content="校园健康后台管理系统 v1.0，是一款校园健康后台管理系统，适合中小型CMS后台系统。">
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Meta tag Keywords -->

<!-- css files -->
<link rel="stylesheet" href="lib/login/style.css" type="text/css"
	media="all" />
<!-- Style-CSS -->
<link rel="stylesheet" href="lib/login/font-awesome.css">
<!-- Font-Awesome-Icons-CSS -->
<link rel="stylesheet" href="lib/layer/2.4/skin/layer.css">
<!-- Font-Awesome-Icons-CSS -->

<!-- js -->
<script type="text/javascript" src="lib/jquery/jquery-2.1.4.min.js"></script>
<script src="lib/login/jquery.vide.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript"
	src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
	src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/gt.js"></script>
<script src="https://v.vaptcha.com/v3.js"></script>
<link rel="stylesheet" type="text/css" href="lib/login/verify/verify.css">
<script type="text/javascript" src="lib/login/verify/verify.js" ></script>
<style>
.title, h6 {
	font-family: "黑体";
}

.layui-layer-title {
	padding-right: 305px;
	font-family: "Microsoft Yahei"
}

.layui-layer-dialog .layui-layer-content {
	font-family: "Microsoft Yahei"
}

.layui-layer-btn {
	font-family: "Microsoft Yahei"
}

span.icon1 {
	top: 23%;
}

span.icon2 {
	top: 42%;
}

#wait {
	text-align: left;
	color: #ffffff;
	margin: 0;
	font-family: "黑体";
}
</style>
</head>
<body>

	<!-- main -->
	<div data-vide-bg="lib/video/back">
		<div class="center-container">
			<!--header-->
			<div class="header-w3l">
				<h1>
					<!-- MYC -->
					<span class="title">校园健康后台管理系统</span>
				</h1>
			</div>
			<!--//header-->
			<div class="main-content-agile">
				<div class="sub-main-w3">
					<div class="wthree-pro">
						<h2>Login Here</h2>
					</div>
					<form id="login" action="" method="post" width="100%">
						<input placeholder="用户名" name="username" id="username"
							class="user" type="text" required=""> 
                    	<span class="icon1">
                    		<i class="fa fa-user" aria-hidden="true"/>
                    	</span><br><br>
						<input placeholder="密码" name="password" id="password" class="pass"
							type="password" required=""> 
						 <span class="icon2">
						 <i class="fa fa-unlock" aria-hidden="true"/>
						 </span><br><br>

						<div id="captcha" class="verify-bar-area" data-vide-bg="lib/video/captch" style="width: 350px; height: 40px; line-height: 40px;cursor:pointer;">
							<strong id="cap_msg" class="verify-msg" style="font-weight:bold;">点击进行校验</strong>
							<div id="cap_check" class="verify-left-bar" style="width: 40px; height: 40px; border-color: rgb(221, 221, 221);display:none;">
								<span class="verify-msg" style="color: rgb(0, 0, 0);"/>
								<div class="verify-move-block" style="width: 40px; height: 40px; left: 0px; background-color: rgb(92, 184, 92);">
									<i class="verify-icon iconfont icon-check" style="color: rgb(0, 0, 0);"></i>
								</div>
							</div>
						</div>
						
						<p id="cap" style="display:none;"/>
			
						<div class="sub-w3l">
							<h6 onclick="forgetPass()" style="cursor: pointer">
								<a>游客体验账号密码?</a>
							</h6>
							<div class="right-w3l">
								<input id="loginButton" type="button" class="login" value="登录">
							</div>
						</div>
					</form>
				</div>
			</div>
			<!--//main-->

			<!--footer-->
			<div class="footer">
				<p>
					&copy; 2020 Health. All rights reserved | Design by <a href="#"
						target="_blank">木友茶</a>
				</p>
			</div>
			<!--//footer-->
		</div>
	</div>
	<script type="text/javascript">
	
		var LOGIN = {
			checkInput : function() {
				$("#loginButton").val("登录中...");
				$("#loginButton").attr("disabled", "disabled");
				var name = $("#username").val();
				var pass = $("#password").val();
				if (name == "" || pass == "") {
					layer.msg("用户名或密码不能为空");
					$("#loginButton").val("登录");
					$("#loginButton").removeAttr("disabled");
					return false;
				}
				var reg = /^[0-9A-Za-z]+$/;
				if (!reg.exec(name)) {
					layer.msg("用户名格式有误");
					$("#loginButton").val("登录");
					$("#loginButton").removeAttr("disabled");
					return false;
				}
				return true;
			},
			doLogin : function() {
				$.ajax({
	                url: '/admin/login?t=' + (new Date()).getTime(), // 加随机数防止缓存
	                type: 'POST',
	                dataType: 'json',
	                data: {
	                    username: $("#username").val(),
	                    password: $("#password").val()
	                },
	                success: function (data) {
	                	if(data.success==true){
	                        window.location.href="http://localhost:8082/";
	                    }else{
		                	layer.msg(data.message);
		                    $("#loginButton").val("登录");
		                    $("#loginButton").removeAttr("disabled");
                        }
	                },
	                error:function(XMLHttpRequest){
		                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
		                $("#loginButton").val("登录");
		                $("#loginButton").removeAttr("disabled");
	            	}
	            });
			},
			login : function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		}
		$(function() {
			$("#loginButton").click(function() {
				if(isVer == false)
				{	
					layer.msg("请进行验证");
					return false;
				}
				$("#loginButton").val("登录中...");
		        $("#loginButton").attr("disabled","disabled");
				LOGIN.login();
			});
			$("#captcha").click(function(){
				$("#cap").show();
				$("#captcha").hide();
			});
		});
		$.ajax({
	        url:"http://localhost:8082/system/base",
	        type: 'GET',
	        dataType: "jsonp",
	        success:function (data) {
	            if(data.success!=true){
	                layer.alert(data.message,{title: '错误信息',icon: 2});
	                return;
	            }
	            if(data.result.hasLogNotice==1){
	                layer.alert(data.result.logNotice, {
	                    title: "通知"
	                });
	            }
	        },
	        error:function(XMLHttpRequest){
	            layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
	        }
	    });

		
		var isVer = false;
		
		
		$('#cap').slideVerify({
	    	type : 2,		//类型
    		vOffset : 5,	//误差量，根据需求自行调整
	        vSpace : 5,	//间隔
	        imgName : ['1.jpg', '2.jpg'],
	        imgSize : {
	        	width: '350px',
	        	height: '200px',
	        },
	        blockSize : {
	        	width: '40px',
	        	height: '40px',
	        },
	        barSize : {
	        	width : '350px',
	        	height : '40px',
	        },
	        ready : function() {
	    	},
	        success : function() {
	        	isVer = true;
	        	layer.msg('验证成功!');
	        	$("#cap_check").show();
	        	$("#cap").hide();
	        	$("#cap_msg").text("验证成功");
	        	$("#captcha").unbind("click");
	        	$("#captcha").css("cursor","auto");
				$("#captcha").show();
	        },
	        error : function() {
	        	layer.msg('验证失败!');
	        }
	        
	    });
		
	</script>
</body>
</html>
