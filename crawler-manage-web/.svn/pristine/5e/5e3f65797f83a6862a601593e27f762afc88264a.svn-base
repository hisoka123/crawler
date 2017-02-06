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
				<div id="right_content" class="sr-zql-content-right" style="padding-left:20px;margin-right:0px">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a href='${youtubeIndexPath}'>You TuBe</a>";
                                       var path_2="<a href='${youtubeSearchPath}'>搜索用户</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!--搜索框  -->
                    <div data-name="searchBox" class="row">               
                         <div id="searchBox_big" class="col-md-6 col-md-offset-2" style="margin-top:12%">
                               <jsp:include page="${searchBoxBigPath}"></jsp:include>             
                         </div>
                    </div>
                    
                    <!--搜索结果  -->
                    <div data-name="searchResults" class="row" >
                         <div id="searchResults" class="sr-h-font col-md-8" style="display:none"></div>
                         <div id="searchResultsAction" class="col-md-4" style="display:none">
                              <div style="position:fixed">
                                   <div class="col-md-9">
                                        <jsp:include page="${searchBoxSmallPath}" ></jsp:include>
                                   </div>
                  
                                   <div class="col-md-3" style="padding-right:0px">
                                        <div id="smallSearchIcon" class="sr-h-rightSmallModulesDiv" style="height:40px;cursor:pointer"><i class="glyphicon glyphicon-search" style="margin-top:20%;color:rgb(72, 126, 176);"></i></div>
                                        <div id="to_history" class="sr-h-rightSmallModulesDiv"><img src="${ctx}/static/imgs/modules/history48.png" style="width:28px;heigth:28px;padding-top:5px"></div>
                                   </div>
                               </div>
                          </div> 
                    </div>
                    
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
	
   <%--  <jsp:include page="${footerPath}"></jsp:include> --%>
   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/common.js"></script>


<script type="text/javascript">
$(function(){
    
	 var searchKey="";
	 searchBoxPlaceholder("youtube");
	 leftMenu();
	  
	  
	  $("#searchBox_big_btn").click(function(){
		     searchKey=searchBox_Big();
		     if(searchKey!="" && searchKey!="关键词不能为空! ! !"){
         	   //
          }
	  })
	  
	  $("#searchBox_small_btn").click(function(){
		     
	  })
});
</script>


</body>
</html>