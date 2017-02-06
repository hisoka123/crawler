/************  时间戳  时间    日期 ********************/

//将英文月份缩写转换为数字月份
function commonSelft_EnglishMonthFormat(EnglishMonth){
         var month='';   
	
		 if(EnglishMonth=="Jan"){
		       month="01";
	     }else if(EnglishMonth=="Feb"){
		      month="02";
	     }else if(EnglishMonth=="Mar"){
		      month="03";
	     }else if(EnglishMonth=="Apr"){
		      month="04";
	     }else if(EnglishMonth=="May"){
		      month="05";
	     }else if(EnglishMonth=="Jun"){
		      month="06";
	     }else if(EnglishMonth=="Jul"){
		      month="07";
	     }else if(EnglishMonth=="Aug"){
		      month="08";
	     }else if(EnglishMonth=="Sep"){
		      month="09";
	     }else if(EnglishMonth=="Oct"){
		      month="10";
	     }else if(EnglishMonth=="Nov"){
		     month="11";
	     }else if(EnglishMonth=="Dec"){
		     month="12";
	     }

         return month;
}
//验证时间戳格式
function commonSelft_checkTimeStamp(timeStamp){
         
         var checkResult='';

		 if(timeStamp==null || timeStamp==''){
				checkResult="noPass";
		 }else{

		       if(/^[1-9]\d*$/.test(timeStamp)){
			         checkResult="pass";
			   }else{
			         checkResult="noPass";
			   }
		 	 
		 }

         return checkResult;
}
//日期格式化 将时间戳转变为日期  2000-01-01
function commonSelf_timeStampConvertToDate(timeStamp){
	var month='',date='';
	
	if(commonSelft_checkTimeStamp(timeStamp.toString())=="noPass"){
	        return "N/A";
	}
	
    date=new Date(parseInt(timeStamp)).toString().split(" ");
	month=commonSelft_EnglishMonthFormat(date[1]);
	
	return date[3]+"-"+month+"-"+date[2];	
}

//时间格式化  将时间戳转变为日期时间  2000-01-01 00:00:00
function commonSelf_timeStampConvertToTime(timeStamp){
	var month='',date='';
	
	if(commonSelft_checkTimeStamp(timeStamp.toString())=="noPass"){
	        return "N/A";
	}
	
	date=new Date(parseInt(timeStamp)).toString().split(" ");
	month=commonSelft_EnglishMonthFormat(date[1]);
	
	return date[3]+"-"+month+"-"+date[2]+" "+date[4];	
}

/**
 * bootstrap分页样式 
 * 
 * onePageShowNum    每页显示记录的条数
 * showPageNum       一次显示的页数
 * objectNum        全部记录数
 * totalPageNum     总页数
 * pageID           分页div的id
 * callBack         回调函数
 * 
 * objectNum、totalPageNum其中一个有值即可，另一个赋值为'isNull'
 * 
 * 使用步骤：
 *(1)第一次发送ajax请求
 *
 *(2)调用显示第一页内容(初始化)
 *(3)调用commonSelef_pageStyle
 *(4)回调函数｛
 *
 *   //获取当前页
 *
 *   //发送第（当前页）的ajax请求,获取当前页内容
 *
 *    //调用当前页的内容
 *   ｝
 * (5)显示内容函数
 * 
 */
