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
 <script src="${ctx}/static/js/sina-ajax.js"></script>
 <script src="${ctx}/static/js/sina-common.js"></script> 
 
 <link rel='icon' href='${logo}' type=‘image/x-ico’ />
 <title>${title}--新浪微博</title>
 
</head>
<body>
<body> 
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div style="margin-top:5%;margin-left:30%;margin-bottom:3%">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/sinaWeibo/crawler_search" method="post">             
                <input type="text" id="crawler_search" name="crawlerPerson" class="scm-text" placeholder="输入关注人物......">&nbsp;&nbsp;
                <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button>           
         </form>
   </div>    
   
   
    <div id="loading" style="margin-left:45%">
                <br><br>
                 <%@ include file="../loading.jsp" %>
    </div>
   
    <div style="width:75%;margin-left:20%;">
         <div id="body" style="display:none">
              <h4 class="sub-header" id="id-h4-1"> </h4>
              <table class="table  table-striped">
                     <thead>
                            <tr> 
                                <th class="sina-td-60px">序号</th>              
                                <th class="sina-td-130px ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户呢称</th>
                                <th class="sina-td-80px ">用户头像</th>
                                <th class="sina-td-60px ">关注数</th>
                                <th class="sina-td-80px ">粉丝数</th>
                                <th class="sina-td-250px ">用户简介</th>           
                                <th class="sina-td-150px ">操作</th>
                           </tr>
                     </thead>
                     <tbody id="tbody">     
                     </tbody>
               </table> 
         </div>
     </div>
    
    
    
    <script type="text/javascript">
      var person;    //爬取到的全部人
      var allUid;    //已关注人物的uid
      
      var rootPath='<%=request.getContextPath()%>';
      
      var already_attention='<div class="btn-group">'
                                  +'<button type="button" class="btn btn-primary" style="cursor:default;"><i class="glyphicon glyphicon-ok"></i>&nbsp;已关注</button>'
                                  +'<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true">'
                                        +'<span class="caret"></span>'
                                        +'<span class="sr-only">是否关注</span>' 
                                  +'</button>'
                                  +'<ul class="dropdown-menu" role="menu">'
                                          +'<li><a href="#" onClick="details_2(this);"><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;微博详情</a></li>'
                                          +'<li><a href="#"  onClick="contacts(this);"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Ta的人脉圈 </a></li>'
                                          +'<li><a href="#" onClick="delete1(this);"><i class="glyphicon glyphicon-minus"></i>&nbsp&nbsp;;取消关注</a></li>'
                                  +'</ul>'
                            +'</div>';
     var attention='<div class="btn-group">'
                        +'<button type="button" class="btn btn-warning" onClick="save(this);"><i class="glyphicon glyphicon-plus"></i>&nbsp;关注</button>'
                        +'<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="true">'
                               +'<span class="caret"></span>'
                               +'<span class="sr-only">是否关注</span>' 
                        +'</button>'
                        +'<ul class="dropdown-menu" role="menu">'
                              +'<li><a href="#" onClick="details_1(this);"><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;微博详情</a></li>'
                        +'</ul>'
                   +'</div>';
      
$(function(){
      var row='';
  	  var button_content;	  
 	  var crawlerPerson='<%=request.getAttribute("crawlerPerson")%>';
 	  
 	  $("#crawler_search").val(crawlerPerson);
 	   	  
 	  
 	  person=sina_crawlerPersons(rootPath,crawlerPerson);//爬取相关人物 	
  	  allUid=sina_AllFriendships_uid(rootPath); //从数据库中取出全部已关注的uid
  	  
  	  $("#loading").hide();
  	  $("#body").show();
  	  
  	  if("error"==allUid){
  		sina_common_dataBaseError();
  	      return;
  	  }else{
  		  allUid=allUid.split(",");//将字符串化为数组
  	  }
 	   
  	  
  	  for(var i=0;i<person.length;i++){    	    	   	
	         button_content=attention;     		    
	    	 for(var j=0;j<allUid.length;j++){
	    	        if(person[i].uid==allUid[j]){
	    		          button_content=already_attention;    
	    		          break;
	    	         }
	          }
	          var person_info=person[i].person_info;
	          if(null==person_info){
	        	  person_info="N/A";
	          }
	          row +='<tr>'
		               +'<td>'+(i+1)+'</td>'
		               +'<td><a href="'+person[i].profile+'"  target="_blank">'+person[i].nickname+'</a></td>'
		               +'<td><img src="'+person[i].profile_image+'" class="sina-img-50px"  onerror="sina_common_smallImgNoFound(\''+rootPath+'\');"/></td>'
		               +'<td>'+person[i].follow+'</td>'
		               +'<td>'+person[i].fans+'</td>'
		               +'<td>'+person_info+'</td>'
		               +'<td id="'+i+'">'+button_content+'</td>'
		               +'</tr>'; 		       
	    } 
  	    $("#id-h4-1").html("共查找到相关人物数（位）: &nbsp;"+person.length);
	    $("#tbody").html(row);
    });


//加关注
function save(button){
   	       
   	       var index=$(button).parent().parent().attr("id");
   	       var crawlerInfo=encodeURI(encodeURI(JSON.stringify(person[index])));/*对于中文，需要两次 encodeURI  */
   	       var save_result=sina_crawlerCreate_friendships(rootPath, crawlerInfo);
   	       
   	       if("saved-success"==save_result){
   	    	 $("#"+index).html(already_attention);
   	       }else if("saved-failed"==save_result){
   	    	    sina_common_addToSina();
   	       }else if("error"==save_result){
   	    	    sina_common_dataBaseError();   	       }   	       	 
    }
    
//取消关注    
function delete1(button){
 	   var index=$(button).parent().parent().parent().parent().attr("id");
 	   var crawler_delete=person[index].uid;
 	   var delete_result=sina_destroy_friendships(rootPath, crawler_delete);
 	   
 	  if("deleted-success"==delete_result){
 		     $("#"+index).html(attention);
 		}else if("deleted-failed"==delete_result){
 			 sina_common_deleteFromSina();
 		}else if("error"==delete_result){
 			 sina_common_dataBaseError(); 		}
 	  
}
 

//跳转至还未关注人物的帖子详情页   
function details_1(button){
	var index=$(button).parent().parent().parent().parent().attr("id");
	location.href=encodeURI(encodeURI(rootPath+'/sinaWeibo/unattentionPosts?uid='+person[index].uid+"&nickname="+person[index].nickname));
}
 
//微博详情
function details_2(button){
	var index=$(button).parent().parent().parent().parent().attr("id");
	location.href=rootPath+'/sinaWeibo/postsAttention?uid='+person[index].uid; 
	
}

//人脉圈
function contacts(a){
	  var index=$(a).parent().parent().parent().parent().attr("id");
	  var contacts_uid=person[index].uid;
	  location.href=rootPath+'/sinaWeibo/contacts?uid='+contacts_uid+'&isAttention=1';
	  
}
 
</script>
</body>
</html>