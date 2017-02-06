package com.crawlermanage.controller.doc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;











import redis.clients.jedis.JedisPubSub;

import com.crawlermanage.service.doc.ExportPDFService;
import com.crawlermanage.service.doc.SiteService;
import com.crawlermanage.service.doc.WikiDocService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.DocumentException;
import com.module.dao.entity.doc.Site;
import com.module.dao.entity.doc.WikiDocBase;
import com.module.dao.entity.doc.WikiDocContent;
import com.module.dao.entity.doc.WikiResult;
import com.module.dao.repository.doc.ExportPDFResult;

/**
 * @author kingly hmy
 * @date 2015年10月12日
 * 文档说明控制器
 * 
 */

@Controller
@RequestMapping("/tools")
public class WikiController {
	@Autowired
	private WikiDocService wikiDocService;
	
	
	@Autowired
	private ExportPDFService exportPDFService;
	

	
	private static final Logger log = LoggerFactory.getLogger(WikiController.class);
	
	
	
	
	@RequestMapping("/wiki")
    public String towiki(String docName, Model model) { //docName的命名方式举例：sina_userSearch.html、zhihu_userInfo.html，并与apidoc文件夹中配置文件名一致
		model.addAttribute("docName", docName);
   	 	return "tools/wiki";
    }
	
	/*@RequestMapping(value="/wiki/initdata", method=RequestMethod.POST)
	@ResponseBody
	public WikiDocBase wikiaddInitdata() {
		return wikiDocService.getWikiDocTree();
	}*/
	
	@RequestMapping(value="/wiki/edit", method=RequestMethod.GET)
	public String towikiAdd(Model model) {
   	 	return "tools/wikiadd";
    }
	
	@RequestMapping(value="/wiki/addaction", method=RequestMethod.POST)
	@ResponseBody
	public String wikiaddaction(WikiDocContent wikiDocContent) {
		//wikiDocContent = wikiDocService.saveWikiDocContent(wikiDocContent);
		if (wikiDocContent==null) {
			return "Error";
		}
		return "OK";
	}
	
	/*@RequestMapping(value="/wiki/doc", method=RequestMethod.GET)
	@ResponseBody
	public String wiki(@RequestParam("ajaxParam") String ajaxParam) throws Exception {
		ajaxParam = URLDecoder.decode(URLDecoder.decode(ajaxParam, "utf-8"), "utf-8");
		JsonObject root = new JsonParser().parse(ajaxParam).getAsJsonObject();
		String docName = root.get("docName").getAsString();
		String unitName = root.get("unitName").getAsString();
		LOGGER.info("unitName:{}, docName:{}", unitName, docName);
		String result = wikiDocService.getWikiDocByUnitNameAndDocName(unitName, docName).getDocContent();
		return result;
	}*/
	
	
	@RequestMapping("/getSite")
	public @ResponseBody List<Site> getSite(){
		  return wikiDocService.getSite(1);    
	}
	
	@RequestMapping(value="/getChildNodes", method=RequestMethod.POST)
	@ResponseBody
	public List<WikiDocBase> getChildrenNodes(@RequestParam(value="site_id",required=false) Long site_id,
			                                  @RequestParam(value="parent_id",required=false) Long parent_id,
			                                  @RequestParam("nodesGrade") String nodesGrade) {
		      
		      if(nodesGrade.equals("sonNodes")){
		    	      return wikiDocService.getSecondNodes(site_id);//获取子级菜单
		      }else if(nodesGrade.equals("otherNodes")){
		    	       return null;
		    	      //return wikiDocService.getGtSecondNodes(parent_id);//获取大于二级的菜单
		      }else{
		    	      return null;
		      }
	}
	
	//新增接口文档
	@RequestMapping(value="/addInterfaceDoc",method=RequestMethod.POST)
	public @ResponseBody WikiResult addInterfaceDoc(@RequestParam("content") String content,
			                                        WikiDocBase wikiDocBase) throws Exception{
		
		     log.info("content:"+content);
		     log.info("wikiDocBase"+wikiDocBase);
		       
		     WikiDocContent wikiDocContent=new WikiDocContent();
		     wikiDocContent.setContent(content);
		     wikiDocContent.setWikiDocBase(wikiDocBase);
		     
		     WikiResult result=new WikiResult();
		     if(wikiDocService.saveORUpdateWikiDocContent(wikiDocContent)){
		    	      result.setCode(1);
		    	      result.setMessage("保存成功!");
		     }else{
		    	      result.setCode(0);
		    	      result.setMessage("保存失败!");
		     }
		     
		     return result;
			
	}
	
	//更新接口文档
	@RequestMapping(value="/updateInterfaceDoc",method=RequestMethod.POST)
	public @ResponseBody WikiResult updateInterfaceDoc(@RequestParam("contentID") Long contentID,
			                                           @RequestParam("content") String content,
			                                           @RequestParam("baseID") Long baseID,
			                                           WikiDocBase wikiDocBase){
		
		   WikiDocContent wikiDocContent=new WikiDocContent();
		   wikiDocContent.setId(contentID);
		   wikiDocContent.setContent(content);
		   wikiDocBase.setId(baseID);
		   wikiDocContent.setWikiDocBase(wikiDocBase);
		
		   WikiResult wikiResult=new WikiResult();
		   if(wikiDocService.saveORUpdateWikiDocContent(wikiDocContent)){
			        wikiResult.setCode(1);
			        wikiResult.setMessage("更新成功");
		   }else{
			        wikiResult.setCode(0);
			        wikiResult.setMessage("更新失败");
		   }
		
		   return wikiResult;
	}
	
