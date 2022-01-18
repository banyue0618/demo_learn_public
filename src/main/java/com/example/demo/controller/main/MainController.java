package com.example.demo.controller.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author banyue
 * date 2020-04-10
 * 用于控制登录登出，及相关登录信息处理
 */
@Slf4j
@RequestMapping("/login")
@Controller
public class MainController {

    @RequestMapping("")
    public String login(){
        return "demo-sign";
    }

    @RequestMapping("/error")
    public String loginError(){
        return "login-error";
    }

    @RequestMapping("/invalid")
    @ResponseBody
    public String loginInvalid(){
        return "Session 已过期，请重新登录";
    }

}
