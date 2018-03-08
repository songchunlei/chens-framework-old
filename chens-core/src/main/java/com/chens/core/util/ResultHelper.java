package com.chens.core.util;

import com.chens.core.entity.Result;

/**
 * 结果集工具
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
public final class ResultHelper<T> {
    private static final int SUCCESS_CODE = 1;
    private static final int ERROR_CODE = 0;
    private static final String EMPTY_DATA = "{}";
    private static final String EMPTY_MSG = "";

    public static Result getInstance() {
        return new Result();
    }

    /**
     * 错误反馈通用方法
     * @param code
     * @param msg
     * @return
     */
    public static Result<String> getError(int code, String msg) {
        if (msg == null) {
            msg = EMPTY_MSG;
        }
        return new Result(code, msg, EMPTY_DATA);
    }

    /**
     * 错误反馈
     * @param error
     * @return
     */
    public static Result<String> getError(String error) {
        return getError(ERROR_CODE, error);
    }

    /**
     * 错误反馈
     * @param code
     * @return
     */
    public static Result<String> getError(int code) {
        return getError(code, EMPTY_MSG);
    }

    /**
     * 错误反馈
     * @return
     */
    public static Result<String> getError() {
        return getError(ERROR_CODE, EMPTY_MSG);
    }

    /**
     * 成功反馈通用方法
     * @param msg
     * @param data
     * @return
     */
    public static Result<Object> getSuccess(String msg, Object data) {
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
    public static Result<Object> getSuccess(Object data) {
        return getSuccess( EMPTY_MSG,data);
    }

    /**
     * 成功反馈
     * @param msg
     * @return
     */
    public static Result<Object> getSuccess(String msg) {
        return getSuccess( msg,EMPTY_DATA);
    }

    /**
     * 成功反馈
     * @return
     */
    public static Result<Object> getSuccess() {
        return getSuccess( EMPTY_MSG,EMPTY_DATA);
    }



}
