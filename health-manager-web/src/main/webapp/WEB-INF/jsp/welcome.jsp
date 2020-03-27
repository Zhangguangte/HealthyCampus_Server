<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="muyoucha">
<link rel="Shortcut Icon" href="icon/H.png" />
<title>校园健康后台管理系统 v1.0</title>
<meta name="keywords" content="校园健康后台管理系统 v1.0,Health,校园健康后台管理系统">
<meta name="description"
	content="校园健康后台管理系统 v1.0，是一款校园健康后台管理系统，适合中小型CMS后台系统。">

<!-- Bootstrap core CSS -->
<link href="lib/flatlab/css/bootstrap.min.css" rel="stylesheet">
<link href="lib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="lib/flatlab/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link
	href="lib/flatlab/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="lib/flatlab/css/owl.carousel.css"
	type="text/css">
<!-- Custom styles for this template -->
<link href="lib/flatlab/css/style.css" rel="stylesheet">
<link href="lib/flatlab/css/style-responsive.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
    <script src="lib/flatlab/js/html5shiv.js"></script>
    <script src="lib/flatlab/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
#main-content {
	margin-left: 20px;
	margin-top: -50px;
}

.site-footer .text-center a {
	color: #53bee6;
}
</style>
<body>
	<section id="container">
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!--state overview start-->
				<div class="row state-overview">
					<div class="col-lg-3 col-sm-6">
						<section class="panel">
							<div class="symbol terques">
								<i class="icon-user"></i>
							</div>
							<div class="value">
								<h1 class="count">...</h1>
								<p>用户总数</p>
							</div>
						</section>
					</div>
					<div class="col-lg-3 col-sm-6">
						<section class="panel">
							<div class="symbol blue">
								<i class="icon-bar-chart"></i>
							</div>
							<div class="value">
								<h1 class=" count4">...</h1>
								<p>浏览量</p>
							</div>
						</section>
					</div>
				</div>
				<!--state overview end-->

				<div class="row">
					<div class="col-lg-7">
						<!--custom chart start-->
						<div class="border-head">
							<h3>系统统计</h3>
						</div>
						<div class="custom-bar-chart">
							<ul class="y-axis">
								<li><span>100</span></li>
								<li><span>80</span></li>
								<li><span>60</span></li>
								<li><span>40</span></li>
								<li><span>20</span></li>
								<li><span>0</span></li>
							</ul>
							<div class="bar">
								<div class="title">JAN</div>
								<div id="jan" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">FEB</div>
								<div id="feb" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">90%</div>
							</div>
							<div class="bar ">
								<div class="title">MAR</div>
								<div id="mar" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">APR</div>
								<div id="apr" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar">
								<div class="title">MAY</div>
								<div id="may" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">JUN</div>
								<div id="jun" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar">
								<div class="title">JUL</div>
								<div id="jul" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">AUG</div>
								<div id="aug" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">SEP</div>
								<div id="sep" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">OCT</div>
								<div id="oct" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">NOV</div>
								<div id="nov" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
							<div class="bar ">
								<div class="title">DEC</div>
								<div id="dec" class="value1 tooltips" data-original-title="0%"
									data-toggle="tooltip" data-placement="top">0%</div>
							</div>
						</div>
						<!--custom chart end-->
					</div>

					<div class="col-lg-5">
						<!--widget start-->
						<section class="panel">
							<header class="panel-heading tab-bg-dark-navy-blue">
								<ul class="nav nav-tabs nav-justified ">
									<li class="active"><a href="#popular" data-toggle="tab">
											公告 </a></li>
									<!-- <li>
                                    <a href="#comments" data-toggle="tab">
                                        评论
                                    </a>
                                </li> -->
									<li class=""><a href="#recent" data-toggle="tab"> 最新通知
									</a></li>
								</ul>
							</header>
							<div class="panel-body">
								<div class="tab-content tasi-tab">
									<div class="tab-pane active" id="popular">
										<article class="media">
											<a class="pull-left thumb p-thumb"> <img
												src="lib/flatlab/img/product1.jpg">
											</a>
											<div class="media-body">
												<a class="p-head"> 尊敬的 <span id="username"></span>， <span
													id="hello"></span> 现在时间是：<span id="currentTime"></span></a>
												<p>
													<br>健康校园是基于SOA架构的大型开源分布式校园健康管理平台，前后端开发分离，前台
													基于MVP开发的Android端，前台App下载演示地址：<a href="" target="_blank">点我查看</a><br>
													<br>
													后台几乎完成所有功能开发，你所看到的几乎皆为真实后台数据，为避免数据遭恶意修改，测试体验账号只具备查看权限。
													后台主要使用技术有Dubbo/SSM/SolrCloud/Redis/MySQL/ActiveMQ/FastDFS/Zookeeper等<br>
													<br> Github后台源码:<a
														href="https://github.com/Zhangguangte/HealthyCampus_Server"
														target="_blank"> <img
														src="https://img.shields.io/github/stars/Zhangguangte/HealthyCampus_Server.svg?style=social&label=Stars">
														<img
														src="https://img.shields.io/github/forks/Zhangguangte/HealthyCampus_Server.svg?style=social&label=Fork"></a>
													前台：<a href="https://github.com/Zhangguangte/HealthyCampus"
														target="_blank"> <img
														src="https://img.shields.io/github/stars/Zhangguangte/HealthyCampus.svg?style=social"></a><br>
													Fork或下载前请动动手指Star一下~<br>
													<br> <br>
													<br> 作者目前大四，项目还有许多不足与更多需要学习的地方，期待您的指点与赞赏捐赠支持！
												</p>
											</div>
										</article>
									</div>
									<div
										style="overflow-y: scroll; height: 320px; margin: -15px -15px 0 0"
										class="tab-pane" id="comments">
										<article class="media">
											<div id="SOHUCS" sid="12345678"></div>
										</article>
									</div>
									<div class="tab-pane " id="recent">
										<div style="text-align: center">可怜可怜孩子吧 !( 》 - . - 《 )!</div>
										<div style="text-align: center; margin-top: 10px;">
											<img width="180px" height="180px" src="icon/income1.png"
												style="margin-right: 10px;"> <img width="180px"
												height="180px" src="icon/income2.png"
												style="margin-left: 10px;">
										</div>
									</div>
								</div>
							</div>
						</section>
						<!--widget end-->
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4">
						<!--user info table start-->
						<section class="panel">
							<div class="panel-body">
								<a href="http://blog.exrick.cn" target="_blank"
									class="task-thumb"> <img width="83px" height="83px"
									src="lib/flatlab/img/avatar1.jpg" alt="">
								</a>
								<div class="task-thumb-details">
									<h1>
										<a>小友，请留步</a>
									</h1>
									<p>Author</p>
								</div>
							</div>
							<table class="table table-hover personal-task">
								<tbody>
									<tr>
										<td><i class=" icon-tasks"></i></td>
										<td><a href="#"> 个人简介（暂时没做） </a> <a href="#">
												个人博客（暂时没做） </a></td>
										<td><span style="margin-top: -1px"
											class="label label-primary pull-right r-activity">02</span></td>
									</tr>
									<tr>
										<td><i class=" icon-music"></i></td>
										<td><a href="#"> 网易云音乐（暂时没做） </a></td>
										<td><span style="margin-top: -1px"
											class="label label-info pull-right r-activity">01</span></td>
									</tr>
									<tr>
										<td><i class="icon-envelope"></i></td>
										<td><a href="mailto:741737219@qq.com" target="_blank">741737219@qq.com</a>
										</td>
										<td><span style="margin-top: -1px"
											class="label label-warning pull-right r-activity">01</span></td>
									</tr>
									<tr>
										<td><i class=" icon-bell-alt"></i></td>
										<td><a href="#"> Github （暂时没做） </a> <a href="#">
												Bilibili （暂时没做） </a></td>
										<td><span style="margin-top: -1px"
											class="label label-success pull-right r-activity">02</span></td>
									</tr>
								</tbody>
							</table>
						</section>
						<!--user info table end-->
					</div>
					<div class="col-lg-8">
						<!--work progress start-->
						<section class="panel">
							<div class="panel-body progress-panel">
								<div class="task-progress">
									<h1>工作进度</h1>
									<p>小友，请留步</p>
								</div>
								<div class="task-option">
									<select class="styled">
										<option>小友</option>
										<option>欢迎您加入开发</option>
									</select>
								</div>
							</div>
							<table class="table table-hover personal-task">
								<tbody>
									<tr>
										<td>1</td>
										<td>前台校园健康App</td>
										<td><span class="badge bg-important">90%</span></td>
										<td>
											<div id="work-progress1"></div>
										</td>
									</tr>
									<tr>
										<td>2</td>
										<td>后台管理系统</td>
										<td><span class="badge bg-success">90%</span></td>
										<td>
											<div id="work-progress2"></div>
										</td>
									</tr>
									<tr>
										<td>3</td>
										<td>整合，统一系统</td>
										<td><span class="badge bg-info">90%</span></td>
										<td>
											<div id="work-progress3"></div>
										</td>
									</tr>
									<tr>
										<td>4</td>
										<td>毕业设计</td>
										<td><span class="badge bg-warning">90%</span></td>
										<td>
											<div id="work-progress4"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</section>
						<!--work progress end-->
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8">
						<!--timeline start-->
						<section class="panel">
							<div class="panel-body">
								<div class="text-center mbot30">
									<h3 class="timeline-title">小友更新日志</h3>
									<p class="t-info">This is a project timeline</p>
								</div>

								<div class="timeline">
									<article class="timeline-item">
										<div class="timeline-desk">
											<div class="panel">
												<div class="panel-body">
													<span class="arrow"></span> <span class="timeline-icon red"></span>
													<span class="timeline-date">11:25 am</span>
													<h1 class="red">13 Feb | Thursday</h1>
													<p>待更新</p>
												</div>
											</div>
										</div>
									</article>
									 <article class="timeline-item alt">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon green"></span>
                                                <span class="timeline-date">17:00 pm</span>
                                                <h1 class="green">15 May | Sunday</h1>
                                                <p><a href="#" target="_blank">小友</a> 完成目前所有功能开发 <span><a href="#" class="green" target="_blank">v1.0发布</a></span></p>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow"></span>
                                                <span class="timeline-icon blue"></span>
                                                <span class="timeline-date">15:45 pm</span>
                                                <h1 class="blue">9 May | Monday</h1>
                                                <p><a href="http://blog.exrick.cn" target="_blank">小友</a> 完成后端接口改造二次开发 <span><a class="blue" href="#" target="_blank">HEALTH-Front</a></span></p>
                                            </div>
                                        </div>
                                    </div>
                                </article>
									<article class="timeline-item alt">
										<div class="timeline-desk">
											<div class="panel">
												<div class="panel-body">
													<span class="arrow-alt"></span> <span
														class="timeline-icon purple"></span> <span
														class="timeline-date">14:00 pm</span>
													<h1 class="purple">10 Feb | Monday</h1>
													<p>
														感谢 
														<a href="#"target="_blank">Exrick</a> 的开源 
														<a href="https://github.com/Exrick/xmall" target="_blank">XMall</a>
														项目提供后台页面及框架支持
													</p>
													<div class="notification">
														<i class=" icon-exclamation-sign"></i> 启动了后台项目 <a href="#">Health-Manager</a>
													</div>
												</div>
											</div>
										</div>
									</article>
									<article class="timeline-item">
										<div class="timeline-desk">
											<div class="panel">
												<div class="panel-body">
													<span class="arrow"></span> <span
														class="timeline-icon light-green"></span> <span
														class="timeline-date">14:00 pm</span>
													<h1 class="light-green">6 Oct | Sunday</h1>
													<p>
														<a href="#">小友</a> 开始编写 <span><a href="#"
															class="light-green">校园健康App</a></span>
													</p>
												</div>
											</div>
										</div>
									</article>
								</div>

								<div class="clearfix">&nbsp;</div>
							</div>
						</section>
						<!--timeline end-->
					</div>
					<div class="col-lg-4">

						<!--weather statement start-->
						<section class="panel">
							<div class="weather-bg">
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-6">
											<i id="weather" class="icon-cloud"></i> <span id="city">...</span>
										</div>
										<div class="col-xs-6">
											<div class="degree">
												<span id="degree">...</span>
											</div>
										</div>
									</div>
								</div>
							</div>

							<footer class="weather-category">
								<ul>
									<li class="active">
										<h5>更新时间</h5> <span id="updateTime">...</span>
									</li>
									<li class="active">
										<h5>当前温度</h5> <span id="curTem">...</span>
									</li>
									<li class="active">
										<h5>湿度</h5> <span id="humidity">...</span>
									</li>
									<ul>
									</ul>
									<li>
										<h5>空气质量</h5> <span id="airCondition">...</span>
									</li>
									<li>
										<h5>风力</h5> <span id="wind">...</span>
									</li>
								</ul>
							</footer>
						</section>
						<!--weather statement end-->
					</div>

					<div class="col-lg-4">
						<section class="panel post-wrap pro-box">
							<aside>
								<div class="post-info">
									<span class="arrow-pro left"></span>
									<div class="panel-body">
										<div class="text-center twite">
											<h1>Follow Me</h1>
										</div>

										<footer class="social-footer">
											<ul>
												<li><a href="#"> <i class="icon-github"></i>
												</a></li>
												<li class="active"><a href="#"> <img
														style="margin-bottom: 3px" width="40px" height="40px"
														src="icon/qq.png"></img>
												</a></li>
												<li><a href="#"> <i class="icon-pinterest"></i>
												</a></li>
											</ul>
										</footer>
									</div>
								</div>
							</aside>
						</section>
						twitter feedback end
					</div>
				</div>
			</section>
		</section>
		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				Copyright &copy;2020 <a href="#">小友，请留步</a> All Rights Reserved.
				本后台系统由<a href="http://www.h-ui.net/" target="_blank"> H-ui</a>、<a
					href="https://themeforest.net/item/flatlab-bootstrap-3-responsive-admin-template/5902687"
					target="_blank">FlatLab </a>提供前端静态页面支持 <a href="#" class="go-top">
					<i class="icon-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="lib/flatlab/js/jquery.js"></script>
	<script src="lib/flatlab/js/jquery-1.8.3.min.js"></script>
	<script src="lib/flatlab/js/bootstrap.min.js"></script>
	<script src="lib/flatlab/js/jquery.scrollTo.min.js"></script>
	<script src="lib/flatlab/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script src="lib/flatlab/js/jquery.sparkline.js" type="text/javascript"></script>
	<script
		src="lib/flatlab/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="lib/flatlab/js/owl.carousel.js"></script>
	<script src="lib/flatlab/js/jquery.customSelect.min.js"></script>
	<script src="lib/flatlab/js/respond.min.js"></script>

	<script class="include" type="text/javascript"
		src="lib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>

	<!--common script for all pages-->
	<script src="lib/flatlab/js/common-scripts.js"></script>

	<script charset="utf-8" type="text/javascript" src="lib/changyan.js"></script>
	<script async src="lib/busuanzi.pure.mini.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>

	<!--script for this page-->
	<script src="lib/flatlab/js/sparkline-chart.js"></script>
	<script src="lib/flatlab/js/easy-pie-chart.js"></script>
	<script src="lib/flatlab/js/count.js"></script>

	<!-- 统计浏览量 -->
	<!-- <span id="busuanzi_container_site_uv">
  <span style="display: none" id="busuanzi_value_site_uv"></span>
