<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp"%>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>
<script type="text/javascript">
	$(function() {
		var title = "${title}";
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
				<div id="right_content" class="sr-zql-content-right sr-h-font"
					style="padding-left: 20px; margin-right: 0px">

					<!--面包屑路径 -->
					<div data-name="breadcrumb">
						<div id="breadcrumbPath">
							<jsp:include page="${breadcrumbPath}"></jsp:include>
							<script type="text/javascript">
								$(function() {
									var path_1 = "<a href='${cncaIndexPath}'>认证信息查询网</a>";
									var path_2 = "<a href='${cncaSearchPath}'>搜索认证信息</a>";
									$("#path_1").html(path_1);
									$("#path_2").html(path_2);
								})
							</script>
						</div>
					</div>

					<!--身份验证  -->
					<div data-name="searchBox" class="row">
						<div id="searchBox_big" class="col-md-6 col-md-offset-2"
							style="margin-top: 3%; padding: 30px; border: 1px solid #EEEEEE; border-radius: 7px; background-color: #FCFCFC; display: block;">

							<form id="auth_fo" class="form-horizontal">
								<div class="form-group">
									<label for="keyword" class="col-sm-3 control-label">获证组织名称</label>
									<div class="col-sm-7" style="padding-left: 5px;">
										<input type="text" class="form-control" id="keyword"
											name="keyword" placeholder="请输入不少于6个字符">
									</div>
								</div>
								<div class="form-group">
									<label for="randomCode" style="padding-top: 20px;"
										class="col-sm-3 control-label">验证码</label>
									<div class="col-sm-7"
										style="padding-left: 5px; padding-top: 14px; padding-right: 10px;">
										<input type="text" class="form-control" id="randomCode"
											name="randomCode" placeholder="图片验证码"
											style="display: inline; width: 110px;"> <img
											id="codeImage" alt="验证码识别中..." src="" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-7"
										style="padding-left: 120px; padding-top: 20px;">
										<button id="excute_btn" type="button" class="btn btn-success">&nbsp;搜&nbsp;索&nbsp;</button>
									</div>
								</div>
							</form>
						</div>
					</div>

					<!--搜索结果  -->
					<div data-name="cnca_searchResults" class="row"
						style="display: block; margin: 0 auto;">
						<div id="cnca_searchResults" data-searchKey="null"
							style="display: none;">
							<!-- 填充搜索结果 -->
						</div>
					</div>

					<!--搜索结果  -->
					<div data-name="cnca_searchCnca" class="row"
						style="display: block; margin-top: 30px;">
						<div id="cnca_searchCnca" class="col-md-11" data-searchKey="null"
							style="display: none;">
							<!-- 填充搜索结果 -->
						</div>
					</div>
				</div>
			</div>

			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>

	<jsp:include page="${footerPath}"></jsp:include>

	<%-- <script src="${ctx}/static/js/jquery.md5.js"></script> --%>
	<script src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/cnca/cnca-common.js"></script>

	<script type="text/javascript">
		var serializedFileNameStr = "";

		$(function() {
			ROOTPATH = "${ctx}";
			leftMenu();
			getSearchPage();
		});

		function getSearchPage() {
			$.ajax({
				async : false,
				type : "get",
				url : ROOTPATH + "/api/cncaApiController/getSearchPage",
				timeout : 90000,
				success : function(result) {
					result = JSON.parse(result);
					//serializedFileNameStr = JSON.stringify(result.data.serializedFileName);
					serializedFileNameStr = result.data.serializedFileName;
					//$("#verifycode").val(res.data.codeValue);
					$("#codeImage").attr("src", result.data.imageUrl);
				},
				error : function() {
					console.log("=====error=====");
					alert("获取查询页面失败，请重新获取！");
				}
			});
		}

		//点击执行按钮
		$("#excute_btn").on("click", function() {
			wd();
		});

		function wd() {
			var keyword = $("#keyword").val();
			var randomCode = $("#randomCode").val();
			$("#cnca_searchResults")
					.html(
							"<div class='sr-zql-loading'><img src='/data/static/imgs/icon/loading.gif'>loading...</div>");
			$("#cnca_searchResults").css("display", "block");
			var wd;
			$.ajax({
				async : false,
				type : "post",
				url : ROOTPATH + "/api/cncaApiController/searchCompanyList",
				data : {
					keyword : keyword,
					randomCode : randomCode,
					serializedFileName : serializedFileNameStr
				},
				timeout : 90000,
				success : function(result) {
					alert(result);
					result = JSON.parse(result);
					wd = cnca_displaySearchResults(result, keyword);
					alert(wd);
					$("#cnca_searchResults").html(wd);
					$("#searchBox_big").css("display", "none");
				},
				error : function() {
					console.log("=====error=====");
					wd = "获取查询结果失败，请重新获取！";
					$("#cnca_searchResults").html(wd);
					$("#searchBox_big").css("display", "none");
				}
			});
		}

		function searchResultCnca(orgName, orgCode, checkC, randomCheckCode) {
			alert("sdfdsfsdf");
			$("#cnca_searchCnca").css("display", "block");
			var wd;
			$.ajax({
				async : false,
				type : "get",
				url : ROOTPATH + "/api/cncaApiController/searchCncaList",
				data : {
					keyword : orgName,
					orgCode : orgCode,
					checkC : checkC,
					randomCode : randomCheckCode,
					serializedFileName : serializedFileNameStr
				},
				timeout : 90000,
				success : function(result) {
					result = JSON.parse(result);
					wd = cnca_displaySearchCnca(result);
					$("#cnca_searchCnca").html(wd);
					$("#searchBox_big").css("display", "none");
				},
				error : function() {
					console.log("=====error=====");
					wd = "获取查询结果失败，请重新获取！";
					$("#cnca_searchCnca").html(wd);
					$("#searchBox_big").css("display", "none");
				}
			});
		}
	</script>

</body>
</html>