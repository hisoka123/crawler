/**
 * localstorage
 * 
 *    索引                                     示例                         注解
 * 
 * linkedin_whichContrast                profile**##**profile        两个人的profile,以“**##**”为分隔,用完删除，再用时，重新生成
 * 
 * 
 * 
 * */




/*display linkedIn search result*/
function linkedIn_displaySearchResults(searchResults,person){
	      
	      if(searchResults==null || searchResults==''){
	    	   return "<p class='sr-h-nullPerson'>未能搜索到与&nbsp;<strong><i>"+person+"</i></strong>&nbsp;&nbsp;相关的人物信息。</h4>";
	      }
	      
	      var display='';
	      var display_content='';
	      
	      var display_img='';
	      var display_name='';
	      var display_locationIndus='';
	      var display_headline='';
	      var display_cur='';
	      var display_pre='';
	      
	      var profile_img='';
	      var profile='';
	      var name='';
	      var location='';
	      var industry='';
	      var cur_positions='';
	      var pre_positions='';
	      
	      
	      var officialWebsite='https://www.linkedin.com/';
	      var hr="<hr class='col-md-10'>";
	      
	      
	      $.each(searchResults,function(index,searchResult){
	    	  
		          display_content='';
		      
		          display_img='';
		          display_name='';
		          display_locationIndus='';
		          display_headline='';
		          display_cur='';
		          display_pre='';
	    	        
	    	      profile_img="";
	    	      profile='https://www.linkedin.com/';
	    	      name='';
	    	      location='';
	    	      industry='';
	    	      cur_positions='';
	    	      pre_positions='';
	    	      
	    	      if(searchResult.profile_img!=null && searchResult.profile_img!=""){
	    	    	     profile_img=searchResult.profile_img;
	    	      }
	    	      if(searchResult.profile!=null && searchResult.profile!=""){
	    	    	     profile=searchResult.profile;
	    	      }
	    	      if(searchResult.name!=null && searchResult.name!=""){
	    	    	     name=searchResult.name;
	    	      }else{
	    	    	     return true;
	    	      }
	    	      if(searchResult.location!=null && searchResult.location!=""){
	    	    	      location=searchResult.location;
	    	      }
	    	      if(searchResult.industry!=null && searchResult.industry!=""){
	    	    	      industry=searchResult.industry;
	    	      }
	    	      
	    	      
	    	      //显示头像
	    	      display_img="<a  href='"+profile+"' target='_blank'>"
                                   +"<img class='media-object img-circle sr-h-personImg' src='"+profile_img+"'  onerror='personImgSubstitute(this)'/>" 
                              +"</a>";
	    	  
	    	      //显示姓名,加入详情按钮
	    	      display_name="<div class='row'>"
                                    +"<h4 class='media-heading col-md-7' style='margin-top:1%;'>"
                                          +"<a href='"+profile+"' target='_blank'>"+name+"</a>"
                                    +"</h4>"
                                    +"<div class='col-md-3 col-md-offset-1' style='text-align:center'>"
                                          +"<button class='btn btn-warning' type='button'>"
                                                 +"<i id='linkedIn_searchResults_"+index+"_"+$.md5(searchResult.profile)+"_joinDetail' class='glyphicon glyphicon-plus'></i>&nbsp;加入详情&nbsp;"
                                          +"</button>"
                                    +"</div>"
                               +"</div>";
	    	      
	    	      //显示地区、行业
	    	      if(location!='' || industry!=''){
	    	    	       if(location!='' && industry!=''){
	    	    	            display_locationIndus="<div class='row' style='margin-top:1%'>"
	    	    	            	                        +"<div class='col-md-10'>"
	    	    	    	                                       +location+"&nbsp;&nbsp;|&nbsp;&nbsp;"+industry
	    	    	    	                                +"</div>"
	    	    	    	                          +"</div>";
	    	    	       }else{
	    	    	    	    display_locationIndus="<div class='row' style='margin-top:1%'>"
	    	    	    	    	                        +"<div class='col-md-10'>&nbsp;&nbsp;&nbsp;"
	                                                               +location+industry
	                                                        +"</div>"
	                                                   +"</div>";  
	    	    	       }
	    	      }
	    	      
	    	      //显示头衔
	    	      if(searchResult.headline!=null && searchResult.headline!=""){
	    	    	       display_headline="<div class='row' style='margin-top:1%'>"
                                                  +"<div class='col-md-2'>职业头衔:</div>"
                                                  +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                         +searchResult.headline
                                                  +"</div>"
                                           +"</div>";
	    	      }
	    	      
	    	      //显示目前就职
	    	      if(searchResult.cur_positions!=null && searchResult.cur_positions!="" ){
	    	    	       for(var i=0;i<searchResult.cur_positions.length;i++){
	    	    	              cur_positions +=searchResult.cur_positions[i];
	    	    	              if(i<(searchResult.cur_positions.length-1)){
	    	    	    	           cur_positions +="<br>";
	    	    	              }
	    	                }
	    	    	        display_cur="<div class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>目前就职:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +cur_positions
                                              +"</div>"
                                         +"</div>";
	    	      }
	    	      
	    	      //显示曾经就职
	    	      if(searchResult.pre_positions!=null && searchResult.pre_positions!=""){
   	    	            for(var i=0;i<searchResult.pre_positions.length;i++){
   	    	                   pre_positions +=searchResult.pre_positions[i];
   	    	                   if(i<(searchResult.pre_positions.length-1)){
   	    	    	                pre_positions +="<br>";
   	    	                   }
   	                    }
   	    	            display_pre="<div class='row' style='margin-top:1%'>"
                                         +"<div class='col-md-2'>曾经就职:</div>"
                                         +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                +pre_positions
                                         +"</div>"
                                    +"</div>";
   	               }
	    	      
	    	      
	    	      
	    	      display_content=display_name
                                  +display_locationIndus
                                  +display_headline
                                  +display_cur
                                  +display_pre;
	    	      
	    	      display+=displayResults(index,"linkedIn_searchResults_",searchResults.length,"linkedIn_more","显示全部领英人物",display_img,"linkedIn_name_",ROOTPATH+MODULE.LINKEDIN_IMG,"领英社交",display_content,searchResult.profile);
	    	      
	    	  
	      })
	
	     return display;
	
}

function linkedIn_action(searchKey){
	   
	 if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
   	      $("#linkedIn_searchResults").html(linkedIn_displaySearchResults(linkedIn_getSearchResult(searchKey),searchKey));
   	      $("#linkedIn_more").remove();
   	      $("div[id^=linkedIn_name_]").remove();
   	      $("#linkedIn_searchResults").children().show();
   	      $("#linkedIn_searchResults").show();
   	      $("#linkedIn_searchResultsRightIcon").show();
   	      if($("hr").css("margin-top")=="0px"){
   	           $("hr").removeAttr("style");
          }
   	       module_action_reload("linkedIn",searchKey);
    }
}

//跳转至对比详情
function linkedIn_jumpContrast(linkedInDetailURL){
	
	    var length='',dataName='';
	
	    length=$("#contrast_detail ul").children("li").length;
	    for(var i=0;i<length;i++){
	    	
               dataName +=$("#contrast_detail ul").children("li").eq(i).find("img[data-name]").attr("data-name"); 
               if(i<(length-1)){
            	    dataName += "**##**";   //分隔符
               }
	    }
	    
	    localStorage.setItem("linkedin_whichContrast",dataName);
	    
	    window.open(linkedInDetailURL);
}


























































/*----------------------------------------------------*/
/*----------------------------------------------------*/
/*----------------------------------------------------*/
/*----------------------------------------------------*/
/*----------------------------------------------------*/






//(1-1-1)图片替换caret
function linkedIn_common_imgNoFind(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/linkedIn.ico";      
    img.onerror=null;
}
//(1-1-2)头像替换
function linkedIn_common_personImg(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/person.png";      
    img.onerror=null;
}
//(1-2)js截取字符串(支持中英文混合)
function linkedIn_common_subAndAll(allContent,num){

	     var reg=/[^\x00-\xff]/g;
	     if(allContent.replace(reg,"mm").length<=num){
	    	     return allContent;
	     }
	     var len=Math.floor(num/2);
	     for(var i=len;i<allContent.length;i++){
	    	     if(allContent.substring(0,i).replace(reg,"mm").length>=num){
	    	    	        return allContent.substring(0,i)+'...';
	    	     }
	     }
}

