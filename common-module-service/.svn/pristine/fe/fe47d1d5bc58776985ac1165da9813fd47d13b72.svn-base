/**
 * 
 */
package com.crawlermanage.service.linkedin.task;

/**
 * @author kingly
 * @date 2015年9月8日
 * 
 */
public class CrawlerTaskEvent extends Event {
	private boolean isUserLoginedin = false;
	private int loginAttemptTimes = 3;
	private boolean allTaskStop = false;
	private boolean allTaskPause = false;
	private boolean allTaskSaddProfilesPause = false;

	public synchronized boolean isUserLoginedin() {
		return isUserLoginedin;
	}
	public synchronized void setUserLoginedin(boolean isUserLoginedin) {
		this.isUserLoginedin = isUserLoginedin;
	}
	public synchronized int getLoginAttemptTimes() {
		return loginAttemptTimes;
	}
	public synchronized void setLoginAttemptTimes(int loginAttemptTimes) {
		this.loginAttemptTimes = loginAttemptTimes;
	}
	public synchronized boolean isAllTaskStop() {
		return allTaskStop;
	}
	public synchronized void setAllTaskStop(boolean allTaskStop) {
		this.allTaskStop = allTaskStop;
	}
	public synchronized boolean isAllTaskPause() {
		return allTaskPause;
	}
	public synchronized void setAllTaskPause(boolean allTaskPause) {
		this.allTaskPause = allTaskPause;
	}
	public synchronized boolean isAllTaskSaddProfilesPause() {
		return allTaskSaddProfilesPause;
	}

	public synchronized void setAllTaskSaddProfilesPause(boolean allTaskSaddProfilesPause) {
		this.allTaskSaddProfilesPause = allTaskSaddProfilesPause;
	}

	public long getRandomTaskInterval() {
		return (120000 + (int)(Math.random()*120000));
		//return 10 * 1000;
	}

}
