package com.chens.auth.vo;

/**
 * jwt通用接口类-信息反馈
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/22
 */
public interface IJwtInfo {

    /**
     * 用户id
     * @return
     */
    String getId();

    /**
     * 用户账号
     * @return
     */
    String getUsername();

    /**
     * 用户名称
     * @return
     */
    String getName();

    /**
     * 租户id
     * @return
     */
    String getTenantId();
}
