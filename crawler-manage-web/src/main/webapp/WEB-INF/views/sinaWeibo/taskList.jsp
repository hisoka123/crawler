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
	<div class="sina-content">
		<h3 style="text-align: center">定时任务管理页面</h3>
		<ul class="nav nav-pills">
		<br>
			<!-- <li><button id ="startScheduler" type="button" class="btn btn-success">启动任务调度器</button></li>
			<li><button id ="shutdownScheduler" type="button" class="btn btn-success">关闭任务调度器</button></li> -->
			<li><button id="pauseAll" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-pause">&nbsp;暂停所有任务</i></button></li>
			<li><button id="resumeAll" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-play">&nbsp;恢复所有任务</i></button></li>
			<li><button id="removeAll" type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-remove-circle">&nbsp;删除所有任务</i></button></li>
			<li><button id="addJob" type="button" data-target="#addJobModal"
					data-toggle="modal" class="btn btn-primary"><i
										class="glyphicon glyphicon-user">&nbsp;添加新任务</i></button></li>
		</ul>
		<br>
		<c:forEach var="jobBean" items="${jobList}" varStatus="jobStatus">
			<c:if test="${jobBean.jobStatus == '无'}">
				<div id="job_${jobStatus.count}" class="panel panel-primary">
			</c:if>
			<c:if test="${jobBean.jobStatus == '正常'}">
				<div id="job_${jobStatus.count}" class="panel panel-success">
			</c:if>
			<c:if test="${jobBean.jobStatus == '暂停'}">
				<div id="job_${jobStatus.count}" class="panel panel-warning">
			</c:if>
			<c:if test="${jobBean.jobStatus == '触发完成'}">
				<div id="job_${jobStatus.count}" class="panel panel-info">
			</c:if>
			<c:if test="${jobBean.jobStatus == '错误'}">
				<div id="job_${jobStatus.count}" class="panel panel-danger">
			</c:if>
			<c:if test="${jobBean.jobStatus == '阻塞'}">
				<div id="job_${jobStatus.count}" class="panel panel-default">
			</c:if>
			<div class="panel-heading">
				<div class="row">
					<div id="jobName_${jobStatus.count}" title="${jobBean.jobName}"
						class="col-md-3" >
						<h3 class="panel-title" style="margin : 8.2px 0px 0px 0px;"><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>&nbsp;任务：${jobBean.jobName}</h3>
					</div>

					<div class="col-md-2 col-md-offset-7">
						<div class="btn-group">
							<button type="button" class="btn btn-primary" onclick="trace('${jobBean.jobName}','${jobBean.jobGroup}')"><i
										class="glyphicon glyphicon-cog">&nbsp;追踪</i></button>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								<span class="caret"></span> <span class="sr-only"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="javascript:void(0)" data-toggle="modal"
									onclick="modifyTime(${jobStatus.count},this)"><i
										class="glyphicon glyphicon-pencil">&nbsp;修改触发时间</i></a></li>
							<c:if test="${jobBean.jobStatus == '正常'}">
								<li><a  href="javascript:void(0)"
									onclick="window.location.href=encodeURI('${ctx}/schedule/pauseJob?jobName=${jobBean.jobName}&&jobGroup=${jobBean.jobGroup}');"><i
										class="glyphicon glyphicon-pause">&nbsp;暂停定时任务</i></a></li>
							</c:if>
							<c:if test="${jobBean.jobStatus == '暂停'}">
								<li><a
									href="${ctx}/schedule/resumeJob?jobName=${jobBean.jobName}&&jobGroup=${jobBean.jobGroup}"><i
										class="glyphicon glyphicon-play">&nbsp;恢复定时任务</i></a></li>
							</c:if>																	
								<li><a
									href="${ctx}/schedule/removeJob?jobName=${jobBean.jobName}&&jobGroup=${jobBean.jobGroup}"><i
										class="glyphicon glyphicon-remove-circle">&nbsp;删除定时任务</i></a></li>
