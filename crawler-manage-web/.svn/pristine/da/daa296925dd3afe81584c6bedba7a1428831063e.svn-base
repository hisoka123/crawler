/*display search result */
function zhihu_displaySearchResults(searchResults,person){
	
	    if(searchResults==null || searchResults==''){
	    	  return "<p class='sr-h-nullPerson'>未能搜索到与&nbsp;<strong><i>"+person+"</i></strong>&nbsp;&nbsp;相关的人物信息。</p>";
	    }
	    
	    var display='';
	    var display_content='';
	    
	    var display_img='';
	    var display_name='';
	    var display_gender='';
	    var display_count='';
	    var display_bio='';
	    
	    
	    var profile_image='';
	    var profile='';
	    var name='';
	    var answers_num='';
	    var posts_num='';
	    var followers_num='';
	    
	    var hr="<hr class='col-md-10'>";
	    
	    $.each(searchResults,function(index,searchResult){
	    	    
		         display_content='';
		         
		         display_img='';
		         display_name='';
		         display_gender='';
		         display_count='';
		         display_bio='';
	    	     
	    	     profile_image='';
	    	     profile="http://www.zhihu.com/";
	             name='';
	             answers_num=0;
	     	     posts_num=0;
	     	     followers_num=0;
	    	     
	    	     if(searchResult.profile!=null && searchResult.profile!=""){
	    	    	   profile=searchResult.profile;
	    	     }
	    	     if(searchResult.profile_image!=null && searchResult.profile_image!=""){
	    	    	   profile_image=searchResult.profile_image;
	    	     }
	    	     if(searchResult.name!=null && searchResult.name!=""){
	    	    	   name=searchResult.name;
	    	     }else{
	    	    	   return true;
	    	     }
	    	     if(searchResult.answers_num!=null && searchResult.answers_num!=""){
	    	    	    answers_num=searchResult.answers_num;
	    	     }
	    	     if(searchResult.posts_num!=null && searchResult.posts_num!=""){
	    	    	     posts_num=searchResult.posts_num;
	    	     }
	    	     if(searchResult.followers_num!=null && searchResult.followers_num!=""){
	    	    	     followers_num=searchResult.followers_num;
	    	     }
	    	     
	    	     
	    	     //显示头像
	    	     display_img="<a href='"+profile+"' target='_blank'>"
                                 +"<img class='media-object img-circle sr-h-personImg' onerror='personImgSubstitute(this)' src='"+profile_image+"'  />"
                              +"</a>";
	    	     
	    	     //显示昵称、详情按钮
	    	     display_name="<div class='row'>"
                                   +"<h4 class='media-heading col-md-7' style='margin-top:1%'>"
                                         +"<a href='"+profile+"' target='_blank'>"+name+"</a>"
                                   +"</h4>"
                                   +"<div class='col-md-3 col-md-offset-1' style='text-align:center'>"
                                          +"<button class='btn btn-warning' type='button'>"
                                                   +"<i id='zhihu_searchResults_"+index+"_"+$.md5(searchResult.profile)+"_joinDetail' class='glyphicon glyphicon-plus'></i>&nbsp;加入详情&nbsp;"
                                          +"</button>"
                                   +"</div>"
                               +"</div>";
	    	
	    	     //显示性别
	    	     if(searchResult.gender!=null && searchResult.gender!=""){
	    	    	     if(personGender(searchResult.gender)=="男"){
	    	    	    	    display_gender="<div class='row'>"
                                                   +"<div class='col-md-1'>"
                                                         +"<div title='男' class='sr-h-gender sr-h-gender-male'></div>"
                                                   +"</div>"
                                                +"</div>";
	    	    	     }else if(personGender(searchResult.gender)=='女'){
	    	    	    	     display_gender="<div class='row'>"
                                                     +"<div class='col-md-1'>"
                                                          +"<div title='女' class='sr-h-gender sr-h-gender-female'></div>"
                                                     +"</div>"
                                                 +"</div>";
	    	    	     }
	    	     }
	    	     
	    	     //显示回答数、文章数、关注者
	    	     display_count="<div class='row' style='margin-top:1%;'>"
	    	    	                +"<div class='col-md-10'>"
                                          +"<span>回答数 &nbsp;<strong class='sr-h-fontColor'>"+answers_num+"</strong></span>"
                                          +"<span style='margin-left:3%'>文章数 &nbsp;<strong class='sr-h-fontColor'>"+posts_num+"</strong></span>"
                                          +"<span style='margin-left:3%'>关注者 &nbsp;<strong class='sr-h-fontColor'>"+followers_num+"</strong></span>"
                                    +"</div>"
                               +"</div>";

	    	     //简介
	    	     if(searchResult.bio!=null && searchResult.bio!=""){
	    	    	   display_bio="<div class='row' style='margin-top:1%'>"
                                        +"<div class='col-md-2'>简介：</div>"
                                        +"<div class='col-md-8' style='margin-left:-10%;width:76%'>"
                                               +searchResult.bio
                                        +"</div>"
                                    +"</div>";
	    	     }
	    	     
	    	     
	    	     display_content=display_name
                                 +display_gender
                                 +display_count
                                 +display_bio;
	    	     
	    	     
	    	     display+=displayResults(index,"zhihu_searchResults_",searchResults.length,"zhihu_more","显示全部知乎用户",display_img,"zhihu_name_",ROOTPATH+MODULE.ZHIHU_IMG,"知乎社区",display_content,searchResult.profile);
	    	
	    })
	    
	     return display;
	    
}
function zhihu_action(searchKey){
	
     if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
 	          $("#zhihu_searchResults").html(zhihu_displaySearchResults(zhihu_getSearchResult(searchKey),searchKey));
 	          
 	          $("#zhihu_more").remove();
 	          $("div[id^=zhihu_name_]").remove();
 	          
 	          $("#zhihu_searchResults").children().show();
 	          $("#zhihu_searchResults").show();
 	          
 	          $("#zhihu_searchResultsRightIcon").show();
 	          if($("hr").css("margin-top")=="0px"){
	        	    $("hr").removeAttr("style");
	          }
 	         module_action_reload("zhihu",searchKey);
     }
	
}

























