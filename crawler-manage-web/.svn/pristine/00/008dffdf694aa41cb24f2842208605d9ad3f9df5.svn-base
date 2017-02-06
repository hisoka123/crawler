/**
 * 
 * @param imgsId     图片组id名
 * @param bigImgs    大图数组
 * @param idActive   class为active的图片序号
 * @param smallImgs  小图数组
 * @param countsOnePage   在小图浏览栏中，一页显示几张小图
 * @returns {String}      图片幻灯片
 */
function imgSlide(imgsId,bigImgs,idActive,smallImgs,countsOnePage){
	    
	    // alert("*****imgsId:"+imgsId+"\n*****bigImgs:"+bigImgs+"\n*****idActive:"+idActive+"\n*****smallImgs:"+smallImgs+"\n*****countsOnePage:"+countsOnePage);
         
	     //调用大图样式
	      var big=bigImgsStyle(imgsId,bigImgs,idActive);
	      if(smallImgs.length==1){
	    	  return big;
	      }
	     //调用小图样式
	     var small=smallImgsStyle(imgsId,idActive,smallImgs,countsOnePage);
	   // alert(big);
	     
	     return big+small;
	
}
/*
 * 大图样式
 */
function  bigImgsStyle(imgsId,bigImgs,idActive){
	      var bigImgsStyle_result='';
	      var all_imgs='';  //大图
	 
	      var aStyle='';
	      if (bigImgs.length==1){
	    	  return '<div style="width:750px;">'
	    		           +'<img src="'+bigImgs[0]+'">'
	    	          +'</div>';
	    	    
	      }
	      
	 
	      
          for(var i=0;i<bigImgs.length;i++){
          	    if(idActive==i){
          	        all_imgs +='<div id="'+imgsId+'_big_'+i+'" class="item active"><img src="'+bigImgs[i]+'"></div>';
          	    }else{
                    all_imgs +='<div id="'+imgsId+'_big_'+i+'" class="item"><img src="'+bigImgs[i]+'"></div>';
          	    }
               
          }  
     
          bigImgsStyle_result='<div style="width:750px;">'
                                      +'<div id="'+imgsId+'_big_slide" class="carousel slide" style="margin:2%;width:57%;">'
                                              +'<div class="carousel-inner">'
                                                     +all_imgs
                                              +'</div>'
                                              +'<a  class="carousel-control left" href="#'+imgsId+'_big_slide"  data-slide="prev" data-interval="false" style="width:30px;left:-5%;"><h2>&lsaquo;</h2></a>'
                                              +'<a  class="carousel-control right" href="#'+imgsId+'_big_slide" data-slide="next" data-interval="false"  style="width:30px;right:-5%;"><h2>&rsaquo;</h2></a>'
                                      +'</div>'                             
                              +'</div>';
       
          return bigImgsStyle_result;
	
	
}

/**
 * 
 * @param {} smallImgs       小图数组
 * @param {} countsOnePage   一页显示小图的个数
 */
function smallImgsStyle(imgsId,idActive,smallImgs,countsOnePage){
     var smallImgsStyle_result='';
     
     
     var pages=smallImgs.length%countsOnePage==0?smallImgs.length/countsOnePage:Math.floor((smallImgs.length/countsOnePage)+1);  //小图能够显示几页
   
     var idActive_page_index=idActive%countsOnePage==0?idActive/countsOnePage:Math.floor((idActive/countsOnePage));      //class为active的大图对应的小图在第几页
     
     
     var pages_style='';
     for(var i=0;i<pages;i++){
     	     if(idActive_page_index==i){
     	         pages_style +='<div id="'+imgsId+'_page_'+(i+1)+'" class="item active">';
     	     }else{
     	         pages_style +='<div id="'+imgsId+'_page_'+(i+1)+'" class="item">';
     	     }
     	     
     	     for(var j=i*countsOnePage;j<smallImgs.length;j++){
     	     	     if("undefined"==smallImgs[j]) break;     //图片组中最后一张图片时，退出
     	             pages_style +='<span><img id="'+imgsId+'_small_'+j+'" src="'+smallImgs[j]+'" onClick="smallToBig(this)" style="cursor:pointer;" ></span>&nbsp;';
     	             if((j+1)%countsOnePage==0) break;  //当一页上的小图个数为countsOnePage个时，进入下一页
     	     }    
     	     pages_style +="</div>";
     }
     
  
     smallImgsStyle_result='<div style="width:750px;">'
                                 +'<div id="'+imgsId+'_small_slide" class="carousel slide" style="margin:2%;width:57%;">'
                                        +'<div class="carousel-inner">'
                                               +pages_style
                                        +'</div>'
                                        +'<a  class="carousel-control left" href="#'+imgsId+'_small_slide"  data-slide="prev" data-interval="false" style="width:30px;left:-5%;"><h2>&lsaquo;</h2></a>'
                                        +'<a  class="carousel-control right" href="#'+imgsId+'_small_slide" data-slide="next" data-interval="false"  style="width:30px;right:-5%;"><h2>&rsaquo;</h2></a>'
                                 +'</div>'
                           +'</div>';
 
     return smallImgsStyle_result;
	
}


//点击小图显示相应大图
function smallToBig(img){
     var index=$(img).attr("id");//获取小图的id号
     index=index.split("_small_");    //转换为数组，长度为2.其中，[0]为图片组id,[1]为图片序号
     var parent=$(img).parent().parent().parent();
     
     var indexs=parent.find("span").length;   //图片组的长度
    
     
     
     
     
     for(var i=0;i<indexs;i++){
    	   //使大图序号为当前点击小图序号的大图class为active
     	   if(i==index[index.length-1]){
     	      $("#"+index[0]+"_big_"+i).attr("class","item active");
     	   }else{
     	      $("#"+index[0]+"_big_"+i).attr("class","item");
     	   }
     }
         
}

