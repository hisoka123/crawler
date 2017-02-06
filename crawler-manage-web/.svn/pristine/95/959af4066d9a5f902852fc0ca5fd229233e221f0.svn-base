$(function(){
	
	searchBoxPlaceholder("gsxt");
	
	
	//搜索框样式
	gsxtCommon_searchBoxStyle();
	
	//搜索框聚焦
	$("#searchBox_big_content").focus(function(){
		$("#info >div").text("");
		$("#info").hide();
	})
	
	
	//判断环境
	var env=$.trim($("#env").val());
	
	if(env=="prod"){

		  $("#chooseDataSource").remove();

		  $("#searchBox_big_btn").on("click",function(){
			 gsxtCommon_detailFromDB();
		  })
	}else{
		 $("#chooseDataSource").show();
		 gsxtCommon_dataSource();
    }
	
})

//搜索框样式
function gsxtCommon_searchBoxStyle(){

	
	    var areas='';
	    //修正搜索框
	    $("#searchBox_big >div[class='input-group'] >#searchBox_big_content").before("<div class='input-group-btn'>"
	    		                                                                           +"<button type='button' class='btn btn-default dropdown-toggle active' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='height:40px'>"
	    		                                                                                 +"<span id='area' data-name='area'>选择地区 </span>"
	    		                                                                                 +"<span class='caret'></span>"
	    		                                                                           +"</button>"
	    		                                                                           +"<ul id='chooseArea' class='dropdown-menu' style='font-size:14px;padding-left:5px;padding-right:5px'>"
	    		                                                                           +"</ul>"
	    		                                                                     +"</div>");
	    
	    var areaArray=gsxtCommon_areaArray();
	    
	   for(var i  in areaArray){
		   
		       if(i.indexOf("China")!=-1){
		    	   
		    	       if(areas !=''){
		    	    	    areas +="</li>";
		    	       }
		    	       areas +="<li data-name='"+i+"' style='margin-top:10px;margin-bottom:10px;'>"
		    	    	            +"<span style='color:#337ab7;font-weight:bold'>"+areaArray[i]+"</span>";
		       }else{
		    	       areas +="<span data-name='"+i+"' style='margin-left:20px;padding:5px 15px;cursor:pointer'>"+areaArray[i]+"</span>";
		       }
	   }
	    
	   $("#chooseArea").html(areas+"</li>");
	   
	   //鼠标移入地区
	   $("#chooseArea").on("mouseenter","span[data-name]",function(){
		          $(this).attr("style",$(this).attr("style")+";background:#f1f1f1;border-radius:20px;-moz-border-radius:20px;-webkit-border-radius:20px");
	   })
	   
	   //鼠标移入地区
	   $("#chooseArea").on("mouseleave","span[data-name]",function(){
		          $(this).attr("style","margin-left:20px;padding:5px 15px;cursor:pointer");
	   })
	   
	   //选择地区
	   $("#chooseArea").on("click","span[data-name]",function(){
		    	     $("#area").text($(this).text());
				     $("#area").attr("data-name",$(this).attr("data-name"));
	   })

}  
//测试环境
function gsxtCommon_dataSource(){
	    
	    $("#searchBox_big_btn").on("click",function(){
	    	
	    	     var value='';
	    	     value=$("input[name='chooseDataSource']:checked").attr("value");
	    	
	    	     if(value=="interfaceVersion"){
	    	    	 gsxtCommon_detailFromInterface(); 
	    	     }else if(value=="dbVersion"){
	    	    	 gsxtCommon_detailFromDB();
	    	     }
	    	
	    })
	
}	   
//从接口处获取企业详情	    
function gsxtCommon_detailFromInterface(){
		   
		 var area='',area_temp='',keyword='',searchResult='',ctx='';
		           
		 ctx=$("#ctx").val();
		 area_temp=$("#area").attr("data-name");
		 area=area_temp.substr(0,1).toLowerCase()+area_temp.substr(1);
		 keyword=$.trim($("#searchBox_big_content").val());
		 
 
		 
		 if(area=="area"||keyword==''){
		       alert("地区选择或输入企业名称错误！");
		       return false;
		 }else{
			   
			   $("#info >div").text("努力查询，请稍候...");
			   $("#info >div").attr("style","color:red");
		       $("#info").show();
		       
		       
		       setTimeout(function(){
		        	    	 
		             searchResult=gsxtAjax_getDetailFromInterface(ctx,false,area,keyword);
		     
		             gsxtCommon_detailFromIN(ctx,searchResult);
			        	      
		          },1000);
		}
	
}
//从接口处异步显示结果
function gsxtCommon_detailFromIN(ctx,searchResult){
	
	     var detailName='';
	     
	     detailName="gsxtDetail";
	 
	     $("#info").hide();
	  
         if(JSON.parse(searchResult).data!=null){
        	 
               gsxtAjax_windowOpenPostDetail(ctx+"/modules/gsxt/gsxtDetail",detailName,JSON.stringify(JSON.parse(searchResult).data));
               
               //调试时用
               setTimeout(function(){
            	   gsxtAjax_windowOpenPostInterface(ctx+"/modules/gsxt/gsxtJson","data",JSON.stringify(JSON.parse(searchResult).data),"error","eNull");
                },2000);
               
         }else{
        	     
        	    if(JSON.parse(searchResult).error.errorCode==7){//无关键词
        	 
                      alert(JSON.parse(searchResult).error.errorName);
               
               }else{
                     if(searchResult.indexOf("errorName")!=-1 && /[\u4e00-\u9fa5]/.test(searchResult)){
   	                    alert("请求无效，可能原因：  "+JSON.parse(searchResult).error.errorName);
                     }else{
   	                    alert("服务器繁忙，请重试！");
                     }
               }
              
              //调试时用
              setTimeout(function(){
            	  gsxtAjax_windowOpenPostInterface(ctx+"/modules/gsxt/gsxtJson","data","dNull","error",JSON.stringify(JSON.parse(searchResult).error));
               },2000);
       }
   	      
   	      
}
//从数据库中获取企业详情
function gsxtCommon_detailFromDB(){
	     
	    var area='',keyword='',searchResult='',detailName='',ctx='',checkResult='';
	    var ownerTaskResult='',loginName='';
    
	   ctx=$("#ctx").val();
	   area=$("#area").attr("data-name");
	   keyword=$.trim($("#searchBox_big_content").val());
	   detailName="gsxtDetail";
	   loginName=$("#fat-menu").find("span[id='loginName']").text();
	           
	    
	  if(area=="area"||keyword==''){
	       alert("地区选择或输入企业名称错误！");
	       return false;
	  }else{
		   checkResult=gsxtAjax_getDetailFromDB(ctx,false,area,keyword);
		   
		   if(checkResult.existCode==0){   //existCode=0 && state=0
			   gsxtCommon_confirmJoinModule(ctx,area,keyword,loginName,checkResult.existCode,0);
		   }else{
			   
			      if(checkResult.state==7){  //existcode=1 && state=7
			    	  $("#info >div").text("没检索到关键字");
			    	  $("#info >div").attr("style","color:green");
	                  $("#info").show();
			      }else if(checkResult.state==1){ //existcode=1 && state=1
			    	   
			    	      gsxtAjax_windowOpenPostDetail(ctx+"/modules/gsxt/gsxtDetail",detailName,JSON.stringify(checkResult.aicFeedJson));
						      
			      }else{ //existcode=1 && state=0
			    	  gsxtCommon_confirmJoinModule(ctx,area,keyword,loginName,checkResult.existCode,checkResult.company_id);
			      }
			   
		  }
	  }
}
//创建新任务
function gsxtCommon_confirmJoinModule(ctx,area,keyword,loginName,existCode,companyID){
	
	     var ownerTaskResult='';
	     
 	     ownerTaskResult=gsxtOTAjax_gsxtJoinTask(ctx,false,area,keyword,loginName,existCode,companyID);
 	                 
 	     common_createNewTask("info",ownerTaskResult,gsxtCommon_areaArray()[area],keyword,"工商网");
	
}

