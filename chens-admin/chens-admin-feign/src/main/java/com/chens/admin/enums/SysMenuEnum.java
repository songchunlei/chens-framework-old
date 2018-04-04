package com.chens.admin.enums;

/**
 * 菜单枚举
 * @auther songchunlei
 * @create 2018/4/4
 */
public enum SysMenuEnum {

    MOUDLE("模块", "MOUDLE"),
    PAGE("页面","PAGE"),
    BUTTON("按钮","BUTTON");

    private String display;
    private String code;

    private SysMenuEnum(String display, String code ) {
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
