$(function(){
	
       ownerTaskCommon_allTask();
       
       //定义全局变量
       localStorage.setItem("feedbackType","none");
       
})

//生成全部任务总表
function ownerTaskCommon_allTask(){
	
	     var allTask='',ctx='',trs='',loginName='',taskType='',taskTypeNum='';
	     var siteJsonObject='',order_index=0;
	     
	     
	     ctx=$("#ctx").val();
	     loginName=$("#fat-menu").find("span[id='loginName']").text();
	     
	     allTask=ownerTaskAjax_getAllTask(ctx,false,loginName);
	     
	     
	     if(allTask.length==0){
	    	   trs='';
	     }else{
	    	   siteJsonObject=leftMenuOT_checkSiteEnable(ctx);
	           $.each(allTask,function(index,task){
	        	      for(var i=0;i<siteJsonObject.length;i++){
	        	    	    if(task.taskType==siteJsonObject[i].ownerTaskType){
	        	    	    	 trs +="<tr>"
	    	    	                   +"<td>"+(++order_index)+"</td>"
	    	    	                   +"<td>"+task.taskType+"</td>"
	    	    	                   +"<td>"+task.taskSuccessNum+"</td>"
	    	    	                   +"<td>"+task.taskNum+"</td>"
	    	    	                   +"<td><a href='"+siteJsonObject[i].ownerTaskUrl+"'>查看</a></td>"
	    	    	               +"</tr>";
	        	    	    }  
	        	      }
	           })
	     }
	     
	     if(trs==''){
	    	 trs="<tr><td colspan='5' align='left'>暂无任务</td></tr>";
	     }
	     
	     $("table >tbody").html(trs);
}

