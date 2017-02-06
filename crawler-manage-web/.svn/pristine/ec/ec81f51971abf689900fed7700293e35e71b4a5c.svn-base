$(function(){
	
	    var sitesObj='';
	    var siteList='';
	    var siteNum=0;//某类型下站点已显示的个数
	    var siteTypeArray=new Array();
	    var br1="";
	    
	    
	    sitesObj=index_getSite($("#ctx").val());
	
	    if(sitesObj==null || sitesObj=='' || sitesObj=="isError"){
	    	  return;
	    }
	    
	
	    $.each(sitesObj,function(index,site){
               if($.inArray(site.type,siteTypeArray)==-1){
                	siteTypeArray.push(site.type);
               }	    	    
	    })
	    
	    
	    //5站点个为一行
	    for(var i=0;i<siteTypeArray.length;i++){
	    	
	    	    siteList +="<div data-name='"+siteTypeArray[i]+"'>"
	                            +"<div class='row' style='margin-bottom:2%'>"
	                                  +"<div class='col-md-4 col-md-offset-5'><h3 style='font-weight:bold'>"+siteTypeArray[i]+"类</h3></div>"
	                            +"</div>";
	    	
	    	    siteNum=0;
	    	    for(var j=0;j<sitesObj.length;j++){
	    	    
	    	    	    if(sitesObj[j].type!=siteTypeArray[i]){
	    	    	    	  if(j==(sitesObj.length-1)){
	    	    	    		     siteList +="</div>";   //如果站点总数不是5的倍数，循环到最后一个时，row(1*)结尾
	    	    	    	  }
	    	    	    	  continue;
	    	    	    }else{
                              if(siteNum%5==0){
                            	    siteList +="<div class='row' data-name='"+siteTypeArray[i]+"类站点_"+parseInt(siteNum/5)+"'>";//(1*),三种结尾方法:某类型下站点正好为5的倍数、不是5的倍数(最后一个不是该类型下的站点)、最后一个正好是该类型下的站点
                              }  
                              
	    	    	    	  /*if(sitesObj[j].siteInfo.length<=7){
	    	    	    		    br1="<br>";
	    	    	    	  }else{
	    	    	    		    br1="";
	    	    	    	  }*/
	    	    	    	  
	    	    	    	  
                              siteList +="<div data-name='"+sitesObj[j].name+"' class='col-sm-2 col-md-2 thumbnail' style='margin-left:2%;width:125px;float:left'>"
                                              +"<a href='"+sitesObj[j].siteURL+"' target='_blank'>"
                                                   +"<img class='img-responsive' src='"+$("#ctx").val()+sitesObj[j].siteLogo+"' alt='"+sitesObj[j].name+"' style='width:127px;height:127px'>"
                                              +"</a>"
                                              +"<div class='caption'>"
                                                   +"<h4 data-name='name'><strong>"+sitesObj[j].name+"</strong></h4>"
                                                   +"<p data-name='intruduction'>"+sitesObj[j].siteInfo+"</p>"+br1
                                                   +"<p><a href='"+$("#ctx").val()+sitesObj[j].linkUrl+"' class='btn btn-primary btn-block btn-sm' role='button'>进入 &gt;&gt;</a></p>"
                                              +"</div>"
                                         +"</div>";
                              
                              if((siteNum+1)%5==0 || j==(sitesObj.length-1)){  //如果站点总数正好是5的倍数、最后一个正好是该类型下的站点，row(1*)结尾
                          	         siteList +="</div>";
                              }
                              siteNum ++;
	    	    	    }//if 1
	    	    }//for内
	    	    
	    	    siteList +="</div>";
	    }//for外
	    
	    $("#siteList").html(siteList);
	    
	    
	    
	    //修正高度
	
	    var siteTypes='', oneSiteType='',siteRows='',oneSiteRow='',oneSiteRow_sites='';
	    var nameHeight='',intruductionHeight='',maxNameRowNum='',nameRowNum='',brs='',oneSite='',oneSite_nameHeight='';
	    
	    siteTypes=$("#siteList").children("div");
	    
	    $("body").append("<div id='test' class='col-sm-2 col-md-2 thumbnail' style='color:white;border:0px'>"
	    		               +"<div class='caption'>"
	    		                      +"<h4 id='test_1'><strong>测试</strong></h4>"
	    		                      +"<p id='test_2'>测试</p>"
	    		               +"</div>"
	    		         +"</div>");
	    nameHeight=parseInt($("#test_1").css("height").replace(/px/,''));
	    intruductionHeight=parseInt($("#test_2").css("height").replace(/px/,''));
	   
	    //console.log("参考值："+nameHeight+" "+intruductionHeight)
	    
	    $("div[id='test']").remove();
	    
	    for(var i=0;i<siteTypes.length;i++){
	    	   
	    	    oneSiteType=$("#siteList").children("div").eq(i);  //一个站点类
	    	    
	    	    siteRows=$(oneSiteType).children("div[data-name]");  //一个站点类下的全部行
	    	    
	    	    for(var j=0;j<siteRows.length;j++){    //一行
	    	    	
	    	    	    oneSiteRow=$(siteRows).eq(j);// 一个站点行
	    	    	    
	    	    	    oneSiteRow_sites=$(oneSiteRow).children("div");    //一个站点行下的全部站点
	    	    	  
	    	    	     maxNameRowNum=1;
	    	    	     nameRowNum='';
	    	    	     maxIntruRowNum=1;
	    	    	     intruRowNum='';
	    	    	    for(var k=0;k<oneSiteRow_sites.length;k++){
	    	    	    	
	    	    	    	    oneSite=$(oneSiteRow_sites).eq(k);
	    	    	    	    oneSite_nameHeight=parseInt($(oneSite).find("h4[data-name='name']").css("height").replace(/px/,''));
	    	    	    	    
	    	    	    	    //解决name
	    	    	            if(oneSite_nameHeight>(2*nameHeight+2)){
	    	    	            	    nameRowNum=3;
	    	    	            }else if(oneSite_nameHeight>(nameHeight+2)){
	    	    	            	    nameRowNum=2;
	    	    	            }else{
	    	    	            	    nameRowNum=1; 
	    	    	            }	
	    	    	            
	    	    	            if(nameRowNum>maxNameRowNum){
	    	    	            	  maxNameRowNum=nameRowNum;
	    	    	            }
	    	    	            
	    	    	            //解决intruduction
	    	    	            oneSite_intruHeight=parseInt($(oneSite).find("p[data-name='intruduction']").css("height").replace(/px/,''));
	    	    	            
	    	    	            
    	                        if(oneSite_intruHeight>(5*intruductionHeight+2)){
    	                        	   intruRowNum=6;
	    	    	            }else if(oneSite_intruHeight>(4*intruductionHeight+2)){
	    	    	            	   intruRowNum=5;
    	    	                }else if(oneSite_intruHeight>(3*intruductionHeight+2)){
    	    	                	   intruRowNum=4;
    	    	                }else if(oneSite_intruHeight>(2*intruductionHeight+2)){
    	    	                	   intruRowNum=3;
    	    	                }else if(oneSite_intruHeight>(intruductionHeight+2)){
    	    	                       intruRowNum=2;
    	    	                }else{
    	    	                	   intruRowNum=1; 
    	    	                }
	    	    	            
	    	    	            if(intruRowNum>maxIntruRowNum){
	    	    	            	maxIntruRowNum=intruRowNum;
	    	    	            }
	    	    	    	   
	    	    	    }
	    	    	    
	    	    	    
	    	    	    //解决name
	    	    	    if(maxNameRowNum>1){
	    	    	    	    for(var m=0;m<oneSiteRow_sites.length;m++){
	    	    	    	    	    brs='';
	    	    	    	    	    oneSite=$(oneSiteRow_sites).eq(m);
	    	    	    	    	    oneSite_nameHeight=parseInt($(oneSite).find("h4[data-name='name']").css("height").replace(/px/,''));
	    	    	    	    	   
	    	    	    	            for(var n=0;n<(maxNameRowNum-Math.floor(oneSite_nameHeight/nameHeight));n++){
	    	    	    	            	  brs+="<br>";
	    	    	    	            }	    
	    	    	    	    	    
	    	    	    	    	   if(maxNameRowNum>Math.floor(oneSite_nameHeight/nameHeight)){
	    	    	    	    		         $(oneSite).find("h4[data-name='name']").html("<strong>"
	    	    	    	    		        		                                           +$(oneSite).find("h4[data-name='name']").text()
	    	    	    	    		        		                                           +"<br>"+brs
	    	    	    	    		        		                                       +"</strong>");
		    	    	               } 
	    	    	    	    }
	    	    	    }
	    	    	    
	    	    	    //解决intruduction
	    	    	    if(maxIntruRowNum>1){
    	    	    	    for(var m=0;m<oneSiteRow_sites.length;m++){
    	    	    	    	    brs='';
    	    	    	    	    oneSite=$(oneSiteRow_sites).eq(m);
    	    	    	    	    oneSite_intruHeight=parseInt($(oneSite).find("p[data-name='intruduction']").css("height").replace(/px/,''));
    	    	    	    	   
    	    	    	            for(var n=0;n<(maxIntruRowNum-Math.floor(oneSite_intruHeight/intruductionHeight));n++){
    	    	    	            	  brs+="<br>";
    	    	    	            }	    
    	    	    	    	    
    	    	    	    	   if(maxIntruRowNum>Math.floor(oneSite_intruHeight/intruductionHeight)){
    	    	    	    		         $(oneSite).find("p[data-name='intruduction']").html($(oneSite).find("p[data-name='intruduction']").text()
    	    	    	    		        		                                              +"<br>"+brs);
	    	    	               } 
    	    	    	    }
    	    	    }
	    	    }//一行
	    }
	    
	   
})
//获取全部isEnabled站点
function index_getSite(rootPath){
	   
	     var getSiteResult='';
	
	     $.ajax({
	    	    url:rootPath+"/tools/getSite",
	    	    async:false,
	    	    success:function(data){
	    	    	getSiteResult=data;
	    	    },
	    	    error:function(){
	    	    	alert("服务器断开\n方法名：index_getSite/index.js");
	    	    	getSiteResult="isError";
	    	    }
	     })
	     
	     return getSiteResult;
}