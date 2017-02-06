package com.crawlermanage.controller;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/home")
    public String home(){ 
   	 	return "demo/home"; 
    }
	
	@RequestMapping("/index")
    public String index(){ 
   	 	return "demo/index"; 
    }
	
	@RequestMapping("/apitest")
	public String apitest() {
		return "demo/api-test";
	}
	
	@RequestMapping("/wiki")
    public String wiki(){ 
   	 	return "demo/wiki"; 
    }
	
	@RequestMapping(value = "/wiki/doc")
	@ResponseBody
	public String userinfo(@RequestParam("docName") String docName) throws IOException {
		String result = null;
		
		InputStream is = DemoController.class.getResourceAsStream("/apidoc/" + docName);
		try {
			result = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			throw e;
		} finally {
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		
		return result;
	}
	
	
	
	@RequestMapping("search")
	public String toSearch(){
		return "demo/search";
	}
	@RequestMapping(value="searchResult",method=RequestMethod.GET)
	public String toSearchResult(@RequestParam("nickname") String nickname,Model model){
		  model.addAttribute("nickname",nickname);
		   return "demo/searchResult";
	}
	
}
