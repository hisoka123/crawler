package com.crawlermanage.controller.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/modules/fang")
public class FangController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FangController.class);
	@Autowired
	//private IecmsService iecmsService;
	@RequestMapping(value={"", "fangSearch"})
	public String getSearchPage() {
	LOGGER.info("==============fangController.getSearchPage start!=================");
   // Gson gson = new GsonBuilder().setPrettyPrinting().create();
   // Boolean isDebug=true;
     return "/modules/fang/fangSearch";
	}
	
	//搜索
//		@RequestMapping(value="/getSearchResults",method=RequestMethod.GET)
//		public @ResponseBody Result<Map<String,String>> getSearchResults(@RequestParam("keyword") String keyword){
//
//			Result<Map<String,String>> result = null;
//			try {
//				  
//				LOGGER.info("搜房网keyword:{}",URLDecoder.decode(keyword,"utf-8"));
//
//				  result = dailianmengUserInfoService.searchPage(keyword, true);          
//				  
//		          Gson gson = new Gson();  		
//			      String usersJson = gson.toJson(result);
//			      LOGGER.info("搜房网搜索结果:{}", usersJson);	
//				
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			return result;
//			
//		}
//	
}
