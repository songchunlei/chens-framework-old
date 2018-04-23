package com.chens.file.exception;

import com.chens.core.enums.IBaseEnum;
import com.chens.file.constants.FileConstants;

/**
 * 文件错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum FileExceptionEnum implements IBaseEnum {


    FOLDER_PARENT_ID_IS_NULL(401,"父文件夹id为空"),
    FILE_MD5_ERROR(402,"创建md5失败"),
    FILE_READING_ERROR(403,"文件读取错误"),
    FILE_SAVE_ERROR(404,"文件保存失败"),
    FILE_IS_NOT_FOUND(405,"文件找不到"),
    FILE_MAX(406,"你最多只能建"+ FileConstants.MAX_FOLDER_COUNT+"个文件夹");


    private Integer code;

    private String message;

    FileExceptionEnum(Integer code, String message) {
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
