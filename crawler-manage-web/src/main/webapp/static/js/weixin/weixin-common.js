/*display weixin officialAccounts search result*/
function weixin_displaySearchOfficialAccountsResults(weixinOfficialAccountsResults){
	
	
	      if(weixinOfficialAccountsResults==null || weixinOfficialAccountsResults==''){
	    	       return "isNull";
	      }
	
	      
	      var display='';
	      var display_content='';
	      
	      var display_img='';
	      var display_name='';
	      var display_account='';
	      var display_func='';
	      var display_authen='';
	      var display_article='';
	      
	      var profile_image='';
	      var name='';
	      var weixin_account='';
	      var created_time='';
	      var link='';
	      var profile='';
	      var qrcode='';
	      
	      
	      var hr="<hr  class='col-md-10'>";
	      
	      $.each(weixinOfficialAccountsResults,function(index,weixinOfficialAccountsResult){
	    	  
                        display_content='';         
	    	  
		                display_img='';
		                display_name='';
		                display_account='';
		                display_func='';
		                display_authen='';
		                display_article='';
	    	          
	    	  
	    	            profile_image=""; 
	    	            name='';
	    	            weixin_account='';
	    	            created_time='';
	    	            link='http://weixin.sogou.com';
	    	            profile='http://weixin.sogou.com/?p=73141200&kw=';
	    	            qrcode='无法获取该公众号的二维码';
	    	            
	    	            
	    	            if(weixinOfficialAccountsResult.profile_image!=null && weixinOfficialAccountsResult.profile_image!=""){
	    	            	      profile_image=weixinOfficialAccountsResult.profile_image;
	    	            }
                        if(weixinOfficialAccountsResult.name!=null && weixinOfficialAccountsResult.name!=""){
                        	      name=weixinOfficialAccountsResult.name;
                        }else{
                        	      return true;
                        }	
                        if(weixinOfficialAccountsResult.qr_codes!=null && weixinOfficialAccountsResult.qr_codes!=""){
                        	    qrcode="<img src='"+weixinOfficialAccountsResult.qr_codes[0]+"' class='sr-h-qrcode'>"
                        	           +"<img src='"+profile_image+"' class='sr-h-qrcodeImg'>"
                        	           +"<div style='padding-top:10px'><img src='"+ROOTPATH+"/static/imgs/logo/weixin02.png' class='sr-h-logo18'>&nbsp;微信扫一扫</div>"
                        }
	    	            
	    	            
	    	            
	    	            //显示头像
	    	            display_img="<a href='"+profile+"' target='_blank'>"
	    	                            +"<img class='media-object img-circle sr-h-personImg' src='"+profile_image+"'  onerror='personImgSubstitute(this)' />"
	    	                        +"</a>";
	    	  
	    	            //显示姓名、二维码
	    	            display_name="<div class='row' style='margin-top:1%'>"
                                           +"<h4 class='media-heading col-md-8' >"
                                                 +"<a href='"+profile+"' target='_blank'>"+name+"</a>"
                                           +"</h4>"
                                           +"<div data-name='OfficialAccounts_QRCode' class='col-md-offset-1 col-md-1'>"
                                                  +"<div class='sr-h-qrcodeDiv'>"
                                                        +qrcode
                                                  +"</div>"
                                                  +"<i class='glyphicon glyphicon-qrcode' style='cursor:pointer;'></i>"
                                           +"</div>"
                                      +"</div>";
	    	             
	    	            //微信号
	    	            if(weixinOfficialAccountsResult.weixin_account!=null && weixinOfficialAccountsResult.weixin_account!=""){
	    	            	      display_account="<div class='row' style='margin-top:1%'>"
                                                        +"<div class='col-md-2'>微信号码:</div>"
                                                        +"<div class='col-md-8' style='margin-left:-5%'>&nbsp;"
                                                               +weixinOfficialAccountsResult.weixin_account
                                                        +"</div>"
                                                  +"</div>"
	    	            }
                        
	    	            //显示功能介绍
	    	            if(weixinOfficialAccountsResult.func_info!=null && weixinOfficialAccountsResult.func_info!=""){
	    	            	     display_func="<div style='margin-top:1%' class='row'>"
                                                   +"<div class='col-md-2'>功能介绍:</div>"
                                                   +"<div class='col-md-8' style='margin-left:-5%'>&nbsp;"
                                                          +weixinOfficialAccountsResult.func_info
                                                   +"</div>"
                                               +"</div>";
	    	            }
	    	            
	    	            //显示微信认证
	    	            if(weixinOfficialAccountsResult.authen_info!=null && weixinOfficialAccountsResult.authen_info!=""){
	    	            	     display_authen="<div style='margin-top:1%' class='row'>"
                                                     +"<div class='col-md-2'>微信认证:</div>"
                                                     +"<div class='col-md-8' style='margin-left:-5%'>&nbsp;"
                                                            +weixinOfficialAccountsResult.authen_info
                                                     +"</div>"
                                                +"</div>";
	    	            }
	    	            
	    	            //最新文章
	    	            if(weixinOfficialAccountsResult.article!=null && weixinOfficialAccountsResult.article!=""){
	    	            	      if(weixinOfficialAccountsResult.article.title!=null && weixinOfficialAccountsResult.article.title!=""){
                                             if(weixinOfficialAccountsResult.article.created_time!=null && weixinOfficialAccountsResult.article.created_time!=""){
                                            	     created_time="<br><span style='color:grey'>"+weixinOfficialAccountsResult.article.created_time+"</span>";
                                             }
                                             
                                             if(weixinOfficialAccountsResult.article.link!=null && weixinOfficialAccountsResult.article.link!=""){
                                            	     link=link+weixinOfficialAccountsResult.article.link;
                                             }
                                             
                                             display_article="<div style='margin-top:1%' class='row'>"
                                                                   +"<div class='col-md-2'>最新文章:</div>"
                                                                   +"<div class='col-md-8' style='margin-left:-5%;'>&nbsp;"
                                                                          +"<a href='"+link+"' target='_blank'>"+weixinOfficialAccountsResult.article.title+"</a>"
                                                                          +created_time
                                                                   +"</div>"
                                                              +"</div>";
	    	            	      }
	    	            }
	    	            
	    	            
	    	            
	    	            
	    	            display_content=display_name
                                        +display_account
                                        +display_func
                                        +display_authen
                                        +display_article
	    	            
	    	            display+=displayResults(index,"weixin_searchOfficialAccountsResults_",weixinOfficialAccountsResults.length,"weixin_moreOfficialAccounts","显示全部微信公众号",display_img,"weixin_officicalAccounts_",ROOTPATH+MODULE.WEIXIN_IMG,"腾讯微信",display_content,weixinOfficialAccountsResult.openid);
	    	            
	    	            
	    	            
	    	  
	      })
	
	
          return display;	
}



