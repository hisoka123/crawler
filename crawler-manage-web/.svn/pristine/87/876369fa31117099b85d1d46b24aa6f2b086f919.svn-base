<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<link href="${ctx}/static/css/user/reg.css" rel="stylesheet">
<jsp:include page="${headerPath}"></jsp:include>
<body>
    <jsp:include page="${navPath}"></jsp:include>

	<div class="jumbotron sr-zql-jumbotron-margintop">
      <div class="container">
        <h2>新用户注册</h2> 
      </div>
    </div>

    <div class="box-login">
	<div class="box-form">
		<form action="${ctx}/reg" method="post" id="regForm">
			<div class="form-group">
				<label for="loginName">登录名</label>
				<input type="text" class="form-control" id="loginName" name="loginName" value="${user.loginName}"  placeholder="登录名">
			</div>
			<div class="form-group">
				<label for="name">用户名</label>
				<input type="text" class="form-control" id="name" name="name" value="${user.name}"  placeholder="用户名">
			</div>
			<div class="form-group">
				<label for="plainPassword">密码</label>
				<input type="password" class="form-control" id="plainPassword" name="plainPassword" placeholder="密码">
			</div>
			<div class="form-group"> 
				 <label>
				 	<input type="radio" name="role"  value="2" checked> 普通用户
				    <input type="radio" name="role"  value="1"> 管理员 
				  </label>
			</div>
			<button type="button" class="btn btn-default" id ="fromsubmit">提交</button>
		</form>


        </div>
    </div>
    <jsp:include page="${panel}"></jsp:include>
    <jsp:include page="${footerPath}"></jsp:include>
    <!-- /container -->

    <!-- script -->
    <script src="${ctx}/static/js/jquery-ui.min.js"></script>
    <script src="${ctx}/static/js/admin/common.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	  $("#fromsubmit").click( function () { 
		  var loginName = $("#loginName").val();
		  var name = $("#name").val();
		  var plainPassword = $("#plainPassword").val();
		  var actionUrl = "${ctx}/reg/checkIsAbleReg";
		  var isAbleReg = false;
		  if(name==null||name==''){
			  var noticeContent = "用户名不能为空！";
	          $("#noticeContent").html(noticeContent);
	          $("#noticeModal").modal();
		  }else if(plainPassword==null||plainPassword==''){
			  var noticeContent = "密码不能为空！";
	          $("#noticeContent").html(noticeContent);
	          $("#noticeModal").modal();
		  }else if(loginName==null||loginName==''){
			  var noticeContent = "登录名不能为空！";
	          $("#noticeContent").html(noticeContent);
	          $("#noticeModal").modal();
		  }else{
			  $.get(actionUrl, { loginName:loginName } ,function(result){
				  //alert("Data Loaded: " + result.data);
				  if(!result.data){
					  var noticeContent = "该用户已经被注册，请更换登录名！";
			          $("#noticeContent").html(noticeContent);
			          $("#noticeModal").modal();
				  }else{
					  $("#regForm").submit();
				  }
			  });
		  }
		  
		   
	  });
});

	 

</script>
</body>
</html>
