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

   <!-- <link href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"> -->
   <!-- <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> -->
   <!--  <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>  -->
 
 
  <link rel='icon' href='${logo}' type=‘image/x-ico’ />
  <title>${title}--新浪微博</title>
 
</head>
<body>
   
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div id="loading" style="margin-left:45%;margin-top:10%"><%@ include file="../loading.jsp" %></div>
    <div  id="body" style="display:none">      
          <div class="row" style="width:80%;position:fixed;margin-left:19%;margin-top:-1%;background-color:#fff;z-index:1;">
               <div class="col-md-4" style="margin-top:1%;margin-top:2%;">
                   <span id="attention_count" style="font-size:20px"> </span>
               </div>
               <div class="col-md-5 col-md-offset-3" style="margin-top:1%">
                   <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/sinaWeibo/attentionInfo_search" method="post">
                         <input name="attention_searchPerson" id="attention_search" type="text" class="form-control" placeholder="输入用户昵称......">&nbsp;&nbsp;
                         <button type="submit" class="btn btn-primary">搜索</button>&nbsp;&nbsp;&nbsp;
                   </form>
              </div>        
          </div>
         <br><br><br><br>
         <div  style="width:80%;margin-left:19%;z-index:-1">       
              <table class="table table-striped">
                     <thead>
                            <tr>
                                <th class="sina-td-60px">序号</th>
                                <th class="sina-td-130px ">用户呢称</th>              
                                <th class="sina-td-100px ">用户头像</th>
                                <th class="sina-td-100px">性别</th>
                                <th class="sina-td-200px">所在地</th>              
                                <th class="sina-td-100px ">粉丝数</th>
                                <th class="sina-td-100px ">关注数</th>
                                <th class="sina-td-100px ">微博数</th>
                                <th class="sina-td-300px ">用户创建时间</th>
                                <th class="sina-td-200px ">操作</th>
                           </tr>
                    </thead>
                    <tbody id="tbody">               
                    </tbody>
              </table>     
          </div>
   </div>
   <br><br>
  
  
  
  
  
  <script type="text/javascript">
     
        var attentionPersons;  //人物列表(json对象)
        var rootPath='<%=request.getContextPath()%>';
       
         var details_button='<div class="btn-group">'
                                  +'<button type="button" class="btn btn-primary" onClick="furtherInfo(this);" ><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;详情</button>'
                                  +'<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">'
                                           +'<span class="caret"></span>'
                                           +'<span class="sr-only">是否关注</span>'
                                  +'</button>'
                                  +'<ul class="dropdown-menu" role="menu">'
                                          +'<li><a href="#"  onClick="contacts(this);"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Ta的人脉圈 </a></li>'
                                          +'<li><a href="#"  onClick="delete2(this);"><i class="glyphicon glyphicon-minus"></i>&nbsp;&nbsp;取消关注</a></li>'
                                  +'</ul>'
                          +'</div>'; 
       var attention_button='<button type="button" class="btn btn-warning sina-td-85px"  onClick="save2(this);">'
                          +'<i class="glyphicon glyphicon-plus"></i>&nbsp;关注</button>';


      //打印人物列表
      $(function(){
    	   var row='';
    	   var disable=0;
           var num=0;

            
           attentionPersons=sina_person_list(rootPath);  //读取userFeed表中人物
           
           $("#loading").hide();
           $("#body").show();
           
           if("error"==attentionPersons){
        	   sina_common_deleteFromSina();
        	   
           }
           
          
           
          for(var i=0;i<attentionPersons.length;i++){
        	  if(attentionPersons[i].enable==0){
        		  continue;
        	  }else if(attentionPersons[i].enable==1){
        		 
        	      
        		  num+=1;
        	      row +='<tr>'
        	               +'<td>'+num+'</td>'
        	               +'<td>'+'<a href="'+attentionPersons[i].statuses_url+'" target="_blank">'+attentionPersons[i].screen_name+'</a></td>'
        	               +'<td><a href="'+attentionPersons[i].statuses_url+'" target="_blank"><img src="'+attentionPersons[i].profile_image_url+'" class="sina-td-50px center-block" onerror="sina_common_smallImgNoFound(\''+rootPath+'\');" /></a></td>'
        	               +'<td>&nbsp;&nbsp;'+sina_common_sex(attentionPersons[i].gender)+'</td>'
        	               +'<td>'+attentionPersons[i].location+'</td>'
        	               +'<td>'+toWan(attentionPersons[i].followers_count)+'</td>'
        	               +'<td>'+toWan(attentionPersons[i].friends_count)+'</td>'
        	               +'<td>'+toWan(attentionPersons[i].statuses_count)+'</td>'       	               
        	               +'<td>'+sina_common_date(attentionPersons[i].created_at)+'</td>'
        	               +'<td id="'+i+'">'+details_button+'</td>' 
        	            +'</tr>';  
        	      
        	  }        	         
          }
          
          $("#attention_count").html("已关注人物数（位）:  "+num);
          $("#tbody").html(row); 
  	  
    });
      
   //跳转至选中人物的帖子详情页   
    function furtherInfo(button){
    	var index=$(button).parent().parent().attr("id");   	
    	var uid=attentionPersons[index].uid;
    	location.href=rootPath+'/sinaWeibo/postsAttention?uid='+uid;    	
    }
    
   //取消关注
    function delete2(button){
 	   var index=$(button).parent().parent().parent().parent().attr("id");
 	   var attention_delete=attentionPersons[index].uid;
 	   var delete2_result=sina_destroy_friendships(rootPath,attention_delete);
 	   if("deleted-success"==delete2_result){
 		  $("#"+index).html(attention_button);
 	   }else if("deleted-failed"==delete2_result){
 		  sina_common_deleteFromSina();
 	   }else if("error"==delete2_result){
 		  sina_common_dataBaseError();
 	   }	  
   }
   
  
   
    
   //加关注 
   function save2(button){
	   var index = $(button).parent().attr("id");     	     	  
	   var attentionInfo=encodeURI(encodeURI(JSON.stringify(attentionPersons[index])));/*对于中文，需要两次 encodeURI  */
	   
	   var save2_result=sina_create_friendships(rootPath, attentionInfo);
	   if("saved-success"==save2_result){
		   $("#"+index).html(details_button);
	   }else if("saved-failed"==save2_result){
		   sina_common_addToSina();
	   }else if("error"==save2_result){
		   sina_common_dataBaseError();
	   }   
   } 
    
   
  //人脉圈
  function contacts(a){
	  var index=$(a).parent().parent().parent().parent().attr("id");
	  var contacts_uid=attentionPersons[index].uid;
	  var contacts_screen_name=attentionPersons[index].screen_name;
	  location.href=rootPath+'/sinaWeibo/contacts?uid='+contacts_uid+'&isAttention=1';
	  
  }

</script>
  
</body>
</html>