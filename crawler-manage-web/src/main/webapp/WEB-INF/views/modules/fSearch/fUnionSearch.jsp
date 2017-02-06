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
				     <span id="unionSearchAbsPath" path="${unionSearchAbsPath}" style="display: none"></span>
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a id='module' href='${fSearchPath}'>五网联查</a>";
                                       var path_2="<a id='searchEngineBreadCrumb' href='${fUnionSearchPath}'>查询</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!-- 搜索框 -->
                    <div data-name="searchBox" class="row" id="fSearch">
                         <div id="searchBox_big" class="col-md-8 col-md-offset-2" style="margin-top:12%">
                             <div class="input-group" >
                                 <div class="input-group-btn">
                                     <button type="button" class="btn btn-default dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height:40px">
                                         <span id="searchEngine"
                                                 <c:choose>
                                                     <c:when test="${empty engine}">data-name="engine"</c:when>
                                                     <c:otherwise>data-name="${engine}"</c:otherwise>
                                                 </c:choose> >
                                             <c:choose>
                                                 <c:when test="${empty engineCN}">选择搜索引擎</c:when>
                                                 <c:otherwise>${engineCN}</c:otherwise>
                                             </c:choose>
                                         </span>
                                         <span class="caret"></span>
                                     </button>
                                     <ul id="chooseSearchEngine" class="dropdown-menu">
                                         <li data-name="baidu"><a href="#baidu">百度</a></li>
                                         <li data-name="sougou"><a href="#sougou">搜狗</a></li>
                                         <li data-name="haosou"><a href="#haosou">好搜</a></li>
                                         <li data-name="bing"><a href="#bing">必应</a></li>
                                         <li data-name="yahoo"><a href="#yahoo">雅虎</a></li>
                                     </ul>
                                 </div>
                                 <input id="searchBox_big_content" type="text" class="form-control" style="height:40px" onfocus="searchBoxFocus(this)"/>
                                 <span class="input-group-btn">
                                      <button id="searchBox_big_btn" class="btn btn-primary" type="button" style="height:40px">
                                          <i class="glyphicon glyphicon-search"></i>
                                      </button>
                                 </span>
                             </div>
                         </div>
                    </div>



                    <!--搜索结果  -->
                    <div id="f_searchResults_wrapper" class="row"  style="display:none">

                        <div class="keywords col-md-9" style="color: red">
                            <span>关键字 ： </span> &nbsp;&nbsp;&nbsp;
                            <span id="keywords"></span>

                        </div>

                        <!--页码  -->
                        <div id="f_searchPageNo_head"  class="col-md-9" >
                            <div class="col-md-9">
                                <ul class="pagination row">
                                    <li><a href="#" class="pageNo">1</a></li>
                                    <li><a href="#" class="pageNo">2</a></li>
                                    <li><a href="#" class="pageNo">3</a></li>
                                    <li><a href="#" class="pageNo">4</a></li>
                                    <li><a href="#" class="pageNo">5</a></li>
                                </ul>
                            </div>
                        </div>

                        <div id="f_searchResults" class="col-md-15" data-searchKey="null"></div>

                        <!--页码  -->
                        <div id="f_searchPageNo_foot" class="col-md-9" >
                            <div class="col-md-9">
                                <ul class="pagination row">
                                    <li><a href="#" class="pageNo">1</a></li>
                                    <li><a href="#" class="pageNo">2</a></li>
                                    <li><a href="#" class="pageNo">3</a></li>
                                    <li><a href="#" class="pageNo">4</a></li>
                                    <li><a href="#" class="pageNo">5</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
		   </div>
	   </div>
   </div>

   <div id="loading-mask" style="display: none;position:fixed; top: 0px;right:0px; bottom:0px; left: 0px; background-color: #EEE; z-index:1001; -moz-opacity: 0.7;opacity:.70;filter: alpha(opacity=70);">
   </div>
	
<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">
<input id="serializedFileName" style="display:none">
   
   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/common.js"></script>
   <script src="${ctx}/static/js/fSearch/fSearch.js"></script>
<%--
<script src="${ctx}/static/js/gsxt/gsxt-common.js"></script>
<script src="${ctx}/static/js/gsxt/gsxt-ajax.js"></script>
--%>



</body>
</html>