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
    <title>编辑管理员</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="attend-update">
        <input type="text" hidden value="" placeholder="" id="id" name="id">
        <input type="text" hidden value="" placeholder="" id="no" name="no">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" readonly="readonly" class="input-text" value="" placeholder="" id="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别:</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
               	<input type="text" readonly="readonly" class="input-text" value="" placeholder="" id="sex" >
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>班级:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" readonly="readonly" class="input-text" value="" placeholder="" id="cls">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型:</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				  <select class="select" size="1" name="type" id="type">
				    <option value="1">签到</option>
				    <option value="2">补签</option>
				    <option value="3">请假</option>
				    <option value="4">缺勤</option>
				  </select>
				</span>
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
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">

    $("#sex").val(parent.sex == "1" ? "男":"女");
    $("#name").val(parent.sname);
    $("#cls").val(parent.cname);
    $("#id").val(parent.sid);
    $("#no").val(parent.ano);
    $("#type option[value='"+parent.status+"']").prop("selected",true);
    
    
    var index;
    $(function(){
        $("#attend-update").validate({
            onkeyup:false,
            focusCleanup:false,
            success:"valid",
            submitHandler:function(form){
              	var  index1 = layer.load(3);
                $(form).ajaxSubmit({
                    url: "/item/timetable/atttend/update",
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        layer.close(index1);
                        if (data.success == true) {
                        	parent.articleCount();
                            parent.msgSuccess("编辑成功!");
                            parent.refresh();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.alert(data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.close(index1);
                        layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {
                            title: '错误信息',
                            icon: 2
                        });
                    }
                });
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>