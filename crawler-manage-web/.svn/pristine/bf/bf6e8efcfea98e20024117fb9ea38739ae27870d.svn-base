$(function(){
	$(document).keydown(function(event){
		 
	      //”回车键“监听事件
	      if(event.keyCode==13){
	    	     if($("#searchBox_big_content").is(":focus")){
	    	    	  $("#searchBox_big_btn").click();
	    	     }
	    	     
	    	     if($("#searchBox_small_content").is(":focus")){
	    	    	  $("#searchBox_small_btn").click();
	    	     }
	      }
    })
    
    
  //左侧菜单项加载
   common_loadLeftMenu();
	
})

/*搜索框*/
//检查搜索框是否为空
function searchBoxCheckIsNull(searchBoxId){
	var searchContent=$("#"+searchBoxId).val();
    if(searchContent==""){
  	     $("#"+searchBoxId).val("关键词不能为空! ! !")
  	     $("#"+searchBoxId).attr("style","height:40px;color:red");
  	     return  "";
    }else{
    	 return $.trim(searchContent);
    }   
}
//接上面的函数，将“关键词不能为空”去掉，并将字体颜色改为黑色
function searchBoxFocus(searchBox){
	 $(searchBox).val("");
	 $(searchBox).attr("style","height:40px;color:black");
}
//搜索框中的placeholder
function searchBoxPlaceholder(module){

	if(module=="all"){
	        $("#searchBox_big_content").attr("placeholder","请输入关键词");
            $("#searchBox_small_content").attr("placeholder","请输入关键词");
	   }else if(module=="sina"){
		    $("#searchBox_big_content").attr("placeholder","请输入新浪微博用户昵称");
            $("#searchBox_small_content").attr("placeholder","请输入新浪微博用户昵称");
	   }else if(module=="baidu"){
		    $("#searchBox_big_content").attr("placeholder","请输入百度百科词条");
            $("#searchBox_small_content").attr("placeholder","请输入百度百科词条");
	   }else if(module=="zhihu"){
		    $("#searchBox_big_content").attr("placeholder","请输入知乎用户昵称");
            $("#searchBox_small_content").attr("placeholder","请输入知乎用户昵称");
	   }else if(module=="weixin"){
		    $("#searchBox_big_content").attr("placeholder","请输入微信公众号 / 微信文章");
            $("#searchBox_small_content").attr("placeholder","请输入微信公众号 /微信文章");
	   }else if(module=="linkedIn"){
		    $("#searchBox_big_content").attr("placeholder","请输入领英人物名称");
            $("#searchBox_small_content").attr("placeholder","请输入领英人物名称");
	   }else if(module=="taobao"){
		    $("#searchBox_big_content").attr("placeholder","请输入淘宝用户名称");
            $("#searchBox_small_content").attr("placeholder","请输入淘宝用户名称");
	   }else if(module=="youku"){
		    $("#searchBox_big_content").attr("placeholder","请输入优酷用户名称");
            $("#searchBox_small_content").attr("placeholder","请输入优酷用户名称");
	   }else if(module=="youtube"){
		    $("#searchBox_big_content").attr("placeholder","请输入YouTuBe用户名称");
            $("#searchBox_small_content").attr("placeholder","请输入YouTuBe用户名称");  
	   }else if(module=="facebook"){
		    $("#searchBox_big_content").attr("placeholder","请输入Facebook用户昵称");
            $("#searchBox_small_content").attr("placeholder","请输入Facebook用户昵称");
	   }else if(module=="pbccrc") {
		   	alert("commons.js searchBoxPlaceholder pbccrc"); /////////
	   }else if(module=="gsxt"){
		    $("#searchBox_big_content").attr("placeholder","请输入企业名称");
	   }else if(module.indexOf("refawang")!=-1){
		    if(module=="refawang_name"){
		    	$("#searchBox_big_content").attr("placeholder","请输入公司名称.如，信和汇诚信用管理(北京)有限公司");
		    }else if(module=="refawang_identity"){
		    	$("#searchBox_big_content").attr("placeholder","请输入身份证号.如180109300000000987");
		    }
	   }else if(module.indexOf("shixin")!=-1){
		   if(module=="shixin_name"){
		    	$("#searchBox_big_content").attr("placeholder","请输入被执行人姓名/名称.如，信和汇诚信用管理(北京)有限公司");
		    }else if(module=="shixin_identity"){
		    	$("#searchBox_big_content").attr("placeholder","请输入身份证号码/组织机构代码.如180109300000000987");
		    }
	   }else if(module.indexOf("dailianmeng")!=-1) {
		    if(module=="dailianmeng_name"){
		    	$("#searchBox_big_content").attr("placeholder","请输入公司名称.如，信和汇诚信用管理(北京)有限公司");
		    }else if(module=="dailianmeng_identity"){
		    	$("#searchBox_big_content").attr("placeholder","请输入身份证号.如,180109300000000987");
		    }
	   }else if(module.indexOf("iecms")!=-1){
		    if(module=="iecms_jyzdm"){
		    	$("#searchBox_big_content").attr("placeholder","请输入13位经营者代码，如，320113475094X");
	            $("#searchBox_small_content").attr("placeholder","请输入13位经营者代码，如，320113475094X");
		    }else if(module=="iecms_jyzmc"){
		    	$("#searchBox_big_content").attr("placeholder","请输入经营者名称，如，北京三花高科技公司");
	            $("#searchBox_small_content").attr("placeholder","请输入经营者名称，如，北京三花高科技公司");
		    }else if(module=="iecms_shtydm"){
		    	$("#searchBox_big_content").attr("placeholder","请输入统一社会信用代码，如，91440113MA59C6K03M");
	            $("#searchBox_small_content").attr("placeholder","请输入统一社会信用代码，如，91440113MA59C6K03M");
		    }
		    
	   }else if(module=="creditchina"){
		    $("#searchBox_big_content").attr("placeholder","请输入企业或者个人信息（如：名称、企业组代码或身份证号）");
	   }else if(module=="fang"){
		   $("#searchBox_big_content").attr("placeholder","请输入小区名称或地址，给自己的房子估个价");
	  
	   }else if(module=="iautos"){
		    $("#searchBox_big_content").attr("placeholder","请输入汽车名称类型，如，奥迪Q7");	
	   }else if(module=="sipo"){
		    $("#searchBox_big_content").attr("placeholder","请输入企业名称");
	   }else if(module=="zhengxin"){
		    $("#searchBox_big_content").attr("placeholder","请输入企业名称.如,诺和诺德（中国）制药有限公司");
	   }else if(module=="cnca"){
		    $("#searchBox_big_content").attr("placeholder","请输入获证组织名称，不少于6个字符");
            $("#searchBox_small_content").attr("placeholder","请输入获证组织名称，不少于6个字符");
	   }else if(module=="sxjlcxpt"){
		    $("#searchBox_big_content").attr("placeholder","请输入失信机构名称");
           $("#searchBox_small_content").attr("placeholder","请输入失信机构名称");
	   }else if(module=="cnca"){
			$("#searchBox_big_content").attr("placeholder","请输入不少于6个字符");
			$("#searchBox_small_content").attr("placeholder","请输入不少于6个字符");
	   }else if(module.indexOf("fahaicc")!=-1){
		   if(module=="fahaicc_name"){
		    	$("#searchBox_big_content").attr("placeholder","请输入个人/企业名称.如,信和汇诚信用管理(北京)有限公司");
		    }else if(module=="fahaicc_identity"){
		    	$("#searchBox_big_content").attr("placeholder","请输入身份证号码/组织机构代码.如,180109300000000987");
		    }
	   }else if(module === "fSearch"){
			$("#searchBox_big_content").attr("placeholder","请输入要查询的内容");
	   }else if(module=="customs"){
		   $("#searchBox_big_content").attr("placeholder","请输入企业名称或组织机构代码或社会信用代码.如,信和汇诚信用管理(北京)有限公司");
	   }else if(module=="zjsfgkw"){
		    $("#searchBox_big_content").attr("placeholder","请输入查询的内容.如,姓名：王刚");
	   }
	   
}
//搜索大框
function searchBox_Big(){

	  var searchKey=searchBoxCheckIsNull("searchBox_big_content");//检查搜索框是否为空
	  if(searchKey!="" && searchKey.indexOf("关键词不能为空")==-1){
	        $("#searchBox_small_content").val(searchKey);
	        $("#searchBox_big").parent().remove();
     }
     return searchKey;
}
//搜索小框
function searchBox_small(){
	   return searchBoxCheckIsNull("searchBox_small_content");//检查搜索框是否为空
}

