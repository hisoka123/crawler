package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawlermanage.controller.common.Config;
import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.ownerTask.EnterpCreditOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.qiyezhengxin.QiyezhengxinApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskEnterpCredit;
import com.module.dao.entity.qiyezhengxin.Zhengxin;
import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;

@Controller
@RequestMapping("/modules/zhengxin")
public class ZhengxinController {

	
	private static final Logger log = LoggerFactory.getLogger(ZhengxinController.class);
	
	@Autowired
	private QiyezhengxinApiService qiyezhengxinApiService;
	
	@Autowired
	private EnterpCreditOTService enterpCreditOTService;
	@Autowired
    private OwnerTaskAllService ownerTaskAllService;
	@Autowired
	private Env env;
	
	@RequestMapping("/zhengxinSearch")
	  public String toExecuteCaseSearch(Model model){
		     model.addAttribute("env",env.getEnv());
		     return "/modules/zhengxin/zhengxinSearch";
	  }
	
	
	/**
	   * 11315企业征信
	   */
	@RequestMapping(value="/getSearchResult",method=RequestMethod.GET)
	public @ResponseBody List<ZhengxinJson> execute(@RequestParam("companyName") String companyName,boolean isDebug, String logback){
		
		List<ZhengxinJson> results=null;
		 try {
			  
			  log.info("11315企业征信companyName:{}",URLDecoder.decode(companyName,"utf-8"));
			  
			 String url = "http://www.11315.com/newSearch?regionMc="
			 +URLEncoder.encode("选择地区","utf-8")+"&searchType=1&regionDm=&searchTypeHead=1&name="
			 +companyName+"@"+URLDecoder.decode(companyName,"utf-8"); 
			  
			  log.info("11315企业征信url:{}",url);
			  
			  Result<List<ZhengxinJson>> result = qiyezhengxinApiService.search(url,isDebug,logback);         
			  results=result.getData();
			  
			  Gson gson = new Gson();  		
		      String resultsJson = gson.toJson(results);
		      log.info("11315企业征信查询搜索结果:{}", resultsJson);
		      System.err.println("resultsJson="+resultsJson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return results;
		
	}
	
	 @RequestMapping(value="/zhengxinDetailByInterface",method=RequestMethod.POST)
     public String toRenFaWangDetailByInterface(@RequestParam("renfawangDetail") String renfawangDetail,Model model){
 		
 		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
 		   List<ZhengxinJson> pcs=gson.fromJson(renfawangDetail,new TypeToken<ArrayList<ZhengxinJson>>(){}.getType());
 		   model.addAttribute("peopleCourts",pcs);
 		  model.addAttribute("nfsNginxServer",Config.getNginxServer() + "/11315/");
 		   return "/modules/zhengxin/zhengxinDetail";
 	   }
	 
	 
	 //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method=RequestMethod.POST)
	   public @ResponseBody TimeTaskSearchResult<List<Zhengxin>>  checkIsExist(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<Zhengxin>> result=new TimeTaskSearchResult<List<Zhengxin>>();
		       
		          log.info("11315企业征信核查：queryType:"+queryType+"  content:"+content);
		          List<ZhengxinKeyword> coiList=qiyezhengxinApiService.checkIsExistBYName(content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<Zhengxin> pcList=qiyezhengxinApiService.getDetailByCOI(idList);
		    	   
		    	           if(pcList.size()>0){
		    	    	         result.setState(1);
		    	    	         result.setTtSearchResult(pcList);
		    	           }else{
		    	    	          if(coiList.get(0).getState()==7){
		    	    		            result.setState(coiList.get(0).getState());
		    	    	          }else{
		    	    		            result.setState(0);
		    	    	          }
		    	           }
		          }else{
		    	            result.setExistCode(0);
		    	            result.setState(0);
		         }
		      
		         return result;
	   }
	   
	   
	 //创建新任务
	    @RequestMapping(value="/zhengxinJoinTask",method=RequestMethod.POST)
	    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
	    		                                      @RequestParam("content") String content,
	    		                                      @RequestParam("date") Date date,
	    		                                      @RequestParam("loginName") String loginName,
	    		                                      @RequestParam("existCode") Integer existCode,
	    		                                      @RequestParam("timetaskID") Long timetaskID){
	    	
	    	   log.info("创建11315企业征信网任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
	    	
	    	   long timetask_id=0;
	    	   int i=0;
	    	   //重复新任务，返回
	    	   for(OwnerTaskEnterpCredit pcExist:enterpCreditOTService.getAllPeopleCourtTask(loginName)){
	    		      if(pcExist.getKeyWord()!=null && pcExist.getKeyWord().equals(content)){
	    		    	     return returnPeopleCourtResult(type,content,2);
	    		      }
	    	   }
	    	   
	    	   //创建定时任务
	    	   if(existCode==0){ //定时任务表中无该企业,写入company表中
	    		   
	    		   ZhengxinKeyword zhengxinKeyword=new ZhengxinKeyword();
	    		   zhengxinKeyword.setCompany(content);
	    		   zhengxinKeyword.setState(0);
	    		   zhengxinKeyword.setPriority(1);
	    		   zhengxinKeyword.setNum(0);
	    		   zhengxinKeyword.setTotalNum(0);
	    		   
	    		   ZhengxinKeyword zhengxinKeyword2= qiyezhengxinApiService.saveTimeTask(zhengxinKeyword);
	    		   if(zhengxinKeyword2!=null){
			    	      timetask_id=zhengxinKeyword2.getId();
			       }else{
			    	      return returnPeopleCourtResult(type,content,0);
			       }
		    	   
		      }else{
		    	    ZhengxinKeyword zhengxinKeyword2=qiyezhengxinApiService.getTimeTask(timetaskID);
		    	    zhengxinKeyword2.setPriority(1);
		    	    zhengxinKeyword2.setNum(0);
		    	    if(qiyezhengxinApiService.saveTimeTask(zhengxinKeyword2)==null){
		    	    	 return returnPeopleCourtResult(type,content,0);
		    	    }
		    	    
		    	    timetask_id=timetaskID;
		    	   
		      }
	    	
	    	 //创建自身任务,OwnerTask_zjCourt表
	    	   OwnerTaskEnterpCredit pc=new OwnerTaskEnterpCredit();
	    	   pc.setKeyWord(content);
	    	   pc.setLoginName(loginName);
	    	   pc.setState(0);
	    	   pc.setTimeTaskId(timetask_id);
	    	   pc.setCreateTaskDate(date);
	    	
	    	   OwnerTaskEnterpCredit rePc=enterpCreditOTService.joinTask(pc);
	    	   if(rePc==null){
		    	    return returnPeopleCourtResult(type,content,0);
		       }
	    	
	    	   
	    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "11315企业征信");
	    	   if(reTaskAll==null){
	    		   return returnPeopleCourtResult(type,content,0);
	    	   }
			 
	    	   
	    	   return returnPeopleCourtResult(type,content,1);
	    	 
	    }
	    
	    //创建任务,返回结果
		   public OwnerTaskResult returnPeopleCourtResult(String queryType,String content,long ownerTaskCode){
			   
			      OwnerTaskResult result=new OwnerTaskResult();
			   
			      if(ownerTaskCode==0){
			    	   result.setOwnerTaskCode(0);
		               result.setMessage("11315企业征信创建新任务："+queryType+"--"+content+" 失败");
			      }else if(ownerTaskCode==1){
		    	       result.setOwnerTaskCode(1);
		    	       result.setMessage("11315企业征信创建新任务："+queryType+"--"+content+" 成功");
		          }else if(ownerTaskCode==2){
		        	   result.setOwnerTaskCode(2);
		    	       result.setMessage("11315企业征信新任务："+queryType+"--"+content+" 已存在");
		          }
			       
			     return result;
			   
		   }
		   
		   //调试时用
		   	@RequestMapping(value="/zhengxinJson",method=RequestMethod.POST)
		       public String toRenFaWangJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
		   		   
		   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   		   
		   		   Result<List<ZhengxinJson>> result=new Result<List<ZhengxinJson>>();
		   		   
		   		   if(error.equals("eNull")){
		   			   
		   			   result.setData((List<ZhengxinJson>)gson.fromJson(data,new TypeToken<List<ZhengxinJson>>(){}.getType()));
		   			   
		   		   }else if(data.equals("dNull")){
		   			    
		   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
		   		   }
		   		  
		   		   model.addAttribute("zhengxinDetail",gson.toJson(result));
		   		   
		   		   return "/modules/zhengxin/zhengxinJson";
		   	}
	
}
