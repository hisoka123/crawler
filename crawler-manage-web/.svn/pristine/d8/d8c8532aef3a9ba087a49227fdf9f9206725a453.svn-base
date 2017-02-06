/*
 * (1)爬取结果
 */
function fang_getSearchResults(keyword){
	  var  searchResult;	  
	  $.ajax({
		   url:ROOTPATH+"/modules/fang/getSearchResults",
		   data:encodeURI(encodeURI("keyword="+keyword)),
		   async:false,
	       success:function(result){
	    	       searchResult=result;

	       },
	       error:function(){
	    	       searchResult="error";
	       }		  
	  });	
	  return searchResult;	
}