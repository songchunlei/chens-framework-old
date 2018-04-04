package com.chens.admin.enums;

/**
 * 租户类型
 * @auther songchunlei
 * @create 2018/3/22
 */
public enum TenantType {

    NEW("待审批", "NEW"),
    APPROVED("审批通过","APPROVED");

    private String display;
    private String code;

    private TenantType(String display, String code ) {
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
