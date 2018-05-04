package com.chens.bpm.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 流程错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum BpmExceptionEnum implements IBaseEnum {


    WORKFLOW_START_FAIL(30001, "流程发起失败"),
    WORKFLOW_COMPLETE_FAIL(30002, "流程办理失败"),
    STATUS_IS_NOT_PASS(30003,"该记录未审批通过，不能发布"),
    STATUS_IS_NOT_PUBLISH(30004,"状态不是已发布，不能取消"),
    CAN_NOT_PASS(30005,"该表单可能已被审批，请联系管理员"),
    COMPLETE_TASK_ERROR(30006,"审批异常"),
    SINGLE_TASK_SELECT_USER_ERROR(30007,"单处理人任务节点只能选择一个处理人"),
    WORKFLOW_IS_STARTED(30008,"流程已发起"),
    ONLY_MODIFY_CREATED_DATA(30009,"只能修改自己创建的数据"),
    NOT_CURRENT_ASSIGNEE_CANNOT_SUBMIT(30010,"非当前任务处理人员，无权审批");


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
