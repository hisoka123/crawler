/**
 * 
 */
package com.module.ocr.utils;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author kingly
 * @date 2016年1月5日
 * 
 */
public class ImageHandler {
	//图片预处理
	public static void imagePreHandle(File imageFile, Set<Integer> excludedColorRgbs) throws IOException {
		BufferedImage image = ImageIO.read(imageFile);
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		
		//灰度化、二值化 处理
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				//灰度化
				int rgb = image.getRGB(x, y);
				Color color = new Color(rgb);
				int gray = (int) (0.3*color.getRed() + 0.59*color.getGreen() + 0.11*color.getBlue()); 
				Color newColor = new Color(gray, gray, gray);
				image.setRGB(x, y, newColor.getRGB());
				//二值化
				int rgb2 = image.getRGB(x, y);
				image2.setRGB(x, y, rgb2);
				//去杂色
				excludeInterColorByRgbs(excludedColorRgbs, image2, x, y);
			}
		}
		
		image.flush();
		ImageIO.write(image2, imageFile.getName().substring(imageFile.getName().lastIndexOf(".")+1), imageFile);
	}
	
	//去杂色
	public static void excludeInterColorByRgbs(Set<Integer> excludedColorRgbs, BufferedImage image, int x, int y) {
		if (excludedColorRgbs==null || excludedColorRgbs.isEmpty()) return;
		if (excludedColorRgbs.contains(image.getRGB(x, y))) {
			image.setRGB(x, y, Color.WHITE.getRGB());
		}
	}
	
	//裁剪
	public static void cut(File imageFile, int x, int y, int width, int height) throws IOException {
        FileInputStream is = null;  
        ImageInputStream iis = null;  
        try {  
            // 读取图片文件  
            is = new FileInputStream(imageFile);  
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");  
  
            ImageReader reader = it.next();  
            iis = ImageIO.createImageInputStream(is);  
            reader.setInput(iis, true);  
            ImageReadParam param = reader.getDefaultReadParam();  
            Rectangle rect = new Rectangle(x, y, width, height);  
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0, param);  
  
            // 保存新图片  
            ImageIO.write(bi, "jpg", imageFile);  
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }  
    }
	
}
