package com.chens.file.enums;

import com.chens.core.util.StringUtils;

/**
 * 文件夹类型
 *
 * @author songchunlei@qq.com
 * @create 2018/4/15
 */
public enum FolderTypeEnum {

    COMMON("通用文件见","COMMON");

    private String display;
    private String code;

    private FolderTypeEnum(String display, String code) {
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
