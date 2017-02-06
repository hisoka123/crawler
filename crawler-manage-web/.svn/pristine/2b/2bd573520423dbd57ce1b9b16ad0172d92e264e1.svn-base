<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map, java.util.ArrayList"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<jsp:include page="../header.jsp"></jsp:include>
<%-- <link href="${ctx}/static/css/prettify.css" rel="stylesheet"> --%>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>

	<div class="container-fluid sr-zql-sui-container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
	<div class="sina-content">
		<h3 style="text-align: center">任务执行记录跟踪</h3>
		<br>
		<input id="jobGroup" type="hidden" value="${taskGroup}">
		<input id="jobName" type="hidden" value="${taskName}">
		<ul class="nav nav-pills">
			<li><button id="searchAll" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-list-alt">&nbsp;查看所有</i></button></li>
			<li><button id="searchSuccess" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-check">&nbsp;只看正常</i></button></li>
			<li><button id="searchExeception" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-alert">&nbsp;只看异常</i></button></li>
		</ul>
		<br>
		<c:forEach var="taskTrack" items="${tList}" varStatus="taskTrackStatus">
			<c:if test="${null != taskTrack.exception}">
				<div id="job_${jobStatus.count}" class="panel panel-danger">
			</c:if>
			<c:if test="${null == taskTrack.exception}">
			<div id="taskTrack_${taskTrackStatus.count}" class="panel panel-success">
			</c:if>
				<div class="panel-heading">
					<div class="row">
						<div id="taskName_${taskTrackStatus.count}" title="${taskTrack.taskName}"
							class="col-md-6">
							<span class="glyphicon glyphicon-tasks" aria-hidden="true">&nbsp;任务名称：${taskTrack.taskName}</span>
						</div>
						<div id="taskGroup_${taskTrackStatus.count}"
							title="${taskTrack.taskGroup}" class="col-md-6">
							<span class="glyphicon glyphicon-briefcase" aria-hidden="true">&nbsp;任务分组：${taskTrack.taskGroup}</span>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0;">
					<div class="row">
						<div id="executeTime_${taskTrackStatus.count}"
							title="${taskTrack.executeTime}" class="col-md-4" style="padding:15px;margin-left: 15px;">执行时间：${taskTrack.executeTime}</div>
						<div id="executeState_${taskTrackStatus.count}"
							title="${taskTrack.executeState}" class="col-md-2" style="padding:15px;">执行状态：${taskTrack.executeState}</div>
						<div id="duration_${taskTrackStatus.count}"
							title="${taskTrack.duration}" class="col-md-2" style="padding:15px;">执行时长：${taskTrack.duration} ms</div>
						<c:if test="${null != taskTrack.content}">
						<div id="jsonString_${taskTrackStatus.count}"
							title="${taskTrack.content}" class="col-md-12" style="padding:15px;">
						  <div class="panel panel-default">
						      <div class="panel-heading">
						         <h4 class="panel-title">
						            <a data-toggle="collapse" data-parent="#accordion" 
						               href="#collapse_${taskTrackStatus.count}">
						               <span class="glyphicon glyphicon-th" aria-hidden="true"  style="text-align: center;">&nbsp;点击我展开API返回结果</span>
						            </a>
						         </h4>
						      </div>
						      <div id="collapse_${taskTrackStatus.count}" class="panel-collapse collapse">
						         <div class="panel-body">
							<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap'>${taskTrack.content}</pre>	
						         </div>
						      </div>
						   </div>
							</div>		

										
						</c:if>	
						<c:if test="${null != taskTrack.storageNum && 0 != taskTrack.storageNum}">
						<div id="storageNum_${taskTrackStatus.count}"
							title="${taskTrack.storageNum}" class="col-md-2" style="padding:15px;">入库数目：${taskTrack.storageNum}</div>
						<input type="hidden" value="${taskTrack.ids}" >
						<div id = "accordionparent_${taskTrackStatus.count}" class="col-md-2"style="padding:15px;"> 
						<a
							data-toggle="collapse" data-parent="#accordion"
							href="#collapseparent_${taskTrackStatus.count}" >新入库微博</a>
							</div>
						<div id = "collapseparent_${taskTrackStatus.count}" class="col-md-12" ></div>
					</c:if>
					</div>
					<c:if test="${null != taskTrack.exception}">
					<div class="row">
						<div id="message_${taskTrackStatus.count}"
							title="${taskTrack.exception.message}" class="col-md-11" style="padding:0px 0px 15px 15px;margin-left: 15px;">异常信息：<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap'>${taskTrack.exception.message}</pre>
						</div>
					</div>
					</c:if>
					<c:if test="${null != taskTrack.arguments}">
					<div class="row">
						<div id="arguments_${taskTrackStatus.count}"
							title="${taskTrack.arguments}" class="col-md-12" style="padding:0px 0px 15px 15px;margin-left: 15px;">输入参数：${taskTrack.arguments}</div>
					</div>
					</c:if>
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


	
	<!-- 返回顶部 -->
	<div class="sr-zql-totop"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
	<script>
		$(function() {
			var page = Number($("#currentPage").val());
			var size = $("#pageSize").val();
			var total = $("#totalPages").val();
			var totalElements = Number($("#totalElements").val());
			var currLast = $("nav ul.pagination li.child").length + 1;
			var jobGroup = $("#jobGroup").val();
			var jobName = $("#jobName").val();
			
			//为每个微博按钮块添加事件input[id^='dgItem_txt']"
			$("div[id^='collapseparent_']").on('show.bs.collapse', function () {
				var data = $(this).prev().prev().val().replace("[","").replace("]",""); 
				var tweetNew = $(this).prev();
				var idvar = tweetNew.next().attr("id");
				if("" == (tweetNew.next().text())){
					$.ajax({
						  url: "${ctx}/taskTrack/getTweet?tids="+data,
						  data: data,
						  success: function(result){
							    $.each(result, function(i, tweet){
							    	tweetNew.next().append("<div id='tweet_"+idvar+i+"' class='panel panel-info' style='margin:0;'>"+
											"<div class='panel-heading'>"+
									"<div class='row'>"+
									"<a data-toggle='collapse' data-parent='#accordion'"+ 
						                "href='#collapse_"+idvar+i+"'>"+
										"<div id='source_"+idvar+i+"' title='"+tweet.nickname+"'"+
											"class='col-md-4'>"+
											"<span class='glyphicon glyphicon-bullhorn' aria-hidden='true'>&nbsp;来源："+tweet.nickname+"</span>"+
										"</div>"+
										"<div id='"+tweet.region+"'"+
										"title='"+tweet.region+"' class='col-md-3'><span class='glyphicon glyphicon-pushpin' aria-hidden='true'>&nbsp;地点："+tweet.region+"</div>"+
									"<input id='regionHidden_"+idvar+i+"' type='hidden'"+
										"value='"+tweet.region+"'>"+
										"<div id='created_at_"+idvar+i+"'"+
										"title='"+tweet.created_at+"' class='col-md-5'>"+
										"<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;发帖时间："+tweet.created_at+"</span>"+
									"</div>"+
										"</a>"+
									"</div>"+
								"</div>"+
								"<div id = 'collapse_"+idvar+i+"' class='panel-body panel-collapse collapse in'>"+
									"<div class='row'>"+
										"<div id='text_"+idvar+i+"' class='col-md-12'>事件："+tweet.content_text+"</div>"+
									"</div>"+
									"<div class='row' id='img_"+idvar+i+"'>"+
									"</div>"+
									"<div id='img_"+idvar+i+"_big'></div>");
							    	  for(j=0;j<tweet.pic_urls.length;j++){
										  $("div#tweet_"+idvar+i+" div.panel-body div#img_"+(idvar+i)).append("<span><img id='img_"+(idvar+i)+"_small_original_"+j+"' src='"+tweet.pic_urls[j]+"' onClick='sina_imgsZoom(this)' style='cursor: url('../static/img/magnifier.ico'), auto; margin-top: 5px;' />&nbsp;</span>"); 
									  }
									  $("div#tweet_"+idvar+i+"div.panel-body div#img_"+(idvar+i)).after("</div> <div id='img_"+(idvar+i)+"_big'></div> </div> </div>");
									  
									  if($("div#created_at_"+idvar+i).text().indexOf("undefined") != -1){$("div#created_at_"+idvar+i).html("<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;发帖时间：未知</span>")};
									  if($("div#source_"+idvar+i).text().indexOf("undefined") != -1){$("div#source_"+idvar+i).html("<span class='glyphicon glyphicon-bullhorn' aria-hidden='true'>&nbsp;来源：未知</span>")};
							    });
					},
					async:false,
					  dataType: "json"
						});
				}
				//是文本正文地点显红
				var length = $("div[id^='text_']").size();
				for (var i = 0; i < length; i++) {
					var region = $("#regionHidden_" + idvar+i).val();
					var text = $("div[id='text_" + idvar+i + "']").text();
					var textRed = text.replace(new RegExp(region),
							"<b style='color:red;'>" + region + "</b>");
					$("div[id='text_" + idvar+i + "']").html(textRed);
				}
});
			
			//为查看所有添加请求路径
				 $("#searchAll").click(function(){
			    	  location.href = "${ctx}/taskTrack/list?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber=0&&pageSize=30"
			      });
				//为查看异常添加请求路径
				 $("#searchExeception").click(function(){
			    	  location.href = "${ctx}/taskTrack/listException?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber=0&&pageSize=30"
			      });
				//为查看正常添加请求路径
				 $("#searchSuccess").click(function(){
			    	  location.href = "${ctx}/taskTrack/listSuccess?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber=0&&pageSize=30"
			      });
			//点击按钮之后是按钮保持点击状态
			if(location.pathname.match("Exception")){
				 $("#searchExeception").addClass("active");
			}else if(location.pathname.match("Success")){
				 $("#searchSuccess").addClass("active");
			}else{
				$("#searchAll").addClass("active");
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
								location.href = location.pathname+"?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=first"
							});
			//分页  上一页
			$("nav ul.pagination li#previous")
					.click(
							function() {
								location.href = location.pathname+"?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=previous"
							});
			//分页  下一页
			$("nav ul.pagination li#next")
					.click(
							function() {
								location.href = location.pathname+"?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber="
										+ page
										+ "&&pageSize="
										+ size
										+ "&&pageOption=next"
							});
			//分页  尾页
			$("nav ul.pagination li a[aria-label='Last']")
					.click(
							function() {
								location.href = location.pathname+"?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber="
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
													location.href = location.pathname+"?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber="
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

