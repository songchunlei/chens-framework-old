package com.chens.auth.constants;

/**
 * 授权通用常量
 *
 * @author songchunlei@qq.com
 * @create 2018/3/25
 */
public final class AuthConstants {

    /**
     * 默认JWT配置-加密key
     */
    public static final String JWT_DEFAULT_KEY = "1dhdkah122i1hdsakbkjsdbks";

    /**
     * 默认JWT配置-发行来源
     */
    public static final String JWT_DEFAULT_ISS = "chens-auth-client";

    /**
     * 默认JWT配置-有效时长（分钟）
     */
    public static final int JWT_DEFAULT_EXPM = 120;

    /**
     * 默认JWT配置-用户端token名
     */
    public static final String JWT_DEFAULT_USERHEADER = "Authorization";

    /**
     * key-默认用户id
     */
    public static final String KEY_USER_ID = "UserIdKey";

    /**
     * key-默认用户username
     */
    public static final String KEY_USER_NAME = "UserNameKey";

    /**
     * key-默认用户name
     */
    public static final String KEY_NAME = "NameKey";

    /**
     * key-默认用户tenant_id
     */
    public static final String KEY_TENANT_ID = "tenantIdKey";

}
