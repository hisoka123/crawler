/*生成综合人物搜索结果样式*/
function personSearch_searchResults(searchKey,isfirstSearch){
	         
	         //alert("personSearch_searchResults:\n searchKey:"+searchKey);
	         
	         
	         var displayPersonSearchResults='';
	 
	         var sinaResults='';
	         var zhihuResults='';
	         var weixinOfficialAccountsResults='';
	         var weixinArticleResults='';
	         var linkedInResults='';
	         var baiduResults='';
	         
	         var sina_display="";
		     var zhihu_display="";
		     var weixin_display="";
		     var baidu_display='';
		     var linkedIn_display='';
	        
		     
		     var sina_isNull='';
		     var zhihu_isNull='';
		     var weixin_isNull='';
		     var baidu_isNull='';
		     var linkedIn_isNull='';
		     var searchResults_isNull='';
		     
		     
		     if(localStorage.getItem(encodeURI(encodeURI((searchKey))))!=null){
		    	    return localStorage.getItem(encodeURI(encodeURI((searchKey))));
		     }
		     
		     
		     
		     if($("#to_sinaSearchResults_checkbox div:first-child").css("display")=="block" || isfirstSearch==1){
		    	    sinaResults=sina_getSearchResults(searchKey);
		    	    if(sinaResults!=''){
				         sina_display="<div id='sina_searchResults'>"
					                      +sina_displaySearchResults(sinaResults,searchKey)  
					                  +"</div>";
				    }else{
				    	 sina_isNull="<p id='sina_searchResults_isNull' class='sr-h-nullPerson' style='display:none'>"
                                          +"新浪：未能搜索到与&nbsp;"
                                          +"<strong><i>"+searchKey+"</i></strong>&nbsp;&nbsp;相关的人物信息。"
                                     +"</p>";
				    }
		     }

		     if($("#to_zhihuSearchResults_checkbox div:first-child").css("display")=="block" || isfirstSearch==1){
		    	        zhihuResults=zhihu_getSearchResult(searchKey);
		    	        if(zhihuResults!=''){
		   			           zhihu_display="<div id='zhihu_searchResults'>"
		   				                          +zhihu_displaySearchResults(zhihuResults,searchKey)
		   				                     +"</div>";
		   			    }else{
		   			    	   zhihu_isNull="<p id='zhihu_searchResults_isNull' class='sr-h-nullPerson' style='display:none'>"
                                                +"知乎：未能搜索到与&nbsp;"
                                                +"<strong><i>"+searchKey+"</i></strong>&nbsp;&nbsp;相关的人物信息。"
                                            +"</p>";
		   			    }
		    	 
		     }
		     
		     if($("#to_weixinSearchResults_checkbox div:first-child").css("display")=="block" || isfirstSearch==1){
		    	         weixinOfficialAccountsResults=weixin_getSearchOfficialAccountsResult(searchKey);
		                 weixinArticleResults= weixin_getSearchArticleResult(searchKey);
		                 if(weixinOfficialAccountsResults!='' || weixinArticleResults!=''){
		  				         weixin_display="<div id='weixin_searchResults'>"
		                                              +weixin_displaySearchResults(weixinOfficialAccountsResults,weixinArticleResults,searchKey)
		                                         +"</div>";
		  			     }else{
		  			    	    weixin_isNull="<p id='weixin_searchResults_isNull' class='sr-h-nullPerson' style='display:none'>"
                                                  +"微信：未能搜索到与&nbsp;"
                                                  +"<strong><i>"+searchKey+"</i></strong>&nbsp;&nbsp;相关的人物信息。"
                                               +"</p>";
		  			     }
		     }
		     
		     if($("#to_linkedInSearchResults_checkbox div:first-child").css("display")=="block" || isfirstSearch==1){
		    	         linkedInResults=linkedIn_getSearchResult(searchKey);
		    	         if(linkedInResults!=''){
		    			    	linkedIn_display="<div id='linkedIn_searchResults'>"
		    			    		                   +linkedIn_displaySearchResults(linkedInResults,searchKey)
		    			    		             +"</div>";
		    			 }else{
		    				    linkedIn_isNull="<p id='linkedIn_searchResults_isNull' class='sr-h-nullPerson' style='display:none'>"
                                                    +"领英：未能搜索到与&nbsp;"
                                                    +"<strong><i>"+searchKey+"</i></strong>&nbsp;&nbsp;相关的人物信息。"
                                                +"</p>";
		    			 } 
		     }
		     
	         
		     if($("#to_baiduSearchResults_checkbox div:first-child").css("display")=="block" || isfirstSearch==1){
		    	          baiduResults=baidu_getSearchResult(searchKey);
		    	          if(baiduResults!=''){
		    		    	  baidu_display="<div id='baidu_searchResults'>"
		    		    		                 +baidu_displaySearchResults(baiduResults,searchKey)
		    		    		            +"</div>";
		    		     }else{
		    		    	  baidu_isNull="<p id='baidu_searchResults_isNull' class='sr-h-nullPerson' style='display:none'>"
                                               +"百度百科：未能搜索到与&nbsp;"
                                               +"<strong><i>"+searchKey+"</i></strong>&nbsp;&nbsp;相关的人物信息。"
                                           +"</p>";
		    		     }
		     }
	         
		     
		     
		     searchResults_isNull=sina_isNull
		                          +zhihu_isNull
		                          +weixin_isNull
		                          +linkedIn_isNull
		                          +baidu_isNull;

		     if(searchResults_isNull!=''){
		    	    searchResults_isNull="<div id='personSearchResults_isNull' style='display:none;'>"
		    			                          +searchResults_isNull
                                            +"</div>";
		     }
		    		 

		     
		     
			displayPersonSearchResults=sina_display
			                           +zhihu_display
			                           +weixin_display
			                           +linkedIn_display
			                           +baidu_display
			                           +searchResults_isNull;
			
			
		    $("#personSearchResults").attr("data-searchkey",encodeURI(encodeURI(("personSearch_"+searchKey))));
		   	localStorage.setItem(encodeURI(encodeURI(("personSearch_"+searchKey))),displayPersonSearchResults);
		   
	        return displayPersonSearchResults;
}




