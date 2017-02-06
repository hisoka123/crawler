package com.crawlermanage.service.auth;

import com.crawler.domain.json.Result;
import com.module.dao.entity.auth.ActiveUser;
import com.module.dao.entity.auth.PagerHelper;
import com.module.dao.entity.auth.Role;
import com.module.dao.repository.auth.RoleDao;
import com.module.unit.CommonConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxz on 2015/11/6.
 */

@Component
public class RoleService{

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleDao roleDao;


    public Role getByRoleName(String roleName){
        Integer start = 0;
        Integer rowCount = 0;
        Role role = null;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",roleName);

        role = roleDao.findByModule(map,start,rowCount).get(0);

        return role;
    }

    public Result<PagerHelper> getRoleList(int page){
        log.info("RoleSerivce.getRoleList(page)");
        Integer start = page;
        Integer pageSize = CommonConstruct.PAGE_SIZE_TWIENTY;
        Long totalCount = 0L;
        Map<String,Object> map = new HashMap<String,Object>();

        Result<PagerHelper> result = new Result<PagerHelper>();
        PagerHelper pagerHelper = null;
        List<Role> roleList = null;
        roleList = (List<Role>) roleDao.findByModule(map,start,pageSize);
        totalCount = roleDao.countAll(map);

        pagerHelper = new PagerHelper(roleList,page,pageSize,totalCount);
        result.setData(pagerHelper);
        return result;

    }

    public Role findById(Long id){
        log.info("RoleService.getById(id)");
        return roleDao.findById(id);
    }

    public Role updateRole(Role role){
        log.info("RoleService.updateRole(role)");
        return roleDao.update(role);
    }



}
