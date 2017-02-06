package com.module.ocr.service;

import java.awt.Color;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.module.ocr.utils.AbstractTesseserHandler;
import com.module.ocr.utils.ImageHandler;

@Component
public class PbccrcOCRService extends AbstractTesseserHandler {
	public static final Set<Integer> EXCLUDED_COLOR_RGBS = new HashSet<Integer>();
	static {
		EXCLUDED_COLOR_RGBS.add(new Color(219, 219, 219).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(222, 222, 222).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(225, 225, 225).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(230, 230, 230).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(232, 232, 232).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(233, 233, 233).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(234, 234, 234).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(235, 235, 235).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(239, 239, 239).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(241, 241, 241).getRGB());
		EXCLUDED_COLOR_RGBS.add(new Color(248, 248, 248).getRGB());
	}

	@Override
	public String getVerifycode(File imageFile) throws Exception {
		//图片预处理
		ImageHandler.imagePreHandle(imageFile, EXCLUDED_COLOR_RGBS);
		
		//验证码识别
		String imageCode = super.getVerifycode(imageFile);
		if (!StringUtils.isEmpty(imageCode)) {
			imageCode = imageCode.toLowerCase();
		}
		return imageCode;
	}
}
