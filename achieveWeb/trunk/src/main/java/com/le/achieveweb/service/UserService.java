package com.le.achieveweb.service;

import com.le.achieveweb.dao.UserMapper;
import com.le.achieveweb.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    //autowire:可以对类成员变量、方法以及构造函数进行标注，完成自动装配工作
    UserMapper userMapper;//用接口名定义变量：这只是一个接口的引用，接口不能实例化对象，而接口的引用指向的是实现了接口方法的类的实例化对象。

    // 登录操作
    public String login(UserLogin user) {
        try {
            UserLogin userExistN = userMapper.queryByName(user.getUsername());
            if (userExistN != null) {
                String userExistP = userMapper.queryPswByName(user.getUsername());
                if (userExistP.equals(user.getPassword())) {
                    return "登录成功";
                } else {
                    return "密码错误" ;
                }
            } else {
                return "用户名不存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    // 注册操作
    public String register(UserLogin user) {
        try {
            UserLogin userExist = userMapper.queryByName(user.getUsername());
            if (user.getUsername().equals("")) {
                return "用户名不能为空";
            } else if (user.getPassword().equals("")) {
                return "密码不能为空";
            } else if (userExist != null) {
                return "用户已经存在";
            } else {
                userMapper.save(user);
                return "注册成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }}