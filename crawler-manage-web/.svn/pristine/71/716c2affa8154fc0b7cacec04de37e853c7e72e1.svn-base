//查询法海网任务，条件查询
function searchFahaiccTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskFahaiccPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchFahaiccTask",
        type:"post",
        async:async,
        data:{
            pageSize:pageSize,
            currentPage:currentPage,
            loginName:loginName,
            searchType:searchType,
            status:status,
            keyWord:keyWord
        },
        success:function(data){
            if(!async){
                ownerTaskFahaiccPage=data;
            }

        },
        error:function(){
            ownerTaskFahaiccPage="isError";
        }

    });

    if(!async){
        return ownerTaskFahaiccPage;
    }



}
//保存新任务
function fahaiccOTAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
         
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/fahaiccJoinTask",
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
