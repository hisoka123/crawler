
(function(){
    var unionSearchAbsPath = $("#unionSearchAbsPath").attr("path");

    searchBoxPlaceholder("fSearch");

    //选择搜索引擎
    $("#chooseSearchEngine").on("click","li",function(){
        var searchEngine = $("#searchEngine");
        var engine = $(this).attr("data-name");
        var engineCN = $(this).text();
        searchEngine.attr("data-name", engine);
        searchEngine.text(engineCN);

        $("#searchEngineBreadCrumb").text(engineCN);
        $("#searchEngineBreadCrumb").attr("href" , unionSearchAbsPath + engine);

    });

    $(function(){
        var searchEngine = $("#searchEngine").attr("data-name");
        if(searchEngine == "baidu" ||
            searchEngine == "sougou"||
            searchEngine == "haosou"||
            searchEngine == "bing"||
            searchEngine == "yahoo"){

            var engineLi = $("#chooseSearchEngine li[data-name='" + searchEngine + "']");

            var engine = engineLi.attr("data-name");
            var engineCN = engineLi.text();

            $("#searchEngineBreadCrumb").text(engineCN);
            $("#searchEngineBreadCrumb").attr("href" , unionSearchAbsPath + engine);
        }
    });


    //点击搜索
    $("#searchBox_big_btn").on("click",function(){
        var searchEngine = $("#searchEngine");
        var engine = searchEngine.attr("data-name");
        if(!engine || engine == "engine"){
            alert("请选择搜索引擎");
            return;
        }

        var searchContent = $.trim($("#searchBox_big_content").val());

        if("" == searchContent){
            alert("请输入搜索内容");
            return;
        }

        getSearchResult(engine,searchContent,1,false);

    });

    $(".pageNo").on("click",function(){
        var that = $(this);

        var searchContent = $.trim($("#searchBox_big_content").val());
        var engine = $("#searchEngine").attr("data-name");
        var pn = $.trim(that.text());
        var isDebug = false;

        getSearchResult(engine,searchContent,pn,isDebug);
    });

    function paging(){

    }


    function getSearchResult(engine,searchContent,pn,isDebug){
        //搜索引擎已经选好，且搜索内容不为空，则ajax搜索
        //得到查询结果后搜索框隐藏
        var start,end;
        $.ajax({

            url:$("#ctx").val() + "/api/se/" + engine + "/search",
            type:"get",
            data:{
                q:searchContent,
                pn:pn, //页码
                isDebug:isDebug
            },
            async:true,
            beforeSend:function(){
                $("#loading-mask").show();
                start = new Date();
            },
            success:function(data){
                //隐藏搜索栏
                $("#fSearch").hide("fast");

                $("#keywords").html(searchContent);

                //将json列表渲染到页面,
                var result = JSON.parse(data);//解析json
                if(result.error){
                    $("#f_searchResults").html(data);
                }else{
                    var arr = result.data;

                    var searchResult = "";
                    for(var i = 0 ; i < arr.length ; i++){
                        var link = arr[i].linkUrl || arr[i].urlLink || 'javascript:void(0)';
                        var title = arr[i].title || '';
                        var content = arr[i].content || '';
                        searchResult += '<div class="col-md-9">' +
                            '<div class="row">' +
                            '<h4 class="media-heading col-md-10" style="margin-top:1%">'+
                            '<a href="'+ link +'" target="_blank">'+ title +'</a>' +
                            '</h4>' +
                            '</div>' +
                            '<div class="row" style="margin-top:1%">' +
                            '<div class="col-md-10">' + content + '</div>' +
                            '</div>' +
                            '<hr class="col-md-10">' +
                            '</div>';
                    }

                    $("#f_searchResults").html(searchResult);
                }

                //显示搜索结果
                $("#f_searchResults_wrapper").show();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //报错
            },
            complete:function(){
                end = new Date();
                if(end - start < 500){
                    setTimeout(function(){
                        $("#loading-mask").hide();
                    },500);
                }else{
                    $("#loading-mask").hide();
                }

            }

        });
    }


})();