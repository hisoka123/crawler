<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${ctx}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${ctx}/static/css/sina.css"
	type="text/css" rel="stylesheet">
<link href="${ctx}/static/css/common.css"
	type="text/css" rel="stylesheet">
<link href="${ctx}/static/css/tree.css"
	type="text/css" rel="stylesheet">
<script
	src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
<script
	src="${ctx}/static/js/bootstrap.min.js"></script>
<script
	src="${ctx}/static/js/sina-common.js"></script>
<script src="${ctx}/static/js/tree.js"></script>

<link rel='icon' href='${logo}' type=‘image/x-ico’ />
<title>${title}--新浪微博</title>

</head>
<body>

	<div class="scm-head"><%@ include file="../head.jsp"%></div>
	<div class="sina-left"><%@ include file="leftMenu.jsp"%></div>
	<div class="sina-content">
		<h3 style="text-align: center">突发事件报警管理页面</h3>
		<br>
		<ul class="nav nav-pills">
			<li><button id="addJob" type="button" data-target="#addAlarm"
					data-toggle="modal" class="btn btn-primary"><i
										class="glyphicon glyphicon-user">&nbsp;添加报警任务</i></button></li>
		</ul>
		<br>
		<c:forEach var="alarm" items="${alarms}" varStatus="alarmStatus">
			<div id="alarm_${alarmStatus.count}" class="panel panel-warning">
				<div class="panel-heading">
					<div class="row">
						<div id="name_${alarmStatus.count}" title="${alarm.name}"
							class="col-md-5">
							<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;报警名称：${alarm.name}</span>
						</div>
						<div id="updatime_${alarm.updatime}" title="${alarm.updatime}"
							class="col-md-5">
							<span class="glyphicon glyphicon-time" aria-hidden="true">&nbsp;更新时间：${alarm.updatime}</span>
						</div>
						<div class="col-md-2 ">
						<div class="btn-group">
							<button type="button" class="btn btn-primary"><i
										class="glyphicon glyphicon-cog">&nbsp;操作</i></button>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								<span class="caret"></span> <span class="sr-only"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="javascript:void(0)" onclick="modifyAlarm(${alarm.id})"><i
										class="glyphicon glyphicon-pencil">&nbsp;修改报警任务</i></a></li>
								<li><a
									href="${ctx}/sinaWeibo/alarmRemove?id=${alarm.id}"><i
										class="glyphicon glyphicon-remove-circle">&nbsp;删除报警任务</i></a></li>
											<li><a
									href="${ctx}/sinaWeibo/alarmBurstoutList?pageNumber=0&&pageSize=5&&id=${alarm.id}"><i
										class="glyphicon glyphicon-eye-open">&nbsp;查看报警微博</i></a></li>
										
							</ul>
						</div>
					</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div id="alarm_${alarmStatus.count}" class="col-md-12">
							邮箱：
							<c:forEach var="email" items="${alarm.emails}"
								varStatus="emailStatus">${email.address}&nbsp;&nbsp;</c:forEach>
						</div>
					</div>
					<div class="row">
						<div id="regions_${alarmStatus.count}" class="col-md-12">
							地区：${alarmIdMap[alarm.id]}
								<input id="emailshidden_${alarmStatus.count}"  type="hidden" value="${alarm.id}">
						</div>
					</div>
				</div>
			</div>

		</c:forEach>

	</div>

	<div class="modal fade" id="addAlarm" tabindex="-1" role="dialog"
		aria-labelledby="addAlarmlLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addAlarmlLabel">添加新报警任务</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form"
						action="${ctx}/sinaWeibo/alarmAdd"
						method="post">
						<div class="form-group">
							<label for="alarmName" class="col-sm-2 control-label">报警名称</label>
							<div class="col-sm-10" data-toggle="tooltip" title="为该报警任务起一个名字">
								<input id="alarmName" name="alarmName" type="text" required='required'
									class="form-control" placeholder="在此填写报警名称">
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">邮箱设置</label>
							<div class="col-sm-10"  data-toggle="tooltip" title="有所选地区报警事件将向该邮箱发邮件">
								<div>
									<input id="email" name="email" type="email" required="required"
										class="form-control" placeholder="在此填写邮箱地址">
								</div>
								<div>
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span><span
										class="glyphicon glyphicon-minus" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="region" class="col-sm-2 control-label">地区设置</label>
							<div class="col-sm-10" data-toggle="tooltip" title="有所选地区报警事件将向报警邮箱发邮件">
								<input id="region" name="regionName" type="text" required='required'
									data-target="#addRegion" data-toggle="modal"
									class="form-control" placeholder="用鼠标点击此处选择报警地区"> <input id="regionHidden"
									name="region" type="hidden">
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


	<div class="modal fade" id="addRegion" tabindex="-1" role="dialog"
		aria-labelledby="addRegionLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addRegionLabel">添加地区</h4>
				</div>
				<div class="modal-body">
					<div class="tree well">
						<ul>
							<li value="-2"><span><i
									class="glyphicon glyphicon-folder-open"></i>中国</span> <input
								type="hidden" value="中国"> <a href="javascript:void(0);"><span><i id="glyphicon-ok_-2}"
										class="glyphicon glyphicon-ok"></i></span></a>
								<ul>
									<c:forEach var="region" items="${regionSet}"
										varStatus="regionMapStatus">
										<li style="display: none" value="${region.key.id}"><span><i
												class="glyphicon glyphicon-eye-open"></i>
												${region.key.regionChiName}</span> <input type="hidden"
											value="${region.key.regionChiName}"> <a
											href="javascript:void(0)"><span><i id="glyphicon-ok_${region.key.id}"
													class="glyphicon glyphicon-ok"></i></span></a> <c:if
												test="${region.value != null}">
												<ul>
													<c:forEach var="regionChild" items="${region.value}"
														varStatus="regionChildStatus">
														<li style="display: none" value="${regionChild.id}"><span><i
																class="glyphicon glyphicon-leaf"></i>
																${regionChild.regionChiName}</span> <input type="hidden"
															value="${regionChild.regionChiName}"> <a
															href="javascript:void(0)"><span><i id="glyphicon-ok_${regionChild.id}"
																	class="glyphicon glyphicon-ok"></i></span></a></li>
													</c:forEach>
												</ul>
											</c:if></li>
									</c:forEach>
								</ul></li>
						</ul>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary" onclick="addRegion()">提交</button>
				</div>
			</div>


		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->

	<script>
		$(function() {
			$("div#addAlarm div.modal-body div.form-group span.glyphicon-plus")
					.click(
							function() {
								$(this).parent()
										.before(
												"<div class='emails'><br><input id='email' name='email' type='email' required='required' class='form-control'></div>");
							});
			$("div#addAlarm div.modal-body div.form-group span.glyphicon-minus")
					.click(function() {
						$("div#addAlarm div.modal-body div.form-group div.emails").remove(":last");
					});
			
			$('#addAlarm').on('hide.bs.modal', function () {
				$("div#addAlarm div.modal-header h4.modal-title").text("添加新报警任务");
				$("div#addAlarm div.modal-body div.form-group :input").val("");
				$("div#addAlarm div.modal-body div.form-group div.emails").remove();
				$("div#addAlarm div.modal-body div.form-group input#nameidHidden").remove();
				});
		});
		
		
		//增加修改页面
		function modifyAlarm(id){
			$.getJSON("${ctx}/sinaWeibo/getAlarm?id="+id,function(alarm){
				$("div#addAlarm div.modal-body div.form-group input#alarmName").after("<input id='nameidHidden' type='hidden' name='id' value='"+id+"' >");
				$("div#addAlarm div.modal-body div.form-group input#alarmName").val(alarm.name);
				var regNams = alarm.regionChiNams;
			$("div#addAlarm div.modal-body div.form-group input#region").val(regNams);
			var regIds = alarm.regionIds;
			$("#addAlarm div.modal-body div.form-group input#regionHidden").val(regIds.join());
			for(i=0;i<regIds.length;i++){
				$("i.glyphicon-ok_"+regIds[i]).addClass("addRegion");
			}
				var emls = alarm.emails;
				for(i=0;i<emls.length;i++){
					$("div#addAlarm div.modal-body div.form-group input:eq("+(i+2)+")").val(emls[i]);
					if(i<emls.length-1){
									$("div#addAlarm div.modal-body div.form-group span.glyphicon-plus").parent()
											.before(
													"<div class='emails'><br><input id='email' name='email' type='email' required='required' class='form-control'></div>");
					}
				}
			});
			$("div#addAlarm div.modal-body form.form-horizontal").attr("action","${ctx}/sinaWeibo/alarmModify");
			$("div#addAlarm div.modal-header h4.modal-title").text("修改报警任务");
			$('#addAlarm').modal('show');
		}
		$(function () { $("[data-toggle='tooltip']").tooltip(); });
	</script>
</body>
</html>