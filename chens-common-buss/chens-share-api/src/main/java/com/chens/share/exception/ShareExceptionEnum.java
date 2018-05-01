package com.chens.share.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 分享异常枚举
 * @author songchunlei
 * @date  2018-04-29
 */
public enum ShareExceptionEnum implements IBaseEnum {


    QR_SAVEPATH_IS_NULL(20201, "二维码保存位置为空"),
    SHARE_ROOT_URL_IS_NULL(20202, "分享网址为空");

    private Integer code;

    private String message;

    ShareExceptionEnum(Integer code, String message) {
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
