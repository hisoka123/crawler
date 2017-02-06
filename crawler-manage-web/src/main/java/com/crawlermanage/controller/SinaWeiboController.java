package com.crawlermanage.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.weibo.domain.json.FriendCircle;
import com.crawler.weibo.domain.json.TweetJson;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawlermanage.service.TweetService;
import com.crawlermanage.service.UserFeedService;
import com.crawlermanage.service.weibo.WeiboTweetService;
import com.crawlermanage.service.weibo.WeiboUserActionService;
import com.crawlermanage.service.weibo.WeiboUserFriendCircleService;
import com.crawlermanage.service.weibo.WeiboUserSearchService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.data.UserFeed;
/**
 * 本类为sprig mvc的controller类，主要功能为以下模块：
 * 
 * 
 * @author hmy
 *
 */

@Controller
@RequestMapping("/sinaWeibo")
public class SinaWeiboController {
    
	private static final Logger log = LoggerFactory.getLogger(SinaWeiboController.class);
	
	@Autowired
	private WeiboUserSearchService weiboUserSearchService;
	
	@Autowired
	private WeiboUserActionService weiboUserActionService;
	
	@Autowired
	private UserFeedService userFeedService;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private WeiboTweetService weiboTweetService;
	
	@Autowired
	private WeiboUserFriendCircleService weiboUserFriendCircleService;
	
	
	String usersJson;
	
	
/*
 * (1)爬取人物的基本信息			
 */
	//(1-1)转向”爬取“页，填写爬取人物。
	@RequestMapping("/crawler")
	public String  crawler(){
		return "sinaWeibo/crawler";
	}
	
	//(1-2)转向“爬取结果”页
	@RequestMapping(value="/crawler_search",method=RequestMethod.POST)
	public String crawler_search(@RequestParam("crawlerPerson") String crawlerPerson,Model model){		
		model.addAttribute("crawlerPerson", crawlerPerson);
		return "sinaWeibo/crawler_search";		
	}
	

