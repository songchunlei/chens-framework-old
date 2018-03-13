package com.chens.core.vo;

public class JWTToken {
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JWTToken(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	

}
