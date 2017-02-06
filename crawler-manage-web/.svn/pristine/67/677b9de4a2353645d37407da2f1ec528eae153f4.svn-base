<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="com.module.aws.EvnConfig" %>
<%
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
EvnConfig evnConfig = (EvnConfig)ctx.getBean("evnConfig");    
%>
<style>
	.footer {
		text-align:center;
		bottom:0; 
		padding-bottom:6px;
		padding-top:5px;
	} 
</style>

<footer>
	<div class="col-md-12 footer"> 
		<hr/> 
		
		<%
		String evnType = evnConfig.getType(); 
		if("dev".equals(evnType.trim())){
			evnType = "调试环境"; 
		}
		if("prod".equals(evnType.trim())){
			evnType = "生产环境";  
		}
		if("test".equals(evnType.trim())){
			evnType = "测试环境";  
		}
		%>
		<%=evnType%>
	</div>
</footer>  