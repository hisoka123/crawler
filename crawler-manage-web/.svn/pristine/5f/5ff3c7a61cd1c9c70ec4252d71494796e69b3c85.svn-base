//查询任务，失信记录查询平台条件查询
function searchCrqpTask(ctx,async,loginName,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskCrqpPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchCrqpTask",
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
                ownerTaskCrqpPage=data;
            }

        },
        error:function(){
            ownerTaskCrqpPage="isError";
        }

    });

    if(!async){
        return ownerTaskCrqpPage;
    }

}
