<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>

<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
      $(function(){
    	     var title="${title}";
    	     $("title").html(title);
      })
</script>

<link href="${ctx}/static/css/ownerTask/gsxt.css" rel="stylesheet">
<link href="${ctx}/static/css/ownerTask/ownerTask-common.css" rel="stylesheet">

<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div id="body" class="row">
			<!-- 左侧导航栏 -->
			<div id="left_menu" class="col-md-2"></div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     <p class="tableName">反馈信息列表</p>
				     <div data-name="ownerTaskType" class="row" style="margin-bottom:3px">
				          <div class="col-md-2" style="width:10%"><strong>任务类型：</strong></div>
				          <div class="col-md-5">
				               <select id="ownerTaskType" style="width:170px;padding:0px 15px;border-radius:6px;-webkit-border-radius:6px;-moz-border-radius:6px">
                               </select>
				          </div>
				     </div>
                     <table class="table table-bordered table-hover table-striped">
                         <thead>
                             <tr class="success">
                                 <th><nobr>序号</nobr></th>
                                 <th><nobr>任务内容</nobr></th>
                                 <th><nobr>任务创建时间</nobr></th>
                                 <th><nobr>反馈内容</nobr></th>
                                 <th><nobr>反馈代码</nobr></th>
                                 <th><nobr>操作</nobr></th>
                             </tr>
                         </thead>
                         <tbody>
                         </tbody>
                     </table>
                      <div id="page" style="margin-left:15%">
                     </div>
               </div>
		   </div>
	   </div>
   </div>
   
<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">
   
<!-- script -->
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/bootstrap-treeview.min.js"></script>
<script src="${ctx}/static/js/commonSelf.js"></script>

<script src="${ctx}/static/js/ownerTask/leftMenuOT.js"></script>
<script src="${ctx}/static/js/ownerTask/ownerTask-ajax.js"></script>


<script src="${ctx}/static/js/ownerTask/feedback/feedback.js"></script>
<script src="${ctx}/static/js/ownerTask/feedback/feedback-ajax.js"></script>


<!-- 获取全部站点，查看enabled属性 -->
<script src="${ctx}/static/js/tools/wiki-ajax.js"></script>
</body>
</html>