//(1-3)下三角显示/隐藏
function linkedIn_common_showAndHide(idName){
	
	   if($("#"+idName).css("display")=='none'){
		       $("#"+idName).show();
	   }else{
		       $("#"+idName).hide();
	   }
	
}
//(1-4)文字'展开'
function linkedIn_common_details(p,idName){
	  
	  var short_id=$(p).parent().attr("id");
	  var index=short_id.split("_")[1];
	  $("#"+short_id).hide();
	  $("#"+idName+"_"+index+"_descFull").show();

}
//(1-5)文字'收起'
function linkedIn_common_back(p,idName){
	  
	  var full_id=$(p).parent().attr("id");
	  var index=full_id.split("_")[1];
	  $("#"+full_id).hide();
	  $("#"+idName+"_"+index+"_descSub").show();

}
//(1-6)模块
function linkedIn_common_model(modelTitleId,modelContentId,modelIco,modelName,modelContent){

	   var model='';
	   model='<div class="panel panel-default">'
                  +'<div class="panel-heading">'
                         +'<h4 class="panel-title">'
                              +'<a id="'+modelTitleId+'" data-toggle="collapse" href="#'+modelContentId+'" style="text-decoration:none;color:#428bca;font-weight:bold">'
                                  +'<i class="'+modelIco+'"></i>&nbsp;&nbsp;'+modelName
                              +'</a>'
                         +'</h4>'
                  +'</div>'       
                  +'<div id="'+modelContentId+'" class="panel-collapse collapse in">'
                         +'<div class="panel-body">'
                                +modelContent
                         +'</div>'
                 +'</div>'
             +'<div>';	 
	
	    return model;
}
//(1-7)移到元素上，文字变色
function linkedIn_common_textColorMove(text){
	  text.style.color='#069'; 
}
//(1-8)移开元素，文字变色
function linkedIn_common_textColorOut(text){
	     text.style.color='#000';
}
//(1-9)文字的全部与部分显示,desc字段
function linkedIn_common_textShow(modelName,index,desc,desc_html){
	// alert(modelName);
	 if(linkedIn_common_subAndAll(desc,300)!=desc){
 	     
              return  '<div id="'+modelName+'_'+index+'_descSub" style="margin-top:1%;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+linkedIn_common_subAndAll(desc,300)
                            +'<span style="cursor:pointer;color:#069;font-weight:bold" onclick="linkedIn_common_details(this,\''+modelName+'\')">展开</span>'
                      +'</div>'
                      +'<div id="'+modelName+'_'+index+'_descFull" style="margin-top:1%;font-size:15px;display:none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+desc_html
                            +'<span style="cursor:pointer;color:#069;font-weight:bold" onclick="linkedIn_common_back(this,\''+modelName+'\')">收起</span>'
                      +'</div>';
    }else{
             return '<div id="'+modelName+'_'+index+'_descFull" style="margin-top:1%;font-size:15px">'
                            +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                            +desc_html
                      +'</div>';
    }
}

//(2)显示基本信息
function linkedIn_common_basicInfo(rootPath,details){
	 
	  var basicInfo='';
	  var img='';                     //头像
	  var basicInfo_otherStyle='';   //除头像外
	  var headline='';               //头衔
	  var location_industry='';      //地区和行业
	  var cur_positions='';          //目前就职
	  var pre_positions='';          //曾经就职
	  var bg_educations='';          //教育背景
	  
	  
	  
	 //(2-1)头像
	 if(details.profile_img!=null){
		  var img='<div class="col-md-3" style="text-align:center;">'
			            +'<img src="'+details.profile_img+'" style="width:195px;height:195px;margin-top:10%" onerror="linkedIn_common_personImg(\''+rootPath+'\')">'
			      +'</div>';
		  basicInfo_otherStyle='<div class="col-md-8">';
	 }else{
		  basicInfo_otherStyle='<div class="col-md-10">';
	 }
	 
	 //(2-2)名字
	 var name='<h2>'+details.name+'</h2>';
	 //(2-3)头衔
     if(details.headline!=null){
    	 headline='<div style="font-size:16px">'
    		            +details.headline
    		      +'</div>';
     }	 
	 //(2-4)地区    行业
     if(details.location!=null && details.industry!=null){
    	    location_industry='<div style="font-size:14px;color:grey;margin-top:1%">'
    	    	                    +details.location+'&nbsp;|&nbsp;'+details.industry
    	    	              +'</div>';
     }else if(details.location!=null && details.industry==null){
    	    location_industry='<div style="font-size:14px;color:grey;margin-top:1%">'
                                   +details.location
                               +'</div>';
     }else if(details.location==null && details.industry!=null){
    	    location_industry='<div style="font-size:14px;color:grey;margin-top:1%">'
                                    +details.industry
                              +'</div>';
     }
     
     //(2-5)目前就职
     if(details.cur_positions!=null){
    	    cur_positions='<div class="row" style="font-size:13px;margin-top:1%">'
                                +'<div class="col-md-3" style="color:grey">'
                                       +'目前就职'
                                +'</div>'
                                +'<div class="col-md-9" style="margin-left:-13%">'
                                       +details.cur_positions
                                +'</div>'
                          +'</div>';
     }
     //(2-6)曾经就职
     if(details.pre_positions!=null){
    	   
    	    pre_positions='<div class="row" style="font-size:13px;margin-top:1%">'
    	    	                +'<div class="col-md-3" style="color:grey">'
    	    	                       +'曾经就职'
    	    	                +'</div>'
    	    	                +'<div class="col-md-9" style="margin-left:-13%">'
    	    	                       +details.pre_positions
    	    	                +'</div>'
    	    	          +'</div>';
     }
     //(2-7)教育背景
     if(details.bg_educations!=null){
    	     bg_educations='<div class="row" style="font-size:13px;margin-top:1%">'
	                            +'<div class="col-md-3" style="color:grey">'
                                      +'教育背景'
                                +'</div>'
                                +'<div class="col-md-9" style="margin-left:-13%">'
                                      +details.bg_educations
                                +'</div>'
                           +'</div>';
     }
     
     
     
     //(2-1至2-7)basicInfoOther
     var basicInfo_other=basicInfo_otherStyle
                                 +name
                                 +headline
                                 +location_industry
                                 +cur_positions
                                 +pre_positions
                                 +bg_educations
                         +'</div>';
     
     
	 basicInfo=img+basicInfo_other;
	           
	 
	 return basicInfo;
}

//(3)联系方式
function linkedIn_common_contactWay(rootPath,details){
	    
	     var contactWay='';
	     var twitters='';
	     var wechat='';
	     var webs='';
	     
	     
	     
	     
	     if(details.cont_info==null){
	    	 $("#contactAction").html(" ");
	    	 $("#contactAction").css({"background-color":"#f6f6f6","cursor":"default"});
	    	 return ;
	     }
	     
	     
	     //推特列表
	     if(details.cont_info.twitters!=null){
	             var twitter_list='<ul style="list-style-type:none">';
	             for(var i=0;i<details.cont_info.twitters.length;i++){
	    	            twitter_list +='<li><a href="'+details.cont_info.twitters[i].profile+'" target="_blank">'+details.cont_info.twitters[i].account+'</a></li>';
	             }
	             twitter_list +='</ul>';
	     
	     
                 twitters='<tr valign="top">'
        	                   +'<td>'
        	                        +'<img src="'+rootPath+'/static/img/linkedIn_contactWay.png" style="position:absolute;clip:rect(350px 16px  360px  0px);margin-top:-345px">'
                               +'</td>'
        	                   +'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;推特</td>'
        	                   +'<td>'+twitter_list+'</td>'
        	               +'</tr>';	    	           
	        }
         //微信 
	     if(details.cont_info.wechat!=null){
                wechat='<tr valign="top">'
        	                +'<td>'
        	                      +'<img src="'+rootPath+'/static/img/weixin.ico" style="width:17px;height:17px">'
        	                +'</td>'
        	                +'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信</td>'
        	                +'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
        	                      +details.cont_info.wechat.account
        	                      +'&nbsp;<a href="'+details.cont_info.wechat.qr_code+'" target="_blank">'
        	                                 +'<i class="glyphicon glyphicon-qrcode" style="color:black"></i>'
   	                              +'</a>'
        	                +'</td>'
        	            +'</tr>';
	     }
	    //个人网站
         if(details.cont_info.per_sites!=null){
	           var web_list='<ul style="list-style-type:none">';
	           for(var i=0;i<details.cont_info.per_sites.length;i++){
	    	         web_list +='<li><a href="'+details.cont_info.per_sites[i].link+'" target="_blank">'+details.cont_info.per_sites[i].name+'</a></li>';
	           }
	           web_list +='</ul>';
	     
	     
               webs='<tr valign="top">'
        	             +'<td>'
        	                 +'<img src="'+rootPath+'/static/img/linkedIn_contactWay.png" style="position:absolute;clip:rect(400px 16px  410px  0px);margin-top:-395px">'
                         +'</td>'
        	             +'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人网站</td>'
        	             +'<td>'+web_list+'</td>'
        	         +'</tr>';	 
         }
         
	      
	       contactWay ='<table style="color:grey;margin-left:5%;margin-top:2%;margin-bottom:3%">'
	    	               +'<tbody>'
                                +twitters
                                +wechat
                                +webs
	    	               +'</tbody>'
	    	           +'</table>';
	       
	       return contactWay;

}

//(4)发布文章
function linkedIn_common_article(details){
	
	     var articles='';
	
	     if(details.articles==null){
	    	 $("#articles").remove();
	    	 return;
	     }
	     
	     
	     
	     var articles_list='<div class="row">';
         for(var i=0;i<details.articles.length;i++){
                articles_list +='<div class="col-md-3" style="margin-left:5%">'
                	                  +'<a href="'+details.articles[i].link+'" target="_blank"><img src="'+details.articles[i].pre_img+'" /></a><br>'
                	                  +'<a href="'+details.articles[i].link+'" target="_blank"><h4>'+details.articles[i].title+'</h4></a>'
                	                  +'<p style="color:grey">'+details.articles[i].pub_date+'</p>'
                	            +'</div>';
         }
	     articles_list +='</div>';
	     
	     return linkedIn_common_model('articlesTitle','articlesContent','glyphicon glyphicon-file','发布文章',articles_list);
}

//(5)自我介绍
function linkedIn_common_bgSummary(details){
	     var bgSummary=''; 
	     
	    if(details.bg_summary==null){
	    	$("#bg_summary").remove();
	    	return;
	    }
	    
	    return linkedIn_common_model('bgSummaryTitle','bgSummaryContent','glyphicon glyphicon-user','自我简介',details.bg_summary_html);
	    
}

