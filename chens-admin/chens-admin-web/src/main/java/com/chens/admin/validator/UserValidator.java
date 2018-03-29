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

    public boolean checkUserNameUnique(String userName) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        return this.checkIsNotExist(sysUser);
    }
}
