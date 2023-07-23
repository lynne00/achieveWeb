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
//使用一个Handle来把Try，Catch中捕获的错误进行判定

//aop切在controller上
@ControllerAdvice
public class GlobalExceptionHandler {
    //日志记录器（Logger），用于在切面中记录日志。
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //只处理BusinessException异常类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException MyException = (BusinessException) e;
            //返回json数据
            return ResultUtil.error(MyException.getCode(), MyException.getMsg());
        }
        //在logger中记录未知异常
        LOGGER.error("系统异常={}", e.toString());
        return ResultUtil.error(EmBusinessErr.UNKNOWN_ERROR);

    }
}