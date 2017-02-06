$(function(){
	 
	 gsxtCommon_leftMenu();
	
	 //显示目录
	 $("#toc").on("click",function(){
		 
		   $("#leftMenu").show();
		   $("#toc").hide();
	 })
	 
	 //隐藏目录
	 $("#table").on("click",function(){
		 
		     if($("#leftMenu").css("display")!="none"){
		    	   $("#leftMenu").hide();
				    $("#toc").show();
		     }
	 })
	 
	
	//td内容为空
	gsxtCommon_tdIsNull();
	 
	 
   //各种信息详情
	 gsxtCommon_infoDetail();
	 
	//目录定位
	 gsxtCommon_fixed();
	 
	 
	
})

//左侧折叠菜单
function gsxtCommon_leftMenu(){
	
	     var menu='['
	    	         +'{'
	    	              +'"text":"工商公示信息<span class=\'sr-only\'>t1</span>",'
	    	              +'"nodes":['
	    	                           +'{'
	    	                               +'"text":"登记信息<span class=\'sr-only\'>t1_1</span>",'
	    	                               +'"nodes":['
	    	                                          +'{"text":"基本信息表<span class=\'sr-only\'>t1_1_1</span>"},'
	    	                                          +'{"text":"股东信息表<span class=\'sr-only\'>t1_1_2</span>"},'
	    	                                          +'{"text":"变更信息表<span class=\'sr-only\'>t1_1_3</span>"},'
	    	                                          +'{"text":"撤销信息表<span class=\'sr-only\'>t1_1_4</span>"}'
	    	                               +']'
	    	                           +'},'
	    	                           +'{'
	    	                                +'"text":"备案信息<span class=\'sr-only\'>t1_2</span>",'
	    	                                +'"nodes":['
	    	                                          +'{"text":"主要人员信息表<span class=\'sr-only\'>t1_2_1</span>"},'
	    	                                          +'{"text":"分支机构信息表<span class=\'sr-only\'>t1_2_2</span>"},'
	    	                                          +'{"text":"清算信息表<span class=\'sr-only\'>t1_2_3</span>"},'
	    	                                          +'{"text":"主管部门(出资人)信息表<span class=\'sr-only\'>t1_2_4</span>"}'
	    	                                +']'
	    	                           +'},'
	    	                           +'{'
	    	                                +'"text":"动产抵押登记信息<span class=\'sr-only\'>t1_3</span>"'
	    	                                /*+',"nodes":['
	    	                                          +'{"text":"动产抵押登记信息表<span class=\'sr-only\'>t1_3_1</span>"}'
	    	                                +']'*/
	    	                           +'},'
	    	                           +'{'
	    	                               +'"text":"股权出资登记信息<span class=\'sr-only\'>t1_4</span>"'
	    	                               /*+',"nodes":['
	    	                                          +'{"text":"股权出质登记信息表<span class=\'sr-only\'>t1_4_1</span>"}'
	    	                               +']'*/
	    	                           +'},'
	    	                           +'{'
	    	                               +'"text":"行政处罚信息<span class=\'sr-only\'>t1_5</span>"'
	    	                               /*+',"nodes":['
	    	                                          +'{"text":"行政处罚信息表<span class=\'sr-only\'>t1_5_1</span>"}'
	    	                               +']'*/
	    	                           +'},'
	    	                           +'{'
	    	                               +'"text":"经营异常信息<span class=\'sr-only\'>t1_6</span>"'
	    	                               /*+',"nodes":['
	    	                                          +'{"text":"经营异常信息表<span class=\'sr-only\'>t1_6_1</span>"}'
	    	                               +']'*/    
	    	                           +'},'
	    	                           +'{'
	    	                                +'"text":"严重违法信息<span class=\'sr-only\'>t1_7</span>"'
	    	                               /* +',"nodes":['
	    	                                           +'{"text":"严重违法信息表<span class=\'sr-only\'>t1_7_1</span>"}'
	    	                                +']'*/
	    	                           +'},'
	    	                           +'{'
	    	                                +'"text":"抽查检查信息<span class=\'sr-only\'>t1_8</span>"'
	    	                               /* +',"nodes":['
	    	                                          +'{"text":"抽查检查信息表<span class=\'sr-only\'>t1_8_1</span>"}'
	    	                                +']'*/     
	    	                           +'}'
	    	                        +']'
	    	          +'},'
	    	          +'{'
	    	               +'"text":"企业公示信息<span class=\'sr-only\'>t2</span>",'
	    	               +'"nodes":['
	    	                            +'{"text":"企业年报<span class=\'sr-only\'>t2_1</span>"},'
	    	                            +'{'
	    	                                +'"text":"股东及出资信息<span class=\'sr-only\'>t2_2</span>",'
	    	                                +'"nodes":['
	    	                                            +'{"text":"股东及出资信息表<span class=\'sr-only\'>t2_2_1</span>"},'
	    	                                            +'{"text":"变更信息表<span class=\'sr-only\'>t2_2_2</span>"}'
	    	                                
	    	                                +']'    
	    	                            +'},'
	    	                            +'{"text":"股权变更信息<span class=\'sr-only\'>t2_3</span>"},'
	    	                            +'{"text":"行政许可信息<span class=\'sr-only\'>t2_4</span>"},'
	    	                            +'{"text":"知识产权出质登记信息<span class=\'sr-only\'>t2_5</span>"},'
	    	                            +'{"text":"行政处罚信息<span class=\'sr-only\'>t2_6</span>"}'
	    	                         +']'
	    	          +'},'
	    	          +'{'
	    	                +'"text":"司法协助公示信息<span class=\'sr-only\'>t3</span>",'
	    	                +'"nodes":['
	    	                             +'{"text":"股权冻结信息<span class=\'sr-only\'>t3_1</span>"},'
	    	                             +'{"text":"股东变更信息<span class=\'sr-only\'>t3_2</span>"}'
	    	                          +']'
	    	          +'},'
	    	          +'{'
	    	                +'"text":"其他部门公示信息<span class=\'sr-only\'>t4</span>",'
	    	                +'"nodes":['
	    	                              +'{'
	    	                                   +'"text":"行政许可信息<span class=\'sr-only\'>t4_1</span>",'
	    	                                   +'"nodes":['
	    	                                              +'{"text":"行政许可信息表<span class=\'sr-only\'>t4_1_1</span>"},'
	    	                                              +'{"text":"变更信息表<span class=\'sr-only\'>t4_1_2</span>"}'
	    	                                   +']'     
	    	                              +'},'
	    	                              +'{"text":"行政处罚信息<span class=\'sr-only\'>t4_2</span>"}'
	    	                           +']'
	    	          +'}'
	    	      +']';
	     
	     $("#leftMenu").treeview({
	    	    levels:1,
	    	    /*enableLinks: true,*/
	    	    /*expandIcon: 'glyphicon glyphicon-chevron-right',
			    collapseIcon: 'glyphicon glyphicon-chevron-down',*/
	    	    data:menu
	     })
}
//td内容为空
function gsxtCommon_tdIsNull(){
	
	    var td='';
	    
	    
	    for(var i=0;i<$("body table").find("td").length;i++){
	    	
	           td=$("body table").find("td").eq(i);
	           
	           
	           if(!/[a-zA-Z0-9\u4e00-\u9fa5\-]/.test(td.html())){
	        	     td.text("---");
	        	     td.attr("align","center")
	           }
	    }
}
//各种信息详情/*,"admLicInfo_","admPunishInfo2_"*/
function gsxtCommon_infoDetail(){
	
	
	     var infoDetailArray=["admPunishInfo_","annReport_","admliInfo_","equFreezeInfo_","stohrChangeInfo_","chatMortgInfo_","admLicInfo_"];
	     
	     for(var i=0;i<infoDetailArray.length;i++){
	    	   
	    	    $("span[id^='"+infoDetailArray[i]+"']").on("click",function(){
	    	    	
	    	         var id='',id_detail='',status='';
	    	         id=$(this).attr("id");
	    	         id_detail=id+"_detail";
	    	         status=$("#"+id_detail).css("display");
	    	         if(status=="none"){
	    	    	     
	    	        	 $("#"+id_detail).show();
	    	    	     
	    	    	     if($(this).text()=="展开"){
	    	    	    	  $(this).text("收起");
	    	    	     }
	    	         }else{
	    	        	 
	    	    	     $("#"+id_detail).hide();
	    	    	     
	    	    	     if($(this).text()=="收起"){
	    	    	    	  $(this).text("展开");
	    	    	     }
	    	         }
	           })
	     }
	     
}
//目录定位
function gsxtCommon_fixed(){
	
	    $("#leftMenu").click(function(){
	    	   
	    	    var liObj='',titleID='',divDistance='',browserCurrentOffset='',navHeight='';
	    	    liObj=$("#leftMenu ul").children("li[class*='node-selected']");
	    	    if (liObj.attr("data-nodeid")!=undefined){
	    	    	
	    	    	   titleID=$(liObj).children("span[class='sr-only']").text();
	    	    	   navHeight=$("nav[class*='navbar-fixed-top']").css("height");
	    	    	   
	    	    	   divDistance=$("#"+titleID).offset().top;   //div距离浏览器顶部的距离
	    	    	   browserCurrentOffset=$(document).scrollTop();
	    	    	   window.scrollTo(0,divDistance-70);
	    	    	
	    	    }
	    })
	
}
