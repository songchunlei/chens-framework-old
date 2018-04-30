package com.chens.core.enums;

/**
 * 文件类型
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/11
 */
public enum FileTypeEnum {
    FOLDER("文件夹", "FOLDER"),
    EXCEL("表格","EXCEL"),
    WORD("文档","WORD"),
    TEXT("文本","TXT"),
    PPT("PPT","PPT"),
    PIC("图片","PIC"),
    VEDIO("视频","VEDIO");

    private String display;
    private String code;

    private FileTypeEnum(String display, String code) {
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
