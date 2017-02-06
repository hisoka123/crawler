<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/sina.css" type="text/css" rel="stylesheet">   
    <link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet">
     <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/baidu/baidu-ajax.js"></script>
    <script src="${ctx}/static/js/baidu/baidu-common.js"></script>
    
    <link rel='icon' href='${logo}' type=‘image/x-ico’ />
    <title>${title}--百度百科</title>
</head>
<body>
     <div class="scm-head"><%@ include file="../head.jsp" %></div>
     <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
     <div class="container">
          <div style="margin-top:5%;" >
               <form  class="navbar-form" action="${pageContext.request.contextPath}/baidu/crawler_search" method="post">
                      <div style="margin-left:13%">
                           <input id="search" type="text" name="crawlerContent" class="scm-text " placeholder="输入查询词条...">
                           <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button><br><br>
                      </div>
              </form>
          </div> 
     </div><br><br>
     <div id="body" class="sina-content" style="display:none">
         <div id="id-h4-1" style="width:64%;margin-left:5%;background-color:#FFEFD5;text-align:right"></div><br>
         
         <div id="crawlerList" style="width:80%;margin-left:5%">
         </div>        
     </div>
     
    <div id="loading" style="margin-left:35%;">
                 <br><br><br><br><br>
                 &nbsp;&nbsp;<img src="../static/img/loading.jpg" />
                <br>
                                         正在加载数据,请稍候......
    </div> 
    
    
    
    
     <script type="text/javascript">
    
            var rootPath='<%=request.getContextPath()%>';
            var crawlerContent='<%=request.getAttribute("crawlerContent")%>';
            var crawlerResult;

            
            $(function(){
            	 var p1='<img src="'+rootPath+'/static/img/baike.png" /><small style="color:grey">&nbsp;以下内容来自百度百科&nbsp;&nbsp;&nbsp;</small>';

            	 $("#search").val(crawlerContent);
            		 crawlerResult=baidu_getCrawler(rootPath,crawlerContent);
            		 $('#id-h4-1').html(p1);
           		     $('#crawlerList').html(baidu_common_articleDisplay(rootPath,crawlerContent,crawlerResult));

                        
                         $("#loading").hide();
              		     $("#body").show();
      	         })


            //redirect to detail page
            function details(link){
                var link = link;
                location.href=rootPath+"redirectToDetails?url="+link;
            }

    </script>
    
    
</body>
</html>