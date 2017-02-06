$(function(){
	
	  var logParam='',site='',interfaceName='',timeStamp='',logUid='';
	  var logParamArray='';
	  
	  
	  
	  $("title").html("接口日志");
	  logParam=decodeURI(decodeURI($("#logParam").val()));
	  logParamArray=logParam.split(",");
	  
	  site=logParamArray[0];
	  interfaceName=logParamArray[1];
	  timeStamp=logParamArray[2];
	  logUid=logParamArray[3];
	  
	  $("#callInterfaceName").text(site+" -- "+interfaceName);
	  $("#callTime").text(commonSelf_timeStampConvertToTime(timeStamp));
	  
	 
	  log_log(logUid);
	  
	    
})
//log服务
function log_log(logUid){
	  
	  var logURL='';
	  var sock='';
	  
	  
	  logURL=window.location.host+$("#ctx").val();
	  
	  /*try{
		   sock=new WebSocket("ws://"+logURL+"/logWebSocket");
		   console.log("ws://"+logURL+"/logWebSocket")
	  }catch(error){*/
		   sock=new SockJS("http://"+logURL+"/sockjs/logWebSocket");
		   console.log("http://"+logURL+"/sockjs/logWebSocket")
	  //}

	  sock.onopen=function(){
		   //console.log("onopen");
	  }
	  sock.onmessage=function(event){
		   if(event.data=="logWebSocketSuccess"){
			     $("#consoleLog").append("<br>******************** 准备输出日志 ********************");
			     sock.send(logUid);
		   }else{
			     $("#consoleLog").append("<pre style='background-color:white;border:0px'>"+event.data.replace(/</g,"&lt;").replace(/>/g,"&gt;")+"</pre>");
			   
			     // console.log(document.body.scrollHeight+" -- "+window.screen.availHeight)
			     var offset=parseInt(document.body.scrollHeight)-parseInt(window.screen.availHeight);
			     $("body").scrollTop(offset+100);
		   }
		   
	  }
	  sock.onclose=function(){
		   $("#consoleLog").append("<br>******************** 日志输出完毕 ********************");
	  }
	 
	 
	
	
}