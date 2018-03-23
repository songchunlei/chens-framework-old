package com.chens.core.entity;

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
	private Long roleId;

    /**
     * 菜单权限id
     */
	@TableField("menu_id")
	private Long menuId;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
