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
    <title>考勤</title>
</head>
<style>
	li { background-color: #f5fafe;border-bottom:1px solid #ddd;}
	li:hover { cursor: pointer;background-color: #f5f5f5;}
	.list-view>.item {padding:5px;}
	.table>tbody>tr>td{
        text-align:center;
    }
    .active{
			background-color: #00a0e9;
			color: #fff;
			cursor:default;
		}
</style>
<body class="pos-r">
<div>
	<form id="form-search" class="page-container" style="height:100%;" > 
    	<div class="row cl">
			<div class="col-xs-2 col-sm-2">
				<div class="col-xs-12 col-sm-12 bg-1  bk-gray" style="margin:20px;min-height:400px;" id="attendlist">
					<div class="row cl" style="margin-top:5px;margin-bottom:5px;">
						&nbsp;&nbsp;<i class="Hui-iconfont">&#xe667;</i>
						&nbsp;&nbsp;<span>过往记录</span>
					</div>
					<ul id="dateList" class="list-view text-c" style="border-top:1px solid #ddd;">
					</ul>
				</div>
			</div>
			<div class="formControls col-xs-10 col-sm-10 mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
	                    <tr class="text-c">
	                        <th width="30"><input name="" type="checkbox" value=""></th>
	                        <th width="40">ID</th>
	                        <th width="100">头像</th>
	                        <th width="80">名称</th>
	                        <th width="80">性别</th>
	                        <th width="60">班级</th>
	                       	<th width="95">签到时间</th>
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
<!-- DataTable buttons -->
<script type="text/javascript" src="lib/datatables/Buttons-1.6.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="lib/datatables/Buttons-1.6.1/js/buttons.bootstrap.min.js"></script>
<!-- buttons 打印功能 -->
<script type="text/javascript" src="lib/datatables/Buttons-1.6.1/js/buttons.print.min.js"></script>
<!-- buttons 导出功能 -->
<script type="text/javascript" src="lib/datatables/Buttons-1.6.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="lib/datatables/JSZip-2.5.0/jszip.min.js"></script>
<!-- buttons 生成PDF功能 -->
<script type="text/javascript" src="lib/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="lib/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
<script type="text/javascript">
    
    $("#tname").html(parent.tname);
	
    var dateStr;
    
    /*datatables配置*/
    $(document).ready(function () {
        $('.table').DataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
            	url:"/item/timetable/atttend/list",
                type: 'GET',
                dataType: "json",
                data: {
                	"tid":parent.getId(),
                	"date":dateStr
                },
		        "error": function (xhr, error, thrown){
		            alert(error);
		        },
            },
            "language": {
                "emptyTable": "My Custom Message On Empty Table"
            },
            "columns": [
            	{ "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.id+"\" type=\"checkbox\" value=\"\">";
                    }
                },
                { "data": "id"},
                { "data": "avator",
                    render: function(data, type, row, meta) {
                    	if(null == data || "" == data)
                    	{	if(row.sex == 1)
	                    	    data = "http://192.168.2.134/group1/M00/00/00/wKgChl5s1oyAS_bLAAAfr9y-QVY626.png";
	                    	else
	                    		data = "http://192.168.2.134/group1/M00/00/00/wKgChl5s1qiAfRW3AAAf6omf-1g841.png";
                    	}
                    	return '<img src="'+data+'" style="width: 40px;height: 40px" alt="lose image" />';
                    }
                },
                { "data": "sname",
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
                            if (1 == data) {
                                return '<span title="男">男</span>';
                            } else {
                                return '<span title="女">女</span>';
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
                { "data": "time",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
            	{ "data": "status",
                    render : function(data,type, row, meta) {
                        if(data==1){
                            return "<span class=\"label label-success radius td-status\">已签到</span>";
                        }else if(data==2){
                            return "<span class=\"label label-secondary radius td-status\">补签</span>";
                        }else if(data==3){
                            return "<span class=\"label label-warning radius td-status\">请假</span>";
                        }else if(data==4){
                            return "<span class=\"label label-danger radius td-status\">缺勤</span>";
                        }
                    }
                },
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        if (row.state == 1) {
                            return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"attend_edit('考勤信息编辑','attend-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
                        } else {
                            return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"attend_edit('考勤信息编辑','attend-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
                        }
                    }
                }
            ],
            "aaSorting": [[ 1, "desc" ]],//默认第几个排序
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,1,2,3,4,5,6,7,8]}// 制定列不参与排序
            ],
            language: {
                url: '/lib/datatables/Chinese.json'
            },
            bLengthChange:false,
            searching : false, //去掉搜索框方法一
            bSort: false,  //禁止排序
            colReorder: true,
            dom: '<"pull-left"B>ft<"pull-left"i>p', 
            buttons: [
                {
                    text: '<i class="Hui-iconfont" title="复制">&#xe6ea;</i>',
                    extend: 'copy'
                }, {
                    text: '<i class="Hui-iconfont" title="打印">&#xe652;</i>',
                    extend: 'print'
                }, {
                    text: '<i class="Hui-iconfont" title="导出excel">&#xe644;</i>',
                    extend: 'excel'
                }, {
                    text: '<i class="Hui-iconfont" title="导出csv">&#xe627;</i>',
                    extend: 'csv'
                }, {
                    text: '<i class="Hui-iconfont" title="生成pdf">&#xe6be;</i>',
                    extend: 'pdf'
                }, 'reload'
            ],
        });

    });
    
    $("ul").on("click","li",function(){
		$("ul li").removeClass("active");
		$(this).addClass("active");
		$(this).removeAttr("onclick");
		$(this).addClass("focus").css("pointer-events","none");;
	});
    
    var sid="",sname="",sex="",cname="",status="";

    /*考勤-编辑*/
    function attend_edit(title,url,id){
    	setId(id);
    	var table = $('.table').DataTable();
    	$('.table tbody').on( 'click', 'tr', function () {
    		cname = table.row(this).data().cname;
    		sid = table.row(this).data().id;
        	sex = table.row(this).data().sex;
        	sname = table.row(this).data().sname;
        	status = table.row(this).data().status;
        });
    	layer_show(title,url,"",400);
    }
    
    var ID=0;
    function setId(id){
        ID=id;
    }

    function getId(){
        return ID;
    }
    
    $(document).ready(function(){
    	$.fn.dataTable.ext.buttons.reload = {
    		    text: '<i class="Hui-iconfont">&#xe6bd;</i>',
    		    action: function ( e, dt, node, config ) {
    		        dt.ajax.reload();
    		    }
    		};
    });
    
    var index = layer.load(3);

    var ano="";
    
    function atttend_list(date,no){
    	ano=no;
    	var param = {
    			"tid":parent.getId(),
            	"date":date
            };
        var table = $('.table').DataTable();
        table.settings()[0].ajax.data = param;
        table.ajax.url( '/item/timetable/atttend/list' ).load();
    }
    
    
    //过往记录
    $.ajax({
        url:"/item/timetable/atttendDate/"+parent.getId(),
        type: 'POST',
        dataType: "json",
        success:function (data) {
        	layer.close(index);
        	if(null == data.result)
           		return ;
           	var con = data.result;
           	var html = "";
            $.each(con, function(k,v) {//这里的函数参数是键值对的形式，k代表键名，v代表值
            	html+="<li class=\"item\" onclick=\"atttend_list("+con[k].time+",'"+con[k].no+"')\">"+date(con[k].time)+"</li>";
            });
            $("#dateList").append(html);
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        }
    });
    
    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
</script>
</body>
</html>
