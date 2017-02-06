package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.service.ownerTask.EnterpCreditOTService;
import com.module.dao.entity.ownerTask.OwnerTaskEnterpCredit;
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
 * 11315企业征信
 */
@Controller
@RequestMapping("/ownerTask")
public class EnterpCreditOTController {
    private static final Logger log = Logger.getLogger(EnterpCreditOTController.class);

    @Autowired
    private EnterpCreditOTService enterpCreditOTService;

    /**
     * 页面跳转 (11315企业征信)
     * @return
     */
    @RequestMapping("/enterpCredit")
    public String toEnterpCreditTask(){
        return "/ownerTask/enterpCredit";
    }

    /**
     * 根据条件查询11315企业征信任务
     *
     * @param loginName
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchEnterpCreditTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskEnterpCredit> searchEnterpCreditTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                       @RequestParam("loginName") String loginName,@RequestParam("status") String status,
                                       @RequestParam("keyWord") String keyWord) {
        log.info("=========searchEnterpCreditTask==========");

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

        Page<OwnerTaskEnterpCredit> ownerTaskEnterpCreditPage = enterpCreditOTService.searchEnterpCreditOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskEnterpCredit> resultList = ownerTaskEnterpCreditPage.getContent();
        System.out.println("====searchEnterpCredit==" + resultList + "===total:" + ownerTaskEnterpCreditPage.getTotalElements());

        return ownerTaskEnterpCreditPage;
    }
}
