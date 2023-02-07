package com.liu.interceptor;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 11:47
 */

import com.liu.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResourcesInterceptor extends HandlerInterceptorAdapter {

    private List<String> ignoreUrl;

    public ResourcesInterceptor(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        //表示登录操作
        if (request.getRequestURI().contains("login")){
            return true;
        }
        //表示已登录
        if (user != null){
            if ("ADMIN".equals(user.getRole())){
                return true;
            }
            else{
                for (String url:ignoreUrl){
                    if (request.getRequestURI().contains(url)){
                        return true;
                    }
                }
            }
        }
        request.setAttribute("msg"," 请先登录");
        request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
        return false;
    }
}
