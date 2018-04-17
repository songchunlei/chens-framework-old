package com.chens.core.exception;

import com.chens.core.enums.IBaseEnum;

/**
 * 基本错误反馈
 *
 * @author songchunlei@qq.com
 * @create 2018/3/8
 */
public class BaseException  extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 743865762699909129L;

	private Integer code;

    private String message;

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(IBaseEnum baseEnum) {
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
