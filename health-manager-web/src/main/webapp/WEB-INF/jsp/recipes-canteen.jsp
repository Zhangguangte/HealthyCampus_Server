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
    <title>食谱列表</title>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>

<body class="pos-r">
<div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
    <ul style="margin-top: 15px;margin-left: 20px"><i class="Hui-iconfont Hui-iconfont-fenlei"></i> 食堂食谱</ul>
    <ul id="treeDemo" style="margin-left: 10px" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 食谱管理 <span class="c-gray en">&gt;</span> 食堂食谱 <span class="c-gray en">&gt;</span><span id="category">所有食谱</span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
        <div class="text-c"> 日期范围：
            <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate" name="minDate" class="input-text Wdate" style="width:120px;">
            <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-%d' })" id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">
            <input type="text" name="searchKey" id="searchKey" placeholder=" 食谱ID、食谱名称、食谱口味等" style="width:250px" class="input-text">
            <button name="" id="searchButton" type="submit" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜食谱</button>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20"> 
        	<span class="l">
        		<a class="btn btn-primary radius" onclick="recipes_all_show()" href="javascript:;">
        			<i class="Hui-iconfont">&#xe625;</i> 显示所有食谱
        		</a>
        		<a class="btn btn-success radius" onclick="recipes_meals_show()" href="javascript:;">
        			<i class="Hui-iconfont">&#xe616;</i> 显示对应三餐食谱
        		</a>
        		<a href="javascript:;" onclick="dataAdd()" id="dataAdd" class="btn btn-warning radius">
        			<i class="Hui-iconfont">&#xe600;</i> 批量添加
        		</a> 
        		<a href="javascript:;" onclick="dataDel()" id="dataDel" class="btn btn-danger radius" style="display:none;">
        			<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
        		</a> 
        	</span>
        	<span class="r">
        		共有数据：<strong id="itemListCount">0</strong> 条
        	</span> 
        </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="30"><input name="" type="checkbox" value=""></th>
                        <th width="50">ID</th>
                        <th width="100">缩略图</th>
                        <th width="100">名称</th>
                        <th width="100">口味</th>
                      	<th width="100">功效</th>
                        <th width="30">烹饪</th>
                        <th width="90">时长</th>
                        <th width="60">类型</th>
                        <th width="70">卡路里</br>(100g/kcal)</th>
                        <th width="70">分类</th>
                        <th width="70">体质</th>
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

	var CATE_CANTEEN = 3;
	var oper = true;
	
    function imageShow(data){
        if(data==""||data==null){
            return "http://ow2h3ee9w.bkt.clouddn.com/nopic.jpg";
        }
        var images= new Array(); //定义一数组
        images=data.split(","); //字符分割
        if(images.length>0){
            return images[0];
        }else{
            return data;
        }
    }

    /*datatables配置*/
    $(document).ready(function () {
        $('.table').DataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
                url:"/item/recipes/list",
                type: 'GET',
                dataType: "json",
                data: {
                	"cid":-1
                }
            },
            "columns": [
                { "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.id+":"+row.name+"\" type=\"checkbox\" value=\"\">";
                    }
                },
                { "data": "id"},
                { "data": "url",
                    render: function(data, type, row, meta) {
                        return '<a href="'+data+'" target="_blank"><img src="'+imageShow(data)+'" style="width: 80px;height: 70px" alt="lose image" />';
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
                { "data": "flavor",
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
                { "data": "functional",
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
                { "data": "process",
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
                { "data": "time",
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
                { "data": "type",
                	render : function(data,type, row, meta) {
                        if(data==0){
                            return "<span class=\"label label-primary radius td-status\">早餐</span>";
                        }else if(data==1){
                            return "<span class=\"label label-secondary radius td-status\">午餐</span>";
                        }else{
                            return "<span class=\"label label-warning radius td-status\">晚餐</span>";
                        }
                    }
                },
                { "data": "calorie",
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
                { "data": "cname",
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
                { "data": "physique",
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
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                    	if(oper)
                        	return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"recipes_add(this,"+row.id+",'"+row.name+"')\" href=\"javascript:;\" title=\"添加\"><i class=\"Hui-iconfont\">&#xe604;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"recipes_show('"+row.name+"','recipes-show',"+row.id+",1)\" href=\"javascript:;\" title=\"查看\"><i class=\"Hui-iconfont\">&#xe6e0;</i></a>";
                        else
                            return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"recipes_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe706;</i></a><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"recipes_show('"+row.name+"','recipes-show',"+row.id+",1)\" href=\"javascript:;\" title=\"查看\"><i class=\"Hui-iconfont\">&#xe6e0;</i></a>";
                    }
                }
            ],
            "aaSorting": [[ 1, "desc" ]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,2,3,4,5,6,10,12]}// 制定列不参与排序
            ],
            language: {
                url: '/lib/datatables/Chinese.json'
            },
            colReorder: true
        });

    });

    recipesCount();

    function recipesCount(){
        $.ajax({
            url:"/item/recipes/count",
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

    initTree();

    function initTree(){
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting);
        demoIframe = $("#testIframe");
        var zTree = $.fn.zTree.getZTreeObj("tree");
    }
    
    /*初始化类别数据*/
    var pid=-1;
    var pname = "";
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
                "cid":-1
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.url( '/item/recipes/listSearch').load();
        }
    });
    
    /*食谱-添加*/
    function recipes_add(obj,id,name){
    	if(pname == "早餐" || pname == "午餐" || pname == "晚餐"){
    		layer.confirm('确认要添加ID为\''+id+'\'的食谱至'+  $("#category").html() +'？',{icon:0},function(index){
	            var index = layer.load(3);
	            $.ajax({
	                type: 'GET',
	                url: '/item/cate/canteen/add/'+id+"/"+pid+"/"+name,
	                dataType: 'json',
	                success: function(data){
	                    layer.close(index);
	                    if(data.success!=true){
	                        layer.alert(data.message,{title: '错误信息',icon: 2});
	                        return;
	                    }
	                    initTree();
	                    layer.msg('已添加!',{icon: 6,time:1000});
	                },
	                error:function(XMLHttpRequest){
	                    layer.close(index);
	                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
	                }
	            });
    		  });
	    }
	    else{
	    	layer.alert('请点击选择三餐中一种! ', {title: '错误信息',icon: 0});
	    }
    }
    
    /*食谱-删除*/
    function recipes_del(obj,id){
  		layer.confirm('确认要删除ID为\''+id+'\'的食谱从'+  $("#category").html() +'？',{icon:0},function(index){
           var index = layer.load(3);
           $.ajax({
               type: 'GET',
               url: '/item/cate/canteen/del/'+id+"/"+pid,
               dataType: 'json',
               success: function(data){
                   layer.close(index);
                   if(data.success!=true){
                       layer.alert(data.message,{title: '错误信息',icon: 2});
                       return;
                   }
                   initTree();
                   refresh();
                   layer.msg('已删除!',{icon: 6,time:1000});
               },
               error:function(XMLHttpRequest){
                   layer.close(index);
                   layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
               }
           });
  		  });
    }
    
    
    /*批量添加*/
    function dataAdd() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="",names="",mix="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                mix = cks[i].value.split(":");
                ids+=mix[0]+",";
                names+=mix[1]+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        
        if(pname == "早餐" || pname == "午餐" || pname == "晚餐"){}
        else{	
        	layer.alert('请点击选择三餐中一种! ', {title: '错误信息',icon: 0});
        	return ;
        }
        /*去除末尾逗号*/
        if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }
        if(names.length>0){
        	names=names.substring(0,names.length-1);
        }
        
        layer.confirm('确认要添加所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'GET',
                url: '/item/cate/canteen/add/'+ids+"/"+pid+"/"+names,
                dataType: 'json',
                success:function(data){
                	$("input[type='checkbox']").prop("checked",false);
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    initTree();
                    refresh();
                    layer.msg('已添加!',{icon:1,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    
    /*批量删除*/
    function dataDel() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="",names="",mix="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                mix = cks[i].value.split(":");
                ids+=mix[0]+",";
                names+=mix[1]+",";
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
        if(names.length>0){
        	names=names.substring(0,names.length-1);
        }
        
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'GET',
                url: '/item/cate/canteen/del/'+ids+"/"+pid,
                dataType: 'json',
                success:function(data){
                	$("input[type='checkbox']").prop("checked",false);
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    initTree();
                    refresh();
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    
    var fac;
    /*食谱-查看*/
    function recipes_show(title,url,id,fac){
    	setId(id);
    	this.fac = fac;
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    
    /*食谱-查看三餐中一种所有食谱*/
    function recipes_meals_show(){
    	if(pname == "早餐" || pname == "午餐" || pname == "晚餐"){
	    	var param = {
	                "cid":pid
	            };
	    	$('#searchKey').val("");
	        $('#minDate').val("");
	       	$('#maxDate').val("");
	        var table = $('.table').DataTable();
	        table.settings()[0].ajax.data = param;
	        table.ajax.url( "/item/recipes/listMeal").load();
	        dataOperation(false);
        }else{	
        	layer.alert('请点击选择三餐中一种! ', {title: '错误信息',icon: 0});
        	return ;
        }
    }
    
    /*食谱-查看所有*/
    function recipes_all_show(){
    	cid = -1;
    	cname = "所有食谱";
        $("#category").html(cname);
    	var param = {
                "cid":-1
            };
    	$('#searchKey').val("");
        $('#minDate').val("");
       	$('#maxDate').val("");
        var table = $('.table').DataTable();
        table.settings()[0].ajax.data = param;
        table.ajax.url( "/item/recipes/list").load();
        dataOperation(true);
    }
    
    
    /* 批量操作*/
    function dataOperation(bol)
    {
    	if(bol){
    		$("#dataAdd").show();
    		$("#dataDel").hide();
    	}else{
    		$("#dataDel").show();
    		$("#dataAdd").hide();
    	}
    	oper = bol;
    }
    
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
            url: "/item/cate/list?type="+CATE_CANTEEN+"&factor=0",
            type: "GET",
            contentType: "application/json",
            autoParam: ["id"],
        },
        callback: {
            onAsyncSuccess: function(){
                layer.close(index);
            },
            beforeClick: function(treeId, treeNode) {
                if(treeNode.isParent){
                	pid=treeNode.id;
                    pname=treeNode.name;
                    $("#category").html(treeNode.name);
                }else{
                	recipes_show(treeNode.name,"recipes-show",treeNode.id,0);
               	}
                
            }
        }
    };

    $(document).ready(function(){
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting);
        var zTree = $.fn.zTree.getZTreeObj("tree");
    });

    var ID=0;
    function setId(id){
        ID=id;
    }

    function getId(){
        return ID;
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
</script>
</body>
</html>
