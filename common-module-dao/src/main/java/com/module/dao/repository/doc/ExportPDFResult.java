package com.module.dao.repository.doc;

import java.io.Serializable;

/** 
 * @author hmy
 * @date：Jan 15, 2016 
 * 
 */
public class ExportPDFResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 897033553794240428L;

	private int exportResult;  //0,失败，1,成功
	private String exportMessage;
	private String exportPDFName;
	public int getExportResult() {
		return exportResult;
	}
	public void setExportResult(int exportResult) {
		this.exportResult = exportResult;
	}
	public String getExportMessage() {
		return exportMessage;
	}
	public void setExportMessage(String exportMessage) {
		this.exportMessage = exportMessage;
	}
	public String getExportPDFName() {
		return exportPDFName;
	}
	public void setExportPDFName(String exportPDFName) {
		this.exportPDFName = exportPDFName;
	}
	
	
}
