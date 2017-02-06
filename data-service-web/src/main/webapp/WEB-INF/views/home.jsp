<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<title>上容</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="${ctx}/static/css/base-global.css" rel="stylesheet">
<link href="${ctx}/static/css/common.css" rel="stylesheet">
<link href="${ctx}/static/css/home.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.7.2.min.js"></script>

</head>


<body>
	<div class="wrap">
		<div class="banner-bg">
			<div class="banner"><a href="home.html"><img src="${ctx}/static/images/banner.jpg"></a></div>
			
			<!-- <div class="title-box txt-c">
				<div><img src="images/title.png"></div>
				<div class="txt-c"><img src="images/tit-txt.png" class="tit-txt"></div>
				<div class="txt-c"><a href=""><img src="images/link.png" class="link"></a></div>
			</div> -->

			<!-- 头部导航 -->
			<jsp:include page="/WEB-INF/layouts/header.jsp"/>
			<!-- 头部导航 end -->

		</div>

		<div class="content">
			<div class="txt-c">
				<p class="fz36">我们的服务</p>
				<div class="fz16 lh30 mg-t2b">
					<p>公司主导产品为上容数据库软件、上容集群数据库软件、大数据处理软件、数据库工具软件等</p>
					<p>具有完全自主版权的数据库系列产品。凭借安全稳定的产品、完善的服务体系、</p>
					<p>通过了国防科学技术大学麒麟操作系统的产品兼容性认证。</p>
				</div>
			</div>

			<ul class="fn-clear list pd-t4b">
				<li class="fl">
					<p class="txt-c"><img src="${ctx}/static/images/icon1.png"></p>
					<p class="txt-c fz18 mg-t8b">数据融合平台</p>
					<p class="fz14 lh30 gray1 mg-t8b">上容数据库SRDB凭着超稳定的运行，并且提供了数据安全无风险、功能强大易扩展、操作简单易维护数据处理工具，打造运营高效的金融生态圈。</p>
				</li>
				<li class="fl mg-l8b">
					<p class="txt-c"><img src="${ctx}/static/images/icon2.png"></p>
					<p class="txt-c fz18 mg-t8b">数据分析</p>
					<p class="fz14 lh30 gray1 mg-t8b">上容数据库基于事件驱动的流程完成对办公政务的发起、准备、派遣、处理、验证、总结等步骤，以此组成O2O闭环处理，实现“精细化、责任化”</p>
				</li>
				<li class="fl mg-l8b">
					<p class="txt-c"><img src="${ctx}/static/images/icon3.png"></p>
					<p class="txt-c fz18 mg-t8b">数据支持与服务</p>
					<p class="fz14 lh30 gray1 mg-t8b">上容数据库可实现城市各核心部门间的高速网络联通、数据共享、无线宽带网络覆盖情况达到基本覆盖，并建成比较完备的空间地理、城市物件。</p>
				</li>
			</ul>
			<p class="txt-c mg-t4b"><a href="${ctx}/product.html"><img src="${ctx}/static/images/more.png" class="more"></a></p>
		</div>

		<div class="footer">
			<ul class="fn-clear ft-list pd-t65">
				<li class="fl">
					<p class="fz24">新闻与公告</p>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">国产自强数据库，科大上容共进步”篮球友谊赛</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">国产软硬件变阵 国产数据库遇启航难题</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">热烈庆祝上容入围2015年中直机关协议供货商</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<a href="" class="more mg-t30 fz16">了解详情</a>
				</li>
				<li class="fl mg-l8b">
					<p class="fz24">新闻与公告</p>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">国产自强数据库，科大上容共进步”篮球友谊赛</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">国产软硬件变阵 国产数据库遇启航难题</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<div class="fz14 mg-t30">
						<p class="gray2 lh30">热烈庆祝上容入围2015年中直机关协议供货商</p>
						<p class="gray3">2014年12月14</p>
					</div>
					<a href="" class="more mg-t30 fz16">了解详情</a>
				</li>
				
			</ul>

			<div class="fn-clear ft-list mg-t4b">
				<span class="fz14 gray4 fl">@2015&nbsp;上容数据服务平台</span>
				<div class="fr">
					<a href="javascript:void(0);"><img src="${ctx}/static/images/share-xl.png" class="share-icon"></a>
					<a href="javascript:void(0);"><img src="${ctx}/static/images/share-wx.png" class="share-icon"></a>
				</div>
			</div>
		</div>
		
	</div>
	

	
</body>
</html>