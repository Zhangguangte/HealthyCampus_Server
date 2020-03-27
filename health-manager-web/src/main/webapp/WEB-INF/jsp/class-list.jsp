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
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>分类管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生管理 <span class="c-gray en">&gt;</span><span id="subTitle">班级分类</span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div style="margin-left: 1vw;margin-right: 1vw" class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l">
        <a href="javascript:;" onclick="category_del()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除所选分类</a>
        <a class="btn btn-primary radius" onclick="categoryAdd('添加班级分类','class-add')" href="javascript:void();"><i class="Hui-iconfont">&#xe600;</i> 添加班级分类</a>
        <a class="btn btn-primary radius" onclick="categoryRootAdd('添加学院分类','class-add')" href="javascript:void();"><i class="Hui-iconfont">&#xe600;</i> 添加学院分类</a>
    </span>
</div>
<table class="table">
    <tr>
        <td style="padding-left: 4vw" width="200" class="va-t">
	        <div>
			    <span class="select-box">
				  <select class="select" size="1" name="year" id="year">
				    <option value="2020" selected>2020</option>
				    <option value="2019">2019</option>
				    <option value="2018">2018</option>
				    <option value="2017">2017</option>
				    <option value="2016">2016</option>
				    <option value="2015">2015</option>
				    <option value="2014">2014</option>
				  </select>
				</span>
			</div>
        	<ul id="treeDemo" class="ztree"></ul>
        </td>
        <td class="va-t">
            <div class="page-container">
                <form action="" method="post" class="form form-horizontal" id="category-edit">
                    <input type="text" hidden class="input-text" id="id" name="id">
                    <input type="text" hidden class="input-text" value="0" id="parentId" name="parentId">
                    <input type="text" hidden class="input-text" value="1" id="status" name="status">
                    <input type="text" hidden class="input-text" value="true" id="isParent" name="isParent">
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2">
                            <span class="c-red">*</span>
                            分类名称:</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <input type="text" class="input-text" value="" placeholder="" id="name" name="name">
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否为父节点:</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <div id="parentSwitch" class="switch" data-on-label="是" data-on="info" data-off-label="否">
                                <input type="checkbox" checked />
                            </div>
                        </div>
                    </div>
                    <div class="row cl" id="choose-parent">
                        <label class="form-label col-xs-4 col-sm-2">选择父节点:</label>
                        <div class="formControls col-xs-8 col-sm-9">
                            <input type="text" onclick="chooseParent()" readonly class="input-text" value="" placeholder="请点击选择其父节点分类" id="parentName" name="parentName" style="width:48%">
                            <input type="button" onclick="chooseParent()" class="btn btn-secondary radius" value="选择父节点分类">
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2">
                            <span class="c-red">*</span>
                            排序优先值:</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <input type="text" class="input-text" value="" placeholder="请输入1~9999，值越小排序越前" id="sortOrder" name="sortOrder">
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否启用:</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <div id="mySwitch" class="switch" data-on-label="启用" data-on="info" data-off-label="禁用">
                                <input type="checkbox" checked />
                            </div>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学年:</label>
                        <div class="formControls col-xs-6 col-sm-6">
                        	<input type="text" id="remark" name="remark" placeholder="请填写学年" onfocus="WdatePicker({ dateFmt: 'yyyy',maxDate:'%y' })" class="input-text Wdate" >           
                        </div>
                    </div>
                    <div class="row cl">
                        <div class="col-9 col-offset-2">
                            <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交修改&nbsp;&nbsp;">
                        </div>
                    </div>
                </form>
            </div>
        </td>
    </tr>
