package com.chens.bpm.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 文件错误反馈
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public class BpmException extends RuntimeException {

	private Integer code;

    private String message;

    public BpmException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BpmException(IBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.message = baseEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
