<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
 <nav class="navbar sr-zql-navbar-inverse navbar-fixed-top">
 <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only"></span>
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand sr-zql-navbar-brand" href="${ctx}/admin">数据服务平台-后台管理系统</a>
        </div>
        <div class="navbar-collapse collapse" role="navigation">
          <ul class="nav navbar-nav navbar-right hidden-sm">
            <shiro:guest>
              <li>
                   <div class="sr-zql-navbar-right">您是我们的用户吗?&nbsp;&nbsp;&nbsp;&nbsp;<a href="/reg">注册</a>&nbsp;/&nbsp;<a href="/login">登录</a></div>
	         </li>
            </shiro:guest>
            <shiro:user>  
	            <li id="fat-menu" class="dropdown">
	              <a id="drop3" href="#" style="color:#fff" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
	                 <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <shiro:principal property="name"/>
	                 <span class="caret"></span>
	              </a>
	              <ul class="dropdown-menu" aria-labelledby="drop3">
	                <li><a href="${ctx}/sui/index">集群监控</a></li>
                      <shiro:hasRole name="系统管理员">
                      <li><a href="${ctx}/tools/wiki/add">文档管理</a></li>
                      </shiro:hasRole>
                      <shiro:hasRole name="普通用户">
                          <li><a href="${ctx}/tools/wiki">查看文档</a></li>
                      </shiro:hasRole>
			        <li><a href="#">个人设置</a></li>
			        <li role="separator" class="divider"></li>
			        <li><a href="${ctx}/logout">退出登录</a></li>
	              </ul>
	            </li>
            </shiro:user>
          </ul>
        </div>
      </div>
      
    </nav>
		
          		