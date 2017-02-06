package com.storm.function;

import com.crawler.weibo.handle.WeiboUserAction;
import com.storm.def.FunctionDefine;
import com.storm.domian.WeiboHandleParam;

public class WeiboHandleFunction {
	
	public String userAction(WeiboHandleParam whp) throws Exception{ 
		String msg = null;
		if(whp.getHandle().equals(FunctionDefine.USER_ATTENTION)){
			msg = userAttention(whp.getUid());
		}
		if(whp.getHandle().equals(FunctionDefine.USER_DELATTENTION)){
			msg = userDelAttention(whp.getUid());
		}
		return msg;
	}
	
	public String userAttention(String uid) throws Exception{
		WeiboUserAction wua = new WeiboUserAction();
		return wua.attention(uid);
	}
	
	public String userDelAttention(String uid) throws Exception{
		WeiboUserAction wua = new WeiboUserAction();
		return wua.delAttention(uid);
	}

}
