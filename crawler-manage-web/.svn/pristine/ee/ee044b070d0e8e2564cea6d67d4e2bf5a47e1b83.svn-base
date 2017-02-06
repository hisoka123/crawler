$(function() {
	searchBoxPlaceholder("creditchina");
	creditchinaCommon_searchBoxStyle();
	// 搜索框聚焦
	$("#searchBox_big_content").focus(function() {
		$("#info >div").text("");
		$("#info").hide();
	})
	// 判断环境
	var env = $("#env").val().trim();
	if (env == "prod") {
		$("#chooseDataSource").remove();
		$("#searchBox_big_btn").on("click", function() {
			creditchinaCommon_detailFromDB();
		})
	} else {
		$("#chooseDataSource").show();
		creditchinaCommon_dataSource();
	}
})
// 测试环境
function creditchinaCommon_dataSource() {
	$("#searchBox_big_btn").on("click", function() {
		var value = '';
		value = $("input[name='chooseDataSource']:checked").attr("value");
		if (value == "interfaceVersion") {
			creditchinaCommon_detailFromInterface();
		} else if (value == "dbVersion") {
			creditchinaCommon_detailFromDB();
		}
	})
}
// 从接口处获取企业详情
function creditchinaCommon_detailFromInterface() {
	var keyword = '', objectType = '2', searchResult = '', ctx = '';
	ctx = $("#ctx").val();
	keyword = $("#searchBox_big_content").val();
	objectType = $("#objectType").attr("data-name");
	if (keyword == '') {
		alert("输入的企业名称错误！");
		return false;
	} else {
		$("#info >div").text("努力查询，请稍候...");
		$("#info >div").attr("style", "color:red");
		$("#info").show();
		setTimeout(function() {
			searchResult = creditchinaAjax_getDetailFromInterface(ctx, false,
					keyword, objectType);
			creditchinaCommon_detailFromIN(ctx, searchResult);
		}, 1000);
	}
}
// 从接口处异步显示结果
function creditchinaCommon_detailFromIN(ctx, searchResult) {
	var detailName = '';
	detailName = "creditchinaDetail";
	$("#info").hide();
	if (JSON.parse(searchResult).data != null) {
		creditchinaAjax_windowOpenPostDetail(ctx
				+ "/modules/creditchina/creditchinaDetail", detailName, JSON
				.stringify(JSON.parse(searchResult).data));
		// 调试时用
		setTimeout(
				function() {
					creditchinaAjax_windowOpenPostInterface(ctx
							+ "/modules/creditchina/creditchinaJson", "data",
							JSON.stringify(JSON.parse(searchResult).data),
							"error", "eNull");
				}, 2000);
	} else {
		if (JSON.parse(searchResult).error.errorCode == 7) {// 无关键词
			alert(JSON.parse(searchResult).error.errorName);
		} else {
			if (searchResult.indexOf("errorName") != -1
					&& /[\u4e00-\u9fa5]/.test(searchResult)) {
				alert("请求无效，可能原因：  " + JSON.parse(searchResult).error.errorName);
			} else {
				alert("服务器繁忙，请重试！");
			}
		}
		// 调试时用
		setTimeout(function() {
			creditchinaAjax_windowOpenPostInterface(ctx
					+ "/modules/creditchina/creditchinaJson", "data", "dNull",
					"error", JSON.stringify(JSON.parse(searchResult).error));
		}, 2000);
	}
}
// 从数据库中获取企业详情
function creditchinaCommon_detailFromDB() {
	var keyword = '', objectType = '2', searchResult = '', detailName = '', ctx = '', checkResult = '';
	var ownerTaskResult = '', loginName = '';
	ctx = $("#ctx").val();
	keyword = $("#searchBox_big_content").val();
	objectType = $("#objectType").attr("data-name");
	detailName = "creditchinaDetail";
	loginName = $("#fat-menu").find("span[id='loginName']").text();
	if (keyword == '') {
		alert("输入的企业或者个人信息错误！");
		return false;
	} else {
		checkResult = creditchinaAjax_getDetailFromDB(ctx, false, keyword,
				objectType);
		if (checkResult.existCode == 0) { // 定时任务中企业存在状态码：0（否）1（在）
			creditchinaCommon_confirmJoinModule(ctx, keyword, objectType,
					loginName, 0, 0);
		} else {
			if (checkResult.state == 7) { // existcode=1 && state=7
				$("#info >div").text("没检索到关键字");
				$("#info >div").attr("style", "color:green");
				$("#info").show();
			} else if (checkResult.state == 1) { // 爬取企业状态码：0（排队等候）1（成功）7（无关键词）
				creditchinaAjax_windowOpenPostDetail(ctx
						+ "/modules/creditchina/creditchinaDetail", detailName,
						JSON.stringify(checkResult.companyRecordResult));
			} else { // existcode=1 && state=0
				creditchinaCommon_confirmJoinModule(ctx, keyword, objectType,
						loginName, checkResult.existCode,
						checkResult.company_id);
			}
		}
	}
}
// 创建新任务
function creditchinaCommon_confirmJoinModule(ctx, keyword, objectType,
		loginName, existCode, companyID) {
	var ownerTaskResult = '';
	ownerTaskResult = creditchinaOTAjax_creditchinaJoinTask(ctx, false,
			keyword, objectType, loginName, existCode, companyID);
	var wd = "";
	if (objectType == "1") {
		wd = "主体类型（自然人）";
	} else if (objectType == "2") {
		wd = "主体类型（法人）";
	}
	common_createNewTask("info", ownerTaskResult, wd, keyword, "信用中国");
}
// 搜索框样式
function creditchinaCommon_searchBoxStyle() {
	// 修正搜索框
	$("#searchBox_big >div[class='input-group'] >#searchBox_big_content")
			.before(
					"<div class='input-group-btn'>"
							+ "<button type='button' class='btn btn-default dropdown-toggle active' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='width:160px;height:40px'>"
							+ "主体类型（<span id='objectType' data-name='2'>法人</span>）"
							+ "<span class='caret'></span>"
							+ "</button>"
							+ "<ul id='chooseObjectType' class='dropdown-menu' style='width:130px;font-size:14px;padding-left:5px;padding-right:5px'>"
							+ "</ul>" + "</div>");
	objectTypes = "<li data-name='2' style='margin-top:10px;margin-bottom:10px;'><span data-name='2' style='margin-left:20px;padding:5px 15px;cursor:pointer'>法人</span></li><li data-name='1' style='margin-top:10px;margin-bottom:10px;'><span data-name='1' style='margin-left:20px;padding:5px 15px;cursor:pointer'>自然人</span></li>";
	$("#chooseObjectType").html(objectTypes);
	$("#chooseObjectType")
			.on(
					"mouseenter",
					"span[data-name]",
					function() {
						$(this)
								.attr(
										"style",
										$(this).attr("style")
												+ ";background:#f1f1f1;border-radius:20px;-moz-border-radius:20px;-webkit-border-radius:20px");
					})
	$("#chooseObjectType").on(
			"mouseleave",
			"span[data-name]",
			function() {
				$(this).attr("style",
						"margin-left:20px;padding:5px 15px;cursor:pointer");
			})
	$("#chooseObjectType").on("click", "span[data-name]", function() {
		$("#objectType").text($(this).text());
		$("#objectType").attr("data-name", $(this).attr("data-name"));
	})
}
// 主体类型数组json
function creditchinaCommon_objectTypeArray() {
	var objectTypeArray = {
		"2" : "法人",
		"1" : "自然人",
	}
	return objectTypeArray;
}