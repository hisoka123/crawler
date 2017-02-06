//查询任务，11315企业征信条件查询
function searchEnterpCreditTask(ctx,async,loginName,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskEnterpCreditPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchEnterpCreditTask",
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
                ownerTaskEnterpCreditPage=data;
            }

        },
        error:function(){
            ownerTaskEnterpCreditPage="isError";
        }

    });

    if(!async){
        return ownerTaskEnterpCreditPage;
    }

}
