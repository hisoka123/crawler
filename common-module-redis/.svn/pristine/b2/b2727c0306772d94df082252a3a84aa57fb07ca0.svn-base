package com.module.redis.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	private static App instance;
	
	private ApplicationContext context;
 
	private App() {
		context = new ClassPathXmlApplicationContext("/applicationContext-redis.xml", App.class);
	}

	public static App getInstance() {
		if (instance == null) {
			synchronized (App.class) {
				if (instance == null) {
					instance = new App();
				}
			}
		}
		return instance;
	}

	/**
	 * @return the context
	 */
	public ApplicationContext getContext() {
		return context;
	}

}
