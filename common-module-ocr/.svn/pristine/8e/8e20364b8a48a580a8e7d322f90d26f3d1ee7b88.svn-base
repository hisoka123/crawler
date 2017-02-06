package com.module.ocr.app;

import com.module.ocr.utils.AbstractTesseserHandler;


public class App {
	private static AbstractTesseserHandler ocrInstance;

	public static AbstractTesseserHandler getOCRInstance(Class<? extends AbstractTesseserHandler> c) throws InstantiationException, IllegalAccessException {
		if (ocrInstance == null) {
			synchronized (App.class) {
				if (ocrInstance == null) {
					ocrInstance = c.newInstance();
				}
			}
		}
		return ocrInstance;
	}
}
