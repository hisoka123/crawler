$(function() {
	// 显示目录
	$("#toc").on("click", function() {
		$("#leftMenu").show();
		$("#toc").hide();
	})
	// 隐藏目录
	$("#table").on("click", function() {
		if ($("#leftMenu").css("display") != "none") {
			$("#leftMenu").hide();
			$("#toc").show();
		}
	})
	// td内容为空
	creditchinaCommon_tdIsNull();
	// 各种信息详情
	creditchinaCommon_infoDetail();
})
// td内容为空
function creditchinaCommon_tdIsNull() {
	var td = '';
	for (var i = 0; i < $("body table").find("td").length; i++) {
		td = $("body table").find("td").eq(i);
		if (!/[a-zA-Z0-9\u4e00-\u9fa5\-]/.test(td.html())) {
			td.text("---");
			td.attr("align", "center")
		}
	}
}
// 各种信息详情
function creditchinaCommon_infoDetail() {
	var infoDetailArray = [ "badInfo_", "dishonestyInfo_" ];
	for (var i = 0; i < infoDetailArray.length; i++) {
		$("span[id^='" + infoDetailArray[i] + "']").on("click", function() {
			var id = '', id_detail = '', status = '';
			id = $(this).attr("id");
			id_detail = id + "_detail";
			status = $("#" + id_detail).css("display");
			if (status == "none") {
				$("#" + id_detail).show();
				if ($(this).text() == "展开") {
					$(this).text("收起");
				}
			} else {
				$("#" + id_detail).hide();
				if ($(this).text() == "收起") {
					$(this).text("展开");
				}
			}
		})
	}
}