<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/icon/favicon.ico" >
    <link rel="Shortcut Icon" href="/icon/favicon.ico" />
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
    <!--/meta 作为公共模版分离出去-->

    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-admin-add" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>教师：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="教师名" id="username" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>性别：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="sex" value="男" type="radio" id="sex-1" checked>
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" value="女" id="sex-2" name="sex">
                    <label for="sex-2">女</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写手机号" id="phone" name="phone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="@" name="email" id="email">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学院:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" hidden class="input-text" id="cid" name="cid" >
				<input type="text" onclick="chooseCategory('选择学院',6,3)" readonly class="input-text" value="" placeholder="请选择学院" id="cname" name="cname" style="width:45%">
                <input type="button" onclick="chooseCategory('选择学院',6,3)" class="btn btn-secondary radius" value="选择学院">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="请输入地址" name="address" id="address">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="description" name="description" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
	var cls = -1;
	/*文本输入限制*/
	$(".textarea").Huitextarealength({
	    minlength:0,
	    maxlength:100
	});

	function giveCid(cid){
		$("#cid").val(cid);
	}
	
	function giveCName(cname){
	   	$("#cname").val(cname);
	}
	
	function chooseCategory(title,type,factor){
		this.type = type;
		this.factor = factor;
	    layer_show(title,"choose-college-category",300,510);
	}
	
    $("#form-admin-add").validate({
        rules:{
            name:{
                required:true,
                minlength:1,
                maxlength:16,
            },
            sex:{
                required:true,
            },
            phone:{
                required:true,
                isPhone:true,
            },
            email:{
                required:true,
                email:true,
            },
            address:{
                maxlength:100,
            },
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            var index = layer.load(3);
            $(form).ajaxSubmit({
                url: "/item/teacher/update?id="+parent.getId(),
                type: "POST",
                dataType: "json",
                success: function (data) {
                    layer.close(index);
                    if (data.success == true) {
                        parent.teacherCount();
                        parent.refresh();
                        parent.initTree();
                        parent.msgSuccess("添加成功!");
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status + ' 错误信息:' + JSON.parse(XMLHttpRequest.responseText).message, {
                        title: '错误信息',
                        icon: 2
                    });
                }
            });
        }
	});

   var index = layer.load(3);

   $.ajax({
       type: 'GET',
       url: '/item/teacher/'+parent.getId(),
       dataType: 'json',
       success: function(data){
           layer.close(index);
           
           $("#username").val(data.result.name);
           $("#phone").val(data.result.phone);
           $("#email").val(data.result.email);
           
           if(data.result.sex=='女'){
               $("#sex-2").attr('checked', 'checked');
               radioCheck();
           }else{
               $("#sex-1").attr('checked', 'checked');
               radioCheck();
           }
           
           $("#address").val(data.result.address);
           
           $("#description").val(data.result.description);
           
           $("#cid").val(data.result.cid);
           $("#cname").val(data.result.cname);
           
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
</body>
</html>
