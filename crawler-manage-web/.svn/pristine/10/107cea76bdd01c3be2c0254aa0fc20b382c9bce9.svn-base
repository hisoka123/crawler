$(function(){
      
	
	var dbConnect= window.indexedDB.open(SEARCHRESULTSDB.NAME,SEARCHRESULTSDB.VERSION);
	
	dbConnect.onsuccess=function(e){
		     var keyIndex='';
	         var index='';
	         var personArray='';
	         var storeName='';
	         var indexName='';
	         
		     SEARCHRESULTSDB.IDB=e.target.result;
		    
		     personArray=localStorage.getItem("personSearch_joinPerson").split(",");
		    
		     for(var i=0;i<personArray.length;i++){
		    	 
		    	     if(personArray[i].split("_")[0]=="sina"){
		    	    	   storeName="sinaSearchResultsTab";
		    	    	   indexName="sinaIndex";
		    	     }else if(personArray[i].split("_")[0]=="zhihu"){
		    	    	   storeName="zhihuSearchResultsTab";
		    	    	   indexName="zhihuIndex";
		    	     }else if(personArray[i].split("_")[0]=="linkedIn"){
		    	    	   storeName="linkedInSearchResultsTab";
		    	    	   indexName="linkedInIndex";
		    	     }else if(personArray[i].split("_")[0]=="baidu"){
		    	    	   storeName="baiduSearchResultsTab";
		    	    	   indexName="baiduIndex";
		    	     }
		    	 
		    	   index=SEARCHRESULTSDB.IDB.transaction(storeName).objectStore(storeName).index(indexName);
		    	  
		    	   index.get(personArray[i]).onsuccess=function(e){
		    		        var result=e.target.result;
		    		        var moduleName=result.personIndex.split("_")[0];
		    		        if(moduleName=="sina"){
		    		        	  personDetails_sina(result);
		    		        }else if(moduleName=="zhihu"){
		    		        	  personDetails_zhihu(result);
		    		        }else if(moduleName=="linkedIn"){
		    		        	  personDetails_linkedIn(result);
		    		        }else if(moduleName=="baidu"){
		    		        	  personDetails_baidu(result);
		    		        }
		    	   };
		    	   index.get(personArray[i]).onerror=function(e){
		    		      console.log("未能从indexedDB中获得该数据。"+e.currentTarget.error.message);
		    	   }
		    }
	};
	dbConnect.onerror=function(e){
		   console.log("Error! personDetails-common:"+e.currentTarget.error.message);
	}
	
})
	
