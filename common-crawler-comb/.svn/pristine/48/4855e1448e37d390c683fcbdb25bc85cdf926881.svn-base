package com.crawler.htmlparser;

import com.crawler.domain.json.GsonSkip;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	private static final Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			if (field.getAnnotation(GsonSkip.class) instanceof GsonSkip) {
				return true;
			}
			return false;
		}
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			if (clazz.getAnnotation(GsonSkip.class) instanceof GsonSkip) {
				return true;
			}
			return false;
		}
	}).create();
	
	
	public static Gson getGson() {
		return gson;
	}
}