//(6)工作经历
function linkedIn_common_jobInfos(rootPath,details){
	
	     var jobInfos=''; 
	     var jobInfos_list='';
	     var jobInfos_position='';
	     var jobInfos_company='';
	     var jobInfos_dateLocation='';
	     var jobInfos_companyImg='';
	     var jobInfos_desc='';
	     
	     if(details.jobInfos==null){
	    	     $("#jobInfos").remove();
	    	     return;
	     }
	     
	     
	     $.each(details.jobInfos,function(index,jobInfos){
	    	       
	    	      
		           jobInfos_position='';
		           jobInfos_company='';
		           jobInfos_dateLocation='';
		           jobInfos_companyImg='';
		           jobInfos_desc='';
	    	 
	    	 
	    	      //职位
	    	      if(jobInfos.position!=null){
	    	    	  jobInfos_position='<div class="title">'+jobInfos.position+'</div>';
	    	      }
	    	      //公司
	    	      if(jobInfos.company!=null){
	    	    	   jobInfos_company='<div style="font-size:15px">'+jobInfos.company+'</div>';
	    	      }
	    	      //时间|地区
	    	      if(jobInfos.exp_date!=null && jobInfos.location!=null){
	    	    	    jobInfos_dateLocation='<div style="color:grey">'
	    	    		                           +jobInfos.exp_date
	    	    		                           +'&nbsp;&nbsp;|&nbsp;&nbsp;'
	    	    		                           +jobInfos.location     
	    	    		                      +'</div>';
	    	      }else if(jobInfos.exp_date!=null && jobInfos.location==null){
	    	    	     jobInfos_dateLocation='<div style="color:grey">'
                                                      +jobInfos.exp_date
                                               +'</div>';
	    	      }else if(jobInfos.exp_date==null && jobInfos.location!=null){
	    	    	     jobInfos_dateLocation='<div style="color:grey">'
                                                     +jobInfos.location     
                                               +'</div>';
	    	      }
	    	      //公司logo
	    	      if(jobInfos.company_img!=null && jobInfos.company_link!=null){
	    	    	        jobInfos_companyImg='<a href="'+jobInfos.company_link+'" target="_blank"><img src="'+jobInfos.company_img+'" /></a>';
	    	      }else if(jobInfos.company_img!=null && jobInfos.company_link==null){
	    	    	        jobInfos_companyImg='<img src="'+jobInfos.company_img+'" />';
	    	      }
	    	      
	    	      //工作描述
	    	      if(jobInfos.desc!=null && jobInfos.desc_html!=null){
	    	    	        jobInfos_desc=linkedIn_common_textShow("jobInfos",index,jobInfos.desc,jobInfos.desc_html)
	    	      }
	    	      
	    	      //荣誉奖励
	    	      var jobInfos_honor='';
	    	      if(jobInfos.honorawards!=null){
	    	    	  jobInfos_honor=linkedIn_common_jobInfosHonor(rootPath,jobInfos.honorawards,index);
	    	      }
	    	     
	    	      
	    	      //推荐信
	    	      var jobInfos_endorsement='';
	    	      if(jobInfos.endorsements!=null){
	    	    	  jobInfos_endorsement=linkedIn_common_jobInfosEndorsement(rootPath,jobInfos.endorsements,index);
	    	      }
	    	      
	    	      //所做项目
	    	      var jobInfos_project='';
	    	      if(jobInfos.projects!=null){
	    	    	    jobInfos_project=linkedIn_common_jobInfosProject(jobInfos.projects,index);
	    	      }

	    	      
	    	      
	    	      
	    	      
	    	      var hr='';
	    	      if(index<(details.jobInfos.length-1)){
	    	    	   hr='<hr>';
	    	      }
	              
	    	      jobInfos_list +='<div id="jobInfo_'+index+'">'
	    	                            +'<div id="jobInfo_'+index+'_details" style="margin-bottom:1%">'
                                               +'<div class="row">'
	    	                                          +'<div class="col-md-10">'
                                                             +jobInfos_position
                                                             +jobInfos_company
                                                             +jobInfos_dateLocation
                                                      +'</div>'
                                                      +'<div class="col-md-2" style="text-align:center">'
                                                             +jobInfos_companyImg
                                                      +'</div>'
                                                +'</div>'
                                               +jobInfos_desc
                                        +'</div>'
                                        +jobInfos_honor
                                        +jobInfos_endorsement
                                        +jobInfos_project
                                    +'</div>'
                                    +hr;
	     });
	
	
	     return linkedIn_common_model('jobInfosTitle','jobInfosContent','glyphicon glyphicon-list-alt','工作经历',jobInfos_list);
	
}

//(6-1)工作经历中的荣誉内容
function linkedIn_common_jobInfosHonor(rootPath,jobInfosHonors,index){
	
	   var summary='';
	   var desc='';
	   var jobHonors_list='';
	
	
	   $.each(jobInfosHonors,function(order,honors){
		   
		        summary='';
		        desc='';
		   
		        if(honors.summary==null){
		        	return true;
		        }else{
		        	summary='<div class="sonTitle">'
		        		          +honors.summary
		        		          +'&nbsp;&nbsp;<a href="#honorTitle" onmousemove="linkedIn_common_simulationClick(\'honorTitle\')">查看</a>'
		        		    +'</div>';
		        }
              
		        if(honors.desc!=null){
		        	desc='<div style="font-size:13px">'
		        		       +honors.desc
		        		  +'</div>';
		        }
		        
		        jobHonors_list +='<div id="jobInfo_'+index+'_honors_'+order+'" style="margin-top:1%">'
		        	                   +summary
		        	                   +desc
		        	             +'</div>';
	   })  
	
	   var jobHonors='<div id="jobInfo_'+index+'_honors" style="margin-top:1%;">'
		                   +'<div id="jobInfo_'+index+'_honors_title" style="font-size:13px;font-family:黑体">'
                                  +jobInfosHonors.length+'项荣誉和奖励'
                                  +'<span class="caret" onclick="linkedIn_common_showAndHide(\'jobInfo_'+index+'_honors_list\')" style="cursor:pointer"></span>'
		                   +'</div>'
		                   +'<div id="jobInfo_'+index+'_honors_list" style="display:none">'
                                  +jobHonors_list
                           +'</div>'
		             +'</div>';
	   
	   return jobHonors;
}

//(6-2)工作经历中的推荐信内容
function linkedIn_common_jobInfosEndorsement(rootPath,jobInfosEndorsements,index){
	
	var name='';
	var headline='';
	var profile='';
	var content='';
	var status='';
	var profile_img='';
	var endorsements_list='';
	
	$.each(jobInfosEndorsements,function(order,endorsement){
		
		
		    name='';
		    headline='';
		    profile='';
		    content='';
		    status='';
		    profile_img='';
		
		    
		    if(endorsement.endorser==null){
		         return true;
	        }else{
		         if(endorsement.endorser.name==null){
			          return true;
		         }
	        }
		
		   
		    if(endorsement.endorser.profile_img!=null){
			      profile_img='<img src="'+endorsement.endorser.profile_img+'" style="width:40px;height:40px" onerror="linkedIn_common_personImg(\''+rootPath+'\')" />';
		    }else{
			      profile_img='<img src="'+rootPath+'/static/img/person.png" style="width:40px;height:40px" />';
		    }
		 
		 
		 if(endorsement.endorser.headline!=null){
			   headline='<div class="attrText">'+endorsement.endorser.headline+'</div>';
		 }
		 
		 
		 
		 if(endorsement.endorser.profile!=null){
			  name='<div class="sonTitle"><a href="'+endorsement.endorser.profile+'">'+endorsement.endorser.name+'</a></div>';
		 }else{
			  name='<div class="sonTitle">'+endorsement.endorser.name+'</div>';
		 }
		 
		 
		 if(endorsement.content!=null){
			  content='<div style="font-size:13px">'
				            +endorsement.content
				            +'<a href="#endorsementsTitle" onmousemove="linkedIn_common_simulationClick(\'endorsementsTitle\')">查看</a>'
				      +'</div>';
		 }
		
		 
		 endorsements_list +='<div id="jobInfo_'+index+'_endorsements_'+order+'" class="row">'
                                   +'<div class="col-md-1" style="text-align:center;">'
                                          +profile_img
                                   +'</div>'
                                   +'<div class="col-md-9">'
                                          +name
                                          +headline
                                          +content
                                   +'</div>'
                             +'</div><br>';
	})
		
	var endorsements='<div id="jobInfo_'+index+'_endorsements" style="margin-top:1%">'
		                   +'<div id="jobInfo_'+index+'_endorsements_title" style="font-size:13px;font-family:黑体">'
                                 +jobInfosEndorsements.length+'封推荐信'
                                 +'<span class="caret" onclick="linkedIn_common_showAndHide(\'jobInfo_'+index+'_endorsements_list\')" style="cursor:pointer"></span>'
                           +'</div>'
                           +'<div id="jobInfo_'+index+'_endorsements_list" style="display:none">'
                                 +endorsements_list
                           +'</div>'
		             +'</div>';

	return     endorsements;
}
//(6-3)工作经历中项目
function linkedIn_common_jobInfosProject(jobInfosProjects,index){
	
	     
	    var project_name='';
	    var projects_list='';
	    
	    
	    $.each(jobInfosProjects,function(order,jobInfosProjects){
	    	
	    	     project_name='';
	    	
	    	      if(jobInfosProjects.name==null){
	    	    	    return true;
	    	      }
	    	      
	    	      project_name ='<div id="jobInfos_'+index+'_projects_'+order+'" class="sonTitle" style="margin-top:1%">'
	    	    	                 +jobInfosProjects.name
	    	    	                 +'&nbsp;&nbsp;<a href="#projectTitle" style="font-size:13px" onmousemove="linkedIn_common_simulationClick(\'projectTitle\')">查看</a>'
	    	    	           +'</div>';
	    	                   
	    	      projects_list +=project_name;
	    })
	
	    
	    var projects='<div id="jobInfo_'+index+'_projects" style="margin-top:1%">'
	    	               +'<div id="jobInfo_'+index+'_projects_title" style="font-size:13px;font-family:黑体">'
                                 +jobInfosProjects.length+'个项目'
                                 +'<span class="caret" onclick="linkedIn_common_showAndHide(\'jobInfo_'+index+'_projects_list\')" style="cursor:pointer"></span>'
                           +'</div>'
                           +'<div id="jobInfo_'+index+'_projects_list" style="display:none">'
                                 +projects_list
                           +'</div>'
	    	         +'</div>';
	    
	
	    return projects;
}
/*
 *(7)荣誉
 * 
 */
