package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.iautos.IautosDBService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.ownerTask.SipoOTService;
import com.crawlermanage.service.sipo.SipoDBService;
import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskIautos;
import com.module.dao.entity.ownerTask.OwnerTaskSipo;
import com.module.dao.entity.sipo.SipoKeyword;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/ownerTask")
public class SipoOTController {
    private static final Logger log = Logger.getLogger(SipoOTController.class);

    @Autowired
    private SipoOTService sipoOTService;
    
    @Autowired
    private SipoDBService sipoDBService; 
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (专利网)
     * @return
     */
    @RequestMapping("/sipotask")
    public String toSipoTask(){
        return "/ownerTask/sipotask";
    }

    /**
     * 根据条件查询专利网任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchSipoTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskSipo> searchSipoTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchSipoTask==========");

        if (searchType.equals("searchType_fmsq")) {
            searchType = "发明授权";
        } else if (searchType.equals("searchType_fmgb")) {
            searchType = "发明公布";
        } else if (searchType.equals("searchType_xxsq")) {
            searchType = "实用新型";
        } else if (searchType.equals("searchType_wgsq")) {
            searchType = "外观设计";
        } else if (searchType.equals("searchType_all")){
            searchType = "";
        }
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
        paramMap.put("searchType",searchType);
        paramMap.put("status",status);
        paramMap.put("stateList",stateList);
        paramMap.put("keyWord",keyWord);

        System.out.println("====param== loginName: " + loginName + ",searchType: " + searchType + ",stateList: " + stateList + ", keyWord: " + keyWord);

        Page<OwnerTaskSipo> ownerTaskSipoPage = sipoOTService.searchSipo(paramMap, currentPage, pageSize, null);
        List<OwnerTaskSipo> resultList = ownerTaskSipoPage.getContent();
        System.out.println("====searchSipo==" + resultList + "===total:" + ownerTaskSipoPage.getTotalElements());

        return ownerTaskSipoPage;
    }
    
    
    //创建新任务
@RequestMapping(value="/sipoJoinTask",method=RequestMethod.POST)
public @ResponseBody OwnerTaskResult iautosJoinTask(@RequestParam("type") String type,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建专利网任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   
    	   //类型转换
    	   String searchType="";
    	   
    	     if ("fmsq".equals(type)) {
    	    	 searchType="发明授权";
			}else if ("fmgb".equals(type)) {
				searchType="发明公布";	
			}else if("xxsq".equals(type)){
				searchType="实用新型";			
			}else if ("wgsq".equals(type)) {	
				searchType="外观设计";	
			}
	    	   
    	   //重复新任务，返回
    	   for(OwnerTaskSipo pcExist:sipoOTService.getAllIautosTask(loginName)){    		   
    		   if ((pcExist.getSearchType() !=null && pcExist.getSearchType().length() != 0) && (pcExist.getKeyWord() !=null && pcExist.getKeyWord().length()!=0)) {
    			   if(pcExist.getSearchType().equals(searchType) && pcExist.getKeyWord().equals(content)){  		    	 
  		    	     return returnSiposResult(type,content,2);
  		      }				
			}   		      
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该查询关键字,写入表中
    		    		   
    		   SipoKeyword coi=new SipoKeyword();
		       coi.setType(type);
		       coi.setName(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       coi.setTotalNum(0);
		       SipoKeyword reCoi=sipoDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnSiposResult(type,content,0);
		       }
	    	  
	      }else{
	    	  SipoKeyword  coi2=sipoDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(sipoDBService.saveTimeTask(coi2)==null){
	    	    	 return returnSiposResult(type,content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_iautos表
    	   OwnerTaskSipo pc=new OwnerTaskSipo();
    	
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTimeTaskId(timetask_id);
    	   pc.setCreateTaskDate(date);
    	   pc.setSearchType(searchType);
	
    	   OwnerTaskSipo rePc=sipoOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnSiposResult(type,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "专利网");
    	   if(reTaskAll==null){
    		   return returnSiposResult(type,content,0);
    	   }
    	   
    	   
    	   return returnSiposResult(type,content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnSiposResult(String type,String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      String searchType="";
		     if ("fmsq".equals(type)) {
		    	  searchType="发明授权";
			}else if ("fmgb".equals(type)) {
				searchType="发明公布";
			}else if("xxsq".equals(type)){
				searchType="实用新型";
			}else if ("wgsq".equals(type)) {
				searchType="外观设计";
			}
		      
		      
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("专利网创建新任务："+searchType+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("专利网创建新任务："+searchType+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("专利网新任务："+searchType+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
    
    
    
}
