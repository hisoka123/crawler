//搜索公众号
function weixin_getSearchOfficialAccountsResult(officialAccounts){
	  /* alert('测试微信爬取weixin_weixin_getSearchOfficialAccountsResult:\n'
			  +'*****officialAccounts:'+officialAccounts+'\n')*/
	  
	   var searchResult='';

	   $.ajax({
		   url:ROOTPATH+"/modules/weixin/getSearchOfficialAccountsResult",
		   data:encodeURI(encodeURI("officialAccounts="+officialAccounts)),
		   async:false,
	       success:function(result){
	    	    searchResult=result;
	    	    
	    	    //将结果保存至indexedDB--> searchResultsDB数据库--> weixinSearchResultsAccountTab数据仓库中
	    	     for(var i=0;i<searchResult.length;i++){
	    	            searchResult[i].personIndex="weixinAccounts_"+officialAccounts+"_"+$.md5(searchResult[i].openid);
	              }
	    	      indexedDB_saveSearchResults("weixinSearchResultsAccountTab",searchResult);
	       },
	       error:function(){
	    	    searchResult="error";
	       }		  
	  });	
	// alert("weixin_getSearchOfficialAccountsResult返回结果为："+searchResult);
	 return searchResult;	   
}


//搜索文章
function weixin_getSearchArticleResult(article){
	  /*alert('测试微信爬取weixin_getSearchArticleResult:\n'
	         +'*****title:'+title)*/

    var  searchResult='';

    $.ajax({
            url:ROOTPATH+"/modules/weixin/getSearchArticleResult",
            data:encodeURI(encodeURI("article="+article)),
            async:false,
            success:function(result){
	                  searchResult=result;
	                  
	                  //将结果保存至indexedDB--> searchResultsDB数据库--> weixinSearchResultsArticleTab数据仓库中
	                  for(var i=0;i<searchResult.length;i++){
	   	    	            searchResult[i].personIndex="weixinArticle_"+article+"_"+$.md5(searchResult[i].link);
	   	               }
	                   indexedDB_saveSearchResults("weixinSearchResultsArticleTab",searchResult);
            },
            error:function(){
	                  searchResult="error";
            }		  
    });	
    //alert("weixin_getSearchArticleResult返回结果为："+searchResult);
    return searchResult;
}
























/* -------------------------------------------------------- */
/* -------------------------------------------------------- */
/* -------------------------------------------------------- */
/* -------------------------------------------------------- */
/* -------------------------------------------------------- */



//(1)获取爬取结果
function weixin_getCrawlerUser(rootPath,crawlerUserKeyword){
	  /* alert('测试微信爬取weixin_getCrawlerUser:\n'
			  +'*****rootPath:'+rootPath+'\n'
			  +'*****crawlerKeyword:'+crawlerKeyword+'\n'
			  +'*****crawlerType:'+crawlerType);*/
	
	   var weixin_getCrawlerContent_result='';

	   $.ajax({
		   url:rootPath+"/weixin/getCrawlerUsers",
		   data:encodeURI(encodeURI("crawlerUserKeyword="+crawlerUserKeyword)),
		   async:false,
	       success:function(result1_list){
	    	   weixin_getCrawlerContent_result=result1_list;
	       },
	       error:function(){
	    	   weixin_getCrawlerContent_result="error";
	       }		  
	  });	
	// alert("weixin_getCrawlerUser返回结果为："+weixin_getCrawlerContent_result);
	 return weixin_getCrawlerContent_result;	   
}
//(2)
function weixin_getCrawlerArticle(rootPath,crawlerArticleKeyword){
	  /*alert('测试微信爬取weixin_getCrawlerArticle:\n'
	         +'*****rootPath:'+rootPath+'\n'
	         +'*****crawlerKeyword:'+crawlerArticleKeyword+'\n'
	         +'*****crawlerType:'+crawlerType);*/

      var weixin_getCrawlerContent_result='';

      $.ajax({
              url:rootPath+"/weixin/getCrawlerArticles",
              data:encodeURI(encodeURI("crawlerArticleKeyword="+crawlerArticleKeyword)),
              async:false,
              success:function(result1_list){
	                  weixin_getCrawlerContent_result=result1_list;
              },
              error:function(){
	                  weixin_getCrawlerContent_result="error";
              }		  
      });	
      //alert("weixin_getCrawlerUser返回结果为："+weixin_getCrawlerContent_result);
      return weixin_getCrawlerContent_result;
}