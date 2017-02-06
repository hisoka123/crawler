$(function(){
	
	//搜索框样式
	shixinCommon_searchBoxStyle();
	
	//搜索框聚焦
	$("#searchBox_big_content").focus(function(){
		$("#info >div").text("");
		$("#info").hide();
	})
	
	
	//判断环境
	var env=$.trim($("#env").val());
	
	if(env=="prod"){

		  $("#chooseDataSource").remove();
		  $("#searchBox_big_btn").on("click",function(){
			  shixinCommon_detailFromDB();
		  })
		  
	}else{
		 $("#chooseDataSource").show();
		 shixinCommon_chooseSearchVersion()
		 
    }
	
})
//搜索框样式
function shixinCommon_searchBoxStyle(){
	
	$("#searchBox_big #chooseType").on("click","li",function(){
		
		       $("#searchBox_big #type").attr("data-name",$(this).attr("data-name"));
		       
		       $("#searchBox_big #type").text($(this).text());
		       
		       if($(this).attr("data-name")=="name"){
		    	       $("#input_identity").hide();
		    	       $("#searchBox_big_name").show();
		       }else{
		    	  
		    	   $("#input_identity").show();
	    	       $("#searchBox_big_name").hide();
		       }
	})

	$("#searchBox_big #chooseType li[data-name='name']").click();
	
}
//选择搜索版本
function shixinCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 shixinCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 shixinCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function shixinCommon_interfaceSearch(){
	
	     var ctx='',type='',searchResult='';
	     var data='';
	     var identity=''
	     
	     ctx=$("#ctx").val();
	     identity=$.trim($("#searchBox_big #identity").val());
	    
	     
	     if(identity==''){
	    	   data={
	    			   "type":0,
	    			   "keyword":$.trim($("#searchBox_big #name").val()),
	    			   "time":new Date()
	    	   };
	     }else{
	    	   data={
	    			   "type":1,
	    			   "cardNum":$.trim($("#searchBox_big #identity").val()),
	    			   "keyword":$.trim($("#searchBox_big #name").val()),
	    			   "time":new Date()
	    	   };
	     }
	     
	     
	     if($.trim($("#searchBox_big #name").val())==''){
	    	    alert("被执行人姓名/名称不能为空");
	    	    return;
	    	 
	     }
	     
	     $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	     
	     setTimeout(function(){
	    	 
	    	    searchResult=shixinAjax_getDetailFromInterface(ctx,false,data);
     
	    	  //  console.log(searchResult);
	    	    
	    	    shixinCommon_detailFromIN(ctx,searchResult);
	        	      
          },1000);
	
}
//接口处显示详情   
function shixinCommon_detailFromIN(ctx,searchResult){
	
         var detailName='';
    
         detailName="shixinDetail";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                   shixinAjax_windowOpenPostDetail(ctx+"/modules/shixin/shixinDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data));
          
                  
                  //调试时用
                 setTimeout(function(){
                	 shixinAjax_windowOpenPostInterface(ctx+"/modules/shixin/shixinJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	   shixinAjax_windowOpenPostInterface(ctx+"/modules/shixin/shixinJson","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function shixinCommon_detailFromDB(){
	
	     var ctx='',type='',checkResult='',detaillName='',loginname='';
	     var queryType='',cardNum='',keyword='';
	     var identity='';
	     
	     ctx=$("#ctx").val();
	     identity=$.trim($("#searchBox_big #identity").val());
	     keyword=$.trim($("#searchBox_big #name").val());
	     
	     if(identity==''){
	    	    queryType=0;
                cardNum='';
	     }else{
	    		 queryType=1;
	    	     cardNum=$.trim($("#searchBox_big #identity").val());
	    	     keyword=$.trim($("#searchBox_big #name").val());
	     }
	     
	     if(keyword==''){
	    	    alert("被执行人姓名/名称不能为空");
	    	    return;
	    	 
	     }
	     
         detailName="shixinDetail";
	     loginName=$("#fat-menu").find("span[id='loginName']").text();
	   
	     
	     checkResult=shixinAjax_checkIsExist(ctx,false,queryType,keyword,cardNum);
	     
	    // console.log(JSON.stringify(checkResult))
	     
	     
	     if(checkResult.existCode==0){
		           shixinCommon_confirmJoinModule(ctx,queryType,keyword,cardNum,loginName,checkResult.existCode,checkResult.timetask_id);
	     }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   shixinAjax_windowOpenPostDetail(ctx+"/modules/shixin/shixinDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
		       }else{
		    	   shixinCommon_confirmJoinModule(ctx,queryType,keyword,cardNum,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	     }
}
//创建新任务
function shixinCommon_confirmJoinModule(ctx,queryType,keyword,cardNum,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='',timetaskData='';
	     
	     
	     ownerTaskResult=shixinOTAjax_joinTask(ctx,false,queryType,keyword,cardNum,loginName,existCode,timetaskId);
 	         
	     if(ownerTaskResult=="isError" || ownerTaskResult==''){
	    	 
	            $("#info >div").text("与服务器断开，请稍候再试!");
	            $("#info >div").attr("style","color:red");
	            $("#info").show();

	        }else{
                  
	              if(ownerTaskResult.ownerTaskCode==0){
	                   $("#info >div").text(ownerTaskResult.message);
	                   $("#info >div").attr("style","color:red");
	                   $("#info").show();
	             }else if(ownerTaskResult.ownerTaskCode==1){
	                   $("#info >div").html(ownerTaskResult.message+"<br> 点击'我的任务'---'失信网'查看.");
	                   $("#info >div").attr("style","color:green");
	                   $("#info").show();
	             }else if(ownerTaskResult.ownerTaskCode==2){
	       	           $("#info >div").html(ownerTaskResult.message+"<br> 点击'我的任务'---'失信网'查看.");
	       	           $("#info >div").attr("style","color:#eea236");
	       	           $("#info").show();
	              }
	        }
}