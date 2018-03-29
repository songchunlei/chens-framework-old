package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.UniqueField;
import com.chens.core.vo.BaseEntity;
import com.chens.core.vo.UserInfo;
import org.hibernate.validator.constraints.Email;

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
    private String password;
    @NotNull(message = "{sysuser.username.null}")
    @UniqueField(message = "{sysuser.username.unique}",serviceClass = "com.chens.admin.validator.UserValidator",methodName ="checkUserNameUnique" )
    private String username;
    @NotNull(message = "{sysuser.phone.null}")
    private String phone;
    @NotNull(message = "{sysuser.email.null}")
    @Email
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

    public UserInfo getUserInfo()
    {
        return new UserInfo(this.getId(),this.getName(),this.getUsername(),this.getTenantId(),"");
    }

}