//隐藏全部搜索结果
function personSearch_modulesSearchResultsAllHide(){
	   
	   $("#personSearchResults").children().hide();
	   $("#personSearchResults_isNull").children().hide();
	   

}
//显示全部搜索结果
function personSearch_modulesSearchResultsAllShow(){
	
	   var searchResults_length=$("#personSearchResults").children().length;
	   
	   for(var i=0;i<searchResults_length;i++){
		   $("#personSearchResults").children().eq(i).show();
	   }
	   
	   $("#personSearchResults_isNull").hide();
	   $("#personSearchResults_isNull").children().hide();
	   
}

//单结果显示搜索结果
function personSearch_SingleSearchResultShow(){
	
	    $("#personSource").find("img[id^=to_]").click(function(){

	    	     personSearch_modulesSearchResultsAllHide();
	    	     
	    	     //相应checkbox选中
	    	     $("#personSource").find("div[id$=_checkbox]").show();
	    	     $("#personSource").find("div[id$=_checkbox]").children().hide();
	    	     $(this).next().next().children().click();
	    })

}

//通过自制checkbox选择多结果显示搜索结果
function personSearch_multiSearchResultsShow(){
	
	    $("#personSource").find("div[id$=_checkbox]").click(function(){
	    	
	    	    var checkboxId=$(this).attr("id");
	    	    
	    	    if(checkboxId=="smallSearchIcon_checkbox"){
	    	           if($(this).children().css("display")=="none"){
	    	        	      $(this).children().show();
	    	        	      $("#personSource").find("div[id$=_checkbox]").children().show();
	    	        	      $("#personSource").find("div[id$=_checkbox]").hide();
	    	        	      $(this).show();
	    	        	      personSearch_modulesSearchResultsAllShow();
	    	           }else if($(this).children().css("display")=="block"){
	    	        	      $(this).children().hide();
	    	        	      $("#personSource").find("div[id$=_checkbox]").children().hide();
	    	        	      $("#personSource").find("div[id$=_checkbox]").show();
	    	        	      personSearch_modulesSearchResultsAllHide();
	    	           }	
	    	    }else{
	    	           var moduleName=checkboxId.split("SearchResults")[0].split("_")[1];
	    	           var moduleDiv="div[id^="+moduleName+"_]";
	    	           var moduleNum=$("#personSource").find("div[id^=to_]").length;
	    	           
	    	           $("#smallSearchIcon_checkbox").show();
    	        	   $("#smallSearchIcon_checkbox").children().hide();
	    	           
	    	          if($(this).children().css("display")=="none"){
	    	    	       
	    	        	       $(this).children().show();
	    	        	  
	    	        	       if($("#personSearchResults").children(moduleDiv).length==0){
	    	        		           $("#personSearchResults_isNull").show();
	    	        		           $("#"+moduleName+"_searchResults_isNull").show();
	    	        	       }else{
	    	        		           $("#personSearchResults").children(moduleDiv).show();
	    	        	       }
	    	    	       
	    	    	       
	    	    	           //当全部模块都选中时，“全部选中”图标，即右侧搜索图标下checkbox为选中状态
	    	    	           for(var i=0;i<moduleNum;i++){
	    	    	    	           if($("#personSource").find("div[id^=to_]").eq(i).children().css("display")=="block"){
	    	    	    	    	           if(i==(moduleNum-1)){
	    	    	    	    	    	          $("#smallSearchIcon_checkbox").show();
	    	    	       	        	              $("#smallSearchIcon_checkbox").children().show();    
	    	    	    	    	           }else{
	    	    	    	    	    	          continue;
	    	    	    	    	           }
	    	    	    	           }else{
	    	    	    	    	           break;
	    	    	    	           }
	    	    	           }   
	    	          }else if($(this).children().css("display")=="block"){

	    	        	       $(this).children().hide();
	    	        	  
	    	        	       if($("#personSearchResults").children(moduleDiv).length==0){
	    	        		           $("#personSearchResults_isNull").show();
	    	        		           $("#"+moduleName+"_searchResults_isNull").hide();
	    	        	       }else{
		    	    	               $("#personSearchResults").children(moduleDiv).hide();
	    	        	       }
	    	          }
	    	    }
	    })
}

