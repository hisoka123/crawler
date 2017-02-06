/**
 * 
 */
package com.ocr.controller;

import java.io.File;

import com.module.ocr.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.module.ocr.utils.IVerifycodeHandler;
import com.ocr.utils.OCRFileHelper;

@Controller
@RequestMapping("/gsxt")
public class GsxtOCRController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtOCRController.class);
	private IVerifycodeHandler verifycodeHandlerCjy = new ChaoJiYingOCRService();
	private IVerifycodeHandler verifycodeHandlerBeijing = new GsxtBeijingOCRService();
	private IVerifycodeHandler verifycodeHandlerGuangdong = new GsxtGuangdongOCRService();
	private IVerifycodeHandler verifycodeHandlerShanghai = new GsxtShanghaiOCRService();
	private IVerifycodeHandler verifycodeHandlerJiangsu = new GsxtJiangsuOCRService();
	private IVerifycodeHandler verifycodeHandlerNingxia = new GsxtNingxiaOCRService();
	private IVerifycodeHandler verifycodeHandlerHebei = new GsxtHebeiOCRService();
	private IVerifycodeHandler verifycodeHandlerGansu = new GsxtGansuOCRService();
	private IVerifycodeHandler verifycodeHandlerQinghai = new GsxtQinghaiOCRService();
	private IVerifycodeHandler verifycodeHandlerSichuan = new GsxtSichuanOCRService();
	private IVerifycodeHandler verifycodeHandlerShanxi = new GsxtShanxiOCRService();
	private IVerifycodeHandler verifycodeHandlerGuizhou = new GsxtGuizhouOCRService();
	private IVerifycodeHandler verifycodeHandlerZhejiang = new GsxtZhejiangOCRService();
	private IVerifycodeHandler verifycodeHandlerChongqing = new GsxtChongqingOCRService();
	
	
	@RequestMapping("/{area}/getVerifycode")
	@ResponseBody
	public String getImageCode(@RequestParam("imageUrl") String imageUrl, @PathVariable(value="area") String area) throws Exception {
		LOGGER.info("===========================GsxtOCRController.getImageCode["+area+"]======================================");

		if (StringUtils.isEmpty(imageUrl)) {
			return "error:url is null!";
		}
		
		String imageFileName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
		String absolutePath = OCRFileHelper.getOCRFILE_BASE_PATH() + "/" + imageFileName; //NFS共享文件路径
		
		String verifycode = null;
		if ("beijing".equals(area)) { //北京
			verifycode = verifycodeHandlerBeijing.getVerifycode(new File(absolutePath));
		} else if ("guangdong".equals(area)) { //广东
			verifycode = verifycodeHandlerGuangdong.getVerifycode(new File(absolutePath));
		} else if ("shanghai".equals(area)) {  //上海
			verifycode = verifycodeHandlerShanghai.getVerifycode(new File(absolutePath));
		} else if ("shanxi".equals(area)) {  //山西
			verifycode = verifycodeHandlerShanxi.getVerifycode(new File(absolutePath));
		}else if ("jiangsu".equals(area)) { //江苏
			verifycode = verifycodeHandlerJiangsu.getVerifycode(new File(absolutePath));
		} else if ("ningxia".equals(area)) { //宁夏
			verifycode = verifycodeHandlerNingxia.getVerifycode(new File(absolutePath));
		} else if ("gansu".equals(area) || "jilin".equals(area)) { //甘肃和吉林
			verifycode = verifycodeHandlerGansu.getVerifycode(new File(absolutePath));
		} else if ("hebei".equals(area) || "liaoning".equals(area)) { //河北和辽宁
			verifycode = verifycodeHandlerHebei.getVerifycode(new File(absolutePath));
		} else if("qinghai".equals(area) || "xizang".equals(area) || "anhui".equals(area) || "henan".equals(area) || "yunnan".equals(area)|| "guangxi".equals(area)|| "hubei".equals(area)|| "tianjin".equals(area) || "neimenggu".equals(area)){ //青海西藏江西
			verifycode = verifycodeHandlerQinghai.getVerifycode(new File(absolutePath));
		}else  if("sichuan".equals(area)){ //四川 安徽江西
			verifycode = verifycodeHandlerSichuan.getVerifycode(new File(absolutePath));
		}else  if("hainan".equals(area)){ //海南yjh
				verifycode = verifycodeHandlerSichuan.getVerifycode(new File(absolutePath));
		}else  if("heilongjiang".equals(area)){ //黑龙江yjh
			verifycode = verifycodeHandlerQinghai.getVerifycode(new File(absolutePath));
//		}else  if("fujian".equals(area)){ //福建yjh
//			verifycode = verifycodeHandlerSichuan.getVerifycode(new File(absolutePath));
		}else  if("shandong".equals(area)){ //山东yjh
			verifycode = verifycodeHandlerQinghai.getVerifycode(new File(absolutePath));
		}else  if("qinghai".equals(area)){ //湖南yjh
			verifycode = verifycodeHandlerQinghai.getVerifycode(new File(absolutePath));
//		}else  if("xinjiang".equals(area)){ //新疆yjh
//			verifycode = verifycodeHandlerSichuan.getVerifycode(new File(absolutePath));
		}else  if("guizhou".equals(area)  || "jiangxi".equals(area)){ //贵州
			verifycode = verifycodeHandlerGuizhou.getVerifycode(new File(absolutePath));
		}else  if("zhejiang".equals(area)) { //浙江
			verifycode = verifycodeHandlerZhejiang.getVerifycode(new File(absolutePath));
		}else  if("chongqing".equals(area)){ //贵州
			verifycode = verifycodeHandlerChongqing.getVerifycode(new File(absolutePath));
		}else{
			verifycode = verifycodeHandlerCjy.getVerifycode(new File(absolutePath), "6003"); //默认使用超级鹰的6003验证码识别方式
		}
		
		LOGGER.info("=============["+ imageUrl + "] ocr result:" + verifycode + "==============");
		return verifycode;
	}
	
}
