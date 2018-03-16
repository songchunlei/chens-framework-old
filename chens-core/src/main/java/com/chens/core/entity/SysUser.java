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
    @NotNull
    private String name;
    @NotNull
    private String password;
    private String salt;
    @NotNull
    private String username;
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

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解

}
