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
                                       var path_1="<a href='${sxjlcxptSearchPath}'>失信记录查询平台</a>";
                                       var path_2="<a href='${sxjlcxptSearchPath}'>失信记录搜索</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!--搜索框  -->
                    <div data-name="searchBox" class="row" id="sxjlcxptdivid" >               
                         <div id="searchBox_big" class="col-md-6 col-md-offset-2" style="margin-top:6%">
                               <jsp:include page="${searchBoxBigPath}"></jsp:include>             
                         </div>
                    </div>
                    
                    
                    <!--搜索结果  -->
                      <div data-name="sxjlcxpt_searchResults" class="row">
                         <div id="sxjlcxpt_searchResults" class="col-md-11" data-searchKey="null" >
                         	<!-- 填充搜索结果 -->
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
<script src="${ctx}/static/js/sxjlcxpt/sxjlcxpt-ajax.js"></script>
<script src="${ctx}/static/js/sxjlcxpt/sxjlcxpt-common.js"></script>


<script type="text/javascript">
function sxjlcxpt_getSearchResults(keyword){  
	  $.ajax({
		   url:ROOTPATH+"/modules/sxjlcxpt/getSearchResults",
		   data:encodeURI(encodeURI("keyword="+keyword)),
		   async:false,
	       success:function(data){
	    	   var searchData = data.tbody;
	    	      // console.log(searchData);	    	 
 					//document.getElementById("sxjlcxptdivid").style.display="none";//隐藏
 					var htmlStr = "";
 					for(var i=0; i < searchData.length; i++) {
 						htmlStr += "<tr>";  
 						//htmlStr +="("<td height=\"25\" colspan=\"3\"><li class=\"lid\"><a href=\"../news/10036164-1.shtml\" target=\"_blank\" title=\"武汉市质监局发布童装产品质量安全风险预警 1样品检出可分解致癌芳香胺染料\">武汉市质监局发布童装产品质量安全风险预警 1样品检出可分解致癌芳香胺染料</a><span>[2016年02月19日]</span></li></td>"
 						htmlStr += "<td height=\"25\" colspan=\"3\"> <li> <a href=\""+searchData[i].herfsxjlcxpt+"\" target=\"_blank\" >"+searchData[i].textsxjlcxpt+"</a></li></td>";
 						//htmlStr += "<td>"+searchData[i].textsxjlcxpt+"</td>";
 						//searchData[i].herfsxjlcxpt
 						htmlStr += "</tr>";
 						htmlStr += "<tr>"; 
 						htmlStr += "<td height=\"25\" colspan=\"3\">"+searchData[i].gjztext+"</td>";
 						htmlStr += "</tr>";
 						
 					}
 					//alert(htmlStr);
 					$("#sxjlcxpt_searchResults").append(
 						"<table border='1'>" +  						
 							"<tbody>"+htmlStr+"</tbody>"+
 						"</table>"
 					);
	    	       
	       },
	       error:function(){
	    	       searchResult="error";
	       }		  
	  });	
	  return searchResult;	
}

function sxjlcxpt_action(searchKey){
		   var result = sxjlcxpt_getSearchResults(searchKey);
	       $("div[data-name='verifycodeBox']").css("display", "block");

}

$(function(){
    
	 ROOTPATH="${ctx}";
	 searchBoxPlaceholder("sxjlcxpt");
	 leftMenu();
	 connectIndexedDB();
	 
	  
	  $("#searchBox_big_btn").click(function(){
		  sxjlcxpt_action(searchBox_Big());
	  })
	  
// 	  $("#searchBox_small_btn").click(function(){
// 		  dailianmeng_action(searchBox_small());
// 	  })
	  
	  $("#verifySubmit").click(function(){
		  var verifycode = $("#verifycode").val();
		  var serializedFileName = $("#serializedFileName").val();
		  
		  dailianmeng_verifySubmit(verifycode, serializedFileName);
	  })
	  
});

</script>
</body>
</html>