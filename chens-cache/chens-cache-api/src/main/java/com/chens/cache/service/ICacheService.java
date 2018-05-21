package com.chens.cache.service;

import com.chens.cache.vo.CacheKeyWrapper;
import com.chens.cache.vo.CacheWrapper;

import java.util.List;

/**
 * 缓存接口
 *
 * @author songchunlei@qq.com
 * @author 2018/5/9
 */
public interface ICacheService {

    /**
     * 向缓存存入key和value,并释放连接资源 如果key已经存在 则覆盖
     * @param key
     * @param value
     * @return 成功 返回OK 失败返回 0
     */
    String set(String key, String value);


    /**
     * 向缓存存入key和value,全参数
     * @param key key
     * @param value 值
     * @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX-- Only set the key if it already exist.
     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time expire time in the units of {@param #expx}
     * @return
     */
    String set(String key, String value,String nxxx,String expx,long time);


    /**
     * 通过key获取储存在缓存中的value 并释放连接
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    String get(String key);

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys
     *            一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    Long del(String... keys);

    /**
     * 解析Lua脚本
     * @param script 脚本
     * @param keys key列表
     * @param args 变量列表
     * @return
     */
    Object eval(String script, List<String> keys, List<String> args);

    /**
     * 通过key判断值得类型
     *
     * @param key
     * @return
     */
    String type(String key);
}
