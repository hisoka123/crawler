package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.ownerTask.ZjCourtOTService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.google.gson.Gson;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskZjCourt;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;


@Controller
@RequestMapping("/modules/ajaxZjsfgkwExecuteCaseSearch")
public class AjaxZjsfgkwExecuteCaseSearchController {

	private static final Logger log = LoggerFactory.getLogger(AjaxZjsfgkwExecuteCaseSearchController.class);
	
	@Autowired
	private ZjsfgkwExecuteCaseSearchService zjsfgkwExecuteCaseSearchService;
	
	@Autowired
	private ZjCourtOTService zjCourtOTService;
	@Autowired
    private OwnerTaskAllService ownerTaskAllService;
	
	
	
	/**
	   * 执行案件信息查询
	   * @param aH_BH 案号,@param  dSR 当事人
	   */
	@RequestMapping(value="/getExecuteCaseSearchResults",method=RequestMethod.GET)
	public @ResponseBody List<ExecuteCaseSearchJson> getSearchResults(@RequestParam("dSR") String dSR,@RequestParam("aH_BH") String aH_BH,@RequestParam("aH_NH") String aH_NH){
		
		 List<ExecuteCaseSearchJson> executeCaseSearchJsonList=null;
		 try {
			  
			  log.info("执行案件信息查询aH_BH:{}",URLDecoder.decode(aH_BH,"utf-8"));

			  String url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?dSR="+URLDecoder.decode(dSR,"utf-8")+"&aH_BH="+URLDecoder.decode(aH_BH,"utf-8")+"&aH_NH="+URLDecoder.decode(aH_NH,"utf-8"); 
			  log.info("执行案件信息查询url:{}",url);
			  
			  Result<List<ExecuteCaseSearchJson>> result = zjsfgkwExecuteCaseSearchService.searchExecuteCase(url,false,"");          
			  executeCaseSearchJsonList=result.getData();
	          
	          Gson gson = new Gson();  		
		      String executeCaseSearchJson = gson.toJson(executeCaseSearchJsonList);
		      log.info("执行案件信息查询搜索结果:{}", executeCaseSearchJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executeCaseSearchJsonList;
		
	}
	
	
	
	 //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method=RequestMethod.POST)
	   public @ResponseBody TimeTaskSearchResult<List<Zjsfgkw>>  checkIsExist(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<Zjsfgkw>> result=new TimeTaskSearchResult<List<Zjsfgkw>>();
		       
		          log.info("浙法网核查：queryType:"+queryType+"  content:"+content);
		          List<ZjsfgkwKeyword> coiList=zjsfgkwExecuteCaseSearchService.checkIsExistBYName(queryType, content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<Zjsfgkw> pcList=zjsfgkwExecuteCaseSearchService.getDetailByCOI(idList);
		    	   
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
	   
	   
	   //核查名称、号码
	   @RequestMapping(value="/checkIsExist2",method=RequestMethod.POST)
	   public @ResponseBody TimeTaskSearchResult<List<Zjsfgkw>>  checkIsExist2(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<Zjsfgkw>> result=new TimeTaskSearchResult<List<Zjsfgkw>>();
		       
		          log.info("浙法网核查：queryType:"+queryType+"  content:"+content);
		          List<ZjsfgkwKeyword> coiList=zjsfgkwExecuteCaseSearchService.checkIsExistBYName2(queryType, content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<Zjsfgkw> pcList=zjsfgkwExecuteCaseSearchService.getDetailByCOI(idList);
		    	   
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
	    @RequestMapping(value="/zjsfgkwJoinTask",method=RequestMethod.POST)
	    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
	    		                                      @RequestParam("content") String content,
	    		                                      @RequestParam("date") Date date,
	    		                                      @RequestParam("loginName") String loginName,
	    		                                      @RequestParam("existCode") Integer existCode,
	    		                                      @RequestParam("timetaskID") Long timetaskID){
	    	
	    	   log.info("创建浙法网任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
	    	
	    	   long timetask_id=0;
	    	   int i=0;
	    	   List<String> typeList=new ArrayList<String>();
	    	   if(type.equals("姓名")){
	    		   i=6;
	    		   typeList.add("执行案件信息查询,1-2");
	    		   typeList.add("曝光台-个人未履行生效裁判信息,2-1-1");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-1");
	    		   typeList.add("执行惩戒-限制高消费,3-1-1");
	    		   typeList.add("执行惩戒-限制出境,3-2-1");
	    		   typeList.add("执行惩戒-限制招投标,3-3-1");
	 		}else if(type.equals("证件号码")){
	 			i=5;
	 			   typeList.add("曝光台-个人未履行生效裁判信息,2-1-2");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-2");
	    		   typeList.add("执行惩戒-限制高消费,3-1-2");
	    		   typeList.add("执行惩戒-限制出境,3-2-2");
	    		   typeList.add("执行惩戒-限制招投标,3-3-2");
	 	     }else if(type.equals("案号")){
	 	    	 i=6;
	 	    	   typeList.add("执行案件信息查询,1-1");
	 	       	   typeList.add("曝光台-个人未履行生效裁判信息,2-1-3");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-3");
	    		   typeList.add("执行惩戒-限制高消费,3-1-3");
	    		   typeList.add("执行惩戒-限制出境,3-2-3");
	    		   typeList.add("执行惩戒-限制招投标,3-3-3");
	 	    }else if(type.equals("执行法院")){
	 	    	i=5;
	 	    	typeList.add("曝光台-个人未履行生效裁判信息,2-1-4");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-4");
	    		   typeList.add("执行惩戒-限制高消费,3-1-4");
	    		   typeList.add("执行惩戒-限制出境,3-2-4");
	    		   typeList.add("执行惩戒-限制招投标,3-3-4");
	 	     }else if(type.equals("立案开始日期")){
	 	    	i=5;
	 	    	typeList.add("曝光台-个人未履行生效裁判信息,2-1-5");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-5");
	    		   typeList.add("执行惩戒-限制高消费,3-1-5");
	    		   typeList.add("执行惩戒-限制出境,3-2-5");
	    		   typeList.add("执行惩戒-限制招投标,3-3-5");
	 	     }else if(type.equals("立案结束日期")){
	 	    	i=5;
	 	    	typeList.add("曝光台-个人未履行生效裁判信息,2-1-6");
	    		   typeList.add("曝光台-单位未履行生效裁判信息,2-2-6");
	    		   typeList.add("执行惩戒-限制高消费,3-1-6");
	    		   typeList.add("执行惩戒-限制出境,3-2-6");
	    		   typeList.add("执行惩戒-限制招投标,3-3-6");
	 	     }
	    	   
	    	   
	    	   //重复新任务，返回
	    	   for(OwnerTaskZjCourt pcExist:zjCourtOTService.getAllPeopleCourtTask(loginName)){
	    		      if(pcExist.getSearchType().equals(type)&& pcExist.getKeyWord().equals(content)){
	    		    	     return returnPeopleCourtResult(type,content,2);
	    		      }
	    	   }
	    	   
	    	   //创建定时任务
	    	   if(existCode==0){ //定时任务表中无该企业,写入company表中
	    		   
	    		   for(int j=0;j<i;j++){
	    		   ZjsfgkwKeyword zjsfgkwKeyword=new ZjsfgkwKeyword();
	    		   zjsfgkwKeyword.setPropertyType(type);
	    		   zjsfgkwKeyword.setValue(content);
	    		   zjsfgkwKeyword.setState(0);
	    		   zjsfgkwKeyword.setPriority(1);
	    		   zjsfgkwKeyword.setNum(0);
	    		   zjsfgkwKeyword.setTotalNum(0);
	    		   if(typeList.get(j)!=null){
	    			   zjsfgkwKeyword.setType(typeList.get(j).split(",")[1]);
	    		   }
	    		  
	    		   ZjsfgkwKeyword zjsfgkwKeyword2= zjsfgkwExecuteCaseSearchService.saveTimeTask(zjsfgkwKeyword);
	    		   if(zjsfgkwKeyword2!=null){
			    	      timetask_id=zjsfgkwKeyword2.getId();
			       }else{
			    	      return returnPeopleCourtResult(type,content,0);
			       }
	    		   
	    		   //创建自身任务,OwnerTask_zjCourt表
		    	   OwnerTaskZjCourt pc=new OwnerTaskZjCourt();
		    	   pc.setSearchType(type);
		    	   pc.setKeyWord(content);
		    	   pc.setLoginName(loginName);
		    	   pc.setState(0);
		    	   pc.setTimeTaskId(timetask_id);
		    	   pc.setCreateTaskDate(date);
		    	   if(typeList.get(j)!=null){
		    		   pc.setType(typeList.get(j).split(",")[0]);
		    		   pc.setDetailType(typeList.get(j).split(",")[1]);
	    		   } 
		    	
		    	   OwnerTaskZjCourt rePc=zjCourtOTService.joinTask(pc);
		    	   if(rePc==null){
			    	    return returnPeopleCourtResult(type,content,0);
			       }
		    	
		    	   
		    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "浙法网");
		    	   if(reTaskAll==null){
		    		   return returnPeopleCourtResult(type,content,0);
		    	   }
	    		   }
		    	   
		    	   return returnPeopleCourtResult(type,content,1);
		    	   
		    	   
		      }else{
		    	     ZjsfgkwKeyword  coi2=zjsfgkwExecuteCaseSearchService.getTimeTask(timetaskID);
		    	     coi2.setPriority(1);
		    	     coi2.setNum(0);
		    	    if(zjsfgkwExecuteCaseSearchService.saveTimeTask(coi2)==null){
		    	    	 return returnPeopleCourtResult(type,content,0);
		    	    }
		    	    
		    	    timetask_id=timetaskID;
		    	    
		    	    //创建自身任务,OwnerTask_zjCourt表
			    	   OwnerTaskZjCourt pc=new OwnerTaskZjCourt();
			    	   pc.setSearchType(type);
			    	   pc.setKeyWord(content);
			    	   pc.setLoginName(loginName);
			    	   pc.setState(0);
			    	   pc.setTimeTaskId(timetask_id);
			    	   pc.setCreateTaskDate(date);
			    	
			    	   OwnerTaskZjCourt rePc=zjCourtOTService.joinTask(pc);
			    	   if(rePc==null){
				    	    return returnPeopleCourtResult(type,content,0);
				       }
			    	
			    	   
			    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "浙法网");
			    	   if(reTaskAll==null){
			    		   return returnPeopleCourtResult(type,content,0);
			    	   }
			    	   
			    	   
			    	   return returnPeopleCourtResult(type,content,1);
		      }
	    	
	    	 
	    }
	    
	    //创建任务,返回结果
		   public OwnerTaskResult returnPeopleCourtResult(String queryType,String content,long ownerTaskCode){
			   
			      OwnerTaskResult result=new OwnerTaskResult();
			   
			      if(ownerTaskCode==0){
			    	   result.setOwnerTaskCode(0);
		               result.setMessage("浙法网创建新任务："+queryType+"--"+content+" 失败");
			      }else if(ownerTaskCode==1){
		    	       result.setOwnerTaskCode(1);
		    	       result.setMessage("浙法网创建新任务："+queryType+"--"+content+" 成功");
		          }else if(ownerTaskCode==2){
		        	   result.setOwnerTaskCode(2);
		    	       result.setMessage("浙法网新任务："+queryType+"--"+content+" 已存在");
		          }
			       
			     return result;
			   
		   }
}
