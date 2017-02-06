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
   <script src="${ctx}/static/js/imgSlide.js"></script>    
   <script src="${ctx}/static/js/sina-ajax.js"></script>
   <script src="${ctx}/static/js/sina-common.js"></script> 
   
  
<link rel='icon' href='${logo}' type=‘image/x-ico’ />
<title>${title}--新浪微博</title>
</head>
<body>
    
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left "><%@ include file="leftMenu.jsp" %></div>
    <div id="loading" style="margin-left:45%;margin-top:10%"><%@ include file="../loading.jsp" %></div>
    <div id="body"  style="display:none;">
         <div  style="width:25%;float:left;">
              <div style="background-image:url(${pageContext.request.contextPath}/static/img/background-image/2.jpg);width:100%;height:100%;">             
                   <div  class="row">
                       <div class="col-md-5 col-md-offset-3 sina-margin-top-4" id="profile_image_url" style="text-align:center"></div>
                   </div>
                   <div class="row">
                       <div class="col-md-6 col-md-offset-3" id="screen_name" style="text-align:center;"></div>
                   </div>
                   <div class="row">
                        <div class="col-md-5" id="attention_button"></div>
                        <div class="col-md-1" id="gender"></div>
                        <div class="col-md-5" id="location"></div>                    
                   </div><br>                
              </div>
              <br>          
              <div class="row" style="background:#FFFAFA">
                   <div class="col-md-3">
                        <div  class="sina-text-center" id="friends_count" ></div>
                        <div class="sina-text-center" id="friends_url"></div>
                   </div> 
                   <div class="col-md-1 ">
                        <div style="height:10px;"></div>                                  
                        <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                   </div>
                   <div class="col-md-3">
                         <div class="sina-text-center" id="followers_count"></div>
                         <div class="sina-text-center" id="followers_url"></div>
                   </div>
                   <div class="col-md-1 ">
                        <div style="height: 10px;"></div>
                        <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                   </div>
                   <div class="col-md-3">
                         <div class="sina-text-center" id="statuses_count"></div>
                         <div class="sina-text-center" id="statuses_url"></div>
                  </div>
              </div>             
              <br>
              <div style="background:#FFFAFA">
                  <h4>简介</h4>
                  <div  id="description">
                  &nbsp;&nbsp;&nbsp;&nbsp;N/A
                  </div>                      
              </div>                    
         </div>          
          <div  id="content" style="width:53%;float:right;margin-right:4%">                       
         </div>         
         <div  id="paging"  class="sina-content-70 sina-fixed-bottom">                       
         </div>
  </div>
   
   
   
<script type="text/javascript">
   
var profile;    //人物基本信息
var pageCount; //微博页数
var rootPath='<%=request.getContextPath()%>';



var already_button='<div class="btn-group">'
                        +'<button type="button" class="btn-primary btn" style="cursor:default;">&nbsp;已关注</button>'
                        +'<button type="button" class="btn-primary btn dropdown-toggle" data-toggle="dropdown">'
                              +'<span class="caret"></span>'
                              +'<span class="sr-only">是否关注</span>'
                        +'</button>'
                        +'<ul class="dropdown-menu" role="menu">'
                             +'<li><a href="#"  onClick="contacts(this);">&nbsp;Ta的人脉圈 </a></li>'
                             +'<li><a href="#" onClick="delete3(this);"><i class="glyphicon glyphicon-minus"></i>&nbsp;取消关注</a></li>'
                        +'</ul>'
                  +'</div>';
var attention_button='<button type="button" class="btn btn-warning sina-td-85px" onClick="save3(this);"><i class="glyphicon glyphicon-plus"></i>&nbsp;关注</button>';





//取消对此人的关注。
function delete3(button){
	
	var delete_result=sina_destroy_friendships(rootPath,profile.uid);
	
	if("deleted-success"==delete_result){
		$("#attention_button").html("&nbsp;&nbsp;&nbsp;&nbsp;"+attention_button);
	}else if("deleted-failed"==delete_result){
		sina_common_deleteFromSina();
	}else if("error"==delete_result){
		sina_common_dataBaseError();
	}
}        
        
