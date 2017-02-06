//从接口处获取详情
function fahaiccAjax_getDetailFromInterface(ctx,async,keyword,callFunName){
	    
	     var getDetailFromInterfaceResult='';
	     $.ajax({
	    	      url:ctx+"/api/fahaicc/getDataOnce",
	    	      type:"post",
	    	      async:async,
	    	      data:{
	    	    	   q:keyword,
	    	    	   pg:1,
	    	    	   time:new Date()
	    	      },
	    	      success:function(data){
	    	    	     if(!async){
	    	    	    	 getDetailFromInterfaceResult=data;
	    	    	     }   
	    	      },
	    	      error:function(){
	    	    	  if(!async){
	    	    	    	 getDetailFromInterfaceResult="isError";
	    	    	     } 
	    	      }
	     })
	     
	     if(!async){
	    	   return getDetailFromInterfaceResult;
	     }
}
//以post方式向详情页传递参数
function fahaiccAjax_windowOpenPostDetail(url,name,content,typeName,type,keywordName,keyword){
	 
	     var form=$("<form>");
	     form.attr("style","display:none");
	     form.attr("method","post");
	     form.attr("target","_blank");
	     form.attr("action",url);
	     
	     var input1=$("<input>");
	     input1.attr("style","display:none");
	     input1.attr("name",name);
	     input1.attr("value",content);
	     
	     var input2=$("<input>");
	     input2.attr("style","display:none");
	     input2.attr("name",typeName);
	     input2.attr("value",type);
	     
	     var input3=$("<input>");
	     input3.attr("style","display:none");
	     input3.attr("name",keywordName);
	     input3.attr("value",keyword);
	     
	     
	     $("body").append(form);
	     form.append(input1);
	     form.append(input2);
	     form.append(input3);
	     
	     form.submit();
	     form.remove();
}
//以post方式传递调试内容
function fahaiccAjax_windowOpenPostInterface(url,data,dataContent,error,errorContent){
	 
	     var form=$("<form>");
	     form.attr("style","display:none");
	     form.attr("method","post");
	     form.attr("target","_blank");
	     form.attr("action",url);
	     
	     var input1=$("<input>");
	     input1.attr("style","display:none");
	     input1.attr("name",data);
	     input1.attr("value",dataContent);
	     
	     
	     var input2=$("<input>");
	     input2.attr("style","display:none");
	     input2.attr("name",error);
	     input2.attr("value",errorContent);
	     
	     $("body").append(form);
	     form.append(input1);
	     form.append(input2);
	     
	     form.submit();
	     form.remove();
}
//检查企业是否存，若存在，从数据库获取企业详情;不存在，直接返回
function fahaiccAjax_checkIsExist(ctx,async,type,content,callFunName){
	
	     var getDetailFromDBResult='';
	     
	     $.ajax({
	    	 
	    	     url:ctx+"/modules/fahaicc/checkIsExist",
	    	     type:"post",
	    	     async:async,
	    	     data:{
	    	    	   type:type,
	    	    	   content:content,
	    	    	   time:new Date()
	    	     },
	    	     success:function(data){
	    	    	 
	    	    	   if(!async){
	    	    		   getDetailFromDBResult=data; 
	    	    	   }
	    	     },
	    	     error:function(){
	    	    	   if(!async){
	    	    		   getDetailFromDBResult="isError";
	    	    	   }
	    	     }
	    	 
	     })
	     
	     if(!async){
	    	  return getDetailFromDBResult;
	     }
	
}