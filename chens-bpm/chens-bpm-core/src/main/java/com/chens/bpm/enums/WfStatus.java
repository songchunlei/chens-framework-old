package com.chens.bpm.enums;

/**
 * 流程服务状态
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public enum WfStatus {
    DELETED("DELETED","已删除"),
    WAITING("WAITING", "待提交"),
    CHECKING("CHECKING", "审核中"),
    PASS("PASS", "审核通过"),
    UN_PASS("UN_PASS", "未通过"),
    PUBLISHED("PUBLISHED","已发布"),
    CANCEL_PUBLISHED("CANCEL_PUBLISHED","取消发布");


    String code;
    String message;

    WfStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String status) {
        if (status == null) {
            return "";
        } else {
            for (WfStatus s : WfStatus.values()) {
                if (s.getCode().equals(status)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