</table>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">

	var CATE_COL = 6;
	
	var CATE_CLS = 8;
	
	var isFirst = true;

	var cls = -1;
	
	var cate_type = 8;

	var remark = "";
	
    /*文本输入限制*/
    $(".textarea").Huitextarealength({
        minlength:0,
        maxlength:100
    });

    function chooseParent(){
        layer_show("选择父节点分类","choose-college-category",300,510);
    }

    var isParent=false,id="",name="";

    var index = layer.load(3);

    function getAsyncUrl() {
    	if(isFirst){
        	isFirst = false;
        	return "/item/cate/list?type="+CATE_COL+"&factor=0";
    	}else
        	return "/item/cate/cls?year="+$("#year option:selected").val(); 
    };
    
    var setting = {
        view: {
            dblClickExpand: true,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        async: {
            enable: true,
            url: getAsyncUrl,
            type: "GET",
            contentType: "application/json",
            autoParam: ["id"]
        },
        callback: {
            onAsyncSuccess: function(){
                layer.close(index);
            },
            beforeClick: function(treeId, treeNode) {
                $("#name").val(treeNode.name);
                $("#id").val(treeNode.id);
                $("#sortOrder").val(treeNode.sortOrder);
                $("#remark").val(treeNode.remark);
                $("#parentId").val(treeNode.pId);
                if($("#parentId").val()==""){
                    $("#parentId").val(0);
                }
                if(treeNode.pId!=0){
                    $("#parentName").val(treeNode.getParentNode().name);
                }else{
                    $("#parentName").val("根目录");
                }
                changeSwitch2(treeNode.status);
                id=treeNode.id;
                name=treeNode.name;
                if (treeNode.isParent) {
                    isParent=true;
                    changeSwitch1(1);
                    return false;
                } else {
                    isParent=false;
                    changeSwitch1(0);
                    return true;
                }
            }
        }
    };
	
    $("#year").change(function(){
    	initTree();
    	year = $("#year option:selected").val();
    	remark = year;
	})
	
    function changeSwitch1(value){
        if(value==1){
            $('#parentSwitch').bootstrapSwitch('setState', true);
        }else{
            $('#parentSwitch').bootstrapSwitch('setState', false);
        }
    }

    $('#parentSwitch').on('switch-change', function (e, data) {
        if(data.value==true){
            $("#isParent").val(true);
        }else{
            $("#isParent").val(false);
        }
    });

    function changeSwitch2(value){
        if(value==1){
            $('#mySwitch').bootstrapSwitch('setState', true);
        }else{
            $('#mySwitch').bootstrapSwitch('setState', false);
        }
    }

    $('#mySwitch').on('switch-change', function (e, data) {
        if(data.value==true){
            $("#status").val(1);
        }else{
            $("#status").val(0);
        }
    });

    initTree();

    function initTree(){
    	isFirst = true;
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting);
        demoIframe = $("#testIframe");
        var zTree = $.fn.zTree.getZTreeObj("tree");
    }

    //保存提交
    $("#category-edit").validate({
        rules:{
            name:{
                required:true,
                minlength:1,
                maxlength:25,
            },
            sortOrder:{
                required:true,
                digits:true,
                maxlength:4,
                range:[1,9999]
            }
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            var index = layer.load(3);
            $(form).ajaxSubmit({
                url: "/item/cate/update",
                type: "POST",
                success: function(data) {
                    layer.close(index);
                    if(data.success==true){
                        initTree();
                        msgSuccess("编辑成功!");
                    }else{
                        layer.alert('添加失败! '+data.message, {title: '错误信息',icon: 2});
                    }
                },
                error:function(XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        }
    });

    function giveCid(cid){
        $("#parentId").val(cid);
	}
	
	function giveCName(cname){
		$("#parentName").val(cname);
	}
    
    var isRoot=false;

    /*子分类-添加*/
    function categoryAdd(title,url){
        if(!isParent||!$("#id").val()){
            layer.alert('请点击选择一父分类! ', {title: '错误信息',icon: 0});
            return;
        }
        isRoot=false;
        layer_show(title,url,700,350);
    }

    /*根节点分类-添加*/
    function categoryRootAdd(title,url){
        isRoot=true;
        layer_show(title,url,700,300);
    }

    /*分类-删除*/
    function category_del() {
        var id=$("#id").val();
        if(!id){
            layer.alert('请点击选择要删除的分类! ', {title: '错误信息',icon: 0});
            return;
        }
        layer.confirm('确认要删除所选的\''+name+'\'分类吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: "/item/cate/del/" +id+"/"+ ("根目录" == $("#parentName").val() ? CATE_COL : CATE_CLS),
                dataType: 'json',
                success: function(data) {
                    layer.close(index);
                    if(data.success==true){
                        initTree();
                        $("#id").val("");
                        msgSuccess("删除成功!");
                    }else{
                        layer.alert('删除失败! '+data.message, {title: '错误信息',icon: 2});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {
                        title: '错误信息',
                        icon: 2
                    });
                }
            });
        });
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
    
    function setParentId(id,name){
        $("#parentName").val(name);
        $("#parentId").val(id);
    }
    
</script>
</body>
</html>