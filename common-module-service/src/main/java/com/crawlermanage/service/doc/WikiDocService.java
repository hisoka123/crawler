/**
 * 
 */
package com.crawlermanage.service.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.module.dao.entity.doc.Site;
import com.module.dao.entity.doc.WikiDocBase;
import com.module.dao.entity.doc.WikiDocContent;
import com.module.dao.repository.doc.SiteRepository;
import com.module.dao.repository.doc.WikiDocBaseRepository;
import com.module.dao.repository.doc.WikiDocContentRepository;


/**
 * @author kingly
 * @date 2015年12月24日
 * 
 */
@Component("wikiDocService")
@Transactional
public class WikiDocService {
	@Autowired
	private WikiDocBaseRepository wikiDocBaseRepository;
	@Autowired
	private WikiDocContentRepository wikiDocContentRepository;
	@Autowired
	private SiteRepository siteRepository;
	
	//@Cacheable(value="dataCache", key="'WikiDocService.getWikiDocTree_'")
	/*public WikiDocBase getWikiDocTree() {
		WikiDocBase docBase = wikiDocBaseRepository.findById(1L);
		Set<WikiDocBase> children = docBase.getChildren();
		System.out.println("*********************************************");
		System.out.println(children);
		return wikiDocBaseRepository.findById(1L);
	}*/

	/*public WikiDocBase saveWikiDocBase(WikiDocBase wikiDocBase) {
		return wikiDocBaseRepository.save(wikiDocBase);
	}*/
	
	
	/*==================================================================*/
	//@Cacheable(value="dataCache", key="'WikiDocService.getWikiContentById_' + #id")
	/*public WikiDocContent getWikiContentById(Long id) {
		return wikiDocContentRepository.findById(id);
	}*/

	//新增或更新文档
	public boolean saveORUpdateWikiDocContent(WikiDocContent wikiDocContent) {
		   WikiDocContent returnWiki=new WikiDocContent();
		   
		   returnWiki=wikiDocContentRepository.save(wikiDocContent);
		   if(wikiDocContent.getContent().equals(returnWiki.getContent())){
			     return true;
		   }else{
			     return false;
		   }
		   
	}
	
	public List<Site> getSite(Integer isEnabled){
		   return siteRepository.findByIsEnabled(isEnabled);
	}
	
	public Site getSingleSite(Long id){
		   return siteRepository.findById(id);
	}
	
	public List<WikiDocBase> getSecondNodes(Long site_id) {
		return wikiDocBaseRepository.findBySite_id(site_id);
	}
	
	public WikiDocContent getInterfaceDoc(Long wdb_id){
		  return wikiDocContentRepository.findByWdb_id(wdb_id);
	}
	
	public WikiDocBase getSingleWikiDocBase(Long id){
		  return wikiDocBaseRepository.findById(id);
	}
	
	//删除接口文档
	public boolean deleteApiContent(Long contentID){
		   
		   wikiDocContentRepository.delete(contentID);
		   if(wikiDocContentRepository.exists(contentID)){
			      return false;
		   }else{
			      return true;
		   }
	}
	
	//以站点名查询
	public List<WikiDocContent> getInterfaceDocBySite(String site){

		return wikiDocContentRepository.findBySite(site);
	
	}
	
	//以站点类型查询全部站点
	public List<Site> getSitesByType(String type){
		   return siteRepository.findByType(type);
	}
	
}
