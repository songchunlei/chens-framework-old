package com.chens.coder;


import com.chens.core.util.StringUtils;

/**
 * 配置
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public class QRConfig {

    /**
     * 内容
     */
    private String content;

    /**
     * 二维码宽度
     */
    private int width = 200;

    /**
     * 二维码高度
     */
    private int height = 200;

    /**
     * 生成图片格式
     */
    private String format = "png";

    /**
     * 字符串编码
     */
    private String charCode = "utf-8";

    /**
     * logo 文件字节流
     */
    private byte[] logo;

    /**
     * logo占比,1.2.3.4.5.6
     */
    private int logoCent = 5;

    public QRConfig() {
    }

    /**
     * 构建
     * @param content
     * @param width
     * @param height
     * @param format
     * @param charCode
     */
    public QRConfig(String content, int width, int height, String format, String charCode) {
        this.setInit(content,width,height,format,charCode);
    }

    /**
     * 构建
     * @param content
     * @param width
     * @param height
     * @param format
     * @param charCode
     * @param logo
     * @param logoCent
     */
    public QRConfig(String content, int width, int height, String format, String charCode, byte[] logo, int logoCent) {
        this.setInit(content,width,height,format,charCode);
        this.logo = logo;
        this.logoCent = logoCent;

    }

    /**
     * 通用初始化
     * @param content
     * @param width
     * @param height
     * @param format
     * @param charCode
     */
    private void setInit(String content, int width, int height, String format, String charCode)
    {
        this.content = content;
        if(width>this.width)
        {
            this.width = width;
        }
        if(height>this.height)
        {
            this.height = height;
        }
        if(StringUtils.isNotEmpty(format))
        {
            this.format = format;
        }
        if(StringUtils.isNotEmpty(charCode))
        {
            this.charCode = charCode;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public int getLogoCent() {
        return logoCent;
    }

    public void setLogoCent(int logoCent) {
        this.logoCent = logoCent;
    }
}
