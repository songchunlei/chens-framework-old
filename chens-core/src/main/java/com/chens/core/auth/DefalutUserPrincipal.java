package com.chens.core.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认获取当前用户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
public class DefalutUserPrincipal implements IUserPrincipal {
    @Override
    public String getName(HttpServletRequest request) {
        if(request.getUserPrincipal()==null) {
            return null;
        }
        return request.getUserPrincipal().getName();
    }
}
