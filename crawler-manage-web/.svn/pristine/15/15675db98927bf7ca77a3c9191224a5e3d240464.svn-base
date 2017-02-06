package com.crawlermanage.controller.api.shixin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crawler.domain.json.Result;
import com.crawler.shixin.domain.json.ShiXinJson;
import com.crawlermanage.controller.common.Config;
import com.crawlermanage.service.shixin.ShixinService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**

* 失信网

* @author dyx

*/
@Controller
@RequestMapping("/api/shixin")
public class ShiXinAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiXinAPIController.class);
	@Autowired
	private ShixinService shixinService;
	
	@RequestMapping(value = "/toShixin")
	@ResponseBody
    public String toShixin(boolean isDebug, String logback) {  
		LOGGER.info("==============ShiXinAPIController.toShixin start!=================");
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    Result<Map<String,String>> ress= shixinService.searchPageHandle(true, logback);
	    return gson.toJson(ress);

	}
	
	@RequestMapping(value = "/getShixinResult")
	@ResponseBody
    public String getShixinResult(@RequestParam("pName")String pName, @RequestParam("pCardNum")String pCardNum,@RequestParam("pCode") String pCode ,@RequestParam("serializedFileName") String serializedFileName,@RequestParam("codeImageUrl") String codeImageUrl, String logback) throws IOException {  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson =null;
		if(pName==null){
			pName="";
		}
		if(pCardNum==null){
			pCardNum="";
		}
		Result<List<Object>> result = shixinService.getResult(pName, pCardNum, pCode, serializedFileName, "");		
		resultJson = gson.toJson(result);
		return resultJson;

	}
	
	//OCR验证码识别
	public String getImageCodeByOCR(String imageUrl) throws IOException {
		URL url = new URL("http://" + Config.getServerHost() + ":8080/ocr-service/pbccrc/getImageCode?imageUrl=" + URLEncoder.encode(imageUrl, "utf-8"));
		InputStream is = url.openStream();
		return IOUtils.toString(is, "utf-8");
	}
	
	
	@RequestMapping(value = "/getShixinDataOnce")
	@ResponseBody
    public String getShixinDataOnce(@RequestParam("type")String type, @RequestParam("keyword")String keyword, @RequestParam(value="cardNum",required=false)String cardNum) throws IOException {  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson =null;
		String pCardNum="";
		if(type!=null && !type.isEmpty()){
		if(type.equals("1")){
			pCardNum=cardNum;
		}
		}
		Result<List<ShiXinJson>> result = shixinService.getShixinDataOnce(keyword, pCardNum,"");		
		resultJson = gson.toJson(result);
		return resultJson;

	}

}
