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
    <title>${title}--微信</title>
<script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
 <script src="${ctx}/static/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
   $(function(){
	     $("#weixinArticle").click(function(){
	    	   var radioChecked=$('input[name="weixinOptions"]:checked').val();
	    	   var search_content=$("#search").val();
	    	   
	    	   if(radioChecked=='weixinArticle'){
	    		   $('#search').attr('placeholder','输入关注文章...');

	    	   }
	    	   
	    	   
	     })
	     $("#weixinUser").click(function(){
	    	   var radioChecked=$('input[name="weixinOptions"]:checked').val();
	    	   if(radioChecked=='weixinUser'){
	    		   $('#search').attr('placeholder','输入关注公众号...');
	    	   }
	     })
	   
	   
   })
</script>
</head>
<body>
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div style="margin-top:10%;margin-left:30%;">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/weixin/crawler_search" method="post">             
              <input id="search" type="text" name="crawlerContent" class="scm-text " placeholder="输入关注文章...">&nbsp;&nbsp;
              <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button><br><br>
              <label class="radio-inline">
                     &nbsp;&nbsp;<input type="radio" name="weixinOptions" id="weixinArticle" value="weixinArticle" checked>文章
               </label>
               <label class="radio-inline">
                     <input type="radio" name="weixinOptions" id="weixinUser" value="weixinUser">公众号
              </label>           
          </form>  
    </div> 
</body>
</html>