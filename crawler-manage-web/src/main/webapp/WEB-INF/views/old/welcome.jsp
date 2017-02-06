<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/static/js/bootstrap.min.js" rel="stylesheet">
    <link href="${ctx}/static/css/sina.css" type="text/css" rel="stylesheet">   
   <link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet">
  
  
   <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   <script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
   <script src="${ctx}/static/js/bootstrap.min.js"></script> 
   
<link rel='icon' href='${logo}' type=‘image/x-ico’ />    
<title>${title}</title>

</head>
<body>
    <div style="width:80%"><%@ include file="head.jsp"  %></div>
    
    <div  style="margin-top:13%;margin-left:30%">
        <div class="row" style="width:100%">
            <div class="col-md-2">
              <a href="${ctx}/sinaWeibo/crawler"><img src="${ctx}/static/img/sinaWeibo.ico" class="img-thumbnail"/></a>
              <br>
              <h4><a href="${ctx}/sinaWeibo/crawler"  style="text-decoration:none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新浪微博</a></h4>
            </div>
            <div class="col-md-2" style="margin-left:3%">
              <a href="${ctx}/zhihu/crawler"><img src="${ctx}/static/img/zhihu.PNG" class="img-thumbnail" style="width:132px;height;128px;"/></a>
              <br>
              <h4><a href="${ctx}/zhihu/crawler"  style="text-decoration:none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知乎</a></h4>
            </div>
            <div class="col-md-2" style="margin-left:3%">
                <a href="${ctx}/baidu/crawler"><img src="${ctx}/static/img/baike.ico" class="img-thumbnail"/></a>
                <br>
                <h4><a href="${ctx}/baidu/crawler"  style="text-decoration:none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;百度百科</a></h4>
            </div>
        </div><br>
        <div class="row" style="width:100%">
            <div class="col-md-2 ">
              <a href="${ctx}/weixin/crawler"><img src="${ctx}/static/img/weixin.ico" class="img-thumbnail"/></a>
              <br>
              <h4><a href="${ctx}/weixin/crawler"  style="text-decoration:none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信</a></h4>
            </div>
            <div class="col-md-2" style="margin-left:3%">
              <a href="${ctx}/linkedIn/crawler"><img src="${ctx}/static/img/linkedIn.ico" class="img-thumbnail"/></a>
              <br>
              <h4><a href="${ctx}/linkedIn/crawler"  style="text-decoration:none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LinkedIn</a></h4>
            </div>
            <div class="col-md-2">
            </div>
        </div>
    </div>
</body>
</html>