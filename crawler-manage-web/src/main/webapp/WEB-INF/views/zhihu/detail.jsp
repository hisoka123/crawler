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
      <div class="scm-head"><%@ include file="../head.jsp" %></div>
      <div class="sina-left"><%@ include file="leftMenu.jsp" %></div>
      <div id="loading" style="margin-left:45%;margin-top:10%"><%@ include file="../loading.jsp" %></div>
      <div id="all_content"  style="display:none;">
            <div style="width:25%;float:left;" >
                 <div style="background-image:url(${ctx}/static/img/background-image/3.jpg);width:98%;height:100%;text-align:center">             
                      <div  class="row">
                          <div  id="profile_image" style="margin-top:2%"></div>
                      </div>
                      <div class="row" >
                          <div  id="name" style="margin-top:2%;"></div>    
                      </div>
                      <div class="row">
                          <div style="color:#FFF;font-weight:bold">
                                 <span id="location"></span>
                                 <span id="gender"></span>
                          </div> 
                     </div>
                     <div class=row>
                         <div id="bio" style="margin-top:2%;margin-left:3%;margin-right:3%"></div>
                     </div><br>                
                 </div>
                 <br>
                 
                 <div class="row" style="background:#FFFAFA">
                      <div class="col-md-3 col-md-offset-1" style="margin-top:2%">
                           <div  class="sina-text-center" id="attention_num" >0</div>
                           <div class="sina-text-center" id="attention" style="font-size:14px;font-weight:bold;margin-top:7%">关注人</div>
                      </div> 
                      <div class="col-md-1 col-md-offset-1">
                           <div style="height:10px;"></div>                                  
                           <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                      </div>
                      <div class="col-md-3 col-md-offset-1" style="margin-top:2%">
                           <div class="sina-text-center" id="followers_num">0</div>
                           <div class="sina-text-center" id="followeres" style="font-size:14px;font-weight:bold;margin-top:7%">关注者</div>
                      </div>
                  </div>
                  <br>
                  
                 <div class="row" style="background:#FFFAFA">
                      <!-- <div class="col-md-2" style="margin-top:5%">获得</div> -->
                      <div class="col-md-3 col-md-offset-1" style="margin-top:2%">
                           <div  class="sina-text-center" id="agree_num" style="font-size:14px;">0</div>
                           <div class="sina-text-center" id="agree" style="font-size:14px;font-weight:bold;margin-top:7%"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;赞同</div>
                      </div> 
                      <div class="col-md-1 col-md-offset-1 ">
                           <div style="height:10px;"></div>                                  
                           <div style="border:1px solid #CCCCFF;width:1px;height:40px;"> </div>
                      </div>
                      <div class="col-md-3 col-md-offset-1" style="margin-top:2%" >
                           <div  id="thank_num" style="font-size:14px;text-align:center">0</div>
                           <div  id="thank" style="font-size:14px;font-weight:bold;margin-top:7%;text-align:center"><i class="glyphicon glyphicon-heart" style="color:red"></i>&nbsp;感谢</div>
                      </div>
                  </div>
                  <br>
                  
                  <div id="skill">
                       
                  </div>
                  <br>
                  <div style="background:#FFFAFA">
                       <h4>简介</h4>
                       <div  id="description">&nbsp;&nbsp;&nbsp;&nbsp;无</div>                      
                  </div>                    
         </div>          
         <div id="content" style="width:53%;float:right;margin-right:3%">
              <div> 
                   <ul class="nav nav-tabs navbar-right">
                       <li class="active" ><a href="#action_tab" data-toggle="tab" id="tab_1">最新动态(0)</a></li>
                       <li><a href="#ask_tab" data-toggle="tab" id="tab_2">提问(0)</a></li>
                       <li><a href="#answer_tab" data-toggle="tab" id="tab_3">回答(0)</a></li>
                   </ul>
              </div>
              <div class="tab-content" style="margin-top:10%">
                   <div class="tab-pane fade in active" id="action_tab">这个人好懒哦，最近没有任何动态。</div>
                   <div class="tab-pane fade" id="ask_tab">这个人暂时没有任何提问。</div>
                   <div class="tab-pane fade" id="answer_tab">这个人还没有回答过任何问题。</div>
              </div>                    
         </div> 
    </div>
    
    
    
    
    