/*人物替代图片*/
function personImgSubstitute(img){
	
	   $(img).attr("src",ROOTPATH+"/static/imgs/modules/person-substitute.jpg")
	
	    /*var img=event.srcElement;
        img.src=ROOTPATH+"/static/imgs/modules/person-substitute.jpg";      
        img.onerror=null;*/
}
/* 性别*/
function personGender(gender){
	    
	     if(gender=="男" || gender=="他"){
	    	     return "男"
	     }else if(gender=="女" || gender=="她"){
	    	     return "女";
	     }else{
	    	     return "性别未知";
	     }
}

/*左侧菜单导航*/
function  leftMenu(){
	      
	    var url=document.location.toString();
        var style1="background:#185F7D;color:#ffffff";
	    var style2="background:#f5f5f5;";
        
        
        
        var  url_array=url.split("/");
        var  url_id=url_array[url_array.length-1];
        
        
        if(url_id=="personSearch"){
        	$("#person_menu_search a").attr("style",style1);
        }else if(url_id=="sinaSearch" || url_id=="sinaweibo"){
        	$("#sina_menu a:first").click();
        	$("#sina_menu_personSearch a").attr("style",style2);
        }else if(url_id=="sina_personList"){
        	//   新浪微博用户已关注列表
        }else if(url_id=="baiduSearch" || url_id=='baidubaike'){
        	 $("#baidu_menu a:first").click();
        	 $("#baidu_menu_personSearch a").attr("style",style2);
        }else if(url_id=="zhihuSearch" ||  url_id=="zhihu"){
        	 $("#zhihu_menu a:first").click();
       	     $("#zhihu_menu_personSearch a").attr("style",style2);
        }else if(url_id=="weixinSearch" || url_id=="weixin"){
        	 $("#weixin_menu a:first").click();
      	     $("#weixin_menu_personSearch a").attr("style",style2);
        }else if(url_id=="linkedInSearch" || url_id=="linkedIn"){
        	 $("#linkedIn_menu a:first").click();
     	     $("#linkedIn_menu_personSearch a").attr("style",style2);
        }else if(url_id=="taobaoSearch" || url_id=="taobao"){
        	 $("#taobao_menu a:first").click();
    	     $("#taobao_menu_personSearch a").attr("style",style2);
        }else if(url_id=="youkuSearch" || url_id=="youku"){
        	 $("#youku_menu a:first").click();
   	         $("#youku_menu_personSearch a").attr("style",style2);
        }else if(url_id=="youtubeSearch" || url_id=="youtube"){
        	 $("#youtube_menu a:first").click();
  	         $("#youtube_menu_personSearch a").attr("style",style2);
        }else if(url_id=="facebookSearch" || url_id=="facebook"){
        	 $("#facebook_menu a:first").click();
 	         $("#facebook_menu_personSearch a").attr("style",style2);
        }else if(url_id=="pbccrc" || url_id=="pbccrcAuth") {
        	$("#pbccrc_menu a:first").click();
        	$("#pbccrc_menu_pbccrcAuth a").attr("style",style2);
        }else if(url_id=="creditchinaSearch"){
       	 	$("#creditchina_menu a:first").click();
       	 	$("#creditchina_menu_getResult a").attr("style",style2);
        }else if(url_id=="iautosSearch"){
       	 	$("#iautos_menu a:first").click();
       	 	$("#iautos_menu_getResult a").attr("style",style2);
        }else if(url_id=="getSearchPage"){
       	 	$("#customs_menu a:first").click();
       	 	$("#customs_menu_getResult a").attr("style",style2);
        }else if(url_id=="sipoSearch"){
       	 	$("#sipo_menu a:first").click();
       	 	$("#sipo_menu_getResult a").attr("style",style2);
        }else if(url_id=="getSearchPage"){
       	 	$("#cnca_menu a:first").click();
       	 	$("#cnca_menu_getResult a").attr("style",style2);
        }
	
}