/*-----------------------------------------------------------------------*/
/*-----------------------------------------------------------------------*/
/*-----------------------------------------------------------------------*/
/*-----------------------------------------------------------------------*/
/*-----------------------------------------------------------------------*/









//(1-1)原图片不能上加载，用此图替换
function zhihu_common_imgNoFind(rootPath){
	
	var img=event.srcElement;
    img.src=rootPath+"/static/img/small-zhihu.jpg";      
    img.onerror=null;
}
//(1-2)原图片不能上加载，用此图替换
function zhihu_common_bigNoFind(rootPath){
	
	var img=event.srcElement;
    img.src=rootPath+"/static/img/zhihu.PNG";
    img.onerror=null;
}
//(1-3)内容图片
function zhihu_common_replace(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/zhihu-replace.jpg";
    img.onerror=null;
}


/*
 * (2)等待加载
 
function zhihu_common_loading(loadName){
	$("#"+loadName).html('<div style="text-align:center"><br><br><img src="../static/img/loading.jpg" /><br>正在加载数据,请稍候......</div>');
}*/
/*
 *(3)最新动态
 */
function zhihu_common_actions(detail_content){
	    var action_content='';
	    var action_1='';
	    var action_2='';
	    
        if(detail_content.actions==''){
        	return;
        }
	    
	    for(var i=0;i<detail_content.actions.length;i++){
	            action_1='<div style="color:grey">'
	                           +'<span><small>'+detail_content.name+'&nbsp;&nbsp;'+zhihu_common_actionsOperation(detail_content.actions[i].operation)+'</small>'
	                           +'<span style="float:right">'+detail_content.actions[i].operate_time+'</span>'
	                      +'</div>';
	            
	            if(detail_content.actions[i].operation=='agree'){
	            	action_2=zhihu_common_actionsAgree(detail_content.actions[i],i);
	            	//continue;
	     	   }else if(detail_content.actions[i].operation=='follow'){
	     		    action_2=zhihu_common_actionsFollow(detail_content.actions[i]);
	     		   //continue;
	     	   }else if(detail_content.actions[i].operation=='answer'){
	     		    action_2=zhihu_common_actionsAnswer(detail_content.actions[i],i);
	     	   }else if(detail_content.actions[i].operation=='post'){
	     		      action_2=zhihu_common_actionsPost(detail_content.actions[i],i);
	     	   }
	          
	             action_content +='<div id="action_'+i+'" class="panel panel-default">'
	                                    +'<div class="panel-body">'
	                                          +action_1
	                                          +action_2
	                                    +'</div>'
	                             +'</div>';              
	    }
	    return action_content;		
}
/*
 *(3-1)最新动态类型判断
 */
