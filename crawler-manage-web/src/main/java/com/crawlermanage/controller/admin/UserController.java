package com.crawlermanage.controller.admin;

import ch.qos.logback.core.joran.action.ActionUtil;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxz on 2015/11/9.
 */

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ActiveUserService activeUserService;

    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String user() {
        return "admin/user";
    }


    @RequestMapping(value="/getUserList",method=RequestMethod.GET)
    public @ResponseBody  Result<PagerHelper> getUserList(@RequestParam("page") Integer page){
        Result<PagerHelper> restlt = null;
        restlt = activeUserService.findAllUsers(page);
        return restlt;
    }

    @RequestMapping(value="/getById",method=RequestMethod.GET)
    public @ResponseBody Result<ActiveUser> getUserById(@RequestParam("id") Long id){
        Result<ActiveUser> rUserList = new Result<ActiveUser>();
        ActiveUser activeUser = null;
        activeUser = activeUserService.findById(id);
        rUserList.setData(activeUser);
        return rUserList;
    }

    @RequestMapping(value="/updateUser",method=RequestMethod.GET)
    public @ResponseBody Result updateUser(@RequestParam("id") Long id,@RequestParam("userNameCh") String userNameCh,@RequestParam("roleId") int roleId) {
        Result result =  new Result();
        List roleList = null;
        Role role = null;
        ActiveUser activeUser = activeUserService.findById(id);


        roleList = activeUser.getRoleList();
        role = (Role)roleList.get(0);
        if(role.getId() != roleId){
            Role newRole = new Role();
            newRole = roleService.findById(Long.parseLong(roleId+""));
            roleList.set(0,newRole);
        }

        activeUser.setName(userNameCh);

        activeUserService.updateUser(activeUser);
        return result;
    }


    @RequestMapping(value="/resetPassword",method=RequestMethod.GET)
    public @ResponseBody Result resetPassword(@RequestParam("id") Long id){

        Result result =  new Result();

        ActiveUser activeUser = activeUserService.findById(id);
        activeUser.setPassword("f1547dae6c6153b70128a4a62e1895bd2f085f31");
        activeUser.setSalt("bf24cb76715af4ce");
        activeUser.setPlainPassword("123456");
        activeUserService.updateUser(activeUser);

        return result;
    }

    @RequestMapping(value="/stopUserAcc",method=RequestMethod.GET)
    public @ResponseBody Result stopUserAcc(@RequestParam("id") Long id) {
        Result result =  new Result();
        ActiveUser activeUser = activeUserService.findById(id);
        if(activeUser!=null&&"active".equals(activeUser.getStatus())) {
            activeUser.setStatus("inactive");
            activeUserService.updateUser(activeUser);
        }
        return result;
    }



    @RequestMapping(value="/openUserAcc",method=RequestMethod.GET)
    public @ResponseBody Result openUserAcc(@RequestParam("id") Long id) {
        Result result =  new Result();
        ActiveUser activeUser = activeUserService.findById(id);
        if(activeUser!=null&&"inactive".equals(activeUser.getStatus())) {
            activeUser.setStatus("active");
            activeUserService.updateUser(activeUser);
        }
        return result;
    }



}