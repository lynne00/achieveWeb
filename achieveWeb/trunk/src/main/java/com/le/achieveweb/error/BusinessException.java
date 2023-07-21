package com.le.achieveweb.error;

import java.io.PrintWriter;
import java.io.StringWriter;
//构造方法来定义错误信息
public class BusinessException extends Exception implements CommonError{
    private final Exception exception;
    private final CommonError commonError;

    public BusinessException(CommonError commonError) {
        this(null, commonError);
    }
    public BusinessException(CommonError commonError, String msg) {
        this(null, commonError, msg);
    }
    public BusinessException(Exception e, CommonError commonError) {
        this(e, commonError, commonError.getMsg());
    }

    public BusinessException(Exception e, CommonError commonError, String msg) {
        super();
        this.exception = e;
        this.commonError = commonError;
        this.commonError.setMsg(msg);
    }

    @Override
    public int getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public void setMsg(String msg) {
        this.commonError.setMsg(msg);
    }
}
