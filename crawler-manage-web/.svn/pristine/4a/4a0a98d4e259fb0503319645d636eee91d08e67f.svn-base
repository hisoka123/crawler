//查询人法网任务,全部搜索类型、任务状态、创建时间、完成时间
/*function getAllPeopleCourtTask(ctx,async,loginName,callFunName){

    var getPeopleCourtTaskResult='';

    $.ajax({

        url:ctx+"/ownerTask/getAllPeopleCourt",
        type:"post",
        async:async,
        data:{
            loginName:loginName
        },
        success:function(data){
            if(!async){
                getPeopleCourtTaskResult=data;
            }
        },
        error:function(){
            if(!async){
                getPeopleCourtTaskResult="isError";
            }
        }
    });

    if(!async){
        return getPeopleCourtTaskResult;
    }
}*/

//查询人法网任务，条件查询
function searchPeopleCourtTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskPeopleCourtPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchPeopleCourtTask",
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
                ownerTaskPeopleCourtPage=data;
            }

        },
        error:function(){
            ownerTaskPeopleCourtPage="isError";
        }

    });

    if(!async){
        return ownerTaskPeopleCourtPage;
    }

}
//保存新任务
function peopleCourtAjax_joinTask(ctx,async,type,content,loginName,existCode,timetaskId,callFunName){
         
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/renfawangJoinTask",
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