//微信文章搜索结果
function weixin_displaySearchArticleResults(weixinArticleResults){
	
	     if(weixinArticleResults==null || weixinArticleResults==''){
	    	   return 'isNull';
	     }
	     
	     var display='';
	     var display_content='';
	     
	     var display_img='';
	     var display_title='';
	     var display_article='';
	     var display_created_time='';
	     
	     var pre_image='';
	     var link='';
	     var title='';
	     var fromName=''
	     var created_time='';
	     var qrcode='';
	     var profile_image='';
	     
	     
	     $.each(weixinArticleResults,function(index,weixinArticleResult){
	    	 
	    	 
	    	         display_content='';
		     
		             display_img='';
		             display_title='';
		             display_article='';
		             display_created_time='';
	    	 
	    	 
	    	 
                     pre_image="";
                     profile_imag="";
	    	         link="http://weixin.sogou.com";
                     title='';
	    	         fromName='';
	    	         created_time='';
	    	         qrcode='无法获取二维码';
	    	         
                     if(weixinArticleResult.pre_image!=null && weixinArticleResult.pre_image!=""){
                    	   pre_image=weixinArticleResult.pre_image;
                     }
                     if(weixinArticleResult.link!=null && weixinArticleResult.link!=""){
                    	   link=link+weixinArticleResult.link;
                     }
                     if(weixinArticleResult.title!=null && weixinArticleResult.title!=""){
                    	   title=weixinArticleResult.title;
                     }else{
                    	   return true;
                     }
                     if(weixinArticleResult.from!=null && weixinArticleResult.from!=""){
               	         if(weixinArticleResult.from.name!=null && weixinArticleResult.from.name!=""){
               	    	      fromName=weixinArticleResult.from.name;
               	         }
                     }
                     if(weixinArticleResult.created_time!=null && weixinArticleResult.created_time!=""){
                    	   created_time=weixinArticleResult.created_time;
                     }
                     
                     
                     //文章二维码
                     /*if(weixinArticleResult.from!=null){
                    	 
                    	   if(weixinArticleResult.from.profile_image!=null){
                    		     profile_image=weixinArticleResult.from.profile_image;
                    	   }
                    	 
                    	   if(weixinArticleResult.from.qr_codes!=null){
                    		     if(weixinArticleResult.from.qr_codes.length<2){
                    		          qrcode="<img src='"+weixinArticleResult.from.qr_codes[0]+"' class='sr-h-qrcode'>";
                    		     }else if(weixinArticleResult.from.qr_codes.length>1){
                    		    	  qrcode="<img src='"+weixinArticleResult.from.qr_codes[0]+"' class='sr-h-qrcode' onerror='weixin_errorQRCode(\""+weixinArticleResult.from.qr_codes[1]+"\")' />";
                    		     }     
                    		     qrcode+="<img src='"+profile_image+"' class='sr-h-qrcodeImg'>"
                	                     +"<div style='padding-top:10px'>"
                	                            +"<img src='"+ROOTPATH+"/static/imgs/logo/weixin02.png' class='sr-h-logo18'>&nbsp;微信扫一扫"
                	                     +"</div>";
                    	   }
                     }*/
                     
                     
                     
                     //显示头像
                     display_img="<a href='"+link+"' target='_blank'>"
                                     +"<img class='media-object img-circle sr-h-personImg' src='"+pre_image+"'  onerror='personImgSubstitute(this)' />"
                                 +"</a>";
                     
                     //显示标题、二维码
                     display_title="<div class='row' style='margin-top:1%'>"
                                        +"<h4 class='media-heading col-md-8'>"
                                              +"<a href='"+link+"' target='_blank'>"+title+"</a>"
                                        +"</h4>"
                                        /*+"<div class='col-md-offset-1 col-md-1'>"
                                               +"<div class='sr-h-qrcodeDiv'>"
                                                      +qrcode
                                               +"</div>"
                                               +"<i class='glyphicon glyphicon-qrcode' style='cursor:pointer'></i>"
                                        +"</div>"*/
                                   +"</div>";
                     
                     //显示内容
                     if(weixinArticleResult.content!=null && weixinArticleResult.content!=""){
                    	     display_article="<div class='row' style='margin-top:1%'>"
                                                   +"<div class='col-md-10'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                                         +weixinArticleResult.content.replace("<","&lt").replace(">","&gt;")
                                                   +"</div>"
                                             +"</div>";
                     }
                     
                     //显示来源、时间
                     display_created_time="<div style='margin-top:1%;color:grey'>"
                                                +fromName+"&nbsp;&nbsp;&nbsp;&nbsp;"+created_time
                                                +"</div>";

                     
                     display_content=display_title
                                     +display_article
                                     +display_created_time;
                     
                     
                   display+=displayResults(index,"weixin_searchArticleResults_",weixinArticleResults.length,"weixin_moreArticle","显示全部微信文章",display_img,"weixin_Article_",ROOTPATH+MODULE.WEIXIN_IMG,"腾讯微信",display_content,weixinArticleResult.link);
                     
	     })
	
	     return display;
}

