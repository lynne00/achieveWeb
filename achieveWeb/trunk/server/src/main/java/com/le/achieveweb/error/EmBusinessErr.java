package com.le.achieveweb.error;
//枚举类记录已知错误信息
public enum EmBusinessErr implements CommonError {
    LOGIN_ACCOUNT_NOT_EXISTED(2000, "账号不存在"),
    LOGIN_ACCOUNT_PASSWORD_ERROR(2001, "账号或密码错误"),
    ACCOUNT_EXISTED(2002, "账号已存在"),
    INPUT_BLANK(2003, "输入不能为空"),
    LOGIN_CAPTCHA_ERROR(2004, "验证码错误"),
    LOGIN_CAPTCHA_TIMEOUT(2005, "验证码超时"),
    PARAMETER_INVALIDATION(1000, "参数不合法"),
    LOGIN_NOT_EXISTED(2006, "未登录"),
    UNKNOWN_ERROR(000, "未知错误");
    private final Integer errCode;
    private String msg;
    EmBusinessErr(Integer errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }
    @Override
    public int getCode() {
        return this.errCode;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}


