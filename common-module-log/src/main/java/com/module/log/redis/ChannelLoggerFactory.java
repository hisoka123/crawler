/**
 * 
 */
package com.module.log.redis;

/**
 * @author kingly
 * @date 2016年4月8日
 * 
 */
public class ChannelLoggerFactory {
	
	public static ChannelLogger getLogger(Class<?> clazz, String logback) {
		return new ChannelLogger(clazz, logback);
	}
}
