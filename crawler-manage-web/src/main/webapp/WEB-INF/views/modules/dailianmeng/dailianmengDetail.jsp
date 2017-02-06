<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
      $(function(){
    	     var title="${title}";
    	     $("title").html(title);
      })
</script>
<script src="${ctx}/static/js/sweet-totop.js"></script>
<style type="text/css">
     .tableName{
         font-size:20px;
         padding-bottom:10px;
         padding-left:0px;
         color:#337ab7
     }
    .table>tbody>tr>td, .table>tbody>tr>th, 
    .table>tfoot>tr>td, .table>tfoot>tr>th, 
    .table>thead>tr>td, .table>thead>tr>th{
         vertical-align: middle;
    }
    .table>tbody>tr>th,.table>tfoot>tr>th,
    .table>thead>tr>th{
          text-align:center;
    }
   
</style>
<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
             <div id="table" class="col-md-12 table-responsive" style="min-height:380px;width:96%;margin-left:2%;">
<%--                       <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                  <th><nobr>序号</nobr></th>
                                  <th><nobr>被执行人姓名/名称</nobr></th>
                                  <th><nobr>案号</nobr></th>
                                  <th><nobr>年龄</nobr></th>
                                  <th><nobr>性别</nobr></th>
                                  <th><nobr>出生日期</nobr></th>
                                  <th><nobr>身份证号/组织机构代码</nobr></th>
                                  <th><nobr>发证地点</nobr></th>
                                  <th><nobr>法定代表人或者负责人</nobr></th>
                                  <th><nobr>执行法院</nobr></th>
                                  <th><nobr>省份</nobr></th>
                                  <th><nobr>执行依据文号</nobr></th>
                                  <th><nobr>立案时间</nobr></th>
                                  <th><nobr>做出执行依据单位</nobr></th>
                                  <th><nobr>生效法律文书确定的义务</nobr></th>
                                  <th><nobr>被执行人的履行情况</nobr></th>
                                  <th><nobr>已履行</nobr></th>
                                  <th><nobr>未履行</nobr></th>
                                  <th><nobr>失信被执行人行为情形</nobr></th>
                                  <th><nobr>发布时间</nobr></th>
                                  <th><nobr>更新时间</nobr></th>
                                  <th><nobr>债务的金额</nobr></th>
                                  <th><nobr>贷款日期</nobr></th>
                                  <th><nobr>贷款期限</nobr></th>
                                  <th><nobr>名单类型</nobr></th>
                                  <th><nobr>借款状态</nobr></th>
                                  <th><nobr>描述</nobr></th>
                               </tr>
                          </thead>
                          <tbody>
                             <c:forEach items="${loanUnions}" var="loanUnion" varStatus="i">
                                <tr>
                                    <td style="text-align:center">${i.count}</td>
                                    <td>${loanUnion.name }</td>
                                    <td>${loanUnion.caseNum }</td>
                                    <td>${loanUnion.age }</td>
                                    <td>${loanUnion.sex }</td>
                                    <td>${loanUnion.birthday }</td>
                                    <td>${loanUnion.cardID }</td>
                                    <td>${loanUnion.issuePlace }</td>
                                    <td>${loanUnion.legalPerson }</td>
                                    <td>${loanUnion.executeCourt }</td>
                                    <td>${loanUnion.province }</td>
                                    <td>${loanUnion.executeNum }</td>
                                    <td>${loanUnion.caseDate }</td>
                                    <td>${loanUnion.dependCourt }</td>
                                    <td>${loanUnion.effectNum }</td>
                                    <td>${loanUnion.executeSituation }</td>
                                    <td>${loanUnion.alreadyExecute }</td>
                                    <td>${loanUnion.noExecute }</td>
                                    <td>${loanUnion.behaviorSituation }</td>
                                    <td>${loanUnion.pubDate }</td>
                                    <td>${loanUnion.updateDate }</td>
                                    <td>${loanUnion.debtMoney }</td>
                                    <td>${loanUnion.loanDate }</td>
                                    <td>${loanUnion.loanTerm }</td>
                                    <td>${loanUnion.listType }</td>
                                    <td>${loanUnion.loanState }</td>
                                    <td>${loanUnion.describe }</td>
                                </tr>                         
                            </c:forEach>
                         </tbody>
                 </table> --%>
                 <c:forEach items="${loanUnions}" var="loanUnion" varStatus="i">
                 	<div class="col-md-3 col-md-offset-5 tableName">贷联盟信息表${i.count }</div>
                	<table class="table table-bordered table-hover">
                       <tbody>
                           <tr>
                               <th class="success" align="center" ><nobr>被执行人姓名/名称</nobr></th>
                               <td>${loanUnion.name }</td>
                               <th class="success" align="center"><nobr>案号</nobr></th>
                               <td>${loanUnion.caseNum }</td>
                              <th class="success" align="center"><nobr>年龄</nobr></th>
                              <td>${loanUnion.age }</td>
                           </tr>
                           <tr>
                              <th class="success" align="center"><nobr>性别</nobr></th>
                              <td>${loanUnion.sex }</td>
                              <th class="success" align="center"><nobr>出生日期</nobr></th>
                              <td>${loanUnion.birthday }</td>
                              <th class="success" align="center"><nobr>身份证号/组织机构代码</nobr></th>
                              <td>${loanUnion.cardID }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center"><nobr>发证地点</nobr></th>
                               <td>${loanUnion.issuePlace }</td>
                               <th class="success" align="center"><nobr>法定代表人或者负责人</nobr></th>
                               <td>${loanUnion.legalPerson }</td>
                               <th class="success" align="center"><nobr>执行法院</nobr></th>
                               <td>${loanUnion.executeCourt }</td>
                          </tr>
                          <tr>
                               <th class="success" align="center"><nobr>省份</nobr></th>
                               <td>${loanUnion.province }</td>
                               <th class="success" align="center"><nobr>执行依据文号</nobr></th>
                               <td>${loanUnion.executeNum }</td>
                               <th class="success" align="center"><nobr>立案时间</nobr></th>
                               <td>${loanUnion.caseDate }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center"><nobr>做出执行依据单位</nobr></th>
                               <td>${loanUnion.dependCourt }</td>
                               <th class="success" align="center"><nobr>被执行人的履行情况</nobr></th>
                               <td>${loanUnion.executeSituation }</td>
                               <th class="success" align="center"><nobr>已履行</nobr></th>
                               <td>${loanUnion.alreadyExecute }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center"><nobr>未履行</nobr></th>
                               <td>${loanUnion.noExecute }</td>
                               <th class="success" align="center"><nobr>失信被执行人行为情形</nobr></th>
                               <td>${loanUnion.behaviorSituation }</td>
                               <th class="success" align="center"><nobr>发布时间</nobr></th>
                               <td>${loanUnion.pubDate }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center"><nobr>更新时间</nobr></th>
                               <td>${loanUnion.updateDate }</td>
                               <th class="success" align="center"><nobr>债务的金额</nobr></th>
                               <td>${loanUnion.debtMoney }</td>
                               <th class="success" align="center"><nobr>贷款日期</nobr></th>
                               <td>${loanUnion.loanDate }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center"><nobr>贷款期限</nobr></th>
                               <td>${loanUnion.loanTerm }</td>
                               <th class="success" align="center"><nobr>名单类型</nobr></th>
                               <td>${loanUnion.listType }</td>
                               <th class="success" align="center"><nobr>贷款状态</nobr></th>
                               <td>${loanUnion.loanState }</td>
                           </tr>                           
                           <tr>
                               <th class="success" align="center" valign="middle"><nobr>生效法律文书确定的义务</nobr></th>
                               <td colspan="6">${loanUnion.effectNum }</td>
                           </tr>
                           <tr>
                               <th class="success" align="center" valign="middle"><nobr>描述</nobr></th>
                               <td colspan="6">${loanUnion.describe }</td>
                           </tr>
                   </tbody>
          		</table>
          	</c:forEach>
           </div>
	   </div>
   </div>
	
<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">

   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/common.js"></script>


<!-- 贷联盟 -->
<script src="${ctx}/static/js/dailianmeng/dailianmeng-common.js"></script>
<script src="${ctx}/static/js/dailianmeng/dailianmeng-ajax.js"></script>

</body>
</html>