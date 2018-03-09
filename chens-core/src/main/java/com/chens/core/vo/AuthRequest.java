package com.chens.core.vo;

import java.io.Serializable;

/**
 * 认证请求
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
public class AuthRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4958322063059771295L;
	private String userName;
    private String password;

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
