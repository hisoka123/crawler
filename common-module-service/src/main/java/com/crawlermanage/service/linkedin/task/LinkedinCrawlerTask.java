/**
 * 
 */
package com.crawlermanage.service.linkedin.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
public class LinkedinCrawlerTask {
	private Event event;
	private List<Listener> listeners = new ArrayList<Listener>();
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public List<Listener> getListeners() {
		return listeners;
	}
	public void setListeners(List<Listener> listeners) {
		this.listeners = listeners;
	}
	
	public LinkedinCrawlerTask addListener(Listener listener) {
		listeners.add(listener);
		return this;
	}

	public void startTask() {
		for (Listener listener : listeners) {
			listener.action(event);
		}
	}
	
}
