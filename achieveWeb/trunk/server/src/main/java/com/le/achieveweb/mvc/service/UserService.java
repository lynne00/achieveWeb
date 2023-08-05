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
import com.le.achieveweb.mvc.model.entity.UserLogin;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.IdUtil;

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
    public Result login(LoginView user , HttpSession session) {
        String verCode = (String) session.getAttribute(Constants.CAPTCHACODE);
        UserLogin userExistN = userMapper.queryByName(user.getUsername());
        if(verCode==null){
            throw new BusinessException(EmBusinessErr.LOGIN_CAPTCHA_TIMEOUT);
        }
        else if (user.getCaptchaCode()==null) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        else if(!verCode.equals(user.getCaptchaCode())){
            throw new BusinessException(EmBusinessErr.LOGIN_CAPTCHA_ERROR);
        }
        else if (userExistN != null) {
            String userExistP = userMapper.queryHashPswByName(user.getUsername());
            // 验证密码
            boolean isPasswordMatch = PasswordUtil.checkPassword(user.getPassword(), userExistP);
            if (isPasswordMatch) {
                // 登陆成功 设置session 标记登录用户名和登陆状态
                session.setAttribute(Constants.USERNAME, user.getUsername());
                session.setAttribute(Constants.IS_LOGGED_IN, true);
                return ResultUtil.success();
            } else {
                throw new BusinessException(EmBusinessErr.LOGIN_ACCOUNT_PASSWORD_ERROR);
            }
        } else {
            throw new BusinessException(EmBusinessErr.LOGIN_ACCOUNT_NOT_EXISTED);
        }
    }


    // 注册操作
    public Result register(UserLogin user) {
        UserLogin userExist = userMapper.queryByName(user.getUsername());
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
            userMapper.save(user);
            //返回注册成功
            return ResultUtil.success();
        }
    }
}