package com.le.achieveweb.aspect;

import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.response.Result;
import com.le.achieveweb.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
//使用一个Handle对异常进行管理

//aop切在controller上
@ControllerAdvice
public class GlobalExceptionHandler {
    //日志记录器（Logger），用于在切面中记录日志。
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //处理异常类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        //如果是运行的已标记异常
        if (e instanceof BusinessException) {
            BusinessException MyException = (BusinessException) e;
            //在logger中记录运行异常
            LOGGER.error("运行错误={}", ResultUtil.error(MyException));
            //返回json数据
            return ResultUtil.error(MyException);
        }
        //在logger中记录未知异常
        LOGGER.error("系统异常={}", e.toString());
        return ResultUtil.error(EmBusinessErr.UNKNOWN_ERROR);

    }
}