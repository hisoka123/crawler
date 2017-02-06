$(function () {
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree a span i').click(function(){
    	$(this).toggleClass("addRegion");
    	$(this).parent().parent().toggleClass("addRegionColor");
    	$(this).toggleClass("addRegion").parent().parent().parent().find("i.glyphicon-ok").toggleClass("addRegion");
    	$(this).toggleClass("addRegion").parent().parent().parent().find("i.glyphicon-ok").toggleClass("addRegionColor");
    	$(this).toggleClass("addRegion").parent().parent().parent().find("span").toggleClass("addRegionColor");
    });
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').removeClass('glyphicon glyphicon-eye-close').addClass('glyphicon glyphicon-eye-open');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').removeClass('glyphicon glyphicon-eye-open').addClass('glyphicon glyphicon-eye-close');
        }
        e.stopPropagation();
    });
});

function addRegion(){
	var regionValArray=new Array();
	var regionTextArray=new Array();
	var i = 0;
	var j = 0;
	$(".tree a span i.addRegion").parent().parent().parent().each(function(){
		regionValArray[i] = $(this).val();
		i++;
	});
	$(".tree a span i.addRegion").parent().parent().prev().each(function(){
		regionTextArray[j] = $(this).val();
		j++;
	});
	var regionText = $(".tree a span i.addRegion").parent().parent().parent().text();
	$("#addAlarm div.modal-body div.form-group input#region").val(regionTextArray.join());
	$("#addAlarm div.modal-body div.form-group input#regionHidden").val(regionValArray.join());
	$('#addRegion').modal('hide');
}