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
    <title>药品列表</title>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>

<body class="pos-r">
<div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
    <ul style="margin-top: 15px;margin-left: 20px"><i class="Hui-iconfont Hui-iconfont-fenlei"></i> 药品分类</ul>
    <ul id="treeDemo" style="margin-left: 10px" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品管理 <span class="c-gray en">&gt;</span> 药品列表 <span class="c-gray en">&gt;</span><span id="category">所有药品</span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
        <div class="text-c"> 日期范围：
            <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate" name="minDate" class="input-text Wdate" style="width:120px;">
            <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-%d' })" id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">
            <input type="text" name="searchKey" id="searchKey" placeholder=" 药品ID、药品名称、批准文号等" style="width:250px" class="input-text">
            <button name="" id="searchButton" type="submit" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜药品</button>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="medicine_add('添加药品','medicine-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加药品</a></span> <span class="r">共有数据：<strong id="itemListCount">0</strong> 条</span> </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
	                    <tr class="text-c">
	                        <th width="30"><input name="" type="checkbox" value=""></th>
	                        <th width="60">ID</th>
	                        <th width="100">缩略图</th>
	                        <th width="100">名称</th>
	                        <th width="60">规格</th>
	                        <th width="90">批准文号</th>
	                        <th width="90">生产厂家</th>
	                        <th width="80">条形码</th>
	                        <th width="95">主治疾病</th>
	                        <th width="70">处方药</th>
	                        <th width="70">价格</th>
	                       	<th width="95">创建时间</th>
	                       	<th width="95">修改时间</th>
	                        <th width="70">状态</th>
	                        <th width="70">操作</th>
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
<script type="text/javascript">

	var CATE_MED=1;
    /*datatables配置*/
    $(document).ready(function () {
        $('.table').DataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
            	url:"/item/medicine/list",
                type: 'GET',
                dataType: "json",
                data: {
                	"cid":-1
                },
        		error : function (){
        			
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
                        return '<a href="'+data+'" target="_blank"><img src="'+data+'" style="width: 80px;height: 70px" alt="lose image" />';
                    }
                },
                { "data": "goodsName",
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
                { "data": "spec"},
                { "data": "approvalNumber",
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
                { "data": "manufacturer",
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
                { "data": "barCode",
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
                { "data": "indications",
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
                { "data": "otc",
                    render : function(data,type, row, meta) {
                        if(data==1){
                            return "<span class=\"label label-success radius td-status\">处方药</span>";
                        }else if(data==0){
                            return "<span class=\"label label-defant radius td-status\">非处方</span>";
                        }else{
                            return "<span class=\"label label-warning radius td-status\">其它型</span>";
                        }
                    }
                },
                { "data": "price",
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
                { "data": "created",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "updated",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "status",
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
                        if (row.status == 1) {
                            return "<a style=\"text-decoration:none\" onClick=\"medicine_stop(this,"+row.id+")\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"medicine_edit('药品信息编辑','medicine-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"medicine_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        } else {
                            return "<a style=\"text-decoration:none\" onClick=\"medicine_start(this,"+row.id+")\" href=\"javascript:;\" title=\"发布\"><i class=\"Hui-iconfont\">&#xe603;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"medicine_edit('药品信息编辑','medicine-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"medicine_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        }
                    }
                }
            ],
            "aaSorting": [[ 11, "desc" ]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,2,3,4,5,6,7,8,9,13,14]}// 制定列不参与排序
            ],
            language: {
                url: '/lib/datatables/Chinese.json'
            },
            colReorder: true
        });

    });
    medicineCount();

    function medicineCount(){
        $.ajax({
            url:"/item/medicine/count",
            type: 'GET',
            dataType: "json",
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

    /*初始化类别数据*/
    var cid=-1;
    /*多条件查询*/
    $("#form-search").validate({
        rules:{
            minDate:{
                required:true,
            },
            maxDate:{
                required:true,
            },
            searchKey:{
                required:false,
            },
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            var searchKey= $('#searchKey').val();
            var minDate= $('#minDate').val();
            var maxDate= $('#maxDate').val();
            var param = {
                "searchKey": searchKey,
                "minDate": minDate,
                "maxDate":maxDate,
                "cid":cid
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.url( '/item/medicine/listSearch' ).load();
        }
    });
    var index = layer.load(3);

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
            url: "/item/cate/list?type="+CATE_MED+"&factor=0",
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
                    return false;
                } else {
                	$("#searchKey").val("");
                	$("#maxDate").val("");
                	$("#minDate").val("");
                	cid=treeNode.id;
                    $("#category").html(treeNode.name);
                    var param = {
                        "cid": treeNode.id,
                    };
                    var table = $('.table').DataTable();
                    table.settings()[0].ajax.data = param;
                    table.ajax.url( '/item/medicine/list' ).load();
                    return true;
                }
            }
        }
    };

    $(document).ready(function(){
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting);
        var zTree = $.fn.zTree.getZTreeObj("tree");
    });

    /*药品-添加*/
    function medicine_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*药品-查看*/
    function medicine_show(title,url,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*药品-关闭*/
    function medicine_stop(obj,id){
        layer.confirm('确认要关闭ID为\''+id+'\'的药品吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/medicine/stop/'+id,
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

    /*药品-启动*/
    function medicine_start(obj,id){
        layer.confirm('确认要启动ID为\''+id+'\'的药品吗？',{icon:3},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/medicine/start/'+id,
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

    /*药品-编辑*/
    function medicine_edit(title,url,id){
        setId(id);
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    
    var ID=0;
    function setId(id){
        ID=id;
    }

    function getId(){
        return ID;
    }

    /*药品-删除*/
    function medicine_del(obj,id){
        layer.confirm('确认要删除ID为\''+id+'\'的药品吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/item/medicine/del/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                   	medicineCount();
                    refresh();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                },
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
                url: '/item/medicine/del/'+ids,
                dataType: 'json',
                success:function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    medicineCount();
                    refresh();
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
</script>
</body>
</html>
