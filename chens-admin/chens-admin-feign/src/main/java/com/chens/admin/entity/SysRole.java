package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@TableName("sys_role")
public class SysRole extends BaseEntity<SysRole> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{role.name.null}",groups = {InsertValid.class, UpdateValid.class})
    @TableField("role_name")
    private String roleName;

    @NotNull(message = "{role.code.null}",groups = {InsertValid.class, UpdateValid.class})
    @TableField("role_code")
    private String roleCode;

    @TableField("is_delete")
    @TableLogic
    private String isDelete;

    @TableField(exist = false)
    private List<SysMenu> sysMenus;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public List<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }
}
