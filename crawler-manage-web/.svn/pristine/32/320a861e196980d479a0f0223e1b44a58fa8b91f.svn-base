$(function(){
	searchBoxPlaceholder("sipo");
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
			  sipoCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 sipoCommon_chooseSearchVersion()
		 
    }
	
})
//选择搜索版本
function sipoCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 sipoCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 sipoCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function sipoCommon_interfaceSearch(){
	
	   var ctx='',type='',content='',searchResult='';
	     
	     ctx=$("#ctx").val();
	     type=$("input[name='type']:checked").attr("value");
	     content=$("#searchBox_big_content").val().trim();
	     
	     if(content==''){
	    	    alert("搜索内容不能为空");
	    	    return;
	     }
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=sipoAjax_getDetailFromInterface(ctx,false,type,content);
     
	    	    sipoCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function sipoCommon_detailFromIN(ctx,searchResult){
         var detailName='';
    
         detailName="siposDetail";

         $("#info").hide();
       
         if(JSON.parse(searchResult).data!=null){
   	 
                   sipoAjax_windowOpenPostDetail(ctx+"/modules/sipo/sipoDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 sipoAjax_windowOpenPostInterface(ctx+"/modules/sipo/sipoJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   sipoAjax_windowOpenPostInterface(ctx+"/modules/sipo/sipoJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function sipoCommon_detailFromDB(){
	
	   var ctx='',type='',content='',checkResult='';
	
	   ctx=$("#ctx").val();
	   type=$("input[name='type']:checked").attr("value");;;
	   content=$("#searchBox_big_content").val().trim();
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
	     
	 
	   
	   
       detailName="siposDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text().trim();
	   
	   checkResult=sipoAjax_checkIsExist(ctx,false,type,content);
	   
	   if(checkResult.existCode==0){
		       sipoCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   sipoAjax_windowOpenPostDetail(ctx+"/modules/sipo/sipoDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   sipoCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function sipoCommon_confirmJoinModule(ctx,type,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	     ownerTaskResult=sipoAjax_joinTask(ctx,false,type,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,type,content,"专利网");
}