//对此人加关注
function save3(button){ 
	    
	     var save_attentionInfo=encodeURI(encodeURI(JSON.stringify(profile)));/*对于中文，需要两次 encodeURI  */
	     var save_result=sina_create_friendships(rootPath,save_attentionInfo);
	    
	     if("saved-success"==save_result){
	    	 $("#attention_button").html("&nbsp;&nbsp;&nbsp;&nbsp;"+already_button);
	     }else if("saved-failed"==save_result){
	    	 sina_common_addToSina();
	     }else if("error"==save_result){
	    	 sina_common_dataBaseError();
	     }
}
//分页样式
function weibo_page_style(pageCountX){
	 var pageRow='<ul class="pagination sina-td-10px">';
	 for(var j=1;j<=pageCountX;j++){
	   	     pageRow +='<li id="page_'+j+'"><a href="#" onClick="weibo_page_content(this);">'+j+'</a></li>';         
	 }
	 pageRow +='</ul>';
	 
	 $("#paging").html(pageRow);	
}

//分页内容
function weibo_page_content(a){
	var pageCountActive=$(a).text();
	weibo_content_byPage(rootPath,profile.uid,pageCountActive); //显示分页微博内容
	weibo_page_style(pageCount); //分页样式
	$("#page_"+pageCountActive).addClass("active");	
}



//按页显示帖子详情
function weibo_content_byPage(rootPathX,uidX,pageIndex){
	
	var weiboContentByPage=sina_post_read(rootPathX, uidX, pageIndex);
	 if("error"==weiboContentByPage){
	    	 $("#content").html('<strong style="color:red;margin-left:5%;margin-top:5%">与后台数据服务器中断,无法读取人物的微博帖子信息。</strong>');
	    }else{
	    	
	    	$("#content").html(sina_common_getWeiboContent(profile.gender,weiboContentByPage));	    
	 } 
}


//人脉圈
function contacts(a){
	  var contacts_uid=profile.uid;
	  location.href=rootPath+'/sinaWeibo/contacts?uid='+contacts_uid+'&isAttention=1';
	  
}



//main方法
$(function(){        	      
    var uid='<%=request.getAttribute("postsAttention")%>';
     
    
    
    profile=sina_attention_personalInfo(rootPath,uid);//从数据库中读取已关注人物的基本信息，返回为json对象
    if("error"==profile){
    	sina_common_error();
    	return;
    }
    
    $("#profile_image_url").html('<a href="'+profile.statuses_url+'" target="_blank"><img src="'+profile.profile_image_url+'" class="sina-td-110px img-circle" onerror="sina_common_bigImgNoFound(\''+rootPath+'\');"/></a>');
    $("#screen_name").html('<a href="'+profile.statuses_url+'" target="_blank" style="text-decoration:none"><p style="color:#FFFFFF;font-weight:bold;font-size:21px;margin-top:10px;">'+profile.screen_name+'</p></a>');
  	if(profile.enable==1){  	    	    
  	      $("#attention_button").html("&nbsp;&nbsp;&nbsp;&nbsp;"+already_button);
  	}else{
  	      $("#attention_button").html("&nbsp;&nbsp;&nbsp;&nbsp;"+attention_button);
  	}
  	$("#gender").html('<h5><p style="color:#FFFFFF">'+sina_common_sex(profile.gender)+'</p></h5>');
  	$("#location").html('<h5><p style="color:#FFFFFF">&nbsp;'+profile.location+'</p></h5>');
    $("#friends_url").html('<a href="'+profile.friends_url+'" target="_blank">关注</a>'); 
	$("#followers_url").html('<a href="'+profile.followers_url+'" target="_blank">粉丝</a>');
	$("#statuses_url").html('<a href="'+profile.statuses_url+'" target="_blank">微博</a>');
  	$("#friends_count").html('<h4><b>'+toWan(profile.friends_count)+'</b></h4>');
  	$("#followers_count").html('<h4><b>'+toWan(profile.followers_count)+'</b></h4>');
  	$("#statuses_count").html('<h4><b>'+toWan(profile.statuses_count)+'</b></h4>');
  	if(null!=profile.description){
  	   $("#description").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+profile.description); 
  	}    	         	    	 

  	 
  	  	
  	 pageCount=sina_weiboPage_count(rootPath,uid);   //微博页数	
  	 weibo_content_byPage(rootPath,uid,1);   //微博第一页详情 
  	
  	 weibo_page_style(pageCount); //分页样式 
  	 
  	 $("#loading").hide();
  	 $("#body").show();
  	 
  	 $("#page_1").addClass("active");   
}); 
   
</script>      
</body>
</html>