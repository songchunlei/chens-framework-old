package com.chens.core.vo;


import java.io.Serializable;

/**
 * 用户信息
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/6
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 4018322190219282262L;

	private String token;

	public UserInfo(String id, String name, String username, String tenantId,String token) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.tenantId = tenantId;
		this.token = token;
	}
	public UserInfo()
	{

	}

	/*
	 * 用户id
	 */
    private String id;
    
    /*
     * 租户id
     */
    private String tenantId;
    
    /*
     * 姓名
     */
    private String name;
    
    /*
     * 用户名
     */
    private String username;



	public void setId(String id) {
		this.id = id;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getTenantId() {
		return tenantId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
