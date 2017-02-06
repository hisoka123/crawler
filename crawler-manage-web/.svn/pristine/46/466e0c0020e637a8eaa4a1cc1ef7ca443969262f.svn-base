$(function(){
	
	//在本页范围内声明一个全局变量
	taskArray=new Array();
	
	
	//生成工商网全部任务列表
	gsxtOT_gsxtTask();
	
	//筛选div样式、动作
	gsxtOT_searchConStyle();
	
	//搜索
	$("#search").on("click",function(){
		   gsxtOTCommon_search();
	})
	
	 //”回车键“监听事件
	$(document).keydown(function(event){
	      if(event.keyCode==13){
	    	     
	    	     if($("#searchCompany").is(":focus")){
	    	    	  $("#search").click();
	    	     }
	      }
    })
	
    $("#searchCompany").focus(function(){
    	   if($("#areaList").css("display")!="none"){
	    	   $("#close_areaList").click();
	         }
    })
	
	//初始化
	$("#areaList").children("span[data-name='searchArea_all']").click(); 
	$("#chose_status").children("span[data-name='searchStatus_all']").click();
	
	
	
})
//查询全部任务
function gsxtOT_gsxtTask(){
	
	     var ctx='',loginName='',taskList='';    
	
	    ctx=$("#ctx").val();
    
        loginName=$("#fat-menu").find("span[id='loginName']").text();
   
        taskList=gsxtOTAjax_getGsxtTask(ctx,false,loginName);
        
        gsxtOTCommon_productList(taskList);
        
	
}

//生成工商网任务列表
function gsxtOTCommon_productList(taskList){
	    
	     var trs='',completeTaskDate='',btn='',remark='',onePage_content='';
	     var onePageShowNum='',showPageNum='';
	     
	     onePageShowNum=20;  //一页显示条数
	     showPageNum=5;     //一次显示的页数
	     
	     taskArray.length=0;//清空数组
	     
         $.each(taskList,function(index,task){
	    	 
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
        	    }else{
        	    	remark="---";
        	    }
        	
    	        trs +="<tr>"
    	    	         +"<td>"+(index+1)+"</td>"
    	    	         +"<td data-name='"+task.city+"'>"+gsxtOT_areaArray()[task.city]+"</td>"
    	    	         +"<td>"+task.name+"</td>"
    	    	         +"<td>"+gsxtOT_taskState(task.state)+"</td>"
    	    	         +"<td>"+commonSelf_timeStampConvertToTime(task.createTaskDate)+"</td>"
    	    	         +"<td>"+completeTaskDate+"</td>"
    	    	         +"<td>"+btn+"</td>"
    	    	         +"<td>"+remark+"</td>"
    	    	     +"</tr>";
    	     
    	        if((index+1)%onePageShowNum==0){
    	    	    taskArray.push(trs);
    	    	    trs='';
    	        }
       })

       
       /*for(var i=0;i<(onePageShowNum-parseInt(taskList.length%onePageShowNum));i++){
    	       trs +="<tr style='visibility:hidden'><td colspan='8'>占位</td></tr>";
       }*/
         
        taskArray.push(trs);
        
         
         //分页
        $("#page").html("");
        
        //获取首页内容
        onePage_content=taskArray[0];
        
        //显示首页内容
        gsxtOT_showPage(onePage_content)
        
       
        	
       //调用commonSelf_pageStyle
       commonSelf_pageStyle(onePageShowNum,showPageNum,taskList.length,"isNull","page",gsxtOT_pageCallback);
        
         
        
        
         
}
//分页回调函数
function gsxtOT_pageCallback(){
	
	     var currentPage='',currentPage_content='';
	     
	     //获取当前页
	     currentPage=parseInt($("#page #currentPage").text());
	     
	     //获取当前页内容
	     currentPage_content=taskArray[currentPage-1];
	     
	     //显示当前页
	     
	     gsxtOT_showPage(currentPage_content);
	     
}
//显示内容
function gsxtOT_showPage(content){
	     
	     $("table >tbody").html(content);
	     
	     $("table").on("click","a[id^='query_']",function(){
	      	 
	            var tr=$(this).parent().parent();
	            var area=$(tr).children("td").eq(1).attr("data-name");
	            var keyword=$(tr).children("td").eq(2).text();
	        
	            gsxtOT_toCompanyDetail($(tr),area,keyword);
        })
        
        
        $("table").on("click","a[id^='feedback_']",function(){
        	    var type=$("#left_menu ul").children("li[class*='node-selected']").text();
        	   
        	    if(type!="反馈信息"){
        	        localStorage.setItem("feedbackType",type);
        	        $("#left_menu ul").children("li:last").click();
        	    }
        })
	
}

