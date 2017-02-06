/*
 * (1) 将大于10000的数，显示为"万"
 * 
 */
function toWan(count){
       if(count>10000){
       	    return Math.round(count/10000)+'万';
       }else{
       		 return count;
       }    	  
}


/*
 * (2) 获取回贴内容
 */
function sina_common_getRetweetContent(retweet_note,retweet_id){
	   	
	    var retweet_row=''; 	
	    var retweet_pic_temp='';         
	    var retweet_p;
	  
	    //1.显示回贴微博内容
	    retweet_row ="<div>"+retweet_note.content_html+"</div>";
	 
	    
	    //2.显示回帖微博内容图片
	     retweet_p=typeof(retweet_note.pic_urls);
	     if("undefined" != retweet_p && null != retweet_note.pic_urls){
	   	      for(var j=0;j<retweet_note.pic_urls.length;j++){
	  		       retweet_pic_temp +='<span><img id="imgRetweet_'+retweet_id+'_small_original_'+j+'" src="'+retweet_note.pic_urls[j]+'" onClick="sina_imgsZoom(this)" style="cursor:url(\'../static/img/magnifier.ico\'),auto;margin-top:5px;"/>&nbsp;</span>'; 
	   	      }       	   
	   	      retweet_row +='<div id="imgRetweet_'+retweet_id+'">'+retweet_pic_temp+'</div>';
	   	      retweet_row +='<div id="imgRetweet_'+retweet_id+'_big"></div>';
	     }
	  	   
	         
	    //3.显示回贴微博来源及时间
	     var source=retweet_note.source;
         if(null==source){
         	source="未知";
         }
	   	retweet_row +='<div><h6>&nbsp;&nbsp;'+retweet_note.created_at+'&nbsp;&nbsp;来自&nbsp;&nbsp;'+source+'</h6>'+'</div>'
	   	      +'<div class="row">'
	   	            +'<div class="col-md-2 col-md-offset-1">转发&nbsp;'+retweet_note.reposts_count+'</div>'
	   	            +'<div class="col-md-2 col-md-offset-1">评论&nbsp;'+retweet_note.comments_count+'</div>'
	   	            +'<div class="col-md-2 col-md-offset-1"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;'+retweet_note.comments_count+'</div>'
	   	      +'</div>';          	      

	    return retweet_row;
}

/*
 *(3) 显示微博内容（一页）。
 */
function sina_common_getWeiboContent(gender,note){
	
	/*alert("测试sina_commonn_getWeiboContent:\ngender="+gender+"\nnote="+note);*/
	
	
	 var p;
	 var r;
	 var pic_temp;
     var row='';
     
     
    for(var i=0;i<note.length;i++){          	              	            
          pic_temp='';
          p=typeof(note[i].pic_urls);
          r=typeof(note[i].retweet);
          var color='';
         
          
          if(gender=="女"||gender=="f"){
       	    color='#FFC0CB';
          }else{
       	    color='#99CCFF';
          } 
            
          //1.显示微博内容
          row +='<div class="panel panel-default">'
                   +'<div class="panel-body" >'
       	            +'<div>'+note[i].content_html+'</div>';
          
          //2.显示微博内容图片
           if("undefined" != p && null != note[i].pic_urls){
         	   for(var j=0;j<note[i].pic_urls.length;j++){
        		       pic_temp +='<span><img id="img_'+i+'_small_original_'+j+'" src="'+note[i].pic_urls[j]+'" onClick="sina_imgsZoom(this)" style="cursor:url(\'../static/img/magnifier.ico\'),auto;margin-top:5px;"/>&nbsp;</span>';        		       
         	   }
         	   
         	   row +='<div id="img_'+i+'">'+pic_temp+'</div>';
         	   row +='<div id="img_'+i+'_big"></div>';
            } 
             
        	    
          //3.显示回贴
          if("undefined" != r && null != note[i].retweet){
       	      row +='<div class="sina-retweet-position">'
       	               +'<b class="sina-bubble-1">◆</b>'
       	               +'<div class="sina-margin-left-1"><small>'+sina_common_getRetweetContent(note[i].retweet,i)+'</small></div>'
       	           +'</div>';     
          }
          
          
          
          //4.显示微博来源及时间
            var source=note[i].source;
            if(null==source){
            	source="未知";
            }
         	row +='<div><h6>&nbsp;&nbsp;'+note[i].created_at+'&nbsp;&nbsp;来自&nbsp;&nbsp;'+source+'</h6>'+'</div>'
         		  +'</div>'
                 +'<div class="panel-footer" style="background-color:'+color+'">'          		       
         	            +'<div class="row">'
         	                 +'<div class="col-md-2 col-md-offset-1">转发&nbsp;'+note[i].reposts_count+'</div>'
         	                 +'<div class="col-md-2 col-md-offset-1">评论&nbsp;'+note[i].comments_count+'</div>'
         	                 +'<div class="col-md-2 col-md-offset-1"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;'+note[i].comments_count+'</div>'
         	            +'</div>'
         	      +'</div>'
         	      +'</div>';
      
    } 
    
    /*alert("测试sina_common_getWeiboContent返回结果\n"+row);*/
       return row;
} 
/*
 * (4)将输入小图url换成大图url
 */
