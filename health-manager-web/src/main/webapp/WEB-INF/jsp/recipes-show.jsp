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
    <form name="recipes-add" action="" method="post" class="form form-horizontal" id="recipes-add">
	    <div class="row cl">
	        <h3 class="form-label col-xs-4 col-sm-2"  style="color: tomato;text-decoration:underline;">食谱信息:</h3>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">食谱名称:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <input type="text" class="input-text" readonly value="" placeholder="请输入食谱名称" id="name" name="name"/>
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">功效:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <input type="text" class="input-text" readonly value="" placeholder="请输入功效" id="functional" name="functional">
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">口味:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <input type="text" class="input-text" readonly value="" placeholder="请输入口味" id="flavor" name="flavor">
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">食材:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <textarea class="input-text" value="" readonly placeholder="例:(xx:1份,xxx:500g)" id="ingredients" name="ingredients" style="min-height:80px;resize:none;"></textarea>
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">烹饪:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <input type="text" class="input-text" readonly value="" placeholder="请输入烹饪方法:例:(烤)" id="process" name="process">
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">时长:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <input type="text" class="input-text" readonly value="" placeholder="请输入时长:例:(&lt;15分钟)" id="time" name="time">
	        </div>
	    </div>
	    <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>类型:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
				  <select class="select" size="1" name="type" id="type" disabled>
				    <option value="0" selected>早餐</option>
				    <option value="1">午餐</option>
				    <option value="2">晚餐</option>
				  </select>
				</span>
            </div>
        </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">步骤:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	            <textarea class="input-text" value="" readonly  placeholder="例:(1.xxxxx,2.xxxxxxxx,3.xxxxx)" id="practice" name="practice" style="min-height:80px;resize:none;"></textarea>
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">卡路里(100克/千卡):</label>
	        <div class="formControls col-xs-8 col-sm-9">
	        	<input type="text" class="input-text" readonly id="calorie" name="calorie" placeholder="请输入卡路里">
	        </div>
	    </div>
	    <div class="row cl">
	    	<label class="form-label col-xs-3 col-sm-2">成分(100克):</label>
	        <label class="form-label col-xs-1 col-sm-1">碳水化合:</label>
	        <div class="formControls col-xs-2 col-sm-2">
	        	<input type="text" class="input-text" readonly id="components0" name="components[0]" placeholder="碳水化合" style="width:50%">克
	        </div>
	        <label class="form-label col-xs-1 col-sm-1">脂肪:</label>
	        <div class="formControls col-xs-2 col-sm-2">
	        	<input type="text" class="input-text" readonly id="components1" name="components[1]" placeholder="脂肪" style="width:50%">克
	        </div>
	        <label class="form-label col-xs-1 col-sm-1">蛋白质:</label>
	        <div class="formControls col-xs-2 col-sm-2">
	        	<input type="text" class="input-text" readonly id="components2" name="components[2]" placeholder="蛋白质" style="width:50%">克
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-3 col-sm-2">分类:</label>
	        <div class="formControls col-xs-3 col-sm-3">
	            <input type="text" hidden class="input-text" id="cid0" name="cid[0]" >
				<input type="text" readonly class="input-text" value="" placeholder="请选择分类" id="cname0" name="cname[0]" style="width:45%">
	        </div>
	        <div class="formControls col-xs-3 col-sm-3">
	            <input type="text" hidden class="input-text" id="cid1" name="cid[1]" >
				<input type="text" readonly class="input-text" value="" placeholder="" id="cname1" name="cname[1]" style="width:45%">
	        </div>
	        <div class="formControls col-xs-3 col-sm-3">
	            <input type="text" hidden class="input-text" id="cid2" name="cid[2]" >
				<input type="text" readonly class="input-text" value="" placeholder="" id="cname2" name="cname[2]" style="width:45%">
	        </div>
	    </div>
	    <div class="row cl">
	        <label class="form-label col-xs-4 col-sm-2">适用体质:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	        	<input type="text" readonly class="input-text" id="physique" name="physique" placeholder="请输入适用体质:例:(湿热质)" >
	        </div>
	    </div>
	    <div class="row cl">
	        <input type="text" hidden class="input-text" id="url" name="url" >
	        <label class="form-label col-xs-4 col-sm-2">原缩略图:</label>
	        <div class="formControls col-xs-8 col-sm-9">
	          	<a id="imageUrl" target="_blank">
	          		<image class="thumbnail" id="imageShow" style="height:110px;width:110px;" alt="lose image"/>
	        	</a>
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
<script type="text/javascript">
	
    var index = layer.load(3);

   	$.ajax({
       type: 'GET',
       url: '/item/recipes/show/'+parent.getId()+"/"+parent.fac,
       dataType: 'json',
       success: function(data){
           layer.close(index);
           
           $("#name").val(data.result.name);
           $("#functional").val(data.result.functional);
           $("#flavor").val(data.result.flavor);
           $("#ingredients").val(data.result.ingredients);
           $("#process").val(data.result.process);
           $("#time").val(data.result.time);
           $("#practice").val(data.result.practice);
           
           $("#url").val(data.result.url);
           
           $('#type').children('option').eq(data.result.type).prop('selected', true);
           
           $("#calorie").val(data.result.calorie);
           
           $("#components0").val(data.result.components[0]);
           $("#components1").val(data.result.components[1]);
           $("#components2").val(data.result.components[2]);
           
           $("#cid0").val(data.result.cid[0]);
           $("#cname0").val(data.result.cname[0]);
           if(data.result.cname.length > 1){
	           	$("#cname1").val(data.result.cname[1]);
	           	$("#cid1").val(data.result.cid[1]);
	           	if(data.result.cname.length > 2)
	           	{	$("#cname2").val(data.result.cname[2]);
	           		$("#cid2").val(data.result.cid[2]);}
           }
           
           $("#physique").val(data.result.physique);

           if("" == data.result.url)
           		data.result.url = "http://192.168.2.134/group1/M00/00/00/wKgChl5ZWnyARQUhAAA5gdvkdZk034.jpg";
         
           $("#url").val(data.result.url);
           $("#imageShow").attr("src",data.result.url);
           $("#imageUrl").attr("href",data.result.url);
           
       },
       error:function(XMLHttpRequest) {
           layer.close(index);
           layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
       },
   });

</script>
</body>
</html>