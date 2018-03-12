package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@TableName("sys_permission")
public class SysPermission extends BaseEntity<SysPermission> {

    private static final long serialVersionUID = 1L;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源父id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 资源类型
     */
    @TableField("permission_type")
    private String permissionType;
    /**
     * 链接
     */
    private String url;
    /**
     * 权限描述
     */
    private String permission;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
