//搜索
function baidu_getSearchResult(person){

      var searchResult;
     
      $.ajax({
              url:ROOTPATH+"/modules/baidubaike/getSearchResult",
              data:encodeURI(encodeURI("person="+person)),
              async:false,
              success:function(result){
                  searchResult=result;
                  
                //将结果保存至indexedDB--> searchResultsDB数据库--> baiduSearchResultsTab数据仓库中
                  for(var i=0;i<searchResult.length;i++){
 	    	            searchResult[i].personIndex="baidu_"+person+"_"+$.md5(searchResult[i].link);
 	               }
	    	       indexedDB_saveSearchResults("baiduSearchResultsTab",searchResult);
              },
              error:function(){
                  searchResult="error";
              }		  
      });	
      return searchResult;
}