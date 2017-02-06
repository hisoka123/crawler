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

<link href="${ctx}/static/css/ownerTask/gsxt.css" rel="stylesheet">
<link href="${ctx}/static/css/ownerTask/ownerTask-common.css" rel="stylesheet">

<body>
<jsp:include page="${navPath}"></jsp:include>

<div data-name="text" class="container-fluid sr-zql-content">
  <div id="body" class="row">
    <!-- 左侧导航栏 -->
    <div id="left_menu" class="col-md-2"></div>

    <!-- 右侧主体内容 -->
    <div class="col-md-10">
      <div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
        <p class="tableName">人法网任务列表</p>
        <div data-name="chooseConditions" class="row">
          <div data-name="chooseConditions_1" class="col-md-6" >
            <div data-name="chooseSearchType" class="row" style="margin-left:1%;padding:10px 0px">
              <span data-name="taskSearchType" style="font-weight:bold">搜索类型：</span>
                                    <span id="chose_searchType" style="font-size:13px">
                                          <span data-name="searchType_all">全部</span>
                                          <span data-name="searchType_name">公司名称</span>
                                          <span data-name="searchType_IDCard">身份证号码</span>
                                    </span>
            </div>
            <div data-name="chooseTaskStatus" class="row" style="margin-left:1%;padding:10px 0px">
              <span data-name="taskStatus" style="font-weight:bold">任务状态：</span>
                                    <span id="chose_status" style="font-size:13px">
                                          <span data-name="searchStatus_all">全部</span>
                                          <span data-name="searchStatus_success">成功完成</span>
                                          <span data-name="searchStatus_waiting">等候处理</span>
                                          <span data-name="searchStatus_feedback">反馈处理</span>
                                          <span data-name="searchStatus_inProcess">正在处理</span>
                                    </span>
            </div>
          </div>
          <div data-name="chooseConditions_2" class="col-md-5 col-md-offset-1" >
            <div class="row" style="visibility:hidden">占位</div>
            <div class="input-group" style="padding-bottom:5px">
              <input id="searchKeyword" type="text" class="form-control" placeholder="请根据搜索类型输入关键字。" maxlength="50" disabled>
                                      <span class="input-group-btn">
                                            <button id="search" class="btn btn-primary" type="button"><i class="glyphicon glyphicon-search"></i></button>
                                      </span>
            </div>
          </div>
        </div>
        <table class="table table-bordered table-hover table-striped">
          <thead>
          <tr class="success">
            <th><nobr>序号</nobr></th>
            <th><nobr>类型</nobr></th>
            <th><nobr>关键字</nobr></th>
            <th><nobr>任务状态</nobr></th>
            <th><nobr>创建时间</nobr></th>
            <th><nobr>完成时间</nobr></th>
            <th><nobr>操作</nobr></th>
            <th><nobr>备注</nobr></th>
          </tr>
          </thead>
          <tbody>
          </tbody>
        </table>

          <div id="page" style="margin-left:15%">
          </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="${footerPath}"></jsp:include>

<!-- 隐藏域 -->
<input id="ctx" value="${ctx}" style="display:none">

<!-- script -->
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/bootstrap-treeview.min.js"></script>
<script src="${ctx}/static/js/commonSelf.js"></script>

<script src="${ctx}/static/js/ownerTask/leftMenuOT.js"></script>
<script src="${ctx}/static/js/ownerTask/ownerTask-ajax.js"></script>

<script src="${ctx}/static/js/ownerTask/peopleCourt/peopleCourt-ajax.js"></script>
<script src="${ctx}/static/js/ownerTask/peopleCourt/peopleCourt-common.js"></script>
<script src="${ctx}/static/js/renfawang/renfawang-ajax.js"></script>

<!-- 获取全部站点，查看enabled属性 -->
<script src="${ctx}/static/js/tools/wiki-ajax.js"></script>

</body>
</html>