<%-- 								<li><a
									href="${ctx}/taskTrack/list?taskName=${jobBean.jobName}&&taskGroup=${jobBean.jobGroup}&&pageNumber=0&&pageSize=30"><i
										class="glyphicon glyphicon-dashboard">&nbsp;定时任务追踪</i></a></li> --%>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div id="jobGroup_${jobStatus.count}" title="${jobBean.jobGroup}"
						class="col-md-3">任务分组：${jobBean.jobGroup}</div>
					<div id="jobStatus_${jobStatus.count}" title="${jobBean.jobStatus}"
						class="col-md-2">任务状态：${jobBean.jobStatus}</div>
					<div id="cronExpression_${jobStatus.count}"
						title="${jobBean.cronExpression}" class="col-md-4 col-md-offset-1">时间表达式：${jobBean.cronExpression}</div>
				</div>
				<div class="row">
					<div id="previousFireTime_${jobStatus.count}"
						title="${jobBean.previousFireTime}" class="col-md-3 ">上次触发时间：${jobBean.previousFireTime}</div>
					<div id="nextFireTime_${jobStatus.count}"
						title="${jobBean.nextFireTime}" class="col-md-3">下次触发时间：${jobBean.nextFireTime}</div>
					<div id="jobClazz_${jobStatus.count}" title="${jobBean.jobClazz}"
						class="col-md-6">所属类：${jobBean.jobClazz}</div>
				</div>
				<div class="row">
					<div id="desc_${jobStatus.count}" title="${jobBean.desc}"
						class="col-md-12">备注：${jobBean.desc}</div>
				</div>
			</div>
	</div>

	</c:forEach>
	</div>


	<div class="modal fade" id="modifyJobTimeModal" tabindex="-1"
		role="dialog" aria-labelledby="modifyJobTimeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modifyJobTimeModalLabel">修改触发时间</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form"
						action="${ctx}/schedule/modifyJobTime"
						method="post">
						<div class="form-group">
							<label for="jobName" class="col-sm-2 control-label">任务名字</label>
							<div class="col-sm-10">
								<p id="jobName" class="form-control-static">${jobBean.jobName}</p>
								<input id="jobNameHidden" name="jobName" type="hidden">
							</div>
						</div>
						<div class="form-group">
							<label for="jobGroup" class="col-sm-2 control-label">任务分组</label>
							<div class="col-sm-10">
								<p id="jobGroup" class="form-control-static">${jobBean.jobGroup}</p>
								<input id="jobGroupHidden" name="jobGroup" type="hidden">
							</div>
						</div>
						<div class="form-group">
							<label for="cronExpression" class="col-sm-2 control-label">表达式</label>
							<div class="col-sm-10" data-toggle="tooltip" title="可参考cron时间表达式,例如每分钟触发一次:0 0/1 * * * ?">
								<input id="cronExpression" name="cronExpression" type="text"
									class="form-control" value="${jobBean.cronExpression}">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">提交更改</button>
						</div>
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="addJobModal" tabindex="-1" role="dialog"
		aria-labelledby="addJobModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addJobModalLabel">添加新任务</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form"
						action="${ctx}/schedule/addJob"
						method="post">
						<div class="form-group">
							<label for="jobNameAdd" class="col-sm-2 control-label">任务名称</label>
							<div class="col-sm-10" data-toggle="tooltip" title="定时任务名称和定时任务分组一起唯一确定一个任务，切忌重复">
								<input id="jobNameAdd" name="jobName" type="text" required="required"
									class="form-control" placeholder="输入新任务名称">
							</div>
						</div>
						<div class="form-group">
							<label for="jobGroupAdd" class="col-sm-2 control-label" >任务分组</label>
							<div class="col-sm-10" data-toggle="tooltip" title="定时任务名称和定时任务分组一起唯一确定一个任务，切忌重复">
								<input id="jobGroupAdd" name="jobGroup" type="text" required="required"
									class="form-control" placeholder="输入新任务分组名称">
							</div>
						</div>
						<div class="form-group">
							<label for="cronExpressionAdd" class="col-sm-2 control-label">表达式</label>
							<div class="col-sm-10" data-toggle="tooltip" title="可参考cron时间表达式,例如每分钟触发一次:0 0/1 * * * ?">
								<input id="cronExpressionAdd" name="cronExpression" type="text" required="required"
									class="form-control" placeholder="输入新任务时间表达式">
							</div>
						</div>
						<div class="form-group">
							<label for="jobClazzAdd" class="col-sm-2 control-label">所属类</label>
							<div class="col-sm-10" data-toggle="tooltip" title="添加定时任务类包及类名，格式如:package.Class">
								<input id="jobClazzAdd" name="jobClazz" type="text" required="required"
									class="form-control" placeholder="输入新任务所属类">
							</div>
						</div>
						<div class="form-group">
							<label for="descAdd" class="col-sm-2 control-label">任务描述</label>
							<div class="col-sm-10" data-toggle="tooltip" title="对该任务进行一些备注性质的描述，增强任务的可读性！">
								<input id="descAdd" name="desc" type="text" class="form-control"
									placeholder="输入新任务描述">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

			</div>
		</div>
		</div>				
	
		<script>
		 $(function() {
			 $("#startScheduler").click(function(){
		    	  location.href = "${ctx}/schedule/startScheduler"
		      });
			 //启动调度器
			  $("#startScheduler").click(function(){
		    	  location.href = "${ctx}/schedule/startScheduler"
		      });
			  //关闭调度器
		      $("#shutdownScheduler").click(function(){
		    	  location.href = "${ctx}/schedule/shutdownScheduler"
		      });
		      //暂停所有任务
		      $("#pauseAll").click(function(){
		    	  location.href = "${ctx}/schedule/pauseAll"
		      });
		      //恢复所有任务
		      $("#resumeAll").click(function(){
		    	  location.href = "${ctx}/schedule/resumeAll"
		      });
		      //删除所有任务
		      $("#removeAll").click(function(){
		    	  location.href = "${ctx}/schedule/removeAll"
		      });
		   });  
		
				$(function() {
					$("[data-toggle='popover']").popover();
				});
			/* 修改时间时为时间模态框赋值 */
				function modifyTime(count,obj)
		         {
					var jobName =  $("#jobName_"+count+" h3").text();
					var jobGroup =  $("#jobGroup_"+count).text();
					var cronExpression =  $("#cronExpression_"+count).text();
					$("#jobName").text(jobName.substring(4));
					$("#jobNameHidden").val(jobName.substring(4));
					$("#jobGroup").text(jobGroup.substring(5));
					$("#jobGroupHidden").val(jobGroup.substring(5));
					$("#cronExpression").val(cronExpression.substring(6));
		             obj.href = "#modifyJobTimeModal";
		         }
				$(function () { $("[data-toggle='tooltip']").tooltip(); });
				
				function trace(jobName,jobGroup){
					location.href = "${ctx}/taskTrack/list?taskName="+jobName+"&&taskGroup="+jobGroup+"&&pageNumber=0&&pageSize=30";
				}
			</script>
	
	<!-- 返回顶部 -->
	<div class="sr-zql-totop"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
	<script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
</body>
</html>

