package com.le.achieveweb.MVC.service;

import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.util.PasswordUtil;
import org.mindrot.jbcrypt.BCrypt;
import com.le.achieveweb.MVC.dao.UserMapper;
import com.le.achieveweb.MVC.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.IdUtil;

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
                String userExistP = userMapper.queryHashPswByName(user.getUsername());
                // 验证密码
                boolean isPasswordMatch = PasswordUtil.checkPassword(user.getPassword(), userExistP);
                if (isPasswordMatch) {
                    return "登录成功";
                } else {
                    throw new BusinessException(EmBusinessErr.LOGIN_ACCOUNT_PASSWORD_ERROR, "帐号或密码错误");
                }
            } else {
                throw new BusinessException(EmBusinessErr.  LOGIN_ACCOUNT_NOT_EXISTED, "用户名不存在");
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
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION, "用户名不能为空");
            } else if (user.getPassword().equals("")) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION, "密码不能为空");
            } else if (userExist != null) {
                throw new BusinessException(EmBusinessErr.ACCOUNT_EXISTED, "帐号已存在");
            } else {
                String userId = IdUtil.randomUUID().replace("-", "");
                user.setId(userId);
                // BCrypt对密码进行加密
                String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
                user.setHashPassword(hashedPassword);
                userMapper.save(user);
                return "注册成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}