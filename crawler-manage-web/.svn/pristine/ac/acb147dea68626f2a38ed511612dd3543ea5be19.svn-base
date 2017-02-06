$(function(){
	
	   feedbackInfoArray=new Array();
	
	  //查找任务类型
	  feedback_getTaskType();
	
	
	  $("#ownerTaskType").on("change",function(){
		     
		      feedback_list($(this));
	  })

	  var feedbackType=localStorage.getItem("feedbackType");
	  if(feedbackType!='none'){
		  $("#ownerTaskType").children("option[value='"+feedbackType+"']").attr("selected","selected");
		  localStorage.setItem("feedbackType","none");
	  }
	  
	  $("#ownerTaskType").change();
})
//查找任务类型
function feedback_getTaskType(){
	
	     var loginName='',checkResult='',ctx='',sites='',options='';
	     
	     ctx=$("#ctx").val();;
	     loginName=$("#fat-menu").find("span[id='loginName']").text();
	
	     checkResult=ownerTaskAjax_getAllTask(ctx,false,loginName);
	    
	     sites=leftMenuOT_checkSiteEnable(ctx);
	    
	     if(checkResult=="isError"){
	    	   alert("服务器中断!");
	    	   return;
	     }
	     
	     $.each(checkResult,function(index1,task){
	    	   $.each(sites,function(index2,site){
	    		      if(task.taskType==site.ownerTaskType){
	    		    	    options +="<option value='"+task.taskType+"'>"+task.taskType+"</option>";
	    		    	    return false;
	    		      }
	    	   })
	     })
	     
	     
	    $("#ownerTaskType").html(options);
	
}
//根据任务类型，列出反馈信息
function feedback_list(type){
	
	     var ctx='',otType='',feedbackResult='',trs='',loginName='',keyword='';
	     var onePage_content='',onePageShowNum='',showPageNum='';
	     
	     ctx=$("#ctx").val();
	     otType=$(type).children("option:selected").text();
	     loginName=$("#fat-menu").find("span[id='loginName']").text();
	     
	     onePageShowNum=20;  //一页显示条数
	     showPageNum=5;     //一次显示的页数
	     
	     feedbackInfoArray.length=0;//清空数组
	
	     feedbackResult=feedbackAjax_getFeedback(ctx,false,otType,loginName);
	     
	    // console.log(JSON.stringify(feedbackResult))
	    
	     if(feedbackResult=="isError"){
	    	   alert("服务器中断");
	    	   return;
	     }
	    
	     
	     $.each(feedbackResult,function(index,fbr){
	    	     if(fbr.ownerTaskType=="工商网"){
	    	    	   keyword=feedback_areaArray(fbr.ownerTaskContent);
	    	     }else{
	    	    	   keyword=fbr.ownerTaskContent;
	    	     }
	    	 
	    	     //console.log(JSON.stringify(fbr));
	    	      trs +="<tr>"
	    	    	          +"<td>"+(index+1)+"</td>"
	    	    	          +"<td style='text-align:left'><nobr>"+keyword+"</nobr></td>"
	    	    	          +"<td><nobr>"+commonSelf_timeStampConvertToTime(fbr.taskCreateDate)+"</nobr></td>"
	    	    	          +"<td style='text-align:left'>"+fbr.feedbackInfo+"</td>"
	    	    	          +"<td>"+fbr.state+"</td>"
	    	    	          +"<td>"+feedback_state(fbr.state,fbr.ownertTaskId,fbr.timeTaskId)+"</td>"
	    	    	     +"</tr>";
	    	      
	    	      
	    	      if((index+1)%onePageShowNum==0){
	    	    	    feedbackInfoArray.push(trs);
	    	    	    trs='';
	    	        }
	     })
	     
	    
	     
	     if(feedbackInfoArray.length>0 || trs!=''){
                
	    	      feedbackInfoArray.push(trs);
	    	    
	    	       //分页
		           $("#page").html("");
		        
		          //获取首页内容
		          onePage_content=feedbackInfoArray[0];
		        
		          //显示首页内容
		          feedback_showPage(onePage_content)
		        	
		         //调用commonSelf_pageStyle
		         commonSelf_pageStyle(onePageShowNum,showPageNum,feedbackResult.length,"isNull","page",feedback_pageCallback);
	     }else{
	    	    trs="<tr><td colspan='6'>没有相关反馈信息</td></tr>";
	    	    $("table >tbody").html(trs);
	    	    $("#page").html("");
	    	    
	     }
	     
	       
	     
}
function feedback_pageCallback(){
	
	     var currentPage='',currentPage_content='';
     
         //获取当前页
         currentPage=parseInt($("#page #currentPage").text());
     
         //获取当前页内容
         currentPage_content=feedbackInfoArray[currentPage-1];
     
         //显示当前页
     
         feedback_showPage(currentPage_content);
	
	
	
}
//显示内容
function feedback_showPage(content){
	
	     var ctx='',otType='',loginName='';
	
	    ctx=$("#ctx").val();
        otType=$("#ownerTaskType").children("option:selected").text();
        loginName=$("#fat-menu").find("span[id='loginName']").text();
	
	    $("table >tbody").html(content);
     
        feedback_reset(ctx,loginName,otType);
	
	
	
}
//重置标准
function feedback_state(state,ownerTaskId,timeTaskId){
	
	
	     if(state==2 || state==3 || state==4 || state==5 || state==8 || state==9){
	    	   return "<span data-ownertask='"+ownerTaskId+"' data-timetask='"+timeTaskId+"' style='cursor:pointer;color:blue'><nobr>重置</nobr></span>";
	     }else{
	    	   return "---";
	     }
}
//地区数组json
function feedback_areaArray(content){
	
	    var areaArray={
	             "NorthChina":"华北", 	       "Beijing":"北京",      "Tianjin":"天津",      "Hebei":"河北",          "Shanxi":"山西",     "Neimenggu":"内蒙古",
	             "NorthEastChina":"东北",      "Liaoning":"辽宁",     "Jilin":"吉林",        "Heilongjiang":"黑龙江",
	             "EastChina":"华东", 	       "Shanghai":"上海",     "Jiangsu":"江苏",      "Zhejiang":"浙江",       "Anhui":"安徽",      "Fujian":"福建",        "Jiangxi":"江西",     "Shandong":"山东",
	             "SouthChina":"华南",   	       "Guangdong":"广东",    "Guangxi":"广西",      "Hainan":"海南",
	             "CentralChina":"华中", 	       "Henan":"河南",        "Hubei":"湖北",        "Hunan":"湖南",
	             "SouthWestChina":"西南",       "Chongqing":"重庆",   "Sichuan":"四川",      "Guizhou":"贵州",        "Yunnan":"云南",     "Xizang":"西藏",
	             "NorthWestChina":"西北 ", 	    "Shaanxi":"陕西",     "Gansu":"甘肃",        "Qinghai":"青海",        "Ningxia":"宁夏",    "Xinjiang":"新疆"              
	      }
	    
	    var area='',name='';
	    
	    area=content.split("---")[0];
	    name=content.split("---")[1];
	    
	    
	    return areaArray[area]+"："+name;
}
//重置 任务
function feedback_reset(ctx,loginName,taskType){
	
	     $("table").on("click","tbody >tr >td>span[data-ownertask]",function(){
	    	        var ownerTaskId='',timeTaskId='',result='',otType='';
	    	        
	    	        ownerTaskId=$(this).attr("data-ownerTask");
	    	        timeTaskId=$(this).attr("data-timetask");
	    	        
	    	        otType=$("#ownerTaskType").children("option:selected").text();
	    	        
	    	        result=feedbackAjax_reset(ctx,false,loginName,otType,ownerTaskId,timeTaskId);
	    	        
                    if(result=="isError"){
                    	  alert("服务器中断");
                    	  return;
                    }
                    
                    if(result.id==1){
                    	   $(this).removeAttr("style");
                    	   $(this).html("<nobr>已重置</nobr>");
                    }else{
                    	   $(this).html("<nobr>重置失效，请联系客服务。</nobr>");
                    	   $(this).removeAttr("style");
                    }
                    
	     })
}