package com.chens.file.enums;

import com.chens.core.util.StringUtils;

/**
 * 文件类型
 *
 * @author songchunlei@qq.com
 * @create 2018/4/15
 */
public enum FileTypeEnum {

    PIC("图片","PIC","icon-tupian","[.jpg][.png][.jpeg][.gif][.bmp][.ico]"),
    WORD("文档","WORD","icon-word1","[.doc][.docx]"),
    EXCEL("表格","EXCEL","icon-excel","[.xls]"),
    PPT("演示稿","PPT","icon-ppt","[.ppt][.pptx]"),
    TXT("文本","TXT","ico-txt","[.txt]"),
    VIDEO("视频","VIDEO","ico-video","[.avi][.rmvb][wmv][flv][swf][rm]"),
    AUDIO("音频","AUDIO","ico-audio","[.mp3]"),
    PSD("PhotoShop文件","PSD","ico-psd","[.psd]"),
    ZIP("压缩文件","ZIP","ico-zip","[.rar][.zip][.tar][.gz][.bz2]"),
    HTML("网页文件","HTML","ico-html","[.chm][.html][.htm]"),
    OTHER("其他文件","OTHER","ico-other","[other]");

    private String display;
    private String code;
    private String icon;
    private String includeExt;

    FileTypeEnum(String display, String code, String icon, String includeExt) {
        this.display = display;
        this.code = code;
        this.icon = icon;
        this.includeExt = includeExt;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIncludeExt() {
        return includeExt;
    }

    public void setIncludeExt(String includeExt) {
        this.includeExt = includeExt;
    }

    /**
     * 根据拓展名获取文件类型枚举
     * @param ext
     * @return
     */
    public static FileTypeEnum getEnumByExt(String ext)
    {
        for (FileTypeEnum fileTypeEnum:FileTypeEnum.values()) {
            if(StringUtils.isNotEmpty(ext) && fileTypeEnum.getIncludeExt().indexOf(ext)>-1)
            {
                return fileTypeEnum;
            }
        }
        return FileTypeEnum.OTHER;
    }
}
