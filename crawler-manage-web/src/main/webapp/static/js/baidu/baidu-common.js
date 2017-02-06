//爬取词条
function baidu_common_articleDisplay(rootPath,keyword,crawlerResult){
	  var articleDisplay='';
	  var article='';
	  
	  if(crawlerResult==''){
		   
		    article='<b>抱歉!</b>&nbsp;&nbsp;暂无与<strong style="color:red">'+searchname+'</strong>相关的词条。';
		    return article;
	   }
	  
	  $.each(crawlerResult,function(index,content){
		      var replace='<strong style="color:red">'+keyword+'</strong>';
		      articleDisplay ='<div class="panel panel-default">'
		    	                     +'<div class="panel-body">'
		    	                            +'<div style="font-size:17px">'
		    	                                   +'<span><a href="'+this.link+'" target="_blank">'+this.title.replace(new RegExp(keyword,"gm"),replace)+'</a></span>'
                                                   +'<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
                                                   +'<span><button type="button" class="btn btn-primary" onclick="details(\''+this.link+'\')" style="width:10%;height:20%;text-align:center"><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;详情</button></span>'
		    	                            +'</div>'
		    	                            +'<div class="row">'
		    	                                   +'<div  style="font-size:85%;padding-left:15px;padding-right:15px;">'
		    	                                          +'<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+this.introduction.replace(new RegExp(keyword,'gm'),replace)+'</div>'
		    	                                          +'<div style="color:grey;margin-top:1%">最后更新时间：&nbsp;&nbsp;'+this.lastUpdateDate+'</div>'
		    	                                   +'</div>'
		    	                            +'</div>'
		    	                     +'</div>'
		    	               +'</div>';

          article +='<div style="width:100%;">'
              +'<div style="width:80%;float:left">'+articleDisplay+'</div>'
              +'<div id="articleQRCode_'+index+'" style="width:19%;float:right;margin-top:-5%;display:none">'+'</div>'
              +'</div>';

	  })
	  
	  return article;
}


//图片替换
function baidu_common_imgNoFind(rootPath){
	var img=event.srcElement;
    img.src=rootPath+"/static/img/baike.ico";
    img.onerror=null;
}



/////////////////////////////////////////////////
//detail page
//get summary and return.
function baidu_common_basicInfo(details){
    var returnHtml = "";

    var title = details.title;
    var subTitle = details.subTitle;
    var summary = details.introduction;
    var picLink = details.picLink;

    if(picLink == null){
        picLink = rootPath+"/static/img/baike.ico";
    }

    returnHtml = '<div class="col-md-3" style="text-align:center;"><img src=\"'+picLink +'\" style="width:195px;height:195px;margin-top:10%"></div>'
                +'<div class="col-md-8">'
                        +'<h2><em>'+title+'<em>'+subTitle+'</h2>'
                        +'<div style="font-size:13px;margin-top:1%"><span style="color:grey">概要 &nbsp;&nbsp;</span>'+summary+'</div></div>'

    return returnHtml;
}


//get basic_information and return.
function baidu_common_basicInformation(details){
    var returnHtml = "";

    var basicInfo = details.baseInfos;
    if(basicInfo!=null && basicInfo!='undefined'){
    for(var i=0;i<basicInfo.length;i++){
        var title = basicInfo[i].title;
        var value = basicInfo[i].biContent;

        returnHtml += '<div style="font-size:13px;margin-top:1%"><span style="color:grey">'+title +'&nbsp;&nbsp;</span>'+value+'</div>';
    }
    }
    return returnHtml;
}

function baidu_common_article(details) {
    var returnHtml = "";
    var catalogInfos = details.catalogInfos;
    if (catalogInfos != null) {
        for (var i = 0; i < catalogInfos.length; i++) {
            var level = catalogInfos[i].catalogLevel;
            var titleHtml = "";
            var contentHtml = "";

            titleHtml +='<h'+level+'> <span>' + catalogInfos[i].title + '</span></h'+level+'>';

            if (catalogInfos[i].content != null) {
                contentHtml += '<div>' + catalogInfos[i].content + '</div>'
            }
            returnHtml += titleHtml + contentHtml;
        }
    }
    return returnHtml;
}

