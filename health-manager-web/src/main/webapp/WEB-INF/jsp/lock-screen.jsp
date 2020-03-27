<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="muyoucha">
    <link rel="Shortcut Icon" href="icon/all.png" />
     <title>校园健康后台管理系统 v1.0</title>
    <meta name="keywords" content="校园健康后台管理系统 v1.0,Health,校园健康后台管理系统">
	<meta name="description" content="校园健康后台管理系统 v1.0，是一款校园健康后台管理系统，适合中小型CMS后台系统。">
    <title>Lock Screen</title>

    <!-- Bootstrap core CSS -->
    <link href="lib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="lib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="lib/flatlab/css/style.css" rel="stylesheet">
    <link href="lib/flatlab/css/style-responsive.css" rel="stylesheet" />
		
	<link rel="stylesheet" href="lib/layer/2.4/skin/layer.css">
	
</head>

<body class="lock-screen" onload="startTime()">

<div class="lock-wrapper">

    <div id="time"></div>

    <div class="lock-box text-center">
        <img id="avatar" width="85px" height="85px" src="static/h-ui/images/ucnter/avatar-default.jpg" alt="lock avatar"/>
        <h1 id="username">小友，请留步</h1>
        <span class="locked" id="locked">Locked</span>
        <form class="form-inline">
             <input type="password" placeholder="Password" id="password" class="form-control lock-input">
             <button class="btn btn-lock" type="button" id="unlock">
                 <i class="icon-arrow-right"></i>
             </button>
             <input hidden id="account">
        </form>
    </div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>

<script>
	$(function(){
		LOCK.userInfo();
		//防止返回
		if(window.history && window.history.pushState) {
			window.onpopstate=function () {
		            window.history.pushState('forward', null, '');
		            window.history.forward(1);
			};
		}
		//在IE中必须得有这两行
	    window.history.pushState('forward', null, '');
	    window.history.forward(1);
	});

	var LOCK = {
		userInfo : function() {
			var _ticket = $.cookie("HEALTH_TOKEN");
			if (!_ticket) {
				location.href ="/login";
			}
			$.ajax({
		        type: 'GET',
		        dataType : "json",
		        url : "/admin/token/" + _ticket,
		        success:function (data) {
		            if(data.success==true){
		            	 $("#username").html(data.result.name);
		            	 $("#account").val(data.result.account);
		            }else {
		                layer.alert(data.message,{title: '错误信息',icon: 2});
		            }
		        },
		        error:function(XMLHttpRequest){
		            layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
		        }
		    });
		},
		restTime : function (time){
		    if(time > 0)
		    	$("#locked").html("剩余" + time + "次机会");
		    else
		    	location.href ="/login";
	    },
		unlock : function(time){
			var pass = $("#password").val();
			//输入非空
			if("" == pass){  
				layer.msg("输入不为空");
				return ;
			}
			var _ticket = $.cookie("HEALTH_TOKEN");
			if (!_ticket) {
				location.href ="/login";
			}
			
		  	$.ajax({
		        type: 'GET',
		        dataType : "jsonp",
		        url : "http://localhost:8086/admin/unlock",
		        data :{
		        	account : $("#account").val() ,
		        	password : pass ,
		        	token : _ticket
		        },
		        success:function (data) {
		            if(data.success==true){
						location.href = "/"
		            }else {
		            	layer.msg("输入错误!!!");
		            	LOCK.restTime(time);
		            }
		        },
		        error:function(XMLHttpRequest){
		            layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
		            LOCK.restTime(time);
		        }
		    });
		}
	}

	//试错机会
	var time = 5;
	
	$("#unlock").click(function(){
		time--;
		LOCK.unlock(time);
	});
	
    function startTime()
    {
        var today=new Date();
        var h=today.getHours();
        var m=today.getMinutes();
        var s=today.getSeconds();
        // add a zero in front of numbers<10
        m=checkTime(m);
        s=checkTime(s);
        document.getElementById('time').innerHTML=h+":"+m+":"+s;
        t=setTimeout(function(){startTime()},500);
    }
    
    function checkTime(i)
    {
        if (i<10)
        {
            i="0" + i;
        }
        return i;
    }

</script>
</body>
</html>

