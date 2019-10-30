package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询供应商信息
     * @param map 响应数据集合
     * @param providerName 查询关键字
     * @return
     */
//    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @GetMapping("/providers")
    public String list(Map<String,Object> map,@RequestParam(value = "providerName",required = false) String providerName){
        logger.info("查询包含"+providerName+"字段的供应商");
        Collection<Provider> providers = providerDao.getAll(providerName);

        map.put("providers",providers);
        map.put("providerName",providerName);
        return "provider/list";
    }

    /**
     * type=null 访问view.html
     * type=update 访问update.html
     * @param pid 供应商编号
     * @param type
     * @param map 响应数据集合
     * @return
     */
    @GetMapping("/provider/{pid}")
    public String info(@PathVariable("pid") Integer pid,
                       @RequestParam(value = "type",defaultValue = "view") String type,
                       Map<String,Object> map){

        Provider provider = providerDao.getProvider(pid);
        map.put("provider",provider);
//        return "provider/view";
        return "provider/" + type;
    }

    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    @PutMapping("/provider")
    public String update(Provider provider){

        providerDao.save(provider);

        return "redirect:/providers";
    }

    //前往添加页面
    @GetMapping("/provider")
    public String toAddPage(){

        return "provider/add";
    }

    //添加供应商
    @PostMapping("/provider")
    public String add(Provider provider){

        providerDao.save(provider);

        return "redirect:/providers";
    }

}
