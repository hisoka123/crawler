package com.crawlermanage.controller.auth;

import com.crawler.domain.json.Result;
import com.crawlermanage.service.auth.MenuService;
import com.module.dao.entity.auth.Menu;
import com.module.dao.entity.auth.PagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxz on 2015/11/12.
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value="/getAllMenuList",method= RequestMethod.GET)
    public @ResponseBody
    Result<PagerHelper> getAllMenuList(@RequestParam("page") Integer page){
        Result<PagerHelper> restlt = null;
        restlt = menuService.getMenuList(page);
        return restlt;
    }

}
