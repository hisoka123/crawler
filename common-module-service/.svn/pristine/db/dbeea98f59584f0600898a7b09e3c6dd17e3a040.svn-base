package com.crawlermanage.service.auth;

import com.crawler.domain.json.Result;
import com.module.dao.entity.auth.Menu;
import com.module.dao.entity.auth.PagerHelper;
import com.module.dao.entity.auth.Role;
import com.module.dao.repository.auth.MenuDao;
import com.module.unit.CommonConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxz on 2015/12/24.
 */
@Component
public class MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    MenuDao menuDao;
    public Result<PagerHelper> getMenuList(int page){
        log.info("MenuService.getMenuList(page)");
        Integer start = page;
        Integer pageSize = CommonConstruct.PAGE_SIZE_LARGE;
        Long totalCount = 0L;
        Map<String,Object> map = new HashMap<String,Object>();

        Result<PagerHelper> result = new Result<PagerHelper>();
        PagerHelper pagerHelper = null;
        List<Menu> menuList = null;
        menuList = (List<Menu>) menuDao.findByModule(map,start,pageSize);
        totalCount = menuDao.countAll(map);

        pagerHelper = new PagerHelper(menuList,page,pageSize,totalCount);
        result.setData(pagerHelper);
        return result;

    }

}
