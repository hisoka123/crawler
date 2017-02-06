/**
 * 
 */
package com.ocr.controller;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.module.ocr.service.ZhengxinOCRService;
import com.module.ocr.utils.IVerifycodeHandler;
import com.ocr.utils.OCRFileHelper;

/**
 * @author dyx
 * @date 2016年4月15日
 * 
 */
@Controller
@RequestMapping("/zhengxin")
public class ZhengxinOCRController {
	private static IVerifycodeHandler verifycodeHandler = new ZhengxinOCRService();
	private static final Logger LOGGER = LoggerFactory.getLogger(ZhengxinOCRController.class);
	
	@RequestMapping("/getVerifycode")
	@ResponseBody
	public String getImageCode(@RequestParam("imageUrl") String imageUrl) throws Exception {
		LOGGER.info("===========================ZhengxinOCRController.getImageCode======================================");
		
		//String localImagePath = OCRFileHelper.writeImageByUrl(imageUrl);
		if (StringUtils.isEmpty(imageUrl)) {
			return "error:url is null!";
		}
		
		String imageFileName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
		String absolutePath = OCRFileHelper.getOCRFILE_BASE_PATH() + "/" + imageFileName; //NFS共享文件路径
		
		return verifycodeHandler.getVerifycode(new File(absolutePath));
	}
	
}
