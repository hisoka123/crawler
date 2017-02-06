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

        <!-- 右侧主体内容 -->
        <div class="col-md-10">
            <div id="right_content" class="sr-zql-content-right" style="padding-left:20px;margin-right:0px;">


                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">用户列表</div>
                        <div class="pull-right"><span id="totalCounts" class="badge badge-info"></span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>登录名</th>
                                <th>中文名</th>
                                <th>角色</th>
                                <th>状态</th>
                                <th>操作</th>
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


    </div>
</div>
<!-- 返回顶部 -->
<div class="sr-zql-totop sr-h-totop"></div>
<jsp:include page="${panel}"></jsp:include>
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
                <p>用户名：<input type="" class="input-xlarge" value="" id="loginName" disabled></p>
                <p>中文名：<input type="text" class="input-xlarge" id="userNameCh" value=""/></p>
                <p>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-1 col-md-1">角&nbsp;&nbsp;&nbsp;&nbsp;色：</div>--%>
                        <%--<div class="col-sm-4 col-md-4"><select id="roleList" class="form-control input-sm">--%>
                             <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <div class="group">
                    <div class="">角&nbsp;&nbsp;&nbsp;&nbsp;色：</div>
                    <div class=""><select id="roleList" class="form-control input-sm">
                    </select>
                    </div>
                </div>
                </p>
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
<script src="${ctx}/static/js/admin/common.js"></script>

<script type="text/javascript">


    $(function () {
        modifyandImportData(dataAccessWithPageInfo("${ctx}/admin/user/getUserList"),{page:1});
        action = "${ctx}/admin/user/getUserList";
        pagerEffect(action);
    });

    function modifyandImportData(result) {
        var dataContent = ""; //列表内容

        $.each(result, function (index, content) {

            dataContent += "<tr><td>" + (index+1) + "<input type=\"hidden\" value=\"" + this.id + "\"></td><td>" + this.loginName + "</td><td>" + this.name + "</td><td>" + this.roleList[0].name + "</td>" +
                    "<td>" + this.status + "</td>";

            dataContent += "<td><div class=\"btn-group\">" +
                    "<a class=\"btn btn-mini dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">操作 <span class=\"caret\"></span></a>" +
                    "<ul class=\"dropdown-menu\" aria-labelledby=\"drop3\">" +
                    "<li><a href=\"#\" onclick=\"clickOperation('update',"+this.id+")\"><i class=\"icon-pencil\"></i> 编辑</a></li>" +
                    "<li><a href=\"#\" onclick=\"clickOperation('resetPwd',"+this.id+")\"><i class=\"icon-pencil\"></i>重置密码</a></li>";

            if(this.status == "active") {
                dataContent += "<li><a href=\"#\" onclick=\"clickOperation('close',"+this.id+")\"><i class=\"icon-pencil\"></i>停用</a></li>";
            }else{
                dataContent += "<li><a href=\"#\" onclick=\"clickOperation('open',"+this.id+")\"><i class=\"icon-pencil\"></i>启用</a></li>";
            }
            dataContent += "</ul></div></td></tr>";
        })

        $("#totalCounts").html(totalCounts);
        $("#dataContent").html(dataContent);
    }


    //处理操作
    function clickOperation(operation,id) {
        operatedId = id;

        if (operation == "update") {
            openUpdatePanel();
        } else if (operation == "resetPwd") {
            openResetConfirmPanel();
        } else if (operation == "close") {
            openCloseConfirmPanel();
        } else if (operation == "open") {
            openOpenConfirmPanel();
        } else {
            openErrorPanel();
        }

    }



    function openUpdatePanel(){
        var actionUrl = "${ctx}/admin/user/getById";
        var actionUrl2 = "${ctx}/admin/role/getRoleList";
        var param = {id:operatedId};
        var result = dataAccess(actionUrl,param);
        var resultOfType = dataAccess(actionUrl2,{page:1})
        result = result.data;
        resultOfType =resultOfType.data;

        var roleLists = resultOfType.entities;
        var roleListHtml = "";
        for(var i=0;i<roleLists.length;i++){
            if(roleLists[i].name == result.roleList[0].name){
                roleListHtml += "<option selected value = \"" + roleLists[i].id + "\">" + roleLists[i].name + "</option>";
            }else {
                roleListHtml += "<option value = \"" + roleLists[i].id + "\">" + roleLists[i].name + "</option>";
            }
        }

        $("#updateModal").modal();
        $("#loginName").val(result.loginName);
        $("#userNameCh").val(result.name);
        $("#roleList").html(roleListHtml);

    }

    function openResetConfirmPanel(){
        $("#confirmModal").modal();
        $("#confirmTitle").html("重置密码");
        $("#confirmContent").html("确定重置密码？");
        $("#actionButton").attr("onclick","doResetPassword()");
    }

    function openCloseConfirmPanel(){
        $("#confirmModal").modal();
        $("#confirmTitle").html("停用用户");
        $("#confirmContent").html("确定停用用户？");
        $("#actionButton").attr("onclick","doCloseUserAcc()");
    }

    function openOpenConfirmPanel(){
        $("#confirmModal").modal();
        $("#confirmTitle").html("启用用户");
        $("#confirmContent").html("确定启用用户？");
        $("#actionButton").attr("onclick","doOpenUserAcc()");
    }

    function doOpenUserOperation(){

    }








    function doUpdate(){

        var actionUrl = "${ctx}/admin/user/updateUser";
        var userNameChValue = $("#userNameCh").val();
        var selectRoleValue = parseInt($("#roleList").find(":selected").val());
        var data = {id:operatedId,userNameCh:userNameChValue,roleId:selectRoleValue};

        var result = dataAccess(actionUrl,data);
        if(result.errorMessage == 'undefined' ||result.errorMessage == null ){
            onSuccess();
        }else{
            openFaildPanel();
        }
        resetOperatedId();
    }

    function doResetPassword(){

        var actionUrl = "${ctx}/admin/user/resetPassword";
        var data = {id:operatedId};

        var result = dataAccess(actionUrl,data);
        if(result.errorMessage == 'undefined' ||result.errorMessage == null ){
            openSuccessPanel();
        }else{
            openFaildPanel();
        }
        resetOperatedId();
    }

    function doCloseUserAcc(){
        var actionUrl = "${ctx}/admin/user/stopUserAcc";
        var data = {id:operatedId};

        var result = dataAccess(actionUrl,data);
        if(result.errorMessage == 'undefined' ||result.errorMessage == null || result!='error'){
            onSuccess();
        }else{
            openFaildPanel();
        }
        resetOperatedId();
    }

    function doAfterCloseUserAcc(){
        location.reload();
    }

    function doOpenUserAcc(){
        var actionUrl = "${ctx}/admin/user/openUserAcc";
        var data = {id:operatedId};

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