//查询失信网任务，条件查询
function searchDishonestyTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskDishonestyPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchDishonestyTask",
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
                ownerTaskDishonestyPage=data;
            }

        },
        error:function(){
            ownerTaskDishonestyPage="isError";
        }

    });

    if(!async){
        return ownerTaskDishonestyPage;
    }

}

//保存新任务
function shixinOTAjax_joinTask(ctx,async,type,keyword,cardNum,loginName,existCode,timetaskId){
         
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/shixinJoinTask",
   	          type:"post",
   	          async:async,
   	          data:{
   	    	       type:type,
   	    	       keyword:keyword,
   	    	       cardNum:cardNum,
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