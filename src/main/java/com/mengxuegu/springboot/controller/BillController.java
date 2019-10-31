package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.BillDao;
import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Bill;
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
 * @Date: Create in 下午 6:57 2019/10/31
 */
@Controller
public class BillController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BillDao billDao;
    @GetMapping("/bills")
    public String list(Bill bill, Provider provider, Map<String,Object> map){
        if(bill.getBillName()!=null) {
            logger.info("按关键字" + bill.getBillName() + "查询订单.");
        }
        Collection<Bill> bills = billDao.getAll(bill,provider);
        map.put("bills",bills);
        map.put("provider",provider);
        map.put("bill",bill);

        return "bill/list";
    }

    @GetMapping("/bill/{bid}")
    public String info(@PathVariable(value = "bid")Integer bid,
                       @RequestParam(value = "uri",defaultValue = "view")String uri,
                       Map<String,Object> map){
        Bill bill = billDao.get(bid);
        map.put("bill",bill);

        return "bill/" + uri;

    }

    @PutMapping("/bill")
    public String update(Bill bill,Provider provider){
        ProviderDao providerDao = new ProviderDao();
        bill.setProvider(providerDao.getProvider(provider.getPid()));
        billDao.save(bill);

        return "redirect:/bills";
    }

    @GetMapping("/bill")
    public String toAddPage(){

        return "bill/add";
    }

    @PostMapping("/bill")
    public String add(Bill bill,Provider provider){

        Provider provider1 = new ProviderDao().getProvider(provider.getPid());
        bill.setProvider(provider1);
        billDao.save(bill);

        return "redirect:/bills";
    }

    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable(value = "bid")Integer bid){
        billDao.delete(bid);
        return "redirect:/bills";
    }
}
