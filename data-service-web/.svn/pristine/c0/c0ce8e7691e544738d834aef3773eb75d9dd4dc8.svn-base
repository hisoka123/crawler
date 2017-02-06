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
<script type="text/javascript">
	
	$(document).ready(function() {


		//鼠标移入移出效果
		$('.pdlr').mouseenter(function(){
				$(this).addClass('bg-f');
				$(this).find('.box').show()
			}).mouseleave(function(){
				$(this).removeClass('bg-f');
				$(this).find('.box').hide()
			})

		$(function(){
			var h=90;
			$(window).scroll(function(){
				var t = $(document).scrollTop();
				var hh=t-h;
				if(h<t){
					$('.header').addClass('header2');
					$('.logo img').attr('src', "${ctx}/static/images/b-logo.png");

				}
				else{
					$('.header').removeClass('header2');
					$('.logo img').attr('src', "${ctx}/static/images/logo.png");
				}
			})
		})
		

		
	})
</script>

</head>


<body>
	<div class="header">
		<div class="logo fl"><a href="${ctx}/home.html"><img src="${ctx}/static/images/logo.png"></a></div>
		<div class="fr menu">
			<ul class="fn-clear">
				<li class="fl pdlr p-r"><a href="${ctx}/product.html" class="tit">产品</a>
					<div class="box">
						<a href="#">关系数据库</a>
						<a href="#">分布式数据库</a>
						<a href="#">集群数据库</a>
						<a href="#">地理信息数据库</a>
					</div>
				</li>
				<li class="fl pdlr p-r"><a href="${ctx}/solution.html" class="tit">解决方案</a>
					<div class="box">
						<a href="#">关系数据库</a>
						<a href="#">地理信息数据库</a>
					</div>
				</li>
				<li class="fl pdlr p-r"><a href="javascript:void(0);" class="tit">文档</a>
					<div class="box">
						<a href="#">关系数据库</a>
						<a href="#">分布式数据库</a>
						<a href="#">集群数据库</a>
						<a href="#">地理信息数据库</a>
						<a href="#">分布式数据库</a>
						<a href="#">集群数据库</a>
						<a href="#">地理信息数据库</a>
					</div>
				</li>
				<li class="fl pdlr p-r"><a href="javascript:void(0);" class="tit">关于</a></li>
			</ul>
		</div>
	</div>
	

	
</body>
</html>