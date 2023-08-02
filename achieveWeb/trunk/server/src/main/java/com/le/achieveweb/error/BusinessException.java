package com.le.achieveweb.error;


//构造方法来定义错误信息
// RuntimeException运行时异常不需要强制处理,Exception定义了必须处理的异常,而RuntimeException定义的异常可以选择性的进行处理;
public class BusinessException extends RuntimeException implements CommonError{
    private final Exception exception;
    private final CommonError commonError;

    public BusinessException(CommonError commonError) {this(null, commonError);}
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
