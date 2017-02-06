package com.crawler.weibo.domain.compare;

import java.util.Comparator;

import com.crawler.weibo.domain.json.UserFeedJson;

public class UserFansComparator implements Comparator<UserFeedJson> {
    @Override
    public int compare(UserFeedJson o1, UserFeedJson o2) { 
    	Integer i1 = Integer.parseInt(o1.getFans()==null?"0":o1.getFans());
    	Integer i2 = Integer.parseInt(o2.getFans()==null?"0":o2.getFans());
        return i2.compareTo(i1);
    }
}
