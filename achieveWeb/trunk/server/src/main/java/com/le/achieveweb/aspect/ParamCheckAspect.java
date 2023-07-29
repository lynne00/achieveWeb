package com.le.achieveweb.aspect;

import com.le.achieveweb.MVC.entity.UserLogin;
import com.le.achieveweb.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Before：这种拦截器先执行拦截代码，再执行目标代码。如果拦截器抛异常，那么目标代码就不执行了；
//
//@After：这种拦截器先执行目标代码，再执行拦截器代码。无论目标代码是否抛异常，拦截器代码都会执行；
//
//@AfterReturning：和@After不同的是，只有当目标代码正常返回时，才执行拦截器代码；
//
//@AfterThrowing：和@After不同的是，只有当目标代码抛出了异常时，才执行拦截器代码；
//
//@Around：能完全控制目标代码是否执行，并可以在执行前后、抛异常后执行任意拦截代码，可以说是包含了上面所有功能。
@Aspect
//切面
@Component
public class ParamCheckAspect {
    //日志记录器（Logger），用于在切面中记录日志。
    private final static Logger LOGGER = LoggerFactory.getLogger(ParamCheckAspect.class);
    @Autowired
    private GlobalExceptionHandler exceptionHandle;

    // Pointcut用来定义一个切点,拦截所有Controller中的方法
    @Pointcut("execution(public * com.le.achieveweb.MVC.controller.*.*(..))")
    //切入点方法(仅仅用于提供一个切入点标识符，供后面的通知注解引用)
    public void log() {
    }

    //在@Around环绕通知中，尝试捕获目标方法执行过程中抛出的异常，
    @Around("log()")
    //ProceedingJoinPoint 环绕通知中特殊类型的连接点，提供了对目标方法的控制权
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result result = null;
        try {
        } catch (Exception e) {
            // 如果目标方法抛出了异常，可以在这里捕获并进行处理
            // 使用exceptionHandle.exceptionGet(e)来处理异常
            return exceptionHandle.exceptionGet(e);
        }
        // 最后返回result，这将成为响应给客户端的结果
        if (result == null) {
            // result为null，继续执行目标方法，并返回目标方法的返回结果。
            return proceedingJoinPoint.proceed();
        } else {
            // 否则，返回目标方法的返回结果result
            return result;
        }
    }

    //目标代码正常执行打印输出结果
    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterReturing(Object object) {
        if (object != null) {
            LOGGER.info("response={}", object.toString());
        }
    }
}