function linkedIn_common_honorawards(details){

	     var summary='';
	     var org='';
	     var honors_date='';
	     var desc='';
	     var honors_list='';
	
	
	
	
	     if(details.honorawards==null){
	    	 $("#honorawards").remove();
	    	 return;
	     }
	     
	     $.each(details.honorawards,function(index,honors){
	           	 
	    	    summary='';
		        org='';
		        honors_date='';
		        desc='';
	    	   
		        if(honors.summary==null){
		        	return true;
		        }else{
		        	summary='<div class="title">'+honors.summary+'</div>';
		        }
		        
		        if(honors.org!=null){
		        	 org='<div class="attrText">'+honors.org+'</div>';
		        }
		        
		        if(honors.honors_date!=null){
		        	 honors_date='<div class="attrText">'+honors.honors_date+'</div>';
		        }
	    	 
		        if(honors.desc!=null && honors.desc_html!=null){
		        	   desc=linkedIn_common_textShow("honor",index,honors.desc,honors.desc_html);
		        }
	    	 
		        
		        var hr='';
		        if(index<(details.honorawards.length-1)){
		        	   hr='<hr>'
		        }
		        
		        
	    	     honors_list +='<div id="honor_'+index+'">'
	    	                         +summary
                                     +org
                                     +honors_date
                                     +desc
                               +'</div>'
                               +hr;
	    	 
	     })
	     
	    
	     return linkedIn_common_model('honorTitle','honorContent','glyphicon glyphicon-flag','荣誉奖项',honors_list);
	
}
//(8)模拟单击
function linkedIn_common_simulationClick(moduleId){
	/*
	 *如果具体模块是折叠的，那么进行模拟单击
	 *(1)荣誉奖励模块Id     honorTitle
	 *(2)推荐信模块Id       endorsementsTitle
	 *(3)所做项目Id         projectTitle           
	 */
	
	if(document.getElementById(moduleId)!=null){
          if(document.getElementById(moduleId).getAttribute("aria-expanded")=="false"){
                $("#"+moduleId).click();
          }
	}
	
}
//(9)参与组织
function linkedIn_common_partiOrgs(details){
	    
	    var orgs_name='';
	    var orgs_role='';
	    var orgs_date='';
	    var orgs_desc='';
	    var orgs_cate='';
	    var orgs_position='';
	   
	    var orgs_list='';
	    var orgs='';
	
	
	    if(details.parti_orgs==null){
	    	    $("#parti_orgs").remove();
	    	    return;
	    }
	    
	    
	    $.each(details.parti_orgs,function(index,orgs){
	    	 
	    	          orgs_name='';
		              orgs_role='';
		              orgs_date='';
		              orgs_desc='';
		              orgs_cate='';
		              orgs_position='';
	    	
	    	          
	    	          //组织名字
	    	          if(this.name!=null){
	    	        	     orgs_name='<div class="title" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'+this.name+'</div>';
	    	          } 
	    	          //参与身份
	    	          if(this.role!=null){
	    	        	      orgs_role='<div class="attrText">参与身份：'+this.role+'</div>';
	    	          }
	    	          //开始参与时间
	    	          if(this.org_date!=null){
	    	        	      orgs_date='<div class="attrText">开始日期：'+this.org_date+'</div>';
	    	          }
	    	          //机构类型
	    	          if(this.cate!=null){
	    	        	     orgs_cate='<div class="attrText">机构类型：'+this.cate+'</div>';
	    	          }
	    	          //担任职务
	    	          if(this.position!=null){
	    	        	     orgs_position='<div class="attrText">职务：'+this.position+'</div>';
	    	          }
	    	          //描述
	    	          if(this.desc_html!=null){
	    	        	     orgs_desc=linkedIn_common_textShow("orgs",index,this.desc,this.desc_html);
	    	          }
	    	          
	    	          var hr='';
	    	          if(index<(details.parti_orgs.length-1)){
	    	        	     hr="<hr>";
	    	          }
	    	          
	    	          orgs_list +='<div id="orgs_'+index+'">'
	    	                             +orgs_name
	    	                             +orgs_role
	    	                             +orgs_position
	    	                             +orgs_cate
	    	                             +orgs_date
	    	                             +orgs_desc
	    	                       +'</div>'
	    	                       +hr;
	
	    })
	    
	    return linkedIn_common_model('orgsTitle','orgsContent','glyphicon glyphicon-home','参与组织',orgs_list);

}
//(10)语言能力
function linkedIn_common_languages(details){
	  
	     var languages='';
	     var languages_list='';
	     var languages_profic='';
	     
	     var row_head='';
	     var row_tail='';
	     
	    if(details.languages==null){
	    	   $("#languages").remove();
	    	   return;
	    }
	    
	    $.each(details.languages,function(index,languages){
	    	      row_head='';
   	              row_tail='';    
	    	
	    	     if(languages.name==null){
	    	    	 return true;   //continue
	    	     }
	    	
	    	     if(languages.profic!=null){
	    	    	 languages_profic='<p class="attrText">熟练程度：'+languages.profic+'</p>';
	    	     }
	    	    	   
	    	
	    	     if(index%2==0){
	    	    	      row_head='<div id="languages_'+index+'" class="row" style="margin-left:1%">';
	    	     }else{
	    	    	      row_tail='</div>';
	    	     }
	    	     
	    	     languages_list +=row_head
	    	                            +'<div class="col-md-5">'
	    	                                   +'<div class="title">'+languages.name+'</div>'
	    	                                   +languages_profic
	    	                            +'</div>'
	    	                     +row_tail;
	    	     
	    })
	    
	   
	    return linkedIn_common_model('languagesTitle','languagesContent','glyphicon glyphicon-globe','语言能力',languages_list);
}
//(11)擅长技能
function linkedIn_common_skills(details){
	    
	    var skills='';
	    var skills_list='';
	    
	    
	    var row_head='';
	    var row_tail='';
	    var skills_endorseNum='';
	    var indexTemp=0;
	    
	    if(details.skills==null){
	    	   $("#skills").remove();
	    	   return;
	    }
	    
	    $.each(details.skills,function(index,skills){

	    	        row_head='';
	    	        row_tail='';
	    	
	    	        if(skills.endorse_num!=null){
	    	        	   skills_endorseNum='<div class="attrText">认可数(人)：<strong style="color:#428bca">'+skills.endorse_num+'</strong></div>';
	    	        }
	    	        
	    	        if(index%3==0){
	    	        	 indexTemp=index;
	    	    	     row_head='<div id="skills_'+index+'" class="row" style="margin-left:1%;margin-top:1%">';
	    	        }
	    	        if(index==(indexTemp+2)){
	        		     row_tail='</div>';
	        	    }
	    	        
	    	        //最后一行不足三个，加</div>
	    	        if(index==(details.skills.length-1)){
	    	        	    if(row_tail!='</div>'){
	    	        	    	row_tail='</div>';
	    	        	    }
	    	        }
	    	        
	    	        
	    	        skills_list +=row_head
	    	                             +'<div class="col-md-4">'
                                                +'<div class="title">'+skills.name+'</div>'
                                                +skills_endorseNum
                                         +'</div>'
                                  +row_tail;
	    	
	    })
	    
	    var skillsOther_list='';
	    if(details.skills_others!=null){
	            
	    	     var skillsOther_endorseNum='';
                 indexTemp=0;   
	    	
	    	     skillsOther_list='<hr>'
	    	    	              +'<div style="font-size:16px;margin-bottom:1%;font-family:黑体">'
	    	    	                     +details.name+'还擅长'
	    	    	                     +'<span class="caret" onclick="linkedIn_common_showAndHide(\'skills_other\')" style="cursor:pointer"></span>'
	    	    	              +'</div>'
	    	    	              +'<div id="skills_other" style="display:none">';
	             
	    
	             $.each(details.skills_others,function(index,skillsOther){
	    	          
	    	                  row_head='';
 	                          row_tail='';
 	
 	                         if(skillsOther.endorse_num!=null){
 	        	                    skillsOther_endorseNum='<div class="attrText">认可数(人)：'
 	        	            	                         +'<strong style="color:#428bca">' +skillsOther.endorse_num+'</strong>'
 	        	            	                   +'</div>';
 	                         }
 	        
 	                        if(index%3==0){
 	        	                  indexTemp=index;
 	    	                      row_head='<div id="skills_other_'+index+'" class="row" style="margin-left:1%;margin-top:1%">';
 	                        }
 	               
 	                        if(index==(indexTemp+2)){
     		                      row_tail='</div>';
     	                    }
 	                       
 	                       if(index==(details.skills_others.length-1)){
 		    	        	    if(row_tail!='</div>'){
 		    	        	    	
 		    	        	    	row_tail='</div>';
 		    	        	    }
 		    	            }
 	                        
 	                        
 	                        skillsOther_list +=row_head
 	                                                +'<div class="col-md-4">'
                                                          +'<div class="title" >'+skillsOther.name+'</div>'
                                                          +skillsOther_endorseNum
                                                    +'</div>'
                                             +row_tail;          
	             })
	             
	             skillsOther_list +='</div>';
	    }
	    
	    return linkedIn_common_model('skillsTitle','skillsContent','glyphicon glyphicon-wrench','擅长技能',skills_list+skillsOther_list);
}

