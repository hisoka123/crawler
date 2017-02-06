<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/sina.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/static/css/cutImg.css" type="text/css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/baidu/baidu-ajax.js"></script>
    <script src="${ctx}/static/js/baidu/baidu-common.js"></script>


    <link rel='icon' href='${logo}' type='image/x-ico'>
    <title>${title}--LinkedIn</title>
</head>
<body>
<div class="scm-head"><%@ include file="../head.jsp" %></div>
<div class="sina-left"><%@ include file="leftMenu.jsp" %></div>

<div id="loading" style="margin-left:45%;">
    <br><br>
    <%@ include file="../loading.jsp"%>
</div>
<div id="backcolor" >

    <div id="content" style="display:none;width:60%;margin-left:25%">

        <!--显示基本信息  -->
        <div id="basicInfo" class="row">
            <!-- <div id="profile_img" class="col-md-2"></div>
                 <div id="basicInfo_other" class="col-md-8">
                      <h4 id="name"></h4>
                      <div id="headline"></div>
                      <div id="location_industry"></div>
                      <div id="cur_positions"></div>
                      <div id="pre_positions"></div>
                      <div id="bg_educations"></div>
                 </div>
               -->
        </div>


        <!-- 显示联系方式 -->
        <div id="contact" style="margin-top:2%">
            <div class="row" style="border-top:1px dashed grey">
                <div id="profile" class="col-md-10" style="background-color:#f6f6f6;height:35px;padding:7px;">
                </div>
                <div id="contactAction" class="col-md-2" style="background-color:#e9e9e9;height:35px;padding:7px;text-align:center;cursor:pointer">
                    <img src="${ctx}/static/img/baike.png" style="position:absolute;clip:rect(94px 16px  140px  0px);margin-top:-94px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基本信息↓
                </div>
            </div>
            <div id="basicInformation" style="background-color:#e9e9e9;display:none;" class="row">

            </div>
        </div>

        <br>

        <!-- 词条正文 -->
        <div id="articles" class="row" ></div>

    </div>
    <div id="exception" style="color:red;width:60%;margin-left:25%"></div>
</div>



<script type="text/javascript">
    var rootPath = '<%request.getContextPath();%>';
    var detailUrl='<%=request.getAttribute("url")%>';

    $(function(){

        var details=baidu_getCrawlerDetails(rootPath,detailUrl);
        //如果details返回null
        if(details.title == null){
            $("#exception").html("百度百科出现异常，请稍候再试！");
            $("#loading").hide();
            console.log("查询词条返回值为：NUll");
            return;
        }
        //如果details返回505
        //如果details返回error
        //如果details返回""

        $("#loading").hide();
        $("#content").show();

        //(1)显示词条概括
        $("#basicInfo").html(baidu_common_basicInfo(details));

        //(2)显示概要信息
        $("#profile").html('<img src="${ctx}/static/img/baike.png" style="position:absolute;clip:rect(250px 16px  290px  0px);margin-top:-245px">'
                +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                +'<a href="'+details.link+'" target="_blank">'
                +details.title
                +'</a>');
        $("#basicInformation").html(baidu_common_basicInformation(details));

        $("#contactAction").click(function(){
            if(details.baseInfos!=null){
                if($("#basicInformation").css("display")=="none"){
                    $("#basicInformation").show();
                }else{
                    $("#basicInformation").hide();
                }
            }
        })

        //(3)发表文章
        $("#articles").html(baidu_common_article(details));


    })




</script>
</body>
</html>