<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
			     <div class="col-md-3 col-md-offset-5 tableName">专利网信息表</div>
                      <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">                               
                                  <th rowspan="2"><nobr>专利名称</nobr></th>
                                  <th rowspan="2"><nobr>申请号</nobr></th>
                                  <th rowspan="2"><nobr>申请人</nobr></th>
                                  <th rowspan="2"><nobr>发明人</nobr></th>
                                  <th rowspan="2"><nobr>分类号</nobr></th>                                 
                                  <th colspan="4"><nobr>事务数据</nobr></th>                          
                               </tr>
                               <tr class="success">
                                 <th>申请（专利）号</th>
                                 <th>事务数据公告日</th>
                                 <th>事务数据类型</th>
                                 <th>内容</th>                              
                             </tr>
                          </thead>   
                          <tbody>
                            <c:choose>
                               <c:when test="${fn:length(siposList)>0}">
                                       <c:forEach items="${siposList}" varStatus="i" var="sipo" >     
                                         <c:choose>     
                                              <c:when test="${empty sipo.transactionDatas}">
                                                     <tr>
                                                        <td>${sipo.licenseName } </td>
                                                        <td>${sipo.applicationNum } </td>
                                                        <td>${sipo.patentHolder } </td>
                                                        <td>${sipo.inventor } </td>
                                                        <td>${sipo.classNumber } </td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        
                                                     </tr>
                                                 </c:when>
                                         
                                          <c:otherwise>                                                                       
                                                     <tr>
                                                        <td rowspan="${fn:length(sipo.transactionDatas)}">${sipo.licenseName }</td>
                                                        <td rowspan="${fn:length(sipo.transactionDatas)}">${sipo.applicationNum }</td>
                                                        <td rowspan="${fn:length(sipo.transactionDatas)}">${sipo.patentHolder }</td>
                                                        <td rowspan="${fn:length(sipo.transactionDatas)}">${sipo.inventor }</td>
                                                        <td rowspan="${fn:length(sipo.transactionDatas)}">${sipo.classNumber }</td>                                 
                                                        <td>${sipo.transactionDatas[0].num }</td>
                                                        <td>${sipo.transactionDatas[0].date }</td>
                                                        <td>${sipo.transactionDatas[0].type }</td>
                                                       <td>${sipo.transactionDatas[0].content }</td>                                                    
                                                     </tr>                                           
                                                     <c:if test="${fn:length(sipo.transactionDatas)>1 }">
                                                         <c:forEach items="${sipo.transactionDatas }" varStatus="j" begin="1">
                                                               <tr>
                                                                  <td>${sipo.transactionDatas[j.index].num  }</td>
                                                                  <td>${sipo.transactionDatas[j.index].date  }</td>
                                                                   <td>${sipo.transactionDatas[j.index].type  }</td>
                                                                  <td>${sipo.transactionDatas[j.index].content  }</td>                                                             
                                                               </tr>
                                                         </c:forEach>
                                                     </c:if>
                                             </c:otherwise>           
                                           </c:choose>
                                       </c:forEach>
                               </c:when>
                               <c:otherwise>
                                      <tr align="center">
                                          <c:forEach begin="0" end="12"><td>---</td></c:forEach>
                                      </tr>
                               </c:otherwise>
                          </c:choose>
                        </tbody>
                         </tbody>
                         
                        
                         
                         
                 </table>
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


<!-- 专利网 -->
<script src="${ctx}/static/js/sipo/sipo-common.js"></script>
<script src="${ctx}/static/js/sipo/sipo-ajax.js"></script>

</body>
</html>