function zhihu_common_actionsOperation(operation){
	  var operation_result=''; 
	  
	   if(operation=='agree'){
		   operation_result='赞同了回答';
	   }else if(operation=='follow'){
		   operation_result='关注了问题';
	   }else if(operation=='answer'){
		   operation_result='回答了问题';
	   }else if(operation=='post'){
		   operation_result='发表了文章';
	   }else{
		   operation_result='提了个问题';
	   }
	
	   return operation_result;	
}
/*
 *(3-2)最新动态类型判断: agree
 */
function zhihu_common_actionsAgree(actionsAgree,index){
	  var action_1='<div>'
                         +'<a href="'+actionsAgree.answer.question.link+'" target="_blank"><strong>'+actionsAgree.answer.question.title+'</strong></a>'
                   +'</div>';

      var action_2='<div style="width:100%">'
                         +'<div  style="width:10%;margin-left:1%;float:left;">'
                               +'<div style="margin-top:14%;background-color:#B9D3EE;height:20%;text-align:center" class="tooltip-test" data-placement="bottom" title="赞同">'
                                      +'<i class="glyphicon glyphicon-thumbs-up" style="margin-top:2%"></i>&nbsp;'
                                      +'<strong style="color:#4A708B;font-size:15px">'+actionsAgree.answer.agree_num+'</strong>'
                               +'</div>'
                         +'</div>'
                         +'<div style="width:85%;float:right;margin-left:1%;margin-top:1%">'
                                +'<div id="subContent_'+index+'">'
                                      +zhihu_common_subContent(actionsAgree.answer.content)
                                +'</div>'
                                +'<div id="allContent_'+index+'"  style="display: none;">'
                                       +'<i class="glyphicon glyphicon-open" onclick="zhihu_common_subContentShow(this)" style="cursor:pointer"></i>&nbsp;&nbsp;'
                                       +actionsAgree.answer.content
                                       +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong onclick="zhihu_common_subContentShow(this)" style="cursor:pointer">收起</strong>'
                                +'</div>'
                         +'</div>'
                  +'</div>';
      return action_1+action_2;
}
/*
 *(3-3)最新动态类型判断: follow
 */
function zhihu_common_actionsFollow(actionsFollow){
	  var action_1='<div>'
                      +'<a href="'+actionsFollow.answer.question.link+'" target="_blank"><strong>'+actionsFollow.answer.question.title+'</strong></a>'
                   +'</div>';
      return action_1;
}
/*
 *(3-4)最新动态类型判断: answer
 */
function zhihu_common_actionsAnswer(actionsAnswer,index){
	   return zhihu_common_actionsAgree(actionsAnswer,index);
}
/*
 *(3-5)最新动态类型判断: post
 */
