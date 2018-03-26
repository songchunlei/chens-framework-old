package com.chens.core.constants;

/**
 * 通用常量
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
public final class CommonConstants {

    //过滤非租户表
    public static final String NO_TENANT_TABLENAME = "[sys_menu][sys_user][sys_dict_type][sys_permission][sys_role][sys_tenant]";

    //菜单根节点
    public static final String BASE_TREE_ROOT="-1";

    //默认密码
    public static final String DEFAULT_PASSWORD = "12345678";
    //默认密码长度
    public static final int DEFAULT_PASSWORD_LENGTH = 16;
    //系统操作默认用户id
    public static final String SYSTEM_USER_ID="9999999999";

    //基础字段
    public static final String BASE_ENTITY_CREATE_TIME = "createTime";
    public static final String BASE_ENTITY_UPDATE_TIME = "updateTime";
    public static final String BASE_ENTITY_CREATE_BY = "createBy";
    public static final String BASE_ENTITY_UPDATE_BY = "updateBy";
    public static final String BASE_ENTITY_TENANT_ID = "tenantId";
    public static final String BASE_ENTITY_IS_DELETE="isDelete";

    //授权token名称
    public static final String AUTH_TOKEN_KEY = "auth_token";

    /** JWT常量 start**/
    public static final String JWT_TOKEN_SCOPE = "scope";
    public static final String JWT_TOKEN_GRANTTYPE="grantType";
    //用户名称
    public static final String JWT_TOKEN_USER= "user";
    //用户名
    public static final String JWT_TOKEN_USERNAME="userName";
    //租户id
    public static final String JWT_TOKEN_TENANTID="tenantId";
    /** JWT常量 end **/

    //上下文固定Key
    public static final String CONTEXT_KEY_USER_ID = "userId";
    public static final String CONTEXT_KEY_USER_NAME = "user";
    public static final String CONTEXT_KEY_USERNAME = "userName";
    public static final String CONTEXT_KEY_USER_TOKEN = "token";
    public static final String CONTEXT_KEY_USER_TANENTID = "tenantId";


}
