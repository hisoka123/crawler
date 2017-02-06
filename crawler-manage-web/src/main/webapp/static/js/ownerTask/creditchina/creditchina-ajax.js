//查询信用中国任务，条件查询
function searchCreditChinaTask(ctx, async, loginName, searchType, status,
		keyWord, pageSize, currentPage, callFunName) {

	var ownerTaskCreditchinaPage = '';

	$.ajax({

		url : ctx + "/ownerTask/searchCreditchinaTask",
		type : "post",
		async : async,
		data : {
			pageSize : pageSize,
			currentPage : currentPage,
			loginName : loginName,
			searchType : searchType,
			status : status,
			keyWord : keyWord
		},
		success : function(data) {
			if (!async) {
				ownerTaskCreditchinaPage = data;
			}

		},
		error : function() {
			ownerTaskCreditchinaPage = "isError";
		}

	});

	if (!async) {
		return ownerTaskCreditchinaPage;
	}

}
// 加入新任务---信用中国creditchinaOTAjax_creditchinaJoinTask(ctx,false,area,keyword,objectType,loginName,existCode)
function creditchinaOTAjax_creditchinaJoinTask(ctx, async, name, objectType,
		loginName, existCode, companyID, callFunName) {
	var creditchinaResult = '';
	$.ajax({
		url : ctx + "/ownerTask/creditchinaJoinTask",
		type : "post",
		async : async,
		data : {
			name : name,
			objectType : objectType,
			loginName : loginName,
			existCode : existCode,
			companyID : companyID,
			date : new Date()
		},
		success : function(data) {
			if (!async) {
				creditchinaResult = data;
			}
		},
		error : function() {
			if (!async) {
				creditchinaResult = "isError";
			}
		}
	})
	if (!async) {
		return creditchinaResult;
	}
}