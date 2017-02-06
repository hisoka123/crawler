package com.crawlermanage.controller.admin;

import com.crawler.domain.json.Result;
import com.crawlermanage.service.auth.ActiveUserService;
import com.crawlermanage.service.auth.RoleService;
import com.module.dao.entity.auth.ActiveUser;
import com.module.dao.entity.auth.PagerHelper;
import com.module.dao.entity.auth.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/admin/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String role() {
        return "admin/role";
    }

    @RequestMapping(value="/getRoleList",method=RequestMethod.GET)
    public @ResponseBody Result<PagerHelper> getRoleList(@RequestParam("page") Integer page){
        log.info("RoleController.getRoleList()...");
        Result<PagerHelper> result = null;
        result = roleService.getRoleList(page);
        return result;
    }

    @RequestMapping(value="/getById",method=RequestMethod.GET)
    public @ResponseBody Result<Role> getById(@RequestParam("id") Long id){
        Result<Role> rRoleList = new Result<Role>();
        Role role = null;
        role = roleService.findById(id);
        rRoleList.setData(role);
        return rRoleList;
    }

    @RequestMapping(value="/updateRole",method=RequestMethod.GET)
    public @ResponseBody Result updateRole(@RequestParam("id") Long id,@RequestParam("roleName") String roleName) {
        Result result =  new Result();
        Role role = roleService.findById(id);
        role.setName(roleName);
        roleService.updateRole(role);
        return result;
    }

}
