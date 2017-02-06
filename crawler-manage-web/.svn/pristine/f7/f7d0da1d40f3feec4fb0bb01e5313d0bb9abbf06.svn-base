$(function(){
	 
	
	 //增加站点
	 $("#status").val("create");
	 siteManage_addSite();
	
	
	
	
	
	  //显示增加站点DIV
	  $("#add").click(function(){
		  
		      $("#addArea").show();
		      $("#editArea").hide();
		      
		      $("#status").val("create");
		      
		      $("#reset").click();
		      
		      $("#add").hide();
		      $("#edit").show();
	  })
	  
	  //显示编辑站点DIV
	  $("#edit").click(function(){
		     $("#addArea").hide();
	         $("#editArea").show();
	         siteManage_editSite();
	         
	         $("#add").show();
		      $("#edit").hide();
	  })
	 
	 
	  //保存新增站点
       $("#save").click(function(){
    	       siteManage_save();
       });	   
	
	  //重置新增站点
       $("#reset").click(function(){
    	       siteManage_reset();
       })
       
       //删除站点
       $("#delete").click(function(){
    	       siteManage_delete();
       })
	
       
       $("#edit").click();
})
//增加站点
function siteManage_addSite(){
	
	   var siteTypes='',options="<option value='请选择'>----- 请选择 -----</option>";
	   var siteTypesArray=new Array();
	
	    siteTypes=manageAjax_getSitesAndSiteTypes($("#ctx").val());
	    
	    if(siteTypes==null || siteTypes=="isError"){
	    	    return;
	    }
	  
        $.each(siteTypes,function(index,siteType){
        	  if($.inArray(siteType.type,siteTypesArray)==-1){
        		     siteTypesArray.push(siteType.type);
        	  }
        });	    
	    
        for(var i=0;i<siteTypesArray.length;i++){
        	  options +="<option value='"+siteTypesArray[i]+"'>----- "+siteTypesArray[i]+"类 -----</option>";     
        }	
	
	    options +="<option value='添加新站点类型'>---- 添加新站点类型 -----</option>";
	    
	    $("#type").html(options);
	    
	    //站点类型change事件
	    $("#type").change(function(){
	    	
	    	      if($("#type").children("option:selected").attr("value").indexOf("新站点类型")!=-1){
	    	    	  $("#typeOther").removeAttr("disabled");
	    	      }else{
	    	    	     $("#typeOther").val("");
	    	    	     $("#typeOther").attr("disabled","disabled");
	    	      }
	    })
	    
	    //站点图片change事件
	    $("#siteLogo").change(function(){
	    	
	    	     $("#siteLogoShow").attr("src",$("#ctx").val()+$("#siteLogo").val());
	    	
	    })
}
//重置新增站点
function siteManage_reset(){
	
	    $("#name").val("");
	    $("#pyName").val("");
	    $("#siteURL").val("");
	    $("#type").children("option[value='请选择']").attr("selected","selected");
	    $("#typeOther").val("");
	    $("#typeOther").attr("disabled","disabled");
	    $("input[name='isEnabled']:eq(0)").parent().html("<input type='radio' name='isEnabled' value='1' checked>可用"
                                                          +"<input type='radio' name='isEnabled' value='0' style='margin-left:10px'>不可用");
	    $("#linkUrl").val("");
	    $("#siteLogo").val("");
	    $("#siteLogoShow").attr("src",$("#ctx").val()+"/static/imgs/logo/zhengxin01.png");
	    $("#siteInfo").val("");
	
}
//保存/更新  站点
function siteManage_save(){
	
	     var name='',pyName='',siteURL='',typeValue='',type='',isEnabled='',linkUrl='',siteLogo='',siteInfo='',status='',ownerTaskUrl=''; 
	     var saveResult='';
	    
	    name=$("#name").val().trim();
	    /*if(!/^[\u4e00-\u9fa50-9a-zA-Z]+$/.test(name)){
	    	   alert("提示：站点名称只包含中文、数字、英文!")
	    	   return; 
	    }*/
	    if(name==""){
	    	   alert("提示：站点名称不能为空!");
	    	   return;
	    }
	    
	    pyName=$("#pyName").val().trim();
	    if(!/^[a-zA-Z0-9]+$/.test(pyName)){
	    	   alert("提示：站点英文名称只包含字母、数字!");
	    	   return
	    }
	    
	    siteURL=$("#siteURL").val().trim();
	    if(!/^(https?):\/\/[^\s]+$/.test(siteURL)){
	    	   alert("提示：原网地址格式错误!");
	    	   return;
	    }
	
	    typeValue=$("#type").children("option:selected").attr("value");
	    if(typeValue!='请选择' && typeValue!='添加新站点类型'){
	    	  type=typeValue;
	    }else{
	    	  type=$("#typeOther").val();
	    }
	    if(!/^[\u4e00-\u9fa5]+$/.test(type)){
	    	   alert("提示：站点类型只包含中文!")
	    	   return; 
	    }
	    
        isEnabled=$("input[name='isEnabled']:checked").attr("value");
        
        linkUrl=$("#linkUrl").val().trim();
        if(!/^(\/[a-zA-z0-9]+\/?)+$/.test(linkUrl)){
        	  alert("提示：站内地址只包括斜杠、字母、数字!");
        	  return;
        }
        
        ownerTaskUrl=$("#ownerTaskUrl").val().trim();
        if(!/^(\/[a-zA-z0-9]+\/?)+$/.test(ownerTaskUrl)){
      	     alert("提示：任务地址只包括斜杠、字母、数字!");
      	     return;
         }
        
        siteLogo=$("#siteLogo").val().trim();
        if(!/^(\/[a-zA-Z0-9]+\/?)+[a-zA-Z0-9]+\.[a-zA-Z]{3,4}$/.test(siteLogo)){
        	 alert("提示：站点图标地址只包含斜杠、字母、数字、点号");
        	 return;
        }
        
        siteInfo=$("#siteInfo").val().trim();
        /*if(!/^[\u4e00-\u9fa5]+$/.test(siteInfo)){
	    	   alert("提示：站点简介只包含中文!")
	    	   return; 
	    }*/
        if(siteInfo==''){
        	   alert("提示：站点简介不能为空");
        	   return;
        }
        
        
        status=$("#status").val();
        if(status=="create"){
        	  saveResult=manmageAjax_addNewSite($("#ctx").val(),name,pyName,siteURL,type,isEnabled,linkUrl,siteLogo,siteInfo,ownerTaskUrl);
            
              if(saveResult==null || saveResult=="" || saveResult=="isError"){
            	     alert("保存失败!");
              }else{
            	     alert(saveResult.message);
            	     if(saveResult.code==1){
            	    	   $("#reset").click();
            	     }
              }
              
        }else if(status.indexOf("update")!=-1){
        	  saveResult=manmageAjax_addNewSite($("#ctx").val(),name,pyName,siteURL,type,isEnabled,linkUrl,siteLogo,siteInfo,ownerTaskUrl,$("#status").val().split("_")[1]);
        	  
        	  if(saveResult==null || saveResult=="" || saveResult=="isError"){
         	       alert("更新失败!");
              }else{
         	       alert(saveResult.message);
         	       if(saveResult.code==1){
         	    	   $("#edit").click();
         	       }
              }
        	  
        }
        
        
        
        
}
//编辑站点：查阅、删除
function siteManage_editSite(){
	
	   var sitesObj='';
	   var sites='',siteAndSiteTypeList='',siteNum=0,color='';
	   var siteTypeArray=new Array();
	   
	   
	   sitesObj=manageAjax_getSitesAndSiteTypes($("#ctx").val());
	
	  
	   if(sitesObj==null || sitesObj=='isError'){
		       return;
	   }else if(sitesObj==''){
		       $("#editArea").html("<h4 style='color:red'>暂无站点信息.<h4>");
		       return;
	   }
	
	   $.each(sitesObj,function(index,siteObj){
		     if($.inArray(siteObj.type,siteTypeArray)==-1){
		    	   siteTypeArray.push(siteObj.type);
		     }
	   })
	
	   for(var i=0;i<siteTypeArray.length;i++){
		       siteAndSiteTypeList +="<div class='row' style='margin-top:10px'>"
                                          +"<div class='col-md-2 col-md-offset-3' style='font-size:18px'>"
                                                 +siteTypeArray[i]+"类"
                                          +"</div>"
                                          +"<div class='col-md-4 col-md-offset-1' style='font-size:18px'>";
		   
		       siteNum=0;
		       
		       for(var j=0;j<sitesObj.length;j++){
                       if(sitesObj[j].type==siteTypeArray[i]){
                    	       
                    	      if(sitesObj[j].isEnabled==0){
                    	    	   color="red";
                    	      }else if(sitesObj[j].isEnabled==1){
                    	    	   color="green";
                    	      }
                    	   
                    	      siteAndSiteTypeList +="<span title='单击编辑  "+sitesObj[j].name+" 站点' data-name='"+sitesObj[j].id+"' style='cursor:pointer;margin-right:5%;word-wrap:break-word;color:"+color+"' >"+sitesObj[j].name+"</span>"; 
                    	      siteNum++;
                       }		    	   
		       }
		       //<input type='checkbox' name='site'>   <input type='checkbox' name='site' style='margin-left:5%'>
		       
		       
		       siteAndSiteTypeList +="</div></div>";
		       
		       if(i<(siteTypeArray.length-1)){
		    	   siteAndSiteTypeList +="<hr style='color:black' class='col-md-8 col-md-offset-2'>";
		       }
	   }
	   
	   
	   $("#editAreaShow").html(siteAndSiteTypeList);
	   
	   
	   $("#editArea").on("click","span[title^='单击编辑']",function(){
		       
		      siteMange_update(this,sitesObj);
	   })
		  
}
//删除站点
function siteManage_delete(){
	     
	
	
	
	
}
//单击修改站点信息
function siteMange_update(span,sitesObj){
	
	      var id='';
	      
	      id=$(span).attr("data-name");
	
	      $("#add").click();
	      
	      $.each(sitesObj,function(index,siteObj){
	    	  
	    	        if(siteObj.id==id){
                           $("#name").val(siteObj.name);
                           $("#pyName").val(siteObj.pyName);
                           $("#siteURL").val(siteObj.siteURL);
                           $("#type").children("option[value='"+siteObj.type+"']").attr("selected","selected");
                           
                           if(siteObj.isEnabled==1){
                        	    $("input[name='isEnabled']:eq(0)").parent().html("<input type='radio' name='isEnabled' value='1' checked>可用"
                                                                                     +"<input type='radio' name='isEnabled' value='0' style='margin-left:10px'>不可用");
                           }else if(siteObj.isEnabled==0){
                        	    $("input[name='isEnabled']:eq(1)").parent().html("<input type='radio' name='isEnabled' value='1'>可用"
                                                                                     +"<input type='radio' name='isEnabled' value='0' style='margin-left:10px' checked>不可用");
                           }
	    	        	   $("#linkUrl").val(siteObj.linkUrl);
	    	        	   $("#siteLogo").val(siteObj.siteLogo);
	    	        	   $("#siteLogoShow").attr("src",$("#ctx").val()+siteObj.siteLogo);
	    	        	   $("#siteInfo").val(siteObj.siteInfo);
	    	        	   $("#ownerTaskUrl").val(siteObj.ownerTaskUrl);
	    	        	   
	    	        	   $("#status").val("update_"+siteObj.id);
	    	        	   return false;
	    	        }   
	      })
}

