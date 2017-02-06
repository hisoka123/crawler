package test.ocr;

import java.io.File;

import com.module.ocr.service.GsxtBeijingOCRService;
import com.module.ocr.service.GsxtHebeiOCRService;
import com.module.ocr.service.GsxtZhejiangOCRService;
import com.module.ocr.service.PbccrcOCRService;
import com.module.ocr.utils.IVerifycodeHandler;
import com.module.ocr.utils.ImageHandler;

public class TesseractTest {
	public static void main(String[] args) throws Exception {
		File imageFile = new File("/home/ubuntu/nfs-images/code.do");
		ImageHandler.imagePreHandle(imageFile, null);
//		IVerifycodeHandler verifycodeHandler = new GsxtBeijingOCRService();
//		String imageCodeByOCR = verifycodeHandler.getVerifycode(imageFile);
//		System.out.println(imageCodeByOCR);

		IVerifycodeHandler verifycodeHandlerZhejiang = new GsxtZhejiangOCRService();
		String imageCodeByOCRZhejiang = verifycodeHandlerZhejiang.getVerifycode(imageFile);
		System.out.println(imageCodeByOCRZhejiang);
	}
}
