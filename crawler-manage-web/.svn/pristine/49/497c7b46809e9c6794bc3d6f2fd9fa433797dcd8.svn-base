$(function(){
	searchBoxPlaceholder("zjsfgkw");
	//搜索框样式
	renfawangCommon_searchBoxStyle();
	
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
			  renfawangCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 renfawangCommon_chooseSearchVersion()
		 
    }
	
})
//搜索框样式
function renfawangCommon_searchBoxStyle(){
	
	
	
	//修正搜索框
    $("#searchBox_big >div[class='input-group'] >#searchBox_big_content").before("<div class='input-group-btn'>"
    		                                                                           +"<button type='button' class='btn btn-default dropdown-toggle active' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='height:40px'>"
    		                                                                                 +"<span id='type' data-name='type'></span>"
    		                                                                                 +"<span class='caret'></span>"
    		                                                                           +"</button>"
    		                                                                           +"<ul id='chooseType' class='dropdown-menu' style='font-size:14px;padding-left:5px;padding-right:5px'>"
    		                                                                                 +"<li data-name='txtReallyName' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>姓名</li>"
    		                                                                                 +"<li data-name='txtCredentialsNumber' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>证件号码</li>"
    		                                                                                 +"<li data-name='txtAH' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>案号</li>"
    		                                                                                 +"<li data-name='drpZXFY' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>执行法院</li>"
    		                                                                           +"</ul>"
    		                                                                     +"</div>");
	
	$("#searchBox_big #chooseType").on("click","li",function(){
		
		       $("#searchBox_big #type").attr("data-name",$(this).attr("data-name"));
		       
		       $("#searchBox_big #type").text($(this).text());
		       
		       searchBoxPlaceholder("refawang_"+$(this).attr("data-name"));
	})

	$("#searchBox_big #chooseType li[data-name='txtReallyName']").click();
	
}
//选择搜索版本
function renfawangCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 renfawangCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 renfawangCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function renfawangCommon_interfaceSearch(){
	
	     var ctx='',type='',content='',searchResult='',queryType='';
	     
	     ctx=$("#ctx").val();
	     type=$("#searchBox_big #type").text().trim();
	     content=$("#searchBox_big_content").val().trim();
	     
	     if(content==''){
	    	    alert("搜索内容不能为空");
	    	    return;
	     }
	     
	     if(type=="姓名"){
	    	   queryType="姓名";
	     }else if(type=="证件号码"){
	    	   queryType="证件号码";
	     }else if(type=="案号"){
	    	   queryType="案号";
	     }else if(type=="执行法院"){
	    	   queryType="执行法院";
	     }else if(type=="立案开始日期"){
	    	   queryType="立案开始日期";
	     }else if(type=="立案结束日期"){
	    	   queryType="立案结束日期";
	     }
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=renfawangAjax_getDetailFromInterface(ctx,false,queryType,content);
     
	    	    renfawangCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function renfawangCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="renfawangDetail";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   renfawangAjax_windowOpenPostDetail(ctx+"/modules/zjsfgkw/zjsfgkwDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 renfawangAjax_windowOpenPostInterface(ctx+"/modules/zjsfgkw/zjsfgkwJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   renfawangAjax_windowOpenPostInterface(ctx+"/modules/renfawang/renfawangJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function renfawangCommon_detailFromDB(){
	
	     var ctx='',type='',content='',checkResult='',queryType=''
	
	   ctx=$("#ctx").val();
	   type=$("#searchBox_big #type").text().trim();
	   content=$("#searchBox_big_content").val().trim();
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
    
	   if(type=="姓名"){
    	   queryType="姓名";
     }else if(type=="证件号码"){
    	   queryType="证件号码";
     }else if(type=="案号"){
    	   queryType="案号";
     }else if(type=="执行法院"){
    	   queryType="执行法院";
     }else if(type=="立案开始日期"){
    	   queryType="立案开始日期";
     }else if(type=="立案结束日期"){
    	   queryType="立案结束日期";
     }
	     
       detailName="renfawangDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text().trim();
	   
	   checkResult=renfawangAjax_checkIsExist(ctx,false,queryType,content);
	   
	   if(checkResult.existCode==0){
		       
		       renfawangCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   renfawangAjax_windowOpenPostDetail(ctx+"/modules/zjsfgkw/zjsfgkwDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   renfawangCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function renfawangCommon_confirmJoinModule(ctx,type,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	     ownerTaskResult=zjsfgkwAjax_joinTask(ctx,false,type,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,type,content,"浙法网");
}


//保存新任务
function zjsfgkwAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
         
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/modules/ajaxZjsfgkwExecuteCaseSearch/zjsfgkwJoinTask",
   	          type:"post",
   	          async:async,
   	          data:{
   	    	       type:type,
   	    	       content:content,
   	    	       loginName:loginName,
   	    	       existCode:existCode,
   	    	       timetaskID:timetaskId,
   	    	       date:new Date()
   	         },
   	         success:function(data){
   	    	       if(!async){
   	    		       joinTaskResult=data;
   	    	       }
   	         },
   	         error:function(){
   	    	     if(!async){
   	    		    joinTaskResult="isError";
   	    	     }
   	         }
    })

    if(!async){
   	    return joinTaskResult;
    }

}