<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map, java.util.ArrayList"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<jsp:include page="../header.jsp"></jsp:include>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>

	<div class="container-fluid sr-zql-sui-container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<!-- 集群简介 -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> 集群简介：</caption>
					<tr class="info">
						<th>版本</th>
						<th>平台启动时间</th>
						<th>节点数</th>
						<th>被使用的并发数</th>
						<th>空闲的并发数</th>
						<th>总并发数</th>
						<th>执行者数</th>
						<th>任务数</th>
					</tr>
					<tr>
						<td>${dataMap.cluster.stormVersion }</td>
						<td>${dataMap.cluster.nimbusUptime }</td>
						<td><fmt:formatNumber value="${dataMap.cluster.supervisors }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.cluster.slotsUsed }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.cluster.slotsFree }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.cluster.slotsTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.cluster.executorsTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.cluster.tasksTotal }" maxFractionDigits="0"/></td>
					</tr>
				</table>
				</div>
				
				<!-- 拓扑图简介 -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> 项目简介：</caption>
					<tr class="info">
						<th>名称</th>
						<th>ID</th>
						<th>状态</th>
						<th>运行时间</th>
						<th>并发数</th>
						<th>执行者数</th>
						<th>任务数</th>
					</tr>
					<c:forEach var="topologie" items="${dataMap.topology.topologies }">
					<tr>
						<td><a href="${ctx}/sui/topology?id=${topologie.encodedId}">${topologie.name }</a></td>
						<td>${topologie.id }</td>
						<td>${topologie.status }</td>
						<td>${topologie.uptime }</td>
						<td><fmt:formatNumber value="${topologie.workersTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${topologie.executorsTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${topologie.tasksTotal }" maxFractionDigits="0"/></td>
					</tr>
					</c:forEach>
				</table>
				</div>
				
				<!-- 分布式节点简介 -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> 分布式节点简介：</caption>
					<tr class="info">
						<th>ID</th>
						<th>节点名</th>
						<th>运行时间</th>
						<th>并发数</th>
						<th>被使用的并发数</th>
					</tr>
					<c:forEach var="supervisor" items="${dataMap.supervisor.supervisors }">
					<tr>
						<td>${supervisor.id }</td>
						<td>${supervisor.host }</td>
						<td>${supervisor.uptime }</td>
						<td><fmt:formatNumber value="${supervisor.slotsTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${supervisor.slotsUsed }" maxFractionDigits="0"/></td>
					</tr>
					</c:forEach>
				</table>
				</div>
				
				<!-- 管理台配置 -->
				<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> 管理台配置：</caption>
					<tr class="info">
						<th>Key</th>
						<th>Value</th>
					</tr>
					<c:forEach var="configMap" items="${dataMap.config }">
					<tr>
						<td>${configMap.key }</td>
						<td>${configMap.value }</td>
					</tr>	
					</c:forEach>
					
					<!-- 以下是将config的double数据转换为int数据 的  替代方案-->
					<%-- <%
					Map<?,?> dataMap = (Map<?,?>)request.getAttribute("dataMap");
					Map<?,?> configMap = (Map<?,?>)dataMap.get("config");
					for (Map.Entry<?,?> entry: configMap.entrySet()) {
						String key = (String) entry.getKey();
						Object value = entry.getValue();
					%>
						<tr>
						<td><%=key %></td>
						<%
						if (value==null) {
						%>
						<td></td>
						<%	
						} else if (value instanceof Double) {
						%>
						<td><%=((Double)value).intValue()%></td>
						<%		
						} else if(value instanceof ArrayList && !((ArrayList<?>)value).isEmpty() && ((ArrayList<?>)value).get(0) instanceof Double) {
							ArrayList<Integer> showlist = new ArrayList<Integer>();
							int size = ((ArrayList<?>)value).size();
							for (int i=0; i<size; i++) {
								Object arrayele = ((ArrayList<?>)value).get(i);
								if (arrayele instanceof Double) {
									Integer showele = ((Double)arrayele).intValue();
									showlist.add(i,showele);
								}
							}
							System.out.println(showlist);
						%>
						<td><%=showlist.toString()%></td>
						<%
						} else if (value!=null){
						%>
						<td><%=value.toString()%></td>
						<%	
						}
						%>
						</tr>
					<%
					}
					%> --%>
						
				</table>
				</div>
			</div>
		</div>			
	</div>
	
	<!-- 返回顶部 -->
	<div class="sr-zql-totop"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
	<script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
</body>
</html>

