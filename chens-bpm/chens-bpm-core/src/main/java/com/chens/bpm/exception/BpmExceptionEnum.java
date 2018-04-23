package com.chens.bpm.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 流程错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum BpmExceptionEnum implements IBaseEnum {


    STATUS_IS_NOT_PASS(30001,"该记录未审批通过，不能发布"),
    STATUS_IS_NOT_PUBLISH(30002,"状态不是已发布，不能取消"),
    CAN_NOT_PASS(30003,"该表单可能已被审批，请联系管理员");


    private Integer code;

    private String message;

    BpmExceptionEnum(Integer code, String message) {
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
