package com.mengxuegu.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 用户登录
     * @param session 登录成功把用户名保存在session中
     * @param username 用户名
     * @param password 密码
     * @param map 响应数据
     * @return 跳转页面
     */
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

    /**
     * 退出登录
     * @param session 用户记录
     * @return 重定向到登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //1.删除session中的登录记录
        session.removeAttribute("loginUser");
        //2.注销session对象
        session.invalidate();
        //3.返回登录页面
        return "redirect:/index.html";

    }

    @GetMapping("/password")
    public String toPasswordPage(){

        return "main/password";
    }
    @PostMapping("/password")
    public String changePwd(@RequestParam(value = "password") String password, Map<String, Object> map) {
        logger.info("修改后的密码为：" + password);
        map.put("msg", "修改成功");
        return "main/login";
    }
}
