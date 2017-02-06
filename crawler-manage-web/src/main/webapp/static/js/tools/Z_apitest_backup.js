var index = 0; //API参数下标

//二级联动下拉列表初始化数据
var api_config = new Object();

//文档加载时调用
$(function(){
	//初始化数据
	ajaxTransObj("post", $("#ctx").val()+"/tools/wiki/initdata?time="+new Date(), null, null, apitestInitDataCallback, apitestInitDataErrorback, false);

	//初始化api_cate下拉列表
	var initApiCateOpts = "";
	for (var i=0; i<api_config.unit.length; i++) {
		initApiCateOpts += "<option value='"+i+"'>"+api_config.unit[i]+"</option>";
	}
	$("select[name='api_cate']").html(initApiCateOpts);
	//初始化api_name下拉列表
	var initApiNameOpts = "";
	for (var i=0; i<api_config.api[0].length; i++) {
		initApiNameOpts += "<option value='"+i+"'>"+api_config.api[0][i]+"</option>";
	}
	$("select[name='api_name']").html(initApiNameOpts);
	
	//填充默认参数
	var api_param_mark = "param_" + $("select[name='api_cate'] option:selected").val() + $("select[name='api_name'] option:selected").val();
	var api_param_names = api_config.param[api_param_mark];
	if (!api_param_names) {
		api_param_names = [];
	}
	
	var param_inputs = "";
	for (var i = 0; i < api_param_names.length; i++) {
		param_inputs +="<div>"
		param_inputs +=	"<label><input type='text' name='key_"+ index +"' class='param-key' placeholder='key' value='"+ api_param_names[i] +"'><span style='color:#AAAAAA;'>&nbsp;→</span> "
		param_inputs +=	"<input type='text' name='value_"+ index +"' class='param-value' placeholder='value'></label> " 
		param_inputs +=	"<i class='glyphicon glyphicon-remove' style='opacity:0.5;'></i>"
		param_inputs += "</div>";
		index++;
	}
	
	var $param_inputs_container = $(".param-container");
	$param_inputs_container.html(param_inputs);
	docNameChange("#doc_btn");
});
	
//回调函数
function apitestInitDataCallback(res){ //res---Map<String, List<WikiDocJson>>
	if (!res) {
		return;
	}
	var unit = new Array();
	var api = new Array();
	var path = new Array();
	var doc = new Array();
	var param = new Object();
	
	//遍历取出res的有效数据
	var i=0;
	for (var unitName in res) {
		var unitWikiDocList = res[unitName];
		unit[i] = unitWikiDocList[0].unitTitle;
		api[i] = new Array();
		path[i] = new Array();
		doc[i] = new Array();
		for (var j in unitWikiDocList) {
			api[i][j] = unitWikiDocList[j].docTitle;
			path[i][j] = unitWikiDocList[j].path;
			doc[i][j] = unitWikiDocList[j].unitName + "_" + unitWikiDocList[j].docName;
			if (unitWikiDocList[j].params) {
				param["param_"+i+""+j] = unitWikiDocList[j].params.split(",");
			} else {
				param["param_"+i+""+j] = [];
			}
		}
		i++;
	}
	api_config.unit = unit;
	api_config.api = api;
	api_config.path = path;
	api_config.doc = doc;
	api_config.param = param;
	console.log(JSON.stringify(api_config));
}
function apitestInitDataErrorback(XMLHttpRequest, textStatus, errorThrown) {
	alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus + " " + errorThrown);
}


//增加参数
$("#btn_addparam").on("click", function() {
	var param_container = $(".param-container");
	var appendContent = "<div>"
						+	"<label><input type='text' name='key_"+ index +"' class='param-key' placeholder='key'><span style='color:#AAAAAA;'>&nbsp;→</span> "
						+	"<input type='text' name='value_"+ index +"' class='param-value' placeholder='value'></label> " 
						+	"<i class='glyphicon glyphicon-remove' style='opacity:0.5;'></i>"
						+ "</div>";
	param_container.append(appendContent);
	index++;
});

