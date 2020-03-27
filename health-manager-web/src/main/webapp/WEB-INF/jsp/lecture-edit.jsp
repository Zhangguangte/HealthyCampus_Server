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
    <form name="lecture-add" action="" method="post" class="form form-horizontal" id="lecture-add">
        <div class="row cl">
            <h3 class="form-label col-xs-4 col-sm-2"  style="color: tomato;text-decoration:underline;">讲座信息:</h3>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>讲座标题:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入讲座标题" id="title" name="title"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>作者:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入作者" id="author" name="author">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>分类:</label>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid0" name="cid[0]" >
				<input type="text" onclick="chooseCategory('选择分类',5,3,0)" readonly class="input-text" value="" placeholder="请选择分类" id="cname0" name="cname[0]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择分类',4,3,0)" class="btn btn-secondary radius" value="选择分类">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid1" name="cid[1]" >
				<input type="text" onclick="chooseCategory('选择分类',5,3,1)" readonly class="input-text" value="" placeholder="请选择分类" id="cname1" name="cname[1]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择分类',5,3,1)" class="btn btn-secondary radius" value="选择分类">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="cid2" name="cid[2]" >
				<input type="text" onclick="chooseCategory('选择分类',5,3,2)" readonly class="input-text" value="" placeholder="请选择分类" id="cname2" name="cname[2]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择分类',5,3,2)" class="btn btn-secondary radius" value="选择分类">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>内容:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="input-text" value="" placeholder="请输入内容" id="content" name="content" style="min-height:150px;resize:none;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并发布</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
	    layer_show(title,"choose-category",300,510);
	}
	
	jQuery.validator.addMethod("decimalsValue",function(value, element) {
        var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
        return this.optional(element) || (decimalsValue.test(value));
    }, "金额必须大于0并且只能精确到分");
	
	//保存发布
	$("#lecture-add").validate({
	    rules:{
	    	title:{
	            required:true,
	            maxlength:30,
	        },
	        author:{
	            required:true,
	            maxlength:30,
	        },
	        content:{
	            required:true,
	        },
	        "cname[0]":{
	            required:true,
	        },
	    },
	    onkeyup:false,
	    focusCleanup:false,
	    success:"valid",
	    submitHandler:function(form){
	    	
	    	$("#content").val().replace("&", "&amp;");
	    	$("#content").val().replace("<", "&lt;");
	    	$("#content").val().replace(">", "&gt;");
	    	$("#content").val().replace("\"", "&quot;");
	        
	        var index1 = layer.load(3);
	        $(form).ajaxSubmit({
	            url: "/item/lecture/update/"+parent.getId(),
	            type: "POST",
	            dataType: "json",
	            success: function(data) {
	                layer.close(index1);
	                if(data.success==true){
	                    parent.refresh();
	                    parent.msgSuccess("编辑成功!");
	                    var index = parent.layer.getFrameIndex(window.name);
	                    parent.layer.close(index);
	                }else{
	                    layer.alert('编辑失败! '+data.message, {title: '错误信息',icon: 2});
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
       url: '/item/lecture/'+parent.getId(),
       dataType: 'json',
       success: function(data){
           layer.close(index);
           
           $("#title").val(data.result.title);
           $("#author").val(data.result.author);
           $("#content").val(data.result.content);
           
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