//(12)出版作品
function linkedIn_common_pubs(rootPath,details){
	
	      var pubs='';
	      var pubs_list='';
	
	
	      var pubs_name='';
	      var pubs_press='';
	      var pubs_date='';
	      var pubs_desc='';
	      var pubs_authors='';
	      
	      
	      var authors_name='';
	      var authors_headline='';
	      var authors_img='';
	
	      if(details.pubs==null){
	    	  $("#pubs").remove();
	    	  return;
	      }
	      
	      
	      $.each(details.pubs,function(index,pubs){
	    	 
	    	             pubs_name='';
		                 pubs_press='';
		                 pubs_date='';
		                 pubs_desc='';
		                 pubs_authors='';
	    	  
	    	          if(pubs.name==null){
	    	        	  return true;
	    	          }
	    	  
	    	          if(pubs.link!=null){
	    	        	   pubs_name='<div class="title">'
	    	        		              +'<a href="'+pubs.link+'" target="_blank" style="color:black" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'+pubs.name+"</a>"
	    	        		          +'</div>';
	    	          }else{
	    	        	   pubs_name='<div class="title" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'
	    	        		               +pubs.name
	    	        		         +'</div>';
	    	          }
	    	          
	    	          if(pubs.press!=null){
	    	        	    pubs_press='<div class="attrText">'
	    	        	    	             +pubs.press
	    	        	    	       +'</div>';
	    	          }
	    	          if(pubs.pub_date!=null){
	    	        	    pubs_date='<div class="attrText">'
	    	        	    	             +pubs.pub_date
	    	        	    	      +'</div>';
	    	          }
	    	          if(pubs.desc_html!=null){
	    	        	    pubs_desc=linkedIn_common_textShow("pubs",index,pubs.desc,pubs.desc_html);
	    	          }
	    	          
	    	          if(pubs.authors!=null){
	    	        	        
	    	        	       pubs_authors='<div id="pubs_'+index+'_authors" style="margin-top:1%">'
	    	        	                          +'<div id="pubs_'+index+'_authorsTitle" style="font-size:13px;font-family:黑体">'
	    	        	    	                         +'共'+pubs.authors.length+'位作者'
	    	        	                                 +'<span class="caret" onclick="linkedIn_common_showAndHide(\'pubs_'+index+'_authorsList\')" style="cursor:pointer"></span>'
	    	        	                          +'</div>'
	    	        	                          +'<div id="pubs_'+index+'_authorsList" style="display:none">';
	    	        	       
	    	        	       for(var i=0;i<pubs.authors.length;i++){
	    	        	    	        
	    	        	    	       authors_name='';
	 	    	        	           authors_headline='';
	 	    	        	           authors_img='';
	 	    	        	           
	    	        	    	       if(pubs.authors[i].name==null){
	    	        	    	        	 continue;
	    	        	    	       }
	    	        	    	           
	    	        	    	       if(pubs.authors[i].profile!=null){
	    	        	    	        	 authors_name='<div class="sonTitle" >'
	    	        	    	        	    	            +'<a href="'+pubs.authors[i].profile+'" target="_blank">'+pubs.authors[i].name+'</a>'
	    	        	    	        	    	       +'</div>';
	    	        	    	       }else{
	    	        	    	        	 authors_name='<div class="sonTitle">'
   	    	        	    	                                +pubs.authors[i].name
   	    	        	    	                          +'</div>';
	    	        	    	       }
                                       
	    	        	    	       if(pubs.authors[i].headline!=null){
	    	        	    	    	      authors_headline='<div class="arrtText">'+pubs.authors[i].headline+'</div>';
	    	        	    	       }
	    	        	    	          
                                       if(pubs.authors[i].profile_img!=null){
                                    	      authors_img='<div><img src="'+pubs.authors[i].profile_img+'" onerror="linkedIn_common_personImg(\''+rootPath+'\')" style="width:40px;height:40px"></div>';
                                       }else{
                                    	      authors_img='<div><img src="'+rootPath+'/static/img/person.png"></div>';
                                       }	    	        	    	        	   
	    	        	    	       
	    	        	    	       pubs_authors +='<div id="pubs_'+index+'_authors_'+i+'" class="row" style="margin-top:1%">'
	    	        	    	    	                  +'<div class="col-md-1">'
	    	        	    	    	                        +authors_img
	    	        	    	    	                  +'</div>'
	    	        	    	    	                  +'<div class="col-md-8">'
	    	        	    	    	                        +authors_name
	    	        	    	    	                        +authors_headline
	    	        	    	    	                  +'</div>'
	    	        	    	    	             +'</div>';
	    	        	       }
	    	        	       pubs_authors +='</div></div>';
	    	          }
	    	          
	    	          var hr='';
	    	          if(index<(details.pubs.length-1)){
	    	        	     hr="<hr>";
	    	          }
	    	  
	    	          pubs_list +='<div id="pubs_'+index+'">'
	    	                          +pubs_name
	    	                          +pubs_press
	    	                          +pubs_date
	    	                          +pubs_desc
	    	                          +pubs_authors
	    	                     +'</div>'
	    	                     +hr;
	      })
	
	
	      return linkedIn_common_model('pubsTitle','pubsContent','glyphicon glyphicon-book','出版作品',pubs_list);
}
//(13)教育背景
function linkedIn_common_edus(details){
	
	
	      var edus='';
	      var edus_list='';
	
	      var edus_schoolName='';
	      var edus_schoolLogo='';
	      var edus_schoolCourses='';
	      var edus_schoolHonors='';
	      var edus_schoolProjects='';
	      var edus_major='';
	      var edus_date='';
	      var edus_degree='';
	      var edus_desc='';
	      var edus_activities='';
	      
	      
	      
	      if(details.edus==null){
	    	  $("#edus").remove();
	    	  return;
	      }
	
	      
	      
	      $.each(details.edus,function(index,edus){
	    	  
	    	       edus_schoolName='';
		           edus_schoolLogo='';
		           edus_schoolCourses='';
		           edus_schoolHonors='';
		           edus_schoolProjects='';
		           edus_major='';
		           edus_date='';
		           edus_degree='';
		           edus_desc='';
		           edus_activities='';
	    	       
	    	  
	    	       if(edus.school==null){
	    	    	     return true;
	    	       }
	    	       if(edus.school.name==null){
	    	    	      return true;
	    	       }
	    	       if(edus.school.link==null){
	    	    	      edus_schoolName='<div class="title" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'
	    	    	    	                    +edus.school.name
	    	    	    	              +'</div>';
	    	       }else if(edus.school.link!=null){
	    	    	      edus_schoolName='<div class="title">'
	    	    	    	                    +'<a href="'+edus.school.link+'" style="color:#000" target="_blank" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'
	    	    	    	                         +edus.school.name
	    	    	    	                    +'</a>'
	    	    	    	              +'</div>';
	    	       }
	    	       
	    	       
	    	       if(edus.school.logo!=null){
	    	    	       edus_schoolLogo='<div><img src="'+edus.school.logo+'" style="width:60px;height:60px"></div>';        
	    	       }
	    	       
	    	       if(edus.major!=null){
	    	    	       edus_major='<div class="attrText" >'+edus.major+'</div>';
	    	       }
	    	       
	    	       if(edus.edu_date!=null){
	    	    	       edus_date='<div  class="attrText">'+edus.edu_date+'</div>';        
	    	       }
	    	       if(edus.degree!=null){
	    	    	        edus_degree='<div class="attrText">'+edus.degree+'</div>';
	    	       }
	    	       if(edus.activities!=null){
	    	    	        edus_activities='<div class="row" class="attrText">'
	    	    	        	                  +'<div class="col-md-2">'
	    	    	        	                         +'社团活动：'
	    	    	        	                  +'</div>'
	    	    	        	                  +'<div class="col-md-10">'
	    	    	        	                         +edus.activities
	    	    	        	                  +'</div>'
	    	    	        	            +'</div>';
	    	       }
	    	       if(edus.desc!=null && edus.desc_html!=null){
	    	    	        edus_desc=linkedIn_common_textShow("edus",index,edus.desc,edus.desc_html);
	    	       }
	    	       
	    	       //荣誉奖励
	    	       if(edus.school.honorawards!=null){
	    	    	       edus_schoolHonors=linkedIn_common_edusHonor(edus.school.honorawards,index);
	    	       }
	    	       
	    	       //所做项目
	    	       if(edus.school.projects!=null){
	    	    	       edus_schoolProjects=linkedIn_common_edusProject(edus.school.projects,index);
	    	       }
	    	       
	    	       
	    	       //所学课程
	    	       if(edus.school.courses!=null){
	    	    	         edus_schoolCourses=linkedIn_common_edusCourse(edus.school.courses,index);
	    	       }
	    	       
	    	       var hr='';
	    	       if(index<(details.edus.length-1)){
	    	        	     hr="<hr>";
	    	       }
	    	       
	    	       
	    	       edus_list +='<div id="edus_'+index+'">'
	    	                         +'<div id="edus_'+index+'_details" style="margin-bottom:2%">'
	    	                                +'<div class="row">'
	    	                                      +'<div class="col-md-10">'
	    	                                            +edus_schoolName
	    	                                            +edus_major
	    	                                            +edus_degree
	    	                                            +edus_date
	    	                                      +'</div>'
	    	                                      +'<div class="col-md-2">'
	    	                                            +edus_schoolLogo
	    	                                      +'</div>'
	    	                                +'</div>'
	    	                                +edus_activities
	    	                                +edus_desc
	    	                         +'</div>'
	    	                         +edus_schoolHonors
	    	                         +edus_schoolProjects
	    	                         +edus_schoolCourses
	    	                   +'</div>'
	    	                   +hr;
	      })
	      
	      return linkedIn_common_model('edusTitle','edusContent','glyphicon glyphicon-bookmark','教育背景',edus_list);
}
//(13-1)教育背景中荣誉奖励
function linkedIn_common_edusHonor(edusHonor,index){
    
    var honor_summary='';
    var honors_list='';
    
    $.each(edusHonor,function(order,edusHonor){
    	
    	     honor_summary='';
    	
    	      if(edusHonor.summary==null){
    	    	    return true;
    	      }
    	      
    	      honor_summary ='<div id="edus_'+index+'_honors_'+order+'" class="sonTitle" style="margin-top:1%;">'
    	    	                 +edusHonor.summary
    	    	                 +'&nbsp;&nbsp;<a href="#honorTitle" style="font-size:13px" onmousemove="linkedIn_common_simulationClick(\'honorTitle\')">查看</a>'
    	    	           +'</div>';
    	                   
    	      honors_list +=honor_summary;
    })

    var honors='<div id="edus_'+index+'_honors" style="margin-top:1%">'
                     +'<div id="edus_'+index+'_honors_title" style="font-size:13px;font-family:黑体">'
                           +edusHonor.length+'项荣誉和奖励'
                           +'<span class="caret" onclick="linkedIn_common_showAndHide(\'edus_'+index+'_honors_list\')" style="cursor:pointer"></span>'
                     +'</div>'
                     +'<div id="edus_'+index+'_honors_list" style="display:none">'
                           +honors_list
                     +'</div>'
               +'</div>';

    return honors;
}


