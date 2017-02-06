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
import com.module.ocr.service.ChaoJiYingOCRService;
import com.module.ocr.utils.IVerifycodeHandler;
import com.ocr.utils.OCRFileHelper;

/**
 * @author kingly
 * @date 2016年5月5日
 * 
 */

@Controller
@RequestMapping("/cjy")
public class ChaoJiYingOCRController {
	private static IVerifycodeHandler verifycodeHandler = new ChaoJiYingOCRService();
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtOCRController.class);
	
	@RequestMapping("/getVerifycode")
	@ResponseBody
	public String getImageCode(@RequestParam("imageUrl") String imageUrl, @RequestParam("codeType") String codeType) throws Exception {
		LOGGER.info("===========================ChoJiYingOCRController.getImageCode======================================");
		
		if (StringUtils.isEmpty(imageUrl)) {
			return "error:url is null!";
		}
		
		String imageFileName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
		String absolutePath = OCRFileHelper.getOCRFILE_BASE_PATH() + "/" + imageFileName; //NFS共享文件路径
		//absolutePath = "C:/TCode/" + imageFileName;
		
		return verifycodeHandler.getVerifycode(new File(absolutePath), codeType);
	}
}
