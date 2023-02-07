package com.liu.controller;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 11:32
 */

import com.liu.domain.User;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        try {
            User u=userService.login(user);
            if(u!=null){
                request.getSession().setAttribute("USER_SESSION",u);
                return "redirect:/admin/main.jsp";
            }
            request.setAttribute("msg","用户名或密码错误");
            return "forward:/admin/login.jsp";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return "forward:/admin/login.jsp";
        }
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            return "forward:/admin/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return "forward:/admin/login.jsp";
        }
    }

}
