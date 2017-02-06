//查询任务，认证网条件查询
function searchAuthenticateTask(ctx,async,loginName,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskAuthenticatePage='';

    $.ajax({

        url:ctx+"/ownerTask/searchAuthenticateTask",
        type:"post",
        async:async,
        data:{
            pageSize:pageSize,
            currentPage:currentPage,
            loginName:loginName,
            status:status,
            keyWord:keyWord
        },
        success:function(data){
            if(!async){
                ownerTaskAuthenticatePage=data;
            }

        },
        error:function(){
            ownerTaskAuthenticatePage="isError";
        }

    });

    if(!async){
        return ownerTaskAuthenticatePage;
    }

}

//保存新任务
function cncaAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/cncaJoinTask",
   	          type:"post",
   	          async:async,
   	          data:{
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