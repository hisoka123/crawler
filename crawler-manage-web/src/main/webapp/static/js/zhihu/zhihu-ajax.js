//(1)搜索结果
function zhihu_getSearchResult(person){
	 
	  var  searchResult;

	  $.ajax({
		   url:ROOTPATH+"/modules/zhihu/getSearchResult",
		   data:encodeURI(encodeURI("person="+person)),
		   async:false,
	       success:function(result){
	    	       searchResult=result;
	    	       
	    	       //将结果保存到indexedDB-->searchResultsDB-->zhihuSearchResultsTab
	    	       for(var i=0;i<searchResult.length;i++){
  	    	            searchResult[i].personIndex="zhihu_"+person+"_"+$.md5(searchResult[i].profile);
  	               }
	    	       indexedDB_saveSearchResults("zhihuSearchResultsTab",searchResult);

	       },
	       error:function(){
	    	      searchResult="error";
	       }		  
	  });	
	 //alert("zhihu_getSearchResult返回结果为："+searchResult);
	 return searchResult;	
}
/**
 * (2)人物详情
 * profile:  个人主页链接
 */
function zhihu_getDetails(profile){
	    
	  var detailsResult='';
	  
	  $.ajax({
		     url:rootPath+"/zhihu/getDetails",
		     data:'url='+profile,
		     async:false,
		     success:function(result){
		    	     detailsResult=result;
		    	     
		    	     //将结果保存到indexedDB-->searchResultsDB-->zhihuDetailsTab
		    	       for(var i=0;i<detailsResult.length;i++){
	  	    	             detailsResult[i].personIndex="zhihu_"+person+"_"+$.md5(searchResult[i].profile);
	  	               }
		    	       indexedDB_saveSearchResults("zhihuDetailsTab",detailsResult);
		     },
		     error:function(){
		    	     detailsResult="error";
		     }
	  });
	  
	  return detailResult;
}




















/* ----------------------------------------------------------------------- */
/* ----------------------------------------------------------------------- */
/* ----------------------------------------------------------------------- */
/* ----------------------------------------------------------------------- */
/* ----------------------------------------------------------------------- */
/* ----------------------------------------------------------------------- */



/*
 *(1)爬取结果
 */
function zhihu_crawlerPersons(rootPath,crawlerPerson){
	  var  crawler_list;
	  $.ajax({
		   url:rootPath+"/zhihu/crawler_searchResult",
		   data:encodeURI(encodeURI("crawlerPerson="+crawlerPerson)),
		   async:false,
	       success:function(result1_list){
	    	       crawler_list=result1_list;
	       },
	       error:function(){
	    	      crawler_list="error";
	       }		  
	  });	
	 //alert("zhihu_crawlerPersons返回结果为："+crawler_list);
	 return crawler_list;	
}

/*
 *(2)最新动态详情
 */
function zhihu_getDetailContent(rootPath,url){
	 /* alert("测试知乎最新动态zhihu_getDetailContent:\n"
			 +"*****rootPath:"+rootPath+"\n"
			 +"*****url:"+url);*/
	  
	  var getDetailContent_result;
	  $.ajax({
		     url:rootPath+"/zhihu/detailContent",
		     data:'url='+url,
		     async:false,
		     success:function(result_list_2){
		    	     getDetailContent_result=result_list_2;
		     },
		     error:function(){
		    	     getDetailContent_result="error";
		     }
	  });
	  
	//  alert("测试知乎最新动态zhihu_getDetailContent返回结果: "+getDetailContent_result);
	  return getDetailContent_result;
}
/*
 *(3)提问详情
 */
function zhihu_getAsksContent(rootPath,url){
	/*alert("测试知乎提问zhihu_getAsksContent:\n"
		  +"*****rootPath:"+rootPath+"\n"
		  +"*****url:"+url);*/
	var getAsksContent_result;
	$.ajax({
	     url:rootPath+"/zhihu/asksContent",
	     data:'url='+url+'/asks',
	     async:false,
	     success:function(result_list_3){
               getAsksContent_result=result_list_3;
	     },
	     error:function(){
	    	   getAsksContent_result="error";
	     }
 });
	//alert("测试知乎提问zhihu_getAsksContent返回结果:"+getAsksContent_result);
	return getAsksContent_result;
}


/*
 *(4)提问详情
 */
function zhihu_getAnswerContent(rootPath,url){
	  /*alert("测试知乎回答zhihu_getAnswerContent:\n"
	           +"*****rootPath:"+rootPath+"\n"
	           +"*****url:"+url);*/
      var getAnswerContent_result;
      $.ajax({
              url:rootPath+"/zhihu/getAnswerContent",
              data:'url='+url+'/answers',
              async:false,
              success:function(result_list_4){
                      getAnswerContent_result=result_list_4;
             },
              error:function(){
  	                  getAnswerContent_result="error";
             }
       });
      //alert("测试知乎回答zhihu_getAnswerContent返回结果:"+getAnswerContent_result);
      return getAnswerContent_result;
}