function commonSelf_pageStyle(onePageShowNum,showPageNum,objectNum,totalPageNum,pageID,callBack){
	
	     var page='',mod='',lis='',options='';
	     
	     
	     if(!/^\d*/.test(onePageShowNum) || !/^\d*/.test(showPageNum)){
	    	       console.error("commonSelf_pageStyle参数赋值错误");
	    	       return;
	     }
	     
	     if(objectNum!="isNull" || totalPageNum!="isNull"){
	    	      if(!/^\d*/.test(objectNum)||!/^\d*/.test(totalPageNum)){
	    	    	   console.error("commonSelf_pageStyle参数赋值错误");
		    	       return;
	    	    	  
	    	      }
	     }else{
	    	   console.error("commonSelf_pageStyle参数赋值错误");
  	           return;   
	     }
	     
	     if(totalPageNum=="isNull"){
	    	 
	    	    mod=parseInt(objectNum)%parseInt(onePageShowNum);
    	     
		        if(mod==0){
		    	    totalPageNum=parseInt(objectNum)/parseInt(onePageShowNum);
		        }else{
		    	    totalPageNum=parseInt(parseInt(objectNum)/parseInt(onePageShowNum))+1;
		        }
	    	 
	     }
	    
	     $("#"+pageID).html(""); //放置新分页前，清空旧分页
	     if(totalPageNum<2){  //只有一页，不显示分页，返回
	    	   return;
	     }
	    	     
	     if(totalPageNum<=showPageNum){
	             showPageNum=totalPageNum;	   
	      }
	     
	      for(var i=0;i<showPageNum;i++){
	    	  
	    	      if(i==0){
	    	    	  lis +="<li id='page_"+(i+1)+"' data-first='init' style='cursor:pointer'><a>"+(i+1)+"</a></li>";
	    	      }else{
	    	    	  lis +="<li id='page_"+(i+1)+"' style='cursor:pointer'><a>"+(i+1)+"</a></li>";
	    	      }
	    	      
	      }
	     
	     
	     for(var i=0;i<totalPageNum;i++){
	    	    options +="<option value='page_"+(i+1)+"'>"+(i+1)+"</option>";
	     }
	     
	     
	     page="<nav>"
                   +"<ul data-name='showPage_1' class='pagination'>"
                        +"<li id='firstPage' style='cursor:pointer'><a>首页</a></li>"
                        +"<li id='previousPage' style='cursor:pointer'><a>&laquo;上一页</a></li>"
                        +lis
                        +"<li id='nextPage' style='cursor:pointer'><a>下一页&raquo;</a></li>"
                        +"<li id='lastPage' style='cursor:pointer'><a>尾页</a></li>"
                   +"</ul>" 
                   +"<ul data-name='showPage_2' class='pagination' style='padding-left:5px'>"
                         +"<li class='active'><a href='javascript:void(0)'><span id='currentPage'></span>/<span id='totalPagNum'>"+totalPageNum+"</span>页</a></li>"
                   +"</ul>"
                   +"<ul data-name='showPage_3' class='pagination'>"
                         +"<div class='col-md-9'>"
                               +"<select id='currentPageSelect' class='form-control' style='margin-top:33%;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5p'>"
                                    +options
                               +"</select>"
                         +"</div>"
                         +"<div class='col-md-1' style='margin-top:20%;font-size:16px;padding-left:0px;color:#337ab7;font-weight:bold'>页</div>"
                   +"</ul>"
             +"</nav>";
	     
	     $("#"+pageID).html(page);
	     
	     commonSelf_pageStyleAct(pageID,callBack);
	     
	     $("#"+pageID+" #page_1").click();
	     
}
//与function commonSelf_pageStyle对应的操作
function commonSelf_pageStyleAct(pageID,callBack){
	
	     $("#"+pageID).on("click","nav >ul[data-name='showPage_1'] >li[id^='page_']",function(){
	    	 
	    	           var id_order='',lastPage='',totalPageNum='',omitPageNumNext='',omitPageNumPrev='',showPageNum='',lis='';
	    	           var each='';
	    	 
	    	           //如果本身已 处于选中状态，返回
	    	           if($(this).attr("class")=="active"){  
	    	        	     return ;
	    	           }
	    	           
	    	           totalPageNum=parseInt($("#"+pageID+"  #totalPagNum").text().trim());
	    	           showPageNum=$("#"+pageID+" ul[data-name='showPage_1']").children("li").length-4;
	    	           
	    	           
	    	           
	    	           //恢复之前页的鼠标小手状态
	    	           $(this).parent().children("li[id^='page_'][class='active']").css("cursor","pointer");
	    	           //去掉之前页的选中状态
	    	           $(this).parent().children("li[id^='page_'][class='active']").removeAttr("class");
	    	           
	    	           
	    	           //选中本页做为当前页
	    	           $(this).addClass("active");
	    	           //去掉本页鼠标的小手状态
	    	           $(this).css("cursor","default");
	    	           
	    	           
	    	           //记录当前页码
	    	           id_order=parseInt($(this).attr("id").split("_")[1]);
	    	           $("#"+pageID+"  #currentPage").text(id_order);
	    	           
	    	           
	    	           //通过当前页开启与关闭：上一页、下一页标签
	    	          // lastPage=$("#"+pageID+"  #nextPage").prev("li").attr("id");
	    	           if(id_order==1){
	    	        	    $("#"+pageID+"  #previousPage").addClass("disabled");
	    	        	    if($("#"+pageID+"  #nextPage").attr("class")=="disabled"){
	    	        	    	$("#"+pageID+"  #nextPage").removeAttr("class");
	    	        	    }
	    	           }else if(id_order==totalPageNum){
	    	        	    $("#"+pageID+"  #nextPage").addClass("disabled");
	    	        	    if($("#"+pageID+"  #previousPage").attr("class")=="disabled"){
	    	        	    	$("#"+pageID+"  #previousPage").removeAttr("class");
	    	        	    }
	    	           }else{
	    	        	    if($("#"+pageID+"  #previousPage").attr("class")=="disabled"){
	    	        	    	$("#"+pageID+"  #previousPage").removeAttr("class");
	    	        	    }
	    	        	    
	    	        	    if($("#"+pageID+"  #nextPage").attr("class")=="disabled"){
	    	        	    	$("#"+pageID+"  #nextPage").removeAttr("class");
	    	        	    }
	    	           }
	    	         
	    	           
	    	           if($("#"+pageID+" #page_1").attr("data-first")=="init"){
	    	        	     $("#"+pageID+" #page_1").removeAttr("data-first");  
	    	           }else{
	    	        	     callBack();
	    	           }
	    	          
	    	          
	     })
	     
	     
	     //上一页
	     $("#"+pageID+"  #previousPage").click(function(){
	    	 
	    	      var currentPage_old='',omitPageNumPrev='',pageNum='',showPageNum='',lis='';
	    	      var currentPage_max='';
	    	      var each='';
	    	      
	    	      currentPage_old=parseInt($("#"+pageID+"  #currentPage").text().trim());
	    	      pageNum=parseInt($("#"+pageID+" ul[data-name='showPage_1']").children("li").length);
	    	      showPageNum=parseInt(pageNum-4);
	    	      
	    	      if("page_"+currentPage_old==$(this).next("li").attr("id") && currentPage_old!=1){
	    	    	  
	    	    	         omitPageNumPrev=currentPage_old-1;
	    	    	         
	    	    	         if(omitPageNumPrev<showPageNum){
	    	    	        	 
	    	    	        	      lis='';
	    	    	        	      
	    	    	        	      currentPage_max=parseInt($("#"+pageID+" #nextPage").prev("li").attr("id").split("_")[1]);
	    	    	        	      
    	        	                  for(var i=currentPage_max;i>(currentPage_max-omitPageNumPrev);i--){
    	        	    	              $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id='page_"+i+"']").attr("data-name","remove_"+$(each).attr("id"));
    	        	                   }
	    	    	        	 
    	        	                  $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_'][data-name^='remove']").remove();
    	        	                  
    	        	                  for(var i=0;i<omitPageNumPrev;i++){
    	        	                	     lis +="<li id='page_"+(i+1)+"' style='cursor:pointer'><a>"+(i+1)+"</a></li>";
    	        	                  }
    	        	                  
    	        	                  $("#"+pageID+"  #previousPage").after(lis);
	    	    	        	 
	    	    	         }else{
	    	    	        	 
	    	    	        	      lis='';
	    	    	        	      
	    	    	        	      for(var i=0;i<showPageNum;i++){
	    	    	        	    	     each=$("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").eq(i);
 	        	    	                     $(each).attr("data-name","remove_"+$(each).attr("id"));
	    	    	        	      }
	    	    	        	      
	    	    	        	      $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_'][data-name^='remove']").remove();
	    	    	        	      
	    	    	        	      for(var i=(currentPage_old-5);i<currentPage_old;i++){
	    	    	        	    	      lis +="<li id='page_"+i+"' style='cursor:pointer'><a>"+i+"</a></li>";
	    	    	        	      }
	    	    	        	      
	    	    	        	      $("#"+pageID+"  #previousPage").after(lis);
	    	    	         }
	    	      }
	    	      
	    	      //显示数字页标签内容
	    	      $("#"+pageID+"  #page_"+(parseInt(currentPage_old)-1)).click();
	    	     
	     })
	     
	     //下一页
	     $("#"+pageID+"  #nextPage").click(function(){
	    	 
	    	     var currentPage_old='';
	    	     var totalPageNum='',omitPageNumNext='',showPageNum='',lis='';
  	             var each='';

  	             
  	             currentPage_old=parseInt($("#"+pageID+"  #currentPage").text().trim());  //在未移动前的当前页标签
  	             totalPageNum=parseInt($("#"+pageID+"  #totalPagNum").text().trim());
	             showPageNum=$("#"+pageID+" ul[data-name='showPage_1']").children("li").length-4;
  	             
	    	     //左移数字标签
	    	     if(("page_"+currentPage_old==$(this).prev("li").attr("id")) && currentPage_old!=totalPageNum){
  	        	   
        	                 omitPageNumNext=totalPageNum-currentPage_old;
        	      
        	                 if(omitPageNumNext<showPageNum){ //如果隐藏的页数<显示的页数
        	        	     
        	        	             lis='';
        	        	             for(var i=0;i<omitPageNumNext;i++){
        	        	    	
        	        	    	            each=$("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").eq(i);
        	        	    	            $(each).attr("data-name","remove_"+$(each).attr("id"));
        	        	             }
        	        	 
        	        	             $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_'][data-name^='remove']").remove();
        	        	     
        	        	             for(var i=currentPage_old;i<totalPageNum;i++){
        	        	    	            lis +="<li id='page_"+(i+1)+"' style='cursor:pointer'><a>"+(i+1)+"</a></li>";   
        	        	             }
        	        	     
        	        	             $("#"+pageID+"  #page_"+currentPage_old).after(lis);
        	                 }else{
        	        	 
        	        	             lis='';
        	        	             for(var i=0;i<showPageNum;i++){
        	        	    	            each=$("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").eq(i);
   	        	    	                    $(each).attr("data-name","remove_"+$(each).attr("id"));
        	        	             }
        	        	    
        	        	             $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_'][data-name^='remove']").remove();
        	        	 
        	        	             for(var i=currentPage_old;i<(currentPage_old+showPageNum);i++){
    	        	    	                lis +="<li id='page_"+(i+1)+"' style='cursor:pointer'><a>"+(i+1)+"</a></li>"    
    	        	                 }
    	        	     
    	        	                 $("#"+pageID+"  #previousPage").after(lis);
        	                 }
                }
	    	     
	    	     //显示数字页标签内容
   	             $("#"+pageID+"  #page_"+(parseInt(currentPage_old)+1)).click();
   	             
	     })
	     
	     
	     //首页
	     $("#"+pageID+"  #firstPage").click(function(){
	    	 
	    	       var lis='',pages='',showPageNum='';
	    	       
	    	       pages=commonSelf_pagesArray(pageID)[0].split("_");
	    	       showPageNum=parseInt($("#"+pageID+" ul[data-name='showPage_1']").children("li").length)-4;
	    	       
	    	       for(var i=0;i<showPageNum;i++){
                 	  lis +="<li id='page_"+pages[i]+"' style='cursor:pointer'><a>"+pages[i]+"</a></li>";
                  }
	    	       
	    	      $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").remove();
                   
                  $("#"+pageID+"  #previousPage").after(lis);
                  
                  $("#"+pageID+"  #page_1").click();
	     })
	     
	     
	     //尾页
	     $("#"+pageID+"  #lastPage").click(function(){
	    	 
	    	        var lis='',pages='',showPageNum='',totalPageNum='';
	    	        
	    	        
                    pages=commonSelf_pagesArray(pageID)[commonSelf_pagesArray(pageID).length-1].split("_");
                    showPageNum=parseInt($("#"+pageID+" ul[data-name='showPage_1']").children("li").length)-4;
                    totalPageNum=parseInt($("#"+pageID+"  #totalPagNum").text().trim());
                    
                    for(var i=0;i<showPageNum;i++){
                    	  lis +="<li id='page_"+pages[i]+"' style='cursor:pointer'><a>"+pages[i]+"</a></li>";
                    }
                    $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").remove();
                    
                    $("#"+pageID+"  #previousPage").after(lis);
                    
                    $("#"+pageID+"  #page_"+totalPageNum).click();
	    	 
	     })
	     
	     
	     //文本框选择项
	     $("#"+pageID+" #currentPageSelect").change(function(){
	    	 
	    	         var selected_id='',pageArray='',eachPages='',pageArrayIndex='',showPageNum='',lis='';
	    	         
	    	         selected_id=$(this).children("option:selected").attr("value").split("_")[1];
	    	         
	    	         showPageNum=parseInt($("#"+pageID+" ul[data-name='showPage_1']").children("li").length)-4;
	    	 
	    	         pageArray=commonSelf_pagesArray(pageID);
	    	         
	    	         for(var i=0;i<pageArray.length;i++){
	    	        	     eachPages=pageArray[i].split("_");
	    	        	     if($.inArray(selected_id,eachPages)!=-1){
	    	        	    	    pageArrayIndex=i;
	    	        	    	    break;
	    	        	     }
	    	         }
	    	         
	    	         console.log(pageArray[pageArrayIndex])
	    	         for(var i=0;i<showPageNum;i++){
	    	        	     lis +="<li id='page_"+pageArray[pageArrayIndex].split("_")[i]+"' style='cursor:pointer'><a>"+pageArray[pageArrayIndex].split("_")[i]+"</a></li>";
	    	        	    
	    	         }
	    	         
	    	         $("#"+pageID+"  ul[data-name='showPage_1']").children("li[id^='page_']").remove();
	    	         
	    	         $("#"+pageID+"  #previousPage").after(lis);
	    	 
	    	         $("#"+pageID+"  #page_"+selected_id).click();
	    	 
	     })
	
}

