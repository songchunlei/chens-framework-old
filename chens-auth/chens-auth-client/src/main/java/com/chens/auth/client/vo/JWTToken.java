package com.chens.auth.client.vo;


import com.chens.auth.vo.IJwtInfo;
import com.chens.core.vo.MenuTree;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JWTToken implements Serializable{
	private String accessToken;
	private List<MenuTree> menus;
	private Map<Long,MenuTree> all;
	private IJwtInfo user;

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

	public JWTToken(String accessToken, List<MenuTree> menus,Map<Long,MenuTree> all,IJwtInfo user) {
		this.accessToken = accessToken;
		this.menus = menus;
		this.all = all;
		this.user = user;
	}

	public List<MenuTree> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuTree> menus) {
		this.menus = menus;
	}

	public Map<Long, MenuTree> getAll() {
		return all;
	}

	public void setAll(Map<Long, MenuTree> all) {
		this.all = all;
	}

	public IJwtInfo getUser() {
		return user;
	}

	public void setUser(IJwtInfo user) {
		this.user = user;
	}
}
