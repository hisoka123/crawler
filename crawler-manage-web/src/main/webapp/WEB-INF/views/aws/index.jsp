<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map, java.util.ArrayList"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<jsp:include page="../header.jsp"></jsp:include>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>

	<div class="container-fluid sr-zql-sui-container">
		<form id="ipWhiteListForm" name="ipWhiteListForm" >
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<!-- IP白名单 -->
				<div class="table-responsive">
				<table id="ipWhiteList" class="table table-striped table-bordered table-hover">
					<caption><i class="glyphicon glyphicon-paperclip"></i> IP白名单：</caption>
					<thead>
						<tr class="info">
							<th class="col-md-2">协议</th>
							<th class="col-md-1">端口范围</th>
							<th class="col-md-6">来源</th>
							<th class="col-md-1">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr style="display:none">
							<td>
								<select class="form-control"  name="ipProtocol" onchange="changePortRange(this)" >
									<option value="tcp">TCP</option>
									<option value="http">HTTP</option>
									<option value="https">HTTPS</option>
								</select>
							</td>
							<td>
								<input type="text"  class="form-control" name="portRange"  />
							</td>
							<td>
								<input type="text" name="ipRange" class="form-control"   />
							</td>
							<td>
								<a href="javascript:void(0)" onclick="deleteRule(this)">
									<span class="glyphicon glyphicon-remove direct-remove" aria-hidden="true"></span>
								</a>
							</td>
						</tr>
						<c:forEach var="ipPermissionVO" items="${ipPermissionVOs}" varStatus="index">
							<tr>
								<td>
									<input type="hidden" name="ipProtocolHid"   value="${ipPermissionVO.ipProtocol}" />

									<select class="form-control"  name="ipProtocol"  onchange="changePortRange(this)" >
										<option <c:if test="${ipPermissionVO.portRange ne '80' and ipPermissionVO.portRange ne '443'}" > selected="selected"</c:if> value="tcp">TCP</option>
										<option <c:if test="${ipPermissionVO.portRange eq '80'}" > selected="selected"</c:if> value="http">HTTP</option>
										<option <c:if test="${ipPermissionVO.portRange eq '443'}" > selected="selected"</c:if> value="https">HTTPS</option>
									</select>

								</td>
								<td>
									<input type="hidden" name="portRangeHid"   value="${ipPermissionVO.portRange}" />
									<input type="text" name="portRange"  class="form-control"  <c:if test="${ipPermissionVO.portRange eq '80' or ipPermissionVO.portRange eq '443' }" > readonly="readonly"</c:if>   value="${ipPermissionVO.portRange}" />
								</td>
								<td>
									<input type="hidden" name="ipRangeHid" value="${ipPermissionVO.ipRange}" />
 									<input type="text" name="ipRange" class="form-control" value="${ipPermissionVO.ipRange}" />
								</td>
								<td>
									<a href="javascript:void(0)" onclick="deleteRule(this)">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>

			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-2">
					<button class="btn btn-default pull-left" id="addRule" type="button" >添加规则 »</button>
				</div>
				<div class="col-md-8">
					<div class="pull-right">
						<button class="btn btn-primary" id="resetBtn" type="reset" >重置</button>
						<button class="btn btn-primary" id="saveRule"  type="button">保存</button>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	<div id="mask" style="display: none"></div>
	<!-- 返回顶部 -->
	<div class="sr-zql-totop"></div>
	<jsp:include page="../footer.jsp"></jsp:include>

	<script>
		var ctx = "${ctx}";
	</script>

	<script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/aws/ipWhiteList.js" type="text/javascript"></script>
</body>
</html>

