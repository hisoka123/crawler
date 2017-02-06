package com.crawlermanage.controller.auth;


import com.crawler.domain.json.Result;
import com.crawlermanage.service.auth.RoleService;
import com.module.dao.entity.auth.Role;
import org.apache.xpath.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawlermanage.service.auth.ActiveUserService;
import com.module.dao.entity.auth.ActiveUser;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegController {
	
	private static final Logger log = LoggerFactory.getLogger(RegController.class); 
	
	@Autowired
	protected ActiveUserService activeUserService;

    @Autowired
    protected RoleService roleService;

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg() {
		log.info("----------reg------------");
		return "user/reg";
	}


    @RequestMapping(value = "/reg/checkIsAbleReg", method = RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> checkIsAbleReg(@RequestParam("loginName") String loginName){
        log.info("check is loginname is been registed ----------------------------");
        Boolean isRegistered = false;
        Boolean isAbleReg = true;
        Result<Boolean> result = new Result<Boolean>();
        try{
            ActiveUser au = activeUserService.findUserByLoginName(loginName);
            if(null != au){
                isRegistered = true;
            }
        }catch(Exception e){
            isRegistered = true;
        }
        isAbleReg = !isRegistered;
        result.setData(isAbleReg);
        return result;
    }
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String save(@RequestParam("loginName") String loginName,@RequestParam("name") String name,@RequestParam("role") String inputRole,@RequestParam("plainPassword") String plainPassword) {

        log.info("----------" + loginName + "-----------------" + plainPassword + "---------------" + name);

        try {
            ActiveUser au = activeUserService.findUserByLoginName(loginName);
            List<Role> roleList = null;
            Role role = null;
            String originUserRoleName = "普通用户";
            if("1".equals(inputRole)){
            	originUserRoleName = "系统管理员";
            }

            if (au == null) {
                au = new ActiveUser();
                role = roleService.getByRoleName(originUserRoleName);
                roleList = new ArrayList<>();
                roleList.add(role);
            }else{
                throw new Exception("用户已经被注册");
            }
            au.setLoginName(loginName);
            au.setName(name);
            au.setStatus("active");
            au.setPlainPassword(plainPassword);
            au.setRoleName(originUserRoleName);
            au.setRoleList(roleList);
            activeUserService.saveActiveUser(au); 
            return "redirect:/login";
        } catch (Exception e) {
            return "error/500";
        }
    }
/*
    @RequestMapping(value = "/successReg", method = RequestMethod.GET)
    public String rsuccessRegeg() {
        log.info("----------reg------------");
        return "user/successReg";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        log.info("----------reg------------");
        return "user/error";
    }
*/
}
