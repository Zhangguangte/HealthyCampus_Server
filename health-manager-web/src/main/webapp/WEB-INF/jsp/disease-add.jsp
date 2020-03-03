<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("detail") != null ? request.getParameter("detail") : "";
%>
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
    <form name="disease-add" action="" method="post" class="form form-horizontal" id="disease-add">
        <div class="row cl">
            <h3 class="form-label col-xs-4 col-sm-2"  style="color: tomato;text-decoration:underline;">基本信息:</h3>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>疾病名称:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写疾病名称" id="name" name="name"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>疾病别名:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写疾病别名:例:(暂无)" id="alias" name="alias"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>疾病简介:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="input-text" value="" placeholder="简介字数在20-400之间" id="introduce" name="introduce" style="min-height:80px;resize:none;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>传染性:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写传染性" id="contagious" name="contagious">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>多发人群:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写多发人群" id="population" name="population">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>有无医保:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写是否医保:例:(无)" id="insurance" name="insurance">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>临床检查:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写传临床检查" id="dcheck" name="dcheck">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>疾病部位:</label>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="pid0" name="pid[0]" >
				<input type="text" onclick="chooseCategory('选择部位分类',0,1,0)" readonly class="input-text" value="" placeholder="请选择疾病部位" id="part0" name="part[0]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择部位分类',0,1,0)" class="btn btn-secondary radius" value="选择部位">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="pid1" name="pid[1]" >
				<input type="text" onclick="chooseCategory('选择部位分类',0,1,1)" readonly class="input-text" value="" placeholder="请选择疾病部位" id="part1" name="part[1]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择部位分类',0,1,1)" class="btn btn-secondary radius" value="选择部位">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="pid2" name="pid[2]" >
				<input type="text" onclick="chooseCategory('选择部位分类',0,1,2)" readonly class="input-text" value="" placeholder="请选择疾病部位" id="part2" name="part[2]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择部位分类',0,1,2)" class="btn btn-secondary radius" value="选择部位">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>疾病科室:</label>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="did0" name="did[0]" >
				<input type="text" onclick="chooseCategory('选择科室分类',0,2,0)" readonly class="input-text" value="" placeholder="请选择疾病科室" id="depart0" name="depart[0]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择科室分类',0,2,0)" class="btn btn-secondary radius" value="选择科室">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="did1" name="did[1]" >
				<input type="text" onclick="chooseCategory('选择科室分类',0,2,1)" readonly class="input-text" value="" placeholder="请选择疾病科室" id="depart1" name="depart[1]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择科室分类',0,2,1)" class="btn btn-secondary radius" value="选择科室">
            </div>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" hidden class="input-text" id="did2" name="did[2]" >
				<input type="text" onclick="chooseCategory('选择科室分类',0,2,2)" readonly class="input-text" value="" placeholder="请选择疾病科室" id="depart2" name="depart[2]" style="width:45%">
                <input type="button" onclick="chooseCategory('选择科室分类',0,2,2)" class="btn btn-secondary radius" value="选择科室">
            </div>
        </div>
        <div class="row cl">
            <input type="text" hidden class="input-text" id="url" name="url" >
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>展示缩略图片上传：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="uploader-list-container">
                    <div class="queueList">
                        <div id="dndArea" class="placeholder">
                            <div name="filePicker" id="filePicker-2"></div>
                            <p>或将照片拖到这里，最多可选1张</p>
                        </div>
                    </div>
                    <div class="statusBar" style="display:none;">
                        <div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
                        <div class="info"></div>
                        <div class="btns">
                            <!-- <div id="filePicker2"></div> -->
                            <div class="uploadBtn">开始上传</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row cl">
            <h3 class="form-label col-xs-4 col-sm-2"  style="color: tomato;text-decoration:underline;">疾病知识:</h3>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>并发症:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写并发症" id="complication" name="complication">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>典型症状:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写典型症状:例:(xxx xxx)" id="symptoms" name="symptoms">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>病因:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="input-text" value="" placeholder="简介字数在20-400之间" id="dcase" name="dcase" style="min-height:80px;resize:none;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>预防:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="input-text" value="" placeholder="简介字数在20-400之间" id="prevention" name="prevention" style="min-height:80px;resize:none;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <h3 class="form-label col-xs-4 col-sm-2"  style="color: tomato;text-decoration:underline;">治疗知识:</h3>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>推荐药品:</label>
             <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写推荐药品:例:(xxx xxx)" id="drug" name="drug">
            </div>
        </div>
        <div class="row cl">
             <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>治疗方法:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写治疗方法:例:(xx xxx)" id="way" name="way">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>治疗率:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写治疗率:例:(100%)" id="rate" name="rate">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>治疗费用:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写治疗费用" id="cost" name="cost">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>治疗时间:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请填写治疗时间:例:(2-4个月)" id="time" name="time">
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
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script>
<link rel="stylesheet" href="lib/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="lib/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="lib/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="lib/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="lib/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
	var editor;
	var type; 
	var factor; 
	var num;
	
	function giveCid(cid){
		if(factor == 1)
			$("#pid" + num).val(cid);
		else
			$("#did" + num).val(cid);
	   
	}
	
	function giveCName(cname){
		if(factor == 1)
	    	$("#part" + num).val(cname);
		else
			$("#depart" + num).val(cname);
	}
	
	function chooseCategory(title,type,factor,num){
		this.type = type;
		this.factor = factor;
		this.num = num;
	    layer_show(title,"choose-category",300,510);
	}
	
	//保存发布
	$("#disease-add").validate({
	    rules:{
	    	name:{
	            required:true,
	            maxlength:50,
	        },
	        alias:{
	            required:true,
	            maxlength:200,
	        },
	        introduce:{
	            required:true,
	            minlength:20,
	            maxlength:400,
	        },
	        contagious:{
	            required:true,
	            maxlength:100,
	        },
	        population:{
	            required:true,
	            maxlength:100,
	        },
	        insurance:{
	            required:true,
	            maxlength:100,
	        },
	        dcheck:{
	            required:true,
	            maxlength:200,
	        },
	        "part[0]":{
	            required:true,
	            maxlength:20,
	        },
	        "depart[0]":{
	            required:true,
	            maxlength:20,
	        },
	        complication:{
	            required:true,
	            maxlength:100,
	        },
	        symptoms:{
	            required:true,
	            maxlength:100,
	        },
	        prevention:{
	            required:true,
	            maxlength:400,
	        },
	        dcase:{
	            required:true,
	            minlength:20,
	            maxlength:400,
	        },
	        drug:{
	            required:true,
	            maxlength:200,
	        },
	        way:{
	            required:true,
	            maxlength:200,
	        },
	        rate:{
	            required:true,
	            maxlength:100,
	        },
	        cost:{
	            required:true,
	            maxlength:100,
	        },
	        time:{
	            required:true,
	            maxlength:60,
	        }
	    },
	    onkeyup:false,
	    focusCleanup:false,
	    success:"valid",
	    submitHandler:function(form){
	    	if(images==null){
                layer.alert('请上传疾病展示缩略图! ', {title: '错误信息',icon: 0});
                return;
            }
	        var index = layer.load(3);
	        $(form).ajaxSubmit({
	            url: "/item/disease/add",
	            type: "POST",
	            dataType: "json",
	            success: function(data) {
	            	layer.close(index);
                    if(data.success==true){
                        if(parent.location.pathname!='/'){
                            parent.DiseaseCount();
                            parent.refresh();
                            parent.msgSuccess("添加成功!");
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }else{
                            layer.confirm('添加成功!', {
                                btn: ['确认'],icon: 1
                            }, function(){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        }
                    }else{
                        layer.alert(data.message, {title: '错误信息',icon: 2});
                    }
	            },
	            error:function(XMLHttpRequest) {
	                layer.close(index);
	                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
	            }
	        });
	    }
	});

    var images=null;

    (function ($) {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        // 当domReady的时候开始初始化
        $(function () {
            var $wrap = $('.uploader-list-container'),

                // 图片容器
                $queue = $('<ul class="filelist"></ul>')
                    .appendTo($wrap.find('.queueList')),

                // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

                // 文件总体选择信息。
                $info = $statusBar.find('.info'),

                // 上传按钮
                $upload = $wrap.find('.uploadBtn'),

                // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),

                $progress = $statusBar.find('.progress').hide(),

                // 添加的文件数量
                fileCount = 0,

                // 添加的文件总大小
                fileSize = 0,

                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

                // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,

                // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',

                // 所有文件的进度信息，key为file id
                percentages = {},
                // 判断浏览器是否支持图片的base64
                isSupportBase64 = (function () {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function () {
                        if (this.width != 1 || this.height != 1) {
                            support = false;
                        }
                    }
                    data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                    return support;
                })(),

                // 检测是否已经安装flash，检测flash的版本
                flashVersion = (function () {
                    var version;

                    try {
                        version = navigator.plugins['Shockwave Flash'];
                        version = version.description;
                    } catch (ex) {
                        try {
                            version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                        } catch (ex2) {
                            version = '0.0';
                        }
                    }
                    version = version.match(/\d+/g);
                    return parseFloat(version[0] + '.' + version[1], 10);
                })(),

                supportTransition = (function () {
                    var s = document.createElement('p').style,
                        r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                    s = null;
                    return r;
                })(),

                // WebUploader实例
                uploader;

            if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

                // flash 安装了但是版本过低。
                if (flashVersion) {
                    (function (container) {
                        window['expressinstallcallback'] = function (state) {
                            switch (state) {
                                case 'Download.Cancelled':
                                    alert('您取消了更新！')
                                    break;

                                case 'Download.Failed':
                                    alert('安装失败')
                                    break;

                                default:
                                    alert('安装已成功，请刷新！');
                                    break;
                            }
                            delete window['expressinstallcallback'];
                        };

                        var swf = 'expressInstall.swf';
                        // insert flash object
                        var html = '<object type="application/' +
                            'x-shockwave-flash" data="' + swf + '" ';

                        if (WebUploader.browser.ie) {
                            html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                        }

                        html += 'width="100%" height="100%" style="outline:0">' +
                            '<param name="movie" value="' + swf + '" />' +
                            '<param name="wmode" value="transparent" />' +
                            '<param name="allowscriptaccess" value="always" />' +
                            '</object>';

                        container.html(html);

                    })($wrap);

                    // 压根就没有安转。
                } else {
                    $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
                }

                return;
            } else if (!WebUploader.Uploader.support()) {
                alert('Web Uploader 不支持您的浏览器！');
                return;
            }

            // 实例化
            uploader = WebUploader.create({
                pick: {
                    id: '#filePicker-2',
                    label: '点击选择图片',
                    multiple: false			//单张上传
                },
                formData: {
                    uid: 123
                },
                dnd: '#dndArea',
                paste: '#uploader',
                swf: 'lib/webuploader/0.1.5/Uploader.swf',
                chunked: false,
                chunkSize: 512 * 1024,
                server: '/pic/upload',
                // runtimeOrder: 'flash',

                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    //mimeTypes: 'image/*'
                },

                // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
                disableGlobalDnd: true,
                fileNumLimit: 1,
                fileSizeLimit: 25 * 1024 * 1024,    // 25 M
                fileSingleSizeLimit: 5 * 1024 * 1024    // 5 M
            });

            // 拖拽时不接受 js, txt 文件。
            uploader.on('dndAccept', function (items) {
                var denied = false,
                    len = items.length,
                    i = 0,
                    // 修改js类型
                    unAllowed = 'text/plain;application/javascript';

                for (; i < len; i++) {
                    // 如果在列表里面
                    if (~unAllowed.indexOf(items[i].type)) {
                        denied = true;
                        break;
                    }
                }

                return !denied;
            });

            uploader.on('dialogOpen', function () {
                console.log('here');
            });

           /*  // 添加“添加文件”的按钮，
            uploader.addButton({
                id: '#filePicker2',
                label: '继续添加'
            }); */

            uploader.on('ready', function () {
                window.uploader = uploader;
            });

            // 当有文件添加进来时执行，负责view的创建
            function addFile(file) {
                var $li = $('<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>' +
                    '<p class="progress"><span></span></p>' +
                    '</li>'),

                    $btns = $('<div class="file-panel">' +
                        '<span class="cancel">删除</span>' +
                        '<span class="rotateRight">向右旋转</span>' +
                        '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find('p.imgWrap'),
                    $info = $('<p class="error"></p>'),

                    showError = function (code) {
                        switch (code) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;

                            case 'interrupt':
                                text = '上传暂停';
                                break;

                            default:
                                text = '上传失败，请重试';
                                break;
                        }

                        $info.text(text).appendTo($li);
                    };

                if (file.getStatus() === 'invalid') {
                    showError(file.statusText);
                } else {
                    // @todo lazyload
                    $wrap.text('预览中');
                    uploader.makeThumb(file, function (error, src) {
                        var img;

                        if (error) {
                            $wrap.text('不能预览');
                            return;
                        }

                        if (isSupportBase64) {
                            img = $('<img src="' + src + '">');
                            $wrap.empty().append(img);
                        } else {
                            $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                                method: 'POST',
                                data: src,
                                dataType: 'json'
                            }).done(function (response) {
                                if (response.result) {
                                    img = $('<img src="' + response.result + '">');
                                    $wrap.empty().append(img);
                                } else {
                                    $wrap.text("预览出错");
                                }
                            });
                        }
                    }, thumbnailWidth, thumbnailHeight);

                    percentages[file.id] = [file.size, 0];
                    file.rotation = 0;
                }

                file.on('statuschange', function (cur, prev) {
                    if (prev === 'progress') {
                        $prgress.hide().width(0);
                    } else if (prev === 'queued') {
                        $li.off('mouseenter mouseleave');
                        $btns.remove();
                    }

                    // 成功
                    if (cur === 'error' || cur === 'invalid') {
                        console.log(file.statusText);
                        showError(file.statusText);
                        percentages[file.id][1] = 1;
                    } else if (cur === 'interrupt') {
                        showError('interrupt');
                    } else if (cur === 'queued') {
                        percentages[file.id][1] = 0;
                    } else if (cur === 'progress') {
                        $info.remove();
                        $prgress.css('display', 'block');
                    } else if (cur === 'complete') {
                        $li.append('<span class="success"></span>');
                    }

                    $li.removeClass('state-' + prev).addClass('state-' + cur);
                });

                $li.on('mouseenter', function () {
                    $btns.stop().animate({height: 30});
                });

                $li.on('mouseleave', function () {
                    $btns.stop().animate({height: 0});
                });

                $btns.on('click', 'span', function () {
                    var index = $(this).index(),
                        deg;

                    switch (index) {
                        case 0:
                            uploader.removeFile(file);
                            return;

                        case 1:
                            file.rotation += 90;
                            break;

                        case 2:
                            file.rotation -= 90;
                            break;
                    }

                    if (supportTransition) {
                        deg = 'rotate(' + file.rotation + 'deg)';
                        $wrap.css({
                            '-webkit-transform': deg,
                            '-mos-transform': deg,
                            '-o-transform': deg,
                            'transform': deg
                        });
                    } else {
                        $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                        // use jquery animate to rotation
                        // $({
                        //     rotation: rotation
                        // }).animate({
                        //     rotation: file.rotation
                        // }, {
                        //     easing: 'linear',
                        //     step: function( now ) {
                        //         now = now * Math.PI / 180;

                        //         var cos = Math.cos( now ),
                        //             sin = Math.sin( now );

                        //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                        //     }
                        // });
                    }


                });

                $li.appendTo($queue);
            }

            // 负责view的销毁
            function removeFile(file) {
                var $li = $('#' + file.id);

                delete percentages[file.id];
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
            }

            function updateTotalProgress() {
                var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

                $.each(percentages, function (k, v) {
                    total += v[0];
                    loaded += v[0] * v[1];
                });

                percent = total ? loaded / total : 0;


                spans.eq(0).text(Math.round(percent * 100) + '%');
                spans.eq(1).css('width', Math.round(percent * 100) + '%');
                updateStatus();
            }

            function updateStatus() {
                var text = '', stats;

                if (state === 'ready') {
                    text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize(fileSize) + '。';
                } else if (state === 'confirm') {
                    stats = uploader.getStats();
                    if (stats.uploadFailNum) {
                        text = '已成功上传' + stats.successNum + '张照片，' +
                            stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                    }

                } else {
                    stats = uploader.getStats();
                    text = '共' + fileCount + '张（' +
                        WebUploader.formatSize(fileSize) +
                        '），已上传' + stats.successNum + '张';

                    if (stats.uploadFailNum) {
                        text += '，失败' + stats.uploadFailNum + '张';
                    }
                }

                $info.html(text);
            }

            function setState(val) {
                var file, stats;

                if (val === state) {
                    return;
                }

                $upload.removeClass('state-' + state);
                $upload.addClass('state-' + val);
                state = val;

                switch (state) {
                    case 'pedding':
                        $placeHolder.removeClass('element-invisible');
                        $queue.hide();
                        $statusBar.addClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'ready':
                        $placeHolder.addClass('element-invisible');
                        /* $('#filePicker2').removeClass('element-invisible'); */
                        $queue.show();
                        $statusBar.removeClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'uploading':
                        /* $('#filePicker2').addClass('element-invisible'); */
                        $progress.show();
                        $upload.text('暂停上传');
                        break;

                    case 'paused':
                        $progress.show();
                        $upload.text('继续上传');
                        break;

                    case 'confirm':
                        $progress.hide();
                        /* $('#filePicker2').removeClass('element-invisible'); */
                        $upload.text('开始上传');

                        stats = uploader.getStats();
                        if (stats.successNum && !stats.uploadFailNum) {
                            setState('finish');
                            return;
                        }
                        break;
                    case 'finish':
                        stats = uploader.getStats();
                        if (stats.successNum) {
                            //alert('上传成功');
                        } else {
                            // 没有成功的图片，重设
                            state = 'done';
                            location.reload();
                        }
                        break;
                }

                updateStatus();
            }

            uploader.onUploadProgress = function (file, percentage) {
                var $li = $('#' + file.id),
                    $percent = $li.find('.progress span');

                $percent.css('width', percentage * 100 + '%');
                percentages[file.id][1] = percentage;
                updateTotalProgress();
            };

            uploader.onFileQueued = function (file) {
                fileCount++;
                fileSize += file.size;

                if (fileCount === 1) {
                    $placeHolder.addClass('element-invisible');
                    $statusBar.show();
                }

                addFile(file);
                setState('ready');
                updateTotalProgress();
            };

            uploader.onFileDequeued = function (file) {
                fileCount--;
                fileSize -= file.size;

                if (!fileCount) {
                    setState('pedding');
                }

                removeFile(file);
                updateTotalProgress();

            };
            // 文件上传成功
            uploader.on( 'uploadSuccess', function( file,data ) {
            	if(data.success==true){
                	$("#url").val(data.result);
                	images=data.result;
                }else{
                    layer.alert("上传失败:"+data.message)
                }
            });

            uploader.on('all', function (type) {
                var stats;
                switch (type) {
                    case 'uploadFinished':
                        setState('confirm');
                        break;

                    case 'startUpload':
                        setState('uploading');
                        break;

                    case 'stopUpload':
                        setState('paused');
                        break;

                }
            });

            uploader.onError = function (code) {
                if(code=="Q_TYPE_DENIED"){
                	layer.alert("文件类型不支持，仅支持gif,jpg,jpeg,bmp,png格式图片");
                }else if(code=="F_DUPLICATE"){
                	layer.alert("文件已选中，请勿重复上传");
                }else if(code="F_EXCEED_SIZE"){
                    layer.alert("文件大小超出限制，单张图片不得超过5MB");
                }else if(code="Q_EXCEED_NUM_LIMIT"){
                	layer.alert("最多上传1张图片");
                }else{
                	layer.alert('Error: ' + code);
                }

            };

            $upload.on('click', function () {
                if ($(this).hasClass('disabled')) {
                    return false;
                }

                if (state === 'ready') {
                    uploader.upload();
                } else if (state === 'paused') {
                    uploader.upload();
                } else if (state === 'uploading') {
                    uploader.stop();
                }
            });

            $info.on('click', '.retry', function () {
                uploader.retry();
            });

            $info.on('click', '.ignore', function () {
            	layer.alert('已忽略');
            });

            $upload.addClass('state-' + state);
            updateTotalProgress();
        });

    })(jQuery);
</script>
</body>
</html>