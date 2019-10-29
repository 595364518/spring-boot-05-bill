package com.mengxuegu.springboot.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: yaya
 * @Description:
 * @Date: Create in 下午 7:15 2019/10/29
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String,Object> map){

        if(!StringUtils.isEmpty(username)&&"123".equals(password)){
            //登录成功
            session.setAttribute("loginUser",username);
            //此处使用请求转发方式跳转到index页面
            //return "main/index";
            //重定向：可以重定向到任意一个页面（包括其他项目），特点是地址栏会发生改变
            return "redirect:/main.html";//需要在自定义配置类中加入视图控制器main.html
        }
        map.put("msg","用户名或密码错误");
        return "main/login";
    }
}