//地区数组json
function gsxtOT_areaArray(){
	
	    var areaArray={
	             "NorthChina":"华北", 	       "Beijing":"北京",      "Tianjin":"天津",      "Hebei":"河北",          "Shanxi":"山西",     "Neimenggu":"内蒙古",
	             "NorthEastChina":"东北",      "Liaoning":"辽宁",     "Jilin":"吉林",        "Heilongjiang":"黑龙江",
	             "EastChina":"华东", 	       "Shanghai":"上海",     "Jiangsu":"江苏",      "Zhejiang":"浙江",       "Anhui":"安徽",      "Fujian":"福建",        "Jiangxi":"江西",     "Shandong":"山东",
	             "SouthChina":"华南",   	       "Guangdong":"广东",    "Guangxi":"广西",      "Hainan":"海南",
	             "CentralChina":"华中", 	       "Henan":"河南",        "Hubei":"湖北",        "Hunan":"湖南",
	             "SouthWestChina":"西南",       "Chongqing":"重庆",   "Sichuan":"四川",      "Guizhou":"贵州",        "Yunnan":"云南",     "Xizang":"西藏",
	             "NorthWestChina":"西北 ", 	    "Shaanxi":"陕西",     "Gansu":"甘肃",        "Qinghai":"青海",        "Ningxia":"宁夏",    "Xinjiang":"新疆"              
	      }
	    
	    return areaArray;
}
//任务状态
function gsxtOT_taskState(state){
	
	     var returnState='';
	
	     if(state==0){
	    	 returnState="<span style='color:#FFC125'>等候处理</span>";
	     }else if(state==1 || state==7){
	    	 returnState="<span style='color:green'><strong>成功完成</strong></span>";
	     }else if(state==-3){
	    	 returnState="<span style='color:#80167f'>正在处理</span>";
	     }else{
	    	 returnState="<span style='color:#5bc0de'>反馈处理</span>";
	     }
	
	     return returnState;
}
//只转至企业详情
function gsxtOT_toCompanyDetail(tr,area,keyword){
	
	     var ctx='',checkResult='',detailName="";
	
	     ctx=$("#ctx").val();
	     detailName="gsxtDetail";
	     checkResult=gsxtAjax_getDetailFromDB(ctx,false,area,keyword);
	     
	     if(checkResult.state==1){
	    	
	    	 gsxtAjax_windowOpenPostDetail(ctx+"/modules/gsxt/gsxtDetail",detailName,JSON.stringify(checkResult.aicFeedJson));
		      
		      //调试时用
	          /*setTimeout(function(){
	        	  gsxtAjax_windowOpenPostDetail(ctx+"/modules/gsxt/gsxtJson",detailName,JSON.stringify(checkResult.aicFeedJson));
	          },2000);*/
	    	 
	     }
	     
}
//筛选div样式
function gsxtOT_searchConStyle(){

	     var areas='';
	
	     var areaArray=gsxtOT_areaArray();
		    
		 for(var i  in areaArray){
			   
			     if(i.indexOf("China")!=-1){
			    	   if(areas !=''){
			    	    	 areas +="</li>";
			    	   }
			    	   areas +="<li data-name='"+i+"' style='margin-top:10px;margin-bottom:10px;'>"
			    	    	            +"<span style='color:#337ab7;font-weight:bold;margin-right:5px'>"+areaArray[i]+"</span>";
			       }else{
			    	       areas +="<span data-name='"+i+"'>"+areaArray[i]+"</span>";
			       }
		 }
		 
		 $("#areaList").children("ul").html(areas+"</li>");
		 
		 
		//筛选动作
		 gsxtOT_searchConAct();
		 
		 //关闭、隐藏div
		 $("#close_areaList").click(function(){
			 $("#areaList").hide();
		 })
		 
		 //显示div
		 $("#open_areaList").click(function(){
			  $("#areaList").show();
		 })
}
//筛选动作
function gsxtOT_searchConAct(){
	
		 
		 //地区筛选
	    //地区选中
		$("#areaList").on("click","span[data-name]",function(){
			   
			    
			    if($(this).attr("class")=="area_selectedStyle"){//已选中状态，单击，取消
			    	   $(this).attr("class","area_unSelectedStyle");
                       $("#chose_areas").children("span[data-name='"+$(this).attr("data-name")+"']").remove();
			    	  if($("#chose_areas").children("span[data-name]").length==0){
			    		    $("#areaList").children("span[data-name='searchArea_all']").click(); 
			    	  }
			    }else{  //没选中状态，单击，选中
			    	  if($(this).attr("data-name")=="searchArea_all"){
				           gsxtOTCommon_selected($(this),"areaList","area_selectedStyle","area_unSelectedStyle");
				           $("#chose_areas").html("<span data-name='searchArea_all' class='area_selectedStyle' style='margin-left:5px;padding:0px 5px;cursor:default'>全部</span>");
				      }else{
				    	   if($("#chose_areas").children("span").length<5){
				    		   
				    		      $(this).attr("class","area_selectedStyle");
					    	      if($("#areaList span[data-name='searchArea_all']").attr("class")=="area_selectedStyle"){
					    		      $("#areaList span[data-name='searchArea_all']").attr("class","area_unSelectedStyle");
					    	      }
					    	      if($("#chose_areas").children("span[data-name='searchArea_all']").length==1){
					    	    	  $("#chose_areas").children("span[data-name='searchArea_all']").remove();
					    	      }
					    	   
					    	      $("#chose_areas").append("<span data-name='"+$(this).attr("data-name")+"' class='"+$(this).attr("class")+"' style='margin-left:5px;padding:0px 5px;position:relative;cursor:default'>"+$(this).text()+"<span class='glyphicon glyphicon-remove' style='font-size:1px;color:black;z-index:2;position:absolute;left:83%;top:-29%;cursor:pointer;display:none'></span></span>");
				    	   }
				      }
			    }
		})
		 
	    //移入
		$("#areaList").on("mouseenter","span[data-name]",function(){
			 if($(this).attr("class")=="area_unSelectedStyle"){
			      $(this).attr("class","area_mouseenter");
			 }
	    })
	    
	    $("#chose_areas").on("mouseenter","span[data-name]",function(){
	    	 if($(this).attr("data-name")!='searchArea_all'){
	    		    $(this).children("span").show();
	    	 }
	    })
	    
	    $("#chose_areas").on("click","span[data-name] >span",function(){
	    	    $("#areaList >ul").find("span[data-name='"+$(this).parent().attr("data-name")+"']").click();
	    })
		    
		 //移出
		 $("#areaList").on("mouseleave","span[data-name]",function(){
			  if($(this).attr("class")=="area_mouseenter"){
			       $(this).attr("class","area_unSelectedStyle");
			  }
		})
		
		$("#chose_areas").on("mouseleave","span[data-name]",function(){
	    	 if($(this).attr("data-name")!='searchArea_all'){
	    		    $(this).children("span").hide();
	    	 }
	    })
		
		
		//状态筛选
	    //状态选中
		$("#chose_status").on("click","span[data-name]",function(){
			  gsxtOTCommon_selected($(this),"chose_status","status_selectedStyle","status_unSelectedStyle");
			  
		})
		 
	    //状态移入
		$("#chose_status").on("mouseenter","span[data-name]",function(){
			 if($(this).attr("class")=="status_unSelectedStyle"){
			      $(this).attr("class","status_mouseenter");
			 }
	    })
		    
		 //状态移出
		 $("#chose_status").on("mouseleave","span[data-name]",function(){
			  if($(this).attr("class")=="status_mouseenter"){
			       $(this).attr("class","status_unSelectedStyle");
			  }
		})
	
}
//单个选中
function gsxtOTCommon_selected(self,scope,selectedClass,unSelectedClass){
	
	    var each='';
	    for(var i=0;i<$("#"+scope).find("span[data-name]").length;i++){
  	  
	            each=$("#"+scope).find("span[data-name]").eq(i);
	           $(each).attr("class",unSelectedClass);
	  
       }

	   $(self).attr("class",selectedClass);
	
}
//搜索
function gsxtOTCommon_search(){
	
	     var ctx='',loginName='';
	     var searchAreas='',searchStatus='',searchCompany='';
	     var searchResult='';
	     var noKeys='';
	     var eachArea='';

	     
	     ctx=$("#ctx").val();
         loginName=$("#fat-menu").find("span[id='loginName']").text();
	     noKeys="<tr><td colspan='8'>没有搜索到相关信息</td></tr>";
         
	     
	     //获取搜索地区，可多选
	     for(var i=0;i<$("#chose_areas").children("span[data-name]").length;i++){
	    	    eachArea=$("#chose_areas").children("span[data-name]").eq(i);
	    	    searchAreas +=$(eachArea).attr("data-name");
	    	    if(i<($("#chose_areas").children("span[data-name]").length-1)){
	    	    	searchAreas +=",";
	    	    }
	     }
	     
	     //获取任务状态
	     searchStatus=$("#chose_status").children("span[class='status_selectedStyle']").attr("data-name");

	     //获取公司
	     searchCompany=$("#searchCompany").val().trim();
	     
	   //  console.log("searchAreas---"+searchAreas+"     searchStatus---"+searchStatus+"    searchCompany---"+searchCompany);
	     if($("#areaList").css("display")!="none"){
	    	   $("#close_areaList").click();
	     }
	     
	     searchResult=gsxtOTAjax_getGsxtTaskByCon(ctx,false,loginName,searchAreas,searchStatus,searchCompany);	
	
	     
	    
	     if(searchResult=="isError"){
	    	    alert("服务器中断");
	    	    return;
	     }else{
	    	    
	    	    if(searchResult=='' ||searchResult.length==0){
	    	    	  $("table >tbody").html(noKeys);
	    	    	  $("#page").html("");//分页
	    	    }else{
	    	    	  gsxtOTCommon_productList(searchResult);
	    	    }
	     }
	
}


