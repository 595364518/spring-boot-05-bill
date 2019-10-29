package com.mengxuegu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: yaya
 * @Description:
 * @Date: Create in 下午 9:52 2019/10/28
 */
@Controller
public class ProviderController {

//    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @GetMapping("/providers")
    public String list(){

        return "provider/list";
    }

}