//(13-2)教育背景中所做项目
function linkedIn_common_edusProject(edusProjects,index){
	
    
    var project_name='';
    var projects_list='';
    
    
    $.each(edusProjects,function(order,edusProjects){
    	
    	     project_name='';
    	
    	      if(edusProjects.name==null){
    	    	    return true;
    	      }
    	      
    	      project_name ='<div id="edus_'+index+'_projects_'+order+'" class="sonTitle" style="margin-top:1%;">'
    	    	                 +edusProjects.name
    	    	                 +'&nbsp;&nbsp;<a href="#projectTitle" style="font-size:13px" onmousemove="linkedIn_common_simulationClick(\'projectTitle\')">查看</a>'
    	    	           +'</div>';
    	                   
    	      projects_list +=project_name;
    })

    
    var projects='<div id="edus_'+index+'_projects" style="margin-top:1%">'
                      +'<div id="edus_'+index+'_projects_title" style="font-size:13px;font-family:黑体">'
                            +edusProjects.length+'个项目'
                            +'<span class="caret" onclick="linkedIn_common_showAndHide(\'edus_'+index+'_projects_list\')" style="cursor:pointer"></span>'
                      +'</div>'
                      +'<div id="edus_'+index+'_projects_list" style="display:none">'
                            +projects_list
                      +'</div>'
                +'</div>';

    return projects;
}
//(13-3)教育背景中的所学课程
function linkedIn_common_edusCourse(edusCourses,index){
	
	   var courses='';
	   var courses_list='';
	   
	   for(var i=0;i<edusCourses.length;i++){
		      courses_list +='<li>'+edusCourses[i]+'</li>'; 
	   }
	   
	   
	   courses='<div id="edus_'+index+'_courses" style="margin-top:1%">'
	                 +'<div id="edus_'+index+'_courses_title" style="margin-top:1%;margin-bottom:1%;font-size:13px;font-family:黑体">'
                            +edusCourses.length+'门课程'
                            +'<span class="caret" onclick="linkedIn_common_showAndHide(\'edus_'+index+'_courses_list\')" style="cursor:pointer"></span>'
                     +'</div>'
                     +'<ul  id="edus_'+index+'_courses_list" class="attrText" style="display:none">'
                            +courses_list
                     +'</ul>'
	           +'</div>';
        
	   return  courses;
}

