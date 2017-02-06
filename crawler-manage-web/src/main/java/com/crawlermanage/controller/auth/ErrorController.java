package com.crawlermanage.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zxz on 2015/11/26.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String permissionError() {
        return "error/403";
    }
}
