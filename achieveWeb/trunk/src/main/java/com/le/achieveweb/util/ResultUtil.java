package com.le.achieveweb.util;

import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.response.Result;

public class ResultUtil {
    /**
     * 返回成功，传入返回体具体出參
     *
     * @param
     * @return
     */
    public static Result loginSuccess() {
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("login success");
        result.setData(null);
        return result;
    }
    public static Result registerSuccess() {
        Result result = new Result();
        result.setStatus(1);
        result.setMsg("register success");
        result.setData(null);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */

    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setStatus(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    /**
     * 返回异常信息，在已知的范围内
     *
     * @param exceptionEnum
     * @return
     */
    public static Result error(EmBusinessErr exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
