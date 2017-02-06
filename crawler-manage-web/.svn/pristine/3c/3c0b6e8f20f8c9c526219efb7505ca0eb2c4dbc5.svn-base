<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
      $(function(){
    	     var title="${title}";
    	     $("title").html(title);
      })
</script>
<script src="${ctx}/static/js/sweet-totop.js"></script>
<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div data-name="left_menu" class="col-md-2">
			     <jsp:include page="${leftMenuPath}"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a href='${pbccrcIndexPath}'>人行征信</a>";
                                       var path_2="<a href='${pbccrcAuthPath}'>身份验证</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!--身份验证  -->
                    <div data-name="searchBox" class="row">               
                         <div id="searchBox_big" class="col-md-6 col-md-offset-2" style="margin-top:3%; padding:30px; border:1px solid #EEEEEE; border-radius:7px; background-color:#FCFCFC; display:block;">
							
							<form id="auth_fo" class="form-horizontal">
							  <div class="form-group">
							    <label for="username" class="col-sm-2 control-label">用户名</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="password" class="col-sm-2 control-label">密码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="password" class="form-control" id="password" name="password" placeholder="密码">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="verifycode" class="col-sm-2 control-label">验证码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="verifycode" name="verifycode" placeholder="图片验证码" style="display:inline; width:110px;">
							      <img id="codeImage" alt=" 验证码识别中..." src="">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="tradecode" class="col-sm-2 control-label">授权码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="tradecode" name="tradecode" placeholder="手机授权码">
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="col-sm-offset-2 col-sm-7" style="padding-left:5px;">
							      <button id="excute_btn" type="button" class="btn btn-success">执行&nbsp;>></button>
							      <span style="margin-left:20px;">
							      	<input type="radio" name="reportCategory" id="reportCategory" value="json" checked="checked"> 获取报告&nbsp;
							      	<input type="radio" name="reportCategory" id="reportCategory" value="pdf"> 生成PDF
							      </span>
							    </div>
							  </div>
							</form>
							
							<script type="text/javascript">
								$("input[name='reportCategory']").on("click", function(){
									var reportCategory = $(this).val();
									if (reportCategory=="json") {
										$("#tradecode").attr("disabled", false);
									} else if (reportCategory=="pdf") {
										$("#tradecode").attr("disabled", true);
									}
								});
							</script>
                         </div>
                         
                         <!--获取pdf验证码 -->
                         <div id="searchBox_big2" class="col-md-6 col-md-offset-2" style="margin-top:3%; padding:30px; border:1px solid #EEEEEE; border-radius:7px; background-color:#FCFCFC; display:none;">
	              		 	<form id="pdf_verifycode_fo" class="form-horizontal">
	              		 		<div class="form-group">
								    <label for="verifycode" class="col-sm-3 control-label" style="margin-left:8px;margin-right:-8px;">PDF验证码</label>
								    <div class="col-sm-7" style="padding-left:5px;">
								      <input type="text" class="form-control" name="verifycode" placeholder="图片验证码" style="display:inline; width:110px;">
								      <img id="pdf_verifycode_codeImage" alt="图片验证码" src="">
								    </div>
							    </div>
							    <div class="form-group">
								    <div class="col-sm-offset-1 col-sm-7" style="padding-left:5px;">
								      <button id="excute2_btn" type="button" class="btn btn-success">执行2&nbsp;>></button>
							    	</div>
							  	</div>
	              		 	</form>
	              		 </div>
	              		 
                    </div>
                    
                    <!--搜索结果  -->
                    <div data-name="pbccrc_searchResults" class="row">
                         <div id="pbccrc_searchResults" class="col-md-11" data-searchKey="null" style="display:none;">
                         	<!-- 填充搜索结果 -->
                         </div>
                         <div id="pbccrc_searchResults_pdf" style="display:none;">
                         	<!-- 填充搜索结果 -->
                         </div>
                    </div>
				</div>
			</div>
			
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
	
<jsp:include page="${footerPath}"></jsp:include>
   
<!-- script -->
<script src="${ctx}/static/js/common.js"></script>
<script src="${ctx}/static/js/sweet-ajax-progress.js"></script>
<script src="${ctx}/static/js/sweet-form-collection.js"></script>

