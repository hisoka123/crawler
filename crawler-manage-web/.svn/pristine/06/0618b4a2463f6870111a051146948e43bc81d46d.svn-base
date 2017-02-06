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
import com.crawlermanage.service.iautos.IautosDBService;
import com.module.dao.entity.iautos.Iautos;
import com.module.dao.entity.iautos.IautosKeyword;
//第一车网

@Controller
@RequestMapping("/modules/iautos")
public class AjaxIautosController {

private static final Logger log = LoggerFactory.getLogger(AjaxIautosController.class);

@Autowired
IautosDBService iautosDBService;

//核查名称、号码
@RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TimeTaskSearchResult<List<Iautos>> checkIsExist(
			@RequestParam("city") String city, @RequestParam("name") String name) {

		TimeTaskSearchResult<List<Iautos>> result = new TimeTaskSearchResult<List<Iautos>>();

		log.info("第一车网核查：city:" + city + "  name:" + name);
		List<IautosKeyword> coiList = iautosDBService.checkIsExistBYName(city,name);
		if (coiList.size() > 0) {

			result.setExistCode(1);

			List<Long> idList = new ArrayList<Long>();

			for (int i = 0; i < coiList.size(); i++) {
				idList.add(coiList.get(i).getId());
			}

			result.setTimetask_id(coiList.get(0).getId());

			List<Iautos> pcList = iautosDBService.getDetailByCOI(idList);

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