//微信搜索结果显示
function weixin_displaySearchResults(weixinOfficialAccountsResults,weixinArticleResults,person){
	
	var weixin_officialAccountsDisplay='';
	var weixin_articleDisplay='';
	
	var display_officialAccounts=weixin_displaySearchOfficialAccountsResults(weixinOfficialAccountsResults);
	var display_article=weixin_displaySearchArticleResults(weixinArticleResults);
	
	
	if(display_officialAccounts=='isNull' && display_article=='isNull'){
		   return "<p class='sr-h-nullPerson'>微信：未能搜索到与&nbsp;<strong><i>"+person+"</i></strong>&nbsp;&nbsp;相关的人物信息。</h4>";
	}else{
		   if(display_officialAccounts!='isNull'){
			    weixin_officialAccountsDisplay="<div id='weixin_searchOfficialAccountsResults'>"
	                                                 +display_officialAccounts
	                                           +"</div>";
		   }
		
		   if(display_article!='isNull'){
			    weixin_articleDisplay="<div id='weixin_searchArticleResults'>"
	                                       +display_article
	                                  +"</div>";
		   }
		   
		   return  weixin_officialAccountsDisplay+weixin_articleDisplay;
	}
	
}

//显示/隐藏二维码
function weixin_qrCodeShowAndHide(){
	
	   $("#weixin_searchResults").find(".glyphicon-qrcode").mouseenter(function(){
	    	    $(this).prev().show();
	   })
	      
	   $("#weixin_searchResults").find(".glyphicon-qrcode").mouseleave(function(){
	    	    $(this).prev().hide();
	   })
	
}