//分页，页数分组
function commonSelf_pagesArray(pageID){
	
	     var totalPageNum='',showPageNum='',D_value='',lastArray_startNum='';
	     var pageArray_each='';
	     
	     var pageArray=new Array();

	     totalPageNum=parseInt($("#"+pageID+"  #totalPagNum").text().trim());
	     showPageNum=parseInt($("#"+pageID+" ul[data-name='showPage_1']").children("li").length)-4;
	
	     for(var i=1;i<=totalPageNum;i++){
	    	     
	    	     if(i%showPageNum==0){
	    	    	   pageArray.push(pageArray_each+i);
	    	    	   pageArray_each='';
	    	    	   lastArray_startNum=i+1;
	    	     }else{
	    	    	   pageArray_each +=i+"_";
	    	     }
	     }
	     
	     if(pageArray_each!=''){

	    	   pageArray_each='';
	    	   
	    	   if(totalPageNum<=showPageNum){
	    		      for(var i=1;i<totalPageNum;i++){
	    		          pageArray_each +=i+"_";
  	                  }
	    	   }else{
	    		      D_value=showPageNum-totalPageNum%showPageNum;
		    	   
		    	      for(var i=(lastArray_startNum-D_value);i<totalPageNum;i++){
		    		       pageArray_each +=i+"_";
	  	              }
	    	   }
	    	   
	    	   pageArray.push(pageArray_each+totalPageNum);
	     }
	     return pageArray;
	
}