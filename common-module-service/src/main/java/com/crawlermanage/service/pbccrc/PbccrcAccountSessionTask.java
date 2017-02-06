package com.crawlermanage.service.pbccrc;


import com.crawler.htmlparser.EncryptAndDecryptUtil;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.pbccrc.PbccrcAccount;
import com.module.dao.entity.pbccrc.PlainPbccrcJson;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PbccrcAccountSessionTask {

    private String username;
    private String password;
    private String cookies;

    private PbccrcService pbccrcService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PbccrcAccountSessionTask.class);

    public PbccrcAccountSessionTask(String username, String password, String cookies,
                                     PbccrcService pbccrcService) {
        this.username = username;
        this.password = password;
        this.cookies = cookies;
        this.pbccrcService = pbccrcService;
    }

    public void saveAccountInfo() {
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
        Set<Cookie> cookieSet = gson.fromJson(cookies, type);
        Iterator<Cookie> it = cookieSet.iterator();
        String tsf75e5b = "";
        while (it.hasNext()){
            Cookie cookie = it.next();
            String name = cookie.getName();
            if ("TSf75e5b".equals(name)) {
            	tsf75e5b = cookie.getValue();
            	break;
            }
        }

        LOGGER.info("===================TSf75e5b======================" + tsf75e5b);
        
        PbccrcAccount pbccrcAccount = pbccrcService.findByUsername(username);
        if(null == pbccrcAccount){
            pbccrcAccount = new PbccrcAccount();
        }

        pbccrcAccount.setUsername(username);
        pbccrcAccount.setCreateTime(new Date());
        String salt = null;
        try {
            salt = EncryptAndDecryptUtil.encrypt(password, EncryptAndDecryptUtil.KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pbccrcAccount.setPassword(salt);

        PlainPbccrcJson plainPbccrcJson = new PlainPbccrcJson();
        plainPbccrcJson.setCreateTime(new Date());
        plainPbccrcJson.setPbccrcAccount(pbccrcAccount);
        plainPbccrcJson.setTsf75e5b(tsf75e5b);

        pbccrcService.saveSessionInfo(pbccrcAccount,plainPbccrcJson);

    }
}
