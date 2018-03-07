package com.chens.core.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 反馈结果集
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
public class Result implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5375579517756081365L;
	//反馈编码
    private int code;
    //反馈消息
    private String msg;
    //反馈数据
    private Object data;

    public Result() {
        super();
    }

    public Result(int code, String msg, Object data) {
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

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
