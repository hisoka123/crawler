package com.crawlermanage.dao.result;

import java.io.Serializable;


public class TimeTaskSearchResult<T> implements Serializable {

	   private static final long serialVersionUID = 3696040679647946893L;
	
	   private int state;  //定时任务中，爬取该任务的任务状态码     0,排队等候；1，成功；7，无关键词
	   private int existCode;  //定时任务中，该企业是否存在状态码，0，否；1，在
	   private long timetask_id; //定时任务中,该任务的id;
	   private T ttSearchResult;    //定时任务中，该任务state=1时的附带详情
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getExistCode() {
		return existCode;
	}
	public void setExistCode(int existCode) {
		this.existCode = existCode;
	}
	public long getTimetask_id() {
		return timetask_id;
	}
	public void setTimetask_id(long timetask_id) {
		this.timetask_id = timetask_id;
	}
	public T getTtSearchResult() {
		return ttSearchResult;
	}
	public void setTtSearchResult(T ttSearchResult) {
		this.ttSearchResult = ttSearchResult;
	}
	@Override
	public String toString() {
		return "TimeTaskSearchResult [state=" + state + ", existCode="
				+ existCode + ", timetask_id=" + timetask_id
				+ ", ttSearchResult=" + ttSearchResult + "]";
	}
	   
	   
}
