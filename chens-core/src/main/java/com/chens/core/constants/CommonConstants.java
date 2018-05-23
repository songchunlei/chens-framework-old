package com.chens.core.constants;

/**
 * 通用常量
 *
 * @author songchunlei@qq.com
 * @create 2018/3/8
 */
public final class CommonConstants {

    /**
     * 过滤非租户表
     */
    public static final String NO_TENANT_TABLENAME = "[sys_menu][sys_user][sys_dict_type][sys_permission]" +
            "[sys_role][sys_tenant][sys_token][sys_log][sys_user_role][sys_role_menu]";

    /**
     * MD5
     */
    public static final String MD5 = "MD5";

    /**
     * utf-8
     */
    public static final String CHARACTER_UTF8 = "UTF-8";

    /**
     * ISO8859-1
     */
    public static final String CHARACTER_ISO88591= "ISO8859-1";

    /**
     * 根节点
     */
    public static final String BASE_TREE_ROOT="-1";
    public static final String BASE_TREE_ROOT_NAME="根节点";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "12345678";

    /**
     * 工作人员角色
     */
    public static final String SYSROLE_COMMON_ROLE = "COMMON_ROLE";

    /**
     * 默认密码长度
     */
    public static final int DEFAULT_PASSWORD_LENGTH = 16;

    /**
     * 系统操作默认用户id
     */
    public static final String SYSTEM_USER_ID="9999999999";

    /**
     * 基础实体属性
     */
    public static final String BASE_ENTITY_CREATE_TIME = "createTime";
    public static final String BASE_ENTITY_UPDATE_TIME = "updateTime";
    public static final String BASE_ENTITY_CREATE_BY = "createBy";
    public static final String BASE_ENTITY_UPDATE_BY = "updateBy";
    public static final String BASE_ENTITY_CREATE_BY_NAME = "createByName";
    public static final String BASE_ENTITY_UPDATE_BY_NAME = "updateByName";
    public static final String BASE_ENTITY_TENANT_ID = "tenantId";
    public static final String BASE_ENTITY_IS_DELETE="isDelete";

    /**
     * 基础字段
     */
    public static final String BASE_COLUMN_ID = "id";
    public static final String BASE_COLUMN_CREATE_TIME = "create_time";
    public static final String BASE_COLUMN_UPDATE_TIME = "update_time";
    public static final String BASE_COLUMN_CREATE_BY = "create_by";
    public static final String BASE_COLUMN_UPDATE_BY = "update_by";
    public static final String BASE_COLUMN_CREATE_BY_NAME = "create_by_name";
    public static final String BASE_COLUMN_UPDATE_BY_NAME = "update_by_name";
    public static final String BASE_COLUMN_TENANT_ID = "tenant_id";
    public static final String BASE_COLUMN_IS_DELETE="is_delete";

    /**
     * 授权token名称
     */
    public static final String AUTH_TOKEN_KEY = "auth_token";

    /**
     * JWT常量
     */
    public static final String JWT_TOKEN_SCOPE = "scope";
    public static final String JWT_TOKEN_GRANTTYPE="grantType";

    /**
     * JWT常量-用户名称
     */
    public static final String JWT_TOKEN_USER= "user";

    /**
     * JWT常量-用户名
     */
    public static final String JWT_TOKEN_USERNAME="userName";

    /**
     * JWT常量-租户id
     */
    public static final String JWT_TOKEN_TENANTID="tenantId";

    /**
     * 上下文固定Key
     */
    public static final String CONTEXT_KEY_USER_ID = "userId";
    public static final String CONTEXT_KEY_USER_NAME = "user";
    public static final String CONTEXT_KEY_USERNAME = "userName";
    public static final String CONTEXT_KEY_USER_TOKEN = "token";
    public static final String CONTEXT_KEY_USER_TANENTID = "tenantId";

    /**
     * 基本返回消息
     */
    public static final String SAVE_SUCCESS="保存成功";
    public static final String DELETE_SUCCESS="删除成功";
    public static final String QUERY_SUCCESS="查询成功";
    public static final String QUERY_ERROR="数据为空";


}
