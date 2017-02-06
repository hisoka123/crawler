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
                                       var path_1="<a href='${sinaIndexPath}'>新浪微博</a>";
                                       var path_2="<a href='${sinaSearchPath}'>搜索用户</a>";
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
                    <div data-name="sina_searchResults" class="row" >
                         <div id="sina_searchResults" class="col-md-9" data-searchKey="null" style="display:none"></div>
                         <div id="sina_searchResultsRightIcon" class="col-md-3" style="display:none">
                              <div>
                                    <div class="col-md-8" style="padding-left:0px;padding-right:0px">
                                        <jsp:include page="${searchBoxSmallPath}" ></jsp:include>
                                        <br>
                                        <div id="contrast_detail" class="panel panel-default" style="width:155px;float:right"> 
                                             <div class="panel-heading" style="text-align:center">
                                                 <strong>
                                                     [<span id="contrastDetailed_num">0</span>/4]
                                                     <span style="cursor:pointer" title="新浪对比详情">对比详情</span>
                                                 </strong>
                                              </div>
                                             <ul class="list-group">
                                             </ul>
                                             <div style="text-align:center;padding-top:5px;padding-bottom:5px">
                                                  <span class="sr-h-fontColor" style="cursor:pointer" onclick="lookAllContrastORLookDetail(this,'sina');"><strong title="对比多个人物详情">对&nbsp;&nbsp;比</strong></span>
                                                  &nbsp;&nbsp;&nbsp;
                                                  <span class="sr-h-fontColor" style="cursor:pointer" onclick="clearAllContrastORLookDetail(this,'contrastDetailed_num','contrast_detail');"><strong>清&nbsp;&nbsp;空</strong></span>
                                             </div>
                                        </div>
                                   </div> 
                  
                                   <div class="col-md-3">
                                        <div id="smallSearchIcon" class="sr-h-rightIconDiv">
                                             <i id="smallSearchIcon" class="glyphicon glyphicon-search sr-h-rightSearchStyle"  onClick="rightSearchIcon('searchResultsAction');"></i>
                                        </div>
                                        <div id="to_history" class="sr-h-rightIconDiv">
                                              <img src="${ctx}/static/imgs/modules/history48.png" class="sr-h-logo28 sr-h-rightIconStyle" title="历史记录">
                                        </div>
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
	
<jsp:include page="${footerPath}"></jsp:include>
   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/defineConstant.js"></script>
<script src="${ctx}/static/js/indexedDB.js"></script>
<script src="${ctx}/static/js/common.js"></script>
<script src="${ctx}/static/js/sinaweibo/sina-ajax.js"></script>
<script src="${ctx}/static/js/sinaweibo/sina-common.js"></script>


<script type="text/javascript">
$(function(){
    
	 ROOTPATH="${ctx}";
	 searchBoxPlaceholder("sina");
	 leftMenu();
	 connectIndexedDB();
	 
	  
	  $("#searchBox_big_btn").click(function(){
		    sina_action(searchBox_Big());
	  })
	  
	  $("#searchBox_small_btn").click(function(){
		   sina_action(searchBox_small());
	  })
});
</script>
</body>
</html>