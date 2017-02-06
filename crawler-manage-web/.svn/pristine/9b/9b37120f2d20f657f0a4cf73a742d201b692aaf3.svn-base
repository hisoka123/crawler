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
    <script src="${ctx}/static/js/weixin/weixin-ajax.js"></script>
    <script src="${ctx}/static/js/weixin/weixin-common.js"></script>
    
    <link rel='icon' href='${logo}' type=‘image/x-ico’ />
    <title>${title}--微信</title>
</head>
<body>
     <div class="scm-head"><%@ include file="../head.jsp" %></div>
     <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
     <div class="container">
          <div style="margin-top:5%;" >
               <form  class="navbar-form" action="${pageContext.request.contextPath}/weixin/crawler_search" method="post">             
                      <div style="margin-left:13%">
                           <input id="search" type="text" name="crawlerContent" class="scm-text " placeholder="输入关注文章...">
                           <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button><br><br>
                      </div>
                      <div style="margin-left:13%">
                           <label class="radio-inline">
                                  &nbsp;<input type="radio" name="weixinOptions" id="weixinArticle" value="weixinArticle">文章
                           </label>
                           <label class="radio-inline">
                                  <input type="radio" name="weixinOptions" id="weixinUser" value="weixinUser">公众号
                           </label>
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
                 <!-- &nbsp;&nbsp;<img src="../static/img/loading.jpg" /> -->
                <br>
                                         正在加载数据,请稍候......
    </div> 
    
    
    
    
     <script type="text/javascript">
    
            var rootPath='<%=request.getContextPath()%>';
            var weixin='<%=request.getAttribute("crawlerContent")%>';         
            var crawlerArticleResult;
            var crawlerUserResult;
            
            
            $(function(){
                 
            	 weixin=weixin.split(",");
            	 if(weixin.length!=2){
            		  $("#loading").html('无相应数据。');
            		  return;
            	 }
            	 var weixin_keyword=weixin[0];            	 
            	 var weixin_type=weixin[1];
            	 var p1='<img src="'+rootPath+'/static/img/weixin2.png" /><small style="color:grey">&nbsp;以下内容来自微信公众平台&nbsp;&nbsp;&nbsp;</small>';
            	 var p2='&nbsp;<img src="'+rootPath+'/static/img/weixin2.png" /><small style="color:grey">&nbsp;以下内容来自微信公众号&nbsp;&nbsp;&nbsp;</small>';
            	 
            	 $("#search").val(weixin_keyword);
            	 
            	 
            	 
            	 if(weixin_type=='weixinArticle'){
            		 $('#weixinArticle').attr('checked','weixinArticle');
            		 crawlerArticleResult=weixin_getCrawlerArticle(rootPath,weixin_keyword);
            		 $('#id-h4-1').html(p1);
           		     $('#crawlerList').html(weixin_common_articleDisplay(rootPath,weixin_keyword,crawlerArticleResult));
           		     
           		     $("#loading").hide();
           		     $("#body").show();
            	 }else if(weixin_type=='weixinUser'){
            		 $('#weixinUser').attr('checked','weixinUser');
            		 crawlerUserResult=weixin_getCrawlerUser(rootPath,weixin_keyword);
            		 $('#id-h4-1').html(p2);
           		     $('#crawlerList').html(weixin_common_userDisplay(rootPath,weixin_keyword,crawlerUserResult));
           		     
           		     $("#loading").hide();
        		     $("#body").show();
            	 }
            	 
            	 
            	 
            	 //单选项有效
            	  $("#weixinArticle").click(function(){
            		     $("#body").hide(); 
            		     $("#loading").show();
            		               		  
      	    	        var radioChecked=$('input[name="weixinOptions"]:checked').val();
      	    	        var search_content=$("#search").val();
                        if(search_content!=''){
                        	 crawlerArticleResult=weixin_getCrawlerArticle(rootPath,search_content);
                        	 $('#id-h4-1').html(p1);
                   		     $('#crawlerList').html(weixin_common_articleDisplay(rootPath,search_content,crawlerArticleResult));
                        }else{
                        	$("#search").attr("placeholder","输入关注文章...");
                        }
                        
                         $("#loading").hide();
              		     $("#body").show();
      	         })
      	         
      	         
      	         $("#weixinUser").click(function(){
      	        	     $("#body").hide(); 
        		         $("#loading").show();
        		         
      	    	        var radioChecked=$('input[name="weixinOptions"]:checked').val();
      	    	        var search_content=$("#search").val();
                        if(search_content!=''){
                      	     crawlerUserResult=weixin_getCrawlerUser(rootPath,search_content);
                      	     $('#id-h4-1').html(p2);
                 		     $('#crawlerList').html(weixin_common_userDisplay(rootPath,search_content,crawlerUserResult));
                        }else{
                      	     $("#search").attr("placeholder","输入关注微信公众号...");
                         } 
                        
                         $("#loading").hide();
              		     $("#body").show();
      	         })
 
            })
    </script>
    
    
</body>
</html>