//右侧搜索小图标滑动
function rightSearchIcon(searchIconDivID){
	 
	//单击“小搜索”图标，滑动搜索框
	   var width= $("#"+searchIconDivID+" div:first-child").children().eq(0).css("width");
		   
	   if($("#smallSearchIcon").parent().parent().css("margin-left")!="0px"){
			 $("#smallSearchIcon").parent().parent().attr("style","margin-left:0px");  
	   }
		   
	   $("#searchBox_small").toggle("slide",{direction:"right"},2000,function(){
			  if($("#searchBox_small").css("display")=="none" && $("#read_detail").css("display")=="none"){
			          $("#smallSearchIcon").parent().parent().attr("style","margin-left:"+width);       
			  }
			  
			  if($("#searchBox_small").css("display")=="none"){
				     if($("#read_detail").prev().is("br")){
				          $("#read_detail").prev().remove();
				     }    
			  }
	   });
}
/*右侧小图标背景色*/
function rightIconBackgroundColor(rightIcoDivID){
	
	   //鼠标移动移到小图标上，变色
	   $("#"+rightIcoDivID).find(".sr-h-rightIconDiv").mouseenter(function(){
		        $(this).css("background-color","#ffffff");
	   })

	   //鼠标移开小图标，恢复原色
	   $("#"+rightIcoDivID).find(".sr-h-rightIconDiv").mouseleave(function(){
		        $(this).css("background-color","#f5f5f5");
	   })
	  
}
/*右侧图标Div固定定位*/
function rightLogoFixedPosition(rightLogoID){
	
	   $(window).scroll(function(){
		   
		         var nav_height_px=$("body").children().eq(0).css("height");
		         var nav_height=nav_height_px.substring(0,nav_height_px.length-2);   //nav导航栏的高度
		         
		         var rightIcon_offsetTop=$("#"+rightLogoID).offset().top;            //右侧图标偏离页面顶部的高度
		         var d_value=parseInt(rightIcon_offsetTop)-parseInt(nav_height);     
		         var vtop=parseInt($(document).scrollTop());                       //滚动条的偏移量
		         
		         //如果(右侧图标偏离页面顶部的高度-nav导航栏的高度) < 文档高度-可视区域高度，退出
		         var d_value2=parseInt($(document.body).height())-parseInt($(window).height());
		         
                 if(d_value2<d_value){
                	   if($("#"+rightLogoID).children().css("position")=="fixed"){
	        	           $("#"+rightLogoID).children().removeAttr("style");
                       }
                	   return;
                 }else{		         
		               if(vtop>d_value){
		        	         $("#"+rightLogoID).children().attr("style","position:fixed;top:"+(parseInt(nav_height))+"px");
		               }else{
		        	         if($("#"+rightLogoID).children().css("position")=="fixed"){
		        	           $("#"+rightLogoID).children().removeAttr("style");
	                         }
		              }
                }
	   })
}
/*
 * 此方法用产生单个搜索结果样式，用于$.each中
 * 
 * index    序号
 * searchResultsIDInModule   各模块搜索结果的 ID,sina模块搜索结果中第一个名为sina_searchResults_0,以此类推  
 * searchResultsLength       各模块搜索结果的长度
 * moreID                    各模块显示“更多”的id
 * moreTitle                 各模块显示“更多”时的title
 * headImg                   人物头像
 * fromModuleID              来源于哪个模块的ID                
 * moduleIco                 模块图标
 * moduleName                模块名称
 * searchResult              搜索结果，指一个模块中一个结果
 * uniqueID                  搜索结果唯一标识
 * */
