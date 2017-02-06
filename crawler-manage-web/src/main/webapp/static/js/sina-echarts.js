//人脉圈echarts显示
function sina_echarts_twoContacts(rootPath,uid,nickname,img_url,one_contacts,two_contacts){
	/* alert("测试二度人脉echarts显示sina_echarts_twoContacts:"
			+"\n*****rootPath:"+rootPath
			+"\n*****uid:"+uid
			+"\n*****nickname:"+nickname
			+"\n*****img_url:"+img_url
			+"\n*****one_contacts:"+one_contacts
			+"\n*****two_contacts:"+two_contacts);
	*/
	
	
	  var already_uid=0;                  //是否已收录，未收录，为0，已收录为1
	  var two_contacts_uid=new Array();   //已收录的二度人脉uid
	  var tempRestore;  //临时存储
	  var tempRestore_array=new Array();
	  
	  
	
	require(
	         [
	            'echarts',
	            'echarts/chart/force'
	          
	          ],		
	          function (ec){	        	  
	        	  var myChart_two=ec.init(document.getElementById('two'),'macarons');
	        	  var option={
	        		 
	        			title:{
	        				    text:"人脉指数",
	        				    x:'right',
	        				    y:'top'
	        			},
	        		    tooltip : {
	        		        trigger: 'item',
	        		        formatter: '{b}'
	        		    },
	        		          		 
	        		    series : [
	        		        {
	        		            type:'force',
	        		            name : "二度人脉",
	        		            ribbonType: false,
	        		            categories : [
	        		                {
	        		                    name: '人物'
	        		                },
	        		                {
	        		                    name:'朋友'
	        		                }
	        		            ],
	        		            itemStyle: {
	        		                normal: {
	        		                    label: {
	        		                        show:false,
	        		                       textStyle: {
	        		                            color: '#333'
	        		                        }
	        		                    },
	        		                    nodeStyle : {
	        		                        borderColor : 'rgba(255,215,0,0.4)',
	        		                        borderWidth : 1
	        		                    }
	        		                },
	        		                emphasis: {
	        		                    label: {
	        		                        show: false
	        		                    },
	        		                    nodeStyle : {
	        		                    },
	        		                    linkStyle : {}
	        		                }
	        		            },
	        		            minRadius : 55,
	        		            maxRadius : 55,
	        		            gravity: 1,
	        		            scaling: 1.3,
	        		            draggable: true,
	        		            linkSymbol: 'arrow',
	        		            steps: 10,
	        		            coolDown: 0.9,
	        		            nodes:[
	        		                   {
	        		                	   category:0,
	        		                	   name:"核心人物："+nickname,
	        		                	   symbol:'image://'+img_url,
	        		                	   symbolSize:[35,35],
	        		                	   value:uid,
	        		                	   draggable:true  
	        		                   }
	        		            ],
	        		            links:[]
	        		        }
	        		    ]
	        	  };//option
	        	  var ecConfig = require('echarts/config');
	        	  var nodes=option.series[0].nodes;
	        	  var links=option.series[0].links;
	        	  var corePerson="核心人物："+nickname;
	        	  
	        	  for(var i=0;i<one_contacts.friend.length;i++){
	        	        nodes.push({
	        	        	         category:1,
	        	        	         name:"一度人脉: "+one_contacts.friend[i].nickname,
	        	        	         symbol:'image://'+one_contacts.friend[i].profile_image,
	        	        	         symbolSize:[25,25],
	        	        	         value:one_contacts.friend[i].uid
	        	        	       });
	        	        links.push({
   	        	                     source:corePerson,
   	        	                     target:"一度人脉: "+one_contacts.friend[i].nickname
   	        	       });
	        	  }
	        	  
	        	  for(var i=0;i<two_contacts.length;i++){
	        		      if('undefined'==typeof(two_contacts[i].friend)) continue;
	        		      
	        		      for(var k=0;k<one_contacts.friend.length;k++){
      		    	            if(two_contacts[i].uid==one_contacts.friend[k].uid){
      		    	    	          tempRestore=one_contacts.friend[k];
      		    	    	          break;
      		    	            }
      		               }
	        		       var num=1; //二度人脉计数
	        		       
	        		       if(two_contacts[i].friend==null) continue;
	        		       for(var k=0;k<two_contacts[i].friend.length;k++){
	        			           
	        			           already_uid=0;
	        		    	   
	        		    	       //查看二度人脉与核心人物是否相等
	        		    	       if(two_contacts[i].friend[k].uid==one_contacts.uid){
	        		    		        already_uid=1;
	        		    	       }
	        		    	       //查看二度人脉与一度人脉中的人物是否重复
	        		    	       for(var p=0;p<one_contacts.friend.length;p++){
	        		    		           if(two_contacts[i].friend[k].uid==one_contacts.friend[p].uid){
	        		    			              already_uid=1;
	        		    		    	          break;
	        		    		           }
	        		           	    }
	        		    	        //查看二度人脉是否已收录
	        		    	        for(var p=0;p<two_contacts_uid.length;p++){
	        		    		            if(two_contacts[i].friend[k].uid==two_contacts_uid[p]){
	        		    			              already_uid=1;
	        		    			              break;
	        		    		            }
	        		    	         }
	        		    	         if(already_uid==1) continue;  //如果该uid已收录，则下一个
	        			    
	        			    
	        			              nodes.push({
	        			    	                category:1,
	        			    	                name:"二度人脉: "+two_contacts[i].friend[k].nickname,
	        			    	                symbol:'image://'+two_contacts[i].friend[k].profile_image,
	        			    	                symbolSize:[17,17],
	        			    	                value:two_contacts[i].friend[k].uid
	        			              });
	        			        
	        			              links.push({
      		    		                        source:"一度人脉: "+tempRestore.nickname,
      		    		                        target:"二度人脉: "+two_contacts[i].friend[k].nickname
      		    	                  });
	        			              
	        			              
	        			              //将收录的uid放入数组
	        				    	  two_contacts_uid.push(two_contacts[i].friend[k].uid);
	        			             // if(num++==3) break; //达到三个二度人脉跳出
	        		     }
	        	   }
	        	  
	        	  if(two_contacts_uid.length>=10) {
	        		  option.title.text="\n一度人脉指数: 0"+one_contacts.friend.length+"\n\n 二度人脉指数: "+two_contacts_uid.length; 
	        	  }else{
	        		  option.title.text="\n一度人脉指数: 0"+one_contacts.friend.length+"\n\n 二度人脉指数: 0"+two_contacts_uid.length; 
	        	  }
	        	  
	        	 
	              //链接人脉中微博帖子详情	  
	        	  function nodeDetail(param){	        		 
	        		   var data=param.data;
	        		   sina_common_contactsWeibo(rootPath,data.value,data.name);
	        	  }
	        	  
	        	 // myChart_two.on(ecConfig.EVENT.CLICK,nodeDetail);
	        	  myChart_two.setOption(option);
	         }//function(ec)
	        
	);//require
	
	
}//sina_