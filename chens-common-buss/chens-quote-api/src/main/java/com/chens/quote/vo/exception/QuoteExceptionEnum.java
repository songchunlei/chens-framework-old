package com.chens.quote.vo.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 引入错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum QuoteExceptionEnum implements IBaseEnum {


    FILE_ID_IS_NULL(20100,"关联文件id为空"),
    FILE_TYPE_IS_NULL(20101,"关联文件类型为空"),
    DATA_ID_IS_NULL(20102,"数据id为空"),
    DATA_TYPE_IS_NULL(20103,"数据类型为空");


    private Integer code;

    private String message;

    QuoteExceptionEnum(Integer code, String message) {
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