	//获取接口文档内容
	@RequestMapping(value="/getInterface",method=RequestMethod.POST)
	public @ResponseBody WikiDocContent getInterface(@RequestParam("wdb_id") Long wdb_id){
		   return wikiDocService.getInterfaceDoc(wdb_id);
	}
	
	@RequestMapping(value="/getSingleWikiDocBase",method=RequestMethod.POST)
	public @ResponseBody WikiDocBase getSingleWikiDocBase(@RequestParam("id") Long id){
		   log.info("id:"+id);
		   return wikiDocService.getSingleWikiDocBase(id);
	}
	
	
	@RequestMapping(value="/getSingleSite",method=RequestMethod.POST)
	public @ResponseBody Site getSingleSite(@RequestParam("id") Long id ){
		    return wikiDocService.getSingleSite(id);
	}
	
	
	//删除接口
	@RequestMapping(value="/deleteApi",method=RequestMethod.POST)
	public @ResponseBody WikiResult deleteApi(@RequestParam("contentID") Long contentID){
		
		  log.info("contentID:"+contentID);
		  
		   WikiResult wikiResult=new WikiResult();
		   if(wikiDocService.deleteApiContent(contentID)){
			   
			        wikiResult.setCode(1);
			        wikiResult.setMessage("删除成功!");
			   
		   }else{
			        wikiResult.setCode(0);
			        wikiResult.setMessage("删除失败!");
		   }
		
		   return wikiResult;
		
	}
	
	
	
	//导出pdf文件接口
	@RequestMapping(value="/exportPDF",method=RequestMethod.GET)
	public void exportPDF(@RequestParam(value="wikiDocBaseID",required=false) String wikiDocBaseID,
			                     @RequestParam(value="bulk",required=false) String bulk,
			                     HttpServletResponse response){
		        
		        log.info("导出pdf文件: wikiDocBaseID:{},bulk:{}",wikiDocBaseID,bulk);
		
		        ExportPDFResult result=new ExportPDFResult();
		        if(wikiDocBaseID!=""){
		        	result=exportPDFService.exportSinglePDF(Long.valueOf(wikiDocBaseID));
		        }else if(bulk!=""){
		        	String bulk_siteType=bulk.split(",")[0];
		        	String bulk_site=bulk.split(",")[1];
		        	
		        	List<String> siteTypeList=new ArrayList<String>();
		        	List<String> siteList=new ArrayList<String>();
		        	
		        	StringBuffer sbContent=new StringBuffer();
		        	
		        	if(!"bulk_siteType".equals(bulk_siteType)){
		        		  String[] siteTypeStr=bulk_siteType.split("_");
		        		  for(String str:siteTypeStr){
		        			    siteTypeList.add(str);
		        		  }
		        	}
		        	if(!"bulk_site".equals(bulk_site)){
		        		   String[] siteStr=bulk_site.split("_");
		        		   for(String str:siteStr){
		        			      siteList.add(str);
		        		   }
		        	}
		        	
		        	
		        	String siteName="";
		        	//获取站点类型下全部站点
		        	if(siteTypeList.size()>0){
		        		    for(Iterator<String> iter=siteTypeList.iterator();iter.hasNext();){
		        		    	   List<Site> sites=wikiDocService.getSitesByType(iter.next());
		        		    	   for(Site site:sites){
		 		        			   siteList.add(site.getName());
		 		        		  }
		        		    }
		        	}
		        	
		        	//获取站点下全部接口文档
		        	if(siteList.size()>0){
		        		  for(String site:siteList){
		        			  siteName="<h1 text-align='center'>"+site+"接口API</h1>";
		        			  for(WikiDocContent doc:wikiDocService.getInterfaceDocBySite(site)){
		        			    	sbContent.append(siteName+doc.getContent());
		        			    	siteName="";
		        			  }
		        		  }
		        	}
		        	
		        	//服务器打印pdf文件
		        	result=exportPDFService.bulkExportPDF(sbContent.toString());
		        	
		        	
		        }
		        
		        //从服务器下载pdf文件
		        FileInputStream fis=null;
		        ServletOutputStream output=null;
		        if(result.getExportResult()==1){
		                try {
		                	log.info("获取pdf文件:"+result.getExportMessage());
							fis=new FileInputStream(new File(result.getExportMessage()));
							
							response.setContentType("multipart/form-data;charset=UTF-8");
							response.addHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(result.getExportPDFName(),"UTF-8"));
							output=response.getOutputStream();
							
							byte[] b=new byte[1048576];  //1MB
							int len;
						    while((len=fis.read(b))!=-1){
							      output.write(b,0,len);	
							}
							fis.close();
							output.close();
							output.flush();
							
							new File(result.getExportMessage()).delete();
						    
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							
							try {
								response.getWriter().write("<script>alert('导出失败!')</script>");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							e1.printStackTrace();
						}  
		        }else{
		        	    try {
		        	    	if(result.getExportMessage()!=null){
		        	    		response.getWriter().write("<script>alert('"+result.getExportMessage()+"')</script>");
		        	    	}else{
		        	    		response.getWriter().write("<script>alert('导出失败!')</script>");
		        	    	}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        }
	}
	
	
	//加载addDemo
	@RequestMapping("/addDemo")
	public String toAddDemo(){
		  return "tools/addDemo";
	}
	
	
	//获取日志
	@RequestMapping(value="/apitest/log",method=RequestMethod.GET)
	public String getLog(@RequestParam("logParam") String logParam,Model model){
				try {
					log.info("logParam:"+URLDecoder.decode(logParam,"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    model.addAttribute("logParam",logParam);
	        
		    return "tools/log";
	}
}

