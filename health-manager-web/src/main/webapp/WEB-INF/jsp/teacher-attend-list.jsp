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
    <title>课表列表</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
    #coursesTable {
        padding: 15px 10px;
    }

    .Courses-head {
        background-color: #edffff;
    }

    .Courses-head > div {
        text-align: center;
        font-size: 14px;
        line-height: 28px;
    }

    .left-hand-TextDom, .Courses-head {
        background-color: #f2f6f7;
    }

    .Courses-leftHand {
        background-color: #f2f6f7;
        font-size: 12px;
    }

    .Courses-leftHand .left-hand-index {
        color: #9c9c9c;
        margin-bottom: 4px !important;
    }

    .Courses-leftHand .left-hand-name {
        color: #666;
    }

    .Courses-leftHand p {
        text-align: center;
        font-weight: 900;
    }

    .Courses-head > div {
        border-left: none !important;
    }

    .Courses-leftHand > div {
        padding-top: 5px;
        border-bottom: 1px dashed rgb(219, 219, 219);
    }

    .Courses-leftHand > div:last-child {
        border-bottom: none !important;
    }

    .left-hand-TextDom, .Courses-head {
        border-bottom: 1px solid rgba(0, 0, 0, 0.1) !important;
    }

    .Courses-content > ul {
        border-bottom: 1px dashed rgb(219, 219, 219);
        box-sizing: border-box;
    }

    .Courses-content > ul:last-child {
        border-bottom: none !important;
    }

    .highlight-week {
        color: #02a9f5 !important;
    }

    .Courses-content li {
        text-align: center;
        color: #666666;
        font-size: 14px;
        line-height: 50px;
    }

    .Courses-content li span {
        padding: 6px 2px;
        box-sizing: border-box;
        line-height: 18px;
        border-radius: 4px;
        white-space: normal;
        word-break: break-all;
        cursor: pointer;
    }

    .grid-active {
        z-index: 9999;
    }

    .grid-active span {
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    }
</style>

<body class="pos-r">
<div>
    <nav class="breadcrumb">
    	<i class="Hui-iconfont">&#xe67f;</i> 首页 
    	<span class="c-gray en">&gt;</span> 教师管理 
    	<span class="c-gray en">&gt;</span> 课表列表 
    	<span class="c-gray en">&gt;</span>
    	<span id="category">所有课表</span> 
    	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
    	<div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">日期范围:</label>
            <div class="formControls col-xs-2 col-sm-3">
                <span class="select-box">
				  <select class="select" size="1" name="cYear" id="cYear">
				    <option value="2020">2020</option>
				    <option value="2019">2019</option>
				    <option value="2018">2018</option>
				    <option value="2017">2017</option>
				  </select>
				</span>
        	</div>
	        <div class="formControls col-xs-2 col-sm-3">
	            <span class="select-box">
				  <select class="select" size="1" name="semester" id="semester">
				    <option value="1">第一学期</option>
				    <option value="2">第二学期</option>
				  </select>
				</span>
	         </div>
             <button name="" id="searchButton" type="button" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜课表</button>
        </div>
        <div class="text-l" id ="loading" style="visibility:hidden"> 正在加载中。。。。
        </div>
        <div class="mt-20">
            <div  id="coursesTable" class="mt-20" style="margin-bottom: 70px">
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
    /*初始化类别数据*/
    var cid=-1;

    var index = layer.load(3);
    
    function loading(bol){
		bol ? $("#loading").css("visibility","visible"):$("#loading").css("visibility","hidden");    	
    }
    
    $(document).ready(function(){
    	 COURSE.getCourseList();
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
    
    $("#searchButton").click(function(){
    	COURSE.getCourseList();
    });
    
    var week = window.innerWidth > 360 ? ['周一', '周二', '周三', '周四', '周五','周六','周日'] :
	        ['一', '二', '三', '四', '五','六','日'];
    var day = new Date().getDay();
    var courseType = [
        [{index: '1', name: '8:30'}, 1],
        [{index: '2', name: '9:30'}, 1],
        [{index: '3', name: '10:30'}, 1],
        [{index: '4', name: '11:30'}, 1],
        [{index: '5', name: '12:30'}, 1],
        [{index: '6', name: '14:30'}, 1],
        [{index: '7', name: '15:30'}, 1],
        [{index: '8', name: '16:30'}, 1],
        [{index: '9', name: '17:30'}, 1],
        [{index: '10', name: '18:30'}, 1],
        [{index: '11', name: '19:30'}, 1],
        [{index: '12', name: '20:30'}, 1]
    ];
    
    /*签到*/
    function attend(title,url,id){
    	setId(id);
    	var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    
    
    var tname = "";
    
    var courseList,ids;
    var Timetable;
    var COURSE = {
    	loadCourse : function(){
    		$("#coursesTable").html("");
    		 // 实例化(初始化课表)
    	    Timetable = new Timetables({
    	       el: '#coursesTable',
    	       timetables: courseList,
    	       week: week,
    	       timetableType: courseType,
    	       highlightWeek: day,
    	       gridOnClick: function (e) {
    	       	if(e.name == ""){
    	       		return;
    	       	}
	        	 attend("考勤列表","/attend-list",ids[COURSE.weeks(e.week)][e.index-1]);
    	       },
    	       styles: {
    	         Gheight: 50
    	       }
    	     });
    	},
    	getCourseList : function(){
    		loading(true);
    		$.ajax({
                url:"/item/timetable/list/"+$("#cYear").val()+"/"+$("#semester").val(),
                type: 'GET',
                dataType: "json",
                success:function (data) {
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    courseList = data.result.descs;
                    ids = data.result.ids;
                    COURSE.loadCourse();
                    loading(false);
                    layer.close(index);
                },
                error:function(XMLHttpRequest){
                    if(XMLHttpRequest.status!=200){
                        layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                    }
                    loading(false);
                }
            });
    	},
    	weeks : function(weeks){
    		switch(weeks)
    		{
    		case "周一":
    			return 0;
    		case "周二":
    			return 1;
    		case "周三":
    			return 2;
    		case "周四":
    			return 3;
    		case "周五":
    			return 4;
    		case "周六":
    			return 5;
    		case "周日":
    			return 6;
    		}
    	},
    }
    

</script>
</body>
</html>
