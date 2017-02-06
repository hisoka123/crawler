//获取反馈信息
function feedbackAjax_getFeedback(ctx,async,type,loginName,callFunName){
	 
	     var getFeedBackResult='';
	     
	     $.ajax({
	    	 
	    	    url:ctx+"/ownerTask/getFeedback",
	    	    type:"post",
	    	    async:async,
	    	    data:{
	    	    	   ownerTaskType:type,
	    	    	   loginName:loginName,
	    	    	   time:new Date()
	    	    },
	    	    success:function(data){
	    	    	   if(!async){
	    	    		   getFeedBackResult=data;  
	    	    	   }
	    	    },
	    	    error:function(){
	    	    	   if(!async){
	    	    		   getFeedBackResult="isError";
	    	    	   }
	    	    }
	     })
	
         if(!async){
        	    return getFeedBackResult;
         }	
}
//重置
function feedbackAjax_reset(ctx,async,loginName,taskType,ownerTaskId,timeTaskId,callFunName){
	
	     var resetResult='';
	     $.ajax({
	    	 
	    	     url:ctx+"/ownerTask/feedbackReset",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	   loginName:loginName,
	    	    	   taskType:taskType,
	    	    	   ownerTaskId:ownerTaskId,
	    	    	   timeTaskId:timeTaskId,
	    	    	   time:new Date()
	    	     },
	    	     success:function(data){
	    	    	   if(!async){
	    	    		   resetResult=data; 
	    	    	   }
	    	     },
	    	     error:function(){
	    	    	   if(!async){
	    	    		   resetResult="isError";
	    	    	   }
	    	     }
	     })
	     
	     if(!async){
	    	 return resetResult;
	     }
}
//获取反馈信息
function feedbackAjax_getTaskType(ctx,async,loginName,callFunName){
	 
	     var getTaskTypeResult='';
	     
	     $.ajax({
	    	 
	    	    url:ctx+"/ownerTask/getFeedbackTaskType",
	    	    type:"post",
	    	    async:async,
	    	    data:{
	    	    	   loginName:loginName,
	    	    	   time:new Date()
	    	    },
	    	    success:function(data){
	    	    	   if(!async){
	    	    		   getTaskTypeResult=data;  
	    	    	   }
	    	    },
	    	    error:function(){
	    	    	   if(!async){
	    	    		   getTaskTypeResult="isError";
	    	    	   }
	    	    }
	     })
	
         if(!async){
        	    return getTaskTypeResult;
         }	
}