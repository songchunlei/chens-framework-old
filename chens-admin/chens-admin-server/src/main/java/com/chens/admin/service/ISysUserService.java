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
    SysUser findByUsername(AuthRequest authRequest) throws BaseException;
}