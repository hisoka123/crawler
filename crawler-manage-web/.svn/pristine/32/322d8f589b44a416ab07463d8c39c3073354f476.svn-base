<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <%--  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"> --%>
    
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   <%--  <script src="${pageContext.request.contextPath}/static/js/jquery-2.1.4.min.js"></script> --%>
    
</head>
<body>
    <div class="container" style="float:left;width:80%">
        
            <div class="sidebar">
                <img src="${pageContext.request.contextPath}/static/img/sinaWeibo.ico" style="width:25px;height:25px" style="float:left">
                <ul class="nav nav-sidebar" style="text-align:center;">                   
                    <li id="menu_1" ><a href="${pageContext.request.contextPath}/sinaWeibo/crawler">关注人物</a></li>
                    <li id="menu_2"><a href="${pageContext.request.contextPath}/sinaWeibo/attentionInfo">关注列表</a></li>
                    <li id="menu_3"><a href="${pageContext.request.contextPath}/schedule/scheduleList">定时任务</a></li>
                    <li id="menu_4"><a href="${pageContext.request.contextPath}/sinaWeibo/burstoutList?pageNumber=0&&pageSize=5">突发事件</a></li>
                    <li id="menu_5"><a href="${pageContext.request.contextPath}/sinaWeibo/alarmList">报警设置</a></li>
                </ul>
            </div>
        </div>
        
  <script type="text/javascript">
          var url=document.location.toString();
          var style="background-color:#f5f5f5";
          
          
          function menu3(){
                if(url.split("?")[0].indexOf("/schedule/")!=-1 ||
                   url.split("?")[0].indexOf("/taskTrack/list")!=-1){
        	         $("#menu_3").attr("style",style);        	  
                     return;
                }
                
          }
         
          function func(){
        	  var url_array=url.split("sinaWeibo");
        	  
        	  if(url_array.length==1){
        		  return;
        	  }
              var url_active=url_array[1].split("?");
             
              
              if(url_active[0]=="/crawler" || 
                 url_active[0]=="/crawler_search" ||
                 url_active[0]=="/unattentionPosts"){
            	  
            	  $("#menu_1").attr("style",style);
            	  
              }else if(url_active[0]=="/attentionInfo" ||
            		   url_active[0]=="/attentionInfo_search" ||
            		   url_active[0]=="/postsAttention" ||
            		   url_active[0]=="/contacts" ){
            	  
            	   $("#menu_2").attr("style",style);
              }else if(url_active[0]=="/burstoutList"){
            	   $("#menu_4").attr("style",style);
              }else if(url_active[0]=="/alarmList" ||
            		   url_active[0]=="/alarmBurstoutList"){
            	   $("#menu_5").attr("style",style);
              }
        	  
        	  
        	  
          }
          
          
          
          func();
          menu3();
          
  
  </script>


</body>
</html>