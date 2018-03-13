package com.chens.core.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

// 自定义的配置加载类，spring启动后，会讲这个类实例化，并将配置文件中与之对应的值注入进来
@Component
@ConfigurationProperties("token.jwt")
public class JwtConfiguration {
	// 计算token用的key
	private String key;
	// 在哪里生成的这个token
	private String iss;
	/** 有效期：分钟 */
	private int expm;
	
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

	public SecretKeySpec getSecretKeySpec() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(this.getKey()
				.getBytes(), SignatureAlgorithm.HS512.getJcaName());
		return secretKeySpec;
	}

}
