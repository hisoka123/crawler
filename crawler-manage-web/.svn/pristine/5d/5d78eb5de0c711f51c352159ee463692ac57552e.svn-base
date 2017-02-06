//获取全部站点isEnable=1
function wikiAjax_getSite(ctx){
	   
	     var getSiteResult='';
	
	     $.ajax({
	    	    url:ctx+"/tools/getSite",
	    	    async:false,
	    	    success:function(data){
	    	    	getSiteResult=data;
	    	    },
	    	    error:function(){
	    	    	alert("服务器断开\n方法名：wiki_getSite/wiki-ajax.js");
	    	    	getSiteResult="isError";
	    	    }
	     })
	     
	     return getSiteResult;
}
//获取某个站点下全部接口
function wikiAjax_getSiteInterface(ctx,nodesGrade,id){
	
	     var params='';
	     var getSiteFirstResult='';
		    
		 if(nodesGrade=='sonNodes'){
			   params={
		    			 site_id:id,
		    			 nodesGrade:nodesGrade
		    	       }
		 }else if(nodesGrade=='otherNodes'){
		       params={
		    			  parent_id:id,
		    			  nodesGrade:nodesGrade
		       }
		 }else{
			   return;
		 }
	     
	     $.ajax({
  	         url:ctx+"/tools/getChildNodes",
  	         data:params,
  	         type:"post",
  	         async:false,
  	         success:function(data){
  	        	   getSiteFirstResult=data;
  	         },
  	         error:function(){
  	    	       alert("服务器断开\nwikiAjax_getSiteFirst/wiki-ajax.js");
  	    	     getSiteFirstResult="isError";
  	         }
       })
       
       return getSiteFirstResult;
}
//新增文档
function wikiAjax_addInterfaceDoc(ctx,content,title,name,params,path,titleIcon,requestMethod,site_id){
	
	    var submitDocResult='';
	   $.ajax({
		      url:ctx+"/tools/addInterfaceDoc",
		      data:{
		    	  
		    	     content:content,
		    	     title:title,
		    	     name:name,
		    	     params:params,
		    	     path:path,
		    	     titleIcon:titleIcon,
		    	     requestMethod:requestMethod,
		    	     site_id:site_id,
		    	     time:new Date()
		    	     
		      },
		      async:false,
		      type:"post",
		      success:function(data){
		    	      submitDocResult=data;
		      },
		      error:function(){
		    	  alert("服务器断开\nwikiAjax_submitDoc/wiki-ajax.js"); 
		      }
		   
	   })
	   return submitDocResult;
}
//更新文档
function wikiAjax_updateInterfaceDoc(ctx,contentID,content,baseID,title,name,params,path,titleIcon,requestMethod,site_id){
	     
	     var updateInterfaceDocResult='';
	     $.ajax({
	    	    url:ctx+"/tools/updateInterfaceDoc",
	    	    data:{
	    	    	  contentID:contentID,
	    	    	  content:content,
	    	    	  baseID:baseID,
	    	    	  title:title,
	    	    	  name:name,
	    	    	  params:params,
	    	    	  path:path,
	    	    	  titleIcon:titleIcon,
	    	    	  requestMethod:requestMethod,
	    	    	  site_id:site_id,
	    	    	  time:new Date()
	    	    },
	    	    type:"post",
	    	    async:false,
	    	    success:function(data){
	    	    	updateInterfaceDocResult=data;
	    	    },
	    	    error:function(){
	    	    	updateInterfaceDocResult="isError";
	    	        alert("服务器断开\n wikiAjax_updateInterfaceDoc/wiki-ajax.js");
	    	    }
	     })
	     
	     return updateInterfaceDocResult;
}
//获取单个接口文档内容,
function wikiAjax_getInterface(ctx,wdb_id){
	
	    var interfaceResult="";
	    $.ajax({
	    	    url:ctx+"/tools/getInterface",
	            data:{
	            	wdb_id:wdb_id
	            },
	            async:false,
	            type:"post",
	            success:function(data){
	            	    interfaceResult=data;
	            },
	            error:function(){
	            	alert("服务器断开\nwikiAjax_getInterface/wiki-ajax.js"); 
	            	interfaceResult="isError";
	            }
	    })
	
	    return interfaceResult;
	
}
//根据wikiDocBase中id号，获取某个具体接口属性
function wikiAjax_getSingleWikiDocBase(ctx,id){
	
	    var singleWikiDocBaseResult='';
	    
	    $.ajax({
	         url:ctx+"/tools/getSingleWikiDocBase",
	    	 data:{
	    		   id:id
	    	 },
	    	 async:false,
	    	 type:"post",
	    	 success:function(data){
	    		 singleWikiDocBaseResult=data;
	    	 },
	    	 error:function(){
	    		 alert("服务器断开\nwikiAjax_getSingleWikiDocBase/wiki-ajax.js");  
	    		 singleWikiDocBaseResult="isError";
	    	 }
	    })
	
	    return singleWikiDocBaseResult;
}
//根据id，获取某个站点的信息(site表中)
function wikiAjax_getSingleSite(ctx,id){
	     
	     var singleSiteResult='';
	     $.ajax({
	    	 
	    	    url:ctx+"/tools/getSingleSite",
	    	    data:{
	    	    	 id:id,
	    	    },
	    	    async:false,
	    	    type:"post",
	    	    success:function(data){
	    	    	singleSiteResult=data;
	    	    },
	    	    error:function(){
	    	    	alert("服务器断开\nwikiAjax_getSingleSite/wiki-ajax.js"); 
	    	    	singleSiteResult="isError";
	    	    }
	     })
	
         return singleSiteResult;	
}
/*//导出单个接口文档pdf
function wikiAjax_singleExportPDF(ctx,wikiDocBase_id,saveDirPath){
	
	    var singleExportPDFResult='';
	    $.ajax({
	    	
	    	    url:ctx+"/tools/singleExportPDF",
	    	    data:{
	    	    	wikiDocBase_id:wikiDocBase_id,
	    	    	saveDirPath:saveDirPath
	    	    },
	    	    timeout:300000,
	    	    async:false,
	    	    type:"post",
	    	    success:function(data){
	    	    	  singleExportPDFResult=data;
	    	    },
	    	    error:function(){
	    	    	 alert("服务器断开\nwikiAjax_singleExportPDF/wiki-ajax.js"); 
	    	    	 singleExportPDFResult="isError";
	    	    }
	    })
	    
	    return singleExportPDFResult;
}
//批量导出单个接口文档pdf格式
function wikiAjax_bulkExportPDF(ctx,exportArray,saveDirPath,bulkExportType){
	     
	     var bulkExportPDFResult='';
	     $.ajax({
	    	    url:ctx+"/tools/bulkExportPDF",
	    	    data:{
	    	    	exportArray:exportArray,
	    	    	saveDirPath:saveDirPath,
	    	    	bulkExportType:bulkExportType
	    	    },
	    	    timeout:300000,
	    	    async:false,
	    	    type:"post",
	    	    success:function(data){
	    	    	bulkExportPDFResult=data;
	    	    },
	    	    error:function(){
	    	    	alert("服务器断开\nwikiAjax_bulkExportPDF/wiki-ajax.js"); 
	    	    	bulkExportPDFResult="isError";
	    	    }
	     })
	     
	     return bulkExportPDFResult;
}
//批量导出（带书签）接口文档
function wikiAjax_bulkExportWithBookMark(ctx,exportArray,saveDirPath,bulkExportType){
	
	    var bulkExportWithBookMarkResult='';
        $.ajax({
   	         url:ctx+"/tools/bulkExportWithBookMark",
   	         data:{
   	    	      exportArray:exportArray,
   	    	      saveDirPath:saveDirPath,
   	    	      bulkExportType:bulkExportType
   	         },
   	         async:false,
   	         type:"post",
   	         success:function(data){
   	        	bulkExportWithBookMarkResult=data;
   	         },
   	         error:function(){
   	    	      alert("服务器断开\nwikiAjax_bulkExportPDF/wiki-ajax.js"); 
   	    	      bulkExportWithBookMarkResult="isError";
   	         }
        })
    
    return bulkExportWithBookMarkResult;
}*/
//api接口测试
function wikiAjax_apitest(ctx,ajaxParam,logUid){
	
	     var apitestResult='';
	     
	     $.ajax({
	    	    url:ctx+"/tools/apitest/play",
	    	    data:{
	    	    	   ajaxParam:encodeURI(encodeURI(ajaxParam)),
	    	    	   logback:logUid
	    	    },
	    	    type:"get",
	    	    async:true,
	    	    dataType:"text",
	    	    success:function(data){
	    	    	  // apitestResult=data;
	    	
	    	    	  $("#responseResult").html("<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap'>"
		                                               +data
		                                         +"</pre>");
                       prettyPrint();
	    	    },
	    	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	    	  apitestResult=XMLHttpRequest+"<br>"+textStatus+"<br>"+errorThrown;
	    	    	  
	    	    	  $("#responseResult").html("<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap'>"
		                                              +responseResult
		                                        +"</pre>");
                      prettyPrint();
	    	    }
	     })
	     
	     // return apitestResult;	 
}

