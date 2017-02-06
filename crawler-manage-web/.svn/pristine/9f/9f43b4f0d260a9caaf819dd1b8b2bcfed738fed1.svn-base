
//分页 设置默认值
var pageSize='',currentPage='',showPageNum='';
pageSize=20;  //显示页大小
currentPage=1; //当前页
showPageNum=5;     //一次显示的页数
var lineIndex = 0; //设置行号；

$(function(){

    //生成贷联盟全部任务列表
    show_CreditUnion_AllTask();

    //筛选div样式、动作
    creditUnion_searchConStyle();

    //点击搜索
    $("#search").on("click",function(){
        creditUnion_search();
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
    $("#chose_searchType").children("span[data-name='searchType_all']").click();
    $("#chose_status").children("span[data-name='searchStatus_all']").click();


    setSearchKeywordDisable();
});

//设置搜索框的disable属性
function setSearchKeywordDisable(){
    var searchType=$("#chose_searchType").children("span[class='status_selectedStyle']").attr("data-name");
    if(searchType=="searchType_name" || searchType=="searchType_IDCard"){
        $("#searchKeyword").removeAttr("disabled");
    }else {
        $("#searchKeyword").attr("disabled","disabled");
        $("#searchKeyword").val("");
    }
}

//查询第一页的内容
function show_CreditUnion_AllTask(){

    var ctx='',loginName='',ownerTaskCreditUnionPage='',taskList='';
    var totalElements = ''; //总记录

    ctx=$("#ctx").val();

    loginName=$("#fat-menu").find("span[id='loginName']").text();

    //taskList=getAllCreditUnionTask(ctx,false,loginName);

    ownerTaskCreditUnionPage=searchCreditUnionTask(ctx,false,loginName,"searchType_all","searchStatus_all",null,pageSize,currentPage);
    taskList = ownerTaskCreditUnionPage["content"];
    totalElements = ownerTaskCreditUnionPage["totalElements"];

    console.log("=====taskList=====" + taskList + "=====totalElements=====" + totalElements);

    //分页
    $("#page").html("");
    //显示首页内容
    creditUnion_productList(taskList);

    //console.log("pageSize:"+ pageSize + "showPageNum: " +showPageNum +"=====totalElements=====" + totalElements);

    //调用commonSelf_pageStyle
    commonSelf_pageStyle(pageSize,showPageNum,totalElements,"isNull","page",getCreditUnion__pageCallback);

}



//生成贷联盟任务列表
function creditUnion_productList(taskList){

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
            +"<td>"+task.searchType+"</td>"
            +"<td>"+task.keyWord+"</td>"
            +"<td>"+creditUnion_taskState(task.state)+"</td>"
            +"<td>"+commonSelf_timeStampConvertToTime(task.createTaskDate)+"</td>"
            +"<td>"+completeTaskDate+"</td>"
            +"<td>"+btn+"</td>"
            +"<td>"+remark+"</td>"
            +"</tr>";

    });


    $("table >tbody").html(trs);

    $("table").on("click","a[id^='query_']",function(){

        var tr=$(this).parent().parent();
        //搜索类型
        var searchType=$(tr).children("td").eq(1).text();
        //关键字
        //获取搜索关键字
        var searchKeyword=$(tr).children("td").eq(2).text();

        creditUnion_detail(searchType,searchKeyword);
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
function getCreditUnion__pageCallback(){

    var ctx='',loginName='',currentPage_content='';
    var searchType='',searchStatus='',searchKeyword='';
    var ownerTaskCreditUnionPage='';


    //获取当前页
    currentPage=parseInt($("#page #currentPage").text());

    ctx=$("#ctx").val();

    loginName=$("#fat-menu").find("span[id='loginName']").text();


    //搜索类型
    searchType=$("#chose_searchType").children("span[class='status_selectedStyle']").attr("data-name");

    //获取任务状态
    searchStatus=$("#chose_status").children("span[class='status_selectedStyle']").attr("data-name");

    //获取搜索关键字
    searchKeyword=$("#searchKeyword").val().trim();

    ownerTaskCreditUnionPage=searchCreditUnionTask(ctx,false,loginName,searchType,searchStatus,searchKeyword,pageSize,currentPage);
    currentPage_content = ownerTaskCreditUnionPage["content"];

    //console.log("=====currentPage_content=====" + currentPage_content);

    //显示当前页
    creditUnion_productList(currentPage_content);

}

//任务状态
function creditUnion_taskState(state){

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
function creditUnion_searchConStyle(){
    //状态筛选
    //==搜索类型选中==
    $("#chose_searchType").on("click","span[data-name]",function(){
        creditUnionCommon_selected($(this),"chose_searchType","status_selectedStyle","status_unSelectedStyle");

    });

    //移入
    $("#chose_searchType").on("mouseenter","span[data-name]",function(){
        if($(this).attr("class")=="status_unSelectedStyle"){
            $(this).attr("class","status_mouseenter");
        }
    });

    //移出
    $("#chose_searchType").on("mouseleave","span[data-name]",function(){
        if($(this).attr("class")=="status_mouseenter"){
            $(this).attr("class","status_unSelectedStyle");
        }
    });

    //==状态选中==
    $("#chose_status").on("click","span[data-name]",function(){
        creditUnionCommon_selected($(this),"chose_status","status_selectedStyle","status_unSelectedStyle");
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
function creditUnionCommon_selected(self,scope,selectedClass,unSelectedClass){
    var dataName='';

    dataName=$(self).attr("data-name");

    var each='';
    for(var i=0;i<$("#"+scope).find("span[data-name]").length;i++){

        each=$("#"+scope).find("span[data-name]").eq(i);
        $(each).attr("class",unSelectedClass);

    }

    $(self).attr("class",selectedClass);

    //console.log("====scope: "+scope + "===data-name:" + $("#" + scope).find("span[data-name]") + "===dataName:" + dataName);

    setSearchKeywordDisable();
}

//搜索
function creditUnion_search(){

    var ctx='',loginName='';
    var searchType='',searchStatus='',searchKeyword='';
    var ownerTaskCreditUnionPage='',searchResult='';
    var noKeys='';


    ctx=$("#ctx").val();
    loginName=$("#fat-menu").find("span[id='loginName']").text();
    noKeys="<tr><td colspan='8'>没有搜索到相关信息</td></tr>";

    //搜索类型
    searchType=$("#chose_searchType").children("span[class='status_selectedStyle']").attr("data-name");

    //获取任务状态
    searchStatus=$("#chose_status").children("span[class='status_selectedStyle']").attr("data-name");

    //获取搜索关键字
    searchKeyword=$("#searchKeyword").val().trim();

    console.log("     searchStatus---"+searchStatus+"    searchKeyword---"+searchKeyword + "   searchType---" + searchType);

    currentPage = 1;
    ownerTaskCreditUnionPage=searchCreditUnionTask(ctx,false,loginName,searchType,searchStatus,searchKeyword,pageSize,currentPage);
    searchResult = ownerTaskCreditUnionPage["content"];
    var totalElements = ownerTaskCreditUnionPage["totalElements"];

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

            creditUnion_productList(searchResult);

            //调用commonSelf_pageStyle
            commonSelf_pageStyle(pageSize,showPageNum,totalElements,"isNull","page",getCreditUnion__pageCallback);

        }
    }

}

//显示详情
function creditUnion_detail(type,content){

    var ctx='',checkResult='',detailName="";

    ctx=$("#ctx").val();
    detailName="dailianmengDetail";

    checkResult=dailianmengAjax_checkIsExist(ctx,false,type,content);

    if(checkResult.state==1){
        dailianmengAjax_windowOpenPostDetail(ctx+"/modules/dailianmeng/dailianmengDetailByInterface",detailName,JSON.stringify(checkResult.ttSearchResult));
    }

}