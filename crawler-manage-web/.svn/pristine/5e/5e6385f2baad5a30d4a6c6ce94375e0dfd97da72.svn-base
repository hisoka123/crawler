//查询浙法网任务，条件查询
function searchZjCourtTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskZjCourtPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchZjCourtTask",
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
                ownerTaskZjCourtPage=data;
            }

        },
        error:function(){
            ownerTaskZjCourtPage="isError";
        }

    });

    if(!async){
        return ownerTaskZjCourtPage;
    }



}