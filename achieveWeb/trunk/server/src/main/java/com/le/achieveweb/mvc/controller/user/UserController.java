package com.le.achieveweb.mvc.controller.user;

import com.le.achieveweb.mvc.model.entity.User;
import com.le.achieveweb.mvc.model.vo.LoginView;
import com.le.achieveweb.mvc.service.UserService;
import com.le.achieveweb.response.Result;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//跨域
@CrossOrigin
@RequestMapping("/achieve")
//代替Autowired，可以用来确保对于一个类的所有实例被正确地初始化。参数类型必须要是final的
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // 登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginView user, HttpSession session) {
        return userService.login(user, session);
    }

    // 注册
    @RequestMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    //注销
    @RequestMapping("/logOut")
    @ResponseBody
    public Result loginOut(HttpSession session) {
        return userService.logOut(session);
    }

    //获取用户信息
    @RequestMapping("/getInfo")
    @ResponseBody
    public Result getInfo(HttpSession session) {
        return userService.getInfo(session);
    }

    //修改用户信息
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Result updateInfo(@RequestBody User user,HttpSession session) {
        return userService.updateInfo(user,session);
    }

    //修改用户密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public Result updateInfo(@RequestBody String password,HttpSession session) {
        return userService.updatePassword(password,session);
    }
}