//(14)所学课程
function linkedIn_common_courses(details){
	
	
	      var school_name='';
	      var courses_name='';

	      var courses_list='';
	      
	      if(details.schools==null){
	    	  $("#courses").remove();
	    	  return;
	      }
	     
	     $.each(details.schools,function(index,school){
	    	 
	    	        school_name='';
		            courses_name='';
	    	 
	    	      if(school.name==null || school.courses==null){
		    	        return true;
		          }
	    	 
	    	 
	    	     school_name='<div class="title" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'+school.name+'</div>';
	    	     
	    	     courses_name='<ul class="attrText">'
	    	     
	    	     for(var i=0;i<details.schools[index].courses.length;i++){
	    	    	      courses_name +='<li>'+details.schools[index].courses[i]+'</li>';
	    	     }
	    	     
	    	     courses_name +='</ul>'
	    	     
	    	 
	    	     var hr='';
	    	     if(index<(details.schools.length-1)){
	    	        	hr="<hr>";
	    	     }
	    	     
	    	     courses_list +='<div id="courses_'+index+'">'
	    	                          +school_name
	    	                          +courses_name
	    	                    +'</div>'
	    	                    +hr;
	    	 
	     })
	
         if(courses_list==''){
        	 return;
         } 
	     
	     return linkedIn_common_model('coursesTitle','coursesContent','glyphicon glyphicon-pencil','所学课程',courses_list);
}
//(14)资格证书
function linkedIn_common_certis(details){
	
	      var certis_list='';
	      var certi_name='';
	      var org_name='';
	      var certi_date='';
	      
	      
	     if(details.certis==null){
	    	  $("#certis").remove();
	    	  return;
	     }
	
	     
	     $.each(details.certis,function(index,certis){
	    	 
	    	         certi_name='';
		             org_name='';
		             certi_date='';
	    	 
	    	      if(certis.certi_name==null){
	    	    	   return true;
	    	      }else{
	    	    	   certi_name='<div class="title" onmousemove="linkedIn_common_textColorMove(this)" onmouseout="linkedIn_common_textColorOut(this)">'
	    	    		                +certis.certi_name
	    	    		           +'</div>';
	    	      }
	    	      
	    	      if(certis.org_name!=null){
	    	    	   org_name='<div class="attrText">'
	    	    		              +certis.org_name
	    	    		         +'</div>';
	    	      }
	    	 
	    	       if(certis.certi_date!=null){
	    	    	     certi_date='<div class="attrText">'
	    	    	    	              +'开始日期：'+certis.certi_date
	    	    	    	         +'</div>';
	    	       }
	    	       
	    	       var hr='';
		    	   if(index<(details.certis.length-1)){
		    	        hr="<hr>";
		    	   }
		    	   
		    	   certis_list +='<div id="certi_'+index+'">'
		    	                       +certi_name
		    	                       +org_name
		    	                       +certi_date
		    	                 +'</div>'
		    	                 +hr;
	    	 
	     })
	
	
	     return linkedIn_common_model('certisTitle','certisContent','glyphicon glyphicon-picture','资格证书',certis_list);
	
}
//(15)推荐信
function linkedIn_common_endorsements(rootPath,details){
	
	     var name='';
	     var headline='';
	     var profile_img='';
	     var add_desc='';
	     var content='';
	     var date='';
	     var endorsement='';
	     var endorsements_list='';
	     var endorsements_receivedList='';
	     var endorsements_givenList='';
	     
	     var received_num=0;
	     var given_num=0;

	     
	     if(details.jobwendors==null){
	    	 $("#endorsements").remove();
	    	 return;
	     }
	     
	    $.each(details.jobwendors,function(index,job){
	    	
	    	        name='';
		            headline='';
		            profile_img='';
		            add_desc='';
		            content='';
		            date='';
	    	
	    	       if(job.endorsements==null){
	    	    	      return true;
	    	       }
	    	       
	    	       for(var i=0;i<job.endorsements.length;i++){
	    	    	   
	    	    	       if(job.endorsements[i].endorser==null){ 
	    	    	    	   continue;
	    	    	       }else{
	    	    	    	   if(job.endorsements[i].endorser.name==null){
	    	    	    		    continue;
	    	    	    	   }
	    	    	       }
	    	    	       
	    	    	       if(job.endorsements[i].endorser.profile!=null){
	    	    	    	        name='<div class="title">'
	    	    	    	        	      +'<a href="'+job.endorsements[i].endorser.profile+'" target="_blank">'
	    	    	    	        	            +job.endorsements[i].endorser.name
	    	    	    	        	      +'</a>'
	    	    	    	        	 +'</div>';
	    	    	       }else{
	    	    	    	        name='<div class="title">'+job.endorsements[i].endorser.name+'</div>';
	    	    	       }
	    	    	       
	    	    	       if(job.endorsements[i].endorser.headline!=null){
	    	    	    	         headline='<div class="attrText">'+job.endorsements[i].endorser.headline+'</div>';
	    	    	       }
	    	    	       
	    	    	       if(job.endorsements[i].endorser.profile_img!=null){
	    	    	    	         profile_img='<div><img src="'+job.endorsements[i].endorser.profile_img+'" style="width:60px;height:60px" onerror="linkedIn_common_personImg(\''+rootPath+'\')"></div>';
	    	    	       }else{
	    	    	    	         profile_img='<div><img src="'+rootPath+'/static/img/person.png" style="width:60px;height:60px"></div>';
	    	    	       }
	    	    	       if(job.endorsements[i].endorser.add_desc!=null){
	    	    	    	         add_desc='<span class="attrText">'+job.endorsements[i].endorser.add_desc+'</span>';
	    	    	       }
	    	    	       if(job.endorsements[i].content!=null){
	    	    	    	         content=linkedIn_common_textShow("endorsement",index,job.endorsements[i].content,job.endorsements[i].content);
	    	    	       }
	    	    	       if(job.endorsements[i].endors_date!=null){
	    	    	    	         date='<div class="attrText">推荐时间：'+job.endorsements[i].endors_date+'</div>'
	    	    	       }
	    	    	       
	    	    	       endorsement='<div class="row">'
	                                         +'<div class="col-md-1">'
	                                                +profile_img
	                                         +'</div>'
	                                         +'<div class="col-md-9" style="margin-left:1%">'
	                                                +name
	                                                +headline
	                                                +date
	                                                +content
	                                         +'</div>'
	                                    +'</div>';
	    	    	       
	    	    	       if(job.endorsements[i].status=='received'){
	    	    	    	         if(received_num==0){
	    	    	    	        	 endorsements_receivedList='<div id="endorsementReceived_'+received_num+'">'
	    	    	    	        	                                 +endorsement
	    	    	    	        	                           +'</div>';
	    	    	    	         }else{
	    	    	    	        	 endorsements_receivedList +='<div id="endorsementReceived_'+received_num+'" style="margin-top:2%">'
	    	    	    	        		                             +endorsement
	    	    	    	        		                         +'</div>';
	    	    	    	         }
	    	    	    	         received_num++;
	    	    	       }else if(job.endorsements[i].status=='given'){
	    	    	    	         if(given_num==0){
  	    	    	        	          endorsements_givenList='<div id="endorsementGiven_'+given_num+'">'
  	    	    	        	                                       +endorsement
  	    	    	        	                                 +'</div>';
  	    	    	                }else{
  	    	    	        	          endorsements_givenList +='<div id="endorsementGiven_'+given_num+'" style="margin-top:2%">'
  	    	    	        	        	                           +endorsement
  	    	    	        	        	                       +'</div>';
  	    	    	                }
	    	    	    	         given_num++;
	    	    	       }
	    	       }
	    })
	    
	    if(endorsements_receivedList!=''){
	    	       endorsements_receivedList='<div id="endorsements_received">'
	    	    	                               +'<div style="float:right;font-weight:bold;font-family:黑体">'
	    	    	                                      +'收到的推荐信('+received_num+')'
	    	    	                               +'</div>'
	    	    	                               +'<br>'+endorsements_receivedList
	    	    	                         +'</div>';
	    }
	    if(endorsements_givenList!=''){
	    	       endorsements_givenList='<div id="endorsements_given">'
	    	    	                            +'<div style="float:right;font-weight:bold;font-family:黑体">'
	    	    	                                   +'发出的推荐信('+given_num+')'
	    	    	                            +'</div>'
	    	    	                            +'<br>'+endorsements_givenList
	    	    	                      +'</div>';
	    	    	                      
	    }
	    
	    if(endorsements_receivedList!='' && endorsements_givenList!=''){
	    	   endorsements_list=endorsements_receivedList+'<hr>'+endorsements_givenList;
	    }else{
	    	   endorsements_list=endorsements_receivedList+endorsements_givenList;  
	    }
	    
	    
	    
	    return linkedIn_common_model('endorsementsTitle','endorsementsContent','glyphicon glyphicon-envelope','推荐信',endorsements_list);
}
//(16)志愿者经历和公益话题
function linkedIn_common_volunteer(details){
	   
	
	   var parti_orgs='';
	   var intens='';
	   var interests='';
	   var volunteer_list='';
	
	   
	   if(details.volunteer==null){
		   $("#volunteer").remove();
		   return;
	   }
	
	   
	   if(details.volunteer.parti_orgs!=null){
		   
		         var orgs_role='';
		         var orgs_name='';
		         var orgs_orgDate='';
		         var orgs_cate='';
		         var orgDate_cate='';
		         var orgs_position='';
		         var orgs_desc='';
                 var orgs_list='';
		         
		         $.each(details.volunteer.parti_orgs,function(index,orgs){
		        	            
		        	          orgs_role='';
			                  orgs_name='';
			                  orgs_orgDate='';
			                  orgs_cate='';
			                  orgDate_cate='';
			                  orgs_position='';
			                  orgs_desc='';
		        	       
		        	          if(orgs.role!=null){
			        	             orgs_role='<div class="title">'+orgs.role+'</div>';
			                  }
			                  if(orgs.name!=null){
			        	             orgs_name='<div class="attrText">'+orgs.name+'</div>';
			                  }
			                  if(orgs.org_date!=null){
			        	             orgs_orgDate='<span>'+orgs.org_date+'</span>';
			                  }
			                  if(orgs.cate!=null){
			        	             orgs_cate='<span>'+orgs.cate+'</span>';
			                  }
			                  if(orgs.org_date!=null && orgs.cate!=null){
			        	             orgDate_cate='<div class="attrText">'+orgs_orgDate+'&nbsp;|&nbsp;'+orgs_cate+'</div>';
			                  }else{
			        	             orgDate_cate='<div class="attrText">'+orgs_orgDate+orgs_cate+'</div>';
			                  }
			         
			                  if(orgs.position!=null){
			        	             orgs_position='<div class="attrText">'+orgs.position+'</div>';
			                  }
			                  if(orgs.desc!=null){
			        	             orgs_desc='<div style="font-size:13px;margin-top:1%">'+orgs.desc+'</div>';
			                  }
			   
		        	 
		        	          orgs_list +='<div id="volunteer_partiOrgs_'+index+'">'
		        	                          +orgs_role
		       		    	                  +orgs_name
		    		    	                  +orgDate_cate
		    		    	                  +orgs_position
		    		    	                  +orgs_desc
		        	                    +'</div>';
		        	         if(index<(details.volunteer.parti_orgs.length-1)){
		        	        	      orgs_list +='<br>';
		        	         }
		         });
		         
		         
		      parti_orgs='<div id="volunteer_parti_orgs">'
		    	               +orgs_list           
		    	         +'</div>';
		      
	   }
	   
	   if(details.volunteer.intens!=null){
		        
		        var intens_list='';
		        for(var i=0;i<details.volunteer.intens.length;i++){
		        	    intens_list +='<li>'+details.volunteer.intens[i]+'</li>';
		        }
		        intens='<br><div id="volunteer_intens">'
		        	         +'<div class="title">'+details.name+'的志愿服务意向</div>'
		        	         +'<ul style="margin-top:1%;">'
		        	              +intens_list
		        	         +'</ul>'
		        	   +'</div>';
	   }
	
	   if(details.volunteer.interests!=null){
		        var interests_list='';
		        for(var i=0;i<details.volunteer.interests.length;i++){
		        	    interests_list +='<li>'+details.volunteer.interests[i]+'</li>';
		        }
		        
		        interests='<br><div id="volunteer_interests">'
		        	           +'<div class="title">'+details.name+'关心的公益话题</div>'
		                       +'<ul style="margin-top:1%;">'
		                            +interests_list
		                       +'</ul>'
		        	      +'</div>';
	   }
	
	   volunteer_list=parti_orgs
	                  +intens
	                  +interests;
	                  
	   if(volunteer_list==''){
		     return;
	   }
	   
	   return  linkedIn_common_model("volunteerTitle","volunteerContent","glyphicon glyphicon-heart","志愿者经历和公益话题",volunteer_list);
}
//(17)所做项目
function linkedIn_common_projects(rootPath,details){
	
	
	     var projects_list='';
	     
	     var project_name='';
	     var project_proDate='';
	     var project_role='';
	     var project_desc='';
	     
	     
	     var project_membersList='';
	     var member_name='';
	     var member_headline='';
	     var member_profileImg='';
	     
	     if(details.projects==null){
	    	 $("#projects").remove();
	    	 return;
	     }
	
	     
	     $.each(details.projects,function(index,projects){
	    	 
	    	       project_name='';
		           project_proDate='';
		           project_role='';
		           project_desc='';
		           project_membersList='';
	    	 
	    	       if(projects.name==null){
	    	    	   return true;
	    	       }else{
	    	    	   if(projects.link!=null){
	    	    		        project_name='<div class="title">'
	    	    		        	               +'<a href="'+projects.link+'" target="_blank">'
	    	    		        	                     +projects.name
	    	    		        	               +'</a>'
	    	    		        	         +'</div>';
	    	    	   }else{
	    	    		        project_name='<div class="title">'+projects.name+'</div>';
	    	    	   }
	    	       }
	    	 
	    	       if(projects.role!=null){
	    	    	      project_role='<div class="attrText">'+projects.role+'</div>';
	    	       }
	    	       
	    	       if(projects.pro_date!=null){
	    	    	      project_proDate='<div class="attrText">开始日期：'+projects.pro_date+'</div>';
	    	       }
	    	       
	    	       if(projects.desc!=null && projects.desc_html!=null){
	    	    	      project_desc=linkedIn_common_textShow("project",index,projects.desc,projects.desc_html);
	    	       }
	    	       
	    	       
	    	       //项目成员:多个
	    	       if(projects.members!=null){
	    	    	       
	    	    	   
	    	    	      project_membersList='<div id="project_'+index+'_members" style="margin-top:1%">'
	    	    	                                +'<div id="project_'+index+'_members_title" style="margin-top:1%;margin-bottom:1%;font-size:13px;font-family:黑体">'
                                                           +'共'+projects.members.length+'位团队成员'
                                                           +'<span class="caret" onclick="linkedIn_common_showAndHide(\'project_'+index+'_members_list\')" style="cursor:pointer"></span>'
                                                    +'</div>'
                                                    +'<div id="project_'+index+'_members_list" style="display:none">';
	    	    	   
	    	    	       for(var i=0;i<projects.members.length;i++){
	    	    	    	   
	    	    	    	          member_name='';
	    	    	  	              member_headline='';
	    	    	  	              member_profileImg='';
	    	    	    	           
	    	    	    	           if(projects.members[i].name==null){
	    	    	    	        	     continue;
	    	    	    	           }else{
	    	    	    	        	     if(projects.members[i].profile!=null){
	    	    	    	        	    	    member_name='<div class="sonTitle">'
	    	    	    	        	    	    	              +'<a href="'+projects.members[i].profile+'" target="_blank">'
	    	    	    	        	    	    	                   +projects.members[i].name
	    	    	    	        	    	    	              +'</a>'
	    	    	    	        	    	    	        +'</div>';
	    	    	    	        	     }else{
	    	    	    	        	    	    member_name='<div class="sonTitle">'+projects.members[i].name+'</div>';
	    	    	    	        	     }
	    	    	    	           }
	    	    	    	           
	    	    	    	           if(projects.members[i].headline!=null){
	    	    	    	        	      member_headline='<div class="attrText">'+projects.members[i].headline+'</div>';
	    	    	    	           }
	    	    	    	           
	    	    	    	           if(projects.members[i].profile_img!=null){
	    	    	    	        	      member_profileImg='<img src="'+projects.members[i].profile_img+'" style="width:40px;height:40px" onerror="linkedIn_common_personImg(\''+rootPath+'\')">';
	    	    	    	           }else{
	    	    	    	        	      member_profileImg='<img src="'+rootPath+'/static/img/person.png" style="width:40px;height:40px">';
	    	    	    	           }
	    	    	    	           
	    	    	    	           project_membersList +='<div id="project_'+index+'_members_'+i+'" class="row" style="margin-top:1%">'
	    	    	    	                                     +'<div class="col-md-1">'
	    	    	    	                                           +member_profileImg
	    	    	    	                                     +'</div>'
	    	    	    	                                     +'<div class="col-md-9">'
	    	    	    	                                           +member_name
	    	    	    	                                           +member_headline
	    	    	    	                                     +'</div>'
	    	    	    	                               +'</div>';
	    	    	       }
	    	    	       
	    	    	       project_membersList +='</div></div>';
	    	       }
	    	       
	    	        var hr='';
	    	        if(index<(details.projects.length-1)){
	    	        	 hr='<hr>';
	    	        }
	    	        
                   projects_list +='<div id="project_'+index+'">'
                                         +project_name
                                         +project_role
                                         +project_proDate
                                         +project_desc
                                         +project_membersList
                                   +'</div>'
                                   +hr;
	     })
	     
	     return  linkedIn_common_model("projectTitle","projectContent","glyphicon glyphicon-tree-deciduous","所做项目",projects_list);
}
//(18)专利发明
function linkedIn_common_patents(details){
	
	     
	     var patents_list='';     
	
	      var patent_title='';
	      var patent_mark='';
	      var patent_date='';
	      var patent_desc='';
	      var patent_inventors='';
	      
	      
	      var inventor_name='';
          var inventor_headline='';
          var inventor_profileImg='';
	      
	
	      if(details.patents==null){
	    	    $("#patents").remove();
	    	    return;
	      }
 
	      $.each(details.patents,function(index,patents){
	    	    
	    	           patent_title='';
		               patent_mark='';
		               patent_date='';
	    	           patent_desc='';
	    	           patent_inventors='';
	    	  
	    	           if(patents.title==null){
	    	        	      return true;
	    	           }else{
	    	        	   
	    	        	      if(patents.patentURL!=null){
	    	        	    	     patent_title='<div class="title">'
	    	        	    	    	               +'<a href="'+patents.patentURL+'" target="_blank">'
	    	        	    	    	                    +patents.title
	    	        	    	    	               +'</a>'
	    	        	    	    	          +'</div>';
	    	        	      }else{
	    	        	    	     patent_title='<div class="title">'+patents.title+'</div>';
	    	        	      }
	    	           }
	    	           
	    	           
	    	           if(patents.patentMark!=null){
	    	        	       patent_mark='<div class="attrText">专利标识：'+patents.patentMark+'</div>';
	    	           }
	    	           
	    	           if(patents.awardDate!=null){
	    	        	       patent_date='<div class="attrText">颁发时间：'+patents.awardDate+'</div>';
	    	           }
	    	           if(patents.appliDate!=null){
	    	        	      patent_date='<div class="attrText">申请时间：'+patents.appliDate+'</div>';
	    	           }
	    	           
	    	           if(patents.desc!=null && patents.desc_html!=null){
	    	        	       patent_desc=linkedIn_common_textShow("patent",index,patents.desc,patents.desc_html);
	    	           }
	    	           if(patents.inventors!=null){
	    	        	       
	    	        	          patent_inventors='<div style="margin-top:1%;margin-bottom:1%;">'
        	    	                                     +'<div style="font-size:13px;font-family:黑体">'
        	    	                                           +'共'+patents.inventors.length+'位发明者'
        	                                                   +'<span class="caret" onclick="linkedIn_common_showAndHide(\'patentInventors_'+index+'\')" style="cursor:pointer"></span>'
        	                                             +'</div>'
        	                                             +'<div id="patentInventors_'+index+'" style="display:none">';
	    	        	   
	    	        	   
	    	        	          for(var i=0;i<patents.inventors.length;i++){
	    	        	        	  
	    	        	        	  
	    	        	        	          inventor_name='';
	    	        	        	          inventor_headline='';
	    	        	        	          inventor_profileImg='';
	    	        	        	          
	    	        	        	          if(patents.inventors[i].name==null){
	    	        	        	        	     continue;
	    	        	        	          }else{
	    	        	        	        	        if(patents.inventors[i].profile!=null){
	    	        	        	        	        	    inventor_name='<div class="sonTitle">'
	    	        	        	        	        	    	                +'<a href="'+patents.inventors[i].profile+'" target="_blank">'
	    	        	        	        	        	    	                       +patents.inventors[i].name
	    	        	        	        	        	    	                +'</a>'
	    	        	        	        	        	    	          +'</div>';
	    	        	        	        	        }else{
	    	        	        	        	        	    inventor_name='<div class="sonTitle">'
	    	        	        	        	        	    	                +patents.inventors[i].name
	    	        	        	        	        	    	          +'</div>';
	    	        	        	        	        }
	    	        	        	          }
	    	        	        	  
                                              if(patents.inventors[i].headline!=null){
                                            	      inventor_headline='<div class="attrText">'+patents.inventors[i].headline+'</div>';
                                              }	    	        	        	  
	    	        	        	           
                                              if(patents.inventors[i].profile_img!=null){
                                            	      inventor_profileImg='<img src="'+patents.inventors[i].profile_img+'" style="width:40px;height:40px" onerror="linkedIn_common_personImg(\''+rootPath+'\')" />';
                                              }else{
                                            	      inventor_profileImg='<img src="'+rootPath+'/static/img/person.png" />';
                                              }
	    	        	        	  
                                              
                                              patent_inventors +='<div class="row" style="margin-top:1%">'
                                            	                       +'<div class="col-md-1">'
                                            	                              +inventor_profileImg
                                            	                       +'</div>'
                                            	                       +'<div class="col-md-9">'
                                            	                              +inventor_name
                                            	                              +inventor_headline
                                            	                       +'</div>'
                                            	                 +'</div>';
	    	        	          }
	    	        	          patent_inventors +='</div>';
	    	           }
	    	           
	    	           
	    	           
	    	           var hr='';
	    	           if(index<(details.patents.length-1)){
	    	        	      hr='<hr>';
	    	           }
	    	           patents_list +='<div id="patent_'+index+'">'
	    	                               +patent_title
	    	                               +patent_mark
	    	                               +patent_date
	    	                               +patent_desc
	    	                               +patent_inventors
	    	                          +'</div>'
	    	                          +hr;
	      })
	      
	      return  linkedIn_common_model("patentsTitle","patentsContent","glyphicon glyphicon-map-marker","专利发明",patents_list);
}
//(19)测试成绩
function linkedIn_common_testScores(details){
	
	     var tests_list='';
	
	     var test_name='';
	     var test_score='';

	     
	     if(details.testScores==null){
	    	    $("#testScores").remove();
	    	    return;
	     }
	     
	     $.each(details.testScores,function(index,scores){
	    	 
	    	       test_name='';
	    	       test_score='';
	    	        
	    	       if(scores.name==null || scores.score==null){
	    	    	    return true;
	    	       }
	    	       
	    	       test_name='<div class="title">'+scores.name+'</div>';
	    	       test_score='<div class="attrText">成绩：'+scores.score+'</div>';
	    	 
	    	       tests_list +='<div id="testScore_'+index+'" style="margin-bottom:4%">'
	    	                          +test_name
	    	                          +test_score
	    	                    +'</div>';
	     })
	     
	     return  linkedIn_common_model("testScoresTitle","testScoresContent","glyphicon glyphicon-tasks","测试成绩",tests_list);
}

