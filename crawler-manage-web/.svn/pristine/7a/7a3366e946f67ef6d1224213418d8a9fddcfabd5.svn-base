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
 <script src="${ctx}/static/js/zhihu/zhihu-ajax.js"></script>
 <script src="${ctx}/static/js/zhihu/zhihu-common.js"></script> 

<link rel='icon' href='${logo}' type=‘image/x-ico’ /> 
<title>${title}--知乎</title>
</head>
<body>
<body>
  
    <div class="scm-head"><%@ include file="../head.jsp" %></div>
    <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
    <div style="margin-top:5%;margin-left:30%;margin-bottom:3%">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/zhihu/crawler_search" method="post">             
                <input type="text" id="crawler_search" name="crawlerPerson" class="scm-text " placeholder="输入关注人物......">&nbsp;&nbsp;
                <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;&nbsp;</button>           
         </form>
    </div> 
    
     <div id="loading" style="margin-left:45%"><br><%@ include file="../loading.jsp" %></div>  
    <div id="body"  style="display:none;width:75%;margin-left:20%;">        
         <h4 class="sub-header" id="id-h4-1"> </h4>
         <table class="table  table-striped">
             <thead>
                <tr> 
                    <th class="sina-td-60px">序号</th>              
                    <th class="sina-td-130px ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户呢称</th>
                    <th class="sina-td-80px ">用户头像</th>
                    <th class="sina-td-60px">性别</th>
                    <th class="sina-td-60px ">&nbsp;回答数</th>
                    <th class="sina-td-60px ">&nbsp;文章数</th>
                    <th class="sina-td-60px">&nbsp;关注者</th> 
                    <th class="sina-td-250px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户简介</th>          
                    <th class="sina-td-150px ">操作</th>
              </tr>
           </thead>
           <tbody id="tbody">                                           
           </tbody>
       </table> 
        
       <div id="exception"></div>    
  </div>
  
  
  
  
<script type="text/javascript">
      var person;    //爬取到的全部人
     
      
      var rootPath='<%=request.getContextPath()%>';
      var crawlerPerson='<%=request.getAttribute("crawlerPerson")%>';
      
      
      var askAnswer_button='<button type="button" class="btn btn-primary sina-td-110px" onClick="detail(this);">'
                            +'<i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;详情</button>';

                            
function detail(button){
	var index=$(button).parent().attr("id");
	var url=person[index].profile;
	location.href=encodeURI(encodeURI(rootPath+"/zhihu/detail?url="+url+"&crawlerPerson="+crawlerPerson));
	
}                            
                            
                        
$(function(){
	 
      var row='';
      var person_info='';
      var gender='';
      var button_style='';
 	  
 	  
 	  $("#crawler_search").val(crawlerPerson);
 	  	  
 	  person=zhihu_crawlerPersons(rootPath,crawlerPerson);//获取人物列表
 	  
 	  $("#loading").hide();
 	  $("#body").show();
 	  
 	  
 	  if(person.indexOf("系统内部错误")!=-1){
 		  $("#exception").html('<br><strong style="color:red">500 - 系统内部发生错误。</strong>');
 		  return;
 	  }
 	  
 	  
 	  button_style=askAnswer_button;
 	  
 	  
  	  for(var i=0;i<person.length;i++){  		  
	          person_info=person[i].bio;
	          if(null==person_info){
	        	  person_info="无";
	          }
	         
	          row +='<tr>'
		               +'<td>'+(i+1)+'</td>'
		               +'<td><a href="'+person[i].profile+'"  target="_blank">'+person[i].name+'</a></td>'
		               +'<td><img src="'+person[i].profile_image+'" class="sina-img-50px" onerror="zhihu_common_imgNoFind(\''+rootPath+'\');" /></td>'
		               +'<td>'+zhihu_common_sex(person[i].gender)+'</td>'
		               +'<td>'+person[i].answers_num+'</td>'
		               +'<td>'+person[i].posts_num+'</td>'
		               +'<td>'+person[i].followers_num+'</td>'
		               +'<td>'+person_info+'</td>'
		               +'<td id="'+i+'">'+button_style+'</td>'
		               +'</tr>'; 		       
	    } 
  	    $("#id-h4-1").html("共查找到相关人物数（位）: &nbsp;"+person.length);
	    $("#tbody").html(row);
    });

 </script>
  
</body>
</html>