function  displayResults(index,searchResultsIDInModule,searchResultsLength,moreID,moreTitle,headImg,fromModuleID,moduleIco,moduleName,searchResult,uniqueID){
	
	    var display_1='';
	    var display_2='';
	    var display_3='';
	    var display='';
	    var hr="<hr class='col-md-10'>";
	    var clearFloat="<div style='clear:both'></div>";
	    
	    
	 if(index==0){
  	        
		    display_1="<div id='"+searchResultsIDInModule+index+"_"+$.md5(uniqueID)+"'>";
  	        
  	        if(searchResultsLength>1){
  		       
  	        	   display_3="<div id='"+moreID+"' class='col-md-offset-9 col-md-2 sr-h-more' title='"+moreTitle+"'>更多&gt;&gt;&gt;</div>"
  			                 +"</div>"
  			                 +"<hr class='col-md-10' style='margin-top:0px'>"
  			                 +clearFloat
                             +"</div>";
  	        }else{
  		           display_3="</div>"+hr+clearFloat+"</div>";
  	        }
     }else{
  	        display_1="<div id='"+searchResultsIDInModule+index+"_"+$.md5(uniqueID)+"' style='display:none'>";
  	        display_3="</div>"+hr+clearFloat+"</div>";
     }
   
     display_2="<div class='media' style='float:left'>"
                    +"<div class='media-left' style='text-align:center;padding-left:20px;'>"
                          +"<div>"+headImg+"</div>"
                          +"<div id='"+fromModuleID+index+"'><br>&nbsp;&nbsp;&nbsp;来自："
                                +"<div><img src='"+moduleIco+"' class='sr-h-logo18'>&nbsp;"+moduleName+"</div>"
                          +"</div>"
                   +"</div>"
                   +"<div class='media-body' style='padding-left:30px'>"
                          +searchResult 
                   +"</div>";
     
     return display_1+display_2+display_3;
   
}

