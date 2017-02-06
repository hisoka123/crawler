<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/admin/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/admin/pathVariable.jsp" %>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script src="${ctx}/static/js/sweet-totop.js"></script>
<body>
<jsp:include page="${navPath}"></jsp:include>

<div data-name="text" class="container-fluid sr-zql-content">
    <div class="row">
        <!-- 左侧导航栏 -->
        <div data-name="left_menu" class="col-md-2">
            <jsp:include page="${leftMenuPath}"></jsp:include>
        </div>

        <!-- 用户主体内容 -->
        <div class="col-md-10">
            <div id="right_content" class="sr-zql-content-right" style="padding-left:20px;margin-right:0px">

                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">角色列表</div>
                        <div class="pull-right"><span id= "totalCounts" class="badge badge-info"></span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>权限</th>
                                <!-- <th>操作</th> -->
                            </tr>
                            </thead>
                            <tbody id="dataContent">

                            </tbody>
                        </table>
                    </div>
                    <%--分页条--%>
                    <div id="pagerContent">
                        <ul id="pager"></ul>
                    </div>
                </div>

            </div>
        </div>

        <!-- 返回顶部 -->
        <div class="sr-zql-totop"></div>
    </div>
</div>

<jsp:include page="${footerPath}"></jsp:include>

<!-- updateModal -->
<div id="updateModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">编辑面板</h4>
            </div>
            <div class="modal-body">
                <p>角色名称：<input type="" class="input-xlarge" value="" id="roleName"></p>
                <p>权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：<input type="text" class="input-xlarge" id="authority" value="" disabled/><p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="doUpdate()">更新</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- script -->
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<%--<script src="${ctx}/static/js/json2.js"></script>
<script src="${ctx}/static/js/jquery.md5.js"></script>
<script src="${ctx}/static/js/indexedDB.js"></script>--%>
<script src="${ctx}/static/js/admin/common.js"></script>



<script type="text/javascript">
    $(function () {
        modifyandImportData(dataAccessWithPageInfo("${ctx}/admin/role/getRoleList",{page:1}));
        action = "${ctx}/admin/role/getRoleList";
        pagerEffect(action);
    });


    function modifyandImportData(result) {
        var dataContent = ""; //列表内容

        $.each(result, function (index, content) {

            dataContent += "<tr><td>" + (index+1) + "<input type=\"hidden\" value=\"" + this.id + "\"></td><td>" + this.name + "</td><td>" + this.permissions + "</td>";
/* 
            dataContent += "<td><div class=\"btn-group\">" +
                    "<a class=\"btn btn-mini dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">操作 <span class=\"caret\"></span></a>" +
                    "<ul class=\"dropdown-menu\" aria-labelledby=\"drop3\">" +
                    "<li><a href=\"#\" onclick=\"clickOperation('update',"+this.id+")\"><i class=\"icon-pencil\"></i> 编辑</a></li>";
 */
            dataContent += "</ul></div></td></tr>";
        })

        //$("#totalCounts").html(totalCounts);
        $("#dataContent").html(dataContent);
    }


    //处理操作
    function clickOperation(operation,id) {
        operatedId = id;

        if(operation == "update"){
            openUpdatePanel();
        }else{
            openErrorPanel();
        }

    }

    function openUpdatePanel(){
        var actionUrl = "${ctx}/admin/role/getById";
        var param = {id:operatedId};
        var result = dataAccess(actionUrl,param);
        result = result.data;


        $("#updateModal").modal();
        $("#roleName").val(result.name);
        $("#authority").val(result.permissions);


    }

    function doUpdate(){

        var actionUrl = "${ctx}/admin/role/updateRole";
        var roleName = $("#roleName").val();
        var data = {id:operatedId,roleName:roleName};

        var result = dataAccess(actionUrl,data);
        if(result.errorMessage == 'undefined' ||result.errorMessage == null ){
            onSuccess();
        }else{
            openFaildPanel();
        }
        resetOperatedId();
    }

</script>
</body>
</html>
