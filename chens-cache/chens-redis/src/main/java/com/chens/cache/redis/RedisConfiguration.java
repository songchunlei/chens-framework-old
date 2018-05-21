package com.chens.cache.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * redis缓存配置
 *
 * @author songchunlei@qq.com
 * @create 2018/5/7
 */
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);


    private JedisPool pool;

    @Value("{spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWait;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private String database;

    @Bean
    public JedisPoolConfig constructJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(maxActive);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(maxIdle);
        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(true);
        return config;
    }

    @Bean(name = "pool")
    public JedisPool constructJedisPool() {
        String ip = this.host;
        String password = this.password;
        int database = Integer.parseInt(this.database);
        if (null == pool) {
            if (StringUtils.isBlank(password)) {
                pool = new JedisPool(constructJedisPoolConfig(), ip, port, timeout);
            } else {
                pool = new JedisPool(constructJedisPoolConfig(), ip, port, timeout, password, database);
            }
        }
        return pool;
    }

    /*
    @Bean
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(@SuppressWarnings("SpringJavaAutowiringInspection") RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //设置序列化工具
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    private void setSerializer(StringRedisTemplate template){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
    */

}
