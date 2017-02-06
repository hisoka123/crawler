<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
			     <div class="col-md-3 col-md-offset-5 tableName">海关网信息表</div>
			          <c:forEach items="${customs }" var="custom" varStatus="i">
			                <div class="col-md-2 col-md-offset-10"><span style="color:red;float:right">第${i.count }条/共${fn:length(customs) }条</span></div>
			                <table class="table table-bordered table-hover">
			                     <tbody>
			                         <tr>
			                              <th class="success" align="center"><nobr>企业中文名称</nobr></th>
			                              <td>${custom.bCName }</td>
			                              <th class="success" align="center"><nobr>海关注册编码</nobr></th>
			                              <td>${custom.cRCode }</td>
			                              
			                         </tr>
			                         <tr>
			                              <th class="success" align="center"><nobr>社会信用代码</nobr></th>
			                              <td>${custom.socialCreditCode }</td>
			                              <th class="success" align="center"><nobr>组织机构代码</nobr></th>
			                              <td>${custom.barCode }</td>
			                         </tr>
			                         <tr>
			                              <th class="success" align="center"><nobr>注册海关</nobr></th>
			                              <td>${custom.customsName }</td>
			                              <th class="success" align="center"><nobr>注册日期</nobr></th>
			                              <td>${custom.recordDate }</td>
			                         </tr>
			                         <tr>
			                              <th class="success" align="center"><nobr>工商注册地址</nobr></th>
			                              <td colspan="3">${custom.regAddress }</td>
			                         </tr>
			                         <tr>
			                             <th class="success" align="center"><nobr>行政区划</nobr></th>
			                             <td>${custom.admDivision }</td>
			                             <th class="success" align="center"><nobr>经济区划</nobr></th>
			                             <td>${custom.ecoReg }</td>
			                         </tr>
			                         <tr>
			                              <th class="success" align="center"><nobr>经济类型</nobr></th>
			                              <td>${custom.economicCategory }</td>
			                              <th class="success" align="center"><nobr>经营类别</nobr></th>
			                              <td>${custom.busType }</td>
			                         </tr>
			                         <tr>
			                             <th class="success" align="center"><nobr>行业种类</nobr></th>
			                             <td>${custom.businessLine }</td>
			                             <th class="success" align="center"><nobr>报关有效期</nobr></th>
			                             <td>${custom.customsValidity }</td>
			                             
			                         </tr>
			                         <tr>
			                             <th class="success" align="center"><nobr>海关注销标志</nobr></th>
			                             <td>${custom.customsMarks }</td>
			                             <th class="success" align="center"><nobr>年报情况</nobr></th>
			                             <td>${custom.annualReport }</td>
			                         </tr>
			                         <tr>
			                           <td colspan="4">
			                             <table class="table table-bordered table-hover table-striped">
			                                    <div style="text-align:center;color:#337ab7">信用等级表</div>
			                                  <thead>
			                                      <tr class="success">
			                                           <th><nobr>序号</nobr></th>
			                                           <th><nobr>认定时间</nobr></th>
			                                           <th><nobr>法律文书编号</nobr></th>
			                                           <th><nobr>信用等级</nobr></th>
			                                      </tr>
			                                  </thead>
			                                  <tbody>
			                                      <c:choose>
			                                          <c:when test="${fn:length(custom.creditRate)>0 }">
			                                               <c:forEach items="${custom.creditRate }" var="cr" >
			                                                     <tr>
			                                                         <td align="center">${cr.cId }</td>
			                                                         <td>${cr.identifyTime }</td>
			                                                         <td>${cr.legalNumber }</td>
			                                                         <td>${cr.quatityRate }</td>
			                                                     </tr>
			                                               </c:forEach>
			                                          </c:when>
			                                          <c:otherwise>
			                                               <tr><c:forEach begin="0" end="3"><td align="center">---</td></c:forEach></tr>
			                                          </c:otherwise>
			                                      </c:choose>
			                                  </tbody>
			                             </table>
			                             <table class="table table-bordered table-hover table-striped">
			                                 <div style="text-align:center;color:#337ab7">行政处罚信息表</div>
			                                 <thead>
			                                     <tr class="success">
			                                         <th><nobr>序号</nobr></th>
			                                         <th><nobr>当事人</nobr></th>
			                                         <th><nobr>案件性质</nobr></th>
			                                         <th><nobr>处罚日期</nobr></th>
			                                         <th><nobr>行政处罚决定书编号</nobr></th>
			                                     </tr>
			                                 </thead>
			                                 <tbody>
			                                     <c:choose>
			                                          <c:when test="${fn:length(custom.admPuInformation)>0 }">
			                                                <c:forEach items="${custom.admPuInformation }" var="ad">
			                                                      <tr>
			                                                          <td>${ad.aId }</td>
			                                                          <td>${ad.party }</td>
			                                                          <td>${ad.caseNature }</td>
			                                                          <td>${ad.punishmentDate }</td>
			                                                          <td>${ad.penaltyNumber }</td>
			                                                      </tr>
			                                                </c:forEach>
			                                          </c:when>
			                                          <c:otherwise>
			                                               <tr><c:forEach begin="0" end="4"><td align="center">---</td></c:forEach></tr> 
			                                          </c:otherwise>
			                                     </c:choose>
			                                 </tbody>
			                             </table>
			                             </td>
			                         </tr>
			                     </tbody>
			                </table>
			                <br>
			                
			          
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


</body>
</html>