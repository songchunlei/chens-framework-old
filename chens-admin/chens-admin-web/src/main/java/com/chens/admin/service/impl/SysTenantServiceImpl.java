package com.chens.admin.service.impl;

import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysTenant;
import com.chens.admin.mapper.SysTenantMapper;
import com.chens.admin.service.ISysTenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.admin.vo.RegisterTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService {

    @Autowired
    private ISysUserService sysUserService;

    @Transactional
    @Override
    public boolean register(RegisterTenant registerTenant) throws BaseException{

        //1.创建租户
        SysTenant sysTenant = registerTenant.getSysTenant();
        if(!this.insert(sysTenant))
        {
            throw new BaseException(BaseExceptionEnum.REGISTER_SYSTENANT_ERROR);
        }
        //放入租户id
        String tenantId = sysTenant.getId();
        //BaseContextHandler.setTenantId(sysTenant.getId());
        //2.创建代理人账户
        SysUser sysUser = registerTenant.getSysUser();
        sysUser.setTenantId(tenantId);
        if(!sysUserService.createAccount(sysUser))
        {
            throw new BaseException(BaseExceptionEnum.REGISTER_SYSUSER_ERROR);
        }
        return true;
    }
}
