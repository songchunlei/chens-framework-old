package com.chens.core.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 授权异常
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public class AuthException extends RuntimeException{
    private Integer code;

    private String message;

    public AuthException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AuthException(IBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.message = baseEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
