$(function(){
         
	       //获取左侧任务菜单项
           ownerTaskAjax_getAllTask($("#ctx").val(),true,$("#fat-menu").find("span[id='loginName']").text(),"leftMenuOT_leftMenu");
           
})
//生成左侧任务菜单
function leftMenuOT_leftMenu(ownerTaskList){
	
	     var ctx='',menu='',url='',li='',taskType='';
	     var menu_start='',menu_center='',menu_end='';
	     var siteJsonObject='';
	     
	     ctx=$("#ctx").val();
	     url=window.location.href;
	    
	      menu_start='['
	    	            +'{'
	    	                +'"text":"我的任务",'
	    	                +'"href":"'+ctx+'/ownerTask/homeTask"'     
	    	            +'}';
	      
	      
	     if(ownerTaskList.length>0){
	    	 
	    	   siteJsonObject=leftMenuOT_checkSiteEnable(ctx);
	    	   
	           $.each(ownerTaskList,function(index1,task){
	        	      
	        	     for(var i=0;i<siteJsonObject.length;i++){
	        	    	   if(task.taskType==siteJsonObject[i].ownerTaskType){
       	        	               menu_center +='{'
   	        	                         +'"text":"'+task.taskType+'",'
   	        	                         +'"href":"'+siteJsonObject[i].ownerTaskUrl+'"'
   	        	                       +'},';  
       	                    }
	        	     }
	           })
	           
	           if(menu_center !=''){
	        	     menu_center =','+menu_center.substring(0,menu_center.length-1);
	           }
	     }
          
	     menu_end=',{'
	    	        +'"text":"反馈信息",'
	    	        +'"href":"'+ctx+'/ownerTask/feedback"'
	    	     +'}]';

		 /*menu +=',{'
			+'"text":"失信网",'
			+'"href":"'+leftMenuOT_href("失信网")+'"'
			+'}';*/
	     menu =menu_start+menu_center+menu_end;

	     $("#left_menu").treeview({
	    	    levels:1,
	    	    enableLinks: true,
	    	    data:menu
	     })
	     
	     //使当前面下的任务菜单处于选中状态
         for(var i=0;i<$("#left_menu >ul").children("li").length;i++){
      	   
      	           li=$("#left_menu >ul").children("li").eq(i);
      	           
      	           if(url.indexOf($(li).children("a").attr("href"))!=-1){
      	        	      $(li).addClass("node-selected");
      	        	      $(li).attr("style","color:white;background-color:#428BCA;");
      	           }
         }
	     
	     
	     $("#left_menu").on("nodeSelected",function(event,data){
                   window.location=data.href;
         })
	
}
//任务链接
function leftMenuOT_href(taskType){
	 
	   /*  var href='#',ctx='';
	     
	     ctx=$("#ctx").val();
	
	    if(taskType=="工商网"){
             href=ctx+"/ownerTask/gsxtTask";	   
	    }else if(taskType=="反馈信息"){
	    	 href=ctx+"/ownerTask/feedback";
	    }else if(taskType=="人法网"){
			 href=ctx+"/ownerTask/peoplecourt";
		}else if(taskType=="失信网"){
			 href=ctx+"/ownerTask/dishonesty";
		}else if(taskType=="贷联盟"){
			 href=ctx+"/ownerTask/creditUnion";
		}else if(taskType=="法海网"){
			 href=ctx+"/ownerTask/fahaiccTask";
		}
	    
	    return  href;*/
}
//检查站点任务可用性
function leftMenuOT_checkSiteEnable(ctx){
	
	     var site='',siteJson='';
	     
	     site=wikiAjax_getSite(ctx);
	     
	     for(var i=0;i<site.length;i++){
	    	     siteJson +='{"ownerTaskType":"'+site[i].name+'","ownerTaskUrl":"'+ctx+site[i].ownerTaskUrl+'"},'
	    	     
	     }
	     
	     siteJson ='['+siteJson.substring(0,siteJson.length-1)+']';
	   // console.log(siteJson);
	     return JSON.parse(siteJson);
}