//最后，其他信息
function linkedIn_common_other(details){
	
	     var interests='';
	     var contact='';
	     var desc='';
	     var emails='';
	
	     var other_list='';
	
         if(details.add_info==null){
        	     $("#other").remove();
        	     return;
         }	
	
         
	     if(details.add_info.interests!=null){
	    	      interests='<div id="add_info_interest">'
	    	    	              +'<div class="title">兴趣爱好</div>'
	    	    	              +'<div style="margin-top:1%">'+details.add_info.interests+'</div>'
	    	    	         +'</div><br>';
	     }
	     
	     if(details.add_info.contact!=null){
	    	      if(details.add_info.contact.desc!=null){
	    	    	     desc='<div style="margin-top:1%">'+details.add_info.contact.desc+'</div>';     
	    	      }
	    	      if(details.add_info.contact.emails!=null){
	    	    	     emails='<div style="margin-top:1%">Email：'+details.add_info.contact.emails+'</div>';     
	    	      }
	    	      contact='<div id="add_info_contact">'
	    	    	            +'<div class="title">'+details.name+'的联系详情</div>'
	    	    	            +emails
	    	    	            +desc
	    	    	      +'</div><br>';
	     }
	     
	     other_list=interests+contact;
	     
	     return linkedIn_common_model("otherTitle","otherContent"," ","其他信息",other_list);
}
