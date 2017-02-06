<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/admin/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/admin/pathVariable.jsp" %>
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
            <div id="right_content" class="sr-zql-content-right" style="padding-left:20px;margin-right:0px;">
                <h2>
                    欢迎您：
                    <shiro:hasRole name="系统管理员">系统管理员<br/></shiro:hasRole>
                    <shiro:hasRole name="普通用户">普通用户<br/></shiro:hasRole>
                </h2>
            </div>
        </div>


    </div>
</div>
<!-- 返回顶部 -->
<div class="sr-zql-totop sr-h-totop"></div>

<jsp:include page="${footerPath}"></jsp:include>

<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/defineConstant.js"></script>
<script src="${ctx}/static/js/indexedDB.js"></script>
<script src="${ctx}/static/js/common.js"></script>

<script type="text/javascript">
    $(function(){

        searchBoxPlaceholder("all");
        leftMenu();



        $("#searchBox_big_btn").click(function(){
            // personSearch_action("${ctx}",searchBox_Big(),personSearch_createModulesSearchResultsObject());
            personSearch_action("${ctx}",searchBox_Big());
        })

        $("#searchBox_small_btn").click(function(){
            // personSearch_action("${ctx}",searchBox_small(),personSearch_createModulesSearchResultsObject());
            personSearch_action("${ctx}",searchBox_small());
        })

    });


</script>


</body>
</html>