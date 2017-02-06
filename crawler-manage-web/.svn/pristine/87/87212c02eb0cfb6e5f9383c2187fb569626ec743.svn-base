/*全局变量*/

//跟目录
rootPath = "";
/*待操作的主键*/ /*也可以写成一个数组*/
var operatedId;
var rootPath;
var action;

//分页相关,分页div中ul的主键id一定要是"pager"
var currentPage;
var totalPages;
var totalCounts;   //总数据量
var pageSize;



function pagerEffect(action){
	var options = {
		currentPage: currentPage,
		totalPages: totalPages,
		size:"normal",
		alignment:"right",//not support bootstrap3
		bootstrapMajorVersion:3,
		itemTexts: function (type, page, current) {
			switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
			}
		},
		onPageClicked: function (event, originalEvent, type, page ,requestUrl) {
			currentPage = page;
			var param = {page:page};
			var result;
			result = dataAccessWithPageInfo(action,param)
			modifyandImportData(result);
		}
	}
	$('#pager').bootstrapPaginator(options);
}



$(function(){

	//监听事件
	$(document).keydown(function(event){
	      //”回车键“监听事件
	      if(event.keyCode==13){
	    	     if($("#searchBox_big_content").is(":focus")){
	    	    	  $("#searchBox_big_btn").click();
	    	     }
	    	     
	    	     if($("#searchBox_small_content").is(":focus")){
	    	    	  $("#searchBox_small_btn").click();
	    	     }
	      }
    })
	
})


/*左侧菜单导航*/
function  leftMenu(){
	      
	    var url=document.location.toString();
        var style1="background:#185F7D;color:#ffffff";
	    var style2="background:#f5f5f5;";
        
        
        
        var  url_array=url.split("/");
        var  url_id=url_array[url_array.length-1];
        
        
        if(url_id=="personSearch"){
        	$("#person_menu_search a").attr("style",style1);
        }else if(url_id=="sinaSearch" || url_id=="sinaweibo"){
        	$("#sina_menu a:first").click();
        	$("#sina_menu_personSearch a").attr("style",style2);
        }else if(url_id=="sina_personList"){
        	//   新浪微博用户已关注列表
        }else if(url_id=="baiduSearch" || url_id=='baidubaike'){
        	 $("#baidu_menu a:first").click();
        	 $("#baidu_menu_personSearch a").attr("style",style2);
        }else if(url_id=="zhihuSearch" ||  url_id=="zhihu"){
        	 $("#zhihu_menu a:first").click();
       	     $("#zhihu_menu_personSearch a").attr("style",style2);
        }else if(url_id=="weixinSearch" || url_id=="weixin"){
        	 $("#weixin_menu a:first").click();
      	     $("#weixin_menu_personSearch a").attr("style",style2);
        }else if(url_id=="linkedInSearch" || url_id=="linkedIn"){
        	 $("#linkedIn_menu a:first").click();
     	     $("#linkedIn_menu_personSearch a").attr("style",style2);
        }else if(url_id=="taobaoSearch" || url_id=="taobao"){
        	 $("#taobao_menu a:first").click();
    	     $("#taobao_menu_personSearch a").attr("style",style2);
        }else if(url_id=="youkuSearch" || url_id=="youku"){
        	 $("#youku_menu a:first").click();
   	         $("#youku_menu_personSearch a").attr("style",style2);
        }else if(url_id=="youtubeSearch" || url_id=="youtube"){
        	 $("#youtube_menu a:first").click();
  	         $("#youtube_menu_personSearch a").attr("style",style2);
        }else if(url_id=="facebookSearch" || url_id=="facebook"){
        	 $("#facebook_menu a:first").click();
 	         $("#facebook_menu_personSearch a").attr("style",style2);
        }
	
}


/*ajax方式请求后台并返回数据，一般需要返回列表时使用*/
function dataAccessWithPageInfo(action,param){
	var result = '';
	var data;
	action = action;
	currentPage = (currentPage!=null)?currentPage:1;
	var param = {page:currentPage};


	$.ajax({
		url: action,
		data: param,
		async: false,
		success: function (result1_list) {
			result = result1_list;
		},
		error: function () {
			result = "error";
		}
	});

	data = result.data;
	currentPage = data.page;
	pageSize =  data.pageSize;
	totalCounts = data.totalCounts;
	totalPages = data.pageCounts;

	return result.data.entities;
}


/*ajax方式请求后台并返回数据*/
function dataAccess(action, param) {

	var result = '';

	$.ajax({
		url: rootPath + action,
		data: param,
		async: false,
		success: function (resultValue) {
			result = resultValue;
		},
		error: function () {
			result = "error";
		}
	});
	return result;
}

function onSuccess(){
    location.reload();
}

function onFaild(){
    openFaildPanel();
}

/*弹出失败提示框*/
function openErrorPanel(){

}

/*弹出成功提示框*/
function openSuccessPanel(){
	$('#noticeModal').modal();
	$("#noticeContent").html("操作成功")
}

/*弹出失败提示框*/
function openFaildPanel(){
	$('#noticeModal').modal();
	$("#noticeContent").html("操作失败!")
}

function resetOperatedId(){
    operatedId = -1;
}




