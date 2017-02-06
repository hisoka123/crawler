/*cycle display search results on the page*/
function dailianmeng_displaySearchResults(searchResults,person){
		if(searchResults==null || searchResults==''){
		     return "<p class='sr-h-nullPerson'>此名称暂时没有找到相关的信用记录</h4>";
		}

	   var url = "http://www.dailianmeng.com";
	   
	   var display='';
	   var display_content='';
	   
	   var display_name='';
	   var display_lenderMoney='';
	   var display_lenderDate='';
	   var display_lenderMethod='';
	   var display_InfoSource='';
	   var display_updateDate='';
	   var display_detailUrl='';
	   
	   var hr="<hr  class='col-md-10'>";
	   
	   $.each(searchResults,function(index,searchResult){
		   		
		         display_content='';
		   
		         //显示姓名
		         if(searchResult.name!=null && searchResult.name!=""){
		        	       display_name="<div  class='row' style='margin-top:1%'>"
                                             +"<div class='col-md-2'>姓名:</div>"
                                             +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                    +searchResult.name
                                             +"</div>"
                                        +"</div>";
		         }
		         
		         //显示金额
		         if(searchResult.lenderMoney!=null && searchResult.lenderMoney!=""){
		        	        display_lenderMoney="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>金额:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.lenderMoney
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         //显示借款日期
		         if(searchResult.lenderDate!=null && searchResult.lenderDate!=""){
		        	 display_lenderDate="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>借款日期:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.lenderDate
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         //显示借款方式
		         if(searchResult.lenderMethod!=null && searchResult.lenderMethod!=""){
		        	        display_lenderMethod="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>借款方式:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.lenderMethod
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         //显示信息来源
		         if(searchResult.InfoSource!=null && searchResult.InfoSource!=""){
		        	        display_InfoSource="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>信息来源:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.InfoSource
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         //显示更新时间
		         if(searchResult.updateDate!=null && searchResult.updateDate!=""){
		        	        display_updateDate="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>更新时间:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                                     +searchResult.updateDate
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         //显示查看详情
		         if(searchResult.detailUrl!=null && searchResult.detailUrl!=""){
		        	        display_detailUrl="<div  class='row' style='margin-top:1%'>"
                                              +"<div class='col-md-2'>详情:</div>"
                                              +"<div class='col-md-8' style='margin-left:-5%;width:71%'>"
                                              +"<a href='javascript:dailianmeng_detail(\""+url+searchResult.detailUrl+"\", this);'>查看</a>"
                                              +"</div>"
                                         +"</div>";
		         }
		         
		         display_content=display_name
                                 +display_lenderMoney
                                 +display_lenderDate
                                 +display_InfoSource
                                 +display_updateDate
                                 +display_detailUrl;
		         
		         display+=displayResults(index,"dailianmeng_searchResults_",searchResults.length,"dailianmeng_more","贷联盟搜索结果","","dailianmeng_name_","","贷联盟",display_content,"");
		         
	   })

	   return display;
}

/*搜房网*/
function fang_action(searchKey){

	if(searchKey!="" && searchKey.indexOf("请输入小区名称或地址，给自己的房子估个价")==-1){
		   var result = dailianmeng_getSearchResults(searchKey);
	       $("div[data-name='verifycodeBox']").css("display", "block");
	       //$("#codeImage").attr("src", result.data.codeImageUrl);
	      // $("#serializedFileName").val(result.data.serializedFileName);
	}
}


function dailianmeng_verifySubmit(verifycode, serializedFileName) {
	$("div[data-name='verifycodeBox']").css("display", "none");
	if(verifycode=="") {
		alert("输入验证码");
	}else {
		$.ajax({
			url: ROOTPATH + '/modules/dailianmeng/getSearchResults2',
			type: 'post',
			data: {'verifycode': verifycode, 'serializedFileName': serializedFileName},
			success: function(data) {
				$("#dailianmeng_searchResults").html(dailianmeng_displaySearchResults(data.tbody, ""));
				$("#dailianmeng_more").remove();
				$("div[id^=dailianmeng_name_]").remove();
				$("#dailianmeng_searchResults").children().show();
				$("#dailianmeng_searchResults").show();
				$("#dailianmeng_searchResultsRightIcon").show();
				if($("hr").css("margin-top")=="0px"){
					$("hr").removeAttr("style");
				}
			},
			error: function() {
				
			}
		});
	}
}

function dailianmeng_detail(detailUrl, element) {
	$.ajax({
		url: ROOTPATH + '/modules/dailianmeng/getDetailPage',
		type: 'post',
		data: {'detailUrl': detailUrl},
		success: function(data) {
			alert(data);
			$(element).parent().append(
					"<div class='col-md-2'>"+
					"被执行人被执行人姓名/名称："+data.info1+
					"</div>");
		},
		error: function() {
		}
	});	
}