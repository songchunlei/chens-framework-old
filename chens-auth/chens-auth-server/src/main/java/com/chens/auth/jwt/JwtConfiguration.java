package com.chens.auth.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * 自定义的配置加载类，spring启动后，会讲这个类实例化，并将配置文件中与之对应的值注入进来
 * @auther songchunlei@qq.com
 * @create 2018/3/1
 */
@Component
@ConfigurationProperties("token.jwt")
public class JwtConfiguration {
	/**
	 * 加密token用的key
 	 */
	private String key;
	/**
	 * token来源
	 */
	private String iss;
	/**
	 * 有效期：分钟
	 */
	private int expm;
	/**
	 * 用户端token名
	 */
	private String userheader;
	
	public int getExpm() {
		return expm;
	}
	public void setExpm(int expm) {
		this.expm = expm;
	}

	public String getIss() {
		return iss;
	}
	
	public void setIss(String iss) {
		this.iss = iss;
	}

	private String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserheader() {
		return userheader;
	}

	public void setUserheader(String userheader) {
		this.userheader = userheader;
	}

	public SecretKeySpec getSecretKeySpec() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(this.getKey()
				.getBytes(), SignatureAlgorithm.HS512.getJcaName());
		return secretKeySpec;
	}

	/**
	 * 获取失效时间
	 * @return
	 */
	public Date getExpiration()
	{
		return new Date(System.currentTimeMillis() + this.expm * 1000 * 60);
	}
}
