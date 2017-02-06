package com.crawlermanage.controller.modules;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modules/fSearch")
public class FSearchController {


    private static final Logger logger = LoggerFactory.getLogger(FSearchController.class);


    @RequestMapping("")
    public String getSearchPage(){
        return "/modules/fSearch/fUnionSearch";
    }

    @RequestMapping("/fUnionSearch/{engine}")
    public String getSearchPage2(@PathVariable String engine , ModelMap map){

        logger.info("搜索引擎: " + engine);

        if("baidu".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","百度");
        }else if("sougou".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","搜狗");
        }else if("haosou".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","好搜");
        }else if("bing".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","必应");
        }else if("yahoo".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","雅虎");
        }else if("engine".equals(engine)){
            map.put("engine",engine);
            map.put("engineCN","选择搜索引擎");
        }

        return "/modules/fSearch/fUnionSearch";
    }



}
