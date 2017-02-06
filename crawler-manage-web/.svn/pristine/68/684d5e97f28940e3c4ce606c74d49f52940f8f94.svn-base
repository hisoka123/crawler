
//分页 设置默认值
var pageSize='',currentPage='',showPageNum='';
pageSize=20;  //显示页大小
currentPage=1; //当前页
showPageNum=5;     //一次显示的页数
var lineIndex = 0; //设置行号；

$(function(){

    //生成海关网全部任务列表
    show_Customs_AllTask();

    //筛选div样式、动作
    customs_searchConStyle();

    //点击搜索
    $("#search").on("click",function(){
        customs_search();
    });

    //”回车键“监听事件
    $(document).keydown(function(event){
        if(event.keyCode==13){

            if($("#searchKeyword").is(":focus")){
                $("#search").click();
            }
        }
    });

    //初始化
    $("#chose_status").children("span[data-name='searchStatus_all']").click();

});

//查询第一页的内容
function show_Customs_AllTask(){

    var ctx='',loginName='',ownerTaskCustomsPage='',taskList='';
    var totalElements = ''; //总记录

    ctx=$("#ctx").val();

    loginName=$("#fat-menu").find("span[id='loginName']").text();

    //taskList=getAllCustomsTask(ctx,false,loginName);

    ownerTaskCustomsPage=searchCustomsTask(ctx,false,loginName,"searchStatus_all",null,pageSize,currentPage);
    taskList = ownerTaskCustomsPage["content"];
    totalElements = ownerTaskCustomsPage["totalElements"];

    console.log("=====taskList=====" + taskList + "=====totalElements=====" + totalElements);

    //分页
    $("#page").html("");
    //显示首页内容
    customs_productList(taskList);

    //console.log("pageSize:"+ pageSize + "showPageNum: " +showPageNum +"=====totalElements=====" + totalElements);

    //调用commonSelf_pageStyle
    commonSelf_pageStyle(pageSize,showPageNum,totalElements,"isNull","page",getCustoms__pageCallback);

}


//生成海关网任务列表
function customs_productList(taskList){

    var trs='',completeTaskDate='',btn='',remark='';

    $.each(taskList,function(index,task){

        lineIndex = (currentPage-1)*pageSize + index + 1;

        if(task.completeTaskDate==null){
            completeTaskDate="---"
        }else{
            completeTaskDate=commonSelf_timeStampConvertToTime(task.completeTaskDate);
        }

        if(task.state==1){
            btn="<a href='javascript:void(0)' id='query_"+(index+1)+"'>查看</a>"
        }else if(task.state!=0 && task.state!=7 && task.state!=-3){
            btn="<a href='javascript:void(0)' id='feedback_"+(index+1)+"'>反馈详情</a>"
        }else{
            btn='---';
        }

        if(task.state==7){
            remark="没检索到关键字";
        }else if(task.state==10){
            remark="搜索关键字中含有非法字符（如：<>()等）";
        }else{
            remark="---";
        }

        trs +="<tr>"
            +"<td>"+lineIndex+"</td>"
            +"<td>"+task.keyWord+"</td>"
            +"<td>"+customs_taskState(task.state)+"</td>"
            +"<td>"+commonSelf_timeStampConvertToTime(task.createTaskDate)+"</td>"
            +"<td>"+completeTaskDate+"</td>"
            +"<td>"+btn+"</td>"
            +"<td>"+remark+"</td>"
            +"</tr>";

    });


    $("table >tbody").html(trs);

    $("table").on("click","a[id^='query_']",function(){

        var tr=$(this).parent().parent();
        var keyword=$(tr).children("td").eq(1).text();
        customsTask_detail(keyword);
    });

    $("table").on("click","a[id^='feedback_']",function(){
        var type=$("#left_menu ul").children("li[class*='node-selected']").text();
        
        if(type!="反馈信息"){
        	 localStorage.setItem("feedbackType",type);
             $("#left_menu ul").children("li:last").click();
        }
       
    })


}

