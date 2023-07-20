package com.le.achieveweb.aspect;
import com.le.achieveweb.MVC.entity.UserLogin;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParamCheckAspect {
    // Pointcut用来定义一个切面,拦截所有Controller中的方法
    @Pointcut("execution(public * com.le.achieveweb.MVC.controller.*.*(..))")
    public void controllerMethod() {
    }
    // Before表示controllerMethod将在目标方法执行前执行
    @Before("controllerMethod()")
    //JoinPoint用它来获取一个签名，利用签名可以获取请求的包名、方法名，包括参数
    public void checkParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        // 确保参数不为空
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof UserLogin) {
                    UserLogin user = (UserLogin) arg;
                    if (user.getUsername() == null || user.getUsername().isEmpty()) {
                        throw new IllegalArgumentException("用户名不能为空");
                    }
                    if (user.getPassword() == null || user.getPassword().isEmpty()) {
                        throw new IllegalArgumentException("密码不能为空");
                    }
                }
            }
        }
    }
}