//微信文章二维码替换
function weixin_errorQRCode(qrCodeURL){
	  var img=event.srcElement;
      img.src=""+qrCodeURL;      
      img.onerror=null;
	
}

function weixin_action(searchKey){
	
	  if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
  	     
		 $("#weixin_searchResults").html(weixin_displaySearchResults(weixin_getSearchOfficialAccountsResult(searchKey),weixin_getSearchArticleResult(searchKey),searchKey)); 
  	     
		 
		 $("#weixin_moreOfficialAccounts").remove();
  	     $("div[id^=weixin_officicalAccounts_]").remove();
  	     $("#weixin_searchOfficialAccountsResults").children().show();
  	     
  	     
  	     $("#weixin_moreArticle").remove();
  	     $("div[id^=weixin_Article_]").remove();
  	     $("#weixin_searchArticleResults").children().show();
  	     
  	     $("#weixin_searchResults").show();
  	     $("#weixin_searchResultsRightIcon").show();
  	     
  	     
  	     weixin_qrCodeShowAndHide();  //微信公众号二维码显示
  	     
  	     $("#weixin_searchResults").find("hr").removeAttr("style");
  	     
  	     rightLogoFixedPosition("weixin_searchResultsRightIcon");
      }

}




















/*---------------------------------------------------------------*/
/*---------------------------------------------------------------*/
/*---------------------------------------------------------------*/
/*---------------------------------------------------------------*/
/*---------------------------------------------------------------*/








