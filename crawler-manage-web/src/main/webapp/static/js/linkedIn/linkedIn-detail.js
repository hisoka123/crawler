$(function(){
	
	   var dataNames='',dataNameArray='';
	   var personObj='';
	   var table='',isNull="<td class='col-md-3'>---</td>";
	   
	   var head='',headline='',location='',industry='',cur_positions='';
	   var pre_positions='',bg_educations='';
	   
	   ROOTPATH=$("#ctx").val();
	   dataNames=localStorage.getItem("linkedin_whichContrast");
	   
	   if(dataNames==null){
		      return;
	   }
	
	   dataNameArray=dataNames.split("**##**");
	   
	   for(var i=0;i<dataNameArray.length;i++){
		   
               personObj=linkedIn_getLinkedInDetail($("#ctx").val(),dataNameArray[i]);
               
              /* var aa='';
               for(var i in personObj){
            	    aa +=i+":"+personObj[i]+"\n";
               }
               alert(aa);
               */
               if(personObj==null && personObj=='' && personObj=="isError"){
            	     continue;
               }else{
            	     //头像和姓名
            	     head +="<th class='col-md-3'>"
            	                 +"<a href='"+personObj.profile+"' target='_blank'><img src='"+personObj.profile_img+"' class='img-circle' style='width:80px' onerror='personImgSubstitute(this)'></a>"
            	                 +"<div>"+personObj.name+"</div>"
            	             +"</th>";
            	     
            	     //头衔
            	     if(personObj.headline!=null && personObj.headline!=''){
            	    	      headline +="<td class='col-md-3'>"
            	    	    	              +personObj.headline
            	    	    	         +"</td>";
            	     }else{
            	    	     headline +=isNull;
            	     }
            	     
            	     //地区
            	     if(personObj.location!=null && personObj.location!=''){
            	    	     location +="<td class='col-md-3'>"
            	    	    	             +personObj.location
            	    	    	        +"</td>";
            	     }else{
            	    	     location +=isNull;
            	     }
            	     
            	     //行业
            	     if(personObj.industry!=null && personObj.industry!=''){
            	    	     industry +="<td class='col-md-3'>"
            	    	    	             +personObj.industry
            	    	    	        +"</td>";
            	     }else{
            	    	     industry +=isNull;
            	     }
            	     
            	     //目前就职
            	     if(personObj.cur_positions!=null && personObj.cur_positions!=''){
            	    	    cur_positions +="<td class='col-md-3'>"
            	    	    	                 +personObj.cur_positions
            	    	    	            +"</td>";
            	     }else{
            	    	    cur_positions +=isNull;
            	     }
            	     
            	     //曾经就职
            	     if(personObj.pre_positions!=null && personObj.pre_positions!=''){
            	    	    pre_positions +="<td class='col-md-3'>"
            	    	    	                 +personObj.pre_positions
            	    	    	            +"</td>";
            	     }else{
            	    	    pre_positions +=isNull;
            	     }
            	   
            	     //教育背景
            	     if(personObj.bg_educations!=null && personObj.bg_educations!=''){
                            bg_educations +="<td class='col-md-3'>"
                            	                 +personObj.bg_educations
                            	            +"</td>";
            	     }else{
            	    	    bg_educations +=isNull;
            	     }
            	   
            	   
               }//if
	   }//for
	   
	   table ="<table class='table table-bordered'>"
		            +"<thead>"
		                +"<tr class='scrollColThead'>"
		                      +"<th class='col-md-1'>*</th>"
		                      +head
		                +"</tr>"
		            +"</thead>"
		            +"<tbody>"
		                +"<tr data-name='headline'>"
		                      +"<th class='col-md-1'>头衔</th>"
		                      +headline
		                +"</tr>"
		                +"<tr data-name='location'>"
		                      +"<th class='col-md-1'>地区</th>"
		                      +location
		                +"</tr>"
		                +"<tr data-name='industry'>"
	                          +"<th class='col-md-1'>行业</th>"
	                          +industry
	                    +"</tr>"
	                    +"<tr data-name='cur_positions'>"
                              +"<th class='col-md-1'>目前就职</th>"
                              +cur_positions
                        +"</tr>"
                        +"<tr data-name='cur_positions'>"
                              +"<th class='col-md-1'>曾经就职</th>"
                              +pre_positions
                        +"</tr>"
                        +"<tr data-name='cur_positions'>"
                              +"<th class='col-md-1'>教育背景</th>"
                              +bg_educations
                        +"</tr>"
                        		
		            +"</tbody>"
		      +"</table>";
	   
	   
	   $("#linkedInContrastDetail").html(table);
	
})