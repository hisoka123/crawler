package com.crawlermanage.controller;

import com.crawler.baidu.domain.json.BaiduWiki;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.baidu.BaiduService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/baidu")
public class BaiduController {
	private static final Logger log = LoggerFactory.getLogger(BaiduController.class);
	
	@Autowired
	private BaiduService baiduService;

	
	/*
	 * (1)爬取词条基本信息
	 */

	//(1-1)转向”爬取“页，填写爬取词条。
	@RequestMapping("/crawler")
	public String crawler(){
		return "baidu/crawler";
	}
	//(1-2)转向“爬取结果展示”页
	@RequestMapping(value="/crawler_search",method=RequestMethod.POST)
	public String crawlerSearch (@RequestParam("crawlerContent") String crawlerContent,Model model){
	      log.info("百度百科爬取内容：{}"+crawlerContent);
	      model.addAttribute("crawlerContent",crawlerContent);
	      return "baidu/crawler-search";
	}

	
	//(1-3)得到"词条搜索列表爬取结果"
	@RequestMapping(value="/getListCrawlerResult",method=RequestMethod.GET)
	public @ResponseBody Result<List<BaiduWiki>> getListCrawlerResult(@RequestParam("crawlerContent") String crawlerContent,@RequestParam("isDebug") boolean isDebug){
		    try {
				log.info("爬取词条关键词:{}"+URLDecoder.decode(crawlerContent,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String url = "http://baike.baidu.com/search?word="+crawlerContent;
		    log.info("爬取文章url:{}"+url);
		    Result<List<BaiduWiki>> searchResult = baiduService.getListSearchResult(url,isDebug,"");
		    Gson gson=new Gson();
		    log.info("爬取文章结果:{}"+gson.toJson(searchResult));
		    return searchResult;
	}

    //转至详情页
    @RequestMapping(value="/redirectToDetails",method=RequestMethod.GET)
    public String details(@RequestParam("url") String url,Model model){
        log.info("url:{}",url);
        model.addAttribute("url",url);
        return "baidu/detail";
    }




    //(1-4)得到"词条详细内容爬取结果"
    @RequestMapping(value="/getDetailCrawlerResult",method=RequestMethod.GET)
    public @ResponseBody Result<BaiduWiki> getDetailCrawlerResult(@RequestParam("url") String url,@RequestParam("isDebug") boolean isDebug){

        log.info("爬取详细词条内容url:{}"+url);
        Result<BaiduWiki> searchResult = baiduService.getDetailSearchResult(url,isDebug,"");
        Gson gson=new Gson();
        log.info("爬取详细词条内容:{}"+gson.toJson(searchResult));
        return searchResult;
    }
			                     
}
