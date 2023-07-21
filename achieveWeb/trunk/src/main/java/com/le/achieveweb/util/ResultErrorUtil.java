package com.le.achieveweb.util;

import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.response.ResultError;

public class ResultErrorUtil {
    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return
     */
    public static ResultError success(Object object) {
        ResultError result = new ResultError();
        result.setStatus(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }
    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static ResultError success() {
        return success(null);
    }
    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResultError error(Integer code, String msg) {
        ResultError result = new ResultError();
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
    public static ResultError error(EmBusinessErr exceptionEnum) {
        ResultError result = new ResultError();
        result.setStatus(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
