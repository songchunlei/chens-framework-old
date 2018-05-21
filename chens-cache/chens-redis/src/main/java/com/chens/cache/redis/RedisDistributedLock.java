package com.chens.cache.redis;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.chens.cache.IDistributedLock;
import com.chens.cache.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

/**
 * redis实现分布式锁
 *
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
@Component
public class RedisDistributedLock implements IDistributedLock {

	@Value(value="classpath:unlock.lua")
	private Resource resource;

	@Autowired
	private ICacheService cacheService;
	
	private static final String LOCK_NODE ="LOCK";
	
	private ThreadLocal<String> local = new ThreadLocal<>();
	
	@Override
	public boolean getLock() {
		String value = UUID.randomUUID().toString();
		String ret = cacheService.set(LOCK_NODE, value, "NX", "PX", 10000);
		if(!StringUtils.isEmpty(ret) && "OK".equals(ret)){
			local.set(value);
			return true;
		}
		return false;
	}

	@Override
	public void releaseLock() {
		String script = "";
		try {
			script = FileCopyUtils.copyToString(new FileReader(resource.getFile()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> keys = new ArrayList<String>();
		keys.add(LOCK_NODE);
		List<String> args = new ArrayList<String>();
		args.add(local.get());
		cacheService.eval(script, keys, args);
	}

}
