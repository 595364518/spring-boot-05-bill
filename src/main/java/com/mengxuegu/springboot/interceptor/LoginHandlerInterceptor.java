package com.mengxuegu.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yaya
 * @Description:
 * @Date: Create in 下午 7:51 2019/10/29
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //调用目标方法之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            //已登录，不拦截
            return true;
        }
        //未登录，进行拦截
        request.setAttribute("msg","没有权限，请登录！");
        //请求转发，不能用重定向，使用重定向不能携带参数
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
}