/*
 * 对比详情     或     “综合查阅详情”
 * searchResultsALLID  包含全部搜索结果DIV的ID,personSearch为personSearchResults,sina为sina_searchResults，以此类推
 * searchKey  搜索关键词
 * contrastORLookDivID   对比详情     或     “综合查阅详情”框DIV的ID,personSearch为read_detail,其他（如,sina）为contrast_detail
 * numID        //记录对比或查阅框中人物数目DIV的ID
 */
function contrastORLookDetail(searchResultsALLID,searchKey,contrastORLookDivID,numID){
	
	    /* alert("common.js\n  searchResultsALLID:"+searchResultsALLID+"\n"
	    		             +"searchKey:"+searchKey+"\n"
	    		             +"contrastORLookDivID:"+contrastORLookDivID+"\n"
	    		             +"numID:"+numID);*/
	
	
	     $("#"+searchResultsALLID).find("i[id$='_joinDetail']").parent().click(function(){
	    	 
	    	       //最多加载4个
	    	       if($("#"+contrastORLookDivID+" ul").children().length>=4){
	    	    	        return;
	    	       }
	    	       
	    	        var idArray=$(this).children("i").attr("id").split("_");
	    	        var moduleName=idArray[0];  //模块名，如moduleName="sina"
	    	        var uniqueID=idArray[3];      
	    	        
	    	        contrastORLookDetailULLI(searchKey,moduleName,uniqueID,contrastORLookDivID,searchResultsALLID,numID);
	    	       
	     })
}
/*
 * "综合查阅详情"ul >li中内容样式
 * searchKey  搜索关键词
 * moduleName 模块名称,如,sina,zhihu等
 * uniqueID   sina的uid,zhihu的profile,weixin的openid/link,linkedIn的profile,baidu的link  的md5值
 * contrastORLookDivID  对比详情     或     “综合查阅详情”框DIV的ID,personSearch为read_detail,其他（如,sina）为contrast_detail
 * searchResultsALLID  包含全部搜索结果DIV的ID,personSearch为personSearchResults,sina为sina_searchResults，以此类推
 * numID        //记录对比或查阅框中人物数目DIV的ID
 */
