<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<!-- prettify.css -->
<link href="${ctx}/static/css/prettify.css" rel="stylesheet">

<jsp:include page="header.jsp"></jsp:include>
<body onload="prettyPrint()">
	<jsp:include page="nav.jsp"></jsp:include>
	
	<div class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div class="col-md-2">
				<ul class="nav nav-tabs nav-stacked sr-zql-main-nav" style="border:0;">
					<li class="active"><a href="#"> <i class="glyphicon glyphicon-th-large"></i> 首页</a></li>
					<li><a href="${ctx}/demo/apitest"> <i class="glyphicon glyphicon-wrench"></i> API测试</a></li>
					<li>
						<a href="#sina_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-sina-12"></i> 新浪微博<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="sina_menu" class="nav nav-list collapse sr-zql-secondmenu">
							<li><a id="sina_userSearch" href="#"><i class="glyphicon glyphicon-user"></i> 搜索用户</a></li>
							<li><a id="sina_userInfo" href="#"><i class="glyphicon glyphicon-info-sign"></i> 查询用户信息</a></li>
							<li><a id="sina_userFriendCircle" href="#"><i class="glyphicon glyphicon-th"></i> 查询用户人脉</a></li>
							<li><a id="sina_tweet" href="#"><i class="glyphicon glyphicon-edit"></i> 查询用户发帖</a></li>
							<li><a id="sina_topic" href="#"><i class="glyphicon glyphicon-comment"></i> 查询微博话题</a></li>
                            <li><a id="sina_concernedPeople" href="#"><i class="glyphicon glyphicon-eye-open"></i>查询关注人</a></li>
							<li><a id="sina_action" href="#"><i class="glyphicon glyphicon-eye-open"></i> 关注用户</a></li>
						</ul>
					</li>
					<li>
						<a href="#baidubaike_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-baidu-12"></i> 百度百科<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="baidubaike_menu" class="nav nav-list collapse sr-zql-secondmenu">
                            <li><a id="baidubaike_list" href="#"><i class="glyphicon glyphicon-search"></i> 搜索词条</a></li>
                            <li><a id="baidubaike_detail" href="#"><i class="glyphicon glyphicon-info-sign"></i> 词条详情</a></li>
						</ul>
					</li>
					<li>
						<a href="#zhihu_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-zhihu-12"></i> 知乎<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="zhihu_menu" class="nav nav-list collapse sr-zql-secondmenu">
							<li><a id="zhihu_userSearch" href="#"><i class="glyphicon glyphicon-user"></i> 搜索用户</a></li>
							<li><a id="zhihu_userInfo" href="#"><i class="glyphicon glyphicon-info-sign"></i> 查询用户信息</a></li>
							<li><a id="zhihu_followers" href="#"><i class="glyphicon glyphicon-th"></i> 查询用户关注人</a></li>
							<li><a id="zhihu_asks" href="#"><i class="glyphicon glyphicon-question-sign"></i> 查询用户提问</a></li>
							<li><a id="zhihu_answers" href="#"><i class="glyphicon glyphicon-ok-sign"></i> 查询用户回答</a></li>
						</ul>
					</li>
					<li>
						<a href="#weixin_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-weixin-12"></i> 微信<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="weixin_menu" class="nav nav-list collapse sr-zql-secondmenu">
							<li><a id="weixin_userSearch" href="#"><i class="glyphicon glyphicon-user"></i> 搜索用户</a></li>
							<li><a id="weixin_articleSearch" href="#"><i class="glyphicon glyphicon-file"></i> 搜索文章</a></li>
						</ul>
					</li>
					<li>
						<a href="#linkedIn_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i> 领英<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="linkedIn_menu" class="nav nav-list collapse sr-zql-secondmenu">
							<li><a id="linkedIn_userSearch" href="#"><i class="glyphicon glyphicon-user"></i> 搜索用户</a></li>
							<li><a id="linkedIn_userInfo" href="#"><i class="glyphicon glyphicon-info-sign"></i> 查询用户信息</a></li>
						</ul>
					</li>
					<li>
						<a href="#news_menu" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-news-12"></i> 新闻<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
						<ul id="news_menu" class="nav nav-list collapse sr-zql-secondmenu">
							<li><a id="news_news" href="#"><i class="glyphicon glyphicon-search"></i> 搜索新闻</a></li>
						</ul>
					</li>
				</ul>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div class="sr-zql-content-right">
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include> 
    
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
  	<script src="${ctx}/static/js/sweet-ajax-progress.js" type="text/javascript"></script>
    <script type="text/javascript">
        preDocName=""; //
        
    	$(".sr-zql-main-nav li:has(ul) ul li").on("click", function() {
    		var $currentClickNode = $(this);
    		var currentId = $currentClickNode.children("a").attr("id");
    		var docName = currentId + ".html";
    		if (docName==preDocName) return; //防止连续重复请求相同资源
    		ajaxProgress("${ctx}/demo/wiki/doc", {"docName":docName}, "div.sr-zql-content-right");
    		preDocName = docName;
    	});
    	
    </script>
</body>
</html>