function sina_toBigImgURL(small_ImgURL){
	/*alert("小图url:"+small_ImgURL);*/
	
	var sina_toBigImgURL_result;
	var img_temp=small_ImgURL.split("/");
	img_temp[3]="bmiddle";
	sina_toBigImgURL_result=img_temp.join("/");
   
	/*alert("大图url:"+sina_toBigImgURL_result);*/
	
	return sina_toBigImgURL_result;
}

/*
 * (5)图片放大
 * 每张图片都属于一个图片组，图片组的长度≥1
 * 图片组id命名: img_数字，该数字的值为该微博帖子在该页的顺序。由0开始
 * 图片id命名：img_数字_small_original_数字。img_数字为所在图片组，small_original_数字为图片在图片组中序号，从0开始
 */

function sina_imgsZoom(img){
	
	var index_active=$(img).attr("id");        //获取点击图片的id
	index_active=index_active.split("_");     //将id转换成数组，
	var imgs_id=$(img).parent().parent().attr("id");//图片组id名
	var imgs_num=$(img).parent().parent().children().length; //获取图片组中有多少张图片
	var content='';
	
	
	var small_imgs=new Array();
	var big_imgs=new Array();
	
	//图片组，小图
	for(var i=0;i<imgs_num;i++){
		small_imgs[i]=$(img).parent().parent().children().eq(i).children().attr("src");
		
	}
	
	//图片组，大图
	for(var i=0;i<imgs_num;i++){
		big_imgs[i]=sina_toBigImgURL(small_imgs[i]);
	}
	
	content='<br><a  onClick="sina_common_bigToSmall(this);" style="cursor:pointer;" >↑&nbsp;收起</a><br>'
		    +imgSlide(imgs_id,big_imgs,index_active[index_active.length-1],small_imgs,"5")
		    +'<br>';
	
   
   //备用	$("#"+imgs_id).html("<br><br>"+imgSlide(imgs_id,big_imgs,index_active[index_active.length-1],small_imgs,"5")+"<br>");
	$("#"+imgs_id).hide();
	$("#"+imgs_id+"_big").html(content);
}


/*
 * (6)图片缩小
 */

function sina_common_bigToSmall(a){
	var bigId=$(a).parent().attr("id");   //eg:bigId=img_0_big
	var smallId=bigId.split("_big");      //eg:smallId=img_0

	
	$("#"+bigId).html("");
	$("#"+smallId[0]).show();
	
}


/*
 * (7)一度人脉，以json对象形式返回具体的人
 */
function sina_common_oneNetworkJson(rootPath,uid){
     
	 return sina_contacts(rootPath,uid,'1','5');	
}



