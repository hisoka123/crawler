$(function(){
	
	//搜索框样式
	fahaiccCommon_searchBoxStyle();
	
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
			  fahaiccCommon_detailFromDB();
		  })
	}else{
		 $("#chooseDataSource").show();
		 fahaiccCommon_chooseSearchVersion()
    }
	
})
//搜索框样式
function fahaiccCommon_searchBoxStyle(){
	
	//修正搜索框
    $("#searchBox_big >div[class='input-group'] >#searchBox_big_content").before("<div class='input-group-btn'>"
    		                                                                           +"<button type='button' class='btn btn-default dropdown-toggle active' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='height:40px'>"
    		                                                                                 +"<span id='type' data-name='type'></span>"
    		                                                                                 +"<span class='caret'></span>"
    		                                                                           +"</button>"
    		                                                                           +"<ul id='chooseType' class='dropdown-menu' style='font-size:14px;padding-left:5px;padding-right:5px'>"
    		                                                                                 +"<li data-name='name' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>个人/企业名称</li>"
    		                                                                                 +"<li data-name='identity' style='margin-top:10px;margin-bottom:10px;cursor:pointer'>身份证号码/组织机构代码</li>"
    		                                                                           +"</ul>"
    		                                                                     +"</div>");
	
    $("#searchBox_big #chooseType").on("click","li",function(){
		
	       $("#searchBox_big #type").attr("data-name",$(this).attr("data-name"));
	       
	       $("#searchBox_big #type").text($(this).text());
	       
	       searchBoxPlaceholder("fahaicc_"+$(this).attr("data-name"));
   })
 
   $("#searchBox_big #chooseType li[data-name='name']").click();
}
//选择搜索版本
function fahaiccCommon_chooseSearchVersion(){
	
	    $("#searchBox_big_btn").on("click",function(){
	    	
	         var value='';
	         value=$("input[name='chooseDataSource']:checked").attr("value");
	
	         if(value=="interfaceVersion"){
	        	 fahaiccCommon_interfaceSearch(); 
	         }else if(value=="dbVersion"){
    	    	 fahaiccCommon_detailFromDB();
    	     }
	
       })
	
}
//接口搜索
function fahaiccCommon_interfaceSearch(){
	
	   
	    var ctx='',type='',content='',searchResult='',queryType='';
     
        ctx=$("#ctx").val();
        type=$("#searchBox_big #type").text();
        content=$.trim($("#searchBox_big_content").val());
       
        
        if(content==''){
    	    alert("搜索内容不能为空");
    	    return;
       }
     
         $("#info >div").text("努力查询，请稍候...");
		 $("#info >div").attr("style","color:red");
	     $("#info").show();
	
	   setTimeout(function(){
	    	 
	    	    searchResult=fahaiccAjax_getDetailFromInterface(ctx,false,content);
  
	    	  //  console.log(searchResult);
	    	    fahaiccCommon_detailFromIN(ctx,searchResult,type,content);
	        	      
       },1000);
	
}
//接口处显示详情
function fahaiccCommon_detailFromIN(ctx,searchResult,type,content){
	
         var detailName='',typeName='',contentName='';
         
         
         detailName="fahaiccDetail";
         typeName="type";
         contentName="keyword";

         $("#info").hide();
 
         
         if(JSON.parse(searchResult).data!=null){
   	 
                     fahaiccAjax_windowOpenPostDetail(ctx+"/modules/fahaicc/fahaiccDetailByInterface",detailName,JSON.stringify(JSON.parse(searchResult).data),typeName,type,contentName,content);
          
                  //调试时用
                 setTimeout(function(){
                	 fahaiccAjax_windowOpenPostInterface(ctx+"/modules/fahaicc/fahaiccJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
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
            	      fahaiccAjax_windowOpenPostInterface(ctx+"/modules/fahaicc/fahaiccJson","data","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },1000);
          }
}
//从数据库获取详情、创建任务
function fahaiccCommon_detailFromDB(){
	
	     var ctx='',type='',content='',checkResult='',typeName='',contentName='',detailName='',loginName='';
	
	   ctx=$("#ctx").val();
	   type=$("#searchBox_big #type").text();
	   content=$.trim($("#searchBox_big_content").val());
	   
	   if(content==''){
   	       alert("搜索内容不能为空");
   	       return;
       }
    
	     
       detailName="fahaiccDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text();
	   typeName="type";
	   contentName="keyword";
	   
	   checkResult=fahaiccAjax_checkIsExist(ctx,false,type,content);
	   
	   console.log(JSON.stringify(checkResult))
	   
	   if(checkResult.existCode==0){
		       
		       fahaiccCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
	   }else{
		   
		       if(checkResult.state==7){  //existcode=1 && state=7
		    	  $("#info >div").text("没检索到关键字");
		    	  $("#info >div").attr("style","color:green");
                  $("#info").show();
		       }else if(checkResult.state==1){
		    	   fahaiccAjax_windowOpenPostDetail(ctx+"/modules/fahaicc/fahaiccDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult),typeName,type,contentName,content);
		       }else{
		    	   fahaiccCommon_confirmJoinModule(ctx,type,content,loginName,checkResult.existCode,checkResult.timetask_id);
		       }
		   
	   }
}
//创建新任务
function fahaiccCommon_confirmJoinModule(ctx,type,content,loginName,existCode,timetaskId){
	
	     var ownerTaskResult='';

	     ownerTaskResult=fahaiccOTAjax_joinTask(ctx,false,type,content,loginName,existCode,timetaskId);
 	         
 	     common_createNewTask("info",ownerTaskResult,type,content,"法海风控");
}