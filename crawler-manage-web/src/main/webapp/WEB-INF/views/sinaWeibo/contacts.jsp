<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>

<!DOCTYPE html">
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
     <script src="${ctx}/static/js/echarts/echarts.js"></script>
      
    <script src="${ctx}/static/js/sina-ajax.js"></script> 
    <script src="${ctx}/static/js/sina-common.js"></script> 
    <script src="${ctx}/static/js/sina-echarts.js"></script>
    

    <link rel='icon' href='${logo}' type=‘image/x-ico’ />    
    <title>${title}--新浪微博</title>
</head>
<body>
   <div class="scm-head"><%@ include file="../head.jsp" %></div>
   <div class="sina-left"><%@ include file="leftMenu.jsp" %></div> 
     
   <div id="body" class="sina-content" style="height:100%;display:none"> 
        <span id="person" style="font-size:150%;color:#4F94CD"></span>              
        <div id="two" style="height:90%"></div>                                 
   </div>

   
   <div id="loading" style="margin-left:45%;">
                 <br><br><br><br><br>
                 <!-- &nbsp;&nbsp;<img src="../static/img/loading.jpg" /> -->
                <br>
                                         正在构建人脉圈,请稍候......
   </div> 
   
   <script type="text/javascript">         
         var rootPath='<%=request.getContextPath()%>';
         var contactsPerson='<%=request.getAttribute("contactsPerson")%>';
         
         contactsPerson=contactsPerson.split(",");
         
         var uid=contactsPerson[0];         //人物的uid
         var isAttention=contactsPerson[1];//是否已关注，0为没有关注，1为关注
         
         var person_info;  //人物的基本信息
         var nickname;     //人物昵称
         var img_url;      //人物头像
         var one_contacts; //一度人脉数据
    	 var two_contacts; //二度人脉数据
         
  
                  
         //获取人物的昵称、头像地址
         function personInfo(){
        	 if(isAttention==0){
                alert("未关注");		 
        	 }else if(isAttention==1){
        		person_info=sina_attention_personalInfo(rootPath,uid);        		
        		nickname=person_info.screen_name;
        		img_url=person_info.profile_image_url;
        	 }
         }
           
         //main函数  
         $(function(){
        	              	 
        	 personInfo(); //获取人物基本信息
        	 $("#person").html('<strong>&nbsp;&nbsp;'+nickname+'的人脉圈</strong>');
        	
        	 require.config({
            	 paths:{
            		 echarts:'${ctx}/static/js/echarts'
            	 }           	 
             });
        	 
 	 
        	 one_contacts=sina_common_oneNetworkJson(rootPath,uid);//获取一度人脉数据
        	 if(null==one_contacts.friend){
        		 $("#loading").hide();
                 $("#body").show();
        		 $("#two").html('<br><br>用户您好，您所查询的<strong style="color:red">'+nickname+'</strong>的一、二度人脉指数均为0，无法构造人脉圈。请谅解！');
        		 return;
        	 }else{
        	     two_contacts=sina_common_twoNetworkJson(rootPath,one_contacts);//获取二度人脉数据
        	    
        	 }    
              
                 $("#loading").hide();
                 $("#body").show();
                 
        	 //人脉圈echarts显示
     		sina_echarts_twoContacts(rootPath,uid,nickname,img_url,one_contacts,two_contacts);
     
         }); 
         
    </script>
</body>
</html>