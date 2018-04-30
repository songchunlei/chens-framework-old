package com.chens.share.enums;

/**
 * 分享类型
 *
 * @author songchunlei@qq.com
 * @create 2018/4/29
 */
public enum ShareTypeEnum {
    PWD("密码分享", "PWD"),
    USER("内部用户","USER"),
    ORG("内部组织分享","ORG"),
    PUBLIC("公开","PUBLIC");

    private String display;
    private String code;

    private ShareTypeEnum(String display, String code) {
        this.display = display;
        this.code = code;

    }
    public String getDisplay() {
        return display;
    }

    public String getCode() {
        return code;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
