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
	
	
	<div class="container sr-zql-container">
		<div class="row sr-zql-rowbox">
			<!-- 左侧导航栏 -->
			<div class="col-md-3 sr-zql-boxleft">
				<form id="left-form" action="#" method="get">
					<!-- API分类 -->
					<div>
						<label>API分类：
							<select id="api_cate" name="api_cate" autocomplete="off" onChange="apitest_createApiNames(this);">
							</select>
						</label>
					</div>
					
					<!-- API名称 -->
					<div>
						<label>API名称：
							<select id="api_name" name="api_name" autocomplete="off" onChange="apitest_choseAPI(this);">
							</select>
						</label>
					</div>
					
					<!-- 城市 -->
					<div id="api_city" style="display: none">
						<label>省市名称：
							<select id="api_cityname" name="api_cityname" autocomplete="off">
								<option value="beijing">北京</option>
								<option value="shanghai">上海</option>
								<option value="tianjin">天津</option>
								<option value="shaanxi">陕西</option>
								<option value="shanxi">山西</option>
								<option value="guangxi">广西</option>
								<option value="henan">河南</option>
								<option value="hubei">湖北</option>
								<option value="yunnan">云南</option>
								<option value="shandong">山东</option>
								<option value="jiangsu">江苏</option>
								<option value="anhui">安徽</option>
								<option value="heilongjiang">黑龙江</option>
								<option value="ningxia">宁夏</option>
								<option value="liaoning">辽宁</option>
								<option value="hebei">河北</option>
								<option value="gansu">甘肃</option>
								<option value="sichuan">四川</option>
								<option value="qinghai">青海</option>
								<option value="xizang">西藏</option>
								<option value="xinjiang">新疆</option>
								<option value="fujian">福建</option>
								<option value="hunan">湖南</option>
								<option value="guizhou">贵州</option>
							</select>
						</label>
					</div><br/>
					
					<!-- 获取方式 -->
					<div>
						获取方式：<hr/>
						<div class="radio">
							<label><input type="radio" name="req_method" value="get">get</label>
							<label><input type="radio" name="req_method" value="post">post</label>
						</div><hr/>	
					</div><br/>
					
					<!-- 获取API文档 -->
					<div>
						API文档：
						<span id="doc_btn" title="${ctx}/tools/wiki" style="cursor:pointer;color:blue" onclick="apitest_showAPI();">点击获取文档</span>
					</div><br/>
					
					<!-- API参数 -->
					<div class="params">
						<p>API参数（自动编码）：</p>
						<div id="params" class="param-container">
							<!-- JS填充参数项 -->
						</div>
						<button id="btn_addparam" type="button" class="btn btn-info btn-sm" onclick="apitest_addParams();"><i class="glyphicon glyphicon-plus"></i>&nbsp;添加</button>
					</div>
					<div style="color:green">注：crtl+z显示编码转换工具</div>
					
					<!-- 调用接口按钮 -->
					<div>
						<button id="btn_play" type="button" class="btn btn-success btn-sm" onclick="apitest_submit(this);"><i class="glyphicon glyphicon-play"></i>&nbsp;调用接口</button>						
					</div>
				</form>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-9 sr-zql-boxright">
				<div class="sr-zql-boxright-req">
					请求：<a data-toggle="collapse" data-target="#req_content">折叠请求</a>
					<div class="collapse in">
						<div id="req_content" class="sr-zql-boxright_cont boxright-req">
							<!-- 填充请求参数 -->
						</div>
					</div>
				</div>
				
				<div class="sr-zql-boxright-res">
					<table style="width: 100%;">
						<tr>
							<td>返回内容&nbsp;&nbsp;[注]gson转json串时默认将一些字符自动转换为unicode转义字符：</td>
							<%-- <td style="text-align:right;margin-right:5%"><a href="${ctx}/tools/wiki?docName=help_error.html"><a href="#">常见错误代码及释义</a></td> --%>
							<td style="display:none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td style="margin-left:10%;display:none">
							    <a id="log" target="_blank">日志</a>
							</td>
						</tr>
					</table>
					<div id="responseResult" class="sr-zql-boxright_cont boxright-res">
						<!-- 填充返回内容 -->
					</div>
				</div>
			</div>
			
			<!-- 返回顶部 -->
			<div class="sr-zql-topret-up"></div>
			<div class="sr-zql-topret-down" id="${ctx}/tools/wiki"></div>
		</div>
	</div>
    <jsp:include page="../footer.jsp"></jsp:include>
 	<jsp:include page="tools-model.jsp"></jsp:include>
 	
 	
 	<div id="logShow" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 	     <div class="modal-dialog">
              <div class="modal-content">
                   <div class="modal-header">
                   </div>
                   <div id="logContent" class="modal-body" style="overflow-y:auto;overflow-x:hidden;max-height:400px">
                                                                暂无日志
                   </div>
                   <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                   </div>
              </div>
         </div> 	
 	</div>
 	
 	<!-- 隐藏域 -->
 	<input id="ctx" value="${ctx}" style="display:none">
    
    <script src="${ctx}/static/js/jquery.md5.js"></script>
    <script src="${ctx}/static/js/prettify.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-form-collection.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-ajax-progress.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/sweet-totop.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/tools/wiki-ajax.js"></script>
    <script src="${ctx}/static/js/tools/apitest.js" type="text/javascript"></script>
    
</body>
</html>

