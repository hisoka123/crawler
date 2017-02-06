/*
 * 执行案件信息查询
 */
function zjsfgkwExecuteCaseSearch_getSearchResults(){
	  var  searchResult;
	  var dSR=$("#dSR").val();
	  var aH_BH=$("#aH_BH").val();
	  var aH_NH=$("#aH_NH").val();
	  $("#zf_result").html("");
	  $("#shclDefault").show();
	  $.ajax({
		   url:ROOTPATH+"/modules/ajaxZjsfgkwExecuteCaseSearch/getExecuteCaseSearchResults",
		   data:encodeURI(encodeURI("dSR="+dSR+"&aH_BH="+aH_BH+"&aH_NH="+aH_NH)),
		   async:false,
	       success:function(result){
	    	       searchResult=result;
	    	       $("#zf_result").html('');
	    	       createTable(searchResult);
	    	    
	       },
	       error:function(){
	    	       searchResult="error";
	       }		  
	  });	
	 // alert("sina_getSearchResult返回结果为："+searchResult);
	  return searchResult;	
}



/*
 * 0曝光台-个人未履行生效裁判信息 ;1曝光台-单位未履行生效裁判信息;2执行惩戒-限制高消费;3执行惩戒-限制出境;4执行惩戒-限制招投标
 */
function zjsfgkwCreditSearch_getSearchResults(){
	  var  searchResult;
	  var txtReallyName=$("#txtReallyName").val();
	  var txtCredentialsNumber=$("#txtCredentialsNumber").val();
	  var txtAH=$("#txtAH").val();
	  var drpZXFY=$("#drpZXFY").val();
	  var txtStartLARQ=$("#txtStartLARQ").val();
	  var txtEndLARQ=$("#txtEndLARQ").val();
	  var creditType=$("#creditType").val();
	  if(txtCredentialsNumber!=''){
		  if(txtCredentialsNumber.length!=18){
			  alert("证件号长度应为18位！");
			  return;
		  }
	  }
	  $("#zf_result").html("");
	  $("#shclDefault").show();
	  if(creditType==0){
		  /*
		   * 曝光台-个人未履行生效裁判信息
		   */
		  $.ajax({
			   url:ROOTPATH+"/modules/ajaxZjsfgkwCredit/getCreditResults",
			   data:encodeURI(encodeURI("txtReallyName="+txtReallyName+"&txtCredentialsNumber="+txtCredentialsNumber+
					   "&txtAH="+txtAH+"&drpZXFY="+drpZXFY+"&txtStartLARQ="+txtStartLARQ+"&txtEndLARQ="+txtEndLARQ)),
			   async:false,
		       success:function(result){
		    	       searchResult=result;
		    	       $("#zf_result").html('');
		    	       createTable2(searchResult);
		    	    
		       },
		       error:function(){
		    	       searchResult="error";
		       }		  
		  });
	  }else if(creditType==1){
		  /*
		   * 曝光台-单位未履行生效裁判信息
		   */
		  $.ajax({
			   url:ROOTPATH+"/modules/ajaxZjsfgkwCreditCompany/getCreditCompanyResults",
			   data:encodeURI(encodeURI("txtComReallyName="+txtReallyName+"&txtComCredentialsNumber="+txtCredentialsNumber+
					   "&txtComAH="+txtAH+"&drpComZXFY="+drpZXFY+"&txtComStartLARQ="+txtStartLARQ+"&txtComEndLARQ="+txtEndLARQ)),
			   async:false,
		       success:function(result){
		    	       searchResult=result;
		    	       $("#zf_result").html('');
		    	       createTable2(searchResult);
		    	    
		       },
		       error:function(){
		    	       searchResult="error";
		       }		  
		  });
	  }else if(creditType==2){
		  /*
		   * 执行惩戒-限制高消费
		   */
		  $.ajax({
			   url:ROOTPATH+"/modules/ajaxZjsfgkwLimitHighConsum/getLimitHighConsumResults",
			   data:encodeURI(encodeURI("txtReallyName="+txtReallyName+"&txtCredentialsNumber="+txtCredentialsNumber+
					   "&txtAH="+txtAH+"&drpZXFY="+drpZXFY+"&txtStartLARQ="+txtStartLARQ+"&txtEndLARQ="+txtEndLARQ)),
			   async:false,
		       success:function(result){
		    	       searchResult=result;
		    	       $("#zf_result").html('');
		    	       createTable2(searchResult);
		    	    
		       },
		       error:function(){
		    	       searchResult="error";
		       }		  
		  });
	  }else if(creditType==3){
		  /*
		   * 执行惩戒-限制出境
		   */
		  $.ajax({
			   url:ROOTPATH+"/modules/ajaxZjsfgkwLimitExit/getLimitExitResults",
			   data:encodeURI(encodeURI("txtReallyName="+txtReallyName+"&txtCredentialsNumber="+txtCredentialsNumber+
					   "&txtAH="+txtAH+"&drpZXFY="+drpZXFY+"&txtStartLARQ="+txtStartLARQ+"&txtEndLARQ="+txtEndLARQ)),
			   async:false,
		       success:function(result){
		    	       searchResult=result;
		    	       $("#zf_result").html('');
		    	       createTable2(searchResult);
		    	    
		       },
		       error:function(){
		    	       searchResult="error";
		       }		  
		  });
	  }else if(creditType==4){
		  /*
		   * 执行惩戒-限制招投标
		   */
		  $.ajax({
			   url:ROOTPATH+"/modules/ajaxZjsfgkwLimitBidding/getLimitBiddingResults",
			   data:encodeURI(encodeURI("txtReallyName="+txtReallyName+"&txtCredentialsNumber="+txtCredentialsNumber+
					   "&txtAH="+txtAH+"&drpZXFY="+drpZXFY+"&txtStartLARQ="+txtStartLARQ+"&txtEndLARQ="+txtEndLARQ)),
			   async:false,
		       success:function(result){
		    	       searchResult=result;
		    	       $("#zf_result").html('');
		    	       createTable2(searchResult);
		    	    
		       },
		       error:function(){
		    	       searchResult="error";
		       }		  
		  });
	  }else{
		  $("#zf_result").html('没有查询到相关数据！');
	  }
	  
		
	 // alert("sina_getSearchResult返回结果为："+searchResult);
	  return searchResult;	
}