<script type="text/javascript">
    var rootPath='<%=request.getContextPath()%>';
    var detail_content;
    
    
    $(function(){
            var url_person='<%=request.getAttribute("url_person")%>';
            url_person=url_person.split("?");
            var url=url_person[0];
            var crawlerPerson=url_person[1];
 
            var location;
            var detail_content_2;
            
 
            detail_content=zhihu_getDetailContent(rootPath,url);   //获取最新动态
            //如果detail_content返回的内容为null，那么重新获取人物列表，并从人物列表中再次获取该人物的基本信息。
            if(detail_content==''){
            	var crawlerPerson_list=zhihu_crawlerPersons(rootPath,crawlerPerson);
            	for(var i=0;i<crawlerPerson_list.length;i++){            		   
            		    if(url==crawlerPerson_list[i].profile){
            		    	  detail_content=crawlerPerson_list[i];
            		    	  break;
            		    }
            	}
            }
 
            
            /*
             *打印基本信息
             */
            $("#profile_image").html('<a href="'+detail_content.profile+'" target="_blank"><img src="'+detail_content.profile_image+'" class="sina-td-110px img-circle" onerror="zhihu_common_bigNoFind(\''+rootPath+'\');"></a>');
            $("#name").html('<a href="'+detail_content.profile+'" target="_blank" style="text-decoration:none;color:#FFFFFF;font-weight:bold;font-size:21px;">'+detail_content.name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            
            
            //地址
            location=detail_content.location;           
            if(location==null){
                $("#location").html("");	
            }else{
                $("#location").html('<i class="glyphicon glyphicon-map-marker" style="color:black"></i>&nbsp;'+location+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            
            //性别
            if(detail_content.gender==null){
            	$("#gender").html("");
            }else{
                $("#gender").html(zhihu_common_sex(detail_content.gender));
            }
            
            
            if(detail_content.bio!=null){
                 $("#bio").html('<small style="text-align:center">'+detail_content.bio+'</small>');
            }
            if(detail_content.agree_num!=null){
    	         $("#agree_num").html(detail_content.agree_num);
            }
            if(detail_content.thank_num!=null){
    	         $("#thank_num").html(detail_content.thank_num);
            }
            if(detail_content.attention_num!=null){
    	         $("#attention_num").html(detail_content.attention_num);
            }
            if(detail_content.followers_num!=null){
    	         $("#followers_num").html(detail_content.followers_num);
            }
            
            
            if(detail_content.skill_topics!=null){
    	             var skill_content='<div class="row">';
    	             for(var i=0;i<detail_content.skill_topics.length;i++){
    	    	             if(i%3==0){
    	    	                   skill_content +='<div class="col-md-3">'
    	    	             }else{
    	    		               skill_content +='<div class="col-md-3 col-md-offset-1">'
    	    	             }
    	    	  
    	    	             skill_content +='<div><a href="'+detail_content.skill_topics[i].link+'" target="_blank"><img src="'+detail_content.skill_topics[i].avatar+'" class="img-circle" style="width:60px;" onerror="zhihu_common_imgNoFind(\''+rootPath+'\')"></a></div>'
    	    	                                   +'<div style="font-size:13px;text-align:center">'+detail_content.skill_topics[i].name+'</div>'
    	    	                             +'</div>';
    	    	    	    	 
    	    	             if((i+1)%3==0){
    	    		                skill_content +='</div>';
    	    		                if((i+1)<detail_content.skill_topics.length){
   	    			                      skill_content +='<div class="row" style="margin-top:2%">';
   	    		                    } 
    	    	             }
      	    	  
    	    	             if((i+1)==detail_content.skill_topics.length){
    	    		               if((i+1)%3!=0){
    	    			                skill_content +='</div>';
    	    		               }
    	    	             }
    	              }
    	    
    	            $("#skill").html("<strong>擅长话题</strong><br><br>"+skill_content);
            }
    	    
            if(detail_content.person_info!=null){
    	         $("#description").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+detail_content.person_info);
            }
            
            
    	    /*
    	     *建立标签页
    	     */
    	    if(detail_content.actions!=null){
    	         $("#tab_1").html("最新动态("+detail_content.actions.length+")");
    	    }
    	    if(detail_content.ask_num!=null){
    	    	$("#tab_2").html("提问("+detail_content.ask_num+")");
    	    }
    	    if(detail_content.answers_num!=null){
    	        $("#tab_3").html("回答("+detail_content.answers_num+")");
    	    }
    	    
    	    
    	    /*
    	     *标签页内容
    	     */
    	     if(detail_content.actions!=null){
    	         $("#action_tab").html(zhihu_common_actions(detail_content));  //显示最新动态的内容
    	     }
    	     //获取及显示提问内容
    	     $("#ask_tab").html(zhihu_common_asks(rootPath,url,detail_content.ask_num));
    	     //获取及显示回答内容
    	     $("#answer_tab").html(zhihu_common_answer(rootPath,url,detail_content.answers_num));
    	     
    	         	     
    	     $("#content").find("img").attr({width:'121',height:'75'});   	     
    	     $("#content").find("img").attr('onerror','zhihu_common_replace(\''+rootPath+'\')');
    	     
             
    	     $("#loading").hide();
    	     $("#all_content").show();
    })




</script>
    
</body>
</html>