/*
 * (8)二度人脉,以数组的形式返回具体的人，数组中的元素为json对象
 */
function sina_common_twoNetworkJson(rootPath,one_contacts){
	 var two_contacts=new Array(); 
	
	 for(var i=0;i<one_contacts.friend.length;i++){
		   
	       two_contacts[i]=sina_common_oneNetworkJson(rootPath,one_contacts.friend[i].uid);	   
     }
	 
	 //alert("测试sina_common_twoNetworkJson返回结果："+two_contacts);
	 return two_contacts;
}


/*
 * (9)链接至人脉中人物帖子详情
 */
function sina_common_contactsWeibo(rootPath,uid,nickname){
     //  alert("测试sina_common_contactsWeibo:\n"+"*****rootPath:"+rootPath+"\n*****uid:"+uid+"\n*****nickname:"+nickname);
	   var all_attention_uid=sina_AllFriendships_uid(rootPath);
	   all_attention_uid=all_attention_uid.split(",");
	   var is_attention=0   //0,人脉中此人物未关注；1，此人物已关注
	   for(var i=0;i<all_attention_uid.length;i++){
		        if(all_attention_uid[i]==uid){
		        	is_attention=1;
		        	break;
		        }
	   }
	   if(is_attention==0){
	         location.href=encodeURI(encodeURI(rootPath+'/sinaWeibo/unattentionPosts?uid='+uid+'&nickname='+nickname));	  
	   }else if(is_attention==1){
		     location.href=encodeURI(encodeURI(rootPath+'/sinaWeibo/postsAttention?uid='+uid));
	   }	
}


/*
 *(10)未连接到后台服务器的提示
 */
function sina_common_dataBaseError(){
	alert("与数据库连接断开！");
}
/*
 * (11)从新浪中取消关注，失败提示
 */
function sina_common_deleteFromSina(){
	alert("未能从新浪微博的关注名单中删除！");

}
/*
 * (12)往新浪中加关注，失败提示
 */
function sina_common_addToSina(){
	alert("未能加入到新浪微博的关注名单中！");
		  
}
/*
 * (13)等待加载
 */
function sina_common_loading(){
	
	//$("#"+loadName).html('<div style="text-align:center"><br><br><img src="../static/img/loading.jpg" /><br>正在加载数据,请稍候......</div>');
    return '<div style="text-align:center"><br><br><img src="../static/img/loading.jpg" /><br>正在加载数据,请稍候......</div>';
}
/*
 *(14)时间戳变时间格式化
 */
function sina_common_date(time){
	var month='';
	
	if(time==null) return "N/A";
	
	
	var date=new Date(time).toString().split(" ");
	if(date[1]=="Jan"){
		month="01";
	}else if(date[1]=="Feb"){
		month="02";
	}else if(date[1]=="Mar"){
		month="03";
	}else if(date[1]=="Apr"){
		month="04";
	}else if(date[1]=="May"){
		month="05";
	}else if(date[1]=="Jun"){
		month="06";
	}else if(date[1]=="Jul"){
		month="07";
	}else if(date[1]=="Aug"){
		month="08";
	}else if(date[1]=="Sep"){
		month="09";
	}else if(date[1]=="Oct"){
		month="10";
	}else if(date[1]=="Nov"){
		month="11";
	}else if(date[1]=="Dec"){
		month="12";
	}	
	return date[3]+"-"+month+"-"+date[2]+"   "+date[4];	
}
/*
 *(16)性别
 */
function sina_common_sex(gender){
	if("男"==gender){
		return "男";
	}else if("m"==gender){
		return "男";
	}else if("女"==gender){
		return "女";
	}else if("f"==gender){
		return "女";
	}
}
/*
 *(17)原小图片不能加载时，显示这张图片
 */
function sina_common_smallImgNoFound(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/small-sina.ico";
    img.onerror=null;
}
/*
 *(18)原大图片不能加载时，显示这张图片
 */
function sina_common_bigImgNoFound(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/sinaWeibo.ico";
    img.onerror=null;
}