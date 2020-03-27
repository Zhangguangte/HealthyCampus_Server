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
    <title>教师列表</title>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body class="pos-r">
<div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
    <ul style="margin-top: 15px;margin-left: 20px">
    	<i class="Hui-iconfont Hui-iconfont-fenlei"></i> 教师分类</ul>
    <ul id="treeDemo" style="margin-left: 10px" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb">
    	<i class="Hui-iconfont">&#xe67f;</i> 首页 
    	<span class="c-gray en">&gt;</span> 教师管理 
    	<span class="c-gray en">&gt;</span> 教师列表 
    	<span class="c-gray en">&gt;</span>
    	<span id="category">所有教师</span> 
    	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
	<form id="form-search" class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> 
        	<span class="l">
	        	<a class="btn btn-primary radius" onclick="teacher_opear(true)" href="javascript:;">
	        		<i class="Hui-iconfont">&#xe625;</i> 查看所有教师
	        	</a>
	        	<a class="btn btn-warning radius" style="display:none;"  id="dataAdd" onclick="teacher_add('添加教师','teacher-add')" href="javascript:;">
	        		<i class="Hui-iconfont">&#xe600;</i> 添加教师
	        	</a>
	        	<a href="javascript:;" onclick="datadel()" style="display:none;" id="dataDel"  class="btn btn-danger radius">
        			<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
        		</a> 
        	</span> 
        	<span class="r">共有数据：
        		<strong id="itemListCount">0</strong> 条
        	</span> 
        </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
	                    <tr class="text-c">
	                        <th width="30"><input name="" type="checkbox" value=""></th>
	                        <th width="40">ID</th>
	                        <th width="80">头像</th>
	                        <th width="100">名称</th>
	                        <th width="40">性别</th>
	                        <th width="110">编号</th>
	                        <th width="100">电话号码</th>
	                        <th width="60">电子邮箱</th>
	                        <th width="90">地址</th>
	                       	<th width="80">创建时间</th>
	                       	<th width="80">修改时间</th>
	                        <th width="60">状态</th>
	                        <th width="60">操作</th>
	                    </tr>
                    </thead>
                </table>
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

	var CATE_COL = 6;

	var CATE_TEA = 7;
	
    var index = layer.load(3);

    var type = CATE_COL;
    
    var isFirst = true;
    
    var cid = -1;
    
    function defaultImageSex(bol)
    {
    	if(bol)
    		return "http://192.168.2.134/group1/M00/00/00/wKgChl5x9fKALJl3AAAd3voooFY513.png";
    	else
    		return "http://192.168.2.134/group1/M00/00/00/wKgChl5x9buAc5eJAAAnT15pPLo787.png";
    }
    
    /*datatables配置*/
    $(document).ready(function () {
        $('.table').DataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
            	url:"/item/teacher/list",
                type: 'GET',
                dataType: "json",
                data: {
                	"cid":-1
                }
            },
            "columns": [
            	{ "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.id+"\" type=\"checkbox\" value=\"\">";
                    }
                },
                { "data": "id"},
                { "data": "logo",
                    render: function(data, type, row, meta) {
                    	if(null == row.logo || row.logo == "")
                    	{	if(row.sex == '男')
                               return '<a href="'+defaultImageSex(true)+'" target="_blank"><img src="'+defaultImageSex(true)+'" style="width: 50px;height: 50px" alt="lose image" />';
                           	else
                               return '<a href="'+defaultImageSex(false)+'" target="_blank"><img src="'+defaultImageSex(false)+'" style="width: 50px;height: 50px" alt="lose image" />';
                    	}
                        else
                        	return '<a href="'+data+'" target="_blank"><img src="'+data+'" style="width: 80px;height: 70px" alt="lose image" />';
                    }
                },
                { "data": "name",
                    render: function(data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 20) {
                                return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                            } else {
                                return '<span title=' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }
                },
                { "data": "sex",
                    render: function(data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 20) {
                                return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                            } else {
                                return '<span title=' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }
                },
                { "data": "no"},
                { "data": "phone",
                	render: function(data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 20) {
                                return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                            } else {
                                return '<span title=' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }		
                },
                { "data": "email",
                	render: function(data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 20) {
                                return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                            } else {
                                return '<span title=' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }	
                },
                { "data": "address",
                	render: function(data, type, row, meta) {
                        if (type === 'display') {
                            if (data.length > 20) {
                                return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                            } else {
                                return '<span title=' + data + '>' + data + '</span>';
                            }
                        }
                        return data;
                    }	
                },
                { "data": "createTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "updateTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "state",
                    render : function(data,type, row, meta) {
                        if(data==1){
                            return "<span class=\"label label-success radius td-status\">已启动</span>";
                        }else if(data==0){
                            return "<span class=\"label label-defant radius td-status\">已停用</span>";
                        }else{
                            return "<span class=\"label label-warning radius td-status\">其它态</span>";
                        }
                    }
                },
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        if (row.state == 1) {
                            return "<a style=\"text-decoration:none\" onClick=\"teacher_stop(this,"+row.id+")\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"teacher_edit('教师信息编辑','teacher-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"teacher_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        } else {
                            return "<a style=\"text-decoration:none\" onClick=\"teacher_start(this,"+row.id+")\" href=\"javascript:;\" title=\"发布\"><i class=\"Hui-iconfont\">&#xe603;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"teacher_edit('教师信息编辑','teacher-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"teacher_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        }
                    }
                }
            ],
            "aaSorting": [[ 1, "desc" ]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,2,3,4,5,6,7,8,10,11,12]}// 制定列不参与排序
            ],
            language: {
                url: '/lib/datatables/Chinese.json'
            },
            colReorder: true
        });

    });

    function teacher_opear(bol){
    	var param = {
                "cid":bol?-1:cid,
            };
    	var table = $('.table').DataTable();
        table.settings()[0].ajax.data = param;
    	if(bol){
    		table.ajax.url( "/item/teacher/list").load();
    		dataOperation(false);
    		$("#category").html('所有教师');
    		return ;	
    	}
    	if($("#category").html() == '所有教师'){
    		layer.alert('请点击选择一个学院! ', {title: '错误信息',icon: 0});
    		return;
    	}
    	dataOperation(true);
        table.ajax.url( "/item/teacher/college").load();
    }
    
    function getAsyncUrl() {
    	if(isFirst){
        	isFirst = false;
        	return "/item/cate/list?type="+CATE_COL+"&factor=0";
    	}else
        	return "/item/cate/list?type="+CATE_TEA+"&factor=0"; 
    };
    
    var setting = {
        view: {
            dblClickExpand: true,
            showLine: false,
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
            autoParam: ["id"],
        },
        callback: {
            onAsyncSuccess: function(){
                layer.close(index);
            },
            beforeClick: function(treeId, treeNode) {
                if (treeNode.isParent) {
                	cid=treeNode.id;
                    $("#category").html(treeNode.name);
                    var param = {
                        "cid": treeNode.id,
                    };
                    teacher_opear(false);
                    return true;
                } else {
                	 return false;
                }
            }
        }
    };

    initTree();
    
    function initTree(){
        isFirst = true;
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting);
        demoIframe = $("#testIframe");
        var zTree = $.fn.zTree.getZTreeObj("tree");
    }

   
    
    var id=0;
    function setId(id){
        this.id=id;
    }

    function getId(){
        return id;
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
    
    /*教师-添加*/
    function teacher_add(title,url){
    	if($("#category").html() == "所有教师"){
    		layer.msg('请您选择一名教师!',{icon:5,time:3000});
    		return ;
    	}
    	layer_show(title,url,"",600);
    }
    
    /*教师-编辑*/
    function teacher_edit(title,url,id){
    	setId(id);
    	layer_show(title,url,"",600);
    }
    
    /* 批量操作*/
    function dataOperation(bol)
    {
    	if(bol){
    		$("#dataAdd").show();
    		$("#dataDel").show();
    	}else{
    		$("#dataDel").hide();
    		$("#dataAdd").hide();
    	}
    	oper = bol;
    }
    
    teacherCount();

    function teacherCount(){
        $.ajax({
            url:"/item/teacher/count",
            type: 'GET',
            success:function (result) {
                $("#itemListCount").html(result.recordsTotal);
            },
            error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }
        });
    }
    
    /*教师-关闭*/
    function teacher_stop(obj,id){
        layer.confirm('确认要关闭ID为\''+id+'\'的教师吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/teacher/stop/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('已关闭!',{icon: 5,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });

        });
    }

    /*教师-启动*/
    function teacher_start(obj,id){
        layer.confirm('确认要启动ID为\''+id+'\'的教师吗？',{icon:3},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/teacher/start/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('已启动!',{icon: 6,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    
    /*批量删除*/
    function datadel() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids+=cks[i].value+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        /*去除末尾逗号*/
        if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/item/teacher/del/'+ids,
                dataType: 'json',
                success:function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    teacherCount();
                    refresh();
                    initTree();
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    
    /*教师-删除*/
    function teacher_del(obj,id){
        layer.confirm('确认要删除ID为\''+id+'\'的教师吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/item/teacher/del/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    teacherCount();
                    refresh();
                    initTree();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                },
            });
        });
    }
</script>
</body>
</html>
