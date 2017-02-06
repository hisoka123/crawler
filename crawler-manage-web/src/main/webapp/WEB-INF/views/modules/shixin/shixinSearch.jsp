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
<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div data-name="left_menu" class="col-md-2">
			     <jsp:include page="${leftMenuPath}"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a href='${shixinIndexPath}'>失信网</a>";
                                       var path_2="<a href='${shixinSearchPath}'>搜索个人和企业</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!-- 搜索框 -->
                    <div data-name="searchBox" class="row" id="gxsearch">               
                         <div id="searchBox_big" class="col-md-8 col-md-offset-2" style="margin-top:12%">
                              <div class="input-group" >
                                   <span class="input-group-addon" id="sizing-addon1"><span style="color:red">*</span>被执行人姓名/名称</span>
                                   <input id="name" type="text" class="form-control" placeholder="请输入被执行人姓名/名称,如，信和汇诚信用管理(北京)有限公司" maxlength="200">
                              </div>
                              <div class="input-group"  style="margin-top:10px">
                                   <span class="input-group-addon" id="sizing-addon1">身份证号码/组织机构代码</span>
                                   <input id="identity" type="text" class="form-control" placeholder="请输入身份证号码/组织机构代码,如,180109300000000987" maxlength="200">
                              </div>
                              <div style="margin-left:10%;margin-top:10px">
                                   <button id="searchBox_big_btn" class="btn btn-primary" type="button" style="height:40px;width:100px">
                                       <i class="glyphicon glyphicon-search"></i>&nbsp;搜索
                                   </button>
                              </div>
                         </div>
                    </div>
                    
                    
                     <div id="chooseDataSource" class="row" style="margin-top:3%;display:none">
                         <div class="col-md-5 col-md-offset-3">
                              <label class="checkbox-inline">
                                     <input name="chooseDataSource" type="radio" value="interfaceVersion" checked> 接口版
                              </label>
                              <label class="checkbox-inline">
                                     <input name="chooseDataSource" type="radio"  value="dbVersion"> 数据库版
                              </label>
                         </div>
                    </div>

                    <div id="info" class="row" style="margin-top:5%;display:none">
                         <div class="col-md-8 col-md-offset-2" style="color:red">
                         </div>
                    </div>                    
                   
                      
               </div>
		   </div>
	   </div>
   </div>
	
<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">
<input id="env" value="${env}" style="display:none">
   
<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/common.js"></script>


<!-- 失信网 -->
<script src="${ctx}/static/js/shixin/shixin-common.js"></script>
<script src="${ctx}/static/js/shixin/shixin-ajax.js"></script>
<script src="${ctx}/static/js/ownerTask/dishonesty/dishonesty-ajax.js"></script>

</body>
</html>