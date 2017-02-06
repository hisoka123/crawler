/*
 * (1)爬取结果
 */
function sina_crawlerPersons(rootPath,crawlerPerson){
	  var  crawler_list;
	  $.ajax({
		   url:rootPath+"/sinaWeibo/crawler_searchResult",
		   data:encodeURI(encodeURI("crawlerPerson="+crawlerPerson)),
		   async:false,
	       success:function(result1_list){
	    	       crawler_list=result1_list;
	       },
	       error:function(){
	    	      crawler_list="error";
	       }		  
	  });	
	 /* alert("sina_crawlerPersons返回结果为："+crawler_list);*/
	  return crawler_list;	
}



/*
 * (2-1)获取UserFeed全部人物，包括已关注、已关注但已取消的，即enable=0、enable=1
 */
function sina_person_list(rootPath){
	   var userFeeds;	   
	   $.ajax({
		      url:rootPath+"/sinaWeibo/attentionInfo_read",
    	      async:false,
    	      dataType:'json',
    	      success:function(result2_list){  	    	   
    		       userFeeds=result2_list;
    	      },
    	      error:function(){
    	    	  userFeeds="error";
    	      }		   		   
	   });
	// alert("返回UserFeed表的对象为："+userFeeds);
	 return userFeeds;	
}

/*
 * (2-2)在已关注人物列表中，搜索某个（或某些）人物
 */
function sina_person_search_list(rootPath,attention_searchPerson){
	  /* alert("测试sina_person_search_list: \n rootPath="+rootPath+"\n attention_searchPerson="+attention_searchPerson);*/
	   var person_search_list_result;
	   $.ajax({
		     type:"get",
		     url:rootPath+"/sinaWeibo/attentionInfo_search_result",
	         async:false,
	         data:encodeURI(encodeURI("attention_searchPerson="+attention_searchPerson)),
		     success:function(result2_2){
		    	 person_search_list_result=result2_2;
		     },
		     error:function(){
		    	 person_search_list_result="error";
		     }		   
	   });
	
	   /*alert("测试 person_search_list返回结果为："+person_search_list_result);*/
	   return person_search_list_result;
}



/*
 * (3_1)加关注，
 *    针对 情景：加关注时，人物的基本信息数据来源为数据库。
 *    举例：当用户对一个“已关注”的人物进行了“取消关注”的动作后，再进行“关注”，即调用该方法。
 *  
 */
function sina_create_friendships(rootPath,content){
	  /* alert("测试sina_create_friendships:\n\n rootPath="+rootPath+"\n content="+content);*/
	    var create_result='';
	    $.ajax({  		    
              url:rootPath+"/sinaWeibo/attentionSave",
              data:"attentionJson="+content,
              async:false,
              success:function(result){ 
        	          create_result=result;	           		    	  
              },
              error:function(){
	                create_result="error"; 	    	    	 
              }
        });
	/* alert("测试sina_create_friendships返回结果：create_result="+create_result);*/
	return create_result;
}

/*
 * (3_2)加关注
 *      针对情景：加关注时，人物的基本信息数据来源为爬取结果。
 *      举例：当用户对一个从未关注的人物，进行关注时，调用该方法
 */
function sina_crawlerCreate_friendships(rootPath,content){	     
	    // alert("测试sina_crawlerCreate_friendships:\n\n rootPath="+rootPath+"\n content="+content);
	     var crawlerCreate_result; 
	     $.ajax({   		    
	           url:rootPath+"/sinaWeibo/crawlerSave",
	           data:"crawlerJson="+content,
	           async:false,
	           success:function(result){
	    	       crawlerCreate_result=result;    	  
	           },
	          error:function(){
	        	   crawlerCreate_result="error";
	          }
         }); 
	     
	    // alert("测试sina_crawlerCreate_friendships返回结果：crawlerCreate_result="+crawlerCreate_result);
	     return crawlerCreate_result;	
}




/*
 *(4)取消关注 
*/
function sina_destroy_friendships(rootPath,uid){
	/*alert("测试sina_destory_friendships:\n\n rootPath="+rootPath+"\n  uid="+uid);*/
	var destory_result='';
	$.ajax({
	      url:rootPath+"/sinaWeibo/crawlerDelete",
	      data:"deleteUid="+uid,
	      async:false,
	      success:function(result){
	    	      destory_result=result;   		    	  
	      },
	      error:function(){
	    	      destory_result="error";
	      }
	 });
	/*alert("测试sina_destroy_friendships返回结果： destory_result="+destory_result);*/
	return destory_result;
}