function contrastORLookDetailULLI(searchKey,moduleName,uniqueID,contrastORLookDivID,searchResultsALLID,numID){	
	
	   var indexedDB_storeName=''; //数据仓库名
	   var indexedDB_indexName=''; //数据仓库索引名
	   var indexedDB_keyIndex='';  //数据仓库中每行的搜索字段名，相当于主键
	   var indexedDB_index='';    //数据仓库中每行具体的搜索名
	   
	   var menuOptionStyle='';      //正在进行哪个菜单项的操作,personSearch,sina,zhihu...
	   
	   if(moduleName=="weixin"){
		     console.log("加入综合查阅详情 为weixin");
	   }else{
		     indexedDB_storeName=moduleName+"SearchResultsTab";
		     indexedDB_indexName=moduleName+"Index";
		     indexedDB_keyIndex=moduleName+"_"+searchKey+"_"+uniqueID;
		   //  console.log("indexedDB_storeName:"+indexedDB_storeName+"  indexedDB_indexName: "+indexedDB_indexName+"  indexedDB_keyIndex:"+indexedDB_keyIndex);
	   }
	   
	   if(contrastORLookDivID=="read_detail"){
		     menuOptionStyle="personSearch";
	   }
	
	   indexedDB_index=SEARCHRESULTSDB.IDB.transaction(indexedDB_storeName).objectStore(indexedDB_storeName).index(indexedDB_indexName);
	   
	   indexedDB_index.get(indexedDB_keyIndex).onsuccess=function(e){
		         
		         var searchResult=e.target.result;
		         var personImg='';
		         var personName='';
		         var moduleImg='';
		         var linkedIn_profile='';
		         
		         if(moduleName=="sina"){
		        	   personName=searchResult.nickname;
		        	   personImg=searchResult.profile_image;
		        	   moduleImg=ROOTPATH+MODULE.SINA_IMG;
		         }else if(moduleName=="zhihu"){
		        	   personName=searchResult.name;
		        	   personImg=searchResult.profile_image;
		        	   moduleImg=ROOTPATH+MODULE.ZHIHU_IMG;
		         }else if(moduleName=="linkedIn"){
		        	   personName=searchResult.name;
		        	   personImg=searchResult.profile_img;
		        	   moduleImg=ROOTPATH+MODULE.LINKEDIN_IMG;
		        	   linkedIn_profile=searchResult.profile;
		         }else if(moduleName=="baidu"){
		        	   personName=searchResult.title;
		        	   personImg=ROOTPATH+MODULE.NullPERSON;
		        	   moduleImg=ROOTPATH+MODULE.BAIDU_IMG;
		         }
		         
		         
		          if(menuOptionStyle==''){  //应用在sina,zhihu菜单项页面
	        		   menuOptionStyle=moduleName;
	        	  }
		         
		         
		         if($("#"+contrastORLookDivID+" ul").children("#"+searchResult.personIndex+"_joinDetailed").length){  //检查“综合查阅 详情“列表中是否已存在该人物，>0为true,<0为false
		                 return
		         }
			  
		         var joinContent="<li id='"+searchResult.personIndex+"_joinDetailed' data-searchKey='"+encodeURI(encodeURI((menuOptionStyle+"_"+searchKey)))+"' class='list-group-item sr-h-contrastLi' onmouseenter='contrastORLookDetail_checked(this)' onmouseleave='contrastORLookDetail_unChecked(this)'>"
		                           +"<i class='glyphicon glyphicon-remove' style='float:right;cursor:pointer;display:none' onclick='clearContrastORLookSingleDetail(this,\""+numID+"\");'></i>"
		                           +"<div style='padding:10px 5px 5px'>"
		                                  +"<img src='"+personImg+"' class='img-circle' style='width:65px;height:65px;cursor:pointer' onclick='contrastORLookDetailFixedPosition(this,\""+moduleName+"_"+uniqueID+"\",\""+searchResultsALLID+"\")' onerror='personImgSubstitute(this)' data-name='"+linkedIn_profile+"'>"
		                           +"</div>"
		                           +"<div style='padding:0px 5px 5px'>"
		                                   +"<img src='"+moduleImg+"' class='sr-h-logo18'>"
		                                   +personName
		                           +"<div>"
		                    +"</li>";
		         $("#"+numID).html(parseInt($("#"+numID).text())+1);//综合查阅数量加1
		         $("#"+contrastORLookDivID+" ul").append(joinContent);
		       
	   }
	   
	   indexedDB_index.get(indexedDB_keyIndex).onerror=function(e){
		       console.log(moduleName+":getDataError");
		       return ;
	   }
}
/*
 * 结果定位
 * img    对比或综合查阅中的头像
 * moduleSearchResultUniqueID   单个结果的唯一标识,如在id=sina_searchResults_0_6c3739bc871990ae14b7ac9da7ece46a的div中,在页面和indexedDB中都可用的唯一标识为6c3739bc871990ae14b7ac9da7ece46a
 * searchResultsALLID  包含全部搜索结果DIV的ID,personSearch为personSearchResults,sina为sina_searchResults，以此类推
 * */
