package com.chens.core.vo;

import com.alibaba.fastjson.JSON;
import com.chens.core.enums.IBaseEnum;

import java.io.Serializable;

/**
 * 反馈结果集
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 5375579517756081365L;

    private final int SUCCESS_CODE = 1;
    private final String EMPTY_MSG = "";


	//反馈编码
    private int code;
    //反馈消息
    private String msg;
    //反馈数据
    private T data;

    public Result(T data) {
        this.code = SUCCESS_CODE;
        this.msg = EMPTY_MSG;
        this.data = data;
    }

    public Result(String msg) {
        this.code = SUCCESS_CODE;
        this.msg = msg;
        this.data = null;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 枚举转结果集
     * @param baseEnum
     */
    public Result(IBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getMessage();
        this.data = null;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
