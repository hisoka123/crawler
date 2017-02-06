<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<!DOCTYPE html>
<html>

<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
      $(function(){
    	     var title="${title}-站点管理";
    	     $("title").html(title);
      })
</script>

<body>
   <jsp:include page="${navPath}"></jsp:include>
	
	
	<div data-name="text" class="container-fluid sr-zql-content">
         <div class="row" style="margin-bottom:3%">
              <div class="col-md-1 col-md-offset-11">
                   <i id="add" class="glyphicon glyphicon-plus" title="增加站点" style="font-size:17px;cursor:pointer;"></i>
                   <i id="edit" class="glyphicon glyphicon-share-alt" title="返回站点列表" style="margin-left:5px;font-size:17px;cursor:pointer;display:none"></i>
              </div>
         </div>
         <div id="addArea" style="display:none">
               <div class="row" style="font-size:15px;">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold"><span style="color:red">*&nbsp;</span>站点名称</div>
                    <div class="col-md-4"><input id="name" type="text" class="form-control" maxlength="15" placeholder="限15字(含)内"></div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>英文名称</div>
                    <div class="col-md-4"><input id="pyName" type="text" class="form-control" maxlength="25"></div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>原网地址</div>
                    <div class="col-md-4"><input id="siteURL" type="text" class="form-control" maxlength="250" placeholder="例：https://ipcrs.pbccrc.org.cn/"></div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>站点类型</div>
                    <div class="col-md-2">
                         <select id="type" class="form-control">
                                 <option value="请选择">----- 请选择 -----</option>
                         </select>
                    </div>
                    <div class="col-md-2">
                         <input id="typeOther" type="text" class="form-control" disabled>
                    </div>
                    <div class="col-md-1" style="margin-top:5px">类</div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>可&nbsp;&nbsp;用&nbsp;&nbsp;性</div>
                    <div class="col-md-2 checkbox-inline" style="margin-top:5px">
                         <input type="radio" name="isEnabled" value="1" checked>可用
                         <input type="radio" name="isEnabled" value="0" style="margin-left:10px">不可用
                    </div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>站内地址</div>
                    <div class="col-md-4"><input id="linkUrl" type="text" class="form-control" placeholder="例：/modules/pbccrc" maxlength="200"></div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>任务地址</div>
                    <div class="col-md-4"><input id="ownerTaskUrl" type="text" class="form-control" placeholder="例：/ownerTask/pbccrc" maxlength="200"></div>
               </div>
               <div class="row" style="font-size:15px;margin-top:10px">
                    <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>站点图标</div>
                    <div class="col-md-4"><input id="siteLogo" type="text" class="form-control" placeholder="例：/static/imgs/logo/zhengxin01.png" maxlength="200"></div>
               </div>
              <div class="row" style="margin-top:10px">
                   <div class="col-md-2 col-md-offset-4">
                        <img id="siteLogoShow" class="img-rounded" src="${ctx}/static/imgs/logo/zhengxin01.png" style="width:100px;margin-left:10%;color:red" alt="检查图片路径">
                   </div>
              </div>
              <div class="row" style="font-size:15px;margin-top:10px">
                   <div class="col-md-1 col-md-offset-3" style="margin-top:5px;font-weight:bold;"><span style="color:red">*&nbsp;</span>站点简介</div>
                    <div class="col-md-4"><input id="siteInfo" type="text" class="form-control" placeholder="限30字(含)内" maxlength="30"></div>
              </div>
              
              <div class="row" style="font-size:15px;margin-top:3%">
                   <div class="col-md-1 col-md-offset-4">
                        <button id="save" type="button" class="btn btn-primary form-control">保&nbsp;&nbsp;存</button>
                   </div>
                   <div class="col-md-1 col-md-offset-1">
                        <button id="reset" type="button" class="btn btn-primary form-control">重&nbsp;&nbsp;置</button>
                   </div>
              </div>
         </div>
         <div id="editArea" >
              <div class="row">
                   <div class="col-md-2 col-md-offset-3" style="font-weight:bold;font-size:20px;margin-bottom:15px">站点类型</div>
                   <div class="col-md-1 col-md-offset-2" style="font-weight:bold;font-size:20px;margin-bottom:15px">站点</div>
              </div>
              
              <div id="editAreaShow">
              </div>
         </div>
         

	</div>
<jsp:include page="${footerPath}"></jsp:include>


<!-- script -->
<script src="${ctx}/static/js/manage/siteManage.js"></script>
<script src="${ctx}/static/js/manage/manage-ajax.js"></script>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none"> 
<input id="status" style="display:none"> 
</body>
</html>