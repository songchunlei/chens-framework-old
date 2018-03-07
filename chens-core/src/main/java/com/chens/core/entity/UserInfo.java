package com.chens.core.entity;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/6
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 4018322190219282262L;

	/*
	 * 用户id
	 */
    private Long id;
    
    /*
     * 租户id
     */
    private Long tennatId;
    
    /*
     * 姓名
     */
    private String name;
    
    /*
     * 用户名
     */
    private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTennatId() {
		return tennatId;
	}

	public void setTennatId(Long tennatId) {
		this.tennatId = tennatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
