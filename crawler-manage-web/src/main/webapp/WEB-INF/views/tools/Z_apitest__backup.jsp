<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<!-- prettify.css -->
<link href="${ctx}/static/css/prettify.css" rel="stylesheet">

<jsp:include page="../header.jsp"></jsp:include>
<body onload="prettyPrint()">
	<jsp:include page="../nav.jsp"></jsp:include>
	<input type="hidden" id="ctx" value="${ctx}">
	
	<div class="container sr-zql-container">
		<div class="row sr-zql-rowbox">
			<!-- 左侧导航栏 -->
			<div class="col-md-3 sr-zql-boxleft">
				<form id="left-form" action="#" method="get">
					<!-- API分类 -->
					<div>
						<label>API分类：
							<select name="api_cate" autocomplete="off">
							</select>
						</label>
					</div>
					
					<!-- API名称 -->
					<div>
						<label>API名称：
							<select name="api_name" autocomplete="off">
							</select>
						</label>
					</div><br/>
					
					<!-- 获取方式 -->
					<div>
						获取方式：<hr/>
						<div class="radio">
							<label><input type="radio" name="req_method" value="get" checked>get</label>
							<label><input type="radio" name="req_method" value="post">post</label>
						</div><hr/>	
					</div><br/>
					
					<!-- 获取API文档 -->
					<div>
						API文档：
						<a id="doc_btn" title="${ctx}/tools/wiki"  href="#" target="_blank">点击获取文档</a>
					</div><br/>
					
					<!-- API参数 -->
					<div class="params">
						<p>API参数（自动编码）：</p>
						<div class="param-container">
							<!-- JS填充参数项 -->
						</div>
						<button id="btn_addparam" type="button" class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus"></i>&nbsp;添加</button>
					</div><br/>
					
					<!-- 调用接口按钮 -->
					<div>
						<button id="btn_play" type="button" class="btn btn-success btn-sm"><i class="glyphicon glyphicon-play"></i>&nbsp;调用接口</button>						
					</div>
				</form>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-9 sr-zql-boxright">
				<div class="sr-zql-boxright-req">
					请求：<a data-toggle="collapse" data-target="#req_content">折叠请求</a>
					<div id="req_content" class="collapse in">
						<div class="sr-zql-boxright_cont boxright-req">
							<!-- 填充请求参数 -->
						</div>
					</div>
				</div>
				
				<div class="sr-zql-boxright-res">
					<table style="width: 100%;">
						<tr>
							<td>返回内容&nbsp;&nbsp;[注]gson转json串时默认将一些字符自动转换为unicode转义字符：</td>
							<td style="text-align:right;"><a href="${ctx}/tools/wiki?docName=help_error.html">常见错误代码及释义</a></td>
						</tr>
					</table>
					<div class="sr-zql-boxright_cont boxright-res">
						<!-- 填充返回内容 -->
					</div>
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-topret-up"></div>
			<div class="sr-zql-topret-down" id="${ctx}/tools/wiki"></div>
		</div>
	</div>
    <jsp:include page="../footer.jsp"></jsp:include>
 	<jsp:include page="tools-model.jsp"></jsp:include>
    
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-form-collection.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-ajax-progress.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/tools/apitest.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var containerSelector = "div.boxright-res";
    	
    	//点击'调用接口'按钮
    	$("#btn_play").on("click", function() {
    		play();
    	});
    	
    	//按下enter键  //按下alt+t键 
    	$(document).keydown(function(event) {
    		if (event.keyCode=="13") {
    			play();
    		}
    		
    		if (event.altKey && event.keyCode=="84") {
    			$("button[data-target='#toolsModel']").click();
    		}
    	});
    	
    	var play = function() {
    		//获取点击信息
    		var param = collectParam("#left-form");
    		showParams(param, "div.boxright-req");
    		
    		//参数编码 及 额外参数
    		for(var p in param) {
    			param[p] = encodeURIComponent(param[p]);
    		}
    		param.param_size = $("input[type='text'].param-key").size();
    		param.requestPath = "http://localhost:8080${ctx}" + api_config.path[param.api_cate][param.api_name];
    		console.log(JSON.stringify(param));
    		
    		//loading...
    		var container = $(containerSelector);
    		var waiting_icon = $("<div class='sr-zql-loading'><img src='${ctx}/static/imgs/icon/loading.gif'>loading...</div>"); 
    		container.html(waiting_icon);
    		
    		//ajax请求
    		ajaxProgress(param.req_method, "${ctx}/tools/apitest/play", param, containerSelector, ajaxCallback);
    	}
    	
    	var ajaxCallback = function(res) {
    		var container = $(containerSelector);
			container.html("<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap;'>"+ res +"</pre>");
			prettyPrint();
    	}
    </script>
</body>
</html>

