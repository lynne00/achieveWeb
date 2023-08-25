package com.le.achieveweb.mvc.service;

import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.mvc.model.vo.LoginView;
import com.le.achieveweb.response.Result;
import com.le.achieveweb.util.Constants;
import com.le.achieveweb.util.PasswordUtil;
import com.le.achieveweb.util.ResultUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.le.achieveweb.mvc.dao.UserMapper;
import com.le.achieveweb.mvc.model.entity.User;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
//代替Autowired，可以用来确保对于一个类的所有实例被正确地初始化。参数类型必须要是final的
@RequiredArgsConstructor
public class UserService {
    //用接口名定义变量：这只是一个接口的引用，接口不能实例化对象，而接口的引用指向的是实现了接口方法的类的实例化对象。
    private final UserMapper userMapper;

    //    public UserService(UserMapper userMapper) {
    //        this.userMapper = userMapper;
    //    }ss
    // 登录操作
    public Result login(LoginView user, HttpSession session) {
        String verCode = (String) session.getAttribute(Constants.CAPTCHACODE);
        User userExistN = userMapper.queryByNameRole(user.getUsername(), user.getRole());
        if (user.getUsername() == null || user.getUsername().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        if (verCode == null) {
            throw new BusinessException(EmBusinessErr.LOGIN_CAPTCHA_TIMEOUT);
        } else if (user.getCaptchaCode() == null) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        } else if (!verCode.equals(user.getCaptchaCode())) {
            throw new BusinessException(EmBusinessErr.LOGIN_CAPTCHA_ERROR);
        }
        if (userExistN != null) {
            String userExistP = userMapper.queryHashPswByName(user.getUsername());
            // 验证密码
            boolean isPasswordMatch = PasswordUtil.checkPassword(user.getPassword(), userExistP);
            if (isPasswordMatch) {
                // 登陆成功 设置session 标记登录用户名和登陆状态
                session.setAttribute(Constants.USERNAME, user.getUsername());
                session.setAttribute(Constants.USERID,userMapper.queryUserIdByName(user.getUsername()));
                session.setAttribute(Constants.IS_LOGGED_IN, true);
                return ResultUtil.success("登录成功");
            } else {
                throw new BusinessException(EmBusinessErr.LOGIN_ACCOUNT_PASSWORD_ERROR);
            }
        } else {
            throw new BusinessException(EmBusinessErr.LOGIN_ACCOUNT_NOT_EXISTED);
        }
    }

    // 注册操作
    public Result register(User user) {
        User userExist = userMapper.queryByName(user.getUsername());
        if (user.getUsername() == null || user.getUsername().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        } else if (userExist != null) {
            throw new BusinessException(EmBusinessErr.ACCOUNT_EXISTED);
        } else {
            String userId = IdUtil.simpleUUID();
            user.setId(userId);
            // BCrypt对密码进行加密
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            //设置创建时间和修改时间
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdateAt(LocalDateTime.now());
            userMapper.save(user);
            //返回注册成功
            return ResultUtil.success("注册成功");
        }
    }

    //注销帐号
    public Result logOut(HttpSession session) {
        session.invalidate();
        return ResultUtil.success("退出成功");
    }

    //获取用户信息
    public Result getInfo(HttpSession session) {
        User userInfo = userMapper.queryByName((String) session.getAttribute(Constants.USERNAME));
        return ResultUtil.success(userInfo);
    }

    // 用户信息修改
    public Result updateInfo(User user, HttpSession session) {
        User userInfo;
        HashMap<String, Object> map = new HashMap();
        map.put("username", session.getAttribute(Constants.USERNAME));
        map.put("sex", user.getSex());
        map.put("email", user.getEmail());
        map.put("updateAt", LocalDateTime.now());
        userMapper.updateUserInfo(map);
        userInfo = userMapper.queryByName((String) session.getAttribute(Constants.USERNAME));
        return ResultUtil.success(userInfo);
    }

    // 用户修改密码
    public Result updatePassword(String password, HttpSession session) {
        HashMap<String, Object> map = new HashMap();
        // BCrypt对密码进行加密
        String hashedPassword = PasswordUtil.hashPassword(password);
        map.put("password",hashedPassword);
        map.put("username", session.getAttribute(Constants.USERNAME));
        userMapper.updateUserPassword(map);
        return ResultUtil.success("修改成功");
    }


}