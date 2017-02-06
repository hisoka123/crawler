package com.crawlermanage.controller.doc;

import com.crawlermanage.service.doc.SiteService;
import com.module.dao.entity.doc.Site;
import com.module.dao.repository.doc.SiteClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxz on 2016/1/7.
 */

@Controller
@RequestMapping("/product")
public class AjaxProduceController {

    private static final Logger log = LoggerFactory.getLogger(AjaxProduceController.class);

    @Autowired
    SiteService siteService;

    @RequestMapping(value="/getProductList",method= RequestMethod.GET)
    public @ResponseBody
    List<SiteClassify> getProductList(){
        List<SiteClassify> siteClassifyList = siteService.getAllInUseAssembledByClassify();
        return siteClassifyList;
    }

}
