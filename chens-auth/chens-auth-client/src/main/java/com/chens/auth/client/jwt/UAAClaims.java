package com.chens.auth.client.jwt;

import com.chens.core.constants.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.impl.JwtMap;

import java.util.Date;

/**
 * Claims的抽象类，用于方便实体于Claims的转换
 * @auther songchunlei@qq.com
 * @create 2018/3/1
 */
public class UAAClaims extends JwtMap implements Claims {
	private String[] scope;
	private String grantType = "password";
	//用户账号
	private String userName;
	//租户id
	private String tenantId;
	//用户名称
	private String user;

	public String[] getScope() {
		return scope;
	}

	public void setScope(String[] scope) {
		this.scope = scope;
		setValue(CommonConstants.JWT_TOKEN_SCOPE, this.scope);
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
		setValue(CommonConstants.JWT_TOKEN_GRANTTYPE, this.grantType);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		setValue(CommonConstants.JWT_TOKEN_USERNAME, this.userName);
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
		setValue(CommonConstants.JWT_TOKEN_TENANTID, this.tenantId);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		setValue(CommonConstants.JWT_TOKEN_USER, this.user);
	}

	@Override
	public String getIssuer() {
		return getString(ISSUER);
	}

	@Override
	public Claims setIssuer(String iss) {
		setValue(ISSUER, iss);
		return this;
	}

	@Override
	public String getSubject() {
		return getString(SUBJECT);
	}

	/**
	 * Subject 用于存账户id
	 * @param sub
	 * @return
	 */
	@Override
	public Claims setSubject(String sub) {
		setValue(SUBJECT, sub);
		return this;
	}

	@Override
	public String getAudience() {
		return getString(AUDIENCE);
	}

	@Override
	public Claims setAudience(String aud) {
		setValue(AUDIENCE, aud);
		return this;
	}

	@Override
	public Date getExpiration() {
		return get(Claims.EXPIRATION, Date.class);
	}

	@Override
	public Claims setExpiration(Date exp) {
		setDate(Claims.EXPIRATION, exp);
		return this;
	}

	@Override
	public Date getNotBefore() {
		return get(Claims.NOT_BEFORE, Date.class);
	}

	@Override
	public Claims setNotBefore(Date nbf) {
		setDate(Claims.NOT_BEFORE, nbf);
		return this;
	}

	@Override
	public Date getIssuedAt() {
		return get(Claims.ISSUED_AT, Date.class);
	}

	@Override
	public Claims setIssuedAt(Date iat) {
		setDate(Claims.ISSUED_AT, iat);
		return this;
	}

	@Override
	public String getId() {
		return getString(ID);
	}

	@Override
	public Claims setId(String jti) {
		setValue(Claims.ID, jti);
		return this;
	}

	@Override
	public <T> T get(String claimName, Class<T> requiredType) {
		Object value = get(claimName);
		if (value == null) {
			return null;
		}

		if (Claims.EXPIRATION.equals(claimName)
				|| Claims.ISSUED_AT.equals(claimName)
				|| Claims.NOT_BEFORE.equals(claimName)) {
			value = getDate(claimName);
		}

		if (requiredType == Date.class && value instanceof Long) {
			value = new Date((Long) value);
		}

		if (!requiredType.isInstance(value)) {
			throw new RequiredTypeException("Expected value to be of type: "
					+ requiredType + ", but was " + value.getClass());
		}

		return requiredType.cast(value);
	}

}
