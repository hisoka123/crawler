package com.crawlermanage.service.shixin;

import java.io.IOException;
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

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.shixin.domain.json.ShiXinJson;
import com.crawlermanage.service.transfer.ShixinPoVoTransfer;
import com.module.dao.entity.shixin.Shixin;
import com.module.dao.entity.shixin.ShixinKeyword;
import com.module.dao.repository.shixin.ShixinKeywordRepository;
import com.module.dao.repository.shixin.ShixinRepository;

@Service
public class ShixinTaskService {
	@Autowired
    private ShixinRepository shixinRepository;
	
	@Autowired
    private ShixinKeywordRepository shixinKeywordRepository;
	
	@Autowired
    private ShixinService shixinService;
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(ShixinTaskService.class);
	
	@Transactional
	public Map<String, Object> excOneceTask() throws IOException, JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ShixinKeyword shixinKeyword=shixinKeywordRepository.findTopByStateInAndNumLessThanOrderByPriorityDesc(StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
//		for(shixinKeyword!=null){
		if (shixinKeyword != null) {
			String type=shixinKeyword.getType();
			shixinKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
			shixinKeyword=shixinKeywordRepository.save(shixinKeyword);
			
			Result<List<ShiXinJson>> result =null;
			if(type!=null && type.equals("0")){
				result =shixinService.getShixinDataOnce(shixinKeyword.getKeyword(), "", "");
			}else if(type!=null && type.equals("1") && shixinKeyword.getCardNum()!=null && shixinKeyword.getKeyword()!=null){
				result =shixinService.getShixinDataOnce(shixinKeyword.getKeyword(), shixinKeyword.getCardNum(), "");
			}
			if(result.getData().size()!=0){
				//改变状态属性
				shixinKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
				Set<Shixin> shixins=new HashSet<Shixin>();
				for(int i=0;i<result.getData().size();i++){
					Shixin shixin=ShixinPoVoTransfer.voToPo(result.getData().get(i));
					shixin.setShixinKeyword(shixinKeyword);
					shixins.add(shixin);
				}
				shixinKeyword.setShixins(shixins);
			}else{
				shixinKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));
			}
			
			//改变执行次数属性
			Integer num = shixinKeyword.getNum() == null ? 1 : (shixinKeyword.getNum() + 1);
			shixinKeyword.setNum(num);
			Integer totalNum = shixinKeyword.getTotalNum() == null ? 1 : (shixinKeyword.getTotalNum() + 1);
			shixinKeyword.setTotalNum(totalNum);
			
			shixinKeywordRepository.save(shixinKeyword);
			
			Log.info("===================resultMap is "+resultMap.toString()+"====================");
			
//			resultMap.put("APIresult", resultJsonStr);
			
			//返回输入参数
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("keyword", shixinKeyword.getKeyword());
			resultMap.put("arguments", arguments);
			resultMap.put("keyword", shixinKeyword.getKeyword());
			resultMap.put("cId", shixinKeyword.getId());
			resultMap.put("APIresult", result);
		}
//		}
		return resultMap;
	}
	
	
}
