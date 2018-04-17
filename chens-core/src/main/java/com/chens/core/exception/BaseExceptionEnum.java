package com.chens.core.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 错误异常枚举
 * Created by songchunlei on 2018/3/8.
 */
public enum BaseExceptionEnum implements IBaseEnum {



    /**
     * token异常
     */
    TOKEN_EXPIRED(100, "token过期"),
    TOKEN_ERROR(101, "token验证失败"),
    TOKEN_IS_NULL(102,"token为空"),

    /**
     * 登录校验异常
     */
    AUTH_REQUEST_ERROR(200, "账号或密码错误"),
    AUTH_REQUEST_NO_USERNAME(201, "账号为空"),
    AUTH_REQUEST_NO_PASSWORD(202, "密码为空"),
    AUTH_REQUEST_NO_CODE(203, "验证码为空"),


    /**
     * 请求反馈
     */
    NO_DATA(300,"查询不到数据"),
    QUERY_ERROR(301,"查询异常"),
    NO_UPDATE(302,"更新失败"),
    NO_DELETE(303,"删除失败"),
    NO_SAVE(304,"保存失败"),
    VALIDATE_NOPASS(305, "校验不通过"),
    TIMEOUT(306, "请求超时"),


    /**
     * 流程请求
     */
    WORKFLOW_START_FAIL(501, "流程发起失败"),
    WORKFLOW_COMPLETE_FAIL(502, "流程办理失败"),

    /**
     * 请求异常
     */
    REQUEST_NULL(600, "请求有错误或请求数据为空"),
    DATA_REQUEST_ERROR(601, "数据传输失败"),
    SERVER_ERROR(602, "服务器异常"),

    /**
     * 注册异常
     */
    REGISTER_SYSTENANT_ERROR(700,"租户注册错误"),
    REGISTER_SYSUSER_ERROR(701,"账户注册错误");


    private Integer code;

    private String message;

    BaseExceptionEnum(Integer code, String message) {
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
