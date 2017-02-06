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
  <link href="${ctx}/static/css/cutImg.css" type="text/css" rel="stylesheet">
  
   <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
 <script src="${ctx}/static/js/bootstrap.min.js"></script>
 <script src="${ctx}/static/js/linkedIn/linkedIn-ajax.js"></script>
 <script src="${ctx}/static/js/linkedIn/linkedIn-common.js"></script>
 
 
<!--  <script type="text/javascript">
         var total = document.documentElement.clientHeight;
         $(function(){
        	 $("#backcolor").attr("style","background-color:#e9e9e9;margin-left:18%;height:"+total+"px");
        	 
         })
         
 
 </script> -->
<style type="text/css">

.title{
      font-size:16px;
      font-weight:bold;
}

.attrText{
      font-size:14px;
      color:grey;
}
.sonTitle{
      font-size:13px;
      font-weight:bold;
}






</style> 
 
 
 
 <link rel='icon' href='${logo}' type=‘image/x-ico’ />
 <title>${title}--LinkedIn</title>
</head>
<body>
<div class="scm-head"><%@ include file="../head.jsp" %></div>
<div class="sina-left"><%@ include file="leftMenu.jsp" %></div>

<div id="loading" style="margin-left:45%;">
      <br><br>
      <%@ include file="../loading.jsp"%>
</div>
<div id="backcolor" >
       
      <div id="content" style="display:none;width:60%;margin-left:25%">
     
            <!--显示基本信息  -->
            <div id="basicInfo" class="row">
            </div>
             
             <!-- 显示联系方式 -->
             <div id="contact" style="margin-top:2%;margin-bottom:3%">
                   
                   <!-- 具体的联系方式，如微信等 -->   
                   <div id="contactWay" style="background-color:#e9e9e9;display:none;" class="row">
                   </div>
                   
                   <div class="row" style="border-top:1px dashed grey">
                           
                           <!-- 人物linkedIn主页地址 -->
                         <div id="profile" class="col-md-10" style="background-color:#f6f6f6;height:35px;padding:7px;">
                         </div>
                         
                            <!-- 点击显示具体的联系方式 -->
                         <div id="contactAction" class="col-md-2" style="background-color:#e9e9e9;height:35px;padding:7px;text-align:center;cursor:pointer">
                               <img src="${ctx}/static/img/linkedIn_contactWay.png" style="position:absolute;clip:rect(94px 16px  140px  0px);margin-top:-94px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系方式
                         </div>
                         
                   </div> 
             </div>
     
            
            <div id="articles" class="row" ><!-- 发布文章 --></div>
            
            <div id="bg_summary" class="row" ><!-- 自我介绍 --></div>
            
            <div id="jobInfos" class="row"><!-- 工作经历 --></div>
            
            <div id="courses" class="row"><!-- 所学课程 --></div>
            
            <div id="testScores" class="row"> <!-- 测试成绩 --> </div>
            
            <div id="certis" class="row"><!-- 资格证书 --></div>
            
            <div id="patents" class="row"><!-- 专利发明 --></div>
            
            <div id="parti_orgs" class="row"><!-- 参与组织 --></div>
           
            <div id="languages" class="row"> <!-- 语言能力 --></div>
            
            <div id="skills" class="row"><!-- 技能 --></div>
            
            <div id="pubs" class="row"><!-- 出版作品 --></div>
            
            <div id="edus" class="row"><!-- 教育背景 --></div>
           
            <div id="volunteer" class="row"> <!-- 志愿者经历和公益话题 --></div>
            
            <div id="honorawards" class="row"><!-- 荣誉 --></div>
            
            <div id="endorsements" class="row"><!-- 推荐信 --></div>
            
            <div id="projects" class="row"><!-- 所做项目 --></div>
            
            <div id="other" class="row"><!-- 其他信息 --></div>
            
    </div>
    <div id="exception" style="color:red;width:60%;margin-left:25%"></div>
</div>



<script type="text/javascript">
        var rootPath='<%=request.getContextPath()%>';
        var profileURL='<%=request.getAttribute("profile")%>';
        
        $(function(){
        	    var details=linkedIn_getCrawlerDetails(rootPath,profileURL);
        	    //如果details返回null
        	    if(details.name==null){
        	    	 $("#exception").html("暂无法获取详情，请稍候再试！");
        	    	 $("#loading").hide();
        	    	 console.log("人物详情返回值为：NUll");
        	    	 return;
        	    }
        	    //如果details返回505
        	    //如果details返回error
        	    //如果details返回""
        	    
        	    $("#loading").hide();
        	    $("#content").show();
        	    
        	    //显示基本信息
        	          $("#basicInfo").html(linkedIn_common_basicInfo(rootPath,details));     
        	    
        	    //显示联系方式
        	          $("#profile").html('<img src="${ctx}/static/img/linkedIn_contactWay.png" style="position:absolute;clip:rect(250px 16px  290px  0px);margin-top:-245px">'
        	                                 +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
        	                                 +'<a href="'+details.profile+'" target="_blank">'
        	                                      +details.profile
        	                                 +'</a>');
        	          $("#contactWay").html(linkedIn_common_contactWay(rootPath,details));
        	          
        	          if(details.cont_info!=null){
        	                $("#contactAction").click(function(){
        	        	           if(details.cont_info!=null){
        	        	    	         if($("#contactWay").css("display")=="none"){
        	        	    	        	   $("#contactWay").show();
        	        	    	         }else{
        	        	    	        	   $("#contactWay").hide();
        	        	    	         }
        	        	           }
        	                })
        	          }
        	    //发表文章
        	           $("#articles").html(linkedIn_common_article(details));
        	    //自我简介
        	           $("#bg_summary").html(linkedIn_common_bgSummary(details));
        	    //工作经历
        	           $("#jobInfos").html(linkedIn_common_jobInfos(rootPath,details));
        	    //所学课程
        	           $("#courses").html(linkedIn_common_courses(details));
        	    //测试成绩
        	           $("#testScores").html(linkedIn_common_testScores(details));
        	    //资格证书
        	           $("#certis").html(linkedIn_common_certis(details));
        	    //专利发明
        	           $("#patents").html(linkedIn_common_patents(details));
        	    //参与组织
        	           $("#parti_orgs").html(linkedIn_common_partiOrgs(details));
        	    //语言能力
        	           $("#languages").html(linkedIn_common_languages(details));
        	    //擅长技能
        	           $("#skills").html(linkedIn_common_skills(details));
        	   //出版作品
        	           $("#pubs").html(linkedIn_common_pubs(rootPath,details));
               //教育背景
                       $("#edus").html(linkedIn_common_edus(details));
               //荣誉
        	           $("#honorawards").html(linkedIn_common_honorawards(details));
        	   //推荐信
        	           $("#endorsements").html(linkedIn_common_endorsements(rootPath,details));
               //志愿者经历和公益话题
                       $("#volunteer").html(linkedIn_common_volunteer(details));
               //所做项目
                       $("#projects").html(linkedIn_common_projects(rootPath,details));
               //其他信息
                       $("#other").html(linkedIn_common_other(details));
               
        })

</script>
</body>
</html>