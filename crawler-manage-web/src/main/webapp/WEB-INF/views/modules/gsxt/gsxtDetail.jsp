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

<div data-name="text" class="container-fluid sr-zql-content sr-h-font">
    <div class="row">
         <div style="position:absolute;z-index:2;right:25%;top:50px;">
             <div id="leftMenu" style="position:fixed;width:25%;display:none"></div>
         </div>
         <div style="position:absolute;z-index:2;right:20px;top:45%">
             <p id="toc" style="position:fixed;cursor:pointer" title="单击显示">目录</p>
         </div>
   </div>
   <div class="row">
         <div id="table" class="col-md-12 table-responsive" style="min-height:380px;width:96%;margin-left:2%;">
         
              <!-- 工商公示信息  -->
              <div data-name="aicPubInfo">
                   <h3 id="t1">工商公示信息</h3>
                   <hr>
                   
                   <!-- 登记信息  -->
                   <div data-name="regInfo">
                        <h4 id="t1_1">登记信息</h4>
                        
                              <!-- 基本信息表 -->
                              <div id="t1_1_1" class="col-md-3 col-md-offset-5 tableName">基本信息表</div>
                                   <table class="table table-bordered table-hover">
                                         <tbody>
                                             <tr>
                                                 <th class="success" align="center" ><nobr>注册号/<br>统一社会信用代码</nobr></th>
                                                 <td>
                                                     ${aicFeedJson.aicPubInfo.regInfo.baseInfo.num}
                                                     <c:if test="${not empty aicFeedJson.aicPubInfo.regInfo.baseInfo.creditNum }">
                                                           /<br> ${aicFeedJson.aicPubInfo.regInfo.baseInfo.creditNum }
                                                     </c:if> 
                                                 </td>
                                                 <th class="success" align="center"><nobr>名称</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.name}</td>
                                             </tr>
                                             <tr>
                                                <th class="success" align="center"><nobr>类型</nobr></th>
                                                <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.type}</td>
                                                <th class="success" align="center"><nobr>法定代表人(经营者)</nobr></th>
                                                <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.legalRepr}</td>
                                             </tr>
                                             <tr>
                                                <th class="success" align="center"><nobr>注册资本</nobr></th>
                                                <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.regCapital}</td>
                                                <th class="success" align="center"><nobr>登记机关</nobr></th>
                                                <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.regAuthority}</td>
                                             </tr>
                                             <tr>
                                                 <th class="success" align="center"><nobr>成立日期</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.regDateTime}</td>
                                                 <th class="success" align="center"><nobr>吊销日期</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.revokeDate}</td>
                                            </tr>
                                            <tr>
                                                 <th class="success" align="center"><nobr>营业期限：自</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.startDateTime}</td>
                                                 <th class="success" align="center"><nobr>营业期限：至</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.endDateTime}</td>
                                             </tr>
                                             <tr>
                                                 <th class="success" align="center"><nobr>核准日期</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.approvalDateTime}</td>
                                                 <th class="success" align="center"><nobr>登记状态</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.regStatus}</td>
                                             </tr>
                                             <tr>
                                                 <th class="success" align="center"><nobr>组成形式</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.formType}</td>
                                                 <th class="success" align="center"><nobr>经营场所(住所)</nobr></th>
                                                 <td>${aicFeedJson.aicPubInfo.regInfo.baseInfo.address}</td>
                                             </tr>
                                             <tr>
                                                 <th class="success" align="center" valign="middle"><nobr>经营范围</nobr></th>
                                                 <td colspan="3">${aicFeedJson.aicPubInfo.regInfo.baseInfo.businessScope}</td>
                                             </tr>
                                     </tbody>
                            </table>
                  
                  <!--股东信息表  -->
                  
                  <div id="t1_1_2" class="col-md-3 col-md-offset-5 tableName">股东信息表</div>
                  <table class="table table-bordered table-hover table-striped">
                        <thead>
                             <tr class="success">
                                 <th rowspan="2"><nobr>股东</nobr></th>
                                 <th rowspan="2"><nobr>股东类型</nobr></th>
                                 <th rowspan="2"><nobr>投资方式</nobr></th>
                                 <th rowspan="2"><nobr>证件类型</nobr></th>
                                 <th rowspan="2"><nobr>证件号码</nobr></th>
                                 <th rowspan="2"><nobr>认缴额<br>(万)</nobr></th>
                                 <th rowspan="2"><nobr>实缴额<br>(万)</nobr></th>
                                 <th colspan="3"><nobr>认缴明细</nobr></th>
                                 <th colspan="3"><nobr>实缴明细</nobr></th>
                             </tr>
                             <tr class="success">
                                 <th><nobr>出资<br>方式</nobr></th>
                                 <th><nobr>出资额<br>(万)</nobr></th>
                                 <th><nobr>出资<br>日期</nobr></th>
                                 <th><nobr>出资<br>方式</nobr></th>
                                 <th><nobr>出资额<br>(万)</nobr></th>
                                 <th><nobr>出资<br>日期</nobr></th>
                             </tr>
                        </thead>
                        <tbody>
                             <c:choose>
                               <c:when test="${fn:length(aicFeedJson.aicPubInfo.regInfo.stohrInfos)>0}">
                                       <c:forEach items="${aicFeedJson.aicPubInfo.regInfo.stohrInfos}" varStatus="i" var="stohrInfo">
                                             <c:choose>
                                                  <c:when test="${empty stohrInfo.stohrInvestInfo}">
                                                     <tr>
                                                        <td>${stohrInfo.name }</td>
                                                        <td>${stohrInfo.type }</td>
                                                        <td>${stohrInfo.sconform }</td>
                                                        <td>${stohrInfo.idType }</td>
                                                        <td>${stohrInfo.idNum }</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                        <td align="center">---</td>
                                                     </tr>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <tr>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.name }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.type }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.sconform }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.idType }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.idNum }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.stohrInvestInfo.subAmount }</td>
                                                        <td rowspan="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)}">${stohrInfo.stohrInvestInfo.paidAmount }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.subAmountDetails[0].investMethod }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.subAmountDetails[0].investAmount }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.subAmountDetails[0].investDateTime }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[0].investMethod }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[0].investAmount }</td>
                                                        <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[0].investDateTime }</td>
                                                     </tr> 
                                                     <c:if test="${fn:length(stohrInfo.stohrInvestInfo.subAmountDetails)>1 }">
                                                         <c:forEach items="${stohrInfo.stohrInvestInfo.subAmountDetails }" varStatus="j" begin="1">
                                                               <tr>
                                                                   <td>${stohrInfo.stohrInvestInfo.subAmountDetails[j.index].investMethod }</td>
                                                                   <td>${stohrInfo.stohrInvestInfo.subAmountDetails[j.index].investAmount }</td>
                                                                   <td>${stohrInfo.stohrInvestInfo.subAmountDetails[j.index].investDateTime }</td>
                                                                   <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[j.index].investMethod }</td>
                                                                   <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[j.index].investAmount }</td>
                                                                   <td>${stohrInfo.stohrInvestInfo.paidAmountDetails[j.index].investDateTime }</td>                                                               
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
                  </table>
                  
                  <!-- 变更信息表  -->
                  
                  <div id="t1_1_3" class="col-md-3 col-md-offset-5 tableName">变更信息表</div>
                  <table class="table table-bordered table-hover table-striped">    
                        <thead>
                              <tr class="success">
                                  <th rowspan="2"><nobr>变更事项</nobr></th>
                                  <th rowspan="2"><nobr>变更前内容</nobr></th>
                                  <th rowspan="2"><nobr>变更后内容</nobr></th>
                                  <th rowspan="2"><nobr>变更日期</nobr></th>
                                  <th colspan="3"><nobr>变更前</nobr></th>
                                  <th colspan="3"><nobr>变更后</nobr></th>
                              </tr>
                              <tr class="success">
                                  <th><nobr>姓名</nobr></th>
                                  <th><nobr>投资人类型<br>/职位</nobr></th>
                                  <th><nobr>证照号</nobr>
                                  <th><nobr>姓名</nobr></th>
                                  <th><nobr>投资人类型<br>/职位</nobr></th>
                                  <th><nobr>证照号</nobr>
                              </tr> 
                        </thead>
                        <tbody>
                              <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.aicPubInfo.regInfo.changeInfos)>0}">
                                           <c:forEach items="${aicFeedJson.aicPubInfo.regInfo.changeInfos}" var="changeInfo" varStatus="i">
                                                 <c:choose>
                                                       <c:when test="${empty changeInfo.detail }">
                                                             <tr>
                                                                <td>${changeInfo.item }</td>
                                                                <td>${changeInfo.preContent }</td>
                                                                <td>${changeInfo.postContent }</td>
                                                                <td>${changeInfo.dateTime }</td>
                                                                <td align="center">---</td>
                                                                <td align="center">---</td>
                                                                <td align="center">---</td>
                                                                <td align="center">---</td>
                                                                <td align="center">---</td>
                                                                <td align="center">---</td>
                                                            </tr>  
                                                       </c:when>
                                                       <c:otherwise>
                                                             <c:choose>
                                                                  <c:when test="${fn:length(changeInfo.detail.preInfos) >= fn:length(changeInfo.detail.postInfos) }">
                                                                      <tr>
                                                                         <td rowspan="${fn:length(changeInfo.detail.preInfos)}">${changeInfo.item }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.preInfos)}">${changeInfo.preContent }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.preInfos)}">${changeInfo.postContent }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.preInfos)}">${changeInfo.dateTime }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].name }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].typeOrPosition }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].idNum }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].name }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].typeOrPosition }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].idNum }</td>
                                                                      </tr>
                                                                      <c:forEach items="${changeInfo.detail.preInfos}" varStatus="j" begin="1">
                                                                           <tr>
                                                                               <td>${changeInfo.detail.preInfos[j.index].name }</td>
                                                                               <td>${changeInfo.detail.preInfos[j.index].typeOrPosition }</td>
                                                                               <td>${changeInfo.detail.preInfos[j.index].idNum }</td>
                                                                               <td>${changeInfo.detail.postInfos[j.index].name }</td>
                                                                               <td>${changeInfo.detail.postInfos[j.index].typeOrPosition }</td>
                                                                               <td>${changeInfo.detail.postInfos[j.index].idNum }</td>                                                                         
                                                                           </tr>
                                                                     </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                     <tr>
                                                                         <td rowspan="${fn:length(changeInfo.detail.postInfos)}">${changeInfo.item }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.postInfos)}">${changeInfo.preContent }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.postInfos)}">${changeInfo.postContent }</td>
                                                                         <td rowspan="${fn:length(changeInfo.detail.postInfos)}">${changeInfo.dateTime }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].name }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].typeOrPosition }</td>
                                                                         <td>${changeInfo.detail.preInfos[0].idNum }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].name }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].typeOrPosition }</td>
                                                                         <td>${changeInfo.detail.postInfos[0].idNum }</td>
                                                                      </tr>
                                                                      <c:forEach items="${changeInfo.detail.postInfos}" varStatus="k" begin="1">
                                                                           <tr>
                                                                               <td>${changeInfo.detail.preInfos[k.index].name }</td>
                                                                               <td>${changeInfo.detail.preInfos[k.index].typeOrPosition }</td>
                                                                               <td>${changeInfo.detail.preInfos[k.index].idNum }</td>
                                                                               <td>${changeInfo.detail.postInfos[k.index].name }</td>
                                                                               <td>${changeInfo.detail.postInfos[k.index].typeOrPosition }</td>  
                                                                               <td>${changeInfo.detail.postInfos[k.index].idNum }</td>                                                                       
                                                                           </tr>
                                                                     </c:forEach>
                                                                </c:otherwise>
                                                             </c:choose>   
                                                       </c:otherwise>
                                                 </c:choose>
                                           </c:forEach>                                    
                                    </c:when>                              
                                    <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="9"><td>---</td></c:forEach>
                                           </tr>
                                    </c:otherwise>
                              </c:choose>
                        </tbody>
                  </table> 
                  
                  <!-- 撤销信息 表-->
                  <div id="t1_1_4" class="col-md-3 col-md-offset-5 tableName">撤销信息表</div>
                  <table class="table table-bordered table-hover table-striped">
                         <thead>
                             <tr class="success">
                                  <th><nobr>撤销事项</nobr></th>
                                  <th><nobr>撤销前内容</nobr></th>
                                  <th><nobr>撤销后内容</nobr></th>
                                  <th><nobr>撤销日期</nobr></th>
                             </tr>
                         </thead>
                         <tbody>
                             <c:choose>
                                  <c:when test="${fn:length(aicFeedJson.aicPubInfo.regInfo.revokeInfos)>0 }">
                                         <c:forEach items="${aicFeedJson.aicPubInfo.regInfo.revokeInfos}" var="revokeInfo">
                                               <tr>
                                                   <td>${revokeInfo.revokeItem }</td>
                                                   <td>${revokeInfo.revokePreContent }</td>
                                                   <td>${revokeInfo.revokePostContent }</td>
                                                   <td>${revokeInfo.revokeDate }</td>
                                               </tr>
                                         </c:forEach>
                                  </c:when>                             
                                  <c:otherwise>
                                        <tr align="center">
                                            <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                        </tr>
                                  </c:otherwise>
                             </c:choose>                         
                         </tbody>
                  </table>
              </div>
              
              <!-- 备案信息 -->
              <div data-name="archiveInfo">
                 <h4 id="t1_2">备案信息</h4>
                     
                     <!-- 主要人员信息表 -->
                     <div id="t1_2_1" class="col-md-3 col-md-offset-5 tableName">主要人员信息表</div>
                     <table class="table table-bordered table-hover table-striped"> 
                         <thead>
                            <tr class="success">
                               <th style="width:30%"><nobr>姓名</nobr></th>
                               <th><nobr>职务</nobr></th>
                            </tr>
                         </thead>
                         <tbody>
                            <c:choose>
                                <c:when test="${fn:length(aicFeedJson.aicPubInfo.archiveInfo.priMemberInfos)>0 }">
                                      <c:forEach items="${aicFeedJson.aicPubInfo.archiveInfo.priMemberInfos}" var="priMemberInfo" varStatus="i">
                                            <tr align="center">
                                                 <td>${priMemberInfo.name }</td>
                                                 <td>${priMemberInfo.position }</td>                                            
                                            </tr>
                                      </c:forEach>
                                </c:when>  
                                <c:otherwise>
                                      <tr align="center">
                                         <c:forEach begin="0" end="1"><td>---</td></c:forEach>
                                      </tr>
                                </c:otherwise>
                            </c:choose>
                         </tbody>
                  </table>
                  
                  <!-- 分支机构信息 表-->
                  <div id="t1_2_2" class="col-md-3 col-md-offset-5 tableName">分支机构信息表</div>
                  <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr class="success">
                                <th><nobr>统一社会信用代码/注册号</nobr></th>
                                <th><nobr>名称</nobr></th>
                                <th><nobr>登记机关</nobr></th>
                            </tr>                      
                        </thead>
                        <tbody>
                            <c:choose>
                                 <c:when test="${fn:length(aicFeedJson.aicPubInfo.archiveInfo.branchInfos)>0 }">
                                       <c:forEach items="${aicFeedJson.aicPubInfo.archiveInfo.branchInfos }" var="branchInfo" varStatus="i">
                                             <tr align="center">
                                                 <td>${branchInfo.num }</td>
                                                 <td>${branchInfo.name }</td>
                                                 <td>${branchInfo.regAuthority }</td>
                                             </tr>
                                       </c:forEach>
                                 </c:when>                            
                                 <c:otherwise>
                                       <tr align="center">
                                           <c:forEach begin="0" end="2"><td>---</td></c:forEach>
                                       </tr>
                                 </c:otherwise>
                            </c:choose>
                        </tbody>
                  </table>
                  
                  
                  <!-- 清算信息表 -->
                  <div id="t1_2_3" class="col-md-3 col-md-offset-5 tableName">清算信息表</div>
                  <table class="table table-bordered table-hover">
                       <tbody>
                           <c:choose>
                                <c:when test="${not empty aicFeedJson.aicPubInfo.archiveInfo.clearInfo }">
                                      <tr>
                                         <th class="success" align="center" width="20%">清算组负责人</th>
                                         <td>
                                             <c:if test="${aicFeedJson.aicPubInfo.archiveInfo.clearInfo.leader!='' }">
                                                 ${aicFeedJson.aicPubInfo.archiveInfo.clearInfo.leader }
                                             </c:if>
                                         </td> 
                                      </tr>
                                      <tr>
                                         <th class="success" align="center" width="20%">清算组成员</th>
                                         <td id="a">
                                             <c:if test="${aicFeedJson.aicPubInfo.archiveInfo.clearInfo.members!='[]' }">
                                                 ${aicFeedJson.aicPubInfo.archiveInfo.clearInfo.members }
                                             </c:if>
                                          </td>
                                      </tr>
                                </c:when>
                                <c:otherwise>
                                      <tr>
                                         <th class="success" align="center" width="20%">清算组负责人</th>
                                         <td align="center">---</td> 
                                      </tr>
                                      <tr>
                                         <th class="success" align="center" width="20%">清算组成员</th>
                                         <td align="center">---</td>
                                      </tr>
                                </c:otherwise>
                           </c:choose>
                       </tbody>
                  </table>
                  
                  <!-- 主管部门（出资人）信息 -->
                  <div id="t1_2_4" class="col-md-3 col-md-offset-5 tableName">主管部门（出资人）信息表</div>
                  <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr class="success">
                                <th><nobr>出资人类型</nobr></th>
                                <th><nobr>出资人</nobr></th>
                                <th><nobr>证照/证件类型</nobr></th>
                                <th><nobr>证照/证件号码</nobr></th>
                                <th><nobr>公示日期</nobr></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                 <c:when test="${fn:length(aicFeedJson.aicPubInfo.archiveInfo.mainDeptInfo)>0 }">
                                      <c:forEach items="${aicFeedJson.aicPubInfo.archiveInfo.mainDeptInfo }" var="mainDeptInfo">
                                            <tr>
                                                 <td>${mainDeptInfo.type }</td>                                            
                                                 <td>${mainDeptInfo.name }</td>
                                                 <td>${mainDeptInfo.idType }</td>
                                                 <td>${mainDeptInfo.idNum }</td>
                                                 <td>${mainDeptInfo.showDate }</td>
                                            </tr>
                                      </c:forEach>                               
                                 </c:when>
                                 <c:otherwise>
                                       <tr align="center">
                                           <c:forEach begin="0" end="4"> <td>---</td></c:forEach>
                                       </tr>
                                 </c:otherwise>
                            </c:choose>
                        </tbody>
                  </table>
               </div>
               
               <!-- 动产抵押登记信息 -->
               <div data-name="chatMortgInfo">
               <h4 id="t1_3">动产抵押登记信息</h4>
               
               
                     <!-- 动产抵押登记信息表  -->
                     <div class="col-md-3 col-md-offset-5 tableName">动产抵押登记信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr class="success">
                                   <th><nobr>登记编号</nobr></th>
                                   <th><nobr>登记日期</nobr></th>
                                   <th><nobr>登记机关</nobr></th>
                                   <th><nobr>被担保债权数额</nobr></th>
                                   <th><nobr>状态</nobr></th>
                                   <th><nobr>公示时间</nobr></th>
                                   <th>详情</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                     <c:when test="${fn:length(aicFeedJson.aicPubInfo.chatMortgInfo.chatMortgInfos)>0 }">
                                            <c:forEach items="${aicFeedJson.aicPubInfo.chatMortgInfo.chatMortgInfos}" var="chatMortgInfo" varStatus="i">
                                                  <tr>
                                                      <td>${chatMortgInfo.regNum }</td>
                                                      <td>${chatMortgInfo.regDateTime }</td>
                                                      <td>${chatMortgInfo.regAuthority }</td>
                                                      <td>${chatMortgInfo.guaranteedDebtAmount }</td>
                                                      <td>${chatMortgInfo.status }</td>
                                                      <td>${chatMortgInfo.pubDateTime }</td>
                                                      <c:if test="${empty chatMortgInfo.mortgDetail }">
                                                           <td align="center">---</td>
                                                      </c:if>
                                                      <c:if test="${not empty chatMortgInfo.mortgDetail}">
                                                           <td align="center"><span id="chatMortgInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                                      </c:if>
                                                  </tr>
                                                  <c:if test="${not empty chatMortgInfo.mortgDetail}">
                                                      <tr id="chatMortgInfo_${i.count}_detail" style="display:none">
                                                        <td colspan="7">
                                                            <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">动产抵押登记信息表</h5>
                                                            <table class="table table-bordered table-hover">
                                                               <tbody>
                                                                   <tr>
                                                                       <th class="info"><nobr>登记编号</nobr></th>
                                                                       <td>${chatMortgInfo.mortgDetail.mortgRegInfo.regNum }</td>
                                                                       <th class="info"><nobr>登记日期</nobr></th>
                                                                       <td>${chatMortgInfo.mortgDetail.mortgRegInfo.regDate }</td>
                                                                   </tr>
                                                                   <tr>
                                                                        <th class="info"><nobr>登记机关</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.regAuthority }</td>
                                                                        <th class="info"><nobr>被担保债权种类</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.guaranteedDebtType }</td>
                                                                   </tr>
                                                                   <tr>
                                                                        <th class="info"><nobr>被担保债权数额</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.guaranteedDebtAmount }</td>
                                                                        <th class="info"><nobr>债务人履行债务的期限</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.term }</td>
                                                                   </tr>
                                                                   <tr>
                                                                        <th class="info"><nobr>担保范围</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.guaranteedScope }</td>
                                                                        <th class="info"><nobr>备注</nobr></th>
                                                                        <td>${chatMortgInfo.mortgDetail.mortgRegInfo.note }</td>
                                                                   </tr>
                                                               </tbody>
                                                            </table>
                                                            
                                                            <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">抵押权人概况表</h5>
                                                            <table class="table table-bordered table-hover">
                                                                <thead>
                                                                  <tr class="info">
                                                                      <th><nobr>抵押权人名称</nobr></th>
                                                                      <th><nobr>抵押权人证照/证件类型</nobr></th>
                                                                      <th><nobr>证照/证件号码</nobr></th>
                                                                  </tr>
                                                                </thead>
                                                                <tbody>
                                                                  <c:choose>
                                                                      <c:when test="${fn:length(chatMortgInfo.mortgDetail.mortgPersonInfos)>0 }">
                                                                            <c:forEach items="${chatMortgInfo.mortgDetail.mortgPersonInfos}" var="mortgPersonInfo" varStatus="j">
                                                                                  <tr>
                                                                                      <td>${mortgPersonInfo.name }</td>
                                                                                      <td>${mortgPersonInfo.type }</td>
                                                                                      <td>${mortgPersonInfo.num }</td>
                                                                                  </tr>
                                                                            </c:forEach>
                                                                      </c:when>                                                                  
                                                                      <c:otherwise>
                                                                             <tr align="center"><c:forEach begin="0" end="2"><td>---</td></c:forEach></tr>
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                               </tbody>
                                                            </table>
                                                            
                                                            <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">被担保债权概况表</h5>
                                                            <table class="table table-bordered table-hover">
                                                               <tbody>
                                                                  <tr>
                                                                      <th class="info"><nobr>种类</nobr></th>
                                                                      <td>${chatMortgInfo.mortgDetail.mortgGuaranteedInfo.category }</td>
                                                                      <th class="info"><nobr>数额</nobr></th>
                                                                      <td>${chatMortgInfo.mortgDetail.mortgGuaranteedInfo.amount }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th class="info"><nobr>担保范围</nobr></th>
                                                                      <td>${chatMortgInfo.mortgDetail.mortgGuaranteedInfo.guarantyScope }</td>
                                                                      <th class="info"><nobr>债务人履行债务期限</nobr></th>
                                                                      <td>${chatMortgInfo.mortgDetail.mortgGuaranteedInfo.term }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th class="info"><nobr>备注</nobr></th>
                                                                      <td colspan="3">${chatMortgInfo.mortgDetail.mortgGuaranteedInfo.note }</td>
                                                                  </tr>
                                                               </tbody>
                                                           </table>
                                                           
                                                           <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">抵押物概况表</h5>
                                                           <table class="table table-bordered table-hover">
                                                               <thead>
                                                                   <tr class="info">
                                                                       <th><nobr>名称 </nobr></th>
                                                                       <th><nobr>所有权归属 </nobr></th>
                                                                       <th><nobr>数量、质量、状况、所在地等情况</nobr></th>
                                                                       <th><nobr>备注</nobr></th>
                                                                   </tr>
                                                               </thead>
                                                               <tbody>
                                                                   <c:choose>
                                                                       <c:when test="${fn:length(chatMortgInfo.mortgDetail.mortgGoodsInfos)>0 }">
                                                                            <c:forEach items="${chatMortgInfo.mortgDetail.mortgGoodsInfos}" var="mortgGoodsInfo" varStatus="j">
                                                                                  <tr>
                                                                                      <td>${mortgGoodsInfo.name }</td>
                                                                                      <td>${mortgGoodsInfo.ownerShip }</td>
                                                                                      <td>${mortgGoodsInfo.generalSituation }</td>
                                                                                      <td>${mortgGoodsInfo.note }</td>
                                                                                  </tr>
                                                                            </c:forEach>
                                                                       </c:when>
                                                                       <c:otherwise>
                                                                            <tr align="center"><c:forEach begin="0" end="3"><td>---</td></c:forEach></tr>
                                                                       </c:otherwise>
                                                                   </c:choose>                                                               
                                                               </tbody>
                                                           </table>
                                                           
                                                           <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">变更表</h5>
                                                           <table class="table table-bordered table-hover">
                                                                <thead>
                                                                   <tr class="info">
                                                                       <th><nobr>序号</nobr></th>
                                                                       <th><nobr>变更日期</nobr></th>
                                                                       <th><nobr>变更内容</nobr></th>
                                                                   </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:choose>
                                                                         <c:when test="${fn:length(chatMortgInfo.mortgDetail.mortgChangeInfos)>0 }">
                                                                              <c:forEach items="${chatMortgInfo.mortgDetail.mortgChangeInfos}" var="mortgChangeInfo" varStatus="j">
                                                                                   <tr>
                                                                                       <td align="center">${j.count }</td>
                                                                                       <td>${mortgChangeInfo.changeDate }</td>
                                                                                       <td>${mortgChangeInfo.changeContent }</td>
                                                                                   </tr>
                                                                              </c:forEach>
                                                                         </c:when>
                                                                         <c:otherwise>
                                                                            <tr align="center">
                                                                              <c:forEach begin="0" end="2">
                                                                                   <td>---</td>
                                                                              </c:forEach>
                                                                            </tr>
                                                                         </c:otherwise>
                                                                    </c:choose>
                                                                </tbody>
                                                           </table>
                                                           
                                                            <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">注销表</h5>
                                                           <table class="table table-bordered table-hover">
                                                               <tbody>
                                                                   <tr>
                                                                       <th class="info"><nobr>注销日期</nobr></th>
                                                                       <td>${chatMortgInfo.mortgDetail.mortgRevokeInfo.revokeDate }</td>
                                                                       <th class="info"><nobr>注销原因</nobr></th>
                                                                       <td>${chatMortgInfo.mortgDetail.mortgRevokeInfo.revokeReason }</td>
                                                                   </tr>
                                                               </tbody>
                                                           </table>
                                                       </td>
                                                    </tr>
                                                  </c:if>
                                            </c:forEach>
                                     </c:when>
                                     <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="6"><td>---</td></c:forEach>
                                           </tr>
                                     </c:otherwise>
                                </c:choose>
                            </tbody>
                     </table>
               </div>
               
               <!-- 股权出资登记信息 -->
               <div data-name="equMortgRegInfo">
               <h4 id="t1_4">股权出资登记信息</h4>
                    
                     <!-- 股权出质登记信息表 -->
                     <div class="col-md-3 col-md-offset-5 tableName">股权出质登记信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>登记编号</nobr></th>
                                   <th><nobr>出质人</nobr></th>
                                   <th><nobr>证照/证件号码（出质人）</nobr></th>
                                   <th><nobr>出质股权数额</nobr></th>
                                   <th><nobr>质权人</nobr></th>
                                   <th><nobr>证照/证件号码</nobr></th>
                                   <th><nobr>股权出质设立登记日期</nobr></th>
                                   <th><nobr>状态</nobr></th>
                                   <th><nobr>公示时间</nobr></th>
                                   <th>变化情况</th>
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.aicPubInfo.equMortgRegInfo.equmortgregInfos)>0 }">
                                           <c:forEach items="${aicFeedJson.aicPubInfo.equMortgRegInfo.equmortgregInfos}" var="equmortgregInfo" varStatus="i">
                                                 <tr>
                                                     <td>${equmortgregInfo.regNum}</td>
                                                     <td>${equmortgregInfo.mortgagorName }</td>
                                                     <td>${equmortgregInfo.mortgagorIdNum }</td>
                                                     <td>${equmortgregInfo.mortgAmount }</td>
                                                     <td>${equmortgregInfo.mortgageeName }</td>
                                                     <td>${equmortgregInfo.mortgageeIdNum }</td>
                                                     <td>${equmortgregInfo.regDateTime }</td>
                                                     <td>${equmortgregInfo.status }</td>
                                                     <td>${equmortgregInfo.pubDate }</td>
                                                     <td>${equmortgregInfo.changeSitu }</td>
                                                 </tr>
                                           </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                          <tr align="center">
                                             <c:forEach begin="0" end="9"><td>---</td></c:forEach>
                                          </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                     </table>
               
               </div>
               
               <!-- 行政处罚信息 -->
               <div data-name="admPunishInfo">
               <h4 id="t1_5">行政处罚信息</h4>
               
                     
                     <!-- 行政处罚信息表 -->
                     <div class="col-md-3 col-md-offset-5 tableName">行政处罚信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                                 <tr class="success">
                                     <th rowspan="2"><nobr>行政处罚<br>决定书文号</nobr></th>
                                     <th rowspan="2"><nobr>违法行为类型</nobr></th>
                                     <th rowspan="2"><nobr>行政处罚内容</nobr></th>
                                     <th rowspan="2"><nobr>作出行政处罚<br>决定机关名称</nobr></th>
                                     <th rowspan="2"><nobr>作出行政处罚<br>决定日期</nobr></th>
                                     <th colspan="3"><nobr>行政处罚当事人基本情况</nobr></th>
                                     <th rowspan="2"><nobr>行政处罚决定书</nobr></th>
                                 </tr>
                                 <tr class="success">
                                     <th><nobr>名称</nobr></th>
                                     <th><nobr>注册号</nobr></th>
                                     <th><nobr>法定代表人（负责人）<br>姓名</nobr></th>
                                 </tr>
                           </thead>
                           <tbody>
                                <c:choose>
                                     <c:when test="${fn:length(aicFeedJson.aicPubInfo.admPunishInfo.admPunishInfos)>0 }">
                                           <c:forEach items="${aicFeedJson.aicPubInfo.admPunishInfo.admPunishInfos}" var="admPunishInfo" varStatus="i">
                                                 <tr>
                                                    <td>${admPunishInfo.punishRepNum }</td>
                                                    <td>${admPunishInfo.illegalActType }</td>
                                                    <td>${admPunishInfo.punishContent }</td>
                                                    <td>${admPunishInfo.deciAuthority }</td>
                                                    <td>${admPunishInfo.deciDateTime }</td>
                                                    <c:if test="${not empty admPunishInfo.punishDetail }">
                                                          <td>${admPunishInfo.punishDetail.name }</td>
                                                          <td>${admPunishInfo.punishDetail.regNum }</td>
                                                          <td>${admPunishInfo.punishDetail.legalReprName }</td>
                                                          <td align="center"><span id="admPunishInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                                    </c:if>
                                                    <c:if test="${empty admPunishInfo.punishDetail }">
                                                          <td align="center">---</td>
                                                          <td align="center">---</td>
                                                          <td align="center">---</td>
                                                          <td align="center">---</td>
                                                    </c:if>
                                                    
                                                 </tr>
                                                 <c:if test="${not empty admPunishInfo.punishDetail }">
                                                      <tr id="admPunishInfo_${i.count}_detail" style="display:none" >
                                                          <td colspan="9">
                                                               ${admPunishInfo.punishDetail.punishRep }
                                                          </td>
                                                      </tr>
                                                 </c:if>
                                           </c:forEach>
                                     </c:when>
                                     <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="8"><td>---</td></c:forEach>
                                           </tr>
                                     </c:otherwise>
                                </c:choose>
                           </tbody>
                     </table>
              
               </div>
               
               <!-- 经营异常信息 -->
               <div data-name="operAnomaInfo">
               <h4 id="t1_6">经营异常信息</h4>
               
               
                     <!-- 经营异常信息表 -->
                     <div class="col-md-3 col-md-offset-5 tableName">经营异常信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>列入经营异常名录原因</nobr></th>
                                   <th><nobr>列入日期</nobr></th>
                                   <th><nobr>作出决定机关<br>(列入)</nobr></th>
                                   <th><nobr>移出经营异常名录原因</nobr></th>
                                   <th><nobr>移出日期</nobr></th>
                                   <th><nobr>作出决定机关<br>(移出)</nobr></th>
                                   <th><nobr>作出决定机关</nobr></th>
                                   <th><nobr>原因编号</nobr></th>
                                   <th><nobr>原因详情</nobr></th>
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                   <c:when test="${fn:length(aicFeedJson.aicPubInfo.operAnomaInfo.operAnomaInfos)>0 }">
                                          <c:forEach items="${aicFeedJson.aicPubInfo.operAnomaInfo.operAnomaInfos }" var="operAnomaInfo" varStatus="i">
                                                <tr>
                                                     <td>${operAnomaInfo.includeCause }</td>
                                                     <td>${operAnomaInfo.includeDateTime }</td>
                                                     <td>${operAnomaInfo.includeAuthority }</td>
                                                     <td>${operAnomaInfo.removeCause }</td>
                                                     <td>${operAnomaInfo.removeDateTime }</td>
                                                     <td>${operAnomaInfo.removeAuthority }</td>
                                                     <td>${operAnomaInfo.authority }</td>
                                                     <td>${operAnomaInfo.serialNumber }</td>
                                                     <td>${operAnomaInfo.includeCauseDetail }</td>
                                                </tr>
                                          </c:forEach>
                                   </c:when>
                                   <c:otherwise>
                                         <tr align="center">
                                             <c:forEach begin="0" end="8"><td>---</td></c:forEach>
                                         </tr>
                                   </c:otherwise>
                               </c:choose>
                           </tbody>
                     
                     </table>
              
               </div>
               
               <!-- 严重违法信息 -->
               <div data-name="serIllegalInfo">
               <h4 id="t1_7">严重违法信息</h4>
             
                    
                     <!-- 严重违法信息表 -->
                     <div class="col-md-3 col-md-offset-5 tableName">严重违法信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                     <th><nobr>列入严重违法企业名单原因</nobr></th>
                                     <th><nobr>列入日期</nobr></th>
                                     <th><nobr>移出严重违法企业名单原因</nobr></th>
                                     <th><nobr>移出日期</nobr></th>
                                     <th><nobr>作出决定机关</nobr></th>
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.aicPubInfo.serIllegalInfo.serIllegalInfos)>0 }">
                                          <c:forEach items="${aicFeedJson.aicPubInfo.serIllegalInfo.serIllegalInfos }" var="serIllegalInfo" varStatus="i">
                                               <tr>
                                                   <td>${serIllegalInfo.includeCause }</td> 
                                                   <td>${serIllegalInfo.includeDateTime }</td>
                                                   <td>${serIllegalInfo.removeCause }</td>
                                                   <td>${serIllegalInfo.removeDateTime }</td> 
                                                   <td>${serIllegalInfo.deciAuthority }</td>                                   
                                               </tr>                               
                                         </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                         <tr align="center">
                                             <c:forEach begin="0" end="4"><td>---</td></c:forEach>
                                         </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                     </table>
              
               </div>
               
               
               <!-- 抽查检查信息 -->
               <div data-name="checkInfo">
               <h4 id="t1_8">抽查检查信息</h4>
               
                     <!-- 抽查检查信息表  -->
                     <div class="col-md-3 col-md-offset-5 tableName">抽查检查信息表 </div>
                     <table class="table table-bordered table-hover table-striped">
                          <thead>
                              <tr class="success">
                                 <th><nobr>检查实施机关</nobr></th>
                                 <th><nobr>类型</nobr></th>
                                 <th><nobr>日期</nobr></th>
                                 <th><nobr>结果</nobr></th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:choose>
                                 <c:when test="${fn:length(aicFeedJson.aicPubInfo.checkInfo.checkInfos)>0 }">
                                        <c:forEach items="${aicFeedJson.aicPubInfo.checkInfo.checkInfos }" var="checkInfo" varStatus="i">
                                              <tr>
                                                  <td>${checkInfo.checkImplAuthority }</td>
                                                  <td>${checkInfo.type }</td>
                                                  <td>${checkInfo.dateTime }</td>
                                                  <td>${checkInfo.result }</td>
                                              </tr>
                                        </c:forEach>
                                 </c:when>
                                 <c:otherwise>
                                       <tr align="center">
                                           <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                       </tr>
                                 </c:otherwise>
                            </c:choose>
                        </tbody>                     
                    </table>
               </div>
           </div>   
           
           <br>
           <!-- 企业公示信息  --> 
           <div data-name="entPubInfo">
              <h3 id="t2">企业公示信息</h3>
              <hr>
              
               <!-- 企业年报 -->
               <div data-name="annReports">
               <h4 id="t2_1">企业年报 </h4>
              
               
                     <!-- 企业年报表  -->
                     <div class="col-md-3 col-md-offset-5 tableName">企业年报表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                  <th><nobr>报送年度</nobr></th>
                                   <th><nobr>首次公示日期</nobr></th>
                                  <th><nobr>发布日期</nobr></th>
                                  <th><nobr>详情</nobr></th>
                                  <th><nobr>备注</nobr></th>
                               </tr>
                           </thead> 
                           <tbody>
                              <c:choose>
                                   <c:when test="${fn:length(aicFeedJson.entPubInfo.annReports)>0 }">
                                     <c:forEach items="${aicFeedJson.entPubInfo.annReports}" var="annReport" varStatus="i">
                                       <tr align="center">
                                           <td>${annReport.submitYear }</td>
                                           <td>${annReport.firstDate }</td>
                                           <td>${annReport.pubDateTime }</td>
                                           <c:if test="${empty annReport.remarks }">
                                               <td><span id="annReport_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                               <td align="center">---</td>
                                           </c:if>
                                           <c:if test="${not empty annReport.remarks }">
                                               <td>---</td>
                                               <td>${annReport.remarks }</td>
                                           </c:if>
                                           
                                       </tr>
                                       <c:if test="${empty annReport.remarks }">
                                          <tr id="annReport_${i.count}_detail" style="display:none">
                                            <td colspan="5">
                                               <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">企业基本信息</h5>
                                                <table class="table table-bordered table-hover">
                                                   <c:choose>
                                                        <c:when test="${not empty annReport.baseInfo }">
                                                              <tbody>
                                                                  <tr>
                                                                      <th align="center" class="info">注册号/统一社会信用代码</th>
                                                                      <td>${annReport.baseInfo.num }</td>
                                                                      <th align="center" class="info">企业名称</th>
                                                                      <td>${annReport.baseInfo.name }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info">合作社名称</th>
                                                                      <td>${annReport.baseInfo.cooperativeName }</td>
                                                                      <th align="center" class="info">成员人数</th>
                                                                      <td>${annReport.baseInfo.membersNum }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info">经营者姓名</th>
                                                                      <td>${annReport.baseInfo.operator }</td>
                                                                      <th align="center" class="info">营业执照注册号</th>
                                                                      <td>${annReport.baseInfo.businessLicenceNum }</td>
                                                                 </tr>
                                                                 <tr>
                                                                      <th align="center" class="info">资金数额</th>
                                                                      <td>${annReport.baseInfo.capitalAmount }</td>
                                                                      <th align="center" class="info">企业联系电话</th>
                                                                      <td>${annReport.baseInfo.tel }</td>
                                                                </tr>
                                                                <tr>
                                                                      <th align="center" class="info">邮政编码</th>
                                                                      <td>${annReport.baseInfo.zipCode }</td>
                                                                      <th align="center" class="info">电子邮箱</th>
                                                                      <td>${annReport.baseInfo.email }</td>
                                                               </tr>
                                                               <tr>
                                                                      <th align="center"class="info">企业通信地址</th>
                                                                      <td colspan="3">${annReport.baseInfo.address }</td>
                                                               </tr>
                                                               <tr>
                                                                     <th align="center" class="info"><nobr>有限责任公司本年度是否<br>发生股东股权转让</nobr></th>
                                                                     <td>${annReport.baseInfo.isStohrEquTransferred }</td>
                                                                     <th align="center" class="info"><nobr>企业是否有投资信息<br>或购买其他公司股权</nobr></th>
                                                                     <td>${annReport.baseInfo.hasInvestInfoOrPurchOtherCorpEqu }</td>
                                                               </tr>
                                                               <tr>
                                                                     <th align="center" class="info">企业经营状态</th>
                                                                     <td>${annReport.baseInfo.operatingStatus }</td>
                                                                     <th align="center" class="info">是否有网站或网店</th>
                                                                     <td>${annReport.baseInfo.hasWebsiteOrStore }</td>
                                                               </tr>
                                                               <tr>
                                                                     <th align="center" class="info">从业人数</th>
                                                                     <td>${annReport.baseInfo.empNum }</td>
                                                                     <th align="center" class="info">是否对外担保</th>
                                                                     <td>${annReport.baseInfo.hasExternalSecurity }</td>
                                                               </tr>
                                                               <tr>
                                                                     <th align="center" class="info">隶属关系</th>
                                                                     <td colspan="3">${annReport.baseInfo.affiliation }</td>
                                                               </tr>
                                                            </tbody>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tbody>
                                                                <tr>
                                                                    <th align="center" class="info">注册号/统一社会信用代码</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">企业名称</th>
                                                                    <td>---</td>
                                                                </tr>
                                                                <tr>
                                                                      <th align="center" class="info">合作社名称</th>
                                                                      <td>---</td>
                                                                      <th align="center" class="info">成员人数</th>
                                                                      <td>---</td>
                                                                </tr>
                                                                <tr>
                                                                    <th align="center" class="info">经营者姓名</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">营业执照注册号</th>
                                                                    <td>---</td>
                                                                </tr>
                                                                <tr>
                                                                    <th align="center" class="info">资金数额</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">企业联系电话</th>
                                                                    <td>---</td>
                                                                </tr>
                                                                <tr>
                                                                    <th align="center" class="info">邮政编码</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">电子邮箱</th>
                                                                    <td>---</td>
                                                               </tr>
                                                               <tr>
                                                                    <th align="center"class="info">企业通信地址</th>
                                                                    <td colspan="3">---</td>
                                                               </tr>
                                                               <tr>
                                                                    <th align="center" class="info"><nobr>有限责任公司本年度是否<br>发生股东股权转让</nobr></th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info"><nobr>企业是否有投资信息<br>或购买其他公司股权</nobr></th>
                                                                    <td>---</td>
                                                               </tr>
                                                               <tr>
                                                                    <th align="center" class="info">企业经营状态</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">是否有网站或网店</th>
                                                                    <td>---</td>
                                                              </tr>
                                                              <tr>
                                                                    <th align="center" class="info">从业人数</th>
                                                                    <td>---</td>
                                                                    <th align="center" class="info">是否对外担保</th>
                                                                    <td>---</td>
                                                              </tr>
                                                              <tr>
                                                                    <th align="center" class="info">隶属关系</th>
                                                                    <td colspan="3">---</td>
                                                              </tr>
                                                      </tbody>
                                                        
                                                        </c:otherwise>
                                                   </c:choose>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">网站或网店信息</h5>
                                                <table class="table table-bordered table-hover">
                                                       <thead>
                                                           <tr class="info">
                                                               <th>类型</th>
                                                               <th>名称</th>
                                                               <th>网址</th>
                                                           </tr>
                                                       </thead>
                                                       <tbody>
                                                           <c:choose>
                                                                <c:when test="${fn:length(annReport.websiteInfos)>0 }">
                                                                      <c:forEach items="${annReport.websiteInfos}" var="websiteInfo" varStatus="j">
                                                                             <tr>
                                                                                 <td>${websiteInfo.type }</td>
                                                                                 <td>${websiteInfo.name }</td>
                                                                                 <td>${websiteInfo.website }</td>
                                                                             </tr>
                                                                      </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                      <tr align="center">
                                                                          <c:forEach begin="0" end="2"><td>---</td></c:forEach>
                                                                      </tr>
                                                                </c:otherwise>
                                                           </c:choose>
                                                       </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">股东及出资信息</h5>
                                                <table class="table table-bordered table-hover">
                                                     <thead>
                                                         <tr class="info">
                                                             <th><nobr>股东（发起人）</nobr></th>
                                                             <th><nobr>认缴出资额（万元）</nobr></th>
                                                             <th><nobr>认缴出资时间</nobr></th>
                                                             <th><nobr>认缴出资方式</nobr></th>
                                                             <th><nobr>实缴出资额（万元）</nobr></th>
                                                             <th><nobr>实缴出资时间</nobr></th>
                                                             <th><nobr>实缴出资方式</nobr></th>
                                                         </tr>                                                     
                                                     </thead>
                                                     <tbody>
                                                         <c:choose>
                                                              <c:when test="${fn:length(annReport.stohrInvestInfos) >0 }">
                                                                    <c:forEach items="${annReport.stohrInvestInfos }" var="stohrInvestInfo" varStatus="j">
                                                                          <tr>
                                                                              <td>${stohrInvestInfo.stockholder }</td>
                                                                              <td>${stohrInvestInfo.subAmount }</td>
                                                                              <td>${stohrInvestInfo.subDateTime }</td>
                                                                              <td>${stohrInvestInfo.subMethod }</td>
                                                                              <td>${stohrInvestInfo.paidAmount }</td>
                                                                              <td>${stohrInvestInfo.paidDateTime }</td>
                                                                              <td>${stohrInvestInfo.paidMethod }</td>
                                                                          </tr>
                                                                    </c:forEach>                                                              
                                                              </c:when>
                                                              <c:otherwise>
                                                                    <tr align="center">
                                                                          <c:forEach begin="0" end="6"><td>---</td></c:forEach>
                                                                    </tr>
                                                              </c:otherwise>
                                                         </c:choose>
                                                     </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">对外投资信息</h5>
                                                <table class="table table-bordered table-hover">
                                                      <thead>
                                                          <tr class="info">
                                                              <th><nobr>投资设立企业或购买股权企业名称</nobr></th>
                                                              <th><nobr>注册号</nobr></th>
                                                          </tr>
                                                      </thead>
                                                      <tbody>
                                                           <c:choose>
                                                                <c:when test="${fn:length(annReport.extInvestInfos)>0 }">
                                                                      <c:forEach items="${annReport.extInvestInfos }" var="extInvestInfo" varStatus="j">
                                                                            <tr>
                                                                                <td>${extInvestInfo.enterpriseName }</td>
                                                                                <td>${extInvestInfo.regNum }</td>
                                                                            </tr>
                                                                      </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                      <tr align="center">
                                                                          <c:forEach begin="0" end="1"><td>---</td></c:forEach>
                                                                      </tr>
                                                                </c:otherwise>
                                                           </c:choose>
                                                      </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">企业资产状况信息</h5>
                                                <table class="table table-bordered table-hover">
                                                    <tbody>
                                                        <c:choose>
                                                            <c:when test="${not empty annReport.assetInfo}">
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>资产总额</nobr></th>
                                                                      <td>${annReport.assetInfo.assetAmount }</td>
                                                                      <th align="center" class="info"><nobr>负债总额</nobr></th>
                                                                      <td>${annReport.assetInfo.liabilityAmount }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>销售总额</nobr></th>
                                                                      <td>${annReport.assetInfo.salesAmount }</td>
                                                                      <th align="center" class="info"><nobr>利润总额</nobr></th>
                                                                      <td>${annReport.assetInfo.profitAmount }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>销售总额中主营业务收入</nobr></th>
                                                                      <td>${annReport.assetInfo.priBusiIncomeInSalesAmount }</td>
                                                                      <th align="center" class="info"><nobr>净利润</nobr></th>
                                                                      <td>${annReport.assetInfo.netProfit }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>纳税总额</nobr></th>
                                                                      <td>${annReport.assetInfo.taxesAmount }</td>
                                                                      <th align="center" class="info"><nobr>所有者权益合计</nobr></th>
                                                                      <td>${annReport.assetInfo.totalEquity }</td>
                                                                  </tr>
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>获得政府扶持资金、补助</nobr></th>
                                                                      <td>${annReport.assetInfo.governmentFunds }</td>
                                                                      <th align="center" class="info"><nobr>金融贷款</nobr></th>
                                                                      <td>${annReport.assetInfo.financialLoan }</td>
                                                                  </tr>
                                                            </c:when>
                                                            <c:otherwise>
                                                                   <tr>
                                                                      <th align="center" class="info"><nobr>资产总额</nobr></th>
                                                                      <td>---</td>
                                                                      <th align="center" class="info"><nobr>负债总额</nobr></th>
                                                                      <td>---</td>
                                                                   </tr>
                                                                   <tr>
                                                                      <th align="center" class="info"><nobr>销售总额</nobr></th>
                                                                      <td>---</td>
                                                                      <th align="center" class="info"><nobr>利润总额</nobr></th>
                                                                      <td>---</td>
                                                                   </tr>
                                                                   <tr>
                                                                      <th align="center" class="info"><nobr>销售总额中主营业务收入</nobr></th>
                                                                      <td>---</td>
                                                                      <th align="center" class="info"><nobr>净利润</nobr></th>
                                                                      <td>---</td>
                                                                   </tr>
                                                                   <tr>
                                                                      <th align="center" class="info"><nobr>纳税总额</nobr></th>
                                                                      <td>---</td>
                                                                      <th align="center" class="info"><nobr>所有者权益合计</nobr></th>
                                                                      <td>---</td>
                                                                   </tr>
                                                                  <tr>
                                                                      <th align="center" class="info"><nobr>获得政府扶持资金、补助</nobr></th>
                                                                      <td>---</td>
                                                                       <th align="center" class="info"><nobr>金融贷款</nobr></th>
                                                                      <td>---</td>
                                                                  </tr>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">对外提供保证担保信息</h5>
                                                <table class="table table-bordered table-hover">
                                                     <thead>
                                                         <tr class="info">
                                                             <th><nobr>债权人</nobr></th>
                                                             <th><nobr>债务人</nobr></th>
                                                             <th><nobr>主债权种类</nobr></th>
                                                             <th><nobr>主债权数额</nobr></th>
                                                             <th><nobr>履行债务的期限</nobr></th>
                                                             <th><nobr>保证的期间</nobr></th>
                                                             <th><nobr>保证的方式</nobr></th>
                                                             <th><nobr>保证担保的范围</nobr></th>
                                                         </tr>
                                                     </thead>
                                                     <tbody>
                                                         <c:choose>
                                                               <c:when test="${fn:length(annReport.extGuaranteeInfos)>0 }">
                                                                      <c:forEach items="${annReport.extGuaranteeInfos }" var="extGuaranteeInfo">
                                                                            <tr>
                                                                                <td>${extGuaranteeInfo.creditor }</td>
                                                                                <td>${extGuaranteeInfo.debtor }</td>
                                                                                <td>${extGuaranteeInfo.priCredRightType }</td>
                                                                                <td>${extGuaranteeInfo.priCredRightAmount }</td>
                                                                                <td>${extGuaranteeInfo.exeDebtDeadline }</td>
                                                                                <td>${extGuaranteeInfo.guaranteePeriod }</td>
                                                                                <td>${extGuaranteeInfo.guaranteeMethod }</td>
                                                                                <td>${extGuaranteeInfo.guaranteeScope }</td>
                                                                            </tr>
                                                                      </c:forEach>                                                            
                                                               </c:when>
                                                               <c:otherwise>
                                                                     <tr align="center">
                                                                          <c:forEach begin="0" end="7"><td>---</td></c:forEach>
                                                                      </tr>
                                                               </c:otherwise>
                                                         </c:choose>
                                                     </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">股权变更信息</h5>
                                                <table class="table table-bordered table-hover">
                                                      <thead>
                                                          <tr class="info">
                                                              <th><nobr>股东（发起人）</nobr></th>
                                                              <th><nobr>变更前股权比例</nobr></th>
                                                              <th><nobr>变更后股权比例</nobr></th>
                                                              <th><nobr>股权变更日期</nobr></th>
                                                          </tr>
                                                      </thead>
                                                      <tbody>
                                                          <c:choose>
                                                               <c:when test="${fn:length(annReport.equChangeInfos) >0}">
                                                                      <c:forEach items="${annReport.equChangeInfos}" var="equChangeInfo">
                                                                            <tr>
                                                                                <td>${equChangeInfo.stockholder }</td>  
                                                                                <td>${equChangeInfo.preOwnershipRatio }</td>    
                                                                                <td>${equChangeInfo.postOwnershipRatio }</td> 
                                                                                <td>${equChangeInfo.dateTime }</td>                                                                     
                                                                            </tr>
                                                                      </c:forEach>
                                                               </c:when>                                                          
                                                               <c:otherwise>
                                                                     <tr align="center">
                                                                          <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                                                     </tr>
                                                               </c:otherwise>
                                                          </c:choose>
                                                      </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">修改记录</h5>
                                                <table class="table table-bordered table-hover">
                                                     <thead>
                                                           <tr class="info">
                                                               <th><nobr>修改事项</nobr></th>
                                                               <th><nobr>修改前</nobr></th>
                                                               <th><nobr>修改后</nobr></th>
                                                               <th><nobr>修改日期</nobr></th>
                                                           </tr>
                                                     </thead>
                                                     <tbody>
                                                           <c:choose>
                                                               <c:when test="${fn:length(annReport.changeInfos) >0 }">
                                                                     <c:forEach items="${annReport.changeInfos}" var="changeInfo">
                                                                           <tr>
                                                                                <td>${changeInfo.item }</td>
                                                                                <td>${changeInfo.preContent }</td>
                                                                                <td>${changeInfo.postContent }</td>
                                                                                <td>${changeInfo.dateTime }</td>
                                                                           </tr>
                                                                     </c:forEach>
                                                               </c:when>
                                                               <c:otherwise>
                                                                     <tr align="center">
                                                                          <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                                                     </tr>
                                                               </c:otherwise>
                                                           </c:choose>
                                                     </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">行政许可情況</h5>
                                                <table class="table table-bordered table-hover">
                                                      <thead>
                                                          <tr class="info">
                                                              <th><nobr>许可文件名称</nobr></th>
                                                              <th><nobr>有效期至</nobr></th>
                                                          </tr>
                                                      </thead>
                                                      <tbody>
                                                          <c:choose>
                                                               <c:when test="${fn:length(annReport.admlicenseInfos)>0 }">
                                                                    <c:forEach items="${annReport.admlicenseInfos}" var="admlicenseInfo">
                                                                          <tr>
                                                                               <td>${admlicenseInfo.licenseName }</td>
                                                                               <td>${admlicenseInfo.licenseDate }</td>
                                                                          </tr>
                                                                    </c:forEach>
                                                               </c:when>
                                                               <c:otherwise>
                                                                     <tr align="center">
                                                                          <c:forEach begin="0" end="1"><td>---</td></c:forEach>
                                                                     </tr>
                                                               </c:otherwise>
                                                          </c:choose>
                                                      </tbody>
                                                </table>
                                                <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">生产经营情况</h5>
                                                <table class="table table-bordered table-hover">
                                                      <thead>
                                                          <tr class="info">
                                                              <th><nobr>营业额或营业收入</nobr></th>
                                                              <th><nobr>纳税总额</nobr></th>
                                                              <th><nobr>净利润</nobr></th>
                                                          </tr>
                                                      </thead>
                                                      <tbody>
                                                          <c:choose>
                                                              <c:when test="${fn:length(annReport.manageInfos)>0 }">
                                                                    <c:forEach items="${annReport.manageInfos }" var="manageInfo">
                                                                         <tr>
                                                                             <td>${manageInfo.saleSum }</td>
                                                                             <td>${manageInfo.salarySum }</td>
                                                                             <td>${manageInfo.netProfit }</td>
                                                                         </tr>
                                                                    </c:forEach>
                                                              </c:when>
                                                              <c:otherwise>
                                                                    <tr align="center">
                                                                          <c:forEach begin="0" end="2"><td>---</td></c:forEach>
                                                                    </tr>
                                                              </c:otherwise>
                                                          </c:choose>
                                                      </tbody>
                                                </table>
                                                <h5 style="color:#337ab7;margin-left:40%">-----${annReport.submitYear } 年报详情结束 -----</h5>
                                         </td>
                                      </tr>
                                      </c:if>
                                   </c:forEach>
                                </c:when>
                                    <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                           </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                     </table>
              </div>
              
              <!-- 股东及出资信息  -->
              <div data-name="stohrInvestInfo">
              <h4 id="t2_2">股东及出资信息</h4>
                 
                    <!-- 股东及出资信息表  -->
                    <div id="t2_2_1" class="col-md-3 col-md-offset-5 tableName">股东及出资信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                    <th rowspan="2"><nobr>股东</nobr></th>
                                    <th rowspan="2"><nobr>投资人类型</nobr></th>
                                    <th rowspan="2"><nobr>认缴额(万)</nobr></th>
                                    <th rowspan="2"><nobr>实缴额(万)</nobr></th>
                                    <th colspan="4"><nobr>认缴明细</nobr></th>
                                    <th colspan="4"><nobr>实缴明细</nobr></th>
                               </tr>
                               <tr class="success">
                                    <th><nobr>出资方式</nobr></th>
                                    <th><nobr>出资额(万)</nobr></th>
                                    <th><nobr>出资日期</nobr></th>
                                    <th><nobr>公示日期</nobr></th>
                                    <th><nobr>出资方式</nobr></th>
                                    <th><nobr>出资额(万)</nobr></th>
                                    <th><nobr>出资日期</nobr></th>
                                    <th><nobr>公示日期</nobr></th>
                               </tr>                           
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.entPubInfo.stohrInvestInfo.stohrInvestInfos)>0 }">
                                            <c:forEach items="${aicFeedJson.entPubInfo.stohrInvestInfo.stohrInvestInfos}" var="stohrInvestInfo">
                                                      <c:choose>
                                                           <c:when test="${fn:length(stohrInvestInfo.subDetails) >0 }">
                                                                  <tr>
                                                                       <td rowspan="${fn:length(stohrInvestInfo.subDetails)}">${stohrInvestInfo.stockholder }</td>
                                                                       <td rowspan="${fn:length(stohrInvestInfo.subDetails)}">${stohrInvestInfo.investorsType }</td>
                                                                       <td rowspan="${fn:length(stohrInvestInfo.subDetails)}">${stohrInvestInfo.subAmount }</td>
                                                                       <td rowspan="${fn:length(stohrInvestInfo.subDetails)}">${stohrInvestInfo.paidAmount }</td>
                                                                       <td>${stohrInvestInfo.subDetails[0].method }</td>
                                                                       <td>${stohrInvestInfo.subDetails[0].amount }</td>
                                                                       <td>${stohrInvestInfo.subDetails[0].dateTime }</td>
                                                                       <td>${stohrInvestInfo.subDetails[0].showDate }</td>
                                                                       <td>${stohrInvestInfo.paidDetails[0].method }</td>
                                                                       <td>${stohrInvestInfo.paidDetails[0].amount }</td>
                                                                       <td>${stohrInvestInfo.paidDetails[0].dateTime }</td>
                                                                       <td>${stohrInvestInfo.paidDetails[0].showDate }</td>
                                                                   </tr>
                                                                   <c:if test="${fn:length(stohrInvestInfo.subDetails) >1}">
                                                                       <c:forEach items="${stohrInvestInfo.subDetails }" varStatus="j" begin="1">
                                                                             <tr>
                                                                                <td>${stohrInvestInfo.subDetails[j.index].method }</td>
                                                                                <td>${stohrInvestInfo.subDetails[j.index].amount }</td>
                                                                                <td>${stohrInvestInfo.subDetails[j.index].dateTime }</td>
                                                                                <td>${stohrInvestInfo.subDetails[j.index].showDate }</td>
                                                                                <td>${stohrInvestInfo.paidDetails[j.index].method }</td>
                                                                                <td>${stohrInvestInfo.paidDetails[j.index].amount }</td>
                                                                                <td>${stohrInvestInfo.paidDetails[j.index].dateTime }</td>
                                                                                <td>${stohrInvestInfo.paidDetails[j.index].showDate }</td>
                                                                             </tr>
                                                                       </c:forEach>
                                                                   </c:if>
                                                           </c:when>
                                                           <c:otherwise>
                                                                  <tr>
                                                                       <td>${stohrInvestInfo.stockholder }</td>
                                                                       <td>${stohrInvestInfo.investorsType }</td>
                                                                       <td>${stohrInvestInfo.subAmount }</td>
                                                                       <td>${stohrInvestInfo.paidAmount }</td>
                                                                       <c:forEach begin="0" end="7">
                                                                             <td>---</td>
                                                                        </c:forEach>
                                                                  </tr>
                                                           </c:otherwise>
                                                      </c:choose>
                                            </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                          <tr align="center">
                                             <c:forEach begin="0" end="11"><td>---</td></c:forEach>
                                          </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                    </table>
                    
                    <!-- 变更信息表  -->
                    <div id="t2_2_2" class="col-md-3 col-md-offset-5 tableName">变更信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                          <thead>
                               <tr class="success">
                                   <th><nobr>变更事项</nobr></th>
                                   <th><nobr>变更时间</nobr></th>
                                   <th><nobr>变更前</nobr></th>
                                   <th><nobr>变更后</nobr></th>
                               </tr>
                          </thead>
                          <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.entPubInfo.stohrInvestInfo.changeInfos) >0 }">
                                          <c:forEach items="${aicFeedJson.entPubInfo.stohrInvestInfo.changeInfos }" var="changeInfo">
                                                <tr>
                                                    <td>${changeInfo.item }</td>
                                                    <td>${changeInfo.dateTime }</td>
                                                    <td>${changeInfo.preContent }</td>
                                                    <td>${changeInfo.postContent }</td>
                                                </tr>
                                          </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                          <tr align="center">
                                             <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                          </tr>
                                    </c:otherwise>
                               </c:choose>
                          </tbody>
                    </table>
              </div>
              
              <!-- 股权变更信息 -->
              <div data-name="equChangeInfo">
              <h4 id="t2_3">股权变更信息</h4>
                    
                     <!-- 股权变更信息表  -->
                    <div class="col-md-3 col-md-offset-5 tableName">股权变更信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                          <thead>
                              <tr class="success">
                                  <th><nobr>股东</nobr></th>
                                  <th><nobr>变更前股权比例</nobr></th>
                                  <th><nobr>变更后股权比例</nobr></th>
                                  <th><nobr>股权变更日期</nobr></th>
                              </tr>
                          </thead>
                          <tbody>
                              <c:choose>
                                   <c:when test="${fn:length(aicFeedJson.entPubInfo.equChangeInfo.equChangeInfos)>0 }">
                                         <c:forEach items="${aicFeedJson.entPubInfo.equChangeInfo.equChangeInfos}" var="equChangeInfo">
                                                 <tr>
                                                      <td>${equChangeInfo.stockholder }1</td>
                                                      <td>${equChangeInfo.preOwnershipRatio }</td>
                                                      <td>${equChangeInfo.postOwnershipRatio }</td>
                                                      <td>${equChangeInfo.dateTime }</td>
                                                 </tr>
                                         </c:forEach>
                                   </c:when>
                                   <c:otherwise>
                                         <tr align="center">
                                            <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                         </tr>
                                   </c:otherwise>
                              </c:choose>
                          </tbody>
                    </table>
              </div>
              
              
              <!-- 行政许可信息 -->
              <div data-name="admLicInfo">
              <h4 id="t2_4">行政许可信息</h4>
                   
                    <!-- 行政许可信息表  -->
                    <div class="col-md-3 col-md-offset-5 tableName">行政许可信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>许可文件编号</nobr></th>
                                   <th><nobr>许可文件名称</nobr></th>
                                   <th><nobr>有效期自</nobr></th>
                                   <th><nobr>有效期至</nobr></th>
                                   <th><nobr>许可机关</nobr></th>
                                   <th><nobr>许可内容</nobr></th>
                                   <th><nobr>状态</nobr></th>
                                   <th><nobr>公示日期</nobr></th>
                                   <th><nobr>详情</nobr></th>
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.entPubInfo.admLicInfo.admlicInfos) >0 }">
                                           <c:forEach items="${aicFeedJson.entPubInfo.admLicInfo.admlicInfos }" var="admlicInfo" varStatus="i">
                                                 <tr>
                                                      <td>${admlicInfo.licenceNum }</td>
                                                      <td>${admlicInfo.licenceName }</td>
                                                      <td>${admlicInfo.startDateTime }</td>
                                                      <td>${admlicInfo.endDateTime }</td>
                                                      <td>${admlicInfo.deciAuthority }</td>
                                                      <td>${admlicInfo.content }</td>
                                                      <td>${admlicInfo.status }</td>
                                                      <td>${admlicInfo.pubDate }</td>
                                                      <c:choose>
                                                           <c:when test="${fn:length(admlicInfo.admlicDetail)>0 }">
                                                                <td><span id="admliInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                                           </c:when>
                                                           <c:otherwise>
                                                                <td align="center">---</td>
                                                           </c:otherwise>
                                                      </c:choose>
                                                 </tr>
                                                 <c:if test="${fn:length(admlicInfo.admlicDetail)>0}">
                                                       <tr id="admliInfo_${i.count}_detail" style="display:none">
                                                           <td colspan="8">
                                                               <table class="table table-bordered table-hover">
                                                                     <thead>
                                                                         <tr class="info">
                                                                             <td><nobr>变更事项</nobr></td>
                                                                             <td><nobr>变更时间</nobr></td>
                                                                             <td><nobr>变更前内容</nobr></td>
                                                                             <td><nobr>变更后内容</nobr></td>
                                                                         </tr>
                                                                     </thead>
                                                                     <tbody>
                                                                         <c:forEach items="${admlicInfo.admlicDetail}" var="admlicDetail">
                                                                              <tr>
                                                                                  <td>${admlicDetail.changeItem }</td>
                                                                                  <td>${admlicDetail.changeDateTime }</td> 
                                                                                  <td>${admlicDetail.changePreContent }</td>   
                                                                                  <td>${admlicDetail.changePostContent }</td>                                                                          
                                                                              </tr>
                                                                         </c:forEach>
                                                                     </tbody>
                                                               </table>
                                                           </td>
                                                       </tr>
                                                 </c:if>
                                           </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                         <tr align="center">
                                            <c:forEach begin="0" end="8"><td>---</td></c:forEach>
                                         </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                    </table>
              </div>
              
              
               <!-- 知识产权出质登记信息 -->
               <div data-name="intellectualProRegInfo">
              <h4 id="t2_5">知识产权出质登记信息</h4>
                    
                    <!-- 知识产权出质登记信息表  -->
                    <div class="col-md-3 col-md-offset-5 tableName">知识产权出质登记信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                          <thead>
                              <tr class="success">
                                  <th><nobr>注册号</nobr></th>
                                  <th><nobr>名称</nobr></th>
                                  <th><nobr>种类</nobr></th>
                                  <th><nobr>出质人名称</nobr></th>
                                  <th><nobr>质权人名称</nobr></th>
                                  <th><nobr>质权登记期限</nobr></th>
                                  <th><nobr>状态</nobr></th>
                                  <th><nobr>变化情况</nobr></th>
                              </tr>
                          </thead>
                          <tbody>
                              <c:choose>
                                   <c:when test="${fn:length(aicFeedJson.entPubInfo.intellectualProRegInfo.intellectualProRegInfos)>0 }">
                                          <c:forEach items="${aicFeedJson.entPubInfo.intellectualProRegInfo.intellectualProRegInfos }" var="intellectualProRegInfo">
                                                <tr>
                                                    <td>${intellectualProRegInfo.regNum }</td>
                                                    <td>${intellectualProRegInfo.name }</td>
                                                    <td>${intellectualProRegInfo.type }</td>
                                                    <td>${intellectualProRegInfo.mortgagorName }</td>
                                                    <td>${intellectualProRegInfo.mortgageeName }</td>
                                                    <td>${intellectualProRegInfo.pledgeRegDeadline }</td>
                                                    <td>${intellectualProRegInfo.status }</td>
                                                    <td>${intellectualProRegInfo.changeSitu }</td>
                                                </tr>
                                          </c:forEach>
                                   </c:when>
                                   <c:otherwise>
                                         <tr align="center">
                                            <c:forEach begin="0" end="7"><td>---</td></c:forEach>
                                         </tr>
                                   </c:otherwise>
                              </c:choose>
                          </tbody>
                    </table>
              </div>
              
              
               <!-- 行政处罚信息 -->
              <div data-name="admPunishInfo">
              <h4 id="t2_6">行政处罚信息</h4>
                   
                     <!-- 行政处罚信息表  -->
                    <div class="col-md-3 col-md-offset-5 tableName">行政处罚信息表 </div>
                    <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>行政处罚决定<br>书文号</nobr></th>
                                   <th><nobr>行政处罚内容</nobr></th>
                                   <th><nobr>作出行政处罚决定<br>机关名称</nobr></th>
                                   <th><nobr>作出行政处罚决定<br>日期</nobr></th>
                                   <th><nobr>违法行为类型</nobr></th>
                                   <th><nobr>公示日期</nobr></th>
                                   <th><nobr>备注</nobr></th>
                               </tr>                           
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.entPubInfo.admPunishInfo.admPunishInfos)>0 }">
                                          <c:forEach items="${aicFeedJson.entPubInfo.admPunishInfo.admPunishInfos}" var="admPunishInfo">
                                                 <tr>
                                                     <td>${admPunishInfo.punishRepNum }</td>
                                                     <td>${admPunishInfo.punishContent }</td>
                                                     <td>${admPunishInfo.deciAuthority }</td>
                                                     <td>${admPunishInfo.deciDateTime }</td>
                                                     <td>${admPunishInfo.illegalActType }</td>
                                                     <td>${admPunishInfo.showDate }</td>
                                                     <td>${admPunishInfo.note }</td>
                                                 </tr>
                                          </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                          <tr align="center">
                                            <c:forEach begin="0" end="6"><td>---</td></c:forEach>
                                         </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                    </table>
              </div>
              </div>
              
              <br>
              <!-- 司法协助公示信息  -->
              <div data-name="judAssPubInfo">
                 <h3 id="t3">司法协助公示信息</h3>
                 <hr>
                 
                 <!-- 股权冻结信息 -->
                 <div data-name="equFreezeInfo">
                       <h4 id="t3_1">股权冻结信息</h4>

                       <!-- 股权冻结信息表  -->
                       <div class="col-md-3 col-md-offset-5 tableName">股权冻结信息表</div>
                       <table class="table table-bordered table-hover table-striped">
                             <thead>
                                 <tr class="success">
                                     <th><nobr>被执行人</nobr></th>
                                     <th><nobr>股权数额</nobr></th>
                                     <th><nobr>执行法院</nobr></th>
                                     <th><nobr>协助公示通知书文号</nobr></th>
                                     <th><nobr>状态</nobr></th>
                                     <th><nobr>详情</nobr></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <c:choose>
                                      <c:when test="${fn:length(aicFeedJson.judAssPubInfo.equFreezeInfo.equFreezeInfos)>0 }">
                                             <c:forEach items="${aicFeedJson.judAssPubInfo.equFreezeInfo.equFreezeInfos}" var="equFreezeInfo" varStatus="i">
                                                   <tr>
                                                       <td>${equFreezeInfo.executedPerson }</td>                                                   
                                                       <td>${equFreezeInfo.equAmount }</td>
                                                       <td>${equFreezeInfo.exeCourt }</td>
                                                       <td>${equFreezeInfo.assistPubNoticeNum }</td>
                                                       <td>${equFreezeInfo.status }</td>
                                                       <td align="center"><span id="equFreezeInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                                   </tr>
                                                   <tr id="equFreezeInfo_${i.count}_detail" style="display:none">
                                                       <td colspan="6">${equFreezeInfo.detail }</td>
                                                   </tr>
                                             </c:forEach>                                           
                                      </c:when>
                                      <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="5"><td>---</td></c:forEach>
                                           </tr>
                                       </c:otherwise>
                                  </c:choose>
                           </tbody>
                     </table>
                </div>
                
                 <!-- 股东变更信息 -->
                 <div data-name="stohrChangeInfo">
                      <h4 id="t3_2">股东变更信息</h4>
                      
                      <!-- 股东变更信息表  -->
                      <div class="col-md-3 col-md-offset-5 tableName">股东变更信息表</div>
                      <table class="table table-bordered table-hover table-striped">
                             <thead>
                                 <tr class="success">
                                     <th><nobr>被执行人</nobr></th>
                                     <th><nobr>股权数额</nobr></th>
                                     <th><nobr>受让人</nobr></th>
                                     <th><nobr>执行法院</nobr></th>
                                     <th><nobr>详情</nobr></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <c:choose>
                                      <c:when test="${fn:length(aicFeedJson.judAssPubInfo.stohrChangeInfo.stohrChangeInfos)>0 }">
                                            <c:forEach items="${aicFeedJson.judAssPubInfo.stohrChangeInfo.stohrChangeInfos }" var="stohrChangeInfo">
                                                  <tr>
                                                      <td>${stohrChangeInfo.executedPerson }</td>
                                                      <td>${stohrChangeInfo.equAmount }</td>
                                                      <td>${stohrChangeInfo.assignee }</td>
                                                      <td>${stohrChangeInfo.exeCourt }</td>
                                                      <td align="center"><span id="stohrChangeInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>
                                                  </tr>
                                                  <tr id="stohrChangeInfo_${i.count}_detail" style="display:none">
                                                      <td colspan="5">${stohrChangeInfo.detail }</td>
                                                  </tr>
                                            </c:forEach>
                                      </c:when>
                                      <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="4"><td>---</td></c:forEach>
                                           </tr>
                                      </c:otherwise>
                                 </c:choose>
                             </tbody>
                      </table> 
                 </div>
            </div>
            
            <br>
            <!-- 其他部门公示信息  -->
            <div data-name="othrDeptPubInfo">
                <h3 id="t4">其他部门公示信息</h3>
                <hr>
                
                <!-- 行政许可信息 -->
                <div data-name="admLicInfo">
                     <h4 id="t4_1">行政许可信息</h4>

                     <!-- 行政许可信息表 -->
                     <div id="t4_1_1" class="col-md-3 col-md-offset-5 tableName">行政许可信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>许可文件编号</nobr></th>   
                                   <th><nobr>许可文件名称</nobr></th>
                                   <th><nobr>有效期自</nobr></th>
                                   <th><nobr>有效期至</nobr></th>
                                   <th><nobr>有效期</nobr></th>
                                   <th><nobr>许可机关</nobr></th>
                                   <th><nobr>许可内容</nobr></th>
                                   <th><nobr>状态</nobr></th>
                                   <th><nobr>详情</nobr></th>                            
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                     <c:when test="${fn:length(aicFeedJson.othrDeptPubInfo.admLicInfo.admLicInfos)>0 }">
                                           <c:forEach items="${aicFeedJson.othrDeptPubInfo.admLicInfo.admLicInfos }" var="admLicInfo" varStatus="i">
                                                 <tr>
                                                     <td>${admLicInfo.licenceNum }</td>  
                                                     <td>${admLicInfo.licenceName }</td> 
                                                     <td>${admLicInfo.startDateTime }</td> 
                                                     <td>${admLicInfo.endDateTime }</td> 
                                                     <td>${admLicInfo.expiryDate }</td>
                                                     <td>${admLicInfo.deciAuthority }</td>  
                                                     <td>${admLicInfo.content }</td>  
                                                     <td>${admLicInfo.status }</td>  
                                                     <td align="center"><span id="admLicInfo_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td>                                    
                                                 </tr>
                                                 <tr id="admLicInfo_${i.count}_detail" style="display:none">
                                                     <td colspan="9">
                                                         <h5 class="col-md-3 col-md-offset-5" style="color:#337ab7">行政许可信息表  详情表</h5>
                                                         <table class="table table-bordered table-hover">
                                                                <thead>
                                                                   <tr class="info">
                                                                       <th><nobr>变更事项</nobr></th>
                                                                       <th><nobr>变更日期</nobr></th>
                                                                       <th><nobr>变更前</nobr></th>
                                                                       <th><nobr>变更后</nobr></th>
                                                                   </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:choose>
                                                                         <c:when test="${fn:length(admLicInfo.othrdeptpubAAdmlicInfoDetails)>0}">
                                                                              <c:forEach items="${admLicInfo.othrdeptpubAAdmlicInfoDetails }" var="details">
                                                                                    <tr>
                                                                                        <td>${details.alterItem }</td>
                                                                                        <td>${details.alterDate }</td>
                                                                                        <td>${details.preAlter }</td>
                                                                                        <td>${details.postAlter }</td>
                                                                                    </tr>
                                                                              </c:forEach>
                                                                         </c:when>
                                                                         <c:otherwise>
                                                                              <tr align="center">
                                                                                  <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                                                              </tr>
                                                                         </c:otherwise>
                                                                    </c:choose>
                                                                </tbody>
                                                     </table>
                                                     </td>
                                                 </tr>
                                           </c:forEach>
                                     </c:when>                               
                                     <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="8"><td>---</td></c:forEach>
                                           </tr>
                                     </c:otherwise>
                               </c:choose>
                           </tbody>
                     </table>
                     
                     <!-- 变更信息表 -->
                     <div id="t4_1_2" class="col-md-3 col-md-offset-5 tableName">变更信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                               <tr class="success">
                                   <th><nobr>变更事项</nobr></th>
                                   <th><nobr>变更时间</nobr></th>
                                   <th><nobr>变更前内容</nobr></th>
                                   <th><nobr>变更后内容</nobr></th>
                               </tr>
                           </thead>
                           <tbody>
                               <c:choose>
                                    <c:when test="${fn:length(aicFeedJson.othrDeptPubInfo.admLicInfo.changeInfos)>0 }">
                                          <c:forEach items="${aicFeedJson.othrDeptPubInfo.admLicInfo.changeInfos }" var="changeInfo">
                                                <tr>
                                                    <td>${changeInfo.item }</td>
                                                    <td>${changeInfo.dateTime }</td>
                                                    <td>${changeInfo.preContent }</td>
                                                    <td>${changeInfo.postContent }</td>
                                                </tr>
                                          </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                         <tr align="center">
                                               <c:forEach begin="0" end="3"><td>---</td></c:forEach>
                                         </tr>
                                    </c:otherwise>
                               </c:choose>
                           </tbody>
                     </table>
                </div>
                
                <!-- 行政处罚信息  --> 
                <div data-name="admPunishInfo">
                     <h4 id="t4_2">行政处罚信息</h4>
                     
                     <!-- 行政处罚信息表-->
                     <div class="col-md-3 col-md-offset-5 tableName">行政处罚信息表</div>
                     <table class="table table-bordered table-hover table-striped">
                           <thead>
                                <tr class="success">
                                    <th><nobr>行政处罚决定书文号</nobr></th>
                                    <th><nobr>违法行为类型</nobr></th>
                                    <th><nobr>行政处罚内容</nobr></th>
                                    <th><nobr>作出行政处罚决定机关名称</nobr></th>
                                    <th><nobr>作出行政处罚决定日期</nobr></th>
                                   <!--  <th><nobr>详情</nobr></th> -->
                                    <th><nobr>备注</nobr></th>
                                </tr>
                           </thead>
                           <tbody>
                                <c:choose>
                                     <c:when test="${fn:length(aicFeedJson.othrDeptPubInfo.admPunishInfo.admPunishInfos)>0 }">
                                            <c:forEach items="${aicFeedJson.othrDeptPubInfo.admPunishInfo.admPunishInfos}" var="admPunishInfo" varStatus="i">
                                                  <tr>
                                                       <td>${admPunishInfo.punishRepNum }</td>
                                                       <td>${admPunishInfo.illegalActType }</td>
                                                       <td>${admPunishInfo.punishContent }</td>
                                                       <td>${admPunishInfo.deciAuthority }</td>
                                                       <td>${admPunishInfo.deciDateTime }</td>
                                                       <%-- <td align="center"><span id="admPunishInfo2_${i.count}" style="cursor:pointer;color:#337ab7" title="单击显示">展开</span></td> --%>
                                                       <td>${admPunishInfo.note }</td>
                                                  </tr>
                                                  <%-- <tr id="admPunishInfo2_${i.count}_detail" style="display:none">
                                                      <td colspan="7">${admPunishInfo.detail }</td>
                                                  </tr> --%>
                                            </c:forEach>
                                     </c:when>
                                     <c:otherwise>
                                           <tr align="center">
                                               <c:forEach begin="0" end="5"><td>---</td></c:forEach>
                                           </tr>
                                     </c:otherwise>
                                </c:choose>
                           </tbody>
                     </table>
                </div>   
            </div>
              
       </div>
       <div style="clear:both;zoom:1"></div>
    </div>
    <div class="row" style="color:red">
         <div style="margin-left:30px">
              <h6>注意事项：</h6>
              <ul style="font-size:10px">
                  <li>符号  "---",表示此项无内容.</li>
                  <li>单击"目录"显示目录列表，单击目录列表外任何空白区域，隐藏目录列表.</li>
              </ul>
         </div>
    </div>
</div>

<!-- 返回顶部 -->
<div class="sr-zql-totop"></div>
<jsp:include page="${footerPath}"></jsp:include>


<script src="${ctx}/static/js/bootstrap-treeview.min.js"></script>
<script src="${ctx}/static/js/gsxt/gsxt-detail.js"></script>
 <script>
    
</script>


</body>
</html>