package com.chens.core.context;

import com.chens.core.constants.CommonConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理基础上下文方法
 * 参考了 https://gitee.com/geek_qi/ace-security
 * @auther songchunlei@qq.com
 * @create 2018/3/21
 */
public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static String getUserId(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getName(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return returnObjectValue(value);
    }


    public static String getUserName(){
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }

    public static String getToken(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return returnObjectValue(value);
    }

    public static String getTenantId(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TANENTID);
        return returnObjectValue(value);
    }

    public static void setUserId(String userId){set(CommonConstants.CONTEXT_KEY_USER_ID,userId);}
    public static void setName(String name){set(CommonConstants.CONTEXT_KEY_USER_NAME,name);}
    public static void setUserName(String userName){set(CommonConstants.CONTEXT_KEY_USERNAME,userName);}
    public static void setToken(String token){set(CommonConstants.CONTEXT_KEY_USER_TOKEN,token);}
    public static void setTenantId(String tenantId){set(CommonConstants.CONTEXT_KEY_USER_TANENTID,tenantId);}

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    private static String returnObjectValue(Object value) {
        return value==null?null:value.toString();
    }


}
