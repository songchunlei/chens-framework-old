package com.chens.folder.exception;

import com.chens.core.enums.IBaseEnum;
import com.chens.folder.constants.FolderConstants;

/**
 * 文件夹错误异常枚举
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public enum FolderExceptionEnum implements IBaseEnum {


    FOLDER_PARENT_ID_IS_NULL(401,"父文件夹id为空"),
    FOLDER_MAX(406,"你最多只能建"+ FolderConstants.MAX_FOLDER_COUNT+"个文件夹");


    private Integer code;

    private String message;

    FolderExceptionEnum(Integer code, String message) {
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
