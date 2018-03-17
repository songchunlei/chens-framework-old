package com.chens.core.constants;

/**
 * 通用常量
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
public final class CommonContants {

    //过滤非租户表
    public static final String NO_TENANT_TABLENAME = "[sys_menu][sys_user][sys_dict_type][sys_permission][sys_role][sys_tenant]";

    //默认密码
    public static final String DEFAULT_PASSWORD = "12345678";
    //默认密码长度
    public static final int DEFAULT_PASSWORD_LENGTH = 16;

    //基础字段
    public static final String BASE_ENTITY_CREATE_TIME = "create_time";
    public static final String BASE_ENTITY_UPDATE_TIME = "update_time";
    public static final String BASE_ENTITY_CREATE_BY = "create_by";
    public static final String BASE_ENTITY_UPDATE_BY = "update_by";
    public static final String BASE_ENTITY_TENANT_ID = "tenant_id";

    //授权token名称
    public static final String AUTH_TOKEN_KEY = "auth_token";

    //JWT常量
    public static final String JWT_TOKEN_SCOPE = "scope";
    public static final String JWT_TOKEN_GRANTTYPE="grantType";
    public static final String JWT_TOKEN_USERNAME="userName";
    public static final String JWT_TOKEN_EMAIL="email";
    public static final String JWT_TOKEN_PHONE= "phone";


}