//删除参数
$(".param-container").delegate("i", "click", function() { //被委派父元素选择器必须为“一个”确定的元素
	var target = $(this).parent();
	var key_input_name = $(this).parent().find("input[type='text'].param-key").attr("name");
	var target_index = key_input_name.charAt(key_input_name.length-1);
	target.remove();
	if (target_index>=index-1) {
		index--;
	} else {
		index = 0;
		$(".param-container div").each(function(){
			$(this).find("input[type=text].param-key").attr("name", "key_" + index);
			$(this).find("input[type=text].param-value").attr("name", "value_" + index);
			index++;
		});
	}
});


//API类别改变
$("select[name='api_cate']").on("change", function() {
	var api_name_mark = $(this).find("option:selected").val();
	var api_name_names = api_config.api[api_name_mark];
	
	var options = "";
	for (var i=0; i<api_name_names.length; i++) {
		options += "<option value=" + i + ">" + api_name_names[i] + "</option>"
	}
	
	var $options_container = $("select[name='api_name']");
	$options_container.html(options);
	
	apiNameChange("select[name='api_name']");
	docNameChange("#doc_btn");
});

//API名称改变
$("select[name='api_name']").on("change", function() {
	apiNameChange("select[name='api_name']");
	docNameChange("#doc_btn");
});

var apiNameChange = function(apiNameSelector) {
	index = 0; //API参数下标归零
	
	var api_param_mark = "param_" + $("select[name='api_cate'] option:selected").val() + $(apiNameSelector).find("option:selected").val();
	var api_param_names = api_config.param[api_param_mark];
	if (!api_param_names) {
		api_param_names = [];
	}
	
	var param_inputs = "";
	for (var i = 0; i < api_param_names.length; i++) {
		param_inputs +="<div>"
		param_inputs +=	"<label><input type='text' name='key_"+ index +"' class='param-key' plcaceholder='key' value='"+ api_param_names[i] +"'><span style='color:#AAAAAA;'>&nbsp;→</span> "
		param_inputs +=	"<input type='text' name='value_"+ index +"' class='param-value' placeholder='value'></label> " 
		param_inputs +=	"<i class='glyphicon glyphicon-remove' style='opacity:0.5;'></i>"
		param_inputs += "</div>";
		index++;
	}
	
	var $param_inputs_container = $(".param-container");
	$param_inputs_container.html(param_inputs);
}

//获取文档按钮的链接改变
var docNameChange = function(doc_link_selector) {
	var api_cate_mark = $("select[name='api_cate'] option:selected").val();
	var api_name_mark = $("select[name='api_name'] option:selected").val();
	var docName = api_config.doc[api_cate_mark][api_name_mark] + ".html";
	var href = $(doc_link_selector).attr("title") + "?docName=" + docName;
	$(doc_link_selector).attr("href", href);
}


//点击调用接口按钮后，显示请求参数
var showParams = function(param, selector) {
	var $req_parm_cont = $(selector);
	var req_parm_cont_text = "API分类代号：" + param.api_cate + "<br/>"
						   + "API名称代号：" + param.api_name + "<br/>"
						   + "请求方式：" + param.req_method + "<br/>";

	var paramStr = "";
	var paramSize = $("input[type='text'].param-key").size();
	for (var i = 0; i < paramSize; i++) {
		if (param['key_'+i]=="undefined" || param['key_'+i]==null || param['key_'+i]=="") continue;
		if (param['value_'+i]=="undefined" || param['value_'+i]==null || param['key_'+i]=="") continue;
		paramStr += param['key_'+i] + "=" + encodeURIComponent(param['value_'+i]) + "&"; //param['value_'+i]经过一次utf8编码//专用于拼接url参数
	}
	
	if (paramSize!=0) {
		paramStr = paramStr.substring(0, paramStr.length-1);
		req_parm_cont_text += "请求参数：" + paramStr + "<br/>";
	} 
	$req_parm_cont.html(req_parm_cont_text);
}


//返回
$(".sr-zql-topret-down").on("click", function(){
	//window.location.href = $(this).attr("id");
	history.go(-1);
});