	//(1-3)将爬取结果以对象的形式传向前端
	@RequestMapping("/crawler_searchResult")
	public @ResponseBody List<UserFeedJson> toCrawler22(HttpServletRequest request,HttpServletResponse response){
		
		 List<UserFeedJson> userFeedJsonList=null;
		 Result<List<UserFeedJson>> result=null;
		 try {
		      String crawlerPerson = URLDecoder.decode(request.getParameter("crawlerPerson"),"utf-8");
			  log.info("crawlerPerson:{}",crawlerPerson);
			  crawlerPerson= "http://s.weibo.com/user/"+crawlerPerson;
			  log.info("crawlerPerson:{}",crawlerPerson);
	          result = weiboUserSearchService.searchUser(crawlerPerson,false,"");          
			  userFeedJsonList=result.getData();
	          
	          Gson gson = new Gson();  		
		      usersJson = gson.toJson(userFeedJsonList);
		      log.info("爬取结果:{}", usersJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userFeedJsonList;
		
	}
	
	//(1-3)将数据库全部人物的uid以字符串形式传向前端，以检查爬取结果有哪些人物是已关注的
		@RequestMapping("/personAllUid")
		public void personAllUid(HttpServletResponse response){
			List<UserFeed> userFeeds=userFeedService.getUserFeeds();
			
			StringBuilder sb=new StringBuilder();
			
			for(int i=0;i<userFeeds.size();i++){
				if(userFeeds.get(i).getEnable()==0){
					continue;
				}
				if(i==(userFeeds.size()-1)){
			         sb.append(userFeeds.get(i).getUid()+"");		
				}else{
					sb.append(userFeeds.get(i).getUid()+",");				
				}
			}		
			try {
				response.getWriter().print(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
/*
 * (2)已关注人物列表
 */
	//(2-1)转向“已关注人物列表”页		
	@RequestMapping("/attentionInfo")
	public String attentionInfo1(Model model){		
		return "sinaWeibo/attentionInfo";		
	}
	
	//(2-2)将全部已关注人物从数据库取出，并以对象的形式发向前端
	@RequestMapping("attentionInfo_read")
	public @ResponseBody List<UserFeed> attentionInfo1_1(){
		List<UserFeed> userFeeds=userFeedService.getUserFeeds_2();
		return userFeeds;		
	}
	/*public @ResponseBody Page<UserFeed> attentionInfo1_1(Integer page,Integer pageSize){
		Page<UserFeed> userFeeds=userFeedService.getUserFeeds_2(page, pageSize);
		return userFeeds;
	}*/
	
	//(2-3)转向“搜索已关注人物”页
	@RequestMapping(value="/attentionInfo_search",method=RequestMethod.POST)
	public String attentionInfo_search(@RequestParam("attention_searchPerson") String attention_searchPerson,Model model){	
			 log.info("attenation_searchPerson:{}",attention_searchPerson);
			 model.addAttribute("attention_searchPerson",attention_searchPerson);				 		
		     return "sinaWeibo/attentionInfo_search";
	}
	
	//(2-4)将在已关注人物列表中搜索到的结果，传向前端
	@RequestMapping(value="/attentionInfo_search_result",method=RequestMethod.GET)
	public @ResponseBody List<UserFeed> attentionInfo_Search_result(HttpServletRequest request,HttpServletResponse response){
		List<UserFeed> searchUserFeeds=null;
		try {
			String attention_searchPerson = URLDecoder.decode(request.getParameter("attention_searchPerson"),"utf-8");
			log.info("attention_searchPerson:{}",attention_searchPerson);	
		     searchUserFeeds=userFeedService.searchScreen_name(attention_searchPerson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	     return searchUserFeeds;
	}


	
	
	
/*
 * (3)人物发表帖子的详情页,包括已关注人物的帖子详情页、未关注人物的帖子详情页
 */
	
	/*
	 * (3-1)已关注人物
	 */
	//(3-1-1)转向已关注人物的详情页
	@RequestMapping(value="/postsAttention",method=RequestMethod.GET)
	public String attentionPosts(@RequestParam("uid") String uid,Model model){
		model.addAttribute("postsAttention",uid);
		return "sinaWeibo/postsAttention";
	}
	
	//(3-1-2)将单个已关注人物的基本信息从数据库读取，以对象的形式传向前端
	@RequestMapping("/attention_personalInfo")
	public @ResponseBody UserFeed attention_personInfo(@RequestParam("uid") String uid){		    
		    log.info("已关注--帖子--人物--uid:{}"+uid);
			UserFeed userFeed=userFeedService.getUserFeed(Long.valueOf(uid));
			Gson gson=new Gson();
			log.info("已关注--人物基本信息：{}",gson.toJson(userFeed));
			return userFeed;
			    
	}
	
	/*
	 * (3-2)未关注人物
	 */
	
	 //(3-2-1)转向未关注人物帖子详情页
	   @RequestMapping(value="/unattentionPosts",method=RequestMethod.GET)
	   public String unattentionPosts(@RequestParam("uid") String uid,@RequestParam("nickname") String nickname,
			                           Model model){
		        
		       String name="";
		        try {
					 name=URLDecoder.decode(nickname,"utf-8");				
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	         
				log.info("name:{}"+name);
				log.info("uid:{}"+uid);
		        model.addAttribute("weiboPosts",name+","+uid);			   
		        return "sinaWeibo/unattentionPosts";	   
	}
	
	 //(3-2-2)将未关注人物的基本信息重新获取，以对象形式发向前端
	   @RequestMapping(value="/un_attention_oneProfile",method=RequestMethod.GET)
	   public @ResponseBody UserFeedJson un_attention_oneProfile(@RequestParam("nickname") String nickname,
	   		                                                  @RequestParam("uid") String uid){
	   	       String name;
	   	       List<UserFeedJson> userFeedJsonList;
	   	       UserFeedJson userFeedJson=null;
	   	       Result<List<UserFeedJson>> result=null;
	   	       try {
	   			name=URLDecoder.decode(nickname,"utf-8");
	   			name= "http://s.weibo.com/user/"+name;
	   	        result = weiboUserSearchService.searchUser(name,false,"");
	   	        userFeedJsonList=result.getData();
	   			for(Iterator<UserFeedJson> iter=userFeedJsonList.iterator();iter.hasNext();){
	   				   userFeedJson=iter.next();
	   				   if(uid.equals(userFeedJson.getUid())){					  					  					  
	   					   break;
	   				   }
	   			  }
	   			log.info("未关注--详情--人物基本信息userFeedJson:{}"+userFeedJson);
	   		} catch (UnsupportedEncodingException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}	
	   	return userFeedJson;
	   }	
	
	
	
	/*
	 * (3-3)帖子详情页
	 */
	//(3-3-1)爬取人物的微博（一页），将结果以对象形式传向前端
	@RequestMapping(value="/personWeibo_detail",method=RequestMethod.GET)
	public @ResponseBody List<TweetJson> personWeibo_uid(@RequestParam("uid") String uid,@RequestParam("page") String page){
	     				
			String url="http://weibo.com/"+uid+"?page="+page;
		    log.info("详情url:{}"+url);
            
		    
		    Result<List<TweetJson>> result=weiboTweetService.getTweets(url,false,"");
		    List<TweetJson> tweetJsons=result.getData();
			Gson gson=new Gson();
			String tweetjson=gson.toJson(tweetJsons);
			
			log.info("详情tweetJson:{}"+tweetjson);
			log.info("详情uid:{}"+uid);
			log.info("详情page:()"+page);
		    
			return tweetJsons;	   		  
    }
	
	//(3-3-2)将人物帖子页数，以字符串传向前端（只爬5页），用于分页
		@RequestMapping(value="/weiboPage_count",method=RequestMethod.GET)
		public void weiboPage_count(HttpServletRequest request,HttpServletResponse response){
					
			  String url="http://weibo.com/"+request.getParameter("uid")+"?page=";	   
			  
			  int i;
		      for(i=2;i<=5;i++){ 
		    	  if(weiboTweetService.getTweets(url+i,false,"").getData().size()==0){	    		  
		    		  break;
		    	  }	    	 
		      }
		      log.info("微博页数page:{}"+(i-1));
			 try {
				response.getWriter().print(""+(i-1));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	
	
/*
 * (4)加关注及取消关注，即保存及删除人物的基本信息	
 */
		//(4-1)将爬取到人物的基本信息存入数据库，并根据存入结果返回相应信号
		     //应用页面：爬取结果页，未关注人物帖子详情页。
		     //数据来源：爬取结果
		@RequestMapping("/crawlerSave")
		public void crawlerSave(HttpServletRequest request,HttpServletResponse response){
			  try {
				
				String crawlerPerson=URLDecoder.decode(request.getParameter("crawlerJson"),"utf-8");
				log.info("要保存的人：{}"+crawlerPerson);
				Gson gson=new Gson();
				UserFeedJson userFeedJson=gson.fromJson(crawlerPerson, new TypeToken<UserFeedJson>(){}.getType());
				
				UserFeed userFeed =new UserFeed();
						
				userFeed.setUid(Long.valueOf(userFeedJson.getUid()));
				userFeed.setScreen_name(userFeedJson.getNickname());
				userFeed.setLocation(userFeedJson.getLocation());
				userFeed.setDescription(userFeedJson.getPerson_info());
				userFeed.setProfile_image_url(userFeedJson.getProfile_image());
				userFeed.setGender(userFeedJson.getGender());
				userFeed.setFollowers_count(toLong(userFeedJson.getFans()));
				userFeed.setFollowers_url(userFeedJson.getFans_url());
				userFeed.setFriends_count(toLong(userFeedJson.getFollow()));
				userFeed.setFriends_url(userFeedJson.getFollow_url());
				userFeed.setStatuses_count(toLong(userFeedJson.getStatuses()));
				userFeed.setStatuses_url(userFeedJson.getStatuses_url());
				
				String saved_ok=weiboUserActionService.userAction(FunctionDefine.USER_ATTENTION, userFeedJson.getUid(),false,"").getData().getOk();
				//String saved_ok="2";
				if("1".equals(saved_ok)){
				     if(userFeedService.save(userFeed)){
					      response.getWriter().print("saved-success");
				        }
				}else{
					 response.getWriter().print("saved-failed");
				}			
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	
   //(4-2)将人物的基本信息保存到数据库，并根据存入成败返回相应结果
          //应用情景：已关注人物列表页、已关注人物帖子页
          //数据来源：数据库
		 @RequestMapping("/attentionSave")
		 public void attentionSave(HttpServletRequest request,HttpServletResponse response){
			    String attentionPerson;
				try {
					     attentionPerson = URLDecoder.decode(request.getParameter("attentionJson"),"utf-8");
					     log.info("人物列表要保存的人：{}"+attentionPerson);
					     Gson gson=new Gson();
					     UserFeed userFeed=gson.fromJson(attentionPerson, new TypeToken<UserFeed>(){}.getType());
					
					    String saved_ok=weiboUserActionService.userAction(FunctionDefine.USER_ATTENTION, userFeed.getUid().toString(),false,"").getData().getOk();
					    //String saved_ok="2";
					    if("1".equals(saved_ok)){			       	     
					           if(userFeedService.save(userFeed)){					          
						          response.getWriter().print("saved-success");
					           }
					    }else{
						      response.getWriter().print("saved-failed");
					    }				
				 } catch (UnsupportedEncodingException e) {
					  // TODO Auto-generated catch block
					    e.printStackTrace();
				 } catch (IOException e) {
					 // TODO Auto-generated catch block
					   e.printStackTrace();
				}		 
		 }
		
	
	//(4-3)删除某个已关注的人，根据删除成败返回相应结果。
		 //删除过程：并不是真正意义上的将数据从数据库中删除，而是将其enable值设为0，使其在页面上不能得以显示.
		 //应用情景：所有取消关注的地方
			@RequestMapping("/crawlerDelete")
			public void crawlerDelete (HttpServletRequest request,HttpServletResponse response){
				   
				
				try {
				    String	deleteUid = URLDecoder.decode(request.getParameter("deleteUid"),"utf-8");
				    log.info("删除uid{}:"+deleteUid);
				    String deleted_ok=weiboUserActionService.userAction(FunctionDefine.USER_DELATTENTION, deleteUid,false,"").getData().getOk();
					//String deleted_ok="2";
				    if("1".equals(deleted_ok)){
				          if(userFeedService.deleteUid(Long.valueOf(deleteUid))){
						        response.getWriter().print("deleted-success");
					   }
				    }else{
				    	response.getWriter().print("deleted-failed");
				    }
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			
			
    /*
     * (5)人脉圈
     */
	   //(5-1)链接人脉圈页
			
	@RequestMapping("/contacts")
	public String contacts (@RequestParam("uid") String uid,@RequestParam("isAttention") String isAttention,Model model){
	    log.info("人脉：{}"+uid);
	    log.info("人脉：{}"+isAttention);
		model.addAttribute("contactsPerson",uid+","+isAttention);			
		return "sinaWeibo/contacts";
	}
	
	//(5-2)人脉圈详情
	@RequestMapping(value="/contactsDetail",method=RequestMethod.GET)
	public @ResponseBody FriendCircle contactsDetail(@RequestParam("uid") String uid,String depth,String limit){
		    int default_total =1;
		    int default_total_max = 5;
		    int default_limit = 1;
			        	
		    log.info("人脉uid{}:"+uid);
		    log.info("人脉depth{}:"+depth);
		    log.info("人脉limit{}:"+limit);
		     
		    if(uid==null||"".equals(uid.trim())){
				return null;
			}
			int iTotal = getInt(depth,default_total);
			iTotal = iTotal>default_total_max?default_total_max:iTotal;//限制最多5页
			int iLimit = getInt(limit,default_limit);
			FriendCircle friendCircle = weiboUserFriendCircleService.getFriendCircle(uid,iTotal,iLimit,false,"").getData();
			
			
		    return friendCircle;
	}
		
	/*
	 * (6)其它	
	 */
	//(6-1)判断数字中是否有"万"，如果有，则转换。用于存入数据库
		  public Long toLong(String str){
			  int index=str.indexOf("万");
			  if(index==-1){
					return Long.valueOf(str);
			  }else{
				    return Long.valueOf(str.substring(0, index))*10000;
			  }		
		  }	
		  
	//(6-1)	  
		 public int getInt(String str,int defau){
			   int i ;
			   if(str==null){
				  i = defau;
			   }else{
				  try{ 
					  i = Integer.parseInt(str); 
					  i = i<=0?defau:i;
				  }catch(Exception e){
					  log.info(e.getMessage());
					  i = defau;
				  }
			   } 
				return i;
		}
		 

}