function createTable(searchResult){
	
	if(searchResult.length!=0){
		var table=$("<table width=\"820\" height=\"111\" border=\"0\" id=\"tab_3\" cellspacing=\"1\" cellpadding=\"5\" >");
	     table.appendTo($("#zf_result"));
  	 for(var i=0;i<searchResult.length;i++)
	     {
  		 var tr1=$("<tr></tr>");
	    	 var tr2=$("<tr></tr>");
	    	 var tr3=$("<tr></tr>");
  	        tr1.appendTo(table);
  	        tr2.appendTo(table);
  	        tr3.appendTo(table);
	    	
  	     var td1=$("<td height=\"28\" colspan=\"4\" style=\"background-color:#2e9fbf;color:#fff;padding:5px;\">"+searchResult[i].caseNo+"</td>");
  	     var td2_1=$("<td width=\"74\" height=\"33\">法院：</td>");  
  	     var td2_2=$("<td width='225'>"+searchResult[i].court+"</td>"); 
  	     var td2_3=$("<td width='103'>立案日期：</td>"); 
  	     var td2_4=$("<td width='400'>"+searchResult[i].caseDate+"</td>"); 
  	     var td3_1=$("<td>案件状态</td>");  
  	     var td3_2=$("<td>"+searchResult[i].caseState+"</td>"); 
  	     var td3_3=$("<td>当事人：</td>"); 
  	     var td3_4=$("<td>"+searchResult[i].principal+"</td>");
  	        
  	     td1.appendTo(tr1);
  	     td2_1.appendTo(tr2);
  	     td2_2.appendTo(tr2);
  	     td2_3.appendTo(tr2);
  	     td2_4.appendTo(tr2);
  	     td3_1.appendTo(tr3);
  	     td3_2.appendTo(tr3);
  	     td3_3.appendTo(tr3);
  	     td3_4.appendTo(tr3);
  	        
	     }
	     $("#zf_result").append("</table>");
	     $("#shclDefault").hide();
   }else{
  	 $("#zf_result").html('没有查询到相关数据！');
   }
	
}