function zhihu_common_actionsPost(actionsPost,index){
	var action_1='<div>'
                     +'<a href="'+actionsPost.article.link+'" target="_blank"><strong>'+actionsPost.article.titile+'</strong></a>'
                +'</div>';
	
	var action_2='<div style="width:100%">'
                       +'<div style="width:10%;margin-left:1%;float:left;">'
                              +'<div style="margin-top:14%;background-color:#B9D3EE;height:20%;text-align:center" class="tooltip-test" data-placement="bottom" title="赞同">'
                                     +'<i class="glyphicon glyphicon-thumbs-up" style="margin-top:2%"></i>&nbsp;'
                                     +'<strong style="color:#4A708B;font-size:15px">'+actionsPost.article.agree_num+'</strong>'
                              +'</div>'
                       +'</div>'
                       +'<div style="width:85%;float:right;margin-left:1%;margin-top:1%">'
                              +'<div id="subContent_'+index+'">'
                                     +zhihu_common_subContent(actionsPost.article.content)
                              +'</div>'
                              +'<div id="allContent_'+index+'" style="display: none;">'
                                     +'<i class="glyphicon glyphicon-open" onclick="zhihu_common_subContentShow(this)" style="cursor:pointer"></i>&nbsp;&nbsp;'
                                     +actionsPost.article.content
                                     +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong onclick="zhihu_common_subContentShow(this)" style="cursor:pointer">收起</strong>'
                              +'</div>'
                       +'</div>'
                 +'</div>';
	
	return action_1+action_2;
	
	
}
/*
 *(3-5)"最新动态",首次显示部分字段
 */
function zhihu_common_subContent(content){
	 var subContent='';
	 var subContent_result;
	 if(content.length>300){	   
		   subContent_result=content.replace(/<[^>]+>/g,'');		  
		   return '<small>'+subContent_result.substr(0,200)+'</small>......<strong onclick="zhihu_common_content(this)" style="cursor:pointer">显示全部</strong>';
	 }else{
		   return '<small>'+content+'</small>';
	 }
}
/*
 *(3-6)"最新动态",再次显示部分字段
 */
function zhihu_common_subContentShow(subContent){
	var index=$(subContent).parent().parent().parent().parent().parent().attr("id");
	index=index.split("_");
	$('#subContent_'+index[1]).show();
	$('#allContent_'+index[1]).hide();
	
}
/*
 *(3-7)"最新动态",显示全部字段
 */
function zhihu_common_content(subcontent){
	var index=$(subcontent).parent().parent().parent().parent().parent().attr("id");
	index=index.split("_");
	$('#subContent_'+index[1]).hide();
	$('#allContent_'+index[1]).show();
}
/*
 *(4)性别
 */
function zhihu_common_sex(gender){
	 if(gender=="他"){
   	     return "男";
     }else if(gender=="她"){
   	     return "女";
     }else if(gender=="男" || gender=="女"){
   	     return gender;
     }else{
    	 return "未知";
     }
}
/*
 *(5)提问
 */
function zhihu_common_asks(rootPath,url,ask_num){
	 
	  var asksContent=zhihu_getAsksContent(rootPath,url);
	  if(asksContent==''){
		  if(ask_num>0){
			  return '<p style="color:red">由于用户设定权限限制，目前无法获得提问内容。</p>'
		  }else{
		      return;
		  }
	  }
	  var asksContent_display='';
	  for(var i=0;i<asksContent.length;i++){
		  asksContent_display +='<div class="panel panel-default">'
			                        +'<div class="panel-body">'
			                               +'<div style="width:10%;float:left">'
			                                      +'<div style="background-color:#DEDEDE;margin-left:1%;text-align:center" >'
			                                             +'<div style="font-size:13px;font-color:#FFF;font-weight:bold;margin-top:2%;">'+asksContent[i].visits_num+'</div>'
			                                             +'<div>浏览</div>'
			                                      +'</div>'
			                               +'</div>'
			                               +'<div style="width:87%;float:right;margin-left:2%">'
			                                      +'<div><a href="'+asksContent[i].link+'" target="_blank"><strong>'+asksContent[i].title+'</strong></a></div>'
			                                      +'<div class="row" style="color:grey">'
			                                             +'<div class="col-md-3"><i class="glyphicon glyphicon-question-sign"></i>&nbsp;<small>'+asksContent[i].answers_num+'个回答</small></div>'
			                                             +'<div class="col-md-3"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;<small>'+asksContent[i].followers_num+'人关注</small></div>'
			                                      +'</div>'
			                               +'</div>'
			                        +'</div>'
			                   +'</div>';
	  }
	
	return asksContent_display;
}

/*
 *(6)回答
 */
