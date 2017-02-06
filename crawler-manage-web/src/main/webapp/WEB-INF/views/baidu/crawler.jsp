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
    <title>${title}--百度百科</title>
<script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
 <script src="${ctx}/static/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div class="sina-content scm-center-1">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/baidu/crawler_search" method="post">
              <input id="search" type="text" name="crawlerContent" class="scm-text " placeholder="输入查询词条...">&nbsp;&nbsp;
              <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button><br><br>
          </form>
    </div> 
</body>
</html>