//地区数组json
function gsxtCommon_areaArray(){
	
	    var areaArray={
	             "NorthChina":"华北", 	       "Beijing":"北京",      "Tianjin":"天津",      "Hebei":"河北",          "Shanxi":"山西",     "Neimenggu":"内蒙古",
	             "NorthEastChina":"东北",      "Liaoning":"辽宁",     "Jilin":"吉林",        "Heilongjiang":"黑龙江",
	             "EastChina":"华东", 	       "Shanghai":"上海",     "Jiangsu":"江苏",      "Zhejiang":"浙江",       "Anhui":"安徽",      "Fujian":"福建",        "Jiangxi":"江西",     "Shandong":"山东",
	             "SouthChina":"华南",   	       "Guangdong":"广东",    "Guangxi":"广西",      "Hainan":"海南",
	             "CentralChina":"华中", 	       "Henan":"河南",        "Hubei":"湖北",        "Hunan":"湖南",
	             "SouthWestChina":"西南",       "Chongqing":"重庆",   "Sichuan":"四川",      "Guizhou":"贵州",        "Yunnan":"云南",     "Xizang":"西藏",
	             "NorthWestChina":"西北 ", 	    "Shaanxi":"陕西",     "Gansu":"甘肃",        "Qinghai":"青海",        "Ningxia":"宁夏",    "Xinjiang":"新疆"              
	      }
	    
	    return areaArray;
}