function zhihu_common_answer(rootPath,url,answer_num){
	var getAnswerContent=zhihu_getAnswerContent(rootPath,url,answer_num);
	if(getAnswerContent==''){
		 if(answer_num>0){
			 return '<p style="color:red">由于用户设定权限限制，目前无法获取回答内容。</p>';
		 }else{
			 return;
		 }
	}
	var answerContent_display='';
	for(var i=0;i<getAnswerContent.length;i++){
		//alert(getAnswerContent[i].agree_num);
		answerContent_display +='<div id="answer_'+i+'" class="panel panel-default">'
			                          +'<div class="panel-body">'
			                                 +'<div>'
			                                        +'<a href="'+getAnswerContent[i].question.link+'" target="_blank">'
			                                             +'<strong>'+getAnswerContent[i].question.title+'</strong>'
			                                        +'</a>'			                                           
			                                 +'</div>'
			                                 +'<div style="width:100%">'
			                                        +'<div style="width:10%;margin-left:1%;float:left;">'
			                                               +'<div style="margin-top:14%;background-color:#B9D3EE;height:20%;text-align:center" class="tooltip-test" data-placement="bottom" title="赞同">'
			                                                      +'<i class="glyphicon glyphicon-thumbs-up" style="margin-top:2%"></i>&nbsp;'
			                                                      +'<strong style="color:#4A708B;font-size:15px">'+getAnswerContent[i].agree_num+'</strong>'
			                                               +'</div>'
			                                        +'</div>'
			                                        +'<div style="width:87%;float:right;margin-left:1%;margin-top:1%">'
			                                               +'<div id="answerSubContent_'+i+'" >'
			                                                       +zhihu_common_answerSubContent(getAnswerContent[i].content)
			                                               +'</div>'
			                                               +'<div id="answerAllContent_'+i+'" style="display: none;">'
			                                                       +'<i class="glyphicon glyphicon-open" onclick="zhihu_common_answerSubContentShow(this)" style="cursor:pointer"></i>&nbsp;&nbsp;'
			                                                       +getAnswerContent[i].content
			                                                       +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong onclick="zhihu_common_answerSubContentShow(this)" style="cursor:pointer">收起</strong>'
			                                               +'</div>'
			                                        +'</div>'
			                                 +'</div>'
			                                 +'<div class="row" style="color:grey;margin-left:10%;">'
			                                       +'<div class="col-md-3" style="margin-top:1%">'
			                                              +'&nbsp;&nbsp;<i class="glyphicon glyphicon-comment"></i>&nbsp;'+getAnswerContent[i].comments_num+'条评论'
			                                       +'</div>'
			                                       +'<div class="col-md-5" style="margin-top:1%">'
			                                              +'<i class="glyphicon glyphicon-time"></i>&nbsp;'+getAnswerContent[i].created_time
			                                       +'</div>'
			                                 +'</div>'
			                          +'</div>'
			                    +'</div>';
	}
	return answerContent_display;
}
/*
 *(6-1)"回答",首次显示部分字段
 */
function zhihu_common_answerSubContent(content){
	 var subContent='';
	 var subContent_result;
	 if(content.length>300){	   
		   subContent_result=content.replace(/<[^>]+>/g,'');		  
		   var a='<small>'+subContent_result.substr(0,200)+'</small>......<strong onclick="zhihu_common_answerAllContent(this)" style="cursor:pointer">显示全部</strong>';
	       return a;
	 }else{
		   return '<small>'+content+'</small>';
	 }
}
/*
 *(6-2)"回答",再次显示部分字段
 */
function zhihu_common_answerSubContentShow(subContent){
	var index=$(subContent).parent().parent().parent().parent().parent().attr("id");
	index=index.split("_");
	$('#answerSubContent_'+index[1]).show();
	$('#answerAllContent_'+index[1]).hide();
	
}
/*
 *(6-3)"回答",显示全部字段
 */
function zhihu_common_answerAllContent(subcontent){
	var index=$(subcontent).parent().parent().parent().parent().parent().attr("id");
	index=index.split("_");
	$('#answerSubContent_'+index[1]).hide();
	$('#answerAllContent_'+index[1]).show();
}