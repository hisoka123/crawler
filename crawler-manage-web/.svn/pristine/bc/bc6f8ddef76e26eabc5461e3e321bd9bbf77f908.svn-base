package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.sipo.SipoDBService;
import com.module.dao.entity.sipo.Sipo;
import com.module.dao.entity.sipo.SipoKeyword;


@Controller
@RequestMapping("/modules/sipo")
public class AjaxSipoController {

    private static final Logger log = LoggerFactory.getLogger(AjaxSipoController.class);
	
    @Autowired
    private SipoDBService sipoDBService; 
    
  //核查类型、名称
    @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
    	public @ResponseBody TimeTaskSearchResult<List<Sipo>> checkIsExist(
    			@RequestParam("type") String type, @RequestParam("name") String name) {

    		TimeTaskSearchResult<List<Sipo>> result = new TimeTaskSearchResult<List<Sipo>>();

    		log.info("专利网核查：type:" + type + "  name:" + name);
    		List<SipoKeyword> coiList = sipoDBService.checkIsExistBYName(type,name);
    		if (coiList.size() > 0) {

    			result.setExistCode(1);

    			List<Long> idList = new ArrayList<Long>();

    			for (int i = 0; i < coiList.size(); i++) {
    				idList.add(coiList.get(i).getId());
    			}

    			result.setTimetask_id(coiList.get(0).getId());

    			List<Sipo> pcList = sipoDBService.getDetailByCOI(idList);

    			if (pcList.size() > 0) {
    				result.setState(1);
    				result.setTtSearchResult(pcList);
    			} else {
    				if (coiList.get(0).getState() == 7) {
    					result.setState(coiList.get(0).getState());
    				} else {
    					result.setState(0);
    				}
    			}
    		} else {
    			result.setExistCode(0);
    			result.setState(0);
    		}
    		
    		return result;
    	}
    	
    	   
    	   
    
	
	
}
