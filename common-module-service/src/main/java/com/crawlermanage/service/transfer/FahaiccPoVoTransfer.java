package com.crawlermanage.service.transfer;

import com.crawler.fahaicc.domain.json.FahaiItemFeed;
import com.module.dao.entity.fahaicc.FahaiccResult;

public class FahaiccPoVoTransfer {

	//VO-->PO
	public static FahaiccResult voToPo(FahaiItemFeed fahaiItemFeed) {
		if (fahaiItemFeed==null) {
			return null;
		}
		FahaiccResult fahaiccResult = new FahaiccResult();
		fahaiccResult.setAuthority(fahaiItemFeed.getAuthority());
		fahaiccResult.setConclusionDate(fahaiItemFeed.getConclusionDate());
		fahaiccResult.setContent(fahaiItemFeed.getContent());
		fahaiccResult.setContentHtml(fahaiItemFeed.getContentHtml());
		fahaiccResult.setCourtDate(fahaiItemFeed.getCourtDate());
		fahaiccResult.setFilingDate(fahaiItemFeed.getFilingDate());
		fahaiccResult.setLinkUrl(fahaiItemFeed.getLinkUrl());
		fahaiccResult.setPubDate(fahaiItemFeed.getPubDate());
		fahaiccResult.setTitle(fahaiItemFeed.getTitle());
		fahaiccResult.setType(fahaiItemFeed.getType());
		fahaiccResult.setUsername(fahaiItemFeed.getUsername());
		
		return fahaiccResult;
	}
	
}
