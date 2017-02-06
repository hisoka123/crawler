package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.ownerTask.DishonestyOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.shixin.ShixinDBService;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskDishonesty;
import com.module.dao.entity.ownerTask.OwnerTaskPeopleCourt;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.shixin.ShixinKeyword;

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
 * 失信网
 */

@Controller
@RequestMapping("/ownerTask")
public class DishonestyController {
    private static final Logger log = Logger.getLogger(DishonestyController.class);

    @Autowired
    private DishonestyOTService dishonestyOTService;
    
    @Autowired
    ShixinDBService shixinDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (失信网)
     * @return
     */
    @RequestMapping("/dishonesty")
    public String toDishonesty(){
        return "/ownerTask/dishonesty";
    }

    /**
     * 根据条件查询失信网任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchDishonestyTask", method=RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskDishonesty> searchDishonestyTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchDishonestyTask==========");

        if (searchType.equals("searchType_name")) {
            searchType = "被执行人姓名/名称";
        } else if (searchType.equals("searchType_IDCard")) {
            searchType = "身份证号码/组织机构代码";
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

        Page<OwnerTaskDishonesty> ownerTaskDishonestyPage = dishonestyOTService.searchDishonesty(paramMap, currentPage, pageSize, null);
        List<OwnerTaskDishonesty> resultList = ownerTaskDishonestyPage.getContent();
//        System.out.println("====searchDishonesty==" + resultList + "===total:" + ownerTaskDishonestyPage.getTotalElements());

        return ownerTaskDishonestyPage;
    }
    
    
    
   //创建新任务
    @RequestMapping(value="/shixinJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
    		                                      @RequestParam("keyword") String keyword,
    		                                      @RequestParam("cardNum") String cardNum,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建失信网任务：type:"+type+"  keyword:"+keyword+"  cardNum"+cardNum+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   String queryType="";
    	   
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskDishonesty otd:dishonestyOTService.getRepositoryTask(loginName)){
    		   
    		       if("0".equals(type)){
    		    	       if("被执行人姓名/名称".equals(otd.getSearchType())&& keyword.equals(otd.getKeyWord())){
      		    	            return returnPeopleCourtResult(type,cardNum,keyword,2);
      		               }
    		       }else if("1".equals(type)){
    		    	      String keyword2=cardNum+","+keyword;
    		    	      if("身份证号码/组织机构代码".equals(otd.getSearchType()) &&  keyword2.equals(otd.getKeyWord())){
 		    	              return returnPeopleCourtResult(type,cardNum,keyword2,2);
 		                  }
    		       }
    		      
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,写入company表中
    		   
    		   ShixinKeyword sk=new ShixinKeyword();
    		   sk.setType(type);
    		   sk.setKeyword(keyword);
               sk.setNum(0);
    		   sk.setState(0);
		       sk.setPriority(1);
		       
		       if("1".equals(type)){
   			       sk.setCardNum(cardNum);
   		       }
		       
		       ShixinKeyword resk=shixinDBService.saveTimeTask(sk);
		       if(resk!=null){
		    	      timetask_id=resk.getId();
		       }else{
		    	      return returnPeopleCourtResult(type,cardNum,keyword,0);
		       }
	    	  
	      }else{
	    	      ShixinKeyword resk2=shixinDBService.getTimeTaskById(timetaskID);
	    	      resk2.setPriority(1);
	    	      resk2.setNum(0);
	    	    if(shixinDBService.saveTimeTask(resk2)==null){
	    	    	 return returnPeopleCourtResult(type,cardNum,keyword,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_dishonesty表
    	   OwnerTaskDishonesty otdy=new OwnerTaskDishonesty();
    	   otdy.setLoginName(loginName);
    	   otdy.setCreateTaskDate(date);
    	   otdy.setState(0);
    	   otdy.setTimeTaskId(timetask_id);
    	   
    	   if("0".equals(type)){
    		    otdy.setSearchType("被执行人姓名/名称");
    		    otdy.setKeyWord(keyword);
    	   }else if("1".equals(type)){
    		    otdy.setSearchType("身份证号码/组织机构代码"); 
    		    otdy.setKeyWord(cardNum+","+keyword);
    	   }
    	
    	  OwnerTaskDishonesty reotd=dishonestyOTService.joinTask(otdy);
    	   if(reotd==null){
	    	    return returnPeopleCourtResult(type,cardNum,keyword,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "失信网");
    	   if(reTaskAll==null){
    		   return returnPeopleCourtResult(type,cardNum,keyword,0);
    	   }
    	   
    	   
    	   return returnPeopleCourtResult(type,cardNum,keyword,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnPeopleCourtResult(String type,String cardNum,String keyword,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      String content="";
		      if("0".equals(type)){
		    	     content="被执行人姓名/名称---"+keyword;
		      }else if("1".equals(type)){
		    	     content="被执行人姓名/名称---"+keyword+","+"身份证号码/组织机构代码 ---"+cardNum;
		      }
		      
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("失信网网创建新任务："+content+" 失败!");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("失信网创建新任务："+content+" 成功!");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("失信网新任务："+content+" 已存在!");
	          }
		       
		     return result;
		   
	   }
    
    
    
}
