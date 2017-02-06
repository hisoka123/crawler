function personSearch_more(){
	
	   personSearch_more_click("zhihu_more","zhihu_searchResults","知乎用户");
	   personSearch_more_click("sina_more","sina_searchResults","新浪微博用户");
	   personSearch_more_click("weixin_moreOfficialAccounts","weixin_searchOfficialAccountsResults","微信公众号");
	   personSearch_more_click("weixin_moreArticle","weixin_searchArticleResults","微信文章");
	   personSearch_more_click("baidu_more","baidu_searchResults","百度百科词条");
       personSearch_more_click("linkedIn_more","linkedIn_searchResults","领英人物");	
	
}



function personSearch_more_click(moreId,searchResultsList,modulesName){
	
	   var show_sign="&gt;&gt;&gt;";
	   var hide_sign="&lt;&lt;&lt;";
       var length=$("#"+searchResultsList).children().length;	
	   
	   
	   $("#"+moreId).click(function(){

		     if($("#"+moreId).text().indexOf("更多")!=-1){
		    	   for(var i=1;i<length;i++){
	                   $("div[id^='"+searchResultsList+"_"+i+"']").show();
		    	   }
	               $("#"+moreId).html("隐藏"+hide_sign);
                   $("#"+moreId).attr("title","隐藏部分"+modulesName);
	          }else if($("#"+moreId).text().indexOf("隐藏")!=-1){
	        	   for(var i=1;i<length;i++){
	        	      $("div[id^='"+searchResultsList+"_"+i+"']").hide();
	        	   }
                   $("#"+moreId).html("更多"+show_sign);
                   $("#"+moreId).attr("title","显示全部"+modulesName);
	          }
	     
       })
}
	
