package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.MyValidator;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;
import com.chens.core.vo.UserInfo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@TableName("sys_user")
@MyValidator(
        message = "{sysuser.username.unique}",
        thisclass = SysUser.class,
        serviceClass = "com.chens.admin.validator.UserValidator",
        methodName = "check",
        groups = {InsertValid.class, UpdateValid.class})
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;
    /**
     * 用户姓名
     */
    @NotNull(message = "{sysuser.name.null}",groups = {InsertValid.class, UpdateValid.class})
    private String name;
    /**
     * 密码
     */
    private String password;
    @NotNull(message = "{sysuser.username.null}",groups = {InsertValid.class, UpdateValid.class})
    /**
     * 账号
     */
    private String username;
    @NotNull(message = "{sysuser.phone.null}",groups = {InsertValid.class, UpdateValid.class})
    /**
     * 手机
     */
    private String phone;
    @NotNull(message = "{sysuser.email.null}",groups = {InsertValid.class, UpdateValid.class})

    /**
     * 邮箱
     */
    private String email;

    /**
     * 系统角色id
     */
    @TableField(exist = false)
    private List<String> roles;

    /**
     * 角色id（用于查询角色下用户）
     */
    @TableField(exist = false)
    private String roleId;

    @TableLogic
    @TableField(value = "is_delete")
    private String isDelete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public UserInfo getUserInfo()
    {
        return new UserInfo(this.getId(),this.getName(),this.getUsername(),this.getTenantId(),"");
    }

}
