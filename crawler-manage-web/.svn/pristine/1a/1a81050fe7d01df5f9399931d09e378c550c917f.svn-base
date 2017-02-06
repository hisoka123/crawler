$(function(){

	//加载富文本编辑器
	  loadSummerNote();
	
    //获取站点类型及站点信息
	   SITEINFOS=wikiAjax_getSite($("#ctx").val());//全局变量

   //加载站点类型
	   wikiadd_siteType();
	   
   //从只读状态进入，更新修改文档
	   wikiadd_updateApiFromOnlyRead();
	   
   //删除接口
	   wikiadd_deleteApi();
	  
})
//加载富文本编辑器
function loadSummerNote(){
	 $("#rte").summernote({
	     lang:'zh-CN',
	     minHeight:250,
         maxHeight:450,
 });
}
//加载站点类型
function wikiadd_siteType(){
	   
	   var siteTypeOption='';
	   var siteTypeArray=new Array();
	  
	   $.each(SITEINFOS,function(index,siteInfo){
		        var siteType=siteInfo.type+"类";
		        if($.inArray(siteType,siteTypeArray)==-1){
		        	siteTypeArray.push(siteType)
		        }
	  })
	  
	  for(var i=0;i<siteTypeArray.length;i++){
		    siteTypeOption +="<option value='"+siteTypeArray[i]+"'>-----&nbsp;"+siteTypeArray[i]+"&nbsp;-----</option>"     
	  }
	   $("#siteType").append(siteTypeOption);
}
//id=siteType change事件:加载站点
function wikiadd_siteTypeChange(select){
	
	     var siteType=$(select).children("option:selected").val();
	     
	     
	     
	     wikiAdd_resetInput(false,true,true,true,true);
	     if(siteType.indexOf("选择站点类型")!=-1){
	    	    return;
	     }
	     
	     var siteOption="<option name='00' value='选择站点'>-----&nbsp;选&nbsp;&nbsp;择&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;点&nbsp;-----</option>";
	     
	     for(var i=0;i<SITEINFOS.length;i++){
	    	 
	    	     if(SITEINFOS[i].type+"类"==siteType){
	    	    	    siteOption+="<option name='"+SITEINFOS[i].id+"' value='"+SITEINFOS[i].name+"'>-----&nbsp;"+SITEINFOS[i].name+"&nbsp;-----</option>"
	    	     }
	     }
	     
	     $("#site").html(siteOption);
	     
	     //去除Error信息
	     $("#siteTypeError").text("");
}
//id=site change事件：加载API接口、添加API接口
function wikiadd_siteChange(select){
	
	     var siteID='',apiDocTitleList='';
	     var apiDocTitleOption="<option name='00' value='选择API接口'>-----&nbsp;选择API接口&nbsp;-----</option>";
	
	     siteID=$(select).children("option:selected").attr("name");
	     
	     wikiAdd_resetInput(false,false,true,true,true);
	     if(siteID=="00"){
	    	   return;
	     }
	    
	     apiDocTitleList=wikiAjax_getSiteInterface($("#ctx").val(),"sonNodes",siteID);
	     
	     if(apiDocTitleList=="isError"){
	    	 return;
	     }
	     
	     for(var i=0;i<apiDocTitleList.length;i++){
	    	 apiDocTitleOption +="<option name='"+apiDocTitleList[i].id+"' value='"+apiDocTitleList[i].title+"'>-----&nbsp;"+apiDocTitleList[i].title+"&nbsp;-----</option>";
	     }
	     
	     apiDocTitleOption +="<option name='1x' value='添加API接口'>-----&nbsp;添加API接口&nbsp;-----</option>";
	     $("#apiDocTitle").html(apiDocTitleOption);
} 
//id=apiDocTitle change事件:新增或回显接口文档
function wikiadd_apiInterfaceChange(select){
	    
	     var name='',apiDocContent='',params='';
	
	     name=$(select).children("option:selected").attr("name");  
	     
	     wikiAdd_resetInput(false,false,false,true,true);
	     if(name=="00"){
	    	    $("#editStatus").val("none");
	    	    $("#deleteApi").hide();
	    	    return;
	     }else if(name=="1x"){  //新增api接口
	    	 
	    	    //显示参数div
	    	    $("#newApi").modal('show');
	    	    $("#editStatus").val("add");
	    	    $("#newApi_title").val("");
		        $("#newApi_name").val("");
		        $("#newApi_titleIcon").val("");
		        $("input[name='newApi_requestMethod']:eq(0)").attr("checked","checked");
		        $("#newApi_params").val("");
		        $("#newApi_path").val("");
		        
		        //隐藏删除api接口文档按钮
		        $("#deleteApi").hide();
		        
		        //加载api文档模板
		         var div=$("<div>");
		    	 div.attr("id","rteTemp");
		    	 div.attr("style","display:none");
		    	 $("body").append(div);
		    	 $("#rteTemp").load($("#ctx").val()+"/tools/addDemo",function(){
		    		 $("#rte").summernote("code",$("#rteTemp").html());
			    	 div.remove();
	                 
			    	 return;
		    	 });
		    	
	     }else{
	    	    $("#editStatus").val("update");
	    	    $("#deleteApi").show();
	    	    
	    	    //回显接口参数
	   	        params=wikiAjax_getSingleWikiDocBase($("#ctx").val(),name);
	   	        if(params=='isError'){
	   	    	    alert("服务中断，无法获取接口参数");
	   	        }else if(params!=null && params!=''){
	   	    	    wikiadd_echoParams(params.titleIcon,params.title,params.name,params.params,params.path,params.requestMethod);
	   	        }
	   	     
	   	     
	   	        //显示接口内容
	   	        apiDocContent=wikiAjax_getInterface($("#ctx").val(),name);
	   	        
	   	        
	   	        if(apiDocContent=="isError"){
	   	    	     return;
	   	        }else if(apiDocContent==null ||apiDocContent==''){
	       	         return;
	            }else{
	            	 $("#apiContentID").val(apiDocContent.id);
	           	     $("#rte").summernote('code',apiDocContent.content);
	            }
	     }
	    
	    
}
//页面输入框重置
function wikiAdd_resetInput(resetSiteType,resetSite,resetApiDocTitle,resetApiDocContent,resetNewApiTitle){
	    var siteType="<option name='00'>-----&nbsp;选择站点类型&nbsp;-----</option>";
	    var site="<option name='00'>-----&nbsp;选&nbsp;&nbsp;择&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;点&nbsp;-----</option>";
	    var apiDocTitle="<option name='00'>-----&nbsp;选择API接口&nbsp;-----</option>";
	    
	    
	    if(resetSiteType){
	    	$("#siteType").html(siteType);
	    }
	    
	    if(resetSite){
	    	$("#site").html(site);
	    }
	    
	    if(resetApiDocTitle){
	    	 $("#apiDocTitle").html(apiDocTitle);
	    }
	    
	    if(resetNewApiTitle){
	    	  //$("#resetNewApiTitle").html("");
	    	  $("#newApi_titleShow").html("");
              $("#newApi_nameShow").html("");
              $("#newApi_paramsShow").html("");
              $("#newApi_pathShow").html("");
              $("#newApi_requestMethodShow").html("");
		      $("#newApiShow").hide();

	    }

	    if(resetApiDocContent){
	    	 $("#rte").summernote("code","");
	    }
}
//填写接口参数确认
function wikiadd_paramsConfirm(button){
	
	    wikiadd_echoParams($("#newApi_titleIcon").val(),
	    		           $("#newApi_title").val(),
	    		           $("#newApi_name").val(),
	    		           $("#newApi_params").val(),
	    		           $("#newApi_path").val(),
	    		           $("input[name='newApi_requestMethod']:checked").val());
        $("#newApi_close").click();
	
	
}
//页面显示接口参数
function wikiadd_echoParams(titleIcon,title,name,params,path,requestMethod){
	  
	   $("#newApi_titleShow").html("<i class='"+titleIcon+"'></i>"
			                        +"<span style='margin-left:5px'>"+title+"</span>");
	   
	   $("#newApi_nameShow").html(name);
	   $("#newApi_paramsShow").html(params);
	   $("#newApi_pathShow").html(path);
	   $("#newApi_requestMethodShow").html(requestMethod);
	   
	   
	   $("#newApiShow").show();
	   
	   $("#newApi_edit").click(function(){
		        $("#newApi").modal('show');
		        $("#newApi_title").val($("#newApi_titleShow").text());
		        $("#newApi_name").val($("#newApi_nameShow").text());
		        $("#newApi_titleIcon").val($("#newApi_titleShow").children("i").attr("class"));
		        
		        if($("#newApi_requestMethodShow").text()==""||$("#newApi_requestMethodShow").text()==null){
		        	  $("input[name='newApi_requestMethod']:eq(0)").removeAttr("checked");
		        }else{
		        	 $("#newApi_requestMethod").find("input[value='"+$("#newApi_requestMethodShow").text()+"']").attr("checked","checked");
		        }
		        
		        $("#newApi_params").val($("#newApi_paramsShow").text());
		        $("#newApi_path").val($("#newApi_pathShow").text());
	   })
	   
	   $("#newApi_delete").click(function(){
		     wikiAdd_resetInput(false,false,false,false,true);
	   })
	
	   
}
//提交文档
function wikiadd_submitDocClick(button){
	
	      var checkArray='',checkResult='',rte_content='',docInDirID='',docInDirArray='',nodesGrade='';
	      var title='',name='',params='',path='',titleIcon='',requestMethod='get';
	      var editStatus='';
	      var saveORUpdateResult='';
	      
	      editStatus=$("#editStatus").val();
	      if(editStatus=="none"){
	    	  return;
	      }
	      
	      
	    
	      //验证必要项
	      checkArray=["siteType","site","apiDocTitle"];
	      checkResult='';
	      for(var i=0;i<checkArray.length;i++){
	    	   if($("#"+checkArray[i]).children("option:selected").attr("name")=="00"){
	    		    checkResult='0';//没有进行选择
	    		    alert("请选择站点类型、站点、接口!")
	    		    break;
	    	   }
	      }
	      
	     
	      //获取接口文档内容
	      rte_content=$("#rte").summernote('code');
	      if(!/[\u4e00-\u9f5a]/.test(rte_content)){
	    	     alert("文档内容非法!"); 
	    	     checkResult='0';
	      }
	      
	      //获取接口参数
	      title=$("#newApi_titleShow").text();
          name=$("#newApi_nameShow").text();
          params=$("#newApi_paramsShow").text();
          path=$("#newApi_pathShow").text();
          titleIcon=$("#newApi_titleShow").children("i").attr("class");
          requestMethod=$("#newApi_requestMethodShow").text();
          if(title=='' || name=='' || params=='' || path=='' || titleIcon=='' || requestMethod==''){
        	       alert("有关接口参数为空!");
        	       checkResult=0;
          }
	     
	      if(checkResult=='0'){
	    	    return;
	      }
	     
	      
	      if(editStatus=="add"){  //新增接口文档

	    	     saveORUpdateResult=wikiAjax_addInterfaceDoc($("#ctx").val(),
	    	    		                                     rte_content,title,name,params,path,titleIcon,requestMethod,
	    	    		                                     $("#site").children("option:selected").attr("name"));
	    	    		                                     
	    	     if(saveORUpdateResult=="isError"){
	    	    	    alert("保存失败!服务中断")
	    	     }else if(saveORUpdateResult.code==0){
	    	    	    alert("保存失败!");
	    	     }else if(saveORUpdateResult.code==1){
	    	    	    alert("保存成功!");
	    	    	    localStorage.setItem("whichView",$("#siteType").children("option:selected").attr("value")+"_"+$("#site").children("option:selected").attr("value")+"_"+title)
	    	    	    localStorage.setItem("wikiMenuChange","change");//左侧菜单发生变化
	    	    	    window.location.href=$("#ctx").val()+"/tools/wiki/";
	    	     }
	    	  
	      }else if(editStatus=="update"){ //更新接口文档
	    	  
	    	     saveORUpdateResult=wikiAjax_updateInterfaceDoc($("#ctx").val(),
	    	    		                                        $("#apiContentID").val(),
	    	    		                                        rte_content,
	    	    		                                        $("#apiDocTitle").children("option:selected").attr("name"),
	    	    		                                        title,name,params,path,titleIcon,requestMethod,
	    	    		                                        $("#site").children("option:selected").attr("name"));
	    	     if(saveORUpdateResult=="isError"){
	    	    	    alert("更新失败!服务中断")
	    	     }else if(saveORUpdateResult.code==0){
	    	    	    alert("更新失败!");
	    	     }else if(saveORUpdateResult.code==1){
	    	    	    alert("更新成功!");
	    	    	    localStorage.setItem("whichView",$("#siteType").children("option:selected").attr("value")+"_"+$("#site").children("option:selected").attr("value")+"_"+title)
	    	    	    
	    	    	    //如果修改了接口名
	    	    	    if($("#apiDocTitle").children("option:selected").attr("value")!=$("#newApi_titleShow").text()){
	    	    	    	localStorage.setItem("wikiMenuChange","change");//左侧菜单发生变化
	    	    	    }
	    	    	    
	    	    	    window.location.href=$("#ctx").val()+"/tools/wiki/";
	    	     }
	    	  
	      }
          
}
//根据左侧菜单，更新修改文档
function  wikiAdd_updateApiByLeftMenu(){
	
	    $("#treeview5").on("nodeSelected",function(event,data){
	    	
	    	            var dataText='';
	    	            var siteType='',siteID='',siteInterfaceID='';
	    	            
	    	            
	    	            dataText=data.text;
	    	            siteType=dataText.split(">")[1].split("<")[0].split("_")[0];
	    	            siteID=dataText.split(">")[1].split("<")[0].split("_")[1];
	    	            siteInterfaceID=dataText.split(">")[1].split("<")[0].split("_")[2];
	    	            
	    	            wikiAdd_updateApiCommon(siteType,siteID,siteInterfaceID);
	    	        
	    })
}
//从只读状态进入后，更新修改api文档
function  wikiadd_updateApiFromOnlyRead(){
	
	     var apiValue='';
	     var siteType='',siteID='',siteInterfaceID='';
	     
	     
	     apiValue=localStorage.getItem("whichEdit");
	     if(apiValue==null || apiValue==''){
	    	 return;
	     }else{
	    	 localStorage.removeItem("whichEdit");
	     }
	     
	     siteType=apiValue.split("_")[0];
	     siteID=apiValue.split("_")[1];
	     siteInterfaceID=apiValue.split("_")[2];
	     
	     wikiAdd_updateApiCommon(siteType,siteID,siteInterfaceID);
	
}
//更新文档公用方法
function wikiAdd_updateApiCommon(siteType,siteID,siteInterfaceID){
	
	     var apiContent='',params='';
	     
	     $("#siteType").children("option[value='"+siteType+"']").attr("selected","selected");
         $("#siteType").change();
     
         $("#site").children("option[name='"+siteID+"']").attr("selected","selected");
         $("#site").change();
     
         $("#apiDocTitle").children("option[name='"+siteInterfaceID+"']").attr("selected","selected");
         $("#apiDocTitle").change();
         
}
//删除接口
function wikiadd_deleteApi(){
	    $("#deleteApi").click(function(){
	    	     var name=$("#apiDocTitle").children("option:selected").attr("name");
	    	     var contentID=$("#apiContentID").val();
	    	     var result="";
	    	     var currentApi="";
	    	     if(name=="00" || name=="1x"){
	    	    	    return;  
	    	     }else{
	    	    	 currentApi=$("#site").children("option:selected").attr("value")+"__"
	    	    	            +$("#apiDocTitle").children("option:selected").attr("value");
	    	     }

	    	     if(confirm("确认删除当前接口:  "+currentApi)){
	    	    	 result=wikiAjax_deleteAPI($("#ctx").val(),contentID);
		    	     if(result=="isError" || result==""){
		    	    	   alert("服务器中断")
		    	    	   return;
		    	     }else{
		    	    	   alert(result.message);
		    	    	   if(result.code==1){
		    	    		   localStorage.setItem("wikiMenuChange","change");//左侧菜单发生变化
		    	    		   window.location.href=$("#ctx").val()+"/tools/wiki/edit/";  
		    	    	   }
		    	     }   
	    	     }
	    	
	    })
	
}
