$(function(){
	
	searchBoxPlaceholder("iautos");
	
	//搜索框聚焦
	$("#searchBox_big_content").focus(function(){
		$("#info >div").text("");
		$("#info").hide();
	})
	
	
	//判断环境  
	var env=$("#env").val().replace(/(^\s*)|(\s*$)/g, "");
	
	if(env=="prod"){

		  $("#chooseDataSource").remove();
		  $("#searchBox_big_btn").on("click",function(){
			  iautosCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 iautosCommon_chooseSearchVersion()
		 
    }
	
})

//选择搜索版本
function iautosCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 iautosCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
	        	
    	    	 iautosCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function iautosCommon_interfaceSearch(){
	
	     var ctx='',city='',keyword='',searchResult='';
	     
	     ctx=$("#ctx").val();
	     city=$("#searchBox_big #city").text().trim();
	     keyword=$("#searchBox_big_content").val().trim();
	     
	     if(keyword==''){
    	    alert("搜索内容不能为空");
    	    return;
	     }   
	     
	     
	     if(city==''){
	    	 city="quanguo";
	     }   
	     
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=iautosAjax_getDetailFromInterface(ctx,false,city,keyword);
     
	    	    iautosCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function iautosCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="iautosDetail";

         $("#info").hide();
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   iautosAjax_windowOpenPostDetail(ctx+"/modules/iautos/iautosDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 iautosAjax_windowOpenPostInterface(ctx+"/modules/iautos/iautosJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   iautosAjax_windowOpenPostInterface(ctx+"/modules/iautos/iautosJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function iautosCommon_detailFromDB(){
	
	 var ctx='',content='',city='',checkResult='';
	
	   ctx=$("#ctx").val();
	
	   content=$("#searchBox_big_content").val().trim();
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
    
		 if(city==''){
		   city="quanguo";
			 
		 }
	   	        
       detailName="iautosDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text().trim();
	
	   checkResult=iautosAjax_checkIsExist(ctx,false,city,content);
	
	   if(checkResult.existCode==0){	
		    iautosCommon_confirmJoinModule(ctx,city,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		  
		       if(checkResult.state==7){  //existcode=1 && state=7		    	
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){		    
		    	   iautosAjax_windowOpenPostDetail(ctx+"/modules/iautos/iautosDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{		    	
		    	   iautosCommon_confirmJoinModule(ctx,city,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function iautosCommon_confirmJoinModule(ctx,city,content,loginName,existCode,timetaskId){
	     var ownerTaskResult='';

	     ownerTaskResult=iautosAjax_joinTask(ctx,false,city,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,city,content,"第一车网");
}