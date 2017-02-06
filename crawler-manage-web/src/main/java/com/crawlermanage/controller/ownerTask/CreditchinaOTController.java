package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.service.ownerTask.CreditchinaOTService;
import com.module.dao.entity.ownerTask.OwnerTaskCreditchina;
import com.module.dao.entity.ownerTask.OwnerTaskDishonesty;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信用中国
 */
@Controller
@RequestMapping("/ownerTask")
public class CreditchinaOTController {
    private static final Logger log = Logger.getLogger(CreditchinaOTController.class);

    @Autowired
    private CreditchinaOTService creditchinaOTService;

    /**
     * 页面跳转（信用中国）
     * @return
     */
    @RequestMapping("/creditchina")
    public String toCreditchina(){
        return "/ownerTask/creditchina";
    }

    /**
     * 根据条件查询信用中国任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchCreditchinaTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskCreditchina> searchCreditchinaTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchCreditchinaTask==========");

        if (searchType.equals("searchType_name")) { //自然人
            searchType = "1";
        } else if (searchType.equals("searchType_legalPerson")) { //法人
            searchType = "2";
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

        Page<OwnerTaskCreditchina> ownerTaskCreditchinaPage = creditchinaOTService.searchCreditchina(paramMap, currentPage, pageSize, null);
        List<OwnerTaskCreditchina> resultList = ownerTaskCreditchinaPage.getContent();
//        System.out.println("====searchCreditchina==" + resultList + "===total:" + ownerTaskCreditchinaPage.getTotalElements());

        return ownerTaskCreditchinaPage;
    }
}
