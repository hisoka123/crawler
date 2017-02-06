//查找全部任务
function ownerTaskAjax_getAllTask(ctx,async,loginName,callFunName){
	
	     var getAllTaskResult='';
	     $.ajax({
	    	     url:ctx+"/ownerTask/getAllTask",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	 loginName:loginName,
	    	    	 time:new Date()
	    	     },
	    	     success:function(data){
	    	    	    if(!async){
	    	    	    	getAllTaskResult=data; 
	    	    	    }else{
	    	    	    	if(callFunName=="leftMenuOT_leftMenu"){
	    	    	    		leftMenuOT_leftMenu(data);
	    	    	    	}
	    	    	    }
	    	     },
	    	     error:function(){
	    	    	    if(!async){
	    	    	    	getAllTaskResult="isError";
	    	    	    }else{
	    	    	    	alert("服务器繁忙,请稍候再试!")
	    	    	    }
	    	     }
	     })
	     
	     if(!async){
	    	 return getAllTaskResult;
	     }
}


