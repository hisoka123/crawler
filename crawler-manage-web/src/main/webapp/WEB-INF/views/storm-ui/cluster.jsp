<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="../header.jsp"></jsp:include>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>

	<div class="container" style="padding:0;margin:0;">
	<div style="margin-top: 10%; margin-left: 21%;">
			<div class="row"> 
				 ${stormHost}: ${stormPort}
			</div>
		</div>
		
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
