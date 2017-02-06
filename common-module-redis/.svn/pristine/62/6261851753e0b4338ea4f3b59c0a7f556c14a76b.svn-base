/**
 * 
 */
package com.module.redis.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author kingly
 *
 */
public class GsonUtil {
	private static Gson gson;
	static {
		gson = new GsonBuilder().create();
	}
	
	public static Object fromJson(String json, Class<?> c) {
		return gson.fromJson(json, c);
	}
	
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
}
