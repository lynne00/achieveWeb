package com.le.achieveweb.MVC.controller;

import com.le.achieveweb.MVC.entity.UserLogin;
import com.le.achieveweb.MVC.service.UserService;
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
    public String login(@RequestBody UserLogin user) {
//        @RequestBody UserLogin user
         return userService.login(user);
//        return "hello";
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody UserLogin user) {
        return userService.register(user);
    }
}
