//加入新任务---工商网gsxtOTAjax_gsxtJoinTask(ctx,false,area,keyword,loginName,existCode)
function gsxtOTAjax_gsxtJoinTask(ctx,async,city,name,loginName,existCode,companyID,callFunName){
	   
	     var gsxtResult='';
	     $.ajax({
	    	 
	    	     url:ctx+"/ownerTask/gsxtJoinTask",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	   city:city,
	    	    	   name:name,
	    	    	   loginName:loginName,
	    	    	   existCode:existCode,
	    	    	   companyID:companyID,
	    	    	   date:new Date()
	    	     },
	    	     success:function(data){
	    	    	   if(!async){
	    	    		     gsxtResult=data;
	    	    	   }
	    	     },
	    	     error:function(){
	    	    	   if(!async){
	    	    		     gsxtResult="isError";
	    	    	   }
	    	     }
	     })
	
	     if(!async){
	    	    return gsxtResult;
	     }
	
}
//查询工商网任务,全部地区、任务状态、创建时间、完成时间
function gsxtOTAjax_getGsxtTask(ctx,async,loginName,callFunName){
	
	     var getGsxtTaskResult='';
	     
	     $.ajax({
	    	 
	    	     url:ctx+"/ownerTask/getGsxtTask",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	   loginName:loginName  
	    	     },
	    	     success:function(data){
	    	    	   if(!async){
	    	    		   getGsxtTaskResult=data; 
	    	    	   }
	    	     },
	    	     error:function(){
	    	    	   if(!async){
	    	    		   getGsxtTaskResult="isError";
	    	    	   }
	    	     }
	     })
	     
	     if(!async){
	    	    return getGsxtTaskResult;
	     }
}
//查询工商网任务，条件查询
function gsxtOTAjax_getGsxtTaskByCon(ctx,async,loginName,areas,status,company,callFunName){
	
	     var getGsxtTaskByConResult='';
	     
	     $.ajax({
	    	    
	    	     url:ctx+"/ownerTask/getGsxtTaskByCon",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	   loginName:loginName,
	    	    	   areas:areas,
	    	    	   status:status,
	    	    	   company:company,
	    	    	   time:new Date()
	    	     },
	    	     success:function(data){
	    	    	   if(!async){
	    	    		   getGsxtTaskByConResult=data; 
	    	    	   }   
	    	    	 
	    	     },
	    	     error:function(){
	    	    	 getGsxtTaskByConResult="isError";
	    	     }
	    	 
	     })
	     
	     if(!async){
	    	   return getGsxtTaskByConResult;
	     }
	
	
	
}