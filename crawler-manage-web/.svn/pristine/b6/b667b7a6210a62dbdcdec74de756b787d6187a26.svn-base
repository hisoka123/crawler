
//回调函数
var callback_default = function(container_selector, callback) {
	if (typeof(callback)=="undefined" || callback==null || callback=="") {
		return function(res) {
			var container = $(container_selector);
			container.html(res);
			//prettyPrint();
		}
	} else {
		return callback;
	}
}

var errorback_default = function(container_selector, errorback) {
	if (typeof(errorback)=="undefined" || errorback==null || errorback=="") {
		return function(XMLHttpRequest, textStatus, errorThrown) {
			var container = $(container_selector);
			var errorMsg = new Object();
			errorMsg.status = XMLHttpRequest.status;
			errorMsg.readyState = XMLHttpRequest.readyState;
			errorMsg.textStatus = textStatus;
			errorMsg.errorThrown = errorThrown;
			errorMsg = "<pre class='prettyprint sr-zql-pre-without-border' style='white-space:pre-wrap;'>Ajax请求错误：<br/>"+ JSON.stringify(errorMsg) +"</pre>"
			container.html(errorMsg);
			prettyPrint();
		}
	} else {
		return errorback;
	}
}

//ajax
//container_selector, callback, async 为可选参数
var ajaxProgress = function(type, url, data, container_selector, callback, errorback, async) {
	//type //默认get
	if (typeof(type)=="undefined" || type==null || type=="")
		type="get";
		
	//async //默认异步
	if (typeof(async)=="undefined" || async==null || async=="")
		async=true;
	
	//ajax
	$.ajax({
		async:async,
		type:type,//
		url:url,
		data:{"ajaxParam": encodeURI(encodeURI(JSON.stringify(data)))}, //编码两次，在后台也要解码两次
		dataType:"text",
		timeout:90000, //超时时间为90s
		success: callback_default(container_selector, callback), //后台可直接返回对象
		error: errorback_default(container_selector, errorback)
	});
}


//ajax
//传递对象参数
var ajaxTransObj = function(type, url, obj, container_selector, callback, errorback, async) {
	//type //默认get
	if (!type)
		type="get";
		
	//ajax
	$.ajax({
		async:async,
		type:type,//
		url:url,
		data:obj,
		timeout:90000, //超时时间为90s
		success: callback_default(container_selector, callback), //后台可直接返回对象
		error: errorback_default(container_selector, errorback)
	});
}



