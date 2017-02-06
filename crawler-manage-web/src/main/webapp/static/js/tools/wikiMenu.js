$(function(){
	
	//生成树形菜单
	wikiMenu_createMenu();
	
	//树形菜单插入页面名称
	wikiMenu_webName();
	
})
//生成树形菜单
function wikiMenu_createMenu(){
	var treeNodes='',sites='';
	var siteTypeKey_siteValue='',siteTypeKey_siteValue_Obj,existKey='';
	var firstNodes='',secondNodes='',thirdNodes='';
	var valueLength='',valueArray='';
	var value_id='',value_name='';
	var thirdNodesList='';
	
	if(treeNodes==''){
	        sites=wikiAjax_getSite($("#ctx").val());
	        if(sites=='' || sites=="isError"){
		        return;
	        }
	
	       //拼成json对象形式
	       $.each(sites,function(index,site){
		          if(index==0){
		    	      siteTypeKey_siteValue='{"'+site.type+'类":"'+site.id+'_'+site.name+'"}';
		    	      siteTypeKey_siteValue_Obj=JSON.parse(siteTypeKey_siteValue);
		          }else{
		    	      existKey=0;
		    	      for(var key in siteTypeKey_siteValue_Obj){
		    		      if((site.type+"类")==key){
		    		    	  siteTypeKey_siteValue_Obj[key]=siteTypeKey_siteValue_Obj[key]+","+site.id+"_"+site.name;
		    		    	  existKey=1;
		    		      }
		    	       }
		    	      if(existKey==0){
		    		      siteTypeKey_siteValue_Obj[site.type+"类"]=site.id+"_"+site.name;
		    	      }
		          }
	        })
	
	        LoopNodes: for(var siteType in siteTypeKey_siteValue_Obj){
		
		                      firstNodes +='{"text":"'+siteType+'"';    //}'
		
	                          valueArray=siteTypeKey_siteValue_Obj[siteType].split(",");
	                          valueLength=valueArray.length;
	                   
	                          secondNodes='';
	                          if(valueLength>0){
	                    	          secondNodes=',"nodes":[';
	                    	          for(var i=0;i<valueLength;i++){
	                    	    	     
	                    	    	      value_id=valueArray[i].split("_")[0];
	                    	    	      value_name=valueArray[i].split("_")[1];
                                          
	                    	    	      secondNodes +='{"text":"'+value_name+'"';;
	                    	    	      
	                    	    	      thirdNodes='';
	                    	    	      
	                    	    	      thirdNodesList=wikiAjax_getSiteInterface($("#ctx").val(),"sonNodes",value_id);
	                    	    	      
	                    	    	      if(thirdNodesList=="isError"){
	                    	    	    	    firstNodes='';
	                    	    	    	    break LoopNodes;
	                    	    	      }
	                    	    	      if(thirdNodesList!=''){
	                    	    	            if(thirdNodesList.length>0){
	                    	    	    	        thirdNodes =',"nodes":[';
	                    	    	    	        $.each(thirdNodesList,function(index,thirdNodesObj){
	                    	    	    	    	             thirdNodes +='{'
	   	    		                                                             +'"text":"'+thirdNodesObj.title+'<span class=\'sr-only\'>'+siteType+'_'+value_id+'_'+thirdNodesObj.id+'</span>",'
	   	    		                                                             +'"icon":"'+thirdNodesObj.titleIcon+'",'
	   	    		                                                             +'"href":"#"'
	   	    		                                                            +'}';
	                    	    	    	    	             if(index<(thirdNodesList.length-1)){
	                    	    	    	    	            	   thirdNodes +=',';
	                    	    	    	    	             }else{
	                    	    	    	    	            	   thirdNodes +=']';
	                    	    	    	    	             }
	                    	    	    	       })
	                    	    	           }
	                    	    	       }
	                    	               secondNodes +=(i<(valueLength-1))?thirdNodes+'},':thirdNodes+'}]';
	                    	     }//for 2
	                         }
	                         firstNodes +=secondNodes+'},';
	                }//for 1

	
	         if(firstNodes==''){
		           alert("左侧菜单服务器异常. \n break LoopNodes;")
	         }
	
	         treeNodes ='['+firstNodes.substring(0,firstNodes.length-1)+']';
	 }
	 
	$("#treeview5").treeview({
			    	    	   levels:1,
			    		       color: "#185F7D",
			   			       selectedColor: "white",
			   			       selectedBackColor: "#428BCA",
			           	       enableLinks: true,
			   			       expandIcon: 'glyphicon glyphicon-chevron-right',
			   			       collapseIcon: 'glyphicon glyphicon-chevron-down',
			   			       nodeIcon: 'glyphicon glyphicon-bookmark',
			   			       showTags: true,
			   			       highlightSelected: true,
			    		       data:treeNodes
			    	   });
	
}
//树形菜单插入页面名称
function wikiMenu_webName(){
	
	   var currentURL=window.location.pathname;//页面路径
	   
	   if(/data\/tools\/wiki\/?#?$/.test(currentURL)){
		     wiki_menuClickAction();                    //wiki.js中的wiki_menuClickAction()方法生效
	   }else if(/data\/tools\/wiki\/edit\/?#?$/.test(currentURL)){
		   wikiAdd_updateApiByLeftMenu();                 //wikiadd.js中的wiki_menuClickAction()方法生效
	   }
}

