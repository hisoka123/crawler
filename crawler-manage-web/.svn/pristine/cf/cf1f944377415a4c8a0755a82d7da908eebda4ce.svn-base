<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<!-- prettify.css -->
<link href="${ctx}/static/css/prettify.css" rel="stylesheet">

<jsp:include page="../header.jsp"></jsp:include>
<body onload="prettyPrint()">
	<jsp:include page="../nav.jsp"></jsp:include>
	
	<div class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div class="col-md-3">
				<jsp:include page="wiki-menu.jsp"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-9">
				<div id="interface" class="sr-zql-content-right">
				     <!-- 测试窗口 -->
                    <div class="row" style="padding:20px;background-color:#DEDEDE;">
                         <div class="col-md-8">
			                 <h1 style="margin-bottom:8px;">开发文档</h1>
			                 <p>数据服务平基于海量的互联网数据分析，为您的决策提供强有力的数据支撑；<br>API文档提供了详细的功能描述和使用方法，初次使用数据服务我们建议您先阅读使用手册。</p>
		                </div>
		                <div class="col-md-4">
			                <div style="margin:12%;">
				                 <a class="btn btn-large btn-success" href="${ctx}/tools/apitest">开始测试接口</a>
			                </div>
		                </div>
	                </div>
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
    <jsp:include page="../footer.jsp"></jsp:include> 
    
    
    <!--导出pdf 模态框  -->
	<div id="exportModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="font-family:微软雅黑">
	     <div class="modal-dialog">
	           <div class="modal-content" style="margin-top:20%">
                    <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                         <h4 class="modal-title" style="font-weight:bold">导出接口文档</h4>
                    </div>
                    <div class="modal-body" style="font-size:16px;">
                         <div id="exportInterface" style="display:none">
                              <div class="row">
                                    <div class="col-md-3" style="color:green">导出接口 </div>
                                    <div class="col-md-8" style="font-size:15px">
                                         <span id="exportSiteType"></span>
                                         <span id="exportSite"></span>  
                                   </div>
                                   <div class="col-md-1">
                                        <span id="exportClear" class="glyphicon glyphicon-trash" style="font-size:14px;opacity:0.5;cursor:pointer" title="清空"></span>
                                   </div>
                               </div>
                               <div class="row"><hr /></div>
                         </div>
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-offset-3 col-md-9">
                                   <label style="font-weight:normal">
                                       <input type="radio" name="exportType" value="currentExport" checked>导出当前接口文档
                                   </label>
                              </div>
                         </div>
                          <div class="row" style="margin-top:10px">
                              <div class="col-md-offset-3 col-md-9">
                                   <label style="font-weight:normal">
                                       <input type="radio" name="exportType" value="bulkExportWithBookmark" title="全部接口文档合并为一个接口文档">批量导出接口文档（带书签）
                                   </label>
                              </div>
                         </div>
                         <div id="chooseInterface" style="margin-top:20px;padding:0px 10px 0px;display:none;overflow-y:auto;overflow-x:hidden;max-height:200px">
                              <ul class="nav nav-tabs row">
                                  <li class="acitve col-md-6" style="text-align:center">
                                      <a href="#chooseSiteType" data-toggle="tab" >站点类型</a>
                                  </li>
                                   <li class="col-md-6" style="text-align:center">
                                      <a href="#chooseSite" data-toggle="tab" >站点</a>
                                  </li>
                              </ul>
                              
                              <div class="tab-content">
                                   <div id="chooseSiteType" class="tab-pane fade in active" style="padding:15px 0px 15px"></div>
                                   <div id="chooseSite" class="tab-pane fade" style="padding:15px 0px 15px"></div>
                              </div>
                         </div>
                    </div> 
                    <div class="modal-footer">
                          <button type="button" class="btn btn-primary" onclick="wiki_exportPDF();">导出</button>
                          <button id="exportModal_close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>  
	           </div>
	     </div>
	</div>
    
    
    
    
    <!-- 隐藏域 -->
    <input id="ctx" value="${ctx}" style="display:none">
    <input id="wikiDocBase_id" style="display:none">
    
    <!-- 引入js文件 -->
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/tools/wiki.js"></script>
  	<script src="${ctx}/static/js/tools/wiki-ajax.js"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
  	<script src="${ctx}/static/js/sweet-ajax-progress.js" type="text/javascript"></script>
  	<script type="text/javascript">
	    if (window!=top) // 判断当前的window对象是否是top对象
	    	//top.location.href =window.location.href; // 如果不是，将top对象的网址自动导向被嵌入网页的网址
	    	$("html").html("");
	</script>
</body>
</html>

