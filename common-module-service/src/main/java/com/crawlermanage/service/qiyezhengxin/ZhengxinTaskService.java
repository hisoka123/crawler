package com.crawlermanage.service.qiyezhengxin;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawlermanage.service.transfer.ZhengxinPoVoTransfer;
import com.module.dao.entity.qiyezhengxin.Zhengxin;
import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;
import com.module.dao.repository.qiyezhengxin.ZhengxinKeywordRepository;
import com.module.dao.repository.qiyezhengxin.ZhengxinRepository;

@Service
public class ZhengxinTaskService {
	@Autowired
    private ZhengxinKeywordRepository zhengxinKeywordRepository;
	
	@Autowired
    private ZhengxinRepository zhengxinRepository;
	
	@Autowired
	private QiyezhengxinApiService qiyezhengxinApiService;
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(ZhengxinTaskService.class);
	
	@Transactional
	public Map<String, Object> excOneceTask() throws IOException, JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ZhengxinKeyword zhengxinKeyword=zhengxinKeywordRepository.findTopByStateInAndNumLessThanOrderByPriorityDesc(StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
//		for(ZhengxinKeyword zhengxinKeyword:zhengxinKeywords){
		if (zhengxinKeyword != null) {
			zhengxinKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
			zhengxinKeyword=zhengxinKeywordRepository.save(zhengxinKeyword);
			
			String url = "http://www.11315.com/newSearch?regionMc="
					 +URLEncoder.encode("选择地区","utf-8")+"&searchType=1&regionDm=&searchTypeHead=1&name="
					 +URLEncoder.encode(zhengxinKeyword.getCompany(),"utf-8")+"@"+URLDecoder.decode(zhengxinKeyword.getCompany(),"utf-8"); 
			
			Result<List<ZhengxinJson>> result =qiyezhengxinApiService.search(url,true,"");
			
			if(result!=null && result.getHtml().equals("frequently")){
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorName("频率限制");
				errorMsg.setErrorMsg("sorry,你可能访问的太快了");
				result.setError(errorMsg);
				zhengxinKeyword.setState(Integer.parseInt(StatusCodeDef.FREQUENCY_LIMITED));
				zhengxinKeyword=zhengxinKeywordRepository.save(zhengxinKeyword);
			}else{
				if(result.getData()!=null && result.getData().size()!=0){
					//改变状态属性
					zhengxinKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
					Set<Zhengxin> zhengxins=new HashSet<Zhengxin>();
					for(int i=0;i<result.getData().size();i++){
						Zhengxin zhengxin=ZhengxinPoVoTransfer.voToPo(result.getData().get(i));
						zhengxin.setZhengxinKeyword(zhengxinKeyword);
						zhengxins.add(zhengxin);
					}
					zhengxinKeyword.setZhengxins(zhengxins);
				}else{
					zhengxinKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));
				}
				
				//改变执行次数属性
				Integer num = zhengxinKeyword.getNum() == null ? 1 : (zhengxinKeyword.getNum() + 1);
				zhengxinKeyword.setNum(num);
				Integer totalNum = zhengxinKeyword.getTotalNum() == null ? 1 : (zhengxinKeyword.getTotalNum() + 1);
				zhengxinKeyword.setTotalNum(totalNum);
				
				zhengxinKeywordRepository.save(zhengxinKeyword);
				
				Log.info("===================resultMap is "+resultMap.toString()+"====================");
				
//				resultMap.put("APIresult", resultJsonStr);
				
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", zhengxinKeyword.getCompany());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", zhengxinKeyword.getCompany());
				resultMap.put("cId", zhengxinKeyword.getId());
				resultMap.put("APIresult", result);
			}
			
		}
//		}
		return resultMap;
	}
	
	
}
