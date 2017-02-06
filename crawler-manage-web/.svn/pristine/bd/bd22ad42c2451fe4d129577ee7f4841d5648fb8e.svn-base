package com.crawlermanage.controller.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.service.manage.SiteManageService;
import com.module.dao.entity.doc.Site;
import com.module.dao.entity.doc.WikiResult;

@Controller
@RequestMapping("/manage")
public class AjaxManageController {

	   @Autowired
	   private SiteManageService siteManageService;
	
	
	
	  //获取站点类型
	  @RequestMapping("/getSiteType")
	  public @ResponseBody List<Site> getSiteType(){
		     return siteManageService.getAllSite();
	  }
	  
	  
	  //保存新站点
	  @RequestMapping(value="/addNewSite",method=RequestMethod.POST)
	  public @ResponseBody WikiResult addNewSite(@RequestParam(value="siteID",required=false) Long siteID, Site site){
		  
		     String sucessMes="";
		     String failedMes="";
		  
		     if(siteID==null){
		    	   sucessMes="保存成功!";
		    	   failedMes="保存失败!"; 
		     }else{
		    	   site.setId(siteID);
		    	   sucessMes="更新成功!";
		    	   failedMes="更新失败!";
		     }
		  
		     WikiResult wikiResult=new WikiResult();
		     if(siteManageService.saveNewSite(site)){
		    	     wikiResult.setCode(1);
		    	     wikiResult.setMessage(sucessMes);
		     }else{
		    	     wikiResult.setCode(0);
		    	     wikiResult.setMessage(failedMes);
		     }
		      
		     return wikiResult;
		  
	  }
	
}
