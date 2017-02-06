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
	<div class="container sr-zql-container">
		<div class="row sr-zql-rowbox">
			<!-- 左侧导航栏 -->
			<div class="col-md-3 sr-zql-boxleft">
				<form action="#" method="get">
					<!-- API分类 -->
					<div>
						<label>API分类：
							<select name="category">
							</select>
						</label>
					</div>
					
					<!-- API名称 -->
					<div>
						<label>API名称：
							<select name="name">
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
						<a href="${ctx}/demo/wiki">点击获取文档</a>
					</div><br/>
					
					<!-- API参数 -->
					<div class="params">
						<p>API参数：</p>
						<div class="param-container">
							<div>
								<label><input type="text" class="param-key" placeholder="key"><span style="color:#AAAAAA;">&nbsp;→</span>
								<input type="text" class="param-value" placeholder="value"></label> 
								<i class="glyphicon glyphicon-remove" style="opacity:0.5;"></i>
							</div>
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
						<div class="sr-zql-boxright_cont boxright-req">... ...</div>
					</div>
				</div>
				
				<div class="sr-zql-boxright-res">
					返回内容：
					<div class="sr-zql-boxright_cont boxright-res">... ...</div>
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include> 
    
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#btn_addparam").on("click", function() {
        		var param_container = $(".param-container");
        		var appendContent = "<div>"
    								+	"<label><input type='text' class='param-key' placeholder='key'><span style='color:#AAAAAA;'>&nbsp;→</span> "
    								+	"<input type='text' class='param-value' placeholder='value'></label> " 
    								+	"<i class='glyphicon glyphicon-remove' style='opacity:0.5;'></i>"
    								+ "</div>";
        		param_container.append(appendContent);
        	});
        	
        	$(".param-container").delegate("i", "click", function() { //被委派父元素选择器必须为“一个”确定的元素
        		var target = $(this).parent();
        		target.remove();
        	});
    	});
    </script>
</body>
</html>

