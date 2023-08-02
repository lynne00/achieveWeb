package com.le.achieveweb.mvc.controller;

import com.le.achieveweb.mvc.entity.UserLogin;
import com.le.achieveweb.mvc.service.UserService;
import com.le.achieveweb.response.Result;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//跨域
@CrossOrigin
@RequestMapping("/userLogin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // 登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody UserLogin user, HttpSession session) {
        return userService.login(user, session);
    }

    // 注册
    @RequestMapping("/register")
    @ResponseBody
    public Result register(@RequestBody UserLogin user) {
        return userService.register(user);
    }
}
