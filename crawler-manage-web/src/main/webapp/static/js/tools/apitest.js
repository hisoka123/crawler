$(function(){
	
	  var initResult=apitest_apiCastAndApiName();
	  if(initResult==0){
		  return;
	  }
	  
	//从localstorage中测试指定接口
	  apitest_testConcrete();
	  
	  
	//调用编码窗口 ctrl+z
	  $(document).keydown(function(event){
		  
		         if(event.keyCode==90 && event.ctrlKey){
		        	     $("#toolsModel").modal("show");
		         }
	  })
	  
	  
	
})

//获取api分类/api名称，即站点名称/站点下接口
function  apitest_apiCastAndApiName(){
	     var apiCastObjList='',apiCastOptions='';
	     var apiNameObjList='',apiNameObjOptions='';
	     
	     
	     //获取api分类
	     apiCastObjList=wikiAjax_getSite($("#ctx").val());
	     if(apiCastObjList=="isError" || apiCastObjList==''){
	    	    return 0;
	     }
	     $.each(apiCastObjList,function(index,apiCastObj){
	    	    apiCastOptions+="<option name='"+apiCastObj.id+"' value='"+apiCastObj.type+"_"+apiCastObj.name+"'>"+apiCastObj.name+"</option>";
	     })

	     $("#api_cate").html(apiCastOptions);
	     $("#api_cate").change();
	     return 1;
}
//id=api_cate的change事件,产生具体站点下的全部接口
function apitest_createApiNames(select){
	
		//如果选择工商系统api，显示选择省市名称
		var apiName = $(select).val();
		
		if (apiName == "政法_工商网") {
			$("#api_city").css('display','block'); 
		} else {
			$("#api_city").css('display','none');
		}
	
	    var apiNameObjOptions='',apiNameObjList='';
	
        apiNameObjList=wikiAjax_getSiteInterface($("#ctx").val(),"sonNodes",$(select).children("option:selected").attr("name"));

     
        $("#params").html("");
     
        if(apiNameObjList=="isError" || apiNameObjList==''){
   	          $("#api_name").html(apiNameObjOptions);
        }

        $.each(apiNameObjList,function(index,apiNameObj){
   	          apiNameObjOptions +="<option name='"+apiNameObj.id+"' value='"+apiNameObj.title+"' data-method='"+apiNameObj.requestMethod+"' data-params='"+apiNameObj.params+"' data-path='"+apiNameObj.path+"'>"+apiNameObj.title+"</option>";
        })
        
        $("#api_name").html(apiNameObjOptions);
        $("#api_name").change();
}
//id=api_name的change事件,选中具体某个接口
function apitest_choseAPI(select){
	
	    var method='',paramsArray='';
	    var params='';
	    
	    if($("#api_name").children("option").length<1){
	    	 return;
	    }
	    method=$(select).children("option:selected").attr("data-method");
	    paramsArray=$(select).children("option:selected").attr("data-params").split(",");
	    
	    
	    if(method=="get"){
	    	  $("input[name='req_method']:eq(0)").attr("checked","checked");
	    }else if(method=="post"){
	    	  $("input[name='req_method']:eq(1)").attr("checked","checked");
	    }
	    
	    for(var i=0;i<paramsArray.length;i++){
	    	   params +="<div data-name='param_"+i+"'>"
	    	                +"<label>"
	    	                      +"<input type='text' name='key_"+i+"' class='param-key' placeholder='key' value='"+paramsArray[i]+"'>"
	    		                  +"<span style='color:#AAAAAA;'>&nbsp;→</span>"
	    		                  +"<input type='text' name='value_"+i+"' class='param-value' placeholder='value'>"
	                        +"</label>"
	    	                +"<i class='glyphicon glyphicon-remove' style='opacity:0.5;' onclick='apitest_removeParam(this);'></i>"
	                    +"</div>";
	    }
	    
	    $("#params").html(params);
}
//调用接口
function apitest_submit(button){
	   
	     var request='',requestParams='',param_key='',param_value='',keyValue='';
	     var currentURL='',requestURL='',requestMethod='',paramsArray='',ajaxParam='';
	     var responseResult='';
	     var interfaceName='',callTimeStamp='',logContent='',time='',logUid='',uid='';
	     
	     var ajaxParamObj=new Object();
	     
	     for(var i=0;i<$("#params").children("div").length;i++){
	    	   
	    	          param_key=$("#params").find("input[name='key_"+i+"']").val();
	    	          param_value=$("#params").find("input[name='value_"+i+"']").val();
	    	          
	    	         if(param_key!='' && param_value!=''){
	    	        	     requestParams +=param_key+"="+param_value+"&";
	    	         }
	     }
	     
	     currentURL=window.document.location.href;
	     
	     requestURL=currentURL.split("/tools/apitest")[0]+$("#api_name").children("option:selected").attr("data-path");
	     //如果是工商网，requestURL
	     if ($("#api_cate").val() == "政法_工商网") {
	    	 requestURL=currentURL.split("/tools/apitest")[0]+"/api/gsxt/"+$("#api_cityname").children("option:selected").val()+$("#api_name").children("option:selected").attr("data-path")
	     }
	     
	     requestMethod=$("input[name='req_method']:checked").val()
	     
	     request ="请求方式: "+requestMethod+"<br>"
	              +"请求URL: "+requestURL+"<br>"
	              +"请求参数: "+requestParams.substring(0,requestParams.length-1);
	
	     $("#req_content").html(request);
	     
	     paramsArray=requestParams.substring(0,requestParams.length-1).split("&");
	     for(var i=0;i<paramsArray.length;i++){
	    	        ajaxParamObj["key_"+i]=paramsArray[i].split("=")[0];
	    	        ajaxParamObj["value_"+i]=paramsArray[i].split("=")[1];
	     }
	     
	     ajaxParamObj.req_method=requestMethod;
	     ajaxParamObj.param_size=paramsArray.length;
	     ajaxParamObj.requestPath=requestURL;
	     
	     
	     $("#responseResult").html("<div class='sr-zql-loading'><img src='"+$("#ctx").val()+"/static/imgs/icon/loading.gif'>loading...</div>")
	    	 
	     
	     
	     /*setTimeout(function(){

	    	    responseResult=wikiAjax_apitest($("#ctx").val(),JSON.stringify(ajaxParamObj));
	    	 
		        $("#responseResult").html("<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap'>"
		    		                       +responseResult
		    		                   +"</pre>");
		        prettyPrint();
	     },1000);*/
	     
	     
	     
	     callTimeStamp=new Date().getTime();
	     
	     logUid=$.md5(callTimeStamp+""+Math.round(Math.random()*Math.pow(10,128)));
	     
         interfaceName=$("#api_cate").children("option:selected").attr("value").split("_")[1]+","+$("#api_name").children("option:selected").attr("value");
	     
         
         $("#log").attr("href",$("#ctx").val()+"/tools/apitest/log?logParam="+encodeURI(encodeURI(interfaceName+","+callTimeStamp+","+logUid)));
	     
         $("#log").parent().prev().show();
 	     $("#log").parent().show();
        
 	    
 	    window.open($("#ctx").val()+"/tools/apitest/log?logParam="+encodeURI(encodeURI(interfaceName+","+callTimeStamp+","+logUid)));
 	    
 	    setTimeout(function(){
 	    	
 	    	wikiAjax_apitest($("#ctx").val(),JSON.stringify(ajaxParamObj),logUid);
 	    },500);
 	    
 	    
         
         
}
//添加参数
function apitest_addParams(){
	
	     var addParams='',params_size='';
	     params_size=$("#params").children("div").length;
	     addParams ="<div data-name='param_"+params_size+"'>"
                      +"<label>"
                           +"<input type='text' name='key_"+params_size+"' class='param-key' placeholder='key'>"
	                       +"<span style='color:#AAAAAA;'>&nbsp;→</span>"
	                       +"<input type='text' name='value_"+params_size+"' class='param-value' placeholder='value'>"
                      +"</label>"
                      +"<i class='glyphicon glyphicon-remove' style='opacity:0.5;' onclick='apitest_removeParam(this);'></i>"
                  +"</div>";
	     $("#params").append(addParams);
}
//删除参数
function apitest_removeParam(i){
	
	     var this_name=$(i).parent().attr("data-name");
	     var this_order=this_name.split("_")[1];
	    
	     $(i).parent().remove();
	     
	     for(var i=0;i<$("#params").children("div").length;i++){
	    	      if(i>=this_order){
	    	    	     $("#params").children("div").eq(i).attr("data-name","param_"+i);
	    	    	     $("#params").children("div").eq(i).children("label").children("input").eq(0).attr("name","key_"+i);
	    	    	     $("#params").children("div").eq(i).children("label").children("input").eq(1).attr("name","value_"+i);
	    	      }
	     }
}
//获取相应api文档
function apitest_showAPI(){
	
	     var siteType='',site='',interfaceDoc='';
	     
	     siteType=$("#api_cate").children("option:selected").val().split("_")[0]+"类",
	     site=$("#api_cate").children("option:selected").val().split("_")[1];
	     interfaceDoc=$("#api_name").children("option:selected").val();
	     
	     localStorage.setItem("whichView",siteType+"_"+site+"_"+interfaceDoc);//用localStorage实现
	     
	     window.open($("#ctx").val()+"/tools/wiki");
}
//从localstorage中测试指定接口
function apitest_testConcrete(){
	
	     var whichTest='';
	     
	     whichTest=localStorage.getItem("whichTest");
	     if(whichTest==null ||whichTest==''){
	    	 return;
	     }else{
	    	 localStorage.removeItem("whichTest");
	     }
	     
	     
	     $("#api_cate").children("option[name='"+whichTest.split("_")[0]+"']").attr("selected","selected");
	     $("#api_cate").change();
	     
	     $("#api_name").children("option[name='"+whichTest.split("_")[1]+"']").attr("selected","selected");
	     $("#api_name").change();
	     
}