</span> -->

<script>
	$("#username").html(parent.username);

	var now = new Date(), hour = now.getHours();
	if (hour < 6) {
		$("#hello").html("凌晨好！");
	} else if (hour < 9) {
		$("#hello").html("早上好！");
	} else if (hour < 12) {
		$("#hello").html("上午好！");
	} else if (hour < 14) {
		$("#hello").html("中午好！");
	} else if (hour < 17) {
		$("#hello").html("下午好！");
	} else if (hour < 19) {
		$("#hello").html("傍晚好！");
	} else if (hour < 22) {
		$("#hello").html("晚上好！");
	} else {
		$("#hello").html("深夜好！");
	}

	$(function() {
		setInterval(function() {
			$("#currentTime").text(new Date().toLocaleString());
		}, 1000);

		WELCOME.browseCount();

	});

		/* window.changyan.api.config({
		    appid: '',
		    conf: ''
		}); */

		//owl carousel
	$(document).ready(function() {
		$("#owl-demo").owlCarousel({
			navigation : true,
			slideSpeed : 300,
			paginationSpeed : 400,
			singleItem : true,
			autoPlay : true

		});
	});

	//custom select box
	$(function() {
		$('select.styled').customSelect();
	});

	/*统计用户数*/
	$.ajax({
		url : "/user/count",
		type : "GET",
		dataType : "json",
		success : function(data) {
			countUp(data.recordsTotal);
		},
		error : function(XMLHttpRequest) {
			layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status + ' 错误信息:'
					+ JSON.parse(XMLHttpRequest.responseText).message, {
				title : '错误信息',
				icon : 2
			});
		}
	});

	var WELCOME = {
		//用户进入欢迎界面，浏览量加1
		browseCount : function() {
			$.ajax({
				type : 'GET',
				dataType : "json",
				url : "/system/browseCount",
				success : function(data) {
					if (data.success == true) {
						//设置每个月对应的浏览量比例
						countUp4(data.recordsTotal);
						
						$("#jan").html(Math.round(data.data[0] / data.recordsTotal * 10000 )/100 + "%");
						$("#jan").attr("data-original-title",Math.round(data.data[0] / data.recordsTotal*10000)/100 + "%");
						$("#jan").attr('style','height:' + Math.round(data.data[0] / data.recordsTotal*10000)/100 + "%;");
	
						$("#feb").html(Math.round(data.data[1] / data.recordsTotal*10000)/100 + "%");
						$("#feb").attr("data-original-title",Math.round(data.data[1] / data.recordsTotal*10000)/100 + "%");
						$("#feb").attr('style','height:' + Math.round(data.data[1] / data.recordsTotal*10000)/100 + "%;");
	
						$("#mar").html(Math.round(data.data[2] / data.recordsTotal * 10000 )/100+ "%");
						$("#mar").attr("data-original-title",Math.round(data.data[2] / data.recordsTotal * 10000 )/100 + "%");
						$("#mar").attr('style','height:' + Math.round(data.data[2] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#apr").html(Math.round(data.data[3] / data.recordsTotal * 10000 )/100 + "%");
						$("#apr").attr("data-original-title",Math.round(data.data[3] / data.recordsTotal * 10000 )/100 + "%");
						$("#apr").attr('style','height:' + Math.round(data.data[3] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#may").html(Math.round(data.data[4] / data.recordsTotal * 10000 )/100 + "%");
						$("#may").attr("data-original-title",Math.round(data.data[4] / data.recordsTotal * 10000 )/100+ "%");
						$("#may").attr('style','height:' + Math.round(data.data[4] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#jun").html(Math.round(data.data[5] / data.recordsTotal * 10000 )/100 + "%");
						$("#jun").attr("data-original-title",Math.round(data.data[5] / data.recordsTotal * 10000 )/100 + "%");
						$("#jun").attr('style','height:' + Math.round(data.data[5] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#jul").html( Math.round(data.data[6] / data.recordsTotal * 10000 )/100 + "%");
						$("#jul").attr("data-original-title",Math.round(data.data[6] / data.recordsTotal * 10000 )/100 + "%");
						$("#jul").attr('style','height:' + Math.round(data.data[6] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#aug").html(Math.round(data.data[7] / data.recordsTotal * 10000 )/100 + "%");
						$("#aug").attr("data-original-title",Math.round(data.data[7] / data.recordsTotal * 10000 )/100 + "%");
						$("#aug").attr('style','height:' + Math.round(data.data[7] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#sep").html(Math.round(data.data[8] / data.recordsTotal * 10000 )/100 + "%");
						$("#sep").attr("data-original-title",Math.round(data.data[8] / data.recordsTotal * 10000 )/100 + "%");
						$("#sep").attr('style','height:' + Math.round(data.data[8] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#oct").html(Math.round(data.data[9] / data.recordsTotal * 10000 )/100 + "%");
						$("#oct").attr("data-original-title",Math.round(data.data[9] / data.recordsTotal * 10000 )/100  + "%");
						$("#oct").attr('style','height:' + Math.round(data.data[9] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#nov").html(Math.round(data.data[10] / data.recordsTotal * 10000 )/100 + "%");
						$("#nov").attr("data-original-title",Math.round(data.data[10] / data.recordsTotal * 10000 )/100+ "%");
						$("#nov").attr('style','height:' + Math.round(data.data[10] / data.recordsTotal * 10000 )/100 + "%;");
	
						$("#dec").html(Math.round(data.data[11] / data.recordsTotal * 10000 )/100 + "%");
						$("#dec").attr("data-original-title",Math.round(data.data[11] / data.recordsTotal * 10000 )/100 + "%");
						$("#dec").attr('style','height:' +Math.round(data.data[11] / data.recordsTotal * 10000 )/100+ "%;");
	
					} else {
						layer.alert(data.message, {
							title : '错误信息',
							icon : 2
						});
					}
				},
				error : function(XMLHttpRequest) {
					layer.alert(
					'数据处理失败! 错误码:'
							+ XMLHttpRequest.status
							+ ' 错误信息:'
							+ JSON
									.parse(XMLHttpRequest.responseText).message,
					{
						title : '错误信息',
						icon : 2
					});
				}
			});
		}
	}
		/*  setTimeout('count()',2000);
		 function count(){
		     countUp4($("#busuanzi_value_site_uv").html()); 
		 } */

	//天气
	$.ajax({
		url : "/system/weather",
		type : 'GET',
		dataType : "json",
		success : function(data) {
			if (!data.success) {
				layer.msg("无法获取您的IP，天气信息获取失败");
				return;
			}
			if (data.result.weather1.indexOf("雨") >= 0) {
				$("#weather").removeAttr("class");
				$("#weather").attr("class", "icon-umbrella");
			} else if (data.result.weather1.indexOf("晴") >= 0) {
				$("#weather").removeAttr("class");
				$("#weather").attr("class", "icon-sun");
			}
			$("#city").html(data.result.city);
			$("#degree").html(data.result.temp1);
			$("#humidity").html(data.result.sd);
			$("#airCondition").html(
					"PM:" + data.result.pm + "  " + data.result.pm_level);
			$("#wind").html(data.result.wd + "  " + data.result.ws + "  ");
			$("#updateTime").html(
					data.result.week + "  " + data.result.updateTime);
			$("#curTem").html(data.result.temp + "℃");
		},
		error : function(XMLHttpRequest) {
			if (XMLHttpRequest.status != 200) {
				layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status
						+ ' 错误信息:'
						+ JSON.parse(XMLHttpRequest.responseText).message,
						{
							title : '错误信息',
							icon : 2
						});
			}
		}
	});

	if($("#hot-title").text().length > 18) {
		$("#hot-title").text(
				$("#hot-title").text().substring(0, 18) + "...");

	}

	$.ajax({
		url : "/system/base",
		type : 'GET',
		success : function(data) {
			if (data.success != true) {
				layer.alert(data.message, {
					title : '错误信息',
					icon : 2
				});
				return;
			}
			if (data.result.hasAllNotice == 1) {
				allNotice(data.result.allNotice);
			}
		},
		error : function(XMLHttpRequest) {
			if (XMLHttpRequest.status != 200) {
				layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status
						+ ' 错误信息:'
						+ JSON.parse(XMLHttpRequest.responseText).message,
						{
							title : '错误信息',
							icon : 2
						});
			}
		}
	});

	function allNotice(data) {
		layer.open({
			type : 1,
			title : '通知'
			//,area: ['350px', '230px']
			,
			content : '<div style="margin: 10px 20px 10px 20px;">' + data
					+ '</div>',
			btn : [ '知道了' ],
			shade : 0 //不显示遮罩
			,
			yes : function() {
				layer.closeAll();
			}
		});
	}
</script>
</body>
</html>
