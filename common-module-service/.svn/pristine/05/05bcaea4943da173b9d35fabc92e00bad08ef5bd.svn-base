package com.crawlermanage.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.doc.Site;
import com.module.dao.entity.doc.WikiResult;
import com.module.dao.repository.doc.SiteRepository;

@Component("siteManageService")
public class SiteManageService {

	@Autowired
	private SiteRepository siteRepository;
	
	
	
	
	//全部site
	public List<Site>  getAllSite(){
		   return siteRepository.findAll();
	}
	
	
	//保存新增站点
	public boolean saveNewSite(Site site){
		
		   Site returnSite=siteRepository.save(site);
		   if(site.getName().equals(returnSite.getName())){
			       return true;
		   }else{
			       return false;
		   }
	} 
}
