$(function(){
	
	 var dbConnect= window.indexedDB.open(SEARCHRESULTSDB.NAME,SEARCHRESULTSDB.VERSION);
	 dbConnect.onsuccess=function(e){
	     //console.log("zhihuDetail:Connect indexedDB Success!");
		 SEARCHRESULTSDB.IDB=e.target.result;
		 
		 var index='';
		 var detailIndex='';
	     var zhihu_joinPersonArray=localStorage.getItem("zhihu_joinPerson").split(",");
	     
	     for(var i=0;i<zhihu_joinPersonArray.length;i++){
	    	 
    	         index=SEARCHRESULTSDB.IDB.transaction("zhihuSearchResultsTab").objectStore("zhihuSearchResultsTab").index("zhihuIndex");
    	         index.get(zhihu_joinPersonArray[i]).onsuccess=function(e1){
    	        	    var result=e1.target.result;
 	    	            console.log("sinaDetail:get data success!");
    	        	    
    	        	    detailIndex=SEARCHRESULTSDB.IDB.transaction("zhihuDetailsTab").objectStore("zhihuDetailsTab").index("zhihuIndex");
    	        	    detailIndex.get(zhihu_joinPersonArray[i]).onsuccess=function(e2){
    	        	             var detailsResult=e2.target.result;
    	        	             console.log("zhihuDetails:Get more details Success.");
    	        	    	      
    	        	    	     
    	        	             
    	        	    };
    	        	    detailIndex.get(zhihu_joinPersonArray[i]).onerror=function(e2){
                                 console.log("zhihuDetails:Get more details Failed. && "+e2.currentTarget.error.message);	        	    	      
	        	    	     
	        	             
	        	    };
    	        	 
    	        	 
    	         };
    	         index.get(zhihu_joinPersonArray[i]).onerror=function(e){
     	    	    console.log("zhihuDetail: Get data failed from indexedDB.&& "+e.currentTarget.error.message);
    	         }
	    	 
	    	 
	    	 
	    	 
	     }
		 
		 
		 
		 
	 };
	 
	 
	 dbConnect.onerror=function(e){
  	     console.log("zhihuDetail:Connect indexedDB Failed.  && "+e.currentTarget.error.message);
     }
})
/*
 * result  人物基本信息
 * detailsResult  人物详细信息
 * */
function zhihu_detail_style(result,detailResult){
	 var zhihu_contrastDetail="";
	 var thead='';
	 var tbody_gender='';
	 var tbody_answersNum='';
	 var tbody_postsNum='';
	 var tbody_followersNum='';
	 var tbody_bio='';
	 
	 var isNull="<td>---</td>";
     var num=0;
	 
	 
	thead +=zhihu_detail_thead(result);
	   
    //性别
    if(personGender(result.gender)=="男"){
           tbody_gender +="<td ><div title='男' class='sr-h-gender sr-h-gender-male' style='margin-left:45%'></div></td>";
    }else if(personGender(result.gender)=="女"){
           tbody_gender +="<td><div title='女' class='sr-h-gender sr-h-gender-female' style='margin-left:45%'></div></td>";
    }else{
           tbody_gender +=isNull; 
    }

    //回答数
    if(result.answers_num!=null && result.answers_num!=''){
           tbody_answersNum +="<td>"+result.answers_num+"</td>";
    }else{
           tbody_answersNum +=isNull;
    }

    //文章数
    if(result.posts_num!=null && result.posts_num!=''){
           tbody_postsNum+="<td>"+result.posts_num+"</td>";
    }else{
           tbody_postsNum+=isNull;
    }

    //关注者
    if(result.followers_num!=null && result.followers_num!=''){
           tbody_followersNum +="<td>"+result.followers_num+"</td>";
    }else{
           tbody_followersNum +=isNull;
    }

    //简介
    if(result.bio!=null && result.bio!=''){
           tbody_bio +="<td>"+result.bio+"</td>";
    }else{
           tbody_bio +=isNull;
    }


    if(num==(zhihu_joinPersonArray.length-1)){   

              zhihu_contrastDetail="<table class='table table-bordered'>"
                                      +"<thead>"
                                            +"<tr class='scrollColThead'>"
                                                 +"<th class='col-md-1'>*</th>"
                                                 +thead
                                            +"<tr>"
                                      +"</thead>"
                                      +"<tbody>"
                                             +"<tr data-name='gender'>"
                                                  +"<th>性&nbsp;&nbsp;别</th>"
                                                  +tbody_gender
                                             +"<tr>"
                                             +"<tr data-name='answersNum'>"
                                                    +"<th>回答数</th>"
                                                    +tbody_answersNum
                                               +"</tr>"
                                               +"<tr data-name='postsNum'>"
                                                     +"<th>文章数</th>"
                                                     +tbody_postsNum
                                               +"</tr>"
                                               +"<tr data-name='followersNum'>"
                                                   +"<th>关注者</th>"
                                                   +tbody_followersNum
                                             +"</tr>"
                                             +"<tr data-name='bio'>"
                                                   +"<th>个人简介</th>"
                                                   +tbody_bio
                                             +"</tr>"
                                      +"</tbody>"
                                  +"</table>";

      $("#zhihuContrastDetail").html(zhihu_contrastDetail);
}else{
      num ++;
}
	
	
	
	
	
}
function zhihu_detail_thead(result){
	
	   var thead_th='';
	   thead_th="<th class='col-md-2'>"
                    +"<div style='padding-top:5px;padding-bottom:10px'>"
                        +"<a href='"+result.profile+"' target='_blank' >"
                             +"<img class='img-circle sr-h-personImg' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)'/>" 
                        +"</a>"
                    +"</div>"
                    +"<div class='sr-h-fontColor'>"
                        +"<img src='"+ROOTPATH+MODULE.ZHIHU_IMG+"' class='sr-h-logo18' title='来自知乎社区'>"
                        +"&nbsp;&nbsp;"+result.name
                    +"</div>"
                 +"</th>";
	   
	   return thead_th;
}