// 以post方式向详情页传递参数
function creditchinaAjax_windowOpenPostDetail(url, name, content) {
	var form = $("<form>");
	form.attr("style", "display:none");
	form.attr("method", "post");
	form.attr("target", "_blank");
	form.attr("action", url);
	var input = $("<input>");
	input.attr("style", "display:none");
	input.attr("name", name);
	input.attr("value", content);
	$("body").append(form);
	form.append(input);
	form.submit();
	form.remove();
}
// 以post方式传递调试内容
function creditchinaAjax_windowOpenPostInterface(url, data, dataContent, error,
		errorContent) {
	var form = $("<form>");
	form.attr("style", "display:none");
	form.attr("method", "post");
	form.attr("target", "_blank");
	form.attr("action", url);
	var input1 = $("<input>");
	input1.attr("style", "display:none");
	input1.attr("name", data);
	input1.attr("value", dataContent);
	var input2 = $("<input>");
	input2.attr("style", "display:none");
	input2.attr("name", error);
	input2.attr("value", errorContent);
	$("body").append(form);
	form.append(input1);
	form.append(input2);
	form.submit();
	form.remove();
}
// 从接口处获取企业详情
function creditchinaAjax_getDetailFromInterface(ctx, async, keyword,
		objectType, isDebug, callFunName) {
	var oneStepDetailResult = '';
	$.ajax({
		url : ctx + "/api/creditchina/getDataOnce",
		type : "get",
		data : {
			keyword : keyword,
			objectType : objectType,
			isDebug : 1
		},
		async : async,
		success : function(data) {
			if (!async) {
				oneStepDetailResult = data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (!async) {
				oneStepDetailResult = "XMLHttpRequest:" + XMLHttpRequest
						+ "\ntextStatus:" + textStatus + "\nerrorThrown:"
						+ errorThrown;
			} else {
				alert("与服务器断开,请稍候再试");
			}
		}
	})
	if (!async) {
		return oneStepDetailResult;
	}
}
// 检查企业是否存，若存在，从数据库获取企业详情;不存在，直接返回
function creditchinaAjax_getDetailFromDB(ctx, async, name, objectType) {
	var getDetailFromDBResult = '';
	$.ajax({
		url : ctx + "/modules/creditchina/checkchinaCompany",
		type : "post",
		async : async,
		data : {
			name : name,
			objectType : objectType,
			time : new Date()
		},
		success : function(data) {
			if (!async) {
				getDetailFromDBResult = data;
			}
		},
		error : function() {
			if (!async) {
				getDetailFromDBResult = "isError";
			}
		}
	})
	if (!async) {
		return getDetailFromDBResult;
	}
}