
var delRules = [];

(function(){

    var listLen = $("#ipWhiteList tbody tr").length;
    if(listLen == 1){
        $("#addRule").trigger("click");
    }

    $("#addRule").click(function(){
        $("#ipWhiteList tbody").append($('#ipWhiteList tbody tr:first').prop("outerHTML"));
        $('#ipWhiteList tbody tr:last').css({"display":""});
        bindBlurCheck();
    });

    $("#resetBtn").click(function(){
        $("#ipWhiteListForm").reset();
    });

    $("#saveRule").click(function(){
        $("#mask").css({"display":"block"});
        $("#saveRule").attr('disabled',true);
        //校验
        var ipPermissions = [];

        var len = $("#ipWhiteList tbody tr").length - 1;
        for(var i = 1 ; i < len + 1 ; i++){
            var tr = $($("#ipWhiteList tbody tr")[i]);
            var ipPermission = {};

            //var ipProtocol = $.trim(tr.find("select[name='ipProtocol']").val());
            var ipProtocol = "tcp";
            var portRange = $.trim(tr.find("input[name='portRange']").val());
            var ipRange   = $.trim(tr.find("input[name='ipRange']").val());

            if('' != portRange &&  '' != ipRange){
                ipPermission.ipProtocol = ipProtocol;
                ipPermission.portRange = portRange;
                ipPermission.ipRange = ipRange;
            }else{

                if('' == portRange){
                    tr.find("input[name='portRange']").addClass("has-error");
                }

                if('' == ipRange){
                    tr.find("input[name='ipRange']").addClass("has-error");
                }

                $("html,body").animate({scrollTop:tr.offset().top - 55},500);

                $("#mask").css({"display":"none"});
                $("#saveRule").removeAttr('disabled');
                return false;
            }



            ipPermissions.push(ipPermission);
        }

        var permission = {};
        permission.saveRules = ipPermissions;
        permission.delRules = delRules;

        var jsonData = JSON.stringify(permission);

        //提交
        $.ajax({
            type:'POST',
            url:ctx + '/admin/aws/save',
            data:jsonData,
            contentType: 'application/json',
            beforeSend: function () {

            },
            success: function (data) {
                if(data == "success"){
                    alert("保存成功！");
                    top.location.reload();
                }else if("error" == data){
                    alert("保存失败！");
                    top.location.reload();
                }else{
                    alert("错误！");
                }
            },
            error: function () {
                alert("错误");
            },
            complete:function(){
                $("#mask").css({"display":"none"});
                $("#saveRule").removeAttr('disabled');
            }
        });

    });

    function bindBlur(name){

        $("input[name='"+ name +"']").unbind("blur");
        $("input[name='"+ name +"']").bind("blur",function(){
            if('' != $.trim($(this).val())){
                if($(this).hasClass("has-error")){
                    $(this).removeClass("has-error");
                }
            }else{
                if(!$(this).hasClass("has-error")){
                    $(this).addClass("has-error");
                }
            }
        });


    }

    function bindBlurCheck(){
        bindBlur("portRange");
        bindBlur("ipRange");
    }



    bindBlurCheck();

})();

function deleteRule(target){
    var tr = $(target).parent("td").parent("tr");

    if(!$(target).hasClass("direct-remove")){
        var delRule = {};

        delRule.ipProtocol = tr.find("input[name='ipProtocolHid']").val();
        delRule.portRange = tr.find("input[name='portRangeHid']").val();
        delRule.ipRange = tr.find("input[name='ipRangeHid']").val();
        delRule.ipProtocol = "tcp";

        delRules.push(delRule);
    }

    tr.remove();
}

function changePortRange(target){
    var tr = $(target).parent("td").parent("tr");

    var portRangeElem = tr.find("input[name='portRange']");
    var protocol = $(target).val();
    if("tcp" == protocol){
        portRangeElem.prop("readonly",false);
        portRangeElem.val("");
    }else if("http" == protocol){
        portRangeElem.prop("readonly",true);
        portRangeElem.val(80);
        portRangeElem.removeClass("has-error");
    }else if("https" == protocol){
        portRangeElem.prop("readonly",true);
        portRangeElem.val(443);
        portRangeElem.removeClass("has-error");
    }

}