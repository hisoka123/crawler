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
<script src="${ctx}/static/js/sweet-totop.js"></script>
<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div data-name="left_menu" class="col-md-2">
			     <jsp:include page="${leftMenuPath}"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                	   var path_1="<a href='${iecmsAuthPath}'>贸易备案</a>";
                                       var path_2="<a href='${iecmsAuthPath}'>贸易查询</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!-- 搜索框 -->
                    <div data-name="searchBox" class="row" id="gxsearch">               
                         <div id="searchBox_big" class="col-md-8 col-md-offset-2" style="margin-top:12%">
                               <jsp:include page="${searchBoxBigPath}"></jsp:include>             
                         </div>
                    </div>
                     <div id="chooseDataSource" class="row" style="margin-top:3%;display:none">
                         <div class="col-md-5 col-md-offset-3">
                              <label class="checkbox-inline">
                                     <input name="chooseDataSource" type="radio" value="interfaceVersion" checked> 接口版
                              </label>
                              <label class="checkbox-inline">
                                     <input name="chooseDataSource" type="radio"  value="dbVersion"> 数据库版
                              </label>
                         </div>
                    </div>

                    <div id="info" class="row" style="margin-top:5%;display:none">
                         <div class="col-md-7 col-md-offset-3" style="color:red">
                         </div>
                    </div>                    
                   
                      
               </div>
		   </div>
	   </div>
   </div>
	
<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">
<input id="env" value="${env}" style="display:none">
   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/common.js"></script>


<script src="${ctx}/static/js/iecms/iecms-common.js"></script>
<script src="${ctx}/static/js/iecms/iecms-ajax.js"></script>
<script src="${ctx}/static/js/ownerTask/iecms/iecms-ajax.js"></script>

</body>
</html>