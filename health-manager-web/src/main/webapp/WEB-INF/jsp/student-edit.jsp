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
    <title>添加学生 - 学生管理 - H-ui.admin v3.1</title>
    <meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学生:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="学生名" id="username" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>性别:</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="sex" value="1" type="radio" id="sex-1" checked>
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" value="0" id="sex-2" name="sex">
                    <label for="sex-2">女</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写手机号" id="telephone" name="telephone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>出生日期:</label>
            <div class="formControls col-xs-8 col-sm-9">
				<input type="text" id="birthday" name="dateStr" placeholder="出生日期" onfocus="WdatePicker({ maxDate:'%y-%M-%d' })" class="input-text Wdate">           
			</div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学年:</label>
            <div class="formControls col-xs-8 col-sm-9">
				<input type="text" id="year" name="year" placeholder="请填写学年" onfocus="WdatePicker({ dateFmt: 'yyyy',maxDate:'%y' })" class="input-text Wdate" >           
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>分类:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" hidden class="input-text" id="classid" name="classid" >
				<input type="text" onclick="chooseCategory('选择班级',2,7,0)" readonly class="input-text" value="" placeholder="选择班级" id="cname" name="cname" style="width:45%">
                <input type="button" onclick="chooseCategory('选择班级',2,7,0)" class="btn btn-secondary radius" value="选择班级">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

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
<script type="text/javascript">

	$("#cname").val(parent.cname);
	$("#classid").val(parent.cid);

	var type; 
	var factor; 
	var cls = 2;
	
	var year = parent.year;
	
	function giveCid(cid){
		$("#classid").val(cid);
	}
	
	function giveCName(cname){
	   	$("#cname").val(cname);
	}
	
	function chooseCategory(title,type,factor){
		this.type = type;
		this.factor = factor;
		this.num = num;
	    layer_show(title,"choose-college-category",300,510);
	}
	
	var index ;
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-admin-add").validate({
            rules:{
            	name:{
                    required:true,
                },
                sex:{
                    required:true,
                },
                phone:{
                    required:true,
                    isPhone:true,
                },
                birthday:{
                    required:true,
                },
                year:{
                    required:true,
                },
            },
            onkeyup:false,
            focusCleanup:false,
            success:"valid",
            submitHandler:function(form){
            	var index1 = layer.load(3);
                $(form).ajaxSubmit({
                    url: "/item/student/update/"+parent.getId(),
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        layer.close(index1);
                        if (data.success == true) {
                            parent.refresh();
                            parent.msgSuccess("添加成功!");
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.alert(data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.close(index1);
                        layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status + ' 错误信息:' + JSON.parse(XMLHttpRequest.responseText).message, {
                            title: '错误信息',
                            icon: 2
                        });
                    }
                });
            }
        });
    });
    
    var index = layer.load(3);

	$.ajax({
	       type: 'GET',
	       url: '/item/student/'+parent.getId(),
	       dataType: 'json',
	       success: function(data){
	           layer.close(index);
	           $("#username").val(data.result.name);
	           if(data.result.sex==0){
	               $("#sex-2").attr('checked', 'checked');
	               radioCheck();
	           }else{
	               $("#sex-1").attr('checked', 'checked');
	               radioCheck();
	           }
	           
	           $("#sex option[value='0']").prop("checked",true);
	           $("#classid").val(data.result.classid);
	           $("#cname").val(data.result.cname);
	           $("#telephone").val(data.result.telephone);
	           $("#birthday").val(date3(data.result.birthday));
	           $("#year").val(data.result.year);
	           
	       },
	       error:function(XMLHttpRequest) {
	           layer.close(index);
	           layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
	       },
	   });
	
	function radioCheck(){
       $('.skin-minimal input').iCheck({
           checkboxClass: 'icheckbox-blue',
           radioClass: 'iradio-blue',
           increaseArea: '20%'
       });
	 }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>