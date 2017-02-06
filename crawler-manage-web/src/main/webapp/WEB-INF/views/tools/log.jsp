<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="../header.jsp"></jsp:include>
<body>
	<%-- <jsp:include page="../nav.jsp"></jsp:include> --%>
	
	<div class="container-fluid" style="postion:relative;">
	     <div style="width:100%;background-color:white;z-index:2;position:fixed;">
	          <div class="row" style="font-size:16px;width:100%;padding-top:2%;height:15%;">
	               <div class="col-md-1 col-md-offset-2" style="font-weight:bold;">调用接口:</div>
	               <div id="callInterfaceName" class="col-md-3"></div>
	               <div class="col-md-1" style="font-weight:bold;">调用时间:</div>
	               <div id="callTime" class="col-md-3"></div>
	          </div>
	          <hr style="color:black;margin-bottom:0px">
	     </div>
	     <div style="clear:both;zoom:1"></div>
	     <div data-name="showLog" class="row" style="margin-top:8%">
	          <div id="consoleLog" class="col-md-9 col-md-offset-2"></div>
	     </div>
	
	
	</div>
    <%-- <jsp:include page="../footer.jsp"></jsp:include>
 	<jsp:include page="tools-model.jsp"></jsp:include> --%>
 	
 	<!-- 隐藏域 -->
 	<input id="ctx" value="${ctx}" style="display:none">
    <input id="logParam" value="${logParam}" style="display:none">
    
    <!-- script -->
    <script src="${ctx}/static/js/sockjs.min.js"></script>
    <script src="${ctx}/static/js/tools/log.js"></script>
    <script src="${ctx}/static/js/commonSelf.js"></script>
</body>
</html>

