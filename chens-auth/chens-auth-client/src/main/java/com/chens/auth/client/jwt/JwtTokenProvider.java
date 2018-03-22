package com.chens.auth.client.jwt;

import com.alibaba.fastjson.JSONObject;
import com.chens.auth.client.vo.UserInfo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * jwt 工具类
 * 这个博客中描述了jwt是什么东西 http://www.jianshu.com/p/576dbf44b2ae
 * @auther songchunlei@qq.com
 * @create 2018/3/1
 */
@Component
public class JwtTokenProvider {
	// 注入配置类
	private JwtConfiguration configuration;

	public JwtTokenProvider(JwtConfiguration configuration) {
		this.setConfiguration(configuration);
	}

	/**
	 * 获取token
	 * @param request
	 * @return
	 */
	public String getToken(HttpServletRequest request){
		return request.getHeader(configuration.getUserheader());
	}

	/**
	 * 获取用户token-key
	 * @return
	 */
	public String getUserTokenHeader() {
		return configuration.getUserheader();
	}

	/**
	 * 生成token
	 *
	 * @return
	 */
	public String  createToken(Claims claims) {
		String compactJws = Jwts.builder()
				//内容
				.setPayload(JSONObject.toJSONString(claims))
				.compressWith(CompressionCodecs.DEFLATE)
				//加密
				.signWith(SignatureAlgorithm.HS512, configuration.getSecretKeySpec()).compact();
		return compactJws;
	}

	/** token转换 */
	public Claims parseToken(String token) throws Exception{
		return Jwts.parser().setSigningKey(configuration.getSecretKeySpec()).parseClaimsJws(token).getBody();
	}

	public JwtConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(JwtConfiguration configuration) {
		this.configuration = configuration;
	}


}
