package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.service.ownerTask.ZjCourtOTService;
import com.module.dao.entity.ownerTask.OwnerTaskZjCourt;
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
 *  浙法网
 */
@Controller
@RequestMapping("/ownerTask")
public class ZjCourtOTController {
    private static final Logger log = Logger.getLogger(ZjCourtOTController.class);

    @Autowired
    private ZjCourtOTService zjCourtOTService;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/zjCourtTask")
    public String toZjCourtTask(){
        return "/ownerTask/zjCourtTask";
    }


    @RequestMapping(value="/searchZjCourtTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskZjCourt> searchZjCourtTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                             @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                             @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchZjCourtOTTask==========");

        if (searchType.equals("searchType_name")) {
            searchType = "姓名";
        } else if (searchType.equals("searchType_IDCard")) {
            searchType = "证件号码";
        } else if (searchType.equals("searchType_caseNum")) {
            searchType = "案号";
        } else if (searchType.equals("searchType_executionCourt")) {
            searchType = "执行法院";
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

        Page<OwnerTaskZjCourt> ownerTaskZjCourtOTPage = zjCourtOTService.searchZjCourtOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskZjCourt> resultList = ownerTaskZjCourtOTPage.getContent();
        System.out.println("====searchZjCourtOT==" + resultList + "===total:" + ownerTaskZjCourtOTPage.getTotalElements());

        return ownerTaskZjCourtOTPage;
    }
}
