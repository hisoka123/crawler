package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.baidu.domain.json.BaiduWiki;
import com.crawlermanage.service.baidu.BaiduService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/modules/baidubaike")
public class AjaxBaidubaikeController {

	private static final Logger log = LoggerFactory.getLogger(AjaxBaidubaikeController.class);
	@Autowired
	private BaiduService baiduService;
	
	
	@RequestMapping(value="/getSearchResult",method=RequestMethod.GET)
	public @ResponseBody List<BaiduWiki> getSearchResult(@RequestParam("person") String person){
		    
		   List<BaiduWiki> searchResult=null;
		
		   try {
				log.info("百度百科搜索person:{}"+URLDecoder.decode(person,"utf-8"));
				
				String personURL = "http://baike.baidu.com/search?word="+person;
			    log.info("百度百科personURL:{}"+personURL);
			    
			    searchResult = baiduService.getListSearchResult(personURL,false,"").getData();
			    
			    Gson gson=new Gson();
			    log.info("百度百科搜索结果:{}"+gson.toJson(searchResult));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return searchResult;
	}
	
}
