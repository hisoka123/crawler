package com.crawlermanage.service.pbccrc;


import com.crawler.domain.json.Result;
import com.crawler.pbccrc.domain.json.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class PbccrcPersistTask implements Runnable {

    private PbccrcService pbccrcService;
    private String cookies;
    private String tradecode;
    private Result<ReportData> reportResult;
    private PbcCreditReportFeed pbcCreditReportFeed;
    private static final Logger LOGGER = LoggerFactory.getLogger(PbccrcPersistTask.class);
    
    public PbccrcPersistTask(PbccrcService pbccrcService, String cookies, String tradecode,
                             Result<ReportData> reportResult, PbcCreditReportFeed pbcCreditReportFeed) {
        this.pbccrcService = pbccrcService;
        this.cookies = cookies;
        this.tradecode = tradecode;
        this.reportResult = reportResult;
        this.pbcCreditReportFeed = pbcCreditReportFeed;
    }

    @Override
    public void run() {
        LOGGER.info("====================================PbccrcPersistTask Thread start！================================================");
        LOGGER.info("cookies---------"+cookies);
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
        Set<Cookie> cookieSet = gson.fromJson(cookies, type);
        Iterator<Cookie> it = cookieSet.iterator();
       
        String tsf75e5b = null;
        while (it.hasNext()){
            Cookie cookie = it.next();
            if ("TSf75e5b".equals(cookie.getName())) {
            	tsf75e5b = cookie.getValue();
                break;
            }
        }
        if (!StringUtils.isEmpty(tsf75e5b)) {
            pbccrcService.saveReport(tsf75e5b,tradecode,reportResult,pbcCreditReportFeed);
            LOGGER.info("====================================saveReport end！================================================");
        }

    }
}