//1.爬取公众号结果展示
function weixin_common_userDisplay(rootPath,searchname,crawlerResult){
	   var userDisplay='';  
	   var updateArticle='';
	   var user='';
	   if(crawlerResult==''){
		    user='<b>抱歉!</b>&nbsp;&nbsp;暂无与<strong style="color:red">'+searchname+'</strong>相关的官方认证订阅号。';
		    return user;
	   }
	   
	   
	  $.each(crawlerResult,function(index,personList){
		    updateArticle='';

		     if(this.article!=null){
		    	    updateArticle='<div class="row" style="margin-top:2%">'
                                         +'<div class="col-md-2">'
                                               +'<small>最新文章:</small>'
                                         +'</div>'
                                         +'<div class="col-md-10" style="margin-left:-5%">'
                                                +'<a href="'+this.article.link+'" target="_blank"><small>'+this.article.title+'</small></a>'
                                                +'<small style="color:grey">&nbsp;&nbsp;'+this.article.created_time.split(" ")[0]+'</small>'
                                         +'</div>'
                                   +'</div>';
		      }
		     var replaceName='<strong style="color:red">'+searchname+'</strong>';
		     userDisplay  ='<div class="panel panel-default" >'
		    	                 +'<div class="panel-body">'
		    	                        +'<div class="row">'
		    	                               +'<div class="col-md-2">'
		    	                                      +'<img src="'+this.profile_image+'" class="img-circle" style="width:50%" onerror="weixin_common_imgNoFind(\''+rootPath+'\')"/>'
		    	                               +'</div>'
		    	                               +'<div class="col-md-10" style="margin-left:-5%">'
		    	                                      +'<div style="margin-right:1%">'
		    	                                              +'<span style="font-size:17px;"><b>'+this.name.replace(new RegExp(searchname,"gm"),replaceName)+'</b></span>'
		    	                                              +'<span id="user_'+index+'" style="float:right;margin-right:5%" onmousemove="weixin_common_showUserQRCode(this);" onmouseout="weixin_common_hideUserQRCode(this);" >'
		    	                                                      +'<a href="'+this.qr_codes[0]+'" target="_blank">'
		    	                                                           +'<img src="'+rootPath+'/static/img/weixin_QRcode.gif" />'
		    	                                                      +'</a>'
		    	                                              +'</span>'
		    	                                      +'</div>'
		    	                                      +'<h5 style="color:grey">微信号：'+this.weixin_account+'</h5>'
		    	                                      +'<div class="row">'
		    	                                             +'<div class="col-md-2">'
		    	                                                    +'<small>功能介绍:</small>'
		    	                                             +'</div>'
		    	                                             +'<div class="col-md-10" style="margin-left:-5%">'
		    	                                                    +'<small>'+this.func_info+'</small>'
		    	                                             +'</div>'		    	                                            
		    	                                      +'</div>'
		    	                                      +updateArticle
		    	                              +'</div>'		    	                            
		    	                        +'</div>'
		    	                 +'</div>'
		    	           +'</div>';
		     		                   
		      user +='<div style="width:100%;">'
		    	           +'<div style="width:80%;float:left">'+userDisplay+'</div>'
		    	           +'<div id="userQRCode_'+index+'" style="width:19%;float:right;margin-top:-5%;display:none">'+weixin_common_generateQRCode(rootPath,this.qr_codes[0],this.profile_image)+'</div>'
		    	      +'</div>';
  
	  })
	  return user;
}
//2.爬取文章显示
function weixin_common_articleDisplay(rootPath,weixin_keyword,crawlerResult){
	  var articleDisplay='';
	  var article='';
	  
	  if(crawlerResult==''){
		   
		    article='<b>抱歉!</b>&nbsp;&nbsp;暂无与<strong style="color:red">'+searchname+'</strong>相关的文章。';
		    return article;
	   }
	  
	  $.each(crawlerResult,function(index,articles){
		      var replace='<strong style="color:red">'+weixin_keyword+'</strong>';
		      articleDisplay ='<div class="panel panel-default">'
		    	                     +'<div class="panel-body">'
		    	                            +'<div style="font-size:17px">'
		    	                                   +'<span><a href="'+this.link+'" target="_blank">'+this.title.replace(new RegExp(weixin_keyword,"gm"),replace)+'</a></span>'
		    	                                   +'<span id="article_'+index+'" style="float:right" onmousemove="weixin_common_showArticleQRCode(this);" onmouseout="weixin_common_hideArticleQRCode(this);">'
		    	                                           +'<img src="'+rootPath+'/static/img/weixin_QRcode.gif" />'
		    	                                   +'</span>'
		    	                            +'</div>'
		    	                            +'<div class="row">'
		    	                                   +'<div class="col-md-2">'
		    	                                          +'<img src="'+this.pre_image+'" style="width:75px;height:90px" onerror="weixin_common_imgNoFind(\''+rootPath+'\')"/>'
		    	                                   +'</div>'
		    	                                   +'<div class="col-md-10" style="margin-left:-4%;font-size:10px">'
		    	                                          +'<div>'+this.content.replace(new RegExp(weixin_keyword,'gm'),replace)+'</div>'
		    	                                          +'<div style="color:grey;margin-top:1%">'+this.from.name+'&nbsp;&nbsp;'+this.created_time+'</div>'
		    	                                   +'</div>'
		    	                            +'</div>'
		    	                     +'</div>'
		    	               +'</div>';
		      
		      article +='<div style="width:100%;">'
   	                       +'<div style="width:80%;float:left">'+articleDisplay+'</div>'
   	                       +'<div id="articleQRCode_'+index+'" style="width:19%;float:right;margin-top:-5%;display:none">'+weixin_common_generateQRCode(rootPath,this.from.qr_codes,this.from.profile_image)+'</div>'
   	                 +'</div>';
		     
	  })
	  
	  return article;
}

