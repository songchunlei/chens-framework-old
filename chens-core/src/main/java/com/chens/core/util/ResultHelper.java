package com.chens.core.util;

import com.chens.core.enums.IBaseEnum;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 结果集工具
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
public final class ResultHelper {
    private static final int ERROR_CODE = 1;
    private static final int SUCCESS_CODE = 1;
    private static final String EMPTY_DATA = "{}";
    private static final String EMPTY_MSG = "";

    public static Result getInstance() {
        return new Result();
    }


    /**
     * 成功反馈通用方法
     * @param msg
     * @param data
     * @return
     */
    public static Result getSuccess(String msg, Object data) {
        if (msg == null) {
            msg = EMPTY_MSG;
        }
        if (data == null) {
            data = EMPTY_DATA;
        }
        return new Result(SUCCESS_CODE, msg, data);
    }

    /**
     * 成功反馈
     * @param data
     * @return
     */
    public static Result getSuccess(Object data) {
        return getSuccess( EMPTY_MSG,data);
    }

    /**
     * 成功反馈
     * @param msg
     * @return
     */
    public static Result getSuccess(String msg) {
        return getSuccess( msg,EMPTY_DATA);
    }

    /**
     * 成功反馈
     * @return
     */
    public static Result getSuccess() {
        return getSuccess( EMPTY_MSG,EMPTY_DATA);
    }


    public static Result getError(IBaseEnum baseEnum){
        return new Result(baseEnum);
    }

    public static Result getError(Integer code,String msg){
        return new Result(code,msg);
    }

    public static Result getError(String msg){
        return new Result(ERROR_CODE,msg);
    }

}
