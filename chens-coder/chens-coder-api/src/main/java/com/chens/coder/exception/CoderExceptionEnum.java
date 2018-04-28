package com.chens.coder.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 文件错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum CoderExceptionEnum implements IBaseEnum {


    QR_CREATE_ERROR(1601,"二维码创建失败");


    private Integer code;

    private String message;

    CoderExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }


}