/*综合人物搜索页面事务操作集合, 重复加载项*/
function personSearch_action_reload(searchKey){
	   
	    personSearch_more();   //左侧：每个模块搜索结果下的"更多"搜索结果
        weixin_qrCodeShowAndHide();//左侧：微信公众号二维码显示
     
        //左侧结果：加入“综合查阅详情”
        contrastORLookDetail("personSearchResults",searchKey,"read_detail","joinDetailed_num");
     
        //右侧图标
        if($("#smallSearchIcon_checkbox div:first-child").css("display")=="none"){
            $("#smallSearchIcon_checkbox").click();  //右侧图标处于全选状态
        }
}
/*综合人物搜索页面事务操作集合*/
function personSearch_action(searchKey){
	
	   if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
		   
		      $("#personSearchResults").html(personSearch_searchResults(searchKey,1));
		   
	            //显示搜索结果
	           if($("#personSearchResults").css("display")=="none"){
	    	           $("#personSearchResults").show();
	           }
	     
	          //显示右侧图标
	          if($("#personSource").css("display")=="none"){
	    	          $("#personSource").show();
	    	    
	    	          //写入右侧图标checkbox
	   	              var checkbox_checked="<div style='margin-top:-4px;display:none'>"
	                                             +"<i class='glyphicon glyphicon-ok sr-h-checkboxCheckedColor'></i>"
	                                       +"</div>";
	   	              $("#personSource").find("div[id^=to_]").html(checkbox_checked);

	   	              rightIconBackgroundColor("personSource");  //右侧图标背景变色
	   	              personSearch_SingleSearchResultShow();  //右侧单结果显示搜索结果
		              personSearch_multiSearchResultsShow();  //右侧多结果显示搜索结果
		              rightLogoFixedPosition("personSource");   //右侧图标Div固定定位
	            }
	         
	          personSearch_action_reload(searchKey);
	           
	    }
}

