package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  角色菜单
 *
 * @author chunlei.song@live.com
 * @create 2018-03-19
 */
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@TableField("role_id")
	private String roleId;

    /**
     * 菜单权限id
     */
	@TableField("menu_id")
	private String menuId;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
