/*
 * wiki中全部的  localStorage
 * 
 *         KEY              VALUE                     注解
 *        whichEdit        社交类_5_5                 社交类下，站点id为5,接口id为5  进行修改更新(用于文档编辑)
 *        whichView        社交类_微信_搜索用户                     社交类下，站点为微信，接口为搜索用户 （在测试页面用于指定查询文档）  
 *        whichTest        5_1                        测试 站点为5，接口id为1的接口(在查询接口页面中指定测试接口用)
 *        wikiMenuChange   change                     新增api接口时，左侧菜单发生改变（用于新增api接口）
 *        wikiMenu         {左侧菜单内容}              用于存储wiki左侧菜单内容
 * */

$(function(){
	
	  //查看指定文档
	  wikiMenu_viewConcreteInterface();
})


//左侧树形菜单单击动作    由wikiMenu.js调用wikiMenu_webName()方法
function wiki_menuClickAction(){
	
	  $("#treeview5").on("nodeSelected",function(event,data){
		  
		     var realText='',realId='',contentInterface='',realId_parent1='',realId_parent2='';
		     var textArray='';
		     var siteTitle="<ul style='margin-left:3%'>",interfaceTitle="<ul style='margin-left:3%'>";
		     
		    // alert(JSON.stringify(event)+"\n*********\n"+JSON.stringify(data));
		     
		     if(data.text.indexOf("</span>")==-1){
		    	  if(data.text.indexOf("类")!=-1){
		    		   if(data.nodes!=undefined){
		    		          for(var i=0;i<data.nodes.length;i++){
		    			          siteTitle +="<li style='font-size:17px;margin-top:5px'>"+data.nodes[i].text+"</li>";
		    		          }
		    		          siteTitle +="</ul>";
		    		          $("#interface").html("<h4 style='font-weight:bold;margin-top:2%'>站点类型&nbsp;<span style='color:red'>"+data.text+"</span>&nbsp;包含站点:</h4><br>"+siteTitle);
		    		   }else{
		    			       $("#interface").html("<h4 style='font-weight:bold;margin-top:2%'>站点类型&nbsp;<span style='color:red'>"+data.text+"</span>&nbsp;下暂无站点。</h4>");
		    		   }
		    		   
		    	  }else{
		    		  if(data.nodes!=undefined){
		    			  for(var i=0;i<data.nodes.length;i++){
			    			  interfaceTitle +="<li style='font-size:16px;margin-top:5px'>"+data.nodes[i].text+"</li>";
		    		      }
			    		  interfaceTitle +="</ul>";
			    		   $("#interface").html("<h4 style='font-weight;margin-top:2%'>站点 &nbsp;<span style='color:red'>"+data.text+"</span>&nbsp;包含接口:</h4><br>"+interfaceTitle);
		    		  }else{
		    			  $("#interface").html("<h4 style='font-weight:bold;margin-top:2%'>"
		    					                     +"站点 &nbsp;"
		    					                     +"<span style='color:red'>"
		    					                             +data.text
		    					                     +"</span>&nbsp;下暂无接口。"
		    					                     +"<a href='"+$("#ctx").val()+"/tools/wiki/edit'><i class='glyphicon glyphicon-edit' title='新增接口文档' style='cursor:pointer;font-size:15px'></i></a>"
		    					                 +"</h4>");
		    		  }
		    		  
		    	  }
		    	  
	        	 return;
	         }
		     
		     textArray=data.text.split("<span class='sr-only'>");
		     realText=textArray[0];
		     realId=textArray[1].split("</span>")[0].split("_")[2];
		     
		     var contentInterface=wikiAjax_getInterface($("#ctx").val(),realId);
		     
		     var tools="<div class='row'>"
	                        +"<div class='col-md-offset-9 col-md-2' style='font-size:18px'>"
                                   +"<i id='exportButton' class='glyphicon glyphicon-save' title='导出接口文档' style='cursor:pointer'></i>"
                                   +"<i id='cruButton' class='glyphicon glyphicon-edit' title='编辑接口文档' style='margin-left:10px;cursor:pointer'></i>"
                                   +"<i id='testButton' class='glyphicon glyphicon-wrench' title='测试接口' style='margin-left:10px;cursor:pointer'></i>"
	                        +"</div>"
	                   +"</div>";
		     
		     if(contentInterface=="isError" ||contentInterface==''){
		    	 $("#interface").html(tools+"暂无相关内容");
		     }else{
		    	 $("#interface").html(tools+contentInterface.content);
		     }
		     
		     $("#wikiDocBase_id").val(realId);//应用导出本接口pdf
		     
		     
		   //导出pdf文件
		     $("#exportButton").click(function(){
		    	  
		    	      //显示模态框
		 	          $("#exportModal").modal("show");
		           
		              //选择要导出的接口文档
		              wiki_exportOptionAciton();
		           
		  	          //加载站点类型和站点
		              wiki_loadSiteTypeAndSite();
		 	     
		     });
		     
		     //编辑当前接口
		     realId_parent1=textArray[1].split("</span>")[0].split("_")[1];
		     realId_parent2=textArray[1].split("</span>")[0].split("_")[0];
		     localStorage.setItem("whichEdit",realId_parent2+"_"+realId_parent1+"_"+realId);
		     $("#cruButton").click(function(){
		    	     window.location.href=$("#ctx").val()+"/tools/wiki/edit";
		     })
		     
		     //测试当前接口
		     localStorage.setItem("whichTest",realId_parent1+"_"+realId);
		     $("#testButton").click(function(){
		    	    window.location.href=$("#ctx").val()+"/tools/apitest";
		     })
	  })
}
//查看指定文档，用于测试接口apitest.jsp中“点击获取文档”
function wikiMenu_viewConcreteInterface(){
        
		var fixedPosition='',fixedPositionArray='';
		var siteType='',site='',interfaceDoc='';
		var liArray='',liSize='';
		var siteTypeMenu='',siteMenu='',interfaceDocMenu='';
		
		fixedPosition=localStorage.getItem("whichView");
		if(fixedPosition=="0" || fixedPosition==null){
			  return;
		}
		fixedPositionArray=fixedPosition.split("_");
		
		siteType=fixedPositionArray[0];
		site=fixedPositionArray[1];
		interfaceDoc=fixedPositionArray[2];
		
		liSize=$("#treeview5").children("ul").eq(0).children("li").length;
		for(var i=0;i<liSize;i++){
			    siteTypeMenu=$("#treeview5").children("ul").eq(0).children("li").eq(i);
			    if(siteType==$(siteTypeMenu).text()){
			    	     $(siteTypeMenu).children("span").eq(0).click();
			    	     break;
			    }
		}
		
		liSize=$("#treeview5").children("ul").eq(0).children("li").length;
		for(var i=0;i<liSize;i++){
			    siteMenu=$("#treeview5").children("ul").eq(0).children("li").eq(i);
			    if(site==$(siteMenu).text()){
			    	    $(siteMenu).children("span").eq(1).click();
		    	        break;
			    }
		}
		
		liSize=$("#treeview5").children("ul").eq(0).children("li").length;
		for(var i=0;i<liSize;i++){
			    interfaceDocMenu=$("#treeview5").children("ul").eq(0).children("li").eq(i);
			    
			    if(($(interfaceDocMenu).text()).indexOf(interfaceDoc)!=-1){
			    	    $(interfaceDocMenu).children("a").eq(0).click();
			    	    break;
			    }
		}
		
		localStorage.removeItem("whichView");
}
//单个/批量导出pdf
function wiki_exportPDF(){
	    
	       var exportResult='';      
	       var radioValue='';
	       var exportOptions='',exportOptions_sites='',exportOptions_siteTypes='',bulkExportType='';
	       var bulk='',bulk_siteType="bulk_siteType",bulk_site="bulk_site";
	       
	       
	       
	       radioValue=$("input[name='exportType']:checked").val();
	       
	       if(radioValue=="currentExport"){
	    	       wikiAjax_exportPDF($("#ctx").val(),$("#wikiDocBase_id").val(),"");
	       }else if(radioValue=="bulkExportWithBookmark"){
	    	       
	    	       if($("#exportSiteType").children("span").length>0){
	    	    	   bulk_siteType="";
	    	       }
	    	       for(var i=0;i<$("#exportSiteType").children("span").length;i++){
	    	    	       bulk_siteType +=$("#exportSiteType").children("span").eq(i).children("span").eq(0).attr("data-name");
          	               if(i<($("#exportSiteType").children("span").length-1)){
          	            	 bulk_siteType +="_";
          	               }
                   }   	    	   
	   
                 //  bulk +=",";
	    	       
	    	       if($("#exportSite").children("span").length>0){
	    	    	   bulk_site="";
	    	       }
                   for(var i=0;i<$("#exportSite").children("span").length;i++){
                	        bulk_site +=$("#exportSite").children("span").eq(i).children("span").eq(0).attr("data-name").split("_")[1];
          	                if(i<($("#exportSite").children("span").length-1)){
          	                	bulk_site +="_";
          	                }
                   }
	    	   
                   wikiAjax_exportPDF($("#ctx").val(),"",bulk_siteType+","+bulk_site);
	    	   
	       }
	       
	       $("#exportModal_close").click();
	       
}
//不同导出方式相应单击
function wiki_exportOptionAciton(){
	//清空已选导出接口项
	   $("#exportClear").click(function(){
		     $("#exportSiteType").html("");
		     $("#exportSite").html("");
	   })
	
	
	   //根据导出选项radio,显示/隐藏"导出接口"、"选择接口"的div
	   $("input[name='exportType']").change(function(){
		       var exportType=$("input[name='exportType']:checked").val();
	           if(exportType=="bulkExportWithBookmark"){
	        	      $("#exportInterface").show();
	        	      $("#chooseInterface").show();
	        	      $("#chooseInterface ul").children("li").eq(0).children("a").click();
	           }else if(exportType=="currentExport"){
	        	      $("#exportInterface").hide();
	        	      $("#chooseInterface").hide();
	        	      $("#exportClear").click();//清空已选导出接口
	           }
	   })
	
}
//模态框加载站点类型和站点
function wiki_loadSiteTypeAndSite(){
	    
	      var siteObjList='',siteTypes='',sites='',exportSiteTypes='',exportSites='';
	      var siteTypeArray=new Array();
	      var chooseSiteTypeObj='',chooseSiteObj='';
	      var site_type='',checkSiteTypeObj='';
	      var checkSiteObj='',checkSite='';
	      
	      
	      
	      
	     //加载站点类型
	     siteObjList=wikiAjax_getSite($("#ctx").val());
	     if(siteObjList=="isError" || siteObjList==''){
	    	   return;
	     }
	     
	     $.each(siteObjList,function(index,siteObj){
		        if($.inArray(siteObj.type,siteTypeArray)==-1){
		        	siteTypeArray.push(siteObj.type)
	    	        siteTypes +="<div class='col-md-3' style='text-align:center'>"
                                   +"<span style='cursor:pointer;color:green' data-name='"+siteObj.type+"' title='选择本类全部站点'>"+siteObj.type+"类</span>"
                                   +"<i class='glyphicon glyphicon-chevron-right' style='font-size:10px;opacity:0.3;cursor:pointer' data-name='"+siteObj.type+"' title='显示本类全部站点'></i>"
                                 +"</div>";
		        }else{
		        	 return true;
		        }
	     })
	     $("#chooseSiteType").html(siteTypes);
	     
	     
	   //选择导出单元为站点类型
	     for(var i=0;i<$("#chooseSiteType").children("div").length;i++){
	    	      
	    	       chooseSiteTypeObj=$("#chooseSiteType").children("div").eq(i);
	    	      
	    	       $(chooseSiteTypeObj).children("span").eq(0).click(function(){
	    	    	        
	    	    	        //不能再选同一站点类型 
	    	    	        if( $("#exportSiteType").text().indexOf($(this).text())!=-1){   //不重复选择
	 	    	    	            return;
	 	    	            }
	    	    	        
	    	    	        //如果已选择的导出接口中有该站点类型下的站点，那么选择该站点类型后，原已选站点删除
	    	    	        checkSite='';
	    	    	        for(var j=0;j<$("#exportSite").children("span").length;j++){
	    	    	        	        checkSiteObj=$("#exportSite").children("span").eq(j);
	    	    	        	        if($(this).attr("data-name")!=$(checkSiteObj).children("span").eq(0).attr("data-name").split("_")[0]){
	    	    	        	        	  checkSite +="<span style='padding-right:10px'>"
	    	    	        	        		                 +$(checkSiteObj).html()
	    	    	        	        			      +"</span>"; 
	    	    	        	        }
	    	    	        }
	    	    	        $("#exportSite").html(checkSite);
	    	    	        
	    	    	        
	    	    	        exportSiteTypes="<span style='padding-right:10px'>"
                                                 +"<span style='color:red' data-name='"+$(this).attr("data-name")+"'>"+$(this).text()+"</span>"
                                                 +"<span class='glyphicon glyphicon-remove' style='font-size:10px;opacity:0.3;cursor:pointer' title='清除' onClick='this.parentNode.remove()'></span>"
                                            +"</span>";  
	    	    	        $("#exportSiteType").append(exportSiteTypes);
	    	      })
	     }
	     
	     
	     
	     
	     //加载站点
	     $("#chooseSiteType .col-md-3").children("i").click(function(){
	    	         var dataName=$(this).attr("data-name");
	    	         sites='';
	    	        $.each(siteObjList,function(index,siteObj){
	    	        	  if(siteObj.type==dataName){
	    	        	        sites+="<div class='col-md-3' style='text-align:center'>"
                                           +"<span style='cursor:pointer;color:green' data-name='"+siteObj.type+"_"+siteObj.name+"'>"+siteObj.name+"</span>"
                                       +"</div>";   
	    	        	  }
	    	        })
	    	        $("#chooseSite").html(sites);
	    	        
	    	        $("#chooseInterface ul").children("li").eq(1).children("a").click();
	    	        
	    	        
	    	        //选择导出单元为站点,必须包含在“加载站点”事件中，否则不触发。$("#chooseSite").children("div").length=0，
	    		      for(var i=0;i<$("#chooseSite").children("div").length;i++){
	    		    	      
	    		    	       chooseSiteObj=$("#chooseSite").children("div").eq(i);
	    		    	       
	    		    	       $(chooseSiteObj).children("span").eq(0).click(function(){
	    		    	             
	    		    	    	         //不能再选同一站点
	    		    	                 if( $("#exportSite").text().indexOf($(this).text())!=-1){   //不重复选择
	    		    	                          return;
	    		                         }
	    		    	  
	    		    	                 //如果该站点所属的类型已选，则选择该站点时，所选站点类型删除
	    		    	                 site_type=$(this).attr("data-name").split("_")[0];
	    		    	                 for(var j=0;j<$("#exportSiteType").children("span").length;j++){
	    		    	            	          checkSiteTypeObj=$("#exportSiteType").children("span").eq(j);
	    		    	            	          if($(checkSiteTypeObj).children("span").eq(0).attr("data-name")==site_type){
	    		    	            	        	    $(checkSiteTypeObj).remove();    
	    		    	            	          }
	    		    	                 }
	    		    	             
	    		    	                 exportSites="<span style='padding-right:10px'>"
	    	                                               +"<span style='color:#337ab7' data-name='"+$(this).attr("data-name")+"'>"+$(this).text()+"</span>"
	    	                                               +"<span class='glyphicon glyphicon-remove' style='font-size:10px;opacity:0.3;cursor:pointer' title='清除' onClick='this.parentNode.remove()'></span>"
	    	                                          +"</span>";
	    		    	  
	    		    	                 $("#exportSite").append(exportSites);
	    		    	       })
	    		      }
	    	        
	    	        
	     })//加载站点结束
}

