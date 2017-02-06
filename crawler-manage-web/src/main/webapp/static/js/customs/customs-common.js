$(function(){
	
	//搜索框样式
	searchBoxPlaceholder("customs");
	
	//搜索框聚焦
	$("#searchBox_big_content").focus(function(){
		$("#info >div").text("");
		$("#info").hide();
	})
	
	//判断运行环境
	var env=$.trim($("#env").val());
	
	if(env=="prod"){

		  $("#chooseDataSource").remove();
		  $("#searchBox_big_btn").on("click",function(){
			  customsCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 customsCommon_chooseSearchVersion()
		 
    }
})
//选择搜索版本
function customsCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 customsCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 customsCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function customsCommon_interfaceSearch(){
	
	     var ctx='',keyword='',searchResult='';
	     
	     ctx=$("#ctx").val();
	     keyword=$.trim($("#searchBox_big_content").val());
	     
	     if(keyword==''){
	    	    alert("搜索内容不能为空");
	    	    return;
	     }
	     
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=customsAjax_getDetailFromInterface(ctx,false,keyword);
     
	    	      
	    	      customsCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function customsCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="customsDetail";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   customsAjax_windowOpenPostDetail(ctx+"/modules/customs/customsDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 customsAjax_windowOpenPostInterface(ctx+"/modules/customs/customsJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   customsAjax_windowOpenPostInterface(ctx+"/modules/customs/customsJson","data","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function customsCommon_detailFromDB(){
	
	     var ctx='',content='',checkResult='',detailName='',loginName='';
	
	   ctx=$("#ctx").val();
	   content=$.trim($("#searchBox_big_content").val());
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
    
       detailName="customsDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text();
	   
	   checkResult=customsAjax_checkIsExist(ctx,false,content);
	   
	   console.log(JSON.stringify(checkResult));
	   if(checkResult.existCode==0){
		       customsCommon_confirmJoinModule(ctx,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   customsAjax_windowOpenPostDetail(ctx+"/modules/customs/customsDetailByDB",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   customsCommon_confirmJoinModule(ctx,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function customsCommon_confirmJoinModule(ctx,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	     ownerTaskResult=customsOTAjax_joinTask(ctx,false,content,loginName,existCode,timetaskId);
 	       
 	     common_createNewTask("info",ownerTaskResult,'',content,"海关网");
}