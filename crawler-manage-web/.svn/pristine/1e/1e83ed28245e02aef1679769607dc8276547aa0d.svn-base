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
 <script src="${ctx}/static/js/linkedIn/linkedIn-ajax.js"></script>
 <%-- <script src="${ctx}/static/js/linkedIn/linkedIn-common.js"></script>  --%>
 
 <link rel='icon' href='${logo}' type=‘image/x-ico’ />
 <title>${title}--LinkedIn</title>
</head>
<body>

 <div class="scm-head"><%@ include file="../head.jsp" %></div>
 <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
 <div style="margin-top:5%;margin-left:30%;margin-bottom:3%">
         <form  class="navbar-form" action="${pageContext.request.contextPath}/linkedIn/crawlerSearch" method="post">             
              <input type="text" id="crawlerPerson" name="crawlerPerson" class="scm-text " placeholder="输入关注人物......" value="<%=request.getAttribute("crawlerPerson")%>">&nbsp;&nbsp;
              <button type="submit" class="btn btn-primary sina-td-20px" >&nbsp;&nbsp;搜索&nbsp;</button>           
         </form>    
 </div>
 
 
<div id="loading" style="margin-left:45%;">
      <br><br>
      <%@ include file="../loading.jsp"%>
</div>

<div style="width:75%;margin-left:20%;">
         <div id="body" style="display:none">
              <h4 class="sub-header" id="id-h4-1"> </h4>
              <div id="crawlerResult" style="width:90%">
              </div>
         </div>
         <div id="exception" style="color:red">
         </div>
</div>




<script type="text/javascript">
        
        var rootPath='<%=request.getContextPath()%>';
        var crawlerPerson_list;
        
        $(function(){
        	  
        	  var crawlerPerson='<%=request.getAttribute("crawlerPerson")%>';
        	  $("#crawlerPerson").val(crawlerPerson);
        	  
        	  
        	  crawlerPerson_list=linkedIn_getCrawlerPersons(rootPath,crawlerPerson);
     	 
        	  
        	  $("#loading").hide();
        	  $("#body").show();
        	
        	  
        	  if(typeof(crawlerPerson_list)=="string"){
      		        if(crawlerPerson_list.indexOf("系统内部错误")!=-1){
      		            $("#exception").html("500 - 系统发生内部错误。");  
      		          return;
      		        }
      	       }
        	  
        	  
        	
        	  $("#id-h4-1").html("共查找到相关人物数（位）: &nbsp;"+crawlerPerson_list.length);
        	  
        	  var row='';
        	  var profile_image='';
        	  var local_indus='';
        	  var headline='';
        	  var headline_array='';
        	  var cur_positions='';
        	  var pre_positions='';
        	  for(var i=0;i<crawlerPerson_list.length;i++){
        		     
        		          //头像处理
                          if(crawlerPerson_list[i].profile_img==null){
                        	     profile_image=rootPath+"/static/img/linkedIn.ico";
                          }else{
                        	     profile_image=crawlerPerson_list[i].profile_img
                          }        		  
        		  
        		         
        		            if(crawlerPerson_list[i].location==null && crawlerPerson_list[i].industry==null){
        		            	     local_indus='';   
        		            }else if(crawlerPerson_list[i].location==null && crawlerPerson_list[i].industry!=null){
        		            	     local_indus=crawlerPerson_list[i].location;
        		            }else if(crawlerPerson_list[i].location!=null && crawlerPerson_list[i].industry==null){
        		            	     local_indus=crawlerPerson_list[i].industry;
        		            }else if(crawlerPerson_list[i].location!=null && crawlerPerson_list[i].industry!=null){
        		            	     local_indus=crawlerPerson_list[i].location+"&nbsp;·&nbsp;"+crawlerPerson_list[i].industry;
        		            }
        		            
        		            
        		  
        		            //职业头衔
        		            if(crawlerPerson_list[i].headline==null){
        		            	   headline="";
        		            }else{
        		            	   headline="";
        		            	   headline_array=crawlerPerson_list[i].headline.split(" ");
        		            	   for(var j=0;j<headline_array.length;j++){
        		            		      headline +=headline_array[j]+" ";
        		            		      if((j+1)%8==0){
        		            		    	  headline +='<br>';
        		            		      }
        		            	   }
        		            }
        		            
        		            //就职
        		            if(crawlerPerson_list[i].cur_positions==null || crawlerPerson_list[i].cur_positions.length<1 ){
        		            	   cur_positions="";
        		            }else{
        		            	
        		            	   var cur='';
        		            	   for(var j=0;j<crawlerPerson_list[i].cur_positions.length;j++){
        		            		           cur +=crawlerPerson_list[i].cur_positions[j]+"<br>";
        		            	   }
                                      		            	
        		            	   
        		            	   cur_positions='<div class="row">'
        		            	                       +'<div class="col-md-2" style="color:grey">'
        		            	                              +'<span style="margin-top:5%;margin-left:35%">目前就职：</span>'
        		            	                       +'</div>'
        		            	                       +'<div class="col-md-10">'
        		            	                              +'<p >'+cur+'</p>'
        		            	                       +'</div>'
        		            	                 +'</div>';
        		            }
        		            
                            if(crawlerPerson_list[i].pre_positions==null || crawlerPerson_list[i].pre_positions.length<1 ){
                            	   pre_positions="";
                            }else{
                            	
                            	   var pre='';
                            	   for(var j=0;j<crawlerPerson_list[i].pre_positions.length;j++){
                            		        pre +=crawlerPerson_list[i].pre_positions[j]+"<br>";
                            	   }
                            	   
                            	   pre_positions='<div class="row">'
            	                                       +'<div class="col-md-2" style="color:grey">'
     	                                                      +'<span style="margin-left:35%">曾经就职：</span>'
     	                                               +'</div>'
     	                                               +'<div class="col-md-10">'
     	                                                      +'<p>'+pre+'</p>'
     	                                               +'</div>'
     	                                         +'</div>';
                            }
        		            
                            row +='<div id="person_'+i+'" class="panel panel-default">'
                                        +'<div class="panel-body">'
                                               +'<div class="row">'
                                                      +'<div class="col-md-2">'
                                                             +'<img src="'+profile_image+'" class="img-circle" style="width:80px;margin-left:25%" onerror="linkedIn_common_imgNoFind('+rootPath+')">'
                                                      +'</div>'
                                                      +'<div class="col-md-8">'
                                                             +'<div style="margin-left:1%">'
                                                                    +'<a href="'+crawlerPerson_list[i].profile+'" target="_blank"><strong style="font-size:16px;font-weight:bold;color:#4682B4">'+crawlerPerson_list[i].name+'</strong></a><br>'
                                                                    +'<strong style="font-size:15px">'+headline+'</strong><br>'
                                                                    +'<small style="color:grey">'+local_indus+'</small>'
                                                             +'</div>'
                                                      +'</div>'
                                                      +'<div class="col-md-2">'
                                                             +'<button type="button" class="btn btn-primary" onclick="details(this)" style="width:75%;text-align:center"><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;详情</button>'
                                                      +'</div>'
                                               +'</div>'
                                               +cur_positions
                                               +pre_positions
                                        +'</div>'
                                  +'</div>';
        	  }
        	
        	    $("#crawlerResult").html(row);
        })




function details(button){
         var id=$(button).parent().parent().parent().parent().attr("id");
         var index=id.split("_")[1];
         var profile=encodeURIComponent(crawlerPerson_list[index].profile);
         location.href=rootPath+"/linkedIn/details?profile="+profile;
}
        
        
        
        
        
</script>


</body>
</html>