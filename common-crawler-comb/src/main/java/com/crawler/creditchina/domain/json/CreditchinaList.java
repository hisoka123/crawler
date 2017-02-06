package com.crawler.creditchina.domain.json;

import java.io.Serializable;
import java.util.List;

public class CreditchinaList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5616068458691851273L;

	private Integer start;
	private Integer pageSize;
	private Integer totalCount;
	private List<Creditchina> results;
	private String departmentIds;
	private Integer totalPageCount;
	private Integer currentPageNo;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Creditchina> getResults() {
		return results;
	}

	public void setResults(List<Creditchina> results) {
		this.results = results;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	@Override
	public String toString() {
		return "CreditchinaList [start=" + start + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", results=" + results
				+ ", departmentIds=" + departmentIds + ", totalPageCount="
				+ totalPageCount + ", currentPageNo=" + currentPageNo + "]";
	}

}
