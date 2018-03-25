package com.chens.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.sys.AuthRequest;

import java.util.List;

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

    /**
     * 重置密码
     * @param userId 用户id
     * @param isRandom 是否用随机密码串
     * @return
     */
    String restPwd(String userId,boolean isRandom);

    /**
     * 根据角色id获取角色下的用户
     * @param page
     * @param user
     * @return
     */
    List<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user);
}
