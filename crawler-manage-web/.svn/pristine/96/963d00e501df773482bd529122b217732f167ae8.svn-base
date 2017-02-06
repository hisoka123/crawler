$(function(){
	
	//搜索框样式
	iecmsCommon_searchBoxStyle();
	
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
			  iecmsCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 iecmsCommon_chooseSearchVersion()
		 
    }
	
})
//搜索框样式
function iecmsCommon_searchBoxStyle(){
	
	
	
	//修正搜索框
    $("#searchBox_big >div[class='input-group'] >#searchBox_big_content").before("<div class='input-group-btn'>"
    		                                                                           +"<button type='button' class='btn btn-default dropdown-toggle active' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='height:40px'>"
    		                                                                                 +"<span id='type' data-name='type'></span>"
    		                                                                                 +"<span class='caret'></span>"
    		                                                                           +"</button>"
    		                                                                           +"<ul id='chooseType' class='dropdown-menu' style='font-size:14px;padding-left:5px;padding-right:5px'>"
    		                                                                                 +"<li data-name='jyzdm' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>13位经营者代码</li>"
    		                                                                                 +"<li data-name='jyzmc' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>经营者名称</li>"
    		                                                                                 +"<li data-name='shtydm' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>统一社会信用代码</li>"
    		                                                                           +"</ul>"
    		                                                                     +"</div>");
	
	$("#searchBox_big #chooseType").on("click","li",function(){
		
		       $("#searchBox_big #type").attr("data-name",$(this).attr("data-name"));
		       
		       $("#searchBox_big #type").text($(this).text());
		       
		       searchBoxPlaceholder("iecms_"+$(this).attr("data-name"));
	})

	$("#searchBox_big #chooseType li[data-name='jyzmc']").click();
	
}
//选择搜索版本
function iecmsCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 iecmsCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 iecmsCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function iecmsCommon_interfaceSearch(){
	
	     var ctx='',type='',content='',searchResult='',queryType='';
	     
	     ctx=$("#ctx").val();
	     type=$("#searchBox_big #type").text().trim();
	     content=$("#searchBox_big_content").val().trim();
	     
	     if(content==''){
	    	    alert("搜索内容不能为空");
	    	    return;
	     }
	     
	     if(type=="经营者名称"){
	    	   queryType="1";
	     }else if(type=="13位经营者代码"){
	    	   queryType="2";
	     }else if(type=="统一社会信用代码"){
	    	   queryType="3";
	     }
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=iecmsAjax_getDetailFromInterface(ctx,false,queryType,content);
     
	    	    iecmsCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情
function iecmsCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="iecmsDetail";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   iecmsAjax_windowOpenPostDetail(ctx+"/modules/iecms/iecmsDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  //调试时用
                 setTimeout(function(){
                	 iecmsAjax_windowOpenPostInterface(ctx+"/modules/iecms/iecmsJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   iecmsAjax_windowOpenPostInterface(ctx+"/modules/iecms/iecmsJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function iecmsCommon_detailFromDB(){
	
	     var ctx='',type='',content='',checkResult='',queryType=''
	
	   ctx=$("#ctx").val();
	   type=$("#searchBox_big #type").text().trim();
	   content=$("#searchBox_big_content").val().trim();
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
    
	    if(type=="经营者名称"){
	    	   queryType="1";
	     }else if(type=="13位经营者代码"){
	    	   queryType="2";
	     }else if(type=="统一社会信用代码"){
	    	   queryType="3";
	     }
	     
       detailName="iecmsDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text().trim();
	   
	   checkResult=iecmsAjax_checkIsExist(ctx,false,queryType,content);
	   
	   if(checkResult.existCode==0){
		       
		       iecmsCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   iecmsAjax_windowOpenPostDetail(ctx+"/modules/iecms/iecmsDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   iecmsCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function iecmsCommon_confirmJoinModule(ctx,type,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	    ownerTaskResult=iecmsAjax_joinTask(ctx,false,type,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,type,content,"对外贸易经营者备案登记系统");
}

function iecmsAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
    
    var joinTaskResult='';
    
    $.ajax({
    	
	          url:ctx+"/ownerTask/iecmsJoinTask",
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