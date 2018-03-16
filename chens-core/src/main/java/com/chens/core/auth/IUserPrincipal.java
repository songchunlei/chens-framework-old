package com.chens.core.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前用户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
public interface IUserPrincipal {
    String getName(HttpServletRequest request);
}
