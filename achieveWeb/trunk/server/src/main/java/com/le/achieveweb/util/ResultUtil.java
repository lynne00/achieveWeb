package com.le.achieveweb.util;

import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.response.Result;

public class ResultUtil {
    /**
     * 成功信息
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("success");
        result.setData(data);
        return result;
    }


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
     * @param exceptionEnum EmBusinessErr枚举类定义错误信息
     * @return
     */
    public static Result error(EmBusinessErr exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param exceptionEnum BusinessException自定义异常类
     * @return
     */
    public static Result error(BusinessException exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
