package com.le.achieveweb.MVC.controller;

import com.le.achieveweb.MVC.entity.UserLogin;
import com.le.achieveweb.MVC.service.UserService;
import com.le.achieveweb.util.ResultUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userLogin")
public class UserController {
    @Autowired
    UserService userService;
    // 登录
    @PostMapping("/login")
    public String login(@RequestBody UserLogin user, HttpSession session) {
        String result = userService.login(user);
        if (result== ResultUtil.loginSuccess().toString()) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("isLoggedIn", true);
        }
        return result;
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody UserLogin user) {
        return userService.register(user);
    }
}
