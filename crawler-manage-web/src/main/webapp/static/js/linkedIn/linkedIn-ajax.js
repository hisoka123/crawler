//linkedIn搜索
function linkedIn_getSearchResult(person){
	/*alert("测试linkedIn_getSearchResult：\n"
			+"person:"+person);*/
	
	var searchResult;
	
	$.ajax({
		url:ROOTPATH+"/modules/linkedIn/getSearchResult",
		data:encodeURI(encodeURI("person="+person)),
		async:false,
		success:function(result){
	          searchResult=result;  
	          
	        //将结果保存至indexedDB--> searchResultsDB数据库--> linkedInSearchResultsTab数据仓库中
	          for(var i=0;i<searchResult.length;i++){
	    	         searchResult[i].personIndex="linkedIn_"+person+"_"+$.md5(searchResult[i].profile);
	          }
	         indexedDB_saveSearchResults("linkedInSearchResultsTab",searchResult);
		},
		error:function(){
			  searchResult="error";
		}
		
	})
	//alert("测试linkedIn_getSearchResult："+searchResult);
	return searchResult;
}
//获取人物详情
function linkedIn_getLinkedInDetail(rootPath,profileURL){
	 /*alert("测试LinkedIn获取人物详情linkedIn_getLinkedInDetail:\n"+
			"rootPath="+rootPath+"\n"+
			"profileURL="+profileURL);*/
	 var getLinkedInDetail_result;
    $.ajax({
    	   url:rootPath+"/modules/linkedIn/getLinkedInDetails",
    	   data:"profileURL="+encodeURIComponent(profileURL),
    	   async:false,
    	   type:"post",
    	   success:function(data){
    		   getLinkedInDetail_result=data;
    	   },
    	   error:function(){
    		   getLinkedInDetail_result="isError";
    	   }
    })
    
   // alert("测试LinkedIn获取人物详情linkedIn_getLinkedInDetail结果：\n"+getLinkedInDetail_result);
    return getLinkedInDetail_result;
    
}


















/*--------------------------------------------------------*/
/*--------------------------------------------------------*/
/*--------------------------------------------------------*/
/*--------------------------------------------------------*/
/*--------------------------------------------------------*/
/*--------------------------------------------------------*/






//(1)获取搜索人物列表
function linkedIn_getCrawlerPersons(rootPath,crawlerPerson){
	/*alert("测试LinkedIn搜索人物：\n"
			+"rootPath:"+rootPath+"\n"
			+"crawlerPerson:"+crawlerPerson);*/
	
	var getCrawlerPersons_result;
	
	
	$.ajax({
		url:rootPath+"/linkedIn/crawlerSearchGet",
		data:encodeURI(encodeURI("crawlerPerson="+crawlerPerson)),
		async:false,
		success:function(result_1){
	          getCrawlerPersons_result=result_1;  		
		},
		error:function(){
			  getCrawlerPersons_result="error";
		}
		
	})
	
	//alert("测试LinkedIn搜索人物结果："+getCrawlerPersons_result);
	return getCrawlerPersons_result;
	
	
}

//获取人物详情
function linkedIn_getCrawlerDetails(rootPath,profileURL){
	 /*alert("测试LinkedIn获取人物详情linkedIn_getCrawlerDetails:\n"+
			"rootPath="+rootPath+"\n"+
			"profileURL="+profileURL);*/
	 var getCrawlerDetails_result;
    $.ajax({
    	   url:rootPath+"/linkedIn/getCrawlerDetails",
    	   data:"profileURL="+encodeURIComponent(profileURL),
    	   async:false,
    	   success:function(result_1){
    		    getCrawlerDetails_result=result_1;
    	   },
    	   error:function(){
    		   getCrawlerDetails_result="error";
    	   }
    })
    
   // alert("测试LinkedIn获取人物详情linkedIn_getCrawlerDetails结果：\n"+getCrawlerDetails_result);
    return getCrawlerDetails_result;
    
}