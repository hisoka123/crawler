//查询海关网任务，条件查询
function searchCustomsTask(ctx,async,loginName,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskCustomsPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchCustomsTask",
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
                ownerTaskCustomsPage=data;
            }

        },
        error:function(){
            ownerTaskCustomsPage="isError";
        }

    });

    if(!async){
        return ownerTaskCustomsPage;
    }

}
//保存新任务
function customsOTAjax_joinTask(ctx,async,content,loginName,existCode,timetaskId,callFunName){
         
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/customsJoinTask",
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
