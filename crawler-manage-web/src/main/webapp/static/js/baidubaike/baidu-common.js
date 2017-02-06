/*display baidu search result */
function baidu_displaySearchResults(searchResults,person){
	
	     if(searchResults==null || searchResults==''){
	    	   return "<p class='sr-h-nullPerson'>未能搜索到与&nbsp;<strong><i>"+person+"</i></strong>&nbsp;&nbsp;相关的人物信息。</h4>";
	     }
	     
	     var display='';
	     
	     
	     
	     var display_title='';
	     var display_introduction='';
	     var display_date='';
	     
	     var title='';
	     var link='';
	     
	     var hr="<hr class='col-md-10'>";
	     
	     
	     
	     $.each(searchResults,function(index,searchResult){
	    	 
	    	       
	    	       title='';
	    	       link="http://baike.baidu.com/";
	    	       introduction='';
	    	       
	    	       
	    	       
	    	       if(searchResult.title!=null && searchResult.title!=""){
	    	    	       title=searchResult.title;     
	    	       }else{
	    	    	       return true;
	    	       }
	    	       
	    	       if(searchResult.link!=null && searchResult.link!=""){
	    	    	       link=searchResult.link;
	    	       }
	    	       
	    	       
	    	       
	    	       
	    	       
	    	       
	    	       //显示标题
	    	       display_title="<div class='row'>"
                                      +"<h4 class='col-md-7' style='margin-top:1%'>"
                                           +"<a href='"+link+"' target='_blank'>"+title+"</a>"
                                      +"</h4>"
                                      +"<div class='col-md-3 col-md-offset-1' style='margin-left:14%'>"
                                             +"<button class='btn btn-warning' type='button' style='margin-left:10%'>"
                                                      +"<i id='baidu_searchResults_"+index+"_"+$.md5(searchResult.link)+"_joinDetail' class='glyphicon glyphicon-plus'></i>&nbsp;加入详情&nbsp;"
                                             +"</button>"
                                      +"</div>"
                                  +"</div>";
	    	 
	    	        //显示内容
	    	        if(searchResult.introduction!=null && searchResult.introduction!=""){
	    	        	    display_introduction="<div class='row'>"
	    	        	    	                      +"<div class='col-md-10' style='margin-top:1%'>"
	    	        	    	                            +searchResult.introduction
	    	        	    	                      +"</div>"
	    	        	    	                  +"</div>";
	    	        }
	    	 
                     //显示最后更新日期
	    	        if(searchResult.lastUpdateDate!=null && searchResult.lastUpdateDate!=""){
	    	        	    display_date="<div style='margin-top:1%;color:grey'>"
                                               +"<div class='col-md-2'>更新日期:</div>"
                                               +"<div class='col-md-8' style='margin-left:-5%;width:71%'>&nbsp;"
                                                     +searchResult.lastUpdateDate
                                               +"</div>"
                                         +"</div>";
	    	        }
	    	        
	    	        
	    	        if(index==0){
			        	   display_1="<div id='baidu_searchResults_"+index+"_"+$.md5(searchResult.link)+"'>";
			        	   if(searchResults.length>1){
			        		   display_3="<div id='baidu_more' class='col-md-offset-9 col-md-2 sr-h-more' title='显示全部百度百科词条'>更多&gt;&gt;&gt;</div>"
	    	    	                      +"<hr class='col-md-10' style='margin-top:0px'>"
	    	    	                      +"<div style='height:1px; margin-top:-1px;clear:both;overflow:hidden;'></div>"
	  	    	    	                  +"</div>";
			        	   }else{
			        		   display_3=hr+"</div>";
			        	   }
			         }else{
			        	   display_1="<div id='baidu_searchResults_"+index+"_"+$.md5(searchResult.link)+"' style='display:none'>";
			        	   display_3=hr+"</div>";
			         }
	    	 
	    	    	     display_2=display_title+display_introduction+display_date;

	    	    	     display +=display_1+display_2+display_3;
	    	 
	     })
	
	             return display;
	
}
function baidu_action(searchKey){
	
	  if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
		   
 	       $("#baidu_searchResults").html(baidu_displaySearchResults(baidu_getSearchResult(searchKey),searchKey));
	       $("#baidu_more").remove();
	       $("#baidu_searchResults").children().show();
	       $("#baidu_searchResults").show();
	       $("#baidu_searchResultsRightIcon").show();
	       if($("hr").css("margin-top")=="0px"){
	        	    $("hr").removeAttr("style");
	       }
	       module_action_reload("baidu",searchKey);
     }
}