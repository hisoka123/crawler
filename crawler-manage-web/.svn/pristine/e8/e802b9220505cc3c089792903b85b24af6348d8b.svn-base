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
				<h2 class="page-header sr-zql-page-header">项目</h2>
				
				<!-- Topology summary -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> 项目简介：</caption>
					<tr class="info">
						<th><span data-toggle="tooltip" data-placement="right" title="The name given to the topology by when it was submitted.">Name</span></th>
						<th><span data-toggle="tooltip" data-placement="right" data-original-title="The unique ID given to a Topology each time it is launched.">Id</span></th>
						<th><span data-toggle="tooltip" data-placement="top" data-original-title="The status can be one of ACTIVE, INACTIVE, KILLED, or REBALANCING.">Status</span></th>
						<th><span data-toggle="tooltip" data-placement="top" data-original-title="The time since the Topology was submitted.">Uptime</span></th>
						<th><span data-toggle="tooltip" data-placement="top" data-original-title="The number of Workers (processes).">Num workers</span></th>
						<th><span data-toggle="tooltip" data-placement="top" data-original-title="Executors are threads in a Worker process.">Num executors</span></th>
						<th><span data-toggle="tooltip" data-placement="left" data-original-title="A Task is an instance of a Bolt or Spout. The number of Tasks is almost always equal to the number of Executors.">Num tasks</span></th>
					</tr>
					<tr>
						<td>${dataMap.name }</td>
						<td>${dataMap.id }</td>
						<td>${dataMap.status }</td>
						<td>${dataMap.uptime }</td>
						<td><fmt:formatNumber value="${dataMap.workersTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.executorsTotal }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber value="${dataMap.tasksTotal }" maxFractionDigits="0"/></td>
					</tr>
				</table>
				</div>
				
				<!-- Topology actions -->
				<!-- <div class="sr-zal-topo-action-container">
					<div style="color:#777;"><h5><i class="glyphicon glyphicon-paperclip"></i> Topology actions：</h5></div>
					<div class="btn-group" role="group">
						<div class="btn-group" role="group"><button class="btn btn-default">Activate</button></div>
						<div class="btn-group" role="group"><button class="btn btn-default">Deactivate</button></div>
						<div class="btn-group" role="group"><button class="btn btn-default">Rebalance</button></div>
						<div class="btn-group" role="group"><button class="btn btn-default">Kill</button></div>
					</div>
				</div> -->
				
				<!-- Topology stats -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> Topology stats：</caption>
					<tr class="info">
						<th><span data-toggle="tooltip" data-placement="right" title="The past period of time for which the statistics apply. Click on a value to set the window for this page.">Window</span></th>
						<th><span data-toggle="tooltip" data-placement="right" title="The number of Tuples emitted.">Emitted</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted that sent to one or more bolts.">Transferred</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title='The average time a Tuple "tree" takes to be completely processed by the Topology. A value of 0 is expected if no acking is done.'>Complete latency (ms)</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title='The number of Tuple "trees" successfully processed. A value of 0 is expected if no acking is done.'>Acked</span>
						<th><span data-toggle="tooltip" data-placement="top" title='The number of Tuple "trees" that were explicitly failed or timed out before acking was completed. A value of 0 is expected if no acking is done.'>Failed</span>
					</tr>
					<c:forEach var="topologyStat" items="${dataMap.topologyStats }">
				    <tr>
				        <%-- <td><a href="http://10.168.250.21:8080/topology.html?id=${dataMap.encodedId }&amp;window=${topologyStat.window }">${topologyStat.windowPretty }</a></td> --%>
				        <td>${topologyStat.windowPretty}</td>
				        <td><fmt:formatNumber value="${topologyStat.emitted }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${topologyStat.transferred }" maxFractionDigits="0"/></td>
				        <td>${topologyStat.completeLatency }</td>
				        <td><fmt:formatNumber value="${topologyStat.acked }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${topologyStat.failed }" maxFractionDigits="0"/></td>
				    </tr>
				    </c:forEach>
				</table>
				</div>
				
				<!-- Spouts (All time) -->
				<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> Spouts (All time)：</caption>
					<tr class="info">
						<th><span data-toggle="tooltip" data-placement="right" title="The ID assigned to a the Component by the Topology. Click on the name to view the Component's page.">Id</span></th>
						<th><span data-toggle="tooltip" data-placement="right" title="Executors are threads in a Worker process.">Executors</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="A Task is an instance of a Bolt or Spout. The number of Tasks is almost always equal to the number of Executors.">Tasks</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted.">Emitted</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted that sent to one or more bolts.">Transferred</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title='The average time a Tuple "tree" takes to be completely processed by the Topology. A value of 0 is expected if no acking is done.'>Complete latency (ms)</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title='The number of Tuple "trees" successfully processed. A value of 0 is expected if no acking is done.'>Acked</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted.">Failed</span></th>
						<th>Error Host</th>
						<th>Error Port</th>
						<th>Last error</th>
					</tr>
					<c:forEach var="spout" items="${dataMap.spouts }">
					<tr>
				        <td><a href="${ctx}/sui/spout?id=${spout.encodedSpoutId }&topology_id=${dataMap.encodedId }">${spout.spoutId }</a></td>
				        <td><fmt:formatNumber value="${spout.executors }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${spout.tasks }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${spout.emitted }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${spout.transferred }" maxFractionDigits="0"/></td>
				        <td>${spout.completeLatency }</td>
				        <td><fmt:formatNumber value="${spout.acked }" maxFractionDigits="0"/></td>
				        <td><fmt:formatNumber value="${spout.failed }" maxFractionDigits="0"/></td>
				        <td>${spout.errorHost }</td>
				        <td><a href="${spout.errorWorkerLogLink }"><fmt:formatNumber value="${spout.errorPort }" maxFractionDigits="0" pattern="#"/></a></td>
				        <td><span id="" class="errorSpan">${spout.lastError }</span></td>
				    </tr>
				    </c:forEach>
				</table>
				</div>
				
				<!-- Bolts (All time) -->
				<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> Bolts (All time)：</caption>
					<tr class="info">
						<th><span data-toggle="tooltip" data-placement="right" title="The ID assigned to a the Component by the Topology. Click on the name to view the Component's page.">Id</span></th>
						<th><span data-toggle="tooltip" data-placement="right" title="Executors are threads in a Worker process.">Executors</span></th>
						<th><span data-toggle="tooltip" data-placement="right" title="A Task is an instance of a Bolt or Spout. The number of Tasks is almost always equal to the number of Executors.">Tasks</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted.">Emitted</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples emitted that sent to one or more bolts.">Transferred</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="If this is around 1.0, the corresponding Bolt is running as fast as it can, so you may want to increase the Bolt's parallelism. This is (number executed * average execute latency) / measurement time.">Capacity (last 10m)</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The average time a Tuple spends in the execute method. The execute method may complete without sending an Ack for the tuple."> Execute latency (ms)</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of incoming Tuples processed.">Executed</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The average time it takes to Ack a Tuple after it is first received.  Bolts that join, aggregate or batch may not Ack a tuple until a number of other Tuples have been received.">Process latency (ms)</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of Tuples acknowledged by this Bolt.">Acked</span></th>
						<th><span data-toggle="tooltip" data-placement="top" title="The number of tuples Failed by this Bolt.">Failed</span></th>
						<th>Error Host</th>
						<th>Error Port</th>
						<th>Last error</th>
					</tr>
					<c:forEach var="bolt" items="${dataMap.bolts }">
					<tr>
					    <td><a href="${ctx}/sui/bolt?id=${bolt.encodedBoltId }&amp;topology_id=${dataMap.encodedId }">${bolt.boltId }</a></td>
					    <td><fmt:formatNumber value="${bolt.executors }" maxFractionDigits="0"/></td>
					    <td><fmt:formatNumber value="${bolt.tasks }" maxFractionDigits="0"/></td>
					    <td><fmt:formatNumber value="${bolt.emitted }" maxFractionDigits="0"/></td>
					    <td><fmt:formatNumber value="${bolt.transferred }" maxFractionDigits="0"/></td>
					    <td>${bolt.capacity }</td>
					    <td>${bolt.executeLatency }</td>
					    <td><fmt:formatNumber value="${bolt.executed }" maxFractionDigits="0"/></td>
					    <td>${bolt.processLatency }</td>
					    <td><fmt:formatNumber value="${bolt.acked }" maxFractionDigits="0"/></td>
					    <td><fmt:formatNumber value="${bolt.failed }" maxFractionDigits="0"/></td>
					    <td>${bolt.errorHost }</td>
					    <td><a href="${bolt.errorWorkerLogLink }"><fmt:formatNumber value="${bolt.errorPort }" maxFractionDigits="0" pattern="#"/></a></td>
					    <td><span id="<fmt:formatNumber value="${bolt.errorLapsedSecs }" maxFractionDigits="0" pattern="#"/>" class="errorSpan">${bolt.lastError }</span></td>
					</tr>
					</c:forEach>
				</table>
				</div><br/>
				
				<!-- Topology Configuration -->
				<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> Topology Configuration：</caption>
					<tr class="info">
						<th>Key</th>
						<th>Value</th>
					</tr>
					<c:forEach var="configuration" items="${dataMap.configuration }">
					<tr>
						<td>${configuration.key }</td>
						<td>${configuration.value }</td>
					</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>			
	</div>
	
	<!-- 返回顶部 -->
	<div class="sr-zql-topret-up"></div>
	<div class="sr-zql-topret-down"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
	<script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			//工具提示
			$('[data-toggle="tooltip"]').tooltip();
			
			//返回
			$('.sr-zql-topret-down').on("click", function(){
				history.go(-1);
			});
		});
	</script>
</body>
</html>