function contrastORLookDetailFixedPosition(img,moduleSearchResultUniqueID,searchResultsALLID){
	
	
    /*
     * 当前页的searchKey与li中人物不一样时，返回li的当前页
     * 如：li  中的人物是李开复,而左侧结果显示 div中是关雷军的搜索结果，那么结果显示div应重载关于李开复的搜索结果
     */
    
	var searchResult_data_searchKey=$("#"+searchResultsALLID).attr("data-searchKey");
    
	var li_data_searchKey=$(img).parent().parent().attr("data-searchKey");
    
    if(searchResult_data_searchKey!=li_data_searchKey){
   	      $("#"+searchResultsALLID).html(localStorage.getItem(li_data_searchKey));
   	      $("#"+searchResultsALLID).attr("data-searchKey",li_data_searchKey);
   	      if(searchResultsALLID=="personSearchResults"){
   	           personSearch_action_reload(decodeURI(decodeURI(li_data_searchKey)));
   	      }else if(searchResultsALLID=="sina_searchResults"){
   	    	   module_action_reload("sina",decodeURI(decodeURI(li_data_searchKey)).split("_")[1]);
   	      }else if(searchResultsALLID=="zhihu_searchResults"){
   	    	   module_action_reload("zhihu",decodeURI(decodeURI(li_data_searchKey)).split("_")[1]);
   	      }else if(searchResultsALLID=="linkedIn_searchResults"){
   	    	   module_action_reload("linkedIn",decodeURI(decodeURI(li_data_searchKey)).split("_")[1]);
   	      }else if(searchResultsALLID=="baidu_searchResults"){
   	    	   module_action_reload("baidu",decodeURI(decodeURI(li_data_searchKey)).split("_")[1]);
   	      }else if(searchResultsALLID=="creditchina_searchResults"){
   	    	   module_action_reload("creditchina",decodeURI(decodeURI(li_data_searchKey)).split("_")[1]);
   	      }
   	}
    

   //显示全部的搜索模块
    var  moduleName=moduleSearchResultUniqueID.split("_")[0];
    var  searchResultUniqueID=moduleSearchResultUniqueID.split("_")[1];
    var  searchResultID=$("div[id$="+searchResultUniqueID+"]").attr("id");
    

    if($("#smallSearchIcon_checkbox div:first-child").css("display")=="none"){
   	 $("#smallSearchIcon_checkbox").click();    
    }
    
    //显示相应搜索模块下的更多
    if($("#"+searchResultID).css("display")=="none"){
   	 $("#"+moduleName+"_more").click();
    }

    
    //id=personSearchResults 的div距页面的偏移量
    var offsetTop=$("#"+searchResultsALLID).offset().top;
    
    //id=searchResultID的div距页面的偏移量
    //var offsetTop_searchResult=$("#"+searchResultID+" div:first-child").children().eq(0).offset().top
    var offsetTop_searchResult=$("#"+searchResultID).offset().top;
    
    
    
    //差值即为滚动条应滚动的距离
    var d_value=offsetTop_searchResult-offsetTop;
    
    $("html,body").animate({scrollTop:d_value},100);
}
/*删除"对比或综合查阅详情"框,一个*/
function clearContrastORLookSingleDetail(i,numID){
	
	     $(i).parent().remove();
         $("#"+numID).html(parseInt($("#"+numID).text())-1);
	
}
/*"对比综合查阅详情"框,一个，鼠标移上,处于选中状态*/
function contrastORLookDetail_checked(li){
	     $(li).attr("style","border:1px dashed #185F7D");
	     $(li).children("i").show();
	
}
/*"综合查阅详情"框,一个,鼠标移开，处于未选中状态*/
function contrastORLookDetail_unChecked(li){
	     $(li).removeAttr("style");
	     $(li).children("i").hide();
}
/*清空”综合查阅详情“框,全部*/
function clearAllContrastORLookDetail(span,numID,contrastORLookDIVName){
	     
	     var ul=$(span).parent().parent().children("ul");
	     if($(ul).children().length>0){
	    	    $(ul).html("");
	     }
	     $("#"+numID).html("0");
	     //setTimeout(function(){$("#"+contrastORLookDIVName).hide();},1000);
	     
}
/*查阅”综合查阅详情“框,全部*/
function lookAllContrastORLookDetail(span,moduleName){
	     
	     var ul=$(span).parent().parent().children("ul");
	     var li_id='';
	     var indexedDB_index=''; //本结果在indexedDB中的索引
	     var indexedDB_index_array='';
	     
	     if($(ul).children().length>1){
	    	 
	    	    for(var i=0;i<$(ul).children().length;i++){
	    	    	    indexedDB_index=$(ul).children().eq(i).attr("id").split("_joinDetailed")[0];
	    	    	    if(i==0){
	                          indexedDB_index_array=indexedDB_index;    	 
	    	    	    }else{
	    	    	    	  indexedDB_index_array+=","+indexedDB_index;
	    	    	    }
	    	    }
	    	    
	    	    localStorage.setItem(moduleName+"_joinPerson",indexedDB_index_array);
	    	    
	    	    if(moduleName=="sina"){
	    	    	  window.open(ROOTPATH+MODULEDETAIL.SINA_DETAIL);
	    	    }else if(moduleName=="zhihu"){
	    	    	  window.open(ROOTPATH+MODULEDETAIL.ZHIHU_DETAIL);
	    	    }
	     }
}
/*模块动作重载 sina.zhihu,weixin,linkedIn,baidu*/
function  module_action_reload(moduleName,searchKey){
		
	   $("#"+moduleName+"_searchResults").attr("data-searchkey",encodeURI(encodeURI((moduleName+"_"+searchKey))));
	   localStorage.setItem(encodeURI(encodeURI((moduleName+"_"+searchKey))),$("#"+moduleName+"_searchResults").html());
	       
	   rightLogoFixedPosition(moduleName+"_searchResultsRightIcon");
	   rightIconBackgroundColor(moduleName+"_searchResultsRightIcon");
	   contrastORLookDetail(moduleName+"_searchResults",searchKey,"contrast_detail","contrastDetailed_num");
	
}
//左侧菜单项加载
function common_loadLeftMenu(){
	
	$.ajax({
	    url:$("#ctx").val()+"/tools/getSite",
	    async:true,
	    success:function(sites){
	    	   // alert(JSON.stringify(sites))
	    	    $.each(sites,function(index,site){
	    	    	
	    	    	        var site_href='',site_enabled='',site_name='';
	    	    	        var site_hrefArray=new Array();
	    	    	        
	    	    	        site_href=site.linkUrl;
	    	    	        site_enabled=site.isEnabled;
	    	    	        site_name=site.pyName;
	    	    	        
	    	    	        site_hrefArray=site_href.split("/");
	    	    	       
	    	    	        for(var i=1;i<$("#left_menu").children("li").length;i++){
		    	    	    	       
		    	    	    	    var li='',li_ul='',li_href='';
		    	    	    	         
		    	    	    	    li=$("#left_menu").children("li").eq(i);
		    	    	    	    li_ul=$(li).children("ul");
		    	    	    	    li_href=$(li_ul).children("li").eq(0).children("a").attr("href");
		    	    	    	    
		    	    	    	    
		    	    	    	    if(li_href.indexOf(site_hrefArray[1]+"/"+site_hrefArray[2])!=-1){
		    	    	    	    	  
		    	    	    	          $(li).attr("data-name","persist_menu");
		    	    	    	          break;
		    	    	    	    }
		    	    	     }
	    	    	
	    	    })
	    	 
	    	     $("#left_menu").children("li[data-name!='persist_menu']").remove();
	    	     $("#left_menu").children("li[data-temp='persist_menu']").removeAttr("data-temp");
	    	     $("#left_menu").show();
	    	   
	    },
	    error:function(){
	    	alert("服务器断开\n方法名：common_loadLeftMenu/common.js");
	    	
	    }
 })     
}
//应用界面，输入的内容，创建新任务
function common_createNewTask(infoID,ownerTaskResult,type,keyword,name){
	
	     var type1='';
	     if(type==''){
	    	  type1='';
	     }else{
	    	  type1=type+'---';
	     }
	
	    if(ownerTaskResult=="isError" || ownerTaskResult==''){
    	 
            $("#"+infoID+" >div").text("与服务器断开，请稍候再试!");
            $("#"+infoID+" >div").attr("style","color:red");
            $("#"+infoID+"").show();

        }else{

              if(ownerTaskResult.ownerTaskCode==0){
                   $("#"+infoID+" >div").text("创建新任务："+type1+keyword+" 失败!");
                   $("#"+infoID+" >div").attr("style","color:red");
                   $("#"+infoID+"").show();
             }else if(ownerTaskResult.ownerTaskCode==1){
                   $("#"+infoID+" >div").html("创建新任务："+type1+keyword+"   成功! <br> 点击'我的任务'---'"+name+"'查看.");
                   $("#"+infoID+" >div").attr("style","color:green");
                   $("#"+infoID+"").show();
             }else if(ownerTaskResult.ownerTaskCode==2){
       	           $("#"+infoID+" >div").html("任务："+type1+keyword+" 已存在! <br> 点击'我的任务'---'"+name+"'查看.");
       	           $("#"+infoID+" >div").attr("style","color:#eea236");
       	           $("#"+infoID+"").show();
              }
        }
}