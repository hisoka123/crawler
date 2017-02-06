<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
	$(function() {
		var title = "${title}";
		$("title").html(title);
	})
</script>
<script src="${ctx}/static/js/sweet-totop.js"></script>
<style type="text/css">
.tableName {
	font-size: 18px;
	padding-bottom: 10px;
	padding-left: 0px;
	color: #337ab7
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	vertical-align: middle;
}

.table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>th {
	text-align: center;
}
</style>
<body>
	<jsp:include page="${navPath}"></jsp:include>
	<div data-name="text" class="container-fluid sr-zql-content sr-h-font">
		<div class="row">
			<div style="position: absolute; z-index: 2; right: 25%; top: 50px;">
				<div id="leftMenu"
					style="position: fixed; width: 25%; display: none"></div>
			</div>
		</div>
		<div class="row">
			<div id="table" class="col-md-12 table-responsive"
				style="min-height: 380px; width: 96%; margin-left: 2%;">
				<c:choose>
					<c:when test="${fn:length(creditchinaResults) > 0}">
						<c:forEach items="${creditchinaResults}" var="creditchina"
							varStatus="i">
							<div class="col-md-5 col-md-offset-4 tableName">${creditchina.name}</div>
							<table class="table table-bordered table-hover table-striped">
								<tbody>
									<tr align="center">
										<td>不良记录（${creditchina.badCount}）</td>
										<td><span id="badInfo_${i.count}"
											style="cursor: pointer; color: #337ab7" title="单击显示">展开</span></td>
									</tr>
									<tr id="badInfo_${i.count}_detail" style="display: none">
										<td colspan="3">
											<table class="table table-bordered table-hover">
												<c:choose>
													<c:when
														test="${not empty creditchina.badCount && creditchina.badCount > 0}">
														<c:choose>
															<c:when
																test="${not empty creditchina.objectType && (creditchina.objectType == '1' || creditchina.objectType == '1.0')}">
																<c:forEach items="${creditchina.badRecords}"
																	var="badRecord">
																	<tbody>
																		<tr>
																			<th class="success" align="center"><nobr>处罚对象名称</nobr></th>
																			<td>${badRecord.punishPerName}</td>
																			<th class="success" align="center"><nobr>证件号码</nobr></th>
																			<td>${badRecord.cardNumber}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>处罚处理日期</nobr></th>
																			<td>${badRecord.punishDate}</td>
																			<th class="success" align="center"><nobr>处罚机关</nobr></th>
																			<td>${badRecord.punishAuthrity}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>处罚处理种类</nobr></th>
																			<td>${badRecord.punishType}</td>
																			<th class="success" align="center"><nobr>处罚决定书ID</nobr></th>
																			<td>${badRecord.punishNumber}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>有效截止日期</nobr></th>
																			<td>${badRecord.endDate}</td>
																			<th class="success" align="center"><nobr>入库时间</nobr></th>
																			<td>${badRecord.taskDate}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>处罚处理名称</nobr></th>
																			<td colspan="3">${badRecord.punishName}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>处罚处理内容</nobr></th>
																			<td colspan="3">${badRecord.punishContent}</td>
																		</tr>
																	</tbody>
																	<tbody>
																		<tr>
																			<td colspan="4"><br /></td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<c:forEach items="${creditchina.badRecords}"
																	var="badRecord">
																	<tbody>
																		<tr>
																			<th class="success" align="center"><nobr>企业名称</nobr></th>
																			<td>${badRecord.companyName}</td>
																			<th class="success" align="center"><nobr>注册号</nobr></th>
																			<td>${badRecord.regNumber}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>法定代表人</nobr></th>
																			<td>${badRecord.legalPerson}</td>
																			<th class="success" align="center"><nobr>注册资本</nobr></th>
																			<td>${badRecord.regCapital}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>登记机关</nobr></th>
																			<td>${badRecord.regAuthority}</td>
																			<th class="success" align="center"><nobr>行业代码</nobr></th>
																			<td>${badRecord.industryCode}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>设立日期</nobr></th>
																			<td>${badRecord.creationDate}</td>
																			<th class="success" align="center"><nobr>入库时间</nobr></th>
																			<td>${badRecord.taskDate}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>列入时间</nobr></th>
																			<td>${badRecord.includedDate}</td>
																			<th class="success" align="center"><nobr>移出日期</nobr></th>
																			<td>${badRecord.removeDate}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>状态</nobr></th>
																			<td>${badRecord.status}</td>
																			<th class="success" align="center"><nobr>省份</nobr></th>
																			<td>${badRecord.province}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>住所</nobr></th>
																			<td colspan="3">${badRecord.abode}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>列入原因</nobr></th>
																			<td colspan="3">${badRecord.includedReason}</td>
																		</tr>
																	</tbody>
																	<tbody>
																		<tr>
																			<td colspan="4"><br /></td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<tbody>
															<tr align="center">
																<td>平台未收录该企业的不良记录。</td>
															</tr>
														</tbody>
													</c:otherwise>
												</c:choose>
											</table>
										</td>
									</tr>
									<tr align="center">
										<td>失信记录（${creditchina.dishonestyCount}）</td>
										<td><span id="dishonestyInfo_${i.count}"
											style="cursor: pointer; color: #337ab7" title="单击显示">展开</span></td>
									</tr>
									<tr id="dishonestyInfo_${i.count}_detail" style="display: none">
										<td colspan="3">
											<table class="table table-bordered table-hover">
												<c:choose>
													<c:when
														test="${not empty creditchina.dishonestyCount && creditchina.dishonestyCount > 0}">
														<c:choose>
															<c:when
																test="${not empty creditchina.objectType && (creditchina.objectType == '1' || creditchina.objectType == '1.0')}">
																<c:forEach items="${creditchina.dishonestyRecords}"
																	var="dishonestyRecord">
																	<tbody>
																		<tr>
																			<th class="success" align="center"><nobr>名称</nobr></th>
																			<td>${dishonestyRecord.companyName}</td>
																			<th class="success" align="center"><nobr>身份证号码</nobr></th>
																			<td>${dishonestyRecord.codeNumber}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>性别</nobr></th>
																			<td>${dishonestyRecord.sex}</td>
																			<th class="success" align="center"><nobr>年龄</nobr></th>
																			<td>${dishonestyRecord.age}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>执行法院</nobr></th>
																			<td>${dishonestyRecord.exeCourt}</td>
																			<th class="success" align="center"><nobr>省份</nobr></th>
																			<td>${dishonestyRecord.province}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>案号</nobr></th>
																			<td>${dishonestyRecord.caseNumber}</td>
																			<th class="success" align="center"><nobr>立案时间</nobr></th>
																			<td>${dishonestyRecord.filingTime}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>发布时间</nobr></th>
																			<td>${dishonestyRecord.releaseTime}</td>
																			<th class="success" align="center"><nobr>做出执行依据单位</nobr></th>
																			<td>${dishonestyRecord.performUnit}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>执行依据文号</nobr></th>
																			<td colspan="3">${dishonestyRecord.performNum}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>生效法律文书确定的义务</nobr></th>
																			<td colspan="3">${dishonestyRecord.lawEffect}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>已履行</nobr></th>
																			<td colspan="3">${dishonestyRecord.executed}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>未履行</nobr></th>
																			<td colspan="3">${dishonestyRecord.nonperforming}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>被执行人的履行情况</nobr></th>
																			<td colspan="3">${dishonestyRecord.execution}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>失信被执行人行为具体情形</nobr></th>
																			<td colspan="3">${dishonestyRecord.situation}</td>
																		</tr>
																	</tbody>
																	<tbody>
																		<tr>
																			<td colspan="4"><br /></td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<c:forEach items="${creditchina.dishonestyRecords}"
																	var="dishonestyRecord">
																	<tbody>
																		<tr>
																			<th class="success" align="center"><nobr>企业名称</nobr></th>
																			<td>${dishonestyRecord.companyName}</td>
																			<th class="success" align="center"><nobr>组织机构代码</nobr></th>
																			<td>${dishonestyRecord.codeNumber}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>法定代表人或者负责人姓名</nobr></th>
																			<td>${dishonestyRecord.legalPerson}</td>
																			<th class="success" align="center"><nobr>执行法院</nobr></th>
																			<td>${dishonestyRecord.exeCourt}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>省份</nobr></th>
																			<td>${dishonestyRecord.province}</td>
																			<th class="success" align="center"><nobr>做出执行依据单位</nobr></th>
																			<td>${dishonestyRecord.performUnit}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>案号</nobr></th>
																			<td>${dishonestyRecord.caseNumber}</td>
																			<th class="success" align="center"><nobr>立案时间</nobr></th>
																			<td>${dishonestyRecord.filingTime}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>执行依据文号</nobr></th>
																			<td>${dishonestyRecord.performNum}</td>
																			<th class="success" align="center"><nobr>发布时间</nobr></th>
																			<td>${dishonestyRecord.releaseTime}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>生效法律文书确定的义务</nobr></th>
																			<td colspan="3">${dishonestyRecord.lawEffect}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>被执行人的履行情况</nobr></th>
																			<td colspan="3">${dishonestyRecord.execution}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>已履行</nobr></th>
																			<td colspan="3">${dishonestyRecord.executed}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>未履行</nobr></th>
																			<td colspan="3">${dishonestyRecord.nonperforming}</td>
																		</tr>
																		<tr>
																			<th class="success" align="center"><nobr>失信被执行人行为具体情形</nobr></th>
																			<td colspan="3">${dishonestyRecord.situation}</td>
																		</tr>
																	</tbody>
																	<tbody>
																		<tr>
																			<td colspan="4"><br /></td>
																		</tr>
																	</tbody>
																</c:forEach>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<tbody>
															<tr align="center">
																<td><p>平台未收录该企业的失信记录。</p></td>
															</tr>
														</tbody>
													</c:otherwise>
												</c:choose>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="col-md-3 col-md-offset-5 tableName">
							<p>没有搜索到相关的企业或个人信息！</p>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
			</div>
			<div style="clear: both; zoom: 1"></div>
		</div>
		<div class="row" style="color: red">
			<div style="margin-left: 30px">
				<h6>注意事项：</h6>
				<ul style="font-size: 10px">
					<li>符号 "---",表示此项无内容.</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 返回顶部 -->
	<div class="sr-zql-totop"></div>
	<jsp:include page="${footerPath}"></jsp:include>
	<script src="${ctx}/static/js/bootstrap-treeview.min.js"></script>
	<script src="${ctx}/static/js/creditchina/creditchina-detail.js"></script>
</body>
</html>