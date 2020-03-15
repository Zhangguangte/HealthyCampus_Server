<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>考勤</title>
</head>
<body class="pos-r">
<div>
	<form id="form-search" class="page-container" style="height:100%;" > 
    	<div class="row cl">
			<div class="col-xs-4 col-sm-4">
				<div class="col-xs-12 col-sm-12 bg-1  bk-gray" style="margin:20px;min-height:400px;" id="attendlist">
				</div>
			</div>
			<div class="formControls col-xs-8 col-sm-8">
			    <div class="row cl">
					<h3 class="text-l col-xs-7 col-sm-7" id="tname">课名</h3>
					<div  id="begin" class="formControls col-xs-4 col-sm-4 text-r">
						<a class="btn btn-primary radius" onclick="begin_attend()" href="javascript:;">
	        				<i class="Hui-iconfont">&#xe728;</i> 启动签到
	        			</a>
        			</div>
        			<div  id="finish" class="formControls col-xs-4 col-sm-4 text-r" style="display:none;">
        				<a class="btn btn-danger radius" onclick="finish_attend()"  href="javascript:;">
	        				<i class="Hui-iconfont">&#xe631;</i> 结束签到
	        			</a>
        			</div>
				</div>
				<div class="row cl ">
					<div class="text-l col-xs-2 col-sm-2 f-14">
						<i>启动时间:</i>
					</div>
					<div class="text-l col-xs-4 col-sm-4">
						<span id="beginTime"></span>
					</div>
					<div class="text-l col-xs-2 col-sm-2">
						<i>结束时间:</i>
					</div>
					<div class="text-l col-xs-4 col-sm-4">
						<span id="finishTime"></span>
					</div>
				</div>
				<hr>
				<div class="row cl" id="attendLog">
					
				</div>
			</div>
	 	</div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/datatables/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/common.js"></script>
<script type="text/javascript" src="lib/index.js"></script>
<script type="text/javascript">
    
    $("#tname").html(parent.tname);
	
    //显隐按钮
    function attend_oper(bol){
    	if(bol){
    		$("#begin").hide();
    		$("#finish").show();
    	}else{
    		$("#begin").hide();
    		$("#finish").hide();
    	}
    }
    
    var index = layer.load(3);
    
    //确认是否已经存在签到数据
    $.ajax({
        url:"/item/timetable/before/"+parent.getId(),
        type: 'POST',
        dataType: "json",
        success:function (data) {
            if(data.result.isFinsh==true){
            	attend_list();
            	 //开始计时器
                startInterval();
            }else
            	layer.close(index);
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        }
    });
    
    var total = 0;
    
    //签到数据
    function attend_list(){
    	attend_oper(true);
    	$.ajax({
            url:"/item/timetable/attendList/"+parent.getId()+"/"+total,            type: 'POST',
            dataType: "json",
            success:function (data) {
                layer.close(index);
               	if(null == data.result)
               		return ;
                var html="",html1="";
               	var con = data.result;
                $.each(con, function(k,v) {//这里的函数参数是键值对的形式，k代表键名，v代表值
                	if(null == con[k].avator || "" == con[k].avator)
                		 con[k].avator = "http://192.168.2.134/group1/M00/00/00/wKgChl5ntg2AM6PFAAAnT15pPLo054.png";
        			html+='<div class="text-c col-xs-2 col-sm-2 bg-1 " style="margin-top:5px;margin-bottom:5px;">'+
								'<img src="'+  con[k].avator +'"' +	
								' style="width: 40px;height: 40px" alt="lose image" />'+
								'</br>'+
								'<span>'+con[k].name.substring(0,1)+'</span>'+
							'</div>';
					
					html1+='<div class="col-xs-4 col-sm-4">'+
								'<strong class="f-14 ">'+ con[k].name +'</strong>'+
								'<span>&nbsp;&nbsp;签到:</span>'+
								'<span class="f-14">'+ date(con[k].time) +'</span>'+
							'</div>';
					total++;
                });
               	$("#attendlist").append(html);
               	$("#attendLog").append(html1);
            },
            error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }
        });
    }
    
    //启动签到
    function begin_attend(){
    	attend_oper(true);
    	$("#beginTime").html(date(new Date()))
    	$.ajax({
            url:"/item/timetable/begin/"+parent.getId(),
            type: 'POST',
            dataType: "json",
            success:function (data) {
                if(data.success!=true){
                    layer.alert(data.message,{title: '错误信息',icon: 2});
                    return;
                }
                $("#beginTime").html(date(new Date()));
                layer.alert('开始签到',{title: '信息提示',icon: 6});
                //开始计时器
                startInterval();
            },
            error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }
        });
    }
    
    function finish_attend(){
    	attend_oper(false);
    	$.ajax({
            url:"/item/timetable/finish/"+parent.getId(),
            type: 'POST',
            dataType: "json",
            success:function (data) {
                if(data.success!=true){
                    layer.alert(data.message,{title: '错误信息',icon: 2});
                    return;
                }
                $("#finishTime").html(date(new Date()));
            	layer.msg('签到结束!',{icon: 6,time:1000});
            	//清除计数器
            	clearInterval(intervalHandle);
            },
            error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }
        });
    }
    
    var intervalHandle;
    //每10秒请求一次数据
    function startInterval(){
    	intervalHandle = setInterval(attend_list, 10000);
    }
</script>
</body>
</html>