//导出接口文档，pdf格式
function wikiAjax_exportPDF(ctx,wikiDocBase_id,bulk){
	
	     var form=$("<form>");
	     form.attr("style","dispaly:none");
	     form.attr("target","_blank");
	     form.attr("method","get");
	     form.attr("action",ctx+"/tools/exportPDF");
	
	     var input1=$("<input>");
	     input1.attr("type","hidden");
	     input1.attr("name","wikiDocBaseID");
	     input1.attr("value",wikiDocBase_id);
	     
	     var input2=$("<input>");
	     input2.attr("type","hidden");
	     input2.attr("name","bulk");
	     input2.attr("value",bulk);
	     
	     $("body").append(form);
	     form.append(input1);
	     form.append(input2);
	     
	     form.submit();
	     form.remove();
	
}
//删除某个接口
function wikiAjax_deleteAPI(ctx,contentID){
	
	     var deleteAPIResult='';
     
         $.ajax({
    	       url:ctx+"/tools/deleteApi",
    	       data:{
    	    	    contentID:contentID
    	       },
    	       type:"post",
    	       async:false,
    	       dataType:"json",
    	       success:function(data){
    	    	     deleteAPIResult=data;
    	       },
    	       error:function(){
    	    	   deleteAPIResult="isError";
    	    	   alert("服务器断开\n wikiAjax_deleteAPI/wiki-ajax.js");
    	      }
        })
     
        return deleteAPIResult;
}
