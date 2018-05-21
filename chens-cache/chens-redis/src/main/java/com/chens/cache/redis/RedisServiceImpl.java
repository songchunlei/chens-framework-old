package com.chens.cache.redis;

import com.chens.cache.service.ICacheService;
import com.chens.cache.vo.CacheKeyWrapper;
import com.chens.cache.vo.CacheWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * redis缓存实现
 *
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
@Service("redisService")
public class RedisServiceImpl implements ICacheService{

    Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private JedisPool pool;

    @Override
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";
        } finally {
            closeJedis(jedis);
        }
    }

    @Override
    public String set(String key, String value,String nxxx,String expx,long time) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value,nxxx,expx,time);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";
        } finally {
            closeJedis(jedis);
        }
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0L;
        } finally {
            closeJedis(jedis);
        }
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.eval(script, keys, args);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";
        } finally {
            closeJedis(jedis);
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.type(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            closeJedis(jedis);
        }
        return res;
    }

    /**
     * 关闭Jedis
     * @param jedis
     */
    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
