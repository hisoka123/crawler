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
			     <div class="col-md-3 col-md-offset-5 tableName">失信记录平台信息表</div>
                      <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                  <th><nobr>序号</nobr></th>
                                  <th><nobr>企业标题</nobr></th>
                                  <th><nobr>关键字描述</nobr></th>
<!--                                   <th><nobr>内容</nobr></th> -->

                                 
                               </tr>
                          </thead>
                          <tbody>
                             <c:forEach items="${peopleCourts}" var="peopleCourt" varStatus="i">
                                <tr>
                                    <td style="text-align:center">${i.count}</td>
<%--                                     <td>${peopleCourt.companyTitle }</td> --%>
<%--                                     <td>${peopleCourt.keywordDescription }</td> --%>
<%--                                     <td>${peopleCourt.content }</td> --%>
                                        <td><a href="${peopleCourt.content }" target="_blank">${peopleCourt.companyTitle } </a></td>                             
                                       <td>${peopleCourt.keywordDescription }
                                                                            
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


<script src="${ctx}/static/js/iecms/iecms-common.js"></script>
<script src="${ctx}/static/js/iecms/iecms-ajax.js"></script>

</body>
</html>