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
    <title>添加班级分类</title>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="category-add">
        <input type="text" hidden class="input-text" value="0" id="parentId" name="parentId">
        <input type="text" hidden class="input-text" value="true" id="isParent" name="isParent">
        <input type="text" hidden class="input-text" value="1" id="status" name="status">
        <input type="text" hidden class="input-text" value="1" id="sortOrder" name="sortOrder">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                分类名称：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="" placeholder="请填写分类名称" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否为父节点：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <div id="parentSwitch" class="switch" data-on-label="是" data-on="info" data-off-label="否">
                    <input type="checkbox" checked />
                </div>
            </div>
        </div>
        <div class="row cl" id="year">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学年:</label>
            <div class="formControls col-xs-6 col-sm-6">
            	<input type="text" id="remark" name="remark" placeholder="请填写学年" onfocus="WdatePicker({ dateFmt: 'yyyy',maxDate:'%y' })" class="input-text Wdate" >           
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
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    
    var type = parent.cate_type;
    
    /*文本输入限制*/
    $(".textarea").Huitextarealength({
        minlength:0,
        maxlength:100
    });

    if(!parent.isRoot){
        $('#parentSwitch').bootstrapSwitch('setState', false);
        $("#isParent").val(0);
        $("#parentId").val(parent.id);
    }else{
        $('#parentSwitch').bootstrapSwitch('setState', true);
        $("#isParent").val(1);
        $("#parentId").val(0);
        $("#year").hide();
        type = 6;
    }
    
    $('#parentSwitch').on('switch-change', function (e, data) {
        if(data.value==true){
            $("#isParent").val(true);
        }else{
            $("#isParent").val(false);
        }
    });

    //保存发布
    $("#category-add").validate({
        rules:{
            name:{
                required:true,
                minlength:1,
                maxlength:25,
            }
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            var index1 = layer.load(3);
            $(form).ajaxSubmit({
                url: "/item/cate/add?type="+type,
                type: "POST",
                success: function(data) {
                	 layer.close(index1);
                    if(data.success==true){
                        parent.initTree();
                        parent.msgSuccess("添加成功!");
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }else{
                        layer.alert('添加失败! '+data.message, {title: '错误信息',icon: 2});
                    }
                },
                error:function(XMLHttpRequest) {
                    layer.close(index1);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        }
    });
</script>
</body>
</html>