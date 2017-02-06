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
<style type="text/css">
     .tableName{
         padding-bottom:5px;
         text-align:center;
         font-size:20px;
         font-weight:bold
     }
    .table>tbody>tr>td, .table>tbody>tr>th, 
    .table>tfoot>tr>td, .table>tfoot>tr>th, 
    .table>thead>tr>td, .table>thead>tr>th{
         vertical-align: middle;
          text-align:center;
    }
   
</style>
<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div id="left_menu" class="col-md-2"></div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
                     <table class="table table-bordered table-hover table-striped">
                         <p class="tableName">我的任务</p>
                         <thead>
                             <tr class="success">
                                 <th rowspan="2"><nobr>序号</nobr></th>
                                 <th rowspan="2"><nobr>任务类型</nobr></th>
                                 <th colspan="2"><nobr>任务数</nobr></th>
                                 <th rowspan="2"><nobr>操作</nobr></th>
                             </tr>
                             <tr class="success">
                                 <th><nobr>成功数</nobr></th>
                                 <th><nobr>总数</nobr></th>
                             <tr>
                         </thead>
                         <tbody>
                         
                         </tbody>
                     </table>
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
<script src="${ctx}/static/js/ownerTask/leftMenuOT.js"></script>
<script src="${ctx}/static/js/ownerTask/ownerTask-ajax.js"></script>


<!-- 不同的任务对应相应的js -->
<script src="${ctx}/static/js/ownerTask/ownerTask-common.js"></script>

<!-- 获取全部站点，查看enabled属性 -->
<script src="${ctx}/static/js/tools/wiki-ajax.js"></script>



</body>
</html>