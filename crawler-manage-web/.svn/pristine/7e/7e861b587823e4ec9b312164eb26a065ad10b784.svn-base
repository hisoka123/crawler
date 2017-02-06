<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${ctx}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${ctx}/static/css/sina.css"
	type="text/css" rel="stylesheet">
<link href="${ctx}/static/css/common.css"
	type="text/css" rel="stylesheet">
<script
	src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
<script
	src="${ctx}/static/js/bootstrap.min.js"></script>
<script
	src="${ctx}/static/js/sina-common.js"></script>
<script src="${ctx}/static/js/imgSlide.js"></script>

<link rel='icon' href='${logo}' type=‘image/x-ico’ />
<title>${title}--新浪微博</title>

</head>
<body>

	<div class="scm-head"><%@ include file="../head.jsp"%></div>
	<div class="sina-left"><%@ include file="leftMenu.jsp"%></div>
	<div class="sina-content">
		<h3 style="text-align: center">突发事件管理页面</h3>
		<br>
		<div class="alert alert-danger center-block" role="alert"><span class="glyphicon glyphicon-bell"></span>&nbsp;有新微博点开查看&nbsp;<span class="badge"></span></div>
		<div id = "burstOutNew"></div> 
		<br>
		<c:forEach var="tweet" items="${tList}" varStatus="tweetStatus">
			<div id="tweet_${tweetStatus.count}" class="panel panel-info">
				<div class="panel-heading">
					<div class="row">
						<div id="source_${tweetStatus.count}" title="${tweet.source}"
							class="col-md-6">
							<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;来源：${tweet.userFeed.screen_name}</span>
						</div>
						<div id="created_at_${tweetStatus.count}"
							title="${tweet.created_at}" class="col-md-6">
							<span class="glyphicon glyphicon-time" aria-hidden="true">&nbsp;发帖时间：${tweet.created_at}</span>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div id="region_${tweetStatus.count}"
							title="${tweet.region.regionChiName}" class="col-md-12">地点：${tweet.region.regionChiName}</div>
						<input id="regionHidden_${tweetStatus.count}" type="hidden"
							value="${tweet.region.regionChiName}">
					</div>
					<div class="row">
						<div id="text_${tweetStatus.count}" class="col-md-12">事件：${tweet.text}</div>
					</div>
					<div class="row" id="img_${tweetStatus.index}">
						<c:forEach var="pic" items="${tweet.pic}" varStatus="picStatus">
							<span><img
								id="img_${tweetStatus.index}_small_original_${picStatus.index}"
								src="${pic.url}" onClick="sina_imgsZoom(this)"
								style="cursor: url('../static/img/magnifier.ico'), auto; margin-top: 5px;" />&nbsp;</span>
						</c:forEach>
					</div>
					<div id="img_${tweetStatus.index}_big"></div>
				</div>
			</div>

		</c:forEach>
		<nav>
			<ul class="pagination">
				<li><a href="javascript:void(0);" aria-label="First"> <span
						aria-hidden="true">首页</span>
				</a></li>
				<li id="previous"><a href="javascript:void(0);"
					aria-label="Previous"> <span aria-hidden="true">&laquo;上一页</span>
				</a></li>
				<li id="next"><a href="javascript:void(0);" aria-label="Next">
						<span aria-hidden="true">下一页&raquo;</span>
				</a></li>
				<li><a href="javascript:void(0);" aria-label="Last"> <span
						aria-hidden="true">尾页</span>
				</a></li>
				<li><a href="javascript:void(0);"> <span aria-hidden="true">第${page.number+1}页/共${page.totalPages}页</span>
				</a></li>
				<li>
				&nbsp;
				<input id="currentPage" type="hidden" value="${page.number}">
				<input id="pageSize" type="hidden" value="${page.size}">
				<input id="totalPages" type="hidden" value="${page.totalPages}">
				<input id="totalElements" type="hidden" value="${page.totalElements}">
				<input id="maxId" type="hidden" value="${maxId}">
				</li>
			</ul>
		</nav>
	</div>

	<script>
		$(function() {
			var page = Number($("#currentPage").val());
			var size = $("#pageSize").val();
			var sizeMark = size;
			var total = $("#totalPages").val();
			var totalElements = Number($("#totalElements").val());
			var currLast = $("nav ul.pagination li.child").length + 1;
			//隐藏提示新微博信息标签div
			$("div.center-block").hide();
			//为新微薄添加链接
			$("div.center-block")
			.click(
					function() {
						var tid = $("#maxId").val();
						$.getJSON("${ctx}/sinaWeibo/getNewTweet/"+tid, function(tweets){
							  for(i=0;i<tweets.length;i++){
								  $("#burstOutNew").append("<div id='tweet_1"+i+"' class='panel panel-danger'>"+
										  						"<div class='panel-heading'> "+
										  							"<div class='row'> "+
										  								"<div id='source_"+i+"' title='"+tweets[i].nickname+"' class='col-md-6'>"+
										  									"<span class='glyphicon glyphicon-bullhorn' aria-hidden='true'>&nbsp;来源："+tweets[i].nickname+"</span>"+
										  								"</div> "+
										  								"<div id='created_at_"+i+"' title='"+tweets[i].created_at+"' class='col-md-6'>"+
																			"<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;发帖时间："+tweets[i].created_at+"</span>"+
																	   " </div> "+
																    "</div> "+
																"</div> "+
																"<div class='panel-body'> "+
																	"<div class='row'> "+
																		"<div id='region_"+i+"'"+"title='"+tweets[i].created_at+"' class='col-md-12'>地点："+tweets[i].region+"</div> <input id='regionHidden_"+(i+sizeMark+1)+"' type='hidden' value='"+tweets[i].region+"'> "+
																	"</div>"+
																	"<div class='row'> "+
																		"<div id='text_"+(i+sizeMark+1)+"' class='col-md-12'>事件："+tweets[i].content_text+"</div> "+
																	"</div> "+
																	"<div class='row' id='img_"+(i+sizeMark)+"'>");
								  for(j=0;j<tweets[i].pic_urls.length;j++){
									  $("#burstOutNew div.panel-body div#img_"+(i+sizeMark)).append("<span><img id='img_"+(i+sizeMark)+"_small_original_"+j+"' src='"+tweets[i].pic_urls[j]+"' onClick='sina_imgsZoom(this)' style='cursor: url(\'../static/img/magnifier.ico\'), auto; margin-top: 5px;' />&nbsp;</span>"); 
								  }
								  $("#burstOutNew div.panel-body div#img_"+(i+sizeMark)).after("</div> <div id='img_"+(i+sizeMark)+"_big'></div> </div> </div>");
								  
										var region = $("#regionHidden_" + (i+sizeMark+1)).val();
										var text = $("div[id='text_" + (i+sizeMark+1) + "']").text();
										var textRed = text.replace(new RegExp(region),
												"<b style='color:red;'>" + region + "</b>");
										$("div[id='text_" + (i+sizeMark+1) + "']").html(textRed);
										$("div.alert-danger").hide();
										$.get("${ctx}/sinaWeibo/getCountTidAll", function(newCount){
											totalElements = newCount;
							  });
									}
							  sizeMark +=tweets.length;
							});
					});
			//定时刷新
			setInterval(function(){
				$.get("${ctx}/sinaWeibo/getCountTidAll", function(newCount){
					var noRead = newCount-totalElements;
					if(noRead > 0){
						$("div.center-block").show();
						$("div.center-block span.badge").text(noRead);
					}
					});
			},60000);
			//处理发帖时间格式显示问题
			$("div[id^=created_at_] h3").each(function() {
				var created_at_ = new String($(this).text());
				$(this).text(created_at_.substring(0, 24));
			});

			//是文本正文地点显红
			var length = $("div[id^='text_']").size();
			for (var i = 1; i < length + 1; i++) {
				var region = $("#regionHidden_" + i).val();
				var text = $("div[id='text_" + i + "']").text();
				var textRed = text.replace(new RegExp(region),
						"<b style='color:red;'>" + region + "</b>");
				$("div[id='text_" + i + "']").html(textRed);
			}
			if (total <= 5) {
				for (i = 0; i < total; i++)
					$("nav ul.pagination li:eq(" + (1 + i) + ")").after(
							"<li class='child'><a href='javascript:void(0);'>"
									+ (i + 1) + "</a></li>");
			} else if (page > 1 && total > 5 && page + 3 < total) {
				$("nav ul.pagination li:eq(1)")
						.after(
								"<li class='child'><a href='javascript:void(0);'>"
										+ (page - 1)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 1)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 2)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 3) + "</a></li>");
			} else if (page<=1 && total>5) {
				$("nav ul.pagination li:eq(1)")
						.after(
								"<li class='child'><a href='javascript:void(0);'>"
										+ (page + 1)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 2)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 3)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 4)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (page + 5) + "</a></li>");
			} else if (page > 1 && total > 5 && page + 3 >= total) {
				$("nav ul.pagination li:eq(1)")
						.after(
								"<li class='child'><a href='javascript:void(0);'>"
										+ (total - 4)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (total - 3)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (total - 2)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (total - 1)
										+ "</a></li> <li class='child'><a href='javascript:void(0);'>"
										+ (total) + "</a></li>");
			}

			//分页  首页
			$("nav ul.pagination li:first")
					.click(
							function() {
								location.href = "${ctx}/sinaWeibo/burstoutList?pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=first"
							});
			//分页  上一页
			$("nav ul.pagination li#previous")
					.click(
							function() {
								location.href = "${ctx}/sinaWeibo/burstoutList?pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=previous"
							});
			//分页  下一页
			$("nav ul.pagination li#next")
					.click(
							function() {
								location.href = "${ctx}/sinaWeibo/burstoutList?pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=next"
							});
			//分页  尾页
			$("nav ul.pagination li a[aria-label='Last']")
					.click(
							function() {
								location.href = "${ctx}/sinaWeibo/burstoutList?pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=last"
							});
			//激活当前页
			$("nav ul.pagination li:contains(" + (page + 1) + ")").addClass(
					"active");
			//给每个导航页加链接
			$("nav ul.pagination li.child")
					.each(
							function() {
								var page1 = $(this).text() - 1;
								$(this)
										.click(
												function() {
													location.href = "${ctx}/sinaWeibo/burstoutList?pageNumber="
															+ page1
															+ "&&pageSize="
															+ size
												});
							});
			//如果当前页为首页则上一页按钮失效 
			if (page == 0) {
				$("#previous").addClass("disabled");
				$("#previous").unbind("click")
			}
			//如果当前页为尾页则上一页按钮失效 
			if (page + 1 == total) {
				$("#next").addClass("disabled");
				$("#next").unbind("click")
			}
		});
	</script>

</body>
</html>