<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%@ include file="/WEB-INF/commons/taglibs.jsp" %> --%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>
<link href="${ctx}/static/css/loading.css" rel="stylesheet" type="text/css">
<body>
  
  <div style="width:100%">
      <%--  <%@ include file="nav.jsp" %>   --%>
  </div> 
  <div id="left_menu" style="width:17%;float:left;">
       <%@ include file="left-menu.jsp" %>
  </div>
  
  
  <div id="right_content" class="sr-right-content">
       
       <div id="nav_path" style="height:48px">
            <%@ include file="nav-path.jsp" %>
       </div>
                 
       <div class="col-lg-6" style="margin-left:18%;margin-top:9%">
              <%@ include file="searchBox.jsp" %>
             
              
       </div>
       <script type="text/javascript">
             $(function(){
                   $("#search_content").attr("placeholder","输入人物昵称......");
                   
                   $("#search_btn").click(function(){
                	          var nickname=$("#search_content").val();
                	          var rootPath="<%=request.getContextPath()%>";
                	          location.href=rootPath+"/demo/searchResult?nickname="+nickname;
                   })
             })
        </script>
  </div>
  
  
   <div class='btn-group' role='group'>
        <button id='"+uid+"_attend' class='btn btn-warning' type='button'>
            <i class='glyphicon glyphicon-plus'></i> &nbsp;关注&nbsp;&nbsp;&nbsp;
        </button>
        <button class='btn btn-warning dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
             <span class='caret'></span>
             <span class='sr-only'>下拉菜单</span>
        </button>
        <ul class='dropdown-menu'>
            <li><a href='#'>加入详情</a></li>
        </ul>
   </div>
  
  
  <br><br><br>
   <div class="sr-loading" style="width:90px;height:90px;"></div>
  
  
  <%--  <div>
          <%@ include file="footer.jsp" %>  
   </div> --%>
   
 
  
</body>
</html>