/*cycle display search results on the page*/
function sina_displaySearchResults(searchResults,person){
	
	    if(searchResults==null || searchResults==''){
	         return "<p class='sr-h-nullPerson'>未能搜索到与&nbsp;<strong><i>"+person+"</i></strong>&nbsp;&nbsp;相关的人物信息。</h4>";
        }

	   var display='';
	   var display_content='';
	   
	   var display_img='';
	   var display_nickname='';
	   var display_genderLocal='';
	   var display_card='';
	   var display_count='';
	   var display_info='';
	   var display_label='';
	   var display_edu='';
	   var display_job='';
	   
	   
	   var profile='';
	   var profile_image='';
	   var nickname='';
	   var uid='';
	   var gender='';
	   var location='';
	   var follow='';
	   var follow_url='';
	   var fans='';
	   var fans_url='';
	   var statuses='';
	   var statuses_url='';
	   var person_info='';
	   
	   var hr="<hr  class='col-md-10'>";
	   
	   $.each(searchResults,function(index,searchResult){
		
		   
		   
		         display_content='';
		   
		         display_img='';
		         display_nickname='';
		         display_genderLocal='';
                 display_card='';
		         display_count='';
		         display_info='';
		         display_label='';
		         display_edu='';
		         display_job='';
		         
		         profile='http://weibo.com';
		         profile_image='';   
		         nickname='';
		         uid='';
		         gender='';
		         location='';
		         follow=0;
		  	     follow_url='http://weibo.com';
		  	     fans=0;
		  	     fans_url='http://weibo.com';
		  	     statuses=0;
		  	     statuses_url='http://weibo.com';
		         person_info='';
		         
		         var tempIndex=1;  //用于检查简介语句中是否含有“简介”
		         
		         if(searchResult.profile!=null && searchResult.profile!=""){
		    	       profile=searchResult.profile;
		         }
		         if(searchResult.profile_image!=null && searchResult.profile_image!=""){
		    	       profile_image=searchResult.profile_image;
		         }
		         if(searchResult.nickname!=null && searchResult.nickname!=""){
		        	   nickname=searchResult.nickname;
		         }else{
		        	   return true;
		         }
		         if(searchResult.uid!=null && searchResult.uid!=""){
		        	    uid=searchResult.uid;
		         }else{
		        	   return true;
		         }
		         if(searchResult.gender!=null && searchResult.gender!=""){
		        	     if(personGender(searchResult.gender)=="男"){
		        	    	  gender="<div class='col-md-1'>"
                                         +"<div title='男' class='sr-h-gender sr-h-gender-male'></div>"
                                     +"</div>";    
		        	     }else if(personGender(searchResult.gender)=="女"){
		        	    	 gender="<div class='col-md-1'>"
                                         +"<div title='女' class='sr-h-gender sr-h-gender-female'></div>"
                                    +"</div>";
		        	     }
		         }
		         if(searchResult.location!=null && searchResult.location!=""){
		        	      location="<div class='col-md-9'>"+searchResult.location+"</div>";
		         }
		         if(searchResult.follow!=null && searchResult.follow!=""){
		        	       follow=searchResult.follow;
		         }
		         if(searchResult.follow_url!=null && searchResult.follow_url!=""){
		        	       follow_url=searchResult.follow_url;
		         }
		         if(searchResult.fans!=null && searchResult.fans!=""){
		        	       fans=searchResult.fans;
		         }
		         if(searchResult.fans_url!=null && searchResult.fans_url!=""){
		        	       fans_url=searchResult.fans_url;
		         }
		         if(searchResult.statuses!=null && searchResult.statuses!=""){
		        	       statuses=searchResult.statuses;
		         }
		         if(searchResult.statuses_url!=null && searchResult.statuses_url!=""){
		        	       statuses_url=searchResult.statuses_url;
		         }
		         
		         
		         
		         //显示头像
		         display_img="<a  href='"+profile+"' target='_blank'>"
                                   +"<img class='media-object img-circle sr-h-personImg' src='"+profile_image+"'  onerror='personImgSubstitute(this)'/>" 
                              +"</a>";   
		        
		         //显示昵称、关注按钮
		         display_nickname="<div class='row'>"
                                       +"<h4 class='media-heading col-md-7' style='margin-top:1%'>"
                                            +"<a href='"+profile+"' target='_blank'>"+nickname+"</a>"
                                       +"</h4>"
                                       +"<div class='col-md-3 col-md-offset-1' style='text-align:center'>"
                                              +"<div class='btn-group' role='group'>"
                                                     +"<button id='sina_searchResults_"+index+"_"+$.md5(searchResult.uid)+"_attend' class='btn btn-warning' type='button'>"
                                                          +"<i class='glyphicon glyphicon-plus'></i> &nbsp;关注"
                                                     +"</button>"
                                                     +"<button class='btn btn-warning dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                                                          +"<span class='caret'></span>"
                                                          +"<span class='sr-only'>下拉菜单</span>"
                                                     +'</button>'
                                                     +"<ul class='dropdown-menu'>"
                                                          +"<li>"
                                                                +"<a style='cursor:pointer'><i id='sina_searchResults_"+index+"_"+$.md5(searchResult.uid)+"_joinDetail' class='glyphicon glyphicon-plus' style='color:#f0ad4e'></i>&nbsp;加入详情</a>"
                                                          +"</li>"
                                                     +"</ul>"
                                              +"</div>"
                                       +"</div>"
                                    +"</div>";
		         //显示性别、地区
		         if(gender!='' || location!=''){
		        	        display_genderLocal="<div class='row'>"
		        	        	                      +gender
		        	        	                      +location
		        	        	                +"</div>";
		         }
		         
		         //显示个人标识
		         if(searchResult.person_card!=null && searchResult.person_card!=""){
		        	       display_card="<div class='row' style='margin-top:1%'><div class='col-md-10'>"+searchResult.person_card+"</div></div>";
		         }
		         
		         //显示关注数、粉丝数、微博数
		         display_count="<div  class='row' style='margin-top:1%'>"
		        	                 +"<div class='col-md-10'>"
                                           +"<span>关注 &nbsp; <a href='"+follow_url+"' target='_blank' style='font-weight:bold;'>"+follow+"</a></span>"
                                           +"<span style='margin-left:3%'>粉丝 &nbsp;<a href='"+fans_url+"' target='_blank' style='font-weight:bold;'>"+fans+"</a></span>"
                                           +"<span style='margin-left:3%'>微博 &nbsp;<a href='"+statuses_url+"' target='_blank' style='font-weight:bold;'>"+statuses+"</a></span>"
                                     +"</div>" 
                               +"</div>";
		         
		         //显示简介
		         if(searchResult.person_info!=null && searchResult.person_info!=""){
		        	       
		        	       tempIndex=searchResult.person_info.indexOf("简介");
		        	       if(tempIndex==0){
		        	    	     person_info=searchResult.person_info.substring(3);
		        	       }else{
		        	    	     person_info=searchResult.person_info;
		        	       }
		        	       display_info="<div class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>简介：</div>"
                                              +"<div class='col-md-8' style='margin-left:-10%;width:76%'>"
                                                    +person_info
                                              +"</div>"
                                         +"</div>";
		         }
		        
		         //显示标签
		         if(searchResult.person_label!=null && searchResult.person_label!=""){
		        	       display_label="<div class='row' style='margin-top:1%'>"
                                               +"<div class='col-md-2'>标签：</div>"
                                               +"<div class='col-md-8' style='margin-left:-10%;width:76%'>"
                                                      +searchResult.person_label
                                               +"</div>"
                                          +"</div>";
		         }
		         
		         //显示教育信息
		         if(searchResult.person_edu!=null && searchResult.person_edu!=""){
		        	       display_edu="<div  class='row' style='margin-top:1%'>"
                                             +"<div class='col-md-2'>教育信息:</div>"
                                             +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                    +searchResult.person_edu
                                             +"</div>"
                                        +"</div>";
		         }
		         
		         //显示职业信息
		         if(searchResult.person_job!=null && searchResult.person_job!=""){
		        	        display_job="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>职业信息:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.person_job
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         display_content=display_nickname
                                 +display_genderLocal
                                 +display_card
                                 +display_count
                                 +display_info
                                 +display_label
                                 +display_edu
                                 +display_job;
		         
		         display+=displayResults(index,"sina_searchResults_",searchResults.length,"sina_more","显示全部新浪微博用户",display_img,"sina_name_",ROOTPATH+MODULE.SINA_IMG,"新浪微博",display_content,searchResult.uid);
		         
	   })

	   return display;
}
/*执行案件信息查询*/
function zjsfgkwExecuteCaseSearch_action(searchKey){
	
	 if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
  	       /*  $("#sina_searchResults").html(sina_displaySearchResults(sina_getSearchResults(searchKey),searchKey));
  	         $("#sina_more").remove();
  	         $("div[id^=sina_name_]").remove();
  	         $("#sina_searchResults").children().show();
  	         $("#sina_searchResults").show();
  	         $("#sina_searchResultsRightIcon").show();
  	         if($("hr").css("margin-top")=="0px"){
  	        	    $("hr").removeAttr("style");
  	         }
  	         
  	       module_action_reload("sina",searchKey);*/
		 zjsfgkwExecuteCaseSearch_getSearchResults(searchKey);
   }
}