//3.生成二维码
function weixin_common_generateQRCode(rootPath,qrCodeURLs,imgURL){
	     var qrCodeURL='';
	     var qrCodeURL_substitute='';
	     var qrCodeURL_style='';
	     var imgURL_style='';
	
	    if(typeof(qrCodeURLs)=="string"){
	    	   qrCodeURL_style='<img src="'+qrCodeURLs+'" style="position:absolute;left:18px;top:47px;z-index:2" />';
	    	   imgURL_style='<img src="'+imgURL+'" style="position:absolute;left:67px;top:102px;z-index:2;width:38px;height:38px" onerror="weixin_common_imgNoFind(\''+rootPath+'\')"/>';
	    }else if(typeof(qrCodeURLs)=="object"){
	    	   qrCodeURL=qrCodeURLs[0];
	    	   qrCodeURL_substitute=qrCodeURLs[1];
	    	   qrCodeURL_style='<img src="'+qrCodeURL+'" style="position:absolute;left:18px;top:47px;z-index:2" onerror="weixin_common_qrsubs(\''+qrCodeURL_substitute+'\')" />';
	           imgURL_style='<img src="'+imgURL+'" style="position:absolute;left:64px;top:92px;z-index:2;width:38px;height:38px" onerror="weixin_common_imgNoFind(\''+rootPath+'\')"/>'
	    }
	    return '<div style="position:absolute">'
	                 +'<img src="'+rootPath+'/static/img/weixin_frame.jpg" style="position:absolute;z-index:1"/>'
	                 +'<img src="'+rootPath+'/static/img/weixin_phone.png" style="position:absolute;left:175px;top:10px;z-index:2"/>'
	                 +qrCodeURL_style
	                 +imgURL_style
	           +'</div>';
}
//(4-1)显示人物 二维码
function weixin_common_showUserQRCode(span){
	   var userId=$(span).attr("id");
	   userId=userId.split("_");
	   var index=userId[1]
	   $("#userQRCode_"+index).show();	   
}
//(4-2)隐藏人物二维码
function weixin_common_hideUserQRCode(span){
	   var userId=$(span).attr("id");
	   userId=userId.split("_");
	   var index=userId[1]
	   $("#userQRCode_"+index).hide();	
}
//(4-3)显示文章二维码
function weixin_common_showArticleQRCode(span){	    
	    var articleId=$(span).attr("id");
	    articleId=articleId.split("_");
	    var index=articleId[1]
	    $("#articleQRCode_"+index).show();
}
//(4-4)隐藏文章二维码
function weixin_common_hideArticleQRCode(span){
	   var articleId=$(span).attr("id");
       articleId=articleId.split("_");
       var index=articleId[1]
       $("#articleQRCode_"+index).hide();
}
//5.二维码替换
function weixin_common_qrsubs(qrCodeURL){
	  var img=event.srcElement;
      img.src=qrCodeURL;      
      img.onerror=null;
}
//6.图片替换
function weixin_common_imgNoFind(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/weixin.ico";      
    img.onerror=null;
}