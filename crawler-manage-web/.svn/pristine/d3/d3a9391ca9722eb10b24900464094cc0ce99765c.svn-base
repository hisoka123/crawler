function baidu_getCrawler(rootPath,crawlerContent){

      var baidu_getCrawler_result='';

      $.ajax({
              url:rootPath+"/baidu/getListCrawlerResult",
              data:encodeURI(encodeURI("crawlerContent="+crawlerContent)),
              async:false,
              success:function(result1_list){
                  baidu_getCrawler_result=result1_list;
              },
              error:function(){
                  baidu_getCrawler_result="error";
              }		  
      });	
      return baidu_getCrawler_result;
}


function baidu_getCrawlerDetails(rootPath,detailUrl){
    var baidu_getDetailCrawler_result = "";

    $.ajax({
        url:rootPath+"/baidu/getDetailCrawlerResult",
        data:encodeURI(encodeURI("url="+detailUrl)),
        async:false,
        success:function(result1_list){
            baidu_getDetailCrawler_result=result1_list;
        },
        error:function(){
            baidu_getDetailCrawler_result="error";
        }
    });
    return baidu_getDetailCrawler_result;

}