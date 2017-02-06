<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<link href="${ctx}/static/css/user/reg.css" rel="stylesheet">
<html lang="zh-CN">
<jsp:include page="../header.jsp"></jsp:include>

<body>
<jsp:include page="../nav.jsp"></jsp:include>
<shiro:user>
    <%--此处最好用过滤器实现跳转--%>
    <script type="text/javascript">
        $(document).ready(function(){
            window.location.href="/index";
        })
    </script>
</shiro:user>
<div class="jumbotron sr-zql-jumbotron-margintop">
    <div class="container">
        <h2>用户登录</h2>
    </div>
</div>
<div class="box-login">
    <div class="box-form">
        <form action="${ctx}/login" method="post">
            <div class="form-group">
                <label for="username">登录名</label>
                <input type="text" class="form-control" id="username" name="username" value="${user.username}"  placeholder="登录名">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>

        <jsp:include page="../footer.jsp"></jsp:include>
    </div>
</div>

<!-- /container -->

</body>
</html>
