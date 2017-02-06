package com.crawlermanage.controller.ownerTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.iautos.IautosDBService;
import com.crawlermanage.service.ownerTask.IautosOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskIautos;

/**
 * 第一车网
 */
@Controller
@RequestMapping("/ownerTask")
public class IautosOTController {
    private static final Logger log = Logger.getLogger(IautosOTController.class);

    @Autowired
    private IautosOTService iautosOTService;
    
    @Autowired
    private IautosDBService iautosDBService; 
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;
    /**
     * 页面跳转 (第一车网)
     * @return
     */
    @RequestMapping("/iautos")
    public String toIautos(){
        return "/ownerTask/iautos";
    }

    /**
     * 根据条件查询失信网任务
     *
     * @param loginName
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchIautosTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskIautos> searchIautosTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("status") String status,
                                                   @RequestParam("keyWord") String keyWord) {
        log.info("=========searchIautosTask==========");

        List<Integer> stateList=new ArrayList<Integer>();

        if(status.equals("searchStatus_success")){
            stateList.add(1);
            stateList.add(7);
        }else if(status.equals("searchStatus_waiting")){
            stateList.add(0);
        }else if(status.equals("searchStatus_inProcess")){
			stateList.add(-3);
		}else if (status.equals("searchStatus_feedback")) {
            stateList.add(1);
            stateList.add(7);
            stateList.add(0);
			stateList.add(-3);
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loginName",loginName);
        paramMap.put("status",status);
        paramMap.put("stateList",stateList);
        paramMap.put("keyWord",keyWord);

        System.out.println("====param== loginName: " + loginName + ",stateList: " + stateList + ", keyWord: " + keyWord);

        Page<OwnerTaskIautos> ownerTaskIautosPage = iautosOTService.searchIautosOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskIautos> resultList = ownerTaskIautosPage.getContent();
        System.out.println("====searchIautos==" + resultList + "===total:" + ownerTaskIautosPage.getTotalElements());

        return ownerTaskIautosPage;
    }
    
    
    /**
     * 查询第一车网任务,全部
     *
     * @param loginName
     * @return
     */
    @RequestMapping(value="/getAllIautosTask",method= RequestMethod.POST)
    public @ResponseBody
    List<OwnerTaskIautos> getAllIautosTask(@RequestParam("loginName") String loginName){
        log.info("=========getAllIautosTask==========");
        
        return iautosOTService.getAllIautosTask(loginName);
    }
    
    
    //创建新任务
@RequestMapping(value="/iautosJoinTask",method=RequestMethod.POST)
public @ResponseBody OwnerTaskResult iautosJoinTask(@RequestParam("city") String city,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建第一车网任务：city:"+city+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskIautos pcExist:iautosOTService.getAllIautosTask(loginName)){    		   
    		   if ((pcExist.getCity() !=null && pcExist.getCity().length() != 0) && (pcExist.getKeyWord() !=null && pcExist.getKeyWord().length()!=0)) {
    			   if(pcExist.getCity().equals(city) && pcExist.getKeyWord().equals(content)){  		    	 
  		    	     return returnIautosResult(city,content,2);
  		      }				
			}   		      
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该查询关键字,写入表中
    		    		   
    		   IautosKeyword coi=new IautosKeyword();
		       coi.setCity("quanguo");
		       coi.setName(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       coi.setTotalNum(0);
		       IautosKeyword reCoi=iautosDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnIautosResult(city,content,0);
		       }
	    	  
	      }else{
	    	  IautosKeyword  coi2=iautosDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(iautosDBService.saveTimeTask(coi2)==null){
	    	    	 return returnIautosResult(city,content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_iautos表
    	   OwnerTaskIautos pc=new OwnerTaskIautos();
    	   pc.setCity("quanguo");
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTasktimeid(timetask_id);
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskIautos rePc=iautosOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnIautosResult(city,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "第一车网");
    	   if(reTaskAll==null){
    		   return returnIautosResult(city,content,0);
    	   }
    	   
    	   
    	   return returnIautosResult(city,content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnIautosResult(String city,String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("第一车网创建新任务："+city+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("第一车网创建新任务："+city+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("第一车网新任务："+city+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
    
   
}
