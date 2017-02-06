$(function(){
	
	//搜索框样式
	cncaCommon_searchBoxStyle();
	
	//搜索框聚焦
	$("#searchBox_big_content").focus(function(){
		$("#info >div").text("");
		$("#info").hide();
	})
	
	
	//判断环境
	var env=$("#env").val().trim();
	
	if(env=="prod"){

		  $("#chooseDataSource").remove();
		  $("#searchBox_big_btn").on("click",function(){
			  cncaCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 cncaCommon_chooseSearchVersion()
		 
    }
	
})
//搜索框样式
function cncaCommon_searchBoxStyle(){
	
	
	
	//修正搜索框
    $("#searchBox_big >div[class='input-group'] >#searchBox_big_content").before("<div class='input-group-btn'>"
    		                                                                           +"<ul id='chooseType' class='dropdown-menu' style='font-size:14px;padding-left:5px;padding-right:5px'>"
    		                                                                                 +"<li data-name='name' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>获证组织名称</li>"
    		                                                                           +"</ul>"
    		                                                                     +"</div>");
	
	$("#searchBox_big #chooseType").on("click","li",function(){
		
		       searchBoxPlaceholder("cnca");
	})

	$("#searchBox_big #chooseType li[data-name='name']").click();
	
}
//选择搜索版本
function cncaCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 cncaCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 cncaCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function cncaCommon_interfaceSearch(){
	
	     var ctx='',type='',content='',searchResult='',queryType='';
	     
	     ctx=$("#ctx").val();
	     queryType=$("#searchBox_big #type").text().trim();
	     content=$("#searchBox_big_content").val().trim();
	     
	     if(content==''){
	    	    alert("搜索内容不能为空");
	    	    return;
	     } else if(content.length < 6) {
	    	    alert("搜索内容不得小于6个字符");
	    	    return;
	     }
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=cncaAjax_getDetailFromInterface(ctx,false,queryType,content);
     
	    	    cncaCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function cncaCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="cncaDetail";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   cncaAjax_windowOpenPostDetail(ctx+"/modules/cncaController/cncaDetailByInterface2",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 cncaAjax_windowOpenPostInterface(ctx+"/modules/cncaController/cncaJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
                 },1000);
          
         }else{
   	     
   	           if(JSON.parse(searchResult).error.errorCode==7){//无关键词
   	 
                      alert(JSON.parse(searchResult).error.errorName);
          
               }else{
                      if(searchResult.indexOf("errorName")!=-1 && /[\u4e00-\u9fa5]/.test(searchResult)){
	                          alert("请求无效，可能原因：  "+JSON.parse(searchResult).error.errorName);
                      }else{
	                          alert("服务器繁忙，请重试！");
                      }
               }
         
               //调试时用
               setTimeout(function(){
            	   cncaAjax_windowOpenPostInterface(ctx+"/modules/cncaController/cncaJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function cncaCommon_detailFromDB(){
	
	   var ctx='',queryType='',content='',checkResult='';
	
	   ctx=$("#ctx").val();
	   queryType=$("#searchBox_big #type").text().trim();
	   content=$("#searchBox_big_content").val().trim();
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       } else if(content.length < 6) {
	   	    alert("搜索内容不得小于6个字符");
		    return;
		 }
	     
       detailName="cncaDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text().trim();
	   
	   checkResult=cncaAjax_checkIsExist(ctx,false,queryType,content);
	   
	   if(checkResult.existCode==0){
		       cncaCommon_confirmJoinModule(ctx,queryType,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   cncaAjax_windowOpenPostDetail(ctx+"/modules/cncaController/cncaDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   cncaCommon_confirmJoinModule(ctx,queryType,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function cncaCommon_confirmJoinModule(ctx,type,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	     ownerTaskResult=cncaAjax_joinTask(ctx,false,type,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,type,content,"认证网");
}