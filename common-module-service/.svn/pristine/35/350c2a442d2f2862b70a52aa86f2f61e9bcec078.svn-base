/**
 * 
 */
package com.crawlermanage.service.linkedin.task;

/**
 * @author kingly
 * @date 2015年9月8日
 * 
 */
public class CrawlerTaskListener implements Listener {
	private LinkedinUserProducer poducer;
	private static int index = 1;
	
	public CrawlerTaskListener(LinkedinUserProducer poducer) {
		this.poducer = poducer;
	}

	public LinkedinUserProducer getPoducer() {
		return poducer;
	}
	public void setPoducer(LinkedinUserProducer poducer) {
		this.poducer = poducer;
	}

	@Override
	public void action(Event event) {
		if (!(event instanceof CrawlerTaskEvent) || poducer==null) return;
		CrawlerTaskEvent crawlerTaskEvent = (CrawlerTaskEvent) event;
		poducer.setCrawlerTaskEvent(crawlerTaskEvent);
		poducer.startNewProducerThread("crawlerTaskListener_" + index);
		index++;
	}
	
}

