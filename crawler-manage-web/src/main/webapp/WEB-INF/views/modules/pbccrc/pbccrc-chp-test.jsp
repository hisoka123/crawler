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
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     
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
                         
                    </div>
                    
                    <!--搜索结果  -->
                    <div id="result" data-name="pbccrc_searchResults" class="row">
                    	
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
	var excuteLoginCallback = function(res) {
		console.log("======================excuteLoginCallback:res======================\n" + res);
		res = JSON.parse(res);
		
		if (res.data.statusCode!="0") {
			alert("登陆失败！");
			return;
		}
		alert("登陆成功，登陆成功的返回数据见浏览器控制台");
		var authFormParams = collectParam("#auth_fo");
		authFormParams.cookies = JSON.stringify(res.data.cookies);
		authFormParams.isDebug = true;
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/chp3/logged/getcredit", authFormParams, null, excuteChpGetCreditCallback, excuteChpGetCreditErrorback, false);
	}
	var excuteLoginErrorback = function(res) {
		console.log("======================excuteLoginErrorback:res======================\n" + res);
	}
	

	//
	var excuteChpGetCreditCallback = function(res) {
		console.log("======================excuteChpGetCreditCallback:res======================\n" + res);
		$("#searchBox_big").css("display", "none");
		$("#result").html(res);
	}
	var excuteChpGetCreditErrorback = function(res) {
		console.log("======================excuteChpGetCreditErrorback:res======================\n" + res);
		$("#searchBox_big").css("display", "none");
		$("#result").html(res);
	}
	
	
	//点击执行按钮
	$("#excute_btn").on("click", function(){
		var authFormParams = collectParam("#auth_fo");
		authFormParams.cookies = loginpageCookiesStr;
		authFormParams.isDebug = true;
		console.log("======================authFormParams:======================\n" + JSON.stringify(authFormParams));
		
		$("#pbccrc_searchResults").html("<div class='sr-zql-loading'><img src='/data/static/imgs/icon/loading.gif'>loading...</div>");
		$("#pbccrc_searchResults").css("display", "block");
		ajaxTransObj("post", ROOTPATH+"/api/pbccrc/login", authFormParams, null, excuteLoginCallback, excuteLoginErrorback, false); //同步
	});
	
</script>
</body>
</html>