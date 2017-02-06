//查询第一车网任务，条件查询
function searchIautosTask(ctx,async,loginName,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskIautosPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchIautosTask",
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
                ownerTaskIautosPage=data;
            }

        },
        error:function(){
            ownerTaskIautosPage="isError";
        }

    });

    if(!async){
        return ownerTaskIautosPage;
    }

}

//保存新任务
function iautosAjax_joinTask(ctx,async,city,content,loginName,existCode,timetaskId,callFunName){
	     var joinTaskResult='';
         
	     $.ajax({
   	 
   	          url:ctx+"/ownerTask/iautosJoinTask",
   	          type:"post",
   	          async:async,
   	          data:{
   	        	   city:city,
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

