$(function(){
	//工具提示
	$('.glyphicon.glyphicon-edit').attr({"data-toggle":"tooltip", "data-placement":"right", "title":"click to edit"});
	$('.glyphicon.glyphicon-edit').css("cursor", "pointer");
	$('[data-toggle="tooltip"]').tooltip(); 
	
	//编辑与不可编辑
	$('.glyphicon.glyphicon-edit').on("click", function(){
		$('.sr-zql-content-right-box').attr("contenteditable", "true");
		$('.sr-zql-content-right-box p:first').focus();
	});
	$('.sr-zql-content-right-box').on("blur", function(){
		$('.sr-zql-content-right-box').attr("contenteditable", "false");
	});
	
	//表格操作
	$("table").delegate("tr td a", "click", function(){
		$(this).parent().parent().remove();
	});
	$("table").on("mouseover", function(){
		var width = $(this).css("width");
		width = parseInt(width)/2 - 8;
		$(this).next("i.glyphicon.glyphicon-triangle-bottom").css({"margin-left":width, "display":"inline", "cursor":"pointer"});
	});
	$("i.glyphicon.glyphicon-triangle-bottom").on("mouseleave", function(){
		$("i.glyphicon.glyphicon-triangle-bottom").css({"display":"none"});
	});
	$("i.glyphicon.glyphicon-triangle-bottom").on("click", function(){
		var cols = $(this).prev("table").find("thead tr th").size();
		var newTr = "<tr>";
		for (var i=0; i<cols-1; i++) {
			newTr+="<td></td>";
		}
		newTr+="<td><a style='cursor:pointer'>删除</a></td></tr>";
		$(this).prev("table").children("tbody").append(newTr);
	});
	
	//点击提交按钮
	$("#subdoc").on("click", function(){
		  
		if (!validateBeforeSubDoc()) {
			return;
		}
		var isConfirm = confirm("您确定要增加该接口说明文档？");
		if (isConfirm) {
			var wikiDoc = new Object();
			wikiDoc.unitName = $("#unitName").val().trim();
			wikiDoc.unitTitle = $("#unitTitle").val().trim();
			wikiDoc.unitTitleIcon = $("#unitTitleIcon").val().trim();
			wikiDoc.docName = $("#docName").val().trim();
			wikiDoc.docTitle = $("#docTitle").val().trim();
			wikiDoc.docTitleIcon = $("#docTitleIcon").val().trim();
			//wikiDoc.docContent = htmlFilter($("div.sr-zql-content-right-box").html());
			$("pre").addClass("prettyprint  prettyprinted");
			var rte_content=$("#rte").summernote('code');   //接口文档内容
			
			
			var params = "";
			$("#paramsTable tbody tr").each(function(i){
				if (i==0) {
					params+=$(this).children("td")[0].innerHTML;
				} else {
					params+=","+$(this).children("td")[0].innerHTML;
				}
			});
			params = params.replace(/<\/br>/g, "");
			params = params.trim();
			wikiDoc.params = params;
			
			var path = $("#path span:eq(1)").text();
			if (path.indexOf("?")!=-1) {
				path = path.substr(0, path.indexOf("?"));
			}
			path = path.trim();
			wikiDoc.path = path;
			
			ajaxTransObj("post", $("#ctx").val()+"/tools/wiki/addaction?time="+new Date(), wikiDoc, null, callback, errorback, false); //同步
			window.location.reload();
		}
	});
	
	//加载富文本编辑器RTE
	 $("#rte").summernote({
		     lang:'zh-CN',
		     minHeight:250,
	         maxHeight:450,
	         /*toolbar:[
	           ['insert',['link','table','hr']] 
	         ],*/
	 });
	
});
	  	
//函数
//将元素置于可编辑状态
function contenteditable(obj) {
	$(obj).attr("contenteditable", "true");
}
//净化html标签
function htmlFilter(text) {
	text = text.replace(/onclick=[\s\S]*?contenteditable[\s\S]{6};[\s\S]{1}/g, "");
	text = text.replace(/contenteditable=[\s\S]{1}true[\s\S]{1}/g, "");
	text = text.replace(/contenteditable=[\s\S]{1}false[\s\S]{1}/g, "");
	text = text.replace(/<th>操作<\/th>/g, "");
	text = text.replace(/<td><a style=[\s\S]*?cursor:pointer[\s\S]*?>删除<\/a><\/td>/g, "");
	text = text.replace(/<i class=[\s\S]*?glyphicon glyphicon-triangle-bottom[\s\S]*? style=[\s\S]*?display:.*><\/i>/g, "");
	var preContentHtml = $(".prettyprint.sr-zql-pre-without-border").html();
	preContentHtml = preContentHtml.replace(/>/g, "&gt;");
	preContentHtml = preContentHtml.replace(/</g, "&lt;");
	preContentHtml = "<pre class='prettyprint sr-zql-pre-without-border'>" + preContentHtml + "</pre>";
	text = text.replace(/<pre class=[\s\S]*?prettyprint sr-zql-pre-without-border[\s\S]*? .*<\/pre>/g, preContentHtml);
	text = "<h2>"+$("#docTitle").val()+"</h2><hr/>" + text;
	return text;
}
//回调函数
function callback(res) {
	if (res=="OK") {
		alert("操作成功！");
	} else {
		alert("操作失败！");
	}
}
function errorback(XMLHttpRequest, textStatus, errorThrown) {
	alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus + " " + errorThrown);
}
//onkeyup触发函数
function clearDataWithUnderline(obj) {
	if (obj.value.indexOf("_")!=-1) {
		alert("您输入的值不能包含下划线！");
		obj.value='';
		return;
	}
}
//提交验证函数
function validateBeforeSubDoc() {
	var unitName = $("#unitName").val();
	var unitTtile = $("#unitTitle").val();
	var docName = $("#docName").val();
	var docTitle = $("#docTitle").val();
	var rte_content=$("#rte").summernote('code');
	
	if (!unitName) {
		alert("单元名称不能为空！");
		return false;
	}
	if (!unitTtile) {
		alert("单元标题不能为空！");
		return false;
	}
	if (!docName) {
		alert("接口文档名称不能为空！");
		return false;
	}
	if (!docTitle) {
		alert("接口文档标题不能为空！");
		return false;
	}
	
	if(!rte_content){
		 alert("接口文档内容不能为空！");
		 return false;
	}
	return true;
}