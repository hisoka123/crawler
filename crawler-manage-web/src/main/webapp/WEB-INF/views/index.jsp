<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>


<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="${headerPath}"></jsp:include>
<body>
    <jsp:include page="${navPath}"></jsp:include> 
	<div data-name="text" class="container-fluid sr-zql-content">
         <div id="siteList" style="width:60%;margin-left:20%">
              
         </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- 隐藏域 -->
	<input id="ctx" value="${ctx}" style="display:none">
	
	<!-- script -->
	<script src="${ctx}/static/js/index.js"></script>
</body>
</html>
