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
public class Result implements Serializable {

	private static final long serialVersionUID = 5375579517756081365L;

    private static final String EMPTY_DATA = "{}";

	//反馈编码
    private Integer code;
    //反馈消息
    private String msg;
    //反馈数据
    private Object data;


    public Result(){

    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 枚举转结果集
     * @param baseEnum
     */
    public Result(IBaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getMessage();
        this.data = EMPTY_DATA;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
