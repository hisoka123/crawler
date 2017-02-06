$(function(){
	    
	    var dbConnect= window.indexedDB.open(SEARCHRESULTSDB.NAME,SEARCHRESULTSDB.VERSION);
	    dbConnect.onsuccess=function(e){
	    	    SEARCHRESULTSDB.IDB=e.target.result;

	    	    var sina_contrastDetail='';  
	    	    var sina_contrastDetail_head='';
	    	    var sina_contrastDetail_body='';
	    	    
	    	    
	    	    var index='';
	    	    var thead='';
		        var tbody_gender='';
		        var tbody_location='';
		        var tbody_follow='';
		        var tbody_fans='';
		        var tbody_statuses='';
		        var tbody_v='';
		        var tbody_personCard='';
		        var tbody_personInfo='';
		        var tbody_personLabel='';
		        var tbody_personEdu='';
		        var tbody_personJob='';
		        
		        var isNull="<td>---</td>";
		        var sina_joinPersonArray=localStorage.getItem("sina_joinPerson").split(",");
		        var num=0;
		       
		        
		        for(var i=0;i<sina_joinPersonArray.length;i++){
		        	     index=SEARCHRESULTSDB.IDB.transaction("sinaSearchResultsTab").objectStore("sinaSearchResultsTab").index("sinaIndex");
		        	     index.get(sina_joinPersonArray[i]).onsuccess=function(e){
		        	    	        var result=e.target.result;
		        	    	        //console.log("sinaDetail:get data success!");
		        	    	      
		        	    	       thead+=sina_detail_thead(result);
		        	    	     
		        	    	       
		        	    	       //性别
		        	    	       if(personGender(result.gender)=="男"){
		        	    	    	    tbody_gender +="<td ><div title='男' class='sr-h-gender sr-h-gender-male' style='margin-left:45%'></div></td>";
		        	    		   }else if(personGender(result.gender)=="女"){
		        	    			    tbody_gender +="<td><div title='女' class='sr-h-gender sr-h-gender-female' style='margin-left:45%'></div></td>";
		        	    		   }else{
		        	    			    tbody_gender +=isNull; 
		        	    		   }
		        	    	      
		        	    	       //地区
		        	    	       if(result.location!=null && result.location!=''){
		        	    	    	     tbody_location +="<td>"+result.location+"</td>"; 
		        	    	       }else{
		        	    	    	     tbody_location +=isNull;
		        	    	       }
		        	    	       
		        	    	       //关注数
		        	    	       if(result.follow!=null && result.follow!=''){
		        	    	    	      tbody_follow +="<td><a href='"+result.follow_url+"' target='_blank'>"+result.follow+"</a></td>";
		        	    	       }else{
		        	    	    	      tbody_follow +=isNull;
		        	    	       }
		        	    	       
		        	    	       //粉丝数
		        	    	       if(result.fans!=null && result.fans!=''){
		        	    	    	      tbody_fans +="<td><a href='"+result.fans_url+"' target='_blank'>"+result.fans+"</a></td>";
		        	    	       }else{
		        	    	    	      tbody_fans +=isNull;
		        	    	       }
		        	    	       
		        	    	       //微博数
		        	    	       if(result.statuses!=null && result.statuses!=''){
		        	    	    	       tbody_statuses +="<td><a href='"+result.statuses_url+"' target='_blank'>"+result.statuses+"</a></td>";
		        	    	       }else{
		        	    	    	       tbody_statuses +=isNull;
		        	    	       }
		        	    	       
		        	    	       //认证机构
		        	    	       if(result.v!=null && result.v!=''){
		        	    	    	     tbody_v +="<td>"+result.v+"</td>";
		        	    	       }else{
		        	    	    	     tbody_v +=isNull;
		        	    	       }
		        	    	       
		        	    	       //个人名片
		        	    	       if(result.person_card!=null && result.person_card!=''){
		        	    	    	       tbody_personCard +="<td>"+result.person_card+"</td>";
		        	    	       }else{
		        	    	    	       tbody_personCard +=isNull;
		        	    	       }
		        	    	       
		        	    	       //个人简介
		        	    	       if(result.person_info!=null && result.person_info!=''){
		        	    	    	       var tempIndex=result.person_info.indexOf("简介");
		        	          	           if(tempIndex==0){
		        	          	    	        var person_info_temp=result.person_info.substring(3);
		        	          	           }else{
		        	          	    	         person_info_temp=result.person_info;
		        	          	           }
		        	    	    	       tbody_personInfo +="<td>"+person_info_temp+"</td>";
		        	    	       }else{
		        	    	    	       tbody_personInfo +=isNull;
		        	    	       }
		        	    	       
		        	    	       //个人标签
		        	    	       if(result.person_label!=null && result.person_label!=''){
		        	    	    	       tbody_personLabel +="<td>"+result.person_label+"</td>";
		        	    	       }else{
		        	    	    	       tbody_personLabel +=isNull;
		        	    	       }
		        	    	       
		        	    	       //教育信息
		        	    	       if(result.person_edu!=null && result.person_edu!=''){
		        	    	    	       tbody_personEdu +="<td>"+result.person_edu+"</td>";
		        	    	       }else{
		        	    	    	       tbody_personEdu +=isNull;
		        	    	       }
		        	    	       
		        	    	       //职业信息
		        	    	       if(result.person_job!=null && result.person_job!=''){
		        	    	    	        tbody_personJob +="<td>"+result.person_job+"</td>";
		        	    	       }else{
		        	    	    	        tbody_personJob +=isNull;
		        	    	       }
		        	    	       
		        	    	 
		                           if(num==(sina_joinPersonArray.length-1)){   
		        	    	       
		                        	         sina_contrastDetail="<table class='table table-bordered'>"
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
		        	    	    	                                        +"<tr data-name='location'>"
		        	    	    	                                              +"<th>地&nbsp;&nbsp;区</th>"
		        	    	    	                                              +tbody_location
		        	    	    	                                        +"</tr>"
		        	    	    	                                        +"<tr data-name='follow'>"
		        	    	    	                                              +"<th>关注数</th>"
		        	    	    	                                              +tbody_follow
		        	    	    	                                        +"</tr>"
		        	    	    	                                        +"<tr data-name='fans'>"
	        	    	    	                                                  +"<th>粉丝数</th>"
	        	    	    	                                                  +tbody_fans
	        	    	    	                                             +"</tr>"
	        	    	    	                                             +"<tr data-name='statuses'>"
	        	    	    	                                                   +"<th>微博数</th>"
	        	    	    	                                                   +tbody_statuses
	        	    	    	                                             +"</tr>"
	        	    	    	                                             +"<tr data-name='v'>"
	        	    	    	                                                   +"<th>微认证</th>"
	        	    	    	                                                   +tbody_v
	        	    	    	                                             +"</tr>"
	        	    	    	                                             +"<tr data-name='personCard'>"
      	    	    	                                                           +"<th>个人名片</th>"
      	    	    	                                                           +tbody_personCard
      	    	    	                                                     +"</tr>"
      	    	    	                                                     +"<tr data-name='personInfo'>"
	    	    	                                                               +"<th>个人简介</th>"
	    	    	                                                               +tbody_personInfo
	    	    	                                                         +"</tr>"
	    	    	                                                         +"<tr data-name='personLabel'>"
  	    	                                                                       +"<th>个人标签</th>"
  	    	                                                                       +tbody_personLabel
  	    	                                                                 +"</tr>"
  	    	                                                                 +"<tr data-name='personEdu'>"
  	                                                                               +"<th>教育信息</th>"
  	                                                                               +tbody_personEdu
  	                                                                         +"</tr>"
  	                                                                         +"<tr data-name='personJob'>"
                                                                                   +"<th>职业信息</th>"
                                                                                   +tbody_personJob
                                                                             +"</tr>"
      	    	    	                                              +"</tbody>"
		        	    	    	                              +"</table>";
		                        	         
		                        	         $("#sinaContrastDetail").html(sina_contrastDetail);
		                           }else{
		                        	         num ++;
		                           }
		        	     };
		        	     index.get(sina_joinPersonArray[i]).onerror=function(e){
		        	    	    console.log("sinaDetail: Get data failed from indexedDB.&& "+e.currentTarget.error.message);
		        	     }
		        }
	    };
	    dbConnect.onerror=function(e){
	    	   console.log("sinaDetail:Connect indexedDB Failed.  && "+e.currentTarget.error.message);
	    }
	    
})
function sina_detail_thead(result){
	
	   var thead_th='';
	   thead_th="<th class='col-md-2'>"
                    +"<div style='padding-top:5px;padding-bottom:10px'>"
                        +"<a href='"+result.profile+"' target='_blank' >"
                             +"<img class='img-circle sr-h-personImg' src='"+result.profile_image+"'  onerror='personImgSubstitute(this)'/>" 
                        +"</a>"
                    +"</div>"
                    +"<div class='sr-h-fontColor'>"
                        +"<img src='"+ROOTPATH+MODULE.SINA_IMG+"' class='sr-h-logo18' title='来自新浪微博'>"
                        +"&nbsp;&nbsp;"+result.nickname
                    +"</div>"
                 +"</th>";
	   
	   return thead_th;
}