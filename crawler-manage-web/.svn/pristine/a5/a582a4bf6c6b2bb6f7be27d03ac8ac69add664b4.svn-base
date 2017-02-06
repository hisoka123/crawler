<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>

<!-- 登录模态框Model -->
<div class="modal fade bs-example-modal-sm" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title" id="loginModalLabel">请登录：</h5>
      </div>
      <div class="modal-body">
        <form id="loginForm" action="${ctx}/login" method="post">
		  <div class="form-group">
		    <label for="username">name：</label>
		    <input type="text" class="form-control" id="username" name="username" placeholder="name">
		  </div>
		  <div class="form-group">
		    <label for="password">password：</label>
		    <input type="password" class="form-control" id="password" name="password" placeholder="password">
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" id="regist-btn" class="btn btn-default">Regist</button>
        <button type="button" id="login-btn" class="btn btn-primary">Submit</button>
      </div>
    
    </div>
  </div>
</div>

<script type="text/javascript">
	$("#login-btn").on("click", function() {
		$("#loginForm").submit();
	});
</script>