package com.chens.core.enums;

/**
 * 枚举通用抽象接口
 * Created by songchunlei on 2018/3/8.
 */
public interface IBaseEnum {
    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();

    /**
     * 获取异常编码
     */
    void setCode(Integer code);

    /**
     * 获取异常信息
     */
    void setMessage(String message);
}
