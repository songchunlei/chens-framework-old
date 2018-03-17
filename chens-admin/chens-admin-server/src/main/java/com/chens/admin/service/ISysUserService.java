package com.chens.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.AuthRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据账号密码获取用户
     * @param authRequest
     * @return
     * @throws BaseException
     */
    SysUser findByUsername(AuthRequest authRequest) throws BaseException;

    /**
     * 创建账户
     * @return
     */
    boolean createAccount(SysUser sysUser);
}
