package com.crawlermanage.service.doc;

import com.module.dao.entity.doc.Site;
import com.module.dao.repository.doc.SiteClassify;
import com.module.dao.repository.doc.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * Created by zxz on 2016/1/7.
 */
@Component("siteDaoService")
@Transactional
public class SiteService {
    @Autowired
    SiteDao siteDao;

    public List<SiteClassify> getAllInUseAssembledByClassify(){
        List<SiteClassify> siteClassifyList = new ArrayList<SiteClassify>();
        List<Site> siteList = null;
        siteList = siteDao.findAll();
        Iterator<Site> siteSingles = siteList.iterator();

        while(siteSingles.hasNext()){
            Site siteSingle = siteSingles.next();
            if(1 != siteSingle.getIsEnabled()){
                siteSingles.remove();
            }else{
                if(!isHaveClassify(siteClassifyList,siteSingle)){
                    SiteClassify siteClassify =  new SiteClassify(siteSingle.getType());
                    siteClassifyList.add(siteClassify);
                }
            }
        }

        for(Site siteSingle:siteList){
            SiteClassify siteClassify = getSiteClassifyByClassifiedName(siteClassifyList,siteSingle.getType());
            List<Site> sitesList = siteClassify.getSiteList();
            sitesList.add(siteSingle);
        }

        return siteClassifyList;
    }

    public List<Site> getAllInUse(){
        List<Site> siteList = null;
        siteList = siteDao.findAll();
        for(Site siteSingle:siteList){
            if(1 != siteSingle.getIsEnabled()){
                siteList.remove(siteSingle);
            }
        }
        return siteList;
    }

    public List<Site> getAll(){
        List<Site> siteList = null;
        siteList = siteDao.findAll();
        return siteList;
    }

    private boolean isHaveClassify(List<SiteClassify> siteClassifyList,Site site){
        boolean isHaveClassify = false;
        for(SiteClassify siteClassify:siteClassifyList){
            if(site.getType().equals(siteClassify.getClassifiedName())){
                isHaveClassify = true;
            }
        }
        return isHaveClassify;
    }

    public SiteClassify getSiteClassifyByClassifiedName(List<SiteClassify> siteClassifyList,String classifiedName){
        for(SiteClassify siteClassify:siteClassifyList){
            if(classifiedName.equals(siteClassify.getClassifiedName())){
                return siteClassify;
            }
        }
        return null;
    }

    private void doClassiy(List<SiteClassify> siteClassifyList,Site site){
        boolean isHasClassify = false;
        for(SiteClassify siteClassify:siteClassifyList){
        }

    }
}
