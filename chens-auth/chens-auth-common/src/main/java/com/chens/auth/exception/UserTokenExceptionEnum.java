package com.chens.auth.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 用户token枚举值
 * @auther songchunlei
 * @create 2018/3/22
 */
public enum UserTokenExceptionEnum implements IBaseEnum {

    TOKEN_EXPIRED(10001, "用户token已失效"),
    TOKEN_ERROR(10002, "用户token验证失败"),
    TOKEN_IS_NULL(10003, "用户token为空");;

    UserTokenExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
