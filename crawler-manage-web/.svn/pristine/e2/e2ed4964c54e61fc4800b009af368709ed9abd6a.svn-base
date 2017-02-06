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
			     <div class="row"><div class="col-md-3 col-md-offset-5 tableName">法海风控信息表</div></div>
			     <div style="margin-bottom:5px"><strong>关键词：</strong>${type } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${keyword }</div>
                      <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                  <th><nobr>序号</nobr></th>
                                  <th><nobr>标题</nobr></th>
                                  <th><nobr>立案日期</nobr></th>
                                  <th><nobr>开庭日期</nobr></th>
                                  <th><nobr>类别</nobr></th>
                                  <th><nobr>机构</nobr></th>
                                  <th><nobr>审结日期</nobr></th>
                                  <th><nobr>发布日期</nobr></th>
                               </tr>
                          </thead>
                          <tbody>
                             <c:forEach items="${fahaiccList }" var="fahaicc" varStatus="i">
                                   <tr>
                                      <td style="text-align:center">${i.count }</td>                                   
                                      <td class="col-md-4"><a href="${fahaicc.linkUrl }" target="_blank">${fahaicc.title }</a></td>
                                      <td>${fahaicc.filingDate }</td>
                                      <td>${fahaicc.courtDate }</td>
                                      <td>${fahaicc.type }</td>
                                      <td>${fahaicc.authority }</td>
                                      <td>${fahaicc.conclusionDate }</td>
                                      <td>${fahaicc.pubDate }</td>
                                   </tr>
                             </c:forEach>
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

</body>
</html>