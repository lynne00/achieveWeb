package com.le.achieveweb.aspect;

import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.response.ResultError;
import com.le.achieveweb.util.ResultErrorUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//使用一个Handle来把Try，Catch中捕获的错误进行判定
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultError exceptionGet(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException MyException = (BusinessException) e;
            return ResultErrorUtil.error(MyException.getCode(), MyException.getMessage());
        }
        else{
            return ResultErrorUtil.error(EmBusinessErr.Unknown_Error);
        }
    }
}