/*
 * (5)从数据库读取人物的基本信息
 */
function sina_attention_personalInfo(rootPath,uid){

	//alert("测试\n 从数据库读取人物的基本信息    sina_attention_personalInfo:\n rootPath="+rootPath+"\n  uid="+uid);
	var profile_result;
	$.ajax({
  	    type:"get",
  	    url:rootPath+"/sinaWeibo/attention_personalInfo",
  	    async:false, 
  	    data:"uid="+uid,
  	    success:function(result){
  	           profile_result=result;
  	    },
  	    error:function(){
  	    	   profile_result="error";
  	    }
  	    
     });
	/*alert("测试\n  从数据库读取人物的基本信息   sina_attention_personalInfo返回结果: "+profile_result);*/
	return profile_result;
}




/*
 * (6)从接口处读取某个人发表的帖子详情
 */
function sina_post_read(rootPath,uid,pageIndex){
	/*alert("测试sina_post_read:\n rootPath="+rootPath+"\nuid="+uid+"\npageIndex="+pageIndex);*/
	 var post_result;
	 $.ajax({
         type:"get",
         dataType:"json", 
         url:rootPath+"/sinaWeibo/personWeibo_detail",
         async:false, 
         data:"uid="+uid+"&page="+pageIndex,
         success:function(result){     		        	      
        		 post_result=result; //Json格式对象       		                        		                
        	  },
         error:function(){
        	     
        	     post_result="error";
         }
      }); 	
	 /*alert("测试sina_post_read返回结果：post_result="+post_result);*/
	return post_result;	
}


/*
 * (7)从数据库中读取全部已关注人物的uid,已在后台对enable=0人物做了处理
 */
function sina_AllFriendships_uid(rootPath){
	    /* alert("测试sina_AllFriendships_uid");*/
         var allFriendships;
	      $.ajax({
	          url:rootPath+'/sinaWeibo/personAllUid',
	          async: false,
	          success:function(data){
                     allFriendships=data;                     
	          },
	          error:function(){
	        	     allFriendships="error";
	          }
          });
	      
	     /* alert("测试sina_AllFriendships_uid返回结果: allFriendships="+allFriendships);*/
	      return allFriendships;
}

/*
 *(8)在未关注的情况下，获得单个人的基本信息
 */
function sina_unattention_profile(rootPath,nickname,uid){
	
	     /*alert("测试sina_unattention_profile:\n nickname="+nickname+"\nuid="+uid);*/
	     var sina_unattention_profile_result;
	     $.ajax({
	    	 type:'get',
	    	 url:rootPath+'/sinaWeibo/un_attention_oneProfile',
	    	 data:encodeURI(encodeURI('nickname='+nickname+'&uid='+uid)),
	    	 async:false,
	    	 success:function(result8){
	    		 sina_unattention_profile_result=result8;
	    	 },
	    	 error:function(){
	    		 sina_unattention_profile_result="error";
	    	 }
	    	 
	     });
	    /* alert("测试sina_unattention_profile返回结果："+sina_unattention_profile_result);*/
	     return sina_unattention_profile_result;	
}

/*
 * (9)获取帖子页数
 */
function sina_weiboPage_count(rootPath,uid){
	/* alert("测试sina_weiboPage:\nuid="+uid);*/
	 var sina_weiboPage_count_result;
	 $.ajax({
		 type:'get',
		 url:rootPath+'/sinaWeibo/weiboPage_count',
		 async:false,
		 data:'uid='+uid,
		 success:function(result9){
			 sina_weiboPage_count_result=result9;
		 },
		 error:function(){
			 sina_weiboPage_count_result="error";
		 }
		 
	 });
	/* alert("测试sina_weiboPage返回结果："+sina_weiboPage_count_result);*/
	 return sina_weiboPage_count_result;	 
}

/*
 *(10)获取人脉
 */
function sina_contacts(rootPath,uid,depth,limit){
	//alert("测试sina_contacts:\n rootPath: "+rootPath+"\n uid: "+uid+"\ndepth: "+depth+"\nlimit:"+limit);
	var sina_contacts_result;
	$.ajax({
		type:'get',
		url:rootPath+'/sinaWeibo/contactsDetail',
		async:false,
		data:'uid='+uid+'&depth='+depth+'&limit='+limit,
		success:function(result_10){
			sina_contacts_result=result_10;
		},
		error:function(){
			sina_contacts_result="error";
		}		
	});
	
	//alert("测试sina_contacts返回结果:"+sina_contacts_result);
	return sina_contacts_result;
}