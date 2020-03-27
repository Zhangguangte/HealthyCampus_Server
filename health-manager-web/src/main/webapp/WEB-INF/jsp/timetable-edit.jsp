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
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>添加商品分类</title>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="timetable-add">
        <input type="text" hidden class="input-text" value="0" id="tId" name="tId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span> 课程:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写课程:例:(课程名,教师名,地点)" id="descr" name="descr">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学年:</label>
            <div class="formControls col-xs-2 col-sm-3">
                <span class="select-box">
				  <select class="select" size="1" name="cYear" id="cYear">
				    <option value="2020">2020</option>
				    <option value="2019">2019</option>
				    <option value="2018">2018</option>
				    <option value="2017">2017</option>
				  </select>
				</span>
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>学期:</label>
            <div class="formControls col-xs-2 col-sm-3">
                <span class="select-box">
				  <select class="select" size="1" name="semester" id="semester">
				    <option value="1">第一学期</option>
				    <option value="2">第二学期</option>
				  </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>周几:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
				  <select class="select" size="1" name="weeks" id="weeks">
				    <option value="1">周一</option>
				    <option value="2">周二</option>
				    <option value="3">周三</option>
				    <option value="4">周四</option>
				    <option value="5">周五</option>
				    <option value="6">周六</option>
				    <option value="7">周日</option>
				  </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>第几节开始:</label>
            <div class="formControls col-xs-2 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="请填写开始节" id="cStart" name="cStart">
            </div>
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span> 时长:</label>
            <div class="formControls col-xs-2 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="请填写时长" id="period" name="period">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>班级:</label>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid0" name="cid[0]" >
				<input type="text" onclick="chooseCategory('选择班级',6,3,0)" readonly class="input-text" value="" placeholder="请选择班级" id="cname0" name="cname[0]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择班级',6,3,0)" class="btn btn-secondary radius" value="选择班级">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid1" name="cid[1]" >
				<input type="text" onclick="chooseCategory('选择班级',6,3,1)" readonly class="input-text" value="" placeholder="请选择班级" id="cname1" name="cname[1]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择班级',6,3,1)" class="btn btn-secondary radius" value="选择班级">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid2" name="cid[2]" >
				<input type="text" onclick="chooseCategory('选择班级',6,3,2)" readonly class="input-text" value="" placeholder="请选择班级" id="cname2" name="cname[2]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择班级',6,3,2)" class="btn btn-secondary radius" value="选择班级">
            </div>
        </div>
        <div class="row cl">
            <div class="col-7 col-offset-4">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存并提交&nbsp;&nbsp;">
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
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    var type; 
	var factor; 
	var num;
	var cls = 1;
	
	$("#tId").val(parent.cid);
	
	function giveCid(cid){
		$("#cid"+num).val(cid);
	}
	
	function giveCName(cname){
	   	$("#cname"+num).val(cname);
	}
	
	function chooseCategory(title,type,factor,num){
		this.type = type;
		this.factor = factor;
		this.num = num;
	    layer_show(title,"choose-college-category",300,510);
	}

	jQuery.validator.addMethod("decimalsValue",function(value, element) {
        var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
        return this.optional(element) || (decimalsValue.test(value));
    }, "金额必须大于0并且只能精确到分");
	
	var index;
    //保存发布
    $("#timetable-add").validate({
        rules:{
        	descr:{
                required:true,
                maxlength:50,
            },
            semester:{
                required:true,
            },
            weeks:{
                required:true,
            },
            period:{
            	range:[2,4],
            	digits:true,
                required:true,
            },
            cStart:{
            	digits:true,
            	range:[1,12],
                required:true,
            },
            cYear:{
                required:true,
            },
            "cname[0]":{
                required:true,
            }
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
        	var index1 = layer.load(3);
            $(form).ajaxSubmit({
	            url: "/item/timetable/add",
	            type: "POST",
	            dataType: "json",
	            success: function(data) {
	            	layer.close(index1);
                    if(data.success==true){
                        if(parent.location.pathname!='/'){
                            parent.COURSE.getCourseList();
                            parent.loading(true);
                            parent.msgSuccess("添加成功!");
                            index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }else{
                            layer.confirm('添加成功!', {
                                btn: ['确认'],icon: 1
                            }, function(){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        }
                    }else{
                        layer.alert(data.message, {title: '错误信息',icon: 2});
                    }
	            },
	            error:function(XMLHttpRequest) {
	                layer.close(index1);
	                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
	            }
	        });
        }
    });
    
    var index = layer.load(3);

   	$.ajax({
       type: 'GET',
       url: '/item/timetable/'+parent.getId(),
       dataType: 'json',
       success: function(data){
           layer.close(index);
           
           $("#descr").val(data.result.descr);
           $("#cStart").val(data.result.cStart);
           $("#period").val(data.result.period);
           
           $("#cYear option[value='"+data.result.cYear+"']").prop("selected",true);
           $("#semester option[value='"+data.result.semester+"']").prop("selected",true);
           $("#weeks option[value='"+data.result.weeks+"']").prop("selected",true);
           
           $("#cid0").val(data.result.cid[0]);
           $("#cname0").val(data.result.cname[0]);
           if(data.result.cname.length > 1){
	           	$("#cname1").val(data.result.cname[1]);
	           	$("#cid1").val(data.result.cid[1]);
	           	if(data.result.cname.length > 2)
	           	{	$("#cname2").val(data.result.cname[2]);
	           		$("#cid2").val(data.result.cid[2]);}
           }
       },
       error:function(XMLHttpRequest) {
           layer.close(index);
           layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
       },
   });
   	
</script>
</body>
</html>