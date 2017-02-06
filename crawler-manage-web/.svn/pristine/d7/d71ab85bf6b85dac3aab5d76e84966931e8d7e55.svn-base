/*
 * (1)爬取结果
 */
function sina_getSearchResults(person){
	  var  searchResult;
	  
	  $.ajax({
		   url:ROOTPATH+"/modules/sinaweibo/getSearchResults",
		   data:encodeURI(encodeURI("person="+person)),
		   async:false,
	       success:function(result){
	    	       searchResult=result;

	    	       //将结果保存至indexedDB--> searchResultsDB数据库--> sinaSearchResultsTab数据仓库中
	    	       for(var i=0;i<searchResult.length;i++){
   	    	            searchResult[i].personIndex="sina_"+person+"_"+$.md5(searchResult[i].uid);
   	               }
	    	       indexedDB_saveSearchResults("sinaSearchResultsTab",searchResult);
	       },
	       error:function(){
	    	       searchResult="error";
	       }		  
	  });	
	 // alert("sina_getSearchResult返回结果为："+searchResult);
	  return searchResult;	
}