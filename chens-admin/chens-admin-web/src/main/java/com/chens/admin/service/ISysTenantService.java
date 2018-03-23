package com.chens.admin.service;

import com.chens.core.entity.SysTenant;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.vo.sys.RegisterTenant;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysTenantService extends IService<SysTenant> {

    /**
     * 注册租户
     * 同时创建代理人登录账户
     * @param registerTenant
     * @return
     */
    boolean register(RegisterTenant registerTenant);

}
