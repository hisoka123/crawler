<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<!-- prettify.css -->
<link href="${ctx}/static/css/prettify.css" rel="stylesheet">

<!-- RTE -->
<link rel="stylesheet" href="${ctx}/static/css/summernote.css">
<link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet">


<jsp:include page="../header.jsp"></jsp:include>
<body>
	
	<jsp:include page="../nav.jsp"></jsp:include>
	
	<div class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div class="col-md-3">
				<jsp:include page="wiki-menu.jsp"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-9">
				<div class="sr-zql-content-right">
				
				    <div data-name="siteType" class="row" style="min-height:34px">
					  	  <div class="col-md-2" style="font-size:21px;margin-top:5px">
					  	        <span style="color:red">*&nbsp;</span>站点类型
					  	  </div>
					  	  <div class="col-md-4">
					  	       <select id="siteType" class="form-control" onChange="wikiadd_siteTypeChange(this);">
					  	             <option name="00" value="选择站点类型">-----&nbsp;选择站点类型&nbsp;-----</option>
					  	       </select>
					  	  </div>
					</div>
					
					<div data-name="site" class="row" style="min-height:34px;margin-top:20px">
					  	  <div class="col-md-2" style="font-size:21px;margin-top:5px">
					  	        <span style="color:red">*&nbsp;</span>站&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点
					  	  </div>
					  	  <div class="col-md-4">
					  	       <select id="site" class="form-control" onChange="wikiadd_siteChange(this);">
					  	                <option name="00" value="选择站点">-----&nbsp;选&nbsp;&nbsp;择&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;点&nbsp;-----</option>
					  	       </select>
					  	  </div>
					 	  
					</div>
					<div data-name="apiDocTitle" class="row" style="min-height:34px;margin-top:20px">
					  	  <div class="col-md-2" style="font-size:21px;margin-top:5px">
					  	        <span style="color:red">*&nbsp;</span>API&nbsp;&nbsp;接口
					  	  </div>
					  	  <div class="col-md-4">
					  	       <select id="apiDocTitle" class="form-control" onChange="wikiadd_apiInterfaceChange(this);">
					  	              <option name="00" value="选择API接口">-----&nbsp;选择API接口&nbsp;-----</option>
					  	       </select>
					  	  </div>
					  	  <div class="col-md-1" style="margin-top:10px">
					  	       <i id="deleteApi" class="glyphicon glyphicon-trash" title="删除当前接口" style="cursor:pointer;display:none"></i>
					  	  </div>
					</div>
					<!-- 新增接口的参数 -->
	               <div id="newApiShow" class="row" style="margin-top:20px;padding-left:3%;display:none">
	  	                <div class="col-md-9" style="background-color:#f7f7f9;padding-top:5px;padding-bottom:15px">
	  	                     <div class="row">
	  	                          <div class="col-md-1">
	  	                              <i id="newApi_edit" class="glyphicon glyphicon-edit" title="编辑" style="cursor:pointer;padding-left:10px"></i>
	  	                          </div>
	  	                          <!-- <div class="col-md-offset-10 col-md-1">
	  	                              <i id="newApi_delete" class="glyphicon glyphicon-trash" title="删除" style="cursor:pointer"></i>
	  	                          </div> -->
	  	                   </div>
	  	                   <div class="row" style="margin-top:10px">
	  	                        <div class="col-md-2" style="font-size:16px;font-family:黑体">接口名称:</div>
	  	                        <div id="newApi_titleShow" class="col-md-4" style="font-size:16px;word-wrap:break-word"></div>
	  	                        <div class="col-md-2" style="font-size:16px;font-family:黑体">接口全拼:</div>
	  	                        <div id="newApi_nameShow" class="col-md-4" style="font-size:16px;word-wrap:break-word"></div>
	  	                  </div>
	  	                  <div class="row" style="margin-top:10px">
	  	                       <div class="col-md-2" style="font-size:16px;font-family:黑体">传送方式:</div>
	  	                       <div id="newApi_requestMethodShow" class="col-md-4" style="font-size:16px;word-wrap:break-word"></div>
	  	                       <div class="col-md-2" style="font-size:16px;font-family:黑体">接口参数:</div>
	  	                       <div id="newApi_paramsShow" class="col-md-4" style="font-size:16px;word-wrap:break-word"></div>
	  	                 </div>
	  	                 <div class="row" style="margin-top:10px">
	  	                      <div class="col-md-2" style="font-size:16px;font-family:黑体">接口路径:</div>
	  	                       <div id="newApi_pathShow" class="col-md-4" style="font-size:16px;word-wrap:break-word"></div>
	  	                 </div>
	                 </div>
                </div>
					<div class="row" style="margin-top:20px">
						<div class="col-md-2" style="font-size:21px;">
						     <span style="color:red">*&nbsp;</span>接口说明
						</div>
						<div class="col-md-2 col-md-offset-7" style="font-size:21px">
						     <span title="1.带(...)标志的字段为替换字段；&#13;2.在'源代码'状态下，增删表格行数; &#13;3.在'源代码'状态下，输入示例">书写格式</span>
						</div>
						<br>
						<div class="col-md-12" style="margin-top:10px">
							<div id="rte"></div>
						</div>
					</div>
					
					<div class="row" >
					     <div class="col-md-2">
					          <button id="subdoc" type="button" class="btn btn-primary" onClick="wikiadd_submitDocClick(this)">&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;</button>
					     </div>
					     <!-- <div class="col-md-10" style="color:red;font-size:16px">123</div> -->
					</div>
				</div>
				
				
				
				
			</div><!--右侧主体内容结束  -->
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include> 
	
	
	
	
	<!-- 模态框 -->
	<div id="newApi" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="font-family:微软雅黑">
	     <div class="modal-dialog">
	           <div class="modal-content" style="margin-top:20%">
                    <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                         <h4 class="modal-title" style="font-weight:bold">添加接口信息</h4>
                    </div>
                    <div class="modal-body">
                         <div class="row">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>接口名称：
					  	      </div>
                              <div class="col-md-6"><input id="newApi_title" class="form-control" type="text" maxLength="100"></div>
                         </div>  
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>接口全拼：
					  	      </div>
                              <div class="col-md-6"><input id="newApi_name" class="form-control" type="text" maxLength="100"></div>
                         </div>
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>接口图标：
					  	      </div>
                              <div class="col-md-6"><input id="newApi_titleIcon" class="form-control" type="text" maxLength="100"></div>
                         </div> 
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>传送方式：
					  	      </div>
                              <div id="newApi_requestMethod" class="col-md-6" style="margin-top:5px">
                                   <label class="checkbox-inline">
                                          <input type="radio" name="newApi_requestMethod" value="get" checked>GET
                                   </label>
                                   <label class="checkbox-inline">
                                          <input type="radio" name="newApi_requestMethod" value="post">POST
                                   </label>
                              </div>
                         </div>
                          
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>接口参数：
					  	      </div>
                              <div class="col-md-6"><input id="newApi_params" class="form-control" type="text" maxLength="100">&nbsp;&nbsp;<span style="color:red">多个形参之间以逗号分隔</span></div>
                         </div> 
                         <div class="row" style="margin-top:10px">
                              <div class="col-md-3" style="font-size:17px;margin-top:5px">
					  	        <span style="color:red">&nbsp;&nbsp;*&nbsp;</span>接口路径：
					  	      </div>
                              <div class="col-md-6"><input id="newApi_path" class="form-control" type="text" maxLength="200"></div>
                         </div>                                           
                    </div>
                    <div class="modal-footer">
                          <button id="newApi_confirm" type="button" class="btn btn-primary" onClick="wikiadd_paramsConfirm(this);">确认</button>
                          <button id="newApi_close" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
	           </div>
	     </div>
	</div>
	
	
	
    
    <!-- 隐藏域 -->
    <input id="ctx" value="${ctx}" style="display:none">
    <input id="editStatus" value="none" style="display:none"><!-- add /  update / none -->
    <input id="apiContentID" style="display:none"> <!-- 更新文档时，修放apiContent id -->
    
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
  	<script src="${ctx}/static/js/sweet-ajax-progress.js" type="text/javascript"></script>
  	<script src="${ctx}/static/js/tools/wikiadd.js"></script>
  	<script src="${ctx}/static/js/tools/wiki-ajax.js"></script>
  	<script src="${ctx}/static/js/summernote/summernote.min.js"></script>
  	<script src="${ctx}/static/js/summernote/summernote-zh-CN.js"></script>
</body>
</html>

