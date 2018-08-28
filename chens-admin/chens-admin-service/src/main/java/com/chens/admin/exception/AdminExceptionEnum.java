package com.chens.admin.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 错误异常枚举
 * Created by songchunlei on 2018/3/8.
 */
public enum AdminExceptionEnum implements IBaseEnum {


    TENANT_ID_IS_NULL(1100101,"查询租户ID为空"),
    ROLE_ID_IS_NULL(1100102,"查询角色ID为空");


    private Integer code;

    private String message;

    AdminExceptionEnum(Integer code, String message) {
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
