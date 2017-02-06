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
			     <div class="col-md-3 col-md-offset-5 tableName">失信网信息表</div>
                      <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                  <th><nobr>序号</nobr></th>
                                  <th><nobr>姓名</nobr></th>
                                  <th><nobr>性别</nobr></th>
                                  <th><nobr>年龄</nobr></th>
                                  <th><nobr>身份证号码/<br>组织机构代码</nobr></th>
                                  <th><nobr>执行法院</nobr></th>
                                  <th><nobr>省份</nobr></th>
                                  <th><nobr>执行依据文号</nobr></th>
                                  <th><nobr>立案时间</nobr></th>
                                  <th><nobr>案号</nobr></th>
                                  <th><nobr>做出执行依据单位</nobr></th>
                                  <th><nobr>生效法律文书<br>确定的义务</nobr></th>
                                  <th><nobr>被执行人的<br>履行情况</nobr></th>
                                  <th><nobr>失信被执行人<br>行为具体情形</nobr></th>
                                  <th><nobr>发布时间</nobr></th>
                               </tr>
                          </thead>
                          <tbody>
                             <c:forEach items="${shixinList}" var="shixin" varStatus="i">
                                <tr>
                                    <td style="text-align:center">${i.count}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty shixin.iname}">
                                                  ${shixin.iname }
                                            </c:when>
                                            <c:otherwise>
                                                   ${shixin.name }
                                            </c:otherwise>
                                        
                                        </c:choose>
                                    </td>
                                    <td>${shixin.sexy }</td>
                                    <td>${shixin.age }</td>
                                    <td>${shixin.cardNum }</td>
                                    <td>${shixin.courtName }</td>
                                    <td>${shixin.areaName }</td>
                                    <td>${shixin.gistId }</td>
                                    <td>${shixin.regDate }</td>
                                    <td>${shixin.caseCode }</td>
                                    <td>${shixin.gistUnit }</td>
                                    <td style="text-align:center"><span id="shixin_${i.count }" style="cursor:pointer;color:#337ab7">收起</span></td>
                                    <td>${shixin.performance }</td>
                                    <td>${shixin.disruptTypeName }</td>
                                    <td>${shixin.publishDate }</td>
                                </tr>
                                <tr id="shixin_${i.count }_detail">
                                    <td colspan="15">${shixin.duty }</td>
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
<%-- <script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/common.js"></script> --%>

<script type="text/javascript">
     $(function(){
    	 
    	      $("table").on("click","span[id^='shixin_']",function(){
    	    	  
    	    	            var tr_id='';
    	    	            tr_id=$(this).attr("id")+"_detail";
    	    	            
    	    	            if($("#"+tr_id).css("display")=="none"){
    	    	            	
    	    	            	     $("#"+tr_id).show();
    	    	            	     $(this).text("收起")
    	    	            }else{
    	    	            	     $("#"+tr_id).hide();
    	    	            	     $(this).text("查看");
    	    	            }
    	      })
     })
</script>


</body>
</html>