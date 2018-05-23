package com.chens.core.service;

/**
 * 加密
 *
 * @author songchunlei@qq.com
 * @create 2018/5/23
 */
public interface IPasswordCoder {

    /**
     * 加密
     * @param pwd
     * @return
     */
    String encoder(String pwd);

    /**
     * 比较
     * @param newPwd
     * @param oldPwd
     * @return
     */
    boolean matches(String newPwd,String oldPwd);
}