function createTable2(searchResult){
	
	if(searchResult.length!=0){
		var table=$("<table width=\"820\" height=\"111\" border=\"0\" id=\"tab_4\" cellspacing=\"1\" cellpadding=\"5\" >");
	     table.appendTo($("#zf_result"));
  	 for(var i=0;i<searchResult.length;i++)
	     {
  		 var tr1=$("<tr style=\"background-color:#2e9fbf;color:#fff;padding:5px;\">");
	    	 var tr2=$("<tr></tr>");
	    	 var tr3=$("<tr></tr>");
	    	 var tr4=$("<tr></tr>");
	    	 var tr5=$("<tr></tr>");
  	        tr1.appendTo(table);
  	        tr2.appendTo(table);
  	        tr3.appendTo(table);
  	        tr4.appendTo(table);
  	        tr5.appendTo(table);
	    	
  	     var td1=$("<td height=\"28\" colspan=\"4\">"+searchResult[i].name+"&nbsp;&nbsp;&nbsp;"+searchResult[i].idNo+"&nbsp;&nbsp;&nbsp;"+searchResult[i].creditDate+"</td>");
  	     var td2_1=$("<td width=\"74\" height=\"33\">地址：</td>");  
  	     var td2_2=$("<td width='225'>"+searchResult[i].address+"</td>"); 
  	     var td2_3=$("<td width='103'>执行依据:</td>"); 
  	     var td2_4=$("<td width='400'>"+searchResult[i].enforceBasis+"</td>"); 
  	     var td3_1=$("<td>案号：</td>");  
  	     var td3_2=$("<td>"+searchResult[i].caseNo+"</td>"); 
  	     var td3_3=$("<td>执行案由：</td>"); 
  	     var td3_4=$("<td>"+searchResult[i].executReason+"</td>");
  	     var td4_1=$("<td>执行法院：</td>");  
  	     var td4_2=$("<td>"+searchResult[i].court+"</td>"); 
  	     var td4_3=$("<td>未执行金额：</td>"); 
  	     var td4_4=$("<td>"+searchResult[i].amountNotExecuted+"</td>");
  	     var td5_1=$("<td>立案日期：</td>");  
  	     var td5_2=$("<td>"+searchResult[i].caseDate+"</td>"); 
  	     var td5_3=$("<td>标的金额：</td>"); 
  	     var td5_4=$("<td>"+searchResult[i].targetAmount+"</td>");
  	        
  	     td1.appendTo(tr1);
  	     td2_1.appendTo(tr2);
  	     td2_2.appendTo(tr2);
  	     td2_3.appendTo(tr2);
  	     td2_4.appendTo(tr2);
  	     td3_1.appendTo(tr3);
  	     td3_2.appendTo(tr3);
  	     td3_3.appendTo(tr3);
  	     td3_4.appendTo(tr3);
  	     td4_1.appendTo(tr4);
  	     td4_2.appendTo(tr4);
  	     td4_3.appendTo(tr4);
  	     td4_4.appendTo(tr4);
  	     td5_1.appendTo(tr5);
  	     td5_2.appendTo(tr5);
  	     td5_3.appendTo(tr5);
  	     td5_4.appendTo(tr5);
  	        
	     }
	     $("#zf_result").append("</table>");
	     $("#shclDefault").hide();
   }else{
  	 $("#zf_result").html('没有查询到相关数据！');
   }
	
}

//切换搜索业务
//title 业务主体,creditType 业务标示,tab 显示搜索框
function changeTitle(li,title,tab,creditType){
	//creditType  0曝光台-个人未履行生效裁判信息 ;1曝光台-单位未履行生效裁判信息;2执行惩戒-限制高消费;3执行惩戒-限制出境;4执行惩戒-限制招投标;5执行案件信息查询
	$("#zf_result").html('');
	$("#tab_1").hide();
	$("#tab_2").hide();
	$("#"+tab).show();
	$("#creditType").val(creditType);
	$("#tips").html(title);
	$("#li1").css("background","#2e9fbf");
	$("#li2").css("background","#2e9fbf");
	$("#li3").css("background","#2e9fbf");
	$("#li4").css("background","#2e9fbf");
	$("#li5").css("background","#2e9fbf");
	$("#li6").css("background","#2e9fbf");
	$("#"+li).css("background","#027c9e");
	
}
