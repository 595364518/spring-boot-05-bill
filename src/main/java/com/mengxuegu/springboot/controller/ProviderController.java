package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: yaya
 * @Description:
 * @Date: Create in 下午 9:52 2019/10/28
 */
@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ProviderDao providerDao;
//    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @GetMapping("/providers")
    public String list(Map<String,Object> map,@RequestParam(value = "providerName",required = false) String providerName){
        logger.info("查询包含"+providerName+"字段的供应商");
        Collection<Provider> providers = providerDao.getAll(providerName);

        map.put("providers",providers);
        return "provider/list";
    }

}
