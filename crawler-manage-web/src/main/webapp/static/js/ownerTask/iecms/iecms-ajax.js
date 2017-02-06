//查询贸易备案任务，条件查询
function searchIecmsTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskIecmsPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchIecmsTask",
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
                ownerTaskIecmsPage=data;
            }

        },
        error:function(){
            ownerTaskIecmsPage="isError";
        }

    });

    if(!async){
        return ownerTaskIecmsPage;
    }



}