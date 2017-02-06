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
				<div id="right_content" class="sr-zql-content-right" style="padding-left:20px;margin-right:0px;">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a href=\"${modulesHomePath}\">人物搜索</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").remove();
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
                    <div data-name="personSearchResults" class="sr-h-font row" >
                         <div id="personSearchResults" data-searchKey="null" class="col-md-9" style="display:none;">
                         </div>
                         <div id="personSource" class="col-md-3" style="display:none;">
                               <div>
                                   <div class="col-md-8" style="padding-left:0px;padding-right:0px;">
                                        <jsp:include page="${searchBoxSmallPath}" ></jsp:include>
                                        <br>
                                        <div id="read_detail" class="panel panel-default" style="width:155px;float:right"> 
                                             <div class="panel-heading">
                                                 <strong>
                                                    [<span id="joinDetailed_num">0</span>/4]
                                                     <span style="cursor:pointer" title="查阅">综合查阅详情</span>
                                                 </strong>
                                              </div>
                                             <ul class="list-group">
                                             </ul>
                                             <div style="text-align:center;padding-top:5px;padding-bottom:5px">
                                                  <span class="sr-h-fontColor" style="cursor:pointer" onclick="lookAllContrastORLookDetail(this,'personSearch');"><a href="${personDetailsPath}" target="_blank"><strong>详&nbsp;&nbsp;情</strong></a></span>
                                                  &nbsp;&nbsp;&nbsp;
                                                  <span class="sr-h-fontColor" style="cursor:pointer" onclick="clearAllContrastORLookDetail(this,'joinDetailed_num');"><strong>清&nbsp;&nbsp;空</strong></span>
                                             </div>
                                        </div>
                                   </div>
                  
                                   <div class="col-md-3">
                                        <div data-name="smallSearchIcon" class="sr-h-rightIconDiv">
                                             <i id="smallSearchIcon" class="glyphicon glyphicon-search sr-h-rightSearchStyle"  onClick="rightSearchIcon('personSource');"></i>
                                             <br>
                                             <div id="smallSearchIcon_checkbox" class="sr-h-checkboxDiv" title="显示/搜索全部">
                                                  <div style="margin-top:-4px;display:none">
                                                       <i class="glyphicon glyphicon-ok" style="color:#FF7F00"></i>
                                                  </div>
                                             </div>
                                        </div>
                                        <div data-name="to_sinaSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_sinaSearchResults" src="${ctx}/static/imgs/logo/sinaWeibo01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显新浪微博">
                                             <br>
                                             <div id="to_sinaSearchResults_checkbox" class="sr-h-checkboxDiv" title="显示/搜索新浪微博">
                                             </div>
                                        </div>
                                        <div data-name="to_baiduSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_baiduSearchResults" src="${ctx}/static/imgs/logo/baike01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显百度百科">
                                              <br>
                                             <div id="to_baiduSearchResults_checkbox" class="sr-h-checkboxDiv" title="显示/搜索百度百科">
                                             </div>
                                        </div>
                                        <div data-name="to_zhihuSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_zhihuSearchResults" src="${ctx}/static/imgs/logo/zhihu01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显知乎">
                                             <br>
                                             <div id="to_zhihuSearchResults_checkbox" class="sr-h-checkboxDiv" title="显示/搜索知乎">
                                             </div>
                                        </div>
                                        <div data-name="to_weixinSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_weixinSearchResults" src="${ctx}/static/imgs/logo/weixin02.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显微信">
                                              <br>
                                             <div id="to_weixinSearchResults_checkbox" class="sr-h-checkboxDiv" title="显示/搜索微信">
                                             </div>
                                        </div>
                                        <div data-name="to_linkedInSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_linkedInSearchResults" src="${ctx}/static/imgs/logo/linkedIn01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显领英">
                                             <br>
                                             <div id="to_linkedInSearchResults_checkbox" class="sr-h-checkboxDiv" title="显示/搜索领英">
                                             </div>
                                        </div>
                                  <%--  <div data-name="to_taobaoSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_taobaoSearchResults" src="${ctx}/static/imgs/logo/taobao01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显淘宝">
                                        </div>
                                        <div data-name="to_youkuSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_youkuSearchResults" src="${ctx}/static/imgs/logo/youku02.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显优酷">
                                        </div>
                                        <div data-name="to_youtubeSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_youtubeSearchResults" src="${ctx}/static/imgs/logo/youtube01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显YouTuBe">
                                        </div>
                                        <div data-name="to_facebookSearchResults" class="sr-h-rightIconDiv">
                                             <img id="to_facebookSearchResults" src="${ctx}/static/imgs/logo/facebook01.png" class="sr-h-logo28 sr-h-rightIconStyle" title="只显Facebook">
                                        </div> --%>
                                    </div>
                               </div>
                         </div> 
                    </div>
				</div>
			</div>
			
			
		</div>
	</div>
	<!-- 返回顶部 -->
<div class="sr-zql-totop"></div>

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
<script src="${ctx}/static/js/zhihu/zhihu-ajax.js"></script>
<script src="${ctx}/static/js/zhihu/zhihu-common.js"></script>
<script src="${ctx}/static/js/weixin/weixin-ajax.js"></script>
<script src="${ctx}/static/js/weixin/weixin-common.js"></script>
<script src="${ctx}/static/js/linkedIn/linkedIn-ajax.js"></script>
<script src="${ctx}/static/js/linkedIn/linkedIn-common.js"></script>
<script src="${ctx}/static/js/baidubaike/baidu-ajax.js"></script>
<script src="${ctx}/static/js/baidubaike/baidu-common.js"></script>
<script src="${ctx}/static/js/personSearch/personSearch-common.js"></script>
<script src="${ctx}/static/js/personSearch/personSearch-more.js"></script>

<script type="text/javascript">
$(function(){
    
	 ROOTPATH="${ctx}";
	 searchBoxPlaceholder("all");
	 leftMenu();
	 connectIndexedDB();
	 
	  
	  $("#searchBox_big_btn").click(function(){
		     personSearch_action(searchBox_Big());
	  })
	  
	  $("#searchBox_small_btn").click(function(){
		     personSearch_action(searchBox_small());
	  })
	
});
</script>
</body>
</html>