//分页回调函数
function getCustoms__pageCallback(){

    var ctx='',loginName='',currentPage_content='';
    var searchStatus='',searchKeyword='';
    var ownerTaskCustomsPage='';


    //获取当前页
    currentPage=parseInt($("#page #currentPage").text());

    ctx=$("#ctx").val();

    loginName=$("#fat-menu").find("span[id='loginName']").text();

    //获取任务状态
    searchStatus=$("#chose_status").children("span[class='status_selectedStyle']").attr("data-name");

    //获取搜索关键字
    searchKeyword=$("#searchKeyword").val().trim();

    ownerTaskCustomsPage=searchCustomsTask(ctx,false,loginName,searchStatus,searchKeyword,pageSize,currentPage);
    currentPage_content = ownerTaskCustomsPage["content"];

    //console.log("=====currentPage_content=====" + currentPage_content);

    //显示当前页
    customs_productList(currentPage_content);

}

//任务状态
function customs_taskState(state){

    var returnState='';

    if(state==0){
        returnState="<span style='color:#FFC125'>等候处理</span>";
    }else if(state==1 || state==7){
        returnState="<span style='color:green'><strong>成功完成</strong></span>";
    }else if(state==-3){
        returnState="<span style='color:#80167f'><strong>正在处理</strong></span>";
    }else{
        returnState="<span style='color:#5bc0de'>反馈处理</span>";
    }

    return returnState;
}

//筛选　div样式
function customs_searchConStyle(){
    //状态筛选
    //==状态选中==
    $("#chose_status").on("click","span[data-name]",function(){
        customsCommon_selected($(this),"chose_status","status_selectedStyle","status_unSelectedStyle");
    });

    //状态移入
    $("#chose_status").on("mouseenter","span[data-name]",function(){
        if($(this).attr("class")=="status_unSelectedStyle"){
            $(this).attr("class","status_mouseenter");
        }
    });

    //状态移出
    $("#chose_status").on("mouseleave","span[data-name]",function(){
        if($(this).attr("class")=="status_mouseenter"){
            $(this).attr("class","status_unSelectedStyle");
        }
    });
}

//单个选中
function customsCommon_selected(self,scope,selectedClass,unSelectedClass){
    var dataName='';

    dataName=$(self).attr("data-name");

    var each='';
    for(var i=0;i<$("#"+scope).find("span[data-name]").length;i++){

        each=$("#"+scope).find("span[data-name]").eq(i);
        $(each).attr("class",unSelectedClass);

    }

    $(self).attr("class",selectedClass);

    //console.log("====scope: "+scope + "===data-name:" + $("#" + scope).find("span[data-name]") + "===dataName:" + dataName);

}

//搜索
function customs_search(){

    var ctx='',loginName='';
    var searchStatus='',searchKeyword='';
    var ownerTaskCustomsPage='',searchResult='';
    var noKeys='';


    ctx=$("#ctx").val();
    loginName=$("#fat-menu").find("span[id='loginName']").text();
    noKeys="<tr><td colspan='8'>没有搜索到相关信息</td></tr>";

    //获取任务状态
    searchStatus=$("#chose_status").children("span[class='status_selectedStyle']").attr("data-name");

    //获取搜索关键字
    searchKeyword=$("#searchKeyword").val().trim();

    console.log("     searchStatus---"+searchStatus+"    searchKeyword---"+searchKeyword);

    currentPage = 1;
    ownerTaskCustomsPage=searchCustomsTask(ctx,false,loginName,searchStatus,searchKeyword,pageSize,currentPage);
    searchResult = ownerTaskCustomsPage["content"];
    var totalElements = ownerTaskCustomsPage["totalElements"];

    //console.log("==searchResult===" + searchResult + "=条件查询=totalElements==" + totalElements);

    if(searchResult=="isError"){
        alert("服务器中断");
        return;
    }else{
    	$("#page").html("");
        if(searchResult=='' ||searchResult.isEmpty){
            $("table >tbody").html(noKeys);
        }else{
            //分页
            

            customs_productList(searchResult);

            //调用commonSelf_pageStyle
            commonSelf_pageStyle(pageSize,showPageNum,totalElements,"isNull","page",getCustoms__pageCallback);

        }
    }

}

//显示详情
function customsTask_detail(content){
    var ctx='',checkResult='',detailName="";

    ctx=$("#ctx").val();
    detailName="customsDetail";

    checkResult=customsAjax_checkIsExist(ctx,false,content);

    if(checkResult.state==1){
        customsAjax_windowOpenPostDetail(ctx+"/modules/customs/customsDetailByDB",detailName,JSON.stringify(checkResult.ttSearchResult));
    }

}