//查询贷联盟任务，条件查询
function searchCreditUnionTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskCreditUnionPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchCreditUnionTask",
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
                ownerTaskCreditUnionPage=data;
            }

        },
        error:function(){
            ownerTaskCreditUnionPage="isError";
        }

    });

    if(!async){
        return ownerTaskCreditUnionPage;
    }



}


//保存新任务
function loanUnionAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/dailianmengJoinTask",
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