<script type="text/javascript">
	//loginpageCookies
	var loginpageCookiesStr = "";
	var loggedCookiesStr = "";
	
	$(function(){
		ROOTPATH="${ctx}";
		leftMenu();
		
		$("#searchBox_big_btn").click(function(){
		   sina_action(searchBox_Big());
		})
		
		$("#searchBox_small_btn").click(function(){
		  sina_action(searchBox_small());
		})
		
		//getloginpage
		ajaxTransObj("get", ROOTPATH+"/api/pbccrc/getloginpage", null, null, getloginpageCallback, pbccrcErrorBack, true);
	});
	
	
	//回调函数
	var pbccrcErrorBack = function(XMLHttpRequest, textStatus, errorThrown) {
		alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus + " " + errorThrown);
	}
	//
	var getloginpageCallback = function(res) {
		console.log("======================getloginpageCallback:res======================\n" + res);
		res = JSON.parse(res);
		if (res.error) {
			alert(res.error.errorCode+"--"+res.error.errorName+": "+res.error.errorMsg);
		}
		if (res.data.statusCode!=0) {
			alert(res.data.message);
			return;
		}
		loginpageCookiesStr = JSON.stringify(res.data.cookies);////
		$("#verifycode").val(res.data.codeValue);
		$("#codeImage").attr("src", res.data.codeImageUrl);
	}
	//
	var excuteGetcreditCallback = function(res){
		console.log("======================excuteGetcreditCallback:res======================\n" + res);
		res = JSON.parse(res);
		if (res.error) {
			$("div.sr-zql-loading").css("display", "none");
			alert(res.error.errorCode+"--"+res.error.errorName+": "+res.error.errorMsg);
			window.location.reload();
			return;
		}
		if (res.data.statusCode!=0) {
			$("div.sr-zql-loading").css("display", "none");
			alert(res.data.message);
			window.location.reload();
			return;
		}
		showCreditData(res);//
	}
	var excuteGetcreditCallback2 = function(res){
		console.log("======================excuteGetcreditCallback2:res======================\n" + res);
		res = JSON.parse(res);
		if (res.error) {
			alert(res.error.errorCode+"--"+res.error.errorName+": "+res.error.errorMsg);
		}
		if (res.data.statusCode!=0) {
			alert(res.data.message);
			return;
		}
		showCreditData(res);//
	}
	var showCreditData = function(res) {
		loggedCookiesStr = res.data.loggedCookiesJson;////
		var report = res.data.report;
		
		$("#searchBox_big").css("display", "none");	
		$("#pbccrc_searchResults").css("display", "block");
		
		//填充内容代码
		/*
		* 报告基本信息
		*/
		var html = "<h4 class='page-header' style='margin-top:15px;'>报告基本信息</h4>";
		//reportBase
		html+="<table id='reportBase' class='table table-striped table-bordered table-hover'>"
				+"<tr><td>报告编号</td><td>"+ report.reportBase.reportId +"</td></tr>"
				+"<tr><td>查询时间</td><td>"+ report.reportBase.queryTime +"</td></tr>"
				+"<tr><td>报告时间</td><td>"+ report.reportBase.reportTime +"</td></tr>"
				+"<tr><td>姓名</td><td>"+ report.reportBase.realname +"</td></tr>"
				+"<tr><td>证件类型</td><td>"+ report.reportBase.certificateType +"</td></tr>"
				+"<tr><td>证件号码</td><td>"+ report.reportBase.certificateNum +"</td></tr>"
				+"<tr><td>婚姻</td><td>"+ report.reportBase.marriageStatus +" </td></tr></table>";
		/*
		* 信贷记录
		*/
		html+="<h4 class='page-header' style='margin-top:15px;'>信贷记录</h4>";
		//信息概要
		html+="<table id='creditSummary' class='table table-striped table-bordered table-hover'>"
				+"<caption><i class='glyphicon glyphicon-paperclip'></i> 信息概要：</caption>"
				+"<tr class='info'><th></th><th>信用卡</th><th>购房贷款</th><th>其他贷款</th></tr>"
				+"<tr>"
					+"<td>账户数</td>"
					+"<td>"+ report.creditRecord.creditSummary.creditCards.accountNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.housingLoans.accountNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.otherLoans.accountNum +"</td>"
				+"</tr>"
				+"<tr>"
					+"<td>未结清/未销户账户数</td>"
					+"<td>"+ report.creditRecord.creditSummary.creditCards.activeNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.housingLoans.activeNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.otherLoans.activeNum +"</td>"
				+"</tr>"
				+"<tr>"
					+"<td>发生过逾期的账户数</td>"
					+"<td>"+ report.creditRecord.creditSummary.creditCards.overdueNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.housingLoans.overdueNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.otherLoans.overdueNum +"</td>"
				+"</tr>"
				+"<tr>"
					+"<td>发生过90天以上逾期的账户数</td>"
					+"<td>"+ report.creditRecord.creditSummary.creditCards.overdue90Num +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.housingLoans.overdue90Num +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.otherLoans.overdue90Num +"</td>"
				+"</tr>"
				+"<tr>"
					+"<td>为他人担保笔数</td>"
					+"<td>"+ report.creditRecord.creditSummary.creditCards.guaranteeNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.housingLoans.guaranteeNum +"</td>"
					+"<td>"+ report.creditRecord.creditSummary.otherLoans.guaranteeNum +"</td>"
				+"</tr>"
			+"</table>";
		//信用卡明细
		var creditCardDetails = report.creditRecord.creditCardDetails;
		if (creditCardDetails && creditCardDetails.length!=0) {
			html+="<i class='glyphicon glyphicon-paperclip'></i> 信用卡明细：<br/><ol>";
			for(var i in creditCardDetails) {
				var creditCardDetail = creditCardDetails[i];
				if (creditCardDetail.type=="1") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>发生过逾期的贷记卡账户明细如下：</span>";
				} else if (creditCardDetail.type=="2") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>透支超过60天的准贷记卡帐户明细如下：</span>";
				} else if (creditCardDetail.type=="3") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>从未逾期过的贷记卡及透支未超过60天的准贷记卡账户明细如下：</span>";
				}
				for(var p in creditCardDetail.details) {
					html+="<li style='list-style-type: decimal; list-style-position: outside; font-size:12px;margin-left: 15px;margin-bottom: 4px;'>"+ creditCardDetail.details[p] +"</li>";
				}
			}
			html+="</ol>";
		}
		//住房贷款明细
		var housingLoanDetails = report.creditRecord.housingLoanDetails;
		if (housingLoanDetails && housingLoanDetails.length!=0) {
			html+="<i class='glyphicon glyphicon-paperclip'></i> 住房贷款明细：<br/><ol>";
			for(var i in housingLoanDetails) {
				var housingLoanDetail = housingLoanDetails[i];
				if (housingLoanDetail.type=="1") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>发生过的逾期的帐户明细如下：</span>";
				} else if (housingLoanDetail.type=="2") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>从未逾期过的帐户明细如下：</span>";
				}
				for(var p in housingLoanDetail.details) {
					html+="<li style='list-style-type: decimal; list-style-position: outside; font-size:12px;margin-left: 15px;margin-bottom: 4px;'>"+ housingLoanDetail.details[p] +"</li>";
				}
			}
			html+="</ol>";
		}
		//其他贷款明细
		var otherLoanDetails = report.creditRecord.otherLoanDetails;
		if (otherLoanDetails && otherLoanDetails.length!=0) {
			html+="<i class='glyphicon glyphicon-paperclip'></i> 其他贷款明细：<br/><ol>";
			for(var i in otherLoanDetails) {
				var otherLoanDetail = otherLoanDetails[i];
				if (otherLoanDetail.type=="1") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>发生过逾期的账户明细如下：</span>";
				} else if (otherLoanDetail.type=="2") {
					html+="<span style='margin-left:-20px;font-size:12px;font-weight:bold;line-height:30px;'>从未逾期过的账户明细如下：</span>";
				}
				for(var p in otherLoanDetail.details) {
					html+="<li style='list-style-type: decimal; list-style-position: outside; font-size:12px;margin-left: 15px;margin-bottom: 4px;'>"+ otherLoanDetail.details[p] +"</li>";
				}
			}
			html+="</ol>";
		}
		//为他人担保信息
		var guaranteeInfoDetails = report.creditRecord.guaranteeInfoDetails;
		if (guaranteeInfoDetails) {
			html+="<i class='glyphicon glyphicon-paperclip'></i> 为他人担保信息：<br/><ol>";
			for(var p in guaranteeInfoDetails) {
				html+="<li style='list-style-type: decimal; list-style-position: outside; font-size:12px;margin-left: 15px;margin-bottom: 4px;'>"+ guaranteeInfoDetails[p] +"</li>";
			}
			html+="</ol>";
		}
		
		/*
		* 查询记录
		*/
		html+="<h4 class='page-header' style='margin-top:15px;'>查询记录</h4>";
		//机构查询记录  & 个人查询记录
		var queryRecords = report.queryRecords;
		if (queryRecords && queryRecords.length!=0) {
			html+="<table id='orgQueryRecord' class='table table-striped table-bordered table-hover'>"
				+"<caption><i class='glyphicon glyphicon-paperclip'></i> 机构查询记录明细：</caption>"
				+"<tr class='info'>"
					+"<th>编号</th>"
					+"<th>查询类型</th>"
					+"<th>查询日期</th>"
					+"<th>查询操作员</th>"
					+"<th>查询原因</th>"
				+"</tr>";
			for(var i in queryRecords) {
				var queryRecord = queryRecords[i];
				html+="<tr>"
						+"<td>"+ queryRecord.num +"</td>"
						+"<td>"+ queryRecord.queryType +"</td>"
						+"<td>"+ queryRecord.queryDate +"</td>"
						+"<td>"+ queryRecord.operator +"</td>"
						+"<td>"+ queryRecord.queryCause +"</td>"
					  +"</tr>";
			}
			html+="</table>";
		}
		/* //个人查询记录
		var perQueryRecord = report.queryRecord.perRecord;
		if (perQueryRecord && perQueryRecord.length!=0) {
			html+="<table id='perQueryRecord' class='table table-striped table-bordered table-hover'>"
				+"<caption><i class='glyphicon glyphicon-paperclip'></i> 个人查询记录明细：</caption>"
				+"<tr class='info'>"
					+"<th>编号</th>"
					+"<th>查询日期</th>"
					+"<th>查询操作员</th>"
					+"<th>查询原因</th>"
				+"</tr>";
			for(var i in perQueryRecord) {
				html+="<tr>"
						+"<td>"+ perQueryRecord[i].num +"</td>"
						+"<td>"+ perQueryRecord[i].queryDate +"</td>"
						+"<td>"+ perQueryRecord[i].operator +"</td>"
						+"<td>"+ perQueryRecord[i].queryCause +"</td>"
					  +"</tr>";
			}
			html+="</table>";
		} */
		
		//导出pdf
		  html+="<div id='pdfFromJson' class='col-md-offset-5' style='margin-top: 40px;'>"
				+"<button type='button' class='btn btn-info' data-toggle='modal' data-target='#pdfFromJsonModal' id='viewPDFReportBtnInJsonPage'>查看PDF报告</button>"
			  	+"<div id='pdfFromJsonModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog'>"
				    +"<div class='modal-dialog modal-sm'>"
				      +"<div class='modal-content' style='margin-top:200px;'>"
					      +"<div class='modal-header'>"
					        +"<button id='pdfFromJsonModalCloseBtn' type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
					        +"<h5 class='modal-title'>PDF报告图片验证码：</h5>"
					      +"</div>"
					      +"<div class='modal-body' style='padding:20px;'>"
				      		+"<div>"
					     	 	+"<input type='text' class='form-control' name='verifycode' placeholder='请输入图片验证码' style='display:inline; width:150px;'>"
					      		+"<img id='codeImagePdfFromJson' alt='图片验证码' src='http://10.168.250.25/406d6b5e-e15c-437e-ad00-36d9cee760da.jpg'>"
					    	+"</div>"
					      +"</div>"
					      +"<div class='modal-footer' style='padding:9px;'>"
					        +"<button type='button' class='btn btn-info btn-sm' id='pdfFromJsonExcuteBtn'>&nbsp;&nbsp;&nbsp;&nbsp;执行&nbsp;&nbsp;&nbsp;&nbsp;</button>"
					      +"</div>"
				      +"</div>"
				    +"</div>"
				  +"</div>"
				+"</div>";
		
		$("#pbccrc_searchResults").html(html);
		$("#searchBox_big").css("display", "none");
		$("#pbccrc_searchResults").css("display", "block");
		//
		$("#jsonFromPdf").hide();
		$("#pbccrc_searchResults_pdf").css("display", "none");
	}
	//
	var loginCallback = function(res) {
		console.log("=======================loginCallback:res=========================\n"+res);
		
		$("#pbccrc_searchResults_pdf").html("");
		$("#pbccrc_searchResults_pdf").css("display", "none");
		
		res = JSON.parse(res);
		if (res.error) {
			alert(res.error.errorCode+"--"+res.error.errorName+": "+res.error.errorMsg);
		}
		if (res.data.statusCode!="0") {
			alert(res.data.message);
			window.location.reload();
			$("input[name='reportCategory'][value='pdf']").click();
			return;
		}
		loggedCookiesStr = JSON.stringify(res.data.cookies);
		//
		getPDFReportPage();
	}
	//
	var getPDFReportPage = function(){
		var username = $("#auth_fo input[name='username']").val();
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/logged/getpdfreportpage", {username:username, cookies:loggedCookiesStr, isDebug:true}, null, function(resData){
			console.log("=======================/logged/getpdfreportpage:resData=========================\n"+resData);
			resData = JSON.parse(resData);
			if (resData.error) {
				alert(resData.error.errorCode+"--"+resData.error.errorName+": "+resData.error.errorMsg);
			}
			
			if (resData.data.statusCode=="6") { //缓存中数据
				var html = "<div style='margin-left:6%;'>";
				html+="<object data='"+ resData.data.reportFileURL +"' type='application/pdf' style='width:90%;height:1130px;'></object></div>";
				//查看网页报告模态框
				html+="<div id='jsonFromPdf' class='col-md-offset-5' style='margin-top: 20px;'>"
						+"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#jsonFromPdfModal'>查看网页报告</button>"
					  	+"<div id='jsonFromPdfModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog'>"
						    +"<div class='modal-dialog modal-sm'>"
						      +"<div class='modal-content' style='margin-top:200px;'>"
							      +"<div class='modal-header'>"
							        +"<button id='jsonFromPdfModalCloseBtn' type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
							        +"<h5 class='modal-title'>手机授权码：</h5>"
							      +"</div>"
							      +"<div class='modal-body' style='padding:20px;'>"
							      	+"<div class='input-group' style='width:200px;'>"
							      		+"<input type='text' class='form-control' placeholder='请输入授权码' name='tradecode'>"
							      		+"<span class='input-group-btn'>"
							        		+"<button class='btn btn-info' type='button' id='jsonFromPdfExcuteBtn'>执行</button>"
							      		+"</span>"
							    	+"</div>"
							      +"</div>"
							      +"<div class='modal-footer' style='padding:17px;'>"
							      +"</div>"
						      +"</div>"
						    +"</div>"
						  +"</div>"
						+"</div>";
				$("#pbccrc_searchResults_pdf").css("display", "block");
				$("#pbccrc_searchResults_pdf").html(html);
				$("#searchBox_big2").css("display", "none");
				return;
			}
			
			if (resData.data.statusCode!="0") {
				alert(resData.data.message);
				window.location.reload();
				$("input[name='reportCategory'][value='pdf']").click();
				return;
			}
			//
			loggedCookiesStr = JSON.stringify(resData.data.cookies);
			//
			$("#searchBox_big").css("display", "none");
			$("#pdf_verifycode_fo input[name='verifycode']").val(resData.data.codeValue);
			$("#pdf_verifycode_codeImage").attr("src", resData.data.codeImageUrl);
			$("#searchBox_big2").css("display", "block");
		}, pbccrcErrorBack, false); //同步
	}
	var getPDFReportPage2 = function(){
		var username = $("#auth_fo input[name='username']").val();
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/logged/getpdfreportpage", {username:username, cookies:loggedCookiesStr, isDebug:true}, null, function(resData){
			console.log("=======================/logged/getpdfreportpage:resData=========================\n"+resData);
			resData = JSON.parse(resData);
			if (resData.error) {
				alert(resData.error.errorCode+"--"+resData.error.errorName+": "+resData.error.errorMsg);
			}
			
			if (resData.data.statusCode=="6") { //缓存中数据
				var html = "<div style='margin-left:6%;'>";
				html+="<object data='"+ resData.data.reportFileURL +"' type='application/pdf' style='width:90%;height:1130px;'></object></div>";
				//查看网页报告模态框
				html+="<div id='jsonFromPdf' class='col-md-offset-5' style='margin-top: 20px;'>"
						+"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#jsonFromPdfModal'>查看网页报告</button>"
					  	+"<div id='jsonFromPdfModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog'>"
						    +"<div class='modal-dialog modal-sm'>"
						      +"<div class='modal-content' style='margin-top:200px;'>"
							      +"<div class='modal-header'>"
							        +"<button id='jsonFromPdfModalCloseBtn' type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
							        +"<h5 class='modal-title'>手机授权码：</h5>"
							      +"</div>"
							      +"<div class='modal-body' style='padding:20px;'>"
							      	+"<div class='input-group' style='width:200px;'>"
							      		+"<input type='text' class='form-control' placeholder='请输入授权码' name='tradecode'>"
							      		+"<span class='input-group-btn'>"
							        		+"<button class='btn btn-info' type='button' id='jsonFromPdfExcuteBtn'>执行</button>"
							      		+"</span>"
							    	+"</div>"
							      +"</div>"
							      +"<div class='modal-footer' style='padding:17px;'>"
							      +"</div>"
						      +"</div>"
						    +"</div>"
						  +"</div>"
						+"</div>";
				$("#pbccrc_searchResults_pdf").css("display", "block");
				$("#pbccrc_searchResults_pdf").html(html);
				$("#searchBox_big2").css("display", "none");
				
				$("#pbccrc_searchResults").html("");
				$("#pbccrc_searchResults").css("display", "none");
				return;
			}
			
			if (resData.data.statusCode!="0") {
				alert(resData.data.message);
				window.location.reload();
				return;
			}
			//
			loggedCookiesStr = JSON.stringify(resData.data.cookies);
			//
			$("#codeImagePdfFromJson").attr("src", resData.data.codeImageUrl);
			$("#pdfFromJsonModal input[name='verifycode']").val(resData.data.codeValue);
		}, pbccrcErrorBack, false); //同步
	}
	//
	var excuteGetPDFCreditCallback = function(res){
		console.log("=======================excuteGetPDFCreditCallback:res=========================\n"+res);
		res = JSON.parse(res);
		if (res.data.statusCode=="5") { //频率限制
			alert(res.data.message);
			window.location.reload();
			return;
		}
		if (res.data.statusCode!="0") {
			alert(res.data.message);
			getPDFReportPage();
			$("#searchBox_big").css("display", "none");
			return;
		}
		//
		showPDFCreditData(res);////
	}
	//
	var excuteGetPDFCreditCallback2 = function(res){
		console.log("=======================excuteGetPDFCreditCallback2:res=========================\n"+res);
		res = JSON.parse(res);
		if (res.error) {
			alert(res.error.errorCode+"--"+res.error.errorName+": "+res.error.errorMsg);
		}
		if (res.data.statusCode!="0") {
			alert(res.data.message);
			getPDFReportPage();
			$("#searchBox_big").css("display", "none");
			$("#searchBox_big2").css("display", "none");
			return;
		}
		//
		showPDFCreditData(res);////
		$("#pbccrc_searchResults").css("display", "none");
	}
	var showPDFCreditData = function(res) {
		loggedCookiesStr = res.data.loggedCookiesJson;
		//
		var html = "<div style='margin-left:6%;'>";
		html+="<object data='"+ /* "http://10.168.250.25/a63783f3-0ea8-45f0-8c06-6a0d04d484ae.pdf" */ res.data.reportFileURL +"' type='application/pdf' style='width:90%;height:1130px;'></object></div>";
		//查看网页报告模态框
		html+="<div id='jsonFromPdf' class='col-md-offset-5' style='margin-top: 20px;'>"
				+"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#jsonFromPdfModal'>查看网页报告</button>"
			  	+"<div id='jsonFromPdfModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog'>"
				    +"<div class='modal-dialog modal-sm'>"
				      +"<div class='modal-content' style='margin-top:200px;'>"
					      +"<div class='modal-header'>"
					        +"<button id='jsonFromPdfModalCloseBtn' type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
					        +"<h5 class='modal-title'>手机授权码：</h5>"
					      +"</div>"
					      +"<div class='modal-body' style='padding:20px;'>"
					      	+"<div class='input-group' style='width:200px;'>"
					      		+"<input type='text' class='form-control' placeholder='请输入授权码' name='tradecode'>"
					      		+"<span class='input-group-btn'>"
					        		+"<button class='btn btn-info' type='button' id='jsonFromPdfExcuteBtn'>执行</button>"
					      		+"</span>"
					    	+"</div>"
					      +"</div>"
					      +"<div class='modal-footer' style='padding:17px;'>"
					      +"</div>"
				      +"</div>"
				    +"</div>"
				  +"</div>"
				+"</div>";
		$("#pbccrc_searchResults_pdf").css("display", "block");
		$("#pbccrc_searchResults_pdf").html(html);
		$("#searchBox_big2").css("display", "none");
	}
	
	//点击执行按钮
	$("#excute_btn").on("click", function(){
		var authFormParams = collectParam("#auth_fo");
		authFormParams.cookies = loginpageCookiesStr;
		authFormParams.isDebug = true;
		console.log("======================authFormParams:======================\n" + JSON.stringify(authFormParams));
		if (authFormParams.reportCategory=="json") {
			$("#searchBox_big").css("display", "none");
			$("#pbccrc_searchResults").html("<div class='sr-zql-loading'><img src='/data/static/imgs/icon/loading.gif'>loading...</div>");
			$("#pbccrc_searchResults").css("display", "block");
			ajaxTransObj("post", ROOTPATH+"/api/pbccrc/getcredit", authFormParams, null, excuteGetcreditCallback, pbccrcErrorBack, true);
		} else if (authFormParams.reportCategory=="pdf") {
			$("#searchBox_big").css("display", "none");
			$("#pbccrc_searchResults_pdf").html("<div class='sr-zql-loading'><img src='/data/static/imgs/icon/loading.gif'>loading...</div>");
			$("#pbccrc_searchResults_pdf").css("display", "block");
			ajaxTransObj("post", ROOTPATH+"/api/pbccrc/login", authFormParams, null, loginCallback, pbccrcErrorBack, true);
		}
	});
	
	//点击执行2按钮[getpdf]
	$("#excute2_btn").on("click", function(){
		var pdfVerifycodeFormParams = collectParam("#pdf_verifycode_fo");
		pdfVerifycodeFormParams.username = $("#auth_fo input[name='username']").val();
		pdfVerifycodeFormParams.cookies = loggedCookiesStr;
		pdfVerifycodeFormParams.isDebug = true;
		console.log("===================pdfVerifycodeFormParams:==================\n" + JSON.stringify(pdfVerifycodeFormParams));
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/logged/getpdfcredit", pdfVerifycodeFormParams, null, excuteGetPDFCreditCallback, pbccrcErrorBack, true);
	});
	
	//点击jsonFromPdfExcuteBtn
	$("#pbccrc_searchResults_pdf").delegate("#jsonFromPdfExcuteBtn", "click", function(){
		var tradecode = $("#jsonFromPdf input[name='tradecode']").val();
		//
		$("#jsonFromPdfModalCloseBtn").click(); //去掉遮罩
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/logged/getcredit", {cookies:loggedCookiesStr, tradecode:tradecode, isDebug:true}, null, excuteGetcreditCallback2, pbccrcErrorBack, true);
	});
	
	//点击查看PDF报告
	$("#pbccrc_searchResults").delegate("#viewPDFReportBtnInJsonPage", "click", function(){
		getPDFReportPage2();
	});
	//点击pdfFromJsonExcuteBtn
	$("#pbccrc_searchResults").delegate("#pdfFromJsonExcuteBtn", "click", function(){
		var verifycode = $("#pdfFromJson input[name='verifycode']").val();
		var username = $("#auth_fo input[name='username']").val();
		$("#pdfFromJsonModalCloseBtn").click(); //去遮罩
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/logged/getpdfcredit", {username:username, cookies:loggedCookiesStr, verifycode:verifycode, isDebug:true}, null, excuteGetPDFCreditCallback2, pbccrcErrorBack, true);
	});
</script>
</body>
</html>