package com.mengxuegu.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义响应数据
 * @Author: yaya
 * @Description:
 * @Date: Create in 下午 07:33 2019/11/3
 */
@Component//向容器中添加该组件
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * 自定义响应数据
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("name","yaya");
        return map;
    }
}
