package com.chens.coder;


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
    private int width = 400;

    /**
     * 二维码高度
     */
    private int height = 400;

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

    public QRConfig(String content, int width, int height, String format, String charCode) {
        this.content = content;
        if(width>this.width)
        {
            this.width = width;
        }
        if(height>this.height)
        {
            this.height = height;
        }
        this.format = format;
        this.charCode = charCode;
    }

    public QRConfig(String content, int width, int height, String format, String charCode, byte[] logo, int logoCent) {
        this.content = content;
        this.width = width;
        this.height = height;
        this.format = format;
        this.charCode = charCode;
        this.logo = logo;
        this.logoCent = logoCent;
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
