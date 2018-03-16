package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

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
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "{sysuser.name.null}")
    private String name;
    @NotNull(message = "{sysuser.password.null}")
    private String password;
    private String salt;
    @NotNull(message = "{sysuser.username.null}")
    private String username;
    @NotNull(message = "{sysuser.phone.null}")
    private String phone;
    @NotNull(message = "{sysuser.email.null}")
    private String email;

    @TableField(exist = false)
    private List<SysRole> roles;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
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

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解

}
