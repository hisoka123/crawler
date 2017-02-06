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
    
    <link rel='icon' href='${logo}' type=‘image/x-ico’ />
    <title>${title}--LinkedIn</title>
</head>
<body>
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div style="margin-top:10%;margin-left:30%;">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/linkedIn/crawlerSearch" method="post">             
              <input type="text" name="crawlerPerson" class="scm-text " placeholder="输入关注人物......">&nbsp;&nbsp;
              <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;</button>           
         </form>    
    </div>     
</body>
</html>