//sina人物查阅
function personDetails_sina(result){
	
	   var sina_details='';
	   var display_headImg='';
	   var display_infoCard='';
	   var display_analyse='';
	   
	   var person_img_join='';
	   var person_img_card='';
	   var person_gender='';
	   var person_nickname='';
	   var person_follow=0;
	   var person_followURL='http://weibo.com';
	   var person_fans=0;
	   var person_fansURL='http://weibo.com';
	   var person_statuses=0;
	   var person_statusesURL='http://weibo.com';
	   var person_count='';
	   var person_location='';
	   var person_card='';
	   var person_info='';
	   var person_label='';
	   var person_edu='';
	   var person_job='';
	   
	   if(result.follow!=null && result.follow!=""){
	       person_follow=result.follow;
       }
       if(result.follow_url!=null && result.follow_url!=""){
	       person_followURL=result.follow_url;
       }
       if(result.fans!=null && result.fans!=""){
	       person_fans=result.fans;
       }
       if(result.fans_url!=null && result.fans_url!=""){
	       person_fansURL=result.fans_url;
       }
       if(result.statuses!=null && result.statuses!=""){
	       person_statuses=result.statuses;
       }
       if(result.statuses_url!=null && result.statuses_url!=""){
	       person_statusesURL=result.statuses_url;
       }
	   

	   person_img_join="<a href='"+result.profile+"' target='_blank'>"
                          +"<img class='img-circle sr-h-personImg' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)' onmouseenter='personDetails_showPersonCard(this);' onmouseleave='personDetails_hidePersonCard(this)'/>" 
                        +"</a>";

       person_img_card="<a href='"+result.profile+"' target='_blank'>"
                           +"<img class='img-thumbnail sr-h-personImg100' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)'/>" 
                       +"</a>";
	   
	   //性别
	   if(personGender(result.gender)=="男"){
		    person_gender="<div class='col-md-1 col-md-offset-4' style='margin-left:135px'>"
		    	              +"<div title='男' class='sr-h-gender sr-h-gender-male'></div>"
		    	          +"</div>";
	   }else if(personGender(result.gender)=="女"){
		    person_gender="<div class='col-md-1 col-md-offset-4' style='margin-left:135px'>"
		    	              +"<div title='女' class='sr-h-gender sr-h-gender-female'></div>"
		    	          +"</div>";
	   }
	   
	   //昵称
	   if(person_gender!=""){
		    person_nickname="<div>"+result.nickname+"&nbsp;&nbsp;</div>";
	   }else{
		    person_nickname="<div class='col-md-8 col-md-offset-4' style='margin-left:135px'>"+result.nickname+"&nbsp;&nbsp;</div>";
	   }
	   
	
     //显示关注数、粉丝数、微博数
       person_count="<div class='col-md-9 col-md-offset-4' style='margin-left:120px;margin-top:2px;margin-bottom:20px'>"
                          +"<span>关注 &nbsp; <a href='"+person_followURL+"' target='_blank' style='font-weight:bold;'>"+person_follow+"</a></span>"
                          +"<span style='margin-left:12px'>粉丝 &nbsp;<a href='"+person_fansURL+"' target='_blank' style='font-weight:bold;'>"+person_fans+"</a></span>"
                          +"<span style='margin-left:12px'>微博 &nbsp;<a href='"+person_statusesURL+"' target='_blank' style='font-weight:bold;'>"+person_statuses+"</a></span>"
                    +"</div>";
       //地区
       if(result.location!=null && result.location!=""){
    	   person_location="<div class='col-md-12' style='padding-left:20px'>"
    		                  +result.location
    		             +"</div>";
       }
     
       //人物标识
       if(result.person_card!=null && result.person_card!=""){
    	   person_card="<div class='col-md-12' style='padding-left:20px'>"
    		                 +result.person_card
    		           +"</div>";
       }
	  
       //显示简介
       if(result.person_info!=null && result.person_info!=""){
      	       
      	      var tempIndex=result.person_info.indexOf("简介");
      	       if(tempIndex==0){
      	    	     var person_info_temp=result.person_info.substring(3);
      	       }else{
      	    	     person_info_temp=result.person_info;
      	       }
      	       person_info="<div class='row' style='padding-left:20px'>"
                                    +"<div class='col-md-2 sr-h-fontColor'><strong>简介：</strong></div>"
                                    +"<div class='col-md-10' style='padding-left:0px;margin-left:-10px'>"
                                          +person_info_temp
                                    +"</div>"
                               +"</div>";
       }
       //显示标签
       if(result.person_label!=null && result.person_label!=""){
      	       person_label="<div class='row' style='padding-left:20px'>"
                                     +"<div class='col-md-2 sr-h-fontColor' style=''><strong>标签：</strong></div>"
                                     +"<div class='col-md-10' style='padding-left:0px;margin-left:-10px'>"
                                            +result.person_label
                                     +"</div>"
                                +"</div>";
       }
       
       
       //显示教育信息
       if(result.person_edu!=null && result.person_edu!=""){
      	       person_edu="<div  class='row' style='padding-left:20px'>"
                                   +"<div class='col-md-3 sr-h-fontColor'><strong>教育信息:</strong></div>"
                                   +"<div class='col-md-9' style='padding-left:0px;margin-left:-20px'>"
                                          +result.person_edu
                                   +"</div>"
                              +"</div>";
       }
       
       //显示职业信息
       if(result.person_job!=null && result.person_job!=""){
      	        person_job="<div  class='row' style='padding-left:20px'>"
                                    +"<div class='col-md-3 sr-h-fontColor'><strong>职业信息:</strong></div>"
                                    +"<div class='col-md-9' style='padding-left:0px;margin-left:-20px'>"
                                           +result.person_job
                                    +"</div>"
                               +"</div>";
       }
	    
	    display_headImg="<div class='col-md-2' style='text-align:center;'>"
                            +"<div data-name='personImgJoin'>"+person_img_join+"</div>"
                            +"<br>"
                            +"<div data-name='personSource'>"
                                +"<img src='"+ROOTPATH+MODULE.SINA_IMG+"' class='sr-h-logo18' title='来自新浪微博'>"
                                +"&nbsp;&nbsp;"+result.nickname
                            +"</div>"
                       +"</div>";
	   
      	//人物基本信息卡片
	    display_infoCard="<div class='col-md-offset-2 sr-h-joinDetailsCard' style='display:none'>"
                              +"<div style='background-image:url("+ROOTPATH+MODULE.SINA_BACKGROUND_IMG+");height:110px;padding-right:10px;'>"
                                   +"<button type='button' class='btn btn-warning' style='float:right;margin-top:70px'>"
                                         +"<i class='glyphicon glyphicon-plus'></i>&nbsp;关&nbsp;注&nbsp;"
                                   +"</button>"
                              +"</div>"
                              +"<div data-name='personInfo' style='padding-top:2px;'>"
                                   +"<div class='row' style='margin-top:2px'>"
                                        +person_gender
                                        +person_nickname 
                                   +"</div>"
                                   +person_count
                                   +person_location
                                   +person_card
                                   +person_info
                                   +person_label
                                   +person_edu
                                   +person_job
                              +"</div>"
                              +"<div data-name='personImgCard' style='position:absolute;z-index:2;top:70px;left:20px'>"
                                   +person_img_card
                              +"</div>"
                          +"</div>";
	    
	    display_analyse="<div class='col-md-10'>"
	    	            +"</div>";
	    
	    
	    
	    
	    
	   sina_details="<div id='"+result.personIndex+"' style='position:relative'>"
	                      +display_headImg
	                      +display_infoCard
                          +display_analyse	                    
	                     +"<div style='clear:both'></div>"
	                +"</div><hr>";
	    
	   $("#personDetails").append(sina_details);
}
//zhihu人物查阅
function personDetails_zhihu(result){
	
	   var zhihu_details='';
	   
	   var display_headImg='';
	   var display_infoCard='';
	   var display_analyse='';
	   
	   
	   
	   
	   var person_img_join='';
	   var person_img_card='';
	   var person_gender='';
	   var person_name='';
	   var person_answersNum=0;
	   var person_postsNum=0;
	   var person_followersNum=0;
	   var person_count='';
	   var person_info='';
	   
	   
	   
	   if(result.answers_num!=null && result.answers_num!=""){
   	        person_answersNum=result.answers_num;
       }
       if(result.posts_num!=null && result.posts_num!="" ){
   	        person_postsNum=result.posts_num;
       }
       if(result.followers_num!=null && result.followers_num!=""){
   	        person_followersNum=result.followers_num;
       }
	   
       
       
       
	   person_img_join="<a href='"+result.profile+"' target='_blank'>"
                           +"<img class='img-circle sr-h-personImg' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)'  onmouseenter='personDetails_showPersonCard(this);' onmouseleave='personDetails_hidePersonCard(this)'/>" 
                       +"</a>";
	   
	   person_img_card="<a href='"+result.profile+"' target='_blank'>"
                          +"<img class='img-thumbnail sr-h-personImg100' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)'/>" 
                       +"</a>";
	   
	 //性别
	   if(personGender(result.gender)=="男"){
		    person_gender="<div class='col-md-1 col-md-offset-4' style='margin-left:135px'>"
		    	              +"<div title='男' class='sr-h-gender sr-h-gender-male'></div>"
		    	          +"</div>";
	   }else if(personGender(result.gender)=="女"){
		    person_gender="<div class='col-md-1 col-md-offset-4' style='margin-left:135px'>"
		    	              +"<div title='女' class='sr-h-gender sr-h-gender-female'></div>"
		    	          +"</div>";
	   }
	   
	   //昵称
	   if(person_gender!=""){
		    person_name="<div class='col-md-7'>"+result.name+"&nbsp;&nbsp;</div>";
	   }else{
		    person_name="<div class='col-md-8 col-md-offset-4' style='margin-left:135px'>"+result.name+"&nbsp;&nbsp;</div>";
	   }
	   
	   //显示回答数、文章数、关注者
       person_count="<div class='col-md-9 col-md-offset-4' style='margin-left:120px;margin-top:2px;margin-bottom:20px'>"
                          +"<span>回答数 &nbsp;<strong class='sr-h-fontColor'>"+person_answersNum+"</strong></span>"
                          +"<span style='margin-left:12px'>文章数&nbsp;<strong class='sr-h-fontColor'>"+person_postsNum+"</strong></span>"
                          +"<span style='margin-left:12px'>关注者 &nbsp;<strong class='sr-h-fontColor'>"+person_followersNum+"</strong></span>"
                    +"</div>";
	   
     //显示简介
       if(result.bio!=null && result.bio!=""){
      	     person_info="<div class='row' style='padding-left:20px'>"
                               +"<div class='col-md-2 sr-h-fontColor'><strong>简介：</strong></div>"
                               +"<div class='col-md-10' style='padding-left:0px;margin-left:-10px'>"
                                     +result.bio
                               +"</div>"
                         +"</div>";
       } 
	     
	   
	   display_headImg="<div class='col-md-2' style='text-align:center;'>"
                           +"<div data-name='personImgJoin'>"+person_img_join+"</div>"
                           +"<br>"
                           +"<div data-name='personSource'>"
                                +"<img src='"+ROOTPATH+MODULE.ZHIHU_IMG+"' class='sr-h-logo18' title='来自知乎社区'>"
                                +"&nbsp;&nbsp;"+result.name
                           +"</div>"
                       +"</div>";
	  
	 //人物基本信息卡片
	    display_infoCard="<div class='col-md-offset-2 sr-h-joinDetailsCard' style='display:none'>"
                             +"<div style='background-image:url("+ROOTPATH+MODULE.ZHIHU_BACKGROUND_IMG+");height:110px;padding-right:10px;'>"
                                 /* +"<button type='button' class='btn btn-warning' style='float:right;margin-top:70px'>"
                                        +"<i class='glyphicon glyphicon-plus'></i>&nbsp;关&nbsp;注&nbsp;"
                                  +"</button>"*/
                             +"</div>"
                             +"<div data-name='personInfo' style='padding-top:2px;'>"
                                  +"<div class='row' style='margin-top:2px'>"
                                       +person_gender
                                       +person_name
                                  +"</div>"
                                  +person_count
                                  +person_info
                             +"</div>"
                             +"<div data-name='personImgCard' style='position:absolute;z-index:2;top:70px;left:20px'>"
                                   +person_img_card
                             +"</div>"
                         +"</div>";

	    
	    
	   zhihu_details="<div id='"+result.personIndex+"' style='position:relative'>"
                        +display_headImg
                        +display_infoCard
                        +display_analyse	                    
                        +"<div style='clear:both'></div>"
                     +"</div><hr>";
	
	
	  $("#personDetails").append(zhihu_details);
}

