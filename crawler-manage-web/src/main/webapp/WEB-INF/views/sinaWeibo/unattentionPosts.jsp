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
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div id="loading" style="margin-left:45%;margin-top:10%"><%@ include file="../loading.jsp" %></div>
    <div id="body"  style="display:none">
         <div style="width:25%;float:left;">
              <div style="background-image:url(${pageContext.request.contextPath}/static/img/background-image/2.jpg);width:100%;height:100%;">
              
                     <div  class="row">
                         <div class="col-md-5 col-md-offset-3 sina-margin-top-4" id="profile_image"></div>
                     </div>
                     <div class="row">
                         <div class="col-md-6 col-md-offset-3" id="nickname"></div>
                     </div>
                     <div class="row">
                          <div class="col-md-5" id="attention_button"></div>
                          <div class="col-md-1" id="gender"></div>
                          <div class="col-md-5" id="location"></div>                    
                     </div>
                
                    <br>
                </div>
                
                <br>
                <div class="row" style="background:#FFFAFA">
                     <div class="col-md-3">
                          <div  class="sina-text-center" id="follow" ></div>
                          <div class="sina-text-center" id="follow_url"></div>
                     </div> 
                     <div class="col-md-1 ">
                          <div style="height:10px;"></div>                                  
                          <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                     </div>
                     <div class="col-md-3">
                          <div class="sina-text-center" id="fans"></div>
                          <div class="sina-text-center" id="fans_url"></div>
                     </div>
                     <div class="col-md-1 ">
                          <div style="height: 10px;"></div>
                          <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                     </div>
                     <div class="col-md-3">
                          <div class="sina-text-center" id="statuses"></div>
                          <div class="sina-text-center" id="statuses_url"></div>
                     </div>
                </div>
                
               <div style="background:#FFFAFA">
                   <h4>简介</h4>
                   <div  id="person_info">
                         &nbsp;&nbsp;&nbsp;&nbsp;N/A
                   </div>                      
               </div> 
                 
         </div>
         <div  id="content" style="width:53%;float:right;margin-right:4%">              
         </div> 
          <div class="sina-content-70 sina-fixed-bottom " id="paging">   
                    
         </div>
    </div> 
    
    
    
    <script type="text/javascript">
  
    var unattention;//未关注下，人物的基本信息，json对象
    var pageCount;  //微博页数
    var rootPath='<%=request.getContextPath()%>';
  
    var attention_button='<button type="button" class="btn btn-warning sina-td-85px" onClick="save3(this);"><i class="glyphicon glyphicon-plus"></i>&nbsp;关注</button>';

    
//加关注
function save3(button){ 
    var save_content=encodeURI(encodeURI(JSON.stringify(unattention)));
    var save_result=sina_crawlerCreate_friendships(rootPath,save_content);
   
    if("saved-success"==save_result){
    	location.href=rootPath+'/sinaWeibo/postsAttention?uid='+unattention.uid;  
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
	weibo_content_byPage(rootPath,unattention.uid,pageCountActive); //显示分页微博内容
	weibo_page_style(pageCount); //分页样式
	$("#page_"+pageCountActive).addClass("active");	
}
 
 
//按页显示帖子详情
function weibo_content_byPage(rootPathX,uid,pageIndex){
	var weiboContentByPage=sina_post_read(rootPathX, uid, pageIndex);
	 if("error"==weiboContentByPage){
		 $("#content").html('<strong style="color:red;margin-left:5%;margin-top:5%">与后台数据服务器中断,无法读取人物的微博帖子信息。</strong>');
	    }else{
	    	$("#content").html(sina_common_getWeiboContent(unattention.gender,weiboContentByPage));	    
	 } 
}


//main方法
$(function(){
	 
	
	  var nameUid='<%=request.getAttribute("weiboPosts")%>';
	  nameUid=nameUid.split(",");
	  /*
	   *isAttentionUidName[1] uid
	   *isAttentionUidName[0] nickname
	   */	  
	  unattention=sina_unattention_profile(rootPath,nameUid[0],nameUid[1]);//获取未关注人物的基本信息,json对象
	
	 	 
	 $("#profile_image").html('<a href="'+unattention.statuses_url+'" target="_blank"><img src="'+unattention.profile_image+'" class="sina-td-110px img-circle" onerror="sina_common_bigImgNoFound(\''+rootPath+'\');"/></a>');
	 $("#nickname").html('<a href="'+unattention.statuses_url+'" target="_blank" style="text-decoration:none"><p style="color:#FFFFFF;font-weight:bold;font-size:21px;margin-top:10px;">'+unattention.nickname+'</p></a>');
	 $("#attention_button").html("&nbsp;&nbsp;&nbsp;&nbsp;"+attention_button);
	 $("#gender").html('<h5><p style="color:#FFFFFF">'+sina_common_sex(unattention.gender)+'</p></h5>');
	 $("#location").html('<h5><p style="color:#FFFFFF">&nbsp;'+unattention.location+'</p></h5>');
	 $("#follow_url").html('<a href="'+unattention.follow_url+'" target="_blank">关注</a>');
	 $("#fans_url").html('<a href="'+unattention.fans_url+'" target="_blank">粉丝</a>');
	 $("#statuses_url").html('<a href="'+unattention.statuses_url+'" target="_blank">微博</a>');
	 $("#follow").html(unattention.follow);
	 $("#fans").html(unattention.fans);
	 $("#statuses").html(unattention.statuses); 
	 if(null!=unattention.person_info){
     	 $("#person_info").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+unattention.person_info); 
	 }
	 
	
	 pageCount=sina_weiboPage_count(rootPath,nameUid[1]);   //微博页数	
	 weibo_content_byPage(rootPath,nameUid[1],1);   //微博第一页详情
	 
	 weibo_page_style(pageCount); //分页样式     
	 
	 $("#loading").hide();
	 $("#body").show();
	 
	 $("#page_1").addClass("active");

});
</script> 
</body>
</html>