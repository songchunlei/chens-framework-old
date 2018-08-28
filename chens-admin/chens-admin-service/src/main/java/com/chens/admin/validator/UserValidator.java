package com.chens.admin.validator;

import com.chens.admin.entity.SysUser;
import com.chens.admin.service.ISysUserService;
import com.chens.core.util.ApplicationContextUtil;
import com.chens.core.validator.BaseValidator;

/**
 * 用户自定义校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
public class UserValidator extends BaseValidator<ISysUserService,SysUser>{

    public UserValidator() {
        //自定义初始化
        if(service==null)
        {
            service = ApplicationContextUtil.getBeanByClass(ISysUserService.class);
        }
    }

    public boolean check(SysUser sysUser) throws Exception {
        SysUser query = new SysUser();
        query.setUsername(sysUser.getUsername());
        query.setId(sysUser.getId());
        return this.checkIsNotExist(query);
    }
}