//linkedIn
function personDetails_linkedIn(result){
	  var linkedIn_details='';
	  var display_headImg='';
	  var display_infoCard='';
	  var display_analyse='';
	
	  var person_img_join='';
	  var person_img_card='';
	  var person_name='';
	  var person_industry='';
	  var person_location='';
	  var person_headline='';
	  var person_curPositions='';
	  var person_prePositions='';
	  
	  
	  
	  
	  person_img_join="<a href='"+result.profile+"' target='_blank'>"
                          +"<img class='img-circle sr-h-personImg' src='"+result.profile_img+"'  onerror='personImgSubstitute(this)' onmouseenter='personDetails_showPersonCard(this);' onmouseleave='personDetails_hidePersonCard(this)'/>" 
                      +"</a>";

      person_img_card="<a href='"+result.profile+"' target='_blank'>"
                          +"<img class='img-thumbnail sr-h-personImg100' src='"+result.profile_img+"'  onerror='personImgSubstitute(this)'/>" 
                      +"</a>";
      person_name="<div class='col-md-8 col-md-offset-4' style='margin-left:135px'>"+result.name+"</div>";
	  
      
      
	  if(result.industry!=null && result.industry!=''){
		   person_industry="<div class='col-md-8 col-md-offset-4' style='margin-left:135px;margin-top:2px;margin-bottom:20px'>"+result.industry+"</div>";
	  }else{
		   person_industry="<div style='margin-top:2px;margin-bottom:20px'></div>"
	  }
	
	  //地区
      if(result.location!=null && result.location!=''){
   	       person_location="<div class='col-md-12' style='padding-left:20px'>"
   		                        +result.location
   		                   +"</div>";
      }
    //职业头衔
      if(result.headline!=null && result.headline!=''){
     	   person_headline="<div  class='row' style='padding-left:20px'>"
                                 +"<div class='col-md-3 sr-h-fontColor'><strong>职业头衔:</strong></div>"
                                 +"<div class='col-md-9' style='padding-left:0px;margin-left:-20px'>"
                                       +result.headline
                                 +"</div>"
                            +"</div>";
      }
      
    //显示目前就职
      if(result.cur_positions!=null && result.cur_positions!=''){
     	     person_curPositions="<div class='row' style='padding-left:20px'>"
                                       +"<div class='col-md-3 sr-h-fontColor'><strong>目前就职: </strong></div>"
                                       +"<div class='col-md-9' style='padding-left:0px;margin-left:-20px'>"
                                            +result.cur_positions
                                       +"</div>"
                                 +"</div>";
      }
      
      //显示曾经就职
      if(result.pre_positions!=null && result.pre_positions!=''){
     	     person_prePositions="<div class='row' style='padding-left:20px'>"
                                       +"<div class='col-md-3 sr-h-fontColor'><strong>曾经就职: </strong></div>"
                                       +"<div class='col-md-9' style='padding-left:0px;margin-left:-20px'>"
                                            +result.pre_positions
                                       +"</div>"
                                 +"</div>";
      }
      
      
	
      display_headImg="<div class='col-md-2' style='text-align:center;'>"
                           +"<div data-name='personImgJoin'>"+person_img_join+"</div>"
                           +"<br>"
                           +"<div data-name='personSource'>"
                                +"<img src='"+ROOTPATH+MODULE.LINKEDIN_IMG+"' class='sr-h-logo18' title='来自领英社交'>"
                                +"&nbsp;&nbsp;"+result.name
                           +"</div>"
                      +"</div>";
	
  	//人物基本信息卡片
	    display_infoCard="<div class='col-md-offset-2 sr-h-joinDetailsCard' style='display:none'>"
                            +"<div style='background-image:url("+ROOTPATH+MODULE.LINKEDIN_BACKGROUND_IMG+");height:110px;padding-right:10px;'>"
                                /* +"<button type='button' class='btn btn-warning' style='float:right;margin-top:70px'>"
                                       +"<i class='glyphicon glyphicon-plus'></i>&nbsp;关&nbsp;注&nbsp;"
                                 +"</button>"*/
                            +"</div>"
                            +"<div data-name='personInfo' style='padding-top:2px;'>"
                                 +"<div class='row' style='margin-top:2px'>"
                                      +person_name
                                      +person_industry
                                 +"</div>"
                            +"</div>"
                            +"<div data-name='personImgCard' style='position:absolute;z-index:2;top:70px;left:20px'>"
                                 +person_img_card
                            +"</div>"
                            +person_location
                            +person_headline
                            +person_curPositions
                            +person_prePositions
                        +"</div>";
	
	
      linkedIn_details="<div id='"+result.personIndex+"' style='position:relative'>"
                         +display_headImg
                         +display_infoCard
                         +display_analyse	                    
                         +"<div style='clear:both'></div>"
                    +"</div><hr>";
	
	
	
	 $("#personDetails").append(linkedIn_details);
}
//baidu
function personDetails_baidu(result){
	
	  var baidu_details='';
	  var display_headImg='';
	  var display_infoCard='';
	  var display_analyse='';
	  
	  var person_img_join='';
	  var person_img_card='';
	  var person_title='';
	  var person_lastUpdateDate='';
	  
	  
	  
	  
	  person_img_join="<a href='"+result.link+"' target='_blank'>"
                         +"<img class='img-circle sr-h-personImg' src='"+(ROOTPATH+MODULE.NullPERSON)+"' onmouseenter='personDetails_showPersonCard(this);' onmouseleave='personDetails_hidePersonCard(this)'/>" 
                      +"</a>";

      person_img_card="<a href='"+result.profile+"' target='_blank'>"
                         +"<img class='img-thumbnail sr-h-personImg100' src='"+(ROOTPATH+MODULE.NullPERSON)+"'/>" 
                      +"</a>";
      
      person_title="<div class='col-md-8 col-md-offset-4' style='margin-left:135px'>"+result.title+"&nbsp;&nbsp;</div>";
      person_lastUpdateDate="<div class='col-md-8 col-md-offset-4' style='margin-left:135px'><strong class='sr-h-fontColor'>更新时间：</strong>"+result.lastUpdateDate+"&nbsp;&nbsp;</div>";
      
      
      
      
      display_headImg="<div class='col-md-2' style='text-align:center;'>"
                           +"<div data-name='personImgJoin'>"+person_img_join+"</div>"
                           +"<br>"
                           +"<div data-name='personSource'>"
                                +"<img src='"+ROOTPATH+MODULE.BAIDU_IMG+"' class='sr-h-logo18' title='来自百度百科'>"
                                +"&nbsp;&nbsp;"+result.title
                           +"</div>"
                      +"</div>";
      
      
      
    //人物基本信息卡片
	    display_infoCard="<div class='col-md-offset-2 sr-h-joinDetailsCard' style='display:none'>"
                            +"<div style='background-image:url("+ROOTPATH+MODULE.BAIDU_BACKGROUND_IMG+");height:110px;padding-right:10px;'>"
                                 /*+"<button type='button' class='btn btn-warning' style='float:right;margin-top:70px'>"
                                       +"<i class='glyphicon glyphicon-plus'></i>&nbsp;关&nbsp;注&nbsp;"
                                 +"</button>"*/
                            +"</div>"
                            +"<div data-name='personInfo' style='padding-top:2px;'>"
                                 +"<div class='row' style='margin-top:2px'>"
                                      +person_title
                                      +person_lastUpdateDate
                                 +"</div>"
                                 
                            +"</div>"
                            +"<div data-name='personImgCard' style='position:absolute;z-index:2;top:70px;left:20px'>"
                                 +person_img_card
                            +"</div>"
                        +"</div>";
      
      
      
      baidu_details="<div id='"+result.personIndex+"' style='position:relative'>"
                         +display_headImg
                         +display_infoCard
                         +display_analyse	                    
                         +"<div style='clear:both'></div>"
                    +"</div><hr>";
	
      $("#personDetails").append(baidu_details);
	
}
//显示基本信息卡片
function personDetails_showPersonCard(img){
	  
      var obj=$(img).parent().parent();
      $(obj).parent().next().show();
}
//隐藏基本信息卡片
function personDetails_hidePersonCard(img){
	  var obj=$(img).parent().parent();
	  setTimeout(function(){$(obj).parent().next().hide();},300);
}
