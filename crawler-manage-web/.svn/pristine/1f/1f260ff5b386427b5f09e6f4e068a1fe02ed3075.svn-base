//查询专利网任务，条件查询
function searchSipoTask(ctx,async,loginName,searchType,status,keyWord,pageSize,currentPage,callFunName){

    var ownerTaskSipoPage='';

    $.ajax({

        url:ctx+"/ownerTask/searchSipoTask",
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
                ownerTaskSipoPage=data;
            }

        },
        error:function(){
            ownerTaskSipoPage="isError";
        }

    });

    if(!async){
        return ownerTaskSipoPage;
    }



}