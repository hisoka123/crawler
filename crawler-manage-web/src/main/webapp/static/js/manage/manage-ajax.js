//获取全部站点包括可用、不可用
function manageAjax_getSitesAndSiteTypes(rootPath){
	
	     var getSiteTypeResult='';
	
	     $.ajax({
	    	 
	    	   url:rootPath+"/manage/getSiteType",
	    	   type:"get",
	    	   async:false,
	    	   success:function(data){
	    		   getSiteTypeResult=data;    		   
	    	   },
	    	   error:function(){
	    		     alert("服务器中断  manageAjax_getSiteType/manage-ajax.js");
	    		     getSiteTypeResult="isError";
	    	   }
	     })
	     
	     return getSiteTypeResult;
}
//保存新站点
function manmageAjax_addNewSite(rootPath,name,pyName,siteURL,type,isEnabled,linkUrl,siteLogo,siteInfo,ownerTaskUrl,id){
	
	    var addNewSiteResult='';
	    
        $.ajax({
   	 
   	         url:rootPath+"/manage/addNewSite",
   	         type:"post",
   	         async:false,
   	         data:{
   	        	name:name,
   	        	pyName:pyName,
   	        	siteURL:siteURL,
   	        	type:type,
   	        	isEnabled:isEnabled,
   	        	linkUrl:linkUrl,
   	        	siteLogo:siteLogo,
   	        	siteInfo:siteInfo,
   	        	ownerTaskUrl:ownerTaskUrl,
   	        	siteID:id,
   	        	time:new Date()
   	         },
   	         success:function(data){
   	        	addNewSiteResult=data;    		   
   	         },
   	         error:function(){
   		         alert("服务器中断  manmageAjax_addNewSite/manage-ajax.js");
   		         addNewSiteResult="isError";
   	         }
         })
    
       return addNewSiteResult;
	
	    
}