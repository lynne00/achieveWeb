package com.le.achieveweb.controller;

import com.le.achieveweb.entity.UserLogin;
import com.le.achieveweb.service.UserService;
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
        return userService.login(user);
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody UserLogin user) {
        return userService.register(user);
    }
}
